package com.instantduo.codeswitching.dto.request;

import com.instantduo.codeswitching.common.type.Gender;
import com.instantduo.codeswitching.common.type.Grade;
import com.instantduo.codeswitching.common.type.Language;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@ToString

public class SignupRequest {

    @NotNull( message = "아이디를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{4,16}", message = "아이디는 대소문자 영어, 숫자를 포함한 4~16자리여야 합니다.")
    private String loginId;

    @NotNull( message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}" , message = "비밀번호는 대소문자 영어, 숫자를 포함한 8~16자리여야 합니다.")
    private String password;

    @NotNull( message = "비밀번호를 입력해주세요.")
    private String passwordCheck;

    @NotNull( message = "성별을 입력해주세요.")
    private Gender gender;

    @NotNull( message = "나이를 입력해주세요.")
    private Integer age;

    @NotNull( message = "모국어를 입력해주세요.")
    private Language language;

    @NotNull( message = "학력을 입력해주세요.")
    private Grade grade;
}
