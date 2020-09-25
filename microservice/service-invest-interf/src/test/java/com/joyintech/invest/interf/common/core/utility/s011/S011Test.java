package com.joyintech.invest.interf.common.core.utility.s011;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * 投资管理系统发送投资管理系统发送交易数据 -接受测试
 * @program: tams-base
 * @description:
 * @author: 平路远
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S011Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS011() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("pageId", "TrdFundDuration002.02");
        dataMap.put("platDate",Long.valueOf("20200214"));

        // 交易表Map
        Map<String, Object> trdProductDeal =  new HashMap<>();
        // 交易附加表Map
        Map<String, Object> trdProductDealAdd =  new HashMap<>();
        // 标的表Map
        Map<String, Object> finProduct = new HashMap<>();
        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();


        // 交易编号
        trdProductDeal.put("tradeId", "9ceda0eaac3f49bc851b");
        // 成交编号/合同编号
        trdProductDeal.put("fTradeId", "5ceda0eaac3f49bc851b");

        trdProductDeal.put("finprodType2", "1111111");
        trdProductDeal.put("ps", "02");

        trdProductDealAdd.put("portfolioId", "5ceda0eaac3f49bc851u");

        // 交易对手代码
        trdProductDeal.put("counterId","5ceda0eaac3f49bc854b");

        // 交易场所
        trdProductDeal.put("tradeMarket", "03");
        // 资产代码
        trdProductDeal.put("finprodId", "5ceda0eaac3f48bc854b");


        finProduct.put("finprodMarketId", "5ceda0eaac3f49bc854b");
        // 交易市场
        trdProductDeal.put("tradeMarketMode", "02");
        // 交易平台
        trdProductDeal.put("tradePlat", trdProductDeal.get("tradePlat"));

        // 交易日
        trdProductDeal.put("tradeDate", Long.valueOf("20200214"));
        // 交割日
        trdProductDeal.put("settleDate", Long.valueOf("20200214"));
        // 交付日
        trdProductDeal.put("payDate", Long.valueOf("20200214"));
        // 清算速度
        trdProductDeal.put("settleSpeed", 2);
        // 百元净价
        trdProductDeal.put("unitCprice", 12);
        // 百元应计利息
        trdProductDeal.put("unitInt", 10);
        // 百元全价
        trdProductDeal.put("unitFprice",1);
        // 净价总额
        trdProductDeal.put("cpriceAmt", 1000000);
        // 券面总额
        trdProductDeal.put("shareAmt", 9000000);
        // 应计利息总额
        trdProductDeal.put("intAmt", 20000);
        // 全价总额
        trdProductDeal.put("fpriceAmt", 12000000);
        // 到期收益率
        trdProductDeal.put("yield", 0.05);
        // 行权收益率
        trdProductDeal.put("exerYield", 0.06);

        // 收付方向
        stlPayOrders.put("ps", "02");
        // 结算币种
        stlPayOrders.put("ccy", "CNY");
        // 结算金额
        stlPayOrders.put("settleAmt", 120000);


        // 投资目的
        trdProductDeal.put("invAim", "FVTPL");
        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId", "0ceda0eaac3f49bc861b");
        // 撤销说明
        dataMap.put("revokeReason", "hsdjasd");
        // 证券账户代码
        dataMap.put("secManageAcctId", "3ceda0eaac3f49bc861b");

        // 通道代码
        trdProductDeal.put("chlId", "F28_TYTD01");

        // 备注
        trdProductDeal.put("remark", "ply");
        // 转换信息
        // 转换资产代码
        dataMap.put("actPortfolioId", "8eda0eaac3f49bc861b");
        // 转换资产名称
//        body.put("FINPROD_NAME2", request.get("actPortfolioId"));
//        // 转换价格
//        body.put("PRICE2", request.get("actPortfolioId"));
//        // 转换数量
//        body.put("SHARE2", request.get("actPortfolioId"));
//        // 零头折现金额
//        body.put("CONVERT_CASH_AMT", request.get("actPortfolioId"));

        // 保证品信息
        // 保证金金额
        // 保证金收付方向
        // 参与人信息
        // 参与人名称
        dataMap.put("traderName", "liumei");

        // 参与人联系方式
        dataMap.put("traderContactInfo", "18755467321");
        // 对手方交易员
        trdProductDeal.put("counterTrader", "lilei");


        // 费用明细信息
        List<Map<String, Object>> tradeFeeDetaillist = new ArrayList<>();
        Map<String, Object> feeTypeMap = new HashMap<>();
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
        stlPayOrders.put("counterAcctNo", "123123213123");
        stlPayOrders.put("myAcctNo", "123456789012:测试");
        stlPayOrders.put("settleAmt", 2000);

        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("trdProductDealAdd",trdProductDealAdd);
        dataMap.put("finProduct",finProduct);
        dataMap.put("stlPayOrders",stlPayOrders);
        dataMap.put("tradeFeeDetaillist",tradeFeeDetaillist);

        interfCoreController.send(dataMap);

    }
}
