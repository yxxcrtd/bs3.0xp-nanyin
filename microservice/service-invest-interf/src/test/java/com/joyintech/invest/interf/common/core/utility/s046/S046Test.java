package com.joyintech.invest.interf.common.core.utility.s046;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 利率互换续存期
 * @program: tams-base
 * @description:
 * @author: daiguofu
 * @create: 2020-02-25 16:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S046Test {
//    @Autowired
//    private InterfRecvComponent recvComponent;
    @Autowired
    private InterfCoreController interfCoreController;
    @Test
    public void testS046() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> trdProductDeal  = new HashMap<>();
        Map<String, Object> paTrdProductDeal = new HashMap<>();
        // pageId
        dataMap.put("pageId","");

        dataMap.put("platDate",Long.valueOf("20200214"));
        // 交易编号v
        trdProductDeal.put("tradeId","JYSTOCK20190800245");
        // 交易类型
        trdProductDeal.put("finprodType2", "F10");
        paTrdProductDeal.put("paBussType","T01");
        // 交易附加表Map
        Map<String, Object> trdProductDealAdd = new HashMap<>();
        // 组合代码
        trdProductDealAdd.put("portfolioId","jixuzuhe45226");
        // 交易对手代码
        trdProductDeal.put("counterId","12154656565");
        // 交易场所
        trdProductDeal.put("tradeMarket", "03");
        // 资产代码
        trdProductDeal.put("finprodId","5ceda0eaac3f48bc854b");

        // 标的表Map
        Map<String, Object> finProduct = new HashMap<>();
        // 市场代码
        finProduct.put("finprodMarketId","CS001.SH");

        // 交易日
        trdProductDeal.put("tradeDate", Long.valueOf("20200213"));
        // 交割日
        trdProductDeal.put("settleDate", Long.valueOf("20200213"));
        // 起息日
        trdProductDeal.put("vdate",Long.valueOf("20200213"));
        // 到期日
        trdProductDeal.put("mdate",Long.valueOf("20200213"));
        //名义本金
        paTrdProductDeal.put("theoryAmt", BigDecimal.valueOf(15632));
        //名义本金币种
        paTrdProductDeal.put("branch", "CNY");
        //收付方向
        //交易金额
        trdProductDeal.put("tradeAmt",BigDecimal.valueOf(45465));

        // 指令表Map
        Map<String, Object> stlPayOrders = new HashMap<>();
        // 结算币种
        stlPayOrders.put("ccy","CNY");
        // 收付方向
        stlPayOrders.put("ps","T38");
        // 交易状态
        dataMap.put("tradeStatus", "01");
        // 原交易编号
        trdProductDeal.put("pTradeId", "0ceda0eaac3f49bc861b");
        //撤销说明
        dataMap.put("revokeReason","biubiubiu");
        //备注
        trdProductDeal.put("remark", "ply");
        // 本方交易员
        // 参与人名称
        dataMap.put("traderName", "liumei");
        // 参与人联系方式
        dataMap.put("traderContactInfo", "18755467321");
        // 对手方交易员
        // 参与人名称
        trdProductDeal.put("counterTrader","aisiao");
        // 参与人联系方式
        trdProductDeal.put("counterContact","1293849959");
        // 划款信息
        // 收款账户
        stlPayOrders.put("counterAcctNo","bilibili");
        // 付款账户
        stlPayOrders.put("myAcctNo","aiqiyi");
        // 划款金额
        // 币种
        dataMap.put("trdProductDeal",trdProductDeal);
        dataMap.put("stlPayOrders",stlPayOrders);
        dataMap.put("paTrdProductDeal",paTrdProductDeal);
        dataMap.put("trdProductDealAdd",trdProductDealAdd);
        dataMap.put("finProduct",finProduct);

        interfCoreController.send(dataMap);
    }
}
