package com.instantduo.codeswitching;

import com.instantduo.codeswitching.common.ResponseMessage;
import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;
import com.instantduo.codeswitching.common.type.Language;
import com.instantduo.codeswitching.common.type.StroopColor;
import com.instantduo.codeswitching.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class TestController {
    private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNjEyNzYzMjM2IiwiZXhwIjoxNjczMDgyNzQxLCJpYXQiOjE2NzMwNzkxNDF9.1Kg0qV-Eh0Z8E83YrsS3B8gW46aMYzi7bJ9VltpAAiE";

    @PostMapping("/user/signup")
    @ApiOperation(value = "회원가입")
    public ResponseEntity signup(@RequestBody @Valid SignupRequest signupRequest){
        if(!signupRequest.getPassword().equals(signupRequest.getPasswordCheck())){
            throw new CustomException(ErrorCode.NOT_MATCHING_PASSWORD);
        }
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("회원가입이 완료되었습니다.", 200), HttpStatus.OK);
    }

    @PostMapping("/user/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws UnsupportedEncodingException {
        if(!loginRequest.getLoginId().equals("test123")||!loginRequest.getPassword().equals("qwer1234")){
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        response.setHeader("Authorization", token);

        return new ResponseEntity(new ResponseMessage<>("로그인이 완료되었습니다.", 400), HttpStatus.OK);
    }

    @GetMapping("/n-back/play")
    @ApiOperation(value = "NBack 게임 진행")
    public ResponseEntity getNBackPlayData(Language language1, Language language2, String subject, Integer level){

        List<List<String>> question = new ArrayList<>();
        question.add(Arrays.asList("교실", "classroom"));
        question.add(Arrays.asList("책상", "desk"));
        question.add(Arrays.asList("의자", "chair"));
        question.add(Arrays.asList("사물함", "locker"));
        question.add(Arrays.asList("칠판", "whiteboard"));
        List<GamePlayDataResponse> playData = new ArrayList<>();

        Random random = new Random();
        int beforeIndex = random.nextInt(question.size());
        int beforeLan = beforeIndex%2;
        playData.add(new GamePlayDataResponse(question.get(beforeIndex).get(beforeLan), "NO"));
        int index;
        for(int i = 0; i < 20; i++){
            index = random.nextInt(question.size()-1);
            if(index%2 == 0){
                beforeLan = (beforeLan+1)%2;
                playData.add(new GamePlayDataResponse(question.get(beforeIndex).get((beforeLan)), "YES"));
            }else{
                beforeLan = random.nextInt(2)%2;
                beforeIndex = index==beforeIndex?index+1:index;
                playData.add(new GamePlayDataResponse(question.get(beforeIndex).get(beforeLan), "NO"));

            }
        }

        PlayResponse response = new PlayResponse(1L, playData);
        ResponseMessage responseMessage = new ResponseMessage("문제 출제가 완료되었습니다.", 200, response);
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/n-back/end/{gameId}")
    @ApiOperation(value = "NBack 게임 완료")
    public ResponseEntity createNBackPlayData(@PathVariable Long gameId, @RequestBody List<PlayRequest> request){

        ResponseMessage responseMessage = new ResponseMessage("게임이 완료되었습니다.", 200, request);
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/n-back/end/{gameId}")
    @ApiOperation(value = "NBack 게임 종료")
    public ResponseEntity getNBackResult(@PathVariable Long gameId){

        List<MyRankResponse> rankResponses = new ArrayList<>();
        rankResponses.add(new MyRankResponse(97, 102L));
        rankResponses.add(new MyRankResponse(92, 104L));
        rankResponses.add(new MyRankResponse(86, 106L));
        rankResponses.add(new MyRankResponse(84, 101L));
        rankResponses.add(new MyRankResponse(42, 50L));

        GameResultResponse response = new GameResultResponse(97, 102L, rankResponses);

        ResponseMessage responseMessage = new ResponseMessage("결과 반환이 완료되었습니다.", 200, response);
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/stroop/play")
    @ApiOperation(value = "Stroop게임 진행")
    public ResponseEntity getStroopPlayData(Language language1, Language language2, String subject, Integer level){
        List<List<String>> question = new ArrayList<>();
        question.add(Arrays.asList("교실", "classroom"));
        question.add(Arrays.asList("책상", "desk"));
        question.add(Arrays.asList("의자", "chair"));
        question.add(Arrays.asList("사물함", "locker"));
        question.add(Arrays.asList("칠판", "whiteboard"));
        List<GamePlayDataResponse> playData = new ArrayList<>();

        List<StroopColor> colorList = Arrays.asList(StroopColor.RED, StroopColor.GREEN, StroopColor.BLUE, StroopColor.YELLOW);

        Random random = new Random();

        int randomIndex;
        StroopColor randomColor;

        for(int i = 0; i < 20; i++){
            randomColor = colorList.get(random.nextInt(4));
            randomIndex = random.nextInt(question.size());
            GamePlayDataResponse data = new GamePlayDataResponse(randomColor.getKorStr() + " " + question.get(randomIndex).get(random.nextInt(2)), colorList.get(random.nextInt(4)).getInput(), randomColor.getInput());
            playData.add(data);
        }

        PlayResponse response = new PlayResponse(1L, playData);


        ResponseMessage responseMessage = new ResponseMessage("문제 출제가 완료되었습니다.", 200, response);
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/stroop/end/{gameId}")
    @ApiOperation(value = "Stroop 게임 완료")
    public ResponseEntity createStroopPlayData(@PathVariable Long gameId, @RequestBody List<PlayRequest> request){

        ResponseMessage responseMessage = new ResponseMessage("게임이 완료되었습니다.", 200, request);
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/stroop/end/{gameId}")
    @ApiOperation(value = "Stroop 게임 종료")
    public ResponseEntity getStroopResult(@PathVariable Long gameId){

        List<MyRankResponse> rankResponses = new ArrayList<>();
        rankResponses.add(new MyRankResponse(97, 102L));
        rankResponses.add(new MyRankResponse(92, 104L));
        rankResponses.add(new MyRankResponse(86, 106L));
        rankResponses.add(new MyRankResponse(84, 101L));
        rankResponses.add(new MyRankResponse(42, 50L));

        GameResultResponse response = new GameResultResponse(97, 102L, rankResponses);

        ResponseMessage responseMessage = new ResponseMessage("결과 반환이 완료되었습니다.", 200, response);
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/user/cookie")
    public ResponseEntity cookieCheck(@RequestHeader(name = "Authorization") String token){
        ResponseMessage responseMessage = new ResponseMessage("쿠키 반환", 200, token);
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }


    @GetMapping("/user/playdata")
    public ResponseEntity getUserPlayData(@RequestHeader(name = "Authorization") String token){
        UserPlayDataResponse response = new UserPlayDataResponse("test123", 4, 103, 14, 31, 0);
        ResponseMessage responseMessage = new ResponseMessage("유저 정보 반환", 200, response);
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

}
