package com.mark.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public RedisUtil(RedisTemplate<String,Object> redisTemplate){this.redisTemplate=redisTemplate;}

    public boolean expire(String key,long time){
        try{
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key,Object value,long time){
        try{
            redisTemplate.opsForValue().set(key,value);
            expire(key,time);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void del(String key){
        if(key!=null){
            redisTemplate.delete(key);
        }
    }
}
