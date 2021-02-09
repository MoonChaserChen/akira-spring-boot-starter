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
    private static final int    RESULT_LIMITED  = 0;
    private static final String SCRIPT_FILE     = "/rate_limiter.lua";

    private final JedisPool     jedisPool;

    public RateLimiter(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
        try {
            luaScript = getScriptString();
        } catch (IOException e) {
            throw new RuntimeException("RateLimiter script read failed! Please check resource file: " + SCRIPT_FILE);
        }
    }

    public void tryGetToken(String key, int maxToken, double tokenRate) throws LimitedException {
        List<String> keys = new ArrayList<>(1);
        keys.add(key);
        List<String> args = new ArrayList<>(2);
        args.add(String.valueOf(maxToken));
        args.add(String.valueOf(tokenRate));
        try (Jedis jedis = jedisPool.getResource()) {
            Object eval = jedis.eval(luaScript, keys, args);
            if (RESULT_LIMITED == (long)eval) {
                throw new LimitedException("Rate limited of key: " + key);
            }
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
