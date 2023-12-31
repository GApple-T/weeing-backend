package com.gapple.weeingback.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {
    private final String host;
    private final int port;
    private final String auth;


    public RedisConfig(
            @Value("${redis.host}") String host,
            @Value("${redis.port}") int port,
            @Value("${redis.auth}") String auth
    ){
        this.host = host;
        this.port = port;
        this.auth = auth;
    }

    @Bean
    public Jedis jedis(){
        Jedis jedis = new Jedis(host, port, true);
        jedis.auth(auth);

        return jedis;
    }
}
