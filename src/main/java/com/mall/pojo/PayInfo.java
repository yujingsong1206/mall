package com.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayInfo {
    private Integer id;

    private Integer userId;//用户id

    private Long orderNo;//订单号

    private Integer payPlatform;//支付平台:1-支付宝,2-微信

    private String platformNumber;//支付宝支付流水号

    private String platformStatus;//支付宝支付状态

    private Date createTime;

    private Date updateTime;

}