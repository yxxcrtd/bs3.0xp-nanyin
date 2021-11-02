package com.joyintech.tams.invest.trade.exchangetransmatch.common.service.shares;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.TrdProductDealDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 股票（新股中签、增发(配售)、配股、买入、卖出）
 * @author meifaxin
 * @date 2021/11/1
 * @see ExchangeTransMatchShares1Service
 * @since 1.0
 */
@Service
public class ExchangeTransMatchShares1Service extends ExchangeTransMatchSharesCommonService {
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    public BaseTradeProductDealDto genFinProductTradeDto(ExchangeTransMatchCommonDto settleTransDataDto, ExchangeTransMatchCommonEntrustDto entrustDto){
        TrdProductDealDto trdProductDeal = new TrdProductDealDto();
        setSharesTrdProductDeal(trdProductDeal, settleTransDataDto);
        BaseTradeProductDealDto baseTradeProductDealDto=new BaseTradeProductDealDto();
        baseTradeProductDealDto.setTrdProductDeal(trdProductDeal);
        baseTradeProductDealDto.setStlPayOrdersList(generateStlPayOrdersList(Arrays.asList(trdProductDeal),settleTransDataDto));
        baseTradeProductDealDto.setPtlTrade(generatePtlTrade(Arrays.asList(trdProductDeal)));
        return null;
    }

    /**
     * 生成股票新股中签交易TRD_PRODUCT_DEAL的数据
     * @param trdProductDeal TrdProductDealDto
     * @param settleTransDataDto ExchangeTransMatchCommonDto
     */
    private void setSharesTrdProductDeal(TrdProductDealDto trdProductDeal, ExchangeTransMatchCommonDto settleTransDataDto) {
            // 随交易支付费用
            BigDecimal feeAmtPay = BigDecimal.ZERO;
            // 未随交易支付费用
            BigDecimal feeAmtUnpay = BigDecimal.ZERO;
            // ∑全价总额
            BigDecimal cpriceAmtSum = BigDecimal.ZERO;
                if (StringUtil.equals(DictCodeList.是否_是.getValue(), settleTransDataDto.getIsPayWithTrade())) {
                    feeAmtPay = ArithmeticUtil.doAdd(feeAmtPay, settleTransDataDto.getJsfAmt(), settleTransDataDto.getGhfAmt(), settleTransDataDto.getZgfAmt(),
                            settleTransDataDto.getSxfAmt(), settleTransDataDto.getSettleFee(), settleTransDataDto.getStampFee(), settleTransDataDto.getCommissionAmt());
                }
                else {
                    feeAmtPay = ArithmeticUtil.doAdd(feeAmtPay, settleTransDataDto.getJsfAmt(), settleTransDataDto.getGhfAmt(), settleTransDataDto.getZgfAmt(),
                            settleTransDataDto.getSxfAmt(), settleTransDataDto.getSettleFee(), settleTransDataDto.getStampFee());
                    feeAmtUnpay = ArithmeticUtil.doAdd(feeAmtUnpay, settleTransDataDto.getCommissionAmt());
                }
            // 交易编号
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setCommonBusinessType(DictCodeListExt2.共通业务类型_股票投资交易);
        trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
            // 金融产品代码
            trdProductDeal.setFinprodId(settleTransDataDto.getFinprodId());
            // 金融产品类型
            trdProductDeal.setFinprodType(settleTransDataDto.getFinprodType());
            // 金融产品类型（投管分类）
            trdProductDeal.setFinprodType2(settleTransDataDto.getFinprodType2());
            // 业务类型
            trdProductDeal.setBusiType(settleTransDataDto.getBusiType());
            // 分支序号
            trdProductDeal.setBranch(0L);
            // 交易日
            trdProductDeal.setTradeDate(settleTransDataDto.getTradeDate());
            // 交割日
            trdProductDeal.setSettleDate(settleTransDataDto.getSettleDate());
            // 清算速度
            trdProductDeal.setSettleSpeed(settleTransDataDto.getSettleSpeed());
            // 交易币种
            trdProductDeal.setCcy(settleTransDataDto.getCcy());
            // 净值日期
            trdProductDeal.setCpriceDate(settleTransDataDto.getTradeDate());
            // 单位净价
            BigDecimal unitCprice = ArithmeticUtil.divide(cpriceAmtSum, settleTransDataDto.getShareAmt(), 14);
            trdProductDeal.setUnitCprice(unitCprice);
            // 配股需要计算价格
            if (StringUtil.equals(settleTransDataDto.getPs(), DictCodeListExt2.交易方向_配股.getValue())){
                trdProductDeal.setUnitCprice(ArithmeticUtil.divide(settleTransDataDto.getTradeAmt(), settleTransDataDto.getShareAmt(), 14));
            }
            // 净价总额
            trdProductDeal.setCpriceAmt(settleTransDataDto.getFpriceAmt());
            // 交易份额
            trdProductDeal.setShareAmt(settleTransDataDto.getShareAmt());
            // 交易本金
            trdProductDeal.setPrinAmt(settleTransDataDto.getPrinAmt());
            // 随交易支付费用
            trdProductDeal.setFeeAmtPay(feeAmtPay);
            // 交易金额
            trdProductDeal.setTradeAmt(settleTransDataDto.getTradeAmt());
            // 未随交易支付费用
            trdProductDeal.setFeeAmtUnpay(feeAmtUnpay);
            // 总交易费用
            trdProductDeal.setFeeAmt(ArithmeticUtil.doAdd(feeAmtPay, feeAmtUnpay));
            // 交易方向
            trdProductDeal.setPs(settleTransDataDto.getPs());
            // 交易状态
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            // 是否已撤销
            trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
            // 是否外部交易
            trdProductDeal.setIsOutTrade(DictCodeList.是否_是.getValue());
            // 交易场所
            trdProductDeal.setTradeMarket(settleTransDataDto.getTradeMarket());
            // 交易平台
            trdProductDeal.setTradePlat(settleTransDataDto.getTradePlat());
            // 交易市场
            trdProductDeal.setTradeMarketMode(settleTransDataDto.getTradeMarketMode());
            // 证券管理户代码
            trdProductDeal.setSecManageAcctId(settleTransDataDto.getSecAcctId());
            // 登记托管机构
            trdProductDeal.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
            // 投管业务类型
            trdProductDeal.setTgBusiType(DictCodeListExt2.平安业务类型_股票交易.getValue());
            // 交收方式
            trdProductDeal.setSettlemenType(settleTransDataDto.getSettleMode());
            // 席位号
            trdProductDeal.setSeatId(settleTransDataDto.getSeatNumber());
            // 投资组合代码
            trdProductDeal.setPortfolioId(settleTransDataDto.getPortfolioId());
    }

    protected List<PtlTrade> generatePtlTrade(List<TrdProductDealDto> dealList) {
        return dealList.stream().map(trdProductDealDto -> {
            PtlTrade ptlTrade = new PtlTrade();
            // 组合代码
            ptlTrade.setPortfolioId(trdProductDealDto.getPortfolioId());
            // 业务编号
            ptlTrade.setBusiNo(trdProductDealDto.getTradeId());
            // 业务表
            ptlTrade.setBusiTable(DictCodeListStl.业务表_金融产品交易表.getValue());
            // 业务明细代码
            ptlTrade.setBusiId(trdProductDealDto.getFinprodId());
            // 投资目的
            ptlTrade.setInvAim(trdProductDealDto.getInvAim());
            // 证券托管户代码
            ptlTrade.setSecManageAcctId(trdProductDealDto.getSecManageAcctId());
            // 实际发生日
            ptlTrade.setSettleDate(trdProductDealDto.getSettleDate());
            // 业务类型
            ptlTrade.setBusiType(DictCodeListExt2.共通业务类型_股票投资交易.getValue());
            return ptlTrade;
        }).collect(Collectors.toList());
    }



    protected List<StlPayOrdersDto> generateStlPayOrdersList(List<TrdProductDealDto> dealList, ExchangeTransMatchCommonDto settleTransDataDto) {
        return dealList.stream().map(trdProductDealDto -> {
            StlPayOrdersDto stlPayOrders = new StlPayOrdersDto();
            // 指令编号
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setSeqNo("9998");
            stlPayOrders.setOrderId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));            // 结算日期
            stlPayOrders.setSettleDate(trdProductDealDto.getSettleDate());
            // 本方资金账户
//            stlPayOrders.setMyAcctNo(trdProductDealDto.getAcctNo());
            // 收付方向
            if (StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), trdProductDealDto.getPs())) {
                stlPayOrders.setPs(DictCodeList.收付方向_收入.getValue());
            }
            else {
                stlPayOrders.setPs(DictCodeListExt1.收付方向_支出.getValue());
            }
            // 结算金额
            stlPayOrders.setSettleAmt(trdProductDealDto.getTradeAmt());
            // 币种
            stlPayOrders.setCcy(trdProductDealDto.getCcy());
            // 是否需要发送
            stlPayOrders.setIsNeedSend(DictCodeList.是否_否.getValue());
            // 是否汇总交易
            stlPayOrders.setIsTotalTrade(DictCodeList.是否_否.getValue());
            // 交易编号
            stlPayOrders.setTradeId(trdProductDealDto.getTradeId());
            // 指令状态
            stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
            // 投资组合代码
            stlPayOrders.setPortfolioId(trdProductDealDto.getPortfolioId());
            // 划款类型
            stlPayOrders.setPayType(DictCodeListExt1.平安划款类型_清算划款.getValue());
            return stlPayOrders;
        }).collect(Collectors.toList());
    }
}
