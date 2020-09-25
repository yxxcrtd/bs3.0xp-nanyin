package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class AssetTargetDb extends AssetTargetDbKey {
    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 资产一级分类
     */
    private String pinanAssetType;

    /**
     * 资产二级分类
     */
    private String customAssetTypeTwo;

    /**
     * 资产三级分类
     */
    private String customAssetTypeThree;

    /**
     * 资产四级分类
     */
    private String customAssetTypeFour;

    /**
     * 资产五级分类
     */
    private String customAssetTypeFive;

    /**
     * 公允价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fairePrice;

    /**
     * 持仓数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal posAmount;

    /**
     * 名义本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prin;

    /**
     * 持仓成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal posCost;

    /**
     * 持仓市值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal posValue;

    /**
     * 应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexp;

    /**
     * 发行主体
     */
    private String issueParty;

    /**
     * 是否国企
     */
    private String isSoe;

    /**
     * 是否上市公司
     */
    private String isListedCompany;

    /**
     * 企业性质
     */
    private String companyNature;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 行业二级分类
     */
    private String industry2;

    /**
     * 资产起息日
     */
    private Date vdate;

    /**
     * 资产到期日
     */
    private Date mdate;

    /**
     * 期限（天）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal termDays;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 剩余期限（年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastPeriod;

    /**
     * 最近交易日期
     */
    private Date latestDealDate;

    /**
     * 持仓券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bshareFaceValue;

    /**
     * 发行券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueFaceValue;

    /**
     * 票面利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 综合成本收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal costRate;

    /**
     * 估价收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bpyield;

    /**
     * 久期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mDuration;

    /**
     * 修正久期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mduration;

    /**
     * 凸性
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mConvexity;

    /**
     * 含权信息
     */
    private String exInfo;

    /**
     * 下一行权日
     */
    private Date exNextDate;

    /**
     * 剩余行权期限（年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastExPeriod;

    /**
     * 主体评级
     */
    private String mGradeResult;

    /**
     * 主体评级机构
     */
    private String mGradeOrg;

    /**
     * 债项评级
     */
    private String bGradeResult;

    /**
     * 债项评级机构
     */
    private String bGradeOrg;

    /**
     * 主体评级（内部)
     */
    private String mGradeResultIn;

    /**
     * 债项评级（内部）
     */
    private String bGradeResultIn;

    /**
     * 是否城投债
     */
    private String isCityBond;

    /**
     * 所属地区
     */
    private String cityBondLev;

    /**
     * 流通股本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cShares;

    /**
     * 总股本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sShares;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 是否永续
     */
    private String isSus;

    /**
     * 是否含权债
     */
    private String isRight;

    /**
     * 含权摊销日(行权方式)
     */
    private String exDate;

    /**
     * 主体评级机构（内部）
     */
    private String mGradeOrgIn;

    /**
     * 债项评级机构(内部)
     */
    private String bGradeOrgIn;

    /**
     * 持仓规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bshareScale;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 集中度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal crn;

    /**
     * 资产风险等级
     */
    private String assetRiskType;

    
    /**
     * 基点价值 DV01
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dv01;
    
    /**
     * 浮盈浮亏（单价）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal floatIngplAddPrice;
    
    
    /**
     * 浮盈浮亏
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal floatIngplAdd;
    
    /**
     * 基点价值 DV01
     */
    public BigDecimal getDv01() {
		return dv01;
	}

    /**
     * 基点价值 DV01
     */
	public void setDv01(BigDecimal dv01) {
		this.dv01 = dv01;
	}

	 /**
     * 浮盈浮亏（单价）
     */
	public BigDecimal getFloatIngplAddPrice() {
		return floatIngplAddPrice;
	}

	 /**
     * 浮盈浮亏（单价）
     */
	public void setFloatIngplAddPrice(BigDecimal floatIngplAddPrice) {
		this.floatIngplAddPrice = floatIngplAddPrice;
	}

	/**
     * 浮盈浮亏
     */
	public BigDecimal getFloatIngplAdd() {
		return floatIngplAdd;
	}

	/**
     * 浮盈浮亏
     */
	public void setFloatIngplAdd(BigDecimal floatIngplAdd) {
		this.floatIngplAdd = floatIngplAdd;
	}

	/**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 资产代码
     * @param finprodMarketId 资产代码
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId == null ? null : finprodMarketId.trim();
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 资产名称
     * @param finprodName 资产名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 资产一级分类
     * @return 资产一级分类
     */
    public String getPinanAssetType() {
        return pinanAssetType;
    }

    /**
     * 设置 资产一级分类
     * @param pinanAssetType 资产一级分类
     */
    public void setPinanAssetType(String pinanAssetType) {
        this.pinanAssetType = pinanAssetType == null ? null : pinanAssetType.trim();
    }

    /**
     * 取得 资产二级分类
     * @return 资产二级分类
     */
    public String getCustomAssetTypeTwo() {
        return customAssetTypeTwo;
    }

    /**
     * 设置 资产二级分类
     * @param customAssetTypeTwo 资产二级分类
     */
    public void setCustomAssetTypeTwo(String customAssetTypeTwo) {
        this.customAssetTypeTwo = customAssetTypeTwo == null ? null : customAssetTypeTwo.trim();
    }

    /**
     * 取得 资产三级分类
     * @return 资产三级分类
     */
    public String getCustomAssetTypeThree() {
        return customAssetTypeThree;
    }

    /**
     * 设置 资产三级分类
     * @param customAssetTypeThree 资产三级分类
     */
    public void setCustomAssetTypeThree(String customAssetTypeThree) {
        this.customAssetTypeThree = customAssetTypeThree == null ? null : customAssetTypeThree.trim();
    }

    /**
     * 取得 资产四级分类
     * @return 资产四级分类
     */
    public String getCustomAssetTypeFour() {
        return customAssetTypeFour;
    }

    /**
     * 设置 资产四级分类
     * @param customAssetTypeFour 资产四级分类
     */
    public void setCustomAssetTypeFour(String customAssetTypeFour) {
        this.customAssetTypeFour = customAssetTypeFour == null ? null : customAssetTypeFour.trim();
    }

    /**
     * 取得 资产五级分类
     * @return 资产五级分类
     */
    public String getCustomAssetTypeFive() {
        return customAssetTypeFive;
    }

    /**
     * 设置 资产五级分类
     * @param customAssetTypeFive 资产五级分类
     */
    public void setCustomAssetTypeFive(String customAssetTypeFive) {
        this.customAssetTypeFive = customAssetTypeFive == null ? null : customAssetTypeFive.trim();
    }

    /**
     * 取得 公允价格
     * @return 公允价格
     */
    public BigDecimal getFairePrice() {
        return fairePrice;
    }

    /**
     * 设置 公允价格
     * @param fairePrice 公允价格
     */
    public void setFairePrice(BigDecimal fairePrice) {
        this.fairePrice = fairePrice;
    }

    /**
     * 取得 持仓数量
     * @return 持仓数量
     */
    public BigDecimal getPosAmount() {
        return posAmount;
    }

    /**
     * 设置 持仓数量
     * @param posAmount 持仓数量
     */
    public void setPosAmount(BigDecimal posAmount) {
        this.posAmount = posAmount;
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
     * 取得 持仓成本
     * @return 持仓成本
     */
    public BigDecimal getPosCost() {
        return posCost;
    }

    /**
     * 设置 持仓成本
     * @param posCost 持仓成本
     */
    public void setPosCost(BigDecimal posCost) {
        this.posCost = posCost;
    }

    /**
     * 取得 持仓市值
     * @return 持仓市值
     */
    public BigDecimal getPosValue() {
        return posValue;
    }

    /**
     * 设置 持仓市值
     * @param posValue 持仓市值
     */
    public void setPosValue(BigDecimal posValue) {
        this.posValue = posValue;
    }

    /**
     * 取得 应计利息
     * @return 应计利息
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    /**
     * 设置 应计利息
     * @param tdyIntincexp 应计利息
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    /**
     * 取得 发行主体
     * @return 发行主体
     */
    public String getIssueParty() {
        return issueParty;
    }

    /**
     * 设置 发行主体
     * @param issueParty 发行主体
     */
    public void setIssueParty(String issueParty) {
        this.issueParty = issueParty == null ? null : issueParty.trim();
    }

    /**
     * 取得 是否国企
     * @return 是否国企
     */
    public String getIsSoe() {
        return isSoe;
    }

    /**
     * 设置 是否国企
     * @param isSoe 是否国企
     */
    public void setIsSoe(String isSoe) {
        this.isSoe = isSoe == null ? null : isSoe.trim();
    }

    /**
     * 取得 是否上市公司
     * @return 是否上市公司
     */
    public String getIsListedCompany() {
        return isListedCompany;
    }

    /**
     * 设置 是否上市公司
     * @param isListedCompany 是否上市公司
     */
    public void setIsListedCompany(String isListedCompany) {
        this.isListedCompany = isListedCompany == null ? null : isListedCompany.trim();
    }

    /**
     * 取得 企业性质
     * @return 企业性质
     */
    public String getCompanyNature() {
        return companyNature;
    }

    /**
     * 设置 企业性质
     * @param companyNature 企业性质
     */
    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature == null ? null : companyNature.trim();
    }

    /**
     * 取得 所属行业
     * @return 所属行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 所属行业
     * @param industry 所属行业
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 取得 行业二级分类
     * @return 行业二级分类
     */
    public String getIndustry2() {
        return industry2;
    }

    /**
     * 设置 行业二级分类
     * @param industry2 行业二级分类
     */
    public void setIndustry2(String industry2) {
        this.industry2 = industry2 == null ? null : industry2.trim();
    }

    /**
     * 取得 资产起息日
     * @return 资产起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 资产起息日
     * @param vdate 资产起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 资产到期日
     * @return 资产到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 资产到期日
     * @param mdate 资产到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 期限（天）
     * @return 期限（天）
     */
    public BigDecimal getTermDays() {
        return termDays;
    }

    /**
     * 设置 期限（天）
     * @param termDays 期限（天）
     */
    public void setTermDays(BigDecimal termDays) {
        this.termDays = termDays;
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
     * 取得 剩余期限（年）
     * @return 剩余期限（年）
     */
    public BigDecimal getLastPeriod() {
        return lastPeriod;
    }

    /**
     * 设置 剩余期限（年）
     * @param lastPeriod 剩余期限（年）
     */
    public void setLastPeriod(BigDecimal lastPeriod) {
        this.lastPeriod = lastPeriod;
    }

    /**
     * 取得 最近交易日期
     * @return 最近交易日期
     */
    public Date getLatestDealDate() {
        return latestDealDate;
    }

    /**
     * 设置 最近交易日期
     * @param latestDealDate 最近交易日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLatestDealDate(Date latestDealDate) {
        this.latestDealDate = latestDealDate;
    }

    /**
     * 取得 持仓券面总额
     * @return 持仓券面总额
     */
    public BigDecimal getBshareFaceValue() {
        return bshareFaceValue;
    }

    /**
     * 设置 持仓券面总额
     * @param bshareFaceValue 持仓券面总额
     */
    public void setBshareFaceValue(BigDecimal bshareFaceValue) {
        this.bshareFaceValue = bshareFaceValue;
    }

    /**
     * 取得 发行券面总额
     * @return 发行券面总额
     */
    public BigDecimal getIssueFaceValue() {
        return issueFaceValue;
    }

    /**
     * 设置 发行券面总额
     * @param issueFaceValue 发行券面总额
     */
    public void setIssueFaceValue(BigDecimal issueFaceValue) {
        this.issueFaceValue = issueFaceValue;
    }

    /**
     * 取得 票面利率
     * @return 票面利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 票面利率
     * @param rate 票面利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 综合成本收益率
     * @return 综合成本收益率
     */
    public BigDecimal getCostRate() {
        return costRate;
    }

    /**
     * 设置 综合成本收益率
     * @param costRate 综合成本收益率
     */
    public void setCostRate(BigDecimal costRate) {
        this.costRate = costRate;
    }

    /**
     * 取得 估价收益率
     * @return 估价收益率
     */
    public BigDecimal getBpyield() {
        return bpyield;
    }

    /**
     * 设置 估价收益率
     * @param bpyield 估价收益率
     */
    public void setBpyield(BigDecimal bpyield) {
        this.bpyield = bpyield;
    }

    /**
     * 取得 久期
     * @return 久期
     */
    public BigDecimal getmDuration() {
        return mDuration;
    }

    /**
     * 设置 久期
     * @param mDuration 久期
     */
    public void setmDuration(BigDecimal mDuration) {
        this.mDuration = mDuration;
    }

    /**
     * 取得 修正久期
     * @return 修正久期
     */
    public BigDecimal getMduration() {
        return mduration;
    }

    /**
     * 设置 修正久期
     * @param mduration 修正久期
     */
    public void setMduration(BigDecimal mduration) {
        this.mduration = mduration;
    }

    /**
     * 取得 凸性
     * @return 凸性
     */
    public BigDecimal getmConvexity() {
        return mConvexity;
    }

    /**
     * 设置 凸性
     * @param mConvexity 凸性
     */
    public void setmConvexity(BigDecimal mConvexity) {
        this.mConvexity = mConvexity;
    }

    /**
     * 取得 含权信息
     * @return 含权信息
     */
    public String getExInfo() {
        return exInfo;
    }

    /**
     * 设置 含权信息
     * @param exInfo 含权信息
     */
    public void setExInfo(String exInfo) {
        this.exInfo = exInfo == null ? null : exInfo.trim();
    }

    /**
     * 取得 下一行权日
     * @return 下一行权日
     */
    public Date getExNextDate() {
        return exNextDate;
    }

    /**
     * 设置 下一行权日
     * @param exNextDate 下一行权日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setExNextDate(Date exNextDate) {
        this.exNextDate = exNextDate;
    }

    /**
     * 取得 剩余行权期限（年）
     * @return 剩余行权期限（年）
     */
    public BigDecimal getLastExPeriod() {
        return lastExPeriod;
    }

    /**
     * 设置 剩余行权期限（年）
     * @param lastExPeriod 剩余行权期限（年）
     */
    public void setLastExPeriod(BigDecimal lastExPeriod) {
        this.lastExPeriod = lastExPeriod;
    }

    /**
     * 取得 主体评级
     * @return 主体评级
     */
    public String getmGradeResult() {
        return mGradeResult;
    }

    /**
     * 设置 主体评级
     * @param mGradeResult 主体评级
     */
    public void setmGradeResult(String mGradeResult) {
        this.mGradeResult = mGradeResult == null ? null : mGradeResult.trim();
    }

    /**
     * 取得 主体评级机构
     * @return 主体评级机构
     */
    public String getmGradeOrg() {
        return mGradeOrg;
    }

    /**
     * 设置 主体评级机构
     * @param mGradeOrg 主体评级机构
     */
    public void setmGradeOrg(String mGradeOrg) {
        this.mGradeOrg = mGradeOrg == null ? null : mGradeOrg.trim();
    }

    /**
     * 取得 债项评级
     * @return 债项评级
     */
    public String getbGradeResult() {
        return bGradeResult;
    }

    /**
     * 设置 债项评级
     * @param bGradeResult 债项评级
     */
    public void setbGradeResult(String bGradeResult) {
        this.bGradeResult = bGradeResult == null ? null : bGradeResult.trim();
    }

    /**
     * 取得 债项评级机构
     * @return 债项评级机构
     */
    public String getbGradeOrg() {
        return bGradeOrg;
    }

    /**
     * 设置 债项评级机构
     * @param bGradeOrg 债项评级机构
     */
    public void setbGradeOrg(String bGradeOrg) {
        this.bGradeOrg = bGradeOrg == null ? null : bGradeOrg.trim();
    }

    /**
     * 取得 主体评级（内部)
     * @return 主体评级（内部)
     */
    public String getmGradeResultIn() {
        return mGradeResultIn;
    }

    /**
     * 设置 主体评级（内部)
     * @param mGradeResultIn 主体评级（内部)
     */
    public void setmGradeResultIn(String mGradeResultIn) {
        this.mGradeResultIn = mGradeResultIn == null ? null : mGradeResultIn.trim();
    }

    /**
     * 取得 债项评级（内部）
     * @return 债项评级（内部）
     */
    public String getbGradeResultIn() {
        return bGradeResultIn;
    }

    /**
     * 设置 债项评级（内部）
     * @param bGradeResultIn 债项评级（内部）
     */
    public void setbGradeResultIn(String bGradeResultIn) {
        this.bGradeResultIn = bGradeResultIn == null ? null : bGradeResultIn.trim();
    }

    /**
     * 取得 是否城投债
     * @return 是否城投债
     */
    public String getIsCityBond() {
        return isCityBond;
    }

    /**
     * 设置 是否城投债
     * @param isCityBond 是否城投债
     */
    public void setIsCityBond(String isCityBond) {
        this.isCityBond = isCityBond == null ? null : isCityBond.trim();
    }

    /**
     * 取得 所属地区
     * @return 所属地区
     */
    public String getCityBondLev() {
        return cityBondLev;
    }

    /**
     * 设置 所属地区
     * @param cityBondLev 所属地区
     */
    public void setCityBondLev(String cityBondLev) {
        this.cityBondLev = cityBondLev == null ? null : cityBondLev.trim();
    }

    /**
     * 取得 流通股本
     * @return 流通股本
     */
    public BigDecimal getcShares() {
        return cShares;
    }

    /**
     * 设置 流通股本
     * @param cShares 流通股本
     */
    public void setcShares(BigDecimal cShares) {
        this.cShares = cShares;
    }

    /**
     * 取得 总股本
     * @return 总股本
     */
    public BigDecimal getsShares() {
        return sShares;
    }

    /**
     * 设置 总股本
     * @param sShares 总股本
     */
    public void setsShares(BigDecimal sShares) {
        this.sShares = sShares;
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
     * 取得 是否永续
     * @return 是否永续
     */
    public String getIsSus() {
        return isSus;
    }

    /**
     * 设置 是否永续
     * @param isSus 是否永续
     */
    public void setIsSus(String isSus) {
        this.isSus = isSus == null ? null : isSus.trim();
    }

    /**
     * 取得 是否含权债
     * @return 是否含权债
     */
    public String getIsRight() {
        return isRight;
    }

    /**
     * 设置 是否含权债
     * @param isRight 是否含权债
     */
    public void setIsRight(String isRight) {
        this.isRight = isRight == null ? null : isRight.trim();
    }

    /**
     * 取得 含权摊销日(行权方式)
     * @return 含权摊销日(行权方式)
     */
    public String getExDate() {
        return exDate;
    }

    /**
     * 设置 含权摊销日(行权方式)
     * @param exDate 含权摊销日(行权方式)
     */
    public void setExDate(String exDate) {
        this.exDate = exDate == null ? null : exDate.trim();
    }

    /**
     * 取得 主体评级机构（内部）
     * @return 主体评级机构（内部）
     */
    public String getmGradeOrgIn() {
        return mGradeOrgIn;
    }

    /**
     * 设置 主体评级机构（内部）
     * @param mGradeOrgIn 主体评级机构（内部）
     */
    public void setmGradeOrgIn(String mGradeOrgIn) {
        this.mGradeOrgIn = mGradeOrgIn == null ? null : mGradeOrgIn.trim();
    }

    /**
     * 取得 债项评级机构(内部)
     * @return 债项评级机构(内部)
     */
    public String getbGradeOrgIn() {
        return bGradeOrgIn;
    }

    /**
     * 设置 债项评级机构(内部)
     * @param bGradeOrgIn 债项评级机构(内部)
     */
    public void setbGradeOrgIn(String bGradeOrgIn) {
        this.bGradeOrgIn = bGradeOrgIn == null ? null : bGradeOrgIn.trim();
    }

    /**
     * 取得 持仓规模
     * @return 持仓规模
     */
    public BigDecimal getBshareScale() {
        return bshareScale;
    }

    /**
     * 设置 持仓规模
     * @param bshareScale 持仓规模
     */
    public void setBshareScale(BigDecimal bshareScale) {
        this.bshareScale = bshareScale;
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
     * 取得 集中度
     * @return 集中度
     */
    public BigDecimal getCrn() {
        return crn;
    }

    /**
     * 设置 集中度
     * @param crn 集中度
     */
    public void setCrn(BigDecimal crn) {
        this.crn = crn;
    }

    /**
     * 取得 资产风险等级
     * @return 资产风险等级
     */
    public String getAssetRiskType() {
        return assetRiskType;
    }

    /**
     * 设置 资产风险等级
     * @param assetRiskType 资产风险等级
     */
    public void setAssetRiskType(String assetRiskType) {
        this.assetRiskType = assetRiskType == null ? null : assetRiskType.trim();
    }
}