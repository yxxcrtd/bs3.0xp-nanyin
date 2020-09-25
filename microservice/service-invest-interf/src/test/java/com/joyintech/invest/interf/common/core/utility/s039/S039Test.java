package com.joyintech.invest.interf.common.core.utility.s039;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 拆借存续期
 * @program: tams-base
 * @description:
 * @author: daiguofu
 * @create: 2020-02-14 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S039Test {
//    @Autowired
//    private InterfRecvComponent recvComponent;
    @Autowired
    private InterfCoreController interfCoreController;
    @Test
    public void testS039() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> trdProductDeal  = new HashMap<>();

        // 交易编号v
        trdProductDeal.put("tradeId","JYSTOCK20190800254");
        // 成交编号/合同编号
        trdProductDeal.put("fTradeId","CR20130802024451");
        dataMap.put("platDate",Long.valueOf("20200214"));
        // 交易类型
        trdProductDeal.put("finprodType2", "F10");
        trdProductDeal.put("ps","T01");
        // 交易附加表Map
        Map<String, Object> trdProductDealAdd = new HashMap<>();

        // 组合代码
        trdProductDealAdd.put("portfolioId","jixuzuhe45466");
        //交易场所
        trdProductDeal.put("tradeMarket", "03");
        // 资产代码
        trdProductDeal.put("finprodId","F10_CS002.SG");
        // 标的表Map
        Map<String, Object> finProduct = new HashMap<>();
        // 市场代码
        finProduct.put("finprodMarketId","CS002.SH");
        // 通道代码
        trdProductDeal.put("chlId","F28_TDWY003");

        // 交易日
        trdProductDeal.put("tradeDate",Long.valueOf("20200217"));
        // 交割日
        trdProductDeal.put("settleDate",Long.valueOf("20200217"));
        // 交付日
        trdProductDeal.put("payDate",Long.valueOf("20200217"));
        //计息区 开始日
        trdProductDeal.put("vdate",Long.valueOf("20200217"));
        // 计息区结束日
        trdProductDeal.put("mdate",Long.valueOf("20200217"));
        // 本金金额
        trdProductDeal.put("prinAmt",BigDecimal.valueOf(40000));
        // 应计利息总额
        trdProductDeal.put("intAmt",BigDecimal.valueOf(15632));
        // 结算金额
        trdProductDeal.put("tradeAmt",BigDecimal.valueOf(35610));

        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();
        // 收付方向
        stlPayOrders.put("ps","T38");
        // 结算币种
        stlPayOrders.put("ccy","CNY");

        // 交易状态
        dataMap.put("tradeStatus","01");
        // 原交易编号
        trdProductDeal.put("pTradeId","6e5d7c0dcc8d452l79e5e");
        // 撤销说明
        dataMap.put("revokeReason","拉布");
        // 证券账户代码
        dataMap.put("secManageAcctId","2455455");

        // 备注
        trdProductDeal.put("remark","测试");
        // 本方交易员
        dataMap.put("traderName","xiaodai123");
        // 参与人联系方式
        dataMap.put("traderContactInfo","12349560004");

        // 划款信息
        // 收款账户
        stlPayOrders.put("counterAcctNo","4190522");
        // 付款账户
        stlPayOrders.put("myAcctNo","80865");
        // 划款金额
        // 币种

        // 交易表Map
        dataMap.put("trdProductDeal",trdProductDeal);

        // 交易附加表Map
        dataMap.put("trdProductDealAdd",trdProductDealAdd);
        // 指令表Map
        dataMap.put("stlPayOrders",stlPayOrders);
        // 标的表Map
        dataMap.put("finProduct",finProduct);
        // pageId
        dataMap.put("pageId","TrdLendDuration002");
        interfCoreController.send(dataMap);
    }
}
