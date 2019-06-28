package ink.akira.ehcache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ehcache")
public class EhcacheProperties {
    private String diskStore = "java.io.tmpdir";
    private String name = "ehcache";
    private int maxEntriesLocalHeap = 10000;
    private boolean eternal = false;
    private int timeToIdleSeconds = 1000;
    private int timeToLiveSeconds = 2000;
    private int diskExpiryThreadIntervalSeconds = 120;
    private String memoryStoreEvictionPolicy = "LRU"; // LRU最近最少使用 FIFO先进先出 LFU较少使用
    private String persistenceStrategy = "NONE";
    private int maxEntriesLocalDisk = 0;

    private boolean peerEnable = false;
    private int port;
    private int socketTimeoutMillis;
    private String peerRmiUrls;

    public String getDiskStore() {
        return diskStore;
    }

    public void setDiskStore(String diskStore) {
        this.diskStore = diskStore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxEntriesLocalHeap() {
        return maxEntriesLocalHeap;
    }

    public void setMaxEntriesLocalHeap(int maxEntriesLocalHeap) {
        this.maxEntriesLocalHeap = maxEntriesLocalHeap;
    }

    public boolean isEternal() {
        return eternal;
    }

    public void setEternal(boolean eternal) {
        this.eternal = eternal;
    }

    public int getTimeToIdleSeconds() {
        return timeToIdleSeconds;
    }

    public void setTimeToIdleSeconds(int timeToIdleSeconds) {
        this.timeToIdleSeconds = timeToIdleSeconds;
    }

    public int getTimeToLiveSeconds() {
        return timeToLiveSeconds;
    }

    public void setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
    }

    public int getDiskExpiryThreadIntervalSeconds() {
        return diskExpiryThreadIntervalSeconds;
    }

    public void setDiskExpiryThreadIntervalSeconds(int diskExpiryThreadIntervalSeconds) {
        this.diskExpiryThreadIntervalSeconds = diskExpiryThreadIntervalSeconds;
    }

    public String getMemoryStoreEvictionPolicy() {
        return memoryStoreEvictionPolicy;
    }

    public void setMemoryStoreEvictionPolicy(String memoryStoreEvictionPolicy) {
        this.memoryStoreEvictionPolicy = memoryStoreEvictionPolicy;
    }

    public String getPersistenceStrategy() {
        return persistenceStrategy;
    }

    public void setPersistenceStrategy(String persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    public boolean isPeerEnable() {
        return peerEnable;
    }

    public void setPeerEnable(boolean peerEnable) {
        this.peerEnable = peerEnable;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSocketTimeoutMillis() {
        return socketTimeoutMillis;
    }

    public void setSocketTimeoutMillis(int socketTimeoutMillis) {
        this.socketTimeoutMillis = socketTimeoutMillis;
    }

    public String getPeerRmiUrls() {
        return peerRmiUrls;
    }

    public void setPeerRmiUrls(String peerRmiUrls) {
        this.peerRmiUrls = peerRmiUrls;
    }

    public int getMaxEntriesLocalDisk() {
        return maxEntriesLocalDisk;
    }

    public void setMaxEntriesLocalDisk(int maxEntriesLocalDisk) {
        this.maxEntriesLocalDisk = maxEntriesLocalDisk;
    }
}
