/*
 * 文件名：InterfCusdep1020InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icusdep1020.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.tams.common.base.entity.StlDvpOrders;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 接口CUSDEP1020-输入
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月22日
 * @see InterfCusdep1020InEntity
 * @since 1.0
 */
public class InterfCusdep1020InEntity {

    /**
     * 付款方账户信息
     */
    private PayAcctInfo payerInfo;

    /**
     * 收款方账户信息
     */
    private PayAcctInfo payeeInfo;

    /**
     * DVP划款信息
     */
    private StlDvpOrders paDvpPay;

    /**
     * 是否DVP划款
     */
    private boolean isDVP;

    /**
     * 划款信息
     */
    private StlPayOrders paPay;

    /**
     * 转账类型
     */
    private String transferType;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 转账系统标志
     */
    private String transferSysFlag;

    /**
     * 缴费单号
     */
    private String dedcutNo;

    /**
     * 附件标志
     */
    private String affixFlag = InterfDictCodeList.附件标志_没有.getValue();

    /**
     * 附件名称
     */
    private String affixName;

    /**
     * 备注1
     */
    private String remarkOne;

    /**
     * 备注2
     */
    private String remarkTwo;

    /**
     * 备注3
     */
    private String remarkThree;

    /**
     * 备注4
     */
    private String remarkFour;

    /**
     * 备注5
     */
    private String remarkFive;

    /**
     * 取得 发起方流水号
     * 
     * @return 发起方流水号
     */
    @EsbName(value = "INITIATOR_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    @Required
    public String getOrderId() {
        if (isDVP) {
            return paDvpPay == null ? StringUtil.EMPTY : paDvpPay.getOrderId();
        }
        else {
            return paPay == null ? StringUtil.EMPTY : paPay.getOrderId();
        }
    }

    /**
     * 取得 转账方式
     * 
     * @return 转账方式
     */
    @EsbName(value = "TRANSFER_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    public String getTransferMode() {
        // 当付款方收款方都是行内账户时，行内转账，否则为行外转账
        if (DictCodeListExt1.资产来源_行内.getValue().equals(payerInfo.getAcctInfo().getInOutBank())
            && DictCodeListExt1.资产来源_行内.getValue().equals(
                payeeInfo.getAcctInfo().getInOutBank())) {
            return InterfDictCodeList.转账方式_行内转账.getValue();
        }
        return InterfDictCodeList.转账方式_行外转账.getValue();
    }

    /**
     * 取得 付款方资金结算账号
     * 
     * @return 付款方资金结算账号
     */
    @EsbName(value = "PAYER_FUND_SETTLE_ACCT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getPayerBusiAcctNo() {
        if (payerInfo == null) {
            return StringUtil.EMPTY;
        }
        return payerInfo.getAcctInfo().getBusiAcctNo();
    }

    /**
     * 取得 付款方资金结算账号户名
     * 
     * @return 付款方资金结算账号户名
     */
    @EsbName(value = "PAYER_FUND_SETTLE_ACCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    public String getPayerAcctName() {
        if (payerInfo == null || payerInfo.getAcctInfo() == null) {
            return StringUtil.EMPTY;
        }
        return payerInfo.getAcctInfo().getAcctName();
    }

    /**
     * 取得 收款方资金结算账号
     * 
     * @return 收款方资金结算账号
     */
    @EsbName(value = "PAYEE_FUND_SETTLE_ACCT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getPayeeBusiAcctNo() {
        if (payeeInfo == null || payeeInfo.getAcctInfo() == null) {
            return StringUtil.EMPTY;
        }
        return payeeInfo.getAcctInfo().getBusiAcctNo();
    }

    /**
     * 取得 收款方资金结算账号户名
     * 
     * @return 收款方资金结算账号户名
     */
    @EsbName(value = "PAYEE_FUND_SETTLE_ACCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    public String getPayeeAcctName() {
        if (payeeInfo == null || payeeInfo.getAcctInfo() == null) {
            return StringUtil.EMPTY;
        }
        return payeeInfo.getAcctInfo().getAcctName();
    }

    /**
     * 取得 付款方账号开户行行号
     * 
     * @return 付款方账号开户行行号
     */
    @EsbName(value = "PAYER_ACCT_OPEN_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getPayerAcctOpBranch() {
        if (payerInfo == null || payerInfo.getAcctInfo() == null) {
            return StringUtil.EMPTY;
        }
        return DictCodeListExt1.资产来源_行内.getValue().equals(
            payerInfo.getAcctInfo().getInOutBank()) ? payerInfo.getAcctInfo().getLargePayno() : payerInfo.getAcctInfo().getCorrNo();
    }

    /**
     * 取得 收款方账户开户行行号
     * 
     * @return 收款方账户开户行行号
     */
    @EsbName(value = "PAYEE_ACCT_OPEN_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getPayeeAcctOpBranch() {
        if (payeeInfo == null || payeeInfo.getAcctInfo() == null) {
            return StringUtil.EMPTY;
        }
        return DictCodeListExt1.资产来源_行内.getValue().equals(
            payeeInfo.getAcctInfo().getInOutBank()) ? payeeInfo.getAcctInfo().getLargePayno() : payeeInfo.getAcctInfo().getCorrNo();
    }

    /**
     * 取得 付款金额
     * 
     * @return 付款金额
     */
    @EsbName(value = "PAYMENT_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public BigDecimal getSettleAmt() {
        if (isDVP) {
            return paDvpPay == null ? null : paDvpPay.getSettleAmt();
        }
        else {
            return paPay == null ? null : paPay.getSettleAmt();
        }
    }

    /**
     * 取得 转账类型
     * 
     * @return 转账类型
     */
    @EsbName(value = "TRANSFER_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    public String getTransferType() {
        if (!StringUtil.isEmpty(transferType)) {
            return transferType;
        }
        String payerType = payerInfo.getAcctType();
        String payeeType = payeeInfo.getAcctType();
        // 托管户-》上清所
        if (DictCodeListExt1.资金账户类型_产品托管户.getValue().equals(payerType)
            && DictCodeListExt1.资金账户类型_上清所DVP账户.getValue().equals(payeeType)) {
            transferType = InterfDictCodeList.转账类型_托管户转上清所.getValue();
        }
        // 托管户-》中债登
        else if (DictCodeListExt1.资金账户类型_产品托管户.getValue().equals(payerType)
                 && DictCodeListExt1.资金账户类型_中债登DVP账户.getValue().equals(payeeType)) {
            transferType = InterfDictCodeList.转账类型_托管户转中债登.getValue();
        }
        // 上清所-》托管账户
        else if (DictCodeListExt1.资金账户类型_上清所DVP账户.getValue().equals(payerType)
                 && DictCodeListExt1.资金账户类型_产品托管户.getValue().equals(payeeType)) {
            transferType = InterfDictCodeList.转账类型_上清所转托管户.getValue();
        }
        // 上清所-》中债登
        else if (DictCodeListExt1.资金账户类型_上清所DVP账户.getValue().equals(payerType)
                 && DictCodeListExt1.资金账户类型_中债登DVP账户.getValue().equals(payeeType)) {
            transferType = InterfDictCodeList.转账类型_上清所转中债登.getValue();
        }
        // 中债登-》托管账户
        else if (DictCodeListExt1.资金账户类型_中债登DVP账户.getValue().equals(payerType)
                 && DictCodeListExt1.资金账户类型_产品托管户.getValue().equals(payeeType)) {
            transferType = InterfDictCodeList.转账类型_中债登转托管户.getValue();
        }
        // 中债登-》上清所
        else if (DictCodeListExt1.资金账户类型_中债登DVP账户.getValue().equals(payerType)
                 && DictCodeListExt1.资金账户类型_上清所DVP账户.getValue().equals(payeeType)) {
            transferType = InterfDictCodeList.转账类型_中债登转上清所.getValue();
        }
        return transferType;
    }

    /**
     * 取得 用途
     * 
     * @return 用途
     */
    @EsbName(value = "USAGE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    @Required
    public String getUsage() {
        if (isDVP) {
            return paDvpPay == null ? StringUtil.EMPTY : StringUtil.substring(
                paDvpPay.getPurpose(), 0, 200);
        }
        else {
            return paPay == null ? StringUtil.EMPTY : StringUtil.substring(paPay.getPurpose(), 0,
                200);
        }
    }

    /**
     * 取得 备注
     * 
     * @return 备注
     */
    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemark() {
        if (isDVP) {
            return paDvpPay == null ? StringUtil.EMPTY : StringUtil.substring(paDvpPay.getRemark(),
                0, 300);
        }
        else {
            return paPay == null ? StringUtil.EMPTY : StringUtil.substring(paPay.getRemark(), 0,
                300);
        }
    }

    /**
     * 取得 手机号码
     * 
     * @return 手机号码
     */
    @EsbName(value = "MOBILE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    public String getMobile() {
        return mobile;
    }

    /**
     * 取得 转账系统标志
     * 
     * @return 转账系统标志
     */
    @EsbName(value = "TRANSFER_SYS_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    public String getTransferSysFlag() {
        return transferSysFlag;
    }

    /**
     * 取得 缴费单号
     * 
     * @return 缴费单号
     */
    @EsbName(value = "DEDCUT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getDedcutNo() {
        return dedcutNo;
    }

    /**
     * 取得 期望支付日期
     * 
     * @return 期望支付日期
     */
    @EsbName(value = "EXPECT_PAY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getSettleDate() {
        if (isDVP) {
            return paDvpPay == null ? StringUtil.EMPTY : DateUtil.format(paDvpPay.getSettleDate(),
                DateUtil.DEFAULT_OTHER_FORMAT);
        }
        else {
            return paPay == null ? StringUtil.EMPTY : DateUtil.format(paPay.getSettleDate(),
                DateUtil.DEFAULT_OTHER_FORMAT);
        }
    }

    /**
     * 取得 附件标志
     * 
     * @return 附件标志
     */
    @EsbName(value = "AFFIX_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getAffixFlag() {
        return affixFlag;
    }

    /**
     * 取得 附件名称
     * 
     * @return 附件名称
     */
    @EsbName(value = "AFFIX_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    public String getAffixName() {
        return affixName;
    }

    /**
     * 取得 备注1
     * 
     * @return 备注1
     */
    @EsbName(value = "REMARK_ONE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemarkOne() {
        return remarkOne;
    }

    /**
     * 取得 备注2
     * 
     * @return 备注2
     */
    @EsbName(value = "REMARK_TWO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemarkTwo() {
        return remarkTwo;
    }

    /**
     * 取得 备注3
     * 
     * @return 备注3
     */
    @EsbName(value = "REMARK_THREE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemarkThree() {
        return remarkThree;
    }

    /**
     * 取得 备注4
     * 
     * @return 备注4
     */
    @EsbName(value = "REMARK_FOUR")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemarkFour() {
        return remarkFour;
    }

    /**
     * 取得 备注5
     * 
     * @return 备注5
     */
    @EsbName(value = "REMARK_FIVE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemarkFive() {
        return remarkFive;
    }

    /**
     * 设置 转账类型
     * 
     * @param transferType 转账类型
     */
    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    /**
     * 设置 手机号码
     * 
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 设置 转账系统标志
     * 
     * @param transferSysFlag 转账系统标志
     */
    public void setTransferSysFlag(String transferSysFlag) {
        this.transferSysFlag = transferSysFlag;
    }

    /**
     * 设置 缴费单号
     * 
     * @param dedcutNo 缴费单号
     */
    public void setDedcutNo(String dedcutNo) {
        this.dedcutNo = dedcutNo;
    }

    /**
     * 设置 附件标志
     * 
     * @param affixFlag 附件标志
     */
    public void setAffixFlag(String affixFlag) {
        this.affixFlag = affixFlag;
    }

    /**
     * 设置 附件名称
     * 
     * @param affixName 附件名称
     */
    public void setAffixName(String affixName) {
        this.affixName = affixName;
    }

    /**
     * 设置 备注1
     * 
     * @param remarkOne 备注1
     */
    public void setRemarkOne(String remarkOne) {
        this.remarkOne = remarkOne;
    }

    /**
     * 设置 备注2
     * 
     * @param remarkTwo 备注2
     */
    public void setRemarkTwo(String remarkTwo) {
        this.remarkTwo = remarkTwo;
    }

    /**
     * 设置 备注3
     * 
     * @param remarkThree 备注3
     */
    public void setRemarkThree(String remarkThree) {
        this.remarkThree = remarkThree;
    }

    /**
     * 设置 备注4
     * 
     * @param remarkFour 备注4
     */
    public void setRemarkFour(String remarkFour) {
        this.remarkFour = remarkFour;
    }

    /**
     * 设置 备注5
     * 
     * @param remarkFive 备注5
     */
    public void setRemarkFive(String remarkFive) {
        this.remarkFive = remarkFive;
    }

    /**
     * 获取 付款方账户信息
     * 
     * @return 付款方账户信息.
     */
    public PayAcctInfo getPayerInfo() {
        return payerInfo;
    }

    /**
     * 设置 付款方账户信息
     * 
     * @param payInfo 付款方账户信息
     */
    public void setPayerInfo(PayAcctInfo payInfo) {
        this.payerInfo = payInfo;
    }

    /**
     * 获取 收款方账户信息
     * 
     * @return 收款方账户信息.
     */
    public PayAcctInfo getPayeeInfo() {
        return payeeInfo;
    }

    /**
     * 设置 收款方账户信息
     * 
     * @param collectInfo 收款方账户信息
     */
    public void setPayeeInfo(PayAcctInfo collectInfo) {
        this.payeeInfo = collectInfo;
    }

    /**
     * 获取 DVP划款信息
     * 
     * @return DVP划款信息.
     */
    public StlDvpOrders getPaDvpPay() {
        return paDvpPay;
    }

    /**
     * 设置 DVP划款信息
     * 
     * @param paDvpPay DVP划款信息
     */
    public void setPaDvpPay(StlDvpOrders paDvpPay) {
        this.paDvpPay = paDvpPay;
    }

    /**
     * 获取 划款信息
     * 
     * @return 划款信息.
     */
    public StlPayOrders getPaPay() {
        return paPay;
    }

    /**
     * 设置 划款信息
     * 
     * @param paPay 划款信息
     */
    public void setPaPay(StlPayOrders paPay) {
        this.paPay = paPay;
    }

    /**
     * 获取 是否DVP划款
     * 
     * @return 是否DVP划款.
     */
    public boolean isDVP() {
        return isDVP;
    }

    /**
     * 设置 是否DVP划款
     * 
     * @param isDVP 是否DVP划款
     */
    public void setDVP(boolean isDVP) {
        this.isDVP = isDVP;
    }
}
