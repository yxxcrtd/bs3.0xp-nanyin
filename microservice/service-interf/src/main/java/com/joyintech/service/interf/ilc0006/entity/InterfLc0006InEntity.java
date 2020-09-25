/*
 * 文件名：InterfLc0006Inentity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0006.entity;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.InterfCreditItemInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * Lc0006信贷指令推送给理财系统接口输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月10日
 * @see InterfLc0006InEntity
 * @since 1.0
 */
public class InterfLc0006InEntity extends InterfCreditItemInfo {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 6005727307098607107L;

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PUTOUT_ORDER_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setCashOutNo(String cashOutNo) {
        super.setCashOutNo(cashOutNo);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REL_CONTRACT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setRelContractNo(String relContractNo) {
        super.setRelContractNo(relContractNo);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_GOV")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setIsGov(String isGov) {
        super.setIsGov(isGov);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_HIGH_POLLUTION")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setIsHighPollution(String isHighPollution) {
        super.setIsHighPollution(isHighPollution);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_GOVT_LIMIT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setIsGovtLimit(String isGovtLimit) {
        super.setIsGovtLimit(isGovtLimit);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_GOVT_NEW_STRATEGY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setIsGovtNewStrategy(String isGovtNewStrategy) {
        super.setIsGovtNewStrategy(isGovtNewStrategy);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "FUND_USE_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setFundUseType(String fundUseType) {
        super.setFundUseType(fundUseType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REPAYMENT_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setRepaymentType(String repaymentType) {
        super.setRepaymentType(repaymentType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BUSI_CONTRACT_PROD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setBusiContractProd(String busiContractProd) {
        super.setBusiContractProd(busiContractProd);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_LOW_RISK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setIsLowRisk(String isLowRisk) {
        super.setIsLowRisk(isLowRisk);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "APPROVE_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setApproveNo(String approveNo) {
        super.setApproveNo(approveNo);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CREDIT_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setCreditType(String creditType) {
        super.setCreditType(creditType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "APROVE_AMOUNT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 26, scale = 4)
    @Override
    public void setAproveAmount(BigDecimal aproveAmount) {
        super.setAproveAmount(aproveAmount);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "APROVE_LIMIT")
    @EsbFieldType(value = EsbFieldType.INT)
    @EsbNumber(length = 6)
    @Override
    public void setAproveLimit(Integer aproveLimit) {
        super.setAproveLimit(aproveLimit);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_APROVE_INS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setLastAproveIns(String lastAproveIns) {
        super.setLastAproveIns(lastAproveIns);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "USED_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 26, scale = 4)
    @Override
    public void setUsedAmount(BigDecimal usedAmount) {
        super.setUsedAmount(usedAmount);
    }

    /**
     * 设置 签发日期
     * 
     * @param signDate 签发日期
     * @see
     * @since 1.0
     */
    @EsbName(value = "SIGN_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public void setSignDateEsb(String signDate) {
        super.setSignDate(DateUtil.parse(signDate));
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "RISK_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setRiskType(String riskType) {
        super.setRiskType(riskType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ASSURE_PATTERN")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public void setAssurePattern(String assurePattern) {
        super.setAssurePattern(assurePattern);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ASSURE_MARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1000)
    @Override
    public void setAssureMark(String assureMark) {
        super.setAssureMark(assureMark);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CORE_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setBranchInsNo(String branchInsNo) {
        super.setBranchInsNo(branchInsNo);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BECIF_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setBecif(String becif) {
        super.setBecif(becif);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CLIENT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setCreditCustomerNo(String creditCustomerNo) {
        super.setCreditCustomerNo(creditCustomerNo);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CLIENT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    @Override
    public void setCreditCustomerName(String creditCustomerName) {
        super.setCreditCustomerName(creditCustomerName);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BUSS_VARIETY_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setCreditBusinessNo(String creditBusinessNo) {
        super.setCreditBusinessNo(creditBusinessNo);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BUSS_VARIETY_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    @Override
    public void setCreditBusinessName(String creditBusinessName) {
        super.setCreditBusinessName(creditBusinessName);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BUSS_CONTRACT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public void setBusiContractNo(String busiContractNo) {
        super.setBusiContractNo(busiContractNo);
    }

    /**
     * 设置 起息日
     * 
     * @param vdate 起息日
     * @see
     * @since 1.0
     */
    @EsbName(value = "VALUE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public void setVdateEsb(String vdate) {
        super.setVdate(DateUtil.parse(vdate));
    }

    /**
     * 设置 到期日
     * 
     * @param mdate 到期日
     * @see
     * @since 1.0
     */
    @EsbName(value = "EXPIRY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public void setMdateEsb(String mdate) {
        super.setMdate(DateUtil.parse(mdate));
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "DRAW_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 30, scale = 14)
    @Override
    public void setYield(BigDecimal yield) {
        super.setYield(yield);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PUTOUT_ORDER_TOTAL_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 26, scale = 4)
    @Override
    public void setPaymentAmount(BigDecimal paymentAmount) {
        super.setPaymentAmount(paymentAmount);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Override
    public void setCcy(String ccy) {
        super.setCcy(ccy);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REMAIN_AVAIL_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 26, scale = 4)
    @Override
    public void setRemainAvailAmt(BigDecimal paymentAmount) {
        super.setRemainAvailAmt(paymentAmount);
    }
}
