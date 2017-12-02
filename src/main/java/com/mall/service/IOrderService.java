package com.mall.service;

import com.mall.common.ServerResponse;

import java.util.Map;

/**
 * Created by song-pc on 2017/12/2.
 */
public interface IOrderService {

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

}
