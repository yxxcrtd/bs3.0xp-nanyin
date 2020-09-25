package com.joyintech.invest.interf.common.core.utility.s010;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
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
public class S010Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS010() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("pageId", "TrdFundInvestTrade003.02");
        dataMap.put("platDate",Long.valueOf("20200214"));

        // 交易表Map
        Map<String, Object> trdProductDeal = new HashMap<>();
        // 标的表Map
        Map<String, Object> finProduct = new HashMap<>();
        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();
        // 交易编号
        trdProductDeal.put("tradeId", "9ceda0eaac3f49bc851b");
        // 成交编号/合同编号
        trdProductDeal.put("fTradeId", "4ceda0eaac3f49bc851b");
        // 交易类型
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
        trdProductDeal.put("finprodId", "5ceda0eaac3f48bc854b");
        // 市场代码
        finProduct.put("finprodMarketId", "9ceda0eaac3f49bc851b");
        // 资产名称
        finProduct.put("finprodName", "triu");
        // 交易日
        trdProductDeal.put("tradeDate", Long.valueOf("20200214"));
        trdProductDeal.put("settleDate", Long.valueOf("20200214"));
        // 交付日
        trdProductDeal.put("payDate", Long.valueOf("20200214"));
        // 待结转份额
        trdProductDeal.put("intAmt", 100000);
        // 交易份额
        trdProductDeal.put("shareAmt", 50000);
        // 收付方向
        stlPayOrders.put("ps", "02");
        // 结算金额
        trdProductDeal.put("tradeAmt", 10000);
        // 结算币种
        stlPayOrders.put("ccy", "CNY");

        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId", "7ceda0eaac3f49bc851b");
        // 撤销说明
        dataMap.put("revokeReason", "tttttt");
        // 证券账户代码
        dataMap.put("secManageAcctId", "7cedy0eaac3f49bc851b");
        // 通道代码
        trdProductDeal.put("chlId", "8ceda0eaac3f49bc851b");
        // 备注
        trdProductDeal.put("remark", "ply");
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
        // 费用明细信息
        List<Map<String, Object>> tradeFeeDetaillist = new ArrayList<>();
        Map<String, Object> feeTypeMap = new HashMap<>();
        dataMap.put("feePs","02");
        // 费用类型
        feeTypeMap.put("feeType", "B02");
        // 费用币种
        feeTypeMap.put("ccy","CNY");
        // 费用金额
        feeTypeMap.put("feeAmt", 20000);
        // 是否随交易支付
        feeTypeMap.put("isPayWithTrade", "1");
        tradeFeeDetaillist.add(feeTypeMap);
        // 划款信息
        stlPayOrders.put("recAcctNo", "123458056800");
        // 付款账户
        stlPayOrders.put("payAcctNo", "125678000000");
        // 划款金额
        stlPayOrders.put("settleAmt", 100000000);


        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("finProduct",finProduct);
        dataMap.put("stlPayOrders",stlPayOrders);
        dataMap.put("tradeFeeDetaillist",tradeFeeDetaillist);

        interfCoreController.send(dataMap);

    }
}
