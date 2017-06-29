package net.xinqushi.entry.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class CacheWrapper {
	
	@Autowired
    private Configuration configuration;
	
	private JedisPool jedisPool = null;

	@PostConstruct
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(150);
        config.setMaxIdle(20);

        jedisPool = new JedisPool(config, configuration.getRedisUrl(),
                configuration.getRedisPort(), 2000, configuration.getRedisPwd());

    }

	public JedisPool getJedisPool() {
		return jedisPool;
	}
	
}