package com.mall.pojo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private Integer id;

    private Integer userId;//用户id

    private Integer productId;//商品id

    private Integer quantity;//数量

    private Integer checked;//是否选择,1=已勾选,0=未勾选

    private Date createTime;

    private Date updateTime;

}