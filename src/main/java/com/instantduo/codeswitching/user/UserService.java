package com.instantduo.codeswitching.user;

import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;
import com.instantduo.codeswitching.common.jwt.JwtUtil;
import com.instantduo.codeswitching.dto.request.LoginRequest;
import com.instantduo.codeswitching.dto.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public void signup(SignupRequest signupRequest) {
        if(userRepository.existsByLoginId(signupRequest.getLoginId())){
           throw new CustomException(ErrorCode.DUPLICATE_LOGINID);
        }

        if(!signupRequest.getPassword().equals(signupRequest.getPasswordCheck())){
            throw new CustomException(ErrorCode.NOT_MATCHING_PASSWORD);
        }

        User user = new User(signupRequest.getLoginId(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getGender(),
                signupRequest.getAge(),
                signupRequest.getLanguage(),
                signupRequest.getGrade());
        userRepository.save(user);
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
}
