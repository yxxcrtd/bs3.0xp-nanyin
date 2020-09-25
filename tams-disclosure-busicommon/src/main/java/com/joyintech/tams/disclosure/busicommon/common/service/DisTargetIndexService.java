package com.joyintech.tams.disclosure.busicommon.common.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbMktHoliday;
import com.joyintech.tams.disclosure.busicommon.common.calculation.AccumulatedValueRateCa;
import com.joyintech.tams.disclosure.busicommon.common.calculation.FloatValueGrowthRate;
import com.joyintech.tams.disclosure.busicommon.common.calculation.StageValueGrowthRate;
import com.joyintech.tams.disclosure.busicommon.common.calculation.StandardNetProductGrowthCa;
import com.joyintech.tams.disclosure.busicommon.common.calculation.StandardPerformanceCa;
import com.joyintech.tams.disclosure.busicommon.common.calculation.WaverageProfitCa;
import com.joyintech.tams.disclosure.busicommon.common.calculation.WeAverageIncomeCa;
import com.joyintech.tams.disclosure.busicommon.common.dto.FinProduct;
import com.joyintech.tams.disclosure.busicommon.common.dto.RepProdIndex;
import com.joyintech.tams.disclosure.busicommon.common.dto.RepTreadInfoDto;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;






/**
 * 用于指标的输出Service
 * 
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-01-02
 */
@Service
public class DisTargetIndexService {

	/**
	 * 货币性表二
	 * 
	 * @param 开始日期
	 *            startDate 结束日期 endDate 产品代码 prodId
	 * @throws Exception 
	 * @retun 指标表二
	 */
	public  Map<String,Object> printTargetCurrencyTypeTbTwo(Date startDate, Date endDate, String prodId,Connection conn) throws Exception {
		//定义返回值
		Map<String,Object> resultmap=new HashMap<>();
//		// 表二dto
//		ResultOneDto oneDto = new ResultOneDto();
		Date beforeDate = getBeforeDate(startDate);
		// 查询(开始日期-1)到结束日期的产品指标
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beforeDate", beforeDate);
		paramMap.put("endDate", endDate);
		paramMap.put("prodId", prodId);
		// 产品指标表集合查询
		//List<RepProdIndex> list = targetIndexMapper.slectTargetIndex(paramMap);
		//改用jdbc方式查询
		List<RepProdIndex> list=getListRepProdIndebyJjdbc(beforeDate, endDate, prodId,conn);
		// 标的类金融产品查询
		//FinProduct finProduct = targetIndexMapper.selectFinProduct(prodId);
        //改用jdbc方式查询
		FinProduct finProduct=getFinProductByJdbc(prodId,conn);
		// 查询产品交易日历
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("startDate", startDate);
		paramMap2.put("endDate", endDate);

		//List<MstHoliday> mstHolidaylist = targetIndexMapper.selectMstHoliday(paramMap2);
        //改用Jdbc方式查询
		List<ChbMktHoliday> chbMktHolidaylist=getListMstHolidaybyJdbc(paramMap2,conn);
		// 获得产品指标list中估值日=产品起息日的指标信息
		// 估值日=结束日期
		RepProdIndex repProd = new RepProdIndex();
		for (RepProdIndex in2 : list) {
			// 当估值日=结束日期时返回dto
			if (DateUtils.isSameDay(in2.getValueDate(), endDate)) {
				repProd = in2;
				continue;
			}
		}
		// 计算sum(报告期内产品指标List.本期利润)
		BigDecimal sum = BigDecimal.ZERO;
		for (RepProdIndex index : list) {
			if (index.getProfitCurrent() != null) {
				sum = sum.add(index.getProfitCurrent());
			}
		}

		// 日历节假日list.toMap()
		Map<Date, String> map = chbMktHolidaylist.stream()
				.collect(Collectors.toMap(ChbMktHoliday::getBusiDate, ChbMktHoliday::getIsHoliday, (old, ne) -> ne));
		// 报告期内基金份额MAP
		// 报告期内产品指标List.stream().collect(Collectors.toMap(产品指标表.get估值日(),
		// 产品指标表));
		Map<Date, RepProdIndex> mapRep = list.stream().collect(
				Collectors.toMap(RepProdIndex::getValueDate, Function.identity(), (entity1, entity2) -> entity1));

		// 调用共通方法：加权平均单位本期净收益计算(表二)
		if(mapRep!=null&&mapRep.size()!=0){
			BigDecimal wavlue = WeAverageIncomeCa.getWeAverageCurrentValue(sum, startDate, endDate, map, mapRep);
			//oneDto.setWavlue(wavlue);
			resultmap.put("wavlue", wavlue);
		}else{
			resultmap.put("wavlue", BigDecimal.ZERO);
		}
	
		// 累积净值收益率->跑批出来的(表二)
		BigDecimal profit = repProd.getNetvalueTotalYield();

		// 塞入表二dto
		//oneDto.setProfit(profit.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		if (profit != null) {
			resultmap.put("profit", profit.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		}
		else {
			resultmap.put("profit", BigDecimal.ZERO);
		}
		
		//货币型表二其它值
		setCTTableOther(startDate, endDate, prodId, resultmap,conn);
		resultmap.put("STARTDATE", startDate);
		resultmap.put("ENDDATE",endDate);
		return resultmap;
	}

	/**
	 * 净值型表二
	 * 
	 * @param 开始日期
	 *            startDate 结束日期 endDate 产品代码 prodId
	 * @throws Exception 
	 * @retun 指标表二
	 */
	public Map<String,Object> printTargetNetTypeTbTwo(Date startDate, Date endDate, String prodId,Connection  conn) throws Exception {
		//定义 
		Map<String,Object> resultmap=new HashMap<>();
		// 产品指标表集合查询
		//改用jdbc方式查询
		List<RepProdIndex> list=getListRepProdIndebyJjdbc(startDate, endDate, prodId,conn);
		// 标的类金融产品查询
		//FinProduct finProduct = targetIndexMapper.selectFinProduct(prodId);
		
		// 查询产品交易日历
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("startDate", startDate);
		paramMap2.put("endDate", endDate);
		//改用Jdbc方式查询
		List<ChbMktHoliday> chbMktHolidaylist=getListMstHolidaybyJdbc(paramMap2,conn);
		// 获得产品指标list中估值日=产品起息日的指标信息
		// 计算sum(报告期内产品指标List.本期利润)
		BigDecimal sum = BigDecimal.ZERO;
		for (RepProdIndex index : list) {
			if (index.getProfitCurrent() != null) {
				sum = sum.add(index.getProfitCurrent());
			}
		}

		// 日历节假日list.toMap()
		Map<Date, String> map = chbMktHolidaylist.stream()
				.collect(Collectors.toMap(ChbMktHoliday::getBusiDate, ChbMktHoliday::getIsHoliday, (old, ne) -> ne));
		// 报告期内基金份额MAP
		// 报告期内产品指标List.stream().collect(Collectors.toMap(产品指标表.get估值日(),
		// 产品指标表));
		Map<Date, RepProdIndex> mapRep = list.stream().collect(
				Collectors.toMap(RepProdIndex::getValueDate, Function.identity(), (entity1, entity2) -> entity1));

		// 调用共通 加权平均产品份额本期利润计算(表二)
		if(mapRep!=null&&mapRep.size()!=0){
			BigDecimal mavalue = WaverageProfitCa.getAveragePfofitCa(sum, startDate, endDate, map, mapRep);
	    //本期加权平均净值利润率
			BigDecimal wavlue = WeAverageIncomeCa.getWeAverageCurrentValue(sum, startDate, endDate, map, mapRep);
			resultmap.put("mavalue", mavalue);
			resultmap.put("wavlue", wavlue);
		}else{
			resultmap.put("mavalue", BigDecimal.ZERO);
		}
	
		// 累积净值增长率(即使结束日=估值日的产品指标的累积净值增长率)(表二)
		RepProdIndex rep = getShareValue(endDate, list);
		// 塞入表二dto
		//oneDto.setProductShareValue(rep.getNetvalueTotalIncre().multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		if(rep!=null){
			resultmap.put("productShareValue", (rep.getNetvalueTotalIncre()==null?BigDecimal.ZERO:rep.getNetvalueTotalIncre()).multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		}else{
			resultmap.put("productShareValue",BigDecimal.ZERO);
		}
		//本期产品份额净值增长率
		BigDecimal stageJzRate = StageValueGrowthRate.getStageValueGrowthRate(startDate, endDate, mapRep,prodId,conn);
		if(stageJzRate==null){
			resultmap.put("stageJzRate", BigDecimal.ZERO);
		}else{
			resultmap.put("stageJzRate", stageJzRate.setScale(4, BigDecimal.ROUND_HALF_UP));

		}
		
		//取结束日dto
	    RepProdIndex  currRepProdIndex=getShareValue(endDate, list);
	    if(currRepProdIndex!=null){
	    	//本期净值收益率
		    resultmap.put("currNetValue", currRepProdIndex.getNetvalueTotalYield()==null?BigDecimal.ZERO:currRepProdIndex.getNetvalueTotalYield());
		   //期末可供分配利润
		    resultmap.put("qmtdyAble", currRepProdIndex.getProfitTdyAble()==null?BigDecimal.ZERO:currRepProdIndex.getProfitTdyAble());
		    //期末可供分配产品份额利润
		    resultmap.put("qmprdtdyAble", currRepProdIndex.getProdProfitTdyAble()==null?BigDecimal.ZERO:currRepProdIndex.getProdProfitTdyAble());
            //产品份额累积净值增长率
		    resultmap.put("prdtdyIncueValue", currRepProdIndex.getNetvalueTotalIncre()==null?BigDecimal.ZERO:currRepProdIndex.getNetvalueTotalIncre());
	    }
		
	    
		//净值型表二其他值
		setNTTableOther(startDate, endDate, prodId, resultmap,conn);
		resultmap.put("FIRSTVALUE", startDate);
		resultmap.put("LASTVALUE", endDate);
		return resultmap;
	}

	/**
	 * 用于输出货币性表三
	 * 
	 * @param 开始日期
	 *            startDate 结束日期 endDate 产品代码 prodId
	 * @throws Exception 
	 * @retun 指标表三
	 */
	public  Map<String,Object> printTargetCurrencyTypeTbThree(Date startDate, Date endDate, String prodId,Connection conn) throws Exception {
		Map<String,Object> resultmap=new HashMap<>();
		// 表三dto
		//ResultTwoDto twoDto = new ResultTwoDto();
		//Date beforeDate = getBeforeDate(startDate);
		// 查询(开始日期-1)到结束日期的产品指标
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beforeDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("prodId", prodId);
		// 产品指标集合的查询
		//List<RepProdIndex> list = targetIndexMapper.slectTargetIndex(paramMap);
		//改用jdbc方式查询
		List<RepProdIndex> list=getListRepProdIndebyJjdbc(startDate, endDate, prodId,conn);
		// 份额净值收益率标准差(表三)
		List<BigDecimal> listTdy = new ArrayList<>();
		for (RepProdIndex in : list) {
			// 塞入当日收益率
			listTdy.add(in.getNetvalueTdyYield());
		}
		// 收益率标准差
		BigDecimal standard = StandardNetProductGrowthCa.getStandardNetProductGrowth(listTdy);
		//twoDto.setProfitStandard(standard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("standard", standard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		// 标的类金融产品查询
		//FinProduct finProduct = targetIndexMapper.selectFinProduct(prodId);
		 //改用jdbc方式查询
		FinProduct finProduct=getFinProductByJdbc(prodId,conn);
		// 获得产品指标list中估值日=产品起息日的指标信息
		// 估值日=产品起息日
		RepProdIndex repProdIndex = new RepProdIndex();
		Date data = null;
		if(finProduct!=null)
		{
			 data = finProduct.getVdate();
		}

		for (RepProdIndex in : list) {
			// 当产品起息日等于估值日时返回dto
			if (DateUtils.isSameDay(in.getValueDate(), data)) {
				repProdIndex = in;
				break;
			}
		}
		// 处理逻辑
		if (finProduct != null) {
			if (finProduct.getVdate().compareTo(startDate) >= 0) {
				// 定义期初
				RepProdIndex rep = new RepProdIndex();
				// 期初.实收资本 = 产品指标表.实收资本(估值日 = 产品起息日)
				rep.setCapital(repProdIndex.getCapital());
				// 期初.产品资产净值(按成本） = 产品指标表.实收资本(估值日 = 产品起息日)
				rep.setAssetValueCost(repProdIndex.getCapital());
				// 期初.产品资产净值(按市值） = 产品指标表.实收资本(估值日 = 产品起息日)
				rep.setAssetValueMarket(repProdIndex.getCapital());
				// 期初.其他值设值为0
				// 期初.估值日 = 标的类金融产品.起息日 - 1
				rep.setValueDate(getBeforeDate(finProduct.getVdate()));
				// 参数.开始日期 = 标的类金融产品.起息日
				startDate = finProduct.getVdate();
				list.add(rep);
			}
		}
		// 报告期内产品指标List.stream().collect(Collectors.toMap(产品指标表.get估值日(),
		// 产品指标表));
		Map<Date, RepProdIndex> mapRep = list.stream().collect(
				Collectors.toMap(RepProdIndex::getValueDate, Function.identity(), (entity1, entity2) -> entity1));
		// 份额净值收益率->(取阶段的)
		// 调用共通
		if(mapRep!=null&&mapRep.size()!=0){
    		BigDecimal currentValue = AccumulatedValueRateCa.getAccValueRateByDay(startDate, endDate, mapRep);
    		//twoDto.setCurrentValue(currentValue.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
    		resultmap.put("currentValue", currentValue.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
        }else{
        	resultmap.put("currentValue",BigDecimal.ZERO);
        }
//			// 业绩比较基准收益率(表三)->调用共通
//		Map<String, Object> claMap=PerformanceBenchmarkCa.getClaCompareRule(startDate, endDate, prodId, conn);
//		//获取阶段业绩比较基准
//		BigDecimal claCompareRule = (BigDecimal) claMap.get("claCompareRule");
//		//获取业绩比较基准list
//		List<BigDecimal> claCompareRuleList = (List<BigDecimal>) claMap.get("list");
//		// 业绩比较基准收益率塞入表三dto 
//		//twoDto.setFloatRate(claCompareRule.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
//		resultmap.put("claCompareRule", claCompareRule.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
//		// 业绩比较基准收益率标准差(表三)->调用标准差公式共通
//		BigDecimal floatRateStandard = StandardPerformanceCa.getStandardPerformance(claCompareRuleList);
//		// 业绩比较基准收益率标准差塞入表三dto
//		//twoDto.setFloatRateStandard(floatRateStandard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
//		resultmap.put("floatRateStandard", floatRateStandard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("claCompareRule",BigDecimal.ZERO);
		resultmap.put("floatRateStandard",BigDecimal.ZERO);
		return resultmap;
	}

	/**
	 * 用于输出净值型表三
	 * 
	 * @param 开始日期
	 *            startDate 结束日期 endDate 产品代码 prodId
	 * @throws Exception 
	 * @retun 指标表三
	 */
	public static Map<String,Object> printTargetNetTypeTbThree(Date startDate, Date endDate, String prodId,Connection conn) throws Exception {
		Map<String,Object> resultmap=new HashMap<>();
		// 表三dto
		//ResultTwoDto twoDto = new ResultTwoDto();
		//Date beforeDate = getBeforeDate(startDate);
		// 查询(开始日期-1)到结束日期的产品指标
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beforeDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("prodId", prodId);
		// 产品指标集合的查询
		//List<RepProdIndex> list = targetIndexMapper.slectTargetIndex(paramMap);
		//改用jdbc方式查询
		List<RepProdIndex> list=getListRepProdIndebyJjdbc(startDate, endDate, prodId,conn);
		// 份额净值增长率标准差(表三)
		List<BigDecimal> listGro = new ArrayList<>();
		for (RepProdIndex in : list) {
			// 塞入当日净值增长率
			listGro.add(in.getNetvalueTdyIncre()==null?BigDecimal.ZERO:in.getNetvalueTdyIncre());
		}
		// 份额净值增长率标准差
		BigDecimal productGrowth = StandardNetProductGrowthCa.getStandardNetProductGrowth(listGro);
		//twoDto.setProductGrowth(productGrowth.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("productGrowth", productGrowth.multiply( BigDecimal.valueOf(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		// 标的类金融产品查询
		//FinProduct finProduct = targetIndexMapper.selectFinProduct(prodId);
		 //改用jdbc方式查询
		FinProduct finProduct=getFinProductByJdbc(prodId,conn);
		Date date = null;
		if(finProduct!=null)
		{
			 date = finProduct.getVdate();
		}

		// 估值日=产品起息日
				RepProdIndex repProdIndex = new RepProdIndex();
				for (RepProdIndex in : list) {
					// 当产品起息日等于估值日时返回dto
					if (DateUtils.isSameDay(in.getValueDate(), date)) {
						repProdIndex = in;
						break;
					}
				}
		// 处理逻辑
		if (finProduct != null) {
			if (finProduct.getVdate().compareTo(startDate) >= 0) {
				// 定义期初
				RepProdIndex rep = new RepProdIndex();
				// 期初.实收资本 = 产品指标表.实收资本(估值日 = 产品起息日)
				rep.setCapital(repProdIndex.getCapital());
				// 期初.产品资产净值(按成本） = 产品指标表.实收资本(估值日 = 产品起息日)
				rep.setAssetValueCost(repProdIndex.getCapital());
				// 期初.产品资产净值(按市值） = 产品指标表.实收资本(估值日 = 产品起息日)
				rep.setAssetValueMarket(repProdIndex.getCapital());
				// 期初.其他值设值为0
				// 期初.估值日 = 标的类金融产品.起息日 - 1
				rep.setValueDate(getBeforeDate(finProduct.getVdate()));
				// 参数.开始日期 = 标的类金融产品.起息日
				startDate = finProduct.getVdate();
				list.add(rep);
			}
		}
//		// 业绩比较基准收益率(表三)->调用共通
//		Map<String, Object> claMap=PerformanceBenchmarkCa.getClaCompareRule(startDate, endDate, prodId, conn);
//		//获取阶段业绩比较基准
//		BigDecimal claCompareRule = (BigDecimal) claMap.get("claCompareRule");
//		//获取业绩比较基准list
//		List<BigDecimal> claCompareRuleList = (List<BigDecimal>) claMap.get("list");
//		// 业绩比较基准收益率塞入表三dto
//		//twoDto.setFloatRate(claCompareRule.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
//		resultmap.put("claCompareRule", claCompareRule.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
//		// 业绩比较基准收益率标准差(表三)->调用标准差公式共通
//		BigDecimal floatRateStandard = StandardPerformanceCa.getStandardPerformance(claCompareRuleList);
//		// 业绩比较基准收益率标准差塞入表三dto
//		//twoDto.setFloatRateStandard(floatRateStandard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
//		resultmap.put("floatRateStandard", floatRateStandard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		//阶段业绩比较基准去结束日的
		RepProdIndex repindex=getShareValue(endDate, list);

		if(repindex!=null)
		{
			resultmap.put("claCompareRule",repindex.getFloatBaseRate()==null?BigDecimal.ZERO:repindex.getFloatBaseRate());
		}
		//业绩比较基准list
		List<BigDecimal> listYj = new ArrayList<>();
		for(RepProdIndex  rpd:list){
			listYj.add(rpd.getFloatBaseRate()==null?BigDecimal.ZERO:rpd.getFloatBaseRate());
		}
		// 业绩比较基准收益率标准差->调用标准差公式共通
		BigDecimal floatRateStandard = StandardPerformanceCa.getStandardPerformance(listYj);
		resultmap.put("floatRateStandard",floatRateStandard);
		// 表三:阶段净值增长率->调用共通（开始日期，结束日期，基金份额Map）
		// 基金份额Map-> 由报告期内产品指标List转换
		Map<Date, RepProdIndex> mapRep = list.stream().collect(
				Collectors.toMap(RepProdIndex::getValueDate, Function.identity(), (entity1, entity2) -> entity1));
		// 调用共通
		if(mapRep!=null&&mapRep.size()!=0){
			BigDecimal stageRate = StageValueGrowthRate.getStageValueGrowthRate(startDate, endDate, mapRep,prodId,conn);
			// 塞入表三dto
			//twoDto.setStageRate(stageRate.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
			resultmap.put("stageRate", stageRate.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));

		}else{
			resultmap.put("stageRate",BigDecimal.ZERO);
		}
			return resultmap;
	}
	
	/**
	 * 过去五年产品每年净值增长率及其与同期业绩比较基准收益率的比较（图展示）
	 * @param startDate
	 * @param endDate
	 * @param prodId
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public  static Map<String,Object> printTableCpValueConfig(Date startDate, Date endDate, String prodId,Connection conn) throws Exception {
		Map<String,Object> resultmap=new HashMap<>();
		// 产品指标集合的查询
		List<RepProdIndex> list=getListRepProdIndebyJjdbc(startDate, endDate, prodId,conn);
        //根据开始日结束日算阶段的净值的增长率
		Map<Date, RepProdIndex> mapRep = list.stream().collect(
				Collectors.toMap(RepProdIndex::getValueDate, Function.identity(), (entity1, entity2) -> entity1));
		if(mapRep!=null&&mapRep.size()!=0){
			// 阶段净值增长率
			BigDecimal stageRate = StageValueGrowthRate.getStageValueGrowthRate(startDate, endDate, mapRep,prodId,conn);
			resultmap.put("netvalueyleld", stageRate.setScale(4, BigDecimal.ROUND_HALF_UP));
		}else{
			resultmap.put("netvalueyleld",BigDecimal.ZERO);
		}
		//阶段业绩比较基准
		//调用阶段业绩比较基准
        BigDecimal claCompareRule=FloatValueGrowthRate.getStageValueGrowthRate(startDate, endDate, mapRep, prodId, conn);
        resultmap.put("floatrate", claCompareRule.setScale(4, BigDecimal.ROUND_HALF_UP));
	
		return resultmap;
		
	}
	/**
	 * 获得前一天日期
	 * 
	 * @param nowDate
	 * @return
	 */
	public static Date getBeforeDate(Date nowDate) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowDate);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		// 获取开始日的前一日
		Date beforeDate = ca.getTime();
		return beforeDate;
	}

	/**
	 * 获取累积净值增长率、累积净值收益率
	 * 
	 * @param Date
	 *            cDate参数
	 * @return RepProdIndex
	 */
	public static RepProdIndex getShareValue(Date cDate, List<RepProdIndex> list) {
		RepProdIndex result = new RepProdIndex();
		if(list!=null&&list.size()!=0){
			// 获取参数日期=估值日的dto
			for (RepProdIndex in : list) {
				// 获取参数日等于估值的dto
				if (DateUtils.isSameDay(in.getValueDate(), cDate)) {
					result = in;
					break;
				}
			}
		}else{
			return null;
		}
		return result;
	}

//	/**
//	 * 产品份额净值收益率，产品份额累积净值收益率计算
//	 * 
//	 * @param startDate
//	 *            endDate pordId
//	 * @return BigDecimal
//	 */
//	public BigDecimal getNetvalue(Date startDate, Date endDate, String pordId) {
//		Date beforeDate = getBeforeDate(startDate);
//		// 查询(开始日期-1)到结束日期的产品指标
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("beforeDate", beforeDate);
//		paramMap.put("endDate", endDate);
//		paramMap.put("prodId", pordId);
//		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM");
//		String sDate = sim.format(startDate);
//		String eDate = sim.format(endDate);
//		// 获取年月区间的万份收益
//		List<NetvaluerResultDto> listdto = targetIndexMapper.getNetvalue(paramMap);
//		Map<String, BigDecimal> map = listdto.stream()
//				.collect(Collectors.toMap(NetvaluerResultDto::getyDate, NetvaluerResultDto::getSwYield));
//		// 调用共通,计算累积净值收益率
//		BigDecimal valueRate = AccumulatedValueRateCa.getAccValueRate(sDate, eDate, map);
//
//		return valueRate;
//	}

	/**
	 * 使用jdbc查询产品指标集合的查询
	 * @param 
	 * @throws Exception 
	 */
	public static  List<RepProdIndex>  getListRepProdIndebyJjdbc(Date beforeDate,Date endDate,String prodId,Connection  conn) {
		//定义返回结果
		List<RepProdIndex>  list=new ArrayList<>();
		
			//编写sql
			String   sql=" select PROD_ID,VALUE_DATE,CAPITAL,NETVALUE_TDY_YIELD, PROFIT_CURRENT,ASSET_VALUE_COST,NETVALUE_TOTAL_YIELD,NETVALUE_TOTAL_INCRE,W_YIELD,NETVALUE_TDY_INCRE,PROFIT_TDY_ABLE,PROD_PROFIT_TDY_ABLE,FLOAT_BASE_RATE from REP_PROD_INDEX   where VALUE_DATE   >= ? and VALUE_DATE    <=   ?  and PROD_ID=?";
			  //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1, new java.sql.Date(beforeDate.getTime()));
			ps.setDate(2, new java.sql.Date(endDate.getTime()));
			ps.setString(3, prodId);
			//执行语句
			rs = ps.executeQuery();
			//获取结果集
			while(rs.next()){
				String pROD_ID=rs.getString("PROD_ID");
				Date vALUE_DATE=rs.getDate("VALUE_DATE");
				BigDecimal cAPITAL=null2Decimal(rs.getBigDecimal("CAPITAL"));
				BigDecimal nETVALUE_TDY_YIELD=null2Decimal(rs.getBigDecimal("NETVALUE_TDY_YIELD"));
				BigDecimal pROFIT_CURRENT=null2Decimal(rs.getBigDecimal("PROFIT_CURRENT"));
				BigDecimal aSSET_VALUE_COST=null2Decimal(rs.getBigDecimal("ASSET_VALUE_COST"));
				BigDecimal  nETVALUE_TOTAL_YIELD=null2Decimal(rs.getBigDecimal("NETVALUE_TOTAL_YIELD"));
				BigDecimal nETVALUE_TOTAL_INCRE=null2Decimal(rs.getBigDecimal("NETVALUE_TOTAL_INCRE"));
				BigDecimal  w_YIELD=null2Decimal(rs.getBigDecimal("W_YIELD"));
				BigDecimal nETVALUE_TDY_INCRE=null2Decimal(rs.getBigDecimal("NETVALUE_TDY_INCRE"));
				BigDecimal pROFIT_TDY_ABLE=null2Decimal(rs.getBigDecimal("PROFIT_TDY_ABLE"));
				BigDecimal pROD_PROFIT_TDY_ABLE=null2Decimal(rs.getBigDecimal("PROD_PROFIT_TDY_ABLE"));
				BigDecimal fLOAT_BASE_RATE=null2Decimal(rs.getBigDecimal("FLOAT_BASE_RATE"));

				RepProdIndex index=new RepProdIndex();
				index.setProdId(pROD_ID);
				index.setValueDate(vALUE_DATE);
				index.setCapital(cAPITAL);
				index.setNetvalueTdyYield(nETVALUE_TDY_YIELD);
				index.setNetvalueTdyIncre(nETVALUE_TDY_INCRE);
				index.setNetvalueTotalIncre(nETVALUE_TOTAL_INCRE);
				index.setNetvalueTotalYield(nETVALUE_TOTAL_YIELD);
				index.setProfitCurrent(pROFIT_CURRENT);
				index.setAssetValueCost(aSSET_VALUE_COST);
				index.setwYield(w_YIELD);
				index.setProfitTdyAble(pROFIT_TDY_ABLE);
				index.setProdProfitTdyAble(pROD_PROFIT_TDY_ABLE);
				index.setFloatBaseRate(fLOAT_BASE_RATE);
				list.add(index);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	
		return list;
	}
	
	/**
	 * 使用jdbc查询标的类金融产品查询
	 * @param String prodId
	 * @throws Exception 
	 */
	public  static FinProduct getFinProductByJdbc(String prodId,Connection conn) {
		//定义返回值
		FinProduct finProduct=new FinProduct();
			//编写sql
			String sql="select PROD_ID,VDATE  from REP_PROD_INFO 	WHERE PROD_ID=? ";
			  //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setString(1, prodId);
			//执行语句
			rs = ps.executeQuery();
			//获取结果集
			if(rs.next()){
				String fINPROD_ID=rs.getString("PROD_ID");
				Date vDATE=rs.getDate("VDATE");
				finProduct.setFinprodId(fINPROD_ID);
				finProduct.setVdate(vDATE);
			}else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return finProduct;
		
	}
	/**
	 * 查询产品交易日历
	 * @param Map<String, Object> paramMap2
	 * @throws Exception 
	 */
	
	public  List<ChbMktHoliday> getListMstHolidaybyJdbc( Map<String, Object> paramMap,Connection conn) {
		//定义返回值
		List<ChbMktHoliday> list=new ArrayList<>();
		
			//编写sql
			String sql=" select BUSI_DATE,IS_HOLIDAY from chb_mkt_holiday  where BUSI_DATE  >=  ?	and BUSI_DATE   <= ? ";
			 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(((Date)paramMap.get("startDate")).getTime()));
			ps.setDate(2,new java.sql.Date(((Date)paramMap.get("endDate")).getTime()));
			//执行语句
			rs = ps.executeQuery();
			while(rs.next()){
				Date cDATE=rs.getDate("BUSI_DATE");
				String  iS_HOLIDAY=rs.getString("IS_HOLIDAY");
				ChbMktHoliday chbMktHoliday=new ChbMktHoliday();
				chbMktHoliday.setBusiDate(cDATE);
				chbMktHoliday.setIsHoliday(iS_HOLIDAY);
				list.add(chbMktHoliday);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
	/**
	 * 货币型表二其它值
	 * @param
	 * @throws Exception 
	 */
	public  void  setCTTableOther(Date startDate,Date endDate,String prodId,Map<String,Object> map,Connection conn) {
			//编写sql
			String sql="SELECT SUM(NVL(bqysx,0))  BQYSX ,SUM(NVL(BQLR,0)) BQLR ,SUM(NVL(SSZB,0)) SSZB ,round(SUM(NVL(JZSY,0)),4)  JZSY,1 FEJZ ,PROD_ID FROM (select PROFIT_CURRENT_YES  bqysx, PROFIT_CURRENT  BQLR, case when VALUE_DATE=? then CAPITAL  end SSZB,case when VALUE_DATE=? then NETVALUE_TOTAL_YIELD end JZSY,prod_id from rep_prod_index WHERE PROD_ID=?  and  VALUE_DATE<=? and VALUE_DATE>=?) GROUP BY PROD_ID";
			 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1, new java.sql.Date(endDate.getTime()));
			ps.setDate(2, new java.sql.Date(endDate.getTime()));
			ps.setString(3, prodId);
			ps.setDate(4, new java.sql.Date(endDate.getTime()));
			ps.setDate(5, new java.sql.Date(startDate.getTime()));
			//执行语句
			rs = ps.executeQuery();
			if(rs.next()){
				map.put("BQYSX", rs.getBigDecimal("BQYSX"));
				map.put("BQLR", rs.getBigDecimal("BQLR"));
				map.put("SSZB", rs.getBigDecimal("SSZB"));
				map.put("JZSY", rs.getBigDecimal("JZSY"));
				map.put("FEJZ", rs.getBigDecimal("FEJZ"));
				map.put("PROD_ID", rs.getString("PROD_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 净值型表二其他值
	 * @param
	 * @throws Exception 
	 */
	public void setNTTableOther(Date startDate,Date endDate,String prodId,Map<String,Object> map,Connection conn) {
		
			//编写sql
			String sql="SELECT sum(a.PROFITCURRENTYES) PROFITCURRENTYES,sum(a.PROFITCURRENT) PROFITCURRENT,sum(b.ASSETVALUEMARKET) ASSETVALUEMARKET,sum(b.UNITVALUE) UNITVALUE, sum(b.NETVALUETOTAL) NETVALUETOTAL FROM (SELECT SUM(RPI.PROFIT_CURRENT_YES) PROFITCURRENTYES,SUM(RPI.PROFIT_CURRENT) PROFITCURRENT FROM REP_PROD_INDEX RPI where RPI.VALUE_DATE BETWEEN ? AND ? and RPI.PROD_ID = ? ) a,(select a.ASSET_VALUE_MARKET   ASSETVALUEMARKET, a.UNIT_VALUE   UNITVALUE,a.NETVALUE_TOTAL_INCRE NETVALUETOTAL from REP_PROD_INDEX a where a.VALUE_DATE = ? and a.PROD_ID =?)b";
			 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1, new java.sql.Date(startDate.getTime()));
			ps.setDate(2, new java.sql.Date(endDate.getTime()));
			ps.setString(3, prodId);
			ps.setDate(4, new java.sql.Date(endDate.getTime()));
			ps.setString(5, prodId);
			//执行语句
			rs = ps.executeQuery();
			if(rs.next()){
				map.put("PROFITCURRENTYES", rs.getBigDecimal("PROFITCURRENTYES"));
				map.put("PROFITCURRENT", rs.getBigDecimal("PROFITCURRENT"));
				map.put("ASSETVALUEMARKET", rs.getBigDecimal("ASSETVALUEMARKET"));
				map.put("UNITVALUE", rs.getBigDecimal("UNITVALUE"));
				map.put("NETVALUETOTAL", rs.getBigDecimal("NETVALUETOTAL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 期末金融资产和金融负债的到期期限分析
	 * 
	 * @param startDate
	 * @param endDate
	 * @param prodId
	 * @param conn
	 * @return
	 */
	public Map<String, Object> printQMJRZCHJRFZDQQXFX(Date startDate, Date endDate, String prodId, Connection conn) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//本期期末：查询.结束日期
		Date bqqm = endDate;
		//本期期初：查询.开始日期-1
		Date bqqc = DateUtil.addDay(startDate, -1);
				
		//期限-本期期末
		Map<String,Integer> termCon_bqqm = getTermConditionByBaseDate(bqqm);
		//期限-本期期初
		Map<String,Integer> termCon_bqqc = getTermConditionByBaseDate(bqqc);
		
		//本期期末数据统计
		List<Map<String,Object>> valueDataMapList_bqqm = selectValueDataByValueDate(conn,bqqm,prodId);
		setBusinessDataMap(resultMap, valueDataMapList_bqqm, termCon_bqqm, "bqqm_");
		//获取负债标识
		
		
		//本期期初数据统计
		List<Map<String,Object>> valueDataMapList_bqqc = selectValueDataByValueDate(conn,bqqc,prodId);
		setBusinessDataMap(resultMap, valueDataMapList_bqqc, termCon_bqqc, "bqqc_");
		
		return resultMap;
	}

	/**
	 * 设置统计数据（根据不同类型资产和期限统计估值）
	 * 
	 * @param resultMap 返回数据集
	 * @param valueDataMapList  估值日全量资产估值数据
	 * @param termCon 期限条件
	 * @param bqType 本期类型字符串（期初bqqc_、期末bqqm_，用作拼写map的key）
	 */
	private void setBusinessDataMap(Map<String, Object> resultMap, List<Map<String, Object>> valueDataMapList,
			Map<String, Integer> termCon, String bqType) {
		if(null == valueDataMapList || valueDataMapList.size() == 0){
			return;
		}
		// 资产类型统计条件  start
				// 资产一级分类对应REP_ASSETINFO.ASSET_TYPE_DIS,数据字典="ASSET_TYPE_DIS"
				//货币市场工具_资产一级分类
				List<String> hbscgj_asset_type_one_list = Arrays.asList();
				//货币市场工具_资产二级分类
				List<String> hbscgj_asset_type_two_list = Arrays.asList("DIS0401","DIS0402");
				//货币市场工具_资产三级分类
				List<String> hbscgj_asset_type_three_list = Arrays.asList();
				//拆出资产_资产一级分类
				List<String> cczc_asset_type_one_list = Arrays.asList();
				//拆出资产_资产二级分类
				List<String> cczc_asset_type_two_list = Arrays.asList("DIS0102");
				//拆出资产_资产三级分类
				List<String> cczc_asset_type_three_list = Arrays.asList();
				//债券资产_资产一级分类
				List<String> zqzc_asset_type_one_list = Arrays.asList();
				//债券资产_资产二级分类
				List<String> zqzc_asset_type_two_list = Arrays.asList("DIS1001","DIS1002","DIS1003","DIS1004",
		        "DIS1005","DIS1006","DIS1007","DIS1008","DIS1009","DIS1010","DIS1011","DIS1012","DIS1014","DIS1015","DIS1016","DIS1017");
				//债券资产_资产三级分类
				List<String> zqzc_asset_type_three_list = Arrays.asList();
				//资产支持证券_资产一级分类
				List<String> zczczq_asset_type_one_list = Arrays.asList();
				//资产支持证券_资产二级分类
				List<String> zczczq_asset_type_two_list = Arrays.asList("DIS1013");
				//资产支持证券_资产三级分类
				List<String> zczczq_asset_type_three_list = Arrays.asList();
				//股票投资_资产一级分类(2019.7.15改权益类投资)
				List<String> gptz_asset_type_one_list = Arrays.asList();
				//股票投资_资产二级分类(2019.7.15改权益类投资)
				List<String> gptz_asset_type_two_list = Arrays.asList("DIS1101","DIS1102","DIS1103");
				//股票投资_资产三级分类(2019.7.15改权益类投资)
				List<String> gptz_asset_type_three_list = Arrays.asList();
				//基金投资_资产一级分类
				List<String> jjtz_asset_type_one_list = Arrays.asList();
				//基金投资_资产二级分类
				List<String> jjtz_asset_type_two_list = Arrays.asList("DIS0501","DIS0502");
				//基金投资_资产三级分类
				List<String> jjtz_asset_type_three_list = Arrays.asList();
				//金融衍生品投资_资产一级分类
				List<String> jrysptz_asset_type_one_list = Arrays.asList();
				//金融衍生品投资_资产二级分类
				List<String> jrysptz_asset_type_two_list = Arrays.asList("DIS1201","DIS1202","DIS1203","DIS1204","DIS1205",
						"DIS1206","DIS1207","DIS1208","DIS1209","DIS1210");
				//金融衍生品投资_资产三级分类
				List<String> jrysptz_asset_type_three_list = Arrays.asList();
				//买入返售金融资产_资产一级分类
				List<String> mrfsjrzc_asset_type_one_list = Arrays.asList();
				//买入返售金融资产_资产二级分类
				List<String> mrfsjrzc_asset_type_two_list = Arrays.asList("DIS0202","DIS0302");
				//买入返售金融资产_资产三级分类
				List<String> mrfsjrzc_asset_type_three_list = Arrays.asList();
				//计划类资产_资产一级分类
				List<String> jhlzc_asset_type_one_list = Arrays.asList();
				//计划类资产_资产二级分类
				List<String> jhlzc_asset_type_two_list = Arrays.asList("DIS0601","DIS0602","DIS0603","DIS0604","DIS0605","DIS0606",
						"DIS0701","DIS0702","DIS0703","DIS0801","DIS0802","DIS0803","DIS0804","DIS0805","DIS0806",
						"DIS0901","DIS0902","DIS0903","DIS0904","DIS0905","DIS0906","DIS0907","DIS0908","DIS0909","DIS0910","DIS0911","DIS0912");
				//计划类资产_资产三级分类
				List<String> jhlzc_asset_type_three_list = Arrays.asList();
				//拆入资金_资产一级分类
				List<String> crzj_asset_type_one_list = Arrays.asList();
				//拆入资金_资产二级分类
				List<String> crzj_asset_type_two_list = Arrays.asList("DIS0101");
				//拆入资金_资产三级分类
				List<String> crzj_asset_type_three_list = Arrays.asList();
				//买入返售金融负债_资产一级分类
				List<String> mrfsjrfz_asset_type_one_list = Arrays.asList();
				//买入返售金融负债_资产二级分类
				List<String> mrfsjrfz_asset_type_two_list = Arrays.asList("DIS0201","DIS0301");
				//买入返售金融负债_资产三级分类
				List<String> mrfsjrfz_asset_type_three_list = Arrays.asList();
				//金融衍生品负债_资产一级分类
				List<String> jryspfz_asset_type_one_list = Arrays.asList();
				//金融衍生品负债_资产二级分类
				List<String> jryspfz_asset_type_two_list = Arrays.asList("DIS1201","DIS1202","DIS1203","DIS1204","DIS1205",
						"DIS1206","DIS1207","DIS1208","DIS1209","DIS1210");
				//金融衍生品负债_资产三级分类
				List<String> jryspfz_asset_type_three_list = Arrays.asList();
				// 资产类型统计条件  end
				
				//货币市场工具
				setAssetTermValue(resultMap,hbscgj_asset_type_one_list,hbscgj_asset_type_two_list,hbscgj_asset_type_three_list,valueDataMapList,termCon,bqType,"hbscgj_");
				//拆出资产
				setAssetTermValue(resultMap,cczc_asset_type_one_list,cczc_asset_type_two_list,cczc_asset_type_three_list,valueDataMapList, termCon,bqType,"cczc_");
				//债券资产
				setAssetTermValue(resultMap,zqzc_asset_type_one_list,zqzc_asset_type_two_list,zqzc_asset_type_three_list,valueDataMapList, termCon,bqType,"zqzc_");
				//资产支持证券
				setAssetTermValue(resultMap,zczczq_asset_type_one_list,zczczq_asset_type_two_list,zczczq_asset_type_three_list,valueDataMapList, termCon,bqType,"zczczq_");
				//股票投资
				setAssetTermValue(resultMap,gptz_asset_type_one_list,gptz_asset_type_two_list,gptz_asset_type_three_list,valueDataMapList, termCon,bqType,"gptz_");
				//基金投资
				setAssetTermValue(resultMap,jjtz_asset_type_one_list,jjtz_asset_type_two_list,jjtz_asset_type_three_list,valueDataMapList, termCon,bqType,"jjtz_");
				//金融衍生品投资
				setAssetTermValue(resultMap,jrysptz_asset_type_one_list,jrysptz_asset_type_two_list,jrysptz_asset_type_three_list,valueDataMapList, termCon,bqType,"jrysptz_");
				//买入返售金融资产
				setAssetTermValue(resultMap,mrfsjrzc_asset_type_one_list,mrfsjrzc_asset_type_two_list,mrfsjrzc_asset_type_three_list,valueDataMapList, termCon,bqType,"mrfsjrzc_");
				//计划类资产
				setAssetTermValue(resultMap,jhlzc_asset_type_one_list,jhlzc_asset_type_two_list,jhlzc_asset_type_three_list,valueDataMapList, termCon,bqType,"jhlzc_");
				//拆入资金
				setAssetTermValue(resultMap,crzj_asset_type_one_list,crzj_asset_type_two_list,crzj_asset_type_three_list,valueDataMapList, termCon,bqType,"crzj_");
				//买入返售金融负债
				setAssetTermValue(resultMap,mrfsjrfz_asset_type_one_list,mrfsjrfz_asset_type_two_list,mrfsjrfz_asset_type_three_list,valueDataMapList, termCon,bqType,"mrfsjrfz_");
				//金融衍生品负债
				setAssetTermValue(resultMap,jryspfz_asset_type_one_list,jryspfz_asset_type_two_list,jryspfz_asset_type_three_list,valueDataMapList, termCon,bqType,"jryspfz_");
			
				// 流动性净额统计
				// 1个月以内
				setLdxjeByKey(resultMap, bqType, "term_0m_1m");
				// 1-3个月
				setLdxjeByKey(resultMap, bqType, "term_1m_3m");
				// 3个月-1年
				setLdxjeByKey(resultMap, bqType, "term_3m_1y");
				// 1-5年
				setLdxjeByKey(resultMap, bqType, "term_1y_5y");
				// 5年以上
				setLdxjeByKey(resultMap, bqType,  "term_5y_ny");
				// 合计
				setLdxjeByKey(resultMap, bqType,  "term_hj");
	}

	/**
	 * 计算流动性净额
	 * @param resultMap 返回数据集
	 * @param bqType 本期类型字符串
	 * @param termKey 期限key
	 */
	private void setLdxjeByKey(Map<String, Object> resultMap, String bqType, String termKey) {
		if(null == resultMap.get(bqType + "zczj_" + termKey) && null == resultMap.get(bqType + "fzzj_" + termKey)){
			return;
		}
		BigDecimal zczj = BigDecimal.ZERO;
		if (null != resultMap.get(bqType + "zczj_" + termKey)) {
			zczj = (BigDecimal) resultMap.get(bqType + "zczj_" + termKey);
		}
		BigDecimal fzzj = BigDecimal.ZERO;
		if (null != resultMap.get(bqType + "fzzj_" + termKey)) {
			fzzj = (BigDecimal) resultMap.get(bqType + "fzzj_" + termKey);
		}
		
		// 流动性净额 = 资产总计 - 负债总计
		resultMap.put(bqType + "ldxje_" + termKey, (zczj).subtract(fzzj));
	}

	/**
	 * 设定资产在期限内的估值数据
	 * @param resultMap 返回到报表的数据集(key规则:本期类型_资产\负债缩写_期限，例如本期期末货币市场工具1-3个月写为"bqqm_hbscgj_term_1m_3m")
	 * @param asset_type_one_list
	 * @param asset_type_two_list
	 * @param asset_type_three_list
	 * @param dataMapList 估值日全量资产估值数据
	 * @param termCon 期限条件
	 * @param bqType 本期类型字符串（期初bqqc_、期末bqqm_，用作拼写map的key）
	 * @param assetType 资产\负债缩写，用作拼写map的key
	 */
	private void setAssetTermValue(Map<String, Object> resultMap, 
									List<String> asset_type_one_list,
									List<String> asset_type_two_list, 
									List<String> asset_type_three_list, 
									List<Map<String, Object>> dataMapList,
									Map<String, Integer> termCon,
									String bqType,
									String assetType) {
		if(null != dataMapList && dataMapList.size() > 0 ){
			String al_flag = null;//资产负债标识
			String asset_type_dis = null;//资产种类
			BigDecimal tdy_cost_amt = null;//资产种类估值
			int end_days_2 = 0;//剩余期限
			String zcgzkey = "";// 资产估值key
			String zczjkey = "";// 资产总计key
			String zczjhjkey = "";// 资产总计合计key
			String fzzjkey = "";// 负债总计key
			String fzzjhjkey = "";// 负债总计合计key
			String key = "";// 期限key
			String hjkey = "";// 合计key
			for (Map<String, Object> tmpMap : dataMapList) {
				tdy_cost_amt = tmpMap.get("TDY_COST_AMT") == null ? BigDecimal.ZERO : 
					((BigDecimal) tmpMap.get("TDY_COST_AMT")).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);//转化为万元，保留两位小数
				end_days_2 = tmpMap.get("END_DAYS_2") == null ? 0 : (int) tmpMap.get("END_DAYS_2");
				al_flag = tmpMap.get("AL_FLAG") == null ? null : tmpMap.get("AL_FLAG").toString();
				asset_type_dis = tmpMap.get("ASSET_TYPE_DIS") == null ? null : tmpMap.get("ASSET_TYPE_DIS").toString();
				//判断资产类型是否满足，满足则根据期限判断，统计到对应的项目下。其中统计合计及总计
				if (!StringUtil.isEmptyList(asset_type_two_list) && asset_type_two_list.contains(asset_type_dis)) {//资产一级分类(已经转换为信批资产类型，数据字典=ASSET_TYPE_DIS)					// 1个月以内
					if (end_days_2 < termCon.get("term_0m_1m_max")) {
						key = "term_0m_1m";
					}
					// 1-3个月
					else if (end_days_2 > termCon.get("term_1m_3m_min") && end_days_2 < termCon.get("term_1m_3m_max")) {
						key = "term_1m_3m";
					}
					// 3个月-1年
					else if (end_days_2 > termCon.get("term_3m_1y_min") && end_days_2 < termCon.get("term_3m_1y_max")) {
						key = "term_3m_1y";
					}
					// 1-5年
					else if (end_days_2 > termCon.get("term_1y_5y_min") && end_days_2 < termCon.get("term_1y_5y_max")) {
						key = "term_1y_5y";
					}
					// 5年以上
					else if (end_days_2 > termCon.get("term_5y_ny_min")) {
						key = "term_5y_ny";
					}
					// 资产估值
					zcgzkey = bqType + assetType + key;
					//金融衍生品资产时当资产标识为负债时
					if(al_flag!=null)
					{
						if(al_flag!=null && al_flag.equals("02")&&(assetType).equals("jrysptz_")||
								al_flag.equals("01")&&(assetType).equals("jryspfz_")){
							tdy_cost_amt=BigDecimal.ZERO;
						}
					}

						addMapValueBykey(resultMap, tdy_cost_amt, zcgzkey);
					// 负债总计
					if (StringUtil.equals("02", al_flag)) {// 02负债  01资产
						fzzjkey = bqType + "fzzj_" + key;
						addMapValueBykey(resultMap, tdy_cost_amt, fzzjkey);

						// 负债总计 - 合计
						fzzjhjkey = bqType + "fzzj_" + "term_hj";// bqqm_fzzj_term_hj
						addMapValueBykey(resultMap, tdy_cost_amt, fzzjhjkey);
					}
					// 资产总计
					else {
						zczjkey = bqType + "zczj_" + key;
						addMapValueBykey(resultMap, tdy_cost_amt, zczjkey);

						// 资产总计 - 合计
						zczjhjkey = bqType + "zczj_" + "term_hj";// bqqm_zczj_term_hj
						addMapValueBykey(resultMap, tdy_cost_amt, zczjhjkey);
					}
					// 合计
					hjkey = bqType + assetType + "term_hj";
					addMapValueBykey(resultMap, tdy_cost_amt, hjkey);
				}
			}
		}
	}

	/**
	 * 根据key，给map中的value增加数据
	 * @param resultMap
	 * @param addValue 需要增加的数据
	 * @param mapKey
	 */
	private void addMapValueBykey(Map<String, Object> resultMap, BigDecimal addValue, String mapKey) {
		if (addValue == null) {
			addValue = BigDecimal.ZERO;
		}
		BigDecimal baseValue = resultMap.get(mapKey) == null ? BigDecimal.ZERO : (BigDecimal) resultMap.get(mapKey);
		resultMap.put(mapKey, addValue.add(baseValue));
	}

	/**
	 * 获取期限统计条件
	 * @param baseDate
	 * @return
	 */
	private Map<String, Integer> getTermConditionByBaseDate(Date baseDate) {
		Map<String, Integer> termCon = new HashMap<String, Integer>();
		// 1个月以内
		Integer term_0m_1m_max = DateUtil.getBetweenDayNumber(baseDate, DateUtil.addMonths(baseDate, 1));
		termCon.put("term_0m_1m_max", term_0m_1m_max);
		// 1-3个月
		Integer term_1m_3m_min = DateUtil.getBetweenDayNumber(baseDate, DateUtil.addMonths(baseDate, 1));
		Integer term_1m_3m_max = DateUtil.getBetweenDayNumber(baseDate, DateUtil.addMonths(baseDate, 3));
		termCon.put("term_1m_3m_min", term_1m_3m_min);
		termCon.put("term_1m_3m_max", term_1m_3m_max);
		// 3个月-1年
		Integer term_3m_1y_min = DateUtil.getBetweenDayNumber(baseDate, DateUtil.addMonths(baseDate, 3));
		Integer term_3m_1y_max = DateUtil.getBetweenDayNumber(baseDate, DateUtil.addYear(baseDate, 1));
		termCon.put("term_3m_1y_min", term_3m_1y_min);
		termCon.put("term_3m_1y_max", term_3m_1y_max);
		// 1-5年
		Integer term_1y_5y_min = DateUtil.getBetweenDayNumber(baseDate, DateUtil.addYear(baseDate, 1));
		Integer term_1y_5y_max = DateUtil.getBetweenDayNumber(baseDate, DateUtil.addYear(baseDate, 5));
		termCon.put("term_1y_5y_min", term_1y_5y_min);
		termCon.put("term_1y_5y_max", term_1y_5y_max);
		// 5年以上
		Integer term_5y_ny_min = DateUtil.getBetweenDayNumber(baseDate, DateUtil.addYear(baseDate, 5));
		termCon.put("term_5y_ny_min", term_5y_ny_min);
		return termCon;
	}
	
	/**
	 * 查询估值日全量资产估值数据（获取估值、资产类型、剩余存续期限）
	 * @param conn
	 * @param valueDate 估值日
	 * @param prodId 产品代码
	 * @return List<Map<String,Object>>
	 * @throws SQLException
	 */
	public  List<Map<String,Object>> selectValueDataByValueDate(Connection conn,Date valueDate,String prodId){
		//定义返回值
				List<Map<String,Object>> dataMapList = new ArrayList<Map<String,Object>>();
				String sql = 
						"SELECT " 
						+ "SUM(nvl(t.TDY_COST_AMT,0)) TDY_COST_AMT," // 估值
						//+ "t.END_DAYS_2,"// 剩余存续期限
						//修改剩余存续期限取值逻辑 如果指标表无数据直接用资产表倒减,如果无到期日默认为基金返回1 add by leipeng
						+ "nvl(t.END_DAYS_2,nvl((t2.ACTMDATE - ?),1)) END_DAYS_2," //剩余存续期限
						+ "t.AL_FLAG,"// 资产负债标识
						+ "t2.DIS_ASSET_TYPETWO " // 资产一级分类(已经转换为信批资产类型，数据字典=ASSET_TYPE_DIS)
						+ "FROM rep_asset_index t "
						+ "LEFT JOIN rep_asset_info t2 ON t.BUSI_ID = t2.ASSET_ID AND t2.status ='E' "
						+ "WHERE t.VALUE_DATE = ? " // 资产估值日
						+ "AND t.prod_id = ? "
						+ "AND t.TDY_COST_AMT IS NOT NULL "
//						+ "AND t.END_DAYS_2 IS NOT NULL "
						+ "AND t.AL_FLAG IS NOT NULL "
						+ "GROUP BY t.END_DAYS_2,t.AL_FLAG,t2.DIS_ASSET_TYPETWO,t2.ACTMDATE";

		PreparedStatement ps = null;
		ResultSet rs = null;
				try {
					//创建执行语句
					ps = conn.prepareStatement(sql);
					//传参
					ps.setDate(1,new java.sql.Date(valueDate.getTime()));
					ps.setDate(2,new java.sql.Date(valueDate.getTime()));
					ps.setString(3, prodId);
					// 执行语句
					 rs = ps.executeQuery();
					Map<String,Object> dataMap = null;
					while (rs.next()) {
						dataMap = new HashMap<String,Object>();
						dataMap.put("TDY_COST_AMT", rs.getBigDecimal("TDY_COST_AMT"));// 估值
						dataMap.put("END_DAYS_2", rs.getInt("END_DAYS_2"));// 剩余存续期限
						dataMap.put("AL_FLAG", rs.getString("AL_FLAG"));// 资产负债标识
						dataMap.put("ASSET_TYPE_DIS", rs.getString("DIS_ASSET_TYPETWO"));// 资产一级分类(已经转换为信批资产类型，数据字典=ASSET_TYPE_DIS)
						dataMapList.add(dataMap);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					// 释放连接
					if (ps != null) {
						try {
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				
				return dataMapList;
	}
	
	/**
	 * 关联方式交易查询
	 * @param startDate
	 * @param endDate
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public  static List<Map<String,Object>> printTableTradeRela(Date startDate, Date endDate,Connection conn) throws Exception{
		List<Map<String,Object>>  listMap=new ArrayList<>();
		 
		List<RepTreadInfoDto> list=new ArrayList<>();
		//查询系统参数
		String  relType=CacheUtil.getSysParam(ReportGlobalConstant.REL_TYPE);
		if(relType.equals("01")){
			 list= getRepTreadInfoDtoListOne(startDate, endDate, conn);	
		}else if(relType.equals("02")){
			//根据截止日查询已披露信息
			int count=getCount(endDate, conn);
			if(count>0){
				//已披露
				 list= getRepTreadInfoDtoListTwo(startDate, endDate, conn);	
			}else{
				//未披露
				 list= getRepTreadInfoDtoListThree(startDate, endDate, conn);	
			}
		}
		//循环list
		if(list!=null&&list.size()!=0){
		for(RepTreadInfoDto  dto:list){
			Map<String,Object> resultmap=new HashMap<>();
			//资产名称
			resultmap.put("ASSET_NAME", dto.getAssetName());
			//买入方
			resultmap.put("BUY_PARTY", dto.getBuyParty());
			//卖出方
			resultmap.put("SELL_PARTY", dto.getSellParty());
			//交易金额
			resultmap.put("TRADE_AMT", dto.getTradeAmt());
			//合同号
			resultmap.put("CONTRACT_NO", dto.getContracNo());
			listMap.add(resultmap);
		}
		}
		return listMap;
	}
	
	
	
	/**
	 * 根据交易信息上挂的是否关联交易属性进行披露
	 * @param startDate
	 * @param endDate
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private static List<RepTreadInfoDto>  getRepTreadInfoDtoListOne(Date startDate,Date endDate,Connection conn) {
		//定义返回结果
		List<RepTreadInfoDto>  list=new ArrayList<>();
		 //编写sql
		String sql="SELECT ri.ASSET_NAME ASSET_NAME,p.BUY_PART  BUY_PARTY ,p.TRADE_COUNTY SELL_PARTY,p.TRADE_AMT/10000 TRADE_AMT,p.CONTRACT_NO FROM REP_TRADE_INFO p join REP_ASSET_INFO ri on ri.ASSET_ID=p.ASSET_ID  where p.STATUS='E' and p.TRADE_DATE<=? and p.TRADE_DATE>=? and p.IS_OUT_TRADE='Y'";		
		  //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1, new java.sql.Date(endDate.getTime()));
			ps.setDate(2, new java.sql.Date(startDate.getTime()));

			//执行语句
			 rs = ps.executeQuery();
			//获取结果集
			while(rs.next()){
				String aSSET_NAME=rs.getString("ASSET_NAME");
				String bUY_PARTY=rs.getString("BUY_PARTY");
				String sELL_PARTY=rs.getString("SELL_PARTY");
				BigDecimal tRADE_AMT=rs.getBigDecimal("TRADE_AMT");
				String cONTRACT_NO=rs.getString("CONTRACT_NO");
				RepTreadInfoDto dto=new RepTreadInfoDto();
				dto.setAssetName(aSSET_NAME);
				dto.setBuyParty(bUY_PARTY);
				dto.setSellParty(sELL_PARTY);
				dto.setTradeAmt(tRADE_AMT);
				dto.setContracNo(cONTRACT_NO);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


				return list;
	}
	
	/**
	 * 查询关联交易(已披露)
	 * @param startDate
	 * @param endDate
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private static List<RepTreadInfoDto>  getRepTreadInfoDtoListTwo(Date startDate,Date endDate,Connection conn){
		//定义返回结果
		List<RepTreadInfoDto>  list=new ArrayList<>();
		 //编写sql
		String sql="    SELECT ri.ASSET_NAME ASSET_NAME,p.BUY_PART  BUY_PARTY ,p.TRADE_COUNTY SELL_PARTY, p.TRADE_AMT/10000 TRADE_AMT,p.CONTRACT_NO FROM REP_TRADE_INFO p  join REP_ASSET_INFO ri on ri.ASSET_ID=p.ASSET_ID  where p.STATUS='E' and p.TRADE_DATE<=? and p.CONTRACT_NO in(select CONTRACT_NO from REP_RELTRDINFO  where REPDATE   between ? and  ?  )";		
		  //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1, new java.sql.Date(endDate.getTime()));
			ps.setDate(2, new java.sql.Date(startDate.getTime()));
			ps.setDate(3, new java.sql.Date(endDate.getTime()));
			//执行语句
			 rs = ps.executeQuery();
			//获取结果集
			while(rs.next()){
				String aSSET_NAME=rs.getString("ASSET_NAME");
				String bUY_PARTY=rs.getString("BUY_PARTY");
				String sELL_PARTY=rs.getString("SELL_PARTY");
				BigDecimal tRADE_AMT=rs.getBigDecimal("TRADE_AMT");
				String cONTRACT_NO=rs.getString("CONTRACT_NO");
				RepTreadInfoDto dto=new RepTreadInfoDto();
				dto.setAssetName(aSSET_NAME);
				dto.setBuyParty(bUY_PARTY);
				dto.setSellParty(sELL_PARTY);
				dto.setTradeAmt(tRADE_AMT);
				dto.setContracNo(cONTRACT_NO);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


				return list;
	}
	
	/**
	 * 查询关联交易(未披露)
	 * @param startDate
	 * @param endDate
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private static List<RepTreadInfoDto>  getRepTreadInfoDtoListThree(Date startDate,Date endDate,Connection conn) {
		//定义返回结果
		List<RepTreadInfoDto>  list=new ArrayList<>();
		 //编写sql
		String sql="   SELECT ri.ASSET_NAME ASSET_NAME,p.BUY_PART  BUY_PARTY ,p.TRADE_COUNTY SELL_PARTY, p.TRADE_AMT/10000 TRADE_AMT,p.CONTRACT_NO FROM REP_TRADE_INFO p  join REP_ASSET_INFO ri on ri.ASSET_ID=p.ASSET_ID join REP_RELORG  rr  on rr.RELORG_ID=p.TRADE_COUNTY  where 	p.STATUS='E' and p.IS_OUT_TRADE='Y' and p.TRADE_DATE<=? and p.CONTRACT_NO in(select CONTRACT_NO from REP_RELTRDINFO)";		
		  //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1, new java.sql.Date(endDate.getTime()));
			//执行语句
			rs = ps.executeQuery();
			//获取结果集
			while(rs.next()){
				String aSSET_NAME=rs.getString("ASSET_NAME");
				String bUY_PARTY=rs.getString("BUY_PARTY");
				String sELL_PARTY=rs.getString("SELL_PARTY");
				BigDecimal tRADE_AMT=rs.getBigDecimal("TRADE_AMT");
				String cONTRACT_NO=rs.getString("CONTRACT_NO");
				RepTreadInfoDto dto=new RepTreadInfoDto();
				dto.setAssetName(aSSET_NAME);
				dto.setBuyParty(bUY_PARTY);
				dto.setSellParty(sELL_PARTY);
				dto.setTradeAmt(tRADE_AMT);
				dto.setContracNo(cONTRACT_NO);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


				return list;
	}
	
	/**
	 * 根据截止日查询已披露信息
	 * @param endDate
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public  static int  getCount(Date endDate,Connection conn){
		int count=0; 
		//编写sql
		String sql="select count(1) from  REP_RELTRDINFO where REPDATE>=?";
		  //创建执行语句
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			// 传参
			ps.setDate(1, new java.sql.Date(endDate.getTime()));
			// 执行语句
			rs = ps.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return count;
	}
	/**
	 * 查询管理人银行
	 * @throws SQLException 
	 */
	public  String   getPraManger(Connection conn) {
		String praManger=null;
		
		//编写sql
				String sql="select  para.param_value bankname from  sys_para para where para.system_code ='DIS' and para.para_code ='BANK_NAME'";
		
				  //创建执行语句
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			// 执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				praManger=rs.getString("bankname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//释放连接
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

				return praManger;
	}
	
	/**
	 * 数据为null时转换为0
	 */
	private static BigDecimal null2Decimal(BigDecimal data) {
		return data == null?BigDecimal.ZERO:data;
	}
	
	/**
	 * 查询收益类型
	 * @throws SQLException 
	 */
	public  String   getProfitType(String prodId,Connection conn) throws SQLException{
		String profitType=null;
		
		//编写sql
		String sql="select PROD_TYPE  profitType from rep_prod_info where PROD_ID = ?";		
				  //创建执行语句
				PreparedStatement ps = conn.prepareStatement(sql);
				// 传参
				ps.setString(1, prodId);
				// 执行语句
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					profitType=rs.getString("profitType");
				}
				return profitType;
	}
	
}
