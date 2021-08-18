package com.joyintech.tams.invest.batch.creditoccup.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * 授信额度
 * @author zznn
 * @date 2021/8/10
 */
public class CreditUsingDto extends BaseEntity{

    /**
     * 操作数据库  1.插入(01) 2.更新(02) 3.删除(03)
     */
    private String operatioMode;

    /**
     * 授信占用方式 1.机构(01) 2.资产(02) 3.系列(03) 4.机构分项(04)
     */
    private String creditUsingType;

    /**
     * 流水号
     */
    private String seqno;

    /**
     * 本次占用额度
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 是否穿透
     */
    private String isBottom;


    /**
     * 占用类型
     */
    private String creditOccureType;

    /**
     * 委托代码
     */
    private String entrustId;

    public String getOperatioMode() {
        return operatioMode;
    }

    public void setOperatioMode(String operatioMode) {
        this.operatioMode = operatioMode;
    }

    public String getCreditUsingType() {
        return creditUsingType;
    }

    public void setCreditUsingType(String creditUsingType) {
        this.creditUsingType = creditUsingType;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getIsBottom() {
        return isBottom;
    }

    public void setIsBottom(String isBottom) {
        this.isBottom = isBottom;
    }

    public String getCreditOccureType() {
        return creditOccureType;
    }

    public void setCreditOccureType(String creditOccureType) {
        this.creditOccureType = creditOccureType;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }
}

