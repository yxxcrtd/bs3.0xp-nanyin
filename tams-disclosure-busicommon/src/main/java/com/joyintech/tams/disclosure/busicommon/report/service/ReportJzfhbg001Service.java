
package com.joyintech.tams.disclosure.busicommon.report.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.DisProdBonusplan;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportJzfhbg001Mapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportJzfhbg001Dto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportOpenPlanDto;
import com.joyintech.tams.disclosure.busicommon.report.util.ReportCollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;

/**
 * 净值及分红报告（私募封闭式）/净值及分红报告（私募开放式）
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportJzfhbg001Service
 * @since 1.0
 */
@Service(value = "ReportJzfhbg001Service")
public class ReportJzfhbg001Service implements IReportDataService {

    @Autowired
    private ReportJzfhbg001Mapper reportJzfhbg001Mapper;

    @Autowired
    private HolidayService holidayService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 1.适用于“募集方式”为“私募”&&“产品类型”为“净值型”的产品；
     * 2.公告生成时点：收益分配基准日日终生成
     * 3.生成规则：
     * 根据产品分红到期表，当分红方式选择“份额分红后净值为1”时，在对应的收益分配基准日日终生成；
     * 若产品是分级产品，需要按照子产品维度来生成数据；
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("净值及分红报告（私募封闭式）/净值及分红报告（私募开放式）【{}】开始生成", JsonUtils.object2Json(paraMap));
        List<Map<String, Object>> resultMap = new ArrayList<>();
        ReportJzfhbg001Dto reportJzfhbg001Dto = new ReportJzfhbg001Dto();
        try {
            //产品代码：子产品代码为空的话，取父产品代码
            String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
            //收益分配基准日
            String settDateStr = (String) paraMap.get("enddate");
            //判断是 净值及分红报告（私募封闭式） 还是 净值及分红报告（私募开放式） 的模板
            String rptType = (String) paraMap.get("uri");

            if (StringUtil.isNotEmpty(prodId) && StringUtil.isNotEmpty(settDateStr)) {
                Map<String, Object> searchParam = new HashMap<>();
                searchParam.put("prodId", prodId);
                searchParam.put("settDate", settDateStr);
                //根据产品分红到期计划表，当分红方式为“份额分红后净值为1”时，在对应的收益分配基准日日终生成此公告；
                //是否生成报表规则：
                boolean generateFlag = false;

                DisProdBonusplan bonusplan1 = reportJzfhbg001Mapper.checkGenerateFlag(searchParam);

                if (bonusplan1 != null) {
                    generateFlag = true;
                }
                if (generateFlag) {
                    //查询产品相关信息
                    reportJzfhbg001Dto = reportJzfhbg001Mapper.getProdInfo(searchParam);
                    //设置产品成立日
                    reportJzfhbg001Dto.setSetDateT(DateUtil.format(reportJzfhbg001Dto.getSetDate(), DateUtil.ABB_DATE_FORMAT_CN));
                    //查询收益分配基准日的产品分红到期表
                    DisProdBonusplan bonusplan = reportJzfhbg001Mapper.getBonusPlanBySettDate(searchParam);

                    if (bonusplan != null) {
                        //设置业绩分配基准日
                        reportJzfhbg001Dto.setSettDate(DateUtil.format(bonusplan.getSettDate()));
                        reportJzfhbg001Dto.setSettDateT(DateUtil.format(bonusplan.getSettDate(), DateUtil.ABB_DATE_FORMAT_CN));
                        //设置现金分红日
                        reportJzfhbg001Dto.setPaymentDate(DateUtil.format(bonusplan.getPaymentDate()));
                        //查询业绩分配基准日净值数据
                        RepProdIndex repProdIndex = reportJzfhbg001Mapper.getIndexBySettDate(searchParam);

                        if (repProdIndex != null) {
                            /**
                             * 分红信息模块
                             */
                            //实收资本
                            BigDecimal capital = repProdIndex.getCapital() == null ? BigDecimal.ZERO : repProdIndex.getCapital();
                            //资产净值
                            BigDecimal assetsValue = repProdIndex.getAssetsValue() == null ? BigDecimal.ZERO : repProdIndex.getAssetsValue();
                            //应分配分红金额 = 产品资产净值 - 期末余额
                            BigDecimal distributableProfit = assetsValue.subtract(capital);
                            //单位分红应分配分红金额/产品期末余额（份额）
                            BigDecimal unitValue = distributableProfit.divide(capital, 4, RoundingMode.HALF_UP);
                            //收益分配基准日份额净值（元） = 【单位分红结果截位法保留四位小数】 + 1
                            String unitValueStr = unitValue.toString();
                            unitValueStr = "1" + unitValueStr.substring(unitValueStr.indexOf("."), unitValueStr.length());
                            reportJzfhbg001Dto.setcUnitValue(unitValueStr);
                            //本次分红方案（元/1份份额）
                            String unitProfitStr = "0" + unitValueStr.substring(unitValueStr.indexOf("."), unitValueStr.length());
                            reportJzfhbg001Dto.setcUnitProfit(unitProfitStr);
                            /**
                             * 当期净值模块
                             */
                            //期末每份额净值（元） = 固定值1.000
                            reportJzfhbg001Dto.setUnitValue("1.0000");

                            //根据业绩分配基准日查询上次分红计划表
                            DisProdBonusplan lastBonus = reportJzfhbg001Mapper.getLastBonusInfo(searchParam);
                            long runday = 1;
                            //产品第一次进行分红
                            if (lastBonus == null) {
                                //若产品是第一次分红，则取子产品成立日至当前收益分配基准日
                                runday = DateUtil.getBetweenDayNumber(reportJzfhbg001Dto.getSetDate(), bonusplan.getSettDate());
                                //设置封闭期
                                reportJzfhbg001Dto.setRange(DateUtil.format(reportJzfhbg001Dto.getSetDate()) + "至" + DateUtil.format(bonusplan.getSettDate()));
                                //每份额累计净值（元） = 若产品是第一次分红，则本字段值=1.000+本次分红方案(结果保留四位小数)；
                                reportJzfhbg001Dto.setUnitValueT(BigDecimal.ONE.add(new BigDecimal(unitProfitStr)).toString());
                            } else {//产品非第一次进行分红
                                //上一收益分配基准日+1工作日至当前收益分配基准日
                                runday = DateUtil.getBetweenDayNumber(lastBonus.getSettDate(), bonusplan.getSettDate());
                                //设置封闭期
                                reportJzfhbg001Dto.setRange(DateUtil.format(lastBonus.getSettDate()) + "至" + DateUtil.format(bonusplan.getSettDate()));
                                // 若产品不是第一次分红，每份额累计净值（元）=（子）产品上一个收益分配基准日的产品累计单位净值+本次分红方案（结果保留四位小数）
                                searchParam.put("settDate", DateUtil.format(lastBonus.getSettDate()));
                                RepProdIndex lastRepProdIndex = reportJzfhbg001Mapper.getIndexBySettDate(searchParam);

                                if (lastRepProdIndex != null) {
                                    reportJzfhbg001Dto.setUnitValueT(lastRepProdIndex.getUnitValueT().add(new BigDecimal(unitProfitStr)).toString());
                                }
                            }
                            //年化收益率
                            reportJzfhbg001Dto.setYield(new BigDecimal(unitProfitStr).multiply(new BigDecimal(365).divide(new BigDecimal(runday), 4, RoundingMode.HALF_UP)).setScale(4, RoundingMode.HALF_UP).toString());
                        }
                    }
                    //设置公告日 = 收益分配基准日+1工作日
                    searchParam.put("busi_date", DateUtil.parse(settDateStr));
                    searchParam.put("is_holiday", "0");
                    searchParam.put("market_code", "04");
                    Date announceDate = holidayService.getNextWorkDay(searchParam);
                    if (announceDate == null) {
                        announceDate = DateUtil.addDay(DateUtil.parse(settDateStr), 1);
                    }
                    reportJzfhbg001Dto.setAnnounceT(DateUtil.format(announceDate, DateUtil.DEFAULT_DATE_FORMAT_CN));

                    //如果是净值及分红报告（私募开放式）的模板的话，需要多设置以下要素信息
                    if (StringUtil.equals(rptType, DisReportType.JZJFHBG_SMKF)) {
                        //赎回价格（元/份）
                        reportJzfhbg001Dto.setRedValue("1.0000");
                        //申购价格（元/份）
                        reportJzfhbg001Dto.setSubValue("1.0000");
                        //根据产品排期表，展示开放起始日大于当前收益分配基准日的下一个开放周期的信息；若当前已处于最后一个产品周期，则正常生成表格，表格内容置空，由业务老师手工调整
                        List<ReportOpenPlanDto> openPlanList = reportJzfhbg001Mapper.getOpenPlanList(searchParam);
                        if (openPlanList != null && openPlanList.size() > 0) {
                            for (ReportOpenPlanDto openPlanDto : openPlanList) {
                                //查询申购/赎回确认日的下一工作日
                                searchParam.put("busi_date", DateUtil.parse(openPlanDto.getOpenEndDate()));
                                searchParam.put("is_holiday", "0");
                                searchParam.put("market_code", "04");
                                Date nextSetday = holidayService.getNextWorkDay(searchParam);
                                if (nextSetday == null) {
                                    nextSetday = DateUtil.addDay(DateUtil.parse(openPlanDto.getOpenEndDate()), 1);
                                }
                                openPlanDto.setSettDate(DateUtil.format(nextSetday));
                            }
                        } else {
                            openPlanList = new ArrayList<>();
                            ReportOpenPlanDto openPlanDto = new ReportOpenPlanDto();
                            //后续排期计划为空的话，需要置空由业务老师手工维护
                            //后续申购/赎回起止日
                            openPlanDto.setOpenStartDate("XXXX-XX-XX");
                            //申购/赎回确认日
                            openPlanDto.setOpenEndDate("XXXX-XX-XX");
                            openPlanDto.setOpenDurationDate("XXXX-XX-XX至XXXX-XX-XX");
                            //申购冷静期
                            openPlanDto.setCollingoffPeriod("XXXX-XX-XX");
                            //赎回资金到账日
                            openPlanDto.setSettDate("XXXX-XX-XX");
                            openPlanList.add(openPlanDto);
                        }
                        reportJzfhbg001Dto.setOpenPlanList(openPlanList);
                    } else {
                        logger.warn("开放、净值及分红公告；不符合【根据产品分红到期计划表，当分红方式为“份额分红后净值为1”时，在对应的收益分配基准日日终生成此公告】【{}】",JsonUtils.object2Json(paraMap));
                    }

                }
            }
            resultMap.add(ReportCollectionUtils.bean2Map(reportJzfhbg001Dto));
            logger.info("净值及分红报告（私募封闭式）/净值及分红报告（私募开放式）【{}】生成成功", JsonUtils.object2Json(paraMap));
        } catch (Exception e) {
            logger.error("净值及分红报告（私募封闭式）/净值及分红报告（私募开放式）【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        return resultMap;
    }
}
