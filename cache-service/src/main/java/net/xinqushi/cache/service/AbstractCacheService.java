package net.xinqushi.cache.service;

import java.util.List;

import org.slf4j.Logger;

import redis.clients.jedis.Jedis;

public abstract class AbstractCacheService {

	protected static final String SET_OK = "OK";

	protected abstract Logger getLogger();

	protected boolean transNeedRetry(List<Object> result) {
		boolean retry = false;
		if (result != null) {
			for (Object obj : result) {
				if (obj instanceof String) {
					if (!SET_OK.equals(obj.toString())) {
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

	protected void retrySet(Jedis jedis, String key, String value) {
		String status = null;
		int count = 0;
		do {
			count++;
			getLogger().error("Retrying setting {} / {}", key, value);
			try {
				status = jedis.set(key, value);
			} catch (Exception e) {
				getLogger().error("Fail to set value to redis", e);
			}
		} while (!SET_OK.equals(status) && count < 2);
	}

	protected Long retryDel(Jedis jedis, String key) {
		Long status = null;
		int count = 0;
		do {
			count++;
			getLogger().error("Retrying deleting {}", key);
			try {
				status = jedis.del(key);
			} catch (Exception e) {
				getLogger().error("Fail to set value to redis", e);
			}
		} while (status <= 0 && count < 2);
		return status;
	}
}
