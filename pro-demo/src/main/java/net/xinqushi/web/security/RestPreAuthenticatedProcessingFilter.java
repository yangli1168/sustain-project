package net.xinqushi.web.security;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.AntPathMatcher;

import net.xinqushi.api.cache.ManagementCacheManager;
import net.xinqushi.common.constants.StatusCodeConstants;
import net.xinqushi.common.entity.ManagementUserElement;


public class RestPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

	@Autowired
	private ManagementCacheManager managementCacheManager;

	private AntPathMatcher matcher = new AntPathMatcher();

	private List<String> noneSecurityList;

	public void setNoneSecurityList(List<String> noneSecurityList) {
		this.noneSecurityList = noneSecurityList;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		if (isNoneSecurity(request.getRequestURI().toString()) || "OPTIONS".equals(request.getMethod())) {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SOMEONE");
			authorities[0] = authority;
			return new RestAuthenticationToken(Arrays.asList(authorities));
		}

		String token = request.getHeader(StatusCodeConstants.REQUEST_TOKEN_KEY);
		try {
			if (token != null && !token.trim().isEmpty()) {
				ManagementUserElement ue = managementCacheManager.getManUserElement(token);

				if (ue instanceof ManagementUserElement) {
					GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
					authorities[0] = authority;
					RestAuthenticationToken authToken = new RestAuthenticationToken(Arrays.asList(authorities));
					authToken.setManUser(ue);
					return authToken;
				} else {
					request.setAttribute("header-error", 401);
				}
			} else {
				logger.warn("Got no token from request header of management client");
			}
		} catch (Exception e) {
			logger.error("Fail to authenticate user", e);
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

}
