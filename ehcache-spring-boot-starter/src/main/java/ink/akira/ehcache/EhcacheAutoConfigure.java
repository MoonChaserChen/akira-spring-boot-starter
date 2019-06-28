package ink.akira.ehcache;

import ink.akira.ehcache.dao.EhcacheDAO;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.*;
import net.sf.ehcache.distribution.RMICacheManagerPeerListenerFactory;
import net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory;
import net.sf.ehcache.distribution.RMICacheReplicatorFactory;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(EhcacheProperties.class)
public class EhcacheAutoConfigure {
    @Autowired
    private EhcacheProperties ehcacheProperties;

    @Bean
    public Configuration configuration() {
        Configuration configuration = new Configuration()
                // 临时文件目录
                .diskStore(new DiskStoreConfiguration().path(ehcacheProperties.getDiskStore()))
                .cache(new CacheConfiguration(ehcacheProperties.getName(), ehcacheProperties.getMaxEntriesLocalHeap())
                        // 清理机制：LRU最近最少使用 FIFO先进先出 LFU较少使用
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
                        // 元素最大闲置时间
                        .timeToIdleSeconds(ehcacheProperties.getTimeToIdleSeconds())
                        // 元素最大生存时间
                        .timeToLiveSeconds(ehcacheProperties.getTimeToLiveSeconds())
                        // 元素是否永久缓存
                        .eternal(ehcacheProperties.isEternal())
                        // 缓存清理时间(默认120秒)
                        .diskExpiryThreadIntervalSeconds(ehcacheProperties.getDiskExpiryThreadIntervalSeconds())
                        // 持久化策略
                        .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.valueOf(ehcacheProperties.getPersistenceStrategy())))
                        // 磁盘中最大缓存对象数0表示无穷大
                        .maxEntriesLocalDisk(ehcacheProperties.getMaxEntriesLocalDisk())
                        .cacheEventListenerFactory(new CacheConfiguration.CacheEventListenerFactoryConfiguration().className(RMICacheReplicatorFactory.class.getName()))//
                );
        if (ehcacheProperties.isPeerEnable()) {
            //指定除自身之外的网络群体中其他提供同步的主机列表，用“|”分开不同的主机
            configuration.cacheManagerPeerProviderFactory(new FactoryConfiguration<FactoryConfiguration<?>>()
                    .className(RMICacheManagerPeerProviderFactory.class.getName())
                    // 例如： peerDiscovery=manual,rmiUrls=//localhost:40004/metaCache|//localhost:40005/metaCache
                    .properties("peerDiscovery=manual,rmiUrls=" + ehcacheProperties.getPeerRmiUrls())
            )
             //配宿主主机配置监听程序
            .cacheManagerPeerListenerFactory(new FactoryConfiguration<FactoryConfiguration<?>>()
                    .className(RMICacheManagerPeerListenerFactory.class.getName())
                    .properties(String.format("port=%s,socketTimeoutMillis=%s", ehcacheProperties.getPort(), ehcacheProperties.getSocketTimeoutMillis()))
            );
        }
        return configuration;
    }

    @Bean
    public CacheManager cacheManager(Configuration configuration) {
        return CacheManager.create(configuration);
    }

    @Bean
    public Cache cache(CacheManager cacheManager) {
        return cacheManager.getCache(ehcacheProperties.getName());
    }

    @Bean
    public EhcacheDAO ehcacheDAO(Cache cache) {
        return new EhcacheDAO(cache);
    }
}
