package com.spring.learn.service;

import com.spring.learn.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
public class BookService {
    /**
     * 先按照类型查找(BookDao.class)
     * 如果找到多个按照名称(bookDao)查找
     * primary 配置首选的bean
     *
     * 通过 AutowiredAnnotationBeanPostProcessor 实现注入功能
     *
     * 还可以注解 构造器 方法 字段 参数
     * 方法：通常标注在setter方法上，方法的参数从容器中获取
     * 构造方法：标注在有参的构造函数上，spring会调用该构造函数(如果没有无参构造函数，只有有参的构造函数，会调用有参的自动注入参数，注解可省略)
     * 参数：标注在形参的参数上
     * '@Bean' 标注的方法创建对象的时候，如果有形参，也会从容器中获取
     */
    @Autowired(required = false)
    private BookDao bookDao;

    /**
     * Qualifier 指定装配哪个bean
     */
    @Autowired
    @Qualifier("bookDao2")
    private BookDao dao;

    /**
     * 默认使用名称注入,可以指定名称
     * 不支持@Primary 和 required=false功能
     */
    @Resource(name = "bookDao")
    private BookDao resourceDao;

    /**
     * 需要引入依赖 javax.inject
     * 支持@Primary
     * 不支持required=false
     */
    @Inject
    private BookDao injectDao;

}
