package com.joyintech.tams.disclosure.busicommon.common.dto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokStatDetPosiiton extends BokStatDetPosiitonKey {
    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 当前持仓
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal posAmt;

    /**
     * 计提利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 日摊销收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dailydscYield;

    /**
     * 当日发生应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexpAdd;

    /**
     * 当日累计应计利息余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexp;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 当日计提份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPosition;

    /**
     * 当日成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyCostAmt;

    /**
     * 当日公允价值变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFloatIngpl;

    /**
     * 剩余期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDays1;

    /**
     * 剩余存续期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDays2;

    /**
     * 剩余期限用成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDaysCostAmt;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 行情日期
     */
    private Date valDate;

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
     * 取得 当前持仓
     * @return 当前持仓
     */
    public BigDecimal getPosAmt() {
        return posAmt;
    }

    /**
     * 设置 当前持仓
     * @param posAmt 当前持仓
     */
    public void setPosAmt(BigDecimal posAmt) {
        this.posAmt = posAmt;
    }

    /**
     * 取得 计提利率
     * @return 计提利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 计提利率
     * @param intRate 计提利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 日摊销收益率
     * @return 日摊销收益率
     */
    public BigDecimal getDailydscYield() {
        return dailydscYield;
    }

    /**
     * 设置 日摊销收益率
     * @param dailydscYield 日摊销收益率
     */
    public void setDailydscYield(BigDecimal dailydscYield) {
        this.dailydscYield = dailydscYield;
    }

    /**
     * 取得 当日发生应计利息
     * @return 当日发生应计利息
     */
    public BigDecimal getTdyIntincexpAdd() {
        return tdyIntincexpAdd;
    }

    /**
     * 设置 当日发生应计利息
     * @param tdyIntincexpAdd 当日发生应计利息
     */
    public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
        this.tdyIntincexpAdd = tdyIntincexpAdd;
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
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 通道代码
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId == null ? null : chlId.trim();
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
     * 取得 当日成本
     * @return 当日成本
     */
    public BigDecimal getTdyCostAmt() {
        return tdyCostAmt;
    }

    /**
     * 设置 当日成本
     * @param tdyCostAmt 当日成本
     */
    public void setTdyCostAmt(BigDecimal tdyCostAmt) {
        this.tdyCostAmt = tdyCostAmt;
    }

    /**
     * 取得 当日公允价值变动
     * @return 当日公允价值变动
     */
    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }

    /**
     * 设置 当日公允价值变动
     * @param tdyFloatIngpl 当日公允价值变动
     */
    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }

    /**
     * 取得 剩余期限
     * @return 剩余期限
     */
    public BigDecimal getEndDays1() {
        return endDays1;
    }

    /**
     * 设置 剩余期限
     * @param endDays1 剩余期限
     */
    public void setEndDays1(BigDecimal endDays1) {
        this.endDays1 = endDays1;
    }

    /**
     * 取得 剩余存续期限
     * @return 剩余存续期限
     */
    public BigDecimal getEndDays2() {
        return endDays2;
    }

    /**
     * 设置 剩余存续期限
     * @param endDays2 剩余存续期限
     */
    public void setEndDays2(BigDecimal endDays2) {
        this.endDays2 = endDays2;
    }

    /**
     * 取得 剩余期限用成本
     * @return 剩余期限用成本
     */
    public BigDecimal getEndDaysCostAmt() {
        return endDaysCostAmt;
    }

    /**
     * 设置 剩余期限用成本
     * @param endDaysCostAmt 剩余期限用成本
     */
    public void setEndDaysCostAmt(BigDecimal endDaysCostAmt) {
        this.endDaysCostAmt = endDaysCostAmt;
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
     * 取得 行情日期
     * @return 行情日期
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 行情日期
     * @param valDate 行情日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }
}