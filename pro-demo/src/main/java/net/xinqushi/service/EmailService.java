package net.xinqushi.service;

import net.xinqushi.common.exceptions.CommonException;

public interface EmailService {
	/**
	 * 发送邮件
	 * @param to
	 * @param name
	 * @param title
	 * @throws CommonException
	 * 2017年4月17日-下午5:34:37
	 */
	public void sendTemplate(String to, String name, String title) throws CommonException;
}
