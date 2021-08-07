package com.joyintech.tams.model.valuation.batch.calculation.util;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.IsoChronology;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 基础计算工具类
 * @author zhangnian
 * @date 2021/7/1
 */
public class BasicUtil {



    private static final String EMPTY_STR = "";

    public static final boolean isNull(final Object args) {
        return args == null || args.toString().equals(EMPTY_STR);
    }

    public static final boolean isNotNull(final Object args) {
        return !isNull(args);
    }

    public static final boolean equals(final String src, final String target) {
        if (null == src && null == target) {
            return true;
        }
        else if (null != src) {
            return src.equals(target);
        }
        else if (null != target) {
            return target.equals(src);
        }
        return false;
    }

    /**
     * 判断两个日期的大小，date1是否在date2之前
     * @param date1 日期
     * @param date2 日期
     * @return 比较结果
     */
    public static final boolean dateBefore(LocalDate date1, LocalDate date2) {
        if(null != date1 && null != date2) {
            return date1.isBefore(date2);
        }
        return false;
    }

    /**
     * 判断两个日期的大小，date1是否在date2之后（包含相等）
     * @param date1 日期
     * @param date2 日期
     * @return 比较结果
     */
    public static final boolean dateAfter(LocalDate date1, LocalDate date2) {
        if(null != date1 && null != date2) {
            return date1.isAfter(date2) || date1.isEqual(date2);
        }
        return false;
    }

    /**
     * 判断两个日期的大小，date1是否和date2相等
     * @param date1 日期
     * @param date2 日期
     * @return 比较结果
     */
    public static final boolean dateEqual(LocalDate date1, LocalDate date2) {
        if(null != date1 && null != date2) {
            return date1.isEqual(date2);
        }
        return false;
    }

    /**
     * 获取两个日期之间相差的天数的绝对值
     *
     * @param date1
     * @param date2
     * @return 相差天数
     */
    public static long days(LocalDate date1, LocalDate date2) {
        return Math.abs(date1.toEpochDay() - date2.toEpochDay());
    }

    /**
     * 判断日期是否在某个区间内
     *
     * @param src 被判断的日期
     * @param start 开始日
     * @param end 结束日
     * @return 是否
     */
    public static boolean in(LocalDate src, LocalDate start, LocalDate end) {
        return (src.isEqual(start) || src.isAfter(start))
                && (/*src.isEqual(end) || */src.isBefore(end));
    }

    /**
     * 将java.util.Date转换为java.time.LocalDate
     *
     * @param date java.util.Date
     * @return java.time.LocalDate
     */
    public static LocalDate dateToLocal(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 计算2个日期之间包含2月29日的次数（不包含结束日当天）
     *
     * @param start 开始日期
     * @param end 结束日期
     * @return 包含2月29日的次数
     */
    public static int get2M29DBetween(LocalDate start, LocalDate end) {
        int count = 0;
        // 非开始和结束年之间的年
        int startYear = start.getYear() + 1;
        while (startYear < end.getYear()) {
            if (IsoChronology.INSTANCE.isLeapYear(startYear++ )) {
                count++ ;
            }
        }
        // 如果开始日期和结束日期不是同一年（开始日期小于结束日期）
        if (start.getYear() < end.getYear()) {
            // 开始日期是否为2月29日
            if (start.isLeapYear()) {
                int month = start.getMonthValue();
                if (month < 2 || (month == 2 && start.getDayOfMonth() <= 29)) {
                    count++ ;
                }
            }
            // 结束日期是否包含2月29日（不包含结束日当天）
            if (end.isLeapYear()) {
                int month = end.getMonthValue();
                if (month >= 3) {
                    count++ ;
                }
            }
        }
        // 始日期和结束日期为同一年
        else {
            if (start.isLeapYear() && end.getMonthValue() >= 3
                    && (start.getMonthValue() < 2
                    || (start.getMonthValue() == 2 && start.getDayOfMonth() <= 29))) {
                count++ ;
            }
        }
        return count;
    }



    /**
     * 获取日期所在年份的自然天数
     * @param date
     */
    public static long yearDays(LocalDate date) {
        int year = date.getYear();
        LocalDate date_first = LocalDate.parse(year+"-01-01");
        LocalDate date_last = LocalDate.parse(year+"-12-31");
        return Math.abs(date_last.toEpochDay() - date_first.toEpochDay())+1;
    }
    /**
     * 获取日期所在年份下一年份的第1天
     * @param date
     * @return
     */
    public static LocalDate getNextYearFirstDay(LocalDate date){
        return LocalDate.parse((date.getYear()+1)+"-01-01");
    }

    /**
     * 将java.util.Date转换为java.time.LocalDate
     *
     * @param date java.util.Date
     * @return java.time.LocalDate
     */
    public static Date localToDate(LocalDate date) {
        if (null == date) {
            return null;
        }
        return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 二分法计算点差
     * @param target
     * @param function
     * @return
     */
    public static BigDecimal dichotomy(BigDecimal target, BigDecimal errorValue,int calTimes,
                                       Function<BigDecimal, BigDecimal> function) {
        //计算误差值
        BigDecimal precision = null!=errorValue?errorValue:new BigDecimal(
                String.valueOf(EngineContext.getInstance().getConfig().dicPrecision()));
        //计算次数
        int totalTimes = 0!=calTimes?calTimes: EngineContext.getInstance().getConfig().dicTotalTimes();
        // 低的起始金额
        BigDecimal tempAmountLow = new BigDecimal(
                String.valueOf(EngineContext.getInstance().getConfig().dicStartAmountLow()));
        // 高的起始金额
        BigDecimal tempAmountHigh = new BigDecimal(
                String.valueOf(EngineContext.getInstance().getConfig().dicStartAmountHigh()));
        // 折中的起始金额
        BigDecimal tempAmount = ArithmeticUtil.divide(
                ArithmeticUtil.doAdd(tempAmountLow, tempAmountHigh), 2);
        // 中间价
        BigDecimal tempPrice = function.apply(tempAmount);
        int counter = 0;
        // 循环只到计算小于精度或者超出最大次数
        while (precision.compareTo(ArithmeticUtil.subtract(tempPrice, target).abs()) < 0
                && counter < totalTimes) {
            if (tempPrice.compareTo(target) > 0) {
                tempAmountLow = tempAmount;
            }
            else {
                tempAmountHigh = tempAmount;
            }
            //中间量
            tempAmount = ArithmeticUtil.divide(ArithmeticUtil.doAdd(NumberEnum.ALL_PRECISION.getCode(), RoundingMode.HALF_UP,tempAmountLow, tempAmountHigh),2, NumberEnum.ALL_PRECISION.getCode());
            tempPrice = function.apply(tempAmount);
            counter++ ;
        }
        return tempAmount;
    }

    /**
     *通过现金流R值计算价格
     * @param cashFlow
     * @param term
     * @param spotR
     * @param amount
     * @return
     */
    public static BigDecimal calPriceByR(BigDecimal[] cashFlow,
                                                       BigDecimal[] term,
                                                       BigDecimal[] spotR,BigDecimal amount) {
        BigDecimal price = BigDecimal.ZERO;
        for (int i = 0; i < cashFlow.length; i++ ) {
            BigDecimal tmpPrice = ArithmeticUtil.divide(cashFlow[i],
                    new BigDecimal(String.valueOf(
                            Math.pow(ArithmeticUtil.doAdd(BigDecimal.ONE,spotR[i], amount).doubleValue(),
                                    term[i].doubleValue()))));
            price = ArithmeticUtil.doAdd(tmpPrice, price);
        }
        return price;
    }

    /**
     * 到期点差中间过程
     * @param cashFlow
     * @param matYield
     * @param
     * @return
     */
    public static BigDecimal calPriceByMatYield(List<CashInfoDto>  cashFlow, BigDecimal matYield, int yearIntRate, BigDecimal spread) {
        BigDecimal price = BigDecimal.ZERO;
        for (int i = 0; i < cashFlow.size(); i++ ) {
            CashInfoDto cashInfoDto = cashFlow.get(i);
            BigDecimal tmpPrice = ArithmeticUtil.divide(cashInfoDto.getCashAmt(),
                    new BigDecimal(String.valueOf(
                            Math.pow(ArithmeticUtil.doAdd(BigDecimal.ONE, ArithmeticUtil.divide(ArithmeticUtil.doAdd(matYield, spread),new BigDecimal(yearIntRate))).doubleValue(),
                                    ArithmeticUtil.multiply(cashInfoDto.getRemainingTerm(),new BigDecimal(yearIntRate)).doubleValue()))));
            price = ArithmeticUtil.doAdd(tmpPrice, price);
        }
        return price;
    }


    /**
     * 即期点差中间过程
     * @param cashFlow
     * @param
     * @return
     */
    public static BigDecimal calPriceByMatYield2(List<CashInfoDto>  cashFlow, BigDecimal spread) {
        BigDecimal price = BigDecimal.ZERO;
        /**现金流金额/pow(1+曲线收益率+点差,剩余期限)**/
        for(int i=0;i<cashFlow.size();i++){
            CashInfoDto cashInfoDto = cashFlow.get(i);
            BigDecimal powa = ArithmeticUtil.doAdd(BigDecimal.ONE,cashInfoDto.getSpR(),spread);
            BigDecimal powb = cashInfoDto.getRemainingTerm().negate();
            price = ArithmeticUtil.doAdd(EngineContext.getInstance().getConfig().getAllPrecision(),RoundingMode.HALF_UP,
                    price,
                    ArithmeticUtil.multiply(cashInfoDto.getCashAmt(),
                            BigDecimal.valueOf(Math.pow(powa.doubleValue(), powb.doubleValue()))
                    )
            );
        }
        return price;
    }

    /**
     * 现金流信息转换
     * @param cashInfoDtos
     * @return
     */
    public static List<ModCashFlowDto>  constructArr(List<ModCashFlowDto> cashInfoDtos){
        List<ModCashFlowDto> newCashInfo = new ArrayList<>();
        //同频合并
        Map<Date, List<ModCashFlowDto>> collect = cashInfoDtos.stream().collect(Collectors.groupingBy(ModCashFlowDto::getMdate));
        for (Map.Entry<Date, List<ModCashFlowDto>> entry : collect.entrySet()) {
            ModCashFlowDto item = new ModCashFlowDto();
            List<ModCashFlowDto> cash = entry.getValue();
            BigDecimal bigDecimal = cash.stream().map(ModCashFlowDto::getCashAmt).reduce((x, y) -> ArithmeticUtil.doAdd(x, y)).get();
            ModCashFlowDto modCashFlowDto = cash.get(0);
            BeanUtil.copyPropertiesIgnoreNull(item, modCashFlowDto);
            item.setCashAmt(bigDecimal);
            newCashInfo.add(item);
        }
        //排序
        newCashInfo.sort(new Comparator<ModCashFlowDto>() {
            @Override
            public int compare(ModCashFlowDto o1, ModCashFlowDto o2) {
                return DateUtil.compareDate(o1.getMdate(),o2.getMdate());
            }
        });
        return newCashInfo;
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date getDateOfLocalDate (LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

