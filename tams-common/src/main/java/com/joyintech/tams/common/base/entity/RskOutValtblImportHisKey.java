package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class RskOutValtblImportHisKey extends BaseEntity {

    /**文件名*/
    private String fileName;

    /**委外组合名称*/
    private String portfolioName;

    /**估值日期*/
    private Date valdate;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public Date getValdate() {
        return valdate;
    }

    public void setValdate(Date valdate) {
        this.valdate = valdate;
    }
}