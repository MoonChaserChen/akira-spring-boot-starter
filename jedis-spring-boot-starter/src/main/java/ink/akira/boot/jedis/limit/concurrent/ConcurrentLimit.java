package ink.akira.boot.jedis.limit.concurrent;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 并发限制，同一个KEY在同一时间只允许一个访问者（通过Redis分布式锁实现）
 *
 * @author 雪行
 * @date 2021/2/10 12:44 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ConcurrentLimit {
    /**
     * @return 限流的key，spEL表达式。spEL参数为注解所加方法的参数
     */
    String key();
    /**
     * @return 锁最长时间（防止锁未成功释放而一直锁住）
     */
    long lockTime() default 0;

    /**
     * @return 锁时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
