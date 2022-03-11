# Spring Boot整合Redisson

## 1）在pom文件中引入依赖

整合spring-cache，实际上需要`spring-boot-starter-cache` + `spring-boot-starter-data-redis`；

```java
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <version>3.16.7</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

## 2）配置文件properties.yaml中添加配置

```properties
server:
  port: 8080


spring:
  application:
    name: example-redisson


# 自己添加的RedissonProperties
redisson:
  config:
    host: redis://127.0.0.1:6379
    database: 8
```

## 3）配置config

配置Redisson

```java
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
```

将配置项写到properties

```java
@ConfigurationProperties("redisson.config")
@Component
@Data
public class RedissonProperties {
    
    private String host = "redis://127.0.0.1:6379";
    
    private int database = 0;
    
}
```

