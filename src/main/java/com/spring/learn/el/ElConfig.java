package com.spring.learn.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * el表达式操作
 * ${} 获取配置文件中的系统属性值
 * #{} 通过el表达式获取 bean 的属性，或者调用 bean 的方法，静态常量静态方法(通过T())
 */
@Configuration
@ComponentScan("com.spring.learn.el")
@PropertySource("classpath:application.properties")
public class ElConfig {

	public static String example = "example";

	/**
	 * 自动给默认值
	 */
	@Value("${corePoolSize:10}")
	private int corePoolSize;

	@Value(" ${array:1, 2, 3, 4} ")
	private int[] array;

	@Value("#{ '${list}'.split(', ') }")
	private List<String> list;

	/**
	 * 没有值时直接给 null
	 */
	@Value("#{ '${list}'.empty ? null: '${list}'.split(', ') }")
	private List<String> list_;

	@Value("#{ '${map:}'.empty? null: '${map:}' }")
	private Map<String, String> map;

	/**
	 * 通过 @Value 根据 id 注入对象
	 */
	@Value("#{ elConfig }")
	private ElConfig config;

	/**
	 * 注入其他类中的静态属性
	 */
	@Value("#{ elConfig.example }")
	private String ex;

	/**
	 * 注入其他类中的实例属性(通过 getter 方法)
	 */
	@Value("#{ elConfig.getExample() }")
	private String exa;

	/**
	 * 通过 T 调用静态类
	 */
	@Value("#{ T(java.io.File).separator }")
	private String separator;

	@Value("#{ T(java.lang.Math).random() }")
	private double random;

	/**
	 * 逻辑运算
	 */
	@Value("#{elConfig.example.length() > 10}")
	private boolean b;

	public static String getExample() {
		return example;
	}
}
