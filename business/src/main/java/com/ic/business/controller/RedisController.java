package com.ic.business.controller;

import com.ic.business.model.TestModel;
import com.ic.business.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisUtil redisUtil;

    public RedisController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @GetMapping("/set")
    public void set() {
        TestModel testModel = new TestModel();
        testModel.setUsername("ice");
        testModel.setPassword("cream");
        testModel.setAge("888");
        redisUtil.set("apple", testModel.toString());
    }

    @GetMapping("/get")
    public String get() {
        long start = System.currentTimeMillis();
        String str = redisUtil.get("apple");
        System.out.println(System.currentTimeMillis() - start);
        return str;
    }
}
