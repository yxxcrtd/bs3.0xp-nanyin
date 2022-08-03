
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportCpflyhMapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.Report1FlncpDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportProdfeeDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 一费率多产品费率优惠公告/报告模板1
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see Report1Flncp002Service
 * @since 1.0
 */
@Service("report1Flncp002Service")
public class Report1Flncp002Service implements IReportDataService {

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
        if (paraMap.get("feename") == null) {
            return result;
        }
        String feename = paraMap.get("feename").toString();
        Map<String,Object> searchparam = new HashMap<>();
        searchparam.put("feename", feename);
        if (null != paraMap.get("pprodid")) {
            List<String> prodlist = StringUtil.splitToList(GlobalConstant.COMMA, paraMap.get("pprodid").toString());
            searchparam.put("prodlist", prodlist);
        }
        String pubDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("enddate")) {
            pubDateStr = paraMap.get("enddate").toString();
        }
        searchparam.put("rptIssueDate", DateUtil.parse(pubDateStr));
        Report1FlncpDto cpnflDto = reportCpflyhMapper.query1Flncp(searchparam);
        if (cpnflDto == null) {
            return result;
        }
        // 费用名称
        dataMap.put("feeName", cpnflDto.getFeename());
        // 优惠说明
        dataMap.put("remark", cpnflDto.getRemark());
        // 设置公告、报告字样
        reportCommonService.setPubString(cpnflDto.getRaiseType(), dataMap);
        // 设置是否有代销机构字样
        reportCommonService.setSaleString(StringUtil.splitToList(GlobalConstant.COMMA, cpnflDto.getProdId()), dataMap);
        // 发布日期
        dataMap.put("pubDate",DateUtil.format(DateUtil.parse(pubDateStr), DateUtil.DEFAULT_DATE_FORMAT_CN));
        // 费率优惠明细
        List<ReportProdfeeDto> prodfeeDtos = reportCpflyhMapper.qureyProdlist(searchparam);
        if (prodfeeDtos != null && prodfeeDtos.size() > 0) {
            List<Map<String, Object>> prodlist = new ArrayList<>();
            prodfeeDtos.forEach(x -> {
                Map<String, Object> prodmap = new HashMap<>();
                // 产品名称
                prodmap.put("prodName", StringUtil.isBlank(x.getProdName()) ? GlobalConstant.SPLIT : x.getProdName());
                // 内部销售代码
                prodmap.put("prodId", StringUtil.isBlank(x.getProdId()) ? GlobalConstant.SPLIT : x.getProdId());
                // 费用名称
                prodmap.put("feename", cpnflDto.getFeename());
                // 本次优惠前费率
                prodmap.put("thisPreFeerate", StringUtil.isBlank(x.getThisPreFeerate()) ? GlobalConstant.SPLIT : x.getThisPreFeerate());
                // 本次优惠后费率
                prodmap.put("thisAfterFeerate", StringUtil.isBlank(x.getThisAfterFeerate()) ? GlobalConstant.SPLIT : x.getThisAfterFeerate());
                // 本次优惠结束后费率
                prodmap.put("thisEndFeerate", StringUtil.isBlank(x.getThisEndFeerate()) ? GlobalConstant.SPLIT : x.getThisEndFeerate());
                prodlist.add(prodmap);
            });
            dataMap.put("prodlist", prodlist);
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
