package com.joyintech.background.component.uplink.interbank.database.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.background.component.common.pack.common.json.BigDecimalSerializer;
import com.joyintech.background.component.common.pack.common.json.CustomDateDeserialize;

public class WflIbuQuoteBond2 extends BaseEntity {

    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

    /**
     * 报价类型，对话报价/请求报价/质押式回购报价
     */
    private String quoteType;

    /**
     * 本方角色
     */
    private String quoteRoleType;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 操作类型
     */
    private String exeType;

    /**
     * 外部报价编号
     */
    private String pQuoteId;

    /**
     * CFETS报价编号
     */
    private String cfetsQuoteId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 报价日期
     */
    private Date quoteDate;

    /**
     * 交割日期
     */
    private Date settleDate;

    /**
     * 清算速度
     */
    private Long settleSpeed;

    /**
     * 结算方式，券款对付、见券付款、见款付券
     */
    private String deliveryType;

    /**
     * 清算类型
     */
    private String settType;

    /**
     * 结算币种
     */
    private String settCcy;

    /**
     * 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    private String ps;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     */
    private String tradeStatus;

    /**
     * 发送状态，待发送/成功/失败/发送中
     */
    private String sendStatus;

    /**
     * 有效时间
     */
    private Date effTime;

    /**
     * 本方银行间机构，21位编号
     */
    private String ouPartyId;

    /**
     * 本方银行间交易员
     */
    private String ouTraderId;

    /**
     * 前置版本号
     */
    private Long bVersion;

    /**
     * 本方资金账户
     */
    private String myAcctNo;

    /**
     * 证券管理户代码
     */
    private String secManageAcctId;

    /**
     * 序号ID,平安个性化字段
     */
    private String branchId;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 数据状态
     */
    private String workflowStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 报价编号（内部代码）
     * @return 报价编号（内部代码）
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * 设置 报价编号（内部代码）
     * @param quoteId 报价编号（内部代码）
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId == null ? null : quoteId.trim();
    }

    /**
     * 取得 报价类型，对话报价/请求报价/质押式回购报价
     * @return 报价类型，对话报价/请求报价/质押式回购报价
     */
    public String getQuoteType() {
        return quoteType;
    }

    /**
     * 设置 报价类型，对话报价/请求报价/质押式回购报价
     * @param quoteType 报价类型，对话报价/请求报价/质押式回购报价
     */
    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType == null ? null : quoteType.trim();
    }

    /**
     * 取得 本方角色
     * @return 本方角色
     */
    public String getQuoteRoleType() {
        return quoteRoleType;
    }

    /**
     * 设置 本方角色
     * @param quoteRoleType 本方角色
     */
    public void setQuoteRoleType(String quoteRoleType) {
        this.quoteRoleType = quoteRoleType == null ? null : quoteRoleType.trim();
    }

    /**
     * 取得 版本号
     * @return 版本号
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置 版本号
     * @param version 版本号
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 取得 操作类型
     * @return 操作类型
     */
    public String getExeType() {
        return exeType;
    }

    /**
     * 设置 操作类型
     * @param exeType 操作类型
     */
    public void setExeType(String exeType) {
        this.exeType = exeType == null ? null : exeType.trim();
    }

    /**
     * 取得 外部报价编号
     * @return 外部报价编号
     */
    public String getpQuoteId() {
        return pQuoteId;
    }

    /**
     * 设置 外部报价编号
     * @param pQuoteId 外部报价编号
     */
    public void setpQuoteId(String pQuoteId) {
        this.pQuoteId = pQuoteId == null ? null : pQuoteId.trim();
    }

    /**
     * 取得 CFETS报价编号
     * @return CFETS报价编号
     */
    public String getCfetsQuoteId() {
        return cfetsQuoteId;
    }

    /**
     * 设置 CFETS报价编号
     * @param cfetsQuoteId CFETS报价编号
     */
    public void setCfetsQuoteId(String cfetsQuoteId) {
        this.cfetsQuoteId = cfetsQuoteId == null ? null : cfetsQuoteId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType2 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 报价日期
     * @return 报价日期
     */
    public Date getQuoteDate() {
        return quoteDate;
    }

    /**
     * 设置 报价日期
     * @param quoteDate 报价日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    /**
     * 取得 交割日期
     * @return 交割日期
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 交割日期
     * @param settleDate 交割日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 取得 清算速度
     * @return 清算速度
     */
    public Long getSettleSpeed() {
        return settleSpeed;
    }

    /**
     * 设置 清算速度
     * @param settleSpeed 清算速度
     */
    public void setSettleSpeed(Long settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 取得 结算方式，券款对付、见券付款、见款付券
     * @return 结算方式，券款对付、见券付款、见款付券
     */
    public String getDeliveryType() {
        return deliveryType;
    }

    /**
     * 设置 结算方式，券款对付、见券付款、见款付券
     * @param deliveryType 结算方式，券款对付、见券付款、见款付券
     */
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType == null ? null : deliveryType.trim();
    }

    /**
     * 取得 清算类型
     * @return 清算类型
     */
    public String getSettType() {
        return settType;
    }

    /**
     * 设置 清算类型
     * @param settType 清算类型
     */
    public void setSettType(String settType) {
        this.settType = settType == null ? null : settType.trim();
    }

    /**
     * 取得 结算币种
     * @return 结算币种
     */
    public String getSettCcy() {
        return settCcy;
    }

    /**
     * 设置 结算币种
     * @param settCcy 结算币种
     */
    public void setSettCcy(String settCcy) {
        this.settCcy = settCcy == null ? null : settCcy.trim();
    }

    /**
     * 取得 交易方向，买入、卖出、收息、还本、申购、赎回等
     * @return 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向，买入、卖出、收息、还本、申购、赎回等
     * @param ps 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 交易份额，债券数量等
     * @return 交易份额，债券数量等
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 交易份额，债券数量等
     * @param shareAmt 交易份额，债券数量等
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 全价总额
     * @return 全价总额
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 全价总额
     * @param fpriceAmt 全价总额
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 取得 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     * @return 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     * @param tradeStatus 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    /**
     * 取得 发送状态，待发送/成功/失败/发送中
     * @return 发送状态，待发送/成功/失败/发送中
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 发送状态，待发送/成功/失败/发送中
     * @param sendStatus 发送状态，待发送/成功/失败/发送中
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 有效时间
     * @return 有效时间
     */
    public Date getEffTime() {
        return effTime;
    }

    /**
     * 设置 有效时间
     * @param effTime 有效时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffTime(Date effTime) {
        this.effTime = effTime;
    }

    /**
     * 取得 本方银行间机构，21位编号
     * @return 本方银行间机构，21位编号
     */
    public String getOuPartyId() {
        return ouPartyId;
    }

    /**
     * 设置 本方银行间机构，21位编号
     * @param ouPartyId 本方银行间机构，21位编号
     */
    public void setOuPartyId(String ouPartyId) {
        this.ouPartyId = ouPartyId == null ? null : ouPartyId.trim();
    }

    /**
     * 取得 本方银行间交易员
     * @return 本方银行间交易员
     */
    public String getOuTraderId() {
        return ouTraderId;
    }

    /**
     * 设置 本方银行间交易员
     * @param ouTraderId 本方银行间交易员
     */
    public void setOuTraderId(String ouTraderId) {
        this.ouTraderId = ouTraderId == null ? null : ouTraderId.trim();
    }

    /**
     * 取得 前置版本号
     * @return 前置版本号
     */
    public Long getbVersion() {
        return bVersion;
    }

    /**
     * 设置 前置版本号
     * @param bVersion 前置版本号
     */
    public void setbVersion(Long bVersion) {
        this.bVersion = bVersion;
    }

    /**
     * 取得 本方资金账户
     * @return 本方资金账户
     */
    public String getMyAcctNo() {
        return myAcctNo;
    }

    /**
     * 设置 本方资金账户
     * @param myAcctNo 本方资金账户
     */
    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo == null ? null : myAcctNo.trim();
    }

    /**
     * 取得 证券管理户代码
     * @return 证券管理户代码
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 证券管理户代码
     * @param secManageAcctId 证券管理户代码
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId == null ? null : secManageAcctId.trim();
    }

    /**
     * 取得 序号ID,平安个性化字段
     * @return 序号ID,平安个性化字段
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * 设置 序号ID,平安个性化字段
     * @param branchId 序号ID,平安个性化字段
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 数据状态
     * @return 数据状态
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 数据状态
     * @param workflowStatus 数据状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus == null ? null : workflowStatus.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}