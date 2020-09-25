package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class TaxSchaBokConfig extends BaseEntity {
    /**
     * 流水
     */
    private String seqNo;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 记账分类配置
     */
    private String bokConfigId;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 取得 流水
     * @return 流水
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水
     * @param seqNo 流水
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
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
     * 取得 记账分类配置
     * @return 记账分类配置
     */
    public String getBokConfigId() {
        return bokConfigId;
    }

    /**
     * 设置 记账分类配置
     * @param bokConfigId 记账分类配置
     */
    public void setBokConfigId(String bokConfigId) {
        this.bokConfigId = bokConfigId == null ? null : bokConfigId.trim();
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
}