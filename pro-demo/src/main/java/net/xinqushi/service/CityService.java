package net.xinqushi.service;

import java.util.List;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.City;

public interface CityService {
	
	/** 查询城市列表*/
	public List<City> getCityList(String cityName, Integer pageNum, Integer pageSize) throws CommonException;
	
	/** 查询城市列表计数*/
	public int countCityList(String cityName) throws CommonException;
}
