package com.instantduo.codeswitching.user;

import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;
import com.instantduo.codeswitching.common.jwt.JwtUtil;
import com.instantduo.codeswitching.common.type.Game;
import com.instantduo.codeswitching.common.type.Subject;
import com.instantduo.codeswitching.dto.request.LoginRequest;
import com.instantduo.codeswitching.dto.request.SignupRequest;
import com.instantduo.codeswitching.dto.response.UserGamePlayDataResponse;
import com.instantduo.codeswitching.dto.response.UserSubjectPlayDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final PlayDataRepository playDataRepository;

    private final JwtUtil jwtUtil;

    public void signup(SignupRequest signupRequest) {
        if(userRepository.existsByLoginId(signupRequest.getLoginId())){
           throw new CustomException(ErrorCode.DUPLICATE_LOGINID);
        }

        if(!signupRequest.getPassword().equals(signupRequest.getPasswordCheck())){
            throw new CustomException(ErrorCode.NOT_MATCHING_PASSWORD);
        }

        User user = userRepository.save(
                new User(signupRequest.getLoginId(),
                        passwordEncoder.encode(signupRequest.getPassword()),
                        signupRequest.getGender(),
                        signupRequest.getAge(),
                        signupRequest.getLanguage(),
                        signupRequest.getGrade()
                )
        );

        for(Game game : Game.values()){
            for(Subject subject : Subject.values()){
                playDataRepository.save(new PlayData(subject, game, 0, user));
            }
        }
    }

    public Pair<String, String> login(LoginRequest loginRequest) {
        User user = userRepository.findByLoginId(loginRequest.getLoginId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        String token = jwtUtil.createToken(user.getLoginId());

        return Pair.of(JwtUtil.AUTHORIZATION_HEADER, token);
    }

    public UserGamePlayDataResponse getGamePlayData(User user) {
        List<PlayData> playDataList =  playDataRepository.findAllByUser(user);
        UserGamePlayDataResponse userGamePlayDataResponse = new UserGamePlayDataResponse(user.getLoginId(), 0, 0, 0, 0, 0);
        for(PlayData playData : playDataList){
            switch (playData.getGame()){
                case N_BACK -> userGamePlayDataResponse.increaseNBack(playData.getCount());
                case STROOP -> userGamePlayDataResponse.increaseStroop(playData.getCount());
                case SIMON -> userGamePlayDataResponse.increaseSimon(playData.getCount());
                case COMPLEX -> userGamePlayDataResponse.increaseComplex(playData.getCount());
                case MATCHING-> userGamePlayDataResponse.increaseMatching(playData.getCount());
            }
        }

        return userGamePlayDataResponse;

    }

    public UserSubjectPlayDataResponse getSubjectPlayData(User user) {
        List<PlayData> playDataList =  playDataRepository.findAllByUser(user);
        UserSubjectPlayDataResponse userSubjectPlayDataResponse = new UserSubjectPlayDataResponse(user.getLoginId(), 0, 0, 0, 0);
        for(PlayData playData : playDataList){
            switch (playData.getSubject()){
                case SCHOOL -> userSubjectPlayDataResponse.increaseSchool(playData.getCount());
                case KITCHEN -> userSubjectPlayDataResponse.increaseKitchen(playData.getCount());
                case SPORTS -> userSubjectPlayDataResponse.increaseSports(playData.getCount());
                case JOB_FAIR -> userSubjectPlayDataResponse.increaseJobFair(playData.getCount());
            }
        }

        return userSubjectPlayDataResponse;


    }
}
