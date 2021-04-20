package com.joyintech.background.component.uplink.interbank.database.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.background.component.common.pack.common.json.BigDecimalSerializer;
import com.joyintech.background.component.common.pack.common.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class WflIbuQuoteBondDet extends BaseEntity {
    /**
     * 明细编号
     */
    private String detId;

    /**
     * 请求编号(请求报价使用的外部编号)
     */
    private String reqId;

    /**
     * 版本号
     */
    private Long version;

    /**
     * CFETS请求编号
     */
    private String cfetsReqId;

    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

    /**
     * 对手银行间机构，21位编号
     */
    private String cpPartyId;

    /**
     * 对手方银行间交易员
     */
    private String cpTrader;

    /**
     * 单位净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 单位利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 单位全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitFprice;

    /**
     * 单位面值，债券100或者当前面值(还本前)，其他资产1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal parValue;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 交易本金，交易成本等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 面值总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal parValueAmt;

    /**
     * 净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;

    /**
     * 利息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 到期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 行权收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exerYield;

    /**
     * 计算方式，净价/到期收益率/行权收益率
     */
    private String calculateField;

    /**
     * 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     */
    private String tradeStatus;

    /**
     * 发送状态，待发送/成功/失败/发送中
     */
    private String sendStatus;

    /**
     * 成交编号
     */
    private String execid;

    /**
     * 回复有效时间
     */
    private Date reEffTime;

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
     * 取得 明细编号
     * @return 明细编号
     */
    public String getDetId() {
        return detId;
    }

    /**
     * 设置 明细编号
     * @param detId 明细编号
     */
    public void setDetId(String detId) {
        this.detId = detId == null ? null : detId.trim();
    }

    /**
     * 取得 请求编号(请求报价使用的外部编号)
     * @return 请求编号(请求报价使用的外部编号)
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * 设置 请求编号(请求报价使用的外部编号)
     * @param reqId 请求编号(请求报价使用的外部编号)
     */
    public void setReqId(String reqId) {
        this.reqId = reqId == null ? null : reqId.trim();
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
     * 取得 CFETS请求编号
     * @return CFETS请求编号
     */
    public String getCfetsReqId() {
        return cfetsReqId;
    }

    /**
     * 设置 CFETS请求编号
     * @param cfetsReqId CFETS请求编号
     */
    public void setCfetsReqId(String cfetsReqId) {
        this.cfetsReqId = cfetsReqId == null ? null : cfetsReqId.trim();
    }

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
     * 取得 对手银行间机构，21位编号
     * @return 对手银行间机构，21位编号
     */
    public String getCpPartyId() {
        return cpPartyId;
    }

    /**
     * 设置 对手银行间机构，21位编号
     * @param cpPartyId 对手银行间机构，21位编号
     */
    public void setCpPartyId(String cpPartyId) {
        this.cpPartyId = cpPartyId == null ? null : cpPartyId.trim();
    }

    /**
     * 取得 对手方银行间交易员
     * @return 对手方银行间交易员
     */
    public String getCpTrader() {
        return cpTrader;
    }

    /**
     * 设置 对手方银行间交易员
     * @param cpTrader 对手方银行间交易员
     */
    public void setCpTrader(String cpTrader) {
        this.cpTrader = cpTrader == null ? null : cpTrader.trim();
    }

    /**
     * 取得 单位净价
     * @return 单位净价
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 单位净价
     * @param unitCprice 单位净价
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 取得 单位利息
     * @return 单位利息
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }

    /**
     * 设置 单位利息
     * @param unitInt 单位利息
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    /**
     * 取得 单位全价
     * @return 单位全价
     */
    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    /**
     * 设置 单位全价
     * @param unitFprice 单位全价
     */
    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    /**
     * 取得 单位面值，债券100或者当前面值(还本前)，其他资产1
     * @return 单位面值，债券100或者当前面值(还本前)，其他资产1
     */
    public BigDecimal getParValue() {
        return parValue;
    }

    /**
     * 设置 单位面值，债券100或者当前面值(还本前)，其他资产1
     * @param parValue 单位面值，债券100或者当前面值(还本前)，其他资产1
     */
    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
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
     * 取得 交易本金，交易成本等
     * @return 交易本金，交易成本等
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 交易本金，交易成本等
     * @param prinAmt 交易本金，交易成本等
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 取得 面值总额
     * @return 面值总额
     */
    public BigDecimal getParValueAmt() {
        return parValueAmt;
    }

    /**
     * 设置 面值总额
     * @param parValueAmt 面值总额
     */
    public void setParValueAmt(BigDecimal parValueAmt) {
        this.parValueAmt = parValueAmt;
    }

    /**
     * 取得 净价总额
     * @return 净价总额
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    /**
     * 设置 净价总额
     * @param cpriceAmt 净价总额
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    /**
     * 取得 利息总额
     * @return 利息总额
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 利息总额
     * @param intAmt 利息总额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
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
     * 取得 到期收益率
     * @return 到期收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 到期收益率
     * @param yield 到期收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 行权收益率
     * @return 行权收益率
     */
    public BigDecimal getExerYield() {
        return exerYield;
    }

    /**
     * 设置 行权收益率
     * @param exerYield 行权收益率
     */
    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    /**
     * 取得 计算方式，净价/到期收益率/行权收益率
     * @return 计算方式，净价/到期收益率/行权收益率
     */
    public String getCalculateField() {
        return calculateField;
    }

    /**
     * 设置 计算方式，净价/到期收益率/行权收益率
     * @param calculateField 计算方式，净价/到期收益率/行权收益率
     */
    public void setCalculateField(String calculateField) {
        this.calculateField = calculateField == null ? null : calculateField.trim();
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
     * 取得 成交编号
     * @return 成交编号
     */
    public String getExecid() {
        return execid;
    }

    /**
     * 设置 成交编号
     * @param execid 成交编号
     */
    public void setExecid(String execid) {
        this.execid = execid == null ? null : execid.trim();
    }

    /**
     * 取得 回复有效时间
     * @return 回复有效时间
     */
    public Date getReEffTime() {
        return reEffTime;
    }

    /**
     * 设置 回复有效时间
     * @param reEffTime 回复有效时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReEffTime(Date reEffTime) {
        this.reEffTime = reEffTime;
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