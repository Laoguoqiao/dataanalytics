package com.team11.dataanalytics.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("redisDao")
public class RedisDaoImpl {

    private static final Logger LOG= LoggerFactory.getLogger(RedisDaoImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;


    public void setObject(String key,Object object){
        LOG.info("set value");

        redisTemplate.opsForValue().set(key,object);
    }

    public Object getObject(String key){
        LOG.info(" getObject");
        return redisTemplate.opsForValue().get(key);
    }

    public void delObject(String key){
        LOG.info("delObject");
        redisTemplate.delete(key);
    }

    public void setList(String key, List<Object> list){

         redisTemplate.opsForList().rightPushAll(key,list);
    }

    private   Long getListSize(String key){
        return redisTemplate.opsForList().size(key);
    }

    public List<Object> getList(String key){

        return (List<Object>) redisTemplate.opsForList().index(key,getListSize(key));
    }

    public void setHash(String key, Map map){
        redisTemplate.opsForHash().putAll(key,map);
    }

    public Object getHash(String key,String hashKey){
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    public void delHashValue(String key,String... hashKeys){
        redisTemplate.opsForHash().delete(key,hashKeys);
    }

}
