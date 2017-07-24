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
    
    @Value("${redis.check.pattern.reserv}")
    private String reservCheckPatternStr;
    
    @Value("${redis.check.pattern.dwaiting}")
    private String driverWaitingPatternStrs;
    
    @Autowired
    private MsgProducer msgProducer;
    
    @Autowired
    private Destination reservCheckDestination;
    
    @Autowired
    private Destination driverWatingDestination;
    
    @PostConstruct
    public void init() {
    	logger.info("Checking reservation pattern {}, {}", this.reservCheckPatternStr, this.driverWaitingPatternStrs);
    }
    
	public void onPMessage(String pattern, String channel, String message) {		
		sendMsg2ReservCheckMQ(message);
		
		sendMsg2DriverWaitingMQ(message);
	}

	private void sendMsg2ReservCheckMQ(String message) {
		Pattern mpattern = Pattern.compile(reservCheckPatternStr);
	    
	    logger.info("Got reserv_check message {}", message);
	    
	    if (message != null) {
	        try {
	            Matcher matcher = mpattern.matcher(message);
	            if (matcher.matches() && matcher.groupCount() >= 1) {
	                msgProducer.sendMessage(matcher.group(1), reservCheckDestination);
	            } else {
	                logger.error("No match for reserv_check");
	            }
	        } catch (Exception e) {
	            logger.error("Fail to send reserv_check message", e);
	        }
	    }
	}
	
	private void sendMsg2DriverWaitingMQ(String message) {
		Pattern mpattern = Pattern.compile(driverWaitingPatternStrs);
		
		logger.info("Got driver_waiting message {}", message);
		
		if (message != null) {
			try {
				Matcher matcher = mpattern.matcher(message);
				if (matcher.matches() && matcher.groupCount() >= 1) {
					msgProducer.sendMessage(matcher.group(1), driverWatingDestination);
				} else {
					logger.error("No match for driver_waiting");
				}
			} catch (Exception e) {
				logger.error("Fail to send driver_waiting message", e);
			}
		}
	}
	
}
