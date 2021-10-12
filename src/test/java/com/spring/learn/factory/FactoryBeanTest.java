package com.spring.learn.factory;

import com.spring.learn.bean.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("./xml/factory-bean.xml");
        Car bean = ((Car) bf.getBean("car"));
        System.out.println(bean);

        Car car = (Car) bf.getBean("carFromFactory");
        System.out.println(car);

        Car carStatic = (Car) bf.getBean("carFromFactoryStatic");
        System.out.println(carStatic);

    }
}
