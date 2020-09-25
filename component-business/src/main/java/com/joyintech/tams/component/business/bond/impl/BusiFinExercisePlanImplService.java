/*
 * 文件名：BusiFinExercisePlanServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.bond.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.component.business.bond.BusiFinExercisePlanService;
import com.joyintech.tams.component.business.bond.dto.BusiFinExercisePlanDto;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 行权计划
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月12日
 * @see BusiFinExercisePlanServiceImpl
 * @since 1.0
 */
@Service
public class BusiFinExercisePlanImplService extends BaseService<BusiFinExercisePlanDto> implements BusiFinExercisePlanService{

    // 增加实际行权日 add by renping 20190927 start
    /**
     * 节假日计算用和日期关联的Service
     */
    @Autowired
    private DateCalService dateCalService;
    // 增加实际行权日 add by renping 20190927 end

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FinExercisePlan> getfinExercisePlanByProductId(Map<String,Object> param) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
            + "/BusiFinExercisePlan/getfinExercisePlanByProductId?finprodId="
            + param.get("finprodId").toString()+"&cashType2="+param.get("cashType2").toString()
            +"&execType01="+param.get("execType01").toString()
            +"&execType02="+param.get("execType02").toString()
            +"&psT03="+param.get("psT03").toString(),
            FinExercisePlan.class);
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
        String actExerciseDate = AdjustedTradeDayCal.getAdjustedTradeDay(exerciseDate, payTdayRule,
            dealCalendarHolidayList);
        Map<String, String> result = new HashMap<String, String>();
        result.put("actExerciseDate", actExerciseDate);
        return result;
    }
    // 增加实际行权日 add by renping 20190927 end
}
