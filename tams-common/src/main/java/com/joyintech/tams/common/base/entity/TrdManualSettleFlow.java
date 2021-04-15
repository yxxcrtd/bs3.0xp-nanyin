package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class TrdManualSettleFlow extends BaseEntity {
    /**
     * 交收流水序号
     */
    private String seqNo;

    /**
     * 待交割序号
     */
    private String settleSeqNo;

    /**
     * 交收时间
     */
    private Date settleTime;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 交割数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settleNum;

    /**
     * 交割金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settleAmt;

    /**
     * 操作类型 数据字典
     */
    private String operType;

    /**
     * 取得 交收流水序号
     * @return 交收流水序号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 交收流水序号
     * @param seqNo 交收流水序号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 待交割序号
     * @return 待交割序号
     */
    public String getSettleSeqNo() {
        return settleSeqNo;
    }

    /**
     * 设置 待交割序号
     * @param settleSeqNo 待交割序号
     */
    public void setSettleSeqNo(String settleSeqNo) {
        this.settleSeqNo = settleSeqNo == null ? null : settleSeqNo.trim();
    }

    /**
     * 取得 交收时间
     * @return 交收时间
     */
    public Date getSettleTime() {
        return settleTime;
    }

    /**
     * 设置 交收时间
     * @param settleTime 交收时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 交割数量
     * @return 交割数量
     */
    public BigDecimal getSettleNum() {
        return settleNum;
    }

    /**
     * 设置 交割数量
     * @param settleNum 交割数量
     */
    public void setSettleNum(BigDecimal settleNum) {
        this.settleNum = settleNum;
    }

    /**
     * 取得 交割金额
     * @return 交割金额
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    /**
     * 设置 交割金额
     * @param settleAmt 交割金额
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    /**
     * 取得 操作类型 数据字典
     * @return 操作类型 数据字典
     */
    public String getOperType() {
        return operType;
    }

    /**
     * 设置 操作类型 数据字典
     * @param operType 操作类型 数据字典
     */
    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }
}