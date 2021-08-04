package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class RepAccessList extends BaseEntity {
    /**
     * 白名单代码
     */
    private String listId;

    /**
     * 回购对手类型
     */
    private String listType;

    /**
     * 客户代码
     */
    private String customerId;

    /**
     * 期限
     */
    private String term;

    /**
     * 额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 批次号
     */
    private String seqId;

    /**
     * 取得 白名单代码
     * @return 白名单代码
     */
    public String getListId() {
        return listId;
    }

    /**
     * 设置 白名单代码
     * @param listId 白名单代码
     */
    public void setListId(String listId) {
        this.listId = listId == null ? null : listId.trim();
    }

    /**
     * 取得 回购对手类型
     * @return 回购对手类型
     */
    public String getListType() {
        return listType;
    }

    /**
     * 设置 回购对手类型
     * @param listType 回购对手类型
     */
    public void setListType(String listType) {
        this.listType = listType == null ? null : listType.trim();
    }

    /**
     * 取得 客户代码
     * @return 客户代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 客户代码
     * @param customerId 客户代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 期限
     * @return 期限
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 期限
     * @param term 期限
     */
    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    /**
     * 取得 额度
     * @return 额度
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 额度
     * @param amount 额度
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     * 取得 批次号
     * @return 批次号
     */
    public String getSeqId() {
        return seqId;
    }

    /**
     * 设置 批次号
     * @param seqId 批次号
     */
    public void setSeqId(String seqId) {
        this.seqId = seqId == null ? null : seqId.trim();
    }
}