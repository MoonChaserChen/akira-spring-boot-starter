package ink.akira.boot.webcore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import ink.akira.boot.webcore.constant.CodeEnum;
import org.junit.Test;

/**
 * 测试IgnoredDebugFieldsSerializer
 *
 * @author 雪行
 * @date 2021/2/9 5:17 下午
 */
public class IgnoredDebugFieldsSerializerTest {
    @Test
    public void testParse() throws JsonProcessingException {
        boolean debugMode = true;
        for (int i = 0; i < 2; i++) {
            debugMode = !debugMode;
            WebResult webResult = WebResult.fail(CodeEnum.SYSTEM_ERROR, "system error debug info");
            SimpleBeanPropertyFilter conditionalFilter = debugMode
                    ? SimpleBeanPropertyFilter.serializeAll() : SimpleBeanPropertyFilter.serializeAllExcept("debugMsg");
            FilterProvider filters = new SimpleFilterProvider().addFilter("webResultFilter", conditionalFilter);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setFilterProvider(filters);
            System.out.println(objectMapper.writeValueAsString(webResult));
        }
    }
}
