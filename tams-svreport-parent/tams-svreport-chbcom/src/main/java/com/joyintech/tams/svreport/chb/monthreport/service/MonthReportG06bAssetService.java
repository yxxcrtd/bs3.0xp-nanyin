package com.joyintech.tams.svreport.chb.monthreport.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthAssetPositionMapper;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportG06bDetailMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.*;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 银行月度报表生成G06b资产端数据处理类
 *
 * @author WanShiWei
 * @version 1.0 2021年4月28日
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class MonthReportG06bAssetService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChbMonthReportG06bDetailMapper chbMonthReportG06bDetailMapper;

    @Autowired
    private ChbMonthAssetPositionMapper chbMonthAssetPositionMapper;

    @Autowired
    private MonthReportServiceUtil monthReportServiceUtil;

    public void genReport(ChbMonthReport report, boolean ifTrigger) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(report.getEnddate());

        logger.info("生成G06b风险资本计算表明细数据开始：" + calendar.get(Calendar.YEAR) + "年" + calendar.get(Calendar.MONTH) + 1 + "月");
        try {
            generateG06bAssetMonthReportDetail(report, ifTrigger);
        } catch (Exception e) {
            logger.error("G06b风险资本计算表生成错误：" + e.getMessage(), e);
        }
        logger.info("生成G06b风险资本计算表明细数据结束：" + calendar.get(Calendar.YEAR) + "年" + calendar.get(Calendar.MONTH) + 1 + "月");
    }

    /**
     * 功能:根据资产端银行月度报表头寸生成G06b风险资本计算表月度报表数据
     */
    public void generateG06bAssetMonthReportDetail(ChbMonthReport report, Boolean ifTrigger) {
        // 从月报资产头寸中查询数据
        List<ChbMonthG06bPosition> chbMonthG06bPositionList = chbMonthAssetPositionMapper.selectChlG06bPositionByCommon(report.getEnddate());

        if (!CollectionUtils.isEmpty(chbMonthG06bPositionList)) {
            // 根据配置属性自动汇总相应属性值
            logger.info("根据配置属性自动汇总相应属性值开始：");
            countG06bMonthReportAmtInvest(chbMonthG06bPositionList, report, ifTrigger);
            logger.info("根据配置属性自动汇总相应属性值结束：");
        }
    }

    /**
     * 功能:统计G06b风险资本期初期末情况
     */
    private Map<String, ChbMonthReportG06bDetail> countG06bMonthReportAmtInvest(List<ChbMonthG06bPosition> assetG06bMonthPositionList
            , ChbMonthReport report, boolean ifTrigger) {
        // 规则项检查
        if (!checkRuleIsRight(true)) {
            return null;
        }

        Map<String, ChbMonthReportG06bDetail> assetMonthReportG06bDetailMap = new HashMap<String, ChbMonthReportG06bDetail>();

        // 根据配置规则汇总相应数据
        for (ChbMonthG06bPosition position : assetG06bMonthPositionList) {
            for (ChbMonthG06bAssetRule singleRule : ChbMonthG06bAssetRule.values()) {
                // 合计值单独处理--Excel中使用公式计算
                /*if (singleRule.getPropertyName().contains("合计")) {
                    // 判断key值是否已存在，已存在则跳过
                    if (null != deptMap.get(position.getOrgId()) && deptMap.get(position.getOrgId()).containsKey(singleRule.getPropertyCode() + "合计")) {
                        continue;
                    } else {

                        //汇总表记录总数
                        if (!assetMonthReportDetailMap.containsKey(singleRule.getPropertyCode() + "合计")) {
                            assetMonthReportDetailMap.put(singleRule.getPropertyCode() + "合计",
                                    buildDetail(reportStartDate, singleRule.getPropertyCode(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
                        }
                        //保存分支的合计数据
                        if (null != deptMap.get(position.getOrgId())) {
                            //拷贝一份相同数据并传入当前机构分支数据中
                            deptMap.get(position.getOrgId()).put(singleRule.getPropertyCode() + "合计",
                                    buildDetail(reportStartDate, singleRule.getPropertyCode(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

                        }
                    }
                }*/
                // 根据配置规则代码匹配资产头寸中相应属性
                if (!StringUtil.isEmptyList(singleRule.getRuleCodeList())
                        && !StringUtil.isEmptyList(singleRule.getRuleValueList())) {
                    // 该笔头寸记录是否匹配配置规则
                    boolean isPattern = true;
                    // 判断是否多条件交集处理
                    for (int i = 0; i < singleRule.getRuleCodeList().size(); i++) {
                        // 重新初始化是否匹配配置规则标记
                        isPattern = true;

                        // 判断是否多条件任取其一，即rulecode和value一一对应以“|”隔开，格式为code1|code2,value1|value2
                        if (singleRule.getRuleCodeList().get(i).contains(GlobalConstant.VERTICAL_LINE)
                                || singleRule.getRuleValueList().get(i).contains(GlobalConstant.VERTICAL_LINE)) {
                            List<String> ruleCodeList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
                                    singleRule.getRuleCodeList().get(i));
                            List<String> rulevalueList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
                                    singleRule.getRuleValueList().get(i));
                            isPattern = false;
                            String ruleCode = ruleCodeList.get(0);
                            for (int k = 0; k < rulevalueList.size(); k++) {
                                // 如果ruleCode相同但是多条件，则支持只输入一个code，如：code1,value1|value2|value3
                                // 否则需一一对应以“|”隔开，格式为code1|code2,value1|value2
                                if (ruleCodeList.size() > 1) {
                                    ruleCode = ruleCodeList.get(k);
                                }
                                // 支持反向判断！value1
                                if (rulevalueList.get(k).startsWith("!")) {
                                    // 只要有一条满足则该条头寸不计入
                                    if (!rulevalueList.get(k).substring(1)
                                            .equals(getGetMethod(position, ruleCode))) {
                                        isPattern = true;
                                        break;
                                    }
                                } else {
                                    // 只有多个条件都不满足时该头寸才不计入
                                    if (rulevalueList.get(k).equals(getGetMethod(position, ruleCode))) {
                                        isPattern = true;
                                        break;
                                    }
                                }
                            }
                            if (!isPattern) {
                                break;
                            }
                        } else {
                            // 多条件同时满足才计入
                            // 支持反向判断！value1
                            if (singleRule.getRuleValueList().get(i).startsWith("!")) {
                                // 只要有一条满足则该条头寸不计入
                                if (singleRule.getRuleValueList().get(i).substring(1
                                )
                                        .equals(getGetMethod(position,
                                                singleRule.getRuleCodeList().get(i)))) {
                                    isPattern = false;
                                    break;
                                }
                            } else {
                                // 只要有一条不满足则该条头寸不计入
                                if (!singleRule.getRuleValueList().get(i)
                                        .equals(getGetMethod(position, singleRule.getRuleCodeList()
                                                .get(i)))) {
                                    isPattern = false;
                                    break;
                                }
                            }
                        }
                    }

                    // 值域匹配则累计，值域不匹配则计0
                    // 判断key值是否已存在，已存在则累计，未存在则新建
                    //	汇总值数据组装
                    if (assetMonthReportG06bDetailMap.containsKey(singleRule.getPropertyCode())) {
                        // 非合计值
                        if (assetMonthReportG06bDetailMap.containsKey(singleRule.getPropertyCode())) {
                            ChbMonthReportG06bDetail detail = assetMonthReportG06bDetailMap.get(singleRule.getPropertyCode());
                            // 已存在计入本次头寸值，否则不计入本次头寸值
                            if (isPattern) {
                                // 期初余额 = 期初余额 + 新增值
                                detail.setBalanceA(detail.getBalanceA().add(position.getQcAssetValue() == null ? BigDecimal.ZERO : position.getQcAssetValue()));
                                // 期末余额 = 期末余额 + 新增值
                                detail.setBalanceB(detail.getBalanceB().add(position.getQmAssetValue() == null ? BigDecimal.ZERO : position.getQmAssetValue()));
                            }
                        }
                    } else {
                        BigDecimal balanceA = BigDecimal.ZERO;
                        BigDecimal balanceB = BigDecimal.ZERO;

                        // 计入本次头寸值
                        if (isPattern) {
                            balanceA = position.getQcAssetValue() == null ? BigDecimal.ZERO : position.getQcAssetValue();
                            balanceB = position.getQmAssetValue() == null ? BigDecimal.ZERO : position.getQmAssetValue();
                        }

                        //如果汇总项无此合计项则填入
                        assetMonthReportG06bDetailMap.put(singleRule.getPropertyCode(),
                                buildDetail(report, singleRule.getPropertyCode(), balanceA, balanceB));
                    }
                }
            }
        }
        // 入库
        insertDetail(assetMonthReportG06bDetailMap, report);
        return assetMonthReportG06bDetailMap;
    }

    /**
     * 根据获取的参数进行详细信息数据插入
     */
    public void insertDetail(Map<String, ChbMonthReportG06bDetail> assetMonthReportG06bDetailMap, ChbMonthReport report) {
        if (assetMonthReportG06bDetailMap != null
                && assetMonthReportG06bDetailMap.size() != 0) {
            Collection<ChbMonthReportG06bDetail> details = assetMonthReportG06bDetailMap.values();
            // 插入数据
            if (!CollectionUtils.isEmpty(details)) {
                chbMonthReportG06bDetailMapper.insertBatch((List<ChbMonthReportG06bDetail>) details);
            }
        }
    }

    public ChbMonthReportG06bDetail buildDetail(ChbMonthReport report, String properCode, BigDecimal balanceA, BigDecimal balanceB) {
        ChbMonthReportG06bDetail detail = new ChbMonthReportG06bDetail();
        // uuid
        detail.setDetailuuid(IDGenerator.generate(36));
        // 期数
        detail.setTermid(DateUtil.format(report.getStartdate(), "yyyyMM"));
        // 报表类型：BALANCE-资产负债表
        detail.setReporttype(ChbMonthReportPrdConstants.REPORT_TYPE_ASSET);
        // 属性代码
        detail.setPropertycode(properCode);
        // 期初余额
        detail.setBalanceA(balanceA);
        // 期末余额
        detail.setBalanceB(balanceB);
        // 月报主键
        detail.setReportuuid(report.getReportuuid());
        // 组织机构
        detail.setOrgCode(report.getOrgCode());
        // 部门代码
        detail.setDeptCode(report.getDeptCode());

        return detail;
    }

    /**
     * 根据属性，获取get方法(不区分大小写)
     *
     * @param ob   对象
     * @param name 属性名
     * @return
     */
    private Object getGetMethod(Object ob, String name) {
        Method[] m = ob.getClass().getMethods();
        for (Method method : m) {
            if (("get" + name).equalsIgnoreCase(method.getName())) {
                try {
                    return method.invoke(ob);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.error(e.getMessage(), e);
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 功能:检查匹配规则是否完善
     */
    private boolean checkRuleIsRight(boolean isIncludeNull) {
        // 检查配置规则项
        for (ChbMonthG06bAssetRule singleRule : ChbMonthG06bAssetRule.values()) {
            // 检查属性代码是否为空
            if (StringUtil.isEmpty(singleRule.getPropertyCode())) {
                logger.error("请检查属性代码是否为空！");
                return false;
            }
            if (!isIncludeNull) {
                // 检查配置规则代码域与配置规则值域是否为空,支持为空时请关闭此项
                if (StringUtil.isEmptyList(singleRule.getRuleCodeList())
                        || StringUtil.isEmptyList(singleRule.getRuleValueList())) {
                    logger.error("请检查置规则代码域与配置规则值域是否为空！");
                    return false;
                }
            }
            // 判断配置规则代码域与配置规则值域是否大小一致，即一一对应
            if (singleRule.getRuleCodeList().size() != singleRule.getRuleValueList().size()) {
                logger.error("请检查属性代码:" + singleRule.getPropertyCode() + "对应规则值域与对应规则代码域大小是否对应一致！");
                return false;
            }
        }
        return true;
    }
}
