package com.joyintech.tams.invest.trade.exchangetransmatch.common.service.shares;

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
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public BaseTradeProductDealDto genFinProductTradeDto(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto){
        BaseTradeProductDealDto baseTradeProductDealDto=new BaseTradeProductDealDto();
        //生成金融产品交易信息
        baseTradeProductDealDto.setTrdProductDeal(this.genTrdProductDeal(dealDto,entrustDto));
        //生成金融产品交易信息附属表
        baseTradeProductDealDto.setTrdProductDealAdd(this.genTrdProductDealAdd(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
        //生成组合交易表数据
        baseTradeProductDealDto.setPtlTrade(this.generatePtlTrade(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
        //生成费用信息list
        baseTradeProductDealDto.setTrdTradeFeeDetailList(this.genTrdTradeFeeDetailList(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
        //生成划款指令
        baseTradeProductDealDto.setStlPayOrdersList(this.generateStlPayOrdersList(dealDto,entrustDto,baseTradeProductDealDto.getTrdProductDeal()));
        return baseTradeProductDealDto;
    }

    /**
     * 生成金融产品交易明细
     */

    public TrdProductDeal genTrdProductDeal(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto) {
        return createDeal(dealDto,entrustDto);
    }

    /**
     * 生成股票新股中签交易TRD_PRODUCT_DEAL的数据
     * @param dealDto ExchangeTransMatchCommonDto
     * @param entrustDto ExchangeTransMatchCommonEntrustDto
     */
    private TrdProductDeal createDeal( ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        // 随交易支付费用
            BigDecimal feeAmtPay = BigDecimal.ZERO;
            // 未随交易支付费用
            BigDecimal feeAmtUnpay = BigDecimal.ZERO;
            // ∑全价总额
            BigDecimal cpriceAmtSum = BigDecimal.ZERO;
                if (StringUtil.equals(DictCodeList.是否_是.getValue(), dealDto.getIsPayWithTrade())) {
                    feeAmtPay = ArithmeticUtil.doAdd(feeAmtPay, dealDto.getJsfAmt(), dealDto.getGhfAmt(), dealDto.getZgfAmt(),
                            dealDto.getSxfAmt(), dealDto.getSettleFee(), dealDto.getStampFee(), dealDto.getCommissionAmt());
                }
                else {
                    feeAmtPay = ArithmeticUtil.doAdd(feeAmtPay, dealDto.getJsfAmt(), dealDto.getGhfAmt(), dealDto.getZgfAmt(),
                            dealDto.getSxfAmt(), dealDto.getSettleFee(), dealDto.getStampFee());
                    feeAmtUnpay = ArithmeticUtil.doAdd(feeAmtUnpay, dealDto.getCommissionAmt());
                }
            // 交易编号
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setCommonBusinessType(DictCodeListExt2.共通业务类型_股票投资交易);
        trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
            // 金融产品代码
            trdProductDeal.setFinprodId(dealDto.getFinprodId());
            // 金融产品类型
            trdProductDeal.setFinprodType(dealDto.getFinprodType());
            // 金融产品类型（投管分类）
            trdProductDeal.setFinprodType2(dealDto.getFinprodType2());
            // 业务类型
            trdProductDeal.setBusiType(dealDto.getBusiType());
            // 分支序号
            trdProductDeal.setBranch(0L);
            // 交易日
            trdProductDeal.setTradeDate(dealDto.getTradeDate());
            // 交割日
            trdProductDeal.setSettleDate(dealDto.getSettleDate());
            // 清算速度
            trdProductDeal.setSettleSpeed(dealDto.getSettleSpeed());
            // 交易币种
            trdProductDeal.setCcy(dealDto.getCcy());
            // 净值日期
            trdProductDeal.setCpriceDate(dealDto.getTradeDate());
            // 单位净价
//            BigDecimal unitCprice = ArithmeticUtil.divide(cpriceAmtSum, dealDto.getShareAmt(), 14);
            trdProductDeal.setUnitCprice(dealDto.getUnitCprice());
//            // 配股需要计算价格
//            if (StringUtil.equals(dealDto.getPs(), DictCodeListExt2.交易方向_配股.getValue())){
//                trdProductDeal.setUnitCprice(ArithmeticUtil.divide(dealDto.getTradeAmt(), dealDto.getShareAmt(), 14));
//            }
            // 净价总额
            trdProductDeal.setCpriceAmt(dealDto.getFpriceAmt());
            // 交易份额
            trdProductDeal.setShareAmt(entrustDto.getMarchQty());
            // 交易本金
            trdProductDeal.setPrinAmt(entrustDto.getMarchAmt());
            // 随交易支付费用
            trdProductDeal.setFeeAmtPay(entrustDto.getMarchFeeAmtPay());
            // 交易金额
            trdProductDeal.setTradeAmt(ArithmeticUtil.subtract(entrustDto.getMarchAmt(),entrustDto.getMarchFeeAmtPay()));
            // 未随交易支付费用
            trdProductDeal.setFeeAmtUnpay(entrustDto.getMarchFeeAmtUnPay());
            // 总交易费用
            trdProductDeal.setFeeAmt(entrustDto.getMarchFeeAmtUnPay());
            // 交易方向
            trdProductDeal.setPs(dealDto.getPs());
            // 交易状态
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            // 是否已撤销
            trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
            // 是否外部交易
            trdProductDeal.setIsOutTrade(DictCodeList.是否_是.getValue());
            // 交易场所
            trdProductDeal.setTradeMarket(dealDto.getTradeMarket());
            // 交易平台
            trdProductDeal.setTradePlat(dealDto.getTradePlat());
            // 交易市场
            trdProductDeal.setTradeMarketMode(dealDto.getTradeMarketMode());

            if(StringUtil.equals(dealDto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
                trdProductDeal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
            }
            else if(StringUtil.equals(dealDto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
                trdProductDeal.setCounterId(BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
            }
            // 证券管理户代码
            trdProductDeal.setSecManageAcctId(dealDto.getSecAcctId());
            // 登记托管机构
            trdProductDeal.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
            // 投管业务类型
            trdProductDeal.setTgBusiType(DictCodeListExt2.平安业务类型_股票交易.getValue());
            // 交收方式
            trdProductDeal.setSettlemenType(dealDto.getSettleMode());
            // 席位号
            trdProductDeal.setSeatId(dealDto.getSeatNumber());
            // 投资组合代码
            trdProductDeal.setPortfolioId(entrustDto.getPortfolioId());
            return trdProductDeal;
    }

    /**
     * 金融产品交易附属表
     */
    public TrdProductDealAdd genTrdProductDealAdd(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto,TrdProductDeal trdProductDeal) {
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

    public List<PtlTrade> generatePtlTrade(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDealDto) {
        List<PtlTrade> ptlTradeList = new ArrayList<>();
        PtlTrade ptlTrade = new PtlTrade();
        // 组合代码
        ptlTrade.setPortfolioId(entrustDto.getPortfolioId());
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
        ptlTradeList.add(ptlTrade) ;
        return ptlTradeList;
    }

    public List<TrdTradeFeeDetail> genTrdTradeFeeDetailList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        List<TrdTradeFeeDetail> result = new ArrayList<TrdTradeFeeDetail>();
        // 经手费
        if (dealDto.getJsfAmt() != null && dealDto.getJsfAmt().compareTo(new BigDecimal(0)) > 0) {
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            // 交易编号
            trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
            // 费用类型
            trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_经手费.getValue());
            // 是否随交易支付
            trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            // 费用金额
            trdTradeFeeDetail.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getJsfAmt()));
            // 登记托管机构
             trdTradeFeeDetail.setRegistOrg(trdProductDeal.getRegistOrg());
            result.add(trdTradeFeeDetail);
        }
        // 过户费
        if (dealDto.getGhfAmt() != null && dealDto.getGhfAmt().compareTo(new BigDecimal(0)) > 0) {
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            // 交易编号
            trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
            // 费用类型
            trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_过户费.getValue());
            // 是否随交易支付
            trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            // 费用金额
            trdTradeFeeDetail.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getGhfAmt()));
            // 登记托管机构
             trdTradeFeeDetail.setRegistOrg(trdProductDeal.getRegistOrg());
            result.add(trdTradeFeeDetail);
        }
        // 证管费
        if (dealDto.getZgfAmt() != null && dealDto.getZgfAmt().compareTo(new BigDecimal(0)) > 0) {
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            // 交易编号
            trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
            // 费用类型
            trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_证管费.getValue());
            // 是否随交易支付
            trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            // 费用金额
            trdTradeFeeDetail.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getZgfAmt()));
            // 登记托管机构
            trdTradeFeeDetail.setRegistOrg(trdProductDeal.getRegistOrg());
            result.add(trdTradeFeeDetail);
        }
        // 手续费
        if (dealDto.getSxfAmt() != null && dealDto.getSxfAmt().compareTo(new BigDecimal(0)) > 0) {
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            // 交易编号
            trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
            // 费用类型
            trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_交易手续费.getValue());
            // 是否随交易支付
            trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            // 费用金额
            trdTradeFeeDetail.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSxfAmt()));
            // 登记托管机构
             trdTradeFeeDetail.setRegistOrg(trdProductDeal.getRegistOrg());
            result.add(trdTradeFeeDetail);
        }
        // 结算费
        if (dealDto.getSettleFee() != null && dealDto.getSettleFee().compareTo(new BigDecimal(0)) > 0) {
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            // 交易编号
            trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
            // 费用类型
            trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_结算费.getValue());
            // 是否随交易支付
            trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            // 费用金额
            trdTradeFeeDetail.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getSettleFee()));
            // 登记托管机构
            trdTradeFeeDetail.setRegistOrg(trdProductDeal.getRegistOrg());
            result.add(trdTradeFeeDetail);
        }
        // 印花税
        if (dealDto.getStampFee() != null && dealDto.getStampFee().compareTo(new BigDecimal(0)) > 0) {
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            // 交易编号
            trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
            // 费用类型
            trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_印花税.getValue());
            // 是否随交易支付
            trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            // 费用金额
            trdTradeFeeDetail.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getStampFee()));
            // 登记托管机构
            trdTradeFeeDetail.setRegistOrg(trdProductDeal.getRegistOrg());
            result.add(trdTradeFeeDetail);
        }
        // 佣金
        if (dealDto.getCommissionAmt() != null && dealDto.getCommissionAmt().compareTo(new BigDecimal(0)) > 0) {
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            // 交易编号
            trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
            // 费用类型
            trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_佣金.getValue());
            // 是否随交易支付
            trdTradeFeeDetail.setIsPayWithTrade(dealDto.getIsPayWithTrade());
            // 费用金额
            trdTradeFeeDetail.setFeeAmt(ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustDto.getMarchQty(),dealDto.getRemainNum(),4),dealDto.getCommissionAmt()));
            // 登记托管机构
            trdTradeFeeDetail.setRegistOrg(trdProductDeal.getRegistOrg());

            result.add(trdTradeFeeDetail);
        }
        return result;
    }



    public List<StlPayOrdersDto> generateStlPayOrdersList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
        List<StlPayOrdersDto> stlPayOrdersList = new ArrayList<>();
        StlPayOrdersDto stlPayOsrdersDto = new StlPayOrdersDto();
            // 指令编号
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setSeqNo("9998");
            stlPayOsrdersDto.setOrderId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));            // 结算日期
            stlPayOsrdersDto.setSettleDate(trdProductDeal.getSettleDate());
            // 本方资金账户
            stlPayOsrdersDto.setMyAcctNo(dealDto.getAcctNo());
            // 收付方向
            if (StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), trdProductDeal.getPs())) {
                stlPayOsrdersDto.setPs(DictCodeList.收付方向_收入.getValue());
            }
            else {
                stlPayOsrdersDto.setPs(DictCodeListExt1.收付方向_支出.getValue());
            }
            // 结算金额
            stlPayOsrdersDto.setSettleAmt(trdProductDeal.getTradeAmt());
            // 币种
            stlPayOsrdersDto.setCcy(trdProductDeal.getCcy());
            // 是否需要发送
            stlPayOsrdersDto.setIsNeedSend(DictCodeList.是否_否.getValue());
            // 是否汇总交易
            stlPayOsrdersDto.setIsTotalTrade(DictCodeList.是否_否.getValue());
            // 交易编号
            stlPayOsrdersDto.setTradeId(trdProductDeal.getTradeId());
            // 指令状态
            stlPayOsrdersDto.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
            // 投资组合代码
            stlPayOsrdersDto.setPortfolioId(entrustDto.getPortfolioId());
            // 划款类型
            stlPayOsrdersDto.setPayType(DictCodeListExt1.平安划款类型_清算划款.getValue());
        stlPayOrdersList.add(stlPayOsrdersDto);

        return stlPayOrdersList;
    }
}
