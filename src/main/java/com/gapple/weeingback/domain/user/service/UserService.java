package com.gapple.weeingback.domain.user.service;

import com.gapple.weeingback.domain.check.entity.Check;
import com.gapple.weeingback.domain.user.entity.User;

import com.gapple.weeingback.domain.user.entity.dto.UserJoinRequest;
import com.gapple.weeingback.domain.user.entity.dto.UserLoginRequest;
import com.gapple.weeingback.domain.user.repository.UserRepository;
import com.gapple.weeingback.global.jwt.JwtProvider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider = new JwtProvider();

    public void join(UserJoinRequest request) throws Exception{
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCheck(new Check());

        if(!userRepository.existsUserByEmail(request.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        else throw new Exception();
    }

    public String login(UserLoginRequest request){
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication.getName();
    }

    public String token(UserLoginRequest request){
        User user = userRepository.findUserByEmail(request.getEmail());
        if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
//            jwtProvider.generateToken();
        }
        return null;
    }
}