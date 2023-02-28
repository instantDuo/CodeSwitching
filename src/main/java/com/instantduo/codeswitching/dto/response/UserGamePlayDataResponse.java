package com.instantduo.codeswitching.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserGamePlayDataResponse {
    private String loginId;
    private Integer nBackCount;
    private Integer stroopCount;
    private Integer matchingCount;
    private Integer simonCount;
    private Integer complexCount;


    public void increaseNBack(Integer count){
        this.nBackCount += count;
    }

    public void increaseStroop(Integer count){
        this.stroopCount += count;
    }

    public void increaseMatching(Integer count){
        this.matchingCount += count;
    }

    public void increaseSimon(Integer count){
        this.simonCount += count;
    }

    public void increaseComplex(Integer count){
        this.complexCount += count;
    }
}
