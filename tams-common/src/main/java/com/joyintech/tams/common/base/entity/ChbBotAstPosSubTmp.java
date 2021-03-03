package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbBotAstPosSubTmp extends BaseEntity {
    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 持仓日期
     */
    private Date posDate;

    /**
     * 资管计划代码(对应资产管理计划行内资产/负债编码)
     */
    private String planAssetId;

    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 资管计划当前总发行数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalNum;

    /**
     * 资管计划当前总折算人民币金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;

    /**
     * 资管计划折算人民币未投资头寸（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal uninvestCash;

    /**
     * 底层资产持仓数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal posNum;

    /**
     * 底层资产折算人民币币值（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal posAmt;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据日期
     */
    private Date rowDate;

    /**
     * 数据来源
     */
    private String dataSource;

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
     * 取得 持仓日期
     * @return 持仓日期
     */
    public Date getPosDate() {
        return posDate;
    }

    /**
     * 设置 持仓日期
     * @param posDate 持仓日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPosDate(Date posDate) {
        this.posDate = posDate;
    }

    /**
     * 取得 资管计划代码(对应资产管理计划行内资产/负债编码)
     * @return 资管计划代码(对应资产管理计划行内资产/负债编码)
     */
    public String getPlanAssetId() {
        return planAssetId;
    }

    /**
     * 设置 资管计划代码(对应资产管理计划行内资产/负债编码)
     * @param planAssetId 资管计划代码(对应资产管理计划行内资产/负债编码)
     */
    public void setPlanAssetId(String planAssetId) {
        this.planAssetId = planAssetId == null ? null : planAssetId.trim();
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
     * 取得 资管计划当前总发行数量
     * @return 资管计划当前总发行数量
     */
    public BigDecimal getTotalNum() {
        return totalNum;
    }

    /**
     * 设置 资管计划当前总发行数量
     * @param totalNum 资管计划当前总发行数量
     */
    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 取得 资管计划当前总折算人民币金额（元）
     * @return 资管计划当前总折算人民币金额（元）
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    /**
     * 设置 资管计划当前总折算人民币金额（元）
     * @param issueAmt 资管计划当前总折算人民币金额（元）
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * 取得 资管计划折算人民币未投资头寸（元）
     * @return 资管计划折算人民币未投资头寸（元）
     */
    public BigDecimal getUninvestCash() {
        return uninvestCash;
    }

    /**
     * 设置 资管计划折算人民币未投资头寸（元）
     * @param uninvestCash 资管计划折算人民币未投资头寸（元）
     */
    public void setUninvestCash(BigDecimal uninvestCash) {
        this.uninvestCash = uninvestCash;
    }

    /**
     * 取得 底层资产持仓数量
     * @return 底层资产持仓数量
     */
    public BigDecimal getPosNum() {
        return posNum;
    }

    /**
     * 设置 底层资产持仓数量
     * @param posNum 底层资产持仓数量
     */
    public void setPosNum(BigDecimal posNum) {
        this.posNum = posNum;
    }

    /**
     * 取得 底层资产折算人民币币值（元）
     * @return 底层资产折算人民币币值（元）
     */
    public BigDecimal getPosAmt() {
        return posAmt;
    }

    /**
     * 设置 底层资产折算人民币币值（元）
     * @param posAmt 底层资产折算人民币币值（元）
     */
    public void setPosAmt(BigDecimal posAmt) {
        this.posAmt = posAmt;
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRowDate() {
        return rowDate;
    }

    /**
     * 设置 数据日期
     * @param rowDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRowDate(Date rowDate) {
        this.rowDate = rowDate;
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }
}