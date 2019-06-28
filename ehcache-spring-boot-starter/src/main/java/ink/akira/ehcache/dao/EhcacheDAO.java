package ink.akira.ehcache.dao;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class EhcacheDAO {
    private Cache cache;

    public EhcacheDAO() {
    }

    public EhcacheDAO(Cache cache) {
        this.cache = cache;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Object get(Object key){
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public Object getQuiet(Object key){
        Element element = cache.getQuiet(key);
        return element == null ? null : element.getObjectValue();
    }

    public void put(Object key, Object value){
        Element e = new Element(key, value);
        cache.put(e);
    }

    public void putQuiet(Object key, Object value){
        Element e = new Element(key, value);
        cache.putQuiet(e);
    }
}
