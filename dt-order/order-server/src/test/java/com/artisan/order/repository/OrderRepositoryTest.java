package com.dt.order.repository;

import com.dt.order.ArtisanOrderApplicationTests;
import com.dt.order.domain.Order;
import com.dt.order.enums.OrderStatusEnum;
import com.dt.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class OrderRepositoryTest  extends ArtisanOrderApplicationTests {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSave(){

        Order order = new Order();
        order.setOrderId("1222");
        order.setBuyerName("dt");
        order.setBuyerPhone("123445664");
        order.setBuyerAddress("Artisan Tech");
        order.setBuyerOpenid("11112233");
        order.setOrderAmount(new BigDecimal(3.9));
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setPayStatus(PayStatusEnum.WAIT.getCode());

        Order result = orderRepository.save(order);
        Assert.assertNotNull(result);
    }
}