package com.gapple.weeingback.domain.member.service.implmentation;

import com.gapple.weeingback.global.jwt.JwtProvider;
import com.gapple.weeingback.global.jwt.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gapple.weeingback.domain.okay.repository.OkayRepository;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.entity.dto.MemberJoinRequest;
import com.gapple.weeingback.domain.member.entity.dto.MemberLoginRequest;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<?> join(MemberJoinRequest req){
        Member member = Member.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();

//        Okay okay = Okay.builder()
//                .isTrue(false)
//                .isAccess(false)
//                .startAt(0L)
//                .issuedAt(0L)
//                .build();

        if(!memberRepository.existsMemberByEmail(req.getEmail())) {
//            okayRepository.save(okay);
//            user.setOkay(okay);
            memberRepository.save(member);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}

    public ResponseEntity<TokenResponse> login(MemberLoginRequest request){
        Member member = memberRepository.findMemberByEmail(request.getEmail());

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            return ResponseEntity.ok(JwtProvider.generateToken(request.getEmail()));
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}