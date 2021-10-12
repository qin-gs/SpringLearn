package com.spring.learn.bean;


public class Message {
    private String mes;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mes='" + mes + '\'' +
                '}';
    }
}
