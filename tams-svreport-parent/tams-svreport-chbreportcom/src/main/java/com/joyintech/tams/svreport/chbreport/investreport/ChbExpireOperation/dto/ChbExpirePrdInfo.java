package com.joyintech.tams.svreport.chbreport.investreport.ChbExpireOperation.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * @ClassName ChbEstablishPrdInfo
 * @Description: 成立公告查询及产品基本信息
 * @Author wangyong
 * @Date 2021-05-11
 * @Version V1.0
 **/
public class ChbExpirePrdInfo extends BaseEntity {

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
     * 投资及收益币种
     */
    private String cashPrinCcy;
    /**
     * 产品成立日
     */
    private String vDate;

    /**
     * 产品到期日
     */
    private String endDate;

    /**
     * 实际到期日
     */
    private String realEndDate;

    /**
     * 系统平台日
     */
    private String sysPlatformDate;

    /**
     * 固定管理费率
     */
    private String investFeeRate;

    /**
     * 托管费率
     */
    private String truFeeRate;

    /**
     * 销售手续费率
     */
    private String saleFeeRate;

    /**
     * 到期日份额净值/每万份净收益
     */
    private String qtyValue;

    /**
     * 到期日份额累计净值
     */
    private String totalQtyValue;

    /**
     * 管理人全称
     */
    private String managerFullName;

    /**
     * 管理人简称
     */
    private String managerShortName;

    /**
     * 报表日期
     */
    private String mDate;

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

    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy;
    }

    public String getvDate() {
        return vDate;
    }

    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInvestFeeRate() {
        return investFeeRate;
    }

    public void setInvestFeeRate(String investFeeRate) {
        this.investFeeRate = investFeeRate;
    }

    public String getTruFeeRate() {
        return truFeeRate;
    }

    public void setTruFeeRate(String truFeeRate) {
        this.truFeeRate = truFeeRate;
    }

    public String getSaleFeeRate() {
        return saleFeeRate;
    }

    public void setSaleFeeRate(String saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    public String getQtyValue() {
        return qtyValue;
    }

    public void setQtyValue(String qtyValue) {
        this.qtyValue = qtyValue;
    }

    public String getTotalQtyValue() {
        return totalQtyValue;
    }

    public void setTotalQtyValue(String totalQtyValue) {
        this.totalQtyValue = totalQtyValue;
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

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(String realEndDate) {
        this.realEndDate = realEndDate;
    }

    public String getSysPlatformDate() {
        return sysPlatformDate;
    }

    public void setSysPlatformDate(String sysPlatformDate) {
        this.sysPlatformDate = sysPlatformDate;
    }
}
