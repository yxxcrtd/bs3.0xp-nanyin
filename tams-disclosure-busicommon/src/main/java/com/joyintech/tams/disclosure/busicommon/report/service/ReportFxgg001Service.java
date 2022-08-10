
package com.joyintech.tams.disclosure.busicommon.report.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportFxggMapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportFxggDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
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
@Service("reportFxgg001Service")
public class ReportFxgg001Service implements IReportDataService {

    @Autowired
    private ReportFxggMapper reportFxggMapper;

    @Autowired
    private HolidayService holidayService;

    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        Map<String,Object> dataMap = new HashMap<>();
        List<Map<String,Object>> result = new ArrayList<>();
        result.add(dataMap);
        String prodId = "";
        if (StringUtil.equals("batch", paraMap.get("opttype"))) {
            prodId = (String)paraMap.get("prodid");
        } else {
            prodId = (String)paraMap.get("pprodid");
        }
        if (StringUtil.isBlank(prodId)) {
            return result;
        }
        Map<String,Object> searchparam = new HashMap<>();
        searchparam.put("prodId", prodId);
        ReportFxggDto fxgg = reportFxggMapper.queryFxgg(searchparam);
        if (fxgg == null) {
            return result;
        }
        dataMap.put("prodName", fxgg.getProdName());
        dataMap.put("regCode", fxgg.getRegCode());
        Date setDate = fxgg.getSetDate();
        if (setDate != null) {
            Map<String,Object> holidaysearchparam = new HashMap<>();
            holidaysearchparam.put("busi_date", setDate);
            holidaysearchparam.put("is_holiday", "0");
            holidaysearchparam.put("market_code", "01");
            Date raiseEndDate = holidayService.getLastWorkDay(holidaysearchparam);
            if (raiseEndDate == null) {
                raiseEndDate = DateUtil.addDay(setDate, -1);
            }
            dataMap.put("raiseEndDate", DateUtil.format(raiseEndDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
            dataMap.put("setDate",DateUtil.format(setDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
        } else {
            dataMap.put("raiseEndDate", "XXXX年X月XX日");
            dataMap.put("setDate", "XXXX年X月XX日");
        }
        BigDecimal raiseAmt = fxgg.getRaiseAmt();
        if (raiseAmt != null) {
            DecimalFormat df = new DecimalFormat("#,###.00");
            dataMap.put("raiseAmt", df.format(raiseAmt));
            dataMap.put("raiseAmtVal", raiseAmt);
        } else {
            dataMap.put("raiseAmt", BigDecimal.ZERO);
        }
        dataMap.put("enddate",DateUtil.format(setDate));
        return result;
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        repReveal.setProdid(prodId);
        Map<String,Object> searchparam = new HashMap<>();
        searchparam.put("prodId", prodId);
        ReportFxggDto fxgg = reportFxggMapper.queryFxgg(searchparam);
        repReveal.setEndDate(fxgg.getSetDate());
        return repReveal;
    }
}
