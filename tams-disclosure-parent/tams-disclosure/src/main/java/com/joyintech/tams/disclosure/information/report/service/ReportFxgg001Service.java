
package com.joyintech.tams.disclosure.information.report.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.information.report.dao.ReportFxgg001Mapper;
import com.joyintech.tams.disclosure.information.report.dto.ReportFxgg001Dto;
import com.joyintech.tams.disclosure.rptdata.service.IReportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发行公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportFxgg001Service
 * @since 1.0
 */
@Service
public class ReportFxgg001Service implements IReportDataService {

    @Autowired
    private ReportFxgg001Mapper reportFxgg001Mapper;

    @Autowired
    private HolidayService holidayService;

    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        List<Map<String,Object>> result = new ArrayList<>();
        result.add(dataMap);
        if (paraMap.get("pprodid") == null) {
            return result;
        }
        String prodId = paraMap.get("pprodid").toString();
        Map<String,Object> searchparam = new HashMap<String,Object>();
        searchparam.put("prodId", prodId);
        ReportFxgg001Dto fxgg = reportFxgg001Mapper.queryFxgg(searchparam);
        if (fxgg == null) {
            return result;
        }
        dataMap.put("${prodName}", fxgg.getProdName());
        dataMap.put("${regCode}", fxgg.getRegCode());
        Date setDate = fxgg.getSetDate();
        if (setDate != null) {
            Map<String,Object> holidaysearchparam = new HashMap<String,Object>();
            holidaysearchparam.put("busi_date", setDate);
            holidaysearchparam.put("is_holiday", "0");
            holidaysearchparam.put("market_code", "01");
            Date raiseEndDate = holidayService.getLastWorkDay(holidaysearchparam);
            if (raiseEndDate == null) {
                raiseEndDate = DateUtil.addDay(setDate, -1);
            }
            dataMap.put("${raiseEndDate}", DateUtil.format(raiseEndDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
            dataMap.put("${setDate}",DateUtil.format(setDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
        } else {
            dataMap.put("${raiseEndDate}", "XXXX年X月XX日");
            dataMap.put("${setDate}", "XXXX年X月XX日");
        }
        BigDecimal raiseAmt = fxgg.getRaiseAmt();
        if (raiseAmt != null) {
            DecimalFormat df = new DecimalFormat("#,###.00");
            dataMap.put("${raiseAmt}", df.format(raiseAmt));
        } else {
            dataMap.put("${raiseAmt}", BigDecimal.ZERO);
        }
        return result;
    }
}
