package com.joyintech.invest.interf.common.core.utility.r003;

import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * SPPI测试结果 -接受测试
 * @program: tams-base
 * @description:
 * @author: 代国富
 * @create: 2020-02-25 16:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R003Test {
    @Autowired
    private InterfRecvComponent recvComponent;

    @Test
    public void testR003() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("interfId", "R003");
        // 资产代码
        dataMap.put("FINPROD_ID", "1111111");
        // 市场代码
        dataMap.put("FINPROD_MARKET_ID", "4646464665");
        //测试日期
        dataMap.put("TEST_DATE",new Date());

        // 测试结果
        dataMap.put("TEST_RESULT","01");

        // 备注
        dataMap.put("REMARK", "good");

        recvComponent.recv(dataMap);

    }
}
