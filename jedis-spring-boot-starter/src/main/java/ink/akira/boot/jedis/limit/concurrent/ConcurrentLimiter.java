package ink.akira.boot.jedis.limit.concurrent;

import ink.akira.boot.jedis.service.JedisDAO;

import java.util.concurrent.TimeUnit;

/**
 * 并发限制
 *
 * @author 雪行
 * @date 2021/2/10 12:48 下午
 */
public class ConcurrentLimiter {
    private JedisDAO jedisDAO;

    public ConcurrentLimiter(JedisDAO jedisDAO) {
        this.jedisDAO = jedisDAO;
    }

    public boolean tryAcquire(String key, long lockTime, TimeUnit timeUnit) {
        if (lockTime <= 0) {
            return jedisDAO.getLock(key, String.valueOf(System.currentTimeMillis()));
        }
        return jedisDAO.getLock(key, String.valueOf(System.currentTimeMillis()), lockTime, timeUnit);
    }

    public void release(String key) {
        jedisDAO.delete(key);
    }
}
