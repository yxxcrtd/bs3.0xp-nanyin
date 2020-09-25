package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.server;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.disclosure.common.db.DBUtil;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dao.DisReportProdIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.BokValTableDataForIndexDto;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexResult;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.busicommon.common.calculation.AccumProductShareValue;
import com.joyintech.tams.disclosure.busicommon.common.calculation.AccumulatedValueRateCa;
import com.joyintech.tams.disclosure.busicommon.common.calculation.ProductShareGrowthRateCa;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;

@Service
public class DisReportProdIndexService  extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
	private DBUtil dBUtil;
    
    @Autowired
    private DisReportProdIndexMapper disReportProdIndexMapper;
    
    @Autowired
    private DisReportProdIndexInterfService disReportProdIndexInterfService;
	/**
     * process 数据处理
     * 
     * @param param1 传递参数
     * @return BookGenerateResult
	 * @throws Exception 
     * @see
     * @since 1.0
     */
    public DisReportProdIndexIntoResult execute(Map<String, Object> param1) throws Exception {
    	// 获取reader返回值
    	DisReportProdIndexResult disReportProdIndexEntity = (DisReportProdIndexResult)param1.get(
            ReportGlobalConstant.REP_READER_RESULT);
    	logger.info("process执行开始");
    	// 若reader传递的参数为空，不进行后续处理
        if (disReportProdIndexEntity == null) {
            return null;
        }
        
        DisReportProdIndexIntoResult result = new DisReportProdIndexIntoResult();
        
        String source = CacheUtil.getSysParam(ReportGlobalConstant.DIS_DATA_SOURCE);
        result = this.processDto(disReportProdIndexEntity);
        

    	logger.info("process执行结束");
		return result;
    }
    

    
    
    /**
     * 
     * @param acctEntity
     * @return
     * @throws Exception
     */
    public DisReportProdIndexIntoResult processDto(DisReportProdIndexResult acctEntity) throws Exception { 

    	// 若reader读取的值为空，直接返回空
    	if (acctEntity == null) {
    		return null;
    	}
    	
    	
		DisReportProdIndexIntoResult returnDto = new DisReportProdIndexIntoResult();

		
			// select * from 产品基本信息 where 产品代码 = DTO.产品代码
	    	List<RepProdInfo> prdInfoDtoList = disReportProdIndexMapper.getPrdInfo(acctEntity.getProdId());
	    	
	    	//产品指标表.偏离金额 = 当日产品指标表DTO.产品资产净值(按成本）- 当日产品指标表DTO.产品资产净值(按市值）
	    	acctEntity.setDeviationAmt(checkBigDec(acctEntity.getAssetValueCost()).subtract(checkBigDec(acctEntity.getAssetValueMarket())));
	    	
	    	// if(null==当日产品指标表.偏离度){
	    	if(null == acctEntity.getDeviation()) {
	    		//产品指标表.偏离度 = 当日产品指标表DTO.偏离金额 / 当日产品指标表DTO.产品资产净值(按市值）	*保留4位小数			
	    		acctEntity.setDeviation(checkBigDec(acctEntity.getDeviationAmt()).divide(checkBigDec(acctEntity.getAssetValueMarket()),4,BigDecimal.ROUND_HALF_UP));
	    	}
	    	
	    	if(null!=prdInfoDtoList&&prdInfoDtoList.size()>0){
	    		//产品指标表.剩余存续期	   =  产品基本信息.到期日- DTO.估值日
		    	acctEntity.setSurplusTerm((int)DateUtil.getBetweenDayNumber(acctEntity.getValueDate(), prdInfoDtoList.get(0).getMdate()));
		    	
	    	}
	    	
	        BigDecimal profitCurrent = BigDecimal.ZERO;
	        
	        if(null!=acctEntity.getProfitCurrentYes()&&null!=acctEntity.getProfitCurrentNo()){
	        	profitCurrent = acctEntity.getProfitCurrentYes().add(acctEntity.getProfitCurrentNo());
	        }
	    	//产品指标表.本期利润 = 当日产品指标表DTO.当日本期利润已实现 + 当日产品指标表DTO.当日本期利润未实现
	    	acctEntity.setProfitCurrent(profitCurrent);
	    	
	    	BigDecimal prodProfitTdyAble = BigDecimal.ZERO;
	        
	        if(null!=acctEntity.getProfitTdyAble()&&null!=acctEntity.getCapital()){
	        	prodProfitTdyAble = acctEntity.getProfitTdyAble().divide(acctEntity.getCapital(),4,BigDecimal.ROUND_HALF_UP);
	        }

	    	//产品指标表.当日可供分配产品份额利润 =当日产品指标表DTO.当日可供分配利润/当日产品指标表DTO.实收资本
	    	acctEntity.setProdProfitTdyAble(prodProfitTdyAble);
	    	
	    	
	    	//产品指标表.当日净值收益率 = 调用【指标库方法计算-净值收益率计算】		
	    	acctEntity.setNetvalueTdyYield(AccumulatedValueRateCa.getNetvalueTdyYield(acctEntity.getwYield()));

	    	Map<String,Object> map = new HashMap<>();
	    	map.put("prodid", acctEntity.getProdId());
	    	map.put("valuedate", DateUtil.addDay(acctEntity.getValueDate(), -1));
	    	
	    	//产品指标表.预期收益率%
	    	
	    	if(null!=prdInfoDtoList&&prdInfoDtoList.size()>0&&prdInfoDtoList.get(0).getProdType().equals(DisclosureDictCodeList.产品类型_预期收益型产品.getValue())){
	    		map.put("cashType", DictCodeListExt1.现金流一级类型_利息.getValue());
	    		acctEntity.setYieldE(disReportProdIndexMapper.getYeildE(map));
	    	}
	    	
	    	
	    	//2.2、取前一日产品指标处理部分复杂指标
	    	
	    	//判断是否需要跑批:针对净值型产品判断，节假日不跑，季末跑批，货币型产品每日都跑  (此部分待定)
	    	// 昨日产品指标表
	    	DisReportProdIndexResult repProdIndexYed;
	    	
	    	repProdIndexYed= disReportProdIndexMapper.getDisReportProdIndexValDate(map);
	    	
	    	
	    	
	    	//产品指标表.累计净值收益率
	    	//if(前一日产品指标表DTO == null){
	    	if(null==repProdIndexYed){
	    		//产品指标表.累计净值收益率 = 产品指标表.净值收益率
	    		acctEntity.setNetvalueTotalYield(acctEntity.getNetvalueTdyYield());
	    	}else{
	    		//调用【指标库方法计算-累计净值收益率计算】
	    		
	    		BigDecimal netvalueTotalYield = BigDecimal.ZERO;
	    		
	    		if(StringUtil.isNotEmpty(acctEntity.getNetvalueTdyYield())&&StringUtil.isNotEmpty(repProdIndexYed.getNetvalueTdyYield())){
	    			netvalueTotalYield = AccumulatedValueRateCa.getAccValueRate(acctEntity.getNetvalueTdyYield(),repProdIndexYed.getNetvalueTdyYield());
	    		}
	    		
	    		acctEntity.setNetvalueTotalYield(netvalueTotalYield);
	    	}
	    		
	    	
	    	if(null==repProdIndexYed){
	    		//产品指标表.当日净值增长率
		    	 //调用【指标库方法计算-产品份额净值增长率计算】
	    		BigDecimal netvalueTdyIncre = BigDecimal.ZERO;
	    		
	    		if(StringUtil.isNotEmpty(acctEntity.getUnitValue())&&StringUtil.isNotEmpty(acctEntity.getBounusValue())){
	    			netvalueTdyIncre = ProductShareGrowthRateCa.getProShareWthRate(
			    			acctEntity.getUnitValue(), acctEntity.getBounusValue(), null!=repProdIndexYed&&null!=repProdIndexYed.getUnitValue()?repProdIndexYed.getUnitValue():BigDecimal.ONE);
	    		}
		    	acctEntity.setNetvalueTdyIncre(netvalueTdyIncre);
	    	}else{
	    		//调用【指标库方法计算-累计净值收益率计算】
	    		acctEntity.setNetvalueTotalYield(BigDecimal.ZERO);
	    	}
	    	
	    	
	    	
	    	
	    	if(null==repProdIndexYed){
		    	acctEntity.setNetvalueTotalIncre(AccumProductShareValue.getProductShareValue(
		    			checkBigDec(acctEntity.getNetvalueTdyIncre()),  
		    			null!=repProdIndexYed&&null!=repProdIndexYed.getNetvalueTotalYield()?repProdIndexYed.getUnitValue():BigDecimal.ZERO));
	    	}else{
	    		//调用【指标库方法计算-累计净值收益率计算】
	    		acctEntity.setNetvalueTotalIncre(BigDecimal.ZERO);
	    	}
	    	
	    	
	    	//产品指标表.业绩比较基准日增长率
	    	if(null==repProdIndexYed){
	    		//产品指标表.业绩比较基准日增长率 = 0

	    		acctEntity.setFloatBaseRate(BigDecimal.ZERO);
	    	}else{
	    		//产品指标表.业绩比较基准日增长率 = 当日产品指标表DTO.业绩比较基准收益率*（DTO.估值日-前一日产品指标表DTO.估值日）/365
	    		acctEntity.setFloatBaseRate(acctEntity.getRateBenchmark().multiply( 
	    				new BigDecimal(DateUtil.getBetweenDayNumber(repProdIndexYed.getValueDate(), acctEntity.getValueDate())).divide(new BigDecimal(365),10,BigDecimal.ROUND_HALF_UP)));
	    		
	    	}
	    	
	    	//产品指标表.累计业绩比较基准增长率    	
	    	if(null==repProdIndexYed){
	    		//累计业绩比较基准增长率 = 0	
	    		acctEntity.setFloatTotalRate(BigDecimal.ZERO);
	    	}else{																															
	    		//累计业绩比较基准增长率 = （业绩比较基准日增长率+1）*（前一日产品指标表DTO.累计业绩比较基准增长率+1）-1		
	    		acctEntity.setFloatTotalRate(checkBigDec(acctEntity.getFloatBaseRate()).multiply(checkBigDec(repProdIndexYed.getFloatBaseRate()).add(BigDecimal.ONE))
	    				.subtract(BigDecimal.ONE));
	    	}																															
	    																																
	    	//产品指标表.累计客户收益
	    		if(null==repProdIndexYed){
																								
		    		//累计客户收益 = 当日产品指标表DTO.当日客户收益 	
	    			acctEntity.setProfitTotal(acctEntity.getProfitTdy());
		    	}else{																								
		    		//累计客户收益 = 当日产品指标表DTO.当日客户收益 + 前一日产品指标DTO.累计客户收益		
		    		
		    		acctEntity.setProfitTotal(null!=acctEntity.getProfitTdy()?acctEntity.getProfitTdy():BigDecimal.ZERO
		    				.add(null!=repProdIndexYed.getProfitTotal()?repProdIndexYed.getProfitTotal():BigDecimal.ZERO));
		    	}																								

//	    		产品指标表.现金形式发放总额																														
	    		if(null==repProdIndexYed){
//	    			现金形式发放总额 = 当日产品指标表DTO.现金分红	
	    			acctEntity.setCashFormGrantTotal(acctEntity.getCashFormGrantTdy());
	    		}else{																													
//	    			现金形式发放总额 = 当日产品指标表DTO.现金分红 + 前一日产品指标DTO.现金形式发放总额	
	    			acctEntity.setCashFormGrantTotal(checkBigDec(acctEntity.getCashFormGrantTdy()).add(checkBigDec(repProdIndexYed.getCashFormGrantTotal())));

	    		}																													
//	    																															
//	    	产品指标表.再投资形式发放总额																														
//	    																															
	    		if(null==repProdIndexYed){																												
//	    			再投资形式发放总额 = 当日产品指标表DTO.红利再投金额	
	    			acctEntity.setReinvestFormGrantTotal(acctEntity.getReinvestFormGrantTdy());
	    		}else	{																												
//	    			再投资形式发放总额 = 当日产品指标表DTO.红利再投金额 + 前一日产品指标DTO.再投资形式发放总额	
	    			acctEntity.setReinvestFormGrantTotal(checkBigDec(acctEntity.getReinvestFormGrantTdy()).add(checkBigDec(repProdIndexYed.getReinvestFormGrantTotal())));

	    		}																													
//	    																															
//	    	             产品指标表.直接通过应付赎回款转出金额																														
	    		acctEntity.setRedemptionTurnoutAmt(checkBigDec(acctEntity.getRedemIntamtTdy()).add(checkBigDec(acctEntity.getCashFormGrantTotal())));
//	    		直接通过应付赎回款转出金额 = 现金形式发放总额 + 当日产品指标表DTO.赎回利息金额																													
//	    																															
//	    	产品指标表.应付利润本年变动																														
//	    																															
	    		if(null==repProdIndexYed){																												
//	    			应付利润本年变动 = 当日产品指标表DTO.应付利润		
	    			acctEntity.setProfitThisyearChange(acctEntity.getProfitChangeTdy());
	    		}else	{																												
//	    			应付利润本年变动 = 当日产品指标表DTO.应付利润 + 前一日产品指标DTO.应付利润本年变动			
	    			acctEntity.setProfitThisyearChange(checkBigDec(acctEntity.getProfitChangeTdy()).add(checkBigDec(repProdIndexYed.getProfitThisyearChange())));
	    		}																													
	    																															

	    		//产品指标表.投资组合平均到期日(需要根据资产指标进行计算，资产指标批需要先处理	)																																				
	    		map.put("valuedate", acctEntity.getValueDate());
	    		acctEntity.setAverageMdate(disReportProdIndexMapper.getAverageMdate(map));																																																																							
	    																																							

	    	// 产品指标表.平均剩余期限 = 空 复杂指标,第二阶段处理
	    	// 定义返回值
	    	returnDto.getRepProdIndexList().add(acctEntity);
	    	
	    	returnDto.setProdId(acctEntity.getProdId());


    	return returnDto;
    }
    
    
    public BigDecimal checkBigDec(BigDecimal bd){
    	BigDecimal val = BigDecimal.ZERO;
    	if(null!=bd){
    		val = bd;
    	}
		return val;
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
 * @param getResultType
 *            获取返回的值 【COST:成本；MARKETVALUE:市值； SUBJECTNAME:科目名称】
 * @return
 * @see
 * @since 1.0
 */
public BigDecimal getBokValTableDataForIndex(String booksetId, Date valDate, String detailDist, String subjectNo,
		String getResultType) {

	BigDecimal result = null;

	// 获取估值表指标数据
	Map<String, Object> sqlMap = new HashMap<String, Object>();
	sqlMap.put("booksetId", booksetId);
	sqlMap.put("valDate", valDate);
	sqlMap.put("detailDist", detailDist);

	if (subjectNo != null && subjectNo.length() > 0) {
		sqlMap.put("subjectNo", subjectNo);
	}

	List<BokValTableDataForIndexDto> bokValTableDataList = disReportProdIndexMapper
			.getBokValTableDataForIndex(sqlMap);

	// 此处取到的值，只取第一个
	if (bokValTableDataList != null && !bokValTableDataList.isEmpty()) {
		// 获取数据实体类
		BokValTableDataForIndexDto vo = bokValTableDataList.get(0);

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
			result = new BigDecimal(vo.getSubjectName());

		} // 其他情况
		else {
			result = null;
		}

	}

	return result;
}
}
