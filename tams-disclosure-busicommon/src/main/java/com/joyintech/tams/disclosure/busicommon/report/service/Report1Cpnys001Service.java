
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportCpystzMapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.Report1CpnysDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportProdadjDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 单产品多要素调整公告/报告模板
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see Report1Cpnys001Service
 * @since 1.0
 */
@Service("report1Cpnys001Service")
public class Report1Cpnys001Service implements IReportDataService {

    @Autowired
    private ReportCpystzMapper reportCpystzMapper;

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
        if (null != paraMap.get("elementname")) {
            List<String> fields = StringUtil.splitToList(GlobalConstant.COMMA, paraMap.get("elementname").toString());
            searchparam.put("fieldlist", fields);
        }
        String pubDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("enddate")) {
            pubDateStr = paraMap.get("enddate").toString();
        }
        searchparam.put("rptIssueDate", DateUtil.parse(pubDateStr));
        Report1CpnysDto cpnysDto = reportCpystzMapper.query1Cpnys(searchparam);
        if (cpnysDto == null) {
            return result;
        }
        // 产品名称
        dataMap.put("prodName", cpnysDto.getProdName());
        // 产品登记编码
        dataMap.put("regCode", cpnysDto.getRegCode());
        // 调整原因
        dataMap.put("adjustReason", cpnysDto.getAdjustReason());
        // 调整补充说明1
        dataMap.put("remark1", cpnysDto.getRemark1());
        // 调整补充说明2
        dataMap.put("remark2", cpnysDto.getRemark2());
        // 设置公告、报告字样
        reportCommonService.setPubString(cpnysDto.getRaiseType(), dataMap);
        // 设置是否有代销机构字样
        List<String> prodIds = new ArrayList<>();
        prodIds.add(prodId);
        reportCommonService.setSaleString(prodIds, dataMap);
        // 发布日期
        dataMap.put("pubDate",DateUtil.format(DateUtil.parse(pubDateStr), DateUtil.DEFAULT_DATE_FORMAT_CN));
        // 产品要素调整明细
        searchparam.put("regCode", cpnysDto.getRegCode());
        List<ReportProdadjDto> prodadjDtos = reportCpystzMapper.qureyFieldlist(searchparam);
        if (prodadjDtos != null && prodadjDtos.size() > 0) {
            List<Map<String, Object>> fieldlist = new ArrayList<>();
            prodadjDtos.forEach(x -> {
                Map<String, Object> fieldmap = new HashMap<>();
                // 内部销售代码
                fieldmap.put("prodId", StringUtil.isBlank(x.getProdId()) ? GlobalConstant.SPLIT : x.getProdId());
                // 要素名称
                fieldmap.put("adjField", StringUtil.isBlank(x.getAdjField()) ? GlobalConstant.SPLIT : x.getAdjField());
                // 调整前
                fieldmap.put("preAdjust", StringUtil.isBlank(x.getPreAdjust()) ? GlobalConstant.SPLIT : x.getPreAdjust());
                // 调整后
                fieldmap.put("afterAdjust", StringUtil.isBlank(x.getAfterAdjust()) ? GlobalConstant.SPLIT : x.getAfterAdjust());
                // 生效时间
                fieldmap.put("effDate", x.getEffDate() == null ? GlobalConstant.SPLIT :
                        DateUtil.format(x.getEffDate(), DateUtil.DEFAULT_DATE_FORMAT_CN));
                fieldlist.add(fieldmap);
            });
            dataMap.put("fieldlist", fieldlist);
        }
        return result;
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (String)paraMap.get("pprodid");
        Date enddate = DateUtil.parse((String)paraMap.get("enddate"));
        repReveal.setProdid(prodId);
        repReveal.setEndDate(enddate);
        return repReveal;
    }
}
