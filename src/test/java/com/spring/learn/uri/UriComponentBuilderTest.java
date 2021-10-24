package com.spring.learn.uri;

import com.spring.learn.controller.BookController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@DisplayName("UriComponentBuilder")
public class UriComponentBuilderTest {

    private HttpServletRequest request;

    /**
     * 主要用来请求转发
     */
    @Test
    public void test() {
        UriComponents components = UriComponentsBuilder
                .fromUriString("https://example.com/hotels/{hotel}/bookings/{booking}")
                .build();
        URI uri = components.expand("42", "21").encode().toUri();

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("example.com")
                .path("/hotels/{hotel}/bookings/{booking}")
                .build()
                .expand("42", "21")
                .encode();

        UriComponents ucb = ServletUriComponentsBuilder
                .fromRequest(request)
                .replaceQueryParam("accountId", "{id}")
                .build()
                .expand("123")
                .encode();

        // 从其他控制器中获取
        UriComponents uc1 = MvcUriComponentsBuilder
                .fromMethodName(BookController.class, "test", 21)
                .buildAndExpand(42);
        URI u1 = uriComponents.encode().toUri();

        // 避免硬编码
        // UriComponents uc2 = MvcUriComponentsBuilder
        //         .fromMethodCall(on(BookController.class).test("21")).buildAndExpand(42);
        // URI u2 = uriComponents.encode().toUri();
        //
        // UriComponentsBuilder base = ServletUriComponentsBuilder.fromCurrentContextPath().path("/en");
        // MvcUriComponentsBuilder builder = MvcUriComponentsBuilder.relativeTo(base);
        // builder.withMethodCall(on(BookController.class).test("21")).buildAndExpand(42);
        // URI u3 = uriComponents.encode().toUri();
    }
}
