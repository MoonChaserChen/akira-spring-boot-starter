## 如何使用jedis-spring-boot-starter

## quickStart
- 引入依赖
```
<dependency>
    <groupId>ink.akira.boot</groupId>
    <artifactId>jedis-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
- 增加配置
```
jedis.host=localhost
jedis.port=6379
jedis.db=0
jedis.auth=foobared
```
- 在Spring中使用
```java
@Autowired
private JedisDAO jedisDAO;
```

## 其它配置
```
jedis.host=localhost
jedis.port=6379
jedis.db=0
jedis.auth=foobared
jedis.pool-max-idle=100
jedis.pool-max-total=500
jedis.pool-max-wait-millis=5000
jedis.pool-min-idle=50
jedis.pool-num-tests-per-eviction-run=50
jedis.pool-time-between-eviction-runs-millis=60000
jedis.timeout=5000
```