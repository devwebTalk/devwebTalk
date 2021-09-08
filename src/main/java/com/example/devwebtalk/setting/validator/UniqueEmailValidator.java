package com.example.devwebtalk.setting.validator;

import com.example.devwebtalk.repository.UserRepository;
import com.example.devwebtalk.setting.annotation.UniqueEmail;
import com.example.devwebtalk.setting.config.ApplicationContextUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 2021-09-07
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * Blog : https://kha0213.github.io/
 */

@NoArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{

    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        userRepository = (UserRepository) ApplicationContextUtil.getBean(UserRepository.class);
    }
}
