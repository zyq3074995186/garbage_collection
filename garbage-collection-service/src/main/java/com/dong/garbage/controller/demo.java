package com.dong.garbage.controller;

import com.dong.garbage.config.RedissonConfig;
import io.swagger.annotations.Api;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@RestController
@Api("用户操作接口")
public class demo {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private RedissonConfig redissonConfig;


    @ApiOperation("测试接口")
    @PostMapping("aaaaaaa")
    public void test() {
        redisTemplate.opsForValue().set("aaa", "aaaa");
        String aaa = redisTemplate.opsForValue().get("aaa");
        System.out.println(aaa);
        redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("a", "a", 10L, TimeUnit.SECONDS);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);
        Jedis jedis = new Jedis("localhost", 6379);
        jedis = jedisPool.getResource();
        jedis.set("jedis", "a");
        jedis.close();
        Boolean a = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
                RedisSerializer keySerializer = redisTemplate.getKeySerializer();
                Object obj = redisConnection.execute("set", keySerializer.serialize("key"),
                        valueSerializer.serialize("value"),
                        "NX".getBytes(StandardCharsets.UTF_8),
                        "EX".getBytes(StandardCharsets.UTF_8),
                        String.valueOf(1L).getBytes(StandardCharsets.UTF_8));
                return obj != null;
            }
        });


        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("a", "v");
        System.out.println(aBoolean + "" + a);
    }

    public void demoOne() {

    }

}