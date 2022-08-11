package org.example.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wch
 * @date 2022/8/11 11:40
 * @description
 */
public class JedisConfig {
    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接
        jedisPoolConfig.setMaxTotal(8);
        //最大空闲连接
        jedisPoolConfig.setMaxIdle(8);
        //最小空闲连接
        jedisPoolConfig.setMinIdle(0);
        //最大等待时间
        jedisPoolConfig.setMaxWaitMillis(200);

        //初始化Jedis连接池
        jedisPool = new JedisPool(jedisPoolConfig, "192.168.200.161", 6379,
                1000, "123321");
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
