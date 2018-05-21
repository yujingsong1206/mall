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
public class User {
    private Integer id;

    private String username;//用户名

    private String password;//用户密码，MD5加密

    private String email;//

    private String phone;//

    private String question;//找回密码问题

    private String answer;//找回密码答案

    private Integer role;//角色0-管理员,1-普通用户

    private Date createTime;

    private Date updateTime;

}