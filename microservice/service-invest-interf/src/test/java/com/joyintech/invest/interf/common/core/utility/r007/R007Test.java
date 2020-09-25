 package com.joyintech.invest.interf.common.core.utility.r007;

import com.joyintech.invest.interf.InvestInterfApplication;
import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资管理系统债券估值信息 -接受测试
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R007Test {
    @Autowired
    private InterfRecvComponent recvComponent;

    @Test
    public void testR007() {
        Map<String, Object> dataMap = new HashMap<>();
        //接口id
        dataMap.put("FINPROD_ID", "R007");
        // 估值日
        dataMap.put("VALUE_DATE", new Date());
        // 估值来源
        dataMap.put("VALUE_SOURCE", "CBC");
        // 剩余期限
        dataMap.put("LAST_PERIOD", "01");
        //估值全价
        dataMap.put("PRICE", BigDecimal.valueOf(110));
      //估价净价
        dataMap.put("NETPRICE", BigDecimal.valueOf(10));
      //估价久期
        dataMap.put("M_DURATION", new Date());
      //估价凸性
        dataMap.put("M_CONVEXITY", "QQQQ");
      //基点价值
        dataMap.put("BPVALUE", BigDecimal.valueOf(101));
      //利差久期
        dataMap.put("SDURATION", BigDecimal.valueOf(1101));
      //利差凸性
        dataMap.put("SCNVXTY", "2.6");
      //利率久期
        dataMap.put("INTEREST_DURATION", BigDecimal.valueOf(111));
      //利率凸性
        dataMap.put("INTEREST_CNVXTY", "1.1");
 
      //估价收益率
        dataMap.put("BPYIELD", "0.6");
      //估价基点价值
        dataMap.put("MBPVALUE",BigDecimal.valueOf(1111));
      //风险价值
        dataMap.put("VAR", BigDecimal.valueOf(121));
      //条件风险价值
        dataMap.put("CVAR", BigDecimal.valueOf(1211));
      //麦考利久期
        dataMap.put("MDURATION", "0.8");
      //市价隐含评级（中债）
        dataMap.put("IMPLICIT_GRADE", "AA");
      //市价历史隐含评级（中债）
        dataMap.put("IMPLICIT_HGRADE", "A");

        recvComponent.recv(dataMap);

    }
}
