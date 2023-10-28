package com.gapple.weeingback.domain.user.service.implmentation;

import com.gapple.weeingback.domain.okay.entity.Okay;
import com.gapple.weeingback.domain.user.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserServiceImplTest {

    @Test
    void createUser(){
        // Give
        Okay okay = new Okay();
        User user = User.builder().okay(okay).email("hello@test.com").password("example").name("광수광수").build();

        // When

        //
    }
}