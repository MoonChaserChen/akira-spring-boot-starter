package ink.akira.boot.jedis.limit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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
    private RateLimiter                          rateLimiter;
    private final SpelExpressionParser           parser         = new SpelExpressionParser();
    private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    @Pointcut(value = "@annotation(rateLimit)")
    public void pointCut(RateLimit rateLimit) {}

    @Around(value = "pointCut(rateLimit)", argNames = "joinPoint,rateLimit")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = parseKey(joinPoint, rateLimit.key());
        int max = rateLimit.maxToken();
        int rate = rateLimit.tokenRate();
        rateLimiter.addLimitIfNotExists(key, max, rate);
        rateLimiter.tryGetToken(key);
        return joinPoint.proceed(joinPoint.getArgs());
    }

    private String parseKey(JoinPoint joinPoint, String spelString) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        Expression expression = parser.parseExpression(spelString);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for(int i = 0 ; i < args.length ; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context, String.class);
    }
}
