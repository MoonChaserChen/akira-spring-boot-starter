package ink.akira.boot.jedis.limit.delay;

import ink.akira.boot.jedis.limit.SpelStringParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ink.akira.boot.jedis.limit.rate.RateLimit;
import ink.akira.boot.jedis.limit.rate.RateLimiter;
import ink.akira.boot.webcore.exception.FrequentOperationException;

/**
 * 限流切面
 *
 * @author 雪行
 * @date 2021/2/8 3:43 下午
 */
@Aspect
@Component
public class DelayLimitAspect {
    @Autowired
    private DelayLimiter delayLimiter;

    @Around(value = "@annotation(delayLimit)")
    public Object around(ProceedingJoinPoint joinPoint, DelayLimit delayLimit) throws Throwable {
        String key = SpelStringParser.parseKey(joinPoint, delayLimit.key());
        if (!delayLimiter.tryAcquire(key, delayLimit.leastDelay(), delayLimit.timeUnit())) {
            throw new FrequentOperationException("Not pass the delay limit for key: " + key);
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }
}
