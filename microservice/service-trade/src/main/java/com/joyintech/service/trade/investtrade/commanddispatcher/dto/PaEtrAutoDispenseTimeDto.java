package com.joyintech.service.trade.investtrade.commanddispatcher.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.util.Date;

public class PaEtrAutoDispenseTimeDto extends BaseEntity {
    /**
     * 开始时间
     */
    private Date vdate;

    /**
     * 结束时间
     */
    private Date mdate;

    /**
     * 备注
     */
    private String remark;

    private String distributeRule;
    private String distributeParameter;

    public String getDistributeRule() {
        return distributeRule;
    }

    public void setDistributeRule(String distributeRule) {
        this.distributeRule = distributeRule;
    }

    public String getDistributeParameter() {
        return distributeParameter;
    }

    public void setDistributeParameter(String distributeParameter) {
        this.distributeParameter = distributeParameter;
    }

    /**
     * 取得 开始时间
     * @return 开始时间
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 开始时间
     * @param vdate 开始时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 结束时间
     * @return 结束时间
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 结束时间
     * @param mdate 结束时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
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