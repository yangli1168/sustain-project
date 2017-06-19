package net.xinqushi.service;

import java.util.List;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.City;
import net.xinqushi.util.Pair;

public interface CityService {
	
	/** 查询城市列表:mysql*/
	public List<City> getCityList(String cityName, Integer pageNum, Integer pageSize) throws CommonException;
	
	/** 查询城市列表计数:mysql*/
	public int countCityList(String cityName) throws CommonException;
	
	/** 修改城市信息*/
	public void updateCityInfo(City mCity) throws CommonException;
	
	/** 查询城市列表:本服务redis*/
	public Pair<List<City>, Integer> getCityListFromLocalRedis(String cityName, Integer pageNum, Integer pageSize) throws CommonException;
	
	/** 查询城市列表:redis服务器*/
	public Pair<List<City>, Integer> getCityListFromRedisServer(String cityName, Integer pageNum, Integer pageSize) throws CommonException;
}
