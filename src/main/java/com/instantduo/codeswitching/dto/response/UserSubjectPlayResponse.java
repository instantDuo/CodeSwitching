package com.instantduo.codeswitching.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSubjectPlayResponse {
    private String loginId;
    private Integer schoolCount;
    private Integer kitchenCount;
    private Integer sportsCount;
    private Integer jobFairCount;

    public void increaseSchool(Integer count){
        this.schoolCount += count;
    }
    public void increaseKitchen(Integer count){
        this.kitchenCount += count;
    }
    public void increaseSports(Integer count){
        this.sportsCount += count;
    }
    public void increaseJobFair(Integer count){
        this.jobFairCount += count;
    }

}
