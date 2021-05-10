/*
 * 文件名：ValProfitQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dao.ValAssetBook001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dao.ValProfitQuery001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.BokBalanceDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ProfitCalculationDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery001Service;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.dao.ValProfitNewQuery001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.dto.ProfitNewCalculationDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.dto.ValProfitNewQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tools.util.ExcelCellValueFunction;
import com.joyintech.tools.util.ExcelTools;

/***
 *
 * @author huanghao
 *
 */
@Service(value = "serviceValProfitNewQuery001Service")
public class ValProfitNewQuery001Service extends CommonExportService<ValProfitNewQuery001Dto> {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValProfitNewQuery001Service.class);
    /**
     * 利润表查询mapper
     */
    @Autowired
    private ValProfitNewQuery001Mapper valProfitNewQuery001Mapper;

    @Autowired
    private ValAssetBook001Mapper valAssetBook001Mapper;


    public List<SuggestDto> getBooksetId(Map<String, Object> param) {
        String profitType = param.get("profitType") == null ? null : (String) param.get("profitType");
        String booksetType = param.get("booksetType") == null ? null : (String) param.get("booksetType");
        return valProfitNewQuery001Mapper.getBooksetId(profitType, booksetType);
    }

    public List<ValProfitNewQuery001Dto> selectProfit(Map<String, Object> param) {
        return valProfitNewQuery001Mapper.selectProfit(param);
    }

    public List<ValProfitNewQuery001Dto> selectProfitAll(Map<String, Object> param) {
        return valProfitNewQuery001Mapper.selectProfitAll(param);
    }

    public List<BokBalance> selectAllBalance(Map<String, Object> param) {
        return valProfitNewQuery001Mapper.selectAllBalance(param);
    }

    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        return valProfitNewQuery001Mapper.getLinkSelectOption(param);
    }

    public List<SuggestDto> getBooksetId3(Map<String, Object> parameters) {
        return valAssetBook001Mapper.getBooksetId3(parameters);
    }

    public List<ValProfitNewQuery001Dto> findPageAll(Map<String, Object> parameters) throws CloneNotSupportedException {
        List<ValProfitNewQuery001Dto> dtoList = this.selectListAll(parameters);
        return dtoList;
    }

    private List<ValProfitNewQuery001Dto> selectListAll(Map<String, Object> pageParam) throws CloneNotSupportedException {
        // 查询表样
        List<ProfitNewCalculationDto> profitCalculationDtoList = valProfitNewQuery001Mapper.selectProfitCalculationDtoList();
        List<ValProfitNewQuery001Dto> list = new ArrayList<ValProfitNewQuery001Dto>();
        changeMapData(pageParam);
        Map<String, ValProfitNewQuery001Dto> dtoMap = null;
        List<SuggestDto> accountList = (List<SuggestDto>) pageParam.get("accounts");
        for (SuggestDto accountDto : accountList) {
            HashMap<String, ProfitNewCalculationDto> calcMap = getInitCalcMap(profitCalculationDtoList);
            dtoMap = this.initReportElement(calcMap);
            pageParam.put("booksetId", accountDto.getCode());
            pageParam.put("vdate", pageParam.get("thisTermVdate"));
            pageParam.put("mdate", pageParam.get("thisTermMdate"));
            List<BokBalanceDto> thisTermData = valProfitNewQuery001Mapper.selectAllBalance2(pageParam);
            pageParam.put("vdate", pageParam.get("lastTermVdate"));
            pageParam.put("mdate", pageParam.get("lastTermMdate"));
            List<BokBalanceDto> lastTermData = valProfitNewQuery001Mapper.selectAllBalance2(pageParam);

            // 处理特殊科目
            thisTermData = this.dealSpeSubject(thisTermData);
            lastTermData = this.dealSpeSubject(lastTermData);

            // 计算本期
            this.calValue(thisTermData, calcMap, true);
            // 计算上期
            this.calValue(lastTermData, calcMap, false);
            // 画面赋值
            this.setValue(calcMap, dtoMap);
            ValProfitNewQuery001Dto projectDto = dtoMap.get(String.valueOf(pageParam.get("project")));
            if (projectDto != null) {
                projectDto.setBooksetId(accountDto.getCode());
                list.add(projectDto);
            }
        }
        return list;
    }
    /**
     * 设置页面传回参数
     *
     * @param pageParam
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public void changeMapData(Map<String, Object> pageParam) {
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
                    pageParam.put("lastTermVdate", thisYear + "-07-01");
                    pageParam.put("lastTermMdate", thisYear + "-12-31");
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
     * 分析余额日期
     * 查询条件
     *    期末、年初
     * @param pageParam
     * @return
     */
    public Date analyseDate(Map<String, Object> pageParam) {

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
    private HashMap<String, ProfitNewCalculationDto> getInitCalcMap(List<ProfitNewCalculationDto> profitNewCalculationDtoList) throws CloneNotSupportedException {
        HashMap<String, ProfitNewCalculationDto> retMap = new HashMap<String, ProfitNewCalculationDto>();
        for (ProfitNewCalculationDto dto : profitNewCalculationDtoList) {
            if (!retMap.containsKey(dto.getProjCode())) {
                retMap.put(dto.getProjCode(), (ProfitNewCalculationDto) dto.clone());
            }
        }
        return retMap;
    }
    /**
     * 初始化报表项
     *
     * @param profitNewCalculationDtoMap
     * @return
     */
    public Map<String, ValProfitNewQuery001Dto> initReportElement(Map<String, ProfitNewCalculationDto> profitNewCalculationDtoMap) {
        Map<String, ValProfitNewQuery001Dto> retMap = new HashMap<String, ValProfitNewQuery001Dto>();
        ValProfitNewQuery001Dto valProfitNewQuery001Dto = null;
        for (String key : profitNewCalculationDtoMap.keySet()) {
            ProfitNewCalculationDto configDto = profitNewCalculationDtoMap.get(key);
            // 是报表展示项
            if (DictCodeList.是否_是.getValue().equals(configDto.getShowFlg())) {
                if (!retMap.containsKey(configDto.getProjName())) {
                    valProfitNewQuery001Dto = new ValProfitNewQuery001Dto();
                    valProfitNewQuery001Dto.setSortNum(configDto.getShowOrder());
                    valProfitNewQuery001Dto.setProject(configDto.getProjName());
                    valProfitNewQuery001Dto.setThisTermAmt(BigDecimal.ZERO);
                    valProfitNewQuery001Dto.setLastTermAmt(BigDecimal.ZERO);
                    retMap.put(configDto.getProjName(), valProfitNewQuery001Dto);
                }
            }
        }
        return retMap;
    }

    /**
     * 以账套维度处理特殊科目，兼容多账套
     * @param listDB
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
     * 将计算结果复制到返回画面结果集
     * @param profitNewCalculationDtoMap
     * @param dtoMap
     */
    public void setValue(Map<String, ProfitNewCalculationDto> profitNewCalculationDtoMap,
                         Map<String, ValProfitNewQuery001Dto> dtoMap) {
        for (String key : profitNewCalculationDtoMap.keySet()) {
            if (dtoMap.containsKey(profitNewCalculationDtoMap.get(key).getProjName())) {
                dtoMap.get(profitNewCalculationDtoMap.get(key).getProjName()).setThisTermAmt(profitNewCalculationDtoMap.get(key).getThisTermAmt());
                dtoMap.get(profitNewCalculationDtoMap.get(key).getProjName()).setLastTermAmt(profitNewCalculationDtoMap.get(key).getLastTermAmt());
            }
        }
    }

    /**
     * 计算利润表各项目值
     * @param termData
     * @param profitNewCalculationDtoMap
     * @param thisTermFlag
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void calValue(List termData, Map<String, ProfitNewCalculationDto> profitNewCalculationDtoMap, boolean thisTermFlag) {
        // 汇总项key集合
        List<String> keyList = new ArrayList<String>();
        // 计算明细项目
        for (String key : profitNewCalculationDtoMap.keySet()) {
            ProfitNewCalculationDto dto = profitNewCalculationDtoMap.get(key);
            if (1== dto.getProjLevel()) {
                // 汇总项，暂不计算。将key保存到汇总项key集合待用
                keyList.add(dto.getProjCode());
                continue;
            }
            // 计算当期值
            BigDecimal termAmt = calcTermAmt(termData, dto);
            if (thisTermFlag) {
                profitNewCalculationDtoMap.get(key).setThisTermAmt(profitNewCalculationDtoMap.get(key).getThisTermAmt().add(termAmt));
            } else {
                profitNewCalculationDtoMap.get(key).setLastTermAmt(profitNewCalculationDtoMap.get(key).getLastTermAmt().add(termAmt));
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
            calcTotalTermAmt(profitNewCalculationDtoMap, key);
        }

    }
    /**
     * 根据报表项目配置信息，计算项目值，目前计算公式只支持加减，且计算公式不能带左右括号
     * @param termData
     * @param dto
     * @return
     */
    private BigDecimal calcTermAmt(List<BokBalance> termData, ProfitNewCalculationDto dto) {
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
     * 根据报表项目配置信息，计算汇总项目值，目前计算公式只支持加减，且计算公式不能带左右括号
     * @param profitNewCalculationDtoMap
     * @param parentKey
     */
    private void calcTotalTermAmt(Map<String, ProfitNewCalculationDto> profitNewCalculationDtoMap, String parentKey) {
        ProfitNewCalculationDto dto = profitNewCalculationDtoMap.get(parentKey);
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
            thisTermAmt = profitNewCalculationDtoMap.get(childKeyArr[0]).getThisTermAmt();
            lastTermAmt = profitNewCalculationDtoMap.get(childKeyArr[0]).getLastTermAmt();
            for (int i = 0; i < punctArr.length; i ++) {
                if ('+' == punctArr[i]) {
                    thisTermAmt = thisTermAmt.add(profitNewCalculationDtoMap.get(childKeyArr[i + 1]).getThisTermAmt());
                    lastTermAmt = lastTermAmt.add(profitNewCalculationDtoMap.get(childKeyArr[i + 1]).getLastTermAmt());
                }
                if ('-' == punctArr[i]) {
                    thisTermAmt = thisTermAmt.subtract(profitNewCalculationDtoMap.get(childKeyArr[i + 1]).getThisTermAmt());
                    lastTermAmt = lastTermAmt.subtract(profitNewCalculationDtoMap.get(childKeyArr[i + 1]).getLastTermAmt());
                }
            }
            profitNewCalculationDtoMap.get(parentKey).setThisTermAmt(thisTermAmt);
            profitNewCalculationDtoMap.get(parentKey).setLastTermAmt(lastTermAmt);
        } catch (Exception e) {
        }
    }
}
