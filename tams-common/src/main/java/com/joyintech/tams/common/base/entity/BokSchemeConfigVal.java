package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokSchemeConfigVal extends BaseEntity {
    /**
     * 流水号
     */
    private String seqId;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 配置项代码
     */
    private String configId;

    /**
     * 账套代码
     */
    private String configValue;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqId() {
        return seqId;
    }

    /**
     * 设置 流水号
     * @param seqId 流水号
     */
    public void setSeqId(String seqId) {
        this.seqId = seqId == null ? null : seqId.trim();
    }

    /**
     * 取得 方案代码
     * @return 方案代码
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * @param schemeId 方案代码
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 配置项代码
     * @return 配置项代码
     */
    public String getConfigId() {
        return configId;
    }

    /**
     * 设置 配置项代码
     * @param configId 配置项代码
     */
    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * 设置 账套代码
     * @param configValue 账套代码
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
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