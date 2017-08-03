package net.xinqushi.api.cache.fallback;

import org.springframework.stereotype.Component;

import net.xinqushi.api.cache.CommonCacheManager;
import net.xinqushi.common.exceptions.CommonException;

/**
 * 调用缓存服务失败时的处理
 * 
 * @author yangli 2017年6月19日-下午4:51:36
 */
@Component
public class CommonCacheManagerFallback implements CommonCacheManager {

	@Override
	public void cacheForExpiry(String key, String value, int timeout) throws CommonException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cache(String key, String value) throws CommonException {
		// TODO Auto-generated method stub

	}

	@Override
	public Long cacheNx(String key, String value, int timeout) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCacheValue(String key) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeKey(String key) throws CommonException {
		// TODO Auto-generated method stub

	}

	@Override
	public void hset(String key, String field, String value) throws CommonException {
		// TODO Auto-generated method stub

	}

	@Override
	public String hget(String key, String field) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cacheForVerificationCode(String key, String value, String type, int timeout, String ip)
			throws CommonException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void cacheCaptchaKeyCode(String captchaKey, String captcha, int timeout) throws CommonException {
		// TODO Auto-generated method stub

	}

	@Override
	public int isCaptchaValid(String captchaKey, String captcha) throws CommonException {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void hdel(String key, String field) throws CommonException {
		// TODO Auto-generated method stub

	}

}
