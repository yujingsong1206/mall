package com.mall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by song on 2017/10/4.
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String TOKEN_PREFIX = "token_";

    public interface RedisCacheExtime{
        int REDIS_SESSION_EXTIME = 60 * 30;//30分钟
    }

    public interface Role {
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1; //管理员
    }

    public interface ProductListOrderBy {
        //set时间复杂度O(1)，list时间复杂度O(n)
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_asc", "price_desc");
    }

    public interface Cart {
        int CHECKED = 1;//购物车选中状态
        int UN_CHECKED = 0;//购物车未选择状态
        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public enum ProductStatusEnum {
        ON_SALE(1, "在线");

        private int code;
        private String value;
        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum OrderStatusEnum {
        CANCELED(0, "已取消"),
        NO_PAU(10, "未支付"),
        PAID(20, "已付款"),
        SHIPPED(40, "已发货"),
        ORDER_SUCCESS(50, "订单完成"),
        ORDER_CLOSE(60, "订单关闭");

        private int code;
        private String value;
        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        public static OrderStatusEnum codeOf(int code) {
            for(OrderStatusEnum orderStatusEnum : values()) {
                if(orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    public interface alipayCallback {
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum PayFlatformEnum {
        ALIPAY(1, "支付宝"),
        WXPAY(2, "微信");

        private int code;
        private String value;
        PayFlatformEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum PaymentTypeEnum {
        ONLINE_PAY(1, "在线支付");

        private int code;
        private String value;
        PaymentTypeEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        public static PaymentTypeEnum codeOf(int code) {
            for(PaymentTypeEnum paymentTypeEnum : values()) {
                if(paymentTypeEnum.getCode() == code) {
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

}
