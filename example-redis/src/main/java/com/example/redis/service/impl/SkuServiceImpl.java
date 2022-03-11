package com.example.redis.service.impl;

import com.example.redis.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


@Service
public class SkuServiceImpl implements SkuService {
    
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    
    @Override
    public void indexPageSkuToRedis() {
        
        // STEP-1 获取首页要展示的sku商品
        String a = "xxx";
        
        // STEP-2 将商品入redis
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set("skus", a);
    }
    
    
}
