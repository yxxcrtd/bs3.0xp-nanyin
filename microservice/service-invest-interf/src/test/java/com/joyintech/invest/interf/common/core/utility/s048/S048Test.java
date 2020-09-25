package com.joyintech.invest.interf.common.core.utility.s048;

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
public class S048Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS048() {
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
        // 资产代码
        trdProductDeal.put("tradeId", "4ceda0eaac3f57bc851b");
        // 资产名称
//        dataMap.put("FINPROD_NAME", "");
        // 交易日
        trdProductDeal.put("tradeDate", Long.valueOf("20200214"));
        // 交易日
        trdProductDeal.put("mdate",  Long.valueOf("20200214"));
        // 清算速度
        trdProductDeal.put("settleSpeed", "01");
        // 即期汇率
        trdProductDeal.put("unitCprice", 100);
        // 远期点
        trdProductDeal.put("unitInt",10000);
        // 远期全价
        trdProductDeal.put("unitFprice", 12000);
        // 投资目的
        trdProductDeal.put("invAim", "adsadsa");
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
        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId", "0ceda0eaac3f49bc861b");
        // 撤销说明
        dataMap.put("revokeReason", "ttrsdfs");
        // 备注
        trdProductDeal.put("REMARK", "sdasdasda");
        // 交易币种
//        body.put("PRIN_CCY", "");
        // 期限品种
        trdProductDeal.put("termType", "10Y:10Y");
        // 货币对
        trdProductDealAdd.put("curPair", "01");
        // 定价日
        trdProductDealAdd.put("pricedDate", Long.valueOf("20200214"));
        // 差额交割参考汇率
        trdProductDealAdd.put("refRate", "0.05");
        // 折美元金额
        trdProductDealAdd.put("usdAmt", "2000");

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
        Map<String, Object> tradeFeeDetail =new HashMap<>();
        tradeFeeDetail.put("feePs","02");
        // 费用类型
        tradeFeeDetail.put("feeType", "B02");
        // 费用币种
        tradeFeeDetail.put("ccy","CNY");
        // 费用金额
        tradeFeeDetail.put("feeAmt", 20000);
        // 是否随交易支付
        tradeFeeDetail.put("isPayWithTrade", "1");

        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("trdProductDealAdd",trdProductDealAdd);
        dataMap.put("stlPayOrderslist",stlPayOrderslist);
        dataMap.put("tradeFeeDetaillist",tradeFeeDetail);

        interfCoreController.send(dataMap);

    }
}
