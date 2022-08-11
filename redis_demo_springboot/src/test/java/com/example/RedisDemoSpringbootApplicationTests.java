package com.example;

import com.example.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoSpringbootApplicationTests {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	void testString() {
		redisTemplate.opsForValue().set("name","lisi");
		Object name = redisTemplate.opsForValue().get("name");
		System.out.println(name);
	}
	@Test
	void testUser() {
		redisTemplate.opsForValue().set("user:2", new User("李华", 22));
		User o = (User) redisTemplate.opsForValue().get("user:2");
		System.out.println(o);
	}

}
