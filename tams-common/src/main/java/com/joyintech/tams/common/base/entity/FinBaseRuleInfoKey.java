package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinBaseRuleInfoKey extends BaseEntity {
    /**
     * 业绩基准代码，同金融产品代码
     */
    private String baseRuleId;

    /**
     * 生效日期
     */
    private Date effDate;

    /**
     * 取得 业绩基准代码，同金融产品代码
     * @return 业绩基准代码，同金融产品代码
     */
    public String getBaseRuleId() {
        return baseRuleId;
    }

    /**
     * 设置 业绩基准代码，同金融产品代码
     * @param baseRuleId 业绩基准代码，同金融产品代码
     */
    public void setBaseRuleId(String baseRuleId) {
        this.baseRuleId = baseRuleId == null ? null : baseRuleId.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日期
     * @param effDate 生效日期
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
}