package com.spring.learn.feature;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.util.concurrent.*;

/**
 * Java 在 JDK1.6 提供了 Future，FutureTask，ExecutorService 等用于支持异步编程，
 * 但是 Future，FutureTask 没有提供 Callback 机制，只能主动轮询，通过 get 去获取结果。
 * Spring 的 ListenableFutureTask 对此做了扩展，支持 callback 机制。
 */
@DisplayName("spring4 新功能")
public class Spring4FeatureTest {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        ListenableFutureTask<String> task = new ListenableFutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("task execute");
                return "callable return";
            }
        });
        // 添加一些回调
        task.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failed - 1");
            }

            @Override
            public void onSuccess(String result) {
                System.out.println("success - 1");
            }
        });
        task.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failed - 2");
            }

            @Override
            public void onSuccess(String result) {
                System.out.println("success - 2");
            }
        });

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<?> submit = service.submit(task);
        System.out.println(submit.get());
    }
}
