package com.dt.order.message;

import com.dt.order.utils.JsonUtil;
import com.dt.product.common.ProductOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductOutputReceive {

    // 自动创建productOutput队列
    @RabbitListener(queuesToDeclare = @Queue("productOutput"))
    public void process(String message) {
        // message --> ProductOutput
        ProductOutput productOutput = JsonUtil.JsonToBean(message, ProductOutput.class);
        log.info("接收到的消息为:{}",productOutput);
    }

}
