package com.spring.learn.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 开启缓存管理
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * 声明缓存管理器
     */
    @Bean
    public CacheManager cacheManager() {
        // 缓存存储基于内存，声明周期是应用相关的
        return new ConcurrentMapCacheManager();
    }

}
