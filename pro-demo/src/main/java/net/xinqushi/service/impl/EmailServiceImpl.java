package net.xinqushi.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.service.EmailService;
import net.xinqushi.util.EmailSendUtils;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender mailSender; // 注入邮件发送对象

	private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Override
	public void sendTemplate(String to, String name, String title) throws CommonException {
		logger.info("开始执行发送邮件");
		MimeMessage mimeMessage = null;
		try {
			mimeMessage = mailSender.createMimeMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			// 基本设置
			helper.setFrom("350895216@qq.com");
			helper.setTo(to);
			helper.setSubject(title);
			// 设置图片附件
			// 获取文件对象1
			String pathname = "C:\\Users\\Administrator\\Pictures\\Saved Pictures\\QQ图片20161208104621.jpg";
			FileSystemResource file1 = new FileSystemResource(new File(pathname));
			// 添加
			helper.addAttachment("图片1.jpg", file1);

			// 获取文件对象2
			pathname = "D:\\uto\\my tools\\Git.jpg";
			helper.addAttachment("图片2.jpg", new FileSystemResource(new File(pathname)));

			// 设置邮件模板中所需内容，map
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("userName", name);
			model.put("companyName", "悠途信息科技有限公司");

			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			// 设置模板读取路径[目录]
			cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
			// 在目录中获取模板文件
			Template template = cfg.getTemplate("email.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			// 设置邮件内容
			helper.setText(html, true);

			// 发送邮件
			mailSender.send(mimeMessage);
		} catch (MessagingException | IOException | TemplateException e) {
			e.printStackTrace();
			logger.info("helper配置异常");
		}
	}
	
	public void send(){
		
		EmailSendUtils.sendMessage(smtpHost, from, fromUserPassword, to, subject, attachedFileName, messageText, messageType);
	}

}
