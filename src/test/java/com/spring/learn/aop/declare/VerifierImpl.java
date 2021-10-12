package com.spring.learn.aop.declare;

import com.spring.learn.bean.User;

public class VerifierImpl implements Verifier {
    @Override
    public boolean validate(User user) {
        return user.getUsername().equals("qqq");
    }
}
