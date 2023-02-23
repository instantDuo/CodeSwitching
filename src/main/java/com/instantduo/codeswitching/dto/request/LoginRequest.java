package com.instantduo.codeswitching.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginRequest {

    @NotNull( message = "아이디를 입력해주세요.")
    private String loginId;

    @NotNull( message = "비밀번호를 입력해주세요.")
    private String password;
}
