package com.joyintech.invest.interf.common.core.utility.s049;

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
public class S049Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS049() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("pageId", "TrdForwexInvestTrade002.01");
        dataMap.put("platDate",Long.valueOf("20200214"));

        // 交易表Map
        @SuppressWarnings("unchecked")
        Map<String, Object> trdProductDeal = new HashMap<>();
        // 交易表AddMap
        @SuppressWarnings("unchecked")
        Map<String, Object> trdProductDealAdd = new HashMap<>();

        // 交易编号
        trdProductDeal.put("tradeId", "9ceda0eaac3f49bc851b");
        // 交易类型
        trdProductDeal.put("finprodType2", "1111111");
        trdProductDeal.put("ps", "02");
        dataMap.put("isInnerTrade","plu12");
        // 组合代码
        dataMap.put("portfolioId", "1ceda0eaac3f49bc861b");
        // 交易对手代码
        trdProductDeal.put("counterId", "4ceda0eaac3f57bc851b");
        // 资产代码
        trdProductDeal.put("tradeId", "4ceda0eaac3f57bc851b");
        // 资产名称
//        body.put("FINPROD_NAME", "");
        // 交易日
        trdProductDeal.put("tradeDate", Long.valueOf("20200214"));
        trdProductDeal.put("mdate", Long.valueOf("20200214"));
        // 交割日
        trdProductDeal.put("settleDate", Long.valueOf("20200214"));
        // 清算模式
        trdProductDeal.put("deliveryType", "02");
        // 交易币种金额
        trdProductDeal.put("prinAmt", 120000);
        // 对应币种收付方向
        trdProductDeal.put("ps", "02");
        // 对应币种金额
        trdProductDeal.put("tradeAmt", 100000);
        // 对应币种
        trdProductDeal.put("ccy","CNY");
        // 交易币种
//        body.put("PRIN_CCY", "");
        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId", "0ceda0eaac3f49bc861b");
        // 撤销说明
        dataMap.put("revokeReason", "ttrsdfs");
        // 备注
        trdProductDeal.put("REMARK", "sdasdasda");
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

        // 定价日
        trdProductDealAdd.put("pricedDate", Long.valueOf("20200214"));
        // 定价日即期汇率
        trdProductDealAdd.put("pricedDateRate","0.05");


        // 划款信息
        // 收款账户
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> stlPayOrderslist = new ArrayList<>();
        Map<String, Object> settleInfoMap = new HashMap<>();
        settleInfoMap.put("recAcctNo", "123458056800");
        // 付款账户
        settleInfoMap.put("payAcctNo", "125678000000");
        // 划款金额
        settleInfoMap.put("settleAmt", 100000000);
        // 币种
        settleInfoMap.put("CCY", "CNY");

        stlPayOrderslist.add(settleInfoMap);

        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("trdProductDealAdd",trdProductDealAdd);
        dataMap.put("stlPayOrderslist",stlPayOrderslist);

        interfCoreController.send(dataMap);

    }
}
