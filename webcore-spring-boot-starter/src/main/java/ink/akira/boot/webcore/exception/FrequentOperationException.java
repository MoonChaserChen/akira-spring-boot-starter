package ink.akira.boot.webcore.exception;

import ink.akira.boot.webcore.constant.CodeEnum;

/**
 * 操作过于频繁
 *
 * @author 雪行
 * @date 2021/2/9 3:58 下午
 */
public class FrequentOperationException extends CodeCapableBizException {
    @Override
    public int getCode() {
        return CodeEnum.FREQUENT_OPERATION.getCode();
    }

    public FrequentOperationException() {
    }

    public FrequentOperationException(String message) {
        super(message);
    }

    public FrequentOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrequentOperationException(Throwable cause) {
        super(cause);
    }

    public FrequentOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
