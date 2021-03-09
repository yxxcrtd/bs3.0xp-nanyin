package com.joyintech.tams.svreport.interf.chb.investmentsinfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_PRD_RATE表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class ChbProdInfoRate extends BaseEntity{
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
     * 产品收益类型:01净值型,02预期收益型,03	货币型
     */
    private String profitType;
    /**
     * 变动类型
     */
    private String changType;
    /**
     * 产品最新收益率
     */
    private BigDecimal prodYield;
    /**
     * 产品最新业绩基准
     */
    private BigDecimal prodBenchmarkRate;

    /**
     * 单位累计净值
     */
    private BigDecimal unitValueTotal;
    /**
     * 单位净值
     */
    private BigDecimal unitValue;
    /**
     * 七日年化收益率
     */
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
     * 数据状态
     */
    private String status;

    /**
     * 撤销日
     */
    private Date revDate;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 币种
     */
    private String ccy;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public BigDecimal getProdYield() {
        return prodYield;
    }

    public void setProdYield(BigDecimal prodYield) {
        this.prodYield = prodYield;
    }

    public BigDecimal getProdBenchmarkRate() {
        return prodBenchmarkRate;
    }

    public void setProdBenchmarkRate(BigDecimal prodBenchmarkRate) {
        this.prodBenchmarkRate = prodBenchmarkRate;
    }

    public BigDecimal getUnitValueTotal() {
        return unitValueTotal;
    }

    public void setUnitValueTotal(BigDecimal unitValueTotal) {
        this.unitValueTotal = unitValueTotal;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    public BigDecimal getSevenDayRate() {
        return sevenDayRate;
    }

    public void setSevenDayRate(BigDecimal sevenDayRate) {
        this.sevenDayRate = sevenDayRate;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getChangType() {
        return changType;
    }

    public void setChangType(String changType) {
        this.changType = changType;
    }

    public String getFprodId() {
        return fprodId;
    }

    public void setFprodId(String fprodId) {
        this.fprodId = fprodId;
    }

    public String getIsLay() {
        return isLay;
    }

    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }
}