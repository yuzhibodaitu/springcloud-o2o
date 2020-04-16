package com.dt.product.service;

import com.dt.product.domain.ProductCategory;

import java.util.List;

/**
 * 商品类别查询
 * @author tyler.yan
 */
public interface ProductCategoryService {

    /**
     * 批量查询商品列表
     * @param typeList 类目标号
     * @return ProductCategory 商品类目
     */
    List<ProductCategory> listByType(List<Integer> typeList);
}
