package ink.akira.boot.jedis.limit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 使用redis进行限制
 *
 * @author 雪行
 * @date 2021/2/8 3:05 下午
 */
public interface Limiter {
    default String parseKey(JoinPoint joinPoint, String spelString) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = new DefaultParameterNameDiscoverer().getParameterNames(methodSignature.getMethod());
        Expression expression = new SpelExpressionParser().parseExpression(spelString);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for(int i = 0 ; i < args.length ; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context, String.class);
    }
}
