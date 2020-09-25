package com.joyintech.invest.interf.common.core.utility.s023;


import com.joyintech.invest.interf.common.core.controller.InterfCoreController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资管理系统计划类净值型资产存续期
 * @program: tams-base
 * @description:
 * @author: daiguofu
 * @create: 2020-02-14 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S023Test {
//    @Autowired
//    private InterfRecvComponent recvComponent;
    @Autowired
    private InterfCoreController interfCoreController;
    @Test
    public void testS023() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> trdProductDeal  = new HashMap<>();
        Map<String, Object> paTrdProductDeal = new HashMap<>();
        // 交易编号v
        trdProductDeal.put("tradeId","JYSTOCK201908020004");
        // 成交编号/合同编号
        trdProductDeal.put("fTradeId","CR20130802000098");
        // 交易类型
        trdProductDeal.put("finprodType2", "F10");
        paTrdProductDeal.put("paBussType", "104");
        // 交易附加表Map
        Map<String, Object> trdProductDealAdd = new HashMap<>();
        // 组合代码
        trdProductDealAdd.put("portfolioId","jixuzuhe0002");
        // 交易对手代码
        trdProductDeal.put("counterId","JYDS000000000384");
        dataMap.put("platDate",Long.valueOf("20200214"));
        // 交易场所
        trdProductDeal.put("tradeMarket","03");
        // 资产代码
        trdProductDeal.put("finprodId","F10_CS001.SH");
        // 标的表Map
        Map<String, Object> finProduct = new HashMap<>();
        // 市场代码
        finProduct.put("finprodMarketId","CS001.SH");
        // 交易市场
        trdProductDeal.put("tradeMarketMode","02");
        // 交易平台
        trdProductDeal.put("tradePlat","01");
        // 交易日
        trdProductDeal.put("tradeDate",Long.valueOf("20200214"));
        // 交割日
        trdProductDeal.put("settleDate",Long.valueOf("20200214"));
        // 交付日
        trdProductDeal.put("payDate",Long.valueOf("20200214"));
        // 清算速度
        trdProductDeal.put("settleSpeed","01");
        // 百元净价
        trdProductDeal.put("unitCprice",BigDecimal.valueOf(30000));
        // 百元应计利息
        trdProductDeal.put("unitInt",BigDecimal.valueOf(100));
        // 百元全价
        trdProductDeal.put("unitFprice",BigDecimal.valueOf(6000));
        // 净价总额
        trdProductDeal.put("cpriceAmt",BigDecimal.valueOf(8000));
        // 券面总额
        trdProductDeal.put("shareAmt",BigDecimal.valueOf(20000));
        // 应计利息总额
        trdProductDeal.put("intAmt",BigDecimal.valueOf(40000));
        // 全价总额
        trdProductDeal.put("fpriceAmt",BigDecimal.valueOf(50000));
        // 到期收益率
        trdProductDeal.put("yield","0.04500584176950");
        // 行权收益率
        trdProductDeal.put("exerYield","-0.36994098251900");
        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();
        // 收付方向
        stlPayOrders.put("ps","T38");
        // 结算金额
        trdProductDeal.put("settleAmt",BigDecimal.valueOf(5000));
        // 结算币种
        stlPayOrders.put("ccy","CNY");
        // 投资目的
        trdProductDeal.put("invAim","FVTPL");
        // 交易状态
        dataMap.put("tradeStatus","01");
        // 原交易编号
        trdProductDeal.put("pTradeId","6e5d7c0dcc8d42d79e5f");
        // 撤销说明
        dataMap.put("revokeReason","起来搞点夜点心吃吃吧");
        // 证券账户代码
        dataMap.put("secManageAcctId","1902903");
        // 通道代码
        trdProductDeal.put("chlId","F28_TDWY002");
        // 备注
        trdProductDeal.put("remark","测试");
        // 转换信息
        dataMap.put("actPortfolioId","2334455566");
        // 保证品信息
        // 保证金金额
        // 保证金收付方向
        // 参与人信息
        // 本方交易员
        dataMap.put("traderName","xiaodai");
        // 参与人联系方式
        dataMap.put("traderContactInfo","1234900004");
        // 对手方交易员
        // 参与人名称
        trdProductDeal.put("counterTrader","aiing");
        // 参与人联系方式
        trdProductDeal.put("counterContact","18856981834");
        // 费用明细信息
        List<Map<String, Object>> tradeFeeDetaillist= new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        // 费用类型
        item1.put("feeType","B14");
        // 费用币种
        item1.put("ccy","CNY");
        // 费用金额
        item1.put("feeAmt",BigDecimal.valueOf(400));
        // 是否随交易支付
        item1.put("isPayWithTrade","Y");
        tradeFeeDetaillist.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        // 费用类型
        item2.put("feeType","B15");
        // 费用币种
        item2.put("ccy","CNY");
        // 费用金额
        item2.put("feeAmt",BigDecimal.valueOf(300));
        // 是否随交易支付
        item2.put("isPayWithTrade","N");
        tradeFeeDetaillist.add(item2);
        dataMap.put("tradeFeeDetaillist",tradeFeeDetaillist);
        // 划款信息
        // 收款账户
        stlPayOrders.put("counterAcctNo","4190522");
        // 付款账户
        stlPayOrders.put("myAcctNo","80865");
        // 划款金额
        trdProductDeal.put("tradeAmt",BigDecimal.valueOf(40000));
        // 交易表Map
        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("paTrdProductDeal",paTrdProductDeal);
        // 标的表Map
        dataMap.put("finProduct",finProduct);
        // 交易附加表Map
        dataMap.put("trdProductDealAdd",trdProductDealAdd);
        // 指令表Map
        dataMap.put("stlPayOrders",stlPayOrders);
        // pageId
        dataMap.put("pageId","TrdPlanNetDuration002");
        interfCoreController.send(dataMap);
    }
}
