package com.dt.order.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;

@Component
@Slf4j
public class SnowFlakeUtilsTest {

    @Test
    public void nextId() {
        // 构造方法设置机器码：第9个机房的第20台机器
        SnowFlakeUtils  snowFlake = new SnowFlakeUtils(9, 20);
        //循环生成2^12个ID
        for(int i =0; i <(1<< 12); i++){
            System.out.println(snowFlake.nextId());
        }
    }

    @Test
    public void nextTenId() {
        // 构造方法设置机器码：第9个机房的第20台机器
        SnowFlakeUtils  snowFlake = new SnowFlakeUtils(9, 20);
        //循环生成2^12个ID
        for(int i =0; i <(1<< 12); i++){
            log.info("index:{},{}",i,snowFlake.nextId());
        }
    }
}
