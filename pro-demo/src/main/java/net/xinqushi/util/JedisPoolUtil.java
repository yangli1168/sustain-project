package net.xinqushi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * redis工具类：主要返回jedis客户端
 * @author yangli
 */
public class JedisPoolUtil {

	private static Logger logger = LoggerFactory.getLogger(JedisPoolUtil.class);

	private static JedisPool jedisPool = null;
	private static Jedis jedis = null;
	private static final String REDIS_HOST = "127.0.0.1";
	private static final String REDIS_PASSWORD = "123456";
	private static final Integer REDIS_PORT = 6379;
	
	/** 静态方法：获取jedis客户端 */
	public static Jedis getJedis(){
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(10);
			config.setMaxIdle(5);

			jedisPool = new JedisPool(config, REDIS_HOST, REDIS_PORT, 2000, REDIS_PASSWORD);
			jedis = jedisPool.getResource();
			//设置jedis默认的redis数据库表
//			jedis.select(8);
		} catch (Exception e) {
			logger.error("Fail to initialize jedis pool", e);
			try {
				throw new Exception("Fail to initialize jedis pool");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return jedis;
	}
	
	/**
	 * 静态方法：获取jedis客户端
	 * @param host 默认127.0.0.1
	 * @param prot 端口，默认6379
	 * @param timeout 超时时间，默认2000
	 * @param password 默认123456
	 * @param libraryNum 数据库序号,默认为0
	 * @return
	 */
	public static Jedis getJedis(String host, Integer prot, Integer timeout, String password,
			Integer libraryNum){
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(10);
			config.setMaxIdle(5);
			
			jedisPool = new JedisPool(config, 
							host == null ? "127.0.0.1" : host, 
							prot == null ? 6379 : prot, 
							timeout == null ? 2000 : timeout, 
							password == null ? "123456" : password);
			jedis = jedisPool.getResource();
			//设置jedis默认的redis数据库表
			jedis.select(libraryNum==null?0:libraryNum);
		} catch (Exception e) {
			logger.error("Fail to initialize jedis pool", e);
			try {
				throw new Exception("Fail to initialize jedis pool");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return jedis;
	}
	
}
