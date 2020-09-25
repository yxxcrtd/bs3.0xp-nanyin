package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokValRuleTypedetail extends BaseEntity {
    /**
     * 方案明细代码
     */
    private String ruleDetailId;

    /**
     * 方案代码
     */
    private String ruleId;

    /**
     * 证券品种
     */
    private String secType;

    /**
     * 证券细分类
     */
    private String secDetailType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 开始日

     */
    private Date vdate;

    /**
     * 结束日

     */
    private Date mdate;

    /**
     * 取得 方案明细代码
     * @return 方案明细代码
     */
    public String getRuleDetailId() {
        return ruleDetailId;
    }

    /**
     * 设置 方案明细代码
     * @param ruleDetailId 方案明细代码
     */
    public void setRuleDetailId(String ruleDetailId) {
        this.ruleDetailId = ruleDetailId == null ? null : ruleDetailId.trim();
    }

    /**
     * 取得 方案代码
     * @return 方案代码
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 方案代码
     * @param ruleId 方案代码
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    /**
     * 取得 证券品种
     * @return 证券品种
     */
    public String getSecType() {
        return secType;
    }

    /**
     * 设置 证券品种
     * @param secType 证券品种
     */
    public void setSecType(String secType) {
        this.secType = secType == null ? null : secType.trim();
    }

    /**
     * 取得 证券细分类
     * @return 证券细分类
     */
    public String getSecDetailType() {
        return secDetailType;
    }

    /**
     * 设置 证券细分类
     * @param secDetailType 证券细分类
     */
    public void setSecDetailType(String secDetailType) {
        this.secDetailType = secDetailType == null ? null : secDetailType.trim();
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
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
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 结束日

     * @param mdate 结束日

     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
}