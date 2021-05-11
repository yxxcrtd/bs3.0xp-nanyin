package com.joyintech.tams.svreport.chbreport.investreport.chbnetworthoperation.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * @ClassName ChbNetworthInfo
 * @Description: 净值披露报告查询及产品基本信息
 * @Author wangyong
 * @Date 2021-05-09
 * @Version V1.0
 **/
public class ChbNetWorthInfoDto extends BaseEntity {
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 净值日期
     */
    private String endDate;
    /**
     * 单位净值
     */
    private String prodValue;
    /**
     * 累计净值
     */
    private String unitValueTotal;
    /**
     * 资产净值
     */
    private String netAssetValue;

    /**
     * 客户类型
     */
    private String customerType;


    public  String getProdName() {
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProdValue() {
        return prodValue;
    }

    public void setProdValue(String prodValue) {
        this.prodValue = prodValue;
    }

    public String getUnitValueTotal() {
        return unitValueTotal;
    }

    public void setUnitValueTotal(String unitValueTotal) {
        this.unitValueTotal = unitValueTotal;
    }

    public String getNetAssetValue() {
        return netAssetValue;
    }

    public void setNetAssetValue(String netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
