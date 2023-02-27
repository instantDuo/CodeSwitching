package com.instantduo.codeswitching.user;


import com.instantduo.codeswitching.common.ResponseMessage;
import com.instantduo.codeswitching.dto.request.LoginRequest;
import com.instantduo.codeswitching.dto.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")

public class UserController {
    private final UserService userService;

    public static final String AUTHORIZATION_HEADER = "Authorization";


    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage<String>> signup(@RequestBody @Valid SignupRequest signupRequest){
        userService.signup(signupRequest);
        ResponseMessage<String> message = new ResponseMessage<>("회원가입이 완료되었습니다.", 200);
        return new ResponseEntity<>(message, HttpStatus.valueOf(message.getStatusCode()));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<String>> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response){
        Pair<String, String> token = userService.login(loginRequest);
        ResponseMessage<String> message = new ResponseMessage<>("로그인이 완료되엇습니다.", 200);

        response.addHeader(token.getFirst(), token.getSecond());

        return new ResponseEntity<>(message, HttpStatus.valueOf(message.getStatusCode()));

    }

}
