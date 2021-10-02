package com.spring.learn.editor;

public class DependsOnExoticType {

    private ExoticType type;

    public void setType(ExoticType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DependsOnExoticType{" +
                "type=" + type +
                '}';
    }
}
