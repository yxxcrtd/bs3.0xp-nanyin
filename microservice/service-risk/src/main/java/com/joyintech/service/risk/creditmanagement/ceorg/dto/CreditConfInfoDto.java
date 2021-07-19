package com.joyintech.service.risk.creditmanagement.ceorg.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.CreditConfInfo;

public class CreditConfInfoDto extends CreditConfInfo{
	
	private String seqno;
	
    /**
     * 授信分项代码
     */
    private String creditMstId;

    /**
     * 授信标签代码
     */
    private String creditTagId;

    /**
     * 最大投资期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal term;

    /**
     * 授信使用条件
     */
    private String creditCondition;

    /**
     * 备注
     */
    private String remark;

    /**
     * 授信额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditAmt;
    
    /**
     * 取得 授信额度
     * @return 授信额度
     */
    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    /**
     * 设置 授信额度
     * @param creditAmt 授信额度
     */
    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }
    /**
     * 取得 授信分项代码
     * @return 授信分项代码
     */
    public String getCreditMstId() {
        return creditMstId;
    }

    /**
     * 设置 授信分项代码
     * @param creditMstId 授信分项代码
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId == null ? null : creditMstId.trim();
    }

    /**
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    public String getCreditTagId() {
        return creditTagId;
    }

    /**
     * 设置 授信标签代码
     * @param creditTagId 授信标签代码
     */
    public void setCreditTagId(String creditTagId) {
        this.creditTagId = creditTagId == null ? null : creditTagId.trim();
    }

    /**
     * 取得 最大投资期限
     * @return 最大投资期限
     */
    public BigDecimal getTerm() {
        return term;
    }

    /**
     * 设置 最大投资期限
     * @param term 最大投资期限
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 取得 授信使用条件
     * @return 授信使用条件
     */
    public String getCreditCondition() {
        return creditCondition;
    }

    /**
     * 设置 授信使用条件
     * @param creditCondition 授信使用条件
     */
    public void setCreditCondition(String creditCondition) {
        this.creditCondition = creditCondition == null ? null : creditCondition.trim();
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
    /**
     * 取得 授信额度代码
     * @return 授信额度代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 授信额度代码
     * @param seqno 授信额度代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }
}