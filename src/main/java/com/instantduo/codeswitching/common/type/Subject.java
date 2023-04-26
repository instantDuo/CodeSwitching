package com.instantduo.codeswitching.common.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;

public enum Subject {
    SCHOOL("school"),
    KITCHEN("kitchen"),
    SPORTS("sports"),
    JOB_FAIR("jobFair");

    private final String input;

    Subject(String input) {
        this.input = input;
    }

    @JsonCreator
    public static Subject inputToEnum(String input){
        return switch (input){
            case "school" -> SCHOOL;
            case "kitchen" -> KITCHEN;
            case "sports" -> SPORTS;
            case "jobFair" -> JOB_FAIR;
            default -> throw new CustomException(ErrorCode.INVALID_COLOR);
        };
    }
}

