
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.information.report.dao.ReportZjxsjg001Mapper;
import com.joyintech.tams.disclosure.information.report.dto.ReportZjxsjg001Dto;
import com.joyintech.tams.disclosure.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 增加销售机构公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportZjxsjg001Service
 * @since 1.0
 */
@Service
public class ReportZjxsjg001Service implements IReportDataService {

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private ReportZjxsjg001Mapper reportZjxsjg001Mapper;
    
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
        ReportZjxsjg001Dto zjxsjg = reportZjxsjg001Mapper.queryZjxsjg(searchparam);
        if (zjxsjg == null) {
            return result;
        }
        dataMap.put("${prodName}", zjxsjg.getProdName());
        dataMap.put("${regCode}", zjxsjg.getRegCode());
        Date puDate = commonPlatformDateService.getSysDate();
        if (null != paraMap.get("endDate")) {
            puDate = (java.sql.Date)paraMap.get("endDate");
        }
        dataMap.put("${pubDate}",DateUtil.format(puDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
        return result;
    }
}
