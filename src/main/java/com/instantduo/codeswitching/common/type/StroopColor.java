package com.instantduo.codeswitching.common.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum StroopColor {
    RED("red", "빨간"),
    GREEN("green", "초록"),
    BLUE("blue", "파란"),
    YELLOW("yellow", "노란");

    final String input;
    final String korStr;

    StroopColor(String input, String korStr) {
        this.input = input;
        this.korStr = korStr;
    }

    @JsonCreator
    public static StroopColor inputToEnum(String input){
        return switch (input){
            case "red" -> RED;
            case "green" -> GREEN;
            case "blue" -> BLUE;
            case "yellow" -> YELLOW;
            default -> throw new CustomException(ErrorCode.INVALID_COLOR);
        };
    }

}
