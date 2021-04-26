package com.joyintech.tams.svreport.chbreport.prdprofit.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * 理财产品资产负债表导出Dto
 *
 * @author WanShiwei
 * @version 2.1.6 2021年4月20日
 * @see PrdProfitQuery001Dto
 * @since 2.1.6
 */
public class PrdProfitQuery001Dto extends BaseEntity {

    private static final long serialVersionUID = -1493892052117293624L;

    /**
     * 项目
     */
    private String project;

    /**
     * 本期
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal thisTermAmt = BigDecimal.ZERO;

    /**
     * 本年累计
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal lastTermAmt = BigDecimal.ZERO;

    public PrdProfitQuery001Dto() {
        super();
    }

    public PrdProfitQuery001Dto(String project) {
        super();
        this.project = project;
    }

    public PrdProfitQuery001Dto(String project, BigDecimal thisTermAmt, BigDecimal lastTermAmt) {
        super();
        this.project = project;
        this.thisTermAmt = thisTermAmt;
        this.lastTermAmt = lastTermAmt;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public BigDecimal getThisTermAmt() {
        return thisTermAmt;
    }

    public void setThisTermAmt(BigDecimal thisTermAmt) {
        this.thisTermAmt = thisTermAmt;
    }

    public BigDecimal getLastTermAmt() {
        return lastTermAmt;
    }

    public void setLastTermAmt(BigDecimal lastTermAmt) {
        this.lastTermAmt = lastTermAmt;
    }
}

