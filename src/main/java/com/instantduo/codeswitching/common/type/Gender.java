package com.instantduo.codeswitching.common.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;

public enum Gender{
    MALE("male", "남성"),
    FEMALE("female", "여성");

    final String input;
    final String korStr;

    Gender(String input, String korStr) {
        this.input = input;
        this.korStr = korStr;
    }


    @JsonCreator
    public static Gender inputToEnum(String input){
        return switch (input){
            case "male" -> MALE;
            case "female" -> FEMALE;
            default -> throw new CustomException(ErrorCode.INVALID_GENDER);
        };
    }



}
