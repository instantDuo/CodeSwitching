package com.instantduo.codeswitching.common.type;

import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;

public enum Gender{
    MALE("male", "남성"),
    FEMALE("female", "여성");

    final String input;
    final String str;

    Gender(String input, String str) {
        this.input = input;
        this.str = str;
    }


    public Gender inputToEnum(String input){
        return switch (input){
            case "male" -> MALE;
            case "female" -> FEMALE;
            default -> throw new CustomException(ErrorCode.INVALID_GENDER);
        };
    }
}
