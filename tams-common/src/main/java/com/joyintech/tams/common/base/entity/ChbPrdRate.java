package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdRate extends BaseEntity {
    /**
     * 交易编号
     */
    private String seqno;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 母产品代码
     */
    private String fprodId;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 变动日期
     */
    private Date changeDate;

    /**
     * 计提结束日
     */
    private Date cashmdate;

    /**
     * 产品收益类型:01净值型,02预期收益型,03	货币型
     */
    private String profitType;

    /**
     * 变动类型
     */
    private String changeType;

    /**
     * 产品最新收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodYield;

    /**
     * 产品最新业绩基准
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodBenchmarkRate;

    /**
     * 单位累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitValueTotal;

    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitValue;

    /**
     * 七日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sevenDayRate;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 撤销日
     */
    private Date revDate;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 备注
     */
    private String remark;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 交易编号
     * @param seqno 交易编号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

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
     * 取得 母产品代码
     * @return 母产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 设置 母产品代码
     * @param fprodId 母产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

    /**
     * 取得 是否分层
     * @return 是否分层
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 是否分层
     * @param isLay 是否分层
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay == null ? null : isLay.trim();
    }

    /**
     * 取得 变动日期
     * @return 变动日期
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * 设置 变动日期
     * @param changeDate 变动日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * 取得 计提结束日
     * @return 计提结束日
     */
    public Date getCashmdate() {
        return cashmdate;
    }

    /**
     * 设置 计提结束日
     * @param cashmdate 计提结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCashmdate(Date cashmdate) {
        this.cashmdate = cashmdate;
    }

    /**
     * 取得 产品收益类型:01净值型,02预期收益型,03	货币型
     * @return 产品收益类型:01净值型,02预期收益型,03	货币型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 产品收益类型:01净值型,02预期收益型,03	货币型
     * @param profitType 产品收益类型:01净值型,02预期收益型,03	货币型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 变动类型
     * @return 变动类型
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * 设置 变动类型
     * @param changeType 变动类型
     */
    public void setChangeType(String changeType) {
        this.changeType = changeType == null ? null : changeType.trim();
    }

    /**
     * 取得 产品最新收益率
     * @return 产品最新收益率
     */
    public BigDecimal getProdYield() {
        return prodYield;
    }

    /**
     * 设置 产品最新收益率
     * @param prodYield 产品最新收益率
     */
    public void setProdYield(BigDecimal prodYield) {
        this.prodYield = prodYield;
    }

    /**
     * 取得 产品最新业绩基准
     * @return 产品最新业绩基准
     */
    public BigDecimal getProdBenchmarkRate() {
        return prodBenchmarkRate;
    }

    /**
     * 设置 产品最新业绩基准
     * @param prodBenchmarkRate 产品最新业绩基准
     */
    public void setProdBenchmarkRate(BigDecimal prodBenchmarkRate) {
        this.prodBenchmarkRate = prodBenchmarkRate;
    }

    /**
     * 取得 单位累计净值
     * @return 单位累计净值
     */
    public BigDecimal getUnitValueTotal() {
        return unitValueTotal;
    }

    /**
     * 设置 单位累计净值
     * @param unitValueTotal 单位累计净值
     */
    public void setUnitValueTotal(BigDecimal unitValueTotal) {
        this.unitValueTotal = unitValueTotal;
    }

    /**
     * 取得 单位净值
     * @return 单位净值
     */
    public BigDecimal getUnitValue() {
        return unitValue;
    }

    /**
     * 设置 单位净值
     * @param unitValue 单位净值
     */
    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * 取得 七日年化收益率
     * @return 七日年化收益率
     */
    public BigDecimal getSevenDayRate() {
        return sevenDayRate;
    }

    /**
     * 设置 七日年化收益率
     * @param sevenDayRate 七日年化收益率
     */
    public void setSevenDayRate(BigDecimal sevenDayRate) {
        this.sevenDayRate = sevenDayRate;
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
     * 取得 撤销日
     * @return 撤销日
     */
    public Date getRevDate() {
        return revDate;
    }

    /**
     * 设置 撤销日
     * @param revDate 撤销日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevDate(Date revDate) {
        this.revDate = revDate;
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
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }
}