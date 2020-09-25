/*
 * 文件名：OwnerEquityChange001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.ownerequitychange.service;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.ownerequitychange.dao.OwnerEquityChange001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.ownerequitychange.dto.OwnerEquityChange001Dto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.common.AssetLiabilityStatementEnum;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangzhao
 * @version 1.0 2018年9月18日
 * @see OwnerEquityChange001Service
 * @since 1.0
 */
@Service(value = "serviceOwnerEquityChange001Service")
public class OwnerEquityChange001Service extends CommonExportService<OwnerEquityChange001Dto> {

    @Autowired
    private OwnerEquityChange001Mapper ownerEquityChange001Mapper;

    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    /**
     * 标的类共通方法
     */
    @Autowired
    private FinProductCommonBatchService finProductCommonBatchService;

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<OwnerEquityChange001Dto> selectList(Map<String, Object> pageParam, OwnerEquityChange001Dto dto)
            throws Exception {
        changeMapData(pageParam);
        Map<String, OwnerEquityChange001Dto> dtoMap = new HashMap<String, OwnerEquityChange001Dto>();
        List<OwnerEquityChange001Dto> list = new ArrayList<OwnerEquityChange001Dto>();

        // 报表维度为单账套
        if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(String.valueOf(pageParam.get("reportDimen")))) {
            // 报表维度为单账套
            initData(dtoMap);
            // 调用共通方法【详细设计-标的类共通方法设计】-核算主体查询
            FinProduct finProduct = finProductCommonBatchService.queryAcctSubject((String) pageParam.get("booksetId"));
            String thisTermVdate1 = (String) pageParam.get("thisTermVdate"); // 实收资本
            String thisTermVdate2 = (String) pageParam.get("thisTermVdate"); // 非实收资本
            String thisTermMdate = (String) pageParam.get("thisTermMdate");
            if (finProduct != null && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(thisTermVdate1)) > 0
                    && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(thisTermMdate)) < 0) {
                thisTermVdate1 = DateUtil.format(finProduct.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
            }

            String lastTermVdate1 = (String) pageParam.get("lastTermVdate"); // 实收资本
            String lastTermVdate2 = (String) pageParam.get("lastTermVdate"); // 非实收资本
            String lastTermMdate = (String) pageParam.get("lastTermMdate");
            if (finProduct != null && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(lastTermVdate1)) > 0
                    && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(lastTermMdate)) < 0) {
                lastTermVdate1 = DateUtil.format(finProduct.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
            }

            // ---------------------------------期初--------------------------------------------
            // 本期期初
            pageParam.put("earlysta", "01");
            pageParam.put("vdate1", thisTermVdate1);
            pageParam.put("vdate2", thisTermVdate2);
            List<OwnerEquityChange001Dto> thisTermData = ownerEquityChange001Mapper.selectAll(pageParam);
            // 上去期初
            pageParam.put("vdate1", lastTermVdate1);
            pageParam.put("vdate2", lastTermVdate2);
            List<OwnerEquityChange001Dto> lastTermData = ownerEquityChange001Mapper.selectAll(pageParam);
            // ---------------------------------本期--------------------------------------------
            // 本期期中
            pageParam.put("earlysta", "02");
            pageParam.put("vdate1", thisTermVdate1);
            pageParam.put("vdate2", thisTermVdate2);
            pageParam.put("mdate", pageParam.get("thisTermMdate"));
            List<OwnerEquityChange001Dto> thisTermData_end = ownerEquityChange001Mapper.selectAll(pageParam);
            formatData(thisTermData_end, true, dtoMap, thisTermData);
            // 上期其中
            pageParam.put("vdate1", lastTermVdate1);
            pageParam.put("vdate2", lastTermVdate2);
            pageParam.put("mdate", pageParam.get("lastTermMdate"));
            List<OwnerEquityChange001Dto> lastTermData_end = ownerEquityChange001Mapper.selectAll(pageParam);
            formatData(lastTermData_end, false, dtoMap, lastTermData);

            list.addAll(dtoMap.values());
        } else {
        	List<String> accountList = new ArrayList<>();
        	if(StringUtil.isNotEmpty(dto)) {
        		accountList = Arrays.asList(dto.getBooksetId().split(","));
        	}else {
        		List<SysBatchGrpPrdlink> list1 = valAssetBook001Service.findAllProduct(pageParam);
            	String bookSetIdGroup = list1.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(","));
                accountList = Arrays.asList(bookSetIdGroup.split(","));
			}
            for (String account : accountList) {
                // 报表维度为账套组
                //List<SysBatchGrpPrdlink> list1 = valAssetBook001Service.findAllProduct(pageParam);

                initData(dtoMap);
                // 调用共通方法【详细设计-标的类共通方法设计】-核算主体查询
                FinProduct finProduct = finProductCommonBatchService.queryAcctSubject(account);
                String thisTermVdate1 = (String) pageParam.get("thisTermVdate"); // 实收资本
                String thisTermVdate2 = (String) pageParam.get("thisTermVdate"); // 非实收资本
                String thisTermMdate = (String) pageParam.get("thisTermMdate");
                if (finProduct != null
                        && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(thisTermVdate1)) > 0
                        && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(thisTermMdate)) < 0) {
                    thisTermVdate1 = DateUtil.format(finProduct.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
                }

                String lastTermVdate1 = (String) pageParam.get("lastTermVdate"); // 实收资本
                String lastTermVdate2 = (String) pageParam.get("lastTermVdate"); // 非实收资本
                String lastTermMdate = (String) pageParam.get("lastTermMdate");
                if (finProduct != null
                        && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(lastTermVdate1)) > 0
                        && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(lastTermMdate)) < 0) {
                    lastTermVdate1 = DateUtil.format(finProduct.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
                }

                // ---------------------------------期初--------------------------------------------
                // 本期期初
                pageParam.put("earlysta", "01");
                pageParam.put("vdate1", thisTermVdate1);
                pageParam.put("vdate2", thisTermVdate2);
                pageParam.put("booksetId", account);
                List<OwnerEquityChange001Dto> thisTermData = ownerEquityChange001Mapper.selectAll(pageParam);
                // 上去期初
                pageParam.put("vdate1", lastTermVdate1);
                pageParam.put("vdate2", lastTermVdate2);
                List<OwnerEquityChange001Dto> lastTermData = ownerEquityChange001Mapper.selectAll(pageParam);
                // ---------------------------------本期--------------------------------------------
                // 本期期中
                pageParam.put("earlysta", "02");
                pageParam.put("vdate1", thisTermVdate1);
                pageParam.put("vdate2", thisTermVdate2);
                pageParam.put("mdate", pageParam.get("thisTermMdate"));
                List<OwnerEquityChange001Dto> thisTermData_end = ownerEquityChange001Mapper.selectAll(pageParam);
                formatData(thisTermData_end, true, dtoMap, thisTermData);
                // 上期其中
                pageParam.put("vdate1", lastTermVdate1);
                pageParam.put("vdate2", lastTermVdate2);
                pageParam.put("mdate", pageParam.get("lastTermMdate"));
                List<OwnerEquityChange001Dto> lastTermData_end = ownerEquityChange001Mapper.selectAll(pageParam);
                formatData(lastTermData_end, false, dtoMap, lastTermData);

                list.addAll(dtoMap.values());
            }
        }

        list.sort(new Comparator<OwnerEquityChange001Dto>() {

            @Override
            public int compare(OwnerEquityChange001Dto arg0, OwnerEquityChange001Dto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                } else if (arg0.getSortNum() > arg1.getSortNum()) {
                    return 1;
                }
                return 0;
            }
        });
        // 账套组情况下,添加样式
        if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals(String.valueOf(pageParam.get("reportDimen")))) {
            return dealStyle(list);
        } else {
            return list;
        }
    }

    /**
     * 处理样式
     *
     * @param list
     * @param style
     * @param reportDimen
     *            报表维度
     * @see
     * @since 1.0
     */
    public List<OwnerEquityChange001Dto> dealStyle(List<OwnerEquityChange001Dto> list) {

        List<OwnerEquityChange001Dto> resList = new ArrayList<OwnerEquityChange001Dto>();

        for (int i = 0; i < list.size(); i++) {

            if (i == 0) {
                resList.add(list.get(i));
            } else {
                if (hasSameProject(list.get(i).getProject(), resList)) {
                    OwnerEquityChange001Dto resModel = resList.get(resList.size() - 1);
                    resModel.settIncomeFund(resModel.gettIncomeFund().add(list.get(i).gettIncomeFund()));
                    resModel.settUnQuarterProfit(resModel.gettUnQuarterProfit().add(list.get(i).gettUnQuarterProfit()));
                    resModel.settOtherCompIncome(resModel.gettOtherCompIncome().add(list.get(i).gettOtherCompIncome()));
                    resModel.settOwnerEquityTot(resModel.gettOwnerEquityTot().add(list.get(i).gettOwnerEquityTot()));
                    resModel.setyIncomeFund(resModel.getyIncomeFund().add(list.get(i).getyIncomeFund()));
                    resModel.setyUnQuarterProfit(resModel.getyUnQuarterProfit().add(list.get(i).getyUnQuarterProfit()));
                    resModel.setyOtherCompIncome(resModel.getyOtherCompIncome().add(list.get(i).getyOtherCompIncome()));
                    resModel.setyOwnerEquityTot(resModel.getyOwnerEquityTot().add(list.get(i).getyOwnerEquityTot()));

                } else {
                    resList.add(list.get(i));
                }
            }

        }
        return resList;
    }

    private boolean hasSameProject(String project, List<OwnerEquityChange001Dto> list) {

        for (OwnerEquityChange001Dto model : list) {
            if (project.equals(model.getProject())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询现金流量表数据list
     *
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<OwnerEquityChange001Dto> selectListp(Map<String, Object> pageParam, OwnerEquityChange001Dto dto)
            throws ParseException {
        try {
            changeMapData(pageParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, OwnerEquityChange001Dto> dtoMap = new HashMap<String, OwnerEquityChange001Dto>();
        List<OwnerEquityChange001Dto> list1 = new ArrayList<OwnerEquityChange001Dto>();
        // 全账套
        List<String> accountList = Arrays.asList(dto.getBooksetId().split(","));
        // 项目名
        String project = dto.getProject();
        for (String account : accountList) {
            initData(dtoMap);
            // 调用共通方法【详细设计-标的类共通方法设计】-核算主体查询
            FinProduct finProduct = finProductCommonBatchService.queryAcctSubject(account);
            String thisTermVdate1 = (String) pageParam.get("thisTermVdate"); // 实收资本
            String thisTermVdate2 = (String) pageParam.get("thisTermVdate"); // 非实收资本
            String thisTermMdate = (String) pageParam.get("thisTermMdate");
            if (finProduct != null && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(thisTermVdate1)) > 0
                    && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(thisTermMdate)) < 0) {
                thisTermVdate1 = DateUtil.format(finProduct.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
            }

            String lastTermVdate1 = (String) pageParam.get("lastTermVdate"); // 实收资本
            String lastTermVdate2 = (String) pageParam.get("lastTermVdate"); // 非实收资本
            String lastTermMdate = (String) pageParam.get("lastTermMdate");
            if (finProduct != null && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(lastTermVdate1)) > 0
                    && DateUtil.compareDate(finProduct.getVdate(), DateUtil.parse(lastTermMdate)) < 0) {
                lastTermVdate1 = DateUtil.format(finProduct.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
            }

            // ---------------------------------期初--------------------------------------------
            // 本期期初
            pageParam.put("earlysta", "01");
            pageParam.put("vdate1", thisTermVdate1);
            pageParam.put("vdate2", thisTermVdate2);
            pageParam.put("booksetId", account);
            List<OwnerEquityChange001Dto> thisTermData = ownerEquityChange001Mapper.selectAll(pageParam);
            // 上去期初
            pageParam.put("vdate1", lastTermVdate1);
            pageParam.put("vdate2", lastTermVdate2);
            List<OwnerEquityChange001Dto> lastTermData = ownerEquityChange001Mapper.selectAll(pageParam);
            // ---------------------------------本期--------------------------------------------
            // 本期期中
            pageParam.put("earlysta", "02");
            pageParam.put("vdate1", thisTermVdate1);
            pageParam.put("vdate2", thisTermVdate2);
            pageParam.put("mdate", pageParam.get("thisTermMdate"));
            List<OwnerEquityChange001Dto> thisTermData_end = ownerEquityChange001Mapper.selectAll(pageParam);
            formatData(thisTermData_end, true, dtoMap, thisTermData);
            // 上期其中
            pageParam.put("vdate1", lastTermVdate1);
            pageParam.put("vdate2", lastTermVdate2);
            pageParam.put("mdate", pageParam.get("lastTermMdate"));
            List<OwnerEquityChange001Dto> lastTermData_end = ownerEquityChange001Mapper.selectAll(pageParam);
            formatData(lastTermData_end, false, dtoMap, lastTermData);

            List<OwnerEquityChange001Dto> list = new ArrayList<OwnerEquityChange001Dto>();
            list.addAll(dtoMap.values());

            // 找到项目对应的
            Map<String, OwnerEquityChange001Dto> map = Maps.uniqueIndex(list,
                    new Function<OwnerEquityChange001Dto, String>() {

                        @Override
                        public String apply(OwnerEquityChange001Dto t) {
                            return t.getProject();
                        }

                    });
            OwnerEquityChange001Dto tempDto = map.get(project);
            tempDto.setBooksetId(account);
            list1.add(tempDto);

        }
        return list1;
    }

    /**
     * 把科目余额数据转换为查询数据
     *
     * @param dataList 科目余额信息
     * @param flag     true-本期数据 false-上期数据
     * @param dtoMap   转换的dto数据
     * @see
     * @since 1.0
     */
    public void formatData(List<OwnerEquityChange001Dto> dataList, boolean flag,
                           Map<String, OwnerEquityChange001Dto> dtoMap, List<OwnerEquityChange001Dto> dataList2) {
        // ---------------------------------------五、期末所有者权益（产品净值）--------------------------------------------
        // 所有者权益
        BigDecimal qm_sszb = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal qm_wfplr = BigDecimal.ZERO;
        // 其他中和收益
        BigDecimal qm_qtzhsy = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal qm_syzqy = BigDecimal.ZERO;
        // ---------------------------------------本期向产品份额持有人分配利润产生的产品净值变动（净值减少以“-”号填列）
        // -
        // 应付利润-------------
        // 期初_应付利润
        BigDecimal bqxcpfe_wfplr_qc = BigDecimal.ZERO;
        // ---------------------------------------一、期初所有者权益（产品净值）--------------------------------------------
        // 实收资本
        BigDecimal qcsyzqy_sszb = BigDecimal.ZERO;
        // 未分配利润
        BigDecimal qcsyzqy_wfplr = BigDecimal.ZERO;
        // 其他综合收益
        BigDecimal qcsyzqy_qtzhsy = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal qcsyzqy_syzqy = BigDecimal.ZERO;
        // 期初
        for (OwnerEquityChange001Dto dto2 : dataList2) {
            if (StringUtil.equals("4001", dto2.getSubjectNo())) {
                if (StringUtil.equals(DictCodeList.余额方向_贷方.getValue(), dto2.getBalFlag())) {
                    // 本币余额 + 本币结转余额借方- 本币结转余额贷方
                    qcsyzqy_sszb = ArithmeticUtil.doAdd(qcsyzqy_sszb, dto2.getbAmt(),
                            ArithmeticUtil.subtract(dto2.getbCoCAmt(), dto2.getbCoDAmt()));
                } else {
                    // 本币余额需要按照期待方向判断如果不一致本币余额=本币余额*-1
                    qcsyzqy_sszb = ArithmeticUtil.doAdd(qcsyzqy_sszb,
                            ArithmeticUtil.multiply(dto2.getbAmt(), BigDecimal.valueOf(-1)),
                            ArithmeticUtil.subtract(dto2.getbCoCAmt(), dto2.getbCoDAmt()));
                }
            }
            if (StringUtil.equals(AssetLiabilityStatementEnum.所有者权益类_其他综合收益.getValue(),
                    dto2.getSubjectNo())) {
                if (StringUtil.equals(DictCodeList.余额方向_贷方.getValue(), dto2.getBalFlag())) {
                    // 其他综合收益 = 本币余额
                    qcsyzqy_qtzhsy = dto2.getbAmt();
                } else {
                    // 其他综合收益 = -1*本币余额
                    qcsyzqy_qtzhsy = ArithmeticUtil.multiply(dto2.getbAmt(),
                            BigDecimal.valueOf(-1));
                }
            }
            String subject = dto2.getSubjectNo().substring(0, 1);

            if ("4".equals(subject)) {
                if (StringUtil.equals(DictCodeList.余额方向_贷方.getValue(), dto2.getBalFlag())) {
                    qcsyzqy_syzqy = ArithmeticUtil.doAdd(qcsyzqy_syzqy, dto2.getbAmt());
                } else {
                    qcsyzqy_syzqy = ArithmeticUtil.subtract(qcsyzqy_syzqy, dto2.getbAmt());
                }
            }

            if (StringUtil.equals("2232", dto2.getSubjectNo())) {
                // 本期 本币余额 + 本币结转余额贷方- 本币结转余额借方 FIXME
                bqxcpfe_wfplr_qc = ArithmeticUtil.doAdd(bqxcpfe_wfplr_qc, dto2.getbCAmt(),
                        ArithmeticUtil.subtract(dto2.getbCoCAmt(), dto2.getbCoDAmt()));
            }
        }
        // 期初所有者权益合计-其他综合收益- 期初实收资本
        qcsyzqy_wfplr = ArithmeticUtil.doSub(qcsyzqy_syzqy, qcsyzqy_qtzhsy, qcsyzqy_sszb);
        if (flag) {
            dtoMap.get("一、期初所有者权益（产品净值）").settIncomeFund(qcsyzqy_sszb);
            dtoMap.get("一、期初所有者权益（产品净值）").settUnQuarterProfit(qcsyzqy_wfplr);
            dtoMap.get("一、期初所有者权益（产品净值）").settOwnerEquityTot(qcsyzqy_syzqy);
            dtoMap.get("一、期初所有者权益（产品净值）").settOtherCompIncome(qcsyzqy_qtzhsy);
        } else {
            dtoMap.get("一、期初所有者权益（产品净值）").setyIncomeFund(qcsyzqy_sszb);
            dtoMap.get("一、期初所有者权益（产品净值）").setyUnQuarterProfit(qcsyzqy_wfplr);
            dtoMap.get("一、期初所有者权益（产品净值）").setyOwnerEquityTot(qcsyzqy_syzqy);
            dtoMap.get("一、期初所有者权益（产品净值）").setyOtherCompIncome(qcsyzqy_qtzhsy);
        }
        // ---------------------------------------二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）--------------------------------------------
        // 实收资本
        BigDecimal bqjyhd_sszb = BigDecimal.ZERO;
        // 未分配利润
        BigDecimal bqjyhd_wfplr = BigDecimal.ZERO;
        // 其他综合收益
        BigDecimal bqjyhd_qtzhsy = BigDecimal.ZERO;
        // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_所有者权益 =
        // 本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-未分配利润+本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-其他综合收益
        BigDecimal bqjyhd_syzqy = BigDecimal.ZERO;
        // ---------------------------------------三、本期产品份额交易产生的产品净值变动数--------------------------------------------------
        // -实收资本
        BigDecimal bqcpfe_sszb = BigDecimal.ZERO;
        // 应付利润
        BigDecimal bqcpfe_wfplr = BigDecimal.ZERO;
        // 其他综合收益
        BigDecimal bqcpfe_qtzhsy = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal bqcpfe_syzqy = BigDecimal.ZERO;
        // ---------------------------------------三、本期产品份额交易产生的产品净值变动数
        // 1.产品认申购------------------------------------------
        // -实收资本
        BigDecimal cprsg_sszb = BigDecimal.ZERO;
        // 应付利润
        BigDecimal cprsg_wfplr = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal cprsg_syzqy = BigDecimal.ZERO;
        // ---------------------------------------三、本期产品份额交易产生的产品净值变动数
        // 2.产品赎回-------------------------------------------
        // -实收资本
        BigDecimal cpsh_sszb = BigDecimal.ZERO;
        // 应付利润
        BigDecimal cpsh_wfplr = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal cpsh_syzqy = BigDecimal.ZERO;
        // ---------------------------------------四、本期向产品份额持有人分配利润产生的产品净值变动--------------------------------------------------------
        // -实收资本
        // BigDecimal bqxcpfe_sszb_bq=BigDecimal.ZERO;
        // 应付利润
        BigDecimal bqxcpfe_wfplr_bq = BigDecimal.ZERO;
        // 所有者权益
        // BigDecimal bqxcpfe_syzqy_bq=BigDecimal.ZERO;
        // -实收资本
        BigDecimal bqxcpfe_sszb = BigDecimal.ZERO;
        // 应付利润
        BigDecimal bqxcpfe_wfplr = BigDecimal.ZERO;
        // 其他综合收益
        BigDecimal bqxcpfe_qtzhsy = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal bqxcpfe_syzqy = BigDecimal.ZERO;

        // --------------------------------------- 计算
        // --------------------------------------------------
        for (OwnerEquityChange001Dto dto : dataList) {
            // ------------------二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）--------------
            // 实收资本
            bqjyhd_sszb = BigDecimal.ZERO;
            // 未分配利润
            if (StringUtil.equals("6011", dto.getSubjectNo())
                    || StringUtil.equals("6101", dto.getSubjectNo())
                    || StringUtil.equals("6111", dto.getSubjectNo())
                    || StringUtil.equals("6112", dto.getSubjectNo())
                    || StringUtil.equals("6302", dto.getSubjectNo())
                    || StringUtil.equals("6412", dto.getSubjectNo())
                    || StringUtil.equals("6403", dto.getSubjectNo())
                    || StringUtil.equals("6404", dto.getSubjectNo())
                    || StringUtil.equals("6406", dto.getSubjectNo())
                    || StringUtil.equals("6409", dto.getSubjectNo())
                    || StringUtil.equals("6410", dto.getSubjectNo())
                    || StringUtil.equals("6407", dto.getSubjectNo())
                    || StringUtil.equals("6408", dto.getSubjectNo())
                    || StringUtil.equals("6411", dto.getSubjectNo())
                    || StringUtil.equals("6605", dto.getSubjectNo())
                    || StringUtil.equals("6702", dto.getSubjectNo())) {
                // 本币当日发生额贷方-本币当日发生额借方+本币结转当日发生额借方 - 本币结转当日发生额贷方
                bqjyhd_wfplr = ArithmeticUtil.doAdd(bqjyhd_wfplr,
                        ArithmeticUtil.subtract(
                                dto.getTdyBCAmt() == null ? BigDecimal.ZERO : dto.getTdyBCAmt(),
                                dto.getTdyBDAmt() == null ? BigDecimal.ZERO : dto.getTdyBDAmt()),
                        ArithmeticUtil.subtract(
                                dto.getTdyBCoDAmt() == null ? BigDecimal.ZERO : dto.getTdyBCoDAmt(),
                                dto.getTdyBCoCAmt() == null ? BigDecimal.ZERO : dto.getTdyBCoCAmt()));

            }
            if (StringUtil.equals(AssetLiabilityStatementEnum.所有者权益类_其他综合收益.getValue(),
                    dto.getSubjectNo())) {
                // 其他综合收益 = 本币当日发生额贷方-本币当日发生额借方
                bqjyhd_qtzhsy = ArithmeticUtil.subtract(dto.getTdyBCAmt(), dto.getTdyBDAmt());
            }
            // ---------------------------------------三、本期产品份额交易产生的产品净值变动数--------------------------------------------------
            if (StringUtil.equals("4001", dto.getSubjectNo())) {
                // -------------------------三、本期产品份额交易产生的产品净值变动数_实收资本--------------------------
                // SUM（本币当日发生额贷方）
                cprsg_sszb = ArithmeticUtil.doAdd(cprsg_sszb, dto.getTdyBCAmt());
                // -1*SUM（本币当日发生额借方）
                cpsh_sszb = ArithmeticUtil.doAdd(cpsh_sszb, dto.getTdyBDAmt());

            }
            // -------------------------三、本期产品份额交易产生的产品净值变动数_未分配利润--------------------------
            if (StringUtil.equals("4011", dto.getSubjectNo())) {
                // 本位币申购未实现平准金贷
                cprsg_wfplr = ArithmeticUtil.doAdd(cprsg_wfplr,
                        ArithmeticUtil.subtract(dto.getTdyPurBCUlamt(), dto.getTdyPurBDUlamt()));
                // 本位币赎回未实现平准金贷
                cpsh_wfplr = ArithmeticUtil.doAdd(cpsh_wfplr,
                        ArithmeticUtil.subtract(dto.getTdyRedBCUlamt(), dto.getTdyRedBDUlamt()));
            }
            // ------------------------- 四、本期向产品份额持有人分配利润产生的产品净值变动
            // --------------------------------------------------
            if (StringUtil.equals("2232", dto.getSubjectNo())) {
                // 本期 本币余额 + 本币结转余额贷方- 本币结转余额借方
                bqxcpfe_wfplr_bq = ArithmeticUtil.doAdd(bqxcpfe_wfplr_bq, dto.getTdyBCAmt(),
                        ArithmeticUtil.subtract(dto.getbCoCAmt(), dto.getbCoDAmt()));
            }
        }
        // 本期-期初
        // bqxcpfe_wfplr = ArithmeticUtil.subtract(bqxcpfe_wfplr_bq, bqxcpfe_wfplr_qc);
        bqxcpfe_wfplr = ArithmeticUtil.subtract(bqxcpfe_wfplr_bq, BigDecimal.ZERO);
        // 此数据恒负数
        if (bqxcpfe_wfplr.compareTo(BigDecimal.ZERO) >= 0) {
            bqxcpfe_wfplr = ArithmeticUtil.multiply(BigDecimal.valueOf(-1), bqxcpfe_wfplr);
        }
        cpsh_sszb = ArithmeticUtil.multiply(BigDecimal.valueOf(-1), cpsh_sszb);
        // --------------------------------------- 赋值
        // --------------------------------------------------
        if (flag) {
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").settIncomeFund(bqjyhd_sszb);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").settUnQuarterProfit(bqjyhd_wfplr);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").settOtherCompIncome(bqjyhd_qtzhsy);
            // 本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-未分配利润+本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-其他综合收益
            bqjyhd_syzqy = ArithmeticUtil.doAdd(bqjyhd_wfplr, bqjyhd_qtzhsy);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").settOwnerEquityTot(bqjyhd_syzqy);
            // 实收资本
            bqcpfe_sszb = ArithmeticUtil.doAdd(cprsg_sszb, cpsh_sszb);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").settIncomeFund(bqcpfe_sszb);
            dtoMap.get("1.产品认申购").settIncomeFund(cprsg_sszb);
            dtoMap.get("2.产品赎回").settIncomeFund(cpsh_sszb);
            // 未分配利润
            bqcpfe_wfplr = ArithmeticUtil.doAdd(cprsg_wfplr, cpsh_wfplr);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").settUnQuarterProfit(bqcpfe_wfplr);
            dtoMap.get("1.产品认申购").settUnQuarterProfit(cprsg_wfplr);
            dtoMap.get("2.产品赎回").settUnQuarterProfit(cpsh_wfplr);
            // 所有者权益=实收资本+费分配利润
            bqcpfe_syzqy = ArithmeticUtil.doAdd(bqcpfe_sszb, bqcpfe_wfplr);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").settOwnerEquityTot(bqcpfe_syzqy);

            cprsg_syzqy = ArithmeticUtil.doAdd(cprsg_sszb, cprsg_wfplr);
            dtoMap.get("1.产品认申购").settOwnerEquityTot(cprsg_syzqy);

            cpsh_syzqy = ArithmeticUtil.doAdd(cpsh_sszb, cpsh_wfplr);
            dtoMap.get("2.产品赎回").settOwnerEquityTot(cpsh_syzqy);

            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").settIncomeFund(bqxcpfe_sszb);
            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").settUnQuarterProfit(bqxcpfe_wfplr);
            // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_所有者权益 = 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_实收资本+
            // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_未分配利润
            bqxcpfe_syzqy = ArithmeticUtil.doAdd(bqxcpfe_sszb, bqxcpfe_wfplr);
            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").settOwnerEquityTot(bqxcpfe_syzqy);
        } else {
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").setyIncomeFund(bqjyhd_sszb);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").setyUnQuarterProfit(bqjyhd_wfplr);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").setyOtherCompIncome(bqjyhd_qtzhsy);
            // 本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-未分配利润+
            // 本期经营活动产生的产品净值变动数(本期净利润及其他综合收益)-其他综合收益
            bqjyhd_syzqy = ArithmeticUtil.doAdd(bqjyhd_wfplr, bqjyhd_qtzhsy);
            dtoMap.get("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）").setyOwnerEquityTot(bqjyhd_syzqy);
            // 实收资本
            bqcpfe_sszb = ArithmeticUtil.doAdd(cprsg_sszb, cpsh_sszb);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").setyIncomeFund(bqcpfe_sszb);
            dtoMap.get("1.产品认申购").setyIncomeFund(cprsg_sszb);
            dtoMap.get("2.产品赎回").setyIncomeFund(cpsh_sszb);
            // 未分配利润
            bqcpfe_wfplr = ArithmeticUtil.doAdd(cprsg_wfplr, cpsh_wfplr);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").setyUnQuarterProfit(bqcpfe_wfplr);
            dtoMap.get("1.产品认申购").setyUnQuarterProfit(cprsg_wfplr);
            dtoMap.get("2.产品赎回").setyUnQuarterProfit(cpsh_wfplr);
            // 所有者权益=实收资本+费分配利润
            bqcpfe_syzqy = ArithmeticUtil.doAdd(bqcpfe_sszb, bqcpfe_wfplr);
            dtoMap.get("三、本期产品份额交易产生的产品净值变动数").setyOwnerEquityTot(bqcpfe_syzqy);

            cprsg_syzqy = ArithmeticUtil.doAdd(cprsg_sszb, cprsg_wfplr);
            dtoMap.get("1.产品认申购").setyOwnerEquityTot(cprsg_syzqy);

            cpsh_syzqy = ArithmeticUtil.doAdd(cpsh_sszb, cpsh_wfplr);
            dtoMap.get("2.产品赎回").setyOwnerEquityTot(cpsh_syzqy);

            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").setyIncomeFund(bqxcpfe_sszb);
            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").setyUnQuarterProfit(bqxcpfe_wfplr);
            // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_所有者权益 = 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_实收资本+
            // 本期经营活动产生的产品净值变动数（本期利润及其他综合收益）_未分配利润
            bqxcpfe_syzqy = ArithmeticUtil.doAdd(bqxcpfe_sszb, bqxcpfe_wfplr);
            dtoMap.get("四、本期向产品份额持有人分配利润产生的产品净值变动").setyOwnerEquityTot(bqxcpfe_syzqy);

        }
        qm_sszb = ArithmeticUtil.doAdd(qm_sszb, qcsyzqy_sszb, bqjyhd_sszb, bqcpfe_sszb,
                bqxcpfe_sszb);
        qm_wfplr = ArithmeticUtil.doAdd(qm_wfplr, qcsyzqy_wfplr, bqjyhd_wfplr, bqcpfe_wfplr,
                bqxcpfe_wfplr);
        // ∑[期初所有者权益（产品净值）其他综合收益合计列+本期经营活动产生的产品净值变动数（本期净利润及其他综合收益）其他综合收益计列+
        // 本期产品份额交易产生的产品净值变动数其他综合收益合计列+本期向产品份额持有人分配利润产生的产品净值变动其他综合收益合计列]
        qm_qtzhsy = ArithmeticUtil.doAdd(qm_qtzhsy, qcsyzqy_qtzhsy, bqjyhd_qtzhsy, bqcpfe_qtzhsy,
                bqxcpfe_qtzhsy);
        qm_syzqy = ArithmeticUtil.doAdd(qm_syzqy, qcsyzqy_syzqy, bqjyhd_syzqy, bqcpfe_syzqy,
                bqxcpfe_syzqy);
        // 期末所有者权益-所有者权益合计

        if (flag) {
            dtoMap.get("五、期末所有者权益（产品净值）").settIncomeFund(qm_sszb);
            dtoMap.get("五、期末所有者权益（产品净值）").settUnQuarterProfit(qm_wfplr);
            dtoMap.get("五、期末所有者权益（产品净值）").settOtherCompIncome(qm_qtzhsy);
            dtoMap.get("五、期末所有者权益（产品净值）").settOwnerEquityTot(qm_syzqy);
        } else {
            dtoMap.get("五、期末所有者权益（产品净值）").setyIncomeFund(qm_sszb);
            dtoMap.get("五、期末所有者权益（产品净值）").setyUnQuarterProfit(qm_wfplr);
            dtoMap.get("五、期末所有者权益（产品净值）").setyOtherCompIncome(qm_qtzhsy);
            dtoMap.get("五、期末所有者权益（产品净值）").setyOwnerEquityTot(qm_syzqy);
        }

    }

    /**
     * 设置页面传回参数
     *
     * @param pageParam
     * @throws Exception
     * @see
     * @since 1.0
     */
    public void changeMapData(Map<String, Object> pageParam)
            throws Exception {
        String reportTermType = (String) pageParam.get("reportTermType");
        String valDate = DateUtil.format(analyseDate(pageParam), DateUtil.DEFAULT_DATE_FORMAT_EN);
        int thisYear = DateUtil.getYear(valDate);
        int lastYear = thisYear - 1;
        int yearBeforeLast = thisYear - 2;
        String reportTerm = (String) pageParam.get("reportTerm");
        String lastDimen = (String) pageParam.get("lastDimen");
        if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_日报.getValue())) {

            pageParam.put("thisTermMdate", valDate); // 本期
            pageParam.put("thisTermVdate", DateUtil.addDay(valDate, -1));// 期初
            if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                pageParam.put("lastTermMdate", DateUtil.addDay(valDate, -1)); // 上期本期
                pageParam.put("lastTermVdate", DateUtil.addDay(valDate, -2)); // 上期期初
            } else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                pageParam.put("lastTermMdate",
                        DateUtil.format(DateUtil.addYear(DateUtil.parse(valDate), -1),
                                DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                        DateUtil.format(DateUtil.addDate(DateUtil.parse(valDate), -1, 0, -1),
                                DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
        } else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_月报.getValue())) {
            pageParam.put("thisTermMdate", DateUtil.format(
                    DateUtil.getLastDay(DateUtil.parse(valDate)), DateUtil.DEFAULT_DATE_FORMAT_EN)); // 本期
            pageParam.put("thisTermVdate",
                    DateUtil.format(
                            DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -1)),
                            DateUtil.DEFAULT_DATE_FORMAT_EN)); // 期初
            if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                pageParam.put("lastTermMdate",
                        DateUtil.format(
                                DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -1)),
                                DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                        DateUtil.format(
                                DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -2)),
                                DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            } else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                pageParam.put("lastTermMdate",
                        DateUtil.format(
                                DateUtil.getLastDay(DateUtil.addYear(DateUtil.parse(valDate), -1)),
                                DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                        DateUtil.format(
                                DateUtil.getLastDay(DateUtil.addDate(DateUtil.parse(valDate), -1, -1, 0)),
                                DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
        } else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_季报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第一季度.getValue())) {
                pageParam.put("thisTermVdate", lastYear + "-12-31"); // 期初
                pageParam.put("thisTermMdate", thisYear + "-03-31"); // 本期
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-09-30"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-12-31"); // 本期
                } else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", yearBeforeLast + "-12-31"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-03-31"); // 本期
                }
            } else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第二季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-03-31"); // 期初
                pageParam.put("thisTermMdate", thisYear + "-06-30"); // 本期
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-12-31"); // 期初
                    pageParam.put("lastTermMdate", thisYear + "-03-31"); // 本期
                } else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-03-31"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-06-30"); // 本期
                }
            } else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第三季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-06-30");
                pageParam.put("thisTermMdate", thisYear + "-09-30");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", thisYear + "-03-31");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                } else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-09-30");
                }
            } else {
                pageParam.put("thisTermVdate", thisYear + "-09-30");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", thisYear + "-06-30");
                    pageParam.put("lastTermMdate", thisYear + "-09-30");
                } else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-09-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
            }
        } else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_半年报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_上半年.getValue())) {
                pageParam.put("thisTermVdate", lastYear + "-12-31");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                } else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", yearBeforeLast + "-12-31");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                }
            } else {
                pageParam.put("thisTermVdate", thisYear + "-06-30");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-12-31");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                } else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
            }
        } else {
            pageParam.put("thisTermVdate", lastYear + "-12-31");
            pageParam.put("thisTermMdate", thisYear + "-12-31");
            pageParam.put("lastTermVdate", yearBeforeLast + "-12-31");
            pageParam.put("lastTermMdate", lastYear + "-12-31");
        }
    }

    /**
     * 分析余额日期
     *
     * @param pageParam 查询条件
     * @return
     * @throws ParseException
     */
    public Date analyseDate(Map<String, Object> pageParam)
            throws ParseException {

        String reportTermType = (String) pageParam.get("reportTermType");
        String reportDate = (String) pageParam.get("valDate");
        String reportTerm = (String) pageParam.get("reportTerm");

        int year = 0;
        int month = -1;
        int day = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
        calendar.set(Calendar.MINUTE, 0);// 分
        calendar.set(Calendar.SECOND, 0);// 秒
        calendar.set(Calendar.MILLISECOND, 0);// 毫秒

        String[] strArr = reportDate.split(",")[0].split("-");
        if (DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)) {// 年底
            year = Integer.valueOf(strArr[0]);
            month += 12;
            day = 31;
        }
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.半年_上半年.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            } else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.季度_第一季度.getValue().equals(reportTerm)) {
                month += 3;
                day = 31;
            } else if (DictCodeListExt1.季度_第二季度.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            } else if (DictCodeListExt1.季度_第三季度.getValue().equals(reportTerm)) {
                month += 9;
                day = 30;
            } else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)) {// 月底：当前月的下个月的第0天
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]) + 1;
            day = 0;
        }
        if (DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]);
            day = Integer.valueOf(strArr[2]);
        }

        calendar.set(year, month, day);
        return calendar.getTime();

    }

    /**
     * 初始化数据
     *
     * @param dtoMap
     * @return
     * @see
     * @since 1.0
     */
    public void initData(Map<String, OwnerEquityChange001Dto> dtoMap) {
        OwnerEquityChange001Dto newDto = new OwnerEquityChange001Dto();
        newDto.setSortNum(1);
        newDto.setProject("一、期初所有者权益（产品净值）");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("一、期初所有者权益（产品净值）", newDto);

        newDto = new OwnerEquityChange001Dto();
        newDto.setSortNum(2);
        newDto.setProject("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("二、本期经营活动产生的产品净值变动数（本期利润及其他综合收益）", newDto);

        newDto = new OwnerEquityChange001Dto();
        newDto.setSortNum(3);
        newDto.setProject("三、本期产品份额交易产生的产品净值变动数");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("三、本期产品份额交易产生的产品净值变动数", newDto);

        newDto = new OwnerEquityChange001Dto();
        newDto.setSortNum(4);
        newDto.setProject("1.产品认申购");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("1.产品认申购", newDto);

        newDto = new OwnerEquityChange001Dto();
        newDto.setSortNum(5);
        newDto.setProject("2.产品赎回");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("2.产品赎回", newDto);

        newDto = new OwnerEquityChange001Dto();
        newDto.setSortNum(6);
        newDto.setProject("四、本期向产品份额持有人分配利润产生的产品净值变动");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        dtoMap.put("四、本期向产品份额持有人分配利润产生的产品净值变动", newDto);

        newDto = new OwnerEquityChange001Dto();
        newDto.setSortNum(7);
        newDto.setProject("五、期末所有者权益（产品净值）");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        newDto.settOtherCompIncome(BigDecimal.ZERO);
        newDto.setyOtherCompIncome(BigDecimal.ZERO);
        dtoMap.put("五、期末所有者权益（产品净值）", newDto);
    }
    
    /**
     * 获取默认日期信息
     * 
     * @param pageParam
     * @return OwnerEquityChange001Dto
     */
    public OwnerEquityChange001Dto getDateInfo(Map<String, Object> pageParam) {
        String booksetId = String.valueOf(pageParam.get("booksetId"));
        String reportTermType = String.valueOf(pageParam.get("reportTermType"));
        OwnerEquityChange001Dto datedto = new OwnerEquityChange001Dto();
        if(booksetId != "null" && booksetId != ""){
            datedto = ownerEquityChange001Mapper.getDateInfo1(pageParam);
        }else{
            List<OwnerEquityChange001Dto> dateList = ownerEquityChange001Mapper.getDateInfo2(pageParam);
            for(OwnerEquityChange001Dto item:dateList){
                if(datedto.getValDate() == null || datedto.getValDate() == ""){
                    datedto.setValDate(item.getValDate());
                }else{
                    if(datedto.getValDate().compareTo(item.getValDate()) > 0){
                        datedto.setValDate(item.getValDate());
                    }
                }
            }
        }
        if(DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)){
            datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), 0, 0, -1)));
//            datedto.setValDate(DateUtil.addDate(datedto.getValDate(), 0, 0, -1));
        }else if(DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)){
            datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), 0, -1, 0), "yyyy-MM"));
        }else if(DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)){
            int season = DateUtil.getSeason(DateUtil.parse(datedto.getValDate()));
            if(season == 1){
                datedto.setReportTerm(DictCodeListExt1.季度_第四季度.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), -1, 0, 0), "yyyy"));
            }else if(season == 2){
                datedto.setReportTerm(DictCodeListExt1.季度_第一季度.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.parse(datedto.getValDate()), "yyyy"));
            }else if(season == 3){
                datedto.setReportTerm(DictCodeListExt1.季度_第二季度.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.parse(datedto.getValDate()), "yyyy"));
            }else if(season == 4){
                datedto.setReportTerm(DictCodeListExt1.季度_第三季度.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.parse(datedto.getValDate()), "yyyy"));
            }
        }else if(DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)){
            int month = DateUtil.getMonth(datedto.getValDate());
            if(month<=6){
                datedto.setReportTerm(DictCodeListExt1.半年_下半年.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), -1, 0, 0), "yyyy"));
            }else{
                datedto.setReportTerm(DictCodeListExt1.半年_上半年.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.parse(datedto.getValDate()), "yyyy"));
            }
        }else if(DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)){
            datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), -1, 0, 0), "yyyy"));
        }
        return datedto;
    }

}
