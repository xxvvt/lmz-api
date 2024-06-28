package com.bing.admin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
@Repository
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    // 添加数据到redis
    public void addToRedis(String name, String value,long time) {
        redisTemplate.opsForValue().set(name,value, time, TimeUnit.SECONDS);
    }
    public void addToRedis(String name, String value) {
        redisTemplate.opsForValue().set(name,value, 3600, TimeUnit.SECONDS);
    }
    // 从redis获取数据
    public String getData(String key) {
        String v = (String) redisTemplate.boundValueOps(key).get();
        return  v;
    }

    public boolean delete(String key){
        return redisTemplate.delete(key);
    }
    //    删除多个key
    public void deleteKey (String ...keys){
        redisTemplate.delete(keys);
    }
    //    指定key的失效时间
    public void expire(String key,long time){
        redisTemplate.expire(key,time,TimeUnit.MINUTES);
    }

    //    根据key获取过期时间
    public long getExpire(String key){
        Long expire = redisTemplate.getExpire(key);
        return expire;
    }

    //    判断key是否存在
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
}
