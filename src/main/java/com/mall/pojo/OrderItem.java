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
public class OrderItem {
    private Integer id;

    private Integer userId;//用户id

    private Long orderNo;//订单号

    private Integer productId;//商品id

    private String productName;//商品名称

    private String productImage;//商品图片地址

    private BigDecimal currentUnitPrice;//单价

    private Integer quantity;//数量

    private BigDecimal totalPrice;//总价

    private Date createTime;

    private Date updateTime;

}