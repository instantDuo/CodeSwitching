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
    INVALID_LANGUAGE("언어를 확인해주세요.", 400);


    private final String msg;
    private final int statusCode;
}