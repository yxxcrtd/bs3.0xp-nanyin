package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class TaxBokDetail extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 账务明细流水
     */
    private String bokSeqno;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 凭证编号
     */
    private String bookId;

    /**
     * 处理序列
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bookOrder;

    /**
     * 币种
     */
    private String oCcy;

    /**
     * 发生含税销售额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenAmt;

    /**
     * 当日税额_计提
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxAmt;

    /**
     * 当日税额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balAmt;

    /**
     * 当日税额_结转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal carryAmt;

    /**
     * 增值税类型
     */
    private String vatType;

    /**
     * 确认标识
     */
    private String taxPType;

    /**
     * 计税方式
     */
    private String calcType;

    /**
     * 税率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxRate;

    /**
     * 税则代码
     */
    private String taxRegId;

    /**
     * 免税收入类型
     */
    private String feeType;

    /**
     * 发生场景
     */
    private String incomeSource;

    /**
     * 结转区间截止日
     */
    private Date mdate;

    /**
     * 收入科目号
     */
    private String subjectNo;

    /**
     * 发生含税销售额本币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenAmtB;

    /**
     * 当日税额本币_计提
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxAmtB;

    /**
     * 当日税额本币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balAmtB;

    /**
     * 当日税额本币_结转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal carryAmtB;

    /**
     * 本位币
     */
    private String bCcy;

    /**
     * 含税销售额_计提
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeAccrAmt;

    /**
     * 含税销售额_结转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeCarryAmt;

    /**
     * 含税销售额本币_计提
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeAccrAmtB;

    /**
     * 含税销售额本币_结转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeCarryAmtB;

    /**
     * 分支序号
     */
    private Long branch;

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
    public String getBokSeqno() {
        return bokSeqno;
    }

    /**
     * 设置 账务明细流水
     * @param bokSeqno 账务明细流水
     */
    public void setBokSeqno(String bokSeqno) {
        this.bokSeqno = bokSeqno == null ? null : bokSeqno.trim();
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
     * 取得 凭证编号
     * @return 凭证编号
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置 凭证编号
     * @param bookId 凭证编号
     */
    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    /**
     * 取得 处理序列
     * @return 处理序列
     */
    public BigDecimal getBookOrder() {
        return bookOrder;
    }

    /**
     * 设置 处理序列
     * @param bookOrder 处理序列
     */
    public void setBookOrder(BigDecimal bookOrder) {
        this.bookOrder = bookOrder;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getoCcy() {
        return oCcy;
    }

    /**
     * 设置 币种
     * @param oCcy 币种
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy == null ? null : oCcy.trim();
    }

    /**
     * 取得 发生含税销售额
     * @return 发生含税销售额
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    /**
     * 设置 发生含税销售额
     * @param happenAmt 发生含税销售额
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    /**
     * 取得 当日税额_计提
     * @return 当日税额_计提
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * 设置 当日税额_计提
     * @param taxAmt 当日税额_计提
     */
    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    /**
     * 取得 当日税额
     * @return 当日税额
     */
    public BigDecimal getBalAmt() {
        return balAmt;
    }

    /**
     * 设置 当日税额
     * @param balAmt 当日税额
     */
    public void setBalAmt(BigDecimal balAmt) {
        this.balAmt = balAmt;
    }

    /**
     * 取得 当日税额_结转
     * @return 当日税额_结转
     */
    public BigDecimal getCarryAmt() {
        return carryAmt;
    }

    /**
     * 设置 当日税额_结转
     * @param carryAmt 当日税额_结转
     */
    public void setCarryAmt(BigDecimal carryAmt) {
        this.carryAmt = carryAmt;
    }

    /**
     * 取得 增值税类型
     * @return 增值税类型
     */
    public String getVatType() {
        return vatType;
    }

    /**
     * 设置 增值税类型
     * @param vatType 增值税类型
     */
    public void setVatType(String vatType) {
        this.vatType = vatType == null ? null : vatType.trim();
    }

    /**
     * 取得 确认标识
     * @return 确认标识
     */
    public String getTaxPType() {
        return taxPType;
    }

    /**
     * 设置 确认标识
     * @param taxPType 确认标识
     */
    public void setTaxPType(String taxPType) {
        this.taxPType = taxPType == null ? null : taxPType.trim();
    }

    /**
     * 取得 计税方式
     * @return 计税方式
     */
    public String getCalcType() {
        return calcType;
    }

    /**
     * 设置 计税方式
     * @param calcType 计税方式
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType == null ? null : calcType.trim();
    }

    /**
     * 取得 税率
     * @return 税率
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 设置 税率
     * @param taxRate 税率
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 取得 税则代码
     * @return 税则代码
     */
    public String getTaxRegId() {
        return taxRegId;
    }

    /**
     * 设置 税则代码
     * @param taxRegId 税则代码
     */
    public void setTaxRegId(String taxRegId) {
        this.taxRegId = taxRegId == null ? null : taxRegId.trim();
    }

    /**
     * 取得 免税收入类型
     * @return 免税收入类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 免税收入类型
     * @param feeType 免税收入类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * 取得 发生场景
     * @return 发生场景
     */
    public String getIncomeSource() {
        return incomeSource;
    }

    /**
     * 设置 发生场景
     * @param incomeSource 发生场景
     */
    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource == null ? null : incomeSource.trim();
    }

    /**
     * 取得 结转区间截止日
     * @return 结转区间截止日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 结转区间截止日
     * @param mdate 结转区间截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 收入科目号
     * @return 收入科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 收入科目号
     * @param subjectNo 收入科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }

    /**
     * 取得 发生含税销售额本币
     * @return 发生含税销售额本币
     */
    public BigDecimal getHappenAmtB() {
        return happenAmtB;
    }

    /**
     * 设置 发生含税销售额本币
     * @param happenAmtB 发生含税销售额本币
     */
    public void setHappenAmtB(BigDecimal happenAmtB) {
        this.happenAmtB = happenAmtB;
    }

    /**
     * 取得 当日税额本币_计提
     * @return 当日税额本币_计提
     */
    public BigDecimal getTaxAmtB() {
        return taxAmtB;
    }

    /**
     * 设置 当日税额本币_计提
     * @param taxAmtB 当日税额本币_计提
     */
    public void setTaxAmtB(BigDecimal taxAmtB) {
        this.taxAmtB = taxAmtB;
    }

    /**
     * 取得 当日税额本币
     * @return 当日税额本币
     */
    public BigDecimal getBalAmtB() {
        return balAmtB;
    }

    /**
     * 设置 当日税额本币
     * @param balAmtB 当日税额本币
     */
    public void setBalAmtB(BigDecimal balAmtB) {
        this.balAmtB = balAmtB;
    }

    /**
     * 取得 当日税额本币_结转
     * @return 当日税额本币_结转
     */
    public BigDecimal getCarryAmtB() {
        return carryAmtB;
    }

    /**
     * 设置 当日税额本币_结转
     * @param carryAmtB 当日税额本币_结转
     */
    public void setCarryAmtB(BigDecimal carryAmtB) {
        this.carryAmtB = carryAmtB;
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
     * 取得 含税销售额_计提
     * @return 含税销售额_计提
     */
    public BigDecimal getIncomeAccrAmt() {
        return incomeAccrAmt;
    }

    /**
     * 设置 含税销售额_计提
     * @param incomeAccrAmt 含税销售额_计提
     */
    public void setIncomeAccrAmt(BigDecimal incomeAccrAmt) {
        this.incomeAccrAmt = incomeAccrAmt;
    }

    /**
     * 取得 含税销售额_结转
     * @return 含税销售额_结转
     */
    public BigDecimal getIncomeCarryAmt() {
        return incomeCarryAmt;
    }

    /**
     * 设置 含税销售额_结转
     * @param incomeCarryAmt 含税销售额_结转
     */
    public void setIncomeCarryAmt(BigDecimal incomeCarryAmt) {
        this.incomeCarryAmt = incomeCarryAmt;
    }

    /**
     * 取得 含税销售额本币_计提
     * @return 含税销售额本币_计提
     */
    public BigDecimal getIncomeAccrAmtB() {
        return incomeAccrAmtB;
    }

    /**
     * 设置 含税销售额本币_计提
     * @param incomeAccrAmtB 含税销售额本币_计提
     */
    public void setIncomeAccrAmtB(BigDecimal incomeAccrAmtB) {
        this.incomeAccrAmtB = incomeAccrAmtB;
    }

    /**
     * 取得 含税销售额本币_结转
     * @return 含税销售额本币_结转
     */
    public BigDecimal getIncomeCarryAmtB() {
        return incomeCarryAmtB;
    }

    /**
     * 设置 含税销售额本币_结转
     * @param incomeCarryAmtB 含税销售额本币_结转
     */
    public void setIncomeCarryAmtB(BigDecimal incomeCarryAmtB) {
        this.incomeCarryAmtB = incomeCarryAmtB;
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }
}