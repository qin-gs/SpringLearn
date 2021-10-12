package com.spring.learn.valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidatorFactory;

@Service
public class ValidService {

    // @Autowired
    // private Validator validator;
    @Autowired
    private javax.validation.Validator validator_;
    @Autowired
    private ValidatorFactory factory;
}
