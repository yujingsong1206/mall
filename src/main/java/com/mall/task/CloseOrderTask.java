package com.mall.task;

import com.mall.service.IOrderService;
import com.mall.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by sjyjs on 2018/12/20.
 */
@Component
public class CloseOrderTask {
    private static final Logger logger = LoggerFactory.getLogger(CloseOrderTask.class);

    @Autowired
    private IOrderService iOrderService;

    @Scheduled(cron = "0 */1 * * * ?")//每1分钟（每个1分钟的整数倍）
    public void closeOrderTaskV1(){
        logger.info("关闭订单定时任务启动");
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour", "2"));
        iOrderService.closeOrder(hour);
        logger.info("关闭订单定时任务结束");
    }

}
