package com.joyintech.tams.disclosure.busicommon.rptdata.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;

public class NostdReportDto{
    private String prodId;

    /**
     * 产品名称：子产品名称、母产品名称、普通产品名称
     */
    private String prodName;

    private String prodRe;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdRe() {
        return prodRe;
    }

    public void setProdRe(String prodRe) {
        this.prodRe = prodRe;
    }
}
