/*
 * 文件名：PrdIndexCalculateProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.processor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.batch.prdindexcalculate.common.AccumulatedValueRateCa;
import com.joyintech.tams.valuation.batch.prdindexcalculate.common.NetRightCurrentValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dao.PrdIndexCalculateMapper;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.BokValTableDataForIndexDto;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.FinBenchmarklDto;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdIndexCalculateProcessorIntoResult;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdIndexCalculateReaderIntoResult;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdInfoDto;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.utils.DBUtil;

/**
 * 信息披露-产品指标计算(数据插入)-processor
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateIntoProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdIndexCalculateIntoProcessor
		extends BaseProcessor<NeedOperateEntity, PrdIndexCalculateProcessorIntoResult> {

	@Autowired
	private PrdIndexCalculateMapper prdIndexCalculateMapper;

	@Autowired
	private DBUtil dBUtil;

	@Override
	public PrdIndexCalculateProcessorIntoResult process(NeedOperateEntity items) throws Exception {
		// 获取reader方法的返回值
		PrdIndexCalculateReaderIntoResult acctEntity = (PrdIndexCalculateReaderIntoResult) items;

		// 若reader读取的值为空，直接返回空
		if (acctEntity == null) {
			return null;
		}

		Map<String,Object> map = new HashMap<>();
		// 定义产品指标表数据
		List<RepProdIndex> RepProdIndexList = new ArrayList<RepProdIndex>();
		
//		// add by wm 由于预期收益型为组合建账，故需要通过组合代码查询产品,故通过产品代码查询为空时，再通过组合查询 start 2019-06-14
//		if(StringUtil.isEmptyList(prdInfoDtoList)){
//			prdInfoDtoList = prdIndexCalculateMapper.getPrdInfoYQ(acctEntity.getPrdId());
//		}
		// add by wm 由于预期收益型为组合建账，故需要通过组合代码查询产品,故通过产品代码查询为空时，再通过组合查询 end 2019-06-14
		
			//取产品分层信息   add by  dongxiangyang  2019/09/24 start
			Map<String, Object> pmap=new  HashMap<String,Object>();
			pmap.put("booKsetId", acctEntity.getBooksetId());
			pmap.put("branch", 0);
			List<BokAcctLayeringInfo>  listBokAcctLayeringInfo=prdIndexCalculateMapper.getBokAcctLayeringInfo(pmap);
		   //循环产品分层信息
			for(BokAcctLayeringInfo  bokInfo:listBokAcctLayeringInfo){
				
				RepProdIndex repProdIndex = new RepProdIndex();
				
				// 根据产品id，获取金融产品信息
				PrdInfoDto prdInfoDto = prdIndexCalculateMapper.getPrdInfo(bokInfo.getBooksetId());
				
				//有效状态=E
				repProdIndex.setStatus("E");
				// 产品指标表.流水号 <- 自动生成，规则为BOOK + "YYYYMMDD"+20位序列号
				repProdIndex.setSeqNo(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
				// 产品指标表.账套代码 = DTO.账套代码
				repProdIndex.setBooksetId(acctEntity.getBooksetId());
				repProdIndex.setProdId(prdInfoDto.getFinprodMarketId());
				// 产品指标表.估值日 = DTO.入账日
				repProdIndex.setValueDate(acctEntity.getBookDate());
				// 产品指标表.产品代码 = 金融产品数据.副产品Id
				repProdIndex.setProdId(prdInfoDto.getFinprodMarketId());
				//获取产品收益类型
				String profitType=prdInfoDto.getProfitType();
				
				//前一日dto
				Map<String, Object> paramMap = new HashMap<String, Object>();
				RepProdIndex dto = new RepProdIndex();
				paramMap.put("bookDate", acctEntity.getBookDate());
				paramMap.put("prodId", repProdIndex.getProdId());
				dto=prdIndexCalculateMapper.selectRepProdIndexNetworth(paramMap);
				
				//分层产品代码
				String  layerProdId="";
				if(StringUtil.isNotEmpty(bokInfo.getfLayeringId())){
					layerProdId=bokInfo.getLayeringId();
					//产品指标表.产品代码 = 分层产品信息.分层产品代码
					repProdIndex.setProdId(bokInfo.getBusiId());
					
				}else{
					layerProdId=null;
				}
				//产品指标表.业绩比较基准日增长率
				Map<String, Object> param= new HashMap<String, Object>();
				
				//如果前一日指标数据为空
				//产品指标表.累计业绩比较基准增长率
				if(dto==null){
					//产品指标表.业绩比较基准日增长率 = 0
					repProdIndex.setFloatBaseRate(BigDecimal.ZERO);
					//产品指标表.累计业绩比较基准增长率 = 0
					repProdIndex.setFloatTotalRate(BigDecimal.ZERO);
				}else{
					param.put("prodId", bokInfo.getBusiId());
					param.put("valueDate", dto.getValueDate());
					param.put("bookDate", acctEntity.getBookDate());
					param.put("baseRuleType",DictCodeListExt1.业绩基准类型_产品披露.getValue());
					param.put("publistType", DictCodeListExt1.披露类型_单值披露.getValue());
					//利用参数查询业绩比较基准
					FinBenchmarklDto finBenchmarklDto=prdIndexCalculateMapper.getFloatBaseValue(param);
				    if(finBenchmarklDto!=null){
				    	repProdIndex.setFloatBaseRate(finBenchmarklDto.getStMance()==null?BigDecimal.ZERO:finBenchmarklDto.getStMance());
				    	repProdIndex.setBenchmarkInfo(finBenchmarklDto.getStDetail());
				    	repProdIndex.setRateBenchmark(finBenchmarklDto.getRateBenchmark());
				    }else{
				    	repProdIndex.setFloatBaseRate(BigDecimal.ZERO);
				    	repProdIndex.setBenchmarkInfo(null);
				    	repProdIndex.setRateBenchmark(BigDecimal.ZERO);
					}
				  //累计业绩比较基准增长率 = （业绩比较基准日增长率+1）*（前一日产品指标表DTO.累计业绩比较基准增长率+1）-1
				    repProdIndex.setFloatTotalRate((repProdIndex.getFloatBaseRate().add(BigDecimal.ONE)).multiply(dto.getFloatTotalRate().add(BigDecimal.ONE)).subtract(BigDecimal.ONE));	
				}
				
					
					// 定义方法【getBokValTableDataForIndex】获取变量字符串
					String costString = "COST";// 获取成本
					String marketValueString = "MARKETVALUE";// 获取市值
					String subjectNameString = "SUBJECTNAME";// 获取科目

					// 产品指标表.实收资本 = 取值:估值表数据.成本，条件:估值表数据.明细区分 = "02"
					repProdIndex.setCapital(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_实收资本.getValue(), null,null, costString,layerProdId));

					// 产品指标表.损益平准金未实现 = 取值:估值表数据.成本，条件:估值表数据.明细区分 = "03"
//					repProdIndex.setProfitNo(this.getBokValTableDataForIndex(repProdIndex.getBooksetId(),
//							repProdIndex.getValueDate(), DictCodeList.估值表类型_损益平准金未实现.getValue(), null, costString));
			//
//					// 产品指标表.损益平准金已实现 = 取值:估值表数据.成本，条件:估值表数据.明细区分 = "04"
//					repProdIndex.setProfitYes(this.getBokValTableDataForIndex(repProdIndex.getBooksetId(),
//							repProdIndex.getValueDate(), DictCodeList.估值表类型_损益平准金已实现.getValue(), null, costString));
			//
//					// 产品指标表.资产类合计(按成本） = 取值:估值表数据.成本，条件:估值表数据.明细区分 = "05"
//					repProdIndex.setAssetTotalCost(this.getBokValTableDataForIndex(repProdIndex.getBooksetId(),
//							repProdIndex.getValueDate(), DictCodeList.估值表类型_资产类合计.getValue(), null, costString));
			//
					// 产品指标表.资产类合计(按市值） = 取值:估值表数据.估值，条件:估值表数据.明细区分 = "05"
				repProdIndex.setAssetTotalMarket(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_资产类合计.getValue(), null,null, marketValueString,layerProdId));
			//
//					// 产品指标表.负债类合计 = 取值:估值表数据.成本，条件:估值表数据.明细区分 = "06"
//					repProdIndex.setDebtTotal(this.getBokValTableDataForIndex(repProdIndex.getBooksetId(),
//							repProdIndex.getValueDate(), DictCodeList.估值表类型_负债类合计.getValue(), null, costString));

					// 产品指标表.产品资产净值(按成本） = 取值:估值表数据.成本，条件:估值表数据.明细区分 = "07"  or  ="39"
					repProdIndex.setAssetValueCost(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_产品资产净值.getValue(), DictCodeList.估值表类型_产品资产净值_费后.getValue(),null, costString,layerProdId));
		            
					// 产品指标表.产品资产净值(按成本）_费前 = 取值:估值表数据.成本，条件:估值表数据.明细区分 = "41"
					repProdIndex.setAssetValueCostFq(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_产品资产净值_费前.getValue(), null,null, costString,layerProdId));

					// 产品指标表.品资产净值(按市值） = 取值:估值表数据.估值，条件:估值表数据.明细区分 = "07"
					repProdIndex.setAssetValueMarket(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_产品资产净值.getValue(), DictCodeList.估值表类型_产品资产净值_费后.getValue(),null, marketValueString,layerProdId));

					// 产品指标表.品资产净值(按市值）_费前 = 取值:估值表数据.估值，条件:估值表数据.明细区分 = "41"
					repProdIndex.setAssetValueMarketFq(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_产品资产净值_费前.getValue(), null,null, marketValueString,layerProdId));

					
					// 产品指标表.产品单位净值 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "08"  or="40"
					repProdIndex.setUnitValue(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_产品单位净值.getValue(), DictCodeList.估值表类型_产品单位净值_费后.getValue(),null, subjectNameString,layerProdId));
					
					// 产品指标表.产品单位净值_费前 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "42"  
					repProdIndex.setUnitValueFq(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_产品单位净值_费前.getValue(), null,null, subjectNameString,layerProdId));

					
					// 产品指标表.产品累计单位净值 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "17"
					repProdIndex.setUnitValueT(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_产品累计单位净值.getValue(), null,null, subjectNameString,layerProdId));

					// 产品指标表.产品分红净值 = 产品指标表.产品累计单位净值 - 产品指标表.产品单位净值
					repProdIndex.setBounusValue(ArithmeticUtil.doSub(repProdIndex.getUnitValueT(), repProdIndex.getUnitValue()));

					// 产品指标表.偏离金额 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "26"
					repProdIndex.setDeviationAmt(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_偏离金额.getValue(), null,null, subjectNameString,layerProdId));

					// 产品指标表.偏离度% = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "27"
					repProdIndex.setDeviation(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_偏离度.getValue(),null, null, subjectNameString,layerProdId));

					// 产品指标表.当日客户收益 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "28"
					repProdIndex.setProfitTdy(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_产品本日收益_费后.getValue(), null,null, subjectNameString,layerProdId));

					// 产品指标表.当日客户收益_费前 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "44"
					repProdIndex.setProfitTdyFq(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_产品本日收益_费前.getValue(), null,null, subjectNameString,layerProdId));

					
					// 产品指标表.累计客户收益 = 取值:估值表数据.成本，条件:估值表数据.明细区分 = "01" AND 科目号 = '22320101'
					repProdIndex.setProfitTotal(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_科目明细列表.getValue(), null,"22320101", costString,layerProdId));

					// 产品指标表.万份收益 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "29"
					repProdIndex.setwYield(this.getBokValTableDataForIndex(acctEntity.getBooksetId(), repProdIndex.getValueDate(),
							DictCodeList.估值表类型_万份收益_费后.getValue(),null, null, subjectNameString,layerProdId));
					// 产品指标表.万份收益 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "29"
					repProdIndex.setwYieldFq(this.getBokValTableDataForIndex(acctEntity.getBooksetId(), repProdIndex.getValueDate(),
							DictCodeList.估值表类型_万份收益_费前.getValue(),null, null, subjectNameString,layerProdId));

					// 产品指标表.本年累积万份收益 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "30"
//					repProdIndex.setwYieldYear(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
//							repProdIndex.getValueDate(), DictCodeList.估值表类型_本年累积万份收益.getValue(), null, subjectNameString));

					// 产品指标表.从成立以来累计万份收益 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "31"
					repProdIndex.setwYieldTotal(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_从成立以来累计万份收益.getValue(),null, null, subjectNameString,layerProdId));

					// 产品指标表.本日年化收益率% = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "32"
					repProdIndex.setYieldTdy(this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_本日年化收益率.getValue(),null, null, subjectNameString,layerProdId));

					// 产品指标表.7日年化收益率% = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "33"
					//modify by wm 2019-06-12 估值表存的未%数据，故此处增加除以100 start
					BigDecimal yield7 = this.getBokValTableDataForIndex(acctEntity.getBooksetId(), repProdIndex.getValueDate(),
							DictCodeList.估值表类型_7日年化收益率.getValue(),null, null, subjectNameString,layerProdId);
					if(StringUtil.isNotEmpty(yield7)){
						repProdIndex.setYield7(this.getBokValTableDataForIndex(acctEntity.getBooksetId(), repProdIndex.getValueDate(),
								DictCodeList.估值表类型_7日年化收益率.getValue(),null, null, subjectNameString,layerProdId).divide(new BigDecimal(100)));
					}
					//modify by wm 2019-06-12 估值表存的未%数据，故此处增加除以100 end
					
					//产品指标表.累计年化收益率% = 产品指标表.从成立以来累计万份收益/10000/(产品指标表.估值日-金融产品数据.起息日）*365		暂时按365计算，没有准确用计息基础计算	
					BigDecimal yieldTotal = null!=repProdIndex.getwYieldTotal()?repProdIndex.getwYieldTotal():BigDecimal.ZERO;

					yieldTotal = yieldTotal.divide(new BigDecimal("10000"));
					yieldTotal = yieldTotal.divide(new BigDecimal(DateUtil.getBetweenDayNumber(prdInfoDto.getvDate(), repProdIndex.getValueDate())+1),12,BigDecimal.ROUND_HALF_UP);
					yieldTotal = yieldTotal.multiply(new BigDecimal(365));
					yieldTotal = yieldTotal.divide(new BigDecimal("1"),4,BigDecimal.ROUND_HALF_UP);
			//
//					if(DateUtil.getBetweenDayNumber(prdInfoDtoList.get(0).getvDate(), repProdIndex.getValueDate())!=0){
//					}
					repProdIndex.setYieldTotal(yieldTotal);
					
				
					
					
					
					
					//产品指标表.预期收益率% //TODO
					if(prdInfoDto.getBusiType().equals(DictCodeList.收益类型_收益型.getValue())){
			    		map.put("cashType", DictCodeList.自定义现金流类型_利息.getValue());
			    		repProdIndex.setYieldE(prdIndexCalculateMapper.getYeildE(map));
			    	}

					// 产品指标表.投资组合平均到期日 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "34"
					BigDecimal averageMdateResult = this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_投资组合平均到期日.getValue(),null, null, subjectNameString,layerProdId);
					if (averageMdateResult != null) {
						repProdIndex.setAverageMdate(averageMdateResult.intValue());
					}

					// 产品指标表.剩余存续期 = 取值:估值表数据.科目名称，条件:估值表数据.明细区分 = "35"
					BigDecimal surplusTermResult = this.getBokValTableDataForIndex(acctEntity.getBooksetId(),
							repProdIndex.getValueDate(), DictCodeList.估值表类型_剩余存续期.getValue(), null,null, subjectNameString,layerProdId);
					if (surplusTermResult != null) {
						repProdIndex.setSurplusTerm(surplusTermResult.intValue());
					}

					// 产品指标表.当日本期利润已实现 =
					// 取值：原币当日发生额贷方 - 原币当日发生额借方 - 原币结转当日发生额贷方 - 原币结转当日发生额借方 ,
					// 条件：科目 not in ("6101", "6112") and 科目 like '6%'
					repProdIndex.setProfitCurrentYes(prdIndexCalculateMapper
							.getProfitCurrentYesFromBlance(acctEntity.getBooksetId(), repProdIndex.getValueDate()));

					// 产品指标表.当日本期利润未实现 =
					// 取值：原币当日发生额贷方 - 原币当日发生额借方 - 原币结转当日发生额贷方 - 原币结转当日发生额借方，
					// 条件：科目 in ("6101", "6112")
					repProdIndex.setProfitCurrentNo(prdIndexCalculateMapper
							.getProfitCurrentNoFromBlance(acctEntity.getBooksetId(), repProdIndex.getValueDate()));

					// 产品指标表.本期利润 = 产品指标表.当日本期利润已实现 + 产品指标表.当日本期利润未实现
					repProdIndex.setProfitCurrent(
							ArithmeticUtil.doAdd(repProdIndex.getProfitCurrentYes(), repProdIndex.getProfitCurrentNo()));

					// 产品指标表.当日可供分配利润 = 取值：原币余额贷方 - 原币余额借方，条件：科目 in ("4103", "4104")
					repProdIndex.setProfitTdyAble(prdIndexCalculateMapper.getProfitTdyAbleFromBlance(acctEntity.getBooksetId(),
							repProdIndex.getValueDate()));

					// 产品指标表.当日可供分配产品份额利润 = 产品指标表.当日可供分配利润/产品指标表.实收资本，*保留4位小数
					repProdIndex.setProdProfitTdyAble(
							ArithmeticUtil.divide(repProdIndex.getProfitTdyAble(), repProdIndex.getCapital(), 4));
//					//前一日dto
//					Map<String, Object> paramMap = new HashMap<String, Object>();
//					
//					RepProdIndex dto = new RepProdIndex();
//					paramMap.put("bookDate", acctEntity.getBookDate());
//					paramMap.put("prodId", acctEntity.getPrdId());
//					dto=prdIndexCalculateMapper.selectRepProdIndexNetworth(paramMap);
//					if (prdInfoDtoList != null && !prdInfoDtoList.isEmpty()){
//						//如果是货币性
//						if(prdInfoDtoList.get(0).getProfitType().equals(DictCodeList.收益类型_货币型.getValue())){
//							paramMap.put("bookDate", WaverageProfitCa.getBeforeDate(acctEntity.getBookDate()));
//							paramMap.put("prodId", acctEntity.getPrdId());
//							dto = prdIndexCalculateMapper.selectRepProdIndexbypd(paramMap);
			//
//						}//如果是净值型
//						else if(prdInfoDtoList.get(0).getProfitType().equals(DictCodeList.收益类型_净值型.getValue())){
//							paramMap.put("bookDate", acctEntity.getBookDate());
//							paramMap.put("prodId", acctEntity.getPrdId());
//							dto=prdIndexCalculateMapper.selectRepProdIndexNetworth(paramMap);
//						}
//					}
					//判断是否需要跑批:针对净值型产品判断，节假日不跑，季末跑批，货币型产品每日都跑
					//IF 金融产品数据.产品类型 = 货币型 || DTO.入账日 IS 季末日期 || DTO.入账日 IS NOT 节假日
					//查询节假日list
				     List<Date>  listDate=prdIndexCalculateMapper.slectIsHoliday(acctEntity.getPrdId());
					//入账日
					Date inputDate=acctEntity.getBookDate();
					SimpleDateFormat format=new SimpleDateFormat("MM-dd");
					if(profitType.equals(DictCodeList.收益类型_货币型.getValue())
							||StringUtil.equals(format.format(inputDate), "03-31")
							||StringUtil.equals(format.format(inputDate), "06-30")
							||StringUtil.equals(format.format(inputDate), "09-30")
							||StringUtil.equals(format.format(inputDate), "12-31")
							||!listDate.contains(inputDate)){
					//产品指标表.当日净值收益率  调用【指标库方法计算-净值收益率计算】
					repProdIndex.setNetvalueTdyYield(NetRightCurrentValue.getRightCurrentValue(repProdIndex.getwYield()==null?BigDecimal.ZERO:repProdIndex.getwYield()));
						
					//如果产品收益类型为货币型,只计算净值收益率
					if(profitType.equals(DictCodeList.收益类型_货币型.getValue())){
						// 产品指标表.当日净值收益率 = 空 复杂指标,第二阶段处理
//						repProdIndex.setNetvalueTdyYield(NetRightCurrentValue.getRightCurrentValue(repProdIndex.getwYield()==null?BigDecimal.ZERO:repProdIndex.getwYield()));
						// 产品指标表.累计净值收益率 = 空 复杂指标,第二阶段处理
					    if(dto==null){
					    	//产品指标表.累计净值收益率 = 产品指标表.净值收益率
					    	repProdIndex.setNetvalueTotalYield(repProdIndex.getNetvalueTdyYield());
					    }else{
					    	//调用共通->当日净值收益率，前一日累积净值收益率
					    	repProdIndex.setNetvalueTotalYield(AccumulatedValueRateCa.getAccValueRate(repProdIndex.getNetvalueTdyYield(), dto.getNetvalueTotalYield()));
					    }
					}
					//当收益类型为净值时，只计算净值增长率
					else if(profitType.equals(DictCodeList.收益类型_净值型.getValue())){
						// 产品指标表.当日净值增长率 = 空 复杂指标,第二阶段处理
						if(repProdIndex.getUnitValue()==null){
							repProdIndex.setNetvalueTdyIncre(BigDecimal.ZERO);
						}else{
							if(dto!=null){
								//调用共通（前一日产品指标为空则净值为1计算）
//								repProdIndex.setNetvalueTdyIncre(ProductShareGrowthRateCa.getProShareWthRate(repProdIndex.getUnitValue(), repProdIndex.getBounusValue(), dto.getUnitValue()==null?BigDecimal.ONE:dto.getUnitValue()));	
							}else{
//								repProdIndex.setNetvalueTdyIncre(ProductShareGrowthRateCa.getProShareWthRate(repProdIndex.getUnitValue(), repProdIndex.getBounusValue(), BigDecimal.ONE));	
							}
						}
//						if(dto==null){
							//				repProdIndex.setNetvalueTdyIncre(BigDecimal.ZERO);
//						}else{
//							//调用共通->当日净值增增长率
//							//对数据为null进行处理
//							if(repProdIndex.getUnitValue()==null){
//								repProdIndex.setNetvalueTdyIncre(BigDecimal.ZERO);
//							}else{
//								repProdIndex.setNetvalueTdyIncre(ProductShareGrowthRateCa.getProShareWthRate(repProdIndex.getUnitValue(), repProdIndex.getBounusValue(), dto.getUnitValue()==null?BigDecimal.ONE:dto.getUnitValue()));
//							}
						
						//repProdIndex.setNetvalueTdyIncre(TargetIndexService.getShareValue(acctEntity.getBookDate()).getNetvalueTdyIncre());
						// 产品指标表.累计净值增长率 = 空 复杂指标,第二阶段处理
						if(repProdIndex.getUnitValue()==null){
							//如果估值表没数据，则为起息日之前跑批。
							repProdIndex.setNetvalueTotalIncre(BigDecimal.ZERO);
						}else{
							if(dto!=null){
								//调用共通(前一日产品指标表DTO ==null? 0:前一日产品指标表DTO.产品单位净值)
//					  		    repProdIndex.setNetvalueTotalIncre(AccumProductShareValue.getProductShareValue(repProdIndex.getNetvalueTdyIncre(),dto.getUnitValue()==null?BigDecimal.ZERO:dto.getNetvalueTotalIncre()));
							}else{
//					  		    repProdIndex.setNetvalueTotalIncre(AccumProductShareValue.getProductShareValue(repProdIndex.getNetvalueTdyIncre(),BigDecimal.ZERO));	
							}
							
						}
//						//调用共通(前一日产品指标表DTO ==null? 1:前一日产品指标表DTO.产品单位净值)
//			  		    repProdIndex.setNetvalueTotalIncre(AccumProductShareValue.getProductShareValue(repProdIndex.getNetvalueTdyIncre(),dto==null?BigDecimal.ONE:dto.getNetvalueTotalIncre()));
//						if(dto==null){
//							repProdIndex.setNetvalueTotalIncre(BigDecimal.ZERO);
//						}else{
//							if(dto.getNetvalueTotalIncre()==null){
//								repProdIndex.setNetvalueTotalIncre(BigDecimal.ZERO);	
//							}else{
//								//调用共通
//								repProdIndex.setNetvalueTotalIncre(AccumProductShareValue.getProductShareValue(repProdIndex.getNetvalueTdyIncre(), dto.getNetvalueTotalIncre()));
//							}
//									}
					}
					// 产品指标表.平均剩余期限 = 空 复杂指标,第二阶段处理
					
					
					
					
					
					
					
					map.put("prodId", acctEntity.getBooksetId());
					map.put("valueDate", repProdIndex.getValueDate());
					
					//查询当日分红数据（现金分红、红利再投金额）   SELECT SUM(交易金额) AS 交易金额,交易方向 FROM 金融产品交易表 WHERE 金融产品代码=DTO.产品代码 AND 交易日= DTO.入账日 AND 交易方向 in（C9 ，C10） GROUP BY 交易方向
					List<Map<String,Object>> resultMap = prdIndexCalculateMapper.queryFormGrant(map);
					
					BigDecimal cashFormGrantTdy = BigDecimal.ZERO;
					BigDecimal reinvestFormGrantTdy = BigDecimal.ZERO;
					for (Map<String, Object> map2 : resultMap) {
						BigDecimal amt = null!=map2.get("AMT")?(BigDecimal)map2.get("AMT"):BigDecimal.ZERO;
						if(map2.get("PS").equals("C9")){
							//产品指标表.现金分红  取值：分红数据.交易金额, 条件：分红数据.交易方向 = C9
							cashFormGrantTdy = amt;
						}else{
							//产品指标表.红利再投金额     条件：分红数据.交易方向 = C10
							reinvestFormGrantTdy = amt;
						}
					}
					
					repProdIndex.setCashFormGrantTdy(cashFormGrantTdy);
					repProdIndex.setReinvestFormGrantTdy(reinvestFormGrantTdy);
					
					//产品指标表.赎回利息金额   SELECT SUM(利息总额) FROM 金融产品交易表 WHERE 金融产品代码=DTO.产品代码 AND 交易日= DTO.入账日 AND 交易方向 in（C4，C15）

					repProdIndex.setRedemIntamtTdy(prdIndexCalculateMapper.queryRedemIntamtTdy(map));
					
						//产品指标表.现金形式发放总额  
						if(dto == null){
							//现金形式发放总额 = 当日产品指标表DTO.现金分红
							repProdIndex.setCashFormGrantTotal(repProdIndex.getCashFormGrantTdy());
						}else{
							//现金形式发放总额 = 当日产品指标表DTO.现金分红 + 前一日产品指标DTO.现金形式发放总额
							repProdIndex.setCashFormGrantTotal(repProdIndex.getCashFormGrantTdy().add(dto.getCashFormGrantTotal()));
						}
						
						//产品指标表.再投资形式发放总额
						if(dto == null){
							//再投资形式发放总额 = 当日产品指标表DTO.红利再投金额
							repProdIndex.setReinvestFormGrantTotal(repProdIndex.getReinvestFormGrantTdy());
						}else{
							//再投资形式发放总额 = 当日产品指标表DTO.红利再投金额 + 前一日产品指标DTO.再投资形式发放总额
							repProdIndex.setReinvestFormGrantTotal(repProdIndex.getReinvestFormGrantTdy().add(dto.getReinvestFormGrantTotal()));
						}
						
						//产品指标表.直接通过应付赎回款转出金额  直接通过应付赎回款转出金额 = 现金形式发放总额 + 当日产品指标表DTO.赎回利息金额
						repProdIndex.setRedemptionTurnoutAmt(null!=repProdIndex.getCashFormGrantTotal()?repProdIndex.getCashFormGrantTotal():BigDecimal.ZERO
								.add(null!=repProdIndex.getRedemIntamtTdy()?repProdIndex.getRedemIntamtTdy():BigDecimal.ZERO));
						
						//产品指标表.应付利润本年变动
						//应付利润本年变动 = 当日产品指标表DTO.累计客户收益
						repProdIndex.setProfitThisyearChange(repProdIndex.getProfitTotal());
						
//						if(dto == null){
//							//应付利润本年变动 = 当日产品指标表DTO.累计客户收益
//							repProdIndex.setProfitThisyearChange(repProdIndex.getProfitChangeTdy());
//						}else{
//							//应付利润本年变动 = 当日产品指标表DTO.应付利润 + 前一日产品指标DTO.应付利润本年变动
//							repProdIndex.setProfitThisyearChange(repProdIndex.getProfitChangeTdy().add(dto.getProfitThisyearChange()));
//						}
					
				}
				
			
			
			
				
				RepProdIndexList.add(repProdIndex);
			
		}


			
		
	
		
		// 定义返回值
		PrdIndexCalculateProcessorIntoResult returnDto = new PrdIndexCalculateProcessorIntoResult();
		returnDto.getRepProdIndexList().addAll(RepProdIndexList);

		// 暂时传入账套代码，后期待确定
		returnDto.setProdId(acctEntity.getBooksetId());

		return returnDto;
	}

	/**
	 * 获取估值表指标数据
	 * 
	 * @param booksetId
	 *            账套代码
	 * @param valDate
	 *            估值日期
	 * @param detailDist
	 *            区间
	 * @param subjectNo
	 *            科目id
	 * @param subjectNo
	 *            第二科目id          
	 * @param getResultType
	 *            获取返回的值 【COST:成本；MARKETVALUE:市值； SUBJECTNAME:科目名称】
	 * @return
	 * @see
	 * @since 1.0
	 */
	public BigDecimal getBokValTableDataForIndex(String booksetId, Date valDate, String detailDist, String  otherdetailDist,String subjectNo,
			String getResultType,String layeringId) {

		BigDecimal result = null;

		// 获取估值表指标数据
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("booksetId", booksetId);
		sqlMap.put("valDate", valDate);
		sqlMap.put("detailDist", detailDist);
		sqlMap.put("layeringId", layeringId);

		if (subjectNo != null && subjectNo.length() > 0) {
			sqlMap.put("subjectNo", subjectNo);
		}else{
			sqlMap.put("subjectNo", "");
		}
		if(otherdetailDist!=null && otherdetailDist.length() > 0){
			sqlMap.put("otherdetailDist", otherdetailDist);
		}
		List<BokValTableDataForIndexDto> BokValTableDataForIndexDtoList = prdIndexCalculateMapper
				.getBokValTableDataForIndex(sqlMap);

		// 此处取到的值，只取第一个
		if (BokValTableDataForIndexDtoList != null && !BokValTableDataForIndexDtoList.isEmpty()) {
			// 获取数据实体类
			BokValTableDataForIndexDto vo = BokValTableDataForIndexDtoList.get(0);

			// 成本
			if ("COST".equals(getResultType)) {
				result = vo.getCost();

			}	
			// 市值
			else if ("MARKETVALUE".equals(getResultType)) {
				result = vo.getMarketValue();
			}
			// 汇总类的指标，在科目名称中
			else if ("SUBJECTNAME".equals(getResultType)) {
				result = new BigDecimal(vo.getSubjectName()==null?"0":vo.getSubjectName());

			} // 其他情况
			else {
				result = null;
			}

		}

		return result;
	}

}
