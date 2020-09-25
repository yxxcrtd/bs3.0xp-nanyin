package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TaxDeclareSumInfo extends BaseEntity {
    /**
     * 流水号
     */
    private String seqId;

    /**
     * 征期
     */
    private String declareDate;

    /**
     * 汇总维度
     */
    private String declareType;

    /**
     * 账套代码
     */
    private String subjectNature;

    /**
     * 收益标识
     */
    private String signFlag;

    /**
     * 应税利息及红利收入-不含税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal a1;

    /**
     * 利息及红利收入增值税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal b1;

    /**
     * 金融商品转让损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal a2;

    /**
     * 金融商品转让增值税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal b2;

    /**
     * 金融商品转让入账增值税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal b3;

    /**
     * 免税收入-36号文件
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal free36file;

    /**
     * 免税收入-46号文件
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal free46file;

    /**
     * 免税收入-70号文件
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal free70file;

    /**
     * 免税收入-国债及地方债
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal freeSec;

    /**
     * 本期应交增值税产品划转到银行增值税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal c1;

    /**
     * 本期应交附加税费产品划转到银行附加税费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal c2;

    /**
     * 本期应交增值税银行划转到税局金融商品转让增值税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal c3;

    /**
     * 本期应交增值税银行划转到税局增值税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal c4;

    /**
     * 本期应交附加税费银行划转到税局附加税费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal c5;

    /**
     * 本期留存收益变动额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal e1;

    /**
     * 期初留存收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal e2;

    /**
     * 期末留存收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal e3;

    /**
     * 年份
     */
    private String declareYear;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqId() {
        return seqId;
    }

    /**
     * 设置 流水号
     * @param seqId 流水号
     */
    public void setSeqId(String seqId) {
        this.seqId = seqId == null ? null : seqId.trim();
    }

    /**
     * 取得 征期
     * @return 征期
     */
    public String getDeclareDate() {
        return declareDate;
    }

    /**
     * 设置 征期
     * @param declareDate 征期
     */
    public void setDeclareDate(String declareDate) {
        this.declareDate = declareDate == null ? null : declareDate.trim();
    }

    /**
     * 取得 汇总维度
     * @return 汇总维度
     */
    public String getDeclareType() {
        return declareType;
    }

    /**
     * 设置 汇总维度
     * @param declareType 汇总维度
     */
    public void setDeclareType(String declareType) {
        this.declareType = declareType == null ? null : declareType.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getSubjectNature() {
        return subjectNature;
    }

    /**
     * 设置 账套代码
     * @param subjectNature 账套代码
     */
    public void setSubjectNature(String subjectNature) {
        this.subjectNature = subjectNature == null ? null : subjectNature.trim();
    }

    /**
     * 取得 收益标识
     * @return 收益标识
     */
    public String getSignFlag() {
        return signFlag;
    }

    /**
     * 设置 收益标识
     * @param signFlag 收益标识
     */
    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag == null ? null : signFlag.trim();
    }

    /**
     * 取得 应税利息及红利收入-不含税
     * @return 应税利息及红利收入-不含税
     */
    public BigDecimal getA1() {
        return a1;
    }

    /**
     * 设置 应税利息及红利收入-不含税
     * @param a1 应税利息及红利收入-不含税
     */
    public void setA1(BigDecimal a1) {
        this.a1 = a1;
    }

    /**
     * 取得 利息及红利收入增值税
     * @return 利息及红利收入增值税
     */
    public BigDecimal getB1() {
        return b1;
    }

    /**
     * 设置 利息及红利收入增值税
     * @param b1 利息及红利收入增值税
     */
    public void setB1(BigDecimal b1) {
        this.b1 = b1;
    }

    /**
     * 取得 金融商品转让损益
     * @return 金融商品转让损益
     */
    public BigDecimal getA2() {
        return a2;
    }

    /**
     * 设置 金融商品转让损益
     * @param a2 金融商品转让损益
     */
    public void setA2(BigDecimal a2) {
        this.a2 = a2;
    }

    /**
     * 取得 金融商品转让增值税
     * @return 金融商品转让增值税
     */
    public BigDecimal getB2() {
        return b2;
    }

    /**
     * 设置 金融商品转让增值税
     * @param b2 金融商品转让增值税
     */
    public void setB2(BigDecimal b2) {
        this.b2 = b2;
    }

    /**
     * 取得 金融商品转让入账增值税
     * @return 金融商品转让入账增值税
     */
    public BigDecimal getB3() {
        return b3;
    }

    /**
     * 设置 金融商品转让入账增值税
     * @param b3 金融商品转让入账增值税
     */
    public void setB3(BigDecimal b3) {
        this.b3 = b3;
    }

    /**
     * 取得 免税收入-36号文件
     * @return 免税收入-36号文件
     */
    public BigDecimal getFree36file() {
        return free36file;
    }

    /**
     * 设置 免税收入-36号文件
     * @param free36file 免税收入-36号文件
     */
    public void setFree36file(BigDecimal free36file) {
        this.free36file = free36file;
    }

    /**
     * 取得 免税收入-46号文件
     * @return 免税收入-46号文件
     */
    public BigDecimal getFree46file() {
        return free46file;
    }

    /**
     * 设置 免税收入-46号文件
     * @param free46file 免税收入-46号文件
     */
    public void setFree46file(BigDecimal free46file) {
        this.free46file = free46file;
    }

    /**
     * 取得 免税收入-70号文件
     * @return 免税收入-70号文件
     */
    public BigDecimal getFree70file() {
        return free70file;
    }

    /**
     * 设置 免税收入-70号文件
     * @param free70file 免税收入-70号文件
     */
    public void setFree70file(BigDecimal free70file) {
        this.free70file = free70file;
    }

    /**
     * 取得 免税收入-国债及地方债
     * @return 免税收入-国债及地方债
     */
    public BigDecimal getFreeSec() {
        return freeSec;
    }

    /**
     * 设置 免税收入-国债及地方债
     * @param freeSec 免税收入-国债及地方债
     */
    public void setFreeSec(BigDecimal freeSec) {
        this.freeSec = freeSec;
    }

    /**
     * 取得 本期应交增值税产品划转到银行增值税
     * @return 本期应交增值税产品划转到银行增值税
     */
    public BigDecimal getC1() {
        return c1;
    }

    /**
     * 设置 本期应交增值税产品划转到银行增值税
     * @param c1 本期应交增值税产品划转到银行增值税
     */
    public void setC1(BigDecimal c1) {
        this.c1 = c1;
    }

    /**
     * 取得 本期应交附加税费产品划转到银行附加税费
     * @return 本期应交附加税费产品划转到银行附加税费
     */
    public BigDecimal getC2() {
        return c2;
    }

    /**
     * 设置 本期应交附加税费产品划转到银行附加税费
     * @param c2 本期应交附加税费产品划转到银行附加税费
     */
    public void setC2(BigDecimal c2) {
        this.c2 = c2;
    }

    /**
     * 取得 本期应交增值税银行划转到税局金融商品转让增值税
     * @return 本期应交增值税银行划转到税局金融商品转让增值税
     */
    public BigDecimal getC3() {
        return c3;
    }

    /**
     * 设置 本期应交增值税银行划转到税局金融商品转让增值税
     * @param c3 本期应交增值税银行划转到税局金融商品转让增值税
     */
    public void setC3(BigDecimal c3) {
        this.c3 = c3;
    }

    /**
     * 取得 本期应交增值税银行划转到税局增值税
     * @return 本期应交增值税银行划转到税局增值税
     */
    public BigDecimal getC4() {
        return c4;
    }

    /**
     * 设置 本期应交增值税银行划转到税局增值税
     * @param c4 本期应交增值税银行划转到税局增值税
     */
    public void setC4(BigDecimal c4) {
        this.c4 = c4;
    }

    /**
     * 取得 本期应交附加税费银行划转到税局附加税费
     * @return 本期应交附加税费银行划转到税局附加税费
     */
    public BigDecimal getC5() {
        return c5;
    }

    /**
     * 设置 本期应交附加税费银行划转到税局附加税费
     * @param c5 本期应交附加税费银行划转到税局附加税费
     */
    public void setC5(BigDecimal c5) {
        this.c5 = c5;
    }

    /**
     * 取得 本期留存收益变动额
     * @return 本期留存收益变动额
     */
    public BigDecimal getE1() {
        return e1;
    }

    /**
     * 设置 本期留存收益变动额
     * @param e1 本期留存收益变动额
     */
    public void setE1(BigDecimal e1) {
        this.e1 = e1;
    }

    /**
     * 取得 期初留存收益
     * @return 期初留存收益
     */
    public BigDecimal getE2() {
        return e2;
    }

    /**
     * 设置 期初留存收益
     * @param e2 期初留存收益
     */
    public void setE2(BigDecimal e2) {
        this.e2 = e2;
    }

    /**
     * 取得 期末留存收益
     * @return 期末留存收益
     */
    public BigDecimal getE3() {
        return e3;
    }

    /**
     * 设置 期末留存收益
     * @param e3 期末留存收益
     */
    public void setE3(BigDecimal e3) {
        this.e3 = e3;
    }

    /**
     * 取得 年份
     * @return 年份
     */
    public String getDeclareYear() {
        return declareYear;
    }

    /**
     * 设置 年份
     * @param declareYear 年份
     */
    public void setDeclareYear(String declareYear) {
        this.declareYear = declareYear == null ? null : declareYear.trim();
    }
}