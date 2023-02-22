package com.instantduo.codeswitching.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PlayResponse {
    private Long gameId;
    private List<GamePlayDataResponse> gameData;
}
