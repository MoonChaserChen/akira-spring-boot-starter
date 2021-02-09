package ink.akira.boot.jedis.limit.rate;

import java.lang.annotation.*;

/**
 * 限流
 *
 * @author 雪行
 * @date 2021/2/8 3:37 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RateLimit {
    /**
     * @return 限流的key，spEL表达式。spEL参数为注解所加方法的参数
     */
    String key();

    /**
     * @return 令牌桶最大令牌数
     */
    int maxToken();

    /**
     * @return 令牌生成速率，每秒生成多少个
     */
    int tokenRate();
}
