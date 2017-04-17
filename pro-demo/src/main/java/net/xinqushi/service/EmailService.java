package net.xinqushi.service;

import java.util.List;

import net.xinqushi.common.exceptions.CommonException;

public interface EmailService {
	/**
	 * 发送邮件
	 * @param to
	 * @param name
	 * @param title
	 * @throws CommonException
	 */
	public void sendTemplate(String to, String name, String title) throws CommonException;
	
	/**
	 * 发送邮件
	 * @param to 收件人地址,eg:1607676749@qq.com
	 * @param subject 邮件主题
	 * @param messageText 邮件内容
	 * @param attachedFileName 附件路径地址,可为null
	 * @throws CommonException
	 */
	public void sendEmail(String to, String subject, String messageText, List<String> attachedFileName) throws CommonException;
}
