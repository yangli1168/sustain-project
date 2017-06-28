package net.xinqushi.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.JSON;

import net.xinqushi.cache.util.CacheWrapper;
import net.xinqushi.cache.util.Configuration;
import net.xinqushi.common.entity.ManagementUserElement;
import net.xinqushi.common.exceptions.CommonException;

@Component
public class ManagementTokenService {

	private static Logger logger = LoggerFactory.getLogger(ManagementTokenService.class);
	
	@Autowired
	private CacheWrapper cacheWrapper;
	
	@Autowired
	private Configuration configuration;
	
	public void putUserElement(String elementStr) throws CommonException {
		JedisPool pool = cacheWrapper.getPool();
		if (pool != null) {
			try (Jedis jedis = pool.getResource()) {
				ManagementUserElement mue = JSON.parseObject(elementStr, ManagementUserElement.class);
				if (mue != null && mue.getToken() != null) {
					String token = mue.getToken();
					String element = elementStr;
					jedis.select(1);
					jedis.setex(token, configuration.getManTokenExpireTime(), element);
				}
			} catch (Exception e) {
				logger.error("fail to cache management user token to cache", e);
				throw new CommonException("Fail to put management user element");
			}
		}
	}
	
	public ManagementUserElement getElementByToken(String token) throws CommonException {
		ManagementUserElement result = null;
		
		JedisPool pool = cacheWrapper.getPool();
		if (pool != null) {
			try (Jedis jedis = pool.getResource()) {
				jedis.select(1);
				String content = jedis.get(token);
				if (content != null) {
					result = JSON.parseObject(content, ManagementUserElement.class);
				}
			} catch (Exception e) {
				logger.error("fail to get management user token from cache", e);
				throw new CommonException("Fail to get management user element");
			}
		}
		
		return result;
	}
	
	/** 新增删除token方法*/
	public void deleteManUserElementByToken(String token) throws CommonException {
		
		JedisPool pool = cacheWrapper.getPool();
		if (pool != null) {
			try (Jedis jedis = pool.getResource()) {
				jedis.select(1);
				jedis.del(token);
			} catch (Exception e) {
				logger.error("fail to del management userInfo from cache", e);
				throw new CommonException("Fail to del management userInfo element");
			}
		}
	}
	
}

