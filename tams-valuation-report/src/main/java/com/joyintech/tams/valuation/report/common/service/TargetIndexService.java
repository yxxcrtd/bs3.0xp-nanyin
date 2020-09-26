package com.joyintech.tams.valuation.report.common.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.report.common.calculation.AccumulatedValueRateCa;
import com.joyintech.tams.valuation.report.common.calculation.PerformanceBenchmarkCa;
import com.joyintech.tams.valuation.report.common.calculation.StageValueGrowthRate;
import com.joyintech.tams.valuation.report.common.calculation.StandardNetProductGrowthCa;
import com.joyintech.tams.valuation.report.common.calculation.StandardPerformanceCa;
import com.joyintech.tams.valuation.report.common.calculation.WaverageProfitCa;
import com.joyintech.tams.valuation.report.common.calculation.WeAverageIncomeCa;
import com.joyintech.tams.valuation.report.common.dto.FinProduct;
import com.joyintech.tams.valuation.report.common.dto.MstHoliday;
import com.joyintech.tams.valuation.report.common.dto.RepProdIndex;

/**
 * 用于指标的输出Service
 * 
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-01-02
 */
@Service
public class TargetIndexService {

	private static Logger logger = LoggerFactory.getLogger(TargetIndexService.class);

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
		if (finProduct != null) {
			paramMap2.put("calendarId", finProduct.getCalendarId());
		} else {
			paramMap2.put("calendarId", "");
		}

		//List<MstHoliday> mstHolidaylist = targetIndexMapper.selectMstHoliday(paramMap2);
        //改用Jdbc方式查询
		List<MstHoliday> mstHolidaylist=getListMstHolidaybyJdbc(paramMap2,conn);
		// 获得产品指标list中估值日=产品起息日的指标信息
		// 估值日=产品起息日
		RepProdIndex repProdIndex = new RepProdIndex();
		// 估值日=结束日期
		RepProdIndex repProd = new RepProdIndex();
		for (RepProdIndex in : list) {
			// 当产品起息日等于估值日时返回dto
			if (DateUtils.isSameDay(in.getValueDate(), finProduct.getVdate())) {
				repProdIndex = in;
				continue;
			}
			
		}
		for (RepProdIndex in2 : list) {
			// 当估值日=结束日期时返回dto
			if (DateUtils.isSameDay(in2.getValueDate(), endDate)) {
				repProd = in2;
				continue;
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

		// 计算sum(报告期内产品指标List.本期利润)
		BigDecimal sum = BigDecimal.ZERO;
		for (RepProdIndex index : list) {
			if (index.getProfitCurrent() != null) {
				sum = sum.add(index.getProfitCurrent());
			}
		}

		// 日历节假日list.toMap()
		Map<Date, Long> map = mstHolidaylist.stream()
				.collect(Collectors.toMap(MstHoliday::getCdate, MstHoliday::getIsHoliday, (old, ne) -> ne));
		// 报告期内基金份额MAP
		// 报告期内产品指标List.stream().collect(Collectors.toMap(产品指标表.get估值日(),
		// 产品指标表));
		Map<Date, RepProdIndex> mapRep = list.stream().collect(
				Collectors.toMap(RepProdIndex::getValueDate, Function.identity(), (entity1, entity2) -> entity1));

		// 调用共通方法：加权平均单位本期净收益计算(表二)
		BigDecimal wavlue = WeAverageIncomeCa.getWeAverageCurrentValue(sum, startDate, endDate, map, mapRep);
		//oneDto.setWavlue(wavlue);
		resultmap.put("wavlue", wavlue);
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
		// 表二dto
		//ResultOneDto oneDto = new ResultOneDto();
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
		if (finProduct != null) {
			paramMap2.put("calendarId", finProduct.getCalendarId());
		} else {
			paramMap2.put("calendarId", "");
		}

		//List<MstHoliday> mstHolidaylist = targetIndexMapper.selectMstHoliday(paramMap2);
		//改用Jdbc方式查询
		List<MstHoliday> mstHolidaylist=getListMstHolidaybyJdbc(paramMap2,conn);
		// 获得产品指标list中估值日=产品起息日的指标信息
		// 估值日=产品起息日
		RepProdIndex repProdIndex = new RepProdIndex();
		for (RepProdIndex in : list) {
			// 当产品起息日等于估值日时返回dto
			if (DateUtils.isSameDay(in.getValueDate(), finProduct.getVdate())) {
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

		// 计算sum(报告期内产品指标List.本期利润)
		BigDecimal sum = BigDecimal.ZERO;
		for (RepProdIndex index : list) {
			if (index.getProfitCurrent() != null) {
				sum = sum.add(index.getProfitCurrent());
			}
		}

		// 日历节假日list.toMap()
		Map<Date, Long> map = mstHolidaylist.stream()
				.collect(Collectors.toMap(MstHoliday::getCdate, MstHoliday::getIsHoliday, (old, ne) -> ne));
		// 报告期内基金份额MAP
		// 报告期内产品指标List.stream().collect(Collectors.toMap(产品指标表.get估值日(),
		// 产品指标表));
		Map<Date, RepProdIndex> mapRep = list.stream().collect(
				Collectors.toMap(RepProdIndex::getValueDate, Function.identity(), (entity1, entity2) -> entity1));

		// 调用共通 加权平均产品份额本期利润计算(表二)
		BigDecimal mavalue = WaverageProfitCa.getAveragePfofitCa(sum, startDate, endDate, map, mapRep);
		//oneDto.setMavalue(mavalue);
		resultmap.put("mavalue", mavalue);
		// 累积净值增长率(即使结束日=估值日的产品指标的累积净值增长率)(表二)
		RepProdIndex rep = getShareValue(endDate, list);
		// 塞入表二dto
		//oneDto.setProductShareValue(rep.getNetvalueTotalIncre().multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("productShareValue", rep.getNetvalueTotalIncre().multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		
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
		for (RepProdIndex in : list) {
			// 当产品起息日等于估值日时返回dto
			if (DateUtils.isSameDay(in.getValueDate(), finProduct.getVdate())) {
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
		BigDecimal currentValue = AccumulatedValueRateCa.getAccValueRateByDay(startDate, endDate, mapRep);
		//twoDto.setCurrentValue(currentValue.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("currentValue", currentValue.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		// 业绩比较基准收益率(表三)->调用共通
		Map<String, Object> claMap=PerformanceBenchmarkCa.getClaCompareRule(startDate, endDate, prodId, conn);
		//获取阶段业绩比较基准
		BigDecimal claCompareRule = (BigDecimal) claMap.get("claCompareRule");
		//获取业绩比较基准list
		List<BigDecimal> claCompareRuleList = (List<BigDecimal>) claMap.get("list");
		// 业绩比较基准收益率塞入表三dto
		//twoDto.setFloatRate(claCompareRule.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("claCompareRule", claCompareRule.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		// 业绩比较基准收益率标准差(表三)->调用标准差公式共通
		BigDecimal floatRateStandard = StandardPerformanceCa.getStandardPerformance(claCompareRuleList);
		// 业绩比较基准收益率标准差塞入表三dto
		//twoDto.setFloatRateStandard(floatRateStandard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("floatRateStandard", floatRateStandard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
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
			listGro.add(in.getNetvalueTdyIncre());
		}
		// 份额净值增长率标准差
		BigDecimal productGrowth = StandardNetProductGrowthCa.getStandardNetProductGrowth(listGro);
		//twoDto.setProductGrowth(productGrowth.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("productGrowth", productGrowth.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		// 标的类金融产品查询
		//FinProduct finProduct = targetIndexMapper.selectFinProduct(prodId);
		 //改用jdbc方式查询
		FinProduct finProduct=getFinProductByJdbc(prodId,conn);
		// 估值日=产品起息日
				RepProdIndex repProdIndex = new RepProdIndex();
				for (RepProdIndex in : list) {
					// 当产品起息日等于估值日时返回dto
					if (DateUtils.isSameDay(in.getValueDate(), finProduct.getVdate())) {
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
		// 业绩比较基准收益率(表三)->调用共通
		Map<String, Object> claMap=PerformanceBenchmarkCa.getClaCompareRule(startDate, endDate, prodId, conn);
		//获取阶段业绩比较基准
		BigDecimal claCompareRule = (BigDecimal) claMap.get("claCompareRule");
		//获取业绩比较基准list
		List<BigDecimal> claCompareRuleList = (List<BigDecimal>) claMap.get("list");
		// 业绩比较基准收益率塞入表三dto
		//twoDto.setFloatRate(claCompareRule.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("claCompareRule", claCompareRule.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		// 业绩比较基准收益率标准差(表三)->调用标准差公式共通
		BigDecimal floatRateStandard = StandardPerformanceCa.getStandardPerformance(claCompareRuleList);
		// 业绩比较基准收益率标准差塞入表三dto
		//twoDto.setFloatRateStandard(floatRateStandard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("floatRateStandard", floatRateStandard.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		// 表三:阶段净值增长率->调用共通（开始日期，结束日期，基金份额Map）
		// 基金份额Map-> 由报告期内产品指标List转换
		Map<Date, RepProdIndex> mapRep = list.stream().collect(
				Collectors.toMap(RepProdIndex::getValueDate, Function.identity(), (entity1, entity2) -> entity1));
		// 调用共通
		BigDecimal stageRate = StageValueGrowthRate.getStageValueGrowthRate(startDate, endDate, mapRep);
		// 塞入表三dto
		//twoDto.setStageRate(stageRate.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
		resultmap.put("stageRate", stageRate.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
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
	public  RepProdIndex getShareValue(Date cDate, List<RepProdIndex> list) {
		RepProdIndex result = new RepProdIndex();
		// 获取参数日期=估值日的dto
		for (RepProdIndex in : list) {
			// 获取参数日等于估值的dto
			if (DateUtils.isSameDay(in.getValueDate(), cDate)) {
				result = in;
				break;
			}
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
	public static  List<RepProdIndex>  getListRepProdIndebyJjdbc(Date beforeDate,Date endDate,String prodId,Connection  conn) throws Exception{
		//定义返回结果
		List<RepProdIndex>  list=new ArrayList<>();
		
			//编写sql
			String   sql=" select PROD_ID,VALUE_DATE,CAPITAL,NETVALUE_TDY_YIELD, PROFIT_CURRENT,ASSET_VALUE_COST,NETVALUE_TOTAL_YIELD,NETVALUE_TOTAL_INCRE,W_YIELD,NETVALUE_TDY_INCRE from REP_PROD_INDEX   where VALUE_DATE   >= ? and VALUE_DATE    <=   ?  and PROD_ID=?";
			  //创建执行语句
		PreparedStatement ps = null;
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
				BigDecimal cAPITAL=rs.getBigDecimal("CAPITAL");
				BigDecimal nETVALUE_TDY_YIELD=rs.getBigDecimal("NETVALUE_TDY_YIELD");
				BigDecimal pROFIT_CURRENT=rs.getBigDecimal("PROFIT_CURRENT");
				BigDecimal aSSET_VALUE_COST=rs.getBigDecimal("ASSET_VALUE_COST");
				BigDecimal  nETVALUE_TOTAL_YIELD=rs.getBigDecimal("NETVALUE_TOTAL_YIELD");
				BigDecimal nETVALUE_TOTAL_INCRE=rs.getBigDecimal("NETVALUE_TOTAL_INCRE");
				BigDecimal  w_YIELD=rs.getBigDecimal("W_YIELD");
				BigDecimal nETVALUE_TDY_INCRE=rs.getBigDecimal("NETVALUE_TDY_INCRE");
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
				list.add(index);
			}
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			//释放连接
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					logger.error("", e);
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
	public  static FinProduct getFinProductByJdbc(String prodId,Connection conn) throws Exception{
		//定义返回值
		FinProduct finProduct = new FinProduct();
		//编写sql
		String sql = "select FINPROD_ID,CALENDAR_ID,VDATE  from FIN_PRODUCT 	WHERE FINPROD_ID=? AND FINPROD_TYPE='F16'";
		//创建执行语句
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setString(1, prodId);
			//执行语句
			rs = ps.executeQuery();
			//获取结果集
			if(rs.next()){
				String fINPROD_ID=rs.getString("FINPROD_ID");
				String cALENDAR_ID=rs.getString("CALENDAR_ID");
				Date vDATE=rs.getDate("VDATE");
				finProduct.setFinprodId(fINPROD_ID);
				finProduct.setCalendarId(cALENDAR_ID);
				finProduct.setVdate(vDATE);
			}
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			//释放连接
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					logger.error("", e);
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
	
	public  List<MstHoliday> getListMstHolidaybyJdbc( Map<String, Object> paramMap,Connection conn) throws Exception{
		//定义返回值
		List<MstHoliday> list = new ArrayList<>();

		//编写sql
		String sql = " select CDATE,IS_HOLIDAY from MST_HOLIDAY  where CDATE  >=  ?	and CDATE   <= ? and CALENDAR_ID=?";
		//创建执行语句
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(((Date)paramMap.get("startDate")).getTime()));
			ps.setDate(2,new java.sql.Date(((Date)paramMap.get("endDate")).getTime()));
			ps.setString(3, (String)paramMap.get("calendarId"));
			//执行语句
			rs = ps.executeQuery();
			while(rs.next()){
				Date cDATE=rs.getDate("CDATE");
				Long  iS_HOLIDAY=rs.getLong("IS_HOLIDAY");
				MstHoliday mstHoliday=new MstHoliday();
				mstHoliday.setCdate(cDATE);
				mstHoliday.setIsHoliday(iS_HOLIDAY);
				list.add(mstHoliday);
			}
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			//释放连接
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					logger.error("", e);
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
	public  void  setCTTableOther(Date startDate,Date endDate,String prodId,Map<String,Object> map,Connection conn) throws Exception{
		//编写sql
		String sql = "SELECT SUM(NVL(bqysx,0))  BQYSX ,SUM(NVL(BQLR,0)) BQLR ,SUM(NVL(SSZB,0)) SSZB ,round(SUM(NVL(JZSY,0)),4)  JZSY,1 FEJZ ,PROD_ID FROM (select PROFIT_CURRENT_YES  bqysx, PROFIT_CURRENT  BQLR, case when VALUE_DATE=? then CAPITAL  end SSZB,case when VALUE_DATE=? then NETVALUE_TOTAL_YIELD end JZSY,prod_id from rep_prod_index WHERE PROD_ID=?  and  VALUE_DATE<=? and VALUE_DATE>=?) GROUP BY PROD_ID";
		//创建执行语句
		PreparedStatement ps = null;
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
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			//释放连接
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					logger.error("", e);
				}

			}
		}


	}
	
	/**
	 * 净值型表二其他值
	 * @param
	 * @throws Exception 
	 */
	public void setNTTableOther(Date startDate,Date endDate,String prodId,Map<String,Object> map,Connection conn) throws Exception{

		//编写sql
		String sql = "SELECT sum(a.PROFITCURRENTYES) PROFITCURRENTYES,sum(a.PROFITCURRENT) PROFITCURRENT,sum(b.ASSETVALUEMARKET) ASSETVALUEMARKET,sum(b.UNITVALUE) UNITVALUE, sum(b.NETVALUETOTAL) NETVALUETOTAL FROM (SELECT SUM(RPI.PROFIT_CURRENT_YES) PROFITCURRENTYES,SUM(RPI.PROFIT_CURRENT) PROFITCURRENT FROM REP_PROD_INDEX RPI where RPI.VALUE_DATE BETWEEN ? AND ? and RPI.PROD_ID = ? ) a,(select a.ASSET_VALUE_MARKET   ASSETVALUEMARKET, a.UNIT_VALUE   UNITVALUE,a.NETVALUE_TOTAL_INCRE NETVALUETOTAL from REP_PROD_INDEX a where a.VALUE_DATE = ? and a.PROD_ID =?)b";
		//创建执行语句
		PreparedStatement ps = null;
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
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			//释放连接
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}

	}
	
}
