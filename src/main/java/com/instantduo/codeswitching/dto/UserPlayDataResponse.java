package com.instantduo.codeswitching.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPlayDataResponse {
    private String loginId;
    private Integer nBackCount;
    private Integer stroopCount;
    private Integer matchingCount;
    private Integer simonCount;
    private Integer complexCount;
}
