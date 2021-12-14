package com.joyintech.tams.invest.trade.exchangetransmatch.common.service.Fund;

import com.joyintech.background.component.common.pack.common.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.TrdProductDealDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExchangeTransMatchFund1Service extends BaseService {

    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    public BaseTradeProductDealDto genFinProductTradeDto(ExchangeTransMatchCommonDto dto, ExchangeTransMatchCommonEntrustDto commonEntrustDto) {
        BaseTradeProductDealDto entity = new BaseTradeProductDealDto();

        entity.setTrdProductDeal(setTrdProductDeal(dto,commonEntrustDto));
        entity.setTrdProductDealAdd(this.genTrdProductDealAdd(dto,commonEntrustDto,entity.getTrdProductDeal()));
        entity.setPtlTrade(generatePtlTrade(dto,commonEntrustDto,Arrays.asList((TrdProductDealDto)entity.getTrdProductDeal())));
        entity.setStlPayOrdersList(generateStlPayOrdersList(dto,Arrays.asList((TrdProductDealDto)entity.getTrdProductDeal())));
        //生成费用信息list
        entity.setTrdTradeFeeDetailList(this.genTrdTradeFeeDetailList(dto,commonEntrustDto,entity.getTrdProductDeal()));
        return entity;
    }

    /**
     * 设置金融产品交易明细DTO
     */
    private TrdProductDealDto setTrdProductDeal(ExchangeTransMatchCommonDto settleTransData,ExchangeTransMatchCommonEntrustDto entrustDto) {
        TrdProductDealDto trdProductDeal = new TrdProductDealDto();
        // ∑交易金额_原始
        BigDecimal tradeAmtRawSum = BigDecimal.ZERO;
        // ∑交易份额
        BigDecimal shareAmtSum = BigDecimal.ZERO;
        // ∑全价总额
        BigDecimal fpriceAmtSum = BigDecimal.ZERO;
        // ∑随交易支付费用
        BigDecimal feeAmtPaySum = BigDecimal.ZERO;
        // ∑未随交易支付费用
        BigDecimal feeAmtUnPaySum = BigDecimal.ZERO;
        // 经手费
        BigDecimal jsfAmt = BigDecimal.ZERO;
        // 过户费
        BigDecimal ghfAmt = BigDecimal.ZERO;
        // 证管费
        BigDecimal zgfAmt = BigDecimal.ZERO;
        // 手续费
        BigDecimal sxfAmt = BigDecimal.ZERO;
        // 印花税
        BigDecimal stampFeeAmt = BigDecimal.ZERO;
        // 结算费
        BigDecimal settleFee = BigDecimal.ZERO;
        // 佣金
        BigDecimal commissionAmt = BigDecimal.ZERO;

        // ∑交易金额
        BigDecimal tradeAmtSum = BigDecimal.ZERO;
        tradeAmtRawSum = ArithmeticUtil.doAdd(tradeAmtRawSum, settleTransData.getTradeAmt());
        shareAmtSum = ArithmeticUtil.doAdd(shareAmtSum, settleTransData.getShareAmt());
        tradeAmtSum = ArithmeticUtil.doAdd(tradeAmtSum, settleTransData.getTradeAmt());
        fpriceAmtSum = ArithmeticUtil.doAdd(fpriceAmtSum, settleTransData.getFpriceAmt());
        feeAmtPaySum = ArithmeticUtil.doAdd(feeAmtPaySum, getFeeAmtPay(settleTransData));
        if (StringUtil.equals(DictCodeList.是否_否.getValue(), settleTransData.getIsPayWithTrade())) {
            feeAmtUnPaySum = ArithmeticUtil.doAdd(feeAmtUnPaySum, settleTransData.getCommissionAmt());
        }
        jsfAmt = ArithmeticUtil.doAdd(jsfAmt, settleTransData.getJsfAmt());
        ghfAmt = ArithmeticUtil.doAdd(ghfAmt, settleTransData.getGhfAmt());
        zgfAmt = ArithmeticUtil.doAdd(zgfAmt, settleTransData.getZgfAmt());
        sxfAmt = ArithmeticUtil.doAdd(sxfAmt, settleTransData.getSxfAmt());
        stampFeeAmt = ArithmeticUtil.doAdd(stampFeeAmt, settleTransData.getStampFee());
        settleFee = ArithmeticUtil.doAdd(settleFee, settleTransData.getSettleFee());
        commissionAmt = ArithmeticUtil.doAdd(commissionAmt, settleTransData.getCommissionAmt());

        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        // 交易编号，按现有的交易编号生成规则，自动生成
        if (StringUtil.equals(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue(), settleTransData.getBusiType())) {
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), settleTransData.getPs())) {

                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金申购);
            }
            else {
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金赎回);
            }
        }
        else {
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), settleTransData.getPs())) {
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_净值型资产申购);
            }
            else {
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_净值型资产赎回);
            }
        }
        trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        // 业务类型
        trdProductDeal.setBusiType(settleTransData.getBusiType());
        // 金融产品代码
        trdProductDeal.setFinprodId(settleTransData.getFinprodId());
        // 金融产品类型
        trdProductDeal.setFinprodType(settleTransData.getFinprodType());
        // 金融产品类型（投管分类）
        trdProductDeal.setFinprodType2(settleTransData.getFinprodType2());
        // 分支序号 - 0
        trdProductDeal.setBranch(0l);
        // 交易日期
        trdProductDeal.setTradeDate(settleTransData.getTradeDate());
        // 交割日期
        trdProductDeal.setSettleDate(settleTransData.getSettleDate());
        // 清算速度
        trdProductDeal.setSettleSpeed(settleTransData.getSettleSpeed());
        // 交易币种
        trdProductDeal.setCcy(settleTransData.getCcy());
        // 净值日期
        trdProductDeal.setCpriceDate(settleTransData.getTradeDate());
        // 单位净价
        BigDecimal unitCprice = ArithmeticUtil.divide(fpriceAmtSum, shareAmtSum, 14);
        trdProductDeal.setUnitCprice(settleTransData.getUnitCprice());
        // 交易本金
        trdProductDeal.setPrinAmt(entrustDto.getMarchAmt());
        // 随交易支付费用
        trdProductDeal.setFeeAmtPay(entrustDto.getMarchFeeAmtPay());
        // ∑成交数据明细.交易份额
        trdProductDeal.setShareAmt(entrustDto.getMarchQty());
        // ∑成交数据明细.交易金额
        trdProductDeal.setTradeAmt(entrustDto.getMarchAmt());
        // 全价总额
        trdProductDeal.setFpriceAmt(entrustDto.getMarchAmt());
        // 未随交易支付费用
        trdProductDeal.setFeeAmtUnpay(entrustDto.getMarchFeeAmtUnPay());
        // 总交易费用
        trdProductDeal.setFeeAmt(ArithmeticUtil.doAdd(entrustDto.getMarchFeeAmtPay(), entrustDto.getMarchFeeAmtUnPay()));
        // 交易方向
        trdProductDeal.setPs(settleTransData.getPs());
        // 交易状态
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 设置 是否已撤销，默认“否”
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        // 是否外部交易
        trdProductDeal.setIsOutTrade(DictCodeList.是否_是.getValue());
        // 交易场所
        trdProductDeal.setTradeMarket(settleTransData.getTradeMarket());
        // 交易平台
        trdProductDeal.setTradePlat(settleTransData.getTradePlat());
        // 交易市场
        trdProductDeal.setTradeMarketMode(settleTransData.getTradeMarketMode());

        if(StringUtil.equals(settleTransData.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
            trdProductDeal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
        }
        else if(StringUtil.equals(settleTransData.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
            trdProductDeal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
        }
        // 证券管理户代码
        trdProductDeal.setSecManageAcctId(settleTransData.getSecAcctId());

        // 登记托管机构
        trdProductDeal.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
        // 交收方式
        trdProductDeal.setSettlemenType(settleTransData.getSettleMode());

        // 投管业务类型
        if (StringUtil.equals(settleTransData.getBusiType(), DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue())
                && StringUtil.equals(settleTransData.getPs(), DictCodeList.交易方向_买入.getValue())) {
            trdProductDeal.setTgBusiType(DictCodeListExt3.平安业务类型_货币基金买入.getValue());
        } else if (StringUtil.equals(settleTransData.getBusiType(), DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue())
                && StringUtil.equals(settleTransData.getPs(), DictCodeList.交易方向_卖出.getValue())) {
            trdProductDeal.setTgBusiType(DictCodeListExt3.平安业务类型_货币基金卖出.getValue());
        } else if (!StringUtil.equals(settleTransData.getBusiType(), DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue())
                && StringUtil.equals(settleTransData.getPs(), DictCodeList.交易方向_卖出.getValue())) {
            trdProductDeal.setTgBusiType(DictCodeListExt3.平安业务类型_非货币基金卖出.getValue());
        } else if (!StringUtil.equals(settleTransData.getBusiType(), DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue())
                && StringUtil.equals(settleTransData.getPs(), DictCodeList.交易方向_买入.getValue())) {
            trdProductDeal.setTgBusiType(DictCodeListExt3.平安业务类型_非货币基金买入.getValue());
        }

        // 席位号
        trdProductDeal.setSeatId(settleTransData.getSeatNumber());
        // 投资组合代码
        trdProductDeal.setPortfolioId(settleTransData.getPortfolioId());
        return trdProductDeal;
    }

    /**
     * 金融产品交易附属表
     */
    public TrdProductDealAdd genTrdProductDealAdd(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        BeanUtil.copyPropertiesIgnoreNull(trdProductDealAdd, trdProductDeal);
        trdProductDealAdd.setEntrustId(entrustDto.getEntrustId());
        trdProductDealAdd.setPortfolioId(entrustDto.getPortfolioId());

        if(StringUtil.equals(DictCodeListExt3.下行交易匹配类型_自动匹配.getValue(),dealDto.getMatchType())) {
            trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_批处理生成.getValue());
        }
        else {
            trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        }
        return trdProductDealAdd;
    }

    /**
     * 获取 组合交易DTO
     * @return 组合交易DTO
     */
    protected List<PtlTrade> generatePtlTrade(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto,List<TrdProductDealDto> dealList) {
        List<PtlTrade> res = new ArrayList<>();
        dealList.forEach(deal-> {
            PtlTrade ptlTrade = new PtlTrade();
            // 组合代码
            ptlTrade.setPortfolioId(entrustDto.getPortfolioId());
            // 业务编号
            ptlTrade.setBusiNo(deal.getTradeId());
            // 业务表
            ptlTrade.setBusiTable(DictCodeListStl.业务表_金融产品交易表.getValue());
            // 业务明细代码
            ptlTrade.setBusiId(deal.getFinprodId());
            // 投资目的
            ptlTrade.setInvAim(deal.getInvAim());
            // 证券托管户代码
            ptlTrade.setSecManageAcctId(deal.getSecManageAcctId());
            // 实际发生日
            ptlTrade.setSettleDate(deal.getSettleDate());
            // 业务类型
            ptlTrade.setBusiType(DictCodeList.共通业务类型_基金类资产交易.getValue());
            res.add(ptlTrade);
        });
        return res;
    }

    /**
     * 金融产品交易费用明细list
     * @param
     * @return
     */
    public List<TrdTradeFeeDetail> genTrdTradeFeeDetailList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        List<TrdTradeFeeDetail> trdTradeFeeDetailList = new ArrayList<>();
        TrdTradeFeeDetail stampFee = new TrdTradeFeeDetail();
        stampFee.setTradeId(trdProductDeal.getTradeId());
        stampFee.setFeeType(DictCodeListExt1.一次性费用类型_印花税.getValue());
        stampFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        stampFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSxfAmt()));
        stampFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail jsfFee = new TrdTradeFeeDetail();
        jsfFee.setTradeId(trdProductDeal.getTradeId());
        jsfFee.setFeeType(DictCodeListExt1.一次性费用类型_经手费.getValue());
        jsfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        jsfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getJsfAmt()));
        jsfFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail ghfFee = new TrdTradeFeeDetail();
        ghfFee.setTradeId(trdProductDeal.getTradeId());
        ghfFee.setFeeType(DictCodeListExt1.一次性费用类型_过户费.getValue());
        ghfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        ghfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getGhfAmt()));
        ghfFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail zgfFee = new TrdTradeFeeDetail();
        zgfFee.setTradeId(trdProductDeal.getTradeId());
        zgfFee.setFeeType(DictCodeListExt1.一次性费用类型_证管费.getValue());
        zgfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        zgfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getZgfAmt()));
        zgfFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail sxfFee = new TrdTradeFeeDetail();
        sxfFee.setTradeId(trdProductDeal.getTradeId());
        sxfFee.setFeeType(DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        sxfFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        sxfFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSxfAmt()));
        sxfFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail settleFee = new TrdTradeFeeDetail();
        settleFee.setTradeId(trdProductDeal.getTradeId());
        settleFee.setFeeType(DictCodeListExt1.一次性费用类型_结算费.getValue());
        settleFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        settleFee.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSettleFee()));
        settleFee.setRegistOrg(trdProductDeal.getRegistOrg());

        TrdTradeFeeDetail commissionAmt = new TrdTradeFeeDetail();
        commissionAmt.setTradeId(trdProductDeal.getTradeId());
        commissionAmt.setFeeType(DictCodeListExt1.一次性费用类型_佣金.getValue());
        commissionAmt.setIsPayWithTrade(dealDto.getIsPayWithTrade());
        commissionAmt.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getCommissionAmt()));
        commissionAmt.setRegistOrg(trdProductDeal.getRegistOrg());

        trdTradeFeeDetailList.add(stampFee);
        trdTradeFeeDetailList.add(jsfFee);
        trdTradeFeeDetailList.add(ghfFee);
        trdTradeFeeDetailList.add(zgfFee);
        trdTradeFeeDetailList.add(sxfFee);
        trdTradeFeeDetailList.add(settleFee);
        trdTradeFeeDetailList.add(commissionAmt);
        return trdTradeFeeDetailList;
    }

    /**
     * 生成指令
     * @return 指令列表
     */
    protected List<StlPayOrdersDto> generateStlPayOrdersList(ExchangeTransMatchCommonDto dealDto,List<TrdProductDealDto> dealList) {
        List<StlPayOrdersDto> res = new ArrayList<>();
        dealList.forEach(deal -> {
            StlPayOrdersDto stlPayOrders = new StlPayOrdersDto();
            // 指令编号
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setSeqNo("9998");
            stlPayOrders.setOrderId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
            // 结算日期
            stlPayOrders.setSettleDate(deal.getSettleDate());
            //本方资金账户
            stlPayOrders.setMyAcctNo(dealDto.getAcctNo());
            // 收付方向
            if (StringUtil.equals(deal.getPs(), DictCodeList.交易方向_买入.getValue())) {
                stlPayOrders.setPs(DictCodeListExt1.收付方向_支出.getValue());
            } else {
                stlPayOrders.setPs(DictCodeList.收付方向_收入.getValue());
            }
            // 结算金额
            stlPayOrders.setSettleAmt(deal.getTradeAmt());
            // 币种
            stlPayOrders.setCcy(deal.getCcy());
            // 是否需要发送
            stlPayOrders.setIsNeedSend(DictCodeList.是否_否.getValue());
            // 是否汇总交易
            stlPayOrders.setIsTotalTrade(DictCodeList.是否_否.getValue());
            // 交易编号
            stlPayOrders.setTradeId(deal.getTradeId());
            // 指令状态
            stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
            // 投资组合代码
            stlPayOrders.setPortfolioId(deal.getPortfolioId());
            // 划款类型
            stlPayOrders.setPayType(DictCodeListExt1.平安划款类型_清算划款.getValue());
            res.add(stlPayOrders);

        });
        return res;
    }

    /**
     * 获取 随交易支付费用
     *
     * @param settleTransDataDto
     * @return
     */
    private BigDecimal getFeeAmtPay(ExchangeTransMatchCommonDto settleTransDataDto) {
        BigDecimal feeAmtPay = BigDecimal.ZERO;
        feeAmtPay = ArithmeticUtil.doAdd(settleTransDataDto.getJsfAmt(), settleTransDataDto.getGhfAmt(),
                settleTransDataDto.getZgfAmt(), settleTransDataDto.getSxfAmt(), settleTransDataDto.getSettleFee(), settleTransDataDto.getStampFee());
        if (StringUtil.equals(DictCodeList.是否_是.getValue(), settleTransDataDto.getIsPayWithTrade())) {
            feeAmtPay = ArithmeticUtil.doAdd(feeAmtPay, settleTransDataDto.getCommissionAmt());
        }
        return feeAmtPay;
    }
}
