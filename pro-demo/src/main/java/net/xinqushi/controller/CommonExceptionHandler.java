package net.xinqushi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.common.response.RestResponse;

/**
 * 如有其它异常类型需特殊处理请自定义扩展
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionHandler {

	private static Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@ExceptionHandler(CommonException.class)
	public RestResponse handleUtooException(CommonException e){
		RestResponse restp = new RestResponse();
		restp.setCode(e.getStatusCode());
		restp.setMsg(e.getMessage());
		return restp;
	}
	
	@ExceptionHandler(Exception.class)
	public RestResponse handleException(Exception e){
		RestResponse restp = new RestResponse();
		String msg = e.getMessage()!=null&& e.getMessage().length()>0 ? e.getMessage() :"内部错误";
		log.error(msg, e);
		restp.setCode(500);
		restp.setMsg(msg);
		return restp;
	}
	
}
