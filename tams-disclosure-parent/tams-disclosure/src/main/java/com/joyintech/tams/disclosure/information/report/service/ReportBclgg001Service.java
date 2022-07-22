
package com.joyintech.tams.disclosure.information.report.service;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.information.report.dao.ReportBclgg001Mapper;
import com.joyintech.tams.disclosure.information.report.dto.ReportBclgg001Dto;
import com.joyintech.tams.disclosure.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 不成立公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportBclgg001Service
 * @since 1.0
 */
@Service("reportBclgg001Service")
public class ReportBclgg001Service implements IReportDataService {

    @Autowired
    private ReportBclgg001Mapper reportBclgg001Mapper;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private ReportCommonService reportCommonService;

    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        Map<String,Object> dataMap = new HashMap<>();
        List<Map<String,Object>> result = new ArrayList<>();
        result.add(dataMap);
        if (paraMap.get("pprodid") == null) {
            return result;
        }
        String prodId = paraMap.get("pprodid").toString();
        Map<String,Object> searchparam = new HashMap<>();
        searchparam.put("prodId", prodId);
        ReportBclgg001Dto bclgg = reportBclgg001Mapper.queryBclgg(searchparam);
        if (bclgg == null) {
            return result;
        }
        dataMap.put("prodName", bclgg.getProdName());
        dataMap.put("regCode", bclgg.getRegCode());
        dataMap.put("nocreateReson", bclgg.getNocreateReason());
        // 设置公告、报告字样
        reportCommonService.setPubString(bclgg.getRaiseType(), dataMap);
        // 设置是否有代销机构字样
        reportCommonService.setSaleString(prodId, dataMap);
        String pubDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("startdate")) {
            pubDateStr = (String)paraMap.get("startdate");
        }
        dataMap.put("pubDate",DateUtil.format(DateUtil.parse(pubDateStr), DateUtil.DEFAULT_DATE_FORMAT_CN));
        return result;
    }
}
