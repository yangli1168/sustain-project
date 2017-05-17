package net.xinqushi;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

import net.xinqushi.common.enums.CommonStatus;
import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.service.EmailService;
import net.xinqushi.util.HTTPSender;
import net.xinqushi.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

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
	
	public static void main(String[] args) {
		JSONObject jo = new JSONObject();
		String url = "";
		
		HTTPSender.sendPost_JSON(url, jo);
	}
}
