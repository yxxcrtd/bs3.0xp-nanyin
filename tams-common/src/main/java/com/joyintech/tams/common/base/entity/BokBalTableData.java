package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokBalTableData extends BaseEntity {
    /**
     * 账套代码
     */
    private String seqNo;

    /**
     * 币种
     */
    private String oCcy;

    /**
     * 原币期初余额方向
     */
    private String oOpenBalFlag;

    /**
     * 原币期初余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oOpenBalance;

    /**
     * 原币本期借方发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oHappenAmtD;

    /**
     * 原币本期贷方发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oHappenAmtC;

    /**
     * 原币期末余额方向
     */
    private String oEndBalFlag;

    /**
     * 原币期末余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oEndBalance;

    /**
     * 本位币期初余额方向
     */
    private String bOpenBalFlag;

    /**
     * 本位币期初余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bOpenBalance;

    /**
     * 本位币本期借方发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bHappenAmtD;

    /**
     * 本位币本期贷方发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bHappenAmtC;

    /**
     * 本位币期末余额方向
     */
    private String bEndBalFlag;

    /**
     * 本位币期末余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bEndBalance;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 估值日期
     */
    private Date balDate;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 账套代码
     * @param seqNo 账套代码
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
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
     * 取得 原币期初余额方向
     * @return 原币期初余额方向
     */
    public String getoOpenBalFlag() {
        return oOpenBalFlag;
    }

    /**
     * 设置 原币期初余额方向
     * @param oOpenBalFlag 原币期初余额方向
     */
    public void setoOpenBalFlag(String oOpenBalFlag) {
        this.oOpenBalFlag = oOpenBalFlag == null ? null : oOpenBalFlag.trim();
    }

    /**
     * 取得 原币期初余额
     * @return 原币期初余额
     */
    public BigDecimal getoOpenBalance() {
        return oOpenBalance;
    }

    /**
     * 设置 原币期初余额
     * @param oOpenBalance 原币期初余额
     */
    public void setoOpenBalance(BigDecimal oOpenBalance) {
        this.oOpenBalance = oOpenBalance;
    }

    /**
     * 取得 原币本期借方发生额
     * @return 原币本期借方发生额
     */
    public BigDecimal getoHappenAmtD() {
        return oHappenAmtD;
    }

    /**
     * 设置 原币本期借方发生额
     * @param oHappenAmtD 原币本期借方发生额
     */
    public void setoHappenAmtD(BigDecimal oHappenAmtD) {
        this.oHappenAmtD = oHappenAmtD;
    }

    /**
     * 取得 原币本期贷方发生额
     * @return 原币本期贷方发生额
     */
    public BigDecimal getoHappenAmtC() {
        return oHappenAmtC;
    }

    /**
     * 设置 原币本期贷方发生额
     * @param oHappenAmtC 原币本期贷方发生额
     */
    public void setoHappenAmtC(BigDecimal oHappenAmtC) {
        this.oHappenAmtC = oHappenAmtC;
    }

    /**
     * 取得 原币期末余额方向
     * @return 原币期末余额方向
     */
    public String getoEndBalFlag() {
        return oEndBalFlag;
    }

    /**
     * 设置 原币期末余额方向
     * @param oEndBalFlag 原币期末余额方向
     */
    public void setoEndBalFlag(String oEndBalFlag) {
        this.oEndBalFlag = oEndBalFlag == null ? null : oEndBalFlag.trim();
    }

    /**
     * 取得 原币期末余额
     * @return 原币期末余额
     */
    public BigDecimal getoEndBalance() {
        return oEndBalance;
    }

    /**
     * 设置 原币期末余额
     * @param oEndBalance 原币期末余额
     */
    public void setoEndBalance(BigDecimal oEndBalance) {
        this.oEndBalance = oEndBalance;
    }

    /**
     * 取得 本位币期初余额方向
     * @return 本位币期初余额方向
     */
    public String getbOpenBalFlag() {
        return bOpenBalFlag;
    }

    /**
     * 设置 本位币期初余额方向
     * @param bOpenBalFlag 本位币期初余额方向
     */
    public void setbOpenBalFlag(String bOpenBalFlag) {
        this.bOpenBalFlag = bOpenBalFlag == null ? null : bOpenBalFlag.trim();
    }

    /**
     * 取得 本位币期初余额
     * @return 本位币期初余额
     */
    public BigDecimal getbOpenBalance() {
        return bOpenBalance;
    }

    /**
     * 设置 本位币期初余额
     * @param bOpenBalance 本位币期初余额
     */
    public void setbOpenBalance(BigDecimal bOpenBalance) {
        this.bOpenBalance = bOpenBalance;
    }

    /**
     * 取得 本位币本期借方发生额
     * @return 本位币本期借方发生额
     */
    public BigDecimal getbHappenAmtD() {
        return bHappenAmtD;
    }

    /**
     * 设置 本位币本期借方发生额
     * @param bHappenAmtD 本位币本期借方发生额
     */
    public void setbHappenAmtD(BigDecimal bHappenAmtD) {
        this.bHappenAmtD = bHappenAmtD;
    }

    /**
     * 取得 本位币本期贷方发生额
     * @return 本位币本期贷方发生额
     */
    public BigDecimal getbHappenAmtC() {
        return bHappenAmtC;
    }

    /**
     * 设置 本位币本期贷方发生额
     * @param bHappenAmtC 本位币本期贷方发生额
     */
    public void setbHappenAmtC(BigDecimal bHappenAmtC) {
        this.bHappenAmtC = bHappenAmtC;
    }

    /**
     * 取得 本位币期末余额方向
     * @return 本位币期末余额方向
     */
    public String getbEndBalFlag() {
        return bEndBalFlag;
    }

    /**
     * 设置 本位币期末余额方向
     * @param bEndBalFlag 本位币期末余额方向
     */
    public void setbEndBalFlag(String bEndBalFlag) {
        this.bEndBalFlag = bEndBalFlag == null ? null : bEndBalFlag.trim();
    }

    /**
     * 取得 本位币期末余额
     * @return 本位币期末余额
     */
    public BigDecimal getbEndBalance() {
        return bEndBalance;
    }

    /**
     * 设置 本位币期末余额
     * @param bEndBalance 本位币期末余额
     */
    public void setbEndBalance(BigDecimal bEndBalance) {
        this.bEndBalance = bEndBalance;
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
     * 取得 账套名称
     * @return 账套名称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套名称
     * @param booksetName 账套名称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName == null ? null : booksetName.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getBalDate() {
        return balDate;
    }

    /**
     * 设置 估值日期
     * @param balDate 估值日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBalDate(Date balDate) {
        this.balDate = balDate;
    }

    /**
     * 取得 科目号
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }

    /**
     * 取得 科目名称
     * @return 科目名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 科目名称
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }
}