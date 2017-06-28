package net.xinqushi.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import net.xinqushi.api.cache.ManagementCacheManager;
import net.xinqushi.common.constants.StatusCodeConstants;
import net.xinqushi.common.response.RestResponse;

@Component
public class RestLogoutHandler extends SimpleUrlLogoutSuccessHandler {

	private static Logger logger = LoggerFactory.getLogger(RestLogoutHandler.class);

	@Autowired
	private ManagementCacheManager userCacheManager;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	        throws IOException, ServletException {

		String token = request.getHeader(StatusCodeConstants.REQUEST_TOKEN_KEY);
		if (token != null) {
			try {
				userCacheManager.deleteManUserElement(token);

				RestResponse resp = new RestResponse();
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.getWriter().write(JSON.toJSONString(resp));
				response.flushBuffer();
			} catch (Exception e) {
				logger.error("Fail to send logout ok response {}", e.getMessage());
			}
		} else {
			logger.error("Logout with no token");
		}

	}

}
