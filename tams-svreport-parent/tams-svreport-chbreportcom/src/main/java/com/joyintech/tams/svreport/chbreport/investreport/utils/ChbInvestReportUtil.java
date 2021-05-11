package com.joyintech.tams.svreport.chbreport.investreport.utils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
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

    /**
     * 处理客户类型
     * @param customerType
     * @return
     */
    public static String dealCustomerType(String customerType) {
        if(StringUtil.isEmpty(customerType) || StringUtil.equals(DictCodeListExt3.人行客户类型_单位及个人.getValue(), customerType)) {
            customerType = DictCodeListExt3.人行客户类型_个人.getValue();
        }
        return getDictCodeAndName(DictCode.人行客户类型, customerType);
    }
    /**
     * 根据报告类型及相关参数生成文件名称
     * @param repId
     * @param prodName
     * @param prodId
     * @param customerType
     * @param endDate 这里格式为2021-05-05
     * @return
     */
    public static String generateFileNameForReport(String repId, String prodName, String prodId, String customerType,
                                                   String endDate) {
        String fileName = null;
        if (StringUtil.equals(DictCodeListExt3.中债披露报告_成立公告.getValue(), repId)) {

            //产品名称[产品代码][销售对象]成立公告YYYYMMDD-19.doc
            fileName = prodName + ChbInvestReportConstant.左中括号 + prodId + ChbInvestReportConstant.右中括号
                    + ChbInvestReportConstant.左中括号 + dealCustomerType(customerType) + ChbInvestReportConstant.右中括号
                    + ChbInvestReportConstant.成立公告 + DateUtil.format(DateUtil.parse(endDate), DateUtil.DEFAULT_OTHER_FORMAT)
                    + ChbInvestReportConstant.华夏文件后缀 + ChbInvestReportConstant.WORD文档;
        }
        else if(StringUtil.equals(DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue(), repId)) {
            //产品名称[产品代码][销售对象]运行报告YYYYMMDD-19.doc
            fileName = prodName + ChbInvestReportConstant.左中括号 + prodId + ChbInvestReportConstant.右中括号
                    + ChbInvestReportConstant.左中括号 + dealCustomerType(customerType) + ChbInvestReportConstant.右中括号
                    + ChbInvestReportConstant.运行报告 + DateUtil.format(DateUtil.parse(endDate), DateUtil.DEFAULT_OTHER_FORMAT)
                    + ChbInvestReportConstant.华夏文件后缀 + ChbInvestReportConstant.WORD文档;
        }
        else if(StringUtil.equals(DictCodeListExt3.中债披露报告_到期公告.getValue(), repId)) {
            //产品名称[产品代码][销售对象]到期报告YYYYMMDD-19.doc
            fileName = prodName + ChbInvestReportConstant.左中括号 + prodId + ChbInvestReportConstant.右中括号
                    + ChbInvestReportConstant.左中括号 + dealCustomerType(customerType) + ChbInvestReportConstant.右中括号
                    + ChbInvestReportConstant.到期报告 + DateUtil.format(DateUtil.parse(endDate), DateUtil.DEFAULT_OTHER_FORMAT)
                    + ChbInvestReportConstant.华夏文件后缀 + ChbInvestReportConstant.WORD文档;
        }
        else if(StringUtil.equals(DictCodeListExt3.中债披露报告_产品净值披露.getValue(), repId)) {
            //产品名称[产品代码][销售对象]净值报告YYYYMMDD.xls
            fileName = prodName + ChbInvestReportConstant.左中括号 + prodId + ChbInvestReportConstant.右中括号
                    + ChbInvestReportConstant.左中括号 + dealCustomerType(customerType) + ChbInvestReportConstant.右中括号
                    + ChbInvestReportConstant.净值报告 + DateUtil.format(DateUtil.parse(endDate), DateUtil.DEFAULT_OTHER_FORMAT)
                    + ChbInvestReportConstant.XLS文档;
        }
        return fileName;
    }

    /**
     * 获取定期报告的文件名称
     * @param prodNameList
     * @param reportType
     * @param year
     * @return
     */
    public static String generateFileNameForDQ(List<String> prodNameList, String reportType,
                                                   String year) {
        //产品名A_产品名B_产品名C+YYYY+报表类型+运行报告.doc
        String fileName = StringUtil.joinString(prodNameList, ChbInvestReportConstant.下划线)
                + year + getDictCodeAndName(DictCode.中债定期报告类型, reportType)
                + ChbInvestReportConstant.运行报告 + ChbInvestReportConstant.WORD文档;
        return fileName;
    }
}
