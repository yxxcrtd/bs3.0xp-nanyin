package com.joyintech.tams.svreport.chbreport.investreport.utils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ChbInvestReportUtil
 * @Description: 投资报告相关util
 * @Author yanya
 * @Date 2021-05-06
 * @Version V1.0
 **/
public class ChbInvestReportUtil {

    /**
     * 获取中债格式化后的数据字典（数据字典名称） 如果传过来的是用“,”分割的字符串，则得到用“,”分割的格式化后的数据字典
     *
     * @param dictCode 数据字段
     * @param code 数据字典明细代码
     * @return 格式化后的数据
     * @see
     * @since 1.0
     */
    public static String getDictCodeAndName(DictCode dictCode, final String code) {
        if (StringUtil.isEmpty(code)) {
            return "";
        }
        List<String> codeList = StringUtil.splitToList(GlobalConstant.COMMA, code);
        List<String> nameList = new ArrayList<>();
        for (String string : codeList) {
            String name = SysDataUtil.getNameFromDataDict(dictCode, string);
            if (StringUtil.isNotEmpty(name)){
                nameList.add(name);
            }
        }
        return StringUtil.joinString(nameList, GlobalConstant.COMMA);
    }

    /**
     * 根据两个日期，取得相隔的天数
     * @param d1 开始日期
     * @param d2 结束日期
     * @return 天数
     */
    public static BigDecimal getBetweenDayNumber(Date d1, Date d2) {
        LocalDate start = new LocalDate(d1);
        LocalDate end = new LocalDate(d2);
        int days = Days.daysBetween(start, end).getDays();
        return new BigDecimal(days);
    }

}
