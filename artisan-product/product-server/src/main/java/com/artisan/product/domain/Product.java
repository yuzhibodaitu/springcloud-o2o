package com.artisan.product.domain;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 *  @Table指定这个实体对应数据库的表名 ,符合驼峰命名的 可以省略不写
 *  @Entity表示这个类是一个实体类
 *  @author tyler.yan
 */
@Data
@Table(name = "product")
@Entity
public class Product {

    /**
     * 商品ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;

    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品库存
     */
    private Integer productStock;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 商品描述
     */
    private String productDescription;
    /**
     * 商品图标
     */
    private String productIcon;
    /**
     * 商品状态
     */
    private Integer productStatus;
    /**
     * 商品类型
     */
    private Integer categoryType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


}
