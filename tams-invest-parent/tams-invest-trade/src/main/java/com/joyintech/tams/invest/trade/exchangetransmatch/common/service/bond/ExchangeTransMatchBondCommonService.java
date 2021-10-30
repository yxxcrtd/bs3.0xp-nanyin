package com.joyintech.tams.invest.trade.exchangetransmatch.common.service.bond;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeTransMatchBondCommonService extends BaseService {

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * 生成汇总交易
     * @param dealDto
     * @param entrustDto
     * @return
     */
    protected TrdFinProductDealTotal genTrdFinProductDealTotal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto) {
        TrdFinProductDealTotal dealTotal = new TrdFinProductDealTotal();
        // 汇总交易编号
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        if (StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券卖出);
        }
        if (StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券卖出);
        }
        if (StringUtil.equals(DictCodeList.交易方向_申购.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        if (StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        if (StringUtil.equals(DictCodeListExt1.交易方向_债转股.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_可转债转股交易);
        }
        if (StringUtil.equals(DictCodeListExt3.交易方向_配债.getValue(), entrustDto.getPs())) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        dealTotal.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));

        // 设置汇总交易信息
//        setDealTotal(dealTotal, trdProductDealDto);
        // 金融产品代码
        dealTotal.setFinprodId(entrustDto.getFinprodId());
        // 金融产品类型（投管分类）
        dealTotal.setFinprodType2(entrustDto.getFinprodType2());
        // 分支序号
        dealTotal.setBranch(0L);
        // 交易日
        dealTotal.setTradeDate(dealDto.getTradeDate());
        // 起息日
        dealTotal.setVdate(null);
        // 到期日
        dealTotal.setMdate(null);
        // 交付日
//        dealTotal.setPayDate(dealDto.getPayDate());
        // 交割日
        dealTotal.setSettleDate(dealDto.getSettleDate());
//        if (StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), entrustDto.getPs())) {
//            // 交割日期是缴款日期
//            dealTotal.setSettleDate(stlDataDealProcessCommonService.getNextWorkDay(dealDto.getTradeDate(),
//                    CacheUtil.getSysParam(StlDataDealProcessConstant.STL_DOWNDATA_DEAL_SHJYS_CALID)));
//        }
        // 清算速度
        dealTotal.setSettleSpeed(dealDto.getSettleSpeed());
        // 交易币种
        dealTotal.setCcy(dealDto.getCcy());
        // 交易份额
        dealTotal.setShareAmt(entrustDto.getMarchQty());
        // 随交易支付费用
        dealTotal.setFeeAmtPay(entrustDto.getMarchFeeAmtPay());
        // 交易金额
        dealTotal.setTradeAmt(ArithmeticUtil.subtract(entrustDto.getMarchAmt(),entrustDto.getMarchFeeAmtPay()));
        // 未随交易支付费用
        dealTotal.setFeeAmtUnpay(entrustDto.getMarchFeeAmtPay());
        // 总交易费用
        dealTotal.setFeeAmt(ArithmeticUtil.doAdd(entrustDto.getMarchFeeAmtPay(),entrustDto.getMarchFeeAmtPay()));
        // 交易方向
        dealTotal.setPs(entrustDto.getPs());
        // 投资目的
        dealTotal.setInvAim(entrustDto.getInvAim());
        // 交易状态
        dealTotal.setTradeStatus(BussinessDictCodeList.交易状态_初始.getValue());
        // 是否已撤销
        dealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        // 证券管理户代码
        dealTotal.setSecManageAcctId(dealDto.getSecAcctId());
        // 前置交易编号
        dealTotal.setbTradeId(null);
        // 是否外部交易
        dealTotal.setIsOutTrade(DictCodeList.是否_是.getValue());
        // 交易场所
        dealTotal.setTradeMarket(dealDto.getTradeMarket());
        // 交易平台
        dealTotal.setTradePlat(dealDto.getTradePlat());
        // 交易市场
        dealTotal.setTradeMarketMode(dealDto.getTradeMarketMode());
        // 投资组合代码
        dealTotal.setPortfolioId(dealDto.getPortfolioId());
        // 备注
        dealTotal.setRemark(entrustDto.getRemark());
        // 投管业务类型
//        dealTotal.setTgBusiType(trdProductDealDto.getTgBusiType());
        // 利率
        dealTotal.setRate(null);
        // 回购期限(天)
        dealTotal.setRepoTerm(null);
        // 期限品种
        dealTotal.setTermType(null);
        return dealTotal;
    }

    public BaseTradeProductDealDto genFinProductTradeDto(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto){
        return null;
    }


}
