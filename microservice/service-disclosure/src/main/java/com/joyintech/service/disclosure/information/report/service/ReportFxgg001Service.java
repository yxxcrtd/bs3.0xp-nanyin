
package com.joyintech.service.disclosure.information.report.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.disclosure.information.report.dao.ReportFxgg001Mapper;
import com.joyintech.service.disclosure.information.report.dto.ReportFxgg001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 发行公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportFxgg001Service
 * @since 1.0
 */
@Service(value="serviceReportFxgg001Service")
@Transactional(rollbackFor = Exception.class)
public class ReportFxgg001Service extends BaseService<ReportFxgg001Dto> {
    
    @Autowired
    private ReportFxgg001Mapper reportFxgg001Mapper;
    
    
   /**
    * 根据产品代码查询公告信息
    * <一句话功能简述>
    * <功能详细描述>
    * @param params
    * @return
    * @throws Exception
    * @see
    * @since 1.0
    */
    public Map<String, Object> findFxgg(Map<String, Object> params) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        if (params.get("portnum") == null) {
            return dataMap;
        }
        String prodId = params.get("portnum").toString();
        Map<String,Object> searchparam = new HashMap<String,Object>();
        searchparam.put("prodId", prodId);
        ReportFxgg001Dto fxgg = reportFxgg001Mapper.queryFxgg(searchparam);
        if (fxgg == null) {
            return dataMap;
        }
        dataMap.put("${prodName}", fxgg.getProdName());
        dataMap.put("${regCode}", fxgg.getRegCode());
        Date setDate = fxgg.getSetDate();
        if (setDate != null) {
            Map<String,Object> holidaysearchparam = new HashMap<String,Object>();
            holidaysearchparam.put("busi_date", setDate);
            holidaysearchparam.put("is_holiday", "0");
            holidaysearchparam.put("market_code", "01");
            Date raiseEndDate = reportFxgg001Mapper.queryNextWorkDay(holidaysearchparam);
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
            dataMap.put("${raiseAmt}", df.format(new BigDecimal("123456789.0036")));
        } else {
            dataMap.put("${raiseAmt}", BigDecimal.ZERO);
        }
        return dataMap;
    }
}
