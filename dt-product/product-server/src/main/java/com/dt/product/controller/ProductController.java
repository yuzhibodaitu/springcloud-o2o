package com.dt.product.controller;

import com.dt.product.common.DecreaseStockInput;
import com.dt.product.common.ProductOutput;
import com.dt.product.domain.Product;
import com.dt.product.domain.ProductCategory;
import com.dt.product.service.ProductCategoryService;
import com.dt.product.service.ProductService;
import com.dt.product.vo.ProductInfoVO;
import com.dt.product.vo.ProductVO;
import com.dt.product.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 商品对外接口
 * @author tyler.yan
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/list")
    public Result list() {
        //1. 查询所有在架的商品
        List<Product> products = productService.list();

        //2. 获取类目type列表
        List<Integer> typeList = products.stream()
                .map(Product::getCategoryType)
                .collect(Collectors.toList());

        //3. 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.listByType(typeList);

        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            ProductVO productVO = new ProductVO();
            // 设置属性
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

            // ProductInfoVO 集合
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (Product product : products) {
                // 挂到对应的的categoryType下
                if (product.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    // 将属性copy到productInfoVO，避免逐个属性set，更简洁
                    BeanUtils.copyProperties(product, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return Result.success(productVOList);
    }

    /**
     * 根据productIdList 查询商品列表
     * 提供给Order微服务用
     *
     * @param ids
     * @return
     */
    @PostMapping("/productListForOrder")
    public List<ProductOutput> getProductForOrder(@RequestBody List<String> ids) {
        return productService.list(ids);
    }


    /**
     * 扣减库存
     * 提供给Order微服务用
     *
     * @param decreaseStockInputList
     * @return
     */
    @PostMapping("/decreseProduct")
    public void  decreseProduct(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productService.decreaseProduct(decreaseStockInputList);
    }



}
