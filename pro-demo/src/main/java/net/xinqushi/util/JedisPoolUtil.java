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
	private static final String REDIS_HOST = "112.74.133.11";
	private static final String REDIS_PASSWORD = "aB~fVS'XVT}7Z4R':]/7*bt-Y(kMZ7_J";
	private static final Integer REDIS_PORT = 6380;
	
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
	 * @param host
	 * @param prot
	 * @param timeout 超时时间
	 * @param password
	 * @param libraryNum 数据库序号,默认为0
	 * @return
	 */
	public static Jedis getJedis(String host, int prot, int timeout, String password,
			int libraryNum){
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(10);
			config.setMaxIdle(5);
			
			jedisPool = new JedisPool(config, host, prot, timeout, password);
			jedis = jedisPool.getResource();
			//设置jedis默认的redis数据库表
			jedis.select(libraryNum);
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
