package com.mall.pojo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id;

    private Integer parentId;//父类别id当id=0时说明是根节点,一级类别

    private String name;//类别名称

    private Boolean status;//类别状态1-正常,2-已废弃

    private Integer sortOrder;//排序编号,同类展示顺序,数值相等则自然排序

    private Date createTime;

    private Date updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id != null ? id.equals(category.id) : category.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}