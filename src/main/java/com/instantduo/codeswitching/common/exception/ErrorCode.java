package com.instantduo.codeswitching.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_PARAMETER("파라미터 값을 확인해주세요.", 400),
    INVALID_GRADE("학력을 확인해주세요.", 400),
    INVALID_COLOR("색깔을 확인해주세요.", 400),
    INVALID_GENDER("성별을 확인해주세요.", 400),
    INVALID_LANGUAGE("언어를 확인해주세요.", 400),
    NOT_MATCHING_PASSWORD("비밀번호가 일치하지 않습니다.", 400),
    NOT_FOUND_USER("일치하는 회원 정보가 존재 하지 않습니다.", 400),
    DUPLICATE_LOGINID("중복되는 아이디가 존재합니다.", 400),
    NOT_LOGIN("로그인이 필요합니다.", 403);


    private final String msg;
    private final int statusCode;
}