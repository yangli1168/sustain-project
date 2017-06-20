package net.xinqushi.controller;
/**
 * 日志服务
 * @author yangli
 * 2017年6月19日-上午11:12:42
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.common.response.RestResponse;
import net.xinqushi.orm.entity.AuditLog;
import net.xinqushi.service.AuditLogService;
import net.xinqushi.util.PageUtils;
import net.xinqushi.util.Pair;
@RestController
@RequestMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuditLogController{
	
	@Autowired
	private AuditLogService auditLogService;
	
	private PageUtils pageUtils;
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public RestResponse testLog(@RequestParam("param") String param) throws Exception{
		
		JSONObject jo = JSON.parseObject(param);
		Long reservId = jo.getLong("reservId");
		Integer userId = jo.getInteger("userId");
		String remark = jo.getString("remark");
		
		if (null == reservId || null == userId || null == remark) {
			throw new CommonException("无效的请求");
		}
		this.auditLogService.testMethod(reservId, userId, remark);
		return RestResponse.ok();
	}
	
	
	@RequestMapping(value = "/reservId", method = RequestMethod.POST)
	public RestResponse listLogs(@RequestParam("param") String param) throws Exception{
		
		JSONObject jo = JSON.parseObject(param);
		Long reservId = jo.getLong("reservId");
	
		Pair<Integer, Integer> pageRequestInfo = this.pageUtils.getPageRequestInfo(jo);
		
		RestResponse response = new RestResponse();
		Pair<List<AuditLog>, Integer> pair = this.auditLogService.listAuditLogsByReservId(reservId, pageRequestInfo.getValue1(), pageRequestInfo.getValue2());
		response.setAppendix(pair.getValue2());
		response.setData(pair.getValue1());
		
		return response;
	}
	
	
}
