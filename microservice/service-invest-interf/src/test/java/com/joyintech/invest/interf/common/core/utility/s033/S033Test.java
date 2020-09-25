package com.joyintech.invest.interf.common.core.utility.s033;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * 投资管理系统发送交易数据-接受测试
 * @program: tams-base
 * @description:
 * @author: 平路远
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S033Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS033() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("pageId", "TrdStock002");
        dataMap.put("platDate",Long.valueOf("20200214"));


        // 交易表Map
        Map<String, Object> trdProductDeal = new HashMap<>();
        // 交易附加表Map
        Map<String, Object> trdProductDealAdd =  new HashMap<>();
        // 标的表Map
        Map<String, Object> finProduct = new HashMap<>();
        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();

        // 交易编号
        trdProductDeal.put("tradeId", "9ceda0eaac3f49bc851b");
        // 交易类型
        trdProductDeal.put("finprodType2", "1111111");
        trdProductDeal.put("ps", "02");
        // 组合代码
        trdProductDealAdd.put("portfolioId", "1ceda0eaac3f49bc861b");
        // 权益登记日
        trdProductDealAdd.put("regDate", Long.valueOf("20200214"));

        // 交易对手代码
        trdProductDeal.put("counterId", "4ceda0eaac3f57bc851b");
        // 交易场所
        trdProductDeal.put("tradeMarket", "03");
        // 资产代码
        trdProductDeal.put("finprodId","5ceda0eaac3f48bc854b");
        // 市场代码
        finProduct.put("finprodMarketId", "6ceda0eaac3f48bc854b");


        // 红股上市日
        trdProductDeal.put("tradeDate", Long.valueOf("20200214"));

        // 交易日
        trdProductDeal.put("tradeDate", Long.valueOf("20200214"));
        trdProductDeal.put("settleDate", Long.valueOf("20200214"));
        // 百元应计利息
        trdProductDeal.put("unitInt", 1);
        // 百元净价
        trdProductDeal.put("unitCprice", 100);
        // 券面总额
        trdProductDeal.put("shareAmt", 100000);
        // 结算金额
        trdProductDeal.put("tradeAmt", 120000);
        // 收付方向
        stlPayOrders.put("ps", "02");
        // 结算币种
        stlPayOrders.put("ccy", "CNY");


        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId", "0ceda0eaac3f49bc861b");
        // 撤销说明
        dataMap.put("revokeReason", "ttrsdfs");
        // 证券账户代码
        dataMap.put("secManageAcctId", "3ceda0eaac3f49bc861b");

        // 通道代码
        trdProductDeal.put("chlId", "F28_TYTD01");

        // 备注
        trdProductDeal.put("remark", "ply");
        // 保证品信息
        // 保证金金额
        // 保证金收付方向
        // 参与人信息
        dataMap.put("traderName", "liumei");

        // 参与人联系方式
        dataMap.put("traderContactInfo", "18755467321");
        // 对手方交易员
        trdProductDeal.put("counterTrader", "lilei");

        // 划款信息
        stlPayOrders.put("counterAcctNo", "123123213123");
        stlPayOrders.put("myAcctNo", "123456789012:测试");
        stlPayOrders.put("settleAmt", 2000);

        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("trdProductDealAdd",trdProductDealAdd);
        dataMap.put("finProduct",finProduct);
        dataMap.put("stlPayOrders",stlPayOrders);

        interfCoreController.send(dataMap);

    }
}
