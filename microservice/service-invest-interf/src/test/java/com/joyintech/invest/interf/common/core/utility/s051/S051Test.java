package com.joyintech.invest.interf.common.core.utility.s051;


import com.joyintech.invest.interf.common.core.controller.InterfCoreController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 外汇掉期存续期
 * @program: tams-base
 * @description:
 * @author: daiguofu
 * @create: 2020-02-19 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S051Test {
//    @Autowired
//    private InterfRecvComponent recvComponent;
    @Autowired
    private InterfCoreController interfCoreController;
    @Test
    public void testS051() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> trdProductDeal  = new HashMap<>();

        // 交易编号v
        trdProductDeal.put("tradeId","RTSTOCK20190844245");

        // 交易类型
        trdProductDeal.put("finprodType2", "F10");
        trdProductDeal.put("ps","T01");
        dataMap.put("isInnerTrade","N");
        //
        dataMap.put("portfolioId","jixuzuhe45226");
        dataMap.put("platDate",Long.valueOf("20200214"));
        // 交易对手代码
        trdProductDeal.put("counterId","JYDS00000001545");
        // 资产代码
        // 交易日
        trdProductDeal.put("tradeDate",Long.valueOf("20200219"));
        // 交割日
        trdProductDeal.put("settleDate",Long.valueOf("20200219"));
        // 清算模式
        trdProductDeal.put("deliveryType", "CENT-SETT");
        // 交易币种金额
        trdProductDeal.put("prinAmt",BigDecimal.valueOf(13000));
        // 对应币种收付方向
        trdProductDeal.put("ps","T01");
        // 对应币种金额
        trdProductDeal.put("tradeAmt",BigDecimal.valueOf(20000));
        // 对应币种
        trdProductDeal.put("ccy","CNY");
        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId","6e54lc0dcc8d452l795455p");
        // 撤销说明
        dataMap.put("revokeReason","太空船");
        // 备注
        trdProductDeal.put("remark","haha");

        // 参与人名称
        dataMap.put("traderName","xiaodai45");
        // 参与人联系方式
        dataMap.put("traderContactInfo","1395466789");
        // 对手方交易员
        // 参与人名称
        trdProductDeal.put("counterTrader","xiaodai56");
        // 参与人联系方式
        trdProductDeal.put("counterContact","12564785262");

        // 划款信息
        List<Map<String, Object>> stlPayOrderslist = new ArrayList<>();
        Map<String, Object> item2 = new HashMap<>();
        // 收款账户
        item2.put("recAcctNo","442222");
        // 付款账户
        item2.put("payAcctNo","4411111");
        // 划款金额
        item2.put("settleAmt",BigDecimal.valueOf(5000));
        // 币种
        item2.put("ccy","CNY");
        Map<String, Object> item3 = new HashMap<>();
        // 收款账户
        item3.put("recAcctNo","22233333");
        // 付款账户
        item3.put("payAcctNo","222244444");
        // 划款金额
        item3.put("settleAmt",BigDecimal.valueOf(4000));
        // 币种
        item3.put("ccy","CNY");
        stlPayOrderslist.add(item2);
        stlPayOrderslist.add(item3);
        // 交易表Map
        dataMap.put("trdProductDeal",trdProductDeal);
        // 划款信息
        dataMap.put("stlPayOrderslist",stlPayOrderslist);
        // pageId
        dataMap.put("pageId","TrdFxswapInvestTrade002.02");
        interfCoreController.send(dataMap);
    }
}
