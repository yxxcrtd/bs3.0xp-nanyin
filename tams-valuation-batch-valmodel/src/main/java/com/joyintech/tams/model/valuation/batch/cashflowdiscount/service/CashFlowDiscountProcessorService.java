/*
 * 文件名：AssetIntdateCarryoverProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.valuation.batch.cashflowdiscount.service;

import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.constant.IntRateType;
import com.joyintech.calculation.shell.AccurIntCalculator;
import com.joyintech.calculation.shell.param.AccurIntCalInParameter;
import com.joyintech.calculation.shell.param.AccurIntCalOutParameter;
import com.joyintech.calculation.shell.param.SecInfoModel;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.*;
import com.joyintech.tams.model.valuation.batch.calculation.CalculatorFactory;
import com.joyintech.tams.model.valuation.batch.calculation.config.NumberEnum;
import com.joyintech.tams.model.valuation.batch.calculation.constant.*;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CurveInfoDto;
import com.joyintech.tams.model.valuation.batch.calculation.entity.KeyPointDto;
import com.joyintech.tams.model.valuation.batch.calculation.shell.calculator.DisCountFullPriceCalculator;
import com.joyintech.tams.model.valuation.batch.calculation.shell.calculator.DisCountSpreadCalculator;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.DisCountInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.DisCountOutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.util.BasicUtil;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dao.CashFlowDiscountMapper;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.*;
import com.joyintech.tams.valuation.common.dto.modelDto.*;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.batch.service.ModValuationCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 现金流贴现批处理- processor相关业务处理
 * @author zznn
 * @date 20210706
 */
@Service
@Scope("prototype")
public class CashFlowDiscountProcessorService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ModValuationCommonService modValuationCommonService;

	@Autowired
	private CashFlowDiscountMapper cashFlowDiscountMapper;

	@Autowired
	private ModLogsService modLogsService;
	/**
	 * 处理
	 * @param param1
	 * @return
	 */
	public CashFlowDiscountResult execute(Map<String, Object> param1) {
		// 获取reader返回值
		CashFlowDiscountReaderResult entity = (CashFlowDiscountReaderResult) param1
				.get("READER_RESULT");

		// 若reader传递的参数或计提基础信息列表为空，不进行后续处理
		if (entity == null) {
			return null;
		}
		// 定义返回值list
		CashFlowDiscountResult result = new CashFlowDiscountResult();
		//贴现估值结果
		ModCurveResult modCurveResult = new ModCurveResult();
		//贴现估值结果明细集合
		List<ModCurveResultDetail> modCurveResultDetails = new ArrayList<>();
		//定价数据
		ModRiskPremium modRiskPremium = new ModRiskPremium();
		//资产代码
		String finprodId = entity.getFinprodId();
		Date happenDate = (Date) param1.get("HAPPEN_DATE"); // 发生日：批处理任务执行信息.处理日（发生日）
		SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param1.get("BATCH_INFO");
		//处理业务数据
		//是否资产定价
		boolean isAssetPrice = false;
		//资产定价基准日
		Date assetPriceBaseDate = happenDate;
		try{
			if(null==entity.getModCurveConfig()){
				modLogsService.error(finprodId, batchExecuteInfo.getBatchExedetailSeqNo(), batchExecuteInfo.getTaskId(),
						"【现金流贴现模型批处理】：未配置未配置收益率曲线");
			}
			if(StringUtil.isEmptyList(entity.getModCashFlows())){
				modLogsService.error(finprodId, batchExecuteInfo.getBatchExedetailSeqNo(), batchExecuteInfo.getTaskId(),
						"【现金流贴现模型批处理】：资产无未来现金流");
			}
			if(!StringUtil.isEmptyList(entity.getModCashFlowPayRules())){
				if(StringUtil.isEmpty(entity.getModCashFlowPayRules().get(0).getPayCycle())){
					modLogsService.error(finprodId, batchExecuteInfo.getBatchExedetailSeqNo(), batchExecuteInfo.getTaskId(),
							"【现金流贴现模型批处理】：资产无付息频率");
				}
			}
			if(!StringUtil.isEmptyList(entity.getModCashFlowCalRules())){
				if(StringUtil.isEmpty(entity.getModCashFlowCalRules().get(0).getBasis())){
					modLogsService.error(finprodId, batchExecuteInfo.getBatchExedetailSeqNo(), batchExecuteInfo.getTaskId(),
							"【现金流贴现模型批处理】：资产无计息基础");
				}
			}
			if(null==entity.getModRateData()){
				modLogsService.warn(finprodId, batchExecuteInfo.getBatchExedetailSeqNo(), batchExecuteInfo.getTaskId(),
						"【现金流折现模型批处理】：资产无评级，采用最小利差法反算");
			}
			//资产定价基准价格
			BigDecimal assetPriceBasePrice = null;
			if(DateUtil.compareDate(entity.getLastTradeInDate(),happenDate)==0){
				isAssetPrice = true;
				assetPriceBaseDate = entity.getLastTradeInDate();
			}
			for (ModCashFlowCalRuleDto item : entity.getModCashFlowCalRules()) {
				if(DateUtil.compareDate(item.getEffDate(),happenDate)==0){
					isAssetPrice = true;
					assetPriceBaseDate = item.getEffDate();
					break;
				}
			}
			if(isAssetPrice){
				assetPriceBasePrice = entity.getLastTradeAvgPrice();
			}else{
				Map<String,Object> param = new HashMap<>();
				param.put("finprodId", finprodId);
				param.put("basedate",happenDate);
				ModRiskPremium modRiskPremium2 = cashFlowDiscountMapper.getModRiskPremium(param);
				if(null==modRiskPremium2){
					isAssetPrice = true;
					assetPriceBasePrice = entity.getLastTradeAvgPrice();
				}else{
					BeanUtil.copyPropertiesIgnoreNull(modRiskPremium,modRiskPremium2);
				}
			}
			//现金流同屏合并
			entity.setModCashFlows(BasicUtil.constructArr(entity.getModCashFlows()));
			Map<String, List<ModYieldCurveInfoDto>> calCurveInfo = new HashMap<>();
			if(isAssetPrice){
				modRiskPremium.setBasedate(assetPriceBaseDate);
				modRiskPremium.setbFullprice(assetPriceBasePrice);
				//曲线行情处理
				calCurveInfo = handleCurve(entity, assetPriceBaseDate);
				if(calCurveInfo.isEmpty()){
					modLogsService.warn(finprodId, batchExecuteInfo.getBatchExedetailSeqNo(), batchExecuteInfo.getTaskId(),
							"【现金流折现模型批处理】：当天无收益率曲线行情");
				}
				//处理风险溢价
				modRiskPremium = handleModRiskPremium(modRiskPremium,entity,assetPriceBaseDate,assetPriceBasePrice,calCurveInfo);
				//资产全价计算
				//处理曲线代码
				modRiskPremium.setModMethod(ModelDictCodeList.模型_估值模型名称_现金流贴现法.getValue());
				modRiskPremium.setSeqno(IDGenerator.generate());
			}
			String curveCode = handlecurveCode(entity,modRiskPremium);
			//如果全价计算基准日 = 资产定价定准日，且做了资产重定价，复用上面得行情
			if(!(isAssetPrice&&DateUtil.compareDate(happenDate,assetPriceBaseDate)==0)){
				//曲线行情处理
				calCurveInfo = handleCurve(entity, assetPriceBaseDate);
			}
			//计算应计利息
			BigDecimal accruedInterest = calAccruedInterest(entity,happenDate);
			//计算全价
			DisCountInputParameter disCountInputParameter = calDiscountFullPrice(entity, happenDate, modRiskPremium.getSpreadAdj(), calCurveInfo.get(curveCode));
			String resultSeqNo = IDGenerator.generate();
			modCurveResult.setSeqno(resultSeqNo);
			modCurveResult.setFinprodId(finprodId);
			modCurveResult.setCdate(happenDate);
			modCurveResult.setFullPrice(disCountInputParameter.getFullprice());
			modCurveResult.setNetPrice(ArithmeticUtil.subtract(modCurveResult.getFullPrice(),accruedInterest));
			modCurveResult.setCurveSpread(modRiskPremium.getSpread());
			modCurveResult.setValModel(ModelDictCodeList.模型_估值模型名称_现金流贴现法.getValue());
			//处理估值明细
			if(!StringUtil.isEmptyList(disCountInputParameter.getCashInfoDtoList())){
				disCountInputParameter.getCashInfoDtoList().stream().forEach(item ->{
					ModCurveResultDetail modCurveResultDetail = new ModCurveResultDetail();
					modCurveResultDetail.setSeqno(IDGenerator.generate());
					modCurveResultDetail.setResultSeqno(resultSeqNo);
					modCurveResultDetail.setFinprodId(finprodId);
					modCurveResultDetail.setCdate(happenDate);
					modCurveResultDetail.setCashDate(BasicUtil.localToDate(item.getActualCutoffDate()));
					modCurveResultDetail.setPeriod(item.getRemainingTerm());
					modCurveResultDetails.add(modCurveResultDetail);
				});
			}
			result.setModCurveResult(modCurveResult);
			result.setModCurveResultDetails(modCurveResultDetails);
			result.setModRiskPremium(modRiskPremium);
		}catch (Exception e){
			e.printStackTrace();
			logger.error("现金流贴现批处理processor相关业务处理异常:资产代码="+entity.getFinprodId()+"===="+e.getMessage());
			modLogsService.warn(finprodId, batchExecuteInfo.getBatchExedetailSeqNo(), batchExecuteInfo.getTaskId(),
					"【现金流折现模型批处理】：计算错误");
		}
		return result;

	}

	/**
	 * 处理计算全价时用到的曲线代码
	 * @param entity
	 * @param modRiskPremium
	 * @return
	 */
    private String handlecurveCode(CashFlowDiscountReaderResult entity,ModRiskPremium modRiskPremium){
		String curveCode =StringUtil.EMPTY;
		if(null==entity.getModRateData()){
			//这里查出来得肯定只有一条
			Map<String,Object> paramCurveCode = new HashMap<>();
			ModCurveConfig modCurveConfig = entity.getModCurveConfig();
			paramCurveCode.put("curveGroupCode", modCurveConfig.getCurveGroupCode());
			//MOD_CURVE_CONFIG与MOD_CURVE_MAPPING曲线类型不一样  需要转换
			paramCurveCode.put("curveType", StringUtil.equals(modCurveConfig.getCurveType(),"01")?"SP":"MA");
			paramCurveCode.put("graderesult",modRiskPremium.getGradeResult());
			List<String> curveCodes = cashFlowDiscountMapper.getCurveCode(paramCurveCode);
			if(!StringUtil.isEmptyList(curveCodes)){
				curveCode = curveCodes.get(0);
			}else{
				logger.error("计算资产全价时,评级信息为空的情况下,曲线代码必须存在");
			}
		}else{
			if(!StringUtil.isEmptyList(entity.getCurveCodes())){
				curveCode = entity.getCurveCodes().get(0);
			}
		}
		return curveCode;
	}
	/**
	 * 曲线行情处理
 	 * @param entity
	 * @param calDate
	 * @return
	 */
	private Map<String,List<ModYieldCurveInfoDto>>  handleCurve(CashFlowDiscountReaderResult entity,Date calDate){
		//曲线行情处理
		ModCurveConfig modCurveConfig = entity.getModCurveConfig();
		Map<String,List<ModYieldCurveInfoDto>> calCurveInfo = new HashMap<>();
		if(null!=modCurveConfig){
			//计算用的曲线行情
			Date smoothStartDate = calDate;
			if(StringUtil.equals(modCurveConfig.getIssmooth(), "Y")){
				if(StringUtil.equals(modCurveConfig.getSmoothsection(),ModelDictCodeList.模型_平滑区间_三个月.getValue())){
					smoothStartDate = DateUtil.addMonthDate(calDate,-3);
				}else{
					smoothStartDate = DateUtil.addMonthDate(calDate,-1);
				}
				ModYeildCurveParamsDto modYeildCurveParamsDto = new ModYeildCurveParamsDto();
				modYeildCurveParamsDto.setYeildIdList(entity.getCurveCodes());
				modYeildCurveParamsDto.setStartDate(smoothStartDate);
				modYeildCurveParamsDto.setSearchDate(calDate);
				Map<String, Map<Date, List<ModYieldCurveInfoDto>>> yeildCurveIntervalinfo = modValuationCommonService.getYeildCurveIntervalinfo(modYeildCurveParamsDto);
				for (String item : entity.getCurveCodes()) {
					Map<Date, List<ModYieldCurveInfoDto>> dateListMap = yeildCurveIntervalinfo.get(item);
					List<ModYieldCurveInfoDto> curveSmoothDtos = this.calCurveSmooth(dateListMap, calDate);
					calCurveInfo.put(item,curveSmoothDtos);
				}
			}
			else{
				ModYeildCurveParamsDto modYeildCurveParamsDto = new ModYeildCurveParamsDto();
				modYeildCurveParamsDto.setYeildIdList(entity.getCurveCodes());
				modYeildCurveParamsDto.setSearchDate(calDate);
				calCurveInfo = modValuationCommonService.getCurveInfo(modYeildCurveParamsDto);
			}
		}
		return calCurveInfo;
	}

	private ModRiskPremium handleModRiskPremium(ModRiskPremium modRiskPremium,CashFlowDiscountReaderResult entity,Date calDate,BigDecimal price,Map<String,List<ModYieldCurveInfoDto>> calCurveInfoMap){
		//点差
		BigDecimal spread = null;
		//反算后现金流折现法计算参数
		DisCountInputParameter caldisCountInputParameter = null;
		//计算用曲线
		String curveCode = StringUtil.EMPTY;
		//遍历曲线行情
		Iterator<Map.Entry<String, List<ModYieldCurveInfoDto>>> iterator = calCurveInfoMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, List<ModYieldCurveInfoDto>> next = iterator.next();
			curveCode = next.getKey();
			List<ModYieldCurveInfoDto> value = next.getValue();
			DisCountInputParameter disCountInputParameter = calRiskPremium(entity, calDate, price, value);
			if(null==spread||spread.compareTo(disCountInputParameter.getSpread())>0){
				spread = disCountInputParameter.getSpread();
				caldisCountInputParameter = disCountInputParameter;
			}
		}
		//处理评级信息
		handleRateInfo(modRiskPremium,entity.getModRateData(),curveCode);
		//处理定价其他属性值
		modRiskPremium.setSecTypeModel(entity.getModCurveConfig().getSecTypeModel());
		modRiskPremium.setSecDetailTypeModel(entity.getModCurveConfig().getSecDetailTypeModel());
		modRiskPremium.setFinprodId(entity.getFinprodId());
		modRiskPremium.setFinprodName(entity.getFinprodAbbr());
		if(null!=caldisCountInputParameter){
			modRiskPremium.setBasedate(BasicUtil.localToDate(caldisCountInputParameter.getCalDate()));
		}
		modRiskPremium.setbFullprice(spread);
		modRiskPremium.setSpread(spread);
		if(null!=modRiskPremium.getSpreadAdj()){
			modRiskPremium.setSpreadAdj(ArithmeticUtil.doAdd(modRiskPremium.getbFullprice(),modRiskPremium.getSpreadAdj()));
		}else{
			modRiskPremium.setSpreadAdj(modRiskPremium.getbFullprice());
		}
		return modRiskPremium;
	}

	/**
	 * 调用现金流折现法点差计算
	 * @param entity
	 * @param calDate
	 * @param assetPriceBasePrice
	 * @param modYieldCurveInfos
	 * @return
	 */
	private DisCountInputParameter calRiskPremium(CashFlowDiscountReaderResult entity,Date calDate,BigDecimal assetPriceBasePrice,List<ModYieldCurveInfoDto> modYieldCurveInfos){
		DisCountInputParameter disCountInputParameter = encapsDisCountInputParameter(entity, calDate, assetPriceBasePrice, modYieldCurveInfos);
		//调用现金流折现法点差计算
		DisCountSpreadCalculator disCountSpreadCalculator = CalculatorFactory.createDisCountSpreadCalculator();
		DisCountOutputParameter disCountOutputParameter = disCountSpreadCalculator.calculate(disCountInputParameter);
		disCountInputParameter.setSpread(disCountOutputParameter.getSpread());
		return disCountInputParameter;
	}

	/**
	 * 现金流折现法全价计算
	 * @param entity
	 * @param calDate
	 * @param spreadAdj
	 * @param modYieldCurveInfos
	 * @return
	 */
	private DisCountInputParameter calDiscountFullPrice(CashFlowDiscountReaderResult entity,Date calDate,BigDecimal spreadAdj,List<ModYieldCurveInfoDto> modYieldCurveInfos){
		DisCountInputParameter disCountInputParameter = encapsDisCountInputParameter(entity, calDate, spreadAdj, modYieldCurveInfos);
		//调用现金流折现法全价计算
		DisCountFullPriceCalculator disCountFullPriceCalculator = CalculatorFactory.createDisCountFullPriceCalculator();
		DisCountOutputParameter disCountOutputParameter = disCountFullPriceCalculator.calculate(disCountInputParameter);
		disCountInputParameter.setSpread(disCountOutputParameter.getSpread());
		disCountInputParameter.setFullprice(disCountOutputParameter.getFullprice());
		disCountInputParameter.setCashInfoDtoList(disCountOutputParameter.getCashInfoDtoList());
		return disCountInputParameter;
	}

	/**
	 * 封装现金流折现法计算参数
	 * @param entity
	 * @param calDate
	 * @param spreadOrPrice
	 * @param modYieldCurveInfos
	 * @return
	 */
	private DisCountInputParameter encapsDisCountInputParameter(CashFlowDiscountReaderResult entity,Date calDate,BigDecimal spreadOrPrice,List<ModYieldCurveInfoDto> modYieldCurveInfos){
		DisCountInputParameter disCountInputParameter = new DisCountInputParameter();
		disCountInputParameter.setCalDate(DateUtil.dateToLocal(calDate));
		if(!StringUtil.isEmptyList(entity.getModCashFlows())){
			List<CashInfoDto> cashInfoDtoList = new ArrayList<>();
			entity.getModCashFlows().stream().forEach(item ->{
				CashInfoDto cashInfoDto = new CashInfoDto();
				BeanUtil.copyPropertiesIgnoreNull(cashInfoDto,item);
				cashInfoDto.setActualCutoffDate(DateUtil.dateToLocal(item.getMdate()));
				cashInfoDto.setActualStartDate(DateUtil.dateToLocal(item.getVdate()));
				cashInfoDto.setTheoryCutoffDate(DateUtil.dateToLocal(item.getMdateUnadjust()));
				cashInfoDto.setTheoryStartDate(DateUtil.dateToLocal(item.getVdateUnadjust()));
				cashInfoDtoList.add(cashInfoDto);
			});
			disCountInputParameter.setCashInfoDtoList(cashInfoDtoList);
		}
		if(!StringUtil.isEmptyList(entity.getModCashFlowPayRules())){
			ModCashFlowPayRulesDto modCashFlowPayRulesDto = entity.getModCashFlowPayRules().get(0);
			//付息频率
			if(StringUtil.equals(modCashFlowPayRulesDto.getPayCycle(),DictCodeList.频率_月.getValue())){
				disCountInputParameter.setYearIntRate(IntFrequency.月付);
			}else if(StringUtil.equals(modCashFlowPayRulesDto.getPayCycle(),DictCodeList.频率_季.getValue())){
				disCountInputParameter.setYearIntRate(IntFrequency.季付);
			}else if(StringUtil.equals(modCashFlowPayRulesDto.getPayCycle(),DictCodeList.频率_半年.getValue())){
				disCountInputParameter.setYearIntRate(IntFrequency.半年付);
			}else{
				disCountInputParameter.setYearIntRate(IntFrequency.年付);
			}
			//推算方式
			if(StringUtil.equals(modCashFlowPayRulesDto.getCalcRule(),DictCodeList.推算方式_起息日向后.getValue())){
				disCountInputParameter.setCalMethod(SecIntSchCalMethod.起息日向后);
			}else if(StringUtil.equals(modCashFlowPayRulesDto.getCalcRule(),DictCodeList.推算方式_到期日向前.getValue())){
				disCountInputParameter.setCalMethod(SecIntSchCalMethod.到期日向前);
			}else if(StringUtil.equals(modCashFlowPayRulesDto.getCalcRule(),DictCodeList.推算方式_首次支付日.getValue())){
				disCountInputParameter.setFirstPayDate(DateUtil.dateToLocal(modCashFlowPayRulesDto.getFirstPayDate()));
				disCountInputParameter.setCalMethod(SecIntSchCalMethod.首次付息日);
			}else{
				disCountInputParameter.setCalMethod(SecIntSchCalMethod.起息日向后);
			}
			//息票品种
			if(StringUtil.equals(entity.getCouponSpecies(),DictCodeList.息票品种_零息.getValue())){
				disCountInputParameter.setVariety(Variety.零息);
			}else if(StringUtil.equals(entity.getCouponSpecies(),DictCodeList.息票品种_贴现.getValue())){
				disCountInputParameter.setVariety(Variety.贴现);
			}else if(StringUtil.equals(entity.getCouponSpecies(),DictCodeList.息票品种_利随本清.getValue())){
				disCountInputParameter.setVariety(Variety.利随本清);
			}else{
				disCountInputParameter.setVariety(Variety.附息);
			}
		}
		if(!StringUtil.isEmptyList(entity.getModCashFlowCalRules())){
			ModCashFlowCalRuleDto modCashFlowCalRuleDto = entity.getModCashFlowCalRules().get(0);
			List<Basis> bases = Arrays.asList(Basis.values());
			bases.stream().forEach(item ->{
				if(StringUtil.equals(item.getName(),modCashFlowCalRuleDto.getBasis())){
					disCountInputParameter.setBasis(item);
					return;
				}
			});
			if(StringUtil.equals(modCashFlowCalRuleDto.getBasis(),DictCodeList.计息基础_A_A_平均分配.getValue())){
				disCountInputParameter.setBasis(Basis.A_A);
			}
			if(StringUtil.equals(modCashFlowCalRuleDto.getBasis(),DictCodeList.计息基础_A_A_按实际天数分配.getValue())){
				disCountInputParameter.setBasis(Basis.A_A_D);
			}
		}
		disCountInputParameter.setTargetPrice(spreadOrPrice);
		disCountInputParameter.setSpread(spreadOrPrice);
		//构建算法需要的曲线信息对象
		CurveInfoDto curveInfoDto = new CurveInfoDto();
		if(!StringUtil.isEmptyList(modYieldCurveInfos)){
			ModYieldCurveInfoDto modYieldCurveInfoDto = modYieldCurveInfos.get(0);
			curveInfoDto.setCurveCode(modYieldCurveInfoDto.getCurveCode());
			curveInfoDto.setCalDate(DateUtil.dateToLocal(modYieldCurveInfoDto.getCurveDate()));
			/*if(StringUtil.equals(modYieldCurveInfoDto.getCurveType(),ModelDictCodeList.收益曲线类型_SP即期.getValue())){
				curveInfoDto.setCurveType(CurveType.即期);
			}else if(StringUtil.equals(modYieldCurveInfoDto.getCurveType(),ModelDictCodeList.收益曲线类型_MA远期.getValue())){
				curveInfoDto.setCurveType(CurveType.到期);
			}*/
			if(StringUtil.equals(modYieldCurveInfoDto.getCurveType(),"SP")){
				curveInfoDto.setCurveType(CurveType.即期);
			}else if(StringUtil.equals(modYieldCurveInfoDto.getCurveType(),"MA")){
				curveInfoDto.setCurveType(CurveType.到期);
			}
			List<KeyPointDto> KeyPoints = new ArrayList<>();
			modYieldCurveInfos.stream().forEach(item->{
				KeyPointDto keyPointDto = new KeyPointDto();
				keyPointDto.setKeyPoint(item.getKeyPoint());
				keyPointDto.setYield(item.getYield());
				KeyPoints.add(keyPointDto);
			});
			curveInfoDto.setKeyPointDtoList(KeyPoints);
		}
		disCountInputParameter.setCurveInfoDto(curveInfoDto);
		return disCountInputParameter;
	}

	/**
	 * 处理定价数据的评级结果、评级来源
	 * @param modRiskPremium
	 * @param modRateData
	 * @return
	 */
	private ModRiskPremium handleRateInfo(ModRiskPremium modRiskPremium, ModRateDataDto modRateData, String curveCode){
		if(null==modRateData){
			modRiskPremium.setGradeResult(cashFlowDiscountMapper.getGradeResult(curveCode));
			modRiskPremium.setGradeSource(ModelDictCodeList.模型_评级来源类型_系统计算.getValue());
		}else{
			modRiskPremium.setGradeResult(modRateData.getGraderesult());
			modRiskPremium.setGradeSource(StringUtil.equals(modRateData.getGradeorgid(),ModelDictCodeList.评级机构_资管内部.getValue())?ModelDictCodeList.模型_评级来源类型_内部.getValue():ModelDictCodeList.模型_评级来源类型_外部.getValue());
		}
		return modRiskPremium;
	}

	/**
	 * 曲线平滑计算
	 * @param map
	 * @param calDate
	 * @return
	 */
	private List<ModYieldCurveInfoDto>  calCurveSmooth(Map<Date,List<ModYieldCurveInfoDto>> map, Date calDate){
		List<ModYieldCurveInfoDto> result = new ArrayList<>();
		Map<BigDecimal,CurveSmoothDto> keyPointMap = new HashMap<>();
		Iterator<Map.Entry<Date, List<ModYieldCurveInfoDto>>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Date, List<ModYieldCurveInfoDto>> entry = it.next();
			entry.getValue().forEach(item->{
				BigDecimal key = item.getKeyPoint();
				CurveSmoothDto curveSmoothDto = new CurveSmoothDto();
				if(keyPointMap.containsKey(key)){
					curveSmoothDto = keyPointMap.get(key);
					curveSmoothDto.setYield(ArithmeticUtil.doAdd(curveSmoothDto.getYield(),item.getYield()));
					curveSmoothDto.setSmoothTimes(curveSmoothDto.getSmoothTimes()+1);
				}
				else{
					BeanUtil.copyPropertiesIgnoreNull(curveSmoothDto,item);
					curveSmoothDto.setCurveDate(calDate);
					curveSmoothDto.setSmoothTimes(1);
				}
				keyPointMap.put(key,curveSmoothDto);
			});
		}

		//计算收益率
		Iterator<Map.Entry<BigDecimal, CurveSmoothDto>> itkeyPoint = keyPointMap.entrySet().iterator();
		while (itkeyPoint.hasNext()) {
			CurveSmoothDto value = itkeyPoint.next().getValue();
			value.setYield(ArithmeticUtil.divide(value.getYield(),value.getSmoothTimes(), NumberEnum.ALL_PRECISION.getCode()));
			result.add(value);
		}
		return result;
	}

	/**
	 * 单位应计利息计算
	 * @param entity
	 * @param happenDate
	 * @return
	 */
	private BigDecimal calAccruedInterest(CashFlowDiscountReaderResult entity,Date happenDate){
		AccurIntCalInParameter accurIntCalInParameter = new AccurIntCalInParameter();
		accurIntCalInParameter.setCalDate(DateUtil.dateToLocal(happenDate));
		SecInfoModel secInfoModel = new SecInfoModel();
		secInfoModel.setStartDate(DateUtil.dateToLocal(entity.getVdate()));
		secInfoModel.setEndDate(DateUtil.dateToLocal(entity.getMdate()));
		secInfoModel.setIssuePrice(entity.getIssuePrice());
		 if(StringUtil.equals(entity.getCouponSpecies(), DictCodeList.息票品种_零息.getValue())){
			 secInfoModel.setBondsType(com.joyintech.calculation.bonds.constant.Variety.零息);
		}else if(StringUtil.equals(entity.getCouponSpecies(),DictCodeList.息票品种_贴现.getValue())){
			 secInfoModel.setBondsType(com.joyintech.calculation.bonds.constant.Variety.贴现);
		}else if(StringUtil.equals(entity.getCouponSpecies(),DictCodeList.息票品种_利随本清.getValue())){
			 secInfoModel.setBondsType(com.joyintech.calculation.bonds.constant.Variety.利随本清);
		}else {
			secInfoModel.setBondsType(com.joyintech.calculation.bonds.constant.Variety.附息);
		}
		if(!StringUtil.isEmptyList(entity.getModCashFlowCalRules())){
			ModCashFlowCalRuleDto modCashFlowCalRuleDto = entity.getModCashFlowCalRules().get(0);
			List<com.joyintech.calculation.bonds.constant.Basis> bases = Arrays.asList(com.joyintech.calculation.bonds.constant.Basis.values());
			bases.stream().forEach(item ->{
				if(StringUtil.equals(item.getName(),modCashFlowCalRuleDto.getBasis())){
					secInfoModel.setBasis(item);
					return;
				}
			});
			if(StringUtil.equals(modCashFlowCalRuleDto.getBasis(),DictCodeList.计息基础_A_A_平均分配.getValue())){
				secInfoModel.setBasis(com.joyintech.calculation.bonds.constant.Basis.A_A);
			}
			if(StringUtil.equals(modCashFlowCalRuleDto.getBasis(),DictCodeList.计息基础_A_A_按实际天数分配.getValue())){
				secInfoModel.setBasis(com.joyintech.calculation.bonds.constant.Basis.A_A_D);
			}
		}
		if(!StringUtil.isEmptyList(entity.getModCashFlowPayRules())){
			ModCashFlowPayRulesDto modCashFlowPayRulesDto = entity.getModCashFlowPayRules().get(0);
			//付息频率
			if(StringUtil.equals(modCashFlowPayRulesDto.getPayCycle(),DictCodeList.频率_月.getValue())){
				secInfoModel.setYearIntRate(com.joyintech.calculation.bonds.constant.IntFrequency.月付);
			}else if(StringUtil.equals(modCashFlowPayRulesDto.getPayCycle(),DictCodeList.频率_季.getValue())){
				secInfoModel.setYearIntRate(com.joyintech.calculation.bonds.constant.IntFrequency.季付);
			}else if(StringUtil.equals(modCashFlowPayRulesDto.getPayCycle(),DictCodeList.频率_半年.getValue())){
				secInfoModel.setYearIntRate(com.joyintech.calculation.bonds.constant.IntFrequency.半年付);
			}else{
				secInfoModel.setYearIntRate(com.joyintech.calculation.bonds.constant.IntFrequency.年付);
			}
			if(StringUtil.equals(modCashFlowPayRulesDto.getCalcRule(),DictCodeList.推算方式_起息日向后.getValue())){
				secInfoModel.setCalMethod(com.joyintech.calculation.bonds.constant.SecIntSchCalMethod.起息日向后);
			}else if(StringUtil.equals(modCashFlowPayRulesDto.getCalcRule(),DictCodeList.推算方式_到期日向前.getValue())){
				secInfoModel.setCalMethod(com.joyintech.calculation.bonds.constant.SecIntSchCalMethod.到期日向前);
			}else if(StringUtil.equals(modCashFlowPayRulesDto.getCalcRule(),DictCodeList.推算方式_首次支付日.getValue())){
				Date firstPayDate = modCashFlowPayRulesDto.getFirstPayDate();
				if(null!= firstPayDate){
					secInfoModel.setFirstPayDate(DateUtil.dateToLocal(firstPayDate));
				}
				secInfoModel.setCalMethod(com.joyintech.calculation.bonds.constant.SecIntSchCalMethod.首次付息日);
			}
			else{
				secInfoModel.setCalMethod(com.joyintech.calculation.bonds.constant.SecIntSchCalMethod.起息日向后);
			}
		}
		if(!StringUtil.isEmptyList(entity.getModCashFlowCalRules())){
			ModCashFlowCalRuleDto modCashFlowCalRuleDto = entity.getModCashFlowCalRules().get(0);
			List<com.joyintech.calculation.bonds.constant.Basis> bases = Arrays.asList(com.joyintech.calculation.bonds.constant.Basis.values());
			bases.stream().forEach(item ->{
				if(StringUtil.equals(item.getName(),modCashFlowCalRuleDto.getBasis())){
					secInfoModel.setBasis(item);
					return;
				}
			});
			if(StringUtil.equals(modCashFlowCalRuleDto.getBasis(),DictCodeList.计息基础_A_A_平均分配.getValue())){
				secInfoModel.setBasis(com.joyintech.calculation.bonds.constant.Basis.A_A);
			}
			if(StringUtil.equals(modCashFlowCalRuleDto.getBasis(), DictCodeList.计息基础_A_A_按实际天数分配.getValue())){
				secInfoModel.setBasis(com.joyintech.calculation.bonds.constant.Basis.A_A_D);
			}
		}
		accurIntCalInParameter.setSecInfoModel(secInfoModel);
		if(!StringUtil.isEmptyList(entity.getModCashFlows())){
			List<SecIntSchedule> secIntSchedules = new ArrayList<>();
			entity.getModCashFlows().stream().forEach(item ->{
				SecIntSchedule secIntSchedule = new SecIntSchedule();
				secIntSchedule.setTheoryStartDate(DateUtil.dateToLocal(item.getVdateUnadjust()));
				secIntSchedule.setTheoryCutoffDate(DateUtil.dateToLocal(item.getMdateUnadjust()));
				secIntSchedule.setActualStartDate(DateUtil.dateToLocal(item.getVdate()));
				secIntSchedule.setActualCutoffDate(DateUtil.dateToLocal(item.getMdate()));
				secIntSchedule.setCorpus(item.getPrinAmt());
				secIntSchedule.setAmount(item.getCashAmt());
				if(!StringUtil.isEmptyList(entity.getModCashFlowCalRules())&&entity.getModCashFlowCalRules().size()>1){
					secInfoModel.setIntRateType(IntRateType.浮动利率);
				}else{
					secInfoModel.setIntRateType(IntRateType.固定利率);
				}
				secIntSchedules.add(secIntSchedule);
			});
			accurIntCalInParameter.setSecIntScheduleList(secIntSchedules);
		}
		if(!StringUtil.isEmptyList(entity.getModCashFlowCalRules())){
			List<PaperIrReset> paperIrResets = new ArrayList<>();
			entity.getModCashFlowCalRules().stream().forEach(item->{
				PaperIrReset paperIrReset = new PaperIrReset();
				paperIrReset.setParRateEffDate(DateUtil.dateToLocal(item.getEffDate()));
				paperIrReset.setBaseRateEffDate(DateUtil.dateToLocal(item.getEffDate()));
				paperIrReset.setParRate(item.getYield());
				paperIrResets.add(paperIrReset);
			});
			accurIntCalInParameter.setPaperIrResetList(paperIrResets);
		}
		AccurIntCalculator calc = com.joyintech.calculation.CalculatorFactory.createAccurIntCalculator();
		AccurIntCalOutParameter calculate = calc.calculate(accurIntCalInParameter);
		return calculate.getInterest();
	}

}
