package com.instantduo.codeswitching.user;

import com.instantduo.codeswitching.common.exception.CustomException;
import com.instantduo.codeswitching.common.exception.ErrorCode;
import com.instantduo.codeswitching.dto.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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
}
