package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdIssueSub extends ChbPrdIssueSubKey {
    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 理财产品代码
     */
    private String prodId;

    /**
     * 募集起始日
     */
    private Date raiseVdate;

    /**
     * 募集结束日
     */
    private Date raiseEnddate;

    /**
     * 产品起始日期
     */
    private Date prdVdate;

    /**
     * 产品终止日期
     */
    private Date prdMdate;

    /**
     * 是否为结构化（分级）产品
     */
    private String isConstracte;

    /**
     * 层级类别
     */
    private String gradeType;

    /**
     * 预计客户最低年收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expMinYield;

    /**
     * 预计客户最高年收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expMaxYield;

    /**
     * 预计客户平均年收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expAvgYield;

    /**
     * 收益率分档情况说明
     */
    private String yieldComment;

    /**
     * 开放模式
     */
    private String openMode;

    /**
     * 规律开放周期
     */
    private String openCircle;

    /**
     * 其他规律开放周期（天）
     */
    private Short openCircleDays;

    /**
     * 无规律开放说明
     */
    private String ruleComment;

    /**
     * 首次开放周期起始日
     */
    private Date firstOpenDay;

    /**
     * 节假日是否开放
     */
    private String holidayOnFlag;

    /**
     * 平均开放次数（年化）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal avgOpenCount;

    /**
     * 开放期业务
     */
    private String openBuss;

    /**
     * 开放期业务说明
     */
    private String openBussRemark;

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
     * 管理方式
     */
    private String manageType;

    /**
     * 业绩比较基准下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldLow;

    /**
     * 业绩比较基准上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldHigh;

    /**
     * 业绩比较基准说明
     */
    private String yieldRemark;

    /**
     * 资金托管账号
     */
    private String busiAcctNo;

    /**
     * 资金托管账户
     */
    private String acctName;

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
     * 取得 理财产品代码
     * @return 理财产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 理财产品代码
     * @param prodId 理财产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 募集起始日
     * @return 募集起始日
     */
    public Date getRaiseVdate() {
        return raiseVdate;
    }

    /**
     * 设置 募集起始日
     * @param raiseVdate 募集起始日
     */
    public void setRaiseVdate(Date raiseVdate) {
        this.raiseVdate = raiseVdate;
    }

    /**
     * 取得 募集结束日
     * @return 募集结束日
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 设置 募集结束日
     * @param raiseEnddate 募集结束日
     */
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 取得 产品起始日期
     * @return 产品起始日期
     */
    public Date getPrdVdate() {
        return prdVdate;
    }

    /**
     * 设置 产品起始日期
     * @param prdVdate 产品起始日期
     */
    public void setPrdVdate(Date prdVdate) {
        this.prdVdate = prdVdate;
    }

    /**
     * 取得 产品终止日期
     * @return 产品终止日期
     */
    public Date getPrdMdate() {
        return prdMdate;
    }

    /**
     * 设置 产品终止日期
     * @param prdMdate 产品终止日期
     */
    public void setPrdMdate(Date prdMdate) {
        this.prdMdate = prdMdate;
    }

    /**
     * 取得 是否为结构化（分级）产品
     * @return 是否为结构化（分级）产品
     */
    public String getIsConstracte() {
        return isConstracte;
    }

    /**
     * 设置 是否为结构化（分级）产品
     * @param isConstracte 是否为结构化（分级）产品
     */
    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte == null ? null : isConstracte.trim();
    }

    /**
     * 取得 层级类别
     * @return 层级类别
     */
    public String getGradeType() {
        return gradeType;
    }

    /**
     * 设置 层级类别
     * @param gradeType 层级类别
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType == null ? null : gradeType.trim();
    }

    /**
     * 取得 预计客户最低年收益率
     * @return 预计客户最低年收益率
     */
    public BigDecimal getExpMinYield() {
        return expMinYield;
    }

    /**
     * 设置 预计客户最低年收益率
     * @param expMinYield 预计客户最低年收益率
     */
    public void setExpMinYield(BigDecimal expMinYield) {
        this.expMinYield = expMinYield;
    }

    /**
     * 取得 预计客户最高年收益率
     * @return 预计客户最高年收益率
     */
    public BigDecimal getExpMaxYield() {
        return expMaxYield;
    }

    /**
     * 设置 预计客户最高年收益率
     * @param expMaxYield 预计客户最高年收益率
     */
    public void setExpMaxYield(BigDecimal expMaxYield) {
        this.expMaxYield = expMaxYield;
    }

    /**
     * 取得 预计客户平均年收益率
     * @return 预计客户平均年收益率
     */
    public BigDecimal getExpAvgYield() {
        return expAvgYield;
    }

    /**
     * 设置 预计客户平均年收益率
     * @param expAvgYield 预计客户平均年收益率
     */
    public void setExpAvgYield(BigDecimal expAvgYield) {
        this.expAvgYield = expAvgYield;
    }

    /**
     * 取得 收益率分档情况说明
     * @return 收益率分档情况说明
     */
    public String getYieldComment() {
        return yieldComment;
    }

    /**
     * 设置 收益率分档情况说明
     * @param yieldComment 收益率分档情况说明
     */
    public void setYieldComment(String yieldComment) {
        this.yieldComment = yieldComment == null ? null : yieldComment.trim();
    }

    /**
     * 取得 开放模式
     * @return 开放模式
     */
    public String getOpenMode() {
        return openMode;
    }

    /**
     * 设置 开放模式
     * @param openMode 开放模式
     */
    public void setOpenMode(String openMode) {
        this.openMode = openMode == null ? null : openMode.trim();
    }

    /**
     * 取得 规律开放周期
     * @return 规律开放周期
     */
    public String getOpenCircle() {
        return openCircle;
    }

    /**
     * 设置 规律开放周期
     * @param openCircle 规律开放周期
     */
    public void setOpenCircle(String openCircle) {
        this.openCircle = openCircle == null ? null : openCircle.trim();
    }

    /**
     * 取得 其他规律开放周期（天）
     * @return 其他规律开放周期（天）
     */
    public Short getOpenCircleDays() {
        return openCircleDays;
    }

    /**
     * 设置 其他规律开放周期（天）
     * @param openCircleDays 其他规律开放周期（天）
     */
    public void setOpenCircleDays(Short openCircleDays) {
        this.openCircleDays = openCircleDays;
    }

    /**
     * 取得 无规律开放说明
     * @return 无规律开放说明
     */
    public String getRuleComment() {
        return ruleComment;
    }

    /**
     * 设置 无规律开放说明
     * @param ruleComment 无规律开放说明
     */
    public void setRuleComment(String ruleComment) {
        this.ruleComment = ruleComment == null ? null : ruleComment.trim();
    }

    /**
     * 取得 首次开放周期起始日
     * @return 首次开放周期起始日
     */
    public Date getFirstOpenDay() {
        return firstOpenDay;
    }

    /**
     * 设置 首次开放周期起始日
     * @param firstOpenDay 首次开放周期起始日
     */
    public void setFirstOpenDay(Date firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    /**
     * 取得 节假日是否开放
     * @return 节假日是否开放
     */
    public String getHolidayOnFlag() {
        return holidayOnFlag;
    }

    /**
     * 设置 节假日是否开放
     * @param holidayOnFlag 节假日是否开放
     */
    public void setHolidayOnFlag(String holidayOnFlag) {
        this.holidayOnFlag = holidayOnFlag == null ? null : holidayOnFlag.trim();
    }

    /**
     * 取得 平均开放次数（年化）
     * @return 平均开放次数（年化）
     */
    public BigDecimal getAvgOpenCount() {
        return avgOpenCount;
    }

    /**
     * 设置 平均开放次数（年化）
     * @param avgOpenCount 平均开放次数（年化）
     */
    public void setAvgOpenCount(BigDecimal avgOpenCount) {
        this.avgOpenCount = avgOpenCount;
    }

    /**
     * 取得 开放期业务
     * @return 开放期业务
     */
    public String getOpenBuss() {
        return openBuss;
    }

    /**
     * 设置 开放期业务
     * @param openBuss 开放期业务
     */
    public void setOpenBuss(String openBuss) {
        this.openBuss = openBuss == null ? null : openBuss.trim();
    }

    /**
     * 取得 开放期业务说明
     * @return 开放期业务说明
     */
    public String getOpenBussRemark() {
        return openBussRemark;
    }

    /**
     * 设置 开放期业务说明
     * @param openBussRemark 开放期业务说明
     */
    public void setOpenBussRemark(String openBussRemark) {
        this.openBussRemark = openBussRemark == null ? null : openBussRemark.trim();
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
    /**
     * 取得 管理方式
     * @return 管理方式
     */
    public String getManageType() {
        return manageType;
    }

    /**
     * 设置 管理方式
     * @param manageType 管理方式
     */
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**
     * 取得 业绩比较基准下限
     * @return 业绩比较基准下限
     */
    public BigDecimal getYieldLow() {
        return yieldLow;
    }

    /**
     * 设置 业绩比较基准下限
     * @param yieldLow 业绩比较基准下限
     */
    public void setYieldLow(BigDecimal yieldLow) {
        this.yieldLow = yieldLow;
    }

    /**
     * 取得 业绩比较基准上限
     * @return 业绩比较基准上限
     */
    public BigDecimal getYieldHigh() {
        return yieldHigh;
    }

    /**
     * 设置 业绩比较基准上限
     * @param yieldHigh 业绩比较基准上限
     */
    public void setYieldHigh(BigDecimal yieldHigh) {
        this.yieldHigh = yieldHigh;
    }

    /**
     * 取得 业绩比较基准说明
     * @return 业绩比较基准说明
     */
    public String getYieldRemark() {
        return yieldRemark;
    }

    /**
     * 设置 业绩比较基准说明
     * @param yieldRemark 业绩比较基准说明
     */
    public void setYieldRemark(String yieldRemark) {
        this.yieldRemark = yieldRemark == null ? null : yieldRemark.trim();
    }

    /**
     * 取得 资金托管账号
     * @return 资金托管账号
     */
    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    /**
     * 设置 资金托管账号
     * @param busiAcctNo 资金托管账号
     */
    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo == null ? null : busiAcctNo.trim();
    }

    /**
     * 取得 资金托管账户
     * @return 资金托管账户
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 资金托管账户
     * @param acctName 资金托管账户
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
    }
}