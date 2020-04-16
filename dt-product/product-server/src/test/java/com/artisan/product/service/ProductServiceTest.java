package com.dt.product.service;

import com.dt.product.ArtisanProductApplicationTests;
import com.dt.product.common.DecreaseStockInput;
import com.dt.product.domain.Product;
import com.dt.product.enums.ProductStatusEnum;
import com.dt.product.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class ProductServiceTest extends ArtisanProductApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void getAllUpProduct() {
        List<Product> list =  productRepository.findByProductStatus(ProductStatusEnum.OnLine.getCode());
        Assert.assertEquals(3,list.size());
    }

    @Test
    public void getProductList() {
        List<Product> list =  productRepository.findByProductIdIn(Arrays.asList("1","2"));
        Assert.assertEquals(2,list.size());
    }

    @Test
    public void decreaseProductTest() {

        DecreaseStockInput decreaseStockInput = new DecreaseStockInput();
        decreaseStockInput.setProductId("3");
        decreaseStockInput.setProductQuantity(2);

        productService.decreaseProduct(Arrays.asList(decreaseStockInput));
    }
}