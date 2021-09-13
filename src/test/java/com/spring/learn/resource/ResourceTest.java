package com.spring.learn.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ResourceResolver 测试")
public class ResourceTest {

    /**
     * 扫描目录下的指定资源
     * <p>
     * classpath: 如果多个jar包或文件系统中都有相同的包名，之后加载第一个
     * classpath*: 如果多个包名相同，全部加载
     */
    @Test
    public void test() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:/**/*.xml");
        assertNotNull(resources);
        Arrays.stream(resources).forEach(System.out::println);
    }
}
