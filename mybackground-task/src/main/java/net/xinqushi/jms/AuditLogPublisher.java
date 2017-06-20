package net.xinqushi.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import net.xinqushi.orm.entity.AuditLog;
/**
 * 消息队列日志发布类
 * @author yangli
 */
@Component(value = "auditLogPublisher")
public class AuditLogPublisher {
    
    private static Logger logger = LoggerFactory.getLogger(AuditLogPublisher.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Autowired
    private ActiveMQQueue auditLogDestination;
    
    public void sendAuditLogsToQueue(final AuditLog log) {
        if (log != null) {
            try {
                jmsTemplate.send(auditLogDestination, new MessageCreator() {

                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage msg = session.createTextMessage();
                        msg.setText(JSON.toJSONString(log));
                        return msg;
                    }

                });
            } catch (Exception e) {
                logger.error("Fail to send auditlog to messsage queue", e);
            }
        }
    }
    
}
