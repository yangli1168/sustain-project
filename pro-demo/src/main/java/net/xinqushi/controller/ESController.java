package net.xinqushi.controller;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.common.response.RestResponse;
import net.xinqushi.orm.entity.City;
import net.xinqushi.service.ElasticSearchService;

/**
 * 
 * @author yangli
 * @date 2017年4月16日 - 下午7:21:29
 * @Description:es服务
 */
@RestController
@RequestMapping(value = "/es")
public class ESController {
	
	@Autowired
	private ElasticSearchService elasticSearchService;
	
	private static final TypeReference<List<City>> cityTypeRef = new TypeReference<List<City>>() {
	}; 
	
	@RequestMapping(value = "/put")
	public RestResponse putDoc(@RequestParam(value = "param", required = false)String param){
		try {
			if (null != param) {
				List<City> cities = JSON.parseObject(param, cityTypeRef);
				elasticSearchService.putDoc(cities);
			}
			elasticSearchService.putDoc(null);
			return RestResponse.ok();
		} catch (CommonException e) {
			return RestResponse.build(e.getStatusCode(), e.getMessage());
		}
	}
	
	@RequestMapping(value = "/test")
	public RestResponse test2(){
		return RestResponse.build(200, "欢迎测试springboot热部署功能[修改.v2]");
	}
	
	@RequestMapping(value = "/get/{index}/{type}/{id}", method = RequestMethod.GET)
	public RestResponse gerInfo(@PathVariable("index")String index,
								@PathVariable("type")String type,
								@PathVariable("id")String id){
		try {
			String str = elasticSearchService.getIndexById(index, type, id);
			City city = JSON.parseObject(str, City.class);
			if (null == city) {
				throw new CommonException("索引中无此记录");
			}
			return RestResponse.ok(city);
		} catch (CommonException e) {
			return RestResponse.build(e.getStatusCode(), e.getMessage());
		}
	}
	
}
