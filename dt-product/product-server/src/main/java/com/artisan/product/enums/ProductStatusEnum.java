package com.dt.product.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {

    OnLine(0,"上架"),
    OffLine(1,"下架");

    private int code ;
    private String msg;

    ProductStatusEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
