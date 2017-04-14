package net.xinqushi.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.mapper.CityMapper;
import net.xinqushi.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	private static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	@Autowired
	private CityMapper cityMapper;

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
			logger.info("查询城市列表计数失败");
			throw new CommonException("查询城市列表计数失败");
		}
	}

}
