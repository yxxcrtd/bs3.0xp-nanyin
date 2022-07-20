
package com.joyintech.tams.disclosure.information.report.service;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
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
@Service
public class ReportBclgg001Service implements IReportDataService {

    @Autowired
    private ReportBclgg001Mapper reportBclgg001Mapper;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

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
        ReportBclgg001Dto bclgg = reportBclgg001Mapper.queryBclgg(searchparam);
        if (bclgg == null) {
            return result;
        }
        dataMap.put("${prodName}", bclgg.getProdName());
        dataMap.put("${regCode}", bclgg.getRegCode());
        dataMap.put("${nocreateReson}", bclgg.getNocreateReason());
        String raiseType = bclgg.getRaiseType();
        if (StringUtil.equals(raiseType, DictCodeListExt2.募集方式_中债_公募.getValue())) {
            dataMap.put("${pubType}", "公告");
            dataMap.put("${pubString}", "特此公告。");
        } else if (StringUtil.equals(raiseType, DictCodeListExt2.募集方式_中债_私募.getValue())) {
            dataMap.put("${pubType}", "报告");
        }
        if (StringUtil.equals(bclgg.getIsSalesAgency(), DictCodeListExt2.是否_是.getValue())) {
            dataMap.put("${saleString1}", "本理财产品代销机构或");
            dataMap.put("${saleString2}", "代销机构及");
        }
        Date puDate = commonPlatformDateService.getSysDate();
        if (null != paraMap.get("startdate")) {
            puDate = (java.sql.Date)paraMap.get("startdate");
        }
        dataMap.put("${pubDate}",DateUtil.format(puDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
        return result;
    }
}
