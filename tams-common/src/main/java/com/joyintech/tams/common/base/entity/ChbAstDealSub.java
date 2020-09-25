package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstDealSub extends ChbAstDealSubKey {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 行内交易编码
     */
    private String tradeCode;

    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 币种 参照数据字典
     */
    private String ccy;

    /**
     * 发生金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenAmt;

    /**
     * 折算人民币金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cnyAmt;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal num;

    /**
     * 资产流动类型 参照数据字典
     */
    private String assetFloType;

    /**
     * 资产流动类型说明
     */
    private String floTypeCom;

    /**
     * 交易日
     */
    private Date dealDate;

    /**
     * 交易对手方
     */
    private String trader;

    /**
     * 单位成交价格（全价）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal allPrice;

    /**
     * 单位成交价格（净价）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netPrice;

    /**
     * 到期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 成交编号/合同号
     */
    private String contractNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 产品登记编码
     * @param prodRegistCode 产品登记编码
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode == null ? null : prodRegistCode.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 行内交易编码
     * @return 行内交易编码
     */
    public String getTradeCode() {
        return tradeCode;
    }

    /**
     * 设置 行内交易编码
     * @param tradeCode 行内交易编码
     */
    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode == null ? null : tradeCode.trim();
    }

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 币种 参照数据字典
     * @return 币种 参照数据字典
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种 参照数据字典
     * @param ccy 币种 参照数据字典
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 发生金额
     * @return 发生金额
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    /**
     * 设置 发生金额
     * @param happenAmt 发生金额
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    /**
     * 取得 折算人民币金额
     * @return 折算人民币金额
     */
    public BigDecimal getCnyAmt() {
        return cnyAmt;
    }

    /**
     * 设置 折算人民币金额
     * @param cnyAmt 折算人民币金额
     */
    public void setCnyAmt(BigDecimal cnyAmt) {
        this.cnyAmt = cnyAmt;
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getNum() {
        return num;
    }

    /**
     * 设置 数量
     * @param num 数量
     */
    public void setNum(BigDecimal num) {
        this.num = num;
    }

    /**
     * 取得 资产流动类型 参照数据字典
     * @return 资产流动类型 参照数据字典
     */
    public String getAssetFloType() {
        return assetFloType;
    }

    /**
     * 设置 资产流动类型 参照数据字典
     * @param assetFloType 资产流动类型 参照数据字典
     */
    public void setAssetFloType(String assetFloType) {
        this.assetFloType = assetFloType == null ? null : assetFloType.trim();
    }

    /**
     * 取得 资产流动类型说明
     * @return 资产流动类型说明
     */
    public String getFloTypeCom() {
        return floTypeCom;
    }

    /**
     * 设置 资产流动类型说明
     * @param floTypeCom 资产流动类型说明
     */
    public void setFloTypeCom(String floTypeCom) {
        this.floTypeCom = floTypeCom == null ? null : floTypeCom.trim();
    }

    /**
     * 取得 交易日
     * @return 交易日
     */
    public Date getDealDate() {
        return dealDate;
    }

    /**
     * 设置 交易日
     * @param dealDate 交易日
     */
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    /**
     * 取得 交易对手方
     * @return 交易对手方
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 交易对手方
     * @param trader 交易对手方
     */
    public void setTrader(String trader) {
        this.trader = trader == null ? null : trader.trim();
    }

    /**
     * 取得 单位成交价格（全价）
     * @return 单位成交价格（全价）
     */
    public BigDecimal getAllPrice() {
        return allPrice;
    }

    /**
     * 设置 单位成交价格（全价）
     * @param allPrice 单位成交价格（全价）
     */
    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    /**
     * 取得 单位成交价格（净价）
     * @return 单位成交价格（净价）
     */
    public BigDecimal getNetPrice() {
        return netPrice;
    }

    /**
     * 设置 单位成交价格（净价）
     * @param netPrice 单位成交价格（净价）
     */
    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
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
     * 取得 成交编号/合同号
     * @return 成交编号/合同号
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置 成交编号/合同号
     * @param contractNo 成交编号/合同号
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
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
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}