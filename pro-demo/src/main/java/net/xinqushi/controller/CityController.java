package net.xinqushi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.common.response.RestResponse;
import net.xinqushi.orm.entity.City;
import net.xinqushi.service.CityService;
import net.xinqushi.util.Pair;

/**
 * 城市管理
 * @author yangli
 */
@RestController
@RequestMapping(value = "/xinqushi/city", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value = "/mapper/list", method = RequestMethod.GET)
	public RestResponse getCityListFromMySql(@RequestParam(value = "cityName", required = false)String cityName,
							@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
							@RequestParam(value = "pageSize", defaultValue = "20")Integer pageSize){
		RestResponse response = new RestResponse();
		try {
			response.setData(cityService.getCityList(cityName, pageNum, pageSize));
			response.setAppendix(cityService.countCityList(cityName));
			 return response;
		} catch (CommonException e) {
			return RestResponse.build(e.getStatusCode(), e.getMessage());
		}
	}
	
	@RequestMapping(value = "/cache/local/list", method = RequestMethod.POST)
	public RestResponse getCityListFromLocalRedis(@RequestParam(value = "cityName", required = false)String cityName,
			@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "20")Integer pageSize){
		RestResponse response = new RestResponse();
		try {
			Pair<List<City>, Integer> pageInfo = cityService.getCityListFromLocalRedis(cityName, pageNum, pageSize);
			response.setAppendix(pageInfo.getValue2());
			response.setData(pageInfo.getValue1());
			return response;
		} catch (CommonException e) {
			return RestResponse.build(e.getStatusCode(), e.getMessage());
		}
	}
	
	@RequestMapping(value = "/cache/server/list", method = RequestMethod.POST)
	public RestResponse getCityListFromRedisServer(@RequestParam(value = "cityName", required = false)String cityName,
			@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "20")Integer pageSize){
		RestResponse response = new RestResponse();
		try {
			Pair<List<City>, Integer> pageInfo = cityService.getCityListFromRedisServer(cityName, pageNum, pageSize);
			response.setAppendix(pageInfo.getValue2());
			response.setData(pageInfo.getValue1());
			return response;
		} catch (CommonException e) {
			return RestResponse.build(e.getStatusCode(), e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public RestResponse updateCity(@RequestParam("param")String param){
		City mCity = JSON.parseObject(param, City.class);
		try {
			cityService.updateCityInfo(mCity);
			return RestResponse.ok();
		} catch (CommonException e) {
			return RestResponse.build(e.getStatusCode(), e.getMessage());
		}
	}
	
	
}
