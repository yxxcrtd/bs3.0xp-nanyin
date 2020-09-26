/*
 * 文件名：ProductRegist.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zyf
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.businessdetailed.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 产品登记编码Entity
 * 〈功能详细描述〉
 * @author zyf
 * @version 1.0 2017年9月14日
 * @see ProductRegist
 * @since 1.0
 */
public class ProductRegist implements Serializable{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8834763519869419729L;

    /**
     * 产品代码
     */
    private String prodId;
    
    /**
     * 机构代码
     */
    private String orgCode;
    
    /**
     * 产品名称
     */
    private String prodName;
    
    /**
     * 产品登记编码
     */
    private String regCode;
    
    /**
     * 起息日
     */
    private Date vDate;
    
    /**
     * 到期日
     */
    private Date mDate;
    
    /**
     * 部门代码
     */
    private String deptCode;
    
    /**
     * 投资者类型
     */
    private String investor;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    
    /**
     * 创建人
     */
    private String createUser;
    
    /**
     * 创建时间
     */
    private Timestamp createTime;

    
    /**
     * 获取 prodId
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    
    /**
     * 设置 prodId
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    
    /**
     * 获取 orgCode
     * @return orgCode.
     */
    public String getOrgCode() {
        return orgCode;
    }

    
    /**
     * 设置 orgCode
     * @param orgCode orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    
    /**
     * 获取 prodName
     * @return prodName.
     */
    public String getProdName() {
        return prodName;
    }

    
    /**
     * 设置 prodName
     * @param prodName prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    
    /**
     * 获取 regCode
     * @return regCode.
     */
    public String getRegCode() {
        return regCode;
    }

    
    /**
     * 设置 regCode
     * @param regCode regCode
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    
    /**
     * 获取 vDate
     * @return vDate.
     */
    public Date getvDate() {
        return vDate;
    }

    
    /**
     * 设置 vDate
     * @param vDate vDate
     */
    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    
    /**
     * 获取 mDate
     * @return mDate.
     */
    public Date getmDate() {
        return mDate;
    }

    
    /**
     * 设置 mDate
     * @param mDate mDate
     */
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    
    /**
     * 获取 deptCode
     * @return deptCode.
     */
    public String getDeptCode() {
        return deptCode;
    }

    
    /**
     * 设置 deptCode
     * @param deptCode deptCode
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    
    /**
     * 获取 investor
     * @return investor.
     */
    public String getInvestor() {
        return investor;
    }

    
    /**
     * 设置 investor
     * @param investor investor
     */
    public void setInvestor(String investor) {
        this.investor = investor;
    }

    
    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    /**
     * 获取 updateUser
     * @return updateUser.
     */
    public String getUpdateUser() {
        return updateUser;
    }

    
    /**
     * 设置 updateUser
     * @param updateUser updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    
    /**
     * 获取 updateTime
     * @return updateTime.
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    
    /**
     * 设置 updateTime
     * @param updateTime updateTime
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    
    /**
     * 获取 createUser
     * @return createUser.
     */
    public String getCreateUser() {
        return createUser;
    }

    
    /**
     * 设置 createUser
     * @param createUser createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    
    /**
     * 获取 createTime
     * @return createTime.
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    
    /**
     * 设置 createTime
     * @param createTime createTime
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ProductRegist [prodId=" + prodId + ", orgCode=" + orgCode + ", prodName="
               + prodName + ", regCode=" + regCode + ", vDate=" + vDate + ", mDate=" + mDate
               + ", deptCode=" + deptCode + ", investor=" + investor + ", remark=" + remark
               + ", updateUser=" + updateUser + ", updateTime=" + updateTime + ", createUser="
               + createUser + ", createTime=" + createTime + "]";
    }
    
    
    
}
