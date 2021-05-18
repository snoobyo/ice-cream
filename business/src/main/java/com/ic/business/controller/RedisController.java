package com.ic.business.controller;

import com.ic.business.util.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisUtils redisUtils;

    public RedisController(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @GetMapping("/set")
    public void set() {
        redisUtils.set("apple", "15");
    }
}
