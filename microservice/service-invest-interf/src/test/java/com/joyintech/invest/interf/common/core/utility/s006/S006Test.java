package com.joyintech.invest.interf.common.core.utility.s006;


import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资管理系统债券存续期
 * @program: tams-base
 * @description:
 * @author: daiguofu
 * @create: 2020-02-13 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S006Test {
//    @Autowired
//    private InterfRecvComponent recvComponent;
    @Autowired
    private InterfCoreController interfCoreController;
    @Test
    public void testS006() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> trdProductDeal  = new HashMap<>();
        Map<String, Object> paTrdProductDeal = new HashMap<>();
        // 交易编号
        trdProductDeal.put("tradeId","JYSTOCK201908020003");
        // 交易类型
        trdProductDeal.put("finprodType2", "F10");
        paTrdProductDeal.put("paBussType","104");

        // 交易附加表Map
        Map<String, Object> trdProductDealAdd = new HashMap<>();
        // 组合代码
        trdProductDealAdd.put("portfolioId","jixuzuhe0002");
        // 交易场所
        trdProductDeal.put("tradeMarket", "03");
        // 资产代码
        trdProductDeal.put("finprodId","F10_CS001.SH");
        // 标的表Map
        Map<String, Object> finProduct = new HashMap<>();
        dataMap.put("platDate",Long.valueOf("20200214"));
        finProduct.put("finprodMarketId","CS001.SH");
        // 通道代码
        trdProductDeal.put("chlId","F28_TDWY002");
        // 交易日
        trdProductDeal.put("tradeDate",Long.valueOf("20200213"));
        // 交割日
        trdProductDeal.put("settleDate",Long.valueOf("20200213"));
        // 计息区间开始日
        trdProductDeal.put("vdate",Long.valueOf("20200213"));
        // 计息区间结束日
        trdProductDeal.put("mdate",Long.valueOf("20200213"));
        // 本金金额
        trdProductDeal.put("prinAmt",BigDecimal.valueOf(20000));
        // 应计利息总额
        trdProductDeal.put("intAmt",BigDecimal.valueOf(10000));
        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();

        stlPayOrders.put("ps", "C19");
        // 结算金额
        trdProductDeal.put("tradeAmt",BigDecimal.valueOf(5000));
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
        // 备注
        trdProductDeal.put("remark","good");
        // 参与人名称
        dataMap.put("traderName","xiaolizi");
        // 参与人联系方式
        dataMap.put("traderContactInfo","12334455");
        // 费用明细信息
        List<Map<String, Object>> tradeFeeDetaillist = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        // 费用类型
        item1.put("feeType","B14");
        // 费用币种
        item1.put("ccy","CNY");
        // 费用金额
        item1.put("feeAmt",BigDecimal.valueOf(400));
        // 是否随交易支付
        item1.put("isPayWithTrade","01");
        tradeFeeDetaillist.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        // 费用类型
        item2.put("feeType","B15");
        // 费用币种
        item2.put("ccy","CNY");
        // 费用金额
        item2.put("feeAmt",BigDecimal.valueOf(300));
        // 是否随交易支付
        item2.put("isPayWithTrade","02");
        tradeFeeDetaillist.add(item2);
        dataMap.put("tradeFeeDetaillist",tradeFeeDetaillist);
        // 划款信息
        // 收款账户
        stlPayOrders.put("counterAcctNo","77777");
        // 付款账户
        stlPayOrders.put("myAcctNo","80865");
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
        dataMap.put("pageId","TrdBondDuration002");
        interfCoreController.send(dataMap);
    }
}
