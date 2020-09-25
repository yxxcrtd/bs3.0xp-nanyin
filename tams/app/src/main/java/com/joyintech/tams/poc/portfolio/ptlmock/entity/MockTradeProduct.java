package com.joyintech.tams.poc.portfolio.ptlmock.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MockTradeProduct extends MockTradeProductKey {
    /**
     * 金融产品类型（估值核算），回购、拆借、利率互换等
     */
    private String finprodType;

    /**
     * 金融产品类型（投管分类），回购、拆入、拆出、特拆拆入、利率互换、理财产品子产品等
     */
    private String finprodType2;

    /**
     * 收益类型，净值、预期收益、货币、结构
     */
    private String profitType;

    /**
     * 分支类型
     */
    private String branchType;

    /**
     * 通道代码
     */
    private String chlAgrtId;

    /**
     * 名义本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prin;

    /**
     * 本金币种
     */
    private String ccy;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 期限(天)
     */
    private Long term;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 固定利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 浮动利率基准编号
     */
    private String intRateId;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 到期本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mPrinAmt;

    /**
     * 到期利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mIntAmt;

    /**
     * 到期金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mTradeAmt;

    /**
     * 本金收益特征，保本、非保本
     */
    private String capiIncomeFeature;

    /**
     * 原金融产品代码，发生标的转换时用
     */
    private String oFinprodId;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mUnitCprice;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mUnitInt;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mUnitFprice;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mYield;

    /**
     * null
     */
    private String mDeliveryType;

    /**
     * 首期交付日
     */
    private Date vpayDate;

    /**
     * 到期交付日
     */
    private Date mpayDate;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 实际占款天数
     */
    private Long actCapDays;

    /**
     * 互换方式
     */
    private String irsType;

    /**
     * 外汇交易方向
     */
    private String excPs;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 掉期期限类型
     */
    private String excFxsTermType;

    /**
     * 到期折美元金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal usdAmt;

    /**
     * 差额交割参考汇率
     */
    private String difSettleRefRate;

    /**
     * 争议解决方式
     */
    private String conflictSolveWay;

    /**
     * 期次代码
     */
    private String periodId;

    /**
     * 截止日是否冲回
     */
    private String isRushBack;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 取得 金融产品类型（估值核算），回购、拆借、利率互换等
     * @return 金融产品类型（估值核算），回购、拆借、利率互换等
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型（估值核算），回购、拆借、利率互换等
     * @param finprodType 金融产品类型（估值核算），回购、拆借、利率互换等
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 金融产品类型（投管分类），回购、拆入、拆出、特拆拆入、利率互换、理财产品子产品等
     * @return 金融产品类型（投管分类），回购、拆入、拆出、特拆拆入、利率互换、理财产品子产品等
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 金融产品类型（投管分类），回购、拆入、拆出、特拆拆入、利率互换、理财产品子产品等
     * @param finprodType2 金融产品类型（投管分类），回购、拆入、拆出、特拆拆入、利率互换、理财产品子产品等
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 收益类型，净值、预期收益、货币、结构
     * @return 收益类型，净值、预期收益、货币、结构
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型，净值、预期收益、货币、结构
     * @param profitType 收益类型，净值、预期收益、货币、结构
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 分支类型
     * @return 分支类型
     */
    public String getBranchType() {
        return branchType;
    }

    /**
     * 设置 分支类型
     * @param branchType 分支类型
     */
    public void setBranchType(String branchType) {
        this.branchType = branchType == null ? null : branchType.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlAgrtId() {
        return chlAgrtId;
    }

    /**
     * 设置 通道代码
     * @param chlAgrtId 通道代码
     */
    public void setChlAgrtId(String chlAgrtId) {
        this.chlAgrtId = chlAgrtId == null ? null : chlAgrtId.trim();
    }

    /**
     * 取得 名义本金
     * @return 名义本金
     */
    public BigDecimal getPrin() {
        return prin;
    }

    /**
     * 设置 名义本金
     * @param prin 名义本金
     */
    public void setPrin(BigDecimal prin) {
        this.prin = prin;
    }

    /**
     * 取得 本金币种
     * @return 本金币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 本金币种
     * @param ccy 本金币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
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
     * 取得 期限(天)
     * @return 期限(天)
     */
    public Long getTerm() {
        return term;
    }

    /**
     * 设置 期限(天)
     * @param term 期限(天)
     */
    public void setTerm(Long term) {
        this.term = term;
    }

    /**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型
     * @param intType 利率类型
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
    }

    /**
     * 取得 固定利率
     * @return 固定利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 固定利率
     * @param intRate 固定利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 浮动利率基准编号
     * @return 浮动利率基准编号
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 浮动利率基准编号
     * @param intRateId 浮动利率基准编号
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
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
     * 取得 到期本金
     * @return 到期本金
     */
    public BigDecimal getmPrinAmt() {
        return mPrinAmt;
    }

    /**
     * 设置 到期本金
     * @param mPrinAmt 到期本金
     */
    public void setmPrinAmt(BigDecimal mPrinAmt) {
        this.mPrinAmt = mPrinAmt;
    }

    /**
     * 取得 到期利息
     * @return 到期利息
     */
    public BigDecimal getmIntAmt() {
        return mIntAmt;
    }

    /**
     * 设置 到期利息
     * @param mIntAmt 到期利息
     */
    public void setmIntAmt(BigDecimal mIntAmt) {
        this.mIntAmt = mIntAmt;
    }

    /**
     * 取得 到期金额
     * @return 到期金额
     */
    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    /**
     * 设置 到期金额
     * @param mTradeAmt 到期金额
     */
    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    /**
     * 取得 本金收益特征，保本、非保本
     * @return 本金收益特征，保本、非保本
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 本金收益特征，保本、非保本
     * @param capiIncomeFeature 本金收益特征，保本、非保本
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature == null ? null : capiIncomeFeature.trim();
    }

    /**
     * 取得 原金融产品代码，发生标的转换时用
     * @return 原金融产品代码，发生标的转换时用
     */
    public String getoFinprodId() {
        return oFinprodId;
    }

    /**
     * 设置 原金融产品代码，发生标的转换时用
     * @param oFinprodId 原金融产品代码，发生标的转换时用
     */
    public void setoFinprodId(String oFinprodId) {
        this.oFinprodId = oFinprodId == null ? null : oFinprodId.trim();
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 交易日历
     * @return 交易日历
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId == null ? null : calendarId.trim();
    }

    /**
     * 取得 期限品种
     * @return 期限品种
     */
    public String getTermType() {
        return termType;
    }

    /**
     * 设置 期限品种
     * @param termType 期限品种
     */
    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    /**
     * 取得 交易对手
     * @return 交易对手
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 交易对手
     * @param counterId 交易对手
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId == null ? null : counterId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getmUnitCprice() {
        return mUnitCprice;
    }

    /**
     * 设置 null
     * @param mUnitCprice null
     */
    public void setmUnitCprice(BigDecimal mUnitCprice) {
        this.mUnitCprice = mUnitCprice;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getmUnitInt() {
        return mUnitInt;
    }

    /**
     * 设置 null
     * @param mUnitInt null
     */
    public void setmUnitInt(BigDecimal mUnitInt) {
        this.mUnitInt = mUnitInt;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getmUnitFprice() {
        return mUnitFprice;
    }

    /**
     * 设置 null
     * @param mUnitFprice null
     */
    public void setmUnitFprice(BigDecimal mUnitFprice) {
        this.mUnitFprice = mUnitFprice;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getmYield() {
        return mYield;
    }

    /**
     * 设置 null
     * @param mYield null
     */
    public void setmYield(BigDecimal mYield) {
        this.mYield = mYield;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getmDeliveryType() {
        return mDeliveryType;
    }

    /**
     * 设置 null
     * @param mDeliveryType null
     */
    public void setmDeliveryType(String mDeliveryType) {
        this.mDeliveryType = mDeliveryType == null ? null : mDeliveryType.trim();
    }

    /**
     * 取得 首期交付日
     * @return 首期交付日
     */
    public Date getVpayDate() {
        return vpayDate;
    }

    /**
     * 设置 首期交付日
     * @param vpayDate 首期交付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVpayDate(Date vpayDate) {
        this.vpayDate = vpayDate;
    }

    /**
     * 取得 到期交付日
     * @return 到期交付日
     */
    public Date getMpayDate() {
        return mpayDate;
    }

    /**
     * 设置 到期交付日
     * @param mpayDate 到期交付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMpayDate(Date mpayDate) {
        this.mpayDate = mpayDate;
    }

    /**
     * 取得 合同编号
     * @return 合同编号
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置 合同编号
     * @param contractNo 合同编号
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    /**
     * 取得 实际占款天数
     * @return 实际占款天数
     */
    public Long getActCapDays() {
        return actCapDays;
    }

    /**
     * 设置 实际占款天数
     * @param actCapDays 实际占款天数
     */
    public void setActCapDays(Long actCapDays) {
        this.actCapDays = actCapDays;
    }

    /**
     * 取得 互换方式
     * @return 互换方式
     */
    public String getIrsType() {
        return irsType;
    }

    /**
     * 设置 互换方式
     * @param irsType 互换方式
     */
    public void setIrsType(String irsType) {
        this.irsType = irsType == null ? null : irsType.trim();
    }

    /**
     * 取得 外汇交易方向
     * @return 外汇交易方向
     */
    public String getExcPs() {
        return excPs;
    }

    /**
     * 设置 外汇交易方向
     * @param excPs 外汇交易方向
     */
    public void setExcPs(String excPs) {
        this.excPs = excPs == null ? null : excPs.trim();
    }

    /**
     * 取得 货币对
     * @return 货币对
     */
    public String getCurPair() {
        return curPair;
    }

    /**
     * 设置 货币对
     * @param curPair 货币对
     */
    public void setCurPair(String curPair) {
        this.curPair = curPair == null ? null : curPair.trim();
    }

    /**
     * 取得 掉期期限类型
     * @return 掉期期限类型
     */
    public String getExcFxsTermType() {
        return excFxsTermType;
    }

    /**
     * 设置 掉期期限类型
     * @param excFxsTermType 掉期期限类型
     */
    public void setExcFxsTermType(String excFxsTermType) {
        this.excFxsTermType = excFxsTermType == null ? null : excFxsTermType.trim();
    }

    /**
     * 取得 到期折美元金额
     * @return 到期折美元金额
     */
    public BigDecimal getUsdAmt() {
        return usdAmt;
    }

    /**
     * 设置 到期折美元金额
     * @param usdAmt 到期折美元金额
     */
    public void setUsdAmt(BigDecimal usdAmt) {
        this.usdAmt = usdAmt;
    }

    /**
     * 取得 差额交割参考汇率
     * @return 差额交割参考汇率
     */
    public String getDifSettleRefRate() {
        return difSettleRefRate;
    }

    /**
     * 设置 差额交割参考汇率
     * @param difSettleRefRate 差额交割参考汇率
     */
    public void setDifSettleRefRate(String difSettleRefRate) {
        this.difSettleRefRate = difSettleRefRate == null ? null : difSettleRefRate.trim();
    }

    /**
     * 取得 争议解决方式
     * @return 争议解决方式
     */
    public String getConflictSolveWay() {
        return conflictSolveWay;
    }

    /**
     * 设置 争议解决方式
     * @param conflictSolveWay 争议解决方式
     */
    public void setConflictSolveWay(String conflictSolveWay) {
        this.conflictSolveWay = conflictSolveWay == null ? null : conflictSolveWay.trim();
    }

    /**
     * 取得 期次代码
     * @return 期次代码
     */
    public String getPeriodId() {
        return periodId;
    }

    /**
     * 设置 期次代码
     * @param periodId 期次代码
     */
    public void setPeriodId(String periodId) {
        this.periodId = periodId == null ? null : periodId.trim();
    }

    /**
     * 取得 截止日是否冲回
     * @return 截止日是否冲回
     */
    public String getIsRushBack() {
        return isRushBack;
    }

    /**
     * 设置 截止日是否冲回
     * @param isRushBack 截止日是否冲回
     */
    public void setIsRushBack(String isRushBack) {
        this.isRushBack = isRushBack == null ? null : isRushBack.trim();
    }

    /**
     * 取得 合同名称
     * @return 合同名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 设置 合同名称
     * @param contractName 合同名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }
}