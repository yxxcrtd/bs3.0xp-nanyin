package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RskRelatedParty extends BaseEntity {
    /**
     * 社会统一信用代码
     */
    private String instituteCode;

    /**
     * 关联方名称
     */
    private String instituteName;

    /**
     * 单笔交易额上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal limitAmt;

    /**
     * 取得 社会统一信用代码
     * @return 社会统一信用代码
     */
    public String getInstituteCode() {
        return instituteCode;
    }

    /**
     * 设置 社会统一信用代码
     * @param instituteCode 社会统一信用代码
     */
    public void setInstituteCode(String instituteCode) {
        this.instituteCode = instituteCode == null ? null : instituteCode.trim();
    }

    /**
     * 取得 关联方名称
     * @return 关联方名称
     */
    public String getInstituteName() {
        return instituteName;
    }

    /**
     * 设置 关联方名称
     * @param instituteName 关联方名称
     */
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName == null ? null : instituteName.trim();
    }

    /**
     * 取得 单笔交易额上限
     * @return 单笔交易额上限
     */
    public BigDecimal getLimitAmt() {
        return limitAmt;
    }

    /**
     * 设置 单笔交易额上限
     * @param limitAmt 单笔交易额上限
     */
    public void setLimitAmt(BigDecimal limitAmt) {
        this.limitAmt = limitAmt;
    }
}