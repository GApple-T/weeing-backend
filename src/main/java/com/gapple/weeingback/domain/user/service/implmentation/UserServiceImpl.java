package com.gapple.weeingback.domain.user.service.implmentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gapple.weeingback.domain.okay.entity.Okay;
import com.gapple.weeingback.domain.okay.repository.OkayRepository;
import com.gapple.weeingback.domain.user.entity.User;
import com.gapple.weeingback.domain.user.entity.dto.UserJoinRequest;
import com.gapple.weeingback.domain.user.entity.dto.UserLoginRequest;
import com.gapple.weeingback.domain.user.repository.UserRepository;
import com.gapple.weeingback.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final OkayRepository okayRepository;
    private final PasswordEncoder passwordEncoder;
    // private JwtProvider jwtProvider = new JwtProvider();

    @Transactional(readOnly = true)
    public void join(UserJoinRequest req) throws Exception{
        User user = User.builder().name(req.getName()).email(req.getEmail()).password(req.getPassword()).build();

        Okay okay = Okay.builder().isTrue(false).isAccess(false).startAt(0L).build();
        okayRepository.save(okay);
        user.setOkay(okay);

        if(!userRepository.existsUserByEmail(req.getEmail())) {
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
}