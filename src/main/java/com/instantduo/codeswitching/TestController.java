package com.instantduo.codeswitching;

import com.instantduo.codeswitching.common.ResponseMessage;
import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;
import com.instantduo.codeswitching.common.type.Language;
import com.instantduo.codeswitching.common.type.StroopColor;
import com.instantduo.codeswitching.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        if(!loginRequest.getLoginId().equals("test123")||!loginRequest.getPassword().equals("qwer1234")){
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }
        return new ResponseEntity(new ResponseMessage<>("로그인이 완료되었습니다.", 400), HttpStatus.OK);
    }

    @GetMapping("/n-back/play")
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
    public ResponseEntity createNBackPlayData(@PathVariable Long gameId, @RequestBody List<NBackPlayRequest> request){

        ResponseMessage responseMessage = new ResponseMessage("게임이 완료되었습니다.", 200, request);
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/n-back/end/{gameId}")
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

}
