package net.xinqushi.cache.expiredetection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class ListenerRegister extends Thread {
    
    private static Logger logger = LoggerFactory.getLogger(ListenerRegister.class);

    private JedisPool jedisPool;
    
    @Value("${redis.host}")
    private String redisDBUrl;
    
    @Value("${redis.port}")
    private int port;
    
    @Value("${redis.event.pattern}")
    private String pattern; 

    @Value("${redis.pwd}")
    private String password;
    
    @Autowired
    private KeyExpireSubscribe keyExpireSubscribe;

    @PostConstruct
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(20);

        jedisPool = new JedisPool(config, redisDBUrl, port, 2000, password);
        
        this.start();
    }

    @Override
    public void run() {
        logger.info("Redis event subscriber is running ...");

        while (true) {
            try {
                try (Jedis jedis = jedisPool.getResource()) {
                    jedis.psubscribe(keyExpireSubscribe, pattern);
                }
            } catch (Exception e) {
                logger.error("Error occurs {}", e.getMessage());
            }
        }
    }
}
