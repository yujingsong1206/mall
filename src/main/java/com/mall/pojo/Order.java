package com.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;

    private Long orderNo;//订单号

    private Integer userId;//用户id

    private Integer shippingId;//收货地址id

    private BigDecimal payment;//实际付款金额

    private Integer paymentType;//支付类型,1-在线支付

    private Integer postage;//运费

    private Integer status;//订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭

    private Date paymentTime;//支付时间

    private Date sendTime;//发货时间

    private Date endTime;//交易完成时间

    private Date closeTime;//交易关闭时间

    private Date createTime;

    private Date updateTime;

}