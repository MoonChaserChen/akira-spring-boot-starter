package ink.akira.boot.jedis.limit.delay;

import java.util.concurrent.TimeUnit;

import ink.akira.boot.jedis.limit.Limiter;
import ink.akira.boot.jedis.service.JedisDAO;

/**
 * 下次访问延迟时间限制（两次连续访问不得低于给定时间）
 *
 * @author 雪行
 * @date 2021/2/9 7:40 下午
 */
public class DelayLimiter implements Limiter {
    private JedisDAO jedisDAO;

    public DelayLimiter(JedisDAO jedisDAO) {
        this.jedisDAO = jedisDAO;
    }

    public boolean tryAcquire(String key, long leastDelay, TimeUnit timeUnit) {
        return jedisDAO.getLock(key, String.valueOf(System.currentTimeMillis()), leastDelay, timeUnit);
    }
}
