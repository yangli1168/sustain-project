package net.xinqushi.entry.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class Configuration {
	
	@Value("${redis.url}")
	private String redisUrl;
	
	@Value("${redis.port}")
	private int redisPort;
	
	@Value("${redis.cache.user}")
	private int redisUserDB;
	
	@Value("${redis.cache.manage}")
	private int redisManagementDB;
	
	@Value("${redis.pwd}")
	private String redisPwd;
	
	@Value("${request.versionCheck}")
	private boolean versionCheck;
	
	@Value("${request.ios.driver}")
	private String supportedVersionIosDriver;
	
	@Value("${request.android.driver}")
	private String supportedVersionAndDriver;
	
	@Value("${request.ios.passenger}")
	private String supportedVersionIosPassenger;
	
	@Value("${request.android.passenger}")
	private String supportedVersionAndPassenger;
	
	private Set<String> skipUris = new HashSet<String>();
	
	@Value("${ip.whitesheet}")
	private String ipWhiteSheet;
	
    public Set<String> getSkipUris() {
        return skipUris;
    }

	public String getRedisUrl() {
		return redisUrl;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public int getRedisUserDB() {
		return redisUserDB;
	}

	public int getRedisManagementDB() {
		return redisManagementDB;
	}

	public String getRedisPwd() {
		return redisPwd;
	}

	public boolean isVersionCheck() {
		return versionCheck;
	}

	public String getSupportedVersionIosDriver() {
		return supportedVersionIosDriver;
	}

	public String getSupportedVersionAndDriver() {
		return supportedVersionAndDriver;
	}

	public String getSupportedVersionIosPassenger() {
		return supportedVersionIosPassenger;
	}

	public String getSupportedVersionAndPassenger() {
		return supportedVersionAndPassenger;
	}

	public String getIpWhiteSheet() {
		return ipWhiteSheet;
	}

	
}