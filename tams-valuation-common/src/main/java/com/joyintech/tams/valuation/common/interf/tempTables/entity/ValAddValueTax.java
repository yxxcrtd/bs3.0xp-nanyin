package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValAddValueTax extends ValAddValueTaxKey {
    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 科目性质
     */
    private String subjectNature;

    /**
     * 科目代码
     */
    private String subjectId;

    /**
     * 四级科目号
     */
    private String detailSubjectNo;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 借方发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cAmt;

    /**
     * 贷方发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dAmt;

    /**
     * 核算数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valNum;

    /**
     * 科目余额(发生日)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balAmtHap;

    /**
     * 科目余额方向(发生日)
     */
    private String dirctHap;

    /**
     * 科目余额(记账日)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balAmtAct;

    /**
     * 科目余额方向(记账日)
     */
    private String dirctAct;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

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
     * 取得 科目性质
     * @return 科目性质
     */
    public String getSubjectNature() {
        return subjectNature;
    }

    /**
     * 设置 科目性质
     * @param subjectNature 科目性质
     */
    public void setSubjectNature(String subjectNature) {
        this.subjectNature = subjectNature == null ? null : subjectNature.trim();
    }

    /**
     * 取得 科目代码
     * @return 科目代码
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 设置 科目代码
     * @param subjectId 科目代码
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    /**
     * 取得 四级科目号
     * @return 四级科目号
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }

    /**
     * 设置 四级科目号
     * @param detailSubjectNo 四级科目号
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo == null ? null : detailSubjectNo.trim();
    }

    /**
     * 取得 借贷方向
     * @return 借贷方向
     */
    public String getCdFlag() {
        return cdFlag;
    }

    /**
     * 设置 借贷方向
     * @param cdFlag 借贷方向
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag == null ? null : cdFlag.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 借方发生额
     * @return 借方发生额
     */
    public BigDecimal getcAmt() {
        return cAmt;
    }

    /**
     * 设置 借方发生额
     * @param cAmt 借方发生额
     */
    public void setcAmt(BigDecimal cAmt) {
        this.cAmt = cAmt;
    }

    /**
     * 取得 贷方发生额
     * @return 贷方发生额
     */
    public BigDecimal getdAmt() {
        return dAmt;
    }

    /**
     * 设置 贷方发生额
     * @param dAmt 贷方发生额
     */
    public void setdAmt(BigDecimal dAmt) {
        this.dAmt = dAmt;
    }

    /**
     * 取得 核算数量
     * @return 核算数量
     */
    public BigDecimal getValNum() {
        return valNum;
    }

    /**
     * 设置 核算数量
     * @param valNum 核算数量
     */
    public void setValNum(BigDecimal valNum) {
        this.valNum = valNum;
    }

    /**
     * 取得 科目余额(发生日)
     * @return 科目余额(发生日)
     */
    public BigDecimal getBalAmtHap() {
        return balAmtHap;
    }

    /**
     * 设置 科目余额(发生日)
     * @param balAmtHap 科目余额(发生日)
     */
    public void setBalAmtHap(BigDecimal balAmtHap) {
        this.balAmtHap = balAmtHap;
    }

    /**
     * 取得 科目余额方向(发生日)
     * @return 科目余额方向(发生日)
     */
    public String getDirctHap() {
        return dirctHap;
    }

    /**
     * 设置 科目余额方向(发生日)
     * @param dirctHap 科目余额方向(发生日)
     */
    public void setDirctHap(String dirctHap) {
        this.dirctHap = dirctHap == null ? null : dirctHap.trim();
    }

    /**
     * 取得 科目余额(记账日)
     * @return 科目余额(记账日)
     */
    public BigDecimal getBalAmtAct() {
        return balAmtAct;
    }

    /**
     * 设置 科目余额(记账日)
     * @param balAmtAct 科目余额(记账日)
     */
    public void setBalAmtAct(BigDecimal balAmtAct) {
        this.balAmtAct = balAmtAct;
    }

    /**
     * 取得 科目余额方向(记账日)
     * @return 科目余额方向(记账日)
     */
    public String getDirctAct() {
        return dirctAct;
    }

    /**
     * 设置 科目余额方向(记账日)
     * @param dirctAct 科目余额方向(记账日)
     */
    public void setDirctAct(String dirctAct) {
        this.dirctAct = dirctAct == null ? null : dirctAct.trim();
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }
}