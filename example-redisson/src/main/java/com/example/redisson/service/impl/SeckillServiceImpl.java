package com.example.redisson.service.impl;

import com.example.redisson.constant.SeckillRedisCache;
import com.example.redisson.service.SeckillService;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SeckillServiceImpl implements SeckillService {
    
    
    @Autowired
    RedissonClient redissonClient;

    
    @Override
    public void cache3DaysSeckillSku() {
        // step-1 获取三天内 所有 秒杀活动+活动对应商品的 信息
        // ...
        
        // step-2 将数据存入redis
        // ...
        
        // ----------------------------------使用redisson将随机码对应的信号量存入-start-------------------------------
        // 随机码
        String randomCode = UUID.randomUUID().toString();
        
        // 设置信号量（限流
        RSemaphore semaphore = redissonClient.getSemaphore(SeckillRedisCache.STOCK_SEMAPHORE + randomCode);
        semaphore.trySetPermits(10);
        // ----------------------------------使用redisson将随机码对应的信号量存入-end---------------------------------
        
        // ...
    }
    
    
}
