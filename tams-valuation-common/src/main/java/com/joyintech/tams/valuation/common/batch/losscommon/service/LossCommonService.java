/*
 * 文件名：LossCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.batch.losscommon.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.constant.AllocationType;
import com.joyintech.calculation.bonds.constant.Basis;
import com.joyintech.calculation.bonds.constant.IntFrequency;
import com.joyintech.calculation.bonds.constant.IntRateType;
import com.joyintech.calculation.bonds.constant.SecIntSchCalMethod;
import com.joyintech.calculation.bonds.constant.Variety;
import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.shell.AccurIntCalculator;
import com.joyintech.calculation.shell.param.AccurIntCalInParameter;
import com.joyintech.calculation.shell.param.AccurIntCalOutParameter;
import com.joyintech.calculation.shell.param.SecInfoModel;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import com.joyintech.tams.common.base.entity.BokStatLossPosiiton;
import com.joyintech.tams.common.base.entity.TrdAssetLoss;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.batch.calculator.BasisDaysCal;
import com.joyintech.tams.valuation.common.batch.calculator.InterestSubAmtCal;
import com.joyintech.tams.valuation.common.batch.calculator.RepayScheduleSubCal;
import com.joyintech.tams.valuation.common.batch.calculator.dto.AssetCommonInfoDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.FinCashPlanDetailDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.FinCashPlanEx;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashCalcRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPayRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPlan;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.batch.losscommon.dao.LossCommonMapper;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.valuation.common.dto.calc.InvRateQuo;
import com.joyintech.tams.valuation.common.dto.calc.InvRepaySchedule;
import com.joyintech.tams.valuation.common.dto.calc.InvRpayscheDetail;
import com.joyintech.tams.valuation.common.dto.calc.PrincipalInfo;
import com.joyintech.tams.valuation.common.dto.calc.QuoteInfo;
import com.joyintech.tams.valuation.common.dto.calc.SecIntRange;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.utils.CalendarUtil;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.tams.valuation.common.utils.DataHandleUtil;

/**
 * 减值相关共通Service
 * 
 * @author caoguo
 * @version 1.0 2020年10月15日
 * @see LossCommonService
 * @since 1.0
 */
@Service
public class LossCommonService {

    @Autowired
    private DBUtil dBUtil;

    /**
     * 减值相关共通Mapper
     */
    @Autowired
    private LossCommonMapper lossCommonMapper;
    
    /**
     * 标的共通方法service
     */
    @Autowired
    private FinProductCommonBatchService finProductCommonBatchService;
    
    private Logger logger = LoggerFactory.getLogger(InterfCommonService.class);

    /**
     * 减值计提计算
     * 
     * @param trdAssetLoss 资产减值流水DTO
     * @param variableEda EAD金额
     * @return
     */
	public BigDecimal lossProvisionCalculate(TrdAssetLoss trdAssetLoss, BigDecimal variableEda) {
		// 定义 变量.减值余额 =0
		BigDecimal lossBalance = BigDecimal.ZERO;
		if (StringUtil.equals(DictCodeListExt3.减值测算方式_外部评估.getValue(), trdAssetLoss.getLossTestType())
				&& trdAssetLoss.getLossRatio() != null) {
			lossBalance = variableEda.multiply(trdAssetLoss.getLossRatio());
		} else if (StringUtil.equals(DictCodeListExt3.减值测算方式_预期信用损失模型.getValue(), trdAssetLoss.getLossTestType())
				&& trdAssetLoss.getPdVaule() != null && trdAssetLoss.getLgdVaule() != null) {
			lossBalance = (variableEda.multiply(trdAssetLoss.getPdVaule())).multiply(trdAssetLoss.getLgdVaule());
		}
		return ArithmeticUtil.round(lossBalance, NumberEnum.MONEY);
	}

    /**
     * 减值明细数据生成-基础
     * 
     * @param bookInfo 账务明细
     * @param jzje 减值金额
     * @param jzjeb 减值金额本位币
     * @param jzye 减值余额
     * @param jzyeb 减值余额本位币
     * @param tradeId 交易编号
     * @param lossExeType 减值方式
     * @param schemeDetailId 方案代码
     * @return 减值明细数据
     * @see 减值明细数据生成-基础
     * @since 1.0
     */
    public BokStatLossPosiiton setLossInfoBase(BokBookdetailCurrent bookInfo, BigDecimal jzje,
                                               BigDecimal jzjeb, BigDecimal jzye, BigDecimal jzyeb,
                                               String tradeId, String lossExeType,
                                               String schemeDetailId,String lossGenType) {
        if(lossGenType == null) {
            lossGenType = DictCodeListExt3.减值生成方式_交易批处理.getValue();
        }
        BokStatLossPosiiton loss = new BokStatLossPosiiton();
        loss.setSeqno(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
        loss.setBokSeqno(bookInfo.getSeqno());
        loss.setBooksetId(bookInfo.getBooksetId());
        loss.setHappenDate(bookInfo.getHappenDate());
        loss.setBookDate(bookInfo.getBookDate());
        loss.setBooksetDate(bookInfo.getBooksetDate());
        loss.setLossGenType(lossGenType);
        loss.setBokDetailId(bookInfo.getBokDetailId());
        loss.setSchemeDetailId(schemeDetailId);
        loss.setBusiId(bookInfo.getBusiId());
        loss.setTradeId(tradeId);
        loss.setLossExeType(lossExeType);
        loss.setCcy(bookInfo.getoCcy());
        loss.setbCcy(bookInfo.getbCcy());
        loss.setTdyLossamtAdd(jzje);
        loss.setTdyLossamtAddB(jzjeb);
        loss.setTdyLossamt(jzye);
        loss.setTdyLossamtB(jzyeb);
        return loss;
    };

    /**
     * 减值使用明细数据生成（仅交易批处理使用）
     * 
     * @param bookInfo 账务明细
     * @param jzje 减值金额
     * @param jzjeb 减值金额本位币
     * @param jzye 减值余额
     * @param jzyeb 减值余额本位币
     * @param tradeId 交易编号
     * @return 减值明细数据
     * @see 减值使用明细数据生成
     * @since 1.0
     */
    public BokStatLossPosiiton setLossInfoUsed(BokBookdetailCurrent bookInfo, BigDecimal jzje,
                                               BigDecimal jzjeb, BigDecimal jzye, BigDecimal jzyeb,
                                               String tradeId) {
        return setLossInfoUsed(bookInfo, jzje, jzjeb, jzye, jzyeb, tradeId, null);
    }
    
    /**
     * 减值使用明细数据生成
     * 
     * @param bookInfo 账务明细
     * @param jzje 减值金额
     * @param jzjeb 减值金额本位币
     * @param jzye 减值余额
     * @param jzyeb 减值余额本位币
     * @param tradeId 交易编号
     * @param lossGenType 生成方式
     * @return 减值明细数据
     * @see 减值使用明细数据生成
     * @since 1.0
     */
    public BokStatLossPosiiton setLossInfoUsed(BokBookdetailCurrent bookInfo, BigDecimal jzje,
                                               BigDecimal jzjeb, BigDecimal jzye, BigDecimal jzyeb,
                                               String tradeId, String lossGenType) {
        String lossExeType = DictCodeListExt3.减值处理方式_减值使用.getValue();
        return setLossInfoBase(bookInfo, jzje, jzjeb, jzye, jzyeb, tradeId, lossExeType, null, lossGenType);
    }

    /**
     * 减值计提明细数据生成（仅交易批处理使用）
     * 
     * @param bookInfo 账务明细
     * @param jzje 减值金额
     * @param jzjeb 减值金额本位币
     * @param jzye 减值余额
     * @param jzyeb 减值余额本位币
     * @param tradeId 交易编号
     * @param schemeDetailId 方案代码
     * @return 减值明细数据
     * @see 减值计提明细数据生成
     * @since 1.0
     */
    public BokStatLossPosiiton setLossInfoAccr(BokBookdetailCurrent bookInfo, BigDecimal jzje,
                                               BigDecimal jzjeb, BigDecimal jzye, BigDecimal jzyeb,
                                               String tradeId, String schemeDetailId) {
        return setLossInfoAccr(bookInfo, jzje, jzjeb, jzye, jzyeb, tradeId, schemeDetailId,null);
    }
    
    /**
     * 减值计提明细数据生成
     * 
     * @param bookInfo 账务明细
     * @param jzje 减值金额
     * @param jzjeb 减值金额本位币
     * @param jzye 减值余额
     * @param jzyeb 减值余额本位币
     * @param tradeId 交易编号
     * @param schemeDetailId 方案代码
     * @return 减值明细数据
     * @see 减值计提明细数据生成
     * @since 1.0
     */
    public BokStatLossPosiiton setLossInfoAccr(BokBookdetailCurrent bookInfo, BigDecimal jzje,
                                               BigDecimal jzjeb, BigDecimal jzye, BigDecimal jzyeb,
                                               String tradeId, String schemeDetailId, String lossGenType) {
        String lossExeType = null;
        // 减值计提
        if (jzye.compareTo(BigDecimal.ZERO) <= 0) {
            lossExeType = DictCodeListExt3.减值处理方式_减值.getValue();
        }
        else { // 减值转回
            lossExeType = DictCodeListExt3.减值处理方式_转回.getValue();
        }
        return setLossInfoBase(bookInfo, jzje, jzjeb, jzye, jzyeb, tradeId, lossExeType,
            schemeDetailId, lossGenType);
    }
    

    /**
     * 减值清理明细数据生成（仅交易批处理使用）
     * 
     * @param bookInfo 账务明细
     * @param jzje 减值金额
     * @param jzjeb 减值金额本位币
     * @param jzye 减值余额
     * @param jzyeb 减值余额本位币
     * @param tradeId 交易编号
     * @return 减值明细数据
     * @see 减值清理明细数据生成
     * @since 1.0
     */
    public BokStatLossPosiiton setLossInfoClean(BokBookdetailCurrent bookInfo, BigDecimal jzje,
                                                BigDecimal jzjeb, BigDecimal jzye, BigDecimal jzyeb, String tradeId) {
        return setLossInfoClean(bookInfo, jzje, jzjeb,jzye,jzyeb, tradeId, null);
    }
    
    /**
     * 减值清理明细数据生成
     * 
     * @param bookInfo 账务明细
     * @param jzje 减值金额
     * @param jzjeb 减值金额本位币
     * @param jzye 减值余额
     * @param jzyeb 减值余额本位币
     * @param tradeId 交易编号
     * @return 减值明细数据
     * @see 减值清理明细数据生成
     * @since 1.0
     */
    public BokStatLossPosiiton setLossInfoClean(BokBookdetailCurrent bookInfo, BigDecimal jzje,
                                                BigDecimal jzjeb, BigDecimal jzye, BigDecimal jzyeb, String tradeId, String lossGenType) {
        String lossExeType = DictCodeListExt3.减值处理方式_清理核销.getValue();
        return setLossInfoBase(bookInfo, jzje, jzjeb, jzye, jzyeb, tradeId, lossExeType, null, lossGenType);
    }

    /**
     * 获取最新资产减值流水
     * 
     * @param param 查詢條件
     * @return 资产减值流水entity
     * @see 获取最新资产减值流水
     * @since 1.0
     */
    public TrdAssetLoss getTrdAssetLoss(Map<String, Object> param) {
        return lossCommonMapper.getTrdAssetLoss(param);
    }
    /**
     * 将数据，插入【统计分析明细表（资产减值）】表
     * @param bokStatLossPosiitonList
     * @return
     * @see
     * @since 1.0
     */
    public int insertBokStatLossPosiitonList(List<BokStatLossPosiiton> bokStatLossPosiitonList) {
    	
    	int sucessNum = 0;
    	//数据存在
    	if(bokStatLossPosiitonList!=null && !bokStatLossPosiitonList.isEmpty()){
            List<List<BokStatLossPosiiton>> list = DataHandleUtil.splitList(bokStatLossPosiitonList);
            for(List<BokStatLossPosiiton> bokBatchMarketwarningInfos : list){
            	lossCommonMapper.insertBokStatLossPosiiton(bokBatchMarketwarningInfos);
            }

    	}
    	return sucessNum;
	}
    
    /**
     * 债券日间利息计算
     * @param finProduct 标的信息
     * @param costBalance 交易份额
     * @param faceValue 面值
     * @param happenDate 计算日
     * @param batchExedetailSeqNo
     * @param bookSetId
     * @return
     */
    public BigDecimal calSecIntAmt(TargetFinProduct finProduct,BigDecimal costNum,BigDecimal faceValue,Date happenDate
    		, String batchExedetailSeqNo, String bookSetId){
    	BigDecimal result = BigDecimal.ZERO;
    	
    	if(faceValue==null || faceValue.compareTo(BigDecimal.ZERO)==0){
    		return result;
    	}
    	
    	// 查询条件
        List<TargetSearchDto> queryCriteria = new ArrayList<TargetSearchDto>();
        
        // 付息计划条件
        TargetSearchDto searchIntinfo = new TargetSearchDto();
        searchIntinfo.setQueryType(ValDictCodeList.标的_现金流计划.getValue());
        searchIntinfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        queryCriteria.add(searchIntinfo);
        
        
        // 报价计划条件
        TargetSearchDto searchCashinfo = new TargetSearchDto();
        searchCashinfo.setQueryType(ValDictCodeList.标的_现金流计算规则.getValue());
        searchCashinfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        searchCashinfo.setEffdateEnd(DateUtils.addDays(happenDate, -1));
        queryCriteria.add(searchCashinfo);
        
        
        // 现金流支付规则条件
        TargetSearchDto searchPayinfo = new TargetSearchDto();
        searchPayinfo.setQueryType(ValDictCodeList.标的_现金流支付规则.getValue());
        searchPayinfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        queryCriteria.add(searchPayinfo);
        
        Map<TargetSearchDto, Object> targetSearchMap = finProductCommonBatchService.getTargetInfo(finProduct.getFinprodId(), 
        		queryCriteria);
        

        @SuppressWarnings("unchecked")
        //定义 变量.付息计划
		List<TargetCashPlan> listIntinfo = targetSearchMap.get(
                searchIntinfo) == null ? null : (List<TargetCashPlan>)targetSearchMap.get(
                    searchIntinfo);
    	
        //定义 变量.报价列表		
    	 @SuppressWarnings("unchecked")
        List<TargetCashCalcRule> listCashinfo = targetSearchMap.get(
            searchCashinfo) == null ? null : (List<TargetCashCalcRule>)targetSearchMap.get(
                searchCashinfo);
    	
    	// 变量.支付规则 = 上述共通返回支付规则列表
        @SuppressWarnings("unchecked")
        List<TargetCashPayRule> listPayinfo = targetSearchMap.get(
            searchPayinfo) == null ? null : (List<TargetCashPayRule>)targetSearchMap.get(
                searchPayinfo);
    	
        //计算日
        Date tempSettleDate = happenDate;
        if (StringUtil.equals(DictCodeListExt1.交易场所_上海证券交易所.getValue(), finProduct.getTradeMarket())
            || StringUtil.equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue(), finProduct.getTradeMarket())) {
        	tempSettleDate = DateUtil.addDay(tempSettleDate, 1);
        }
        
        AccurIntCalInParameter parameter = new AccurIntCalInParameter();
        // 计算日
        parameter.setCalDate(BasicUtil.dateToLocal(tempSettleDate));
        // 资产信息
        SecInfoModel secInfoModel = new SecInfoModel();
        // 债券起息日
        secInfoModel.setStartDate(BasicUtil.dateToLocal(finProduct.getVdate()));
        // 债券到期日
        secInfoModel.setEndDate(BasicUtil.dateToLocal(finProduct.getActmdate()));
        // 债券首次付息日
        Date firstPayDate = listPayinfo != null && listPayinfo.size() > 0 ? listPayinfo.get(
            0).getFirstPayDate() : null;
        secInfoModel.setFirstPayDate(BasicUtil.dateToLocal(firstPayDate));
        // 发行价
        secInfoModel.setIssuePrice(finProduct.getIssuePrice());
        // 债券类型
        Variety bondsType = null;
        if (DictCodeList.息票品种_贴现.getValue().equals(finProduct.getCouponSpecies())) {
            bondsType = Variety.贴现;
        }
        else if (DictCodeList.息票品种_零息.getValue().equals(finProduct.getCouponSpecies())) {
            bondsType = Variety.零息;
        }
        else if (DictCodeList.息票品种_附息.getValue().equals(finProduct.getCouponSpecies())) {
            bondsType = Variety.附息;
        }
        else if (DictCodeList.息票品种_利随本清.getValue().equals(finProduct.getCouponSpecies())) {
            bondsType = Variety.利随本清;
        }
        secInfoModel.setBondsType(bondsType);
        // 计息基础
        Basis basis = null;
        String basisString = listCashinfo != null
                             && listCashinfo.size() > 0 ? listCashinfo.get(0).getBasis() : null;
        if (DictCodeList.计息基础_A_360.getValue().equals(basisString)) {
            basis = Basis.A_360;
        }
        else if (DictCodeList.计息基础_A_365.getValue().equals(basisString)) {
            basis = Basis.A_365;
        }
        else if (DictCodeList.计息基础_A_A_平均分配.getValue().equals(basisString)
                 || DictCodeList.计息基础_A_A_按实际天数分配.getValue().equals(basisString)) {
            basis = Basis.A_A;
        }
        else if (DictCodeList.计息基础_A_365F.getValue().equals(basisString)) {
            basis = Basis.A_365F;
        }
        else if (DictCodeList.计息基础_30_360.getValue().equals(basisString)) {
            basis = Basis._30_360;
        }
        else if (DictCodeListExt3.计息基础_30U_360.getValue().equals(basisString)) {
            basis = Basis._30U_360;
        }
        
        secInfoModel.setBasis(basis);
        // 利率类型
        IntRateType intRateType = null;
        String intRateTypeString = listCashinfo != null
                                   && listCashinfo.size() > 0 ? listCashinfo.get(
                                       0).getCalcType() : null;
        if (DictCodeList.利率类型_固定利率.getValue().equals(intRateTypeString)) {
            intRateType = IntRateType.固定利率;
        }
        else if (DictCodeList.利率类型_浮动利率.getValue().equals(intRateTypeString)) {
            intRateType = IntRateType.浮动利率;
        }
        intRateType = IntRateType.浮动利率;
        secInfoModel.setIntRateType(intRateType);
        // 利息分配方式
        AllocationType allocationType = null;
        if (DictCodeList.计息基础_A_A_平均分配.getValue().equals(basisString)) {
            allocationType = AllocationType.平均分配;
        }
        else if (DictCodeList.计息基础_A_A_按实际天数分配.getValue().equals(basisString)) {
            allocationType = AllocationType.按实际天数分配;
        }
        else {
            allocationType = AllocationType.按实际天数分配;
        }
        secInfoModel.setAllocationType(allocationType);
        // 付息计划推算方式
        SecIntSchCalMethod secIntSchCalMethod = null;
        String secIntSchCalMethodString = listPayinfo != null
                                          && listPayinfo.size() > 0 ? listPayinfo.get(
                                              0).getCalcRule() : null;
        if (DictCodeList.推算方式_起息日向后.getValue().equals(secIntSchCalMethodString)) {
            secIntSchCalMethod = SecIntSchCalMethod.起息日向后;
        }
        else if (DictCodeList.推算方式_到期日向前.getValue().equals(secIntSchCalMethodString)) {
            secIntSchCalMethod = SecIntSchCalMethod.到期日向前;
        }
        else if (DictCodeList.推算方式_首次支付日.getValue().equals(secIntSchCalMethodString)) {
            secIntSchCalMethod = SecIntSchCalMethod.首次付息日;
        }
        secInfoModel.setCalMethod(secIntSchCalMethod);
        // 付息频率
        IntFrequency yearIntRate = null;
        String yearIntRateString = listPayinfo != null && listPayinfo.size() > 0 ? listPayinfo.get(
            0).getPayCycle() : null;
        if (DictCodeList.频率_月.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.月付;
        }
        else if (DictCodeList.频率_季.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.季付;
        }
        else if (DictCodeList.频率_年.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.年付;
        }
        else if (DictCodeList.频率_半年.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.半年付;
        }
        else {
            yearIntRate = IntFrequency.年付;
        }
        secInfoModel.setYearIntRate(yearIntRate);
        parameter.setSecInfoModel(secInfoModel);

        // 付息计划列表
        List<SecIntSchedule> secIntScheduleList = new ArrayList<SecIntSchedule>();
        if (listIntinfo != null && listIntinfo.size() > 0) {
            for (TargetCashPlan targetCashPlan : listIntinfo) {
                SecIntSchedule secIntSchedule = new SecIntSchedule();
                // 理论起始日
                secIntSchedule.setTheoryStartDate(
                    targetCashPlan.getVdateTerm() == null ? BasicUtil.dateToLocal(
                        targetCashPlan.getVdate()) : BasicUtil.dateToLocal(
                            targetCashPlan.getVdateTerm()));
                // 理论截止日
                secIntSchedule.setTheoryCutoffDate(
                    targetCashPlan.getMdateTerm() == null ? BasicUtil.dateToLocal(
                        targetCashPlan.getMdate()) : BasicUtil.dateToLocal(
                            targetCashPlan.getMdateTerm()));
                // 实际起始日
                secIntSchedule.setActualStartDate(
                    BasicUtil.dateToLocal(targetCashPlan.getVdate()));
                // 实际截止日
                secIntSchedule.setActualCutoffDate(
                    BasicUtil.dateToLocal(targetCashPlan.getMdate()));
                // 计息本金
                secIntSchedule.setCorpus(new BigDecimal(100));
                // 利息金额
                secIntSchedule.setAmount(targetCashPlan.getCashAmt());
                secIntScheduleList.add(secIntSchedule);
            }
            parameter.setSecIntScheduleList(secIntScheduleList);
        }

        // 债券利率重置信息
        List<PaperIrReset> paperIrResetList = new ArrayList<PaperIrReset>();
        if (listCashinfo != null && listCashinfo.size() > 0) {
            for (TargetCashCalcRule targetCashCalcRule : listCashinfo) {
                PaperIrReset paperIrReset = new PaperIrReset();
                // 票面利率生效日
                paperIrReset.setParRateEffDate(
                    BasicUtil.dateToLocal(targetCashCalcRule.getEffDate()));
                // 票面利率
                paperIrReset.setParRate(targetCashCalcRule.getYield());
                paperIrResetList.add(paperIrReset);
            }
            parameter.setPaperIrResetList(paperIrResetList);
        }

        // 当前面值
        parameter.setPaperAmt(faceValue);
        
        // 计算百元面值的债券的利息
        AccurIntCalculator calculator2 = CalculatorFactory.createAccurIntCalculator();
        AccurIntCalOutParameter output2 = calculator2.calculate(parameter); 
        BigDecimal accurInt = output2.getInterest();
        
        //银行间债券在计算应计利息时的舍位位数
        String SEC_INT_SCALE_BANK = CacheUtil.getSysParam("SEC_INT_SCALE_BANK");
        //交易所债券在计算应计利息时的舍位位数
        String SEC_INT_SCALE_EXCHANGE = CacheUtil.getSysParam("SEC_INT_SCALE_EXCHANGE");
        //控制是先计算利息还是先对应计利息进行舍位：Y-先舍位；N-先计算
        String SEC_INT_SCALE = CacheUtil.getSysParam("SEC_INT_SCALE");
        
        if(SEC_INT_SCALE==null||SEC_INT_SCALE.equals(DictCodeList.是否_是.getValue())){
            // if（标的信息.交易场所 = "银行间"）
            if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(finProduct.getTradeMarket())) {
                // 调用金额共通方法设置有效小数位,小数位数取值共通参数 SEC_INT_SCALE_BANK
            	accurInt = ArithmeticUtil.round(accurInt,
                        Integer.parseInt(StringUtils.isEmpty(SEC_INT_SCALE_BANK) ? "12" : SEC_INT_SCALE_BANK),null);
            }
            // else if（标的信息.交易场所 = "上交所" or "深交所"）
            else if (DictCodeListExt1.估值交易市场_上交所.getValue().equals(finProduct.getTradeMarket()) || 
            			DictCodeListExt1.估值交易市场_深交所.getValue().equals(finProduct.getTradeMarket())) {
                // 调用金额共通方法设置有效小数位,小数位数取值共通参数 SEC_INT_SCALE_EXCHANGE	
            	accurInt = ArithmeticUtil.round(accurInt,
                        Integer.parseInt(StringUtils.isEmpty(SEC_INT_SCALE_EXCHANGE) ? "8" : SEC_INT_SCALE_EXCHANGE),null);
            }
            else {
                // 调用金额共通方法设置有效小数位,小数位数取值共通参数 SEC_INT_SCALE_BANK
            	accurInt = ArithmeticUtil.round(accurInt,
                        Integer.parseInt(StringUtils.isEmpty(SEC_INT_SCALE_BANK) ? "12" : SEC_INT_SCALE_BANK),null);
            }
            // 变量.本日应计利息余额 =round(上述返回值,舍位参数) * 变量.百元券面面值 /100 * 变量.变量.当日计算成本科目数量
            result = ArithmeticUtil.multiply(accurInt, costNum);
            result = ArithmeticUtil.multiply(result, faceValue);
            result = ArithmeticUtil.divide(result,new BigDecimal(100), NumberEnum.HIGH_PREC_NUM.getCode());
        }else{
            // 变量.本日应计利息余额 =round (上述返回值  * 变量.百元券面面值 /100,舍位参数)* 变量.变量.当日计算成本科目数量
        	result = ArithmeticUtil.multiply(accurInt, faceValue);
        	result = ArithmeticUtil.divide(result,new BigDecimal(100), NumberEnum.HIGH_PREC_NUM.getCode());
            // if（标的信息.交易场所 = "银行间"）
            if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(finProduct.getTradeMarket())) {
                // 调用金额共通方法设置有效小数位,小数位数取值共通参数 SEC_INT_SCALE_BANK
            	result = ArithmeticUtil.round(result,
                        Integer.parseInt(StringUtils.isEmpty(SEC_INT_SCALE_BANK) ? "12" : SEC_INT_SCALE_BANK),null);
            }
            // else if（标的信息.交易场所 = "上交所" or "深交所"）
            else if (DictCodeListExt1.估值交易市场_上交所.getValue().equals(finProduct.getTradeMarket()) || 
            			DictCodeListExt1.估值交易市场_深交所.getValue().equals(finProduct.getTradeMarket())) {
                // 调用金额共通方法设置有效小数位,小数位数取值共通参数 SEC_INT_SCALE_EXCHANGE	
            	result = ArithmeticUtil.round(result,
                        Integer.parseInt(StringUtils.isEmpty(SEC_INT_SCALE_EXCHANGE) ? "8" : SEC_INT_SCALE_EXCHANGE),null);
            }
            else {
                // 调用金额共通方法设置有效小数位,小数位数取值共通参数 SEC_INT_SCALE_BANK
            	result = ArithmeticUtil.round(result,
                        Integer.parseInt(StringUtils.isEmpty(SEC_INT_SCALE_BANK) ? "12" : SEC_INT_SCALE_BANK),null);
            }
            result = ArithmeticUtil.multiply(result, costNum);
        }
        return ArithmeticUtil.round(result, NumberEnum.MONEY);
    }
    /**
     * 通用资产日间利息计算
     * @param finProduct 标的信息
     * @param costBalance 计息基数
     * @param accrType  计提方式
     * @param happenDate 计算日
     * @return
     */
    public BigDecimal calCommonIntAmt(TargetFinProduct finProduct,BigDecimal costBalance,String accrType,Date happenDate){
    	BigDecimal result = BigDecimal.ZERO;
    	
    	// 查询条件
        List<TargetSearchDto> queryCriteria = new ArrayList<TargetSearchDto>();
        
        // 付息计划条件
        TargetSearchDto searchIntinfo = new TargetSearchDto();
        searchIntinfo.setQueryType(ValDictCodeList.标的_现金流计划.getValue());
        searchIntinfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        queryCriteria.add(searchIntinfo);
        
        // 报价计划条件
        TargetSearchDto searchCashinfo = new TargetSearchDto();
        searchCashinfo.setQueryType(ValDictCodeList.标的_现金流计算规则.getValue());
        searchCashinfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        searchCashinfo.setEffdateEnd(DateUtils.addDays(happenDate, -1));
        queryCriteria.add(searchCashinfo);
        
        
        // 现金流支付规则条件
        TargetSearchDto searchPayinfo = new TargetSearchDto();
        searchPayinfo.setQueryType(ValDictCodeList.标的_现金流支付规则.getValue());
        searchPayinfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        queryCriteria.add(searchPayinfo);
        
        
        Map<TargetSearchDto, Object> targetSearchMap = finProductCommonBatchService.getTargetInfo(finProduct.getFinprodId(), 
        		queryCriteria);
        //定义 变量.现金流计划DTO = null
        TargetCashPlan finCashPlan = null;
        
        
        @SuppressWarnings("unchecked")
		List<TargetCashPlan> listIntinfo = targetSearchMap.get(
                searchIntinfo) == null ? null : (List<TargetCashPlan>)targetSearchMap.get(
                    searchIntinfo);
    	for(TargetCashPlan obj:listIntinfo){
    		if(happenDate.compareTo(obj.getVdate())>=0 && happenDate.compareTo(obj.getMdate())<0){
    			finCashPlan = obj;
    		}
    	}
    	
    	//定义 变量.现金流计算规则List = null			
    	@SuppressWarnings("unchecked")
		List<TargetCashCalcRule> finCashCalcRuleList = targetSearchMap.get(
                searchCashinfo) == null ? null : (List<TargetCashCalcRule>)targetSearchMap.get(
                    searchCashinfo);
    	
    	@SuppressWarnings("unchecked")
		List<TargetCashPayRule> listPayinfo = targetSearchMap.get(
                searchPayinfo) == null ? null : (List<TargetCashPayRule>)targetSearchMap.get(
                    searchPayinfo);
    	//定义 变量.现金流支付规则
    	TargetCashPayRule targetCashPayRule = listPayinfo != null && listPayinfo.size()>0?listPayinfo.get(0):null;
    	
    	//定义 变量.计息基础		
    	String basis = finCashCalcRuleList != null
                && finCashCalcRuleList.size() > 0 ? finCashCalcRuleList.get(0).getBasis() : null;
                
    	//1.计提方式_每日新增
    	if(DictCodeList.计提方式_区间倒减.getValue().equals(accrType)){
    		AssetCommonInfoDto scai = new AssetCommonInfoDto();
            scai.setBasis(basis);
            scai.setIntpayRule(DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
            scai.setVdate(finCashPlan.getVdate());
            scai.setMdate(finProduct.getMdate());
            
            int ruleSize = finCashCalcRuleList.size();
            for(int i=0;i<ruleSize;i++) {
            	TargetCashCalcRule finCashCalcRule= finCashCalcRuleList.get(i);
            	FinCashPlanEx finCashPlanDto = new FinCashPlanEx();
            	
                finCashPlanDto.setVdate(finCashCalcRule.getEffDate().compareTo(scai.getVdate()) < 0 ?
                		scai.getVdate() : finCashCalcRule.getEffDate());
                finCashPlanDto.setMdate((i==finCashCalcRuleList.size()-1 || ruleSize<2) ? happenDate:
                	finCashCalcRuleList.get(i+1).getEffDate());
                if(finCashPlanDto.getMdate().compareTo(finCashPlanDto.getVdate()) <0) {
                	continue;
                }

                FinCashPlanDetailDto finCashPlanDetailDto = new FinCashPlanDetailDto();
                finCashPlanDetailDto.setVdate(finCashPlanDto.getVdate());
                finCashPlanDetailDto.setMdate(finCashPlanDto.getMdate());
                finCashPlanDetailDto.setYield(finCashCalcRuleList.get(i).getYield());
                finCashPlanDetailDto.setIntPrinAmt(costBalance);
                try {
	            	BigDecimal cintAmt = InterestSubAmtCal.cal(finCashPlanDto, finCashPlanDetailDto, targetCashPayRule, scai);
	            	//避免出现负数的利息
	            	if(cintAmt.compareTo(BigDecimal.ZERO)<0) {
	            		cintAmt = BigDecimal.ZERO;
	            	}
	            	result = ArithmeticUtil.doAdd(result,cintAmt);
	            } catch (ParseException e) {
	                logger.error("计算利息失败", e);
	            }
            }
    	}
    	//2.计提方式_每日新增
    	else if(DictCodeList.计提方式_每日新增.getValue().equals(accrType)){
    		//本金列表
    		List<PrincipalInfo> prinList = new ArrayList<PrincipalInfo>();
    		PrincipalInfo principalInfo = new PrincipalInfo();
    		// modify by caoguo 防止活期类资产没有付息计划
            Date vdate = finCashPlan != null ? finCashPlan.getVdate() : finProduct.getVdate();
    		principalInfo.setChangeDate(vdate);
    		principalInfo.setPrinAmount(costBalance);
    		
    		//报价列表
    		List<QuoteInfo> quoteList = new ArrayList<QuoteInfo>();
    		for(TargetCashCalcRule calcRule:finCashCalcRuleList){
    			QuoteInfo quoteInfo = new QuoteInfo();
    			quoteInfo.setAccountBasis(calcRule.getBasis());
    			quoteInfo.setInterest(calcRule.getYield());
    			quoteInfo.setIssueDate(calcRule.getEffDate());
    		}
    		
    		// 1.分段区间计算，调用共通方法【分段区间计算】
            List<InvRateQuo> invRateQuoList = new ArrayList<>(quoteList.size());
            for (QuoteInfo quote : quoteList) {
                InvRateQuo rate = new InvRateQuo();
                if (null == quote.getInterest()) {
                    rate.setPaperRate(BigDecimal.ZERO);
                }
                else {
                    rate.setPaperRate(quote.getInterest());
                }
                rate.setStartDate(quote.getIssueDate());
                rate.setAccountBasis(quote.getAccountBasis());
                invRateQuoList.add(rate);
            }
            List<InvRepaySchedule> invRepayScheduleList = new ArrayList<>(prinList.size());
            for (PrincipalInfo prin : prinList) {
                InvRepaySchedule invRepay = new InvRepaySchedule();
                invRepay.setActMdate(prin.getChangeDate());
                invRepay.setRepayAmt(prin.getPrinAmount());
                invRepayScheduleList.add(invRepay);
            }
            // 调用共通方法【分段区间计算】
            List<InvRpayscheDetail> tmpList = RepayScheduleSubCal.cal(vdate, happenDate,
                invRateQuoList, invRepayScheduleList, BigDecimal.ZERO, InvRpayscheDetail.class);

            // 分段付息区间列表
            List<SecIntRange> secIntRangeList = new ArrayList<>();
            if (null != tmpList) {
                for (InvRpayscheDetail detail : tmpList) {
                    SecIntRange sec = new SecIntRange();
                    sec.setStartDate(detail.getVdate());
                    sec.setEndDate(detail.getMdate());
                    sec.setIntAmt(detail.getAmt());
                    sec.setIntRate(detail.getYield());
                    sec.setPrinInt(detail.getCapi());
                    sec.setAccountBasis(detail.getAccountBasis());
                    secIntRangeList.add(sec);
                }
            }
            // 2.利息计算
            for (SecIntRange secIntRange : secIntRangeList) {
            	//调用共通设计->【详细设计-计算类共通方法设计.xlsx】->计息基础天数																		
        		BigDecimal basisDays = BasisDaysCal.cal(basis, 
        				listPayinfo != null  && listPayinfo.size() > 0 ? listPayinfo.get(0).getCalcRule() : null, 
        				new Date[] {
        						secIntRange.getStartDate(), //区间开始日
        						secIntRange.getStartDate(), //资产起息日
        						secIntRange.getEndDate(),//资产到期日
        						listPayinfo != null && listPayinfo.size() > 0 ? listPayinfo.get(0).getFirstPayDate() : null//首次付息日
        								});
                BigDecimal intAmt = ArithmeticUtil.round(
                						ArithmeticUtil.divide(
                								ArithmeticUtil.multiply(secIntRange.getPrinInt(),secIntRange.getIntRate()), 
                								basisDays), 
                						NumberEnum.MONEY);
                BigDecimal dayCount = CalendarUtil.calendarDays(secIntRange.getStartDate(), secIntRange.getEndDate());
                
                intAmt = ArithmeticUtil.round(ArithmeticUtil.multiply(intAmt,dayCount),NumberEnum.MONEY);
                
                result = ArithmeticUtil.doAdd(result, intAmt);
            }
    	}
    	return result;
    }
    
    /**
     * 删除【统计分析明细表（资产减值）】表
     * @param param
     * @return
     */
    public int deleteBokStatLossPosiiton(Map<String, Object> param){
    	return lossCommonMapper.deleteBokStatLossPosiiton(param);
    }
    
    
    /**
     * P3阶段查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param serDate
     * @param finprodId
     * @return P3阶段数量
     * @see
     * @since 1.0
     */
    public Long getP3num(Date serDate, String finprodId) {
        Map<String, Object> param = new HashMap<String , Object>();
        param.put("serDate", serDate);
        param.put("finprodId", finprodId);
        param.put("status", DictCodeListExt1.交易状态_初始.getValue());
        param.put("stage", DictCodeListExt3.阶段_买入后出现减值_P3.getValue());
        return lossCommonMapper.getP3num(param);
    }
    
    /**
     * 
     * 是否进入或转出三阶段
     * <功能详细描述>
     * @param serDate 查询日
     * @param finprodId 金融产品代码
     * @param tdyP3num 当日P3阶段数量
     * @param ystP3num 前一日P3阶段数量
     * @return 是否进入或转出三阶段
     * @see
     * @since 1.0
     */
    public boolean isP3InOrOut(Date serDate, String finprodId, Long tdyP3num, Long ystP3num) {
        if (tdyP3num == null) {
            tdyP3num = getP3num(serDate, finprodId);
        }
        if (ystP3num == null) {
            ystP3num = getP3num(DateUtil.addDay(serDate,-1), finprodId);
        }
        if ((tdyP3num.longValue() > 0 && ystP3num.longValue() == 0)
            || (tdyP3num.longValue() == 0 && ystP3num.longValue() > 0)) {
            return true;
        }
        return false;
    }
}
