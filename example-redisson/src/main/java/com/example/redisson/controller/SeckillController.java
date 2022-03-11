package com.example.redisson.controller;

import com.example.redisson.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/seckill")
@RestController
public class SeckillController {


    @Autowired
    SeckillService seckillService;


    @GetMapping("/set-stock-semaphore")
    public void setStockSemaphore() {

        seckillService.cache3DaysSeckillSku();

    }


}
