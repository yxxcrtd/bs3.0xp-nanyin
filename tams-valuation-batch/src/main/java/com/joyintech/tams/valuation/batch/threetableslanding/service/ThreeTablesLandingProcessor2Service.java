/*
 * 文件名：ExpReturnTaTradeBookProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.batch.threetableslanding.dao.ThreeTableLandingMapper;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ProfitCalculationDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ThreeTableLandingResultDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ValProfitDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ValTaskTodoDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;


/**
 * 三表落地reader
 * 
 * @author weipeng
 * @version 1.0 2020年5月8日
 * @see ThreeTablesLandingProcessor2Service
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ThreeTablesLandingProcessor2Service {

    @Autowired
    private ThreeTableLandingMapper threeTableLandingMapper;

    /**
     * process 数据处理
     * 
     * @param param 传递参数
     * @return BookGenerateResultWithBokposition
     * @see
     * @since 1.0
     */
    public ThreeTableLandingResultDto execute(Map<String, Object> param) {
        // 获取reader返回值
        ValTaskTodoDto acctEntity = (ValTaskTodoDto)param.get(GlobalConstant.READER_RESULT);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("booksetId", acctEntity.getBooksetId());
        params.put("balanceDate", acctEntity.getHappenDate());
        // 查询表样
        List<ProfitCalculationDto> listCalDto = threeTableLandingMapper.selectProfitCalculationDtoList();
        /** 
         * 2020年7月13日 weipeng:对表样List做重新赋值处理
         * 由于myBatis缓存机制，导致同一个sql同一参数，第二次调用时不会去库中查询，会直接调用一级缓存中的结果数据，process多个线程并发执行，当第一个线程操作过该List后，
         * 已对List中数据做过处理，后续process调用List会直接取缓存中的数据，为已处理后的数据，所以会导致后面账套的利润表会出现值为前面已执行过的账套对应指标值为累加值的情况
         */
        // 重新初始化表样
        List<ProfitCalculationDto> profitCalculationDtoList = initCalcDtoList(listCalDto);
        
        Map<String, ProfitCalculationDto> profitCalculationMap = profitCalculationDtoList.stream().collect(
            Collectors.toMap(ProfitCalculationDto::getProjCode, Function.identity()));

        Map<String, ValProfitDto> dtoMap = initReportElement(profitCalculationMap, acctEntity.getBooksetId(), acctEntity.getHappenDate());
        List<BokBalance> listBalance = threeTableLandingMapper.selectAllBalance(params);
        // 计算本期
        calValue(listBalance, profitCalculationMap);
        // 画面赋值
        setValue(profitCalculationMap, dtoMap);

        List<ValProfitDto> list = new ArrayList<ValProfitDto>();
        list.addAll(dtoMap.values());
        list.sort(new Comparator<ValProfitDto>() {

            @Override
            public int compare(ValProfitDto arg0, ValProfitDto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                }
                return 1;
            }
        });
        // 进行批处理需要将结果集封装到result
        ThreeTableLandingResultDto result = new ThreeTableLandingResultDto();
        result.setListValProfitDto(list);
        return result;
    }

    /**
     * 初始化报表项
     * 
     * @param profitCalculationMap
     * @return
     */
    private Map<String, ValProfitDto> initReportElement(Map<String, ProfitCalculationDto> profitCalculationMap,
                                                        String booksetId, Date happenDate) {
        Map<String, ValProfitDto> retMap = new HashMap<String, ValProfitDto>();
        ValProfitDto ValProfitDto = null;
        for (String key : profitCalculationMap.keySet()) {
            ProfitCalculationDto configDto = profitCalculationMap.get(key);
            // 是报表展示项
            if (DictCodeList.是否_是.getValue().equals(configDto.getShowFlg())) {
                if (!retMap.containsKey(configDto.getProjName())) {
                    ValProfitDto = new ValProfitDto();
                    ValProfitDto.setSortNum(configDto.getShowOrder());
                    ValProfitDto.setBooksetId(booksetId);
                    ValProfitDto.setCdate(happenDate);
                    ValProfitDto.setItemName(configDto.getProjName());
                    ValProfitDto.setItemCode(configDto.getProjCode());
                    ValProfitDto.setItemLevel(new Long(configDto.getProjLevel()));
                    retMap.put(configDto.getProjName(), ValProfitDto);
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
    private void setValue(Map<String, ProfitCalculationDto> profitCalculationMap,
                          Map<String, ValProfitDto> dtoMap) {
        for (String key : profitCalculationMap.keySet()) {
            if (dtoMap.containsKey(profitCalculationMap.get(key).getProjName())) {
                dtoMap.get(profitCalculationMap.get(key).getProjName()).setItemAmt(
                    profitCalculationMap.get(key).getThisTermAmt());
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
    private void calValue(List<BokBalance> termData,
                          Map<String, ProfitCalculationDto> profitCalculationMap) {
        // 汇总项key集合
        List<String> keyList = new ArrayList<String>();
        // 计算明细项目
        for (String key : profitCalculationMap.keySet()) {
            ProfitCalculationDto dto = profitCalculationMap.get(key);
            if (1 == dto.getProjLevel()) {
                // 汇总项，暂不计算。将key保存到汇总项key集合待用
                keyList.add(dto.getProjCode());
                continue;
            }
            // 计算当期值
            BigDecimal termAmt = calcTermAmt(termData, dto);
            profitCalculationMap.get(key).setThisTermAmt(
                profitCalculationMap.get(key).getThisTermAmt().add(termAmt));
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
     * 
     * @param termData
     * @param dto
     * @return
     */
    private void calcTotalTermAmt(Map<String, ProfitCalculationDto> profitCalculationMap,
                                  String parentKey) {
        ProfitCalculationDto dto = profitCalculationMap.get(parentKey);
        BigDecimal thisTermAmt = BigDecimal.ZERO;
        try {
            // 汇总项计算公式
            String formula = dto.getFormula();
            // 参与运算的子项key
            StringBuilder childKeyBuilder = new StringBuilder();
            // 参与计算的运算符
            StringBuilder punctBuilder = new StringBuilder();
            char[] arr = formula.toCharArray();
            for (int i = 0; i < arr.length; i++ ) {
                if ('+' == arr[i] || '-' == arr[i]) {
                    childKeyBuilder.append('@');
                    punctBuilder.append(arr[i]);
                }
                else {
                    childKeyBuilder.append(arr[i]);
                }
            }

            char[] punctArr = punctBuilder.toString().toCharArray();
            String[] childKeyArr = childKeyBuilder.toString().split("@");
            thisTermAmt = profitCalculationMap.get(childKeyArr[0]).getThisTermAmt();
            for (int i = 0; i < punctArr.length; i++ ) {
                if ('+' == punctArr[i]) {
                    thisTermAmt = thisTermAmt.add(
                        profitCalculationMap.get(childKeyArr[i + 1]).getThisTermAmt());
                }
                if ('-' == punctArr[i]) {
                    thisTermAmt = thisTermAmt.subtract(
                        profitCalculationMap.get(childKeyArr[i + 1]).getThisTermAmt());
                }
            }
            profitCalculationMap.get(parentKey).setThisTermAmt(thisTermAmt);
        }
        catch (Exception e) {
        }
    }

    /**
     * 根据报表项目配置信息，计算项目值，目前计算公式只支持加减，且计算公式不能带左右括号
     * 
     * @param termData
     * @param dto
     * @return
     */
    private BigDecimal calcTermAmt(List<BokBalance> termData, ProfitCalculationDto dto) {
        // 计算必要信息缺失，直接返回0
        if (termData == null || dto == null || termData.isEmpty()
            || StringUtils.isEmpty(dto.getSubjectNo()) || StringUtils.isEmpty(dto.getFormula())) {
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
        while (m.find()) {
            punct = punct + m.group(0);
        }
        char[] punctArr = punct.toCharArray();
        try {
            for (BokBalance balance : termData) {
                if (balance.getSubjectNo().equals(dto.getSubjectNo())) {
                    Method method = balance.getClass().getMethod("get" + fieldArr[0]);
                    Object obj = method.invoke(balance);
                    termAmt = termAmt.add((BigDecimal)(obj == null ? BigDecimal.ZERO : obj));
                    for (int i = 0; i < punctArr.length; i++ ) {
                        Method method1 = balance.getClass().getMethod("get" + fieldArr[i + 1]);
                        Object obj1 = method1.invoke(balance);
                        if ('+' == punctArr[i]) {
                            termAmt = termAmt.add(
                                (BigDecimal)(obj1 == null ? BigDecimal.ZERO : obj1));
                        }
                        if ('-' == punctArr[i]) {
                            termAmt = termAmt.subtract(
                                (BigDecimal)(obj1 == null ? BigDecimal.ZERO : obj1));
                        }
                    }
                }
            }
        }
        catch (Exception e) {
        }
        return termAmt;
    }
    
    /**
     * 查询计算表样重新初始化
     * @param list
     * @return
     */
    private List<ProfitCalculationDto> initCalcDtoList(List<ProfitCalculationDto> list){
    	List<ProfitCalculationDto> listDto= new ArrayList<ProfitCalculationDto>();
    	if(list != null && list.size() > 0){
    		for (ProfitCalculationDto item : list) {
    			ProfitCalculationDto dto = item;
    			dto.setThisTermAmt(null);
    			listDto.add(dto);
			}
    	}
    	return listDto;
    }

}
