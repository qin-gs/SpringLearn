package com.spring.learn.factory;

import com.spring.learn.bean.Car;

public class CarFactory {
    public Car getCar() {
        Car car = new Car();
        car.setMaxSpeed(120);
        car.setBrand("car factory created");
        car.setPrice(20_0000.0D);
        return car;
    }

    public static Car getCarStatic() {
        Car car = new Car();
        car.setMaxSpeed(120);
        car.setBrand("car factory created static");
        car.setPrice(20_0000.0D);
        return car;
    }
}
