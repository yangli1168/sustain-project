package net.xinqushi.cache.expiredetection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPubSub;
@Component
public class KeyExpireSubscribe extends JedisPubSub {

    private static Logger logger = LoggerFactory.getLogger(KeyExpireSubscribe.class);
    
    @Value("${redis.check.pattern.activity}")
    private String activityPatternStrs;
    
    @Autowired
    private MsgProducer msgProducer;
    
    @Autowired
    private Destination activityDestination;
    
    @PostConstruct
    public void init() {
    	logger.info("Checking reservation pattern {}", this.activityPatternStrs);
    }
    
	public void onPMessage(String pattern, String channel, String message) {		
		
		sendMsg2activityCheckMQ(message);
	}
	
	private void sendMsg2activityCheckMQ(String message) {
		Pattern mpattern = Pattern.compile(activityPatternStrs);
		
		logger.info("Got activity_check message {}", message);
		
		if (message != null) {
			try {
				Matcher matcher = mpattern.matcher(message);
				if (matcher.matches() && matcher.groupCount() >= 1) {
					msgProducer.sendMessage(matcher.group(1), activityDestination);
				} else {
					logger.error("No match for activity_check");
				}
			} catch (Exception e) {
				logger.error("Fail to send activity_check message", e);
			}
		}
	}

}
