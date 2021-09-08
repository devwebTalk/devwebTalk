package com.example.devwebtalk.setting.annotation;

import com.example.devwebtalk.setting.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface UniqueEmail {
    String message() default "이미 존재하는 이메일입니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
