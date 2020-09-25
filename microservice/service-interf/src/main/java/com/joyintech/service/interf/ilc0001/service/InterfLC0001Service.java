package com.joyintech.service.interf.ilc0001.service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.ifeerate.entity.FeeRateEntity;
import com.joyintech.service.interf.ifeerate.service.FeeRateService;
import com.joyintech.service.interf.workflownode.service.WorkFlowNodeService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillInEntity;
import com.joyintech.service.interf.ibillinfo.service.InterfBillInfoService;
import com.joyintech.service.interf.ilc0001.dao.InterfLC0001Mapper;
import com.joyintech.service.interf.ilc0001.entity.InterfLC0001InEntity;
import com.joyintech.service.interf.ilc0001.entity.InterfLC0001OutEntity;

import java.math.BigDecimal;

/**
 * 接口LC0001查询成立单据信息 详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see InterfLC0001Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LC0001)
public class InterfLC0001Service extends ReceivedEsbService<InterfLC0001OutEntity, InterfLC0001InEntity> {

    /**
     * 接口LC0001查询Mapper
     */
    @Autowired
    private InterfLC0001Mapper iLC0001Mapper;

    /**
     * 单据信息处理详细
     */
    @Autowired
    private InterfBillInfoService billService;

    /**
     * 查询流程节点处理人——详细处理
     */
    @Autowired
    private WorkFlowNodeService workFlowNodeService;

    /**
     * 查询费率
     */
    @Autowired
    private FeeRateService feeService;

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfLC0001InEntity entity)
        throws Exception {
        // 查询类接口，无登录操作
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfLC0001InEntity entity)
        throws Exception {
        // 查询类接口，无登录操作
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfLC0001OutEntity selectResponseData(InterfLC0001InEntity entity)
        throws Exception {
        // 数据字典转换
        billService.changeSaleDeptCode(entity);
        // 判断产品信息是否存在
        InterfBillInEntity finProdInfo = billService.selectFinProdInfo(entity);
        if (null == finProdInfo) {
            doException(new Exception(), entity.getProdId() + "产品信息不存在！");
            return null;
        }
        // 金融产品代码
        String finProdId = finProdInfo.getFinProdId();
        entity.setFinProdId(finProdId);
        // 平安业务类型
        entity.setPabussType(DictCodeListExt1.平安业务类型_产品成立.getValue());
        // 是否分层
        entity.setIsLay(finProdInfo.getIsLay());
        // 处理模式
        String type = finProdInfo.getDealType();
        String errMsg = entity.getProdId() + "在日期（" + finProdInfo.getVdate() + "）产品未成立单据！";
        // 非首次成立时
        if (StringUtil.isEmpty(finProdInfo.getVdate())) {
            // 普通,日添利,智能日添利,货币型,净值型时未成立单据
            if (StringUtil.equals(type, DictCodeList.处理模式_普通.getValue())
                || StringUtil.equals(type, BussinessDictCodeList.处理模式_日添利.getValue())
                || StringUtil.equals(type, BussinessDictCodeList.处理模式_智能日添利.getValue())
                || StringUtil.equals(type, BussinessDictCodeList.处理模式_货币型.getValue())
                || StringUtil.equals(type, BussinessDictCodeList.处理模式_净值型.getValue())) {
                doException(new Exception(), errMsg);
                return null;
            }
            // 取得起息日
            String vdate = billService.selectVdate(entity);
            entity.setVdate(vdate);
            entity.setTradePS(DictCodeList.交易方向_产品申购.getValue());
        }
        // 首次成立时
        else {
            entity.setVdate(finProdInfo.getVdate());
            entity.setTradePS(DictCodeList.交易方向_产品认购.getValue());
        }
        // 判断成立单据是否存在,查询汇总编号
        String totleTradeId = billService.selectTotleTradeId(entity);
        if (StringUtil.isEmpty(totleTradeId)) {
            doException(new Exception(), errMsg);
            return null;
        }

        entity.setTotleTradeId(totleTradeId);

        // 获取产品信息
        InterfLC0001OutEntity outEntity = iLC0001Mapper.selectProdInfo(entity);
        if (!StringUtil.isNotEmpty(outEntity)) {
            doException(new Exception(), errMsg);
            return null;
        }
        // 获取分层金额和利率
        billService.selectLayAmt(entity, outEntity);
        // 检索分行机构名称，查询base库
        String branchName = billService.selectBranchgName(outEntity.getAcctBranchId());
        outEntity.setBranchName(branchName);
        // 设置费率
        outEntity.setFinProdId(finProdId);
        outEntity.setEnddate(entity.getEnddate());
        billService.setFeeRate(outEntity);
        // 设置名称
        billService.setCodeName(outEntity);
        // 设置流程处理人
        billService.setWkfUser(outEntity, DictCodeList.共通业务类型_收益型产品认申购管理);
        ProcessMark mark = new ProcessMark();
        // 产品成立确人画面
        mark.setPageId("FinProductEstablish002");
        // pk1 金融产品代码
        mark.setBusinessDataPk1(finProdId);
        // pk2 交易类型
        mark.setBusinessDataPk2(DictCodeList.共通业务类型_收益型产品认申购管理.getValue());
        // PK3 产品成立日
        mark.setBusinessDataPk3(
            DateUtil.format(outEntity.getValueDate(), DateUtil.DEFAULT_DATE_FORMAT_EN));

        BeanUtil.copyPropertiesIgnoreNull(outEntity, workFlowNodeService.selectWorkFlowNode(mark));
        return outEntity;
    }

    /**
     * 从数据字典缓存中取各个代码对应的名称
     * 
     * @param outEntity lc0001输出
     * @see
     * @since 1.0
     */
    private void setCodeName(InterfLC0001OutEntity outEntity) {
        // 从数据字典缓存中取各个代码对应的名称,分别是销售部门,币种,排期发行方式,销售部门
        if (StringUtil.isNotEmpty(outEntity.getSaleDept())) {
            outEntity.setDeptName(DictCodeList.from(DictCode.销售部门,
                outEntity.getSaleDept()).getCodeName());
        }
        if (StringUtil.isNotEmpty(outEntity.getSubscriptionCcy())) {
            outEntity.setSubscriptionCcyName(DictCodeList.from(DictCode.币种,
                outEntity.getSubscriptionCcy()).getCodeName());
        }
        if (StringUtil.isNotEmpty(outEntity.getIssueMode())) {
            outEntity.setIssueModeName(DictCodeList.from(DictCode.排期发行方式,
                outEntity.getIssueMode()).getCodeName());
        }
    }

    /**
     * 取得并设置费率
     * 
     * @param finProdId 金融产品代码
     * @param outEntity lc0001输出
     * @see
     * @since 1.0
     */
    private void setFeeRate(String finProdId, InterfLC0001OutEntity outEntity) {
        // 3.获取费用信息：销售费率、管理费率、托管费率、其他费率
        // 金融产品类型F16-理财产品，现金流一级类型03-费用，现金流二级类型C06-销售费
        FeeRateEntity feeRateEntity = new FeeRateEntity();
        feeRateEntity.setFinprodId(finProdId);
        feeRateEntity.setSysDate(getSysDate());
        feeRateEntity.setProdType(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        feeRateEntity.setCdate(outEntity.getEnddate());
        feeRateEntity.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
        feeRateEntity.setCashType2(DictCodeListExt1.现金流二级类型_销售费.getValue());
        BigDecimal saleFeeRate = feeService.selectFeeRate(feeRateEntity);

        feeRateEntity.setCashType2(DictCodeListExt1.现金流二级类型_托管费.getValue());
        BigDecimal consignFeerate = feeService.selectFeeRate(feeRateEntity);

        feeRateEntity.setCashType2(DictCodeListExt1.现金流二级类型_通道其他费.getValue());
        BigDecimal otherFeerate = feeService.selectFeeRate(feeRateEntity);

        outEntity.setSaleFeeRate(saleFeeRate);
        outEntity.setConsignFeeRate(consignFeerate);
        outEntity.setOtherFeeRate(otherFeerate);
    }

    /**
     * 销售部门数据字典转换
     * 
     * @param entity 接口输入
     */
    private void changeSaleDeptCode(InterfLC0001InEntity entity) {
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
}
