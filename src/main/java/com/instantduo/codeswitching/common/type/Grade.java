package com.instantduo.codeswitching.common.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;

public enum Grade{
    COLLEGE("college", "대졸"),
    HIGH_SCHOOL("high_school", "고졸"),
    MIDDLE_SCHOOL("middle_school", "중졸"),
    PRIMARY_SCHOOL("primary_school", "초졸");

    final String input;
    final String korStr;

    Grade(String input, String korStr) {
        this.input = input;
        this.korStr = korStr;
    }


    @JsonCreator
    public static Grade inputToEnum(String input){
        return switch (input){
            case "college" -> COLLEGE;
            case "high_school" -> HIGH_SCHOOL;
            case "middle_school" -> MIDDLE_SCHOOL;
            case "primary_school" -> PRIMARY_SCHOOL;
            default -> throw new CustomException(ErrorCode.INVALID_GRADE);
        };
    }

}
