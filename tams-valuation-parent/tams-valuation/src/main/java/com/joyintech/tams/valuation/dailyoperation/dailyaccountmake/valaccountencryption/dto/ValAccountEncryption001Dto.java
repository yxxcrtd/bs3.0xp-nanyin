package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccountencryption.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

@PageId("ValAccountEncryption001")
public class ValAccountEncryption001Dto extends BaseEntity {
    // 业务明细代码
    private String busiDetailId;
    // 业务明细类型
    private String busiDetailType;
    // 理财子产品起息日
    private Date vdate;
    // 收益类型
    private String profitType;
    // 投资目的
    private String invAim;
    // 币种
    private String ccy;
    // 账务明细代码
    private String bokDetailId;

    public String getBusiDetailId() {
        return busiDetailId;
    }

    public void setBusiDetailId(String busiDetailId) {
        this.busiDetailId = busiDetailId;
    }

    public String getBusiDetailType() {
        return busiDetailType;
    }

    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }
}
