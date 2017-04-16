package net.xinqushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.common.response.RestResponse;
import net.xinqushi.service.CityService;

/**
 * 城市管理
 * @author yangli
 */
@RestController
@RequestMapping(value = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public RestResponse getCityList(@RequestParam(value = "cityName", required = false)String cityName,
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
	
	
}
