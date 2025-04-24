package com.authentication.authentication.services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    public void saveToken(String token, String username, long expiration) {
        redisTemplate.opsForValue().set(token, username, Duration.ofMillis(expiration));
    }
    public boolean isTokenValid(String token) {
        String stored = redisTemplate.opsForValue().get(token);
        return token.equals(stored);
    }
    public void removeToken(String token) {
        redisTemplate.delete(token);
    }
}
