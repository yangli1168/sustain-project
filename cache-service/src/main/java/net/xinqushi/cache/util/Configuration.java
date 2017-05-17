package net.xinqushi.cache.util;

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

	@Value("${platform.comp.id}")
	private long transPlatformCompId;

	@Value("${token.expiry.user}")
	private int tokenExpireTime;

	@Value("${token.expiry.manage}")
	private int manTokenExpireTime;

	@Value("${weixin.mp.appid}")
	private String weixinMpAppId;

	@Value("${weixin.mp.secret}")
	private String weixinMpSecret;

	@Value("${qiniu.ak}")
	private String qiniuAK;

	@Value("${qiniu.sk}")
	private String qiniuSK;
	
	@Value("${qiniu.domain}")
	private String qiniuDomain;
	
	/** 新增dispatch_domain*/
	@Value("${qiniu.dispather.domain}")
	private String qiniuDispatherDomain;

	@Value("${qiniu.url.timeout}")
	private int qiniuURLTimeout;

	@Value("${qiniu.url.small.width}")
	private int imgSmallWid;

	@Value("${qiniu.url.small.height}")
	private int imgSmallHei;

	@Value("${qiniu.url.mid.width}")
	private int imgMidWid;

	@Value("${qiniu.url.mid.height}")
	private int imgMidHei;

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

	public long getTransPlatformCompId() {
		return transPlatformCompId;
	}

	public int getTokenExpireTime() {
		return tokenExpireTime;
	}

	public int getManTokenExpireTime() {
		return manTokenExpireTime;
	}

	public String getWeixinMpAppId() {
		return weixinMpAppId;
	}

	public String getWeixinMpSecret() {
		return weixinMpSecret;
	}

	public String getQiniuAK() {
		return qiniuAK;
	}

	public String getQiniuSK() {
		return qiniuSK;
	}

	public String getQiniuDomain() {
		return qiniuDomain;
	}

	public String getQiniuDispatherDomain() {
		return qiniuDispatherDomain;
	}

	public int getQiniuURLTimeout() {
		return qiniuURLTimeout;
	}

	public int getImgSmallWid() {
		return imgSmallWid;
	}

	public int getImgSmallHei() {
		return imgSmallHei;
	}

	public int getImgMidWid() {
		return imgMidWid;
	}

	public int getImgMidHei() {
		return imgMidHei;
	}

}
