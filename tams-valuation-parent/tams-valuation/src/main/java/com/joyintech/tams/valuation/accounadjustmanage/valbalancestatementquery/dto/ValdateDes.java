package com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.dto;

import com.joyintech.framework.base.BaseEntity;

public class ValdateDes extends BaseEntity {

    private static final long serialVersionUID = 5940629660838640921L;

    private String valDates;

    private Integer desDate;

    public String getValDates() {
        return valDates;
    }

    public void setValDates(String valDates) {
        this.valDates = valDates;
    }

    public Integer getDesDate() {
        return desDate;
    }

    public void setDesDate(Integer desDate) {
        this.desDate = desDate;
    }
}
