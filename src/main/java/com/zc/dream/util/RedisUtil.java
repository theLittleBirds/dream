package com.zc.dream.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<Object,Object> template;

    public void set(String key, Object object, Long time) {
        // 让该方法能够支持多种数据类型存放
        // 如果存放时Set类型
        if (object instanceof Set) {
            setSet(key, object);
        }
        setString(key,object);

        // 设置有效期
        if (time != null) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }

    }

    public void setString(String key, Object object) {
        String value = toJson(object);
        // 存放string类型
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setMap(String key,Object object){
        Map value=(Map) object;
        stringRedisTemplate.opsForValue().multiGet((Collection<String>) value);
    }

    public void setSet(String key, Object object) {
        Set<String> valueSet = (Set<String>) object;
        for (String string : valueSet) {
            stringRedisTemplate.opsForSet().add(key, string);
        }
    }

    public String getString(String key) {
//        System.out.println("获取的数据格式是"+stringRedisTemplate.opsForValue().get(key) ) ;
        return stringRedisTemplate.opsForValue().get(key);
    }
    /**
     * Object转成JSON数据
     */
    private String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        System.out.println("存入的数据格式是"+JSON.toJSONString(object));
        return JSON.toJSONString(object);
    }

    public  <T> T fromJson(String json, Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     *分布式锁  setnx 先拿到key的去执行  拿不到的拒绝
     */
    public Object setNx(String key){

//        System.out.println("分布式锁的value是"+stringRedisTemplate.opsForValue().get(key));
//        //以下方法  相当于 redis的setnx命令 已经设置了返回0 没设置返回1  该key存在就返回false   不存在 返回true 并存入值
//        if(stringRedisTemplate.opsForValue().setIfAbsent(key,"1234") ){
//            stringRedisTemplate.expire(key, 60, TimeUnit.SECONDS);
//            System.out.println("获得分布式锁，存入的value是"+stringRedisTemplate.opsForValue().get(key));
//            //"本次进程获得锁，存入值";
//            return R.success(true,"获得分布式锁",null);
//        }
//
//        // "本次进程没有获得锁"; issues
//        return R.error(false,"没有获得分布式锁",null);
        return null;
    }
}
