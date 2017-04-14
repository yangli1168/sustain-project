package net.xinqushi.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件工具类
 * @author yangli 
 * <p> 2017年3月17日-上午9:18:09
 */
public class EmailSendUtils {
	/**
	 * 
	 * @param smtpHost  邮箱主机
	 * @param from  发件人账号
	 * @param fromUserPassword  发件人密码
	 * @param to  收件人
	 * @param subject  邮件标题
	 * @param attachedFileName 附件路径[无文件时null,单文件时string,多文件时"list<<string>>"]
	 * @param messageText  消息文本
	 * @param messageType  消息类型,eg:text/html;charset=utf-8
	 * @throws MessagingException
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	public static String sendMessage(String smtpHost, String from, String fromUserPassword, 
			String to, String subject, Object attachedFileName, String messageText, String messageType) throws MessagingException {
		// 第一步：配置javax.mail.Session对象
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.starttls.enable", "true");// 使用 STARTTLS安全连接
		// props.put("mail.smtp.port", "25"); //google使用465或587端口
		props.put("mail.smtp.auth", "true"); // 使用验证
		// props.put("mail.debug", "true");
		Session mailSession = Session.getInstance(props, new MyAuthenticator(from, fromUserPassword));

		// 第二步：编写消息
		InternetAddress fromAddress = new InternetAddress(from);
		InternetAddress toAddress = new InternetAddress(to);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(fromAddress);
		message.addRecipient(RecipientType.TO, toAddress);
		message.setSentDate(Calendar.getInstance().getTime());
		message.setSubject(subject);
		message.setText(messageText, messageType); //有附件时失效
		//附件
		Multipart multipart = new MimeMultipart();
		if (null != attachedFileName) {
			System.out.println("添加附件中...");
			//邮件内容信息
			MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(messageText);
	        //单附件
	        if (attachedFileName instanceof String) {
	        	BodyPart attchmentPart = new MimeBodyPart();
	        	DataSource source = new FileDataSource((String)attachedFileName);
	        	attchmentPart.setDataHandler(new DataHandler(source));
	        	String[] nameSplit = ((String) attachedFileName).split(".");
	        	attchmentPart.setFileName("file1." + nameSplit[1]);
	        	
	        	multipart.addBodyPart(messageBodyPart);
	        	multipart.addBodyPart(attchmentPart);
			}
            //多附件
	        if (attachedFileName instanceof List) {
	        	multipart.addBodyPart(messageBodyPart);
	        	for(int i=0; i<((List) attachedFileName).size(); i++){
	        		String name = (String) ((List) attachedFileName).get(i);
	        		BodyPart attchmentPart = new MimeBodyPart();
		        	DataSource source = new FileDataSource(name);
		        	attchmentPart.setDataHandler(new DataHandler(source));
		        	String currentName =  new File(name.trim()).getName();
		        	attchmentPart.setFileName(currentName);
		        	
		        	multipart.addBodyPart(attchmentPart);
	        	}
			}
            message.setContent(multipart);
		}

		// 第三步：发送消息
		System.out.println("发送邮件中...");
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(smtpHost, from, fromUserPassword);
		transport.send(message, message.getRecipients(RecipientType.TO));
		return "success";
	}

	public static void main(String[] args) {
		//qq邮箱
//		EmailSendUtils.sendMessage("smtp.qq.com", "350895216@qq.com", "elgtjdbzlryacahd", "1607676749@qq.com",
//				"nihao", "---------------wrwe-----------", "text/html;charset=utf-8");
		
		//163邮箱
		List<String> list = new ArrayList<>();
		list.add("D:/uto/my tools/Git.jpg");
		list.add("D:/out6.html");
		try {
			String msg = EmailSendUtils.sendMessage("smtp.163.com", "yangli-1168@163.com", "king@1168", "1607676749@qq.com", 
					"测试附件-6", list, "这是内容", "text/html;charset=utf-8");
			System.out.println(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

class MyAuthenticator extends Authenticator {
	String userName = "";
	String password = "";

	public MyAuthenticator() {

	}

	public MyAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
