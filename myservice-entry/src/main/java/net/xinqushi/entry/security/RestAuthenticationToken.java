package net.xinqushi.entry.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import net.xinqushi.entry.vo.ManagementUserElement;
import net.xinqushi.entry.vo.UserElement;

public class RestAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8301183935179750862L;

	private UserElement user;
	
	private ManagementUserElement manUser;

	public RestAuthenticationToken(
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
	}

	@Override
	public Object getCredentials() {
		return manUser == null ? null : manUser.getUserId();
	}

	@Override
	public Object getPrincipal() {
		return user == null ? null : user.getUserId();
	}

	public UserElement getUser() {
		return user;
	}

	public ManagementUserElement getManUser() {
		return manUser;
	}

	public void setManUser(ManagementUserElement manUser) {
		this.manUser = manUser;
	}

	public void setUser(UserElement user) {
		this.user = user;
	}

}
