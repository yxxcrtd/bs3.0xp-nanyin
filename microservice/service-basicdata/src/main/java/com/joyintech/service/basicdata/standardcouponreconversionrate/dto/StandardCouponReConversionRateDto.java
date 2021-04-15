package com.joyintech.service.basicdata.standardcouponreconversionrate.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.List;

public class StandardCouponReConversionRateDto extends BaseEntity {

    private transient List<StandardCouponReConversionRateDto> selectedItem;

    private String brokerId;//券商

    private String brokerName; //券商

    private String accountType;//资金账户

    private String accountTypeStr;

    private String tandardRate;//标准券再折算率

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getTandardRate() {
        return tandardRate;
    }

    public void setTandardRate(String tandardRate) {
        this.tandardRate = tandardRate;
    }


    public List<StandardCouponReConversionRateDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<StandardCouponReConversionRateDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getAccountTypeStr() {
        return accountTypeStr;
    }

    public void setAccountTypeStr(String accountTypeStr) {
        this.accountTypeStr = accountTypeStr;
    }
}
