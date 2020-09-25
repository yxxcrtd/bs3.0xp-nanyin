/*
 * 文件名：AutoPeriodRepoCommonService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：etliu 修改时间：2018年12月17日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;

/**
 * 回购存续期共通Service 批处理自动存续期和存续期画面共用逻辑
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see AutoPeriodRepoCommonService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class AutoPeriodRepoCommonService extends BaseService {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    public static WflTrdFinProductDealTotal gen汇总流程表(TrdRepoDurationDto entity, String bizType) {
        WflTrdFinProductDealTotal trdFinProductDealTotal = new WflTrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getBusiCode());
        trdFinProductDealTotal.setBusiType(bizType);
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        // trdProductDeal.setFinprodType(); TODO 估值核算 金融产品类型
        trdFinProductDealTotal.setFinprodType2(entity.getFinprodType2());
        trdFinProductDealTotal.setPortfolioId(entity.getPortfolioId());
        trdFinProductDealTotal.setCcy(entity.getCcy());
        trdFinProductDealTotal.setPs(entity.getPs()==null ? DictCodeList.交易方向_到期.getValue() : entity.getPs());
        trdFinProductDealTotal.setSecManageAcctId(entity.getSecManageAcctId());
        trdFinProductDealTotal.setBranch(
            AutoPeriodCommonService.changePsToBranch(entity.getmDate(), DictCodeList.交易方向_到期.getValue()));
        trdFinProductDealTotal.setTradeDate(DateTime.parse(entity.getSettleDate()).toDate());
        trdFinProductDealTotal.setVdate(DateTime.parse(entity.getvDate()).toDate());
//        终止日存在使用终止日
        if(StringUtil.isNotEmpty(entity.getDeadDate())){
            trdFinProductDealTotal.setMdate(DateTime.parse(entity.getDeadDate()).toDate());
        }
        else{
            trdFinProductDealTotal.setMdate(DateTime.parse(entity.getmDate()).toDate());
        }
        trdFinProductDealTotal.setSettleDate(DateTime.parse(entity.getSettleDate()).toDate());
        trdFinProductDealTotal.setShareAmt(entity.getTheoryPrin());
        //实际利息 = 到期结算金额-理论本金
        trdFinProductDealTotal.setIntAmt(ArithmeticUtil.subtract(entity.getFpriceAmt(), entity.getTheoryPrin()));
//        利息总额
        trdFinProductDealTotal.setTradeAmt(entity.getTradeAmt());
        trdFinProductDealTotal.setTradeMarket(entity.getTradeMarket());
        trdFinProductDealTotal.setCounterId(entity.getCounterId());
        trdFinProductDealTotal.setTrader(entity.getTrader());
        trdFinProductDealTotal.setCounterTrader(entity.getCounterTrader());
        trdFinProductDealTotal.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());

        //到期结算金额
        trdFinProductDealTotal.setFpriceAmt(entity.getFpriceAmt());
        //通道费
        if(StringUtil.isNotEmpty(entity.getChannelFee())){
        	trdFinProductDealTotal.setFeeAmt(entity.getChannelFee());
        	if(StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())){
        		trdFinProductDealTotal.setFeeAmtPay(entity.getChannelFee());
        	}else{
        		trdFinProductDealTotal.setFeeAmtUnpay(entity.getChannelFee());
        	}
        }
        // 通道交易新增 start
        trdFinProductDealTotal.setChlId(entity.getChlId());
        //期待值：要加上罚息金额
        if(entity.getPenaltyIntamt()!= null){
            trdFinProductDealTotal.setIntAmt(ArithmeticUtil.doAdd(entity.getPenaltyIntamt(),trdFinProductDealTotal.getIntAmt()));
        }
        if(entity.getActTradeFee()!= null){
            trdFinProductDealTotal.setFeeAmt(ArithmeticUtil.doAdd(trdFinProductDealTotal.getFeeAmt()==null?BigDecimal.ZERO:trdFinProductDealTotal.getFeeAmt(),entity.getActTradeFee()));
            trdFinProductDealTotal.setFeeAmtUnpay(
                    ArithmeticUtil.doAdd(trdFinProductDealTotal.getFeeAmtUnpay()==null?BigDecimal.ZERO:trdFinProductDealTotal.getFeeAmtUnpay(),entity.getActTradeFee()));
        }
        trdFinProductDealTotal.setRemark(entity.getRemark());
        // 通道交易新增 end
        return trdFinProductDealTotal;
    }

    public static WflPaTrdFinDealTotal gen汇总扩展业务流程(TrdRepoDurationDto entity, WflTrdFinProductDealTotal trdFinProductDealTotal) {
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = new WflPaTrdFinDealTotal();
        wflPaTrdFinDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        if (DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(entity.getFinprodType2())) {
            wflPaTrdFinDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_正回购到期.getValue());
        } else if (DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue().equals(entity.getFinprodType2())) {
            wflPaTrdFinDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_逆回购到期.getValue());
        } else  if (DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue().equals(entity.getFinprodType2())) {
            wflPaTrdFinDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_买断式正回购到期.getValue());
        } else {
            wflPaTrdFinDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_买断式逆回购到期.getValue());
        }
        wflPaTrdFinDealTotal.setRate(ArithmeticUtil.divide(entity.getIntRate(), 100, 5));
        wflPaTrdFinDealTotal.setTheoryAmt(entity.getTheoryAmt());
        wflPaTrdFinDealTotal.setTheoryInt(entity.getTheoryInt());
        wflPaTrdFinDealTotal.setTheoryPrin(entity.getTheoryPrin());
        wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
        wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
        return wflPaTrdFinDealTotal;
    }

    public List<WflTrdProductDeal> gen交易业务流程(TrdRepoDurationDto entity) {
        List<FinCashPlan> 现金流 = restTemplate.postForList(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/findFinCashInfo", entity.getFinprodId(),
            FinCashPlan.class);
        List<WflTrdProductDeal> deals = new ArrayList<>();
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        现金流.forEach(finCashPlan -> {
            WflTrdProductDeal deal = new WflTrdProductDeal();
            deal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
            if (DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(entity.getFinprodType2())
                || DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue().equals(entity.getFinprodType2())) {
                deal.setBusiType(DictCodeList.共通业务类型_质押式回购存续期.getValue());
            }
            else {
                deal.setBusiType(DictCodeList.共通业务类型_买断式回购存续期.getValue());
            }
            deal.setCounterId(entity.getCounterId());
            deal.setTrader(entity.getTrader());
            deal.setCounterTrader(entity.getCounterTrader());
            deal.setFinprodId(entity.getFinprodId());
            deal.setFinprodType2(entity.getFinprodType2());
            // deal.setFinprodType(); TODO 估值核算 金融产品类型
            deal.setSecManageAcctId(entity.getSecManageAcctId());
            deal.setCcy(entity.getCcy());
            deal.setTradeDate(DateTime.parse(entity.getSettleDate()).toDate());
            //存入到期交付日
            if(StringUtil.isNotEmpty(entity.getMpayDate())){
            	deal.setPayDate(DateTime.parse(entity.getMpayDate()).toDate());
            }
            deal.setVdate(DateTime.parse(entity.getvDate()).toDate());
            if(StringUtil.equals(DictCodeListExt3.交易方向_投资端_提前终止.getValue(),entity.getPs())){
                deal.setMdate(DateTime.parse(entity.getSettleDate()).toDate());
            }
            else{
                deal.setMdate(DateTime.parse(entity.getmDate()).toDate());
            }
            deal.setSettleDate(DateTime.parse(entity.getSettleDate()).toDate());
            deal.setShareAmt(entity.getTheoryPrin());
            deal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            deal.setIsCancel(DictCodeList.是否_否.getValue());
            deal.setTradeMarket(entity.getTradeMarket());

            deal.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
            deal.setWorkflowId(entity.getWorkflowId());
            deal.setTotleTradeId(entity.getBusiCode());
            deal.setCashId(finCashPlan.getCashId());
            deal.setCancelRemark(entity.getRevokeReason());
            if (DictCodeList.现金流类型_到期还本.getValue().equals(finCashPlan.getCashType())) {
                deal.setPs(DictCodeList.交易方向_还本到账.getValue());
                deal.setStatus(DictCodeList.交易方向_到期.getValue());
                deal.setBranch(AutoPeriodCommonService.changePsToBranch(entity.getmDate(), deal.getPs()));
                deal.setPs(DictCodeList.交易方向_还本到账.getValue());
                deal.setTradeAmt(entity.getTheoryPrin());
                deal.setPrinAmt(entity.getTheoryPrin());
                deal.setFpriceAmt(entity.getTheoryPrin());
                if(StringUtil.equals(DictCodeList.共通业务类型_买断式回购存续期.getValue(),deal.getBusiType())){
                    Map<String,Object> map =new HashMap<>();
                    map.put("portfolioId",entity.getPortfolioId());
                    map.put("cdate",entity.getSettleDate());
                    map.put("finprodId",entity.getFinprodId());
                    map.put("invAim",deal.getInvAim());
                    map.put("secAcctId",deal.getSecManageAcctId());
                    map.put("ccy",deal.getSecManageAcctId());
                    BigDecimal prinAmtVal = autoPeriodCommonService.genPrinAmtByVal(map);
                    if(prinAmtVal != null){
                        deal.setTradeAmt(prinAmtVal);
                        deal.setPrinAmt(prinAmtVal);
                        deal.setFpriceAmt(prinAmtVal);
                    }
                }
                deal.setIntAmt(BigDecimal.ZERO);
//                if(StringUtil.equals(DictCodeListExt3.交易方向_投资端_逾期返售.getValue(),entity.getPs())||
//                        StringUtil.equals(DictCodeListExt3.交易方向_投资端_提前终止.getValue(),entity.getPs())){
//                    deal.setFpriceAmt(entity.getTheoryPrin());
//                }
            }
            else if (DictCodeList.现金流类型_到期收息.getValue().equals(finCashPlan.getCashType())) {
                deal.setStatus(DictCodeList.交易方向_收息.getValue());
                deal.setPs(DictCodeList.交易方向_收息.getValue());
                deal.setBranch(AutoPeriodCommonService.changePsToBranch(entity.getmDate(), deal.getPs()));
                deal.setPs(DictCodeList.交易方向_收息到账.getValue());
//                deal.setIntAmt(ArithmeticUtil.subtract(entity.getTradeAmt(), entity.getTheoryPrin()));
//                利息金额 到期结算金额-本金
                BigDecimal intAmt = ArithmeticUtil.subtract(entity.getFpriceAmt(), entity.getTheoryPrin());
                deal.setIntAmt(intAmt);
                deal.setFpriceAmt(intAmt);
                deal.setPrinAmt(BigDecimal.ZERO);
//                if(StringUtil.equals(DictCodeListExt3.交易方向_投资端_逾期返售.getValue(),entity.getPs())){
//                    deal.setIntAmt(ArithmeticUtil.subtract(entity.getFpriceAmt(), entity.getTheoryPrin()));
//                    if(entity.getPenaltyIntamt()!=null){
//                        deal.setIntAmt(ArithmeticUtil.doAdd(entity.getPenaltyIntamt(),deal.getIntAmt()));
//                    }
//                    deal.setFpriceAmt(entity.getFpriceAmt());
//                }
//                deal.setTradeAmt(deal.getIntAmt());
                deal.setTradeAmt(ArithmeticUtil.subtract(entity.getTradeAmt(),entity.getTheoryPrin()));
                if(StringUtil.isNotEmpty(entity.getChannelFee())){
//                	deal.setTradeAmt(ArithmeticUtil.subtract(deal.getTradeAmt(), entity.getChannelFee()));
                	//费用字段入库
                	deal.setFeeAmt(entity.getChannelFee());
                	if(StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())){
                		deal.setFeeAmtPay(entity.getChannelFee());
                	}else{
                		deal.setFeeAmtUnpay(entity.getChannelFee());
                	}
                }

                //期待值：要加上罚息金额
                if(entity.getPenaltyIntamt()!= null){
                    deal.setIntAmt(ArithmeticUtil.doAdd(entity.getPenaltyIntamt(),deal.getIntAmt()));
                    deal.setFpriceAmt(deal.getIntAmt());
                }
                if(entity.getActTradeFee()!= null){
                    deal.setFeeAmt(ArithmeticUtil.doAdd(deal.getFeeAmt()==null?BigDecimal.ZERO:deal.getFeeAmt(),entity.getActTradeFee()));
                    deal.setFeeAmtUnpay(
                            ArithmeticUtil.doAdd(deal.getFeeAmtUnpay()==null?BigDecimal.ZERO:deal.getFeeAmtUnpay(),entity.getActTradeFee()));
                }
                if(StringUtil.equals(DictCodeList.交易方向_到期续做.getValue(),entity.getPs())){
                    deal.setIntAmt(ArithmeticUtil.subtract(entity.getFpriceAmt(), entity.getTheoryPrinex()));
                }
            }

            // 通道交易新增 start
            deal.setChlId(entity.getChlId());
            // 通道交易新增 end
            deal.setTradeMarketMode(DictCodeListExt1.交易市场_二级市场.getValue());
            deals.add(deal);
        });

        return deals;
    }

    /**
     * 为调用共通组件组装的entity
     *
     * @param entity
     * @return
     */
    public static TrdProductDeal getTrdProductDeal(TrdRepoDurationDto entity) {
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            if(entity!=null) {
            trdProductDeal.setTradeId(entity.getBusiCode());
            if (DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(entity.getFinprodType2())
                    || DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue().equals(entity.getFinprodType2())) {
                trdProductDeal.setBusiType(DictCodeList.共通业务类型_质押式回购存续期.getValue());
            } else {
                trdProductDeal.setBusiType(DictCodeList.共通业务类型_买断式回购存续期.getValue());
            }
            trdProductDeal.setFinprodId(entity.getFinprodId());
            // trdProductDeal.setFinprodType(); TODO 估值核算 金融产品类型
            trdProductDeal.setFinprodType2(entity.getFinprodType2());
            trdProductDeal.setCcy(entity.getCcy());
            if (!StringUtil.equals(entity.getPs(), DictCodeListExt3.交易方向_投资端_逾期返售.getValue())) {
                trdProductDeal.setPs(DictCodeList.交易方向_到期.getValue());
            } else {
                trdProductDeal.setPs(DictCodeListExt3.交易方向_投资端_逾期返售.getValue());
            }
            trdProductDeal.setTradeDate(DateTime.parse(entity.getSettleDate()).toDate());
            trdProductDeal.setSettleDate(DateTime.parse(entity.getSettleDate()).toDate());
            if (StringUtil.equals(DictCodeListExt3.交易方向_投资端_提前终止.getValue(), entity.getPs())) {
                trdProductDeal.setMdate(DateUtil.parse(entity.getDeadDate()));
                trdProductDeal.setTradeDate(DateTime.parse(entity.getDeadDate()).toDate());
                trdProductDeal.setSettleDate(DateTime.parse(entity.getDeadDate()).toDate());
            } else if (StringUtil.equals(DictCodeListExt3.交易方向_投资端_逾期返售.getValue(), entity.getPs())) {
                trdProductDeal.setMdate(DateUtil.parse(entity.getSettleDate()));
            } else {
                trdProductDeal.setMdate(DateUtil.parse(entity.getmDate()));
            }
            trdProductDeal.setVdate(DateTime.parse(entity.getvDate()).toDate());
            trdProductDeal.setShareAmt(entity.getTheoryPrin());
//        修改为交易本金 20191127 start
            trdProductDeal.setPrinAmt(entity.getTheoryPrin());
//        修改为交易本金 20191127 end
            trdProductDeal.setIntAmt(ArithmeticUtil.subtract(entity.getTheoryAmt(), entity.getTheoryPrin()));
            trdProductDeal.setTradeAmt(entity.getTradeAmt());
            trdProductDeal.setTradeMarket(entity.getTradeMarket());
            trdProductDeal.setCounterId(entity.getCounterId());
            trdProductDeal.setTrader(entity.getTrader());
            trdProductDeal.setCounterTrader(entity.getCounterTrader());
            trdProductDeal.setSecManageAcctId(entity.getSecManageAcctId());
            trdProductDeal.setChlId(entity.getChlId());
            if (StringUtil.isNotEmpty(entity.getChannelFee())) {
//        	trdProductDeal.setTradeAmt(ArithmeticUtil.subtract(trdProductDeal.getTradeAmt(), entity.getChannelFee()));
                //费用字段入库
                trdProductDeal.setFeeAmt(entity.getChannelFee());
                if (StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())) {
                    trdProductDeal.setFeeAmtPay(entity.getChannelFee());
                } else {
                    trdProductDeal.setFeeAmtUnpay(entity.getChannelFee());
                }
            }
            }
            return trdProductDeal;

    }

    /**
     * 为调用共通组件组装的entity
     *到期续做构建首期的交易
     * @param entity
     * @return
     */
    public static TrdProductDeal get首期TrdProductDeal(TrdRepoDurationDto entity) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(entity.getBusiCode());
        if (DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(entity.getFinprodType2())
                || DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue().equals(entity.getFinprodType2())) {
            trdProductDeal.setBusiType(DictCodeList.共通业务类型_质押式回购交易.getValue());
        }
        else {
            trdProductDeal.setBusiType(DictCodeList.共通业务类型_买断式回购交易.getValue());
        }
        trdProductDeal.setFinprodId(entity.getFinprodId());
        // trdProductDeal.setFinprodType(); TODO 估值核算 金融产品类型
        trdProductDeal.setFinprodType2(entity.getFinprodType2());
        trdProductDeal.setCcy(entity.getCcy());
        trdProductDeal.setPs(DictCodeList.交易方向_买入.getValue());
        trdProductDeal.setTradeDate(DateTime.parse(entity.getTradeDate()).toDate());
        trdProductDeal.setVdate(DateTime.parse(entity.getmDate()).toDate());
        trdProductDeal.setMdate(DateTime.parse(entity.getmDatex()).toDate());
        trdProductDeal.setSettleDate(DateTime.parse(entity.getSettleDate()).toDate());
        trdProductDeal.setShareAmt(entity.getTheoryPrinex());
        trdProductDeal.setPrinAmt(entity.getTheoryPrinex());
        trdProductDeal.setIntAmt(entity.getTheoryIntex());
        trdProductDeal.setTradeAmt(entity.getSetAmt());
        trdProductDeal.setFpriceAmt(entity.getTradeAmtex());//到期结算
        trdProductDeal.setTradeMarket(entity.getTradeMarket());
        trdProductDeal.setCounterId(entity.getCounterIdex());
        trdProductDeal.setTrader(entity.getTraderx());
        trdProductDeal.setCounterTrader(entity.getCounterTraderx());
        trdProductDeal.setSecManageAcctId(entity.getSecManageAcctId());
        trdProductDeal.setCounterContact(entity.getCounterContact());
        trdProductDeal.setChlId(entity.getChlId());
        if(StringUtil.isNotEmpty(entity.getChannelFee())){
//        	trdProductDeal.setTradeAmt(ArithmeticUtil.subtract(trdProductDeal.getTradeAmt(), entity.getChannelFee()));
            //费用字段入库
            trdProductDeal.setFeeAmt(entity.getChannelFee());
            if(StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())){
                trdProductDeal.setFeeAmtPay(entity.getChannelFee());
            }else{
                trdProductDeal.setFeeAmtUnpay(entity.getChannelFee());
            }
        }


        return trdProductDeal;
    }
}
