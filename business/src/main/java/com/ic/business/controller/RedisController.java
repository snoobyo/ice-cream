package com.ic.business.controller;

import com.ic.business.model.TestModel;
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
        TestModel testModel = new TestModel();
        testModel.setUsername("ice");
        testModel.setPassword("cream");
        testModel.setAge("888");
        redisUtils.set("apple", testModel.toString());
    }

    @GetMapping("/get")
    public String get() {
        long start = System.currentTimeMillis();
        String str = redisUtils.get("apple");
        System.out.println(System.currentTimeMillis() - start);
        return str;
    }
}
