package com.gapple.weeingback.domain.auth.domain.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthJoinRequest {
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gsm.hs.kr$", message = "gsm.hs.kr 도메인을 사용하는 메일이 아닙니다.")
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$", message = "정규식에 맞지 않는 비밀번호입니다.")
    private String password;

    @NotBlank
    @Pattern(regexp = "^[가-힣]{2,}$", message = "한글 실명을 입력하지 않았습니다.")
    private String name;

    @NotNull
    private Long grade;

    @NotNull
    private Long classroom;

    @NotNull
    private Long number;
}