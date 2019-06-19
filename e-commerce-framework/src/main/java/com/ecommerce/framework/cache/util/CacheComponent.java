package com.ecommerce.framework.cache.util;

import com.ecommerce.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: Supply Center
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-06-18 23:00
 */
@Slf4j
@Component
public class CacheComponent {

   @Autowired
   private EhCacheManager cacheManager;

    public Object getCacheByKeyAndName(String ecName, String key) {
        CacheManager manager = cacheManager.getCacheManager();
        Cache cache = manager.getCache(ecName);
        if (cache != null && cache.isKeyInCache(key)) {
            Element el = cache.get(key);
            if (el != null){
                return el.getObjectValue();
            }

        }
        return null;
    }

    public void setCache(String cacheName,String key, Object value) {
        if (value != null) {
            CacheManager manager = cacheManager.getCacheManager();
            manager.addCacheIfAbsent(cacheName);
            Cache cache = manager.getCache(cacheName);
            Element el = new Element(key, value);
            cache.put(el);
        }
    }
    public void removeCache(String cacheName,String key) {
        CacheManager manager = cacheManager.getCacheManager();
        Cache cache = manager.getCache(cacheName);
        if(StringUtils.isBlank(key)){
            cache.flush();
        }else{
            cache.remove(key);
        }
    }
}
