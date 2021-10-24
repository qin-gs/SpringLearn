package com.spring.learn.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.Callable;

@RestController
public class AsyncController {

    @GetMapping("async")
    public Callable<String> async(HttpServletRequest request) {
        // 将ServletRequest 置于异步模式: Servlet和所有的过滤器都可以退出，但相应保持打开状态，运行稍后完成
        AsyncContext asyncContext = request.startAsync();
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        };
    }

    @GetMapping("deferredResult")
    public DeferredResult<String> deferredResult() {
        DeferredResult<String> result = new DeferredResult<>();
        // 可以在其他线程中设置值
        result.setResult("result");
        result.onTimeout(() -> System.out.println("timeout"));
        result.onCompletion(() -> System.out.println("completion"));
        return result;
    }

    /**
     * 长轮询 http流
     * 可以发送多个对象，通过HttpMessageConverter写入相应
     */
    @GetMapping("emitter")
    public ResponseBodyEmitter emitter() throws IOException {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        emitter.send("a message");
        emitter.send("another message");
        return emitter;
    }
}
