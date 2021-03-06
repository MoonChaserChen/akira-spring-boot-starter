package ink.akira.boot.jedis.limit;

import ink.akira.boot.jedis.limit.rate.RateLimiter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 测试RateLimiter
 *
 * @author 雪行
 * @date 2021/2/9 10:52 上午
 */
public class RateLimiterTest {
    private JedisPool jedisPool;

    @Before
    public void initJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(500);
        config.setMaxIdle(100);
        config.setMinIdle(50);
        config.setMaxWaitMillis(5000);
        config.setNumTestsPerEvictionRun(50);
        config.setTimeBetweenEvictionRunsMillis(60000);
        jedisPool = new JedisPool(config, "localhost", 6379, 5000, null, 0);
    }

    @After
    public void closeJedisPool() {
        jedisPool.close();
    }

    @Test
    public void testLimit() throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(jedisPool);
        String key = "abc";
        for (int i = 0; i < 40; i++) {
            System.out.println(System.currentTimeMillis() + ": " + rateLimiter.tryAcquire(key, 3, 1));
            Thread.sleep(50L);
        }
    }
}
