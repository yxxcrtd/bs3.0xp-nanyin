package com.joyintech.tams.invest.common.dto;

/**
 * @auth 何阳阳
 * @date 2021/8/31
 */
public class ChbCheckRuleDto {
    private String checkmethod;
    private String srcproperty;
    private String remark;

    public String getCheckmethod() {
        return checkmethod;
    }

    public void setCheckmethod(String checkmethod) {
        this.checkmethod = checkmethod;
    }

    public String getSrcproperty() {
        return srcproperty;
    }

    public void setSrcproperty(String srcproperty) {
        this.srcproperty = srcproperty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
