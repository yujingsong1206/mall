package com.mall.task;

import com.mall.common.Const;
import com.mall.common.RedissonManager;
import com.mall.service.IOrderService;
import com.mall.util.PropertiesUtil;
import com.mall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * Created by sjyjs on 2018/12/20.
 */
@Component
public class CloseOrderTask {
    private static final Logger logger = LoggerFactory.getLogger(CloseOrderTask.class);

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private RedissonManager redissonManager;

    @PreDestroy
    public void delLock(){
        RedisShardedPoolUtil.del(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
    }

//    @Scheduled(cron = "0 */1 * * * ?")//每1分钟（每个1分钟的整数倍）
//    public void closeOrderTaskV1(){
//        logger.info("关闭订单定时任务启动");
//        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour", "2"));
//        iOrderService.closeOrder(hour);
//        logger.info("关闭订单定时任务结束");
//    }

//    @Scheduled(cron = "0 */1 * * * ?")//每1分钟（每个1分钟的整数倍）
//    public void closeOrderTaskV2(){
//        logger.info("关闭订单定时任务启动");
//        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeout","5000"));
//        Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis()+lockTimeout));
//        if(setnxResult != null && setnxResult.intValue() == 1){
//            //如果返回值是1，说明设置成功，获取锁
//            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
//        } else {
//            logger.info("没有获得分布式锁：{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
//        }
//        logger.info("关闭订单定时任务结束");
//    }

    @Scheduled(cron = "0 */1 * * * ?")//每1分钟（每个1分钟的整数倍）
    public void closeOrderTaskV3(){
        logger.info("关闭订单定时任务启动");
        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeout","5000"));
        Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis()+lockTimeout));
        if(setnxResult != null && setnxResult.intValue() == 1){
            //如果返回值是1，说明设置成功，获取锁
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        } else {
            //未获取到锁，再判断时间戳，看是否可以重置并获取到锁
            String lockValueStr = RedisShardedPoolUtil.get(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            if(lockValueStr != null && System.currentTimeMillis() > Long.parseLong(lockValueStr)){
                //这里set了一个新的value值，获取旧的值
                String getSetResult = RedisShardedPoolUtil.getSet(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis()+lockTimeout));
                if(getSetResult == null || (getSetResult != null && StringUtils.equals(lockValueStr,getSetResult))){
                    //真正获取到锁
                    closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                } else {
                    logger.info("没有获得分布式锁：{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                }
            } else {
                logger.info("没有获得分布式锁：{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            }
        }
        logger.info("关闭订单定时任务结束");
    }

//    public void closeOrderTaskV4(){
//        RLock lock = redissonManager.getRedisson().getLock(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
//        boolean getLock = false;
//        try {
//            getLock = lock.tryLock(0,5, TimeUnit.SECONDS);
//            if(getLock){
//                logger.info("Redisson获取到分布式锁:{},ThreadName:{}",Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK,Thread.currentThread().getName());
//                int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour", "2"));
//                iOrderService.closeOrder(hour);
//            } else {
//                logger.info("Redisson没获取到分布式锁:{},ThreadName:{}",Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK,Thread.currentThread().getName());
//            }
//        } catch (InterruptedException e) {
//            logger.error("Redisson分布式锁获取异常", e);
//        } finally {
//            if(!getLock){
//                return;
//            }
//            lock.unlock();
//            logger.info("Redisson分布式锁释放锁");
//        }
//    }

    private void closeOrder(String lockName){
        RedisShardedPoolUtil.expire(lockName, 5);//有效期5s，防止死锁
        logger.info("获取{},ThreadName:{}", lockName, Thread.currentThread().getName());
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour", "2"));
        iOrderService.closeOrder(hour);
        RedisShardedPoolUtil.del(lockName);
        logger.info("释放{},ThreadName:{}", lockName, Thread.currentThread().getName());
    }

}
