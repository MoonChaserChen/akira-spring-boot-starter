package ink.akira.boot.webcore.exception;

/**
 * 带有异常码的业务异常
 *
 * @author 雪行
 * @date 2021/2/9 2:56 下午
 */
public abstract class CodeCapableBizException extends BizException implements CodeCapable {
    public CodeCapableBizException() {
    }

    public CodeCapableBizException(String message) {
        super(message);
    }

    public CodeCapableBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeCapableBizException(Throwable cause) {
        super(cause);
    }

    public CodeCapableBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
