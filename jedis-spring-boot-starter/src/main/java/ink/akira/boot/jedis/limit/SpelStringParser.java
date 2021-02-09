package ink.akira.boot.jedis.limit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * spEL字符转换
 *
 * @author 雪行
 * @date 2021/2/9 7:59 下午
 */
public class SpelStringParser {
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();

    public static String parseKey(JoinPoint joinPoint, String spelString) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String[] paramNames = new DefaultParameterNameDiscoverer().getParameterNames(methodSignature.getMethod());
        Expression expression = PARSER.parseExpression(spelString);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context, String.class);
    }
}
