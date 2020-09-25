package com.joyintech.invest.interf.common.core.utility.s042;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * 投资管理系统发送交易数据-发送测试
 * @program: tams-base
 * @description:
 * @author: 平路远
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S042Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS042() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("pageId", "TrdOptionTrade002");
        dataMap.put("platDate",Long.valueOf("20200214"));

        // 交易表Map
        Map<String, Object> trdProductDeal = new HashMap<>();
        // 标的表Map
        Map<String, Object> finProduct = new HashMap<>();
        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();
        //汇总交易表
        Map<String, Object> trdFinProductDealTotal = new HashMap<>();
        //交易类金融产品附属表
        Map<String, Object> finTradeProductAdd =new HashMap<>();
        //行权方式
        Map<String, Object> exercisePlan = new HashMap<>();
        //结构类型
        Map<String, Object> finRateInfoStructure = new HashMap<>();

        // 交易编号
        trdProductDeal.put("tradeId", "9ceda0eaac3f49bc851b");
        // 成交编号/合同编号
        trdProductDeal.put("fTradeId", "5ceda0eaac3f49bc851b");
        trdProductDeal.put("finprodType2", "1111111");
        trdProductDeal.put("ps", "02");
        dataMap.put("isInnerTrade","plu12");

        // 组合代码
        dataMap.put("portfolioId", "1ceda0eaac3f49bc861b");
        // 交易对手代码
        trdProductDeal.put("counterId", "4ceda0eaac3f57bc851b");
        // 交易场所
        trdProductDeal.put("tradeMarket", "03");
        // 资产代码
        trdProductDeal.put("finprodId","5ceda0eaac3f48bc854b");
        // 市场代码
        finProduct.put("finprodMarketId", "6ceda0eaac3f48bc854b");

        // 交易日
        trdProductDeal.put("tradeDate", Long.valueOf("20200214"));
        // 交割日
        trdProductDeal.put("settleDate", Long.valueOf("20200214"));
        // 起息日
        trdProductDeal.put("vdate", Long.valueOf("20200214"));
        // 到期日
        trdProductDeal.put("mdate", Long.valueOf("20200214"));

        //名义本金
        trdFinProductDealTotal.put("PRIN_AMT", 100000);
        //收付方向
        trdFinProductDealTotal.put("PS", "02");
        //交易金额
        trdFinProductDealTotal.put("TRADE_AMT", 80000);
        //交易币种
        trdFinProductDealTotal.put("ccy","CNY");
        //期权费支付类型
        finTradeProductAdd.put("optFeePayType", "01");
        //期权费
        finTradeProductAdd.put("optFee", 1000);
        //期权费率
        finTradeProductAdd.put("optFeeRate",0.05);
        exercisePlan.put("exerciseType", "06");
        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId", "0ceda0eaac3f49bc861b");
        // 撤销说明
        dataMap.put("revokeReason", "ttrsdfs");

        finRateInfoStructure.put("structureType", "01");
        //高障碍价
        finRateInfoStructure.put("HIGH_HIND_PRICE", 100000);
        //低障碍价
        finRateInfoStructure.put("LOW_HIND_PRICE", 10000);
        //高行权价
        finRateInfoStructure.put("HIGH_EXERC_PRICE", 120000);
        // 低行权价
        finRateInfoStructure.put("LOW_EXERC_PRICE", 50000);
        //执行价
        finRateInfoStructure.put("EXE_PRICE", 70000);
        //敲出价
        finRateInfoStructure.put("KNOCK_PRICE", 8000);

        // 参与人信息

        // 参与人名称
        dataMap.put("traderName", "liumei");
        // 参与人联系方式
        dataMap.put("traderContactInfo", "18755467321");

        // 对手方交易员

        // 参与人名称
        trdProductDeal.put("counterTrader", "lilei");
        // 参与人联系方式
        trdProductDeal.put("counterContact", "18755467322");

        // 划款信息
        // 收款账户
        stlPayOrders.put("recAcctNo", "123458056800");
        // 付款账户
        stlPayOrders.put("payAcctNo", "125678000000");
        // 划款金额
        stlPayOrders.put("settleAmt", 100000000);
        // 币种
        stlPayOrders.put("CCY", "CNY");

        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("finProduct",finProduct);
        dataMap.put("stlPayOrders",stlPayOrders);
        dataMap.put("trdFinProductDealTotal",trdFinProductDealTotal);
        dataMap.put("finTradeProductAdd",finTradeProductAdd);
        dataMap.put("exercisePlan",exercisePlan);
        dataMap.put("finRateInfoStructure",finRateInfoStructure);

        interfCoreController.send(dataMap);

    }
}
