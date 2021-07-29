package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class RskRelatedParty extends BaseEntity {
	/**
	 * 关联方编号
	 */
	private String seqno;
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
     * 生效日
     */
    private Date vdate;

    /**
     * 截止日
     */
    private Date mdate;

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

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 生效日
     * @param vdate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 截止日
     * @return 截止日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 截止日
     * @param mdate 截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
    /**
     * 取得 关联方编号
     * @return 关联方编号
     */
	public String getSeqno() {
		return seqno;
	}
	/**
     * 设置 关联方编号
     * @param seqno 关联方编号
     */
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
}