package net.xinqushi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class Configuration {

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private int redisPort;

	@Value("${redis.pwd}")
	private String redisPwd;
	
	@Value("${redis.max.conn}")
	private int maxConn;

	@Value("${redis.max.idle}")
	private int maxIdle;

	public String getRedisHost() {
		return redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public String getRedisPwd() {
		return redisPwd;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

}
