package net.xinqushi.controller;

import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import net.xinqushi.common.response.RestResponse;
import net.xinqushi.util.Base64;
import net.xinqushi.util.CaptchaGenerator;
import net.xinqushi.util.MD5;

/**
 * 验证码服务
 * @author yangli
 * 2017年5月17日-上午10:55:26
 */
@RestController
@RequestMapping(value = "/captcha")
public class CaptchaController {
	
	private static Logger logger = LoggerFactory.getLogger(CaptchaController.class);
	
	@Autowired
	private CaptchaGenerator captchaGenerator;
	
	@RequestMapping(value = "/stream", method = RequestMethod.GET)
	public void getCaptcha(@RequestParam(value = "w", defaultValue = "160") int width,
	        @RequestParam(value = "h", defaultValue = "64") int height, HttpServletResponse response) {
		String code = this.captchaGenerator.generateVerifyCode(4);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			RenderedImage img = this.captchaGenerator.getGeneratedImage(width, height, baos, code);

			String captchaKey = MD5.getMd5(System.currentTimeMillis() + code);
//			commonCacheManager.cacheCaptchaKeyCode(captchaKey, code, 100);

//			response.addHeader(Constants.REQUEST_VERIFY_KEY_KEY, captchaKey);
			response.setContentType("image/jpg");
			ImageIO.write(img, "jpg", response.getOutputStream());
		} catch (Exception e) {
			logger.error("Fail to generate captcha", e);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/image", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestResponse getCaptchaBase64(@RequestParam(value = "w", defaultValue = "160") int width,
	        @RequestParam(value = "h", defaultValue = "64") int height) {
		RestResponse response = new RestResponse();
		
		try {
			String code = this.captchaGenerator.generateVerifyCode(4);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			this.captchaGenerator.getGeneratedImage(width, height, baos, code);
			byte[] data = baos.toByteArray();

			String captchaKey = MD5.getMd5(System.currentTimeMillis() + code);
//			commonCacheManager.cacheCaptchaKeyCode(captchaKey, code, 100);

			JSONObject jo = new JSONObject();
			jo.put("captchaKey", captchaKey);
			jo.put("img", "data:image/jpeg;base64," + Base64.encode(data));
			response.setData(jo);
		} catch (Exception e) {
			logger.error("Fail to generate captcha", e);
		}
		
		return response;
	}
}
