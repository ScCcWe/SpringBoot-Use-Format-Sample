package com.example.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    
    @Bean
    public RedissonClient redissonClient(RedissonProperties properties){

        Config config = new Config();
        
        // 单机配置
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress(properties.getHost());
        serverConfig.setDatabase(properties.getDatabase());
        
        // 集群配置
        // ClusterServersConfig serversConfig = config.useClusterServers();
        // serversConfig.addNodeAddress("127.0.0.1:7004", "127.0.0.1:7001");

        return Redisson.create(config);
        
    }
    
}
