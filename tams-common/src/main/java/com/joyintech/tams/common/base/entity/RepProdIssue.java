package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepProdIssue extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 行内标识码/理财产品代码
     */
    private String repBankProdCode;

    /**
     * 产品登记编码
     */
    private String repFinprodRegId;

    /**
     * 募集起始日
     */
    private Date repRaiseVdate;

    /**
     * 募集结束日
     */
    private Date repRaiseEnddate;

    /**
     * 产品起始日期
     */
    private Date repPrdVdate;

    /**
     * 产品终止日期
     */
    private Date repPrdMdate;

    /**
     * 是否为结构化（分级）产品
     */
    private String repIsConstracte;

    /**
     * 层级类别
     */
    private String repGradeType;

    /**
     * 预计客户最低年收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repExpMinYield;

    /**
     * 预计客户最高年收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repExpMaxYield;

    /**
     * 预计客户平均年收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repExpAvgYield;

    /**
     * 收益率分档情况说明
     */
    private String repYieldComment;

    /**
     * 开放模式
     */
    private String repOpenMode;

    /**
     * 规律开放周期
     */
    private String repOpenCircle;

    /**
     * 其他规律开放周期（天）
     */
    private Short repOpenCircleDays;

    /**
     * 无规律开放说明
     */
    private String repRuleComment;

    /**
     * 首次开放周期起始日
     */
    private Date repFirstOpenDay;

    /**
     * 节假日是否开放
     */
    private String repHolidayOnFlag;

    /**
     * 平均开放次数（年化）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAvgOpenCount;

    /**
     * 开放期业务
     */
    private String repOpenBuss;

    /**
     * 开放期业务说明
     */
    private String repOpenBussRemark;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 发行登记日
     */
    private Date repIssueDate;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 行内标识码/理财产品代码
     * @return 行内标识码/理财产品代码
     */
    public String getRepBankProdCode() {
        return repBankProdCode;
    }

    /**
     * 设置 行内标识码/理财产品代码
     * @param repBankProdCode 行内标识码/理财产品代码
     */
    public void setRepBankProdCode(String repBankProdCode) {
        this.repBankProdCode = repBankProdCode == null ? null : repBankProdCode.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getRepFinprodRegId() {
        return repFinprodRegId;
    }

    /**
     * 设置 产品登记编码
     * @param repFinprodRegId 产品登记编码
     */
    public void setRepFinprodRegId(String repFinprodRegId) {
        this.repFinprodRegId = repFinprodRegId == null ? null : repFinprodRegId.trim();
    }

    /**
     * 取得 募集起始日
     * @return 募集起始日
     */
    public Date getRepRaiseVdate() {
        return repRaiseVdate;
    }

    /**
     * 设置 募集起始日
     * @param repRaiseVdate 募集起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepRaiseVdate(Date repRaiseVdate) {
        this.repRaiseVdate = repRaiseVdate;
    }

    /**
     * 取得 募集结束日
     * @return 募集结束日
     */
    public Date getRepRaiseEnddate() {
        return repRaiseEnddate;
    }

    /**
     * 设置 募集结束日
     * @param repRaiseEnddate 募集结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepRaiseEnddate(Date repRaiseEnddate) {
        this.repRaiseEnddate = repRaiseEnddate;
    }

    /**
     * 取得 产品起始日期
     * @return 产品起始日期
     */
    public Date getRepPrdVdate() {
        return repPrdVdate;
    }

    /**
     * 设置 产品起始日期
     * @param repPrdVdate 产品起始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepPrdVdate(Date repPrdVdate) {
        this.repPrdVdate = repPrdVdate;
    }

    /**
     * 取得 产品终止日期
     * @return 产品终止日期
     */
    public Date getRepPrdMdate() {
        return repPrdMdate;
    }

    /**
     * 设置 产品终止日期
     * @param repPrdMdate 产品终止日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepPrdMdate(Date repPrdMdate) {
        this.repPrdMdate = repPrdMdate;
    }

    /**
     * 取得 是否为结构化（分级）产品
     * @return 是否为结构化（分级）产品
     */
    public String getRepIsConstracte() {
        return repIsConstracte;
    }

    /**
     * 设置 是否为结构化（分级）产品
     * @param repIsConstracte 是否为结构化（分级）产品
     */
    public void setRepIsConstracte(String repIsConstracte) {
        this.repIsConstracte = repIsConstracte == null ? null : repIsConstracte.trim();
    }

    /**
     * 取得 层级类别
     * @return 层级类别
     */
    public String getRepGradeType() {
        return repGradeType;
    }

    /**
     * 设置 层级类别
     * @param repGradeType 层级类别
     */
    public void setRepGradeType(String repGradeType) {
        this.repGradeType = repGradeType == null ? null : repGradeType.trim();
    }

    /**
     * 取得 预计客户最低年收益率
     * @return 预计客户最低年收益率
     */
    public BigDecimal getRepExpMinYield() {
        return repExpMinYield;
    }

    /**
     * 设置 预计客户最低年收益率
     * @param repExpMinYield 预计客户最低年收益率
     */
    public void setRepExpMinYield(BigDecimal repExpMinYield) {
        this.repExpMinYield = repExpMinYield;
    }

    /**
     * 取得 预计客户最高年收益率
     * @return 预计客户最高年收益率
     */
    public BigDecimal getRepExpMaxYield() {
        return repExpMaxYield;
    }

    /**
     * 设置 预计客户最高年收益率
     * @param repExpMaxYield 预计客户最高年收益率
     */
    public void setRepExpMaxYield(BigDecimal repExpMaxYield) {
        this.repExpMaxYield = repExpMaxYield;
    }

    /**
     * 取得 预计客户平均年收益率
     * @return 预计客户平均年收益率
     */
    public BigDecimal getRepExpAvgYield() {
        return repExpAvgYield;
    }

    /**
     * 设置 预计客户平均年收益率
     * @param repExpAvgYield 预计客户平均年收益率
     */
    public void setRepExpAvgYield(BigDecimal repExpAvgYield) {
        this.repExpAvgYield = repExpAvgYield;
    }

    /**
     * 取得 收益率分档情况说明
     * @return 收益率分档情况说明
     */
    public String getRepYieldComment() {
        return repYieldComment;
    }

    /**
     * 设置 收益率分档情况说明
     * @param repYieldComment 收益率分档情况说明
     */
    public void setRepYieldComment(String repYieldComment) {
        this.repYieldComment = repYieldComment == null ? null : repYieldComment.trim();
    }

    /**
     * 取得 开放模式
     * @return 开放模式
     */
    public String getRepOpenMode() {
        return repOpenMode;
    }

    /**
     * 设置 开放模式
     * @param repOpenMode 开放模式
     */
    public void setRepOpenMode(String repOpenMode) {
        this.repOpenMode = repOpenMode == null ? null : repOpenMode.trim();
    }

    /**
     * 取得 规律开放周期
     * @return 规律开放周期
     */
    public String getRepOpenCircle() {
        return repOpenCircle;
    }

    /**
     * 设置 规律开放周期
     * @param repOpenCircle 规律开放周期
     */
    public void setRepOpenCircle(String repOpenCircle) {
        this.repOpenCircle = repOpenCircle == null ? null : repOpenCircle.trim();
    }

    /**
     * 取得 其他规律开放周期（天）
     * @return 其他规律开放周期（天）
     */
    public Short getRepOpenCircleDays() {
        return repOpenCircleDays;
    }

    /**
     * 设置 其他规律开放周期（天）
     * @param repOpenCircleDays 其他规律开放周期（天）
     */
    public void setRepOpenCircleDays(Short repOpenCircleDays) {
        this.repOpenCircleDays = repOpenCircleDays;
    }

    /**
     * 取得 无规律开放说明
     * @return 无规律开放说明
     */
    public String getRepRuleComment() {
        return repRuleComment;
    }

    /**
     * 设置 无规律开放说明
     * @param repRuleComment 无规律开放说明
     */
    public void setRepRuleComment(String repRuleComment) {
        this.repRuleComment = repRuleComment == null ? null : repRuleComment.trim();
    }

    /**
     * 取得 首次开放周期起始日
     * @return 首次开放周期起始日
     */
    public Date getRepFirstOpenDay() {
        return repFirstOpenDay;
    }

    /**
     * 设置 首次开放周期起始日
     * @param repFirstOpenDay 首次开放周期起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepFirstOpenDay(Date repFirstOpenDay) {
        this.repFirstOpenDay = repFirstOpenDay;
    }

    /**
     * 取得 节假日是否开放
     * @return 节假日是否开放
     */
    public String getRepHolidayOnFlag() {
        return repHolidayOnFlag;
    }

    /**
     * 设置 节假日是否开放
     * @param repHolidayOnFlag 节假日是否开放
     */
    public void setRepHolidayOnFlag(String repHolidayOnFlag) {
        this.repHolidayOnFlag = repHolidayOnFlag == null ? null : repHolidayOnFlag.trim();
    }

    /**
     * 取得 平均开放次数（年化）
     * @return 平均开放次数（年化）
     */
    public BigDecimal getRepAvgOpenCount() {
        return repAvgOpenCount;
    }

    /**
     * 设置 平均开放次数（年化）
     * @param repAvgOpenCount 平均开放次数（年化）
     */
    public void setRepAvgOpenCount(BigDecimal repAvgOpenCount) {
        this.repAvgOpenCount = repAvgOpenCount;
    }

    /**
     * 取得 开放期业务
     * @return 开放期业务
     */
    public String getRepOpenBuss() {
        return repOpenBuss;
    }

    /**
     * 设置 开放期业务
     * @param repOpenBuss 开放期业务
     */
    public void setRepOpenBuss(String repOpenBuss) {
        this.repOpenBuss = repOpenBuss == null ? null : repOpenBuss.trim();
    }

    /**
     * 取得 开放期业务说明
     * @return 开放期业务说明
     */
    public String getRepOpenBussRemark() {
        return repOpenBussRemark;
    }

    /**
     * 设置 开放期业务说明
     * @param repOpenBussRemark 开放期业务说明
     */
    public void setRepOpenBussRemark(String repOpenBussRemark) {
        this.repOpenBussRemark = repOpenBussRemark == null ? null : repOpenBussRemark.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }

    /**
     * 取得 发行登记日
     * @return 发行登记日
     */
    public Date getRepIssueDate() {
        return repIssueDate;
    }

    /**
     * 设置 发行登记日
     * @param repIssueDate 发行登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepIssueDate(Date repIssueDate) {
        this.repIssueDate = repIssueDate;
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getRepSendStatus() {
        return repSendStatus;
    }

    /**
     * 设置 报送状态
     * @param repSendStatus 报送状态
     */
    public void setRepSendStatus(String repSendStatus) {
        this.repSendStatus = repSendStatus == null ? null : repSendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRepStatusDate() {
        return repStatusDate;
    }

    /**
     * 设置 数据日期
     * @param repStatusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepStatusDate(Date repStatusDate) {
        this.repStatusDate = repStatusDate;
    }
}