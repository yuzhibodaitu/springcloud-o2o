package com.artisan.product.service;

import com.artisan.product.common.DecreaseStockInput;
import com.artisan.product.common.ProductOutput;
import com.artisan.product.domain.Product;

import java.util.List;

/**
 * 商品服务
 * @author tyler.yan
 */
public interface ProductService {

    /**
     * 查询上架商品
     * @return 商品
     */
    List<Product> list();

    /**
     * 根据Id查询商品信息
     * @param ids
     * @return 商品库存
     */
    List<ProductOutput> list(List<String> ids);

    /**
     * 扣减库存
     * @param decreaseStockInputList 商品扣减库存
     */
    void decreaseProduct(List<DecreaseStockInput> decreaseStockInputList);

}
