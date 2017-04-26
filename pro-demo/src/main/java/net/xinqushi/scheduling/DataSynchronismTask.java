package net.xinqushi.scheduling;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.mapper.CityMapper;
import net.xinqushi.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
@Component
public class DataSynchronismTask extends QuartzJobBean{
	private static Logger logger = LoggerFactory.getLogger(DataSynchronismTask.class);
	
	private CityMapper cityMapper = null;
	
	private void preloadDependencies(JobExecutionContext context) throws Exception {
		ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext()
		        .get("applicationContext");
		cityMapper = applicationContext.getBean(CityMapper.class);

		if (cityMapper == null) {
			throw new Exception("Fail to preload dependencies");
		}
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Long startTime = null;
		
		if (logger.isInfoEnabled()) {
			startTime = System.currentTimeMillis();
			logger.info("定时任务：'数据同步'开始执行----" + startTime + "------");
		}
		
		try {
			preloadDependencies(context);
			Jedis jedis = JedisPoolUtil.getJedis();
			
			List<City> cityList = cityMapper.getCityList(null, null, null);
			String set = jedis.set("city:list:test", JSON.toJSONString(cityList));
			logger.info("------数据同步任务执行完成, 耗时" + (System.currentTimeMillis() - startTime) + "毫秒------");
		} catch (Exception e) {
			logger.error("fail to synchronism data with redis", e);
		}
	}
	
	
}
