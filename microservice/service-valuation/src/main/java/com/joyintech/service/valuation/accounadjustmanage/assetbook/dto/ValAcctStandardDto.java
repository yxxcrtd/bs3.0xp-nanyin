package com.joyintech.service.valuation.accounadjustmanage.assetbook.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 会计准则
 * 
 * @author huangjian
 * @version 1.0 2018年5月28日
 * @see ValAcctStandardDto
 * @since 1.0
 */
public class ValAcctStandardDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 会计准则代码
     */
    private String acctStandardId;

    /**
     * 会计准则名称
     */
    private String acctStandardName;

    /**
     * 取得 会计准则代码
     * 
     * @return 会计准则代码
     */
    public String getAcctStandardId() {
        return acctStandardId;
    }

    /**
     * 设置 会计准则代码
     * 
     * @param acctStandardId 会计准则代码
     */
    public void setAcctStandardId(String acctStandardId) {
        this.acctStandardId = acctStandardId == null ? null : acctStandardId.trim();
    }

    /**
     * 取得 会计准则名称
     * 
     * @return 会计准则名称
     */
    public String getAcctStandardName() {
        return acctStandardName;
    }

    /**
     * 设置 会计准则名称
     * 
     * @param acctStandardName 会计准则名称
     */
    public void setAcctStandardName(String acctStandardName) {
        this.acctStandardName = acctStandardName == null ? null : acctStandardName.trim();
    }
}