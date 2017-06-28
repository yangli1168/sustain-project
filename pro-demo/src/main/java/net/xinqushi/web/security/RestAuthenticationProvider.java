package net.xinqushi.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Configuration
public class RestAuthenticationProvider implements AuthenticationProvider {

	public RestAuthenticationProvider() {
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication instanceof PreAuthenticatedAuthenticationToken) {
			PreAuthenticatedAuthenticationToken preAuth = (PreAuthenticatedAuthenticationToken) authentication;

			if (preAuth.getPrincipal() instanceof RestAuthenticationToken) {
				RestAuthenticationToken sysAuth = (RestAuthenticationToken) preAuth.getPrincipal();

				// 
				if ((sysAuth.getUser() != null || sysAuth.getManUser() != null) && sysAuth.getAuthorities() != null
				        && sysAuth.getAuthorities().size() > 0) {
					sysAuth.setAuthenticated(true);
					return sysAuth;
				} else if (sysAuth.getAuthorities() != null && sysAuth.getAuthorities().size() > 0) {
					//GrantedAuthority authority = new SimpleGrantedAuthority
					GrantedAuthority gauth = sysAuth.getAuthorities().iterator().next();
					if ("ROLE_SOMEONE".equals(gauth.getAuthority())) {
						return sysAuth;
					}
				}
			}
		} else if (authentication instanceof RestAuthenticationToken) {
			RestAuthenticationToken sysAuth = (RestAuthenticationToken) authentication;

			// 
			if ((sysAuth.getUser() != null || sysAuth.getManUser() != null) && sysAuth.getAuthorities() != null
			        && sysAuth.getAuthorities().size() > 0) {
				sysAuth.setAuthenticated(true);
				return sysAuth;
			} else if (sysAuth.getAuthorities() != null && sysAuth.getAuthorities().size() > 0) {
				//GrantedAuthority authority = new SimpleGrantedAuthority
				GrantedAuthority gauth = sysAuth.getAuthorities().iterator().next();
				if ("ROLE_SOMEONE".equals(gauth.getAuthority())) {
					return sysAuth;
				}
			}
		}

		throw new BadCredentialException("unknown.error");
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class authentication) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication)
		        || RestAuthenticationToken.class.isAssignableFrom(authentication);
	}

}