package com.gapple.weeingback.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gapple.weeingback.domain.user.repository.UserRepository;
import com.gapple.weeingback.domain.user.service.implmentation.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
  private final UserRepository userRepository;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserServiceImpl userService(){
    return new UserServiceImpl(userRepository, passwordEncoder());
  }
}
