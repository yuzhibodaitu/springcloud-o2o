package com.dt.product.exceptions;

import com.dt.product.enums.ResultEnum;

/**
 * @author tyler.yan
 */
public class ProductException extends RuntimeException {

    private Integer code ;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
