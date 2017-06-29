package net.xinqushi.entry.security;

import org.springframework.security.core.AuthenticationException;

public class BadCredentialException extends AuthenticationException {

	public BadCredentialException(String msg) {
		super(msg);
	}

	/** @Fields serialVersionUID: */
	private static final long serialVersionUID = -3852217112373736341L;

}
