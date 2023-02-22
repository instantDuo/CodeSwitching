package com.instantduo.codeswitching.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GamePlayDataResponse {
    private String question;
    private String color;
    private String answer;

    public GamePlayDataResponse(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
