package ink.akira.boot.jedis.limit;

import ink.akira.boot.jedis.limit.concurrent.ConcurrentLimiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * SpringBoot测试
 *
 * @author 雪行
 * @date 2021/2/10 1:24 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration()
@ComponentScan("ink.akira.boot.jedis")
public class WebTest {
    @Autowired
    private ConcurrentLimiter concurrentLimiter;

    @Test
    public void testLock() {
        System.out.println(concurrentLimiter.tryAcquire("abc", 0, TimeUnit.SECONDS));
        System.out.println(concurrentLimiter.tryAcquire("abc", 0, TimeUnit.SECONDS));
        System.out.println(concurrentLimiter.tryAcquire("abc", 0, TimeUnit.SECONDS));
    }
}
