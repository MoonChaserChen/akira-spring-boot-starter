package ink.akira.boot.webcore.exception;

/**
 * 业务异常
 *
 * @author 雪行
 * @date 2021/2/9 2:55 下午
 */
public class BizException extends Exception{
    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
