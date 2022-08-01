package com.spring.learn.resource;

import com.spring.learn.configurable.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ResourceResolver 测试")
public class ResourceTest {

    /**
     * 扫描目录下的指定资源
     * <p>
     * classpath: 如果多个jar包或文件系统中都有相同的包名，只会加载第一个
     * classpath*: 如果多个包名相同，全部加载
     */
    @Test
    public void test() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:/**/*.xml");
        assertNotNull(resources);
        Arrays.stream(resources).forEach(System.out::println);
    }

    /**
     * 各种Resource接口
     */
    @Test
    public void resource() throws MalformedURLException, URISyntaxException, FileNotFoundException {
        // ftp, http, file
        UrlResource urlResource = new UrlResource(new URI("https://www.test.com/a.txt"));
        // classpath
        ClassPathResource classPathResource = new ClassPathResource("classpath:bean.xml");
        // file, url
        FileSystemResource fileSystemResource = new FileSystemResource(new File("a.txt"));
        // ServletContext
        // new ServletContextResource(new ServletContext(), "");
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(""));
        ByteArrayResource byteArrayResource = new ByteArrayResource(new byte[0]);
    }

    /**
     * ResourceLoader
     * 所有的ApplicationContext都实现了该接口
     * <p>
     * ResourceLoaderAware
     * 也可以直接用ApplicationContext获取，或者直接注入
     */
    @Test
    public void resourceLoaderTest() throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Resource[] resources = context.getResources("");
    }
}
