/*
 * 文件名：AutoPeriodProjectCommonService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：etliu 修改时间：2018年12月17日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.BasePtlRecePositionService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrade;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdprojduration.TrdProjectDurationDto;

/**
 * 项目类资产存续期共通Service 批处理自动存续期和存续期画面共用逻辑
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see AutoPeriodProjectCommonService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class AutoPeriodProjectCommonService extends BaseService {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    @Autowired
    private BasePtlRecePositionService basePtlRecePositionService;

    /**
     * 以组合维度生成dto，就是以利息明细维度组装新的dto，作为属性保存到流程entity中<br/>
     * 汇总 <br/>
     * 汇总扩展 <br/>
     * 汇总费用明细 <br/>
     * List<Dto> | 交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 附属 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 扩展 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 费用明细 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 组合交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| List<划款指令>
     */
    public BaseTotalTradeInfoDto genFinProductTradeDto(TrdProjectDurationDto entity) {
        BaseTotalTradeInfoDto result = new BaseTotalTradeInfoDto();
        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        TrdFinProductDealTotal trdFinProductDealTotal = gen汇总流程表(entity);
        result.setTrdFinProductDealTotal(trdFinProductDealTotal);

        // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = gen汇总扩展业务流程(entity, trdFinProductDealTotal);
        result.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);

        TrdTradeFeeDetail trdTradeFeeDetailTotal = gen汇总交易费用明细(trdFinProductDealTotal, entity);
        result.setTrdTradeFeeDetail(trdTradeFeeDetailTotal);

        List<BaseTradeInfoDto> baseTradeInfoDtoList = new ArrayList<>();
        result.setBaseTradeInfoDtoList(baseTradeInfoDtoList);

        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
//       划款指令
        
        List<BussTrdTransferOrderDto> stlPayOrdersList = autoPeriodCommonService.save(entity.getBussTrdTransferOrder());
        List<FinAssetIntInfo> 利息明细 = entity.getFinAssetIntInfoList();
        利息明细.forEach(finAssetIntInfo -> {
            BaseTradeInfoDto baseTradeInfoDto = new BaseTradeInfoDto();
            baseTradeInfoDtoList.add(baseTradeInfoDto);
            gen利息明细汇总表和扩展表(finAssetIntInfo, entity, baseTradeInfoDto);
            List<String> cashIds = StringUtil.splitToList(",", finAssetIntInfo.getCashId());
            List<String> cashTypes = StringUtil.splitToList(",", finAssetIntInfo.getCashType());
            //                      生成划款指令
          //update zhangweiqi 2019-10-15 通道费修改 begin
            BigDecimal fPriceAmt=baseTradeInfoDto.getTrdFinProductDealTotal().getFpriceAmt();
            baseTradeInfoDto.getTrdFinProductDealTotal().setFpriceAmt(baseTradeInfoDto.getTrdFinProductDealTotal().getTradeAmt());
            StlPayOrders stl = autoPeriodCommonService.gen划款指令流程(stlPayOrdersList, baseTradeInfoDto.getTrdFinProductDealTotal(), entity.getWorkflowId());
            baseTradeInfoDto.getTrdFinProductDealTotal().setFpriceAmt(fPriceAmt);
            ////update zhangweiqi 2019-10-15 通道费修改 end
            baseTradeInfoDto.setStlPayOrders(stl);
            //封装还本不带息的交易
            if(entity.getIsClean()!=null && entity.getPayUnpaidintAmt()!=null && entity.getPayUnpaidintAmt().compareTo(BigDecimal.ZERO)>0
                    && finAssetIntInfo.getRepaymentUnpayInterest()!=null && finAssetIntInfo.getRepaymentUnpayInterest().compareTo(BigDecimal.ZERO)>0){//
                List<BaseTradeInfoDto> 还本不带息列表=get还本不带息列表明细(entity,finAssetIntInfo,parameter,baseTradeInfoDto.getTrdFinProductDealTotal().getTotleTradeId());
                baseTradeInfoDto.setRepaymentUnpayInterestBaseTradeInfoDtoList(还本不带息列表);
            }

            //            生成子组合利息明细共同方法
            Map<String,Object> subMap = new HashMap<>();
            subMap.put("portfolioId",finAssetIntInfo.getPortfolioId());
//            持仓时间
            subMap.put("dealDate", entity.getmDate());
//            资产代码
            subMap.put("finprodMarketId",entity.getFinprodMarketId());
//            金融产品代码
            subMap.put("finprodId",entity.getFinprodId());
            Map<String,String> defaultMap = new HashMap<>();
//            自定义不累加
            defaultMap.put("yield",finAssetIntInfo.getYield().toString());
            subMap.put("defaultMap", JsonUtils.object2Json(defaultMap));
////            汇总交易编号
//            subMap.put("totleTradeId",entity.getTotleTradeId());
            Map<String, BigDecimal> preMap = new HashMap<>();
            preMap.put("returnAmount",finAssetIntInfo.getReturnAmount());
            preMap.put("theoryAmt",finAssetIntInfo.getTheoryAmt());
            preMap.put("fpriceAmt",finAssetIntInfo.getFpriceAmt());
            preMap.put("taxAmount",finAssetIntInfo.getTaxAmount());
            preMap.put("qty",finAssetIntInfo.getQty());
          //update zhangweiqi 2019-10-15 通道费修改 add
            if(finAssetIntInfo.getChannelFee()!=null)
            preMap.put("channelFee",finAssetIntInfo.getChannelFee());
          //update zhangweiqi 2019-10-15 通道费修改 end
            if(finAssetIntInfo.getRepaymentUnpayInterest()!=null){
            preMap.put("repaymentUnpayInterest",finAssetIntInfo.getRepaymentUnpayInterest());}//理论还本不带息
            if(finAssetIntInfo.getActrRepaymentUnpayInterest()!=null){
            preMap.put("actrRepaymentUnpayInterest",finAssetIntInfo.getActrRepaymentUnpayInterest());}//实际还本不带息
//            划款指令结算金额
            if(stl != null){
                preMap.put("settleAmt",stl.getSettleAmt());
            }
            subMap.put("preMap", JsonUtils.object2Json(preMap));
            subMap.put(GlobalConstant.WORKFLOW_ID,entity.getWorkflowId());
            List<FinAssetIntInfo> 子组合利息明细 = autoPeriodCommonService.distributeDataByPercent(subMap);
//            子组合交易
            List<BaseTradeInfoDto> subBaseTradeInfoList = new ArrayList<>();
            baseTradeInfoDto.setBaseTradeInfoDtoList(subBaseTradeInfoList);
            for (int i = 0; i < cashIds.size(); i++ ) {
                String ps = AutoPeriodCommonService.change现金流类型To交易方向(cashTypes.get(i));
                if (DictCodeList.交易方向_收息.getValue().equals(ps)) {
                    TrdProductDeal deal收息 = 生成收息交易(entity, paTrdFinProductDealTotal, parameter, finAssetIntInfo, baseTradeInfoDto, cashIds, i);
                  //update zhangweiqi 通道费修改 2019-10-15 begin
//                    if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//                    	deal收息.setTradeAmt(deal收息.getTradeAmt().subtract(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee()));
//                    }
                    if(entity.getChannelFee()!=null){
                    	baseTradeInfoDto.setTrdTradeFeeDetail(getFeeDetailList(deal收息,finAssetIntInfo,entity));
                    }
                  //update zhangweiqi 通道费修改 2019-10-15 end
                    baseTradeInfoDto.setPtlTrade(getPtlTrade(entity, finAssetIntInfo, deal收息));
                }
                else {
                    TrdProductDeal deal = getWflTrdProductDeal(entity, parameter, finAssetIntInfo,
                        DictCodeList.交易方向_还本.getValue().equals(
                            ps) ? DictCodeList.交易方向_还本 : DictCodeList.交易方向_到期,
                        baseTradeInfoDto.getTrdFinProductDealTotal().getTotleTradeId(), cashIds.get(i));
                  //update zhangweiqi 通道费修改 2019-10-15 begin
                    if(cashIds.size()==1&&finAssetIntInfo.getFpriceAmt().compareTo(finAssetIntInfo.getReturnAmount()) == 0){
                    	
//                      if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//                    	deal.setTradeAmt(deal.getTradeAmt().subtract(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee()));
//                      }
                      if(entity.getChannelFee()!=null){
                    	baseTradeInfoDto.setTrdTradeFeeDetail(getFeeDetailList(deal,finAssetIntInfo,entity));
                      }
                    }
                    //update zhangweiqi 通道费修改 2019-10-15 end
                    
                    BaseTrade baseTrade = new BaseTrade();
                    baseTrade.setTrdProductDeal(deal);
                    baseTrade.setPaTrdProductDeal(getPaTrdProductDeal(finAssetIntInfo, deal, entity.getCashType().contains(
                        DictCodeList.现金流类型_中间还本.getValue()) ? DictCodeListExt1.平安业务类型_项目类资产中间还本 : DictCodeListExt1.平安业务类型_项目类资产到期));
                    baseTrade.setTrdProductDealAdd(getTrdProductDealAdd(entity, finAssetIntInfo, deal));
                    baseTrade.setPtlTrade(getPtlTrade(entity, finAssetIntInfo, deal));
                    baseTradeInfoDto.setBaseTrade(baseTrade);

                    if (cashIds.size() == 1 && finAssetIntInfo.getFpriceAmt().compareTo(finAssetIntInfo.getReturnAmount()) > 0) {
                        生成收息交易(entity, paTrdFinProductDealTotal, parameter, finAssetIntInfo, baseTradeInfoDto, cashIds, i);
                      //update zhangweiqi 通道费修改 2019-10-15 begin
                        TrdProductDeal deal2=baseTradeInfoDto.getTrdProductDeal();
//                        if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())&&baseTradeInfoDto.getTrdProductDeal()!=null){
//                      	  deal2.setTradeAmt(deal2.getTradeAmt().subtract(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee()));
//                      	}
                        if(entity.getChannelFee()!=null){
                      	  baseTradeInfoDto.setTrdTradeFeeDetail(getFeeDetailList(deal2,finAssetIntInfo,entity));
                        }
                     
                      //update zhangweiqi 通道费修改 2019-10-15 end
                    }
                }
                int temp = i;
                子组合利息明细.forEach(subFinAssetIntInfo->{
                    StlPayOrders subStl = new StlPayOrders();
                    BeanUtil.copyPropertiesIgnoreNull(subStl,stl);
                    subStl.setSettleAmt(subFinAssetIntInfo.getSettleAmt());
                    subFinAssetIntInfo.setPortfolioId(subFinAssetIntInfo.getSubPortfolioId());
                    subStl.setPortfolioId(subFinAssetIntInfo.getPortfolioId());
//                    子组合交易dto
                    BaseTradeInfoDto subBaseTradeInfoDto = new BaseTradeInfoDto();
                    //                        设置划款指令
                    subBaseTradeInfoDto.setStlPayOrders(subStl);
//                    子组合交易dto放入list
                    subBaseTradeInfoList.add(subBaseTradeInfoDto);
                    if (DictCodeList.交易方向_收息.getValue().equals(ps)) {
                        TrdProductDeal deal收息 = 生成子组合收息交易(entity, parameter, subFinAssetIntInfo, subBaseTradeInfoDto,
                                cashIds, temp,baseTradeInfoDto.getTrdFinProductDealTotal().getTotleTradeId());
                        //update zhangweiqi 通道费修改 2019-10-15 begin
//                        if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//                        	deal收息.setTradeAmt(deal收息.getTradeAmt().subtract(subFinAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:subFinAssetIntInfo.getChannelFee()));
//                        }
                        if(entity.getChannelFee()!=null){
                        	subBaseTradeInfoDto.setTrdTradeFeeDetail(getFeeDetailList(deal收息,subFinAssetIntInfo,entity));
                        }
                      //update zhangweiqi 通道费修改 2019-10-15 end
                        subBaseTradeInfoDto.setPtlTrade(getPtlTrade(entity, subFinAssetIntInfo, deal收息));
                    }
                    else {
                        TrdProductDeal deal = getWflTrdProductDeal(entity, parameter, subFinAssetIntInfo,
                                DictCodeList.交易方向_还本.getValue().equals(ps) ? DictCodeList.交易方向_还本 : DictCodeList.交易方向_到期,
                                baseTradeInfoDto.getTrdFinProductDealTotal().getTotleTradeId(), cashIds.get(temp));
                      //update zhangweiqi 通道费修改 2019-10-15 begin
                        if(cashIds.size()==1&&subFinAssetIntInfo.getFpriceAmt().compareTo(subFinAssetIntInfo.getReturnAmount()) == 0)
                         {
//                        if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//                        	deal.setTradeAmt(deal.getTradeAmt().subtract(subFinAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:subFinAssetIntInfo.getChannelFee()));
//                        	
//                        }
                        if(entity.getChannelFee()!=null){
                        	subBaseTradeInfoDto.setTrdTradeFeeDetail(getFeeDetailList(deal,subFinAssetIntInfo,entity));
                         }
                        }
                      //update zhangweiqi 通道费修改 2019-10-15 end
                        BaseTrade baseTrade = new BaseTrade();
                        baseTrade.setTrdProductDeal(deal);
                        baseTrade.setPaTrdProductDeal(getPaTrdProductDeal(subFinAssetIntInfo, deal, entity.getCashType().contains(
                                DictCodeList.现金流类型_中间还本.getValue()) ? DictCodeListExt1.平安业务类型_项目类资产中间还本 : DictCodeListExt1.平安业务类型_计划类资产_利率型_到期));
                        baseTrade.setTrdProductDealAdd(getTrdProductDealAdd(entity, subFinAssetIntInfo, deal));
                        baseTrade.setPtlTrade(getPtlTrade(entity, subFinAssetIntInfo, deal));
                        subBaseTradeInfoDto.setBaseTrade(baseTrade);
                        if (cashIds.size() == 1 && subFinAssetIntInfo.getFpriceAmt().compareTo(subFinAssetIntInfo.getReturnAmount()) > 0) {
                            生成子组合收息交易(entity,parameter, subFinAssetIntInfo, subBaseTradeInfoDto, cashIds, temp,baseTradeInfoDto.getTrdFinProductDealTotal().getTotleTradeId());
                          //update zhangweiqi 通道费修改 2019-10-15 begin
                        	TrdProductDeal deal2=subBaseTradeInfoDto.getTrdProductDeal();
//                        	if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//                        		deal2.setTradeAmt(deal2.getTradeAmt().subtract(subFinAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:subFinAssetIntInfo.getChannelFee()));
//                            }
                            if(entity.getChannelFee()!=null){
                            	subBaseTradeInfoDto.setTrdTradeFeeDetail(getFeeDetailList(deal2,subFinAssetIntInfo,entity));
                            }
                            //update zhangweiqi 通道费修改 2019-10-15 end
                        }
                    }
                    //子组合封装还本不带息
                        List<BaseTradeInfoDto> 子组合还本不带息列表=get还本不带息列表明细(entity,subFinAssetIntInfo,parameter,baseTradeInfoDto.getTrdFinProductDealTotal().getTotleTradeId());
                        if(子组合还本不带息列表!=null && 子组合还本不带息列表.size()>0){
                        subBaseTradeInfoDto.setRepaymentUnpayInterestBaseTradeInfoDtoList(子组合还本不带息列表);}

                });
            }
        });

        return result;

    }
    /**
     * 生成子组合收息交易
     * @param entity 画面entity
     * @param parameter 业务编号生成参数
     * @param subFinAssetIntInfo
     * @param baseTradeInfoDto
     * @param cashIds
     * @param i
     * @param totleTradeId
     * @return
     */
    private TrdProductDeal 生成子组合收息交易(TrdProjectDurationDto entity,
                                     BussinessCodeParameter parameter, FinAssetIntInfo subFinAssetIntInfo,
                                     BaseTradeInfoDto baseTradeInfoDto, List<String> cashIds, int i,String totleTradeId) {
        TrdProductDeal deal收息 = getWflTrdProductDeal(entity, parameter, subFinAssetIntInfo, DictCodeList.交易方向_收息,
                totleTradeId, cashIds.get(i));
        baseTradeInfoDto.setTrdProductDeal(deal收息);
        baseTradeInfoDto.setPaTrdProductDeal(
                getPaTrdProductDeal(subFinAssetIntInfo, deal收息, DictCodeListExt1.平安业务类型_计划类资产_利率型_中间付息));
        baseTradeInfoDto.setTrdProductDealAdd(getTrdProductDealAdd(entity, subFinAssetIntInfo, deal收息));
//        修改TOUGUAN-887 子组合改造-项目类资产中间还本带息（还本付息不同频），组合交易表/持仓/持仓流水/现金余额流水表存的数据缺失
        baseTradeInfoDto.setPtlTrade(getPtlTrade(entity, subFinAssetIntInfo, deal收息));
        return deal收息;
    }
    private TrdProductDeal 生成收息交易(TrdProjectDurationDto entity, PaTrdFinProductDealTotal paTrdFinProductDealTotal, BussinessCodeParameter parameter, FinAssetIntInfo finAssetIntInfo, BaseTradeInfoDto baseTradeInfoDto, List<String> cashIds, int i) {
        TrdProductDeal deal收息 = getWflTrdProductDeal(entity, parameter, finAssetIntInfo,
                DictCodeList.交易方向_收息, baseTradeInfoDto.getTrdFinProductDealTotal().getTotleTradeId(),
                cashIds.get(i));
        baseTradeInfoDto.setTrdProductDeal(deal收息);

        baseTradeInfoDto.setPaTrdProductDeal(
                getPaTrdProductDeal(finAssetIntInfo, deal收息, DictCodeListExt1.平安业务类型_项目类资产中间付息));
        paTrdFinProductDealTotal.setRate(baseTradeInfoDto.getPaTrdProductDeal().getTheoryRate());
        baseTradeInfoDto.setTrdProductDealAdd(getTrdProductDealAdd(entity, finAssetIntInfo, deal收息));
//        修改TOUGUAN-887 子组合改造-项目类资产中间还本带息（还本付息不同频），组合交易表/持仓/持仓流水/现金余额流水表存的数据缺失
        baseTradeInfoDto.setPtlTrade(getPtlTrade(entity, finAssetIntInfo, deal收息));
        return deal收息;
    }

    /**
     * 登录流程中数据
     *
     * @param baseTotalTradeInfoDto 流程中数据
     * @see
     * @since 1.0
     */
    public void insertWflData(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
        if (baseTotalTradeInfoDto == null) {
            return;
        }

        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectDuration/doInsert", baseTotalTradeInfoDto,
            String.class);
    }

    private static TrdFinProductDealTotal gen汇总流程表(TrdProjectDurationDto entity) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getBusiCode());
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_项目类资产存续期.getValue());
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        // trdProductDeal.setFinprodType(); TODO 估值核算 金融产品类型
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        trdFinProductDealTotal.setCcy(entity.getCcy());
        trdFinProductDealTotal.setPs(AutoPeriodCommonService.change现金流类型To交易方向(entity.getCashType()));
        trdFinProductDealTotal.setBranch(
            AutoPeriodCommonService.changePsToBranch(entity.getmDate(), trdFinProductDealTotal.getPs()));
        trdFinProductDealTotal.setTradeDate(DateTime.parse(entity.getArrivalDate()).toDate());
        trdFinProductDealTotal.setVdate(DateTime.parse(entity.getvDate()).toDate());
        trdFinProductDealTotal.setMdate(DateTime.parse(entity.getmDate()).toDate());
        trdFinProductDealTotal.setSettleDate(DateTime.parse(entity.getArrivalDate()).toDate());
        trdFinProductDealTotal.setShareAmt(entity.getReturnAmount());
        trdFinProductDealTotal.setPrinAmt(entity.getReturnAmount());
        trdFinProductDealTotal.setIntAmt(ArithmeticUtil.subtract(entity.getFpriceAmt(), entity.getReturnAmount()));
        trdFinProductDealTotal.setFpriceAmt(entity.getFpriceAmt());
        trdFinProductDealTotal.setTradeAmt(entity.getArrivalAmt());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setIsClean(entity.getIsClean());//是否结清
        trdFinProductDealTotal.setPayUnpaidintAmt(entity.getPayUnpaidintAmt());//累计未支付利息
        trdFinProductDealTotal.setChlId(entity.getChlId());
        //update zhangweiqi 存续期通道费修改  2019-10-15 begin
//         if(entity.getChannelFee()!=null){
//        	trdFinProductDealTotal.setFeeAmt(entity.getChannelFee());	
//        	if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//        		trdFinProductDealTotal.setFeeAmtPay(entity.getChannelFee()==null?BigDecimal.ZERO:entity.getChannelFee());
//        	}else{
//        		trdFinProductDealTotal.setFeeAmtUnpay(entity.getChannelFee()==null?BigDecimal.ZERO:entity.getChannelFee());
//        	}
//        }//update zhangweiqi 存续期通道费修改  2019-10-15 end
         trdFinProductDealTotal.setFeeAmt(entity.getChannelFee());	
         trdFinProductDealTotal.setFeeAmtUnpay(entity.getChannelFee()==null?BigDecimal.ZERO:entity.getChannelFee());
        return trdFinProductDealTotal;
    }

    private static PaTrdFinProductDealTotal gen汇总扩展业务流程(TrdProjectDurationDto entity,
                                                        TrdFinProductDealTotal trdFinProductDealTotal) {
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        paTrdFinProductDealTotal.setTheoryPayDate(DateTime.parse(entity.getInterestPayDay()).toDate());
        paTrdFinProductDealTotal.setTheoryInt(entity.getIntAmtTheory());
        paTrdFinProductDealTotal.setTheoryPrin(entity.getReturnAmount());
        paTrdFinProductDealTotal.setRemainPrinAmt(entity.getQty());
        paTrdFinProductDealTotal.setRate(ArithmeticUtil.divide(entity.getYield(), 100, 5));
        paTrdFinProductDealTotal.setPaBussType(entity.getPaBussType());
        paTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        return paTrdFinProductDealTotal;
    }

    private static TrdTradeFeeDetail gen汇总交易费用明细(TrdFinProductDealTotal trdFinProductDealTotal, TrdProjectDurationDto entity) {
        TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
        trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
        trdTradeFeeDetail.setFeeAmt(entity.getTaxAmount());
        trdTradeFeeDetail.setTradeId(entity.getBusiCode());
        trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_投管_税费.getValue()); // 一次性费用类型
        return trdTradeFeeDetail;
    }

    private void gen利息明细汇总表和扩展表(FinAssetIntInfo finAssetIntInfo, TrdProjectDurationDto entity,
                                BaseTradeInfoDto baseTradeInfoDto) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(
            autoPeriodCommonService.buildBussinessCode(new BussinessCodeParameter(DictCodeList.共通业务类型_资产存续期交易)));
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_项目类资产存续期.getValue());
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        // trdProductDeal.setFinprodType(); TODO 估值核算 金融产品类型
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        trdFinProductDealTotal.setCcy(entity.getFinAssetInfo().getCcy());
        trdFinProductDealTotal.setPs(AutoPeriodCommonService.change现金流类型To交易方向(entity.getCashType()));
        trdFinProductDealTotal.setTradeDate(DateTime.parse(entity.getArrivalDate()).toDate());
        trdFinProductDealTotal.setVdate(DateTime.parse(finAssetIntInfo.getvDate()).toDate());
        trdFinProductDealTotal.setMdate(DateTime.parse(finAssetIntInfo.getmDate()).toDate());
        trdFinProductDealTotal.setSettleDate(DateTime.parse(entity.getArrivalDate()).toDate());
        trdFinProductDealTotal.setShareAmt(finAssetIntInfo.getReturnAmount());
        trdFinProductDealTotal.setPrinAmt(finAssetIntInfo.getReturnAmount());
        trdFinProductDealTotal.setPortfolioId(finAssetIntInfo.getPortfolioId());
        trdFinProductDealTotal.setIntAmt(
            ArithmeticUtil.subtract(finAssetIntInfo.getFpriceAmt(), finAssetIntInfo.getReturnAmount()));
        trdFinProductDealTotal.setFpriceAmt(finAssetIntInfo.getFpriceAmt());
        trdFinProductDealTotal.setTradeAmt(
            ArithmeticUtil.subtract(finAssetIntInfo.getFpriceAmt(), finAssetIntInfo.getTaxAmount()));
        //update zhangweiqi 通道费修改 2019-10-15 begin
//        if(entity.isChannel()&&trdFinProductDealTotal.getTradeAmt()!=null&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//        	trdFinProductDealTotal.setTradeAmt(trdFinProductDealTotal.getTradeAmt().subtract(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee()));
//        	
//        }
        if(entity.getChannelFee()!=null){
//        	if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//        		trdFinProductDealTotal.setFeeAmtPay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
//        	}else{
//        		trdFinProductDealTotal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
//        	}
        	trdFinProductDealTotal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
        	trdFinProductDealTotal.setFeeAmt(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
        	
        }
      //update zhangweiqi 通道费修改 2019-10-15 end
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setIsClean(entity.getIsClean());
        trdFinProductDealTotal.setpTotleTradeId(entity.getBusiCode());
        trdFinProductDealTotal.setChlId(entity.getChlId());
        baseTradeInfoDto.setTrdFinProductDealTotal(trdFinProductDealTotal);

        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        paTrdFinProductDealTotal.setTheoryAmt(finAssetIntInfo.getTheoryAmt());
        paTrdFinProductDealTotal.setRemainPrinAmt(finAssetIntInfo.getAmount());
        paTrdFinProductDealTotal.setTheoryPayDate(DateTime.parse(entity.getInterestPayDay()).toDate());
        paTrdFinProductDealTotal.setRate(ArithmeticUtil.divide(entity.getYield(), 100, 5));
        paTrdFinProductDealTotal.setPaBussType(entity.getPaBussType());
        paTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        baseTradeInfoDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
    }

    private TrdProductDeal getWflTrdProductDeal(TrdProjectDurationDto entity, BussinessCodeParameter parameter,
                                                FinAssetIntInfo finAssetIntInfo, DictCodeList 交易方向, String totalTradeId,
                                                String cashId) {
        TrdProductDeal deal = new TrdProductDeal();
        deal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
        deal.setBusiType(DictCodeList.共通业务类型_项目类资产存续期.getValue());
        deal.setFinprodId(entity.getFinprodId());
        deal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        // deal.setFinprodType(); TODO 估值核算 金融产品类型
        deal.setCcy(entity.getFinAssetInfo().getCcy());
        deal.setTradeDate(DateTime.parse(entity.getArrivalDate()).toDate());
        deal.setVdate(DateTime.parse(entity.getvDate()).toDate());
        deal.setMdate(DateTime.parse(entity.getmDate()).toDate());
        deal.setSettleDate(deal.getTradeDate());
        deal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        deal.setIsCancel(DictCodeList.是否_否.getValue());
        deal.setWorkflowId(entity.getWorkflowId());
        deal.setTotleTradeId(totalTradeId);
        deal.setCashId(cashId);
        deal.setStatus(交易方向.getValue());
        deal.setChlId(entity.getChlId());
        deal.setBranch(AutoPeriodCommonService.changePsToBranch(entity.getmDate(), 交易方向.getValue()));
        deal.setIsClean(entity.getIsClean());//是否结清
        if (DictCodeList.交易方向_收息 == 交易方向) {
//            收息不入shareAmt
//            deal.setShareAmt(finAssetIntInfo.getQty());
            BigDecimal actRepaymenAmt=finAssetIntInfo.getActrRepaymentUnpayInterest();//实际区间未付利息
            if(actRepaymenAmt==null){//还本不带息的场景
                actRepaymenAmt=BigDecimal.ZERO;
            }
            BigDecimal amount= ArithmeticUtil.doAdd(finAssetIntInfo.getReturnAmount(),actRepaymenAmt);//本金+还本不带息的利息
                deal.setIntAmt(ArithmeticUtil.subtract(finAssetIntInfo.getFpriceAmt(),amount));

            deal.setFpriceAmt(deal.getIntAmt());
            deal.setTradeAmt(deal.getIntAmt());
            deal.setPs(DictCodeList.交易方向_收息到账.getValue());
        }
        else if (DictCodeList.交易方向_还本 == 交易方向 || DictCodeList.交易方向_到期 == 交易方向) {
            deal.setShareAmt(finAssetIntInfo.getQty());
            deal.setPrinAmt(finAssetIntInfo.getReturnAmount());
            deal.setFpriceAmt(finAssetIntInfo.getReturnAmount());
            deal.setTradeAmt(finAssetIntInfo.getReturnAmount());
            deal.setPs(DictCodeList.交易方向_还本到账.getValue());
        }
        return deal;
    }

    private PaTrdProductDeal getPaTrdProductDeal(FinAssetIntInfo finAssetIntInfo, TrdProductDeal deal,
                                                 DictCodeListExt1 平安业务类型项目类资产) {
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(deal.getTradeId());
        paTrdProductDeal.setTheoryRate(ArithmeticUtil.divide(finAssetIntInfo.getYield(), 100, 8));
        if(DictCodeListExt1.平安业务类型_项目类资产中间付息 == 平安业务类型项目类资产) {
            paTrdProductDeal.setTheoryInt(deal.getIntAmt());
        }
        else {
            paTrdProductDeal.setTheoryInt(deal.getPrinAmt());
        }
        paTrdProductDeal.setPaBussType(平安业务类型项目类资产.getValue());
        paTrdProductDeal.setWorkflowId(deal.getWorkflowId());
        return paTrdProductDeal;
    }

    private TrdProductDealAdd getTrdProductDealAdd(TrdProjectDurationDto entity, FinAssetIntInfo finAssetIntInfo,
                                                   TrdProductDeal deal) {
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(deal.getTradeId());
        trdProductDealAdd.setPortfolioId(finAssetIntInfo.getPortfolioId());
        //trdProductDealAdd.setProdId(entity.getFinprodId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        trdProductDealAdd.setWorkflowId(deal.getWorkflowId());
        //        丁志鹏 20190924 添加持仓存储 start
        trdProductDealAdd.setRegDateAmt(finAssetIntInfo.getAmount());
        //        丁志鹏 20190924 添加持仓存储 end
        return trdProductDealAdd;
    }

    private List<PtlTrade> getPtlTrade(TrdProjectDurationDto entity, FinAssetIntInfo finAssetIntInfo, TrdProductDeal deal收息) {
        List<PtlTrade> ptlTrades = new ArrayList<>();
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setBusiId(entity.getFinprodId());
        ptlTrade.setBusiType(DictCodeList.共通业务类型_项目类资产存续期.getValue());
        ptlTrade.setSettleDate(DateTime.parse(entity.getArrivalDate()).toDate());
        ptlTrade.setPortfolioId(finAssetIntInfo.getPortfolioId());
        ptlTrade.setBusiNo(deal收息.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setWorkflowId(entity.getWorkflowId());
        ptlTrades.add(ptlTrade);
        return ptlTrades;
    }
    
    /**
     * add by zhangweiqi 生成通道费明细
     */
    private List<TrdTradeFeeDetail>  getFeeDetailList(TrdProductDeal deal,FinAssetIntInfo finAssetIntInfo,TrdProjectDurationDto entity){
    	deal.setFeeAmt(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
    	TrdTradeFeeDetail detail=new TrdTradeFeeDetail();
    	List<TrdTradeFeeDetail> detailList=new ArrayList<TrdTradeFeeDetail>();
    	detail.setTradeId(deal.getTradeId());
    	detail.setFeeAmt(finAssetIntInfo.getChannelFee());
    	detail.setFeeType(DictCodeListExt1.一次性费用类型_通道费.getValue());
    	detail.setCcy(deal.getCcy());
//    	if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//    		detail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
//    		deal.setFeeAmtPay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
//    	}else{
//    		detail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
//    		deal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
//    	}
    	detail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
		deal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
    	detail.setFeeId(DictCodeListExt1.一次性费用类型_通道费.getValue());
    	detail.setChlFinprodId(entity.getChlId());
    	detail.setCreateUser(deal.getCreateUser());
    	detail.setCreateTime(deal.getCreateTime());
    	detail.setUpdateUser(deal.getUpdateUser());
    	detail.setUpdateTime(deal.getUpdateTime());
    	detailList.add(detail);
    	return detailList;
    }


    public List<BaseTradeInfoDto> get还本不带息列表明细(TrdProjectDurationDto entity,FinAssetIntInfo finAssetIntInfo,
                                               BussinessCodeParameter parameter,String totleTradeId){
        String isClean=entity.getIsClean();//是否结清
        List<BaseTradeInfoDto> baseTradeInfoDtoList =new ArrayList<>();
       // for(FinAssetIntInfo intInfo:entity.getFinAssetIntInfoList()){
            List<BaseTradeInfoDto> baseTradeInfoList =new ArrayList<>();
     //       if(finAssetIntInfo.getRepaymentUnpayInterest()!=null && finAssetIntInfo.getRepaymentUnpayInterest().compareTo(BigDecimal
         //         .ZERO)>0) {
                //根据利息明细查询应收款
                Map<String, Object> param = new HashMap<>();
                param.put("chlId", entity.getChlId());//通道代码
                param.put("finprodId", entity.getFinprodId());//资产代码
                param.put("portfolioId", finAssetIntInfo.getPortfolioId());//组合代码
                List<PtlRecePosition> recePositionList = basePtlRecePositionService.getRepayAmtList(param);
                baseTradeInfoList = getPtlReceFlow(entity, recePositionList, finAssetIntInfo, entity.getIsClean(), totleTradeId, parameter);//获取每个组合的还本不带息列表
     //       }
            baseTradeInfoDtoList.addAll(baseTradeInfoList);
      //  }
        return baseTradeInfoDtoList;
    }

    private List<BaseTradeInfoDto>  getPtlReceFlow(TrdProjectDurationDto entity,List<PtlRecePosition> list,
                                                   FinAssetIntInfo finAssetIntInfo,String isClean,
                                                   String totaltradeId,BussinessCodeParameter parameter){
        List<BaseTradeInfoDto> baseTradeInfoDtoList=new ArrayList<>();
        if(isClean!=null && isClean.equals(DictCodeList.是否_否.getValue()) && list.size()>0){
            //【变量】数量=当前组合的区间未付利息/当前组合的理论本息总额*当前组合的实收本息总额*-1
            BigDecimal sumbranchAmt=BigDecimal.ZERO;
            for(int i=0;i<list.size();i++){
                if(i==list.size()-1) {//最后一笔倒减
                    BigDecimal amt=finAssetIntInfo.getActrRepaymentUnpayInterest().subtract(sumbranchAmt).setScale(2,BigDecimal.ROUND_HALF_UP);
                    //实收区间未付利息总额-区间未付利息
                    list.get(i).setAmount(amt);
                }else {
                    BigDecimal branchAmt=BigDecimal.ZERO;
                    PtlRecePosition recePosition = list.get(i);//获取组合多个branch
                    BigDecimal amount = recePosition.getAmount();//理论区间未付利息
                    branchAmt= amount.multiply(finAssetIntInfo.getActrRepaymentUnpayInterest()).divide(finAssetIntInfo.getRepaymentUnpayInterest()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    sumbranchAmt=sumbranchAmt.add(branchAmt);
                    list.get(i).setAmount(branchAmt);
                }
            }
        }

        for(PtlRecePosition recePosition:list) {
            BaseTradeInfoDto tradeInfoDto=new BaseTradeInfoDto();
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            trdProductDeal=getWflTrdProductDeal(entity,parameter,finAssetIntInfo,DictCodeList.交易方向_收息
                    ,totaltradeId,
                    null);
            BigDecimal tradeAmt=recePosition.getAmount();
            trdProductDeal.setFpriceAmt(tradeAmt);
            trdProductDeal.setTradeAmt(tradeAmt);//应收款流水金额
            trdProductDeal.setShareAmt(tradeAmt);
            trdProductDeal.setUnitInt(null);//单位利息
            trdProductDeal.setCpriceAmt(null);//净价总额
            trdProductDeal.setPrinAmt(BigDecimal.ZERO);
            trdProductDeal.setIntAmt(tradeAmt);
            trdProductDeal.setFeeAmt(null);
            trdProductDeal.setFeeAmtPay(null);
            trdProductDeal.setFeeAmtUnpay(null);
            trdProductDeal.setPs(DictCodeList.交易方向_应收款项转出.getValue());
            trdProductDeal.setBranch(recePosition.getBranch());//应收款流水BRANCH
            trdProductDeal.setIsClean(isClean);//是否结清
            tradeInfoDto.setTrdProductDeal(trdProductDeal);

            TrdProductDealAdd addDto = new TrdProductDealAdd();
            addDto.setTradeId(trdProductDeal.getTradeId());
            addDto.setPortfolioId(finAssetIntInfo.getPortfolioId());
            addDto.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
            tradeInfoDto.setTrdProductDealAdd(addDto);

            List<PtlTrade> ptlTrades = new ArrayList<>();
            PtlTrade ptlTrade = new PtlTrade();
            ptlTrade.setPortfolioId(finAssetIntInfo.getPortfolioId());
            ptlTrade.setBusiNo(trdProductDeal.getTradeId());
            ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade.setBusiId(finAssetIntInfo.getFinprodId());
            ptlTrade.setBusiType(DictCodeList.共通业务类型_项目类资产交易.getValue());
            ptlTrade.setSettleDate(trdProductDeal.getTradeDate());
            ptlTrades.add(ptlTrade);
            tradeInfoDto.setPtlTrade(ptlTrades);

            PaTrdProductDeal pdeal= new PaTrdProductDeal();
            pdeal=getPaTrdProductDeal(finAssetIntInfo, trdProductDeal, DictCodeListExt1.平安业务类型_项目类资产中间付息);
            tradeInfoDto.setPaTrdProductDeal(pdeal);
            baseTradeInfoDtoList.add(tradeInfoDto);
        }

        return baseTradeInfoDtoList;
    }
}
