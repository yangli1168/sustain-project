package net.xinqushi.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import net.xinqushi.vo.ManagementEvent;
/**
 * 事件发布工具类
 * @author yangli
 */
@Component(value = "managementEventPublisher")
public class ManagementEventPublisher {

	private static Logger logger = LoggerFactory.getLogger(ManagementEventPublisher.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ActiveMQTopic managementEventDestination;

	public void publishManagementEvent(final ManagementEvent event) {
		if (event != null) {
			try {
				jmsTemplate.send(managementEventDestination, new MessageCreator() {

					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage msg = session.createTextMessage();
						msg.setText(JSON.toJSONString(event));
						return msg;
					}

				});
			} catch (Exception e) {
				logger.error("Fail to publish management event", e);
			}
		}
	}

}
