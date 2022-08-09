
package com.joyintech.tams.disclosure.busicommon.report.service;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportBclggMapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportBclggDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
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
    private ReportBclggMapper reportBclggMapper;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private ReportCommonService reportCommonService;

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
        ReportBclggDto bclgg = reportBclggMapper.queryBclgg(searchparam);
        if (bclgg == null) {
            return result;
        }
        dataMap.put("prodName", bclgg.getProdName());
        dataMap.put("regCode", bclgg.getRegCode());
        dataMap.put("nocreateReson", bclgg.getNocreateReason());
        // 设置公告、报告字样
        reportCommonService.setPubString(bclgg.getRaiseType(), dataMap);
        // 设置是否有代销机构字样
        List<String> prodIds = new ArrayList<>();
        prodIds.add(prodId);
        reportCommonService.setSaleString(prodIds, dataMap);
        String pubDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("enddate")) {
            pubDateStr = (String)paraMap.get("enddate");
        }
        dataMap.put("pubDate",DateUtil.format(DateUtil.parse(pubDateStr), DateUtil.DEFAULT_DATE_FORMAT_CN));
        dataMap.put("enddate",pubDateStr);
        return result;
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        Date enddate = DateUtil.parse((String)paraMap.get("enddate"));
        repReveal.setProdid(prodId);
        repReveal.setEndDate(enddate);
        paraMap.put("enddate_filename", DateUtil.format(enddate, DateUtil.DEFAULT_OTHER_FORMAT));
        return repReveal;
    }
}
