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

## 限流
### 限制访问频率
使用令牌桶算法实现，实用方式 `@RateLimit`：
```java
@RestController
public class HelloController {
    @RequestMapping("/hello")
    @RateLimit(key = "'prefix_' + #userId", maxToken = 2, tokenRate = 2)
    public String hello(String userId) {
        System.out.println("visit");
        return "success";
    }
}
```
参数说明：
1. key： 限流的key，spEL表达式。spEL参数为注解所加方法的参数，如上述例子中#userId直接使用hello方法中的userId参数
2. maxToken： 令牌桶最大令牌数，也是令牌桶初始化时的令牌数
3. tokenRate： 令牌桶中每秒生成的令牌数

如发生限流，则会抛出 `FrequentOperationException` 异常，可通过[webcore.exception-advice.enable](../webcore-spring-boot-starter/README.md#其它配置)进行统一处理

### 限制访问间隔
```java
@RestController
public class HelloController {
    @RequestMapping("/hello")
    @DelayLimit(key = "'delay_' + #userId", leastDelay = 1, timeUnit = TimeUnit.SECONDS)
    public String hello(String userId) {
        System.out.println("visit");
        return "success";
    }
}
```
如上，限制两次访问间隔不小于1秒，防止页面连续点击等情况导致快速连续的相同关键性操作。

如被限制，则会抛出 `FrequentOperationException` 异常，可通过[webcore.exception-advice.enable](../webcore-spring-boot-starter/README.md#其它配置)进行统一处理

