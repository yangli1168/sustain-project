package net.xinqushi.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * redis缓存用工具类
 * 
 * @author yangli 2017年5月5日-下午2:24:31
 */
public class CacheUtils {

	private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

	private Jedis jedis = null;

	/** 构造方法：默认本机 */
	public CacheUtils() {
		if (null == jedis) {
			jedis = JedisPoolUtil.getJedis(null, null, null, null, null);
		}
	}

	/**
	 * 构造方法：定制参数
	 * 
	 * @param host
	 *            null时默认127.0.0.1
	 * @param prot
	 *            null时端口 默认6379
	 * @param timeout
	 *            null时超时时间 默认2000
	 * @param password
	 *            null时密码 默认123456
	 * @param libraryNum
	 *            null时数据库序号 默认为0
	 */
	public CacheUtils(String host, Integer prot, Integer timeout, String password, Integer libraryNum) {
		if (null == jedis) {
			jedis = JedisPoolUtil.getJedis(host, prot, timeout, password, libraryNum);
		}
	}
	
	/**
	 * 删除key-value
	 * @param preKey
	 * @param fieldKey
	 */
	public void deleteKey(String preKey, String fieldKey) {

		int count = 0;
		boolean retry = false;

		do {
			count++;

			Map<String, String> map = jedis.hgetAll(preKey + fieldKey);
			Transaction trans = jedis.multi();
			List<Object> result = null;
			try {
				// String userId = map.get("userId");
				// trans.srem(USER_PREFIX + userId, token);
				// trans.del(TOKEN_PREFIX + token);
				result = trans.exec();
			} catch (Exception e) {
				trans.discard();
				logger.error("Fail to remove {} from redis", preKey + fieldKey, e);
			}

			retry = transNeedRetry(result);
		} while (retry && count < 3);
	}

	private static boolean transNeedRetry(List<Object> result) {
		boolean retry = false;
		if (result != null) {
			for (Object obj : result) {
				if (obj instanceof String) {
					if (!"OK".equals(obj.toString())) {
						retry = true;
						break;
					}
				} else if (obj instanceof Long) {
					if ((Long) obj <= 0) {
						retry = true;
						break;
					}
				}
			}
		}
		return retry;
	}

	/**
	 * 缓存key-value
	 * @param key
	 * @param value
	 * @param expireTime
	 */
	public void cacheKeyValue(String key, String value, int expireTime) {
		int count = 0;
		boolean retry = false;

		do {
			count++;

			Transaction trans = jedis.multi();
			List<Object> result = null;
			try {
				trans.set(key, value);
				trans.expire(key, expireTime);
				result = trans.exec();
			} catch (Exception e) {
				trans.discard();
				logger.error("Fail to cache {} into redis", key, e);
			}

			retry = transNeedRetry(result);

		} while (retry && count < 3);
	}
	
	
}
