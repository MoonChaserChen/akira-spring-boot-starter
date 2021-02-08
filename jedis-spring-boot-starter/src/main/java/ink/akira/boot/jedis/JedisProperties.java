package ink.akira.boot.jedis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author akira
 * Created by akira on 2019/2/14.
 */
@ConfigurationProperties(prefix = "jedis")
public class JedisProperties {
    private String host                              = "localhost";
    private int    port                              = 6379;
    private int    db                                = 0;
    private String auth;
    private int    poolMaxTotal                      = 500;
    private int    poolMaxIdle                       = 100;
    private int    poolMinIdle                       = 50;
    private int    timeout                           = 5000;
    private int    poolMaxWaitMillis                 = 5000;
    private int    poolTimeBetweenEvictionRunsMillis = 60000;
    private int    poolNumTestsPerEvictionRun        = 50;
    

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getPoolMaxTotal() {
        return poolMaxTotal;
    }

    public void setPoolMaxTotal(int poolMaxTotal) {
        this.poolMaxTotal = poolMaxTotal;
    }

    public int getPoolMaxIdle() {
        return poolMaxIdle;
    }

    public void setPoolMaxIdle(int poolMaxIdle) {
        this.poolMaxIdle = poolMaxIdle;
    }

    public int getPoolMinIdle() {
        return poolMinIdle;
    }

    public void setPoolMinIdle(int poolMinIdle) {
        this.poolMinIdle = poolMinIdle;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getPoolMaxWaitMillis() {
        return poolMaxWaitMillis;
    }

    public void setPoolMaxWaitMillis(int poolMaxWaitMillis) {
        this.poolMaxWaitMillis = poolMaxWaitMillis;
    }

    public int getPoolTimeBetweenEvictionRunsMillis() {
        return poolTimeBetweenEvictionRunsMillis;
    }

    public void setPoolTimeBetweenEvictionRunsMillis(int poolTimeBetweenEvictionRunsMillis) {
        this.poolTimeBetweenEvictionRunsMillis = poolTimeBetweenEvictionRunsMillis;
    }

    public int getPoolNumTestsPerEvictionRun() {
        return poolNumTestsPerEvictionRun;
    }

    public void setPoolNumTestsPerEvictionRun(int poolNumTestsPerEvictionRun) {
        this.poolNumTestsPerEvictionRun = poolNumTestsPerEvictionRun;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }
}
