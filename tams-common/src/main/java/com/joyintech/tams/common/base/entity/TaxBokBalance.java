package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class TaxBokBalance extends BaseEntity {
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
     * 币种
     */
    private String oCcy;

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
     * 发生场景
     */
    private String incomeSource;

    /**
     * 含税销售额合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeAmtAll;

    /**
     * 当日含税销售额合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeHappenAmt;

    /**
     * 当日税额合计_结转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal carryAmt;

    /**
     * 当日税额合计_计提
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenAmt;

    /**
     * 增值税额_发生额合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxAmt;

    /**
     * 处理序列
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bookOrder;

    /**
     * 收入科目号
     */
    private String subjectNo;

    /**
     * 当期增值税额合计_余额计算
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal durBalTaxAmt;

    /**
     * 当期含税销售额合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeDurAmt;

    /**
     * 含税销售额本币合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeAmtAllB;

    /**
     * 当日含税销售额本币合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeHappenAmtB;

    /**
     * 当日税额本币合计_结转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal carryAmtB;

    /**
     * 当日税额本币合计_计提
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenAmtB;

    /**
     * 增值税额本币_发生额合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxAmtB;

    /**
     * 本位币
     */
    private String bCcy;

    /**
     * 当日含税销售额合计_计提
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeAccrAmt;

    /**
     * 当日含税销售额合计_结转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeCarryAmt;

    /**
     * 当日含税销售额本币合计_计提
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeAccrAmtB;

    /**
     * 当日含税销售额本币合计_结转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeCarryAmtB;

    /**
     * 增值税额_余额计算
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balCalcTaxAmt;

    /**
     * 增值税额本币_余额计算
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balCalcTaxAmtB;

    /**
     * 当期含税销售额本币合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeDurAmtB;

    /**
     * 当期增值税额本币合计_余额计算
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal durBalTaxAmtB;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 当日增值税额_余额计算
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBalCalcTaxAmt;

    /**
     * 当日增值税额本币_余额计算
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBalCalcTaxAmtB;

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
     * 取得 含税销售额合计
     * @return 含税销售额合计
     */
    public BigDecimal getIncomeAmtAll() {
        return incomeAmtAll;
    }

    /**
     * 设置 含税销售额合计
     * @param incomeAmtAll 含税销售额合计
     */
    public void setIncomeAmtAll(BigDecimal incomeAmtAll) {
        this.incomeAmtAll = incomeAmtAll;
    }

    /**
     * 取得 当日含税销售额合计
     * @return 当日含税销售额合计
     */
    public BigDecimal getIncomeHappenAmt() {
        return incomeHappenAmt;
    }

    /**
     * 设置 当日含税销售额合计
     * @param incomeHappenAmt 当日含税销售额合计
     */
    public void setIncomeHappenAmt(BigDecimal incomeHappenAmt) {
        this.incomeHappenAmt = incomeHappenAmt;
    }

    /**
     * 取得 当日税额合计_结转
     * @return 当日税额合计_结转
     */
    public BigDecimal getCarryAmt() {
        return carryAmt;
    }

    /**
     * 设置 当日税额合计_结转
     * @param carryAmt 当日税额合计_结转
     */
    public void setCarryAmt(BigDecimal carryAmt) {
        this.carryAmt = carryAmt;
    }

    /**
     * 取得 当日税额合计_计提
     * @return 当日税额合计_计提
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    /**
     * 设置 当日税额合计_计提
     * @param happenAmt 当日税额合计_计提
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    /**
     * 取得 增值税额_发生额合计
     * @return 增值税额_发生额合计
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * 设置 增值税额_发生额合计
     * @param taxAmt 增值税额_发生额合计
     */
    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
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
     * 取得 当期增值税额合计_余额计算
     * @return 当期增值税额合计_余额计算
     */
    public BigDecimal getDurBalTaxAmt() {
        return durBalTaxAmt;
    }

    /**
     * 设置 当期增值税额合计_余额计算
     * @param durBalTaxAmt 当期增值税额合计_余额计算
     */
    public void setDurBalTaxAmt(BigDecimal durBalTaxAmt) {
        this.durBalTaxAmt = durBalTaxAmt;
    }

    /**
     * 取得 当期含税销售额合计
     * @return 当期含税销售额合计
     */
    public BigDecimal getIncomeDurAmt() {
        return incomeDurAmt;
    }

    /**
     * 设置 当期含税销售额合计
     * @param incomeDurAmt 当期含税销售额合计
     */
    public void setIncomeDurAmt(BigDecimal incomeDurAmt) {
        this.incomeDurAmt = incomeDurAmt;
    }

    /**
     * 取得 含税销售额本币合计
     * @return 含税销售额本币合计
     */
    public BigDecimal getIncomeAmtAllB() {
        return incomeAmtAllB;
    }

    /**
     * 设置 含税销售额本币合计
     * @param incomeAmtAllB 含税销售额本币合计
     */
    public void setIncomeAmtAllB(BigDecimal incomeAmtAllB) {
        this.incomeAmtAllB = incomeAmtAllB;
    }

    /**
     * 取得 当日含税销售额本币合计
     * @return 当日含税销售额本币合计
     */
    public BigDecimal getIncomeHappenAmtB() {
        return incomeHappenAmtB;
    }

    /**
     * 设置 当日含税销售额本币合计
     * @param incomeHappenAmtB 当日含税销售额本币合计
     */
    public void setIncomeHappenAmtB(BigDecimal incomeHappenAmtB) {
        this.incomeHappenAmtB = incomeHappenAmtB;
    }

    /**
     * 取得 当日税额本币合计_结转
     * @return 当日税额本币合计_结转
     */
    public BigDecimal getCarryAmtB() {
        return carryAmtB;
    }

    /**
     * 设置 当日税额本币合计_结转
     * @param carryAmtB 当日税额本币合计_结转
     */
    public void setCarryAmtB(BigDecimal carryAmtB) {
        this.carryAmtB = carryAmtB;
    }

    /**
     * 取得 当日税额本币合计_计提
     * @return 当日税额本币合计_计提
     */
    public BigDecimal getHappenAmtB() {
        return happenAmtB;
    }

    /**
     * 设置 当日税额本币合计_计提
     * @param happenAmtB 当日税额本币合计_计提
     */
    public void setHappenAmtB(BigDecimal happenAmtB) {
        this.happenAmtB = happenAmtB;
    }

    /**
     * 取得 增值税额本币_发生额合计
     * @return 增值税额本币_发生额合计
     */
    public BigDecimal getTaxAmtB() {
        return taxAmtB;
    }

    /**
     * 设置 增值税额本币_发生额合计
     * @param taxAmtB 增值税额本币_发生额合计
     */
    public void setTaxAmtB(BigDecimal taxAmtB) {
        this.taxAmtB = taxAmtB;
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
     * 取得 当日含税销售额合计_计提
     * @return 当日含税销售额合计_计提
     */
    public BigDecimal getIncomeAccrAmt() {
        return incomeAccrAmt;
    }

    /**
     * 设置 当日含税销售额合计_计提
     * @param incomeAccrAmt 当日含税销售额合计_计提
     */
    public void setIncomeAccrAmt(BigDecimal incomeAccrAmt) {
        this.incomeAccrAmt = incomeAccrAmt;
    }

    /**
     * 取得 当日含税销售额合计_结转
     * @return 当日含税销售额合计_结转
     */
    public BigDecimal getIncomeCarryAmt() {
        return incomeCarryAmt;
    }

    /**
     * 设置 当日含税销售额合计_结转
     * @param incomeCarryAmt 当日含税销售额合计_结转
     */
    public void setIncomeCarryAmt(BigDecimal incomeCarryAmt) {
        this.incomeCarryAmt = incomeCarryAmt;
    }

    /**
     * 取得 当日含税销售额本币合计_计提
     * @return 当日含税销售额本币合计_计提
     */
    public BigDecimal getIncomeAccrAmtB() {
        return incomeAccrAmtB;
    }

    /**
     * 设置 当日含税销售额本币合计_计提
     * @param incomeAccrAmtB 当日含税销售额本币合计_计提
     */
    public void setIncomeAccrAmtB(BigDecimal incomeAccrAmtB) {
        this.incomeAccrAmtB = incomeAccrAmtB;
    }

    /**
     * 取得 当日含税销售额本币合计_结转
     * @return 当日含税销售额本币合计_结转
     */
    public BigDecimal getIncomeCarryAmtB() {
        return incomeCarryAmtB;
    }

    /**
     * 设置 当日含税销售额本币合计_结转
     * @param incomeCarryAmtB 当日含税销售额本币合计_结转
     */
    public void setIncomeCarryAmtB(BigDecimal incomeCarryAmtB) {
        this.incomeCarryAmtB = incomeCarryAmtB;
    }

    /**
     * 取得 增值税额_余额计算
     * @return 增值税额_余额计算
     */
    public BigDecimal getBalCalcTaxAmt() {
        return balCalcTaxAmt;
    }

    /**
     * 设置 增值税额_余额计算
     * @param balCalcTaxAmt 增值税额_余额计算
     */
    public void setBalCalcTaxAmt(BigDecimal balCalcTaxAmt) {
        this.balCalcTaxAmt = balCalcTaxAmt;
    }

    /**
     * 取得 增值税额本币_余额计算
     * @return 增值税额本币_余额计算
     */
    public BigDecimal getBalCalcTaxAmtB() {
        return balCalcTaxAmtB;
    }

    /**
     * 设置 增值税额本币_余额计算
     * @param balCalcTaxAmtB 增值税额本币_余额计算
     */
    public void setBalCalcTaxAmtB(BigDecimal balCalcTaxAmtB) {
        this.balCalcTaxAmtB = balCalcTaxAmtB;
    }

    /**
     * 取得 当期含税销售额本币合计
     * @return 当期含税销售额本币合计
     */
    public BigDecimal getIncomeDurAmtB() {
        return incomeDurAmtB;
    }

    /**
     * 设置 当期含税销售额本币合计
     * @param incomeDurAmtB 当期含税销售额本币合计
     */
    public void setIncomeDurAmtB(BigDecimal incomeDurAmtB) {
        this.incomeDurAmtB = incomeDurAmtB;
    }

    /**
     * 取得 当期增值税额本币合计_余额计算
     * @return 当期增值税额本币合计_余额计算
     */
    public BigDecimal getDurBalTaxAmtB() {
        return durBalTaxAmtB;
    }

    /**
     * 设置 当期增值税额本币合计_余额计算
     * @param durBalTaxAmtB 当期增值税额本币合计_余额计算
     */
    public void setDurBalTaxAmtB(BigDecimal durBalTaxAmtB) {
        this.durBalTaxAmtB = durBalTaxAmtB;
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

    /**
     * 取得 当日增值税额_余额计算
     * @return 当日增值税额_余额计算
     */
    public BigDecimal getTdyBalCalcTaxAmt() {
        return tdyBalCalcTaxAmt;
    }

    /**
     * 设置 当日增值税额_余额计算
     * @param tdyBalCalcTaxAmt 当日增值税额_余额计算
     */
    public void setTdyBalCalcTaxAmt(BigDecimal tdyBalCalcTaxAmt) {
        this.tdyBalCalcTaxAmt = tdyBalCalcTaxAmt;
    }

    /**
     * 取得 当日增值税额本币_余额计算
     * @return 当日增值税额本币_余额计算
     */
    public BigDecimal getTdyBalCalcTaxAmtB() {
        return tdyBalCalcTaxAmtB;
    }

    /**
     * 设置 当日增值税额本币_余额计算
     * @param tdyBalCalcTaxAmtB 当日增值税额本币_余额计算
     */
    public void setTdyBalCalcTaxAmtB(BigDecimal tdyBalCalcTaxAmtB) {
        this.tdyBalCalcTaxAmtB = tdyBalCalcTaxAmtB;
    }


    /**
     * add by xiongr 预估  TISHKBS-1198 start
     * 预估计税余额=昨日余额+今日发生-当日结转
     * 非价差入税，且是预估计税有结转时，预估计税需计提时需包含结转的部分
     * 含税销售额本币合计_含结转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeAmtAllBHjz;

    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal BalCalcTaxAmtBHjz;


    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBalCalcTaxAmtBHjz;


    public BigDecimal getIncomeAmtAllBHjz() {
        return incomeAmtAllBHjz;
    }

    public void setIncomeAmtAllBHjz(BigDecimal incomeAmtAllBHjz) {
        this.incomeAmtAllBHjz = incomeAmtAllBHjz;
    }

    public void setBalCalcTaxAmtBHjz(BigDecimal balCalcTaxAmtBHjz) {
        BalCalcTaxAmtBHjz = balCalcTaxAmtBHjz;
    }

    public BigDecimal getBalCalcTaxAmtBHjz() {
        return BalCalcTaxAmtBHjz;
    }

    public BigDecimal getTdyBalCalcTaxAmtBHjz() {
        return tdyBalCalcTaxAmtBHjz;
    }

    public void setTdyBalCalcTaxAmtBHjz(BigDecimal tdyBalCalcTaxAmtBHjz) {
        this.tdyBalCalcTaxAmtBHjz = tdyBalCalcTaxAmtBHjz;
    }

    /**
     * add by xiongr 预估 TISHKBS-1198 end
     * 预估计税余额=昨日余额+今日发生-当日结转
     * 非价差入税，且是预估计税有结转时，预估计税需计提时需包含结转的部分
     * 含税销售额本币合计_含结转
     */
}