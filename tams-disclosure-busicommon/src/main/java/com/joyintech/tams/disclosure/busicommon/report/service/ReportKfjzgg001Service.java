
package com.joyintech.tams.disclosure.busicommon.report.service;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportRkcpjz001Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.DisProdBonusplan;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportKfjzgg001Mapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportKfjzgg001Dto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportOpenPlanDto;
import com.joyintech.tams.disclosure.busicommon.report.util.ReportCollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;

/**
 * 开放及净值公告（公募）、开放及净值报告（私募）
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportKfjzgg001Service
 * @since 1.0
 */
@Service(value = "ReportKfjzgg001Service")
public class ReportKfjzgg001Service implements IReportDataService {

    @Autowired
    private ReportKfjzgg001Mapper reportKfjzgg001Mapper;

    @Autowired
    private HolidayService holidayService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReportRkcpjz001Mapper reportRkcpjz001Mapper;
    /**
     * 3.生成规则：
     * 根据产品分红到期计划表及产品排期计划表判断：若某个开放结束日不需要分红（开放结束日不是该产品的收益分配基准日），则需要生成在运作起始日生成公告；
     * 若产品是分级产品，需要按照子产品维度来生成数据；
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("开放及净值公告（公募）/（私募）【{}】开始生成", JsonUtils.object2Json(paraMap));
        List<Map<String, Object>> resultMap = new ArrayList<>();
        ReportKfjzgg001Dto reportKfjzgg001Dto = new ReportKfjzgg001Dto();
        //产品代码：子产品代码为空的话，取父产品代码
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        //开放结束日
        String openEndDateStr = (String) paraMap.get("enddate");
        //判断是 开放及净值公告（公募） 还是 开放及净值公告（私募） 的模板
        String rptType = (String) paraMap.get("uri");
        try {
            if (StringUtil.isNotEmpty(prodId) && StringUtil.isNotEmpty(openEndDateStr)) {
                Map<String, Object> searchparam = new HashMap<>();

                Map<String, Object> param = new HashMap<>();
                param.put("prodId", prodId);
                //判断是普通产品还是子产品
                RepProdInfo info=reportRkcpjz001Mapper.getPProdInfo(param);
                if(StringUtil.isNotEmpty(info)){
                    searchparam.put("prodId",info.getProdId());
                    prodId=info.getProdId();
                }



                searchparam.put("prodId", prodId);
                searchparam.put("openEndDateStr", openEndDateStr);

                //根据产品分红到期计划表及产品排期计划表判断：若某个开放结束日不需要分红（开放结束日不是该产品的收益分配基准日），则需要生成在运作起始日生成公告；
                //是否生成报表规则：
                boolean generateFlag = true;
                /*boolean generateFlag = false;
                DisProdBonusplan bonusplan = reportKfjzgg001Mapper.checkGenerateFlag(searchparam);
                if(bonusplan == null){
                    generateFlag = true;
                }*/
                if (generateFlag) {
                    //查询产品信息
                    reportKfjzgg001Dto = reportKfjzgg001Mapper.getProdInfo(searchparam);
                    if (reportKfjzgg001Dto.getSetDate() != null) {
                        reportKfjzgg001Dto.setSetDateT(DateUtil.format(reportKfjzgg001Dto.getSetDate(), DateUtil.DEFAULT_DATE_FORMAT_CN));
                        //需要增加产品成立日的过滤条件（子产品在没成立前查不到母产品历史净值数据）
                        searchparam.put("setDate", reportKfjzgg001Dto.getSetDate());
                    }
                    //查询后续申购赎回安排：根据产品排期表，展示“申购/赎回确认日”大于当前开放结束日的下一个开放周期的信息；
                    // 若当前已是产品的最后一个开放周期，后续没一个开放周期了，则正常生成表格，表格内容置空，由业务老师手工调整
                    List<ReportOpenPlanDto> openList = reportKfjzgg001Mapper.getOpenList(searchparam);
                    if (openList != null && openList.size() >0) {
                        //只取第一条
                        ReportOpenPlanDto reportOpenPlanDto = openList.get(0);
                        if("02".equals(reportKfjzgg001Dto.getRaiseType())){
                            reportOpenPlanDto.setOpenEndDate(reportOpenPlanDto.getCollingoffPeriod());
                        }
                        searchparam.put("busi_date", DateUtil.parse(reportOpenPlanDto.getOpenEndDate()));
                        searchparam.put("is_holiday", "0");
                        searchparam.put("market_code", "04");
                        Date nextSetday = holidayService.getNextWorkDay(searchparam);
                        if (nextSetday == null) {
                            nextSetday = DateUtil.addDay(DateUtil.parse(reportOpenPlanDto.getOpenEndDate()), 1);
                        }
                        reportOpenPlanDto.setSettDate(DateUtil.format(nextSetday,"yyyy-MM-dd"));

                        openList.clear();
                        openList.add(reportOpenPlanDto);
                    } else {
                        openList = new ArrayList<>();
                        ReportOpenPlanDto reportOpenPlanDto = new ReportOpenPlanDto();
                        reportOpenPlanDto.setOpenStartDate(" ");
                        reportOpenPlanDto.setOpenEndDate(" ");
                        reportOpenPlanDto.setCollingoffPeriod(" ");
                        reportOpenPlanDto.setSettDate(" ");
                        reportOpenPlanDto.setOpenDurationDate(" ");
                        openList.add(reportOpenPlanDto);
                    }

                    reportKfjzgg001Dto.setOpenPlanList(openList);

                    //查询历史开放信息
                    List<ReportOpenPlanDto> openHisList = reportKfjzgg001Mapper.getOpenHisList(searchparam);
                    int times = 0;
                    String timesStr = "";
                    //由于私募跟公募报表共用代码，   私募不需要展示： times 、OpenStartDateT、OpenEndDateT几个字段，需要特别隔离
                    if (StringUtil.equals(rptType, DisReportType.KFJJZGG_SM)) {
                        reportKfjzgg001Dto.setTimes("避开检测");
                        reportKfjzgg001Dto.setOpenStartDateT("避开检测");
                        reportKfjzgg001Dto.setOpenEndDateT("避开检测");
                    }
                    if (openHisList != null && openHisList.size() > 0) {
                        //当前净值数据
                        ReportOpenPlanDto curOpenPlanDto = openHisList.get(0);

                        //如果是私募的话，只展示当前数据
                        if (StringUtil.equals(rptType, DisReportType.KFJJZGG_SM)) {
                            openHisList = new ArrayList<>();
                            openHisList.add(curOpenPlanDto);
                        } else {
                            //如果是公募的话，需要展示近三期数据
                            times = openHisList.size();
                            switch (times) {
                                case 0:
                                    timesStr = "";
                                    break;
                                case 1:
                                    timesStr = "一";
                                    break;
                                case 2:
                                    timesStr = "二";
                                    break;
                                case 3:
                                    timesStr = "三";
                                    break;
                                default:
                                    timesStr = "";
                            }
                            reportKfjzgg001Dto.setTimes(timesStr);
                        }
                        //设置返回对象开放开始日
                        reportKfjzgg001Dto.setOpenStartDateT(DateUtil.format(DateUtil.parse(curOpenPlanDto.getOpenStartDate()), DateUtil.DEFAULT_DATE_FORMAT_CN));
                        //设置返回对象开放结束日
                        reportKfjzgg001Dto.setOpenEndDateT(DateUtil.format(DateUtil.parse(curOpenPlanDto.getOpenEndDate()), DateUtil.DEFAULT_DATE_FORMAT_CN));
                        if (openHisList != null && openHisList.size() > 0) {
                            //根据每个区间去查各区间的净值数据
                            for (ReportOpenPlanDto reportOpenPlanDto : openHisList) {
                                //查询上一开放结束日的净值数据
                                searchparam.put("openEndDateStr", reportOpenPlanDto.getOpenEndDate());
                                ReportOpenPlanDto lastOpenPlanDto = reportKfjzgg001Mapper.getLastOpenPlan(searchparam);
                                if (lastOpenPlanDto != null) {
                                    //本期封闭期 = 上一开放周期的运作起始日-当前开放周期的开放结束日
                                    reportOpenPlanDto.setRange(lastOpenPlanDto.getOperationStartDate() + "至" + reportOpenPlanDto.getOpenEndDate());
                                    //运行天数
                                    long runday = DateUtil.getBetweenDayNumber(lastOpenPlanDto.getOpenEndDate(), reportOpenPlanDto.getOpenEndDate());
                                    //计算年化收益率 = 当前开放结束日累计净值-上一开放结束日累计净值）/上一开放结束日单位净值 /本期天数*365
                                    reportOpenPlanDto.setYield(new BigDecimal(reportOpenPlanDto.getUnitValueT()).subtract(new BigDecimal(lastOpenPlanDto.getUnitValueT())).divide(new BigDecimal(lastOpenPlanDto.getUnitValue()), 10, BigDecimal.ROUND_DOWN).divide(new BigDecimal(runday), 10, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(365)).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN).toString() + "%");
                                }
                            }
                        } else {//如果处于第一个周期，表格置空，由业务手工维护
                            openHisList = new ArrayList<>();
                            ReportOpenPlanDto reportOpenPlanDto = new ReportOpenPlanDto();
                            reportOpenPlanDto.setOpenStartDate(" ");
                            reportOpenPlanDto.setOpenEndDate(" ");
                            reportOpenPlanDto.setUnitValue(" ");
                            reportOpenPlanDto.setUnitValueT(" ");
                            reportOpenPlanDto.setRedValue(" ");
                            reportOpenPlanDto.setSubValue(" ");
                            reportOpenPlanDto.setYield(" ");
                            openHisList.add(reportOpenPlanDto);
                        }

                        reportKfjzgg001Dto.setHisValueList(openHisList);
                    }

                    //披露日 = 开放结束日 + 1 工作日
//                    searchparam.put("busi_date", DateUtil.parse(openEndDateStr));
//                    searchparam.put("is_holiday", "0");
//                    searchparam.put("market_code", "04");
                    Date accountDate =  DateUtil.addDay(DateUtil.parse(reportKfjzgg001Dto.getOpenEndDateT()), 1);
//                    if (accountDate == null) {
//                        accountDate = DateUtil.addDay(DateUtil.parse(reportKfjzgg001Dto.getOpenEndDateT()), 1);
//                    }
                    reportKfjzgg001Dto.setAnnounceT(DateUtil.format(accountDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
                    logger.info("开放及净值公告（公募）/（私募）【{}】生成成功", JsonUtils.object2Json(paraMap));
                } else {
                    logger.warn("开放及净值公告（公募）/（私募）；不符合【根据产品分红到期计划表及产品排期计划表判断：若某个开放结束日不需要分红（开放结束日不是该产品的收益分配基准日），则需要生成在运作起始日生成公告】【{}】", JsonUtils.object2Json(paraMap));
                }
            }
        } catch (Exception e) {
            logger.error("开放及净值公告（公募）/（私募）【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        //增加跑批日
        reportKfjzgg001Dto.setEnddate(openEndDateStr);
        Map<String, Object> searchparam = new HashMap<>();
        searchparam.put("prodId", prodId);
        //封装导出文件名称
        ReportKfjzgg001Dto dto =  reportKfjzgg001Mapper.getProdInfo(searchparam);
        if(dto != null){
            reportKfjzgg001Dto.setCusFileName(dto.getTitleName());
        }

        resultMap.add(ReportCollectionUtils.bean2Map(reportKfjzgg001Dto));
        return resultMap;
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        repReveal.setProdid(prodId);
        Map<String, Object> param = new HashMap<>();
        param.put("prodId", prodId);
        RepProdInfo info=reportRkcpjz001Mapper.getProdId(param);
        if(StringUtil.isNotEmpty(info)){
            repReveal.setProdid(info.getpProdId());
        }
        repReveal.setRepId((String) paraMap.get("repId"));
        repReveal.setEndDate((Date) (paraMap.get("reportDate")));
        //查询产品信息
        //开放结束日
        String openEndDateStr = (String) paraMap.get("enddate");
        param.put("openEndDateStr", openEndDateStr);
        ReportKfjzgg001Dto reportKfjzgg001Dto = reportKfjzgg001Mapper.getProdInfo(param);
        if (Objects.nonNull(reportKfjzgg001Dto)){
            paraMap.put("cusFileName",reportKfjzgg001Dto.getTitleName());
            //由于私募跟公募报表共用代码，   私募不需要展示： times 、OpenStartDateT、OpenEndDateT几个字段，需要特别隔离
            if (StringUtil.equals(paraMap.get("uri"), DisReportType.KFJJZGG_SM)) {
                paraMap.put("openenddatet","避开检测");
            }
            if (reportKfjzgg001Dto.getSetDate() != null) {
                //需要增加产品成立日的过滤条件（子产品在没成立前查不到母产品历史净值数据）
                param.put("setDate", reportKfjzgg001Dto.getSetDate());
            }
            //查询历史开放信息
            List<ReportOpenPlanDto> openHisList = reportKfjzgg001Mapper.getOpenHisList(param);
            if (openHisList != null && openHisList.size() > 0) {
                //设置返回对象开放结束日
                paraMap.put("openenddatet",DateUtil.format(DateUtil.parse(openHisList.get(0).getOpenEndDate()), DateUtil.DEFAULT_DATE_FORMAT_CN));
            }
        }
        return repReveal;
    }
}
