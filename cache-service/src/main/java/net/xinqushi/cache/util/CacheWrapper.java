package net.xinqushi.cache.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class CacheWrapper {

	@Autowired
	private Configuration configuration;

	private JedisPool pool = null;

	@PostConstruct
	public void init() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(configuration.getMaxConn());
		config.setMaxIdle(configuration.getMaxIdle());

		pool = new JedisPool(config, configuration.getRedisHost(), configuration.getRedisPort(), 2000,
		        configuration.getRedisPwd());

	}

	public JedisPool getPool() {
		return pool;
	}

}
