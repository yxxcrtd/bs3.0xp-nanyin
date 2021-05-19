package com.joyintech.tams.svreport.chbreport.investreport.chbestablishoperation.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @ClassName ChbEstablishPrdInfo
 * @Description: 成立公告查询及产品基本信息
 * @Author wangyong
 * @Date 2021-05-11
 * @Version V1.0
 **/
public class ChbEstablishPrdInfo extends BaseEntity {

    //private static final long serialVersionUID = 109399923067388230L;
    /**
     * 产品全称
     */
    private String prodName;
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 理财登记编码
     */
    private String regCode;
    /**
     * 募集起始日
     */
    private String paisePstrDate;

    /**
     * 募集结束日
     */
    private String paisePendDate;

    /**
     * 产品起息日
     */
    private String vDate;

    /**
     * 托管账户名称
     */
    private String acctName;

    /**
     * 托管账户账号
     */
    private String busiAcctNo;

    /**
     * 实际募集金额
     */
    private String tradeCost;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 管理人全称
     */
    private String managerFullName;

    /**
     * 管理人简称
     */
    private String managerShortName;

    /**
     * 公告披露日期
     */
    private String revealDate;

    /**
     * 系统平台日
     */
    private String sysPlatformDate;

    /**
     * 客户类型
     */
    private String customerType;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getPaisePstrDate() {
        return paisePstrDate;
    }

    public void setPaisePstrDate(String paisePstrDate) {
        this.paisePstrDate = paisePstrDate;
    }

    public String getPaisePendDate() {
        return paisePendDate;
    }

    public void setPaisePendDate(String paisePendDate) {
        this.paisePendDate = paisePendDate;
    }

    public String getvDate() {
        return vDate;
    }

    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    public String getTradeCost() {
        return tradeCost;
    }

    public void setTradeCost(String tradeCost) {
        this.tradeCost = tradeCost;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getManagerFullName() {
        return managerFullName;
    }

    public void setManagerFullName(String managerFullName) {
        this.managerFullName = managerFullName;
    }

    public String getManagerShortName() {
        return managerShortName;
    }

    public void setManagerShortName(String managerShortName) {
        this.managerShortName = managerShortName;
    }

    public String getRevealDate() {
        return revealDate;
    }

    public void setRevealDate(String revealDate) {
        this.revealDate = revealDate;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getSysPlatformDate() {
        return sysPlatformDate;
    }

    public void setSysPlatformDate(String sysPlatformDate) {
        this.sysPlatformDate = sysPlatformDate;
    }
}
