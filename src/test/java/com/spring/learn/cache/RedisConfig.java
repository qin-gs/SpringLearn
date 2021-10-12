package com.spring.learn.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisConfig {
    // public CacheManager cacheManager() {
    // 	return new RedisCacheManager
    // }
}
