/*
 * 文件名：InterfBillInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ibillinfo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.ibillinfo.dao.InterfBillInfoMapper;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillInEntity;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillOutEntity;
import com.joyintech.service.interf.ifeerate.service.FeeRateService;
import com.joyintech.service.interf.workflownode.service.WorkFlowNodeService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 单据信息处理详细-lc0001成立单据；lc0002到期单据
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月21日
 * @see InterfBillInfoService
 * @since 1.0
 */
@Service
public class InterfBillInfoService {

    /**
     * 单据信息处理查询
     */
    @Autowired
    private InterfBillInfoMapper mapper;

    /**
     * 查询费率
     */
    @Autowired
    private FeeRateService feeService;

    /**
     * 查询流程节点处理人——详细处理
     */
    @Autowired
    private WorkFlowNodeService workFlowNodeService;

    /**
     * 根据市场代码和销售部门查询金融产品代码
     * 
     * @param entity 单据信息输出入
     * @return 金融产品代码
     * @see
     * @since 1.0
     */
    public InterfBillInEntity selectFinProdInfo(InterfBillInEntity entity) {
        entity.setProdType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // 根据市场代码获取金融产品代码
        return mapper.selectFinProdInfo(entity);
    }

    /**
     * 从开放计划表查询起息日
     * 
     * @param entity 查询条件
     * @return 起息日
     */
    public String selectVdate(InterfBillInEntity entity) {
        return mapper.selectVdate(entity);
    }

    /**
     * 查询汇总编号
     * 
     * @param entity 单据信息输出入
     * @return 汇总编号
     * @see
     * @since 1.0
     */
    public String selectTotleTradeId(InterfBillInEntity entity) {
        return mapper.selectTotleTradeId(entity);
    }

    /**
     * 查询分层金额和利率，逗号拼接分层信息
     * 
     * @param entity 单据信息输入
     * @param outEntity 单据信息输出
     * @return 单据信息
     */
    public void selectLayAmt(InterfBillInEntity entity, InterfBillOutEntity outEntity) {
        // 查询交易金额和收益率
        List<InterfBillOutEntity> yieldList = null;
        if (StringUtil.equals(entity.getPabussType(),
            DictCodeListExt1.平安业务类型_产品成立.getValue())) {
            yieldList = mapper.selectAmtYield(entity);
        }
        else {
            // 分层收益
            List<InterfBillOutEntity> yList = selectLayYield(entity.getTotleTradeId(),
                entity.getTradePS2());
            // 分层本金
            yieldList = selectLayYield(entity.getTotleTradeId(), entity.getTradePS());
            mergeList(yieldList, yList);
        }
        if (StringUtil.isEmptyList(yieldList)) {
            return;
        }
        if (DictCodeList.是否_是.getValue().equals(entity.getIsLay())) {
            setLayYieldAmt(yieldList, outEntity);
        }
        else {
            outEntity.setAmt(yieldList.get(0).getAmt());
            outEntity.setYield(yieldList.get(0).getYield());
            // 如果不分层，分层金额又是必输，所以分层金额赋值本金金额
            outEntity.setAmt1(StringUtil.numberToString(outEntity.getAmt()));
        }
        // 计息基础
        outEntity.setInterestMode(yieldList.get(0).getInterestMode());
    }

    /**
     * 合并数据 paramList合并到mainList
     * 
     * @param mainList 主要数据
     * @param paramList 被合并的数据
     */
    private void mergeList(List<InterfBillOutEntity> mainList,
                           List<InterfBillOutEntity> paramList) {
        if (StringUtil.isEmptyList(mainList) || StringUtil.isEmptyList(paramList)) {
            return;
        }
        // 若金融产品代码相同则为同一个分层数据
        for (InterfBillOutEntity main : mainList) {
            String finProdId = main.getFinProdId();
            for (InterfBillOutEntity param : paramList) {
                if (StringUtil.equals(finProdId, param.getFinProdId())) {
                    BigDecimal amt = param.getAmt();
                    main.setLevelactintamt(StringUtil.numberToString(amt));
                    main.setYield(param.getYield());
                    main.setLevelprinintamt(StringUtil.numberToString(main.getAmt().add(amt)));
                }
            }
        }
    }

    /**
     * 查询分层数据
     * 
     * @param totalTradId 汇总交易编码
     * @param ps 交易方向
     * @return 分层数据
     */
    private List<InterfBillOutEntity> selectLayYield(String totalTradId, String ps) {
        InterfBillInEntity entity = new InterfBillInEntity();
        entity.setTotleTradeId(totalTradId);
        entity.setTradePS(ps);
        // 查询交易金额和收益率
        return mapper.selectAmtYield(entity);
    }

    /**
     * 逗号拼接分层信息
     * 
     * @param prodList 单据信息输出List
     * @param pBillProdEntity 单据信息输出
     */
    private void setLayYieldAmt(List<InterfBillOutEntity> prodList,
                                InterfBillOutEntity pBillProdEntity) {
        // 分层收益率
        List<String> yieldList = new ArrayList<>();
        // 分层认购金额
        List<String> layAmtList = new ArrayList<>();
        // 分层收益
        List<String> layIncomeList = new ArrayList<>();
        // 分层本息合计
        List<String> layAmtYldList = new ArrayList<>();
        // sum（金融产品交易拓展.应付利率*金融产品交易.交易金额）
        BigDecimal allYield = BigDecimal.ZERO;
        // sum(金融产品交易.交易金额)
        BigDecimal allAmt = BigDecimal.ZERO;
        for (InterfBillOutEntity entity : prodList) {
            yieldList.add(StringUtil.numberToString(entity.getYield()));
            layAmtList.add(StringUtil.numberToString(entity.getAmt()));
            layIncomeList.add(entity.getLevelactintamt());
            layAmtYldList.add(entity.getLevelprinintamt());
            allYield = allYield.add(entity.getYield().multiply(entity.getAmt()));
            allAmt = allAmt.add(entity.getAmt());
        }
        // sum（金融产品交易拓展.应付利率*金融产品交易.交易金额）/sum(金融产品交易.交易金额)
        pBillProdEntity.setYield(allYield.divide(allAmt));
        pBillProdEntity.setBedYieldChar(StringUtil.joinString(yieldList, GlobalConstant.COMMA));
        pBillProdEntity.setAmt1(StringUtil.joinString(layAmtList, GlobalConstant.COMMA));
        pBillProdEntity.setLevelactintamt(
            StringUtil.joinString(layIncomeList, GlobalConstant.COMMA));
        pBillProdEntity.setLevelprinintamt(
            StringUtil.joinString(layAmtYldList, GlobalConstant.COMMA));
    }

    /**
     * 取得并设置费率
     * 
     * @param outEntity lc0002输出
     * @see
     * @since 1.0
     */
    public void setFeeRate(InterfBillOutEntity outEntity) {
        // 3.获取费用信息：销售费率、管理费率、托管费率、其他费率
        // 销售费
        BigDecimal saleFeeRate = feeService.selectFeeRate(outEntity.getFinProdId(),
            DictCodeListExt1.现金流二级类型_销售费, outEntity.getEnddate());

        // 托管费
        BigDecimal consignFeerate = feeService.selectFeeRate(outEntity.getFinProdId(),
            DictCodeListExt1.现金流二级类型_托管费, outEntity.getEnddate());

        // 固定管理费
        BigDecimal managementFee = feeService.selectFeeRate(outEntity.getFinProdId(),
            DictCodeListExt1.现金流二级类型_固定管理费, outEntity.getEnddate());

        // 其他
        BigDecimal otherFeerate = feeService.selectFeeRate(outEntity.getFinProdId(),
            DictCodeListExt1.现金流二级类型_浮动管理费, outEntity.getEnddate());

        outEntity.setSaleFeeRate(saleFeeRate);
        outEntity.setConsignFeeRate(consignFeerate);
        outEntity.setAssetsManagementFeeRate(managementFee);
        outEntity.setOtherFeeRate(otherFeerate);

        // 计算费用金额
        outEntity.setPaysamt(outEntity.getCorpus() == null
                             || saleFeeRate == null ? null : outEntity.getCorpus().multiply(
                                 saleFeeRate));
        outEntity.setPayhostamt(outEntity.getCorpus() == null
                                || consignFeerate == null ? null : outEntity.getCorpus().multiply(
                                    consignFeerate));
    }

    /**
     * 从数据字典缓存中取各个代码对应的名称
     * 
     * @param outEntity lc0001输出
     */
    public void setCodeName(InterfBillOutEntity outEntity) {
        // 销售部门
        if (StringUtil.isNotEmpty(outEntity.getSaleDept())) {
            String deptName = DictCodeList.from(DictCode.销售部门,
                outEntity.getSaleDept()).getCodeName();
            outEntity.setDeptName(deptName);
            outEntity.setBelongDeptName(deptName);
        }
        // 币种
        if (StringUtil.isNotEmpty(outEntity.getSubscriptionCcy())) {
            outEntity.setSubscriptionCcyName(DictCodeList.from(DictCode.币种,
                outEntity.getSubscriptionCcy()).getCodeName());
        }
        // 排期发行方式
        if (StringUtil.isNotEmpty(outEntity.getIssueMode())) {
            outEntity.setIssueModeName(DictCodeList.from(DictCode.排期发行方式,
                outEntity.getIssueMode()).getCodeName());
        }
    }

    /**
     * 销售部门数据字典转换
     * 
     * @param entity 接口输入
     */
    public void changeSaleDeptCode(InterfBillInEntity entity) {
        String dict = entity.getSaleDept();
        // 零售
        if (StringUtil.equals(dict, InterfDictCodeList.销售部门_零售产品管理部.getValue())) {
            entity.setSaleDept(DictCodeList.销售部门_零售产品管理部.getValue());
        }
        // 同业
        else if (StringUtil.equals(dict, InterfDictCodeList.销售部门_金融同业部.getValue())) {
            entity.setSaleDept(DictCodeList.销售部门_金融同业部.getValue());
        }
        // 对公
        else if (StringUtil.equals(dict, InterfDictCodeList.销售部门_对公产品和现金管理部.getValue())) {
            entity.setSaleDept(DictCodeList.销售部门_对公产品.getValue());
        }
    }

    /**
     * 设置流程处理人
     * 
     * @param outEntity 对象数据
     * @param busiType 业务共同类型
     * @throws Exception 异常
     */
    public void setWkfUser(InterfBillOutEntity outEntity, DictCodeList busiType)
        throws Exception {
        ProcessMark mark = new ProcessMark();
        String pageId = null;
        String cdate = null;
        if (busiType == DictCodeList.共通业务类型_收益型产品认申购管理) {
            pageId = "FinProductEstablish002";
            cdate = DateUtil.format(outEntity.getValueDate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
        }
        else {
            pageId = "FinProductExpire002";
            cdate = DateUtil.format(outEntity.getExpiryDate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
        }
        // 画面ID
        mark.setPageId(pageId);
        // pk1 金融产品代码
        mark.setBusinessDataPk1(outEntity.getFinProdId());
        // pk2 交易类型
        mark.setBusinessDataPk2(busiType.getValue());
        // pk3 成立日/到期日
        mark.setBusinessDataPk3(cdate);

        BeanUtil.copyPropertiesIgnoreNull(outEntity, workFlowNodeService.selectWorkFlowNode(mark));
    }

    /**
     * 查询机构名称
     * 
     * @param acctBranchId 机构代码
     * @return 机构名称
     * @see
     * @since 1.0
     */
    public String selectBranchgName(String acctBranchId) {
        return mapper.selectBranchgName(acctBranchId);
    }
}
