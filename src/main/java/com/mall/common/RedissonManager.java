package com.mall.common;

import com.mall.util.PropertiesUtil;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by sjyjs on 2018/12/21.
 */
@Component
public class RedissonManager {
    private static final Logger logger = LoggerFactory.getLogger(RedissonManager.class);

    private Config config = new Config();
    private Redisson redisson = null;
    private static Integer redis1Port = Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));
    private static String redis1Id = PropertiesUtil.getProperty("redis1.id");
//    private static Integer redis2Port = Integer.parseInt(PropertiesUtil.getProperty("redis2.port"));
//    private static String redis2Id = PropertiesUtil.getProperty("redis2.id");

    @PostConstruct
    private void init(){
        try {
            config.useSingleServer().setAddress(new StringBuilder().append(redis1Port).append(":").append(redis1Port).toString());
            redisson = (Redisson) Redisson.create(config);
            logger.info("初始化Redisson结束");
        } catch (Exception e) {
            logger.error("Redisson init error.",e);
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }
}
