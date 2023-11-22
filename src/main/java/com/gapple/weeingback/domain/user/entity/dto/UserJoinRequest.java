package com.gapple.weeingback.domain.user.entity.dto;

import com.gapple.weeingback.domain.user.entity.UserRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserJoinRequest {
    @NotBlank
    @Size(min = 2, max = 6)
    @Pattern(regexp = "^[가-힣]*$", message = "이름 형식이 아닙니다.")
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 24)
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,24}$", message = "비밀번호는 8~24 자리이며, 특수문자가 1개 이상 들어가야합니다.")
    private String password;
}