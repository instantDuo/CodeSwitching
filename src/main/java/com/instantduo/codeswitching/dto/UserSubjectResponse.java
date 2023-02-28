package com.instantduo.codeswitching.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSubjectResponse {
    private String loginId;
    private Integer schoolCount;
    private Integer kitchenCount;
    private Integer sportsCount;
    private Integer jobFairCount;

}
