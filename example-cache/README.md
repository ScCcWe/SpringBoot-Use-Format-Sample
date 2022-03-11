# Spring Boot整合Spring Cache

## 1）在pom文件中引入依赖

无论使用哪种连接，代码都一样；这是因为spring帮我们封装好了

### 使用jedis连接redis

整合spring-cache，实际上需要`spring-boot-starter-cache` + `spring-boot-starter-data-redis`；

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
  <exclusions>
      <exclusion>
      <groupId>io.lettuce</groupId>
      <artifactId>lettuce-core</artifactId>
      </exclusion>
  </exclusions>
</dependency>

<dependency>
  <groupId>redis.clients</groupId>
  <artifactId>jedis</artifactId>
</dependency>
```

### 使用lettuce连接redis

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
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
    name: example-cache
  
  # 配置redis
  redis:
    host: localhost
    port: 6379
    database: 9

  # 配置spring-cache
  cache:
    type: redis
    redis:
      # 缓存的存活时间：3600000ms = 1000ms * 60 * 60  即一个小时
      time-to-live: 3600000
      # 缓存空值，防止缓存击穿
      cache-null-values: true
      # 使用缓存前缀
      # 如果此项配置为true，但是自己没设置缓存前缀，则自动使用分区名为缓存前缀
      use-key-prefix: true
      # 缓存前缀
      # key-prefix: EXAMPLE_CACHE_
```

## 3）配置config

最简单的配置

```java
// @Configuration
// @EnableCaching
public class CacheConfig {
}
```

将缓存结果保存为json的配置

```java
@EnableConfigurationProperties(CacheProperties.class) // 开启属性配置的绑定功能
@EnableCaching // 开启Spring的缓存功能
@Configuration
public class CacheJsonConfig {
    
    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // config = config.entryTtl();

        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));


        // 将配置文件中的所有配置生效
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());  // ttl
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());  // key的前缀
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }

        return config;
    }
}
```
