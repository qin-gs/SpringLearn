package com.spring.learn.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常，修改状态码
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "有异常")
public class SelfException extends RuntimeException {
}
