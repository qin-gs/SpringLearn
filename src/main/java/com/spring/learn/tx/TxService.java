package com.spring.learn.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Service
public class TxService {

    @Autowired
    private DataSource dataSource;
    private PlatformTransactionManager txManager;
    /**
     * 是线程安全的，因为不维护状态
     */
    private TransactionTemplate template;

    @Autowired
    private ApplicationContext context;


    public TxService(@Qualifier("txManager") PlatformTransactionManager txManager) {
        this.txManager = txManager;
        this.template = new TransactionTemplate(txManager);
        // 可以设置一些值
        this.template.setTimeout(2 * 60);
        this.template.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);
    }

    /**
     * 可以修饰接口、类、方法，还能自选事务管理器
     */
    @Transactional(value = "txManager", rollbackFor = RuntimeException.class)
    public void getTest() {
        System.out.println(dataSource.getClass());
        throw new UnsupportedOperationException();
        // 编程式回滚事务
        // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    public void someMethod() {
        // TransactionTemplate 编程式事务管理
        // 有返回值
        template.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    return updateDataBase();
                } catch (RuntimeException e) {
                    status.setRollbackOnly(); // 抛出异常时回滚事务
                    e.printStackTrace();
                }
                return null;
            }
        });
        // 没有返回值
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                updateDataBase();
            }
        });
    }

    public void someMethod(String s) {
        DefaultTransactionDefinition definition = new TransactionTemplate(txManager);
        definition.setName("my transaction");
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(definition);
        try {
            updateDataBase();
        } catch (RuntimeException e) {
            status.setRollbackOnly();
            throw e;
        }
        txManager.commit(status);
    }

    private Object updateDataBase() {
        context.publishEvent(new TxEvent("txEvent", "name"));
        return null;
    }
}
