/*
 * 文件名：EvalIndexAlgorithmServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年9月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.evaluationentrance.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;

import com.joyintech.tams.invest.common.evaluationentrance.dto.DailyYieldDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 评价指标计算共通方法实现类
 *
 * @author zhangJin
 * @version 1.0 2019年9月30日
 * @see ProdIndexAlgorithmService
 * @since 1.0
 */
@Service
public class ProdIndexAlgorithmService {

    private static BigDecimal Day_365 = new BigDecimal(365);

//    @Autowired
//    private EvalIndexAlgorithmMapper evalIndexAlgorithmMapper;
//
//    @Autowired
//    private BokPositionPrdReferenceMapper bokPositionPrdMapper;
//
//    @Autowired
//    private MstIntRateInfoReferenceMapper mstIntRateInfoMapper;
//
//    /**
//     * 共通查询方法
//     */
//    @Autowired
//    private CommonSearchMapper commonSearchMapper;
//
//    /**
//     * 数据库数据获取
//     */
//    @Autowired
//    private AccountingCommonMapper accountingCommonMapper;

    /**
     * 计算【均值】
     *
     * @param list 1.区间值列表
     * @return 均值
     * @see
     * @since 1.0
     */
    public BigDecimal calcAverage(List<DailyYieldDto> list) {
        BigDecimal sum = BigDecimal.ZERO;
        for (DailyYieldDto temp : list) {
            if(temp.getYield()!=null){
            sum = sum.add(temp.getYield());
            }
        }
        BigDecimal average = ArithmeticUtil.divide(sum, list.size(), 16);
        return average;
    }
//
//    /**
//     * 计算【区间年化收益率】
//     *
//     * @param param 1.区间开始日 2.区间结束日 3.区间开始日累计单位净值 4.区间结束日累计单位净值 5.区间开始日单位净值
//     * @return 区间年化收益率
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public BigDecimal calcYearYield(EvalIndexAlgorithmCalcParam param) {
//        // 差值：区间累计单位净值
//        BigDecimal subRangeANV = ArithmeticUtil.subtract(param.geteAccuNetValue(),
//                param.getsAccuNetValue());
//        // 差值：区间天数
//        BigDecimal subRangeDays = new BigDecimal(DateUtil.getBetweenDayNumber(param.getStartDate(),
//                param.getEndDate()));
//
//        // 公式：【区间年化收益率 = (区间结束日累计单位净值 - 区间开始日累计单位净值)/区间开始日单位净值* 365/(区间结束日 - 区间开始日)】
//        BigDecimal yearYield = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(ArithmeticUtil.divide(subRangeANV, param.getsNetValue(), 16),
//                        Day_365),
//                subRangeDays, 16);
//        return yearYield;
//    }
//
//    /**
//     * 计算【净值增长率】
//     *
//     * @param param 1..区间开始日累计单位净值 2.区间结束日累计单位净值 3.区间开始日单位净值
//     * @return 净值增长率
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public BigDecimal calcNetValueGrowthRate(EvalIndexAlgorithmCalcParam param) {
//        // 公式：【净值增长率 = (区间结束日累计单位净值 - 区间开始日累计单位净值)/区间开始日单位净值】
//        BigDecimal rate = ArithmeticUtil.divide(
//                ArithmeticUtil.subtract(param.geteAccuNetValue(), param.getsAccuNetValue()),
//                param.getsNetValue(), 16);
//        return rate;
//    }
//
//    /**
//     * 计算【胜率】
//     *
//     * @param param 1.区间开始日 2.区间结束日 3.区间业绩基准列表 4.区间每日投资收益率
//     * @return 胜率
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public BigDecimal calcWinRate(EvalIndexAlgorithmCalcParam param) {
//        // 【区间每日投资收益率】列表
//        List<DailyYieldDto> dailyBasicRateList = this.sort(param.getDailyBasicRateList());
//        // 【区间每日投资收益率】列表
//        List<DailyYieldDto> dailyInvestYieldList = this.sort(param.getDailyInvestYieldList());
//
//        int amount = 0;
//        for (int a = 0; a < dailyInvestYieldList.size(); a++ ) {
//            Date yiledDate = dailyInvestYieldList.get(a).getDate();
//            BigDecimal yiled = dailyInvestYieldList.get(a).getYield();
//
//            for (int b = 0; b < dailyBasicRateList.size(); b++ ) {
//                Date rateDate = dailyBasicRateList.get(b).getDate();
//                BigDecimal rate = dailyBasicRateList.get(b).getYield();
//                if (StringUtil.isNotEmpty(yiledDate)&&StringUtil.isNotEmpty(yiled)){
//                    if (DateUtil.compareDate(yiledDate,rateDate) == 0 && yiled.compareTo(rate) > 0) {
//                        amount = amount + 1;
//                        break;
//                    }}
//            }
//        }
//        // 差值：区间天数
//        BigDecimal subRangeDays = new BigDecimal(DateUtil.getBetweenDayNumber(param.getStartDate(),
//                DateUtil.addDay(param.getEndDate(), 1)));
//        // 公式：【胜率 = 变量.正收益率数量/(区间结束日 - 区间开始日+1)】
//        BigDecimal winRate = ArithmeticUtil.divide(new BigDecimal(amount), subRangeDays, 16);
//        return winRate;
//    }
//
//    /**
//     * 计算【正负收益周期比】
//     *
//     * @param accuNetValueList 1.区间累计单位净值列表
//     * @return 正负收益周期比
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public BigDecimal calcPeriodRadio(List<BokPositionPrd> accuNetValueList) {
//        // 正收益数量
//        int positive = 0;
//        // 负收益数量
//        int negative = 0;
//        for (int i = accuNetValueList.size() - 1; i > 0; i-- ) {
//            if (accuNetValueList.get(i).getTdyUnitValueSum()!=null
//                && accuNetValueList.get(i-1).getTdyUnitValueSum()!=null
//                && accuNetValueList.get(i).getTdyUnitValueSum().compareTo(
//                accuNetValueList.get(i - 1).getTdyUnitValueSum()) > 0) {
//                positive = positive + 1;
//            }
//            else {
//                negative = negative + 1;
//            }
//        }
//        // 公式：【正负收益周期比 = 正收益数量/负收益数量】
//        BigDecimal periodRadio = ArithmeticUtil.divide(new BigDecimal(positive),
//                new BigDecimal(negative), 16);
//        return periodRadio;
//    }

    /**
     * 计算【波动率】
     *
     * @param yieldList 1.区间日收益率列表
     * @return 波动率
     * @see
     * @since 1.0
     */
    public BigDecimal calcVolatility(List<DailyYieldDto> yieldList) {
        yieldList = this.sort(yieldList);
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);
        // 波动率
        BigDecimal volatility = BigDecimal.ZERO;

        // 变量.波动率 = 变量.波动率 +(列表.日收益率 - 变量.日收益率均值)*(列表.日收益率 - 变量.日收益率均值)
        for (DailyYieldDto temp : yieldList) {
            BigDecimal sub = ArithmeticUtil.subtract(temp.getYield(), yieldAverage);
            volatility = volatility.add(ArithmeticUtil.multiply(sub, sub));
        }
        // 求平方根
        // 公式：【波动率 = sqrt(波动率/列表.size()-1)】
        BigDecimal divide = ArithmeticUtil.divide(volatility, (yieldList.size() - 1), 16);
        double v = divide.doubleValue();
        double sqrt = Math.sqrt(v);
        volatility = BigDecimal.valueOf(sqrt);
        return volatility;
    }

    /**
     * 计算【年化波动率】
     *
     * @param yieldList 1.区间日收益率列表
     * @return 年化波动率
     * @see
     * @since 1.0
     */
    public BigDecimal calcYearVolatility(List<DailyYieldDto> yieldList) {
        // 波动率
        BigDecimal volatility = this.calcVolatility(yieldList);
        return this.calcYearStdDev(volatility, Day_365);
    }

    /**
     * 计算【年化标准差】
     *
     * @param stdDev 1.标准差 2.周期数
     * @return 年化标准差
     * @see
     * @since 1.0
     */
    public BigDecimal calcYearStdDev(BigDecimal stdDev, BigDecimal cycle) {
        // 年化标准差 = sqrt(周期数)* 参数.标准差
        BigDecimal yearStdDev = BigDecimal.valueOf(Math.sqrt(cycle.doubleValue())).multiply(
                stdDev);
        return yearStdDev;
    }

    /**
     * 计算【下行标准差】
     *
     * @param yieldList 1.区间日收益率列表
     * @return 下行标准差
     * @see
     * @since 1.0
     */
    public BigDecimal calcDownStdDev(List<DailyYieldDto> yieldList) {
        yieldList = this.sort(yieldList);

        // 下行标准差
        BigDecimal downStdDev = BigDecimal.ZERO;
        // 区间负收益率列表
        List<DailyYieldDto> negYieldList = new ArrayList<>();
        for (DailyYieldDto dto : yieldList) {

            if (dto.getYield()!=null&&dto.getYield().compareTo(BigDecimal.ZERO) < 0) {
                negYieldList.add(dto);
            }
        }

        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(negYieldList);

        // 下行标准差 = 下行标准差 +(区间负收益率列表.日收益率 - 日收益率均值)*(区间负收益率列表.日收益率 - 日收益率均值)
        for (DailyYieldDto temp : negYieldList) {
            BigDecimal sub = ArithmeticUtil.subtract(temp.getYield(), yieldAverage);
            downStdDev = downStdDev.add(ArithmeticUtil.multiply(sub, sub));
        }
        // 求平方根
        // 公式：【下行标准差 = sqrt(下行标准差/列表.size()-1)】
        BigDecimal divide = ArithmeticUtil.divide(downStdDev, (negYieldList.size() - 1), 16);
        double v = divide.doubleValue();
        double sqrt = Math.sqrt(v);
        downStdDev = BigDecimal.valueOf(sqrt);
        return downStdDev;
    }

    /**
     * 计算【年化下行标准差】
     *
     * @param yieldList 1.区间日收益率列表
     * @return 年化下行标准差
     * @see
     * @since 1.0
     */
    public BigDecimal calcYearDownStdDev(List<DailyYieldDto> yieldList) {
        // 下行标准差
        BigDecimal downStdDev = this.calcDownStdDev(yieldList);
        return this.calcYearStdDev(downStdDev, Day_365);
    }

    /**
     * 计算【最大回撤】
     *
     * @param netValueList 1.区间净值列表
     * @return 最大回撤
     * @see
     * @since 1.0
     */
    public BigDecimal calcMaxRetreat(List<DailyYieldDto> netValueList) {
        netValueList = this.sort(netValueList);
        // 净值差列表
        List<BigDecimal> subNetValueList = new ArrayList<>();
        for (int i = 0; i < netValueList.size(); i++ ) {
            // 净值差列表.[i] = 区间净值列表.[i].单位净值 - 区间净值列表(i，区间净值列表.size())【之间净值按照单位净值升序取最小值】
            BigDecimal temp = ArithmeticUtil.subtract(netValueList.get(i).getYield(),
                    this.getMinVal(netValueList, i));
            subNetValueList.add(temp);
        }
        // 净值差列表按照净值绝对值降续排列
        subNetValueList.sort((num1, num2) -> num2.abs().compareTo(num1.abs()));
        return subNetValueList.get(0);
    }

    /**
     * 获取之间净值(按照单位净值升序取最小值)
     *
     * @param netValueList
     * @param i
     * @see
     * @since 1.0
     */
    public BigDecimal getMinVal(List<DailyYieldDto> netValueList, int i) {
        List<DailyYieldDto> tempList = new ArrayList<>();
        tempList.addAll(netValueList);
        for (int a = 0; a < i; a++ ) {
            tempList.remove(0);
        }
        // 升序排列
        tempList.sort(Comparator.comparing(DailyYieldDto::getYield));
        return tempList.get(0).getYield();
    }

    /**
     * 计算【年化特雷诺比率的β系数】 Beta
     *
     * @param yieldList 1.区间日收益率列表 2.区间基准日收益率列表 3.区间市场基准日收益率列表
     * @return β
     * @see
     * @since 1.0
     */
    public BigDecimal calcβ(List<DailyYieldDto> yieldList,
                            List<DailyYieldDto> standardList,
                            List<DailyYieldDto> marketBaiscYieldList) {
        yieldList = this.sort(yieldList);
        standardList = this.sort(standardList);
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);
        //基准日收益率均值
        BigDecimal standardAvg = this.calcAverage(standardList);

        BigDecimal β = BigDecimal.ZERO;
        // 分母
        BigDecimal denominator = BigDecimal.ZERO;
        // 变量.β系数 = 变量.β系数 + (区间基准日收益率列表[i].日收益率-变量.基准日收益率均值)*（区间日收益率列表.[i].日收益率-变量.日收益率均值）
        for (int i = 0; i < yieldList.size(); i++ ) {
            BigDecimal subBasicYield = ArithmeticUtil.subtract(
                    standardList.get(0).getYield(), standardAvg);
            BigDecimal subYield = ArithmeticUtil.subtract(yieldList.get(i).getYield(),
                    yieldAverage);
            β = ArithmeticUtil.doAdd(β, ArithmeticUtil.multiply(subBasicYield, subYield));
        }
        // 变量.分母 = 变量.分母 +
        // (区间基准日收益率列表[j].日收益率-变量.基准日收益率均值)*(区间基准日收益率列表[j].日收益率-变量.基准日收益率均值)
        for (int i = 0; i < standardList.size(); i++ ) {
            BigDecimal subBasicYield = ArithmeticUtil.subtract(
                    standardList.get(i).getYield(), standardAvg);
            denominator = ArithmeticUtil.doAdd(denominator,
                    ArithmeticUtil.multiply(subBasicYield, subBasicYield));
        }

        β = ArithmeticUtil.divide(β, denominator, 16);

        return β;
    }

    
    /**
     * 计算【年化特雷诺比率】
     *
     * @param yieldList 1.区间日收益率列表 2.区间基准日收益率列表 3.区间市场基准日收益率列表
     * @return 年化特雷诺比率
     * @see
     * @since 1.0
     */
    public BigDecimal calcTreynorRatio(List<DailyYieldDto> yieldList,
                                       List<DailyYieldDto> standardList,
                                       List<DailyYieldDto> marketBaiscYieldList) {
        BigDecimal β = this.calcβ(yieldList,standardList, marketBaiscYieldList);

        BigDecimal TreynorRatio = BigDecimal.ZERO;
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);
        // 市场基准日收益率均值
        BigDecimal basicYieldAverage = this.calcAverage(marketBaiscYieldList);

        if (β.compareTo(BigDecimal.ZERO) != 0) {
            TreynorRatio = ArithmeticUtil.multiply(ArithmeticUtil.divide(
                    ArithmeticUtil.subtract(yieldAverage, basicYieldAverage), β, 16), Day_365);
        }
        return TreynorRatio;
    }

    /**
     * list排序：日期升序
     *
     * @param list 目标list
     * @return 结果
     * @see
     * @since 1.0
     */
    public List<DailyYieldDto> sort(List<DailyYieldDto> list) {
        list.sort(Comparator.comparing(DailyYieldDto::getCdate));
        return list;
    }

    /**
     * 计算【詹森指数】
     *
     * @param yieldList 1.区间日收益率列表 2.区间基准日收益率列表 3.区间市场基准日收益率列表
     * @return 詹森指数
     * @see
     * @since 1.0
     */
    public BigDecimal calcJensenIndex(List<DailyYieldDto> yieldList,
                                      List<DailyYieldDto> standardList,
                                      List<DailyYieldDto> marketBaiscYieldList) {
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);

        //基准日收益率均值
        BigDecimal standardAvg = this.calcAverage(standardList);

        // 市场基准日收益率均值
        BigDecimal basicYieldAverage = this.calcAverage(marketBaiscYieldList);

        // 计算β系数
        BigDecimal β = this.calcβ(yieldList,standardList, marketBaiscYieldList);

        // 初始化詹森指数
        BigDecimal jensenIndex = BigDecimal.ZERO;

        jensenIndex = ArithmeticUtil.multiply(
                ArithmeticUtil.subtract(yieldAverage,
                        ArithmeticUtil.doAdd(basicYieldAverage,
                                ArithmeticUtil.multiply(β, ArithmeticUtil.subtract(standardAvg, basicYieldAverage)))),
                Day_365);

        return jensenIndex;
    }

//    /**
//     * 计算【区间收益目标】
//     *
//     * @param intervalEndDate
//     *            1.区间开始日 2.区间结束日 3.收益指标列表
//     * @return 区间收益目标
//     * @see
//     * @since 1.0
//     */
//    public BigDecimal calcRangeReturnTarget(Date intervalStartDate, Date intervalEndDate,
//                                            List<RevenueTargetListDetails> rtldList) {
//
//        // 初始化收益目标
//        BigDecimal earningsTarget = BigDecimal.ZERO;
//
//        for(RevenueTargetListDetails rtld : rtldList) {
//            earningsTarget = ArithmeticUtil.doAdd(earningsTarget, rtld.getEarningsTarget());
//        }
//
//        //变量.收益目标 = 变量.收益目标/(区间结束日-区间开始日+1)
//
//        long days = DateUtil.getBetweenDayNumber(intervalStartDate, DateUtil.addDay(intervalEndDate,1));
//
//        earningsTarget = ArithmeticUtil.divide(earningsTarget,BigDecimal.valueOf(days),16);
//
//        return earningsTarget;
//    }

    /**
     * 计算【年化夏普比】
     *
     * @param yieldList 1.区间日收益率列表 2.区间市场基准日收益率列表
     * @return 年化夏普比
     * @see
     * @since 1.0
     */
    public BigDecimal calcSharpeRatio(List<DailyYieldDto> yieldList,
                                      List<DailyYieldDto> marketBaiscYieldList) {
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);
        // 年化日收益率
        // BigDecimal yearYield = ArithmeticUtil.multiply(yieldAverage, Day_365);
        // 市场基准日收益率均值
        BigDecimal basicYieldAverage = this.calcAverage(marketBaiscYieldList);
        // 超额收益率标准差
        BigDecimal stdDev = this.calcExtraYield(yieldList, marketBaiscYieldList);

        // 变量.年化夏普比 = (变量.日收益率均值 -变量.市场基准日收益率均值)/变量.超额收益率标准差*sqrt(365)
        BigDecimal SharpeRadio = ArithmeticUtil.multiply(
                ArithmeticUtil.divide(ArithmeticUtil.subtract(yieldAverage, basicYieldAverage), stdDev,
                        16),
                BigDecimal.valueOf(Math.sqrt(Day_365.doubleValue())));

        return SharpeRadio;
    }

    /**
     * 计算【年化夏普比--超额收益率标准差】
     *
     * @param yieldList 1.区间日收益率列表 2.区间市场基准日收益率列表
     * @return 超额收益
     * @see
     * @since 1.0
     */
    public BigDecimal calcExtraYield(List<DailyYieldDto> yieldList,
                                     List<DailyYieldDto> marketBaiscYieldList) {
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);
        // 年化日收益率
        // BigDecimal yearYield = ArithmeticUtil.multiply(yieldAverage, Day_365);
        // 市场基准日收益率均值
        BigDecimal basicYieldAverage = this.calcAverage(marketBaiscYieldList);
        // 超额收益率标准差
        BigDecimal stdDev = BigDecimal.ZERO;

        for (int a = 0; a < yieldList.size(); a++ ) {
            Date yiledDate = yieldList.get(a).getCdate();
            BigDecimal yiled = yieldList.get(a).getYield();
            if(yiledDate == null || yiled == null){
                break;
            }

            for (int b = 0; b < marketBaiscYieldList.size(); b++ ) {
                Date rateDate = marketBaiscYieldList.get(b).getCdate();
                BigDecimal rate = marketBaiscYieldList.get(b).getYield();
                if (DateUtil.compareDate(yiledDate,rateDate) == 0) {
                    // temp1=区间日收益率列表.日收益率 - 区间市场基准日收益率列表.日收益率/区间市场基准日收益率列表.size()
                    BigDecimal temp1 = ArithmeticUtil.subtract(yiled,
                            ArithmeticUtil.divide(rate, marketBaiscYieldList.size(), 16));
                    // temp2=变量.日收益率均值-变量.市场基准日收益率均值/区间市场基准日收益率列表.size())
                    BigDecimal temp2 = ArithmeticUtil.subtract(yieldAverage,
                            ArithmeticUtil.divide(basicYieldAverage, marketBaiscYieldList.size(), 16));
                    BigDecimal temp = ArithmeticUtil.subtract(temp1, temp2);
                    // 公式：【变量.超额收益率标准差+temp²】
                    stdDev = ArithmeticUtil.doAdd(stdDev, ArithmeticUtil.multiply(temp, temp));
                    break;
                }
            }
        }
        // 求平方根
        // 公式：【超额收益率标准差 = sqrt(超额收益率标准差/列表.size()-1)】
        BigDecimal divide = ArithmeticUtil.divide(stdDev, (yieldList.size() - 1), 16);
        double v = divide.doubleValue();
        double sqrt = Math.sqrt(v);
        stdDev = BigDecimal.valueOf(sqrt);
        return stdDev;
    }

    /**
     * 计算【年化索提诺比率】
     *
     * @param yieldList 1.区间日收益率列表 2.区间市场基准日收益率列表
     * @return 年化索提诺比率
     * @see
     * @since 1.0
     */
    public BigDecimal calcSortinoRatio(List<DailyYieldDto> yieldList,
                                       List<DailyYieldDto> marketBaiscYieldList) {
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);
        // 年化日收益率
        // BigDecimal yearYield = ArithmeticUtil.multiply(yieldAverage, Day_365);
        // 市场基准日收益率均值
        BigDecimal basicYieldAverage = this.calcAverage(marketBaiscYieldList);
        //下行标准差
        BigDecimal stdDev = this.calcYearDownStdDev(yieldList);
        BigDecimal SortinoRatio = BigDecimal.ZERO;
        if (stdDev.compareTo(BigDecimal.ZERO) != 0) {
            //变量.年化索提诺比率 = （变量.年化日收益率 - 变量.市场基准日收益率均值）/变量.下行标准差*sqrt（365）
            SortinoRatio = ArithmeticUtil.multiply(
                    ArithmeticUtil.divide(ArithmeticUtil.subtract(yieldAverage, basicYieldAverage),
                            stdDev, 16),
                    BigDecimal.valueOf(Math.sqrt(Day_365.doubleValue())));
        }
        return SortinoRatio;
    }

//    /**
//     * 计算【收益目标】
//     *
//     * @param date 1.日期 2.组合代码3.层级
//     * @return 收益目标
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public List<PtlTargetReturnDto> calcTargetReturn(Date date, String portfolioId, String level) {
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("date", date);
//        paramMap.put("portfolioId", portfolioId);
//        paramMap.put("EVAL_INDEX_01", DictCodeList.评价指标_收益指标.getValue());
//        BigDecimal target = BigDecimal.ZERO;
//        List<PtlTargetReturnDto> resultList = new ArrayList<>();
//        // 收益目标查询
//        List<PtlTargetReturnDto> list = evalIndexAlgorithmMapper.queryTargetReturnList(
//                paramMap);
//
//        if (StringUtil.isEmptyList(list)) {
//            return resultList;
//        }
//        List<PtlTargetReturnDto> targetList = new ArrayList<>();
//        targetList.add(list.get(0));
//        for (PtlTargetReturnDto dto : targetList) {
//            if (StringUtil.equals(dto.getIncomeType(), DictCodeList.评价指标收益目标类型_固定.getValue())) {
//                target = dto.getIncomeTarget();
//            }
//            else if (StringUtil.equals(dto.getIncomeType(),
//                    DictCodeList.评价指标收益目标类型_浮动.getValue())) {
//                // 浮动收益目标查询
//                paramMap.put("effDate", date);
//                paramMap.put("indexId", dto.getIndexId());
//                paramMap.put("level", level);
//                List<PtlTargetReturnDto> floatTargetList = evalIndexAlgorithmMapper.queryFloatTargetReturnList(
//                        paramMap);
//
//                if (!StringUtil.isEmptyList(floatTargetList)) {
//                    //利差
//                    BigDecimal spreadRate = floatTargetList.get(0).getSpreadRate();
//
//                    for (PtlTargetReturnDto detail : floatTargetList) {
//                        target = ArithmeticUtil.doAdd(target,
//                                ArithmeticUtil.multiply(detail.getIntRate(), detail.getCoefficient()));
//                    }
//                    target = ArithmeticUtil.doAdd(target,spreadRate);
//                }
//            }
//            PtlTargetReturnDto result = new PtlTargetReturnDto();
//            result.setEffDate(date);
//            result.setPortfolioId(portfolioId);
//            result.setIndexId(dto.getIndexId());
//            result.setIncomeTarget(target);
//            resultList.add(result);
//        }
//        return resultList;
//    }

    /**
     * 查询【估值信息查询】列表
     */
//    public List<BokPositionPrd> getBokPositionPrdList(Map<String,Object> parMap){
//        return bokPositionPrdMapper.getBokPositionPrdList(parMap);
//    }
//    /**
//     * 查询某一天的估值信息
//     * @param parMap
//     * @return
//     */
//    public BokPositionPrd getBokPositionPrdOfDay(Map<String,Object> parMap){
//        return bokPositionPrdMapper.getBokPositionPrdOfDay(parMap);
//    }
//
//    /**
//     * 查询【基准日收益率列表】列表
//     */
//    public List<MstIntRateInfoDto> getMstIntRateInfoDtoList(Map<String,Object> parMap){
//        return mstIntRateInfoMapper.getMstIntRateInfoDtoList(parMap);
//    }
//
//    /**
//     * 查询【最近一天非空利率】
//     */
//    public BigDecimal nearistNotEmptyRateDayRate(Map<String,String> map) {
//        return mstIntRateInfoMapper.nearistNotEmptyRateDayRate(map);
//    }
//
//    /**
//     * 凭证最新账务查询
//     *
//     * @param booksetId 账套代码
//     * @param bookDate 入账日
//     * @param bokDetailId 账务明细代码
//     * @param baseSubjectNatureList 基础科目性质
//     * @param tableId 场景代码
//     * @param tradeId 交易编号
//     * @param happenDate 发生日
//     * @param onwayDate 在途日期
//     * @param beginHappenCycDate 发生周期开始日
//     * @param endHappenCycDate 发生周期结束日
//     * @return List<BookLatestInfoDto>
//     * @see
//     * @since 1.0
//     */
//    public List<BookLatestInfoDto> getBookLatestInfo(String booksetId, Date bookDate,
//                                                     String bokDetailId,
//                                                     List<String> baseSubjectNatureList,
//                                                     String tableId, String tradeId,
//                                                     Date happenDate, Date onwayDate,
//                                                     Date beginHappenCycDate,
//                                                     Date endHappenCycDate) {
//
//        // 设置sql查询参数map
//        Map<String, Object> paMap = new HashMap<String, Object>();
//        paMap.put("booksetId", booksetId);
//        paMap.put("bookDate", bookDate);
//        paMap.put("bokDetailId", bokDetailId);
//        paMap.put("baseSubjectNatureList", baseSubjectNatureList);
//        paMap.put("tableId", tableId);
//        paMap.put("tradeId", tradeId);
//        paMap.put("happenDate", happenDate);
//        paMap.put("onwayDate", onwayDate);
//        paMap.put("offsetFlag", DictCodeList.冲正标识_正常交易.getValue());
//        paMap.put("beginHappenCycDate", beginHappenCycDate);
//        paMap.put("endHappenCycDate", endHappenCycDate);
//
//        return accountingCommonMapper.getBookLatestInfo(paMap);
//    }
//
//    /**
//     * 计算类共通方法 --- 周期年化收益率
//     *
//     * @param bokPositionPrdList 帐套资产负债信息列表
//     * @param cycBeginDate 周期开始日
//     * @param cycEndDate 周期结束日
//     * @param bonusReinvestList 红利再投信息列表
//     * @param BasisDays 计算基础天数
//     * @param feeBefOrAft 费前费后区分 [参考枚举类：ValDictCodeList.费前费后区分_XX.getValue()]
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public BigDecimal getCysYearRate(List<BokPositionPrd> bokPositionPrdList, Date cycBeginDate,
//                                     Date cycEndDate, List<BookLatestInfoDto> bonusReinvestList, int BasisDays, String feeBefOrAft) {
//        // 定义 变量.周期年化收益率
//        BigDecimal cycYearRate = BigDecimal.ZERO;
//        // 定义 变量.周期万份收益
//        BigDecimal cycTenThousandProfit = BigDecimal.ZERO;
//        // 定义 变量.红利再投周期万份收益列表
//        List<BigDecimal> bonusRstCycTenThdProfitList = new ArrayList<BigDecimal>();
//
//        // 红利再投信息列表为空情况
//        if (bonusReinvestList == null || bonusReinvestList.size() == 0) {
//
//            // 循环账套资产负债信息
//            for (BokPositionPrd vo : bokPositionPrdList) {
//                // 变量.周期万份收益 = 变量.周期万份收益 + 资产负债信息.万份收益_费后
//                cycTenThousandProfit = ArithmeticUtil.doAdd(cycTenThousandProfit, vo.getTdyUnitValue());
//            }
//
//            // 周期开始日和结束日不为空情况
//            if (cycBeginDate != null && cycEndDate != null) {
//                // 周期开始日和结束日间隔天数
//                long betweenDay = DateUtil.getBetweenDayNumber(cycBeginDate, cycEndDate);
//                // 间隔天数+1，且转为BigDecimal
//                BigDecimal dayNum = ArithmeticUtil.doAdd(new BigDecimal(betweenDay), BigDecimal.ONE);
//
//                // 变量.周期年化收益率 = 变量.周期万份收益 /(参数.周期结束日 - 参数.周期开始日+1)* 参数.计算基础 /10000
//                cycYearRate = ArithmeticUtil.multiply(ArithmeticUtil.divide(cycTenThousandProfit, dayNum), // [变量.周期万份收益
//                        // /(参数.周期结束日
//                        // -
//                        // 参数.周期开始日+1)]
//                        ArithmeticUtil.divide(new BigDecimal(BasisDays), new BigDecimal(10000)) // [参数.计算基础
//                        // /10000]
//                );
//            }
//
//        } else {
//
//            // 循环红利再投信息
//            for (BookLatestInfoDto redVo : bonusReinvestList) {
//                // 定义当前红利再投层的红利再投周期万份收益列表值
//                BigDecimal bonusRstCycTenThdProfitNow = BigDecimal.ZERO;
//
//                // 循环账套资产负债信息
//                for (BokPositionPrd bokVo : bokPositionPrdList) {
//                    // 当资产负债信息的发生日 = 红利再投的发生日 的情况
//                    if (DateUtil.compareDate(bokVo.getCdate(),redVo.getHappenDate()) <= 0) {
//
//                        // 变量.红利再投周期万份收益列表[i] = 变量.红利再投周期万份收益列表[i]+资产负债信息[j].万份收益_费后/10000)
//                        // 即（累加红利再投周期万份收益列表当前塞入值:红利再投周期万份收益 = 红利再投周期万份收益 +
//                        // 资产负债信息.万份收益_费后/10000）
//                        bonusRstCycTenThdProfitNow = ArithmeticUtil.doAdd(bonusRstCycTenThdProfitNow,
//                                ArithmeticUtil.divide(bokVo.getTdyUnitValue(), new BigDecimal(10000)));
//                    }
//                }
//                // 变量.红利再投周期万份收益列表 塞入该值
//                bonusRstCycTenThdProfitList.add(bonusRstCycTenThdProfitNow);
//            }
//            // 定义当前红利再投层的红利再投周期万份收益列表值
//            BigDecimal bonusRstCycTenThdProfitNow = BigDecimal.ZERO;
//            // 循环账套资产负债信息
//            for (BokPositionPrd bokVo : bokPositionPrdList) {
//
//                // 变量.红利再投周期万份收益列表[i] = 变量.红利再投周期万份收益列表[i]+资产负债信息[j].万份收益_费后/10000)
//                // 即（累加红利再投周期万份收益列表当前塞入值:红利再投周期万份收益 = 红利再投周期万份收益 + 资产负债信息.万份收益_费后/10000）
//                bonusRstCycTenThdProfitNow = ArithmeticUtil.doAdd(bonusRstCycTenThdProfitNow,
//                        ArithmeticUtil.divide(bokVo.getTdyUnitValue(), new BigDecimal(10000)));
//            }
//            // 变量.红利再投周期万份收益列表 塞入该值
//            bonusRstCycTenThdProfitList.add(bonusRstCycTenThdProfitNow);
//            cycTenThousandProfit = BigDecimal.ONE;
//            // 遍历[变量.红利再投周期万份收益列表]，遍历0 - (size-2)
//            for (int index = 0; index <= bonusRstCycTenThdProfitList.size() - 1; index++) {
//
//                // 定义当前周期万分收益
//                BigDecimal cycTenThousandProfitNow = BigDecimal.ZERO;
//
//                // 若为第一个
//                if (index == 0) {
//                    // 当前周期万分收益 = 变量.红利再投周期万份收益列表.GET(index)
//                    cycTenThousandProfitNow = bonusRstCycTenThdProfitList.get(index);
//                } else {
//                    // 当前周期万分收益 = 变量.红利再投周期万份收益列表.GET(index+1) - 变量.红利再投周期万份收益列
//                    cycTenThousandProfitNow = ArithmeticUtil.doSub(bonusRstCycTenThdProfitList.get(index),
//                            bonusRstCycTenThdProfitList.get(index - 1));
//                }
//
//                // 变量.周期万份收益 = 1*（1+当前周期万分收益）
//                cycTenThousandProfit = ArithmeticUtil.multiply(cycTenThousandProfit,
//                        ArithmeticUtil.doAdd(BigDecimal.ONE, cycTenThousandProfitNow));
//            }
//
//            // 周期开始日和结束日不为空情况
//            if (cycBeginDate != null && cycEndDate != null) {
//                // 周期开始日和结束日间隔天数
//                long betweenDay = DateUtil.getBetweenDayNumber(cycBeginDate, cycEndDate);
//                // 间隔天数+1，且转为BigDecimal
//                BigDecimal dayNum = ArithmeticUtil.doAdd(new BigDecimal(betweenDay), BigDecimal.ONE);
//
//                // 变量.周期年化收益率 = （变量.周期万份收益-1）*10000 /(参数.周期结束日 - 参数.周期开始日+1)* 参数.计算基础 /10000
//                cycYearRate = ArithmeticUtil.multiply(ArithmeticUtil.doSub(cycTenThousandProfit, BigDecimal.ONE), // [（变量.周期万份收益-1）]
//                        ArithmeticUtil.divide(new BigDecimal(10000), dayNum), // [10000 /(参数.周期结束日 -
//                        // 参数.周期开始日+1)]
//                        ArithmeticUtil.divide(new BigDecimal(BasisDays), new BigDecimal(10000))// [参数.计算基础
//                        // /10000]
//                );
//            }
//
//        }
//
//        return cycYearRate;
//    }
//
//    /**
//     * 凭证最新账务查询公共方法 获取这个方法的参数
//     */
//    public List<BooksetAndBokDetailDto> getCysYearRateParam(Map<String,Object> paraMap) {
//        return accountingCommonMapper.getCysYearRateParam(paraMap);
//    }
//
//    /**
//     * 计算【平均策略执行有效性】
//     *
//     * @param startDate 1.开始日期 2.结束日期3.组合代码
//     * @return 平均策略执行有效性列表
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public List<AverageStrategyDto> calcAverageStrategy(Date startDate, Date endDate,
//                                                        String portfolioId) {
//
//        List<AverageStrategyDto> resultList = new ArrayList<>();
//        // 组合最新策略明细查询
//        List<PtlLatestStrategyDto> ptlLatestStrategyList = this.commonSearchMapper.queryPtlLatestStrategy(
//                endDate, portfolioId);
//
//       for (PtlLatestStrategyDto dto : ptlLatestStrategyList) {
//
//
//           // 变量.某一交易日组合持仓比例和
//           BigDecimal xDayPtlAmtSum = BigDecimal.ZERO;
//            // 投资策略明细分类查询
//            List<PtlInvStrategyDetailClass> detailClass = this.commonSearchMapper.queryDetailClass(
//                    dto.getBatchId());
//
//            for (int i = 0; i <= DateUtil.getBetweenDayNumber(startDate, endDate); i++ ) {
//                Map<String, Object> paramMap = new HashMap<>();
//                paramMap.put("portfolioId", portfolioId);
//                paramMap.put("F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
//                // 组合总持仓
//                BigDecimal ptlTotalAmt = BigDecimal.ZERO;
//                // 变量.某一交易日组合持仓
//                BigDecimal xDayPtlAmt = BigDecimal.ZERO;
//                paramMap.put("date", DateUtil.addDay(startDate, i));
//                // 组合资产估值查询
//                List<PtlAssetValDto> ptlAssetValList = this.commonSearchMapper.queryPtlAssetVal(
//                        paramMap);
//                for (PtlAssetValDto dtoPtl:ptlAssetValList) {
//                    ptlTotalAmt = ArithmeticUtil.doAdd(ptlTotalAmt, dtoPtl.getAmt());
//                }
//
//
//                for (PtlInvStrategyDetailClass pisdc : detailClass) {
//                    paramMap.put("type2", pisdc.getType2());
//                    paramMap.put("type3", pisdc.getType3());
//                    paramMap.put("type4", pisdc.getType4());
//                    paramMap.put("type5", pisdc.getType5());
//                    // 组合资产估值查询
//                    List<PtlAssetValDto> detaailPtlAssetValList = this.commonSearchMapper.queryPtlAssetVal(
//                            paramMap);
//                    if (detaailPtlAssetValList.size()>0){
//                        xDayPtlAmt = ArithmeticUtil.doAdd(xDayPtlAmt,
//                                detaailPtlAssetValList.get(0).getAmt());}
//                }
//               xDayPtlAmtSum = ArithmeticUtil.doAdd(xDayPtlAmtSum,ArithmeticUtil.divide(ArithmeticUtil.divide(xDayPtlAmt,ptlTotalAmt,16),dto.getRatio()));
//            }
//            AverageStrategyDto result = new AverageStrategyDto();
//            result.setBatchId(dto.getBatchId());
//            BigDecimal rangeDays = ArithmeticUtil.doAdd(
//                    new BigDecimal(DateUtil.getBetweenDayNumber(startDate, endDate)),
//                    BigDecimal.ONE);
//            result.setAvgAmtRatio(ArithmeticUtil.divide(xDayPtlAmtSum, rangeDays, 16));
//            resultList.add(result);
//        }
//        return resultList;
//    }
//
//    /**
//     * 计算【时点策略执行有效性】
//     *
//     * @param startDate 1.开始日期 2.结束日期3.组合代码
//     * @return 时点策略执行有效性列表
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public List<TimePointStrategyDto> calcTimePointStrategy(Date startDate, Date endDate,
//                                                            String portfolioId) {
//        List<TimePointStrategyDto> resultList = new ArrayList<>();
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("date", endDate);
//        paramMap.put("portfolioId", portfolioId);
//        paramMap.put("F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
//        // 组合最新策略明细查询
//        List<PtlLatestStrategyDto> ptlLatestStrategyList = this.commonSearchMapper.queryPtlLatestStrategy(
//                endDate, portfolioId);
//        // 组合资产估值查询
//        List<PtlAssetValDto> ptlAssetValList = this.commonSearchMapper.queryPtlAssetVal(paramMap);
//        // 组合总持仓
//        BigDecimal ptlTotalAmt = ptlAssetValList.stream().map(PtlAssetValDto::getAmt).reduce(
//                BigDecimal.ZERO, (a, b) -> ArithmeticUtil.doAdd(a, b));
//        for (PtlLatestStrategyDto dto : ptlLatestStrategyList) {
//            TimePointStrategyDto result = new TimePointStrategyDto();
//            // 投资策略明细分类查询
//            List<PtlInvStrategyDetailClass> detailClass = this.commonSearchMapper.queryDetailClass(
//                    dto.getBatchId());
//            // 变量.某一交易日组合持仓
//            BigDecimal xDayPtlAmt = BigDecimal.ZERO;
//            for (PtlInvStrategyDetailClass pisdc : detailClass) {
//                paramMap.put("type2", pisdc.getType2());
//                paramMap.put("type3", pisdc.getType3());
//                paramMap.put("type4", pisdc.getType4());
//                paramMap.put("type5", pisdc.getType5());
//                // 组合资产估值查询
//                List<PtlAssetValDto> detaailPtlAssetValList = this.commonSearchMapper.queryPtlAssetVal(
//                        paramMap);
//                if(detaailPtlAssetValList.size()>0){
//                xDayPtlAmt = ArithmeticUtil.doAdd(xDayPtlAmt,
//                        detaailPtlAssetValList.get(0).getAmt());}
//            }
//            result.setBatchId(dto.getBatchId());
//            result.setxDayPtlAmtRatio(ArithmeticUtil.divide(xDayPtlAmt, ptlTotalAmt, 16));
//            result.setTimePointEff(ArithmeticUtil.divide(result.getxDayPtlAmtRatio(), dto.getRatio()));
//            resultList.add(result);
//        }
//
//        return resultList;
//    }
//
//    /**
//     * 计算【资产收益贡献率】
//     *
//     * @param assetCost 1.资产净值 2.资产成本3.组合资产净值 4.组合资产成本
//     * @return 资产收益贡献率
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public BigDecimal calcAssetContributionRate(BigDecimal assetNetVal, BigDecimal assetCost,
//                                                BigDecimal ptlAssetNetVal,
//                                                BigDecimal ptlAssetCost) {
//        return ArithmeticUtil.divide(ArithmeticUtil.subtract(assetNetVal, assetCost),
//                ArithmeticUtil.subtract(ptlAssetNetVal, ptlAssetCost), 16);
//    }
//
//    /**
//     * 计算【业绩基准计算】
//     *
//     * @param startDate 1.区间开始日2.区间结束日3.金融产品代码 4.分支序号5.层级
//     * @return 业绩基准计算
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public List<BenchmarkDto> calcBenchmark(Date startDate, Date endDate, String finprodId,
//                                            String branchId, String level) {
//        List<BenchmarkDto> resultList = new ArrayList<>();
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("finprodId", finprodId);
//        paramMap.put("branchId", branchId);
//        paramMap.put("BASE_RULE_TYPE_01", DictCodeListExt1.业绩基准类型_产品披露.getValue());
//        for (int i = 0; i <= DateUtil.getBetweenDayNumber(startDate, endDate); i++ ) {
//            paramMap.put("effDate", DateUtil.addDay(startDate, i));
//            FinBaseRuleInfo finBaseRuleInfo = this.evalIndexAlgorithmMapper.queryLatestBenchmark(
//                    paramMap);
//            BenchmarkDto dto = new BenchmarkDto();
//            if (finBaseRuleInfo != null) {
//                if (StringUtil.equals(finBaseRuleInfo.getPublishType(),
//                        DictCodeListExt1.披露类型_区间披露.getValue())) {
//                    dto.setLowestBen(finBaseRuleInfo.getRateLower());
//                }
//                else if (StringUtil.equals(finBaseRuleInfo.getPublishType(),
//                        DictCodeListExt1.披露类型_单值披露.getValue())) {
//                    if (StringUtil.equals(finBaseRuleInfo.getRateType(),
//                            DictCodeList.利率类型_固定利率.getValue())) {
//                        dto.setLowestBen(finBaseRuleInfo.getRate());
//                    }
//                    else if (StringUtil.equals(finBaseRuleInfo.getRateType(),
//                            DictCodeList.利率类型_浮动利率.getValue())) {
//                        Map<String,Object> params = new HashMap<>();
//                        params.put("effDate",DateUtil.addDay(startDate, i));
//                        params.put("intRateId",finBaseRuleInfo.getIndexId());
//                        List<BigDecimal> floatTargetList = this.evalIndexAlgorithmMapper.queryFloatBenchmark(
//                                params);
//                        BigDecimal lowestBen = BigDecimal.ZERO;
//                        if (!StringUtil.isEmptyList(floatTargetList)) {
//                            for (BigDecimal detail : floatTargetList) {
//                                lowestBen = ArithmeticUtil.doAdd(lowestBen,
//                                        ArithmeticUtil.multiply(detail,
//                                                finBaseRuleInfo.getCoefficient()),
//                                        finBaseRuleInfo.getSpreadRate());
//                            }
//                        }
//                        dto.setLowestBen(ArithmeticUtil.divide(lowestBen,floatTargetList.size()));
//                    }
//                }
//                dto.setEffDate(DateUtil.addDay(startDate, i));
//                dto.setFinprodId(finprodId);
//                dto.setBranchId(branchId);
//                resultList.add(dto);
//            }
//        }
//        return resultList;
//    }
//
//    /**
//     * 分档产品查询
//     */
//    public List<StepProducts> fileProductQuery(String finprodId){
//        return accountingCommonMapper.fileProductQuery(finprodId);
//    }
//
//    /**
//     * 子组合查询
//     */
//    public List<ChildCombination> subcombinedQuery(String financialProductCode){
//        return accountingCommonMapper.subcombinedQuery(financialProductCode);
//    }
//
//    /**
//     * 子组合收益资金占比计算
//     */
//    public List<SubportfolioIncomeCapitalRatio> subportfolioIncomeCapitalRatio(Map<String,Object> paraMap){
//        return accountingCommonMapper.subportfolioIncomeCapitalRatio(paraMap);
//    }
//
//    public List<SubportfolioIncomeCapitalRatio> queryShareAmts(Map<String,Object> paraMap){
//        return accountingCommonMapper.queryShareAmts(paraMap);
//    }
    
    /**********************************更新算法********************************************/
    
    /**
     * 计算【年化特雷诺比率】
     *
     * @param yieldList 1.区间日收益率列表 2.区间基准日收益率列表 3.区间市场基准日收益率列表
     * @return 年化特雷诺比率
     * @see
     * @since 1.0
     */
    public BigDecimal calcTreynorRatio(List<DailyYieldDto> yieldList,
                                       List<DailyYieldDto> standardList,
                                       List<DailyYieldDto> marketBaiscYieldList,BigDecimal days) {
        BigDecimal β = this.calcβ(yieldList,standardList, marketBaiscYieldList);

        BigDecimal TreynorRatio = BigDecimal.ZERO;
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);
        // 市场基准日收益率均值
        BigDecimal basicYieldAverage = this.calcAverage(marketBaiscYieldList);

        if (β.compareTo(BigDecimal.ZERO) != 0) {
            TreynorRatio = ArithmeticUtil.multiply(ArithmeticUtil.divide(
                    ArithmeticUtil.subtract(yieldAverage, basicYieldAverage), β, 16), days);
        }
        return TreynorRatio;
    }
    
    /**
     * 计算【年化索提诺比率】
     *
     * @param yieldList 1.区间日收益率列表 2.区间市场基准日收益率列表
     * @return 年化索提诺比率
     * @see
     * @since 1.0
     */
    public BigDecimal calcSortinoRatio(List<DailyYieldDto> yieldList,
                                       List<DailyYieldDto> marketBaiscYieldList,BigDecimal days) {
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);
        // 年化日收益率
        // BigDecimal yearYield = ArithmeticUtil.multiply(yieldAverage, Day_365);
        // 市场基准日收益率均值
        BigDecimal basicYieldAverage = this.calcAverage(marketBaiscYieldList);
        //下行标准差
        BigDecimal stdDev = this.calcYearDownStdDev(yieldList);
        BigDecimal SortinoRatio = BigDecimal.ZERO;
        if (stdDev.compareTo(BigDecimal.ZERO) != 0) {
            //变量.年化索提诺比率 = （变量.年化日收益率 - 变量.市场基准日收益率均值）/变量.下行标准差*sqrt（365）
            SortinoRatio = ArithmeticUtil.multiply(
                    ArithmeticUtil.divide(ArithmeticUtil.subtract(yieldAverage, basicYieldAverage),
                            stdDev, 16),
                    BigDecimal.valueOf(Math.sqrt(days.doubleValue())));
        }
        return SortinoRatio;
    }
    
    /**
     * 计算【年化夏普比】
     *
     * @param yieldList 1.区间日收益率列表 2.区间市场基准日收益率列表
     * @return 年化夏普比
     * @see
     * @since 1.0
     */
    public BigDecimal calcSharpeRatio(List<DailyYieldDto> yieldList,
                                      List<DailyYieldDto> marketBaiscYieldList,BigDecimal days) {
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);
        // 年化日收益率
        // BigDecimal yearYield = ArithmeticUtil.multiply(yieldAverage, Day_365);
        // 市场基准日收益率均值
        BigDecimal basicYieldAverage = this.calcAverage(marketBaiscYieldList);
        // 超额收益率标准差
        BigDecimal stdDev = this.calcExtraYield(yieldList, marketBaiscYieldList);

        // 变量.年化夏普比 = (变量.日收益率均值 -变量.市场基准日收益率均值)/变量.超额收益率标准差*sqrt(365)
        BigDecimal SharpeRadio = ArithmeticUtil.multiply(
                ArithmeticUtil.divide(ArithmeticUtil.subtract(yieldAverage, basicYieldAverage), stdDev,
                        16),
                BigDecimal.valueOf(Math.sqrt(days.doubleValue())));

        return SharpeRadio;
    }
    
    /**
     * 计算【詹森指数】
     *
     * @param yieldList 1.区间日收益率列表 2.区间基准日收益率列表 3.区间市场基准日收益率列表
     * @return 詹森指数
     * @see
     * @since 1.0
     */
    public BigDecimal calcJensenIndex(List<DailyYieldDto> yieldList,
                                      List<DailyYieldDto> standardList,
                                      List<DailyYieldDto> marketBaiscYieldList,BigDecimal days) {
        // 日收益率均值
        BigDecimal yieldAverage = this.calcAverage(yieldList);

        //基准日收益率均值
        BigDecimal standardAvg = this.calcAverage(standardList);

        // 市场基准日收益率均值
        BigDecimal basicYieldAverage = this.calcAverage(marketBaiscYieldList);

        // 计算β系数
        BigDecimal β = this.calcβ(yieldList,standardList, marketBaiscYieldList);

        // 初始化詹森指数
        BigDecimal jensenIndex = BigDecimal.ZERO;

        jensenIndex = ArithmeticUtil.multiply(
                ArithmeticUtil.subtract(yieldAverage,
                        ArithmeticUtil.doAdd(basicYieldAverage,
                                ArithmeticUtil.multiply(β, ArithmeticUtil.subtract(standardAvg, basicYieldAverage)))),
                days);

        return jensenIndex;
    }
    
    /**
     * 计算IR
     * @param dyRList
     * @param dyBRList
     * @return
     */
    public BigDecimal calcIR(List<DailyYieldDto> dyRList,
            List<DailyYieldDto> dyBRList){
    	//计算超额收益
    	List<DailyYieldDto> excessReturnList = calcExcessReturn(dyRList,dyBRList);
    	
    	BigDecimal avgExcessReturn = this.calcAverage(excessReturnList);
    	
    	BigDecimal stdValue = standardDeviation(excessReturnList,avgExcessReturn);
    	//IR= 超额收益均值/差额收益标准差
    	return  ArithmeticUtil.divide(avgExcessReturn, stdValue, 16);
    }
    
    
    public BigDecimal calcTrack(List<DailyYieldDto> dyRList,
            List<DailyYieldDto> dyBRList){
    	
    	BigDecimal track = BigDecimal.ZERO;
    	//计算偏离度序列
    	List<DailyYieldDto> deviationDegreeList = deviationDegree(dyRList,dyBRList);
    	//计算偏离度均值
    	BigDecimal avgValue = this.calcAverage(deviationDegreeList);
    	
    	track = standardDeviation(deviationDegreeList,avgValue);
    	
    	return track;
    	
    }
    
    /**
     * 计算超额收益
     * @return
     */
    public List<DailyYieldDto> calcExcessReturn(List<DailyYieldDto> dyRList,
            List<DailyYieldDto> dyBRList){
    	
    	List<DailyYieldDto> excessReturnList = new ArrayList<DailyYieldDto>();
    	
    	for(int i = 0 ; i < dyRList.size() ;i++ ){
    		DailyYieldDto dto = new DailyYieldDto();
    		
    		dto.setCdate(dyRList.get(i).getCdate());
    		
    		BigDecimal rYield = dyRList.get(i).getYield()==null?BigDecimal.ZERO:dyRList.get(i).getYield();
    		
    		BigDecimal brYield = dyBRList.get(i).getYield()==null?BigDecimal.ZERO:dyBRList.get(i).getYield();
    		
    		dto.setYield(rYield.subtract(brYield));
    		
    		excessReturnList.add(dto);
    	}
   
    	return excessReturnList;
    }
    
    /**
     * 计算标准差算法
     * @param list
     * @param avgValue
     * @return
     */
    public BigDecimal standardDeviation(List<DailyYieldDto> list,BigDecimal avgValue){
    	
    	BigDecimal downStdDev = BigDecimal.ZERO;
        for (DailyYieldDto temp : list) {
            BigDecimal sub = ArithmeticUtil.subtract(temp.getYield(), avgValue);
            downStdDev = downStdDev.add(ArithmeticUtil.multiply(sub, sub));
        }
        BigDecimal divide = ArithmeticUtil.divide(downStdDev, (list.size() - 1), 16);
        double v = divide.doubleValue();
        double sqrt = Math.sqrt(v);
        downStdDev = BigDecimal.valueOf(sqrt);
        return downStdDev;
    }
    
    /**
     * 计算偏离度序列
     * @param dyRList
     * @param dyBRList
     * @return
     */
    public List<DailyYieldDto> deviationDegree(List<DailyYieldDto> dyRList,
            List<DailyYieldDto> dyBRList){
    	List<DailyYieldDto> tdList = new ArrayList<DailyYieldDto>();
    	
    	for(int i = 0 ; i < dyRList.size() ;i++ ){
    		DailyYieldDto dto = new DailyYieldDto();
    		
    		dto.setCdate(dyRList.get(i).getCdate());
    		
    		BigDecimal rYield = dyRList.get(i).getYield()==null?BigDecimal.ZERO:dyRList.get(i).getYield();
    		
    		BigDecimal brYield = dyBRList.get(i).getYield()==null?BigDecimal.ZERO:dyBRList.get(i).getYield();
    		
    		if(brYield.compareTo(BigDecimal.ZERO) == 0){
    			dto.setYield(BigDecimal.ZERO);
    		}else {
    			dto.setYield(ArithmeticUtil.divide(rYield.subtract(brYield), brYield, 16));
    		}
    		
    		tdList.add(dto);
    	}
    	
    	return tdList;
    	
    }
}
