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

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dao.ValProfitQuery001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.BokBalanceDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ProfitCalculationDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ValProfitQuery001Dto;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * 利润表-明细列表service
 * 
 * @author QuJianZhong
 * @version 1.0 2018年11月20日
 * @see ValProfitQuery002Service
 * @since 1.0
 */
@Service(value="serviceValProfitQuery002Service")
public class ValProfitQuery002Service extends CommonExportService<ValProfitQuery001Dto> {
    
    /**
     * 利润表查询mapper
     */
    @Autowired
    private ValProfitQuery001Mapper valProfitQuery001Mapper;
    
    /**
     * 利润表查询mapper
     */
    @Autowired
    private ValProfitQuery001Service valProfitQuery001Service;

    /**
     * 分析余额日期
     * 
     * @param pageParam 查询条件
     * @param balanceType 期末、年初
     * @return
     * @throws ParseException
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

    /**
     * 分析余额日期
     *
     * @param pageParam
     *            查询条件
     * @param balanceType
     *            期末、年初
     * @return
     * @throws ParseException
     */
    public Date analyseDatep(Map<String, Object> pageParam) throws ParseException {

        String reportTermType = (String) pageParam.get("reportTermType");
        String reportDate = (String) pageParam.get("reportDate");
        String reportTerm = (String) pageParam.get("reportTerm");

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
            year = Integer.valueOf(strArr[0]);
            month += 1;
            day = 1;
        }
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.半年_上半年.getValue().equals(reportTerm)) {
                month += 1;
                day = 1;
            } else {
                month += 6;
                day = 1;
            }
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.季度_第一季度.getValue().equals(reportTerm)) {
                month += 1;
                day = 1;
            } else if (DictCodeListExt1.季度_第二季度.getValue().equals(reportTerm)) {
                month += 4;
                day = 1;
            } else if (DictCodeListExt1.季度_第三季度.getValue().equals(reportTerm)) {
                month += 7;
                day = 1;
            } else {
                month += 10;
                day = 1;
            }
        }
        if (DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)) {// 月底：当前月的下个月的第0天
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]);
            day = 1;
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
     * 分页查询 
     * @param parameters
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public List<ValProfitQuery001Dto> findPageAll(Map<String, Object> parameters) throws Exception {
        List<ValProfitQuery001Dto> dtoList = this.selectListAll(parameters);
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
    @SuppressWarnings("unchecked")
	public List<ValProfitQuery001Dto> selectListAll(Map<String, Object> pageParam)
        throws Exception {
    	// 查询表样
    	List<ProfitCalculationDto> profitCalculationDtoList = valProfitQuery001Mapper.selectProfitCalculationDtoList();
    	List<ValProfitQuery001Dto> list = new ArrayList<ValProfitQuery001Dto>();
    	changeMapData(pageParam);
    	Map<String, ValProfitQuery001Dto> dtoMap = null;
    	List<SuggestDto> accountList = (List<SuggestDto>) pageParam.get("accounts");
    	for (SuggestDto accountDto : accountList) {
    		HashMap<String, ProfitCalculationDto> calcMap = getInitCalcMap(profitCalculationDtoList);
    		dtoMap = valProfitQuery001Service.initReportElement(calcMap);
    		pageParam.put("booksetId", accountDto.getCode());
	        pageParam.put("vdate", pageParam.get("thisTermVdate"));
	        pageParam.put("mdate", pageParam.get("thisTermMdate"));
	        List<BokBalanceDto> thisTermData = valProfitQuery001Mapper.selectAllBalance(pageParam);
	        pageParam.put("vdate", pageParam.get("lastTermVdate"));
	        pageParam.put("mdate", pageParam.get("lastTermMdate"));
	        List<BokBalanceDto> lastTermData = valProfitQuery001Mapper.selectAllBalance(pageParam);

            // 处理特殊科目
            thisTermData = valProfitQuery001Service.dealSpeSubject(thisTermData);
            lastTermData = valProfitQuery001Service.dealSpeSubject(lastTermData);

	        // 计算本期
	        valProfitQuery001Service.calValue(thisTermData, calcMap, true);
	        // 计算上期
	        valProfitQuery001Service.calValue(lastTermData, calcMap, false);
	        // 画面赋值
	        valProfitQuery001Service.setValue(calcMap, dtoMap);
	        ValProfitQuery001Dto projectDto = dtoMap.get(String.valueOf(pageParam.get("project")));
	        if (projectDto != null) {
		        projectDto.setBooksetId(accountDto.getCode());
		        list.add(projectDto);
	        }
    	}
        return list;
    }
    
    private HashMap<String, ProfitCalculationDto> getInitCalcMap(List<ProfitCalculationDto> profitCalculationDtoList) throws CloneNotSupportedException {
    	HashMap<String, ProfitCalculationDto> retMap = new HashMap<String, ProfitCalculationDto>();
		for (ProfitCalculationDto dto : profitCalculationDtoList) {
			if (!retMap.containsKey(dto.getProjCode())) {
				retMap.put(dto.getProjCode(), (ProfitCalculationDto) dto.clone());
			}
		}
		return retMap;
	}

}
