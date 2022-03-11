package com.example.redisson.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("redisson.config")
@Component
@Data
public class RedissonProperties {
    
    private String host = "redis://127.0.0.1:6379";
    
    private int database = 0;
    
}
