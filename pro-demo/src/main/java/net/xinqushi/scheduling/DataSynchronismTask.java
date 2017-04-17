package net.xinqushi.scheduling;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSON;

import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.mapper.CityMapper;
import net.xinqushi.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;

public class DataSynchronismTask extends QuartzJobBean{
	private static final Logger logger = LoggerFactory.getLogger(DataSynchronismTask.class);
	
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
		
		if (logger.isInfoEnabled()) {
			logger.info("数据同步任务开始执行----" + System.currentTimeMillis() + "------");
		}
		
		try {
			preloadDependencies(context);
			Jedis jedis = JedisPoolUtil.getJedis();
			
			List<City> cityList = cityMapper.getCityList(null, null, null);
			String set = jedis.set("city:list:test", JSON.toJSONString(cityList));
			logger.info(set);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
