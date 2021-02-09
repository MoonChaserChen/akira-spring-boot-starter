package ink.akira.boot.jedis.limit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 限流
 *
 * @author 雪行
 * @date 2021/2/8 3:07 下午
 */
public class RateLimiter implements Limiter {
    private final String        initLuaScript;
    private final String        visitLuaScript;
    private static final int    RESULT_LIMITED    = 0;
    private static final int    RESULT_NOT_INIT   = 2;
    private static final String INIT_SCRIPT_FILE  = "/rate_limiter_init.lua";
    private static final String VISIT_SCRIPT_FILE = "/rate_limiter_visit.lua";
    private static final String MAX_TOKEN         = "max_token";
    private static final String TOKEN_RATE        = "token_rate";

    private final JedisPool     jedisPool;

    public RateLimiter(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
        try {
            initLuaScript = getScriptString(INIT_SCRIPT_FILE);
        } catch (IOException e) {
            throw new RuntimeException(
                "RateLimiter script read failed! Please check resource file: " + INIT_SCRIPT_FILE);
        }
        try {
            visitLuaScript = getScriptString(VISIT_SCRIPT_FILE);
        } catch (IOException e) {
            throw new RuntimeException(
                "RateLimiter script read failed! Please check resource file: " + VISIT_SCRIPT_FILE);
        }
    }

    public void addLimitIfNotExists(String key, int maxToken, double tokenRate) {
        try (Jedis jedis = jedisPool.getResource()) {
            List<String> keys = new ArrayList<>(1);
            keys.add(key);
            List<String> args = new ArrayList<>(2);
            args.add(String.valueOf(maxToken));
            args.add(String.valueOf(tokenRate));
            jedis.eval(initLuaScript, keys, args);
        }
    }

    public void tryGetToken(String key) throws LimitedException {
        List<String> keys = new ArrayList<>(1);
        keys.add(key);
        try (Jedis jedis = jedisPool.getResource()) {
            Object eval = jedis.eval(visitLuaScript, keys, new ArrayList<>(0));
            if (RESULT_LIMITED == (long)eval) {
                throw new LimitedException("Rate limited of key: " + key);
            }
        }
    }

    public void resetMaxToken(String key, int maxToken) {
        try (Jedis jedis = jedisPool.getResource()) {
            Map<String, String> hash = new HashMap<>(2);
            hash.put(MAX_TOKEN, String.valueOf(maxToken));
            jedis.hmset(key, hash);
        }
    }

    public void resetTokenRate(String key, int tokenRate) {
        try (Jedis jedis = jedisPool.getResource()) {
            Map<String, String> hash = new HashMap<>(2);
            hash.put(TOKEN_RATE, String.valueOf(tokenRate));
            jedis.hmset(key, hash);
        }
    }

    private String getScriptString(String resourceFile) throws IOException {
        try (InputStream is = RateLimiter.class.getResourceAsStream(resourceFile);
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
