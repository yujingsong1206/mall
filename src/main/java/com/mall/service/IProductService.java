package com.mall.service;

import com.mall.common.ServerResponse;
import com.mall.pojo.Product;

/**
 * Created by song-pc on 2017/11/27.
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse setSaleStatus(Integer productId, Integer status);

    ServerResponse manageProductDetail(Integer productId);

    ServerResponse getProductList(int pageNum, int pageSize);

    ServerResponse searcheProduct(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse getProductDetail(Integer productId);

    ServerResponse getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);

}
