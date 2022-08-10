
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.DisProdAdjustMapper;
import com.joyintech.tams.common.base.entity.DisProdAdjust;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportCpystzMapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.Report1YsncpDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportProdadjDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 单要素多产品调整公告/报告模板
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see Report1Ysncp001Service
 * @since 1.0
 */
@Service("report1Ysncp001Service")
public class Report1Ysncp001Service implements IReportDataService {

    @Autowired
    private ReportCpystzMapper reportCpystzMapper;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private ReportCommonService reportCommonService;

    @Autowired
    private DisProdAdjustMapper disProdAdjustMapper;

    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        Map<String,Object> dataMap = new HashMap<>();
        List<Map<String,Object>> result = new ArrayList<>();
        result.add(dataMap);
        List<Map<String, Object>> prodlist = new ArrayList<>();
        dataMap.put("prodlist", prodlist);
        if (paraMap.get("elementname") == null) {
            return result;
        }
        String adjField = paraMap.get("elementname").toString();
        Map<String,Object> searchparam = new HashMap<>();
        searchparam.put("adjField", adjField);
        if (null != paraMap.get("pprodid")) {
            List<String> prodlist1 = StringUtil.splitToList(GlobalConstant.COMMA, paraMap.get("pprodid").toString());
            searchparam.put("prodlist", prodlist1);
        }
        String pubDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("enddate")) {
            pubDateStr = paraMap.get("enddate").toString();
        }
        searchparam.put("rptIssueDate", DateUtil.parse(pubDateStr));
        String rptType = paraMap.get("uri").toString();
        if (rptType.equals(DisReportType.DYSDCPTZMB_GM) ) {
            searchparam.put("raiseType", DictCodeListExt2.募集方式_中债_公募.getValue());
        } else {
            searchparam.put("raiseType", DictCodeListExt2.募集方式_中债_私募.getValue());
        }
        Report1YsncpDto cpnysDto = reportCpystzMapper.query1Ysncp(searchparam);
        if (cpnysDto == null) {
            return result;
        }
        // 调整要素
        dataMap.put("adjField", cpnysDto.getAdjField());
        // 调整原因
        dataMap.put("adjustReason", cpnysDto.getAdjustReason());
        // 生效时间
        if (cpnysDto.getEffDate() != null) {
            dataMap.put("effDate", DateUtil.format(cpnysDto.getEffDate(), DateUtil.DEFAULT_DATE_FORMAT_CN));
        } else {
            dataMap.put("effDate", "XXXX年XX月XX日");
        }
        // 调整补充说明1
        dataMap.put("remark1", cpnysDto.getRemark1());
        // 调整补充说明2
        dataMap.put("remark2", cpnysDto.getRemark2());
        // 设置公告、报告,公募、私募字样
        reportCommonService.setPubString(cpnysDto.getRaiseType(), dataMap);
        // 设置是否有代销机构字样
        reportCommonService.setSaleString(StringUtil.splitToList(GlobalConstant.COMMA, cpnysDto.getProdId()), dataMap);
        // 发布日期
        dataMap.put("pubDate",DateUtil.format(DateUtil.parse(pubDateStr), DateUtil.DEFAULT_DATE_FORMAT_CN));
        // 产品要素调整明细
        List<ReportProdadjDto> prodadjDtos = reportCpystzMapper.qureyProdlist(searchparam);
        if (prodadjDtos != null && prodadjDtos.size() > 0) {
            prodadjDtos.forEach(x -> {
                Map<String, Object> prodmap = new HashMap<>();
                // 产品名称
                prodmap.put("prodName", StringUtil.isBlank(x.getProdName()) ? GlobalConstant.SPLIT : x.getProdName());
                // 内部销售代码
                prodmap.put("prodId", StringUtil.isBlank(x.getProdId()) ? GlobalConstant.SPLIT : x.getProdId());
                // 调整前
                prodmap.put("preAdjust", StringUtil.isBlank(x.getPreAdjust()) ? GlobalConstant.SPLIT : x.getPreAdjust());
                // 调整后
                prodmap.put("afterAdjust", StringUtil.isBlank(x.getAfterAdjust()) ? GlobalConstant.SPLIT : x.getAfterAdjust());
                // 生效时间
                prodmap.put("effDate", x.getEffDate() == null ? GlobalConstant.SPLIT :
                        DateUtil.format(x.getEffDate(), DateUtil.DEFAULT_DATE_FORMAT_CN));
                prodlist.add(prodmap);
            });
        }
        dataMap.put("enddate",pubDateStr);
        return result;
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        String adjField = (String)paraMap.get("elementname");
        DisProdAdjust disProdAdjust = disProdAdjustMapper.selectByPrimaryKey(adjField);
        Date enddate = DateUtil.parse((String)paraMap.get("enddate"));
        repReveal.setProdid(prodId);
        repReveal.setEndDate(enddate);
        paraMap.put("fieldname", disProdAdjust.getAdjField());
        paraMap.put("enddate_filename", DateUtil.format(enddate, DateUtil.DEFAULT_OTHER_FORMAT));
        return repReveal;
    }
}
