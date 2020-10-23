package com.joyintech.invest.interf.common.core.utility.r002;



import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资管理系统交易请结算状态 -接受测试
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R002Test {
    @Autowired
    private InterfRecvComponent recvComponent;

    @Test
    public void testR002() {
        Map<String, Object> dataMap = new HashMap<>();
        // id
        dataMap.put("interfId", "R002");
         // 交易编号
        dataMap.put("TRADE_ID", "11111"); 
        // 清算状态
        dataMap.put("SETTLE_STATUS", "01");
        //备注
        dataMap.put("REMARK", "交易清结算状态测试");
 
       

        recvComponent.recv(dataMap);

    }
}
