/*
 * 文件名：ValProfitQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valprofitquery.service;

import com.google.common.collect.Lists;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dao.ValProfitQuery001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.BokBalanceDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ProfitCalculationDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ValProfitQuery001Dto;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 利润表service〉
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitQuery001Service
 * @since 1.0
 */
@Service(value="serviceValProfitQuery001Service")
public class ValProfitQuery001Service extends CommonExportService<ValProfitQuery001Dto> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValProfitQuery001Service.class);
    
    /**
     * 利润表查询mapper
     */
    @Autowired
    private ValProfitQuery001Mapper valProfitQuery001Mapper;

    /**
     *  html 样式
     */
    private static final String HTML_STYLE = "HTML_STYLE";
    
    /**
     * excel 样式
     */
    private static final String EXCEL_STYLE = "EXCEL_STYLE";
    
    /**
     * 通用联动查询
     * 
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        return valProfitQuery001Mapper.getLinkSelectOption(param);
    }

    /**
     * 分析余额日期
     * 
     * @param pageParam 查询条件
     * @param balanceType 期末、年初
     * @return
     * @throws ParseException
     */
    public Date analyseDate(Map<String, Object> pageParam)
        throws ParseException {

        String reportTermType = (String)pageParam.get("reportTermType");
        String reportDate = (String)pageParam.get("reportDate");
        String reportTerm = (String)pageParam.get("reportTerm");

        int year = 0;
        int month = -1;
        int day = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
        calendar.set(Calendar.MINUTE, 0);// 分
        calendar.set(Calendar.SECOND, 0);// 秒
        calendar.set(Calendar.MILLISECOND, 0);// 毫秒

        String[] strArr = reportDate.split(",")[0].split("-");
        if (DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)) {// 年底
        	//update yanya 20190912 年报原控件类型yyyy更为到天yyyy-MM-dd
            //year = Integer.valueOf(strArr[0]);
            //month += 12;
            //day = 31;
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]);
            day = Integer.valueOf(strArr[2]);
        }
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.半年_上半年.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            }
            else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.季度_第一季度.getValue().equals(reportTerm)) {
                month += 3;
                day = 31;
            }
            else if (DictCodeListExt1.季度_第二季度.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            }
            else if (DictCodeListExt1.季度_第三季度.getValue().equals(reportTerm)) {
                month += 9;
                day = 30;
            }
            else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)) {// 月底：当前月的下个月的第0天
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]) + 1;
            day = 0;
        }
        if (DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]);
            day = Integer.valueOf(strArr[2]);
        }

        calendar.set(year, month, day);
        return calendar.getTime();

    }

    /**
     * 设置页面传回参数
     * 
     * @param pageParam
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public void changeMapData(Map<String, Object> pageParam)
        throws ParseException {
        String reportTermType = (String)pageParam.get("reportTermType");
        String valDate = DateUtil.format(analyseDate(pageParam), DateUtil.DEFAULT_DATE_FORMAT_EN);
        // 上期维度
        String lastDimen = (String)pageParam.get("lastDimen");
        int thisYear = DateUtil.getYear(valDate);
        int lastYear = thisYear - 1;
        String reportTerm = (String)pageParam.get("reportTerm"); // 期限明细
        Date now = DateUtil.parse(valDate);
        if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_日报.getValue())) {
            pageParam.put("thisTermVdate", valDate);
            pageParam.put("thisTermMdate", valDate);
            if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                pageParam.put("lastTermVdate", DateUtil.format(DateUtil.addYear(now, -1)));
                pageParam.put("lastTermMdate", DateUtil.format(DateUtil.addYear(now, -1)));
            }
            else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                pageParam.put("lastTermVdate", DateUtil.addDay(valDate, -1));
                pageParam.put("lastTermMdate", DateUtil.addDay(valDate, -1));
            }

        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_月报.getValue())) {
            pageParam.put("thisTermVdate", DateUtil.format(
                DateUtil.getFirstDay(DateUtil.parse(valDate)), DateUtil.DEFAULT_DATE_FORMAT_EN));
            pageParam.put("thisTermMdate", DateUtil.format(
                DateUtil.getLastDay(DateUtil.parse(valDate)), DateUtil.DEFAULT_DATE_FORMAT_EN));
            if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                // 查询上期开始日 = 画面.日期-1年的月初
                pageParam.put("lastTermVdate",
                    DateUtil.format(DateUtil.getFirstDay(DateUtil.addYear(now, -1))));
                // 查询上期结束日 = 画面.日期-1年的月末
                pageParam.put("lastTermMdate",
                    DateUtil.format(DateUtil.getLastDay(DateUtil.addYear(now, -1))));
            }
            else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                // 查询上期开始日 = 画面.日期-1月的月初
                pageParam.put("lastTermVdate",
                    DateUtil.format(DateUtil.getFirstDay(DateUtil.addMonthDate(now, -1))));
                // 查询上期结束日 = 画面.日期-1月的月末
                pageParam.put("lastTermMdate",
                    DateUtil.format(DateUtil.getLastDay(DateUtil.addMonthDate(now, -1))));
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_季报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第一季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-01-01");
                pageParam.put("thisTermMdate", thisYear + "-03-31");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-01-01");
                    pageParam.put("lastTermMdate", lastYear + "-03-31");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-10-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }

            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第二季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-04-01");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-04-01");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-01-31");
                    pageParam.put("lastTermMdate", thisYear + "-03-31");
                }
            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第三季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-07-01");
                pageParam.put("thisTermMdate", thisYear + "-09-30");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-07-01");
                    pageParam.put("lastTermMdate", lastYear + "-09-30");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-04-01");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }

            }
            else {
                pageParam.put("thisTermVdate", thisYear + "-10-01");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-10-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-07-01");
                    pageParam.put("lastTermMdate", thisYear + "-09-30");
                }

            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_半年报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_上半年.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-01-01");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-01-01");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-07-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }

            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_下半年.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-07-01");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-07-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-01-01");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_年报.getValue())) {
        	//update yanya 20190912 年报原控件类型yyyy更为到天yyyy-MM-dd
            pageParam.put("thisTermVdate", thisYear + "-01-01");
            //pageParam.put("thisTermMdate", thisYear + "-12-31");
            pageParam.put("lastTermVdate", lastYear + "-01-01");
            //pageParam.put("lastTermMdate", lastYear + "-12-31");
            pageParam.put("thisTermMdate", valDate);
            pageParam.put("lastTermMdate", DateUtil.format(DateUtil.addYear(now, -1)));
        }
    }

    /**
     * 查询利率表数据list
     * 
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<ValProfitQuery001Dto> selectList(Map<String, Object> pageParam)
        throws ParseException {
    	// 查询表样
    	List<ProfitCalculationDto> profitCalculationDtoList = valProfitQuery001Mapper.selectProfitCalculationDtoList();
        Map<String, ProfitCalculationDto> profitCalculationMap = profitCalculationDtoList.stream().collect(Collectors.toMap(ProfitCalculationDto::getProjCode, Function.identity()));
        
        changeMapData(pageParam);
        Map<String, ValProfitQuery001Dto> dtoMap = initReportElement(profitCalculationMap);
        pageParam.put("vdate", pageParam.get("thisTermVdate"));
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<BokBalanceDto> thisTermData = valProfitQuery001Mapper.selectAllBalance(pageParam);
        pageParam.put("vdate", pageParam.get("lastTermVdate"));
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        List<BokBalanceDto> lastTermData = valProfitQuery001Mapper.selectAllBalance(pageParam);

        // 处理特殊科目
        thisTermData = dealSpeSubject(thisTermData);
        lastTermData = dealSpeSubject(lastTermData);

        // 计算本期
        calValue(thisTermData, profitCalculationMap, true);
        // 计算上期
        calValue(lastTermData, profitCalculationMap, false);
        // 画面赋值
        setValue(profitCalculationMap, dtoMap);
        
        List<ValProfitQuery001Dto> list = new ArrayList<ValProfitQuery001Dto>();
        list.addAll(dtoMap.values());
        list.sort(new Comparator<ValProfitQuery001Dto>() {

            @Override
            public int compare(ValProfitQuery001Dto arg0, ValProfitQuery001Dto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                }
                return 1;
            }
        });
        
        return list;
    }

    /**
     * 以账套维度处理特殊科目，兼容多账套
     * @param thisTermData
     * @return
     */
    List<BokBalanceDto> dealSpeSubject(List<BokBalanceDto> listDB) {
        List<BokBalanceDto> listResult = Lists.newArrayList();

        Map<String, List<BokBalanceDto>> map = listDB.stream().collect(Collectors.groupingBy(BokBalanceDto::getBooksetId));
        for (String key : map.keySet()) {
            List<BokBalanceDto> list = map.get(key);
            // if 账套.核算方式=“预期收益型”，增加该科目，管理人报酬 = 6403的值-4003的值
            if (!DictCodeList.收益类型_收益型.getValue().equals(list.get(0).getAcctMode())) {
                // TdyBDAmt-TdyBCAmt
                list.forEach(item -> {
                    if ("4003".equals(item.getSubjectNo())){
                        item.setTdyBDAmt(BigDecimal.ZERO);
                        item.setTdyBCAmt(BigDecimal.ZERO);
                    }
                });
            }
            listResult.addAll(map.get(key));
        }
        return listResult;
    }

    /**
     * 分页查询 并 添加样式
     * @param parameters
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public List<ValProfitQuery001Dto> findPage(Map<String, Object> parameters) throws ParseException {
        List<ValProfitQuery001Dto> dtoList = this.selectList(parameters);
        this.dealStyle(dtoList, HTML_STYLE, String.valueOf(parameters.get("reportDimen"))); // 添加样式
        return dtoList;
    }
    
    /**
     * 处理样式
     * @param list
     * @param style
     * @param reportDimen 报表维度
     * @see
     * @since 1.0
     */
    public void dealStyle(List<ValProfitQuery001Dto> list,  String style, String reportDimen) {
        String prefix1 = "[一二三].*";  // 一级项目前缀 
        String prefix2 = "[\\d].*";   // 二级项目前缀
        if(HTML_STYLE.equals(style)) {// html 样式
            for (ValProfitQuery001Dto dto : list) {
                String project = dto.getProject();
                if(project.matches(prefix1)) {
                    dto.setProject("<strong>" + project + "</strong>");
                }else if(!project.matches(prefix2)) {
                    dto.setProject("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + project);
                }

                if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals(reportDimen)) {
                	dto.setProject("<label style='color:blue;'>" + dto.getProject() + "</label>");
                }
            }
        }else if(EXCEL_STYLE.equals(style)){ // excel 样式
            for (ValProfitQuery001Dto dto : list) {
                String project = dto.getProject();
                if( !project.matches(prefix1) && !project.matches(prefix2)) {
                    // \t 在 windows 平台 不起作用。
                    dto.setProject("    " + project);
                }
            }
        }
    }

    /**
     * 分页查询 并 添加样式
     * @param parameters
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public List<ValProfitQuery001Dto> findPageAll(Map<String, Object> parameters) throws ParseException {
        List<ValProfitQuery001Dto> dtoList = this.selectListAll(parameters);
        this.dealStyle(dtoList, HTML_STYLE, String.valueOf(parameters.get("reportDimen"))); // 添加样式
        return dtoList;
    }
    
    /**
     * 查询利率表数据list
     * 
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<ValProfitQuery001Dto> selectListAll(Map<String, Object> pageParam)
            throws ParseException {
        // 查询表样
        List<ProfitCalculationDto> profitCalculationDtoList = valProfitQuery001Mapper.selectProfitCalculationDtoList();
        Map<String, ProfitCalculationDto> profitCalculationMap = profitCalculationDtoList.stream()
                .collect(Collectors.toMap(ProfitCalculationDto::getProjCode, Function.identity()));

        changeMapData(pageParam);
        Map<String, ValProfitQuery001Dto> dtoMap = initReportElement(profitCalculationMap);
        pageParam.put("vdate", pageParam.get("thisTermVdate"));
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<BokBalanceDto> thisTermData = valProfitQuery001Mapper.selectAllBalanceAll(pageParam);
        pageParam.put("vdate", pageParam.get("lastTermVdate"));
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        List<BokBalanceDto> lastTermData = valProfitQuery001Mapper.selectAllBalanceAll(pageParam);

        // 处理特殊科目
        thisTermData = dealSpeSubject(thisTermData);
        lastTermData = dealSpeSubject(lastTermData);

        /**MODIFY BY yanya ON 20190912 重复累加**/
//        // 计算本期
//        calValue(thisTermData, profitCalculationMap, true);
//        // 计算上期
//        calValue(lastTermData, profitCalculationMap, false);
//        // 画面赋值
//        setValue(profitCalculationMap, dtoMap);
        
        List<ValProfitQuery001Dto> list = new ArrayList<ValProfitQuery001Dto>();
        // if 账套.核算方式=“预期收益型”，增加该科目，管理人报酬 = 6403的值-4003的值
        List<BokBookset> bokBookSetList = valProfitQuery001Mapper.getAcctMode(pageParam);
        for (BokBookset bokBookset : bokBookSetList) {
        	/**MODIFY BY yanya ON 20190916 避免AcctMode为空异常**/
            //if (bokBookset.getAcctMode().equals(DictCodeList.收益类型_收益型.getValue())) {
        	if (DictCodeList.收益类型_收益型.getValue().equals(bokBookset.getAcctMode())) {
            	/**MODIFY BY WUJIE ON 20190807 START
                // 原币当日发生额贷方
                BigDecimal TdyOCAmt = BigDecimal.ZERO;
                // 原币当日发生额借方
                BigDecimal TdyODAmt = BigDecimal.ZERO;
                for (BokBalance bokbalance : thisTermData) {
                    if (bokbalance.getSubjectNo().equals("4003")) {
                        TdyOCAmt = bokbalance.getTdyOCAmt();
                        TdyODAmt = bokbalance.getTdyODAmt();
                    }
                    if (bokbalance.getSubjectNo().equals("6403")) {
                        bokbalance.setTdyOCAmt(bokbalance.getTdyOCAmt().add(TdyOCAmt));
                        bokbalance.setTdyODAmt(bokbalance.getTdyODAmt().add(TdyODAmt));
                    }
                }
                **/
            	// 本位币当日发生额贷方
                BigDecimal TdyBCAmt = BigDecimal.ZERO;
                // 本位币当日发生额借方
                BigDecimal TdyBDAmt = BigDecimal.ZERO;
                for (BokBalance bokbalance : thisTermData) {
                    if (bokbalance.getSubjectNo().equals("4003")) {
                        TdyBCAmt = bokbalance.getTdyBCAmt();
                        TdyBDAmt = bokbalance.getTdyBDAmt();
                    }
                    if (bokbalance.getSubjectNo().equals("6403")) {
                        bokbalance.setTdyBCAmt(bokbalance.getTdyBCAmt().add(TdyBCAmt));
                        bokbalance.setTdyBDAmt(bokbalance.getTdyBDAmt().add(TdyBDAmt));
                    }
                }
            	/**MODIFY BY WUJIE ON 20190807 END**/
            }
        	/**MODIFY BY yanya ON 20190916 避免AcctMode为空异常**/
            //if (bokBookset.getAcctMode().equals(DictCodeList.收益类型_收益型.getValue())) {
        	if (DictCodeList.收益类型_收益型.getValue().equals(bokBookset.getAcctMode())) {
            	/**MODIFY BY WUJIE ON 20190807 START
                // 原币当日发生额贷方
                BigDecimal TdyOCAmt1 = BigDecimal.ZERO;
                // 原币当日发生额借方
                BigDecimal TdyODAmt1 = BigDecimal.ZERO;
                for (BokBalance bokbalance : lastTermData) {
                    if (bokbalance.getSubjectNo().equals("4003")) {
                        TdyOCAmt1 = bokbalance.getTdyOCAmt();
                        TdyODAmt1 = bokbalance.getTdyODAmt();
                    }
                    if (bokbalance.getSubjectNo().equals("6403")) {
                        bokbalance.setTdyOCAmt(bokbalance.getTdyOCAmt().add(TdyOCAmt1));
                        bokbalance.setTdyODAmt(bokbalance.getTdyODAmt().add(TdyODAmt1));
                    }
                }
                **/
            	// 原币当日发生额贷方
                BigDecimal TdyBCAmt1 = BigDecimal.ZERO;
                // 原币当日发生额借方
                BigDecimal TdyBDAmt1 = BigDecimal.ZERO;
                for (BokBalance bokbalance : lastTermData) {
                    if (bokbalance.getSubjectNo().equals("4003")) {
                        TdyBCAmt1 = bokbalance.getTdyBCAmt();
                        TdyBDAmt1 = bokbalance.getTdyBDAmt();
                    }
                    if (bokbalance.getSubjectNo().equals("6403")) {
                        bokbalance.setTdyBCAmt(bokbalance.getTdyBCAmt().add(TdyBCAmt1));
                        bokbalance.setTdyBDAmt(bokbalance.getTdyBDAmt().add(TdyBDAmt1));
                    }
                }
            	/**MODIFY BY WUJIE ON 20190807 END**/
            }
//            // 计算本期
//            calValue(thisTermData, profitCalculationMap, true);
//            // 计算上期
//            calValue(lastTermData, profitCalculationMap, false);
//            // 画面赋值
//            setValue(profitCalculationMap, dtoMap);
//            list.addAll(dtoMap.values());
//            list.sort(new Comparator<ValProfitQuery001Dto>() {
//
//                @Override
//                public int compare(ValProfitQuery001Dto arg0, ValProfitQuery001Dto arg1) {
//                    if (arg0.getSortNum() < arg1.getSortNum()) {
//                        return -1;
//                    }
//                    return 1;
//                }
//            });
//            //return list;
        }
           // 计算本期
          calValue(thisTermData, profitCalculationMap, true);
          // 计算上期
          calValue(lastTermData, profitCalculationMap, false);
          // 画面赋值
          setValue(profitCalculationMap, dtoMap);
	      list.addAll(dtoMap.values());
	      list.sort(new Comparator<ValProfitQuery001Dto>() {
	
	          @Override
	          public int compare(ValProfitQuery001Dto arg0, ValProfitQuery001Dto arg1) {
	              if (arg0.getSortNum() < arg1.getSortNum()) {
	                  return -1;
	              }
	              return 1;
	          }
	      });
        return list;
    }

    /**
     * 初始化报表项
     * 
     * @param profitCalculationMap
     * @return
     */
    public Map<String, ValProfitQuery001Dto> initReportElement(Map<String, ProfitCalculationDto> profitCalculationMap) {
    	Map<String, ValProfitQuery001Dto> retMap = new HashMap<String, ValProfitQuery001Dto>();
    	ValProfitQuery001Dto valProfitQuery001Dto = null; 
		for (String key : profitCalculationMap.keySet()) {
			ProfitCalculationDto configDto = profitCalculationMap.get(key);
			// 是报表展示项
			if (DictCodeList.是否_是.getValue().equals(configDto.getShowFlg())) {
				if (!retMap.containsKey(configDto.getProjName())) {
					valProfitQuery001Dto = new ValProfitQuery001Dto();
					valProfitQuery001Dto.setSortNum(configDto.getShowOrder());
					valProfitQuery001Dto.setProject(configDto.getProjName());
					valProfitQuery001Dto.setThisTermAmt(BigDecimal.ZERO);
					valProfitQuery001Dto.setLastTermAmt(BigDecimal.ZERO);
					retMap.put(configDto.getProjName(), valProfitQuery001Dto);
				}
			}
		}
		return retMap;
	}

	/**
     * 将计算结果复制到返回画面结果集
     * 
     * @param profitCalculationMap
     * @param dtoMap
     */
    public void setValue(Map<String, ProfitCalculationDto> profitCalculationMap,
			Map<String, ValProfitQuery001Dto> dtoMap) {
		for (String key : profitCalculationMap.keySet()) {
			if (dtoMap.containsKey(profitCalculationMap.get(key).getProjName())) {
				dtoMap.get(profitCalculationMap.get(key).getProjName()).setThisTermAmt(profitCalculationMap.get(key).getThisTermAmt());
				dtoMap.get(profitCalculationMap.get(key).getProjName()).setLastTermAmt(profitCalculationMap.get(key).getLastTermAmt());
			}
		}
	}

	/**
     * 计算利润表各项目值
     * 
     * @param termData
     * @param profitCalculationMap
     * @param thisTermFlag
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void calValue(List termData, Map<String, ProfitCalculationDto> profitCalculationMap, boolean thisTermFlag) {
		// 汇总项key集合
		List<String> keyList = new ArrayList<String>();
		// 计算明细项目
		for (String key : profitCalculationMap.keySet()) {
			ProfitCalculationDto dto = profitCalculationMap.get(key);
			if (1== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyList.add(dto.getProjCode());
				continue;
			}
			// 计算当期值
			BigDecimal termAmt = calcTermAmt(termData, dto);
			if (thisTermFlag) {
				profitCalculationMap.get(key).setThisTermAmt(profitCalculationMap.get(key).getThisTermAmt().add(termAmt));
			} else {
				profitCalculationMap.get(key).setLastTermAmt(profitCalculationMap.get(key).getLastTermAmt().add(termAmt));
			}
		}
		// 计算汇总项目
		Collections.sort(keyList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
			
		});
		for (String key : keyList) {
			// 计算汇总项数值
			calcTotalTermAmt(profitCalculationMap, key);
		}
		
	}

	/**
	 * 根据报表项目配置信息，计算汇总项目值，目前计算公式只支持加减，且计算公式不能带左右括号
	 * @param termData
	 * @param dto
	 * @return
	 */
	private void calcTotalTermAmt(Map<String, ProfitCalculationDto> profitCalculationMap, String parentKey) {
		ProfitCalculationDto dto = profitCalculationMap.get(parentKey);
		BigDecimal thisTermAmt = BigDecimal.ZERO;
		BigDecimal lastTermAmt = BigDecimal.ZERO;
		try {
			// 汇总项计算公式
			String formula = dto.getFormula();
			// 参与运算的子项key
			StringBuilder childKeyBuilder = new StringBuilder();
			// 参与计算的运算符
			StringBuilder punctBuilder = new StringBuilder();
			char[] arr = formula.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				if ('+' == arr[i] || '-' == arr[i]) {
					childKeyBuilder.append('@');
					punctBuilder.append(arr[i]);
				} else {
					childKeyBuilder.append(arr[i]);
				}
			}
			
	        char[] punctArr = punctBuilder.toString().toCharArray();
	        String[] childKeyArr = childKeyBuilder.toString().split("@");
	        thisTermAmt = profitCalculationMap.get(childKeyArr[0]).getThisTermAmt();
	        lastTermAmt = profitCalculationMap.get(childKeyArr[0]).getLastTermAmt();
	        for (int i = 0; i < punctArr.length; i ++) {
	        	if ('+' == punctArr[i]) {
	        		thisTermAmt = thisTermAmt.add(profitCalculationMap.get(childKeyArr[i + 1]).getThisTermAmt());
	        		lastTermAmt = lastTermAmt.add(profitCalculationMap.get(childKeyArr[i + 1]).getLastTermAmt());
	        	}
	        	if ('-' == punctArr[i]) {
	        		thisTermAmt = thisTermAmt.subtract(profitCalculationMap.get(childKeyArr[i + 1]).getThisTermAmt());
	        		lastTermAmt = lastTermAmt.subtract(profitCalculationMap.get(childKeyArr[i + 1]).getLastTermAmt());
	        	}
	        }
			profitCalculationMap.get(parentKey).setThisTermAmt(thisTermAmt);
			profitCalculationMap.get(parentKey).setLastTermAmt(lastTermAmt);
		} catch (Exception e) {
		}
	}

	/**
	 * 根据报表项目配置信息，计算项目值，目前计算公式只支持加减，且计算公式不能带左右括号
	 * @param termData
	 * @param dto
	 * @return
	 */
	private BigDecimal calcTermAmt(List<BokBalance> termData, ProfitCalculationDto dto) {
		// 计算必要信息缺失，直接返回0
		if (termData == null || dto == null || termData.isEmpty() || 
				StringUtils.isEmpty(dto.getSubjectNo()) || StringUtils.isEmpty(dto.getFormula())) {
			return BigDecimal.ZERO;
		}
		BigDecimal termAmt = BigDecimal.ZERO;
		// 标点符号
		String regex = "\\p{Punct}";
		// 计算公式
		String formula = dto.getFormula();
		// 参与计算的字段
		String[] fieldArr = formula.split(regex);
		// 参与计算的运算符
		String punct = "";
		Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(formula);
        while(m.find()){  
        	punct = punct + m.group(0);
        }
        char[] punctArr = punct.toCharArray();
        try {
			for (BokBalance balance : termData) {
				if (balance.getSubjectNo().equals(dto.getSubjectNo())) {
					Method method = balance.getClass().getMethod("get" + fieldArr[0]);
			        Object obj = method.invoke(balance);
			        termAmt = termAmt.add((BigDecimal)(obj == null ? BigDecimal.ZERO : obj));
			        for (int i = 0; i < punctArr.length; i ++) {
			        	Method method1 = balance.getClass().getMethod("get" + fieldArr[i + 1]);
			            Object obj1 = method1.invoke(balance);
			            if ('+' == punctArr[i]) {
			            	termAmt = termAmt.add((BigDecimal)(obj1 == null ? BigDecimal.ZERO : obj1));
			            }
			            if ('-' == punctArr[i]) {
			            	termAmt = termAmt.subtract((BigDecimal)(obj1 == null ? BigDecimal.ZERO : obj1));
			            }
			        }
				}
			}
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
		}
		return termAmt;
	}
	
    /**
     * 获取默认日期信息
     * 
     * @param pageParam
     * @return ValProfitQuery001Dto
     */
    public ValProfitQuery001Dto getDateInfo(Map<String, Object> pageParam) {
        String booksetId = String.valueOf(pageParam.get("booksetId"));
        String reportTermType = String.valueOf(pageParam.get("reportTermType"));
        ValProfitQuery001Dto datedto = new ValProfitQuery001Dto();
        if(booksetId != "null" && booksetId != ""){
            datedto = valProfitQuery001Mapper.getDateInfo1(pageParam);
        }else{
            List<ValProfitQuery001Dto> dateList = valProfitQuery001Mapper.getDateInfo2(pageParam);
            for(ValProfitQuery001Dto item:dateList){
                if(datedto.getReportDate() == null || datedto.getReportDate() == ""){
                    datedto.setReportDate(item.getReportDate());
                }else{
                    if(datedto.getReportDate().compareTo(item.getReportDate()) > 0){
                        datedto.setReportDate(item.getReportDate());
                    }
                }
            }
        }
        if(DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)){
            datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), 0, 0, -1)));
//            datedto.setReportDate(DateUtil.addDate(datedto.getReportDate(), 0, 0, -1));
        }else if(DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)){
            datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), 0, -1, 0), "yyyy-MM"));
        }else if(DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)){
            int season = DateUtil.getSeason(DateUtil.parse(datedto.getReportDate()));
            if(season == 1){
                datedto.setReportTerm(DictCodeListExt1.季度_第四季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), -1, 0, 0), "yyyy"));
            }else if(season == 2){
                datedto.setReportTerm(DictCodeListExt1.季度_第一季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }else if(season == 3){
                datedto.setReportTerm(DictCodeListExt1.季度_第二季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }else if(season == 4){
                datedto.setReportTerm(DictCodeListExt1.季度_第三季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }
        }else if(DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)){
            int month = DateUtil.getMonth(datedto.getReportDate());
            if(month<=6){
                datedto.setReportTerm(DictCodeListExt1.半年_下半年.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), -1, 0, 0), "yyyy"));
            }else{
                datedto.setReportTerm(DictCodeListExt1.半年_上半年.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }
        }else if(DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)){
            int year = DateUtil.getYear(datedto.getReportDate())-1;
            datedto.setReportDate(year + "-12-31");
//            datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), -1, 0, 0)));
        }
        return datedto;
    }
}
