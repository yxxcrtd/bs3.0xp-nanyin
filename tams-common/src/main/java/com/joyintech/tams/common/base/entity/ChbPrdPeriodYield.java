package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_PRD_PERIODYIELD表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class ChbPrdPeriodYield extends BaseEntity{
    /**
     * 交易编号
     */
    private String seqno;

    /**
     * 父产品代码
     */
    private String fprodId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 变动日期
     */
    private Date changeDate;

    /**
     * 变动类型 01:净值变动 02:收益率变动
     */
    private String changType;

    /**
     * 变动值
     */
    private BigDecimal prodValue;

    /**
     * 单位累计净值
     */
    private BigDecimal unitValueTotal;

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
    
    public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	/**
     * 交易编号
     * @return SEQNO 交易编号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 交易编号
     * @param seqno 交易编号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 父产品代码
     * @return FPROD_ID 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 父产品代码
     * @param fprodId 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

    /**
     * 产品代码
     * @return PROD_ID 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 变动日期
     * @return CHANGE_DATE 变动日期
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * 变动日期
     * @param changeDate 变动日期
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * 变动类型 01:净值变动 02:收益率变动
     * @return CHANG_TYPE 变动类型 01:净值变动 02:收益率变动
     */
    public String getChangType() {
        return changType;
    }

    /**
     * 变动类型 01:净值变动 02:收益率变动
     * @param changType 变动类型 01:净值变动 02:收益率变动
     */
    public void setChangType(String changType) {
        this.changType = changType == null ? null : changType.trim();
    }

    /**
     * 变动值
     * @return PROD_VALUE 变动值
     */
    public BigDecimal getProdValue() {
        return prodValue;
    }

    /**
     * 变动值
     * @param prodValue 变动值
     */
    public void setProdValue(BigDecimal prodValue) {
        this.prodValue = prodValue;
    }
    

    public BigDecimal getUnitValueTotal() {
		return unitValueTotal;
	}

	public void setUnitValueTotal(BigDecimal unitValueTotal) {
		this.unitValueTotal = unitValueTotal;
	}

	/**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 部门代码
     * @return DEPT_CODE 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 数据状态
     * @return STATUS 数据状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态
     * @param status 数据状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 撤销日
     * @return REV_DATE 撤销日
     */
    public Date getRevDate() {
        return revDate;
    }

    /**
     * 撤销日
     * @param revDate 撤销日
     */
    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    /**
     * 数据来源
     * @return DATA_SOURCE 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}