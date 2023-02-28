package com.instantduo.codeswitching.user;


import com.instantduo.codeswitching.common.ResponseMessage;
import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;
import com.instantduo.codeswitching.common.security.UserDetailsImpl;
import com.instantduo.codeswitching.common.type.Language;
import com.instantduo.codeswitching.dto.request.LoginRequest;
import com.instantduo.codeswitching.dto.request.SignupRequest;
import com.instantduo.codeswitching.dto.response.UserGamePlayDataResponse;
import com.instantduo.codeswitching.dto.response.UserLanguageResponse;
import com.instantduo.codeswitching.dto.response.UserSubjectPlayDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    @GetMapping("/game")
    public ResponseEntity<ResponseMessage<UserGamePlayDataResponse>> getGameCount(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();

        UserGamePlayDataResponse userGamePlayDataResponse = userService.getGamePlayData(user);

        ResponseMessage<UserGamePlayDataResponse> message = new ResponseMessage<>("반환이 완료되었습니다.", 200, userGamePlayDataResponse);
        return new ResponseEntity<>(message, HttpStatus.valueOf(message.getStatusCode()));
    }

    @GetMapping("/subject")
    public ResponseEntity<ResponseMessage<UserSubjectPlayDataResponse>> getSubjectCount(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        if(user == null){
            throw new CustomException(ErrorCode.NOT_LOGIN);
        }
        UserSubjectPlayDataResponse userSubjectPlayDataResponse = userService.getSubjectPlayData(user);

        ResponseMessage<UserSubjectPlayDataResponse> message = new ResponseMessage<>("반환이 완료되었습니다.", 200, userSubjectPlayDataResponse);
        return new ResponseEntity<>(message, HttpStatus.valueOf(message.getStatusCode()));
    }

    @GetMapping("/language")
    public ResponseEntity<ResponseMessage<UserLanguageResponse>> getUserLanguage(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Language language = userDetails.getUser().getLanguage();
        UserLanguageResponse userLanguageResponse = new UserLanguageResponse("kor");
        ResponseMessage<UserLanguageResponse> message = new ResponseMessage<>("반환이 완료되었습니다.", 200, userLanguageResponse);
        return new ResponseEntity<>(message, HttpStatus.valueOf(message.getStatusCode()));
    }


}
