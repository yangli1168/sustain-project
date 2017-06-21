package net.xinqushi.scheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import net.xinqushi.cache.CityCache;

@Component
public class PopulateCacheTask extends QuartzJobBean {

	private static Logger logger = LoggerFactory.getLogger(PopulateCacheTask.class);
	
	private CityCache cityCache;

	private void preloadDependencies(JobExecutionContext context) throws Exception {
		ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext()
		        .get("applicationContext");
		cityCache = applicationContext.getBean(CityCache.class);

		if (cityCache == null) {
			throw new Exception("Fail to preload dependencies");
		}
	}

	@Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			preloadDependencies(context);
			
			long start = System.currentTimeMillis();
			logger.info("----定时任务：'检测缓存城市信息'开始执行----" + start + "-------");
			
			this.cityCache.checkManagementEventAgainstCache();
			
			logger.info("------定时任务：'检测缓存城市信息'执行完成, 耗时" + (System.currentTimeMillis() - start) + "毫秒------");
		} catch (Exception e) {
			logger.error("Fail to process cache update", e);
		}
    }

}
