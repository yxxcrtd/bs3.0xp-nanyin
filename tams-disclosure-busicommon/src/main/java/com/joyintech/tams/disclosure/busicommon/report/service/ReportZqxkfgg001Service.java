
package com.joyintech.tams.disclosure.busicommon.report.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.joyintech.tams.common.base.entity.DisProdPlanInfo;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportZqxkfgg001Mapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportOpenPlanDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportValueDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportZqxkfgg001Dto;
import com.joyintech.tams.disclosure.busicommon.report.util.ReportCollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;

/**
 * 周期型产品开放公告（公募产品）、周期型产品开放公告（公募产品-单赎回）
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportZqxkfgg001Service
 * @since 1.0
 */
@Service(value = "ReportZqxkfgg001Service")
public class ReportZqxkfgg001Service implements IReportDataService {

    @Autowired
    private ReportZqxkfgg001Mapper reportZqxkfgg001Mapper;

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private ReportRkcpjz001Mapper reportRkcpjz001Mapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 1.适用于“募集方式”为“公募”且“是否是客户周期型产品”为“是”的产品；
     * 2.公告生成时点：自动赎回日上午8：00之前；
     * 3.生成规则：
     * 客户周期型产品的自动赎回日大多是后续某一开放周期的运作起始日，根据产品排期表进行判断，若某一自动赎回日不是该产品未来某开放周期的运作起始日，则需要为该条赎回信息生成此公告；
     * 若产品是分级产品，需要按照子产品维度生成报表，报表中历史开放信息中的净值相关字段按照子产品维度来取值；
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("周期型产品开放公告【{}】开始生成", JsonUtils.object2Json(paraMap));
        List<Map<String, Object>> resultMap = new ArrayList<>();
        ReportZqxkfgg001Dto reportZqxkfgg001Dto = new ReportZqxkfgg001Dto();
        //产品代码：子产品代码为空的话，取父产品代码
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        //公告日
        String announceDate = (String) paraMap.get("enddate");
        //判断是 周期型产品开放公告（公募产品-单赎回）还是 周期型产品开放公告（公募产品） 的模板
        //周期型产品开放公告（公募产品-单赎回）:客户周期型产品的自动赎回日大多是后续某一开放周期的运作起始日，根据产品排期表进行判断，若某一自动赎回日不是该产品未来某开放周期的运作起始日，则需要为该条赎回信息生成此公告；
        //周期型产品开放公告（公募产品） 根据产品排期计划表，每个开放周期的运作起始日都需要生成此公告；
        String rptType = (String) paraMap.get("uri");
        try {
            if (StringUtil.isNotEmpty(prodId) && StringUtil.isNotEmpty(announceDate)) {
                Map<String, Object> searchparam = new HashMap<>();
                searchparam.put("prodId", prodId);
                searchparam.put("announceDate", announceDate);
                searchparam.put("rptType",rptType);
                //是否生成报表规则：
                boolean generateFlag = true;
                /*boolean generateFlag = false;
                //判断产品当前日期是否允许生成报表
                DisProdPlanInfo planInfo = reportZqxkfgg001Mapper.checkGenerateFlag(searchparam);

                if(planInfo != null){
                    generateFlag = true;
                }*/

                if (generateFlag) {
                    //查询产品信息
                    //判断是普通产品还是子产品
                    RepProdInfo info=reportRkcpjz001Mapper.getPProdInfo(searchparam);
                    if(StringUtil.isNotEmpty(info)){
                        searchparam.put("prodId",info.getProdId());
                        prodId=info.getProdId();
                    }
                    reportZqxkfgg001Dto = reportZqxkfgg001Mapper.getProdInfo(searchparam);
                    if (reportZqxkfgg001Dto.getSetDate() != null) {
                        reportZqxkfgg001Dto.setSetDateT(DateUtil.format(reportZqxkfgg001Dto.getSetDate(), DateUtil.DEFAULT_DATE_FORMAT_CN));
                        searchparam.put("setDate",reportZqxkfgg001Dto.getSetDate());
                    }
                    reportZqxkfgg001Dto.setAnnounceT(DateUtil.format(DateUtil.parse(announceDate), DateUtil.DEFAULT_DATE_FORMAT_CN));
                    //查询后续申购赎回安排：根据产品排期计划展示当前“运作起始日”之后的未来6个开放周期（运作起始日大于当前运作起始日）】
                    // 注：若产品临近到期后续已没有6个开放周期，则有几个周期展示几个周期；若产品已处于最后一个周期，则表格对应内容置空，业务老师手工维护
                    List<ReportOpenPlanDto> openList = reportZqxkfgg001Mapper.getOpenList(searchparam);

                    if (openList == null || openList.size() == 0 ) {
                        openList = new ArrayList<>();
                        ReportOpenPlanDto reportOpenPlanDto = new ReportOpenPlanDto();
                        reportOpenPlanDto.setOpenDurationDate(" ");
                        reportOpenPlanDto.setOperationStartDate(" ");
                        reportOpenPlanDto.setAutoRedeemDate(" ");
                        reportOpenPlanDto.setHoldDays(" ");
                        openList.add(reportOpenPlanDto);
                    }
                    reportZqxkfgg001Dto.setOpenList(openList);
                    //平台日
                    Date plateDate = DateUtil.platDateFromCache();
                    //查询历史开放信息
                    List<ReportOpenPlanDto> openHisList = reportZqxkfgg001Mapper.getOpenHisList(searchparam);
                    if (openHisList != null && openHisList.size() > 0) {
                        //根据每个区间去查各区间的净值数据
                        for (ReportOpenPlanDto reportOpenPlanDto : openHisList) {
                            //周期运作起始日
                            Date operationStartDate = DateUtil.parse((String) reportOpenPlanDto.getOperationStartDate());
                            //根据周期运作起始日去查上一工作日的净值数据
                            searchparam.put("busi_date", operationStartDate);
                            searchparam.put("is_holiday", "0");
                            searchparam.put("market_code", "04");
                            Date lastWorkDay = holidayService.getLastWorkDay(searchparam);
                            if (lastWorkDay == null) {
                                lastWorkDay = DateUtil.addDay(operationStartDate, -1);
                            }
                            searchparam.put("lastWorkDay", lastWorkDay);
                            //上一工作日的净值数据
                            ReportValueDto reportValueDto = reportZqxkfgg001Mapper.getValueInfo(searchparam);
                            //单位净值
                            BigDecimal unitValue = null;
                            //累计单位净值
                            BigDecimal unitValueT = null;
                            //赎回确认净值
                            BigDecimal redValue = null;

                            if (reportValueDto != null) {
                                unitValue = new BigDecimal(reportValueDto.getUnitValue());
                                unitValueT = new BigDecimal(reportValueDto.getUnitValueT());
                                /**
                                 * 当自动赎回日小于本周期“运作起始日”时，会有赎回确认单位净值，需要结合超额业绩报酬表进行判断，
                                 * 若超额业绩报酬为0，则赎回确认单位净值=申购确认单位净值；若超额业绩报酬不为0，则用确认金额/确认份额的值按照截位法保留4位小数；
                                 * 若自动赎回日大于当前平台日，赎回确认单位净值（元）/赎回价格（元/份）置空；
                                 */
                                //自动赎回日
                                Date autoRedeemDate = DateUtil.parse(reportOpenPlanDto.getAutoRedeemDate());
                                //超额业绩报酬
                                BigDecimal aboveAmt = reportOpenPlanDto.getAboveAmt();
                                //确认金额
                                BigDecimal affirmAmt = reportOpenPlanDto.getAffirmAmt();
                                //确认份额
                                BigDecimal affirmNum = reportOpenPlanDto.getAffirmNum();
                                //自动赎回日小于运作起始日
                                if (autoRedeemDate != null && autoRedeemDate.compareTo(DateUtil.parse(announceDate)) <= 0) {
                                    //超额业绩报酬为0  赎回确认单位净值=申购确认单位净值
                                    if ((aboveAmt != null && aboveAmt.compareTo(BigDecimal.ZERO) == 0)||aboveAmt == null) {
                                        //取自动赎回日的单位净值
                                        Map<String, Object> param = new HashMap<>();
                                        param.put("prodId",searchparam.get("prodId"));
                                        param.put("lastWorkDay",DateUtil.parse(reportOpenPlanDto.getAutoRedeemDate()));
                                        ReportValueDto value = reportZqxkfgg001Mapper.getValueInfo(param);
                                        if(value!=null){
                                            redValue=new BigDecimal(reportValueDto.getUnitValue());
                                        }else{
                                            redValue = null;
                                        }
                                    } else {
                                        if (affirmAmt != null && affirmNum != null) {
                                            redValue = affirmAmt.divide(affirmNum,10,BigDecimal.ROUND_DOWN).setScale(4, BigDecimal.ROUND_DOWN);
                                        }
                                    }
                                } else {
                                    redValue = null;
                                }
                            }
                            reportOpenPlanDto.setRedValue(redValue == null ? "" : String.valueOf(redValue));
                            reportOpenPlanDto.setUnitValue(String.valueOf(unitValue));
                            reportOpenPlanDto.setUnitValueT(String.valueOf(unitValueT));
                        }
                        reportZqxkfgg001Dto.setOpenHisList(openHisList);
                    }
                    logger.info("周期型产品开放公告【{}】生成成功", JsonUtils.object2Json(paraMap));
                }else{
                    logger.warn("周期型产品开放公告（公募产品-单赎回）:客户周期型产品的自动赎回日大多是后续某一开放周期的运作起始日，根据产品排期表进行判断，若某一自动赎回日不是该产品未来某开放周期的运作起始日，则需要为该条赎回信息生成此公告；" +
                            " 周期型产品开放公告（公募产品）：根据产品排期计划表，每个开放周期的运作起始日都需要生成此公告；【请检查当前产品的排期计划以及查询日期】【{}】",JsonUtils.object2Json(paraMap));
                }
            }
        } catch (Exception e) {
            logger.error("周期型产品开放公告【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        //跑批日
        reportZqxkfgg001Dto.setEnddate(announceDate);
        resultMap.add(ReportCollectionUtils.bean2Map(reportZqxkfgg001Dto));
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
        repReveal.setEndDate( (Date)(paraMap.get("reportDate")));
        return repReveal;
    }
}
