package ink.akira.boot.jedis;

import ink.akira.boot.jedis.limit.delay.DelayLimiter;
import ink.akira.boot.jedis.limit.rate.RateLimiter;
import ink.akira.boot.jedis.service.JedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author akira
 * Created by akira on 2019/2/14.
 */
@Configuration
@ComponentScan({"ink.akira.boot.jedis"})
@EnableConfigurationProperties(JedisProperties.class)
public class JedisAutoConfigure {
    @Autowired
    private JedisProperties jedisProperties;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(jedisProperties.getPoolMaxTotal());
        config.setMaxIdle(jedisProperties.getPoolMaxIdle());
        config.setMinIdle(jedisProperties.getPoolMinIdle());
        config.setMaxWaitMillis(jedisProperties.getPoolMaxWaitMillis());
        config.setNumTestsPerEvictionRun(jedisProperties.getPoolNumTestsPerEvictionRun());
        config.setTimeBetweenEvictionRunsMillis(jedisProperties.getPoolTimeBetweenEvictionRunsMillis());
        return config;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig config) {
        return new JedisPool(config, jedisProperties.getHost(),jedisProperties.getPort(),
                jedisProperties.getTimeout(),jedisProperties.getAuth(), jedisProperties.getDb());
    }

    @Bean
    public JedisDAO jedisService(JedisPool jedisPool) {
        return new JedisDAO(jedisPool);
    }

    @Bean
    public RateLimiter rateLimiter(JedisPool jedisPool) {
        return new RateLimiter(jedisPool);
    }

    @Bean
    public DelayLimiter delayLimiter(JedisDAO jedisDAO) {
        return new DelayLimiter(jedisDAO);
    }
}
