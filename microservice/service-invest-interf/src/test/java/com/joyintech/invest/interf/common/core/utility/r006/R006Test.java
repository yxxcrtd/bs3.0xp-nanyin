 package com.joyintech.invest.interf.common.core.utility.r006;

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
 * 投资管理系统资产行情信息 -接受测试
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R006Test {
    @Autowired
    private InterfRecvComponent recvComponent;

    @Test
    public void testR006() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> finProductValuation = new HashMap<>();
     // 报文时间
     		long date =20191101;
     		dataMap.put("platDate",date);
        // id
        dataMap.put("interfId", "R006");
        // 市场代码
        dataMap.put("portfolioId", "portfolioId");
        
         // 估值日 
        finProductValuation.put("valDate", date);
        //每日估值 
        finProductValuation.put("valuation", date);
        //七日年化收益率 
        finProductValuation.put("day7YearYield", "0.8");
        //年化收益率 
        finProductValuation.put("yearYield", "0.9");
        //累计净值 
        finProductValuation.put("totalValue",BigDecimal.valueOf(110));
        //复权单位净值 
        finProductValuation.put("restNetValue",BigDecimal.valueOf(110));
        dataMap.put("finProductValuation", finProductValuation);
       

        recvComponent.recv(dataMap);

    }
}
