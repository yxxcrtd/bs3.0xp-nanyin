package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokBatchMarketwarningInfo extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 批处理任务组执行ID
     */
    private String batchExeSeqNo;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 市场代码
     */
    private String marketCode;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 估值来源
     */
    private String valueSource;

    /**
     * 理论行情日期
     */
    private Date theValueDate;

    /**
     * 实际行情日期
     */
    private Date actValueDate;

    /**
     * 实际行情价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actMarketPrice;

    /**
     * 是否一致
     */
    private String yesOrNo;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 批处理任务组执行ID
     * @return 批处理任务组执行ID
     */
    public String getBatchExeSeqNo() {
        return batchExeSeqNo;
    }

    /**
     * 设置 批处理任务组执行ID
     * @param batchExeSeqNo 批处理任务组执行ID
     */
    public void setBatchExeSeqNo(String batchExeSeqNo) {
        this.batchExeSeqNo = batchExeSeqNo == null ? null : batchExeSeqNo.trim();
    }

    /**
     * 取得 任务代码
     * @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 任务代码
     * @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
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
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 市场代码
     * @return 市场代码
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * 设置 市场代码
     * @param marketCode 市场代码
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode == null ? null : marketCode.trim();
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }

    /**
     * 取得 估值来源
     * @return 估值来源
     */
    public String getValueSource() {
        return valueSource;
    }

    /**
     * 设置 估值来源
     * @param valueSource 估值来源
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource == null ? null : valueSource.trim();
    }

    /**
     * 取得 理论行情日期
     * @return 理论行情日期
     */
    public Date getTheValueDate() {
        return theValueDate;
    }

    /**
     * 设置 理论行情日期
     * @param theValueDate 理论行情日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTheValueDate(Date theValueDate) {
        this.theValueDate = theValueDate;
    }

    /**
     * 取得 实际行情日期
     * @return 实际行情日期
     */
    public Date getActValueDate() {
        return actValueDate;
    }

    /**
     * 设置 实际行情日期
     * @param actValueDate 实际行情日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActValueDate(Date actValueDate) {
        this.actValueDate = actValueDate;
    }

    /**
     * 取得 实际行情价格
     * @return 实际行情价格
     */
    public BigDecimal getActMarketPrice() {
        return actMarketPrice;
    }

    /**
     * 设置 实际行情价格
     * @param actMarketPrice 实际行情价格
     */
    public void setActMarketPrice(BigDecimal actMarketPrice) {
        this.actMarketPrice = actMarketPrice;
    }

    /**
     * 取得 是否一致
     * @return 是否一致
     */
    public String getYesOrNo() {
        return yesOrNo;
    }

    /**
     * 设置 是否一致
     * @param yesOrNo 是否一致
     */
    public void setYesOrNo(String yesOrNo) {
        this.yesOrNo = yesOrNo == null ? null : yesOrNo.trim();
    }
}