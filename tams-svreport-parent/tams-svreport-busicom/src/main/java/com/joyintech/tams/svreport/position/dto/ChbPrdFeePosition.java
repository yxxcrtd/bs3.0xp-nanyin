package com.joyintech.tams.svreport.position.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述:CHB_PRD_FEE_POSITION表的实体类
 * @version 1.7
 * @author:  chenxc
 * @创建时间: 2019-05-22
 */
public class ChbPrdFeePosition {
    /**
     * 生成日
     */
    private Date cdate;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 费用一级类型
     */
    private String feeType;
    
    /**
     * 费用二级类型
     */
    private String feeSectype;
    
    /**
     * 费用金额
     */
    private BigDecimal amt;

    /**
     * 机构代码
     */
    private String orgCode;
    
    /**
     * "部门代码
     */
    private String deptCode;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;
    
    public ChbPrdFeePosition() {
        cdate= Calendar.getInstance().getTime();
        prodId="";
        ccy="CNY";
        feeType="";
        feeSectype="";
        amt=BigDecimal.ZERO;
    }
    
    public Date getCdate() {
        return cdate;
    }
    
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
    
    public String getProdId() {
        return prodId;
    }
    
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    
    public String getCcy() {
        return ccy;
    }
    
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    
    public String getFeeSectype() {
        return feeSectype;
    }
    
    public void setFeeSectype(String feeSectype) {
        this.feeSectype = feeSectype;
    }
    
    public BigDecimal getAmt() {
        return amt;
    }
    
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
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
    
    public String getCreateUser() {
        return createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
      
}