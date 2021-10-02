package com.spring.learn.valid;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * 数据校验
 *
 */
@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Objects.equals(clazz, Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.error");
        Person person = (Person) target;
        if (person.getAge() < 0) {
            errors.rejectValue("age", "age < 0");
        } else if (person.getAge() > 100) {
            errors.rejectValue("age", "age > 100");
        }
    }
}
