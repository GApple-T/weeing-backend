package com.gapple.weeingback.domain.member.service.implmentation;

import com.gapple.weeingback.domain.consulting.entity.Consulting;
import com.gapple.weeingback.domain.member.entity.Member;
import org.junit.jupiter.api.Test;


class MemberServiceImplTest {

    @Test
    void createUser(){
        // Give
        Consulting consulting = new Consulting();
        Member member = Member.builder().consulting(consulting).email("hello@test.com").password("example").name("광수광수").build();

        // When

        //
    }
}