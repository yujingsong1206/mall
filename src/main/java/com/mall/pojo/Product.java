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
public class Product {
    private Integer id;

    private Integer categoryId;//分类id

    private String name;//商品名称

    private String subtitle;//商品副标题

    private String mainImage;//产品主图

    private String subImages;//商品多图片地址，用逗号（,）隔开

    private String detail;//商品详情

    private BigDecimal price;//价格

    private Integer stock;//库存

    private Integer status;//商品状态.1-在售 2-下架 3-删除

    private Date createTime;

    private Date updateTime;

}