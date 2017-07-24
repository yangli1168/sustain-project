package net.xinqushi.cache.expiredetection;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MsgProducer {

    private static Logger logger = LoggerFactory.getLogger(MsgProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String message, Destination destination) {
        
        logger.info("Sending msg {}", message);
        
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}
