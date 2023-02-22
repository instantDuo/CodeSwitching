package com.instantduo.codeswitching.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyRankResponse {
    private Integer correctRate;
    private Long totalTime;
}
