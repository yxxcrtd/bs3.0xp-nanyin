
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportCpflyhMapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.Report1FlncpDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportProdfeeDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 一费率多产品费率优惠公告/报告模板1
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see Report1Flncp001Service
 * @since 1.0
 */
@Service("report1Flncp001Service")
public class Report1Flncp001Service implements IReportDataService {

    @Autowired
    private ReportCpflyhMapper reportCpflyhMapper;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private ReportCommonService reportCommonService;

    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        Map<String,Object> dataMap = new HashMap<>();
        this.initMap(dataMap);
        List<Map<String,Object>> result = new ArrayList<>();
        result.add(dataMap);
        List<Map<String, Object>> prodlist = new ArrayList<>();
        dataMap.put("prodlist", prodlist);
        if (paraMap.get("feename") == null) {
            return result;
        }
        String feename = paraMap.get("feename").toString();
        Map<String,Object> searchparam = new HashMap<>();
        searchparam.put("feename", feename);
        List<String> prodlist1=new ArrayList<>();
        if (null != paraMap.get("pprodid")) {
            prodlist1 = StringUtil.splitToList(GlobalConstant.COMMA, paraMap.get("pprodid").toString());
            searchparam.put("prodlist", prodlist1);
        }
        String pubDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("enddate")) {
            pubDateStr = paraMap.get("enddate").toString();
        }
        searchparam.put("rptIssueDate", DateUtil.parse(pubDateStr));
        String rptType = paraMap.get("uri").toString();
        if (rptType.equals(DisReportType.DFLDCPMB1_GM) ) {
            searchparam.put("raiseType", DictCodeListExt2.募集方式_中债_公募.getValue());
        } else {
            searchparam.put("raiseType", DictCodeListExt2.募集方式_中债_私募.getValue());
        }
        Report1FlncpDto cpnflDto = reportCpflyhMapper.query1Flncp(searchparam);
        if (cpnflDto == null) {
            return result;
        }
        //新增校验产品缺失情况
        List<String>  serchProdList=Arrays.asList(cpnflDto.getProdId().split(","));
        if(serchProdList.size()<prodlist1.size()){
            dataMap.put("nullFlag", null);
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
            prodfeeDtos.forEach(x -> {
                Map<String, Object> prodmap = new HashMap<>();
                // 产品名称
                prodmap.put("prodName", StringUtil.isBlank(x.getProdName()) ? GlobalConstant.SPLIT : x.getProdName());
                // 内部销售代码
                prodmap.put("prodId", StringUtil.isBlank(x.getProdId()) ? GlobalConstant.SPLIT : x.getProdId());
                // 优惠前费率
                prodmap.put("preFeerate", StringUtil.isBlank(x.getPreFeerate()) ? GlobalConstant.SPLIT : x.getPreFeerate());
                // 优惠后费率
                prodmap.put("afterFeerate", StringUtil.isBlank(x.getAfterFeerate()) ? GlobalConstant.SPLIT : x.getAfterFeerate());
                // 优惠起始时间
                prodmap.put("startDate", x.getStartDate() == null ? GlobalConstant.SPLIT :
                        DateUtil.format(x.getStartDate(), DateUtil.DEFAULT_DATE_FORMAT_CN));
                // 优惠结束时间
                prodmap.put("endDate", x.getEndDate() == null ? GlobalConstant.SPLIT :
                        DateUtil.format(x.getEndDate(), DateUtil.DEFAULT_DATE_FORMAT_CN));
                prodlist.add(prodmap);
            });
        }
        dataMap.put("enddate",pubDateStr);
        return result;
    }

    private void initMap(Map<String,Object> dataMap) {
        dataMap.put("feeName", "");
        dataMap.put("remark", "");
        dataMap.put("pubType", "");
        dataMap.put("pubString", "");
        dataMap.put("saleString1", "");
        dataMap.put("saleString2", "");
        dataMap.put("pubDate", "");
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
