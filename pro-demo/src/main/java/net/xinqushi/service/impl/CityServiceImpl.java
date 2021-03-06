package net.xinqushi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.xinqushi.api.cache.CommonCacheManager;
import net.xinqushi.common.constants.CacheConstants;
import net.xinqushi.common.constants.TypeReferenceConstants;
import net.xinqushi.common.enums.ManagementEventType;
import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.jms.ManagementEventMarker;
import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.mapper.CityMapper;
import net.xinqushi.service.CityService;
import net.xinqushi.util.JedisPoolUtil;
import net.xinqushi.util.Pair;

@Service
public class CityServiceImpl implements CityService {

	private static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	@Autowired
	private CityMapper cityMapper;
	
	@Autowired
	private ManagementEventMarker managementEventMarker;
	
	@Autowired
	private CommonCacheManager cacheManager;

	/** 计算分页初始 */
	private int calcPageStart(int pageSize, int pageNum) {
		if (pageNum <= 0) {
			pageNum = 1;
		}
		return (pageNum - 1) * pageSize;
	}

	@Override
	public List<City> getCityList(String cityName, Integer pageNum, Integer pageSize) throws CommonException {
			List<City> cityList = cityMapper.getCityList(cityName, calcPageStart(pageSize, pageNum), pageSize);
			if (cityList.isEmpty() || cityList == null) {
				throw new CommonException("查询城市列表信息失败");
			}
			return cityList;
	}

	@Override
	public int countCityList(String cityName) throws CommonException {
		try {
			return cityMapper.countByName(cityName);
		} catch (Exception e) {
			logger.info("查询城市列表计数失败", e);
			throw new CommonException("查询城市列表计数失败");
		}
	}

	@Override
	public void updateCityInfo(City mCity) throws CommonException {
		try {
			cityMapper.updateByPrimaryKeySelective(mCity);
			//将更新标志存入缓存用于更新任务
			managementEventMarker.appendManagmentEvent(CacheConstants.CITY_PARAM_UPDATED, ManagementEventType.CITY_LIST_UPDATED, mCity.getId() + "");
//			JedisPoolUtil.getJedis().hset(CacheConstants.CITY_PARAM_UPDATED, ManagementEventType.CITY_LIST_UPDATED.name(), mCity.getId() + "");
		} catch (Exception e) {
			logger.info("修改城市信息失败", e);
			throw new CommonException("修改城市信息失败");
		}
		
	}

	@Override
	public Pair<List<City>, Integer> getCityListFromLocalRedis(String cityName, Integer pageNum, Integer pageSize) throws CommonException {
		//从本服务创建jedis从缓存取出
		String cityStr = JedisPoolUtil.getJedis().get(CacheConstants.CITY_KEY);
		
		Pair<List<City>, Integer> pageInfo = convertCity(cityName, pageNum, pageSize, cityStr);
		return pageInfo;
	}
	
	@Override
	public Pair<List<City>, Integer> getCityListFromRedisServer(String cityName, Integer pageNum, Integer pageSize) throws CommonException {
		//从缓存服务中取出
		String cityStr = this.cacheManager.getCacheValue(CacheConstants.CITY_KEY);
		//转换
		if (null == cityStr) {
			throw new CommonException("调用缓存服务失败");
		}
		Pair<List<City>, Integer> pageInfo = convertCity(cityName, pageNum, pageSize, cityStr);
		return pageInfo;
	}

	private Pair<List<City>, Integer> convertCity(String cityName, Integer pageNum, Integer pageSize, String cityStr) {
		List<City> cities = JSON.parseObject(cityStr, TypeReferenceConstants.cityTypeRef);
		
		//按条件进行筛选
		List<City> currentList = new ArrayList<City>();
		int start = ((pageNum<1?1:pageNum) - 1) * pageSize;
		if (null != cityName) {
			for (City city : cities) {
				if (cityName.equals(city.getName())) {
					currentList.add(city);
					break;
				}
			}
		} else {
			for(int i=0; i<cities.size(); i++){
				City city = cities.get(i);
				if (i >= start && i <= start + pageSize - 1) {
					currentList.add(city);
				}
			}
		}
		//
		Pair<List<City>, Integer> pageInfo = new Pair<List<City>, Integer>();
		pageInfo.setValue1(currentList);
		pageInfo.setValue2(currentList.size());
		return pageInfo;
	}

}
