package ink.akira.boot.jedis.limit.delay;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 限制两次访问间隔时间
 *
 * @author 雪行
 * @date 2021/2/9 7:55 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DelayLimit {
    /**
     * @return 限流的key，spEL表达式。spEL参数为注解所加方法的参数
     */
    String key();

    /**
     * @return 访问间隔时间
     */
    long leastDelay();

    /**
     * @return 访问间隔时间单位
     */
    TimeUnit timeUnit();
}
