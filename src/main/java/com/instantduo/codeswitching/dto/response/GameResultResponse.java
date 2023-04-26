package com.instantduo.codeswitching.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GameResultResponse {
    private Integer correctRate;
    private Long totalTime;
    private List<MyRankResponse> myRank;
}
