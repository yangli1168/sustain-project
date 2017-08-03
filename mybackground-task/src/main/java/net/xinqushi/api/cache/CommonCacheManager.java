package net.xinqushi.api.cache;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.xinqushi.api.cache.fallback.CommonCacheManagerFallback;
import net.xinqushi.common.exceptions.CommonException;

/**
 * 调用缓存服务
 * 
 * @author yangli 2017年6月19日-下午4:50:32
 */
@FeignClient(value = "cache-service", fallback = CommonCacheManagerFallback.class, path = "/cache/common")
public interface CommonCacheManager {

	@RequestMapping(value = "/expiry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void cacheForExpiry(@RequestParam("key") String key, @RequestParam("value") String value,
			@RequestParam("timeout") int timeout) throws CommonException;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void cache(@RequestParam("key") String key, @RequestParam("value") String value) throws CommonException;

	@RequestMapping(value = "/nx", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Long cacheNx(@RequestParam("key") String key, @RequestParam("value") String value,
			@RequestParam("timeout") int timeout) throws CommonException;

	@RequestMapping(value = "/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCacheValue(@RequestParam("key") String key) throws CommonException;

	@RequestMapping(value = "/{key}/del", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeKey(@RequestParam("key") String key) throws CommonException;

	@RequestMapping(value = "/hset/{key}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void hset(@PathVariable("key") String key, @RequestParam("field") String field,
			@RequestParam("value") String value) throws CommonException;

	@RequestMapping(value = "/hget/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String hget(@PathVariable("key") String key, @RequestParam("field") String field) throws CommonException;

	@RequestMapping(value = "/verify/code", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int cacheForVerificationCode(@RequestParam("key") String key, @RequestParam("value") String value,
			@RequestParam("type") String type, @RequestParam("timeout") int timeout, @RequestParam("ip") String ip)
			throws CommonException;

	@RequestMapping(value = "/captcha", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void cacheCaptchaKeyCode(@RequestParam("captchaKey") String captchaKey,
			@RequestParam("captcha") String captcha, @RequestParam("timeout") int timeout) throws CommonException;

	@RequestMapping(value = "/captcha/valid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int isCaptchaValid(@RequestParam("captchaKey") String captchaKey, @RequestParam("captcha") String captcha)
			throws CommonException;

	@RequestMapping(value = "/hdel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void hdel(@RequestParam("key") String key, @RequestParam("field") String field) throws CommonException;
}
