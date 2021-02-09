package ink.akira.boot.webcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 雪行
 * @date 2021/2/9 3:35 下午
 */
@Configuration
@ComponentScan({"ink.akira.boot.webcore"})
@EnableConfigurationProperties(WebCoreProperties.class)
public class WebCoreAutoConfigure {
    @Autowired
    private WebCoreProperties webCoreProperties;
}
