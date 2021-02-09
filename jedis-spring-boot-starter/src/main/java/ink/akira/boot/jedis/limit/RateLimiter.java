package ink.akira.boot.jedis.limit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 限流
 *
 * @author 雪行
 * @date 2021/2/8 3:07 下午
 */
public class RateLimiter implements Limiter {
    private final String        luaScript;
    private static final int    RESULT_UNLIMITED = 1;
    private static final String SCRIPT_FILE      = "/rate_limiter.lua";

    private final JedisPool     jedisPool;

    public RateLimiter(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
        try {
            luaScript = getScriptString();
        } catch (IOException e) {
            throw new RuntimeException("RateLimiter script read failed! Please check resource file: " + SCRIPT_FILE);
        }
    }

    public boolean tryAcquire(String key, int maxToken, double tokenRate) {
        List<String> keys = new ArrayList<>(1);
        keys.add(key);
        List<String> args = new ArrayList<>(2);
        args.add(String.valueOf(maxToken));
        args.add(String.valueOf(tokenRate));
        try (Jedis jedis = jedisPool.getResource()) {
            Object eval = jedis.eval(luaScript, keys, args);
            return RESULT_UNLIMITED == (long)eval;
        }
    }

    private String getScriptString() throws IOException {
        try (InputStream is = RateLimiter.class.getResourceAsStream(SCRIPT_FILE);
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();) {
            int result = bis.read();
            while (result != -1) {
                buf.write((byte)result);
                result = bis.read();
            }
            return buf.toString("UTF-8");
        }
    }
}
