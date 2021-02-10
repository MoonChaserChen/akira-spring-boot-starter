package ink.akira.boot.jedis.limit.concurrent;

import ink.akira.boot.jedis.limit.SpelStringParser;
import ink.akira.boot.webcore.exception.FrequentOperationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 并发限制切面
 *
 * @author 雪行
 * @date 2021/2/10 12:51 下午
 */
@Aspect
@Component
public class ConcurrentLimitAspect {
    @Autowired
    private ConcurrentLimiter concurrentLimiter;

    @Around(value = "@annotation(concurrentLimit)")
    public Object around(ProceedingJoinPoint joinPoint, ConcurrentLimit concurrentLimit) throws Throwable {
        String key = SpelStringParser.parseKey(joinPoint, concurrentLimit.key());
        if (!concurrentLimiter.tryAcquire(key, concurrentLimit.lockTime(), concurrentLimit.timeUnit())) {
            throw new FrequentOperationException("Concurrent visit for key: " + key);
        }
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            concurrentLimiter.release(key);
        }
    }
}
