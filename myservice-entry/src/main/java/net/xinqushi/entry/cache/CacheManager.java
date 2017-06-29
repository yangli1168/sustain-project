package net.xinqushi.entry.cache;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import net.xinqushi.entry.util.CacheWrapper;
import net.xinqushi.entry.util.Configuration;
import net.xinqushi.entry.vo.ManagementUserElement;
import net.xinqushi.entry.vo.UserElement;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

@Component
public class CacheManager {

	private static Logger logger = LoggerFactory.getLogger(CacheManager.class);

	@Autowired
	private CacheWrapper utooCacheWrapper;

	@Autowired
	private Configuration configuration;

	private static final String TOKEN_PREFIX = "token.";

	private static final String USER_PREFIX = "user.";

	private static final String SET_OK = "OK";

	public UserElement getUserTokenContent(String token) {
		UserElement ue = null;

		JedisPool pool = utooCacheWrapper.getJedisPool();
		if (pool != null) {
			try (Jedis jedis = pool.getResource()) {
				jedis.select(configuration.getRedisUserDB());
				try {
					Map<String, String> map = jedis.hgetAll(TOKEN_PREFIX + token);
					if (map != null && !map.isEmpty()) {
						ue = UserElement.fromMap(map);
					} else {
						logger.warn("Fail to find cached element for token {}", token);
					}
				} catch (Exception e) {
					logger.error("Fail to get token from redis", e);
				}
			}
		}

		return ue;
	}

	public ManagementUserElement getManagementTokenContent(String token) {
		ManagementUserElement ue = null;

		JedisPool pool = utooCacheWrapper.getJedisPool();
		if (pool != null) {
			try (Jedis jedis = pool.getResource()) {
				jedis.select(configuration.getRedisManagementDB());
				try {
					String content = jedis.get(token);
					if (content != null) {
						ue = JSON.parseObject(content, ManagementUserElement.class);
					}
				} catch (Exception e) {
					logger.error("Fail to get management token from redis", e);
				}
			}
		}

		return ue;
	}

	public void deleteToken(String token) {
		JedisPool pool = utooCacheWrapper.getJedisPool();
		if (pool != null) {
			int count = 0;
			boolean retry = false;
			do {
				count++;

				try (Jedis jedis = pool.getResource()) {
					jedis.select(configuration.getRedisUserDB());
					Map<String, String> map = jedis.hgetAll(TOKEN_PREFIX + token);
					Transaction trans = jedis.multi();
					List<Object> result = null;
					try {
						String userId = map.get("userId");
						trans.srem(USER_PREFIX + userId, token);
						trans.del(TOKEN_PREFIX + token);
						result = trans.exec();
					} catch (Exception e) {
						trans.discard();
						logger.error("Fail to remove token from redis", e);
					}

					retry = transNeedRetry(result);
				}
			} while (retry && count < 3);
		}
	}

	private boolean transNeedRetry(List<Object> result) {
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

}
