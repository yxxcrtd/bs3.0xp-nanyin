package com.joyintech.tams.component.business.evaluation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.business.evaluation.dto.AverageStrategyDto;
import com.joyintech.tams.component.business.evaluation.dto.BenchmarkDto;
import com.joyintech.tams.component.business.evaluation.dto.BokPositionPrd;
import com.joyintech.tams.component.business.evaluation.dto.BookLatestInfoDto;
import com.joyintech.tams.component.business.evaluation.dto.BooksetAndBokDetailDto;
import com.joyintech.tams.component.business.evaluation.dto.ChildCombination;
import com.joyintech.tams.component.business.evaluation.dto.DailyYieldDto;
import com.joyintech.tams.component.business.evaluation.dto.EvalIndexAlgorithmCalcParam;
import com.joyintech.tams.component.business.evaluation.dto.MstIntRateInfoDto;
import com.joyintech.tams.component.business.evaluation.dto.PtlTargetReturnDto;
import com.joyintech.tams.component.business.evaluation.dto.RevenueTargetListDetails;
import com.joyintech.tams.component.business.evaluation.dto.StepProducts;
import com.joyintech.tams.component.business.evaluation.dto.SubportfolioIncomeCapitalRatio;
import com.joyintech.tams.component.business.evaluation.dto.TimePointStrategyDto;

/**
 * 评价指标计算共通方法
 * 
 * @author zhangJin
 * @version 1.0 2019年9月30日
 * @see EvalIndexAlgorithmService
 * @since 1.0
 */
public interface EvalIndexAlgorithmService {

    /**
     * 计算【均值】
     * 
     * @param param 1.区间值列表
     * @return 均值
     * @see
     * @since 1.0
     */
    BigDecimal calcAverage(List<DailyYieldDto> list);

    /**
     * 计算【区间年化收益率】
     * 
     * @param param 1.区间开始日 2.区间结束日 3.区间开始日累计单位净值 4.区间结束日累计单位净值 5.区间开始日单位净值
     * @return 区间年化收益率
     * @see
     * @since 1.0
     */
    BigDecimal calcYearYield(EvalIndexAlgorithmCalcParam param);

    /**
     * 计算【净值增长率】
     * 
     * @param param 1.区间开始日累计单位净值 2.区间结束日累计单位净值 3.区间开始日单位净值
     * @return 净值增长率
     * @see
     * @since 1.0
     */
    BigDecimal calcNetValueGrowthRate(EvalIndexAlgorithmCalcParam param);

    /**
     * 计算【胜率】
     * 
     * @param param 1.区间开始日 2.区间结束日 3.区间业绩基准列表 4.区间每日投资收益率
     * @return 胜率
     * @see
     * @since 1.0
     */
    BigDecimal calcWinRate(EvalIndexAlgorithmCalcParam param);

    /**
     * 计算【正负收益周期比】
     * 
     * @param param 1.区间累计单位净值列表
     * @return 正负收益周期比
     * @see
     * @since 1.0
     */
    BigDecimal calcPeriodRadio(List<BokPositionPrd> accuNetValueList);

    /**
     * 计算【波动率】
     * 
     * @param param 1.区间日收益率列表
     * @return 波动率
     * @see
     * @since 1.0
     */
    BigDecimal calcVolatility(List<DailyYieldDto> yieldList);

    /**
     * 计算【年化波动率】
     * 
     * @param param 1.区间日收益率列表
     * @return 年化波动率
     * @see
     * @since 1.0
     */
    BigDecimal calcYearVolatility(List<DailyYieldDto> yieldList);

    /**
     * 计算【年化标准差】
     * 
     * @param param 1.标准差 2.周期数
     * @return 年化标准差
     * @see
     * @since 1.0
     */
    BigDecimal calcYearStdDev(BigDecimal stdDev, BigDecimal cycle);

    /**
     * 计算【下行标准差】
     * 
     * @param param 1.区间日收益率列表
     * @return 下行标准差
     * @see
     * @since 1.0
     */
    BigDecimal calcDownStdDev(List<DailyYieldDto> yieldList);

    /**
     * 计算【年化下行标准差】
     * 
     * @param param 1.区间日收益率列表
     * @return 年化下行标准差
     * @see
     * @since 1.0
     */
    BigDecimal calcYearDownStdDev(List<DailyYieldDto> yieldList);

    /**
     * 计算【最大回撤】
     * 
     * @param param 1.区间净值列表
     * @return 最大回撤
     * @see
     * @since 1.0
     */
    BigDecimal calcMaxRetreat(List<DailyYieldDto> netValueList);

    /**
     * 计算【年化特雷诺比率的β系数】
     * 
     * @param param 1.区间日收益率列表 2.区间基准日收益率列表 3.区间市场基准日收益率列表
     * @return β
     * @see
     * @since 1.0
     */
    BigDecimal calcβ(List<DailyYieldDto> yieldList,List<DailyYieldDto> standardList, List<DailyYieldDto> marketBaiscYieldList);

    /**
     * 计算【年化特雷诺比率】
     * 
     * @param param 1.区间日收益率列表 2.区间基准日收益率列表 3.区间市场基准日收益率列表
     * @return 年化特雷诺比率
     * @see
     * @since 1.0
     */
    BigDecimal calcTreynorRatio(List<DailyYieldDto> yieldList,
                                List<DailyYieldDto> standardList,
                                List<DailyYieldDto> marketBaiscYieldList);
    
    /**
     * 计算【詹森指数】
     * 
     * @param param 1.区间日收益率列表 2.区间基准日收益率列表 3.区间市场基准日收益率列表
     * @return 詹森指数
     * @see
     * @since 1.0
     */
    BigDecimal calcJensenIndex(List<DailyYieldDto> yieldList,
                               List<DailyYieldDto> standardList,
                               List<DailyYieldDto> marketBaiscYieldList);
    
    
	/**
	 * 计算【区间收益目标】
	 * 
	 * @param param
	 *            1.区间开始日 2.区间结束日 3.收益指标列表
	 * @return 区间收益目标
	 * @see
	 * @since 1.0
	 */
	BigDecimal calcRangeReturnTarget(Date intervalStartDate, Date intervalEndDate,
			List<RevenueTargetListDetails> rtldList);
	


    /**
     * 计算【年化夏普比】
     * 
     * @param param 1.区间日收益率列表 2.区间市场基准日收益率列表
     * @return 年化夏普比
     * @see
     * @since 1.0
     */
    BigDecimal calcSharpeRatio(List<DailyYieldDto> yieldList,
                               List<DailyYieldDto> marketBaiscYieldList);

    /**
     * 计算【年化索提诺比率】
     * 
     * @param param 1.区间日收益率列表 2.区间市场基准日收益率列表
     * @return 年化索提诺比率
     * @see
     * @since 1.0
     */
    BigDecimal calcSortinoRatio(List<DailyYieldDto> yieldList,
                                List<DailyYieldDto> marketBaiscYieldList);

    /**
     * 计算【年化索提诺比率--超额收益】
     * 
     * @param param 1.区间日收益率列表 2.区间市场基准日收益率列表
     * @return 超额收益
     * @see
     * @since 1.0
     */
    BigDecimal calcExtraYield(List<DailyYieldDto> yieldList,
                              List<DailyYieldDto> marketBaiscYieldList);

    /**
     * 计算【收益目标】
     * 
     * @param param 1.日期 2.组合代码3.层级
     * @return 收益目标
     * @see
     * @since 1.0
     */
    List<PtlTargetReturnDto> calcTargetReturn(Date date, String portfolioId, String level);
    
    /**
     * 查询【估值信息查询】列表
     */
    List<BokPositionPrd> getBokPositionPrdList(Map<String,Object> parMap);

    /**
     * 查询某一天的估值信息
     * @param parMap
     * @return
     */
    BokPositionPrd getBokPositionPrdOfDay(Map<String,Object> parMap);
    
    /**
     * 查询【基准日收益率列表】列表
     */
    List<MstIntRateInfoDto> getMstIntRateInfoDtoList(Map<String,Object> parMap);
    
    /**
     * 查询【最近一天非空利率】
     */
    BigDecimal nearistNotEmptyRateDayRate(Map<String,String> map);
    
    
	/**
	 * 凭证最新账务查询
	 *
	 * @param booksetId 账套代码
	 * @param bookDate 入账日
	 * @param bokDetailId 账务明细代码
	 * @param baseSubjectNature 基础科目性质
	 * @param tableId 场景代码
	 * @param tradeId 交易编号
	 * @param happenDate 发生日
	 * @param onwayDate 在途日期
	 * @param beginHappenCycDate 发生周期开始日
	 * @param endHappenCycDate 发生周期结束日
	 * @return List<BookLatestInfoDto>
	 * @see
	 * @since 1.0
	 */
	public List<BookLatestInfoDto> getBookLatestInfo(String booksetId, Date bookDate, String bokDetailId,
			List<String> baseSubjectNatureList, String tableId, String tradeId, Date happenDate, Date onwayDate,
			Date beginHappenCycDate, Date endHappenCycDate);
	
	/**
     * 计算类共通方法 --- 周期年化收益率
     * 
     * @param bokStatBokPositionList 帐套资产负债信息列表
     * @param cycBeginDate 周期开始日
     * @param cycEndDate 周期结束日
     * @param bonusReinvestList 红利再投信息列表
     * @param BasisDays 计算基础天数
     * @param feeBefOrAft 费前费后区分 [参考枚举类：ValDictCodeList.费前费后区分_XX.getValue()]
     * @return
     * @see
     * @since 1.0
     */
	public BigDecimal getCysYearRate(List<BokPositionPrd> bokPositionPrdList, Date cycBeginDate,
			Date cycEndDate, List<BookLatestInfoDto> bonusReinvestList, int BasisDays, String feeBefOrAft);
	
	/**
	 * 凭证最新账务查询公共方法 获取这个方法的参数
	 */
	public List<BooksetAndBokDetailDto> getCysYearRateParam(Map<String,Object> paraMap);
	
	 /**
     * 计算【平均策略执行有效性】
     * 
     * @param param 1.开始日期 2.结束日期3.组合代码
     * @return 平均策略执行有效性列表
     * @see
     * @since 1.0
     */
    List<AverageStrategyDto> calcAverageStrategy(Date startDate, Date endDate, String portfolioId);

    /**
     * 计算【时点策略执行有效性】
     * 
     * @param param 1.开始日期 2.结束日期3.组合代码
     * @return 时点策略执行有效性列表
     * @see
     * @since 1.0
     */
    List<TimePointStrategyDto> calcTimePointStrategy(Date startDate, Date endDate,
                                                     String portfolioId);

    /**
     * 计算【资产收益贡献率】
     * 
     * @param param 1.资产净值 2.资产成本3.组合资产净值 4.组合资产成本
     * @return 资产收益贡献率
     * @see
     * @since 1.0
     */
    BigDecimal calcAssetContributionRate(BigDecimal assetNetVal, BigDecimal assetCost,
                                         BigDecimal ptlAssetNetVal, BigDecimal ptlAssetCost);

    /**
     * 计算【业绩基准计算】
     * 
     * @param param 1.区间开始日2.区间结束日3.金融产品代码 4.分支序号5.层级
     * @return 业绩基准计算
     * @see
     * @since 1.0
     */
    List<BenchmarkDto> calcBenchmark(Date startDate, Date endDate, String finprodId,
                                     String branchId, String level);
    
    /**
	 * 分档产品查询
	 */
	public List<StepProducts> fileProductQuery(String finprodId);
	
	/**
	 * 子组合查询
	 */
	public List<ChildCombination> subcombinedQuery(String financialProductCode); // 金融产品代码
	
	/**
	 * 子组合收益资金占比计算
	 */
	public List<SubportfolioIncomeCapitalRatio> subportfolioIncomeCapitalRatio(Map<String,Object> paraMap);
}
