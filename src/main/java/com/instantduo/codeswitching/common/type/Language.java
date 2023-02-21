package com.instantduo.codeswitching.common.type;

import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;

public enum Language {
    KOR("kor","한국어"),
    ENG("eng","영어"),
    JPN("jpn","일본어"),
    DUE("due","독일어"),
    CHN("chn","중국어");

    final String input;
    final String str;

    Language(String input, String str) {
        this.input = input;
        this.str = str;
    }

    public Language inputToEnum(String input){
        return switch (input){
            case "kor" -> KOR;
            case "eng" -> ENG;
            case "jpn" -> JPN;
            case "due" -> DUE;
            case "chn" -> CHN;
            default -> throw new CustomException(ErrorCode.INVALID_LANGUAGE);
        };
    }
}
