
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportKfqgg001Mapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportKfqgg001Dto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportOpenPlanDto;
import com.joyintech.tams.disclosure.busicommon.report.util.ReportCollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 产品开放期公告/报告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportKfqgg001Service
 * @since 1.0
 */
@Service(value = "ReportKfqgg001Service")
public class ReportKfqgg001Service implements IReportDataService {

    @Autowired
    private ReportKfqgg001Mapper reportKfqgg001Mapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 产品开放期公告/报告
     * 1.若“募集方式”为“公募”，则采用产品开放期公告模板，若“募集方式”为“私募”，则采用产品开放期报告模板；
     * 2.公告生成日期：手工触发生成；
     * 3.生成规则：若产品是分级产品，则需要以子产品维度生成多个公告/报告；
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("产品开放期公告/报告【{}】开始生成", JsonUtils.object2Json(paraMap));
        List<Map<String, Object>> resultMap = new ArrayList<>();
        ReportKfqgg001Dto reportKfqgg001Dto = new ReportKfqgg001Dto();
        try {
            //产品代码：子产品代码为空的话，取父产品代码
            String prodId =  (paraMap.get("prodid") == null ? (String)paraMap.get("pprodid") : (String)paraMap.get("prodid"));
            //年份
            String year = (String)paraMap.get("year");
            if(StringUtil.isNotEmpty(prodId)&&StringUtil.isNotEmpty(year)){
                Map<String, Object> searchparam = new HashMap<>();
                searchparam.put("prodId", prodId);
                searchparam.put("startDate", year + "-01-01");
                searchparam.put("endDate", year + "-12-31");
                //查询产品相关信息
                reportKfqgg001Dto =  reportKfqgg001Mapper.getProdInfo(searchparam);
                //设置年份
                reportKfqgg001Dto.setYear(year);
                //设置公告日 = 平台日
                reportKfqgg001Dto.setPlateDate(DateUtil.format(DateUtil.platDateFromCache(),DateUtil.DEFAULT_DATE_FORMAT_CN));
                //查询年度开放计划 = 根据产品排期表：取运作起始日落在本年度的开放计划数据
                List<ReportOpenPlanDto> openList = reportKfqgg001Mapper.getOpenPlan(searchparam);
                reportKfqgg001Dto.setOpenList(openList);
            }
            resultMap.add(ReportCollectionUtils.bean2Map(reportKfqgg001Dto));
            logger.info("产品开放期公告/报告【{}】生成成功", JsonUtils.object2Json(paraMap));
        } catch (Exception e) {
            logger.error("产品开放期公告/报告【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        return resultMap;
    }
}
