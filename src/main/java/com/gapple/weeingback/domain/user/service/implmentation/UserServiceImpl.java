package com.gapple.weeingback.domain.user.service.implmentation;

import com.gapple.weeingback.global.jwt.JwtProvider;
import com.gapple.weeingback.global.jwt.TokenResponse;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ResponseEntity<?> join(UserJoinRequest req){
        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();

//        Okay okay = Okay.builder()
//                .isTrue(false)
//                .isAccess(false)
//                .startAt(0L)
//                .issuedAt(0L)
//                .build();

        if(!userRepository.existsUserByEmail(req.getEmail())) {
//            okayRepository.save(okay);
//            user.setOkay(okay);
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}

    public ResponseEntity<TokenResponse> login(UserLoginRequest request){
        User user = userRepository.findUserByEmail(request.getEmail());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            return ResponseEntity.ok(JwtProvider.generateToken(request.getEmail()));
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}