
package com.joyintech.tams.disclosure.busicommon.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
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
        //产品代码：子产品代码为空的话，取父产品代码
        String prodId =  (paraMap.get("prodid") == null ? (String)paraMap.get("pprodid") : (String)paraMap.get("prodid"));
        //年份
        String year = (String)paraMap.get("year");
        try {
            if(StringUtil.isNotEmpty(prodId)&&StringUtil.isNotEmpty(year)){
                Map<String, Object> searchparam = new HashMap<>();
                searchparam.put("prodId", prodId);
                //查询产品相关信息
                reportKfqgg001Dto =  reportKfqgg001Mapper.getProdInfo(searchparam);
                //startDate需要考虑子产品成立日
                //由于共用一个母产品排期计划，所以需要考虑子产品的产品成立日、产品到期日
                String startDate = year + "-01-01";
                String endDate = year + "-12-31";
                if(reportKfqgg001Dto != null){
                    if(reportKfqgg001Dto.getSetDate() != null){
                        if(DateUtil.compareDate(DateUtil.parseDate(startDate,"yyyy-MM-dd"),reportKfqgg001Dto.getSetDate())<0){
                            //需要取产品成立日之后的排期计划  所以 开始日 = 成立日 + 1
                            startDate =DateUtil.addDay(DateUtil.format(reportKfqgg001Dto.getSetDate()),1) ;
                        }
                    }
                    if(reportKfqgg001Dto.getmDate() != null){
                        if(DateUtil.compareDate(DateUtil.parseDate(endDate,"yyyy-MM-dd"),reportKfqgg001Dto.getmDate())>0){
                            endDate = DateUtil.format(reportKfqgg001Dto.getmDate());
                        }
                    }
                }
                searchparam.put("startDate", startDate);
                searchparam.put("endDate", endDate);
                //设置年份
                reportKfqgg001Dto.setYear(year);
                //设置公告日 = 平台日
                reportKfqgg001Dto.setPlateDate(DateUtil.format(DateUtil.platDateFromCache(),DateUtil.DEFAULT_DATE_FORMAT_CN));
                //查询年度开放计划 = 根据产品排期表：取运作起始日落在本年度的开放计划数据
                List<ReportOpenPlanDto> openList = reportKfqgg001Mapper.getOpenPlan(searchparam);
                reportKfqgg001Dto.setOpenList(openList);
                logger.info("产品开放期公告/报告【{}】生成成功", JsonUtils.object2Json(paraMap));
            }
        } catch (Exception e) {
            logger.error("产品开放期公告/报告【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        //跑批日
        reportKfqgg001Dto.setEnddate(DateUtil.format(DateUtil.platDateFromCache()));
        resultMap.add(ReportCollectionUtils.bean2Map(reportKfqgg001Dto));
        return resultMap;
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        repReveal.setProdid(prodId);
        repReveal.setRepId((String) paraMap.get("repId"));
        repReveal.setEndDate(DateUtil.platDateFromCache());
        //封装导出文件名称
        Map<String, Object> searchparam = new HashMap<>();
        searchparam.put("prodId", prodId);
        ReportKfqgg001Dto reportKfqgg001Dto =  reportKfqgg001Mapper.getProdInfo(searchparam);
        if(reportKfqgg001Dto != null){
            paraMap.put("cusFileName", reportKfqgg001Dto.getProdName());
        }
        return repReveal;
    }
}
