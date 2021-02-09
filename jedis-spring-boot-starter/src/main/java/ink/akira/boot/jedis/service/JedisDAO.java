package ink.akira.boot.jedis.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author akira
 * Created by akira on 2019/2/14.
 */
public class JedisDAO {
    private final JedisPool jedisPool;

    public JedisDAO(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    public void set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        }
    }

    public boolean getLock(String lockKey, String identifier) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setnx(lockKey, identifier) == 1L;
        }
    }

    public void delete(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        }
    }
}
