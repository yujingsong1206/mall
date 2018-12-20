package com.mall.util;

import com.mall.common.RedisShardedPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;

/**
 * Created by sjyjs
 */
public class RedisShardedPoolUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisShardedPoolUtil.class);

    /**
     * 设置key的有效期，单位是秒
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key, int exTime){
        ShardedJedis jedis = null;
        Long result = null;
        try{
            jedis = RedisShardedPool.getJedis();
            result = jedis.expire(key, exTime);
        }catch (Exception e){
            logger.error("expire key:{} error.",key,e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    //exTime单位是秒
    public static String setEx(String key, String value, int exTime){
        ShardedJedis jedis = null;
        String result = null;
        try{
            jedis = RedisShardedPool.getJedis();
            result = jedis.setex(key, exTime, value);
        }catch (Exception e){
            logger.error("setex key:{} value{} error.",key,value,e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static String set(String key, String value){
        ShardedJedis jedis = null;
        String result = null;
        try{
            jedis = RedisShardedPool.getJedis();
            result = jedis.set(key, value);
        }catch (Exception e){
            logger.error("set key:{} value{} error.",key,value,e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static String get(String key){
        ShardedJedis jedis = null;
        String result = null;
        try{
            jedis = RedisShardedPool.getJedis();
            result = jedis.get(key);
        }catch (Exception e){
            logger.error("get key:{} error.",key,e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static Long del(String key){
        ShardedJedis jedis = null;
        Long result = null;
        try{
            jedis = RedisShardedPool.getJedis();
            result = jedis.del(key);
        }catch (Exception e){
            logger.error("del key:{} error.",key,e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

}