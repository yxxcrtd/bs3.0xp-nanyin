package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class PrdPlanInfo extends BaseEntity {
    /**
     * 产品规划编号
     */
    private String prdPlanCode;

    /**
     * 基准日
     */
    private Date benchDate;

    /**
     * 开始日
     */
    private Date vdate;

    /**
     * 结束日
     */
    private Date edate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 产品规划编号
     * @return 产品规划编号
     */
    public String getPrdPlanCode() {
        return prdPlanCode;
    }

    /**
     * 设置 产品规划编号
     * @param prdPlanCode 产品规划编号
     */
    public void setPrdPlanCode(String prdPlanCode) {
        this.prdPlanCode = prdPlanCode == null ? null : prdPlanCode.trim();
    }

    /**
     * 取得 基准日
     * @return 基准日
     */
    public Date getBenchDate() {
        return benchDate;
    }

    /**
     * 设置 基准日
     * @param benchDate 基准日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBenchDate(Date benchDate) {
        this.benchDate = benchDate;
    }

    /**
     * 取得 开始日
     * @return 开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 开始日
     * @param vdate 开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 结束日
     * @return 结束日
     */
    public Date getEdate() {
        return edate;
    }

    /**
     * 设置 结束日
     * @param edate 结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}