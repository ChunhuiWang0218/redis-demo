package com.example;

import com.example.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class RedisDemoStringTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("name", "lisi");
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testUser() throws JsonProcessingException {
        User user1 = new User("李华", 22);
        String s = mapper.writeValueAsString(user1);
        stringRedisTemplate.opsForValue().set("user:3", s);
        String userJ = stringRedisTemplate.opsForValue().get("user:3");
        User user2 = mapper.readValue(userJ, User.class);
        System.out.println(user2);
    }

    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("user:100", "name", "xiaoliu");
        stringRedisTemplate.opsForHash().put("user:100", "age", "33");
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("user:100");
        System.out.println(map);
    }

}
