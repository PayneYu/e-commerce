package com.ecommerce.framework.cache.component;

import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.cache.model.CacheBean;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Supply Center
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-06-18 23:00
 */
@Slf4j
@Component
public class CacheComponent {

    @Resource
    protected SqlSessionFactory sqlSessionFactory;


   public List<CacheBean> findCacheList(CacheBean searchBean){
       List<CacheBean> list = new ArrayList<>();
       CacheManager manager = CacheManager.create();
       String[] cacheNames = manager.getCacheNames();
       for (String cacheName : cacheNames) {
           if(StringUtils.isNotBlank(searchBean.getCacheName())&&
                   !cacheName.contains(searchBean.getCacheName())){
                   continue;
           }
           Cache cache = manager.getCache(cacheName);
           List keys = cache.getKeys();
           for (Object key : keys) {
               String keyStr = key.toString();
               if(StringUtils.isNotBlank(searchBean.getKey())&&
                       !keyStr.contains(searchBean.getKey())){
                   continue;
               }
               Element el =  cache.get(key);
               if(el!=null){
                   CacheBean cacheBean = new CacheBean();
                   cacheBean.setKey(keyStr);
                   cacheBean.setCacheName(cacheName);
                   cacheBean.setValue( el.getObjectValue().toString());
                   list.add(cacheBean);
               }
           }
       }
       return list;
   }

    public Object getCacheByKeyAndName(String ecName, String key) {
        CacheManager manager = CacheManager.create();
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
            CacheManager manager = CacheManager.create();;
            manager.addCacheIfAbsent(cacheName);
            Cache cache = manager.getCache(cacheName);
            Element el = new Element(key, value);
            cache.put(el);
        }
    }
    public void removeCache(String cacheName) {
       if(cacheName.contains("Mapper")){
           clearMybatisCache(cacheName);
       }else{
           CacheManager manager = CacheManager.create();
           if(manager.cacheExists(cacheName)){
               manager.removeCache(cacheName);
           }
           String cacheMapperName = cacheName.replace("service.impl","mapper").replace("ServiceImpl","Mapper");
           if(cacheMapperName.contains("Mapper")){
               clearMybatisCache(cacheMapperName);
           }
       }

    }

    private void clearMybatisCache(String cacheName){
        Configuration config = sqlSessionFactory.getConfiguration();
        if(config.hasCache(cacheName)) {
            org.apache.ibatis.cache.Cache cache = config.getCache(cacheName);
            if(cache != null){
                cache.clear();
            }
        }
    }

}
