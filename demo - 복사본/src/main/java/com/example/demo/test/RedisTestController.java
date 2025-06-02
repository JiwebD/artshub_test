package com.example.demo.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Redis 테스트를 위한 간단한 REST 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class RedisTestController {

    private final RedisTestService redisTestService;

    /**
     * GET /redis/set 요청 시 Redis에 문자열 저장
     */
    @GetMapping("/redis/set")
    public String set() {
        redisTestService.setData("myKey", "helloRedis");
        return "데이터 저장됨";
    }

    /**
     * GET /redis/get 요청 시 Redis에서 문자열 조회
     */
    @GetMapping("/redis/get")
    public String get() {
        String value = redisTestService.getData("myKey");
        return "Redis에서 가져온 값: " + value;
    }
}
