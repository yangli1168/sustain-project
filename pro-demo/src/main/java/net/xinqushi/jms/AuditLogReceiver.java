package net.xinqushi.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import net.xinqushi.orm.entity.AuditLog;
import net.xinqushi.orm.mapper.AuditLogMapper;

@Component(value = "auditLogReceiver")
public class AuditLogReceiver implements MessageListener {
    
    private static Logger logger = LoggerFactory.getLogger(AuditLogReceiver.class);

    @Autowired
    private AuditLogMapper auditLogMaper;
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMsg = (TextMessage) message;
            String text = textMsg.getText();

            AuditLog log = JSON.parseObject(text, AuditLog.class);
            
            auditLogMaper.insertSelective(log);
        } catch (Exception e) {
            logger.error("Fail to process audit log from MQ", e);
        }
    }

}
