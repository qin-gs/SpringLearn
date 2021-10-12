package com.spring.learn.cache;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class EhcacheConfig {

    /**
     * 缓存管理器
     */
    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cm) {
        return new EhCacheCacheManager(cm);
    }

    /**
     * 缓存
     */
    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("xml/cache/ehcache.xml"));
        return factoryBean;
    }
}
