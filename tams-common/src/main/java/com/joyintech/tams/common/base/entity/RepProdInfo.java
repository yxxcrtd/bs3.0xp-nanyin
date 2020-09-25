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
     * 产品代码
     */
    private String prodId;

    /**
     * 产品登记编码
     */
    private String prodRe;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品简称
     */
    private String prodAbbr;

    /**
     * 募集下限
     */
    private String raiseMin;

    /**
     * 募集上限
     */
    private String raiseMax;

    /**
     * 募集开始日
     */
    private Date raiseStrdate;

    /**
     * 募集结束日
     */
    private Date raiseEnddate;

    /**
     * 募集币种
     */
    private String raiseCcy;

    /**
     * 产品成立日
     */
    private Date setDate;

    /**
     * 产品起息日
     */
    private Date vdate;

    /**
     * 首次开放日
     */
    private Date firstOpenDay;

    /**
     * 产品到期日
     */
    private Date mdate;

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
     * 收益类型
     */
    private String profitType;

    /**
     * 产品类型
     */
    private String prodType;

    /**
     * 发行状态
     */
    private String issueStatus;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 产品风险等级
     */
    private String riskLevel;

    /**
     * 募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseAmt;

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
     * 投资范围/投资性质
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
     * 托管人名称
     */
    private String acctChnname;

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
     * 投资性质
     */
    private String natureInvest;

    /**
     * 销售对象
     */
    private String targetSale;

    /**
     * 是否分层
     */
    private String isLayer;

    /**
     * 业绩比较基准
     */
    private String performCompare;

    /**
     * 管理人
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
     * 起点销售金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleAmtStr;

    /**
     * 产品运作模式
     */
    private String operateMode;

    /**
     * 源系统金融产品代码
     */
    private String fProdId;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRe() {
        return prodRe;
    }

    /**
     * 设置 产品登记编码
     * @param prodRe 产品登记编码
     */
    public void setProdRe(String prodRe) {
        this.prodRe = prodRe == null ? null : prodRe.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 产品简称
     * @return 产品简称
     */
    public String getProdAbbr() {
        return prodAbbr;
    }

    /**
     * 设置 产品简称
     * @param prodAbbr 产品简称
     */
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr == null ? null : prodAbbr.trim();
    }

    /**
     * 取得 募集下限
     * @return 募集下限
     */
    public String getRaiseMin() {
        return raiseMin;
    }

    /**
     * 设置 募集下限
     * @param raiseMin 募集下限
     */
    public void setRaiseMin(String raiseMin) {
        this.raiseMin = raiseMin == null ? null : raiseMin.trim();
    }

    /**
     * 取得 募集上限
     * @return 募集上限
     */
    public String getRaiseMax() {
        return raiseMax;
    }

    /**
     * 设置 募集上限
     * @param raiseMax 募集上限
     */
    public void setRaiseMax(String raiseMax) {
        this.raiseMax = raiseMax == null ? null : raiseMax.trim();
    }

    /**
     * 取得 募集开始日
     * @return 募集开始日
     */
    public Date getRaiseStrdate() {
        return raiseStrdate;
    }

    /**
     * 设置 募集开始日
     * @param raiseStrdate 募集开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseStrdate(Date raiseStrdate) {
        this.raiseStrdate = raiseStrdate;
    }

    /**
     * 取得 募集结束日
     * @return 募集结束日
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 设置 募集结束日
     * @param raiseEnddate 募集结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 取得 募集币种
     * @return 募集币种
     */
    public String getRaiseCcy() {
        return raiseCcy;
    }

    /**
     * 设置 募集币种
     * @param raiseCcy 募集币种
     */
    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy == null ? null : raiseCcy.trim();
    }

    /**
     * 取得 产品成立日
     * @return 产品成立日
     */
    public Date getSetDate() {
        return setDate;
    }

    /**
     * 设置 产品成立日
     * @param setDate 产品成立日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    /**
     * 取得 产品起息日
     * @return 产品起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 产品起息日
     * @param vdate 产品起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 首次开放日
     * @return 首次开放日
     */
    public Date getFirstOpenDay() {
        return firstOpenDay;
    }

    /**
     * 设置 首次开放日
     * @param firstOpenDay 首次开放日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstOpenDay(Date firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    /**
     * 取得 产品到期日
     * @return 产品到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 产品到期日
     * @param mdate 产品到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 实际到期日
     * @return 实际到期日
     */
    public Date getActMdate() {
        return actMdate;
    }

    /**
     * 设置 实际到期日
     * @param actMdate 实际到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActMdate(Date actMdate) {
        this.actMdate = actMdate;
    }

    /**
     * 取得 产品期限
     * @return 产品期限
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 产品期限
     * @param term 产品期限
     */
    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    /**
     * 取得 产品清盘日
     * @return 产品清盘日
     */
    public Date getWindupDate() {
        return windupDate;
    }

    /**
     * 设置 产品清盘日
     * @param windupDate 产品清盘日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setWindupDate(Date windupDate) {
        this.windupDate = windupDate;
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getIssueMode() {
        return issueMode;
    }

    /**
     * 设置 发行方式
     * @param issueMode 发行方式
     */
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode == null ? null : issueMode.trim();
    }

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 产品类型
     * @return 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * 设置 产品类型
     * @param prodType 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * 取得 发行状态
     * @return 发行状态
     */
    public String getIssueStatus() {
        return issueStatus;
    }

    /**
     * 设置 发行状态
     * @param issueStatus 发行状态
     */
    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus == null ? null : issueStatus.trim();
    }

    /**
     * 取得 募集方式
     * @return 募集方式
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 设置 募集方式
     * @param raiseType 募集方式
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 取得 产品风险等级
     * @return 产品风险等级
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置 产品风险等级
     * @param riskLevel 产品风险等级
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 取得 募集金额
     * @return 募集金额
     */
    public BigDecimal getRaiseAmt() {
        return raiseAmt;
    }

    /**
     * 设置 募集金额
     * @param raiseAmt 募集金额
     */
    public void setRaiseAmt(BigDecimal raiseAmt) {
        this.raiseAmt = raiseAmt;
    }

    /**
     * 取得 开放模式
     * @return 开放模式
     */
    public String getOpenMode() {
        return openMode;
    }

    /**
     * 设置 开放模式
     * @param openMode 开放模式
     */
    public void setOpenMode(String openMode) {
        this.openMode = openMode == null ? null : openMode.trim();
    }

    /**
     * 取得 投资收益到账日
     * @return 投资收益到账日
     */
    public String getInvestProfDate() {
        return investProfDate;
    }

    /**
     * 设置 投资收益到账日
     * @param investProfDate 投资收益到账日
     */
    public void setInvestProfDate(String investProfDate) {
        this.investProfDate = investProfDate == null ? null : investProfDate.trim();
    }

    /**
     * 取得 投资本金到账日
     * @return 投资本金到账日
     */
    public String getInvestPrinDate() {
        return investPrinDate;
    }

    /**
     * 设置 投资本金到账日
     * @param investPrinDate 投资本金到账日
     */
    public void setInvestPrinDate(String investPrinDate) {
        this.investPrinDate = investPrinDate == null ? null : investPrinDate.trim();
    }

    /**
     * 取得 是否独立开户
     * @return 是否独立开户
     */
    public String getIsIndepeAcc() {
        return isIndepeAcc;
    }

    /**
     * 设置 是否独立开户
     * @param isIndepeAcc 是否独立开户
     */
    public void setIsIndepeAcc(String isIndepeAcc) {
        this.isIndepeAcc = isIndepeAcc == null ? null : isIndepeAcc.trim();
    }

    /**
     * 取得 投资范围/投资性质
     * @return 投资范围/投资性质
     */
    public String getProdInvestType() {
        return prodInvestType;
    }

    /**
     * 设置 投资范围/投资性质
     * @param prodInvestType 投资范围/投资性质
     */
    public void setProdInvestType(String prodInvestType) {
        this.prodInvestType = prodInvestType == null ? null : prodInvestType.trim();
    }

    /**
     * 取得 披露渠道
     * @return 披露渠道
     */
    public String getRevealChannel() {
        return revealChannel;
    }

    /**
     * 设置 披露渠道
     * @param revealChannel 披露渠道
     */
    public void setRevealChannel(String revealChannel) {
        this.revealChannel = revealChannel == null ? null : revealChannel.trim();
    }

    /**
     * 取得 交易日历
     * @return 交易日历
     */
    public String getDealCal() {
        return dealCal;
    }

    /**
     * 设置 交易日历
     * @param dealCal 交易日历
     */
    public void setDealCal(String dealCal) {
        this.dealCal = dealCal == null ? null : dealCal.trim();
    }

    /**
     * 取得 理财类型
     * @return 理财类型
     */
    public String getFinaType() {
        return finaType;
    }

    /**
     * 设置 理财类型
     * @param finaType 理财类型
     */
    public void setFinaType(String finaType) {
        this.finaType = finaType == null ? null : finaType.trim();
    }

    /**
     * 取得 托管人账号
     * @return 托管人账号
     */
    public String getTruAccount() {
        return truAccount;
    }

    /**
     * 设置 托管人账号
     * @param truAccount 托管人账号
     */
    public void setTruAccount(String truAccount) {
        this.truAccount = truAccount == null ? null : truAccount.trim();
    }

    /**
     * 取得 托管人名称
     * @return 托管人名称
     */
    public String getAcctChnname() {
        return acctChnname;
    }

    /**
     * 设置 托管人名称
     * @param acctChnname 托管人名称
     */
    public void setAcctChnname(String acctChnname) {
        this.acctChnname = acctChnname == null ? null : acctChnname.trim();
    }

    /**
     * 取得 托管费率
     * @return 托管费率
     */
    public BigDecimal getTruFeeRage() {
        return truFeeRage;
    }

    /**
     * 设置 托管费率
     * @param truFeeRage 托管费率
     */
    public void setTruFeeRage(BigDecimal truFeeRage) {
        this.truFeeRage = truFeeRage;
    }

    /**
     * 取得 托管账户账号
     * @return 托管账户账号
     */
    public String getTruAccountNum() {
        return truAccountNum;
    }

    /**
     * 设置 托管账户账号
     * @param truAccountNum 托管账户账号
     */
    public void setTruAccountNum(String truAccountNum) {
        this.truAccountNum = truAccountNum == null ? null : truAccountNum.trim();
    }

    /**
     * 取得 托管账户户名
     * @return 托管账户户名
     */
    public String getTruAccountName() {
        return truAccountName;
    }

    /**
     * 设置 托管账户户名
     * @param truAccountName 托管账户户名
     */
    public void setTruAccountName(String truAccountName) {
        this.truAccountName = truAccountName == null ? null : truAccountName.trim();
    }

    /**
     * 取得 产品期限类型
     * @return 产品期限类型
     */
    public String getProdTermType() {
        return prodTermType;
    }

    /**
     * 设置 产品期限类型
     * @param prodTermType 产品期限类型
     */
    public void setProdTermType(String prodTermType) {
        this.prodTermType = prodTermType == null ? null : prodTermType.trim();
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 投资性质
     * @return 投资性质
     */
    public String getNatureInvest() {
        return natureInvest;
    }

    /**
     * 设置 投资性质
     * @param natureInvest 投资性质
     */
    public void setNatureInvest(String natureInvest) {
        this.natureInvest = natureInvest == null ? null : natureInvest.trim();
    }

    /**
     * 取得 销售对象
     * @return 销售对象
     */
    public String getTargetSale() {
        return targetSale;
    }

    /**
     * 设置 销售对象
     * @param targetSale 销售对象
     */
    public void setTargetSale(String targetSale) {
        this.targetSale = targetSale == null ? null : targetSale.trim();
    }

    /**
     * 取得 是否分层
     * @return 是否分层
     */
    public String getIsLayer() {
        return isLayer;
    }

    /**
     * 设置 是否分层
     * @param isLayer 是否分层
     */
    public void setIsLayer(String isLayer) {
        this.isLayer = isLayer == null ? null : isLayer.trim();
    }

    /**
     * 取得 业绩比较基准
     * @return 业绩比较基准
     */
    public String getPerformCompare() {
        return performCompare;
    }

    /**
     * 设置 业绩比较基准
     * @param performCompare 业绩比较基准
     */
    public void setPerformCompare(String performCompare) {
        this.performCompare = performCompare == null ? null : performCompare.trim();
    }

    /**
     * 取得 管理人
     * @return 管理人
     */
    public String getProManager() {
        return proManager;
    }

    /**
     * 设置 管理人
     * @param proManager 管理人
     */
    public void setProManager(String proManager) {
        this.proManager = proManager == null ? null : proManager.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getProCustodian() {
        return proCustodian;
    }

    /**
     * 设置 null
     * @param proCustodian null
     */
    public void setProCustodian(String proCustodian) {
        this.proCustodian = proCustodian == null ? null : proCustodian.trim();
    }

    /**
     * 取得 巨额赎回阀值
     * @return 巨额赎回阀值
     */
    public BigDecimal getHugeRedeemThreshold() {
        return hugeRedeemThreshold;
    }

    /**
     * 设置 巨额赎回阀值
     * @param hugeRedeemThreshold 巨额赎回阀值
     */
    public void setHugeRedeemThreshold(BigDecimal hugeRedeemThreshold) {
        this.hugeRedeemThreshold = hugeRedeemThreshold;
    }

    /**
     * 取得 起点销售金额
     * @return 起点销售金额
     */
    public BigDecimal getSaleAmtStr() {
        return saleAmtStr;
    }

    /**
     * 设置 起点销售金额
     * @param saleAmtStr 起点销售金额
     */
    public void setSaleAmtStr(BigDecimal saleAmtStr) {
        this.saleAmtStr = saleAmtStr;
    }

    /**
     * 取得 产品运作模式
     * @return 产品运作模式
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 设置 产品运作模式
     * @param operateMode 产品运作模式
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 取得 源系统金融产品代码
     * @return 源系统金融产品代码
     */
    public String getfProdId() {
        return fProdId;
    }

    /**
     * 设置 源系统金融产品代码
     * @param fProdId 源系统金融产品代码
     */
    public void setfProdId(String fProdId) {
        this.fProdId = fProdId == null ? null : fProdId.trim();
    }
}