package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokStatFeePosiiton extends BokStatFeePosiitonKey {
    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 当日计提份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPosition;

    /**
     * 计提利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFeeRate;

    /**
     * 当日新增费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexpAdd;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 分支序号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal branch;

    /**
     * 原币
     */
    private String ccy;

    /**
     * 本位币
     */
    private String bCcy;

    /**
     * 当日新增费用_本位币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexpAddB;

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
     * 取得 当日计提份额
     * @return 当日计提份额
     */
    public BigDecimal getTdyPosition() {
        return tdyPosition;
    }

    /**
     * 设置 当日计提份额
     * @param tdyPosition 当日计提份额
     */
    public void setTdyPosition(BigDecimal tdyPosition) {
        this.tdyPosition = tdyPosition;
    }

    /**
     * 取得 计提利率
     * @return 计提利率
     */
    public BigDecimal getTdyFeeRate() {
        return tdyFeeRate;
    }

    /**
     * 设置 计提利率
     * @param tdyFeeRate 计提利率
     */
    public void setTdyFeeRate(BigDecimal tdyFeeRate) {
        this.tdyFeeRate = tdyFeeRate;
    }

    /**
     * 取得 当日新增费用
     * @return 当日新增费用
     */
    public BigDecimal getTdyIntincexpAdd() {
        return tdyIntincexpAdd;
    }

    /**
     * 设置 当日新增费用
     * @param tdyIntincexpAdd 当日新增费用
     */
    public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
        this.tdyIntincexpAdd = tdyIntincexpAdd;
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
     * 取得 分支序号
     * @return 分支序号
     */
    public BigDecimal getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(BigDecimal branch) {
        this.branch = branch;
    }

    /**
     * 取得 原币
     * @return 原币
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 原币
     * @param ccy 原币
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getbCcy() {
        return bCcy;
    }

    /**
     * 设置 本位币
     * @param bCcy 本位币
     */
    public void setbCcy(String bCcy) {
        this.bCcy = bCcy == null ? null : bCcy.trim();
    }

    /**
     * 取得 当日新增费用_本位币
     * @return 当日新增费用_本位币
     */
    public BigDecimal getTdyIntincexpAddB() {
        return tdyIntincexpAddB;
    }

    /**
     * 设置 当日新增费用_本位币
     * @param tdyIntincexpAddB 当日新增费用_本位币
     */
    public void setTdyIntincexpAddB(BigDecimal tdyIntincexpAddB) {
        this.tdyIntincexpAddB = tdyIntincexpAddB;
    }
}