/*
 * 文件名：InvestFeePay002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.portfolio.feepay.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.portfolio.feepay.validator.InvestFeePay002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 投资端费用支付新建dto
 * 
 * @author malinlin
 * @version 1.0 2019年12月8日
 * @see InvestFeePay002Dto
 * @since 1.0
 */
@PageId(value = "InvestFeePay002")
@ValidLogic(targetClass = InvestFeePay002Validator.class)
public class InvestFeePay002Dto extends BaseEntity implements ProcessEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 7605593327899545754L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 确认模式
     */
    private String confirmFlag;

    /**
     * 按钮Id
     */
    private String elementId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 组合代码
     */
    private String ptlId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 费用代码
     */
    private String feeId;

    /**
     * 费用名称
     */
    private String feeName;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 付费规则
     */
    private String chargeType;

    /**
     * 登记托管机构
     */
    private String regTrustOrg;

    /**
     * 计提开始日
     */
    private Date startDate;

    /**
     * 计提结束日
     */
    private Date endDate;

    /**
     * 支付日
     */
    private Date payDate;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 存json串
     */
    private String allData;

    public String getAllData() {
        return allData;
    }

    public void setAllData(String allData) {
        this.allData = allData;
    }

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 子产品款型
     */
    private String sFinprodId;

    /**
     * 产品收益类型
     */
    private String profitType;

    /**
     * 产品处理模式
     */
    private String dealMode;

    /**
     * mdate
     */
    private Date mdate;

    /**
     * 计费基数
     */
    private String basicBill;

    /**
     * 证券账户
     */
    private String secAcctId;

    public String getBasicBill() {
        return basicBill;
    }

    public void setBasicBill(String basicBill) {
        this.basicBill = basicBill;
    }

    public String getsFinprodId() {
        return sFinprodId;
    }

    public void setsFinprodId(String sFinprodId) {
        this.sFinprodId = sFinprodId;
    }

    /**
     * 理论金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal payAmt;

    /**
     * 实付金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    @Amount(fieldName = "FinProductFeePay002.actPayAmt")
    private BigDecimal actPayAmt;

    /**
     * 调整金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal adjustAmt;

    /**
     * 交易备注
     */
    private String remark;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 费用明细列表
     */
    @Valid
    private transient List<InvestFeePay003Dto> feeInfoList;

    /**
     * 分层费用明细列表
     */
    private transient List<InvestFeePay003Dto> feelayeredInfoList;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 附件上传
     */
    private List<UploadFile> fileArray;

    /**
     * 是否结清
     */
    private String isClean;

    /**
     * 剩余金额
     */
    private BigDecimal remainAmt;

    /**
     * 上次未结清费用
     */
    private BigDecimal unclearAmt;

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 ptlId
     * 
     * @return ptlId.
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 ptlId
     * 
     * @param ptlId ptlId
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    /**
     * 获取 feeId
     * 
     * @return feeId.
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 获取 feeType
     * 
     * @return feeType.
     */
    @NotEmpty(fieldName = "FinProductFeePay002.feeType")
    @PrimaryKey(Field.PK_1)
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 feeType
     * 
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    /**
     * 设置 feeId
     * 
     * @param feeId feeId
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    /**
     * 获取 regTrustOrg
     * 
     * @return regTrustOrg.
     */
    public String getRegTrustOrg() {
        return regTrustOrg;
    }

    /**
     * 设置 regTrustOrg
     * 
     * @param regTrustOrg regTrustOrg
     */
    public void setRegTrustOrg(String regTrustOrg) {
        this.regTrustOrg = regTrustOrg;
    }

    /**
     * 获取 startDate
     * 
     * @return startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 获取 startDate
     * 
     * @return startDate.
     */
    public String getStartDateStr() {
        return DateUtil.format(startDate);
    }

    /**
     * 设置 startDate
     * 
     * @param startDate startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取 endDate
     * 
     * @return endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 获取 endDate
     * 
     * @return endDate.
     */
    public String getEndDateStr() {
        return DateUtil.format(endDate);
    }

    /**
     * 设置 endDate
     * 
     * @param endDate endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取 payDate
     * 
     * @return payDate.
     */
    @NotEmpty(fieldName = "FinProductFeePay002.payDate")
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     * 
     * @param payDate payDate
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 endDate
     *
     * @return endDate.
     */
    public String getPayDateStr() {
        return DateUtil.format(payDate);
    }

    /**
     * 获取 feeInfoList
     * 
     * @return feeInfoList.
     */
    public List<InvestFeePay003Dto> getFeeInfoList() {
        return feeInfoList;
    }

    /**
     * 设置 feeInfoList
     * 
     * @param feeInfoList feeInfoList
     */
    public void setFeeInfoList(List<InvestFeePay003Dto> feeInfoList) {
        this.feeInfoList = feeInfoList;
    }


    /**
     * 获取 payAmt
     * 
     * @return payAmt.
     */
    public BigDecimal getPayAmt() {
        return payAmt;
    }

    /**
     * 设置 payAmt
     * 
     * @param payAmt payAmt
     */
    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    /**
     * 获取 actPayAmt
     * 
     * @return actPayAmt.
     */
    public BigDecimal getActPayAmt() {
        return actPayAmt;
    }

    /**
     * 设置 actPayAmt
     * 
     * @param actPayAmt actPayAmt
     */
    public void setActPayAmt(BigDecimal actPayAmt) {
        this.actPayAmt = actPayAmt;
    }

    /**
     * 获取 adjustAmt
     * 
     * @return adjustAmt.
     */
    public BigDecimal getAdjustAmt() {
        return adjustAmt;
    }

    /**
     * 设置 adjustAmt
     * 
     * @param adjustAmt adjustAmt
     */
    public void setAdjustAmt(BigDecimal adjustAmt) {
        this.adjustAmt = adjustAmt;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 tradeId
     * 
     * @return tradeId.
     */
    @PrimaryKey(Field.PK_2)
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 tradeId
     * 
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 finprodMarketId
     * 
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * 
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 feeName
     * 
     * @return feeName.
     */
    @NotEmpty(fieldName = "FinProductFeePay002.feeName")
    @PrimaryKey(Field.PK_3)
    public String getFeeName() {
        return feeName;
    }

    /**
     * 设置 feeName
     * 
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     * 获取 remark
     * 
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     * 
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 bussTrdTransferOrder
     * 
     * @return bussTrdTransferOrder.
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     * 
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 cashId
     * 
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     * 
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 revokeReason
     * 
     * @return revokeReason.
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     * 
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 fileArray
     * 
     * @return fileArray.
     */
    public List<UploadFile> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     * 
     * @param fileArray fileArray
     */
    public void setFileArray(List<UploadFile> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 获取 confirmFlag
     * 
     * @return confirmFlag.
     */
    public String getConfirmFlag() {
        return confirmFlag;
    }

    /**
     * 设置 confirmFlag
     * 
     * @param confirmFlag confirmFlag
     */
    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public String getCcy() {
        return ccy;
    }

    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getIsClean() {
        return isClean;
    }

    public void setIsClean(String isClean) {
        this.isClean = isClean;
    }

    public BigDecimal getRemainAmt() {
        return remainAmt;
    }

    public void setRemainAmt(BigDecimal remainAmt) {
        this.remainAmt = remainAmt;
    }

    public BigDecimal getUnclearAmt() {
        return unclearAmt;
    }

    public void setUnclearAmt(BigDecimal unclearAmt) {
        this.unclearAmt = unclearAmt;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public List<InvestFeePay003Dto> getFeelayeredInfoList() {
        return feelayeredInfoList;
    }

    public void setFeelayeredInfoList(List<InvestFeePay003Dto> feelayeredInfoList) {
        this.feelayeredInfoList = feelayeredInfoList;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    /**
     * 获取 mdate
     * 
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * 
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 secAcctId
     * @return secAcctId.
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 secAcctId
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

}
