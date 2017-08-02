package net.xinqushi.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.xinqushi.orm.entity.Activity;
import net.xinqushi.orm.mapper.ActivityMapper;

@Component(value = "activityCheckReceiver")
public class ActivityCheckReceiver implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(ActivityCheckReceiver.class);

	@Autowired
	private ActivityMapper activityMapper;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMsg = (TextMessage) message;
			String text = textMsg.getText();

			logger.info("Activity check receives message: {}", text);

			Long activityId = Long.parseLong(text);
			Activity activity = this.activityMapper.selectByPrimaryKey(activityId);
			if (1 == activity.getStatus()) {
				activity.setStatus(0);
				this.activityMapper.updateByPrimaryKeySelective(activity);
			} else if (0 == activity.getStatus()) {
				activity.setStatus(2);
				this.activityMapper.updateByPrimaryKeySelective(activity);
			} else {
				logger.info("Activity {} in status {}, no action needed", activity.getActivityId(),
						activity.getStatus());
			}

		} catch (Exception e) {
			logger.error("Fail to process activity message from MQ", e);
		}
	}

}
