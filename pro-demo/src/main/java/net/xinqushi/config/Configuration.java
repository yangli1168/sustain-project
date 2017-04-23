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
	
	@Value("${special.phone}")
	private String specialPhone;
	
	@Value("${passenger.reservation.taken}")
	private String resvInfo;
	
//
//	@Value("${redis.max.conn}")
//	private int maxConn;
//
//	@Value("${redis.max.idle}")
//	private int maxIdle;

	public String getResvInfo() {
		return resvInfo;
	}

	public void setResvInfo(String resvInfo) {
		this.resvInfo = resvInfo;
	}

	public String getSpecialPhone() {
		return specialPhone;
	}

	public void setSpecialPhone(String specialPhone) {
		this.specialPhone = specialPhone;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	public String getRedisPwd() {
		return redisPwd;
	}

	public void setRedisPwd(String redisPwd) {
		this.redisPwd = redisPwd;
	}

}
