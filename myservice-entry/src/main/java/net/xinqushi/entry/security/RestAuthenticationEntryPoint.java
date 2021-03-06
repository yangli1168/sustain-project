package net.xinqushi.entry.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import net.xinqushi.entry.vo.RestResponse;
import net.xinqushi.entry.vo.StatusCodeConstants;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException arg2) throws IOException, ServletException {
		RestResponse resp = new RestResponse();
		if (request.getAttribute("header-error") != null) {
			if ("400".equals(request.getAttribute("header-error") + "")) {
				String platform = request.getHeader(StatusCodeConstants.REQUEST_PLATFORM_KEY);
				resp.setCode(408);
				if (platform != null && platform.toLowerCase().contains("android")) {
					resp.setMsg("请重新登录，以升级至最新版本");
				} else {
					resp.setMsg("请升级至app最新版本");
				}				
				
			} else {
				resp.setCode(401);
				resp.setMsg("请您登录");
			}
		}
		
		try {
			response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEADER");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, user-token, Content-Type, Accept, version, type, platform");
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(JSON.toJSONString(resp));
			response.flushBuffer();	
		} catch (Exception e) {
			logger.error("Fail to send 401 response {}", e.getMessage());
		}
	}
	
}
