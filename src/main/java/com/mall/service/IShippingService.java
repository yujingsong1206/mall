package com.mall.service;

import com.mall.common.ServerResponse;
import com.mall.pojo.Shipping;

/**
 * Created by song-pc on 2017/11/29.
 */
public interface IShippingService {

    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse select(Integer userId, Integer shippingId);

    ServerResponse list(Integer userId, int pageNum, int pageSize);

}
