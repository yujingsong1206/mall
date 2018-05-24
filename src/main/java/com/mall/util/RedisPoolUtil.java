package com.mall.util;

import com.mall.common.RedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by sjyjs
 */
public class RedisPoolUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisPoolUtil.class);

    /**
     * 设置key的有效期，单位是秒
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key, int exTime){
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, exTime);
        }catch (Exception e){
            logger.error("expire key:{} error.",key,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    //exTime单位是秒
    public static String setEx(String key, String value, int exTime){
        Jedis jedis = null;
        String result = null;
        try{
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, exTime, value);
        }catch (Exception e){
            logger.error("setex key:{} value{} error.",key,value,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static String set(String key, String value){
        Jedis jedis = null;
        String result = null;
        try{
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        }catch (Exception e){
            logger.error("set key:{} value{} error.",key,value,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try{
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        }catch (Exception e){
            logger.error("get key:{} error.",key,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        }catch (Exception e){
            logger.error("del key:{} error.",key,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static void main(String[] args) {
        Jedis jedis = RedisPool.getJedis();
        RedisPoolUtil.set("testkey","testvalue");
        String value = RedisPoolUtil.get("testkey");
        RedisPoolUtil.setEx("exkey","exvalue",60*10);
        RedisPoolUtil.expire("testkey",60*20);
        RedisPoolUtil.del("testkey");
    }

}
