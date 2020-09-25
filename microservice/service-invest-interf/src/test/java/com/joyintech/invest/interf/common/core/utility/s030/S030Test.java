package com.joyintech.invest.interf.common.core.utility.s030;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * 投资管理系统发送交易数据-接受测试
 *
 * @program: tams-base
 * @description:
 * @author: 平路远
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S030Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS030() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("pageId", "TrdDepoDuration002");

        // 交易表Map
        Map<String, Object> trdProductDeal = new HashMap<>();
        Map<String, Object> paTrdProductDeal = new HashMap<>();
        // 交易附加表Map
        Map<String, Object> trdProductDealAdd = new HashMap<>();
        // 标的表Map
        Map<String, Object> finProduct = new HashMap<>();
        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();


        // 交易编号
        trdProductDeal.put("tradeId", "9ceda0eaac3f49bc861b");

        trdProductDeal.put("finprodType2", "1111111");
        paTrdProductDeal.put("paBussType","104");

        trdProductDealAdd.put("portfolioId", "5ceda0eaac3f49bc861b");


        // 资产代码
        trdProductDeal.put("finprodId", "8ceda0eaac3f49bc861b");
        // 市场代码

        finProduct.put("finprodMarketId", "7ceda0eaac3f49bc861b");

        // 通道代码
        trdProductDeal.put("chlId", "F28_TYTD01");

        // 交易日
        trdProductDeal.put("tradeDate", "2019/6/20");
        // 交割日
        trdProductDeal.put("settleDate", "2019/6/20");
        // 计息区间开始日
        trdProductDeal.put("vdate","2019/6/20");
        // 计息区间结束日
        trdProductDeal.put("mdate", "2019/6/20");
        //  本金金额
        trdProductDeal.put("prinAmt", 100000);
        // 应计利息总额
        trdProductDeal.put("intAmt", 1000);
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
        // 备注
        trdProductDeal.put("remark", "ply");

        // 参与人信息
        // 参与人名称
        dataMap.put("traderName", "pk");

        // 参与人联系方式
        dataMap.put("traderContactInfo", "18755467321");
        // 对手方交易员
        trdProductDeal.put("counterTrader", "lilei");


        // 费用明细信息
        dataMap.put("tradeFeeDetaillist", "liumou");
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

        dataMap.put("trdProductDeal", trdProductDeal);
        dataMap.put("trdProductDealAdd", trdProductDealAdd);
        dataMap.put("finProduct", finProduct);
        dataMap.put("stlPayOrders", stlPayOrders);
        dataMap.put("tradeFeeDetaillist", tradeFeeDetaillist);
        interfCoreController.send(dataMap);

    }
}
