
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportMzjz001Mapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportMzjz001Dto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportOpenPlanDto;
import com.joyintech.tams.disclosure.busicommon.report.util.ReportCollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 每周净值公告（公募）/（私募）
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportMzjz001Service
 * @since 1.0
 */
@Service(value = "ReportMzjz001Service")
public class ReportMzjz001Service implements IReportDataService {

    @Autowired
    private ReportMzjz001Mapper reportMzjz001Mapper;

    @Autowired
    private HolidayService holidayService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     1.适用于“开放频率”不是“日开”的产品，若产品“募集方式”是“公募”采用每周净值公告模板，若产品募集方式是“私募”采用每种产品净值报告模板；
     2.公告生成时点：按照交易所日历，在每周的第一个工作日上午8：00之前生成
     3.生成规则：
     需要按照交易所日历生成公告，公布交易所日历中每周最后一个工作日的单位净值及产品资产净值；
     若产品是分级产品，需要按照子产品维度来生成数据；
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("每周净值公告（公募）/（私募）【{}】开始生成", JsonUtils.object2Json(paraMap));
        List<Map<String, Object>> resultMap = new ArrayList<>();
        ReportMzjz001Dto reportMzjz001Dto = new ReportMzjz001Dto();
        try {
            //产品代码：子产品代码为空的话，取父产品代码
            String prodId =  (paraMap.get("prodid") == null ? (String)paraMap.get("pprodid") : (String)paraMap.get("prodid"));
            //净值日
            String valueDate = (String)paraMap.get("enddate");
            if(StringUtil.isNotEmpty(prodId)&&StringUtil.isNotEmpty(valueDate)){
                Map<String, Object> searchparam = new HashMap<>();
                searchparam.put("prodId", prodId);
                searchparam.put("valueDate",valueDate);
                //查询产品相关信息
                reportMzjz001Dto =  reportMzjz001Mapper.getProdInfo(searchparam);
                //设置净值日
                if(reportMzjz001Dto != null){
                    //净值日格式化
                    reportMzjz001Dto.setValueDateT(DateUtil.format(DateUtil.parse(valueDate),DateUtil.DEFAULT_DATE_FORMAT_CN));
                    //产品成立日格式化
                    reportMzjz001Dto.setSetDateT(DateUtil.format(reportMzjz001Dto.getSetDate(),DateUtil.DEFAULT_DATE_FORMAT_CN));
                    //产品到期日格式化
                    reportMzjz001Dto.setmDateT(DateUtil.format(reportMzjz001Dto.getmDate(),DateUtil.DEFAULT_DATE_FORMAT_CN));
                    //公告日 = 净值日 + 1 工作日
                    searchparam.put("busi_date", DateUtil.parse(valueDate));
                    searchparam.put("is_holiday", "0");
                    searchparam.put("market_code", "04");
                    Date announceDate = holidayService.getNextWorkDay(searchparam);
                    if (announceDate == null) {
                        announceDate = DateUtil.addDay(DateUtil.parse(valueDate), 1);
                    }
                    reportMzjz001Dto.setAnnounceT(DateUtil.format(announceDate,DateUtil.DEFAULT_DATE_FORMAT_CN));
                    //跑批日
                    reportMzjz001Dto.setEnddate(valueDate);

                }
            }
            resultMap.add(ReportCollectionUtils.bean2Map(reportMzjz001Dto));
            logger.info("每周净值公告（公募）/（私募）【{}】生成成功", JsonUtils.object2Json(paraMap));
        } catch (Exception e) {
            logger.error("每周净值公告（公募）/（私募）【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        return resultMap;
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        repReveal.setProdid(prodId);
        repReveal.setRepId((String) paraMap.get("repId"));
        repReveal.setEndDate( (Date)(paraMap.get("reportDate")));
        return repReveal;
    }
}
