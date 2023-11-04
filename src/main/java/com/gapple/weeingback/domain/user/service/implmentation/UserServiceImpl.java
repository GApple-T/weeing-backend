package com.gapple.weeingback.domain.user.service.implmentation;

import com.gapple.weeingback.global.jwt.JwtProvider;
import com.gapple.weeingback.global.jwt.TokenResponse;
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

    @Transactional
    public void join(UserJoinRequest req) throws Exception{
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();

        Okay okay = Okay.builder()
                .isTrue(false)
                .isAccess(false)
                .startAt(0L)
                .issuedAt(0L)
                .build();

        if(!userRepository.existsUserByEmail(req.getEmail())) {
            okayRepository.save(okay);
            user.setOkay(okay);

            userRepository.save(user);
        }
        else throw new IllegalAccessException();
    }

    public TokenResponse login(UserLoginRequest request) throws IllegalAccessException {
        User thisUser = userRepository.findUserByEmail(request.getEmail());

        if(!passwordEncoder.matches(request.getPassword(), thisUser.getPassword())) throw new IllegalAccessException();

        return JwtProvider.generateToken(request.getEmail());
    }
}