package com.spring.learn.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 常用的事件，单线程(所以如果一个事件被发布，直至并且除非所有的接收者得到的该消息，该进程被阻塞并且流程将不会继续)
 * <p>
 * 1. ContextRefreshedEvent context.refresh()这个方法发布该事件 所有的Bean被成功装载，后处理Bean被检测并激活，所有Singleton Bean 被预实例化，ApplicationContext容器已就绪可用
 * 2. ContextStartedEvent 容器启动 start()
 * 3. ContextStoppedEvent 容器停止 stop() 进行清理工作
 * 4. ContextClosedEvent 容器关闭 close()
 * 5. RequestHandledEvent
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

	/**
	 * 容器中发布此事件以后，这个方法会被触发
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("收到事件 " + event);
	}
}
