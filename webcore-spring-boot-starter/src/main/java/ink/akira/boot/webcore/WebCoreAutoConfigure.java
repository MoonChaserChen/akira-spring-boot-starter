package ink.akira.boot.webcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

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

    @Bean
    public ObjectMapper objectMapper() {
        SimpleBeanPropertyFilter conditionalFilter = webCoreProperties.getDebug().isEnable()
                ? SimpleBeanPropertyFilter.serializeAll() : SimpleBeanPropertyFilter.serializeAllExcept("debugMsg");
        FilterProvider filters = new SimpleFilterProvider().addFilter("webResultFilter", conditionalFilter);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setFilterProvider(filters);
        return objectMapper;
    }
}
