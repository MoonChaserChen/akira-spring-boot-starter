package ink.akira.boot.webcore;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 雪行
 * @date 2021/2/9 3:36 下午
 */
@ConfigurationProperties(prefix = "webcore")
public class WebCoreProperties {
    private ExceptionAdvice exceptionAdvice;
    private Debug           debug;

    public ExceptionAdvice getExceptionAdvice() {
        return exceptionAdvice;
    }

    public void setExceptionAdvice(ExceptionAdvice exceptionAdvice) {
        this.exceptionAdvice = exceptionAdvice;
    }

    public Debug getDebug() {
        return debug;
    }

    public void setDebug(Debug debug) {
        this.debug = debug;
    }

    public static class ExceptionAdvice {
        private boolean enable;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }

    public static class Debug {
        private boolean enable;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}
