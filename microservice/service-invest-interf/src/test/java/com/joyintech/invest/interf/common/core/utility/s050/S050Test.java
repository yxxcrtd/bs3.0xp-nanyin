package com.joyintech.invest.interf.common.core.utility.s050;


import com.joyintech.invest.interf.common.core.controller.InterfCoreController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 外汇掉期交易
 * @program: tams-base
 * @description:
 * @author: daiguofu
 * @create: 2020-02-19 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S050Test {
//    @Autowired
//    private InterfRecvComponent recvComponent;
    @Autowired
    private InterfCoreController interfCoreController;
    @Test
    public void testS050() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> trdProductDeal  = new HashMap<>();

        // 交易编号v
        trdProductDeal.put("tradeId","JYSTOCK20190800245");
        dataMap.put("platDate",Long.valueOf("20200214"));
        // 成交编号/合同编号
        trdProductDeal.put("fTradeId","CR20130802024891");
        // 交易类型
        trdProductDeal.put("finprodType2", "F10");
        trdProductDeal.put("ps","T01");
        dataMap.put("isInnerTrade","Y");

        // 组合代码
        dataMap.put("portfolioId","jixuzuhe45226");
        // 交易对手代码
        trdProductDeal.put("counterId","JYDS00000001545");
        // 资产代码

        // 交易日
        trdProductDeal.put("tradeDate",Long.valueOf("20200219"));
        // 近端交割日
        trdProductDeal.put("settleDate",Long.valueOf("20200219"));
        // 近端汇率
        trdProductDeal.put("unitCprice",BigDecimal.valueOf(12));
        // 近端掉期点
        trdProductDeal.put("unitInt",BigDecimal.valueOf(1));
        // 近端全价
        trdProductDeal.put("unitFprice",BigDecimal.valueOf(0.8));
        // 清算模式
        trdProductDeal.put("deliveryType","CENT-SETT");
        // 交易币种金额
        trdProductDeal.put("prinAmt",BigDecimal.valueOf(15632));
        // 对应币种收付方向
        //对应币种金额
        trdProductDeal.put("tradeAmt",BigDecimal.valueOf(45465));
        // 对应币种
        trdProductDeal.put("ccy","CNY");
        // 交易状态
        dataMap.put("tradeStatus", "01");

        // 原交易编号
        trdProductDeal.put("pTradeId","6e54lc0dcc8d452l79545e");
        // 撤销说明
        dataMap.put("revokeReason","布鲁克");
        // 备注
        trdProductDeal.put("remark","测试111");
        // 交易表AddMap
        Map<String, Object> trdProductDealAdd = new HashMap<>();
        // 期限品种
        trdProductDealAdd.put("termType","R3M");
        // 货币对
        trdProductDealAdd.put("curPair","CNY/HKD");
        // 近端折美元金额
        trdProductDealAdd.put("usdAmt",BigDecimal.valueOf(2000));

        // 交易类金融产品标的表Map
        Map<String, Object> finTradeProduct =new HashMap<>();
        // 远端汇率
        finTradeProduct.put("mUnitCprice",BigDecimal.valueOf(8));
        // 远端交割日
        finTradeProduct.put("mdate",Long.valueOf("20200219"));
        // 远端掉期点
        finTradeProduct.put("mUnitInt",BigDecimal.valueOf(0.2));
        // 远端全价
        finTradeProduct.put("mUnitFprice",BigDecimal.valueOf(0.8));
        // 远端折美元金额
        finTradeProduct.put("usdAmt",BigDecimal.valueOf(3000));
        // 远端对应币种收付方向
        finTradeProduct.put("excPs", "T_B/S");
        // 远端对应币种金额
        finTradeProduct.put("mTradeAmt",BigDecimal.valueOf(45666));
        // 参与人名称
        dataMap.put("traderName","xiaodai1");
        // 参与人联系方式
        dataMap.put("traderContactInfo","1395646789");
        // 对手方交易员
        // 参与人名称
        trdProductDeal.put("counterTrader","xiaodai56");
        // 参与人联系方式
        trdProductDeal.put("counterContact","12564785262");
        // 费用明细信息
        List<Map<String, Object>> tradeFeeDetaillist = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        // 费用类型
        item1.put("feeType","B14");
        // 费用币种
        item1.put("ccy","CNY");
        // 费用金额
        item1.put("feeAmt",BigDecimal.valueOf(400));
        // 费用收付方向
        dataMap.put("feePs","01");
        // 是否随交易支付
        item1.put("isPayWithTrade","Y");
        tradeFeeDetaillist.add(item1);
        // 账户信息
        List<Map<String, Object>> stlPayOrderslist = new ArrayList<>();
        Map<String, Object> item2 = new HashMap<>();
        // 账号
        item2.put("myAcctNo","1245465");
        // 币种
        item2.put("ccy","CNY");
        // 收款账户
        item2.put("recAcctNo","222222");
        // 付款账户
        item2.put("payAcctNo","11111");
        // 划款金额
        item2.put("settleAmt",BigDecimal.valueOf(5000));
        // 币种
        item2.put("ccy","CNY");
        Map<String, Object> item3 = new HashMap<>();
        item3.put("myAcctNo","");
        // 账号
        item3.put("counterAcctNo","4566632");
        // 币种
        item3.put("ccy","CNY");
        // 收款账户
        item3.put("recAcctNo","33333");
        // 付款账户
        item3.put("payAcctNo","44444");
        // 划款金额
        item3.put("settleAmt",BigDecimal.valueOf(4000));
        // 币种
        item3.put("ccy","CNY");
        stlPayOrderslist.add(item2);
        stlPayOrderslist.add(item3);

        // 交易表Map
        dataMap.put("trdProductDeal",trdProductDeal);
        // 交易表AddMap
        dataMap.put("trdProductDealAdd",trdProductDealAdd);
        // 交易类金融产品标的表Map
        dataMap.put("finTradeProduct",finTradeProduct);
        // 费用明细信息
        dataMap.put("tradeFeeDetaillist",tradeFeeDetaillist);
        // 账户信息
        dataMap.put("stlPayOrderslist",stlPayOrderslist);
        // pageId
        dataMap.put("pageId","TrdFxswapInvestTrade002.01");
        interfCoreController.send(dataMap);
    }
}
