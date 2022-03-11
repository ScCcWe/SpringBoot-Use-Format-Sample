package com.example.redis.controller;

import com.example.redis.service.SkuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sku")
public class SkuController {


    @Resource
    SkuService skuService;


    @GetMapping("/index-to-redis")
    public void setToRedis() {

        skuService.indexPageSkuToRedis();
        
    }


}
