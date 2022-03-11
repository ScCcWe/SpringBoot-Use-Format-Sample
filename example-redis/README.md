# Spring Boot整合redis

## 整合流程

### 1）在pom文件中引入相关依赖

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 2）在application.properties中添加redis相关配置

```properties
spring:
  redis:
    host: localhost
    port: 6379
    database: 9
```

### 3）使用

```java
@Autowired
StringRedisTemplate stringRedisTemplate;
```
