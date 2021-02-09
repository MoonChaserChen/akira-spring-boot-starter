package ink.akira.boot.jedis.limit;

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
        rateLimiter.addLimitIfNotExists(key, 10, 1);
        for (int i = 0; i < 40; i++) {
            try {
                rateLimiter.tryGetToken(key);
                System.out.println(System.currentTimeMillis() + ": true");
            } catch (LimitedException e) {
                System.out.println(System.currentTimeMillis() + ": false");
            }
            Thread.sleep(50L);
        }
    }
}
