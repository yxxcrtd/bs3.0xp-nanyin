package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class EtrAutoDispenseRule extends BaseEntity {
    /**
     * 规则代码
     */
    private String ruleId;

    /**
     * 开始时间
     */
    private Date vdate;

    /**
     * 结束时间
     */
    private Date mdate;

    /**
     * 分发规则
     */
    private String dispenseRule;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 规则代码
     * @return 规则代码
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 规则代码
     * @param ruleId 规则代码
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
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
     * 取得 分发规则
     * @return 分发规则
     */
    public String getDispenseRule() {
        return dispenseRule;
    }

    /**
     * 设置 分发规则
     * @param dispenseRule 分发规则
     */
    public void setDispenseRule(String dispenseRule) {
        this.dispenseRule = dispenseRule == null ? null : dispenseRule.trim();
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