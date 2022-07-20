package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepProdInfo extends BaseEntity {
    /**
     * 产品代码：子产品代码、母产品代码、普通产品代码
     */
    private String prodId;

    /**
     * 产品名称：子产品名称、母产品名称、普通产品名称
     */
    private String prodName;

    /**
     * 成立日期：子产品成立日期、母产品成立日期、普通产品成立日期
     */
    private Date setDate;

    /**
     * 募集金额：子产品或母产品募集金额、普通产品募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseAmt;

    /**
     * 产品简称：母产品简称/普通产品的产品简称，子产品为空
     */
    private String prodAbbr;

    /**
     * 产品登记编码：母产品产品登记编码、普通产品产品登记编码，子产品为空
     */
    private String prodRe;

    /**
     * 母产品代码：当为子产品时填写母产品代码。母产品、普通产品为空
     */
    private String pProdId;

    /**
     * 募集开始日：母产品募集开始日，子产品为空
     */
    private Date raiseStrdate;

    /**
     * 募集结束日：母产品募集结束日，子产品为空
     */
    private Date raiseEnddate;

    /**
     * 到期日：母产品到期日、普通产品到期日，子产品为空
     */
    private Date mdate;

    /**
     * 募集方式：不区分母子产品 01 公募/02 私募
     */
    private String raiseType;

    /**
     * 母产品业绩比较基准：产品说明书中的业绩比较基准，子产品为空
     */
    private String performCompare;

    /**
     * 是否是客户周期型产品：不区分母子产品 01 是/02 否
     */
    private String isPeriod;

    /**
     * 是否是分级产品：不区分母子产品 01 是/02 否
     */
    private String isGrade;

    /**
     * 是否是指数型产品：不区分母子产品 01 是/02 否
     */
    private String isIndex;

    /**
     * 母产品开放频率，子产品为空 01 日开/02 频率在半年（不含）以内/03 频率在半年以上
     */
    private String openFreq;

    /**
     * 投资性质：无需区分母子产品 01固定收益类/02权益类/03商品及金融衍生品类/04 混合类
     */
    private String natureInvest;

    /**
     * 产品类型（枚举值调整）：无需区分母子产品 N 净值/M 货币
     */
    private String prodType;

    /**
     * 发行方式：无需区分母子产品 01 开放式/02 封闭式
     */
    private String issuingWay;

    /**
     * 产品托管人名称：无需区分母子产品
     */
    private String acctChnname;

    /**
     * 产品运作方式：无需区分母子产品 01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    private String profitType;

    /**
     * 母产品状态：子产品为空 01 运行中/02 已到期/03 已销户，用于按照产品树结构选定适用产品
     */
    private String pdStatus;

    /**
     * 母产品系列：子产品为空 用于按照产品树结构选定适用产品，要给出估值账套分类中的三级“产品系列”
     */
    private String prodSeries;

    /**
     * 产品起息日
     * 这个字段用不上，新的表没有这个字段
     */
    private Date vdate;

    /**
     * 披露渠道
     * 这个字段用不上，新的表没有这个字段
     */
    private String revealChannel;

    public String getRevealChannel() {
        return revealChannel;
    }

    public void setRevealChannel(String revealChannel) {
        this.revealChannel = revealChannel;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 产品代码：子产品代码、母产品代码、普通产品代码
     * @return 产品代码：子产品代码、母产品代码、普通产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码：子产品代码、母产品代码、普通产品代码
     * @param prodId 产品代码：子产品代码、母产品代码、普通产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品名称：子产品名称、母产品名称、普通产品名称
     * @return 产品名称：子产品名称、母产品名称、普通产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称：子产品名称、母产品名称、普通产品名称
     * @param prodName 产品名称：子产品名称、母产品名称、普通产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 成立日期：子产品成立日期、母产品成立日期、普通产品成立日期
     * @return 成立日期：子产品成立日期、母产品成立日期、普通产品成立日期
     */
    public Date getSetDate() {
        return setDate;
    }

    /**
     * 设置 成立日期：子产品成立日期、母产品成立日期、普通产品成立日期
     * @param setDate 成立日期：子产品成立日期、母产品成立日期、普通产品成立日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    /**
     * 取得 募集金额：子产品或母产品募集金额、普通产品募集金额
     * @return 募集金额：子产品或母产品募集金额、普通产品募集金额
     */
    public BigDecimal getRaiseAmt() {
        return raiseAmt;
    }

    /**
     * 设置 募集金额：子产品或母产品募集金额、普通产品募集金额
     * @param raiseAmt 募集金额：子产品或母产品募集金额、普通产品募集金额
     */
    public void setRaiseAmt(BigDecimal raiseAmt) {
        this.raiseAmt = raiseAmt;
    }

    /**
     * 取得 产品简称：母产品简称/普通产品的产品简称，子产品为空
     * @return 产品简称：母产品简称/普通产品的产品简称，子产品为空
     */
    public String getProdAbbr() {
        return prodAbbr;
    }

    /**
     * 设置 产品简称：母产品简称/普通产品的产品简称，子产品为空
     * @param prodAbbr 产品简称：母产品简称/普通产品的产品简称，子产品为空
     */
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr == null ? null : prodAbbr.trim();
    }

    /**
     * 取得 产品登记编码：母产品产品登记编码、普通产品产品登记编码，子产品为空
     * @return 产品登记编码：母产品产品登记编码、普通产品产品登记编码，子产品为空
     */
    public String getProdRe() {
        return prodRe;
    }

    /**
     * 设置 产品登记编码：母产品产品登记编码、普通产品产品登记编码，子产品为空
     * @param prodRe 产品登记编码：母产品产品登记编码、普通产品产品登记编码，子产品为空
     */
    public void setProdRe(String prodRe) {
        this.prodRe = prodRe == null ? null : prodRe.trim();
    }

    /**
     * 取得 母产品代码：当为子产品时填写母产品代码。母产品、普通产品为空
     * @return 母产品代码：当为子产品时填写母产品代码。母产品、普通产品为空
     */
    public String getpProdId() {
        return pProdId;
    }

    /**
     * 设置 母产品代码：当为子产品时填写母产品代码。母产品、普通产品为空
     * @param pProdId 母产品代码：当为子产品时填写母产品代码。母产品、普通产品为空
     */
    public void setpProdId(String pProdId) {
        this.pProdId = pProdId == null ? null : pProdId.trim();
    }

    /**
     * 取得 募集开始日：母产品募集开始日，子产品为空
     * @return 募集开始日：母产品募集开始日，子产品为空
     */
    public Date getRaiseStrdate() {
        return raiseStrdate;
    }

    /**
     * 设置 募集开始日：母产品募集开始日，子产品为空
     * @param raiseStrdate 募集开始日：母产品募集开始日，子产品为空
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseStrdate(Date raiseStrdate) {
        this.raiseStrdate = raiseStrdate;
    }

    /**
     * 取得 募集结束日：母产品募集结束日，子产品为空
     * @return 募集结束日：母产品募集结束日，子产品为空
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 设置 募集结束日：母产品募集结束日，子产品为空
     * @param raiseEnddate 募集结束日：母产品募集结束日，子产品为空
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 取得 到期日：母产品到期日、普通产品到期日，子产品为空
     * @return 到期日：母产品到期日、普通产品到期日，子产品为空
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日：母产品到期日、普通产品到期日，子产品为空
     * @param mdate 到期日：母产品到期日、普通产品到期日，子产品为空
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 募集方式：不区分母子产品 01 公募/02 私募
     * @return 募集方式：不区分母子产品 01 公募/02 私募
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 设置 募集方式：不区分母子产品 01 公募/02 私募
     * @param raiseType 募集方式：不区分母子产品 01 公募/02 私募
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 取得 母产品业绩比较基准：产品说明书中的业绩比较基准，子产品为空
     * @return 母产品业绩比较基准：产品说明书中的业绩比较基准，子产品为空
     */
    public String getPerformCompare() {
        return performCompare;
    }

    /**
     * 设置 母产品业绩比较基准：产品说明书中的业绩比较基准，子产品为空
     * @param performCompare 母产品业绩比较基准：产品说明书中的业绩比较基准，子产品为空
     */
    public void setPerformCompare(String performCompare) {
        this.performCompare = performCompare == null ? null : performCompare.trim();
    }

    /**
     * 取得 是否是客户周期型产品：不区分母子产品 01 是/02 否
     * @return 是否是客户周期型产品：不区分母子产品 01 是/02 否
     */
    public String getIsPeriod() {
        return isPeriod;
    }

    /**
     * 设置 是否是客户周期型产品：不区分母子产品 01 是/02 否
     * @param isPeriod 是否是客户周期型产品：不区分母子产品 01 是/02 否
     */
    public void setIsPeriod(String isPeriod) {
        this.isPeriod = isPeriod == null ? null : isPeriod.trim();
    }

    /**
     * 取得 是否是分级产品：不区分母子产品 01 是/02 否
     * @return 是否是分级产品：不区分母子产品 01 是/02 否
     */
    public String getIsGrade() {
        return isGrade;
    }

    /**
     * 设置 是否是分级产品：不区分母子产品 01 是/02 否
     * @param isGrade 是否是分级产品：不区分母子产品 01 是/02 否
     */
    public void setIsGrade(String isGrade) {
        this.isGrade = isGrade == null ? null : isGrade.trim();
    }

    /**
     * 取得 是否是指数型产品：不区分母子产品 01 是/02 否
     * @return 是否是指数型产品：不区分母子产品 01 是/02 否
     */
    public String getIsIndex() {
        return isIndex;
    }

    /**
     * 设置 是否是指数型产品：不区分母子产品 01 是/02 否
     * @param isIndex 是否是指数型产品：不区分母子产品 01 是/02 否
     */
    public void setIsIndex(String isIndex) {
        this.isIndex = isIndex == null ? null : isIndex.trim();
    }

    /**
     * 取得 母产品开放频率，子产品为空 01 日开/02 频率在半年（不含）以内/03 频率在半年以上
     * @return 母产品开放频率，子产品为空 01 日开/02 频率在半年（不含）以内/03 频率在半年以上
     */
    public String getOpenFreq() {
        return openFreq;
    }

    /**
     * 设置 母产品开放频率，子产品为空 01 日开/02 频率在半年（不含）以内/03 频率在半年以上
     * @param openFreq 母产品开放频率，子产品为空 01 日开/02 频率在半年（不含）以内/03 频率在半年以上
     */
    public void setOpenFreq(String openFreq) {
        this.openFreq = openFreq == null ? null : openFreq.trim();
    }

    /**
     * 取得 投资性质：无需区分母子产品 01固定收益类/02权益类/03商品及金融衍生品类/04 混合类
     * @return 投资性质：无需区分母子产品 01固定收益类/02权益类/03商品及金融衍生品类/04 混合类
     */
    public String getNatureInvest() {
        return natureInvest;
    }

    /**
     * 设置 投资性质：无需区分母子产品 01固定收益类/02权益类/03商品及金融衍生品类/04 混合类
     * @param natureInvest 投资性质：无需区分母子产品 01固定收益类/02权益类/03商品及金融衍生品类/04 混合类
     */
    public void setNatureInvest(String natureInvest) {
        this.natureInvest = natureInvest == null ? null : natureInvest.trim();
    }

    /**
     * 取得 产品类型（枚举值调整）：无需区分母子产品 N 净值/M 货币
     * @return 产品类型（枚举值调整）：无需区分母子产品 N 净值/M 货币
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * 设置 产品类型（枚举值调整）：无需区分母子产品 N 净值/M 货币
     * @param prodType 产品类型（枚举值调整）：无需区分母子产品 N 净值/M 货币
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * 取得 发行方式：无需区分母子产品 01 开放式/02 封闭式
     * @return 发行方式：无需区分母子产品 01 开放式/02 封闭式
     */
    public String getIssuingWay() {
        return issuingWay;
    }

    /**
     * 设置 发行方式：无需区分母子产品 01 开放式/02 封闭式
     * @param issuingWay 发行方式：无需区分母子产品 01 开放式/02 封闭式
     */
    public void setIssuingWay(String issuingWay) {
        this.issuingWay = issuingWay == null ? null : issuingWay.trim();
    }

    /**
     * 取得 产品托管人名称：无需区分母子产品
     * @return 产品托管人名称：无需区分母子产品
     */
    public String getAcctChnname() {
        return acctChnname;
    }

    /**
     * 设置 产品托管人名称：无需区分母子产品
     * @param acctChnname 产品托管人名称：无需区分母子产品
     */
    public void setAcctChnname(String acctChnname) {
        this.acctChnname = acctChnname == null ? null : acctChnname.trim();
    }

    /**
     * 取得 产品运作方式：无需区分母子产品 01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     * @return 产品运作方式：无需区分母子产品 01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 产品运作方式：无需区分母子产品 01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     * @param profitType 产品运作方式：无需区分母子产品 01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 母产品状态：子产品为空 01 运行中/02 已到期/03 已销户，用于按照产品树结构选定适用产品
     * @return 母产品状态：子产品为空 01 运行中/02 已到期/03 已销户，用于按照产品树结构选定适用产品
     */
    public String getPdStatus() {
        return pdStatus;
    }

    /**
     * 设置 母产品状态：子产品为空 01 运行中/02 已到期/03 已销户，用于按照产品树结构选定适用产品
     * @param pdStatus 母产品状态：子产品为空 01 运行中/02 已到期/03 已销户，用于按照产品树结构选定适用产品
     */
    public void setPdStatus(String pdStatus) {
        this.pdStatus = pdStatus == null ? null : pdStatus.trim();
    }

    /**
     * 取得 母产品系列：子产品为空 用于按照产品树结构选定适用产品，要给出估值账套分类中的三级“产品系列”
     * @return 母产品系列：子产品为空 用于按照产品树结构选定适用产品，要给出估值账套分类中的三级“产品系列”
     */
    public String getProdSeries() {
        return prodSeries;
    }

    /**
     * 设置 母产品系列：子产品为空 用于按照产品树结构选定适用产品，要给出估值账套分类中的三级“产品系列”
     * @param prodSeries 母产品系列：子产品为空 用于按照产品树结构选定适用产品，要给出估值账套分类中的三级“产品系列”
     */
    public void setProdSeries(String prodSeries) {
        this.prodSeries = prodSeries == null ? null : prodSeries.trim();
    }
}