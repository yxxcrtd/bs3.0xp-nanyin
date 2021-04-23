/*
 * 文件名：DateCalCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2021年1月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.service.dao.DateCalcCommonMapper;

/**
 * 和日期计算关联的Service
 * @author etliu
 * @version 1.0 2021年1月5日
 * @see DateCalCommonService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DateCalCommonService {
    
    /**
     * DateCalcCommonMapper
     */
    @Autowired
    private DateCalcCommonMapper dateCalcCommonMapper;
    
    /**
     * 根据期限获取期限品种
     * @param term 期限
     * @return 期限品种
     */
    public static String genTermTypeByTerm(Long term) {
        String termType = null;
        if (term != null) {
            if (term <= 1) {
                termType = "R001";
            }
            else if (term > 1 && term <= 7) {
                termType = "R007";
            }
            else if (term > 7 && term <= 14) {
                termType = "R014";
            }
            else if (term > 14 && term <= 21) {
                termType = "R021";
            }
            else if (term > 21 && term <= 30) {
                termType = "R1M";
            }
            else if (term > 30 && term <= 60) {
                termType = "R2M";
            }
            else if (term > 60 && term <= 90) {
                termType = "R3M";
            }
            else if (term > 90 && term <= 120) {
                termType = "R4M";
            }
            else if (term > 120 && term <= 150) {
                termType = "R5M";
            }
            else if (term > 150 && term <= 180) {
                termType = "R6M";
            }
            else if (term > 180 && term <= 270) {
                termType = "R9M";
            }
            else {
                termType = "R1Y";
            }
        }
        
        return termType;
    }
    
    /**
     * 获取某个日期加固定天数后的日期（如果该日期是节假日，则获取下一工作日）
     * @param date 日期
     * @param plusDays 固定天数
     * @param dealCalId 交易日历代码
     * @return 某个日期加固定天数后的日期（如果该日期是节假日，则获取下一工作日）
     */
    public Date getPlusDaysWorkDay(Date date, int plusDays, String dealCalId) {
        Date resultDate = DateUtil.addDay(date, plusDays);
        
        if (this.isDealCalendarHoliday(resultDate, dealCalId)) {
            List<String> holidayList = this.getDealCalendarHoliday(resultDate,
                DateUtil.addDays(resultDate, 31), dealCalId);
            resultDate = DateUtil.parse(AdjustedTradeDayCal.calculationTradingDay(
                DateUtil.format(resultDate), 1, holidayList, DictCodeList.是否_是.getValue()));
        }

        return resultDate;
    }
    
    /**
     * 交易日历的节假日取得
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param dealCalId 交易日历代码
     * @return 节假日列表
     */
    public List<String> getDealCalendarHoliday(Date startDate, Date endDate, String dealCalId) {
        Map<String, Object> paraMap = new HashMap<>();
        int days = StringUtil.toInt(CacheUtil.getSysParam(BussinessConstant.HOLIDAY_POSTPONE_DAYS));
        paraMap.put("startDate", DateUtil.addDay(startDate, -days));
        paraMap.put("endDate", DateUtil.addDay(endDate, days));
        paraMap.put("dealCalId", dealCalId);
        return dateCalcCommonMapper.getDealCalendarHoliday(paraMap);
    }

    /**
     * 判断日期参数是否交易日历的节假日 <一句话功能简述> <功能详细描述>
     *
     * @param date 参数日期
     * @param calId 交易日历id
     * @return 是否交易日历的节假日判断结果
     * @see
     * @since 1.0
     */
    public boolean isDealCalendarHoliday(Date date, String calId) {
        if (date == null || StringUtil.isEmpty(calId)) {
            return false;
        }
        String dateStr = DateUtil.format(date);
        return this.isDealCalendarHoliday(dateStr, calId);
    }
    
    /**
     * 判断日期参数是否交易日历的节假日
     *
     * @param dateStr 参数日期
     * @param calId 交易日历id
     * @return 是否交易日历的节假日判断结果
     */
    public boolean isDealCalendarHoliday(String dateStr, String calId) {
        if (StringUtil.isEmpty(dateStr) || StringUtil.isEmpty(calId)) {
            return false;
        }
        int dataCount = dateCalcCommonMapper.selectDealCalendarHolidayCount(dateStr, calId);
        if (dataCount > 0) {
            return true;
        }
        return false;
    }
}
