package net.xinqushi.scheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 用于测试定时任务
 * @author yangli
 * 2017年4月26日-下午2:27:19
 */
public class TestTask extends QuartzJobBean{
	
	private static final Logger logger = LoggerFactory.getLogger(TestTask.class);
	
	/**
	 * 调度业务的方法
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.err.println(System.currentTimeMillis() + " -> 我是测试定时任务的方法");
	}
}
