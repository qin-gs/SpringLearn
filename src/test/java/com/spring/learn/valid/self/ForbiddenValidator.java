package com.spring.learn.valid.self;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * 校验器
 */
public class ForbiddenValidator implements ConstraintValidator<Forbidden, String> {

    @Override
    public void initialize(Forbidden constraintAnnotation) {
        // 这里可以得到注解数据
    }

    private final String[] forbiddenWords = {"admin", "password"};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasLength(value)) {
            return true;
        }
        return Arrays.stream(forbiddenWords).noneMatch(value::contains);
    }
}
