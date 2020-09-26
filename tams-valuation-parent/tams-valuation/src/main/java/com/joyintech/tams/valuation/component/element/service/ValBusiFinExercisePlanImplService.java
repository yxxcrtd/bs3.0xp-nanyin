/*
 * 文件名：ValBusiFinExercisePlanServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.component.common.service.ValDateCalService;
import com.joyintech.tams.valuation.component.element.dao.ValBusiFinExercisePlanMapper;
import com.joyintech.tams.valuation.component.element.dto.ValBusiFinExercisePlanDto;
import com.joyintech.tams.valuation.component.element.interf.ValBusiFinExercisePlanService;

/**
 * 行权计划
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月12日
 * @see BusiFinExercisePlanServiceImpl
 * @since 1.0
 */
@Service
public class ValBusiFinExercisePlanImplService extends BaseService<ValBusiFinExercisePlanDto> implements ValBusiFinExercisePlanService{

    // 增加实际行权日 add by renping 20190927 start
    /**
     * 节假日计算用和日期关联的Service
     */
    @Autowired
    private ValDateCalService dateCalService;
    // 增加实际行权日 add by renping 20190927 end
    
    @Autowired
    private ValBusiFinExercisePlanMapper busiFinExercisePlanMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FinExercisePlan> getfinExercisePlanByProductId(Map<String,Object> param) {
//        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
//            + "/BusiFinExercisePlan/getfinExercisePlanByProductId?finprodId="
//            + param.get("finprodId").toString()+"&cashType2="+param.get("cashType2").toString()
//            +"&execType01="+param.get("execType01").toString()
//            +"&execType02="+param.get("execType02").toString()
//            +"&psT03="+param.get("psT03").toString(),
//            FinExercisePlan.class);
    	String psT39 = DictCodeList.交易方向_行权_赎回.getValue();
//      return busiFinExercisePlanMapper.getfinExercisePlanByProductId(finprodId, psT03, cashType2, execType01, execType02);
    	return busiFinExercisePlanMapper.getfinExercisePlanByProductId(param.get("finprodId").toString(), param.get("psT03").toString(),
    			psT39, param.get("cashType2").toString(), param.get("execType01").toString(), param.get("execType02").toString());
    	
    }   

    // 增加实际行权日 add by renping 20190927 start
    /**
     * 根据行权日取得实际行权日
     *
     * @param dto 参数
     * @return 实际行权日
     * @see
     * @since 1.0
     */
    public Map<String, String> getActExerciseDate(Map<String, Object> param) {
        String exerciseDate = (String)param.get("exerciseDate");
        String calendarId = (String)param.get("calendarId");
        String payTdayRule = (String)param.get("payTdayRule");
        // 交易日历取得
        List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(
            DateUtil.parse(DateUtil.addDay(exerciseDate, -15)),
            DateUtil.parse(DateUtil.addDay(exerciseDate, 15)), calendarId);
        // 节假日调整后
        if (StringUtil.isEmpty(payTdayRule)) {
            payTdayRule = DictCodeList.营业日准则_向后调整.getValue();
        }
        String actExerciseDate = getAdjustedTradeDay(exerciseDate, payTdayRule,
            dealCalendarHolidayList);
        Map<String, String> result = new HashMap<String, String>();
        result.put("actExerciseDate", actExerciseDate);
        return result;
    }
    // 增加实际行权日 add by renping 20190927 end
    
    /**
     * 调整后交易日取得
     *
     * @param adjustBeforeDate 调整前日期 (yyyy-MM-dd)
     * @param adjustRole 调整规则
     * @param holidayList 节假日列表 日期格式必须是 yyyy-MM-dd
     * @throws Exception 例外
     * @return 调整后日期
     * @see
     */
    public static String getAdjustedTradeDay(String adjustBeforeDate, String adjustRole,
                                             List<String> holidayList) {
        if (StringUtil.isBlank(adjustBeforeDate) || StringUtil.isEmptyList(holidayList)) {
            return adjustBeforeDate;
        }

        // 不管传进来的adjustBeforeDate是什么格式的日期字符串，将其转化为yyyy-MM-dd格式的日期字符串
        String adjustDate = adjustBeforeDate;

        // 将传进来的holidayList的日期也转化成yyyy-MM-dd格式的日期字符串
        List<String> newHolidList = holidayList;
//        for (String holidayStr : holidayList) {
//            newHolidList.add(holidayStr);
//        }

        // 输入参数.调整规则 = “不调整”时,调整后日期 = 输入参数.调整前日期
        if (adjustRole != null && adjustRole.equals(DictCodeList.营业日准则_不调整.getValue())) {
            return adjustBeforeDate;
        }

        // 调整前日期在输入参数.节假日列表中不存在时,调整后日期 = 输入参数.调整前日期
        if (newHolidList.indexOf(adjustDate) == -1) {
            return adjustBeforeDate;
        }
        else {
            if (adjustRole != null && adjustRole.equals(DictCodeList.营业日准则_向前调整.getValue())) {
                while (true) {
                    if (!isHoliday(adjustDate, newHolidList)) {
                        break;
                    }
                    else {
                        adjustDate = getAfterOrBeforeDay(adjustDate, 1, false);
                    }
                }
            }
            else if (adjustRole != null && adjustRole.equals(DictCodeList.营业日准则_向后调整.getValue())) {
                while (true) {
                    if (!isHoliday(adjustDate, newHolidList)) {
                        break;
                    }
                    else {
                        adjustDate = getAfterOrBeforeDay(adjustDate, 1, true);
                    }
                }
            }
            else if (adjustRole != null
                     && adjustRole.equals(DictCodeList.营业日准则_调整的向后调整.getValue())) {
                while (true) {
                    if (!isHoliday(adjustDate, newHolidList)) {
                        break;
                    }
                    else {
                        adjustDate = getAfterOrBeforeDay(adjustDate, 1, true);
                    }
                }
                if (adjustDate.substring(0, 7).equals(adjustBeforeDate.substring(0, 7))) {
                    return adjustDate;
                }
                else {
                    adjustDate = getAfterOrBeforeDay(adjustDate.substring(0, 7) + "-01", 1, false);
                    while (true) {
                        if (!isHoliday(adjustDate, holidayList)) {
                            break;
                        }
                        else {
                            adjustDate = getAfterOrBeforeDay(adjustDate, 1, false);
                        }
                    }
                }
            }
            return adjustDate;
        }
    }
    
    /**
     * 判断是否节假日
     *
     * @param date 日期
     * @param holidayList 节假日列表
     * @return true:节假日, false:非节假日
     * @see
     * @since 1.0
     */
    public static boolean isHoliday(String date, List<String> holidayList) {
        if (holidayList.indexOf(date) == -1) {
            return false;
        }
        return true;
    }
    
    /**
     * 取得指定日期的前N天或者后N天
     *
     * @param date 日期
     * @param days 指定天数
     * @param isAfterDay 取得指定日期的前N天:false,取得指定日期的后N天:true
     * @return 指定日期的前N天或者后N天
     * @throws Exception 例外
     * @see
     * @since 1.0
     */
    public static String getAfterOrBeforeDay(String date, int days, boolean isAfterDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.parse(date));
        if (!isAfterDay) {
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - days);
        }
        else {
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);
        }
        return DateUtil.format(calendar.getTime());
    }
    
    
    
}
