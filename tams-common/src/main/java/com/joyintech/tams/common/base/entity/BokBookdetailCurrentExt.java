package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokBookdetailCurrentExt extends BaseEntity {
    /**
     * 流水
     */
    private String seqno;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 账务明细流水
     */
    private String fSeqno;

    /**
     * 处理序列
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bookSummaryOrder;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 计提日
     */
    private Date accrDate;

    /**
     * 当日累计应计利息余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexp;

    /**
     * 取得 流水
     * @return 流水
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水
     * @param seqno 流水
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 入账日期
     * @return 入账日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日期
     * @param bookDate 入账日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 账套日期
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * @param booksetDate 账套日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 账务明细流水
     * @return 账务明细流水
     */
    public String getfSeqno() {
        return fSeqno;
    }

    /**
     * 设置 账务明细流水
     * @param fSeqno 账务明细流水
     */
    public void setfSeqno(String fSeqno) {
        this.fSeqno = fSeqno == null ? null : fSeqno.trim();
    }

    /**
     * 取得 处理序列
     * @return 处理序列
     */
    public BigDecimal getBookSummaryOrder() {
        return bookSummaryOrder;
    }

    /**
     * 设置 处理序列
     * @param bookSummaryOrder 处理序列
     */
    public void setBookSummaryOrder(BigDecimal bookSummaryOrder) {
        this.bookSummaryOrder = bookSummaryOrder;
    }

    /**
     * 取得 账务明细代码
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
    }

    /**
     * 取得 业务明细代码
     * @return 业务明细代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 计提日
     * @return 计提日
     */
    public Date getAccrDate() {
        return accrDate;
    }

    /**
     * 设置 计提日
     * @param accrDate 计提日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setAccrDate(Date accrDate) {
        this.accrDate = accrDate;
    }

    /**
     * 取得 当日累计应计利息余额
     * @return 当日累计应计利息余额
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    /**
     * 设置 当日累计应计利息余额
     * @param tdyIntincexp 当日累计应计利息余额
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }
}