package com.joyintech.invest.interf.common.core.utility.s047;

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
public class S047Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS047() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("pageId", "TrdSpotexInvestTrade002");
        dataMap.put("platDate",Long.valueOf("20200214"));

        // 交易表Map
        @SuppressWarnings("unchecked")
        Map<String, Object> trdProductDeal = new HashMap<>();
        // 交易表AddMap
        @SuppressWarnings("unchecked")
        Map<String, Object> trdProductDealAdd =  new HashMap<>();

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
        // 交易日
        trdProductDeal.put("tradeDate", Long.valueOf("20200214"));
        // 交割日
        trdProductDeal.put("settleDate", Long.valueOf("20200214"));
        // 清算速度
        trdProductDeal.put("settleSpeed", "01");
        // 即期汇率
        trdProductDeal.put("unitCprice", 100);
        // 交易币种金额
        trdProductDeal.put("prinAmt", 120000);
        // 对应币种收付方向
        trdProductDeal.put("ps", "02");
        // 对应币种金额
        trdProductDeal.put("tradeAmt", 100000);
        // 对应币种
        trdProductDeal.put("ccy","CNY");
        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId", "0ceda0eaac3f49bc861b");
        // 撤销说明
        dataMap.put("revokeReason", "ttrsdfs");
        // 备注
        trdProductDeal.put("REMARK", "sdasdasda");
        // 货币对
        trdProductDealAdd.put("CUR_PAIR", "01");
        // 外汇交易类型
        trdProductDealAdd.put("EXC_DEAL_TYPE", "FGN-CY");

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

        // 账户信息
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> stlPayOrderslist = new ArrayList<>();
        Map<String, Object> acctInfoMap = new HashMap<>();
        acctInfoMap.put("myAcctNo","123458056800");
        acctInfoMap.put("ccy", "CNY");
        acctInfoMap.put("counterAcctNo", "523458056800");
        stlPayOrderslist.add(acctInfoMap);

        // 费用明细信息
        @SuppressWarnings("unchecked")
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

        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("trdProductDealAdd",trdProductDealAdd);
        dataMap.put("stlPayOrderslist",stlPayOrderslist);
        dataMap.put("tradeFeeDetaillist",tradeFeeDetaillist);

        interfCoreController.send(dataMap);

    }
}
