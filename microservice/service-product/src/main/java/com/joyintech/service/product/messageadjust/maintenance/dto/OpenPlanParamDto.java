package com.joyintech.service.product.messageadjust.maintenance.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yxf 
 * 2019-12-3
 * 开放计划信息参数查询
 */
public class OpenPlanParamDto {

	private String cashId;
	
	private String openType;
	
	private String branch;
	
	private String cpriceRule;
	/**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 销售部门，零售产品管理部、金融同业部、对公产品和现金管理部
     */
    private String saleDepartment;

    /**
     * 销售分层
     */
    private String saleLayer;

    /**
     * 销售期次
     */
    private Long salePeriod;

    /**
     * 申购确认速度，如果资产只有一个确认速度，则存该字段
     */
    private Long purSpeed;

    /**
     * 赎回确认速度
     */
    private Long redSpeed;

    /**
     * 是否次级债
     */
    private String isSecBond;

    /**
     * WIND对象ID
     */
    private String windId;

    /**
     * 是否接口自动更新
     */
    private String isAutoUpdate;

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 是否周期型
     */
    private String isCycle;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 分层类型，金额分层、期限分层、客户类型
     */
    private String layType;

    /**
     * 投资性质，固定收益类、权益类、商品及金融衍生品类、混合类
     */
    private String investNature;

    /**
     * 收益标识，非保本浮动收益型、保本浮动、保证收益型
     */
    private String profitFlag;

    /**
     * 销售模式，直销、代销、委托
     */
    private String saleMode;

    /**
     * 中债报送编码
     */
    private String chbId;

    /**
     * 产品模板代码
     */
    private String tmplId;

    /**
     * 封闭期限(天)，产品每次开放对应的封闭期限
     */
    private Long closeDays;

    /**
     * 产品发行状态
     */
    private String issueStatus;

    /**
     * 发行状态备注
     */
    private String issueStatusRemark;

    /**
     * 是否按日复利
     */
    private String isCompoundInt;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 面值，债券单位面值
     */
//    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal faceValue;

    /**
     * 划款方式，一次性划款，逐笔划款
     */
    private String payType;

    /**
     * ????
     */
    private String isReconciliation;

    /**
     * 是否现金管理类
     */
    private String isCashManage;

    /**
     * 产品风险等级，一级（低）、二级（中低）、三级（中）、四级（中高）、五级（高）

     */
    private String riskLevel;

    /**
     * 处理模式，普通，分档，周期型
     */
    private String dealMode;

    /**
     * 是否专属产品
     */
    private String isExclusive;

    /**
     * 投标日期，一级市场债使用
     */
    private Date bidDate;

    /**
     * 上市板
     */
    private String board;

    /**
     * 是否沪港通或深港通
     */
    private String shOrZh;

    /**
     * 是否融资融券标的
     */
    private String isMarginFinprod;

    /**
     * 是否城投债
     */
    private String isCityBond;

    /**
     * 城投债级别
     */
    private String cityBondLev;

    /**
     * 收取方式
     */
    private String collectType;

    /**
     * 监管评级
     */
    private String regulatoryRating;

    /**
     * 产品经理
     */
    private String prodManager;

    /**
     * 投资周期
     */
    private String investmentCycle;

    /**
     * 补充条款
     */
    private String addition;

    /**
     * 质押券置换安排
     */
    private String isPledgeRep;

    /**
     * 认购起始日
     */
    private Date toffStrdate;

    /**
     * 认购终止日
     */
    private Date toffEnddate;

    /**
     * 交易所基金简称
     */
    private String trmFundAbbr;

    /**
     * 交易所基金类型
     */
    private String trmFundType;

    /**
     * 分级基金
     */
    private String graFundType;

    /**
     * 申购赎回代码
     */
    private String purRedId;

    /**
     * 申购赎回简称
     */
    private String purRedAbbr;

    /**
     * 单笔申购金额下限
     */
//    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal purStartLow;

    /**
     * 单笔赎回份额下限
     */
//    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redStartLow;

    /**
     * ETF类型
     */
    private String etfType;

    /**
     * ETF最小申购赎回单位
     */
//    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal etfMinPrunit;

    /**
     * 产品设计系列编号
     */
    private String prdDesignId;

    /**
     * 金融产品类型（估值核算）
     */
    private String finprodType;

    /**
     * 金融产品类型（投管分类）
     */
    private String finprodType2;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 息票品种
     */
    private String couponSpecies;

    /**
     * 通道代码
     */
    private String chlFinprodId;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 发行认购代码
     */
    private String issueId;

    /**
     * 发行价
     */
//    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issuePrice;

    /**
     * 发行规模
     */
//    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 境内外
     */
    private String blnArea;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 发行方式/募集方式
     */
    private String issueType;

    /**
     * 运作模式
     */
    private String operationType;

    /**
     * 委托方式
     */
    private String entrustType;

    /**
     * 委托方
     */
    private String entruster;

    /**
     * 托管人
     */
    private String trusteeId;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 管理人
     */
    private String manager;

    /**
     * 融资人
     */
    private String financier;

    /**
     * 发行日
     */
    private Date idate;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 期限天数
     */
    private Long termDays;

    /**
     * 实际到期日
     */
    private Date actmdate;

    /**
     * 清盘日
     */
    private Date liquidationDate;

    /**
     * 是否通道
     */
    private String isChl;

    /**
     * 是否永续
     */
    private String isSus;

    /**
     * 永续条款
     */
    private String sustainableRemark;

    /**
     * 是否含权
     */
    private String isRight;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 原金融产品代码
     */
    private String oFinprodId;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 含权债摊销日
     */
    private String exeDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 是否金融机构发行
     */
    private String isFinOrg;

    /**
     * 担保人
     */
    private String sponsor;

    /**
     * 投资顾问
     */
    private String investAdviser;

    /**
     * 是否轧差清算
     */
    private String liquidationYesno;

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getSaleDepartment() {
		return saleDepartment;
	}

	public void setSaleDepartment(String saleDepartment) {
		this.saleDepartment = saleDepartment;
	}

	public String getSaleLayer() {
		return saleLayer;
	}

	public void setSaleLayer(String saleLayer) {
		this.saleLayer = saleLayer;
	}

	public Long getSalePeriod() {
		return salePeriod;
	}

	public void setSalePeriod(Long salePeriod) {
		this.salePeriod = salePeriod;
	}

	public Long getPurSpeed() {
		return purSpeed;
	}

	public void setPurSpeed(Long purSpeed) {
		this.purSpeed = purSpeed;
	}

	public Long getRedSpeed() {
		return redSpeed;
	}

	public void setRedSpeed(Long redSpeed) {
		this.redSpeed = redSpeed;
	}

	public String getIsSecBond() {
		return isSecBond;
	}

	public void setIsSecBond(String isSecBond) {
		this.isSecBond = isSecBond;
	}

	public String getWindId() {
		return windId;
	}

	public void setWindId(String windId) {
		this.windId = windId;
	}

	public String getIsAutoUpdate() {
		return isAutoUpdate;
	}

	public void setIsAutoUpdate(String isAutoUpdate) {
		this.isAutoUpdate = isAutoUpdate;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getProdRegistCode() {
		return prodRegistCode;
	}

	public void setProdRegistCode(String prodRegistCode) {
		this.prodRegistCode = prodRegistCode;
	}

	public String getIsCycle() {
		return isCycle;
	}

	public void setIsCycle(String isCycle) {
		this.isCycle = isCycle;
	}

	public String getIsLay() {
		return isLay;
	}

	public void setIsLay(String isLay) {
		this.isLay = isLay;
	}

	public String getLayType() {
		return layType;
	}

	public void setLayType(String layType) {
		this.layType = layType;
	}

	public String getInvestNature() {
		return investNature;
	}

	public void setInvestNature(String investNature) {
		this.investNature = investNature;
	}

	public String getProfitFlag() {
		return profitFlag;
	}

	public void setProfitFlag(String profitFlag) {
		this.profitFlag = profitFlag;
	}

	public String getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(String saleMode) {
		this.saleMode = saleMode;
	}

	public String getChbId() {
		return chbId;
	}

	public void setChbId(String chbId) {
		this.chbId = chbId;
	}

	public String getTmplId() {
		return tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public Long getCloseDays() {
		return closeDays;
	}

	public void setCloseDays(Long closeDays) {
		this.closeDays = closeDays;
	}

	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public String getIssueStatusRemark() {
		return issueStatusRemark;
	}

	public void setIssueStatusRemark(String issueStatusRemark) {
		this.issueStatusRemark = issueStatusRemark;
	}

	public String getIsCompoundInt() {
		return isCompoundInt;
	}

	public void setIsCompoundInt(String isCompoundInt) {
		this.isCompoundInt = isCompoundInt;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public BigDecimal getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIsReconciliation() {
		return isReconciliation;
	}

	public void setIsReconciliation(String isReconciliation) {
		this.isReconciliation = isReconciliation;
	}

	public String getIsCashManage() {
		return isCashManage;
	}

	public void setIsCashManage(String isCashManage) {
		this.isCashManage = isCashManage;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	public String getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(String isExclusive) {
		this.isExclusive = isExclusive;
	}

	public Date getBidDate() {
		return bidDate;
	}

	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getShOrZh() {
		return shOrZh;
	}

	public void setShOrZh(String shOrZh) {
		this.shOrZh = shOrZh;
	}

	public String getIsMarginFinprod() {
		return isMarginFinprod;
	}

	public void setIsMarginFinprod(String isMarginFinprod) {
		this.isMarginFinprod = isMarginFinprod;
	}

	public String getIsCityBond() {
		return isCityBond;
	}

	public void setIsCityBond(String isCityBond) {
		this.isCityBond = isCityBond;
	}

	public String getCityBondLev() {
		return cityBondLev;
	}

	public void setCityBondLev(String cityBondLev) {
		this.cityBondLev = cityBondLev;
	}

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

	public String getRegulatoryRating() {
		return regulatoryRating;
	}

	public void setRegulatoryRating(String regulatoryRating) {
		this.regulatoryRating = regulatoryRating;
	}

	public String getProdManager() {
		return prodManager;
	}

	public void setProdManager(String prodManager) {
		this.prodManager = prodManager;
	}

	public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}

	public String getAddition() {
		return addition;
	}

	public void setAddition(String addition) {
		this.addition = addition;
	}

	public String getIsPledgeRep() {
		return isPledgeRep;
	}

	public void setIsPledgeRep(String isPledgeRep) {
		this.isPledgeRep = isPledgeRep;
	}

	public Date getToffStrdate() {
		return toffStrdate;
	}

	public void setToffStrdate(Date toffStrdate) {
		this.toffStrdate = toffStrdate;
	}

	public Date getToffEnddate() {
		return toffEnddate;
	}

	public void setToffEnddate(Date toffEnddate) {
		this.toffEnddate = toffEnddate;
	}

	public String getTrmFundAbbr() {
		return trmFundAbbr;
	}

	public void setTrmFundAbbr(String trmFundAbbr) {
		this.trmFundAbbr = trmFundAbbr;
	}

	public String getTrmFundType() {
		return trmFundType;
	}

	public void setTrmFundType(String trmFundType) {
		this.trmFundType = trmFundType;
	}

	public String getGraFundType() {
		return graFundType;
	}

	public void setGraFundType(String graFundType) {
		this.graFundType = graFundType;
	}

	public String getPurRedId() {
		return purRedId;
	}

	public void setPurRedId(String purRedId) {
		this.purRedId = purRedId;
	}

	public String getPurRedAbbr() {
		return purRedAbbr;
	}

	public void setPurRedAbbr(String purRedAbbr) {
		this.purRedAbbr = purRedAbbr;
	}

	public BigDecimal getPurStartLow() {
		return purStartLow;
	}

	public void setPurStartLow(BigDecimal purStartLow) {
		this.purStartLow = purStartLow;
	}

	public BigDecimal getRedStartLow() {
		return redStartLow;
	}

	public void setRedStartLow(BigDecimal redStartLow) {
		this.redStartLow = redStartLow;
	}

	public String getEtfType() {
		return etfType;
	}

	public void setEtfType(String etfType) {
		this.etfType = etfType;
	}

	public BigDecimal getEtfMinPrunit() {
		return etfMinPrunit;
	}

	public void setEtfMinPrunit(BigDecimal etfMinPrunit) {
		this.etfMinPrunit = etfMinPrunit;
	}

	public String getPrdDesignId() {
		return prdDesignId;
	}

	public void setPrdDesignId(String prdDesignId) {
		this.prdDesignId = prdDesignId;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getFinprodType2() {
		return finprodType2;
	}

	public void setFinprodType2(String finprodType2) {
		this.finprodType2 = finprodType2;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getCouponSpecies() {
		return couponSpecies;
	}

	public void setCouponSpecies(String couponSpecies) {
		this.couponSpecies = couponSpecies;
	}

	public String getChlFinprodId() {
		return chlFinprodId;
	}

	public void setChlFinprodId(String chlFinprodId) {
		this.chlFinprodId = chlFinprodId;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	public BigDecimal getIssuePrice() {
		return issuePrice;
	}

	public void setIssuePrice(BigDecimal issuePrice) {
		this.issuePrice = issuePrice;
	}

	public BigDecimal getIssueAmt() {
		return issueAmt;
	}

	public void setIssueAmt(BigDecimal issueAmt) {
		this.issueAmt = issueAmt;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getBlnArea() {
		return blnArea;
	}

	public void setBlnArea(String blnArea) {
		this.blnArea = blnArea;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getEntrustType() {
		return entrustType;
	}

	public void setEntrustType(String entrustType) {
		this.entrustType = entrustType;
	}

	public String getEntruster() {
		return entruster;
	}

	public void setEntruster(String entruster) {
		this.entruster = entruster;
	}

	public String getTrusteeId() {
		return trusteeId;
	}

	public void setTrusteeId(String trusteeId) {
		this.trusteeId = trusteeId;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getFinancier() {
		return financier;
	}

	public void setFinancier(String financier) {
		this.financier = financier;
	}

	public Date getIdate() {
		return idate;
	}

	public void setIdate(Date idate) {
		this.idate = idate;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public Long getTermDays() {
		return termDays;
	}

	public void setTermDays(Long termDays) {
		this.termDays = termDays;
	}

	public Date getActmdate() {
		return actmdate;
	}

	public void setActmdate(Date actmdate) {
		this.actmdate = actmdate;
	}

	public Date getLiquidationDate() {
		return liquidationDate;
	}

	public void setLiquidationDate(Date liquidationDate) {
		this.liquidationDate = liquidationDate;
	}

	public String getIsChl() {
		return isChl;
	}

	public void setIsChl(String isChl) {
		this.isChl = isChl;
	}

	public String getIsSus() {
		return isSus;
	}

	public void setIsSus(String isSus) {
		this.isSus = isSus;
	}

	public String getSustainableRemark() {
		return sustainableRemark;
	}

	public void setSustainableRemark(String sustainableRemark) {
		this.sustainableRemark = sustainableRemark;
	}

	public String getIsRight() {
		return isRight;
	}

	public void setIsRight(String isRight) {
		this.isRight = isRight;
	}

	public String getCapiIncomeFeature() {
		return capiIncomeFeature;
	}

	public void setCapiIncomeFeature(String capiIncomeFeature) {
		this.capiIncomeFeature = capiIncomeFeature;
	}

	public String getpFinprodId() {
		return pFinprodId;
	}

	public void setpFinprodId(String pFinprodId) {
		this.pFinprodId = pFinprodId;
	}

	public String getoFinprodId() {
		return oFinprodId;
	}

	public void setoFinprodId(String oFinprodId) {
		this.oFinprodId = oFinprodId;
	}

	public String getRegistOrg() {
		return registOrg;
	}

	public void setRegistOrg(String registOrg) {
		this.registOrg = registOrg;
	}

	public String getExeDate() {
		return exeDate;
	}

	public void setExeDate(String exeDate) {
		this.exeDate = exeDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getIsFinOrg() {
		return isFinOrg;
	}

	public void setIsFinOrg(String isFinOrg) {
		this.isFinOrg = isFinOrg;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getInvestAdviser() {
		return investAdviser;
	}

	public void setInvestAdviser(String investAdviser) {
		this.investAdviser = investAdviser;
	}

	public String getLiquidationYesno() {
		return liquidationYesno;
	}

	public void setLiquidationYesno(String liquidationYesno) {
		this.liquidationYesno = liquidationYesno;
	}

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCpriceRule() {
		return cpriceRule;
	}

	public void setCpriceRule(String cpriceRule) {
		this.cpriceRule = cpriceRule;
	}
}
