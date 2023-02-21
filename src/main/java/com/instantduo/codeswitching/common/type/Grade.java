package com.instantduo.codeswitching.common.type;

import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;

public enum Grade{
    COLLEGE("collge", "대졸"),
    HIGH_SCHOOL("high_school", "고졸"),
    MIDDLE_SCHOOL("middle_school", "중졸"),
    PRIMARY_SCHOOL("primary_school", "초졸");

    String input;
    String str;

    Grade(String input, String str) {
        this.input = input;
        this.str = str;
    }


    public Grade inputToEnum(String input){
        return switch (input){
            case "college" -> COLLEGE;
            case "high_school" -> HIGH_SCHOOL;
            case "middle_school" -> MIDDLE_SCHOOL;
            case "primary_school" -> PRIMARY_SCHOOL;
            default -> throw new CustomException(ErrorCode.INVALID_GRADE);
        };
    }
}
