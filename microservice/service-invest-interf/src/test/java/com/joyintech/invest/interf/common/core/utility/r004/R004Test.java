package com.joyintech.invest.interf.common.core.utility.r004;

import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资管理系统到账确认信息 -接受测试
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R004Test {
    @Autowired
    private InterfRecvComponent recvComponent;

    @Test
    public void testR004() {
        Map<String, Object> dataMap = new HashMap<>();
     // 接口ID
        dataMap.put("interfId", "R004");
        // 交易编号
        dataMap.put("TRADE_ID", "1111");
        
        // 到账日期
        dataMap.put("SETTLE_DATE", new Date());
        // 到账金额
        dataMap.put("TRADE_AMT", BigDecimal.valueOf(110));
        //备注
        dataMap.put("REMARK", "到账确认信息 测试");
 
       

        recvComponent.recv(dataMap);

    }
}
