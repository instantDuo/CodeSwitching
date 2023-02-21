package com.instantduo.codeswitching;

import com.instantduo.codeswitching.common.ResponseMessage;
import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;
import com.instantduo.codeswitching.dto.GamePlayData;
import com.instantduo.codeswitching.dto.LoginRequest;
import com.instantduo.codeswitching.dto.NBackPlayResponse;
import com.instantduo.codeswitching.dto.SignupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class TestController {

    @PostMapping("/user/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupRequest signupRequest){
        if(!signupRequest.getPassword().equals(signupRequest.getPasswordCheck())){
            throw new CustomException(ErrorCode.NOT_MATCHING_PASSWORD);
        }
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("회원가입이 완료되었습니다.", 200), HttpStatus.OK);
    }


}
