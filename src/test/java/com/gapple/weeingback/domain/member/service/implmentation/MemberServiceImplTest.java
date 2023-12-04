package com.gapple.weeingback.domain.member.service.implmentation;

import com.gapple.weeingback.domain.okay.entity.Okay;
import com.gapple.weeingback.domain.member.entity.Member;
import org.junit.jupiter.api.Test;


class MemberServiceImplTest {

    @Test
    void createUser(){
        // Give
        Okay okay = new Okay();
        Member member = Member.builder().okay(okay).email("hello@test.com").password("example").name("광수광수").build();

        // When

        //
    }
}