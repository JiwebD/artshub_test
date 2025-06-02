package com.example.demo.test;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Redis에 문자열 데이터를 저장하고 조회하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class RedisTestService {

    // Redis 문자열 처리 전용 템플릿 (자동 설정됨)
    private final StringRedisTemplate redisTemplate;

    /**
     * 지정한 key로 value 저장
     */
    public void setData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
