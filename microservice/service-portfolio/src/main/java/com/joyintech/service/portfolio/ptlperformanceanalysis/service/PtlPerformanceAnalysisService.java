package com.joyintech.service.portfolio.ptlperformanceanalysis.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlperformanceanalysis.dao.PtlPerformanceAnalysisMapper;
import com.joyintech.service.portfolio.ptlperformanceanalysis.dto.PtlPerformanceAnalysis001Dto;
import com.joyintech.service.portfolio.ptlperformanceanalysis.dto.PtlPerformanceLineDto;
import com.joyintech.tams.common.base.entity.MstIndexComponent;
import com.joyintech.tams.common.base.entity.PlTargetDb;
import com.joyintech.tams.common.base.entity.TargetDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.evaluationentrance.dto.DailyYieldDto;
import com.joyintech.tams.invest.common.evaluationentrance.service.ProdIndexAlgorithmService;
import com.joyintech.tams.invest.common.util.PtlDateUtil;

@Service
public class PtlPerformanceAnalysisService extends BaseService<PtlPerformanceAnalysis001Dto>  {
	
	@Autowired
	private PtlPerformanceAnalysisMapper ptlPerformanceAnalysisMapper;
	
	@Autowired
	private ProdIndexAlgorithmService prodIndexAlgorithmService;
	
	
    public Page<PtlPerformanceAnalysis001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
    	Date searchDateStart = DateUtil.parse((String) pageParam.get("searchDateStart"));
    	Date searchDateEnd = DateUtil.parse((String) pageParam.get("searchDateEnd"));
    	
    	pageParam.put("searchDateStart", searchDateStart);
    	pageParam.put("searchDateEnd", searchDateEnd);
    	
        Set<String> searchIds = new HashSet<String>();
        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("portfolioId")));
            
            searchIds = new HashSet<String>(StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("portfolioId")));
            
        }
        if (StringUtil.isNotEmpty(pageParam.get("profitType"))) {
            pageParam.put("profitTypeList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("profitType")));
        }
        
        if (StringUtil.isNotEmpty(pageParam.get("prodManager"))) {
            pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        } else {
        	//TODO 修改为当前用户
            pageParam.put("prodManager", null);
        }
        
        //查询明细数据
		Page<PtlPerformanceAnalysis001Dto> pageData = super.queryForPage(page, length,
				() -> ptlPerformanceAnalysisMapper.queryPageList1(pageParam));
		
//        List<PtlPerformanceAnalysis001Dto> list = pageData.getRoot();
//        
//        Map<String,List<PtlPerformanceAnalysis001Dto>> detailMap = new HashMap<String,List<PtlPerformanceAnalysis001Dto>>();
//        
//        List<PtlPerformanceAnalysis001Dto> detailList = ptlPerformanceAnalysisMapper.queryDetailList(pageParam);
//        
//		for(PtlPerformanceAnalysis001Dto detailDto :  detailList){
//			String portfolioId = detailDto.getPortfolioId();
//			String profitType = detailDto.getProfitType();     
//			StringBuffer sb = new StringBuffer();
//			sb.append(portfolioId).append("|").append(profitType);
//			if(detailMap.containsKey(sb.toString())){
//				detailMap.get(sb.toString()).add(detailDto);
//			}else {
//				List<PtlPerformanceAnalysis001Dto> tempList = new ArrayList<PtlPerformanceAnalysis001Dto>();
//				tempList.add(detailDto);
//				detailMap.put(sb.toString(), tempList);
//			}
//		}
//        
//        
//        for(PtlPerformanceAnalysis001Dto dto : list){
//        	String portfolioId = dto.getPortfolioId();
//			String profitType = dto.getProfitType();     
//			StringBuffer sb = new StringBuffer();
//			sb.append(portfolioId).append("|").append(profitType);
//			
//        	//设置根类型
//        	//对于父id不存在的一定是root
//        	if(dto.getParentId()==null){
//        		dto.setParentId("root");
//        	}
//        	//存在搜索条件的id集合
//        	if(searchIds.size()!=0 && StringUtil.isNotEmpty(dto.getParentId())){
//        		if(!searchIds.contains(dto.getParentId())){
//        			dto.setParentId("root");
//        		}
//        	}
//        	dto.setId(dto.getPortfolioId());
//        	
//        	calcPValue(detailMap.get(sb.toString()),dto,pageParam);
//        	
//        	//计算beta值
//        	calcTargetValue(dto,pageParam);
//        	
//        }
        
        return pageData;
    }
    
    
    public void calcTargetValue(PtlPerformanceAnalysis001Dto ptlPerformanceDto,
    		Map<String, Object> pageParam) throws ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
    	Date cdate = ptlPerformanceDto.getCdate();
    	//业绩基准
    	String benchmark = (String) pageParam.get("benchmark");
    	//业绩基准类型
    	String benchmarkType = (String) pageParam.get("benchmarkType");
    	
    	Date searchDateEnd =  (Date) pageParam.get("searchDateEnd");
    	
    	Map<String,Date> map = new HashMap<String,Date>();
    	
        //周初
        Date currWeek = PtlDateUtil.getCurrWeekDate(cdate);
        map.put("W", currWeek);
        //月初
        Date currMonth = DateUtil.getFirstDay(cdate);
        map.put("M", currMonth);
        
        //季初
        Date currQuarter = PtlDateUtil.getCurrQuarterDate(cdate);    
        map.put("S", currQuarter);
        
        //年初
        Date currYear = DateUtil.getFirstDayOfYear(cdate);
        map.put("Y", currYear);
        
        //一个月前
        Date preOneMonth = DateUtil.addMonthDate(cdate,-1);
        map.put("LoneM", preOneMonth);
        //三个月前
        Date preThreeMonth = DateUtil.addMonthDate(cdate, -3);
        map.put("LthreeM", preThreeMonth);
        //六个月前（半年前）
        Date preSixMonth = DateUtil.addMonthDate(cdate, -6);
        map.put("LHy", preSixMonth);
        //一年前
        Date preTwelveMonth = DateUtil.addMonthDate(cdate, -12);
        map.put("LY", preTwelveMonth);
        
        //成立至今
        map.put("R", preTwelveMonth);
        
        //区间范围
        map.put("P", searchDateEnd);
        
        
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("portfolioId", ptlPerformanceDto.getPortfolioId());
    	betaParam.put("endDate", cdate);  
    	
    	Map<String,Object> sharpeParam = new HashMap<String,Object>();
    	sharpeParam.put("endDate", cdate);
    	sharpeParam.put("intRateType", DictCodeList.行情类型_利率.getValue());
    	sharpeParam.put("intRateId", DictCodeList.利率行情_银行间1天同业拆借利率.getValue());
    	
    	
        for(String key : map.keySet()){
        	betaParam.put("startDate", map.get(key));
        	sharpeParam.put("startDate", map.get(key));
        	List<DailyYieldDto> dyRList = ptlPerformanceAnalysisMapper.queryDailyYieldDto(betaParam);
        	
        	Date startDate = map.get(key);
        	Date endDate = ptlPerformanceDto.getCdate();
        	if(dyRList.size()>0){
        		endDate = dyRList.get(dyRList.size()-1).getCdate();
        		startDate = dyRList.get(0).getCdate();
        	}
        	List<DailyYieldDto> dyBRList = getDyBRList(ptlPerformanceDto,startDate,endDate,pageParam,"BR");
        	List<DailyYieldDto> dyMarkList = getDyBRList(ptlPerformanceDto,startDate,endDate,pageParam,"NORISK");
        	
        	calcValue(ptlPerformanceDto,dyRList,dyBRList,dyMarkList,key);
        }
    	
    }
    
    
    public List<DailyYieldDto> getDyBRList(PtlPerformanceAnalysis001Dto ptlPerformanceDto,Date startDate,Date endDate,
    		Map<String, Object> pageParam,String type){
    	
    	List<DailyYieldDto> list = new ArrayList<DailyYieldDto>();
    	String benchmark = (String) pageParam.get("benchmark");
    	String benchmarkType = (String) pageParam.get("benchmarkType");
    	
    	BigDecimal coefficient = convertoBigDecimal((String)pageParam.get("coefficient"),BigDecimal.ONE);
    	BigDecimal spreadRate = convertoBigDecimal((String)pageParam.get("spreadRate"),null);
    	
    	if("NORISK".equals(type)){
    		benchmark = (String) pageParam.get("noRiskRate");
        	benchmarkType = (String) pageParam.get("noRiskRateType");
        	coefficient =  convertoBigDecimal((String)pageParam.get("noRiskCoefficient"),BigDecimal.ONE);
        	spreadRate = convertoBigDecimal((String)pageParam.get("noRiskSpreadRate"),null);
    	}
        
    	if(DictCodeListExt1.基准类型_2_利率.getValue().equals(benchmarkType) ){
        	list = calcIntDailyYieldList(startDate,endDate,benchmark,benchmarkType,coefficient,spreadRate,BigDecimal.ONE,"0");
        	//converYieldList(list,coefficient,spreadRate);
        	
    	}else if(DictCodeListExt1.基准类型_2_收益率曲线.getValue().equals(benchmarkType)){
        	list = calcCurveDailyYieldList(startDate,endDate,benchmark,benchmarkType,coefficient,spreadRate,BigDecimal.ONE,"0");
        	//converYieldList(list,coefficient,spreadRate);
    		
    	}else if(DictCodeListExt1.基准类型_2_指数.getValue().equals(benchmarkType)){
        	list = calcQuoDailyYieldList(startDate,endDate,benchmark,benchmarkType,coefficient,spreadRate,BigDecimal.ONE,"0");	
    		
    	}else if(DictCodeListExt1.基准类型_2_复合.getValue().equals(benchmarkType)) {
    		
    		list = calcFHJZDailyYieldList(startDate,endDate,benchmark,benchmarkType,coefficient,spreadRate,"0");
    	}
    	
    	return list;
    }
    
    
    public void calcValue(PtlPerformanceAnalysis001Dto ptlPerformanceDto,List<DailyYieldDto> dyRList,
    		List<DailyYieldDto> dyBRList,List<DailyYieldDto> dyMarkList,
    		String type) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
    	
    	if("P".equals(type)){
        	//波动率计算
        	BigDecimal betaValue = prodIndexAlgorithmService.calcVolatility(dyRList);	
        	Field betaField = ptlPerformanceDto.getClass().getDeclaredField("vix"+type);
        	betaField.setAccessible(true);
        	betaField.set(ptlPerformanceDto, betaValue);
        	
        	BigDecimal maxRetreatValue = BigDecimal.ZERO;
        	//最大回撤
        	if(dyRList.size() > 0){
        		maxRetreatValue = prodIndexAlgorithmService.calcMaxRetreat(dyRList);
        	}
        	Field maxRetreatField = ptlPerformanceDto.getClass().getDeclaredField("maxDr"+type);
        	maxRetreatField.setAccessible(true);
        	maxRetreatField.set(ptlPerformanceDto, maxRetreatValue);
    	}
    	
    	//Beta计算
    	BigDecimal betaValue = prodIndexAlgorithmService.calcβ(dyRList, dyBRList, null);	
    	Field betaField = ptlPerformanceDto.getClass().getDeclaredField("beta"+type);
    	betaField.setAccessible(true);
    	betaField.set(ptlPerformanceDto, betaValue);
    	
    	//夏普率计算
    	BigDecimal sharpeValue = prodIndexAlgorithmService.calcSharpeRatio(dyRList,dyMarkList,new BigDecimal(250));
    	Field sharpeField = null;
    	if("P".equals(type)){
    		sharpeField = ptlPerformanceDto.getClass().getDeclaredField("sharpe"+type);
    	}else {
    		sharpeField = ptlPerformanceDto.getClass().getSuperclass().getDeclaredField("sharpe"+type);
    	}	
    	sharpeField.setAccessible(true);
    	sharpeField.set(ptlPerformanceDto, sharpeValue);
    	
    	//索提诺比率
    	BigDecimal sortinoValue = prodIndexAlgorithmService.calcSortinoRatio(dyRList,dyMarkList,new BigDecimal(250));
    	Field sortinoField = null;
    	if("P".equals(type)){
    		sortinoField = ptlPerformanceDto.getClass().getDeclaredField("sortino"+type);
    	}else {
    		sortinoField = ptlPerformanceDto.getClass().getSuperclass().getDeclaredField("sortino"+type);
    	}
    	sortinoField.setAccessible(true);
    	sortinoField.set(ptlPerformanceDto, sortinoValue);
    	
    	//TREYNOR计算
    	BigDecimal treynorValue = prodIndexAlgorithmService.calcTreynorRatio(dyRList, dyBRList, dyMarkList);	
    	Field treynorField = ptlPerformanceDto.getClass().getDeclaredField("treynor"+type);
    	treynorField.setAccessible(true);
    	treynorField.set(ptlPerformanceDto, treynorValue);
    	
    	//Jenson 计算
    	BigDecimal jensonValue = prodIndexAlgorithmService.calcJensenIndex(dyRList, dyBRList, dyMarkList);	
    	Field jensonField = ptlPerformanceDto.getClass().getDeclaredField("jenson"+type);
    	jensonField.setAccessible(true);
    	jensonField.set(ptlPerformanceDto, jensonValue);
    	
    	// IR计算
    	BigDecimal irValue = prodIndexAlgorithmService.calcIR(dyRList, dyBRList);
    	Field irField = ptlPerformanceDto.getClass().getDeclaredField("ir"+type);
    	irField.setAccessible(true);
    	irField.set(ptlPerformanceDto, irValue);
    	
    	//跟踪误差
    	BigDecimal trackValue = prodIndexAlgorithmService.calcTrack(dyRList, dyBRList);
    	Field trackField = ptlPerformanceDto.getClass().getDeclaredField("track"+type);
    	trackField.setAccessible(true);
    	trackField.set(ptlPerformanceDto, trackValue);
    	
    }
    
    //计算区间增长率
    public void calcPValue(List<PtlPerformanceAnalysis001Dto> calcDetalDto,
    		PtlPerformanceAnalysis001Dto rowDto,Map<String, Object> param){

    	PtlPerformanceAnalysis001Dto firstTargetDto = calcDetalDto.get(0);
    	PtlPerformanceAnalysis001Dto lastTargetDto = calcDetalDto.get(calcDetalDto.size()-1);
    	
     	Date searchDateStart = firstTargetDto.getCdate();
    	Date searchDateEnd = lastTargetDto.getCdate();
    	
       	//区间总净值
    	if(BigDecimal.ZERO.compareTo(firstTargetDto.getAssetValue())==0){
    		rowDto.setAssetGrP(BigDecimal.ONE);
    	}else  {
        	rowDto.setAssetGrP((lastTargetDto.getAssetValue().
        			subtract(firstTargetDto.getAssetValue())).divide(firstTargetDto.getAssetValue(),4,BigDecimal.ROUND_HALF_UP));
    	}
    	
    	//区间净资产
    	if(BigDecimal.ZERO.compareTo(firstTargetDto.getAssetNetvalue())==0){
    		rowDto.setNetassetGrP(BigDecimal.ONE);
    	}else {
        	rowDto.setNetassetGrP((lastTargetDto.getAssetNetvalue().
        			subtract(firstTargetDto.getAssetNetvalue())).divide(firstTargetDto.getAssetNetvalue(),4,BigDecimal.ROUND_HALF_UP));    		
    	}

    	//区间单位净值
    	if(BigDecimal.ZERO.compareTo(firstTargetDto.getUnitValue())==0){
    		rowDto.setNetunitGrP(BigDecimal.ONE);
    	}else {
        	rowDto.setNetunitGrP((lastTargetDto.getUnitValue().
        			subtract(firstTargetDto.getUnitValue())).divide(firstTargetDto.getUnitValue(),4,BigDecimal.ROUND_HALF_UP));
    	}
    	
    	rowDto.setNetunitGrP2(BigDecimal.ONE);
//    	if(BigDecimal.ZERO.compareTo(firstTargetDto.getTotUniitValue())==0){
//    		rowDto.setNetunitGrP2(BigDecimal.ONE);
//    	}else {
//        	//累计区间单位净值 
//        	rowDto.setNetunitGrP2((lastTargetDto.getTotUniitValue().
//        			subtract(firstTargetDto.getTotUniitValue())).divide(firstTargetDto.getTotUniitValue(),4,BigDecimal.ROUND_HALF_UP));   		
//    	}

    	
    	//收益类型
    	if(DictCodeListExt2.收益类型_净值.getValue().equals(rowDto.getProfitType())){
    		BigDecimal time = new BigDecimal(DateUtil.getBetweenDayNumber(searchDateStart, searchDateEnd));
    		rowDto.setYieldP(rowDto.getNetunitGrP2().subtract(time).multiply(new BigDecimal(365)));
    	}else if(DictCodeListExt2.收益类型_货币.getValue().equals(rowDto.getProfitType())){
    		//TODO 计算货币型绝对区间收益率
    		if(firstTargetDto.getCdate().compareTo(lastTargetDto.getCdate())==0){
    			rowDto.setYieldP(BigDecimal.ZERO);
    		}else {
        		BigDecimal b  = BigDecimal.ONE.divide(new BigDecimal(DateUtil.getBetweenDayNumber(firstTargetDto.getCdate(), lastTargetDto.getCdate())),8,BigDecimal.ROUND_HALF_UP);
        		double yieldP = Math.pow(calcYield(calcDetalDto).doubleValue(),b.doubleValue())-1;
        		rowDto.setYieldP(new BigDecimal(yieldP));   			
    		}

    	}
    	//绝对区间收益率
    	rowDto.setAbsoluteYieldP(rowDto.getNetunitGrP2());
    	
    }
    
    //∏_(i=1)^n▒〖（1+R_i/10000）-1〗
    public BigDecimal calcYield(List<PtlPerformanceAnalysis001Dto> calcDetalList){
    	
    	BigDecimal yield = BigDecimal.ONE;
    	for(PlTargetDb targetDb : calcDetalList){
    		yield = yield.multiply(BigDecimal.ONE.add(targetDb.getAssetNetvalue().divide(new BigDecimal(10000))));
    	}
    	return yield.subtract(BigDecimal.ONE);
    }
    
    /**
     * 返回利率型的序列
     * @param startDate
     * @param endDate
     * @param benchmark
     * @param benchmarkType
     * @param pageParam
     * @return
     */
    public List<DailyYieldDto> calcIntDailyYieldList(
    		Date startDate,Date endDate,String benchmark,String benchmarkType,
    		BigDecimal coefficient,BigDecimal spreadRate,
    		BigDecimal weight,String type){
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("IntRateId", benchmark);
    	betaParam.put("IntRateType", benchmarkType);
    	
    	if(startDate.compareTo(endDate)< 0){
    		betaParam.put("sectionFlag", "1");
    	}
    	
    	List<DailyYieldDto> list = ptlPerformanceAnalysisMapper.queryIntYieldByBenchmark(betaParam);
    	if("0".equals(type)){
    		converYieldList(list,null,null,weight);
    	}else {
    		converYieldList(list,coefficient,spreadRate,weight);
    	}
    	return list;
    }
    
    
    /**
     * 返回曲线型的序列
     * @param startDate
     * @param endDate
     * @param benchmark
     * @param benchmarkType
     * @param pageParam
     * @return
     */
    public List<DailyYieldDto> calcCurveDailyYieldList(
    		Date startDate,Date endDate,String benchmark,String benchmarkType,
    		BigDecimal coefficient,BigDecimal spreadRate,
    		BigDecimal weight,String type){
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("curveCode", benchmark);
    	List<DailyYieldDto> list = ptlPerformanceAnalysisMapper.queryCurveYieldByBenchmark(betaParam);
    	if("0".equals(type)){
    		converYieldList(list,null,null,weight);
    	}else {
    		converYieldList(list,coefficient,spreadRate,weight);
    	}
    	return list;
    }

    /**
     * 返回曲线型的序列
     * @param startDate
     * @param endDate
     * @param benchmark
     * @param benchmarkType
     * @param pageParam
     * @return
     */
    public List<DailyYieldDto> calcQuoDailyYieldList(
    		Date startDate,Date endDate,String benchmark,String benchmarkType,
    		BigDecimal coefficient,BigDecimal spreadRate,
    		BigDecimal weight,String type){
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("marketId", benchmark);
    	List<DailyYieldDto> list = ptlPerformanceAnalysisMapper.queryQuoYieldByBenchmark(betaParam);
    	if("0".equals(type)){
    		converYieldList(list,null,null,weight);
    	}else {
    		converYieldList(list,coefficient,spreadRate,weight);
    	}
    	return list;
    }
    
    /**
     * 返回复合基准的序列
     * @param startDate
     * @param endDate
     * @param benchmark
     * @param benchmarkType
     * @param pageParam
     * @return
     */
    public List<DailyYieldDto> calcFHJZDailyYieldList(
    		Date startDate,Date endDate,String benchmark,String benchmarkType,
    		BigDecimal coefficient,BigDecimal spreadRate,String type){
    	
    	//查询基准成分表，获取权重信息
    	List<MstIndexComponent> indexlist = ptlPerformanceAnalysisMapper.queryIndexComponent(benchmark);
    	 
    	List<DailyYieldDto> fhjzYieldDtoList = new ArrayList<DailyYieldDto>();
    	
    	List<DailyYieldDto> intYieldDtoList = new ArrayList<DailyYieldDto>();
    	
    	List<DailyYieldDto> curveYieldDtoList = new ArrayList<DailyYieldDto>();
    	
    	List<DailyYieldDto> quoYieldDtoList = new ArrayList<DailyYieldDto>();
    	
    	int length = 0;
    	
    	for(MstIndexComponent indexDto : indexlist){
    		if(DictCodeListExt1.基准类型_2_利率.getValue().equals(indexDto.getComponentType()) ){
    			intYieldDtoList = calcIntDailyYieldList(startDate,endDate,benchmark,benchmarkType,coefficient,spreadRate,indexDto.getWeight(),type);
    			length = intYieldDtoList.size();
    			
        	}else if(DictCodeListExt1.基准类型_2_收益率曲线.getValue().equals(indexDto.getComponentType())){
        		curveYieldDtoList = calcCurveDailyYieldList(startDate,endDate,benchmark,benchmarkType,coefficient,spreadRate,indexDto.getWeight(),type);
        		length = curveYieldDtoList.size();
        		
        	}else if(DictCodeListExt1.基准类型_2_指数.getValue().equals(indexDto.getComponentType())){
        		quoYieldDtoList = calcQuoDailyYieldList(startDate,endDate,benchmark,benchmarkType,coefficient,spreadRate,indexDto.getWeight(),type);	
        		length = quoYieldDtoList.size();
        	}
    	}
    	
    	for(int i = 0; i < length ; i++){
    		DailyYieldDto dto = new DailyYieldDto();
    		BigDecimal yield = BigDecimal.ZERO;
    		
    		Date cdate = DateUtil.addDay(startDate, 0);
    		
    		if(intYieldDtoList.size()>0){
    			yield = yield.add(intYieldDtoList.get(i).getYield());
    		}
    		
    		if(intYieldDtoList.size()>0){
    			yield = yield.add(curveYieldDtoList.get(i).getYield());
    		}
    		
    		if(intYieldDtoList.size()>0){
    			yield = yield.add(quoYieldDtoList.get(i).getYield());
    		}  
    		
    		dto.setYield(yield);
    		dto.setCdate(cdate);
    		fhjzYieldDtoList.add(dto);
    	}
    	
    	return fhjzYieldDtoList;
    }    
    
    /**
     * 没有收益率的自动补最近的值
     * @param resultList
     */
    public void converYieldList(List<DailyYieldDto> resultList,
    		BigDecimal coefficient,BigDecimal spreadRate,BigDecimal weight){
    	
    	for(int i = 0 ;i < resultList.size() ;i++){
    		DailyYieldDto dto = resultList.get(i);
    		if(dto.getYield() == null){
    			if (i > 0) {
    				dto.setYield(resultList.get(i-1).getYield().multiply(weight));
    			} else {
    				dto.setYield(new BigDecimal("0.01"));
    			}
    		}
    		if(coefficient != null && spreadRate !=null) {
    			BigDecimal yield = dto.getYield().multiply(coefficient).multiply(spreadRate);
    			dto.setYield(yield.multiply(weight));
    		}
    	}
    }
	
	
    /**
     * 指标设置树结构数据-》组合持仓分析报表
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<TargetDetail> getAllTreeNode(String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", param);
        map.put("isDefault", DictCodeList.是否_是.getValue());
        map.put("nodeType", DictCodeListExt3.指标类型代码_产品表现分析.getValue());
        return ptlPerformanceAnalysisMapper.getAllTreeNode(map);
    }
    
    /**
     * 获取动态列-》组合持仓分析报表
     *
     * @param param 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<TargetDetail> getDataTableCols(String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", param);
        map.put("isDefault", DictCodeList.是否_是.getValue());
        map.put("nodeType", DictCodeListExt3.指标类型代码_产品表现分析.getValue());
        return ptlPerformanceAnalysisMapper.getDataTableCols(map);
    }
    
    public List<TargetDetail> getTableColumn() {
        return ptlPerformanceAnalysisMapper.getTableColumn();

    }
    
    
    // 获取默认显示列
    public List<TargetDetail> getDefaultTableCols(String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("isDefault", DictCodeList.是否_是.getValue());
        map.put("nodeType", DictCodeListExt3.指标类型代码_产品表现分析.getValue());
        return ptlPerformanceAnalysisMapper.getDefaultTableCols(map);
    }
    
    public PtlPerformanceLineDto findLineData(Map<String,Object> map){
    	
    	Date dateStartDate = DateUtil.parse((String)map.get("searchDateStart"));
    	Date dateEndDate = DateUtil.parse((String)map.get("searchDateEnd"));
    	
    	Date vdate = null;
    	
    	PtlPerformanceLineDto ptlPerformanceLineDto = new PtlPerformanceLineDto();
    	
    	String benchmarkType = (String)map.get("benchmarkType");
    	
    	String comparisonIndex = (String)map.get("comparisonIndex");
    	
    	String type = "P";
    	if("01".equals(comparisonIndex) || "02".equals(comparisonIndex)){
    		type = "total";
    	}
    	
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("portfolioId",(String)map.get("portfolioId"));
		queryMap.put("searchDateStart",DateUtil.parse((String)map.get("searchDateStart")));
		queryMap.put("searchDateEnd",DateUtil.parse((String)map.get("searchDateEnd")));
		List<PtlPerformanceAnalysis001Dto> list = ptlPerformanceAnalysisMapper.queryDetailList(queryMap);
		
    	BigDecimal maxyleftData = null;
    	BigDecimal minyleftData = null;
    	
    	BigDecimal yield = BigDecimal.ZERO;
    	
		for(PtlPerformanceAnalysis001Dto dto : list){
			if(vdate == null){
				vdate = dto.getPlVdate();
			}
			
			ptlPerformanceLineDto.getXaxisdata().add(DateUtil.format(dto.getCdate()));
			if("01".equals(comparisonIndex)){
				ptlPerformanceLineDto.getYleftdata().add(dto.getUnitValue().toString());
				yield = dto.getUnitValue();
			}else if("02".equals(comparisonIndex)){
				ptlPerformanceLineDto.getYleftdata().add(dto.getTotUniitValue().toString());
				yield = dto.getTotUniitValue();
			}else if("03".equals(comparisonIndex)){
				ptlPerformanceLineDto.getYleftdata().add(dto.getTdyYield().toString());
				yield = dto.getTdyYield();
			}else if("04".equals(comparisonIndex)){
				ptlPerformanceLineDto.getYleftdata().add(dto.getYield7().toString());
				yield = dto.getYield7();
			}	
			
    		
    		if(maxyleftData == null){
    			maxyleftData = yield;
    		}
    		
    		if(minyleftData == null){
    			minyleftData = yield;
    		}
    		
    		if(minyleftData.compareTo(yield)>0){
    			minyleftData = yield;
    		}

    		if(maxyleftData.compareTo(yield)<0){
    			maxyleftData = yield;
    		}
		}
		
		//平均分配
		BigDecimal avgYield = maxyleftData.subtract(minyleftData).divide(new BigDecimal(5),BigDecimal.ROUND_HALF_UP,4);
		for(int i = 0 ; i < 5 ; i++){
			BigDecimal tempYield = minyleftData.add(avgYield);
			ptlPerformanceLineDto.getYleftaxisdata().add(tempYield.toString());
		}

		
		
		//对于日期时间赋值
		if(list.size() > 0 ){
			dateStartDate = list.get(0).getCdate();
			dateEndDate = list.get(list.size()-1).getCdate();	
		}
    	
		List<DailyYieldDto> resultList = new ArrayList<DailyYieldDto>();
		
    	if(DictCodeListExt1.基准类型_2_利率.getValue().equals(benchmarkType) ){
    		resultList = calcBatchIntYield(dateStartDate,dateEndDate,map,vdate,type);
    		
    	}else if(DictCodeListExt1.基准类型_2_收益率曲线.getValue().equals(benchmarkType)){
    		
    		resultList = calcBatchCurveYield(dateStartDate,dateEndDate,map,vdate,type);
    		
    	}else if(DictCodeListExt1.基准类型_2_指数.getValue().equals(benchmarkType)){
    		
    		resultList = calcBatchQuoYield(dateStartDate,dateEndDate,map,type);
    		
    	}else if(DictCodeListExt1.基准类型_2_复合.getValue().equals(benchmarkType)) {
    		
    		resultList = calcBatchFHJZYield(dateStartDate,dateEndDate,map,vdate,type);
    	}
    	
    	BigDecimal maxyrightData = null;
    	BigDecimal minyrightData = null;
    	
    	for(DailyYieldDto  tempDto : resultList){
    		
    		BigDecimal yrightYield = new BigDecimal(100).multiply(tempDto.getYield());
    		ptlPerformanceLineDto.getYrightdata().add(yrightYield.toString());
    		
    		if(maxyrightData == null){
    			maxyrightData = yrightYield;
    		}
    		
    		if(minyrightData == null){
    			minyrightData = yrightYield;
    		}
    		
    		if(minyrightData.compareTo(yrightYield)>0){
    			minyrightData = yrightYield;
    		}

    		if(maxyrightData.compareTo(yrightYield)<0){
    			maxyrightData = yrightYield;
    		}
    	}
		//平均分配
    	
    	if(maxyrightData ==null && minyrightData == null){
    		ptlPerformanceLineDto.getYrightaxisdata().add("0");
    		ptlPerformanceLineDto.getYrightaxisdata().add("100");
    	}else {
    		
    		BigDecimal avgYield2 = maxyrightData.subtract(minyrightData).divide(new BigDecimal(5),BigDecimal.ROUND_HALF_UP,4);
    		for(int i = 0 ; i < 5 ; i++){
    			BigDecimal tempYield = minyrightData.add(avgYield2);
    			ptlPerformanceLineDto.getYrightaxisdata().add(tempYield.toString());
    		}
    	}
    	
    	

    	
    	return ptlPerformanceLineDto;
    }
    
    
    public List<DailyYieldDto> calcBatchQuoYield(Date startDate,Date endDate,Map<String,Object> map,String type){
    	
    	List<DailyYieldDto> resultList = new ArrayList<DailyYieldDto>();
    	
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("marketId", (String)map.get("benchmark"));
    	if(startDate.compareTo(endDate)< 0){
    		betaParam.put("sectionFlag", "1");
    	}
    	List<DailyYieldDto> list = ptlPerformanceAnalysisMapper.queryQuoYieldByBenchmark(betaParam);
    	
    	betaParam.put("portfolioId", (String)map.get("portfolioId"));
    	DailyYieldDto vdateDto = ptlPerformanceAnalysisMapper.queryQuoYieldToVdate(betaParam);
    	converYieldList(list,null,null,BigDecimal.ONE);
    	for(DailyYieldDto dto : list){
    		DailyYieldDto temp = new DailyYieldDto();
    		temp.setCdate(dto.getCdate());
    		if("total".equals(type)){
    			temp.setYield(calcSingleQuoTotalYield(dto.getYield(),vdateDto.getYield(),BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),dto.getCdate(),vdateDto.getCdate()));
    		}else {
    			temp.setYield(calcSingleQuoPYield(dto.getYield(),vdateDto.getYield(),BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),dto.getCdate(),vdateDto.getCdate()));
    		}
    		resultList.add(temp);
    	}
    	
    	return resultList;
    	
    }

    public List<DailyYieldDto> calcBatchQuoYield(Date startDate,Date endDate,Map<String,Object> map,String type,BigDecimal weight){
    	
    	List<DailyYieldDto> resultList = new ArrayList<DailyYieldDto>();
    	
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("marketId", (String)map.get("benchmark"));
    	
    	if(startDate.compareTo(endDate)< 0){
    		betaParam.put("sectionFlag", "1");
    	}
    	
    	List<DailyYieldDto> list = ptlPerformanceAnalysisMapper.queryQuoYieldByBenchmark(betaParam);
    	
    	betaParam.put("portfolioId", (String)map.get("portfolioId"));
    	DailyYieldDto vdateDto = ptlPerformanceAnalysisMapper.queryQuoYieldToVdate(betaParam);
    	converYieldList(list,null,null,BigDecimal.ONE);
    	for(DailyYieldDto dto : list){
    		DailyYieldDto temp = new DailyYieldDto();
    		temp.setCdate(dto.getCdate());
    		if("total".equals(type)){
    			temp.setYield(calcSingleQuoTotalYield(dto.getYield(),vdateDto.getYield(),BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),dto.getCdate(),vdateDto.getCdate(),weight));
    		}else {
    			temp.setYield(calcSingleQuoPYield(dto.getYield(),vdateDto.getYield(),BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),dto.getCdate(),vdateDto.getCdate(),weight));
    		}
    		resultList.add(temp);
    		
    	}
    	
    	return resultList;
    	
    }
    
    //系数*（估值日指数收盘点数-组合起息日指数开盘点数）/组合起息日指数开盘点数 *权重*365/(估值日期-组合起息日+1)
    //计算累计年化收益率
    public BigDecimal calcSingleQuoTotalYield(BigDecimal curClosePrice,
    		BigDecimal vdateClosePrice,BigDecimal weight,BigDecimal coefficient,
    		Date curDate,Date vdate){
    	
    	BigDecimal denominator = BigDecimal.ONE;
    	
    	denominator = vdateClosePrice.multiply(weight).multiply(new BigDecimal(365)).
    			multiply(new BigDecimal(DateUtil.compareDate(curDate, vdate)+1));
    	
    	return coefficient.multiply(curClosePrice.subtract(curClosePrice)).divide(denominator,4,BigDecimal.ROUND_HALF_UP);
    	
    }
    
    
    //系数*（估值日指数收盘点数-组合起息日指数开盘点数）/组合起息日指数开盘点数 *权重*365/(估值日期-组合起息日+1)
    //计算累计年化收益率
    public BigDecimal calcSingleQuoTotalYield(BigDecimal curClosePrice,
    		BigDecimal vdateClosePrice,BigDecimal weight,BigDecimal coefficient,
    		Date curDate,Date vdate,BigDecimal fhjzweight){
    	
    	return calcSingleQuoTotalYield(curClosePrice,vdateClosePrice,weight,coefficient,curDate,vdate).multiply(fhjzweight);
    	
    }
    
    //系数*（估值日指数收盘点数-查询区间开始日期指数开盘点数）/查询期开始日指数开盘点数 *权重
    //计算区间收益率
    public BigDecimal calcSingleQuoPYield(BigDecimal curClosePrice,
    		BigDecimal vdateClosePrice,BigDecimal weight,BigDecimal coefficient,
    		Date curDate,Date vdate){
    	
    	BigDecimal denominator = BigDecimal.ONE;
    	
    	denominator = vdateClosePrice.multiply(weight);
    	
    	return coefficient.multiply(curClosePrice.subtract(curClosePrice)).divide(denominator,4,BigDecimal.ROUND_HALF_UP);
    	
    }

    //系数*（估值日指数收盘点数-查询区间开始日期指数开盘点数）/查询期开始日指数开盘点数 *权重
    //计算区间收益率
    public BigDecimal calcSingleQuoPYield(BigDecimal curClosePrice,
    		BigDecimal vdateClosePrice,BigDecimal weight,BigDecimal coefficient,
    		Date curDate,Date vdate,BigDecimal fhjzweight){
    	return calcSingleQuoPYield(curClosePrice,vdateClosePrice,weight,coefficient,curDate,vdate).multiply(fhjzweight);
    	
    }
    
    public List<DailyYieldDto> calcBatchIntYield(Date startDate,Date endDate,Map<String,Object> map,Date vdate,String type){
    	List<DailyYieldDto> resultList = new ArrayList<DailyYieldDto>();
    	
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("IntRateId", (String)map.get("benchmark"));
    	betaParam.put("IntRateType", (String)map.get("benchmarkType"));
    	if(startDate.compareTo(endDate)< 0){
    		betaParam.put("sectionFlag", "1");
    	}
    	List<DailyYieldDto> list = ptlPerformanceAnalysisMapper.queryIntYieldByBenchmark(betaParam);
    	
    	converYieldList(list,null,null,BigDecimal.ONE);
    	
    	List<DailyYieldDto> calcList = new ArrayList<DailyYieldDto>();
    	
    	for(DailyYieldDto dto : list){
    		calcList.add(dto);
    		
    		DailyYieldDto temp = new DailyYieldDto();
    		temp.setCdate(dto.getCdate());
    		if("total".equals(type)){
    			temp.setYield(calcSingleIntTotalYield(calcList,BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),new BigDecimal((String)map.get("spreadRate")),dto.getCdate(),vdate));
    		}else {
    			temp.setYield(calcSingleIntPYield(calcList,BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),new BigDecimal((String)map.get("spreadRate")),dto.getCdate(),vdate));
    		}
    		
    		resultList.add(temp);
    	}
    	
    	
    	return resultList;
    	
    }

    public List<DailyYieldDto> calcBatchIntYield(Date startDate,Date endDate,Map<String,Object> map,Date vdate,String type,BigDecimal weight){
    	
    	List<DailyYieldDto> resultList = new ArrayList<DailyYieldDto>();
    	
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("IntRateId", (String)map.get("benchmark"));
    	betaParam.put("IntRateType", (String)map.get("benchmarkType"));
    	if(startDate.compareTo(endDate)< 0){
    		betaParam.put("sectionFlag", "1");
    	}
    	List<DailyYieldDto> list = ptlPerformanceAnalysisMapper.queryIntYieldByBenchmark(betaParam);
    	
    	converYieldList(list,null,null,BigDecimal.ONE);
    	
    	List<DailyYieldDto> calcList = new ArrayList<DailyYieldDto>();
    	
    	for(DailyYieldDto dto : list){
    		calcList.add(dto);
    		DailyYieldDto temp = new DailyYieldDto();
    		temp.setCdate(dto.getCdate());
    		if("total".equals(type)){
    			temp.setYield(calcSingleIntTotalYield(calcList,BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),new BigDecimal((String)map.get("spreadRate")),dto.getCdate(),vdate,weight));
    		}else {
    			temp.setYield(calcSingleIntPYield(calcList,BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),new BigDecimal((String)map.get("spreadRate")),dto.getCdate(),vdate,weight));
    		}
    		resultList.add(temp);
    	}
    	
    	return resultList;
    	
    }
    
    //计算累计年化收益率
    public BigDecimal calcSingleIntTotalYield(List<DailyYieldDto> list,
    		BigDecimal weight,BigDecimal coefficient,
    		BigDecimal spreadRate,Date curDate,Date vdate){
    	
    	BigDecimal totalYield = BigDecimal.ONE;
    	//TODO 
    	for(DailyYieldDto dto : list){
    		BigDecimal temp = BigDecimal.ONE.add(
    				dto.getYield().multiply(coefficient).add(spreadRate).divide(new BigDecimal(365),16,BigDecimal.ROUND_HALF_UP));
    		totalYield = totalYield.multiply(temp);
    	}
    	BigDecimal denominator = new BigDecimal(DateUtil.getBetweenDayNumber(curDate, vdate)+1);
    	return (totalYield.subtract(BigDecimal.ONE)).multiply(weight).divide(denominator).divide(denominator,16,BigDecimal.ROUND_HALF_UP);
    	
    	
    }
    
    //计算累计年化收益率
    public BigDecimal calcSingleIntTotalYield(List<DailyYieldDto> list,
    		BigDecimal weight,BigDecimal coefficient,
    		BigDecimal spreadRate,Date curDate,Date vdate,BigDecimal fhjzweight){
    	
    	return calcSingleIntTotalYield(list,weight,coefficient,spreadRate,curDate,vdate).multiply(fhjzweight);
    	
    	
    }    

    //计算区间收益率
    public BigDecimal calcSingleIntPYield(List<DailyYieldDto> list,
	BigDecimal weight,BigDecimal coefficient,
	BigDecimal spreadRate,Date curDate,Date vdate){
    	BigDecimal totalYield = BigDecimal.ONE;
    	//TODO 
    	for(DailyYieldDto dto : list){
    		BigDecimal temp = BigDecimal.ONE.add(
    				dto.getYield().multiply(coefficient).add(spreadRate).divide(new BigDecimal(365),16,BigDecimal.ROUND_HALF_UP));
    		totalYield = totalYield.multiply(temp);
    	}
    	return (totalYield.subtract(BigDecimal.ONE)).multiply(weight);
    }
    
    
    //计算区间收益率
    public BigDecimal calcSingleIntPYield(List<DailyYieldDto> list,
	BigDecimal weight,BigDecimal coefficient,
	BigDecimal spreadRate,Date curDate,Date vdate,BigDecimal fhjzweight){
    	return calcSingleIntPYield(list,weight,coefficient,spreadRate,curDate,vdate,fhjzweight);
    }    
    
    public List<DailyYieldDto> calcBatchCurveYield(Date startDate,Date endDate,Map<String,Object> map,Date vdate,String type){
    	
    	List<DailyYieldDto> resultList = new ArrayList<DailyYieldDto>();
    	
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("IntRateId", (String)map.get("benchmark"));
    	betaParam.put("IntRateType", (String)map.get("benchmarkType"));
    	if(startDate.compareTo(endDate)< 0){
    		betaParam.put("sectionFlag", "1");
    	}
    	List<DailyYieldDto> list = ptlPerformanceAnalysisMapper.queryCurveYieldByBenchmark(betaParam);
    	
    	converYieldList(list,null,null,BigDecimal.ONE);
    	
    	List<DailyYieldDto> calcList = new ArrayList<DailyYieldDto>();
    	
    	for(DailyYieldDto dto : list){
    		calcList.add(dto);
    		DailyYieldDto temp = new DailyYieldDto();
    		temp.setCdate(dto.getCdate());
    		if("total".equals(type)){
    			temp.setYield(calcSingleIntTotalYield(calcList,BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),new BigDecimal((String)map.get("spreadRate")),dto.getCdate(),vdate));
    		}else {
    			temp.setYield(calcSingleIntPYield(calcList,BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),new BigDecimal((String)map.get("spreadRate")),dto.getCdate(),vdate));
    		}
    	}
    	
    	return resultList;
    	
    }
    
    public List<DailyYieldDto> calcBatchCurveYield(Date startDate,Date endDate,Map<String,Object> map,Date vdate,String type,BigDecimal weight){
    	
    	List<DailyYieldDto> resultList = new ArrayList<DailyYieldDto>();
    	
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("IntRateId", (String)map.get("benchmark"));
    	betaParam.put("IntRateType", (String)map.get("benchmarkType"));
    	if(startDate.compareTo(endDate)< 0){
    		betaParam.put("sectionFlag", "1");
    	}
    	List<DailyYieldDto> list = ptlPerformanceAnalysisMapper.queryCurveYieldByBenchmark(betaParam);
    	
    	converYieldList(list,null,null,BigDecimal.ONE);
    	
    	List<DailyYieldDto> calcList = new ArrayList<DailyYieldDto>();
    	
    	for(DailyYieldDto dto : list){
    		calcList.add(dto);
    		DailyYieldDto temp = new DailyYieldDto();
    		temp.setCdate(dto.getCdate());
    		if("total".equals(type)){
    			temp.setYield(calcSingleCurveTotalYield(calcList,BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),new BigDecimal((String)map.get("spreadRate")),dto.getCdate(),vdate,weight));
    		}else {
    			temp.setYield(calcSingleCurvePYield(calcList,BigDecimal.ONE,new BigDecimal((String)map.get("coefficient")),new BigDecimal((String)map.get("spreadRate")),dto.getCdate(),vdate,weight));
    		}
    		resultList.add(temp);
    	}
    	
    	return resultList;
    	
    }

    //计算累计年化收益率
    public BigDecimal calcSingleCurveTotalYield(List<DailyYieldDto> list,
    		BigDecimal weight,BigDecimal coefficient,
    		BigDecimal spreadRate,Date curDate,Date vdate){
    	
    	BigDecimal totalYield = BigDecimal.ONE;
    	//TODO 
    	for(DailyYieldDto dto : list){
    		BigDecimal temp = BigDecimal.ONE.add(
    				dto.getYield().multiply(coefficient).add(spreadRate).divide(new BigDecimal(365),16,BigDecimal.ROUND_HALF_UP));
    		totalYield = totalYield.multiply(temp);
    	}
    	BigDecimal denominator = new BigDecimal(DateUtil.getBetweenDayNumber(curDate, vdate)+1);
    	return (totalYield.subtract(BigDecimal.ONE)).multiply(weight).divide(denominator).divide(denominator,16,BigDecimal.ROUND_HALF_UP);
    	
    	
    }

    //计算累计年化收益率
    public BigDecimal calcSingleCurveTotalYield(List<DailyYieldDto> list,
    		BigDecimal weight,BigDecimal coefficient,
    		BigDecimal spreadRate,Date curDate,Date vdate,BigDecimal fhjzweight){
    	
    	return calcSingleCurveTotalYield(list,
    			weight,coefficient,spreadRate,curDate,vdate).multiply(fhjzweight);
    	
    	
    }
    //计算区间收益率
    public BigDecimal calcSingleCurvePYield(List<DailyYieldDto> list,
	BigDecimal weight,BigDecimal coefficient,
	BigDecimal spreadRate,Date curDate,Date vdate){
    	BigDecimal totalYield = BigDecimal.ONE;
    	//TODO 
    	for(DailyYieldDto dto : list){
    		BigDecimal temp = BigDecimal.ONE.add(
    				dto.getYield().multiply(coefficient).add(spreadRate).divide(new BigDecimal(365),16,BigDecimal.ROUND_HALF_UP));
    		totalYield = totalYield.multiply(temp);
    	}
    	return (totalYield.subtract(BigDecimal.ONE)).multiply(weight);
    }
    
    //计算区间收益率
    public BigDecimal calcSingleCurvePYield(List<DailyYieldDto> list,
    		BigDecimal weight,BigDecimal coefficient,
	BigDecimal spreadRate,Date curDate,Date vdate,BigDecimal fhjzweight){
    	return calcSingleCurvePYield(list,weight,coefficient,spreadRate,curDate,vdate).multiply(fhjzweight);
    }
    
    public List<DailyYieldDto> calcBatchFHJZYield(Date startDate,Date endDate,Map<String,Object> map,Date vdate,String type){
    	
    	
    	Map<String,Object> betaParam = new HashMap<String,Object>();
    	betaParam.put("startDate", startDate);
    	betaParam.put("endDate", endDate);  
    	betaParam.put("IntRateId", (String)map.get("benchmark"));
    	betaParam.put("IntRateType", (String)map.get("benchmarkType"));
    	
    	//查询基准成分表，获取权重信息
    	List<MstIndexComponent> indexlist = ptlPerformanceAnalysisMapper.queryIndexComponent((String)map.get("benchmark"));
    	 
    	List<DailyYieldDto> fhjzYieldDtoList = new ArrayList<DailyYieldDto>();
    	
    	List<DailyYieldDto> intYieldDtoList = new ArrayList<DailyYieldDto>();
    	
    	List<DailyYieldDto> curveYieldDtoList = new ArrayList<DailyYieldDto>();
    	
    	List<DailyYieldDto> quoYieldDtoList = new ArrayList<DailyYieldDto>();
    	
    	long length = DateUtil.getBetweenDayNumber(startDate, endDate);
    	
    	for(MstIndexComponent indexDto : indexlist){
    		if(DictCodeListExt1.基准类型_2_利率.getValue().equals(indexDto.getComponentType()) ){
    			intYieldDtoList = calcBatchIntYield(startDate,endDate,map,vdate,type,indexDto.getWeight());
    			
        	}else if(DictCodeListExt1.基准类型_2_收益率曲线.getValue().equals(indexDto.getComponentType())){
        		curveYieldDtoList = calcBatchCurveYield(startDate,endDate,map,vdate,type,indexDto.getWeight());
        		
        	}else if(DictCodeListExt1.基准类型_2_指数.getValue().equals(indexDto.getComponentType())){
        		quoYieldDtoList = calcBatchQuoYield(startDate,endDate,map,type,indexDto.getWeight());	
        	}
    	}
    	
    	for(int i = 0 ; i < length ; i++ ){
    		
    		DailyYieldDto dto = new DailyYieldDto();
    		BigDecimal yield = BigDecimal.ZERO;
    		
    		Date cdate = DateUtil.addDay(startDate, 0);
    		
    		if(intYieldDtoList.size()>0){
    			yield = yield.add(intYieldDtoList.get(i).getYield());
    		}
    		
    		if(intYieldDtoList.size()>0){
    			yield = yield.add(curveYieldDtoList.get(i).getYield());
    		}
    		
    		if(intYieldDtoList.size()>0){
    			yield = yield.add(quoYieldDtoList.get(i).getYield());
    		}  
    		
    		dto.setYield(yield);
    		dto.setCdate(cdate);
    		fhjzYieldDtoList.add(dto);
    		
    	}
    	
    	
    	return fhjzYieldDtoList;
    	
    }
    
    
    public static BigDecimal convertoBigDecimal(String value,BigDecimal defaultValue){
    	BigDecimal resultValue = BigDecimal.ZERO;
    	if(defaultValue != null){
    		resultValue = defaultValue;
    	}
    	if(StringUtil.isBlank(value)){
    		return resultValue;
    	}
    	return new BigDecimal(value);
    }
}
