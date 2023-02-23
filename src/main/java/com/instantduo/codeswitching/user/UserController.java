package com.instantduo.codeswitching.user;


import com.instantduo.codeswitching.common.ResponseMessage;
import com.instantduo.codeswitching.dto.request.LoginRequest;
import com.instantduo.codeswitching.dto.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")

public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupRequest signupRequest){
        userService.signup(signupRequest);
        ResponseMessage message = new ResponseMessage("회원가입이 완료되었습니다.", 200);
        return new ResponseEntity(message, HttpStatus.valueOf(message.getStatusCode()));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest){
        userService.login(loginRequest);
        ResponseMessage message = new ResponseMessage("로그인이 완료되엇습니다.", 200);
        return new ResponseEntity(message, HttpStatus.valueOf(message.getStatusCode()));

    }

}
