package net.xinqushi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.service.EmailService;

/**
 * @author yangli 2017年5月3日-上午9:51:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTest {

	@Autowired
	private EmailService emailService;

	@Test
	public void testEmailService() throws CommonException {
		emailService.sendEmail("1607676749@qq.com", "测试test", "本次为测试方法", null);
	}
}
