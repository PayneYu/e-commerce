package com.ecommerce.framework.cache.aspect;

import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.cache.annotation.CacheClean;
import com.ecommerce.framework.cache.annotation.CacheGet;
import com.ecommerce.framework.cache.component.CacheComponent;
import com.ecommerce.framework.cache.util.KeyGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class CacheAspect {

    @Autowired
    private CacheComponent cacheComponent;

    @Around("@annotation(com.ecommerce.framework.cache.annotation.CacheGet)")
    public Object cacheGet(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        CacheGet methodType = method.getAnnotation(CacheGet.class);
        String cacheName = methodType.cacheName();
        if(StringUtils.isBlank(cacheName)){
            cacheName = joinPoint.getTarget().getClass().getName();
        }
        String key = parserKey(method, args, methodType.key());
        Object object = cacheComponent.getCacheByKeyAndName(cacheName,key);
        if (object==null) {
            long l = System.currentTimeMillis();
            object = joinPoint.proceed(args);
            log.debug("read from DB spend [ " + (System.currentTimeMillis() - l) + " ]millis");
            cacheComponent.setCache(cacheName, key,object);
        }
        return object;
    }

    private String parserKey(Method method, Object[] args, String oKey) {
        if (StringUtils.isBlank(oKey)) {
            return KeyGeneratorUtil.generateKey(method, args);
        }
        return oKey;
    }

    @Around("@annotation(com.ecommerce.framework.cache.annotation.CacheClean)")
    public Object cacheClean(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        CacheClean methodType = method.getAnnotation(CacheClean.class);
        String cacheName = methodType.cacheName();
        if(StringUtils.isBlank(cacheName)){
            cacheName = joinPoint.getTarget().getClass().getName();
        }
        cacheComponent.removeCache(cacheName);
        Object[] args = joinPoint.getArgs();
        return joinPoint.proceed(args);
    }

//    @Around("@annotation(com.ecommerce.framework.cache.annotation.CachePage)")
//    public Object cachePage(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
//        Method method = signature.getMethod();
//        CachePage methodType = method.getAnnotation(CachePage.class);
//        String cacheName = methodType.cacheName();
//        if(StringUtils.isBlank(cacheName)){
//            cacheName = joinPoint.getTarget().getClass().getSimpleName();
//        }
//        String key = KeyGeneratorUtil.generatePageKey(args);
//        Object object = cacheComponent.getCacheByKeyAndName(cacheName,key);
//        if (object==null) {
//            object = joinPoint.proceed(args);
//            cacheComponent.setCache(cacheName, key,object);
//        }
//        return object;
//    }

}
