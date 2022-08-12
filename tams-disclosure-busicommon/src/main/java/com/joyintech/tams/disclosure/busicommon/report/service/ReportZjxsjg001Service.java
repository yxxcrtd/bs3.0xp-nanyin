
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportZjxsjgMapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportZjxsjgDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 增加销售机构公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportZjxsjg001Service
 * @since 1.0
 */
@Service("reportZjxsjg001Service")
public class ReportZjxsjg001Service implements IReportDataService {

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private ReportZjxsjgMapper reportZjxsjgMapper;
    
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        Map<String,Object> dataMap = new HashMap<>();
        this.initMap(dataMap);
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
        ReportZjxsjgDto zjxsjg = reportZjxsjgMapper.queryZjxsjg(searchparam);
        if (zjxsjg == null) {
            return result;
        }
        dataMap.put("prodName", zjxsjg.getProdName());
        dataMap.put("regCode", zjxsjg.getRegCode());
        String pubDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("enddate")) {
            pubDateStr = (String)paraMap.get("enddate");
        }
        dataMap.put("pubDate",DateUtil.format(DateUtil.parse(pubDateStr), DateUtil.DEFAULT_DATE_FORMAT_CN));
        dataMap.put("enddate",pubDateStr);
        return result;
    }

    private void initMap(Map<String,Object> dataMap) {
        dataMap.put("prodName", "");
        dataMap.put("regCode", "");
        dataMap.put("pubDate", "");
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        Date enddate = DateUtil.parse((String)paraMap.get("enddate"));
        repReveal.setProdid(prodId);
        repReveal.setEndDate(enddate);
        return repReveal;
    }
}
