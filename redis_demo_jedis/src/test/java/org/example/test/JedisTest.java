package org.example.test;

import org.example.config.JedisConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @author wch
 * @date 2022/8/11 8:42
 * @description
 */
public class JedisTest {

    private static Jedis jedis;

    @BeforeEach
    void setUp() {
        //建立连接
        jedis = JedisConfig.getJedis();
        //设置密码
        jedis.auth("123321");
        //选择库
        jedis.select(0);
    }

    @Test
    void jedisTestString() {
        //存入数据
        String result = jedis.set("name", "lihua");
        System.out.println(result);
        //读数据
        String name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    void jedisTestHash() {
//        Long result = jedis.hset("user:1", "name", "lucy");
//        Long result = jedis.hset("user:1", "age", "21");
//        System.out.println(result);
        jedis.hincrBy("user:1", "age", 2L);
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }


    @AfterAll
    static void afterAll() {
        if (jedis != null){
            jedis.close();
        }
    }
}
