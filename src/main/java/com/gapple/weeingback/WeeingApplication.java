package com.gapple.weeingback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

@SpringBootApplication
public class WeeingApplication {
  public static void main(String[] args) {
    SpringApplication.run(WeeingApplication.class, args);
  }
}