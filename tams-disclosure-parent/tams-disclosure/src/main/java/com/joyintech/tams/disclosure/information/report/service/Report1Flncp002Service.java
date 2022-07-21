
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.information.report.dao.ReportCpflyhMapper;
import com.joyintech.tams.disclosure.information.report.dto.Report1FlncpDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportProdfeeDto;
import com.joyintech.tams.disclosure.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (null != paraMap.get("startdate")) {
            pubDateStr = paraMap.get("startdate").toString();
        }
        searchparam.put("rptIssueDate", DateUtil.parse(pubDateStr));
        Report1FlncpDto cpnflDto = reportCpflyhMapper.query1Flncp(searchparam);
        if (cpnflDto == null) {
            return result;
        }
        // 费用名称
        dataMap.put("feeName", feename);
        // 优惠说明
        dataMap.put("remark", cpnflDto.getRemark());
        // 设置公告、报告字样
        reportCommonService.setPubString(cpnflDto.getRaiseType(), dataMap);
        // 设置是否有代销机构字样
        reportCommonService.setSaleString(cpnflDto.getProdId(), dataMap);
        // 发布日期
        dataMap.put("pubDate",DateUtil.format(DateUtil.parse(pubDateStr), DateUtil.DEFAULT_DATE_FORMAT_CN));
        // 费率优惠明细
        List<ReportProdfeeDto> prodfeeDtos = reportCpflyhMapper.qureyFeelist(searchparam);
        if (prodfeeDtos != null && prodfeeDtos.size() > 0) {
            List<Map<String, Object>> feelist = new ArrayList<>();
            prodfeeDtos.forEach(x -> {
                Map<String, Object> feemap = new HashMap<>();
                // 产品名称
                feemap.put("prodName", StringUtil.isBlank(x.getProdName()) ? GlobalConstant.SPLIT : x.getProdName());
                // 内部销售代码
                feemap.put("prodId", StringUtil.isBlank(x.getProdId()) ? GlobalConstant.SPLIT : x.getProdId());
                // 费用名称
                feemap.put("feename", feename);
                DecimalFormat df = new DecimalFormat("0.00%");
                // 本次优惠前费率
                feemap.put("thisPreFeerate", x.getThisPreFeerate() == null ? GlobalConstant.SPLIT :
                        df.format(x.getThisPreFeerate().multiply(new BigDecimal(100))));
                // 本次优惠后费率
                feemap.put("thisAfterFeerate", x.getThisAfterFeerate() == null ? GlobalConstant.SPLIT :
                        df.format(x.getThisAfterFeerate().multiply(new BigDecimal(100))));
                // 本次优惠结束后费率
                feemap.put("thisEndFeerate", x.getThisEndFeerate() == null ? GlobalConstant.SPLIT :
                        df.format(x.getThisEndFeerate().multiply(new BigDecimal(100))));
                feelist.add(feemap);
            });
            dataMap.put("feelist", feelist);
        }
        return result;
    }
}
