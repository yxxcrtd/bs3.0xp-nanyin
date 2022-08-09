
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportCpflyhMapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.Report1CpnflDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportProdfeeDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 一产多费费率优惠公告/报告模板2
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see Report1Cpnfl002Service
 * @since 1.0
 */
@Service("report1Cpnfl002Service")
public class Report1Cpnfl002Service implements IReportDataService {

    @Autowired
    private ReportCpflyhMapper reportCpflyhMapper;

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
        List<Map<String, Object>> feelist = new ArrayList<>();
        dataMap.put("feelist", feelist);
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
        if (null != paraMap.get("feename")) {
            List<String> feename = StringUtil.splitToList(GlobalConstant.COMMA, paraMap.get("feename").toString());
            searchparam.put("feelist", feename);
        }
        String pubDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("enddate")) {
            pubDateStr = paraMap.get("enddate").toString();
        }
        searchparam.put("rptIssueDate", DateUtil.parse(pubDateStr));
        Report1CpnflDto cpnflDto = reportCpflyhMapper.query1Cpnfl(searchparam);
        if (cpnflDto == null) {
            return result;
        }
        // 产品名称
        dataMap.put("prodName", cpnflDto.getProdName());
        // 产品登记编码
        dataMap.put("regCode", cpnflDto.getRegCode());
        // 内部销售代码
        dataMap.put("saleCode", cpnflDto.getSaleCode());
        // 费用名称
        dataMap.put("feeName", cpnflDto.getFeeName());
        // 优惠说明
        dataMap.put("remark", cpnflDto.getRemark());
        // 设置公告、报告字样
        reportCommonService.setPubString(cpnflDto.getRaiseType(), dataMap);
        // 设置是否有代销机构字样
        List<String> prodIds = new ArrayList<>();
        prodIds.add(prodId);
        reportCommonService.setSaleString(prodIds, dataMap);
        // 发布日期
        dataMap.put("pubDate",DateUtil.format(DateUtil.parse(pubDateStr), DateUtil.DEFAULT_DATE_FORMAT_CN));
        // 费率优惠明细
        searchparam.put("regCode", cpnflDto.getRegCode());
        List<ReportProdfeeDto> prodfeeDtos = reportCpflyhMapper.qureyFeelist(searchparam);
        if (prodfeeDtos != null && prodfeeDtos.size() > 0) {
            prodfeeDtos.forEach(x -> {
                Map<String, Object> feemap = new HashMap<>();
                // 内部销售代码
                feemap.put("prodId", StringUtil.isBlank(x.getProdId()) ? GlobalConstant.SPLIT : x.getProdId());
                // 费用名称
                feemap.put("feename", StringUtil.isBlank(x.getFeename()) ? GlobalConstant.SPLIT : x.getFeename());
                // 本次优惠前费率
                feemap.put("thisPreFeerate", StringUtil.isBlank(x.getThisPreFeerate()) ? GlobalConstant.SPLIT : x.getThisPreFeerate());
                // 本次优惠后费率
                feemap.put("thisAfterFeerate", StringUtil.isBlank(x.getThisAfterFeerate()) ? GlobalConstant.SPLIT : x.getThisAfterFeerate());
                // 本次优惠结束后费率
                feemap.put("thisEndFeerate", StringUtil.isBlank(x.getThisEndFeerate()) ? GlobalConstant.SPLIT : x.getThisEndFeerate());
                feelist.add(feemap);
            });
        }
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
