package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdRaisetotSub extends ChbPrdRaisetotSubKey {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 个人投资者总数
     */
    private Integer singleTotal;

    /**
     * 法人投资者总数
     */
    private Integer legalTotal;

    /**
     * 非法人投资者总数
     */
    private Integer unlegalTotal;

    /**
     * 认购币种
     */
    private String subscribeCcy;

    /**
     * 认购金额
     */
    private String subscribeAmt;

    /**
     * 折算人民币金额
     */
    private String cnyAmt;

    /**
     * 产品销售区域 参照数据字典
     */
    private String saleArea;

    /**
     * 区域募集金额
     */
    private String areaAmt;

    /**
     * 实际募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalAmt;

    /**
     * 募集总份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseQty;

    /**
     * 是否代销 参照数据字典
     */
    private String isConsign;

    /**
     * 代销总金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal consignAmt;

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
     * 取得 个人投资者总数
     * @return 个人投资者总数
     */
    public Integer getSingleTotal() {
        return singleTotal;
    }

    /**
     * 设置 个人投资者总数
     * @param singleTotal 个人投资者总数
     */
    public void setSingleTotal(Integer singleTotal) {
        this.singleTotal = singleTotal;
    }

    /**
     * 取得 法人投资者总数
     * @return 法人投资者总数
     */
    public Integer getLegalTotal() {
        return legalTotal;
    }

    /**
     * 设置 法人投资者总数
     * @param legalTotal 法人投资者总数
     */
    public void setLegalTotal(Integer legalTotal) {
        this.legalTotal = legalTotal;
    }

    /**
     * 取得 非法人投资者总数
     * @return 非法人投资者总数
     */
    public Integer getUnlegalTotal() {
        return unlegalTotal;
    }

    /**
     * 设置 非法人投资者总数
     * @param unlegalTotal 非法人投资者总数
     */
    public void setUnlegalTotal(Integer unlegalTotal) {
        this.unlegalTotal = unlegalTotal;
    }

    /**
     * 取得 认购币种
     * @return 认购币种
     */
    public String getSubscribeCcy() {
        return subscribeCcy;
    }

    /**
     * 设置 认购币种
     * @param subscribeCcy 认购币种
     */
    public void setSubscribeCcy(String subscribeCcy) {
        this.subscribeCcy = subscribeCcy == null ? null : subscribeCcy.trim();
    }

    /**
     * 取得 认购金额
     * @return 认购金额
     */
    public String getSubscribeAmt() {
        return subscribeAmt;
    }

    /**
     * 设置 认购金额
     * @param subscribeAmt 认购金额
     */
    public void setSubscribeAmt(String subscribeAmt) {
        this.subscribeAmt = subscribeAmt == null ? null : subscribeAmt.trim();
    }

    /**
     * 取得 折算人民币金额
     * @return 折算人民币金额
     */
    public String getCnyAmt() {
        return cnyAmt;
    }

    /**
     * 设置 折算人民币金额
     * @param cnyAmt 折算人民币金额
     */
    public void setCnyAmt(String cnyAmt) {
        this.cnyAmt = cnyAmt == null ? null : cnyAmt.trim();
    }

    /**
     * 取得 产品销售区域 参照数据字典
     * @return 产品销售区域 参照数据字典
     */
    public String getSaleArea() {
        return saleArea;
    }

    /**
     * 设置 产品销售区域 参照数据字典
     * @param saleArea 产品销售区域 参照数据字典
     */
    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea == null ? null : saleArea.trim();
    }

    /**
     * 取得 区域募集金额
     * @return 区域募集金额
     */
    public String getAreaAmt() {
        return areaAmt;
    }

    /**
     * 设置 区域募集金额
     * @param areaAmt 区域募集金额
     */
    public void setAreaAmt(String areaAmt) {
        this.areaAmt = areaAmt == null ? null : areaAmt.trim();
    }

    /**
     * 取得 实际募集金额
     * @return 实际募集金额
     */
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    /**
     * 设置 实际募集金额
     * @param totalAmt 实际募集金额
     */
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    /**
     * 取得 募集总份额
     * @return 募集总份额
     */
    public BigDecimal getRaiseQty() {
        return raiseQty;
    }

    /**
     * 设置 募集总份额
     * @param raiseQty 募集总份额
     */
    public void setRaiseQty(BigDecimal raiseQty) {
        this.raiseQty = raiseQty;
    }

    /**
     * 取得 是否代销 参照数据字典
     * @return 是否代销 参照数据字典
     */
    public String getIsConsign() {
        return isConsign;
    }

    /**
     * 设置 是否代销 参照数据字典
     * @param isConsign 是否代销 参照数据字典
     */
    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign == null ? null : isConsign.trim();
    }

    /**
     * 取得 代销总金额
     * @return 代销总金额
     */
    public BigDecimal getConsignAmt() {
        return consignAmt;
    }

    /**
     * 设置 代销总金额
     * @param consignAmt 代销总金额
     */
    public void setConsignAmt(BigDecimal consignAmt) {
        this.consignAmt = consignAmt;
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