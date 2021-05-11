package com.joyintech.tams.batch.svreport.investreport.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.svreport.investreport.dao.SvrInvestReportMainMapper;
import com.joyintech.tams.common.base.dao.MstHolidayMapper;
import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.common.base.entity.MstHolidayKey;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.chbreport.investreport.common.dao.ChbInvestReportCommonMapper;
import com.joyintech.tams.svreport.chbreport.investreport.common.dto.ReportGenerate001Dto;
import com.joyintech.tams.svreport.chbreport.investreport.common.service.ChbInvestReportCommonService;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产利率接入
 */
@Service(value = "svrInvestReportMainService")
@Scope("prototype")
public class SvrInvestReportMainService extends AutoSendCBBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChbInvestReportCommonService chbInvestReportCommonService;

    @Autowired
    private ChbInvestReportCommonMapper chbInvestReportCommonMapper;

    @Autowired
    private SvrInvestReportMainMapper svrInvestReportMainMapper;

    @Autowired
    MstHolidayMapper mstHolidayMapper;

    /**
     * 根据日期生成上一工作日的报告数据
     * @param batchDate
     * @throws Exception
     */
    public void generateInvestReport(Date batchDate) throws Exception{
        Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.中债报告生成批处理);
        logInfoBatchStart();
        MstHolidayKey key = new MstHolidayKey();
        key.setCalendarId(DictCodeListExt2.节假日_交易市场_中债_中国法定节假日.getValue());
        key.setCdate(batchDate);
        MstHoliday mstHoliday = mstHolidayMapper.selectByPrimaryKey(key);
        if(mstHoliday == null || mstHoliday.getIsHoliday() != 0) {
            logger.info("中债报告生成批处理非工作日不生成报告：" + DateUtil.format(batchDate));
        }
        else {
            MstHoliday preMstHoliday = svrInvestReportMainMapper.getPreMstHolidayByParam(key);
            if(preMstHoliday == null) {
                logger.error("中债报告生成批处理未找到前一工作日：" + DateUtil.format(batchDate));
            }
            else {
                Map<String, Object> params = new HashMap<>();
                List<RepFtpPath> repList = chbInvestReportCommonMapper.queryRepFtpPathList(params);//要生成的报告类型
                repList.stream().forEach(item -> {
                    String isConfirm = DictCodeListExt1.确认类型_手动.getValue();
                    String isReveal = DictCodeListExt1.确认类型_手动.getValue();
                    //是否自动确认
                    String sysIsConfirm = CacheUtil.getSysParam("SVR_REPORT_IS_CONFIRM");
                    //是否自动披露
                    String sysIsReveal = CacheUtil.getSysParam("SVR_REPORT_IS_REVEAL");
                    if (StringUtil.isNotEmpty(sysIsConfirm) && StringUtil.equals(DictCodeListExt1.确认类型_自动.getValue(), sysIsConfirm)) {
                        isConfirm = sysIsConfirm;
                    }
                    if (StringUtil.isNotEmpty(sysIsReveal) && StringUtil.equals(DictCodeListExt1.确认类型_自动.getValue(), sysIsReveal)) {
                        isReveal = sysIsReveal;
                    }
                    List<ReportGenerate001Dto> data = null;
                    params.put("endDate", DateUtil.format(preMstHoliday.getCdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
                    params.put("repId", item.getId());
                    params.put("confirmStatusFlag", DictCodeList.是否_是.getValue());
                    if (StringUtil.equals(DictCodeListExt3.中债披露报告_成立公告.getValue(), item.getId())) {
                        data = chbInvestReportCommonMapper.queryDetailData01(params);
                        startCreateReport(data, DateUtil.format(preMstHoliday.getCdate(), DateUtil.DEFAULT_DATE_FORMAT_EN),
                                item.getId(), isConfirm, isReveal, null);

                    } else if (StringUtil.equals(DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue(), item.getId())) {
                        data = chbInvestReportCommonMapper.queryDetailData02(params);
                        startCreateReport(data, DateUtil.format(preMstHoliday.getCdate(), DateUtil.DEFAULT_DATE_FORMAT_EN),
                                item.getId(), isConfirm, isReveal, null);
                    } else if (StringUtil.equals(DictCodeListExt3.中债披露报告_到期公告.getValue(), item.getId())) {
                        data = chbInvestReportCommonMapper.queryDetailData03(params);
                        startCreateReport(data, DateUtil.format(preMstHoliday.getCdate(), DateUtil.DEFAULT_DATE_FORMAT_EN),
                                item.getId(), isConfirm, isReveal, null);
                    } else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品净值披露.getValue(), item.getId())) {
                        isConfirm = DictCodeListExt1.确认类型_自动.getValue();
                        isReveal = DictCodeListExt1.确认类型_自动.getValue();
                        data = chbInvestReportCommonMapper.queryDetailData05(params);
                        startCreateReport(data, DateUtil.format(preMstHoliday.getCdate(), DateUtil.DEFAULT_DATE_FORMAT_EN),
                                item.getId(), isConfirm, isReveal, null);
                    } else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品定期公告.getValue(), item.getId())) {
                        //特殊处理
                        String startDate = judgeJD(preMstHoliday.getCdate());
                        if (StringUtil.isNotEmpty(startDate)) {
                            params.put("startDate", startDate);
                            data = chbInvestReportCommonMapper.selectDqReportList(params);
                            startCreateReport(data, DateUtil.format(preMstHoliday.getCdate(), DateUtil.DEFAULT_DATE_FORMAT_EN),
                                    item.getId(), isConfirm, isReveal, getJd(preMstHoliday.getCdate()));
                        }
                        startDate = judgeBND(preMstHoliday.getCdate());
                        if (StringUtil.isNotEmpty(startDate)) {
                            params.put("startDate", startDate);
                            data = chbInvestReportCommonMapper.selectDqReportList(params);
                            startCreateReport(data, DateUtil.format(preMstHoliday.getCdate(), DateUtil.DEFAULT_DATE_FORMAT_EN),
                                    item.getId(), isConfirm, isReveal, getBND(preMstHoliday.getCdate()));
                        }
                        startDate = judgeND(preMstHoliday.getCdate());
                        if (StringUtil.isNotEmpty(startDate)) {
                            params.put("startDate", startDate);
                            data = chbInvestReportCommonMapper.selectDqReportList(params);
                            startCreateReport(data, DateUtil.format(preMstHoliday.getCdate(), DateUtil.DEFAULT_DATE_FORMAT_EN),
                                    item.getId(), isConfirm, isReveal, getND(preMstHoliday.getCdate()));
                        }
                    } else {
                        logger.error("中债报告生成批处理未知的报表类型：" + item.getId());
                    }
                });
            }
        }
        logInfoBatchEnd();
    }

    /**
     * 开始生成报告
     * @param data
     * @param endDate
     * @param repId
     * @param isConfirm
     * @param isReveal
     * @param reportType
     */
    private void startCreateReport(List<ReportGenerate001Dto> data, String endDate, String repId, String isConfirm, String isReveal, String reportType) {
        for(ReportGenerate001Dto obj : data) {
            Map<String, Object> param = dealMap(obj, reportType, repId);
            try {
                boolean flag = chbInvestReportCommonService.createReportDoc(param, isConfirm, isReveal);
                if (!flag) {
                    String msg = "中债报告生成批处理生成报告失败：" + repId + "：" +obj.getProdIdName() + "：" + endDate;
                    if (StringUtil.isNotEmpty(reportType)) {
                        msg = msg + "：" + reportType;
                    }
                    logger.error(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("中债报告生成批处理生成报告时出现异常：" + repId + "：" +obj.getProdIdName() + "：" + endDate);
            }
        }
    }

    /**
     * 处理生成报告的相关参数
     * @param item
     * @param reportType
     * @param repId
     * @return
     */
    private Map<String, Object> dealMap(ReportGenerate001Dto item, String reportType, String repId) {
        Map<String, Object> param = new HashMap<>();
        param.put("prodId", item.getProdId());
        param.put("endDate", item.getEndDate());
        param.put("startDate", item.getStartDate());
        param.put("prodName", item.getProdName());
        param.put("prodIdName", item.getProdIdName());
        param.put("reportType", reportType);
        param.put("repId", repId);
        return param;
    }

    /**
     * 根据日期获取第几季度
     * @param cdate
     * @return
     */
    private String getJd(Date cdate) {
        if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.三月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return DictCodeListExt3.中债定期报告类型_一季度报.getValue();
        }
        else if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.六月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return DictCodeListExt3.中债定期报告类型_二季度报.getValue();
        }
        else if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.九月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return DictCodeListExt3.中债定期报告类型_三季度报.getValue();
        }
        else if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.十二月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return DictCodeListExt3.中债定期报告类型_四季度报.getValue();
        }
        return null;
    }

    /**
     * 根据日期获取半年度
     * @param cdate
     * @return
     */
    private String getBND(Date cdate) {
        if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.六月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return DictCodeListExt3.中债定期报告类型_上半年报.getValue();
        } else if (StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.十二月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return DictCodeListExt3.中债定期报告类型_下半年报.getValue();
        }
        return null;
    }

    /**
     * 根据日期获取年度
     * @param cdate
     * @return
     */
    private String getND(Date cdate) {
        if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.十二月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return DictCodeListExt3.中债定期报告类型_年报.getValue();
        }
        return null;
    }
    /**
     * 判断是否生成季度报告
     * @param cdate
     * @return
     */
    private String judgeJD(Date cdate) {
        if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.三月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return chbInvestReportCommonService.getDqStartDate(DictCodeListExt3.中债定期报告类型_一季度报.getValue(),
                    DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN));
        }
        else if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.六月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return chbInvestReportCommonService.getDqStartDate(DictCodeListExt3.中债定期报告类型_二季度报.getValue(),
                    DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN));
        }
        else if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.九月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return chbInvestReportCommonService.getDqStartDate(DictCodeListExt3.中债定期报告类型_三季度报.getValue(),
                    DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN));
        }
        else if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.十二月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return chbInvestReportCommonService.getDqStartDate(DictCodeListExt3.中债定期报告类型_四季度报.getValue(),
                    DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN));
        }
        return null;
    }

    /**
     * 判断是否生成半年度报告
     * @param cdate
     * @return
     */
    private String judgeBND(Date cdate) {
        if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.六月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return chbInvestReportCommonService.getDqStartDate(DictCodeListExt3.中债定期报告类型_上半年报.getValue(),
                    DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN));
        } else if (StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.十二月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return chbInvestReportCommonService.getDqStartDate(DictCodeListExt3.中债定期报告类型_下半年报.getValue(),
                    DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN));
        }
        return null;
    }


    /**
     * 判断是否生成年度报告
     * @param cdate
     * @return
     */
    private String judgeND(Date cdate) {
        if(StringUtil.equals(DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN) + ChbInvestReportConstant.十二月最后一天,
                DateUtil.format(cdate, DateUtil.DEFAULT_DATE_FORMAT_EN))) {
            return chbInvestReportCommonService.getDqStartDate(DictCodeListExt3.中债定期报告类型_年报.getValue(),
                    DateUtil.format(cdate, DateUtil.YEAR_DATE_FORMAT_EN));
        }
        return null;
    }
}
