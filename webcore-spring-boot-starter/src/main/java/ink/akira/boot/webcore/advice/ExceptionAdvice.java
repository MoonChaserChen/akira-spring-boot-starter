package ink.akira.boot.webcore.advice;

import ink.akira.boot.webcore.WebResult;
import ink.akira.boot.webcore.constant.CodeEnum;
import ink.akira.boot.webcore.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 雪行
 * @date 2021/2/7 10:49 上午
 */
@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public WebResult exception(Exception e) {
        logger.info("ExceptionAdvice catch exception", e);
        return WebResult.fail(CodeEnum.SYSTEM_ERROR, e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public WebResult bizException(BizException bizException) {
        logger.info("ExceptionAdvice catch exception", bizException);
        return WebResult.fail(CodeEnum.BIZ_ERROR, bizException.getMessage());
    }
}
