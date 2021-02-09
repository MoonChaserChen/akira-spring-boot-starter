package ink.akira.boot.jedis.limit;

import ink.akira.boot.webcore.exception.FrequentOperationException;

/**
 * 被限流异常
 *
 * @author 雪行
 * @date 2021/2/8 3:31 下午
 */
public class LimitedException extends FrequentOperationException {
    public LimitedException() {
    }

    public LimitedException(String message) {
        super(message);
    }
}
