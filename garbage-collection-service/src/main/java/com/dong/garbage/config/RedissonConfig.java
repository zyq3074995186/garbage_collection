package com.dong.garbage.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean("redissonClient")
    RedissonClient redissonClient() {

//创建配置
        Config config = new Config();

//指定编码，默认编码为org.redisson.codec.JsonJacksonCodec
//之前使用的spring-data-redis，用的客户端jedis，编码为org.springframework.data.redis.serializer.StringRedisSerializer
//改用redisson后为了之间数据能兼容，这里修改编码为org.redisson.client.codec.StringCodec
//        config.setCodec(new org.redisson.client.codec.StringCodec());

//        config.setCodec(new org.redisson.codec.JsonJacksonCodec());

//指定使用单节点部署方式
        SingleServerConfig singleConfig = config.useSingleServer();
//        singleConfig.setAddress("redis://dev.jingzheit.com:6060");
        singleConfig.setAddress("redis://"+host+":"+port);
        //设置密码
        singleConfig.setPassword(password);
//设置对于master节点的连接池中连接数最大为500
        singleConfig.setConnectionPoolSize(500);
        //如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，
        // 那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。
        singleConfig.setIdleConnectionTimeout(10000);
        //同任何节点建立连接时的等待超时。时间单位是毫秒。
        singleConfig.setConnectTimeout(30000);
        //等待节点回复命令的时间。该时间从命令发送成功时开始计时。
        singleConfig.setTimeout(3000);
        singleConfig.setPingTimeout(30000);
        //当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。
        singleConfig.setReconnectionTimeout(3000);

        //创建客户端(发现创建RedissonClient非常耗时，基本在2秒-4秒左右)
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}