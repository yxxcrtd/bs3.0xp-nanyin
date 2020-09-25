package com.joyintech.invest.interf.common.core.utility.s038;


import com.joyintech.invest.interf.common.core.controller.InterfCoreController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 拆借交易
 * @program: tams-base
 * @description:
 * @author: daiguofu
 * @create: 2020-02-14 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S038Test {
//    @Autowired
//    private InterfRecvComponent recvComponent;
    @Autowired
    private InterfCoreController interfCoreController;
    @Test
    public void testS038() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> trdProductDeal  = new HashMap<>();
        Map<String, Object> paTrdProductDeal = new HashMap<>();
        Map<String, Object> finTradeProduct = new HashMap<>();
        // 交易编号v
        trdProductDeal.put("tradeId","JYSTOCK201908020225");
        dataMap.put("platDate",Long.valueOf("20200214"));
        // 成交编号/合同编号
        trdProductDeal.put("fTradeId","CR20130802025361");
        // 交易类型
        trdProductDeal.put("finprodType2","F10");
        trdProductDeal.put("ps","T01");
        dataMap.put("isInnerTrade","Y");

        // 组合代码
        dataMap.put("portfolioId","jixuzuhe0038");
        // 交易对手代码
        trdProductDeal.put("counterId","JYDS000000000384");

        // 资产代码
        trdProductDeal.put("finprodId","F10_CS001.SG");

        // 交易日
        trdProductDeal.put("tradeDate",Long.valueOf("20200217"));
        // 交割日
        trdProductDeal.put("settleDate",Long.valueOf("20200217"));
        // 起息日
        trdProductDeal.put("vdate",Long.valueOf("20200217"));
        // 到期日
        trdProductDeal.put("mdate",Long.valueOf("20200217"));

        //拆借类型
        paTrdProductDeal.put("paBussType","L01");
        //实际占款日
        paTrdProductDeal.put("term","3");
        // 计息基础
        paTrdProductDeal.put("basis","A/360");
        //  拆借利率
        paTrdProductDeal.put("intRate","0.03000000000000");
        // 交易本金
        paTrdProductDeal.put("mPrinAmt",BigDecimal.valueOf(30000));

        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();
        // 收付方向
        stlPayOrders.put("ps","T38");
        // 结算金额
        trdProductDeal.put("settleAmt",BigDecimal.valueOf(5000));
        // 结算币种
        stlPayOrders.put("ccy","CNY");

        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId","6e5d7c0dcc8d42d79e5e");
        // 撤销说明
        dataMap.put("revokeReason","拉布");

        // 通道代码
        trdProductDeal.put("chlId","F28_TDWY003");
        // 备注
        trdProductDeal.put("remark","测试");
        // 转换信息
        dataMap.put("actPortfolioId","2334455566");

        // 本方交易员
        dataMap.put("traderName","xiaodai123");
        // 参与人联系方式
        dataMap.put("traderContactInfo","12349560004");
        // 对手方交易员
        // 参与人名称
        trdProductDeal.put("counterTrader","xioalizi");
        // 参与人联系方式
        trdProductDeal.put("counterContact","18856981834");

        // 划款信息
        // 收款账户
        stlPayOrders.put("recAcctNo","4190522");
        // 付款账户
        stlPayOrders.put("payAcctNo","80865");

        // 交易表Map
        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("paTrdProductDeal",paTrdProductDeal);

        // 指令表Map
        dataMap.put("stlPayOrders",stlPayOrders);
        // pageId
        dataMap.put("pageId","TrdLendInvestTrade002");
        interfCoreController.send(dataMap);
    }
}
