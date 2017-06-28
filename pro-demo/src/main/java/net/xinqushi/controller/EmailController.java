package net.xinqushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.common.response.RestResponse;
import net.xinqushi.service.EmailService;

/**
 * 邮件发送controller
 * 
 * @author yangli
 */
@RestController
@RequestMapping(value = "/xinqushi/mail")
public class EmailController {
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/{to}/{name}/{title}/template", method = RequestMethod.GET)
	public RestResponse sendEmail(@PathVariable(value = "to") String to, @PathVariable(value = "name") String name,
			@PathVariable(value = "title") String title) {
		RestResponse response = new RestResponse();
		try {
			emailService.sendTemplate(to, name, title);
			response.setMsg("邮件发送成功！");
		} catch (Exception e) {
			response.setCode(400);
			response.setMsg("调用异常！");
		}
		return response;
	}

	@RequestMapping(value = "/{to}/{subject}/{messageText}", method = RequestMethod.GET)
	public RestResponse sendEmails(@PathVariable(value = "to") String to,
			@PathVariable(value = "subject") String subject, @PathVariable(value = "messageText") String messageText,
			@RequestParam(value = "attachedFileName", required = false) String attachedFileName) {
		try {
			if (null == attachedFileName) {
				emailService.sendEmail(to, subject, messageText, null);
			}
			return RestResponse.ok();
		} catch (CommonException e) {
			return RestResponse.build(e.getStatusCode(), e.getMessage());
		}
	}

}
