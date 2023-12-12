package com.gapple.weeingback.domain.member.service.implmentation;

import com.gapple.weeingback.domain.consultation.entity.Consultation;
import com.gapple.weeingback.domain.member.entity.Member;
import org.junit.jupiter.api.Test;


class MemberServiceImplTest {

    @Test
    void createUser(){
        // Give
        Consultation consultation = new Consultation();
        Member member = Member.builder().consultation(consultation).email("hello@test.com").password("example").name("광수광수").build();

        // When

        //
    }
}