package net.xinqushi.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.xinqushi.cache.common.CommonException;
import net.xinqushi.cache.service.CommonCacheService;

@RestController
@RequestMapping("/cache/common")
public class CommonCacheController {

	@Autowired
	private CommonCacheService commonCacheService;

	@RequestMapping(value = "/expiry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void cacheForExpiry(@RequestParam("key") String key, @RequestParam("value") String value,
	        @RequestParam("timeout") int timeout) throws CommonException {
		commonCacheService.cacheForExpiry(key, value, timeout);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void cache(@RequestParam("key") String key, @RequestParam("value") String value) throws CommonException {
		commonCacheService.cache(key, value);
	}

	@RequestMapping(value = "/nx", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Long cacheNx(@RequestParam("key") String key, @RequestParam("value") String value,
	        @RequestParam("timeout") int timeout) throws CommonException {
		return commonCacheService.cacheNx(key, value, timeout);
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCacheValue(@RequestParam("key") String key) throws CommonException {
		return commonCacheService.getCachedValue(key);
	}

	@RequestMapping(value = "/{key}/del", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeKey(@RequestParam("key") String key) throws CommonException {
		commonCacheService.removeKey(key);
	}

	@RequestMapping(value = "/hset/{key}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void hset(@PathVariable("key") String key, @RequestParam("field") String field,
	        @RequestParam("value") String value) throws CommonException {
		commonCacheService.hset(key, field, value);
	}

	@RequestMapping(value = "/hget/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String hget(@PathVariable("key") String key, @RequestParam("field") String field) throws CommonException {
		return commonCacheService.hget(key, field);
	}
	
	@RequestMapping(value = "/verify/code", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int cacheForVerificationCode(@RequestParam("key") String key, @RequestParam("value") String value,
	        @RequestParam("type") String type, @RequestParam("timeout") int timeout, @RequestParam("ip") String ip) throws CommonException {
		return commonCacheService.cacheForVerificationCode(key, value, type, timeout, ip);
	}
	
	@RequestMapping(value = "/captcha", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void cacheCaptchaKeyCode(@RequestParam("captchaKey") String captchaKey,
	        @RequestParam("captcha") String captcha, @RequestParam("timeout") int timeout) throws CommonException {
		commonCacheService.cacheCaptchaKeyCode(captchaKey, captcha, timeout);
	}

	@RequestMapping(value = "/captcha/valid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int isCaptchaValid(@RequestParam("captchaKey") String captchaKey, @RequestParam("captcha") String captcha)
	        throws CommonException {
		return commonCacheService.isCaptchaValid(captchaKey, captcha);
	}
	
	@RequestMapping(value = "/hdel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void hdel(@RequestParam("key") String key, @RequestParam("field") String field) throws CommonException{
		commonCacheService.hdel(key, field);
	}
}
