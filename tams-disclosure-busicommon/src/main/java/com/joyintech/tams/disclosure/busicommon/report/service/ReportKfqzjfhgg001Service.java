
package com.joyintech.tams.disclosure.busicommon.report.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.RepReveal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.DisProdBonusplan;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportKfqzjfhgg001Mapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportCommonProdDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportKfqHisBonusDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportKfqzjfhgg001Dto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportOpenPlanDto;
import com.joyintech.tams.disclosure.busicommon.report.util.ReportCollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;

/**
 * 1.1.9.开放、净值及分红公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportKfqzjfhgg001Service
 * @since 1.0
 */
@Service(value = "ReportKfqzjfhgg001Service")
public class ReportKfqzjfhgg001Service implements IReportDataService {
    //

    @Autowired
    private ReportKfqzjfhgg001Mapper reportKfqzjfhgg001Mapper;

    @Autowired
    private HolidayService holidayService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 1.适用于“募集方式”为“公募”&&“发行方式”为“开放式”&&“产品类型”为“净值型产品”&&“是否是客户周期型产品”为“否”的产品；
     * 2.公告生成时点：收益分配基准日日终
     * 3.生成规则：
     * 根据产品分红到期计划表，当分红方式为“份额分红后净值为1”时，在对应的收益分配基准日日终生成此公告；
     * 若产品是分级产品，需要按照子产品维度生成数据；
     *
     * @param paraMap
     * @return //收益分配基准日份额净值（元） = 【子产品当期分红计算清单中“单位分红”截位保留四位小数+1.0000】
     * 单位分红 = 【应分配分红金额/产品期末余额（份额），结果截位法保留四位小数】
     * 应分配分红金额 = 产品资产净值 - 期末余额
     * //本次分红方案（元/1份份额）=【子产品当期分红计算清单中单位分红截位保留四位小数】
     * //分红权益登记日 =【产品分红到期表：权益分配基准日】
     * //除权日 = 【产品分红到期表：权益分配基准日】
     * //现金红利发放日 = 【产品分红到期表：现金红利发放日】
     * //每份额累计净值（元） = 若产品是第一次分红，则本字段值=1.000+本次分红方案(结果保留四位小数)；若产品不是第一次分红，则本字段值=子产品上一个收益分配基准日的产品累计单位净值+本次分红方案（结果保留四位小数）；
     * //年化收益率 = 【应分配分红金额/产品期末余额（份额）*365/运行天数，结果截位法按照小数展示，保留四位小数，例：此处展示的结果应该位0.1234】
     */
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("开放、净值及分红公告【{}】开始生成", JsonUtils.object2Json(paraMap));
        List<Map<String, Object>> resultMap = new ArrayList<>();
        ReportKfqzjfhgg001Dto reportKfqzjfhgg001Dto = new ReportKfqzjfhgg001Dto();
        try {
            //产品代码：子产品代码为空的话，取父产品代码
            String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
            //日期
            String baseDate = (String) paraMap.get("enddate");
            //判断是开放、净值及分红公告（公募&开放频率在半年（不含）以内） 还是 开放、净值及分红公告（公募&开放频率在半年以上） 的模板
            String rptType = (String) paraMap.get("uri");

            if (StringUtil.isNotEmpty(prodId) && StringUtil.isNotEmpty(baseDate)) {
                Map<String, Object> searchParam = new HashMap<>();
                searchParam.put("prodId", prodId);
                searchParam.put("baseDate", baseDate);
                //根据产品分红到期计划表，当分红方式为“份额分红后净值为1”时，在对应的收益分配基准日日终生成此公告；
                //是否生成报表规则：
                boolean generateFlag = true;
                /*boolean generateFlag = false;

                DisProdBonusplan bonusplan = reportKfqzjfhgg001Mapper.checkGenerateFlag(searchParam);

                if(bonusplan != null){
                    generateFlag = true;
                }*/

                if (generateFlag) {
                    //根据产品代码查询产品信息
                    ReportCommonProdDto reportCommonProdDto = reportKfqzjfhgg001Mapper.getProdInfo(searchParam);
                    //设置产品成立日
                    if (reportCommonProdDto.getSetDate() != null) {
                        reportKfqzjfhgg001Dto.setSetDateT(DateUtil.format(reportCommonProdDto.getSetDate(), DateUtil.ABB_DATE_FORMAT_CN));
                        searchParam.put("setDate", reportCommonProdDto.getSetDate());
                    }
                    //查询后续开放计划  根据产品排期表，展示“申购/赎回确认日”大于当前收益分配基准日的下一个开放周期的信息；若当前已处于最后一个产品周期，则正常生成表格，表格内容置空，由业务老师手工调整
                    List<ReportOpenPlanDto> openPlanList = reportKfqzjfhgg001Mapper.getOpenPlanList(searchParam);
                    //查询“申购/赎回确认日”小于等于当前收益分配基准日的过去所有历史数据
                    List<ReportKfqHisBonusDto> list = reportKfqzjfhgg001Mapper.getHisBonusReportList(searchParam);
                    //标题
                    reportKfqzjfhgg001Dto.setTitleName(reportCommonProdDto.getTitleName());
                    //收益分配基准日
                    reportKfqzjfhgg001Dto.setBaseDateT(DateUtil.format(DateUtil.parse(baseDate), DateUtil.ABB_DATE_FORMAT_CN));
                    //产品名称
                    reportKfqzjfhgg001Dto.setProdName(reportCommonProdDto.getProdName());
                    //产品登记编码
                    reportKfqzjfhgg001Dto.setRegCode(reportCommonProdDto.getRegCode());
                    //内部销售代码
                    reportKfqzjfhgg001Dto.setProdId(reportCommonProdDto.getProdId());
                    //历史净值及分红信息
                    if (list != null && list.size() > 0) {
                        ReportKfqHisBonusDto reportKfqHisBonusDto = list.get(0);
                        //半年以上，只展示当前分红信息
                        if (StringUtil.equals(rptType, DisReportType.KFJZJFHGG_BNYS)) {
                            list = new ArrayList<>();
                            list.add(reportKfqHisBonusDto);
                        }
                        //封装历史分红数据
                        for (int i = 0 ; i< list.size(); i++) {
                            ReportKfqHisBonusDto bonusDto = list.get(i);
                            //可供分配利润 = 产品资产净值-产品期末余额（份额）
                            BigDecimal distributableProfit = reportKfqHisBonusDto.getAssetsValue().subtract(reportKfqHisBonusDto.getCapital());
                            //运行天数 =若上一收益分配基准日是成立日：本期收益分配基准日-上一收益分配基准日+1； 若上一收益分配基准日不是成立日：本期收益分配基准日-上一收益分配基准日
                            BigDecimal runday = BigDecimal.ONE;
                            //封闭期
                            String curRange = "";
                            //查询上次收益分配基准日
                            searchParam.put("baseDate", bonusDto.getOpenEndDate());
                            ReportKfqHisBonusDto lastBonus = reportKfqzjfhgg001Mapper.getLastBonusInfo(searchParam);
                            if(lastBonus != null && lastBonus.getSettDate() != null){
                                runday = new BigDecimal(DateUtil.getBetweenDayNumber(lastBonus.getSettDate(), bonusDto.getSettDate()));
                                //上一收益分配基准日+1工作日至当前收益分配基准日
                                curRange = DateUtil.format(DateUtil.addDay(lastBonus.getSettDate(),1)) + "至" + DateUtil.format(bonusDto.getSettDate());
                            }else{
                                runday = new BigDecimal(DateUtil.getBetweenDayNumber(reportCommonProdDto.getSetDate(), bonusDto.getSettDate())+1);
                                //若产品是第一次分红，则取子产品成立日至当前收益分配基准日
                                curRange = DateUtil.format(reportCommonProdDto.getSetDate()) + "至" + DateUtil.format(bonusDto.getSettDate());
                            }
                            //业绩比较基准收益 = 产品期末余额（份额）*“产品分红到期计划表中的“业绩比较基准比例（%）”*365/运行天数，结果截位法保留2位小数
                            BigDecimal achievementBasisProfit =
                                    reportKfqHisBonusDto.getCapital().multiply(reportKfqHisBonusDto.getAchievementBasisProp()).divide(new BigDecimal(100))
                                            .multiply(new BigDecimal(365))
                                            .divide(runday, 10, BigDecimal.ROUND_DOWN);
                            //超出部分 = 可供分配利润-业绩比较基准收益
                            BigDecimal excessPart = distributableProfit.subtract(achievementBasisProfit);
                            //业绩报酬 =  超出部分*【产品分红到期计划中的：超额业绩报酬收取比例
                            BigDecimal floatFee = excessPart.multiply(reportKfqHisBonusDto.getExcessAchievementprop()).divide(new BigDecimal(100));
                            //应分配分红金额 = 可供分配利润-业绩报酬
                            BigDecimal mustDistributedProfit = distributableProfit.subtract(floatFee);
                            //单位分红 = 应分配分红金额/产品期末余额（份额），结果截位法保留四位小数
                            BigDecimal unitValue = mustDistributedProfit.divide(reportKfqHisBonusDto.getCapital(), 10, BigDecimal.ROUND_DOWN);
                            //收益分配基准日份额净值（元） = 【单位分红结果截位法保留四位小数】 + 1
                            String unitValueStr = unitValue.setScale(4, BigDecimal.ROUND_DOWN).toString();
                            unitValueStr = "1" + unitValueStr.substring(unitValueStr.indexOf("."), unitValueStr.length());
                            //本次分红方案（元/1份份额）
                            String unitProfitStr = "0" + unitValueStr.substring(unitValueStr.indexOf("."), unitValueStr.length());
                            //每份额累计净值（元） = 若产品是第一次分红，则本字段值=1.000+本次分红方案(结果保留四位小数)； 若产品不是第一次分红，则本字段值=子产品上一个收益分配基准日的产品累计单位净值+本次分红方案（结果保留四位小数）
                            if (lastBonus != null && lastBonus.getLastUnitValueT() != null) {
                                bonusDto.setMunitValueT(lastBonus.getLastUnitValueT().add(new BigDecimal(unitProfitStr)).toString());
                            }else{
                                bonusDto.setMunitValueT((new BigDecimal("1").add(new BigDecimal(unitProfitStr)).toString()));
                            }
                            //设置历史净值当前封闭期
                            bonusDto.setCurRange(curRange);
                            //计算年化收益率 应分配分红金额/产品期末余额（份额）*365/运行天数，结果截位法按照小数展示，保留四位小数
                            BigDecimal yield = unitValue.divide(new BigDecimal(365), 10, BigDecimal.ROUND_DOWN).multiply(runday).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
                            bonusDto.setYield(yield.toString()+"%");
                            bonusDto.setMunitValue("1.0000");
                            bonusDto.setRedValue("1.0000");
                            bonusDto.setSubValue("1.0000");
                            if(i == 0){
                                //开放起始日
                                reportKfqzjfhgg001Dto.setSubDateT(DateUtil.format(reportKfqHisBonusDto.getOpenStartDate(),DateUtil.ABB_DATE_FORMAT_CN));
                                //基准日
                                reportKfqzjfhgg001Dto.setBaseDateT(DateUtil.format(reportKfqHisBonusDto.getSettDate(),DateUtil.ABB_DATE_FORMAT_CN));
                                reportKfqzjfhgg001Dto.setBaseDate(DateUtil.format(reportKfqHisBonusDto.getSettDate()));
                                reportKfqzjfhgg001Dto.setUnitValue(unitValueStr);
                                reportKfqzjfhgg001Dto.setUnitProfit(unitProfitStr);
                                //现金红利发放日
                                reportKfqzjfhgg001Dto.setPayDate(DateUtil.format(reportKfqHisBonusDto.getPaymentDate()));
                            }
                        }
                    }
                    reportKfqzjfhgg001Dto.setList(list);
                    //若产品已处于最后一个周期，则表格对应内容置空，业务老师手工维护
                    for(int i = 0 ; i < openPlanList.size();i++){
                        if( openPlanList.size() == 1 &&  DateUtil.parse(openPlanList.get(i).getOpenStartDate()).compareTo(DateUtil.parse(baseDate))<=0){
                            openPlanList.remove(i);
                        }
                    }
                    //后续开放计划
                    if (openPlanList != null && openPlanList.size() > 0) {
                       //只取第一条
                        ReportOpenPlanDto reportOpenPlanDto = openPlanList.get(0);
                        openPlanList.clear();
                        openPlanList.add(reportOpenPlanDto);

                        for (ReportOpenPlanDto openPlanDto : openPlanList) {
                            //后续赎回资金到账日
                            //查询申购/赎回确认日的下一工作日
                            searchParam.put("busi_date", DateUtil.parse(openPlanDto.getNextMdate()));
                            searchParam.put("is_holiday", "0");
                            searchParam.put("market_code", "04");
                            Date nextSetday = holidayService.getNextWorkDay(searchParam);
                            if (nextSetday == null) {
                                nextSetday = DateUtil.addDay(DateUtil.parse(openPlanDto.getNextMdate()), 1);
                            }
                            openPlanDto.setNextSettday(DateUtil.format(nextSetday));
                        }
                    } else {
                        openPlanList = new ArrayList<>();
                        ReportOpenPlanDto openPlanDto = new ReportOpenPlanDto();
                        //后续排期计划为空的话，需要置空由业务老师手工维护
                        //后续申购/赎回起止日
                        openPlanDto.setNextRange(" ");
                        //后续申购/赎回确认日
                        openPlanDto.setNextMdate(" ");
                        //后续赎回资金到账日
                        openPlanDto.setNextSettday(" ");
                        openPlanList.add(openPlanDto);
                    }
                    reportKfqzjfhgg001Dto.setOpenPlanList(openPlanList);
                    //公告日 = 收益分配基准日+1工作日
                    searchParam.put("busi_date", DateUtil.parse(baseDate));
                    searchParam.put("is_holiday", "0");
                    searchParam.put("market_code", "04");
                    Date announceDate = holidayService.getNextWorkDay(searchParam);
                    if (announceDate == null) {
                        announceDate = DateUtil.addDay(DateUtil.parse(baseDate), 1);
                    }
                    reportKfqzjfhgg001Dto.setAnnounceT(DateUtil.format(announceDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
                    //跑批日
                    reportKfqzjfhgg001Dto.setEnddate(DateUtil.format(announceDate));

                } else {
                    logger.warn("开放、净值及分红公告；不符合【根据产品分红到期计划表，当分红方式为“份额分红后净值为1”时，在对应的收益分配基准日日终生成此公告】【{}】", JsonUtils.object2Json(paraMap));
                }
            }
            resultMap.add(ReportCollectionUtils.bean2Map(reportKfqzjfhgg001Dto));
            logger.info("开放、净值及分红公告【{}】生成成功", JsonUtils.object2Json(paraMap));
        } catch (Exception e) {
            logger.error("开放、净值及分红公告【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        return resultMap;
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        repReveal.setProdid(prodId);
        repReveal.setRepId((String) paraMap.get("repId"));
        repReveal.setEndDate((Date) (paraMap.get("reportDate")));
        return repReveal;
    }
}
