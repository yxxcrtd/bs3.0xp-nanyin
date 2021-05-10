package com.joyintech.tams.valuation.component.asset.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.asset.dto.BusiFinExercisePlanDto;
import com.joyintech.tams.valuation.component.business.service.DateCalService;
import com.joyintech.tams.valuation.component.common.calculator.AdjustedTradeDayCal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BusiFinExercisePlanService
 * @Description: 行权计划
 * @Author yanya
 * @Date 2021/3/4 0004 
 * @Version V1.0
 **/
@Service
public class BusiFinExercisePlanService extends CommonExportService<BusiFinExercisePlanDto> {

    @Autowired
    private DateCalService dateCalService;
    public List<FinExercisePlan> getfinExercisePlanByProductId(Map<String,Object> param) {
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
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
