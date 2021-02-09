package ink.akira.boot.webcore;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 雪行
 * @date 2021/2/9 3:36 下午
 */
@Configuration
@ConfigurationProperties(prefix = "webcore")
public class WebCoreProperties {
    private boolean debug = false;

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
