package ink.akira.boot.jedis.limit.rate;

import ink.akira.boot.jedis.limit.SpelStringParser;
import ink.akira.boot.webcore.exception.FrequentOperationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 限流切面
 *
 * @author 雪行
 * @date 2021/2/8 3:43 下午
 */
@Aspect
@Component
public class RateLimitAspect {
    @Autowired
    private RateLimiter rateLimiter;

    @Around(value = "@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = SpelStringParser.parseKey(joinPoint, rateLimit.key());
        int maxToken = rateLimit.maxToken();
        double tokenRate = rateLimit.tokenRate();
        if (!rateLimiter.tryAcquire(key, maxToken, tokenRate)) {
            throw new FrequentOperationException("Can not get rate limit token of: " + key);
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }
}
