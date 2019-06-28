package ink.akira.jedis;

import ink.akira.jedis.service.JedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by akira on 2019/2/14.
 */
@Configuration
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


}
