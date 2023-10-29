package com.gapple.weeingback.global.config;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.authorizeHttpRequests(request -> request
        .requestMatchers("/user/*").permitAll()
        .anyRequest().authenticated()
    )
        .httpBasic(withDefaults())
        .formLogin(withDefaults());

    http.sessionManagement((sessionManagement) ->
        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    );

    http.csrf(AbstractHttpConfigurer::disable);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
