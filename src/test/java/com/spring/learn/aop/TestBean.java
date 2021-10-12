package com.spring.learn.aop;

public class TestBean {
    private String str = "testStr";

    public void testAop() {
        System.out.println(str);
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "str='" + str + '\'' +
                '}';
    }
}
