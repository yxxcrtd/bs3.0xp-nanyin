package com.joyintech.tams.svreport.chb.monthreportdetail.dto;

import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;

public class MstCustomerInfoDetail extends MstCustomerInfoAdd {

    private String customerName;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
