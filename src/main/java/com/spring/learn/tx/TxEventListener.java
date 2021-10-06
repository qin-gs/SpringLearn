package com.spring.learn.tx;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TxEventListener {
    /**
     * 监听事务事件
     * 执行数据库操作后，发送消息或事件来异步调用其他组件执行相应的操作
     * <p>
     * 用户注册后发送激活码；
     * 配置修改后发送更新事件等。
     */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT, fallbackExecution = true)
    public void listener(TxEvent event) {

    }
}

class TxEvent extends ApplicationEvent {

    private final String name;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public TxEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
