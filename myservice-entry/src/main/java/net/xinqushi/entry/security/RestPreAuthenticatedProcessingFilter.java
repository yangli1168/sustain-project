package net.xinqushi.entry.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.AntPathMatcher;

import net.xinqushi.entry.cache.CacheManager;
import net.xinqushi.entry.util.Configuration;
import net.xinqushi.entry.vo.ManagementUserElement;
import net.xinqushi.entry.vo.StatusCodeConstants;
import net.xinqushi.entry.vo.UserElement;
import net.xinqushi.entry.vo.UserType;


public class RestPreAuthenticatedProcessingFilter extends
		AbstractPreAuthenticatedProcessingFilter {
	
	private static Logger logger = LoggerFactory.getLogger(RestPreAuthenticatedProcessingFilter.class);

	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private Configuration configuration;

	private AntPathMatcher matcher = new AntPathMatcher();

	private List<String> noneSecurityList;
	
	public void setNoneSecurityList(List<String> noneSecurityList) {
		this.noneSecurityList = noneSecurityList;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		if ("OPTIONS".equals(request.getMethod())) {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SOMEONE");
			authorities[0] = authority;
			return new RestAuthenticationToken(Arrays.asList(authorities));
		} else if (isNoneSecurity(request.getRequestURI().toString()) && !"/utoo/client/v2/reserv".equals(request.getRequestURI().toString())) {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SOMEONE");
			authorities[0] = authority;
			return new RestAuthenticationToken(Arrays.asList(authorities));
		}
		
		String version = request.getHeader(StatusCodeConstants.REQUEST_VERSION_KEY);
		String type = request.getHeader(StatusCodeConstants.REQUEST_TYPE_KEY);
		String platform = request.getHeader(StatusCodeConstants.REQUEST_PLATFORM_KEY);
		logger.info("Request {} info {}|{}|{}|{}", request.getRequestURI(), request.getHeader("Content-type"), version, type, platform);
		
		if (configuration.isVersionCheck()) {
			if (version == null || type == null || platform == null) {
				request.setAttribute("header-error", 400);
			} else {
				if (type.equals(UserType.CAROWNER.ordinal() + "") && platform.toLowerCase().contains("ios")) {
					if (configuration.getSupportedVersionIosDriver().compareTo(version) > 0) {
						request.setAttribute("header-error", 400);
					}
				} else if (type.equals(UserType.CAROWNER.ordinal() + "") && platform.toLowerCase().contains("android")) {
					if (configuration.getSupportedVersionAndDriver().compareTo(version) > 0) {
						request.setAttribute("header-error", 400);
					}
				} else if (type.equals(UserType.PASSENGER.ordinal() + "") && platform.toLowerCase().contains("ios")) {
					if (configuration.getSupportedVersionIosPassenger().compareTo(version) > 0) {
						request.setAttribute("header-error", 400);
					}
				} else if (type.equals(UserType.PASSENGER.ordinal() + "") && platform.toLowerCase().contains("android")) {
					if (configuration.getSupportedVersionAndPassenger().compareTo(version) > 0) {
						request.setAttribute("header-error", 400);
					}
				} else if (platform.toLowerCase().contains("dispatcher")) {
					//TODO 
				} else {
					request.setAttribute("header-error", 400);
				}
			}
		}
		//临时处理 解决android crash
		try {
	        if(request.getRequestURI().toString().equals("/utoo/client/user")){
	        	request.removeAttribute("header-error");
	        }
        } catch (Exception e) {
	        logger.error("/utoo/client/user failed",e);
        }
		String token = request.getHeader(StatusCodeConstants.REQUEST_TOKEN_KEY);
		if (!request.getRequestURI().toString().contains("/manage/")) {
			if (request.getAttribute("header-error") == null) {
				if (token != null && !token.trim().isEmpty()) {
					UserElement ue = cacheManager.getUserTokenContent(token);
				    
					if (ue instanceof UserElement) {
	                    GrantedAuthority authority = new SimpleGrantedAuthority(
	                            "ROLE_USER");
	                    authorities[0] = authority;
	                    RestAuthenticationToken authToken = new RestAuthenticationToken(
	                            Arrays.asList(authorities));
	                    authToken.setUser(ue);
	                    return authToken;
					} else {
						request.setAttribute("header-error", 401);
					}
				} else {
					if (!request.getRequestURI().toString().contains("/msg/latest")) {
						request.setAttribute("header-error", 401);
					}
					
				    logger.warn("Got no token from request header {}", request.getRequestURI());
				}
			}
		} else {
			if (token != null && !token.trim().isEmpty()) {
				ManagementUserElement ue = cacheManager.getManagementTokenContent(token);
			    
				if (ue instanceof ManagementUserElement) {
                    GrantedAuthority authority = new SimpleGrantedAuthority(
                            "ROLE_USER");
                    authorities[0] = authority;
                    RestAuthenticationToken authToken = new RestAuthenticationToken(
                            Arrays.asList(authorities));
                    authToken.setManUser(ue);
                    return authToken;
				} else {
					request.setAttribute("header-error", 401);
				}
			} else {
			    logger.warn("Got no token from request header of management client");
			}
		}
		
		if (request.getAttribute("header-error") == null) {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SOMEONE");
			authorities[0] = authority;
		} else {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_NONE");
			authorities[0] = authority;
		}
		RestAuthenticationToken authToken = new RestAuthenticationToken(Arrays.asList(authorities));
		return authToken;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return null;
	}

	private boolean isNoneSecurity(String uri) {
		boolean result = false;
		if (this.noneSecurityList != null) {
			for (String pattern : this.noneSecurityList) {
				if (matcher.match(pattern, uri)) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		String ip = getIpAddr(req);
		if(matcher.match("/utoo/3rd/activity/**", uri)||
				uri.equals("/utoo/client/pay/weixin/redpack")
				){
				if(!configuration.getIpWhiteSheet().contains(ip))
			return;
		}
		super.doFilter(new ContentTypeFilter(req), response, chain);
	}
	
	 public static String getIpAddr(HttpServletRequest request) {  
         String ip = request.getHeader("X-Forwarded-For");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("Proxy-Client-IP");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("WL-Proxy-Client-IP");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("HTTP_CLIENT_IP");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getRemoteAddr();  
         }  
         return ip;  
     }  
}
