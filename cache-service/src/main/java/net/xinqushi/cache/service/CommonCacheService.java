package net.xinqushi.cache.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.xinqushi.cache.common.CommonException;
import net.xinqushi.cache.util.CacheWrapper;
import net.xinqushi.cache.util.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;


@Component
public class CommonCacheService extends AbstractCacheService {

	private static Logger logger = LoggerFactory.getLogger(CommonCacheService.class);

	private static final String CAPTCHA_PREFIX = "captcha.";
	
	@Autowired
	private CacheWrapper cacheWrapper;

	@Autowired
	private Configuration configuration;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	public void cacheForExpiry(String key, String value, int timeout) throws CommonException {
		try {
			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {
				int count = 0;
				boolean retry = false;

				do {
					count++;

					try (Jedis jedis = pool.getResource()) {
						jedis.select(0);
						Transaction trans = jedis.multi();
						List<Object> result = null;
						try {
							trans.set(key, value);
							trans.expire(key, timeout);
							result = trans.exec();
						} catch (Exception e) {
							trans.discard();
							logger.error("Fail to cache data into redis", e);
						}

						retry = transNeedRetry(result);
					}
				} while (retry && count < 3);
			}
		} catch (Exception e) {
			logger.error("Fail to cache for expiry", e);
			throw new CommonException("Fail to cache for expiry");
		}
	}

	public int cacheForVerificationCode(String key, String value, String type, int timeout, String ip) throws CommonException {
		try {
			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {

				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					
					String ipKey = "ip." + ip;
					if (ip == null) {
						return 3;
					} else {
						String ipSendCount = jedis.get(ipKey);
						try {
							if (ipSendCount != null && Integer.parseInt(ipSendCount) >= 10) {
								return 3;
							}
						} catch (NumberFormatException e) {
							logger.error("Fail to process ip send count", e);
							return 3;
						}
					}
					
					long succ = jedis.setnx(key, value);
					if (succ == 0) {
						return 1;
					}

					String sendCount = jedis.get(key + "." + type);
					try {
						if (sendCount != null && Integer.parseInt(sendCount) >= 10) {
							jedis.del(key);
							return 2;
						}
					} catch (NumberFormatException e) {
						logger.error("Fail to process send count", e);
						jedis.del(key);
						return 2;
					}

					try {
						// trans.set(key, value);
						jedis.expire(key, timeout);
						long val = jedis.incr(key + "." + type);
						if (val == 1) {
							jedis.expire(key + "." + type, 86400);
						}
						
						jedis.incr(ipKey);
						if (val == 1) {
							jedis.expire(ipKey, 86400);
						}
					} catch (Exception e) {
						logger.error("Fail to cache data into redis", e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Fail to cache for expiry", e);
			throw new CommonException("Fail to cache for expiry");
		}

		return 0;
	}

	public void cache(String key, String value) throws CommonException {
		this.cache(key.getBytes(), value.getBytes());
	}

	public void cache(byte[] key, byte[] value) throws CommonException {
		try {
			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {
				int count = 0;
				boolean retry = false;
				do {
					count++;

					try (Jedis jedis = pool.getResource()) {
						jedis.select(0);
						Transaction trans = jedis.multi();
						List<Object> result = null;
						try {
							trans.set(key, value);
							result = trans.exec();
						} catch (Exception e) {
							trans.discard();
							logger.error("Fail to cache data into redis", e);
						}

						retry = transNeedRetry(result);
					}
				} while (retry && count < 3);
			}
		} catch (Exception e) {
			logger.error("Fail to cache data", e);
			throw new CommonException("Fail to cache data");
		}
	}

	public Long cacheNx(String key, String value, int timeout) throws CommonException {
		Long result = 0l;
		try {
			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {

				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					try {
						int count = 0;
						do {
							count++;
							result = jedis.setnx(key, value);
							if (result == 1) {
								jedis.expire(key, timeout);
							} else {
								int endure_millis = 300;
								Thread.sleep(endure_millis);
							}
						} while (result == 0 && count < 3);
					} catch (Exception e) {
						logger.error("Fail to cache data into redis", e);
					}
				}
				if (result == 0) {
					throw new CommonException("内部错误");
				}
			}
		} catch (Exception e) {
			logger.error("Fail to cachenx data", e);
			throw new CommonException("Fail to cachenx data");
		}
		return result;
	}

	public String getCachedValue(String key) throws CommonException {
		String value = null;
		try {
			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {
				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					try {
						value = jedis.get(key);
					} catch (Exception e) {
						logger.error("Fail to check data from redis", e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Fail to get cached value", e);
			throw new CommonException("Fail to get cached value");
		}
		return value;
	}

	public byte[] getCachedValue(byte[] key) throws CommonException {
		byte[] value = null;
		try {
			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {
				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					try {
						return jedis.get(key);
					} catch (Exception e) {
						logger.error("Fail to check data from redis", e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Fail to get cached value", e);
			throw new CommonException("Fail to get cached value");
		}
		return value;
	}

	public void removeKey(String key) throws CommonException {
		try {
			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {
				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					try {
						long response = jedis.del(key);
						if (response <= 0) {
							retryDel(jedis, key);
						}
					} catch (Exception e) {
						logger.error("Fail to remove key from redis", e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Fail to remove key", e);
			throw new CommonException("Fail to remove key");
		}
	}

	public void hset(String key, String field, String value) throws CommonException {
		try {
			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {
				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					try {
						jedis.hset(key, field, value);
					} catch (Exception e) {
						logger.error("Fail to hset value", e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Fail to hset key", e);
			throw new CommonException("Fail to hset key");
		}
	}

	public String hget(String key, String field) throws CommonException {
		try {
			String value = null;

			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {
				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					try {
						value = jedis.hget(key, field);
					} catch (Exception e) {
						logger.error("Fail to hget value", e);
					}
				}
			}

			return value;
		} catch (Exception e) {
			logger.error("Fail to hget key", e);
			throw new CommonException("Fail to hget key");
		}
	}
	
	public void hdel(String key, String field) throws CommonException {
		try {
			
			JedisPool pool = cacheWrapper.getPool();
			if (pool != null) {
				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					try {
						jedis.hdel(key, field);
					} catch (Exception e) {
						logger.error("Fail to hdel value", e);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("Fail to hdel key", e);
			throw new CommonException("Fail to hdel key");
		}
	}
	
	
	
	public void cacheCaptchaKeyCode(String captchaKey, String captcha, int timeout) throws CommonException {
		JedisPool pool = cacheWrapper.getPool();
		if (pool != null) {
			int count = 0;
			boolean retry = false;

			do {
				count++;

				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					Transaction trans = jedis.multi();
					List<Object> result = null;
					try {
						String key = CAPTCHA_PREFIX + captchaKey;
						trans.set(key, captcha);
						trans.expire(key, timeout);
						result = trans.exec();
					} catch (Exception e) {
						trans.discard();
						logger.error("Fail to cache captcha into redis", e);
					}

					retry = transNeedRetry(result);
				}
			} while (retry && count < 3);
		}
	}

	public int isCaptchaValid(String captchaKey, String captcha) throws CommonException {
		int valid = 0;

		JedisPool pool = cacheWrapper.getPool();
		if (pool != null) {
			try (Jedis jedis = pool.getResource()) {
				jedis.select(0);
				try {
					String cachedCode = jedis.get(CAPTCHA_PREFIX + captchaKey);
					if (cachedCode == null) {
						valid = 1;
					}
					if (!cachedCode.equalsIgnoreCase(captcha)) {
						valid = 2;
					}
				} catch (Exception e) {
					logger.error("Fail to check verification captcha code validity with redis", e);
				}
			}
		}

		return valid;
	}
}
