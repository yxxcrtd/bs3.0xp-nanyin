package com.joyintech.tams.invest.trade.exchangetransmatch.common.service.bond;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 配债
 */
@Service
public class ExchangeTransMatchBond5Service extends ExchangeTransMatchBondCommonService {

    /**
     * 配债
     * @param
     * @return
     */
    @Override
    protected void setDealTotal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdFinProductDealTotal trdFinProductDealTotal) {
        // 业务类型
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_债券类资产交易.getValue());
        // 单位净价
        trdFinProductDealTotal.setUnitCprice(dealDto.getUnitCprice());
        // 单位利息
        trdFinProductDealTotal.setUnitInt(dealDto.getUnitInt());
        // 单位全价
        trdFinProductDealTotal.setUnitFprice(dealDto.getUnitFprice());
        // 单位面值
        trdFinProductDealTotal.setParValue(dealDto.getParValue());
        // 净价总额
        trdFinProductDealTotal.setCpriceAmt(ArithmeticUtil.multiply(entrustDto.getMarchQty(),dealDto.getUnitCprice()));
        // 全价总额
        trdFinProductDealTotal.setFpriceAmt(ArithmeticUtil.multiply(entrustDto.getMarchQty(),dealDto.getFpriceAmt()));
        // 交易本金
        trdFinProductDealTotal.setTradeAmt(ArithmeticUtil.subtract(trdFinProductDealTotal.getFpriceAmt(),entrustDto.getMarchFeeAmtPay()));
        // 利息总额
        trdFinProductDealTotal.setIntAmt(ArithmeticUtil.multiply(entrustDto.getMarchQty(),dealDto.getUnitInt()));
//        // 到期收益率
//        trdFinProductDealTotal.setYield(trdProductDealDto.getYield());
//        // 行权收益率
//        trdFinProductDealTotal.setExerYield(trdProductDealDto.getExerYield());
        // 首期结算方式
        trdFinProductDealTotal.setDeliveryType("DVP");
    }

    @Override
    public List<StlPayOrdersDto> generateStlPayOrdersList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        List<StlPayOrdersDto> stlPayOrdersList = new ArrayList<>();

        StlPayOrdersDto stlPayOsrdersDto = createStlPayOrders(dealDto, entrustDto, trdProductDeal);
        // 收付方向
        stlPayOsrdersDto.setPs(DictCodeListExt1.收付方向_支出.getValue());
        stlPayOrdersList.add(stlPayOsrdersDto);

        return stlPayOrdersList;
    }

    @Override
    protected TrdProductDeal createDeal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdFinProductDealTotal trdFinProductDealTotal) {
        // 交易字段设置共通
        TrdProductDeal trdProductDeal = createProductDeal(dealDto, entrustDto,trdFinProductDealTotal);
        trdProductDeal.setSettleSpeed(dealDto.getSettleSpeed());
        // 单位净价
        trdProductDeal.setUnitCprice(dealDto.getUnitCprice());
        // 单位利息
        trdProductDeal.setUnitInt(dealDto.getUnitInt());
        // 单位全价
        trdProductDeal.setUnitFprice(dealDto.getUnitFprice());
        // 单位面值
        trdProductDeal.setParValue(null);
        // 净价总额，交易份额，交易本金，利息总额，全价总额，随交易支付费用，交易金额，未随交易支付费用，总交易费用
        calcFeeAmtPay(dealDto, entrustDto,trdProductDeal);
        // 交易方向
        trdProductDeal.setPs(entrustDto.getPs());
        // 到期收益率和行权收益率
        this.calc(trdProductDeal);

        trdProductDeal.setTgBusiType("163");

        return trdProductDeal;
    }
}
