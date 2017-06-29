package net.xinqushi.entry.security;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ContentTypeFilter extends HttpServletRequestWrapper {

	String contentType = "";

	public ContentTypeFilter(HttpServletRequest request) {
		super(request);
		contentType = request.getContentType();
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		if (null != name && name.equalsIgnoreCase("Content-Type")) {
			return new Enumeration<String>() {
				private boolean hasGetted = false;

				@Override
				public String nextElement() {
					if (hasGetted) {
						throw new NoSuchElementException();
					} else {
						hasGetted = true;
						if (contentType.equals("application/json, text/plain")) {
							return "text/plain";
						} else {
							return contentType;
						}
					}
				}

				@Override
				public boolean hasMoreElements() {
					return !hasGetted;
				}
			};
		}
		return super.getHeaders(name);
	}

}
