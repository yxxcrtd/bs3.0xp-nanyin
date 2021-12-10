package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class EtrEntrustOperHis extends BaseEntity {
    /**
     * 序号
     */
    private String seqNo;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 操作类型
     */
    private String operType;

    /**
     * 操作日期
     */
    private Date operDate;

    /**
     * 操作时间
     */
    private Date operTime;

    /**
     * 业务说明
     */
    private String busiDes;

    /**
     * 操作员
     */
    private String userId;

    /**
     * 导出次数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal statisticalTimes;

    /**
     * 取得 序号
     * @return 序号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 序号
     * @param seqNo 序号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 操作类型
     * @return 操作类型
     */
    public String getOperType() {
        return operType;
    }

    /**
     * 设置 操作类型
     * @param operType 操作类型
     */
    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    /**
     * 取得 操作日期
     * @return 操作日期
     */
    public Date getOperDate() {
        return operDate;
    }

    /**
     * 设置 操作日期
     * @param operDate 操作日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    /**
     * 取得 操作时间
     * @return 操作时间
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * 设置 操作时间
     * @param operTime 操作时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    /**
     * 取得 业务说明
     * @return 业务说明
     */
    public String getBusiDes() {
        return busiDes;
    }

    /**
     * 设置 业务说明
     * @param busiDes 业务说明
     */
    public void setBusiDes(String busiDes) {
        this.busiDes = busiDes == null ? null : busiDes.trim();
    }

    /**
     * 取得 操作员
     * @return 操作员
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 操作员
     * @param userId 操作员
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 取得 导出次数
     * @return 导出次数
     */
    public BigDecimal getStatisticalTimes() {
        return statisticalTimes;
    }

    /**
     * 设置 导出次数
     * @param statisticalTimes 导出次数
     */
    public void setStatisticalTimes(BigDecimal statisticalTimes) {
        this.statisticalTimes = statisticalTimes;
    }
}