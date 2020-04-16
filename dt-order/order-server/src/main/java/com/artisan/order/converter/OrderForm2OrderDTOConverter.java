package com.dt.order.converter;

import com.dt.order.domain.OrderDetail;
import com.dt.order.dto.OrderDTO;
import com.dt.order.enums.ResultEnum;
import com.dt.order.exception.OrderException;
import com.dt.order.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            // fromJson 从Json相关对象到Java实体的方法 ,转换成列表类型
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch(Exception e){
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
