package com.dong.garbage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @PostMapping("aaaaaaa")
    public void test(){
        redisTemplate.opsForValue().set("aaa","aaaa");
        String aaa = redisTemplate.opsForValue().get("aaa");
        System.out.println(aaa);
    }
    public void demoOne(){

    }

}
