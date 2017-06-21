package net.xinqushi.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.xinqushi.common.constants.JMSConstants;


/**
 * 消息接收工具
 * @author yangli
 * 2017年4月24日-下午3:32:07
 */
//@Component
public class MessageReceiver {
	
	private static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
//	@PostConstruct
	public void init(){
		try {
			new MessageReceiver().receiveMessage();
		} catch (Exception e) {
			logger.error("fail to init messagereceiver", e);
		}
	}
	
	public void receiveMessage(){
		Connection connection = null;
		try {
			try {
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMSConstants.DEFAULT_HOST);
				connection = connectionFactory.createConnection();
			} catch (Exception e) {
				logger.error("fail to get connection with activemq server", e);
			}
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createTopic(JMSConstants.TOPIC_NAME);
			
			MessageConsumer consumer = session.createConsumer(destination);
			
			consumeMessageAndClose(connection, session, consumer);
		} catch (Exception e) {
			logger.error("fail to process activemq server", e);
		}
	}
	
	protected void consumeMessageAndClose(Connection connection,
			Session session, MessageConsumer consumer) throws JMSException{
		do {
			Message message = consumer.receive(1000);
			if ("close".equals(message)) {
				consumer.close();
				session.close();
				connection.close();
			}
			if (null != message) {
				onMessage(message);
			}
		} while (true);
	}
	
	public void onMessage(Message message){
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String msg = textMessage.getText();
				System.err.println("Received: " + msg);
			}
		} catch (Exception e) {
			logger.error("fail while run onMessage", e);
		}
	}
	
	public static void main(String[] args) {
		MessageReceiver rm = new MessageReceiver();
		rm.receiveMessage();
	}
}
