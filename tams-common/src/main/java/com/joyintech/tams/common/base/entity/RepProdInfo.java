package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.validation.annotation.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;

public class RepProdInfo extends BaseEntity {
    /**
     * 产品代码：子产品代码、母产品代码、普通产品代码
     */
    @NotEmpty(fieldName = "子产品代码")
    private String prodId;

    /**
     * 产品名称：子产品名称、母产品名称、普通产品名称
     */
    @NotEmpty(fieldName = "子产品名称")
    private String prodName;

    /**
     * 成立日期：子产品成立日期、母产品成立日期、普通产品成立日期
     */
    @NotEmpty(fieldName = "子产品成立日")
    private Date setDate;

    /**
     * 募集金额：子产品或母产品募集金额、普通产品募集金额
     */
    @NotEmpty(fieldName = "产品募集金额")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseAmt;

    /**
     * 产品简称：母产品简称/普通产品的产品简称，子产品为空
     */
    @NotEmpty(fieldName = "产品简称")
    private String prodAbbr;

    /**
     * 产品登记编码：母产品产品登记编码、普通产品产品登记编码，子产品为空
     */
    @NotEmpty(fieldName = "产品登记编码")
    private String prodRe;

    /**
     * 母产品代码：当为子产品时填写母产品代码。母产品、普通产品为空
     */
    @NotEmpty(fieldName = "产品主代码")
    private String pProdId;

    /**
     * 募集开始日：母产品募集开始日，子产品为空
     */
    @NotEmpty(fieldName = "募集开始日期")
    private Date raiseStrdate;

    /**
     * 募集结束日：母产品募集结束日，子产品为空
     */
    @NotEmpty(fieldName = "募集结束日期")
    private Date raiseEnddate;

    /**
     * 到期日：母产品到期日、普通产品到期日，子产品为空
     */
    @NotEmpty(fieldName = "产品到期日")
    private Date mdate;

    /**
     * 募集方式：不区分母子产品 01 公募/02 私募
     */
    @NotEmpty(fieldName = "募集方式")
    private String raiseType;

    /**
     * 母产品业绩比较基准：产品说明书中的业绩比较基准，子产品为空
     */
    private String performCompare;

    /**
     * 是否是客户周期型产品：不区分母子产品 01 是/02 否
     */
    @NotEmpty(fieldName = "是否客户周期型产品")
    private String isPeriod;

    /**
     * 是否是分级产品：不区分母子产品 01 是/02 否
     */
    @NotEmpty(fieldName = "是否分级产品")
    private String isGrade;

    /**
     * 是否是指数型产品：不区分母子产品 01 是/02 否
     */
    @NotEmpty(fieldName = "是否指数型产品")
    private String isIndex;

    /**
     * 母产品开放频率，子产品为空 01 日开/02 频率在半年（不含）以内/03 频率在半年以上
     */
    private String openFreq;

    /**
     * 投资性质：无需区分母子产品 01固定收益类/02权益类/03商品及金融衍生品类/04 混合类
     */
    @NotEmpty(fieldName = "投资性质")
    private String natureInvest;

    /**
     * 产品类型（枚举值调整）：无需区分母子产品 N 净值/M 货币
     */
    @NotEmpty(fieldName = "产品类型")
    private String prodType;

    /**
     * 发行方式：无需区分母子产品 01 开放式/02 封闭式
     */
    @NotEmpty(fieldName = "发行方式")
    private String issuingWay;

    /**
     * 产品托管人名称：无需区分母子产品
     */
    @NotEmpty(fieldName = "产品托管人名称")
    private String acctChnname;

    /**
     * 产品运作方式：无需区分母子产品 01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    @NotEmpty(fieldName = "产品运作方式")
    private String profitType;

    /**
     * 母产品状态：子产品为空 01 运行中/02 已到期/03 已销户，用于按照产品树结构选定适用产品
     */
    @NotEmpty(fieldName = "产品状态")
    private String pdStatus;

    /**
     * 母产品系列：子产品为空 用于按照产品树结构选定适用产品，要给出估值账套分类中的三级“产品系列”
     */
    @NotEmpty(fieldName = "产品系列")
    private String prodSeries;

    /**
     * 发行状态 01 发行成功 02 发行失败 03 确认发行
     */
    private String issueStatus;

    /**
     * 信批产品类别 01 母产品 02 普通产品 03 子产品
     */
    private String repProdType;


    /**
     * 固定持有天数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal holdday;

    /**
     * 托管部门
     */
    @NotEmpty(fieldName = "托管部门")
    private String trustpart;

    /**
     * 错误信息
     */
    private String errormsg;

    /**
     *  发行状态备注
     */
    private String issueStatusRemark;



    /**
     * 募集下限
     */
    private String raiseMin;

    /**
     * 募集上限
     */
    private String raiseMax;

    /**
     * 募集币种
     */
    private String raiseCcy;

    /**
     * 产品起息日
     */
    private Date vdate;

    /**
     * 首次开放日
     */
    private Date firstOpenDay;

    /**
     * 实际到期日
     */
    private Date actMdate;

    /**
     * 产品期限
     */
    private String term;

    /**
     * 产品清盘日
     */
    private Date windupDate;

    /**
     * 发行方式
     */
    private String issueMode;

    /**
     * 产品风险等级
     */
    private String riskLevel;

    /**
     * 开放模式
     */
    private String openMode;

    /**
     * 投资收益到账日
     */
    private String investProfDate;

    /**
     * 投资本金到账日
     */
    private String investPrinDate;

    /**
     * 是否独立开户
     */
    private String isIndepeAcc;

    /**
     * null
     */
    private String prodInvestType;

    /**
     * 披露渠道
     */
    private String revealChannel;

    /**
     * 交易日历
     */
    private String dealCal;

    /**
     * 理财类型
     */
    private String finaType;

    /**
     * 托管人账号
     */
    private String truAccount;

    /**
     * 托管费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal truFeeRage;

    /**
     * 托管账户账号
     */
    private String truAccountNum;

    /**
     * 托管账户户名
     */
    private String truAccountName;

    /**
     * 产品期限类型
     */
    private String prodTermType;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 销售对象
     */
    private String targetSale;

    /**
     * 是否分层
     */
    private String isLayer;

    /**
     * null
     */
    private String proManager;

    /**
     * null
     */
    private String proCustodian;

    /**
     * 巨额赎回阀值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hugeRedeemThreshold;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleAmtStr;

    /**
     * null
     */
    private String operateMode;

    /**
     * null
     */
    private String fProdId;

    /**
     * 产品收益标识
     */
    private String prodprofitflag;

    /**
     * 目标客户类型
     */
    private String saletargetDeclare;

    /**
     * 分红方式:数据字典BONUS_MODE
     */
    private String bonusMode;

    /**
     * 最少追加金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowestAmt;

    public String getRaiseMin() {
        return raiseMin;
    }

    public void setRaiseMin(String raiseMin) {
        this.raiseMin = raiseMin;
    }

    public String getRaiseMax() {
        return raiseMax;
    }

    public void setRaiseMax(String raiseMax) {
        this.raiseMax = raiseMax;
    }

    public String getRaiseCcy() {
        return raiseCcy;
    }

    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy;
    }

    public Date getFirstOpenDay() {
        return firstOpenDay;
    }

    public void setFirstOpenDay(Date firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    public Date getActMdate() {
        return actMdate;
    }

    public void setActMdate(Date actMdate) {
        this.actMdate = actMdate;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Date getWindupDate() {
        return windupDate;
    }

    public void setWindupDate(Date windupDate) {
        this.windupDate = windupDate;
    }

    public String getIssueMode() {
        return issueMode;
    }

    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getOpenMode() {
        return openMode;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    public String getInvestProfDate() {
        return investProfDate;
    }

    public void setInvestProfDate(String investProfDate) {
        this.investProfDate = investProfDate;
    }

    public String getInvestPrinDate() {
        return investPrinDate;
    }

    public void setInvestPrinDate(String investPrinDate) {
        this.investPrinDate = investPrinDate;
    }

    public String getIsIndepeAcc() {
        return isIndepeAcc;
    }

    public void setIsIndepeAcc(String isIndepeAcc) {
        this.isIndepeAcc = isIndepeAcc;
    }

    public String getProdInvestType() {
        return prodInvestType;
    }

    public void setProdInvestType(String prodInvestType) {
        this.prodInvestType = prodInvestType;
    }

    public String getDealCal() {
        return dealCal;
    }

    public void setDealCal(String dealCal) {
        this.dealCal = dealCal;
    }

    public String getFinaType() {
        return finaType;
    }

    public void setFinaType(String finaType) {
        this.finaType = finaType;
    }

    public String getTruAccount() {
        return truAccount;
    }

    public void setTruAccount(String truAccount) {
        this.truAccount = truAccount;
    }

    public BigDecimal getTruFeeRage() {
        return truFeeRage;
    }

    public void setTruFeeRage(BigDecimal truFeeRage) {
        this.truFeeRage = truFeeRage;
    }

    public String getTruAccountNum() {
        return truAccountNum;
    }

    public void setTruAccountNum(String truAccountNum) {
        this.truAccountNum = truAccountNum;
    }

    public String getTruAccountName() {
        return truAccountName;
    }

    public void setTruAccountName(String truAccountName) {
        this.truAccountName = truAccountName;
    }

    public String getProdTermType() {
        return prodTermType;
    }

    public void setProdTermType(String prodTermType) {
        this.prodTermType = prodTermType;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getTargetSale() {
        return targetSale;
    }

    public void setTargetSale(String targetSale) {
        this.targetSale = targetSale;
    }

    public String getIsLayer() {
        return isLayer;
    }

    public void setIsLayer(String isLayer) {
        this.isLayer = isLayer;
    }

    public String getProManager() {
        return proManager;
    }

    public void setProManager(String proManager) {
        this.proManager = proManager;
    }

    public String getProCustodian() {
        return proCustodian;
    }

    public void setProCustodian(String proCustodian) {
        this.proCustodian = proCustodian;
    }

    public BigDecimal getHugeRedeemThreshold() {
        return hugeRedeemThreshold;
    }

    public void setHugeRedeemThreshold(BigDecimal hugeRedeemThreshold) {
        this.hugeRedeemThreshold = hugeRedeemThreshold;
    }

    public BigDecimal getSaleAmtStr() {
        return saleAmtStr;
    }

    public void setSaleAmtStr(BigDecimal saleAmtStr) {
        this.saleAmtStr = saleAmtStr;
    }

    public String getOperateMode() {
        return operateMode;
    }

    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public String getfProdId() {
        return fProdId;
    }

    public void setfProdId(String fProdId) {
        this.fProdId = fProdId;
    }

    public String getProdprofitflag() {
        return prodprofitflag;
    }

    public void setProdprofitflag(String prodprofitflag) {
        this.prodprofitflag = prodprofitflag;
    }

    public String getSaletargetDeclare() {
        return saletargetDeclare;
    }

    public void setSaletargetDeclare(String saletargetDeclare) {
        this.saletargetDeclare = saletargetDeclare;
    }

    public String getBonusMode() {
        return bonusMode;
    }

    public void setBonusMode(String bonusMode) {
        this.bonusMode = bonusMode;
    }

    public BigDecimal getLowestAmt() {
        return lowestAmt;
    }

    public void setLowestAmt(BigDecimal lowestAmt) {
        this.lowestAmt = lowestAmt;
    }

    public String getIssueStatusRemark() {
        return issueStatusRemark;
    }

    public void setIssueStatusRemark(String issueStatusRemark) {
        this.issueStatusRemark = issueStatusRemark;
    }

    public String getTrustpart() {
        return trustpart;
    }

    public void setTrustpart(String trustpart) {
        this.trustpart = trustpart;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public BigDecimal getHoldday() {
        return holdday;
    }

    public void setHoldday(BigDecimal holdday) {
        this.holdday = holdday;
    }

    public String getRepProdType() {
        return repProdType;
    }

    public void setRepProdType(String repProdType) {
        this.repProdType = repProdType;
    }

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

    /**
     * 取得 发行状态 01 发行成功 02 发行失败 03 确认发行
     * @return 发行状态 01 发行成功 02 发行失败 03 确认发行
     */
    public String getIssueStatus() {
        return issueStatus;
    }

    /**
     * 设置 发行状态 01 发行成功 02 发行失败 03 确认发行
     * @param issueStatus 发行状态 01 发行成功 02 发行失败 03 确认发行
     */
    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus == null ? null : issueStatus.trim();
    }
}