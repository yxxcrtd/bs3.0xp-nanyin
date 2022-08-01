
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportJeshMapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportJeshDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巨额赎回公告/报告模板
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportJesh001Service
 * @since 1.0
 */
@Service("reportJesh001Service")
public class ReportJesh001Service implements IReportDataService {

    @Autowired
    private ReportJeshMapper reportJeshMapper;

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
        String pubDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("enddate")) {
            pubDateStr = paraMap.get("enddate").toString();
        }
        searchparam.put("rptIssueDate", DateUtil.parse(pubDateStr));
        ReportJeshDto jeshDto = reportJeshMapper.queryJesh(searchparam);
        if (jeshDto == null) {
            return result;
        }
        // 产品名称
        dataMap.put("prodName", jeshDto.getProdName());
        // 产品登记编码
        dataMap.put("regCode", jeshDto.getRegCode());
        // 内部销售代码
        dataMap.put("saleCode", jeshDto.getSaleCode());
        // 触发时间
        if (jeshDto.getRedDate() != null) {
            dataMap.put("redDate", DateUtil.format(jeshDto.getRedDate(), DateUtil.DEFAULT_DATE_FORMAT_CN));
        } else {
            dataMap.put("redDate", "XXXX年XX月XX日");
        }
        // 触发的情形说明文字
        dataMap.put("redRemark", jeshDto.getRedRemark());
        // 拒绝赎回说明（文字）
        dataMap.put("refRemark", jeshDto.getRefRemark());
        // 设置公告、报告字样
        reportCommonService.setPubString(jeshDto.getRaiseType(), dataMap);
        // 设置是否有代销机构字样
        List<String> prodIds = new ArrayList<>();
        prodIds.add(prodId);
        reportCommonService.setSaleString(prodIds, dataMap);
        // 发布日期
        dataMap.put("pubDate",DateUtil.format(DateUtil.parse(pubDateStr), DateUtil.DEFAULT_DATE_FORMAT_CN));
        return result;
    }
}
