package com.mall.service;

import com.mall.common.ServerResponse;

/**
 * Created by song-pc on 2017/11/28.
 */
public interface ICartService {

    ServerResponse add(Integer userId, Integer productId, Integer count);

    ServerResponse update(Integer userId, Integer productId, Integer count);

    ServerResponse deleteProduct(Integer userId, String productIds);

    ServerResponse list(Integer userId);

    ServerResponse selectOrUnSelect(Integer userId, Integer productId, Integer checked);

    ServerResponse getCartProductCount(Integer userId);

}
