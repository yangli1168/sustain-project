package net.xinqushi.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import net.xinqushi.common.constants.CacheConstants;
import net.xinqushi.common.enums.ManagementEventType;
import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.jms.ManagementEventPublisher;
import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.mapper.CityMapper;
import net.xinqushi.util.JedisPoolUtil;
import net.xinqushi.vo.ManagementEvent;

/**
 * 城市信息缓存
 * @author yangli
 */
@Component
public class CityCache {
	
	private static Logger logger = LoggerFactory.getLogger(CityCache.class);
	
	@Autowired
	private CityMapper cityMapper;
	
	@Autowired
	private ManagementEventPublisher managementEventPublisher;
	
	@PostConstruct
	public void init() throws CommonException {
		try {
				this.cacheCities();
		} catch (Exception e) {
			logger.error("Fail to initialize cityInfo", e);
		}
	}
	
	public void checkManagementEventAgainstCache() {
		try {
			//从缓存取出val
			String val = JedisPoolUtil.getJedis().hget(CacheConstants.CITY_PARAM_UPDATED, 
					ManagementEventType.CITY_LIST_UPDATED.name());
			//从缓存删除val
			JedisPoolUtil.getJedis().hdel(CacheConstants.CITY_PARAM_UPDATED, 
					ManagementEventType.CITY_LIST_UPDATED.name());
			//判断:若缓存中无标志，则缓存城市列表
			if (null != val) {
				this.cacheCities();
			}
		} catch (Exception e) {
			logger.error("Fail to repopulate city list cache", e);
		}
	}
	
	/**
	 * 缓存所有非删除状态城市列表
	 * @return
	 * @throws CommonException
	 */
	public List<City> cacheCities() throws CommonException{
		try {
			List<City> cityList = this.cityMapper.getCityList(null, null, null);
			if (cityList == null) {
				cityList = new ArrayList<City>();
			}
			JedisPoolUtil.getJedis().set(CacheConstants.CITY_KEY, JSON.toJSONString(cityList));
			return cityList;
		} catch (Exception e) {
			logger.error("Fail to cache lines", e);
			throw new CommonException("Fail to cache cities");
		} finally {
			ManagementEvent event = new ManagementEvent();
			event.setType(ManagementEventType.CITY_LIST_UPDATED);
			this.managementEventPublisher.publishManagementEvent(event);
		}
	}
}
