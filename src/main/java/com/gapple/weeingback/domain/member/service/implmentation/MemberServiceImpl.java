package com.gapple.weeingback.domain.member.service.implmentation;

import com.gapple.weeingback.global.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.entity.dto.MemberJoinRequest;
import com.gapple.weeingback.domain.member.entity.dto.MemberLoginRequest;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public ResponseEntity<?> join(MemberJoinRequest req){
        Member member = Member.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();

        if(!memberRepository.existsMemberByEmail(req.getEmail())) {
//            okayRepository.save(okay);
//            user.setOkay(okay);
            memberRepository.save(member);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

    public ResponseEntity<String> login(MemberLoginRequest request){
        Member member = memberRepository.findMemberByEmail(request.getEmail());

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            return ResponseEntity.ok(jwtProvider.generateToken(request.getEmail()));
        } catch (RuntimeException e){
            log.warn("없냐?");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}