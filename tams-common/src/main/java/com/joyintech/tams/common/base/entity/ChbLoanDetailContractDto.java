package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_LOAN_DETAIL_CONTRACT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2020-02-13
 */
public class ChbLoanDetailContractDto extends BaseEntity{
    /**
     * 主键
     */
    private String prodId;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 原始合同币种代码
     */
    private String origCcy;

    /**
     * 原始合同金额
     */
    private BigDecimal origContractAmount;

    /**
     * 原始合同金额折人民币
     */
    private BigDecimal origContractAmountCny;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 部门代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据状态 A-待处理 E-有效 D-删除
     */
    private String status;

    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 交割日期 DB接入算汇率用
     */
    private Date settleDate;

    public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	/**
     * 主键
     * @return PROD_ID 主键
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 主键
     * @param prodId 主键
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 资产代码
     * @return ASSET_CODE 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 原始合同币种代码
     * @return ORIG_CCY 原始合同币种代码
     */
    public String getOrigCcy() {
        return origCcy;
    }

    /**
     * 原始合同币种代码
     * @param origCcy 原始合同币种代码
     */
    public void setOrigCcy(String origCcy) {
        this.origCcy = origCcy == null ? null : origCcy.trim();
    }

    /**
     * 原始合同金额
     * @return ORIG_CONTRACT_AMOUNT 原始合同金额
     */
    public BigDecimal getOrigContractAmount() {
        return origContractAmount;
    }

    /**
     * 原始合同金额
     * @param origContractAmount 原始合同金额
     */
    public void setOrigContractAmount(BigDecimal origContractAmount) {
        this.origContractAmount = origContractAmount;
    }

    /**
     * 原始合同金额折人民币
     * @return ORIG_CONTRACT_AMOUNT_CNY 原始合同金额折人民币
     */
    public BigDecimal getOrigContractAmountCny() {
        return origContractAmountCny;
    }

    /**
     * 原始合同金额折人民币
     * @param origContractAmountCny 原始合同金额折人民币
     */
    public void setOrigContractAmountCny(BigDecimal origContractAmountCny) {
        this.origContractAmountCny = origContractAmountCny;
    }

    /**
     * 创建用户
     * @return CREATE_USER 创建用户
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建用户
     * @param createUser 创建用户
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
     * 更新用户
     * @return UPDATE_USER 更新用户
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新用户
     * @param updateUser 更新用户
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
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 部门代码
     * @return ORG_CODE 部门代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 部门代码
     * @param orgCode 部门代码
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
     * 数据状态 A-待处理 E-有效 D-删除
     * @return STATUS 数据状态 A-待处理 E-有效 D-删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态 A-待处理 E-有效 D-删除
     * @param status 数据状态 A-待处理 E-有效 D-删除
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
}