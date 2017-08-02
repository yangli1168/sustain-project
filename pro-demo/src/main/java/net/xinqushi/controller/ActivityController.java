package net.xinqushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import net.xinqushi.common.response.RestResponse;
import net.xinqushi.orm.entity.Activity;
import net.xinqushi.service.ActivityService;

/**
 * 活动服务
 * @author yangli
 * 2017年8月2日-上午9:52:52
 */
@RestController
@RequestMapping(value = "/xinqushi/activity", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public RestResponse add(@RequestParam("param") String param) throws Exception{
		
		Activity activity = JSON.parseObject(param, Activity.class);
		return RestResponse.ok(this.activityService.addActivity(activity));
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public RestResponse list(@RequestParam("subject")String subject,
							 @RequestParam("status")Integer status,
							 @RequestParam("type")String type,
							 @RequestParam("pageNum")Integer pageNum,
							 @RequestParam("pageSize")Integer pageSize) throws Exception{
		
		return RestResponse.ok(this.activityService.searchActivities(subject, status, type, pageNum, pageSize));
	}
	
	
}
