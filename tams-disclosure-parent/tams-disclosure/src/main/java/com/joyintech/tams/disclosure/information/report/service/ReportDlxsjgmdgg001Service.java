
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.information.report.dao.ReportDlxsjgmdggMapper;
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
 * @see ReportDlxsjgmdgg001Service
 * @since 1.0
 */
@Service("reportDlxsjgmdgg001Service")
public class ReportDlxsjgmdgg001Service implements IReportDataService {

    @Autowired
    private ReportDlxsjgmdggMapper reportDlxsjgmdggMapper;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private HolidayService holidayService;

    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        Map<String,Object> dataMap = new HashMap<>();
        List<Map<String,Object>> result = new ArrayList<>();
        result.add(dataMap);
        Map<String,Object> searchparam = new HashMap<>();
        String curDateStr = commonPlatformDateService.getSysDateStr();
        if (null != paraMap.get("enddate")) {
            curDateStr = (String)paraMap.get("enddate");
        }
        Date curDate = DateUtil.parse(curDateStr);
        searchparam.put("curDate", curDate);
        // 代销机构名单
        String dxjgmd = reportDlxsjgmdggMapper.queryDlxsjmd(searchparam);
        dataMap.put("saleOrgName", dxjgmd);
        // 报告日期
        Map<String,Object> holidaysearchparam = new HashMap<>();
        holidaysearchparam.put("busi_date", curDate);
        holidaysearchparam.put("is_holiday", "0");
        holidaysearchparam.put("market_code", "01");
        Date pubDate = holidayService.getNextWorkDay(holidaysearchparam);
        if (pubDate == null) {
            pubDate = DateUtil.addDay(curDate, -1);
        }
        dataMap.put("pubDate",DateUtil.format(pubDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
        return result;
    }
}
