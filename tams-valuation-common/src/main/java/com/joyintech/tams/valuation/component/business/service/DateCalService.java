package com.joyintech.tams.valuation.component.business.service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.component.business.dao.DateCalcMapper;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DateCalService
 * @Description: 付息计划，开放计划，利率重置，申购计划，赎回计划计算用和日期关联的Service
 * @Author yanya
 * @Date 2021/3/4 0004 
 * @Version V1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DateCalService {


    /**
     * 付息计划，开放计划，利率重置，申购计划，赎回计划计算用和日期关联的Mapper
     */
    @Autowired(required = false)
    private DateCalcMapper dateCalcMapper;

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
        int days = StringUtil.toInt(CacheUtil.getSysParam(ValComponentConstant.HOLIDAY_POSTPONE_DAYS));
        paraMap.put("startDate", DateUtil.addDay(startDate, -days));
        paraMap.put("endDate", DateUtil.addDay(endDate, days));
        paraMap.put("dealCalId", dealCalId);
        return dateCalcMapper.getDealCalendarHoliday(paraMap);
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
        int dataCount = dateCalcMapper.selectDealCalendarHolidayCount(dateStr, calId);
        if (dataCount > 0) {
            return true;
        }
        return false;
    }
}
