package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:REP_PROD_FILE_PRODINFO表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-12-30
 */
public class RepProdFileProdInfoDto  extends BaseEntity {
    /**
     * 主键
     */
    private String produuid;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * 产品系列
     */
    private String prodSeries;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 产品处理模式
     */
    private String dealMode;

    /**
     * 发行方式
     */
    private String issueType;

    /**
     * 产品运作模式
     */
    private String operateMode;

    /**
     * 产品收益标识
     */
    private String profitFlag;

    /**
     * 投资性质
     */
    private String ivstorientation;

    /**
     * 理财币种
     */
    private String ccy;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 产品风险评级
     */
    private String riskLevel;

    /**
     * 投资者风险偏好
     */
    private String riskType;

    /**
     * 分红方式
     */
    private String payType;

    /**
     * 是否允许提前终止
     */
    private String beforeendFlag;

    /**
     * 募集开始日
     */
    private Date raiseVdate;

    /**
     * 募集结束日
     */
    private Date raiseEnddate;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 产品期限
     */
    private BigDecimal term;

    /**
     * 是否有投资封闭期
     */
    private String investcloseFlag;

    /**
     * 投资封闭期（从）
     */
    private Date investcloseStrdate;

    /**
     * 投资封闭期（到）
     */
    private Date investcloseEnddate;

    /**
     * 清盘日
     */
    private Date liquidationDate;

    /**
     * 期限
     */
    private String termtype;

    /**
     * 投资周期（天）
     */
    private String investmentCycle;

    /**
     * 是否自动续投
     */
    private String autocontinueFlag;

    /**
     * 预期年化收益率
     */
    private BigDecimal yield;

    /**
     * 计息基础
     */
    private String basic;

    /**
     * 投资管理人
     */
    private String investManager;

    /**
     * 理财产品托管人
     */
    private String trustee;

    /**
     * 投资范围及方向
     */
    private String investmentScope;

    /**
     * 申购开放日
     */
    private String purOpenDate;

    /**
     * 赎回开放日
     */
    private String redOpenDate;

    /**
     * 认购费率
     */
    private String subsRate;

    /**
     * 申购费率
     */
    private String purRate;

    /**
     * 赎回费率
     */
    private String redRate;

    /**
     * 托管费
     */
    private String truFee;

    /**
     * 销售手续费
     */
    private String saleFee;

    /**
     * 固定管理费
     */
    private String manageFee;

    /**
     * 业绩基准
     */
    private String repYield;

    /**
     * 浮动管理费
     */
    private String floatFee;

    /**
     * 计划募集金额
     */
    private BigDecimal raiseAmtPlan;

    /**
     * 募集上限
     */
    private BigDecimal saleMax;

    /**
     * 募集下限
     */
    private BigDecimal saleMin;

    /**
     * 起点金额
     */
    private BigDecimal startamt;

    /**
     * 最少追加金额
     */
    private BigDecimal lowestamt;

    /**
     * 单笔认购金额上限
     */
    private BigDecimal subsamtMax;

    /**
     * 单一客户认购金额上限
     */
    private BigDecimal custSubsamtMax;

    /**
     * 认购是否允许撤单
     */
    private String subsCancelFlag;

    /**
     * 单笔赎回最低份额
     */
    private BigDecimal minRedQty;

    /**
     * 最低保留份额
     */
    private BigDecimal minRetainQty;

    /**
     * 巨额赎回比例
     */
    private BigDecimal hugeRedRatio;

    /**
     * 赎回原则
     */
    private String redPrinciple;

    /**
     * 申购确认日
     */
    private String purCdate;

    /**
     * 赎回确认日
     */
    private String redCdate;

    /**
     * 投资本金到账日
     */
    private String investPrinDate;

    /**
     * 投资收益到账日
     */
    private String investProfDate;

    /**
     * 销售渠道
     */
    private String saleChannel;

    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 主键
     * @return PRODUUID 主键
     */
    public String getProduuid() {
        return produuid;
    }

    /**
     * 主键
     * @param produuid 主键
     */
    public void setProduuid(String produuid) {
        this.produuid = produuid == null ? null : produuid.trim();
    }

    /**
     * 产品代码
     * @return PROD_ID 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 产品名称
     * @return PROD_NAME 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 产品登记编码
     * @return REG_CODE 产品登记编码
     */
    public String getRegCode() {
        return regCode;
    }

    /**
     * 产品登记编码
     * @param regCode 产品登记编码
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    /**
     * 产品系列
     * @return PROD_SERIES 产品系列
     */
    public String getProdSeries() {
        return prodSeries;
    }

    /**
     * 产品系列
     * @param prodSeries 产品系列
     */
    public void setProdSeries(String prodSeries) {
        this.prodSeries = prodSeries == null ? null : prodSeries.trim();
    }

    /**
     * 收益类型
     * @return PROFIT_TYPE 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 产品处理模式
     * @return DEAL_MODE 产品处理模式
     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 产品处理模式
     * @param dealMode 产品处理模式
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode == null ? null : dealMode.trim();
    }

    /**
     * 发行方式
     * @return ISSUE_TYPE 发行方式
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 发行方式
     * @param issueType 发行方式
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType == null ? null : issueType.trim();
    }

    /**
     * 产品运作模式
     * @return OPERATE_MODE 产品运作模式
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 产品运作模式
     * @param operateMode 产品运作模式
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 产品收益标识
     * @return PROFIT_FLAG 产品收益标识
     */
    public String getProfitFlag() {
        return profitFlag;
    }

    /**
     * 产品收益标识
     * @param profitFlag 产品收益标识
     */
    public void setProfitFlag(String profitFlag) {
        this.profitFlag = profitFlag == null ? null : profitFlag.trim();
    }

    /**
     * 投资性质
     * @return IVSTORIENTATION 投资性质
     */
    public String getIvstorientation() {
        return ivstorientation;
    }

    /**
     * 投资性质
     * @param ivstorientation 投资性质
     */
    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation == null ? null : ivstorientation.trim();
    }

    /**
     * 理财币种
     * @return CCY 理财币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 理财币种
     * @param ccy 理财币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 募集方式
     * @return RAISE_TYPE 募集方式
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 募集方式
     * @param raiseType 募集方式
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 产品风险评级
     * @return RISK_LEVEL 产品风险评级
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 产品风险评级
     * @param riskLevel 产品风险评级
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 投资者风险偏好
     * @return RISK_TYPE 投资者风险偏好
     */
    public String getRiskType() {
        return riskType;
    }

    /**
     * 投资者风险偏好
     * @param riskType 投资者风险偏好
     */
    public void setRiskType(String riskType) {
        this.riskType = riskType == null ? null : riskType.trim();
    }

    /**
     * 分红方式
     * @return PAY_TYPE 分红方式
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 分红方式
     * @param payType 分红方式
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 是否允许提前终止
     * @return BEFOREEND_FLAG 是否允许提前终止
     */
    public String getBeforeendFlag() {
        return beforeendFlag;
    }

    /**
     * 是否允许提前终止
     * @param beforeendFlag 是否允许提前终止
     */
    public void setBeforeendFlag(String beforeendFlag) {
        this.beforeendFlag = beforeendFlag == null ? null : beforeendFlag.trim();
    }

    /**
     * 募集开始日
     * @return RAISE_VDATE 募集开始日
     */
    public Date getRaiseVdate() {
        return raiseVdate;
    }

    /**
     * 募集开始日
     * @param raiseVdate 募集开始日
     */
    public void setRaiseVdate(Date raiseVdate) {
        this.raiseVdate = raiseVdate;
    }

    /**
     * 募集结束日
     * @return RAISE_ENDDATE 募集结束日
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 募集结束日
     * @param raiseEnddate 募集结束日
     */
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 起息日
     * @return VDATE 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 起息日
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 到期日
     * @return MDATE 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 到期日
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 产品期限
     * @return TERM 产品期限
     */
    public BigDecimal getTerm() {
        return term;
    }

    /**
     * 产品期限
     * @param term 产品期限
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 是否有投资封闭期
     * @return INVESTCLOSE_FLAG 是否有投资封闭期
     */
    public String getInvestcloseFlag() {
        return investcloseFlag;
    }

    /**
     * 是否有投资封闭期
     * @param investcloseFlag 是否有投资封闭期
     */
    public void setInvestcloseFlag(String investcloseFlag) {
        this.investcloseFlag = investcloseFlag == null ? null : investcloseFlag.trim();
    }

    /**
     * 投资封闭期（从）
     * @return INVESTCLOSE_STRDATE 投资封闭期（从）
     */
    public Date getInvestcloseStrdate() {
        return investcloseStrdate;
    }

    /**
     * 投资封闭期（从）
     * @param investcloseStrdate 投资封闭期（从）
     */
    public void setInvestcloseStrdate(Date investcloseStrdate) {
        this.investcloseStrdate = investcloseStrdate;
    }

    /**
     * 投资封闭期（到）
     * @return INVESTCLOSE_ENDDATE 投资封闭期（到）
     */
    public Date getInvestcloseEnddate() {
        return investcloseEnddate;
    }

    /**
     * 投资封闭期（到）
     * @param investcloseEnddate 投资封闭期（到）
     */
    public void setInvestcloseEnddate(Date investcloseEnddate) {
        this.investcloseEnddate = investcloseEnddate;
    }

    /**
     * 清盘日
     * @return LIQUIDATION_DATE 清盘日
     */
    public Date getLiquidationDate() {
        return liquidationDate;
    }

    /**
     * 清盘日
     * @param liquidationDate 清盘日
     */
    public void setLiquidationDate(Date liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    /**
     * 期限
     * @return TERMTYPE 期限
     */
    public String getTermtype() {
        return termtype;
    }

    /**
     * 期限
     * @param termtype 期限
     */
    public void setTermtype(String termtype) {
        this.termtype = termtype == null ? null : termtype.trim();
    }

    /**
     * 投资周期（天）
     * @return INVESTMENT_CYCLE 投资周期（天）
     */
    public String getInvestmentCycle() {
        return investmentCycle;
    }
    
    /**
     * 投资周期（天）
     * @param investmentCycle 投资周期（天）
     */
    public void setInvestmentCycle(String investmentCycle) {
        this.investmentCycle = investmentCycle;
    }

    
    /**
     * 是否自动续投
     * @return AUTOCONTINUE_FLAG 是否自动续投
     */
    public String getAutocontinueFlag() {
        return autocontinueFlag;
    }

	/**
     * 是否自动续投
     * @param autocontinueFlag 是否自动续投
     */
    public void setAutocontinueFlag(String autocontinueFlag) {
        this.autocontinueFlag = autocontinueFlag == null ? null : autocontinueFlag.trim();
    }

    /**
     * 预期年化收益率
     * @return YIELD 预期年化收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 预期年化收益率
     * @param yield 预期年化收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 计息基础
     * @return BASIC 计息基础
     */
    public String getBasic() {
        return basic;
    }

    /**
     * 计息基础
     * @param basic 计息基础
     */
    public void setBasic(String basic) {
        this.basic = basic == null ? null : basic.trim();
    }

    /**
     * 投资管理人
     * @return INVEST_MANAGER 投资管理人
     */
    public String getInvestManager() {
        return investManager;
    }

    /**
     * 投资管理人
     * @param investManager 投资管理人
     */
    public void setInvestManager(String investManager) {
        this.investManager = investManager == null ? null : investManager.trim();
    }

    /**
     * 理财产品托管人
     * @return TRUSTEE 理财产品托管人
     */
    public String getTrustee() {
        return trustee;
    }

    /**
     * 理财产品托管人
     * @param trustee 理财产品托管人
     */
    public void setTrustee(String trustee) {
        this.trustee = trustee == null ? null : trustee.trim();
    }

    /**
     * 投资范围及方向
     * @return INVESTMENT_SCOPE 投资范围及方向
     */
    public String getInvestmentScope() {
        return investmentScope;
    }

    /**
     * 投资范围及方向
     * @param investmentScope 投资范围及方向
     */
    public void setInvestmentScope(String investmentScope) {
        this.investmentScope = investmentScope == null ? null : investmentScope.trim();
    }

    /**
     * 申购开放日
     * @return PUR_OPEN_DATE 申购开放日
     */
    public String getPurOpenDate() {
        return purOpenDate;
    }

    /**
     * 申购开放日
     * @param purOpenDate 申购开放日
     */
    public void setPurOpenDate(String purOpenDate) {
        this.purOpenDate = purOpenDate;
    }

    /**
     * 赎回开放日
     * @return RED_OPEN_DATE 赎回开放日
     */
    public String getRedOpenDate() {
        return redOpenDate;
    }

    /**
     * 赎回开放日
     * @param redOpenDate 赎回开放日
     */
    public void setRedOpenDate(String redOpenDate) {
        this.redOpenDate = redOpenDate;
    }

    /**
     * 认购费率
     * @return SUBS_RATE 认购费率
     */
    public String getSubsRate() {
        return subsRate;
    }

    /**
     * 认购费率
     * @param subsRate 认购费率
     */
    public void setSubsRate(String subsRate) {
        this.subsRate = subsRate;
    }

    /**
     * 申购费率
     * @return PUR_RATE 申购费率
     */
    public String getPurRate() {
        return purRate;
    }

    /**
     * 申购费率
     * @param purRate 申购费率
     */
    public void setPurRate(String purRate) {
        this.purRate = purRate;
    }

    /**
     * 赎回费率
     * @return RED_RATE 赎回费率
     */
    public String getRedRate() {
        return redRate;
    }

    /**
     * 赎回费率
     * @param redRate 赎回费率
     */
    public void setRedRate(String redRate) {
        this.redRate = redRate;
    }

    /**
     * 托管费
     * @return TRU_FEE 托管费
     */
    public String getTruFee() {
        return truFee;
    }

    /**
     * 托管费
     * @param truFee 托管费
     */
    public void setTruFee(String truFee) {
        this.truFee = truFee == null ? null : truFee.trim();
    }

    /**
     * 销售手续费
     * @return SALE_FEE 销售手续费
     */
    public String getSaleFee() {
        return saleFee;
    }

    /**
     * 销售手续费
     * @param saleFee 销售手续费
     */
    public void setSaleFee(String saleFee) {
        this.saleFee = saleFee == null ? null : saleFee.trim();
    }

    /**
     * 固定管理费
     * @return MANAGE_FEE 固定管理费
     */
    public String getManageFee() {
        return manageFee;
    }

    /**
     * 固定管理费
     * @param manageFee 固定管理费
     */
    public void setManageFee(String manageFee) {
        this.manageFee = manageFee == null ? null : manageFee.trim();
    }

    /**
     * 业绩基准
     * @return REP_YIELD 业绩基准
     */
    public String getRepYield() {
        return repYield;
    }

    /**
     * 业绩基准
     * @param repYield 业绩基准
     */
    public void setRepYield(String repYield) {
        this.repYield = repYield == null ? null : repYield.trim();
    }

    /**
     * 浮动管理费
     * @return FLOAT_FEE 浮动管理费
     */
    public String getFloatFee() {
        return floatFee;
    }

    /**
     * 浮动管理费
     * @param floatFee 浮动管理费
     */
    public void setFloatFee(String floatFee) {
        this.floatFee = floatFee == null ? null : floatFee.trim();
    }

    /**
     * 计划募集金额
     * @return RAISE_AMT_PLAN 计划募集金额
     */
    public BigDecimal getRaiseAmtPlan() {
        return raiseAmtPlan;
    }

    /**
     * 计划募集金额
     * @param raiseAmtPlan 计划募集金额
     */
    public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
        this.raiseAmtPlan = raiseAmtPlan;
    }

    /**
     * 募集上限
     * @return SALE_MAX 募集上限
     */
    public BigDecimal getSaleMax() {
        return saleMax;
    }

    /**
     * 募集上限
     * @param saleMax 募集上限
     */
    public void setSaleMax(BigDecimal saleMax) {
        this.saleMax = saleMax;
    }

    /**
     * 募集下限
     * @return SALE_MIN 募集下限
     */
    public BigDecimal getSaleMin() {
        return saleMin;
    }

    /**
     * 募集下限
     * @param saleMin 募集下限
     */
    public void setSaleMin(BigDecimal saleMin) {
        this.saleMin = saleMin;
    }

    /**
     * 起点金额
     * @return STARTAMT 起点金额
     */
    public BigDecimal getStartamt() {
        return startamt;
    }

    /**
     * 起点金额
     * @param startamt 起点金额
     */
    public void setStartamt(BigDecimal startamt) {
        this.startamt = startamt;
    }

    /**
     * 最少追加金额
     * @return LOWESTAMT 最少追加金额
     */
    public BigDecimal getLowestamt() {
        return lowestamt;
    }

    /**
     * 最少追加金额
     * @param lowestamt 最少追加金额
     */
    public void setLowestamt(BigDecimal lowestamt) {
        this.lowestamt = lowestamt;
    }

    /**
     * 单笔认购金额上限
     * @return SUBSAMT_MAX 单笔认购金额上限
     */
    public BigDecimal getSubsamtMax() {
        return subsamtMax;
    }

    /**
     * 单笔认购金额上限
     * @param subsamtMax 单笔认购金额上限
     */
    public void setSubsamtMax(BigDecimal subsamtMax) {
        this.subsamtMax = subsamtMax;
    }

    /**
     * 单一客户认购金额上限
     * @return CUST_SUBSAMT_MAX 单一客户认购金额上限
     */
    public BigDecimal getCustSubsamtMax() {
        return custSubsamtMax;
    }

    /**
     * 单一客户认购金额上限
     * @param custSubsamtMax 单一客户认购金额上限
     */
    public void setCustSubsamtMax(BigDecimal custSubsamtMax) {
        this.custSubsamtMax = custSubsamtMax;
    }

    /**
     * 认购是否允许撤单
     * @return SUBS_CANCEL_FLAG 认购是否允许撤单
     */
    public String getSubsCancelFlag() {
        return subsCancelFlag;
    }

    /**
     * 认购是否允许撤单
     * @param subsCancelFlag 认购是否允许撤单
     */
    public void setSubsCancelFlag(String subsCancelFlag) {
        this.subsCancelFlag = subsCancelFlag == null ? null : subsCancelFlag.trim();
    }

    /**
     * 单笔赎回最低份额
     * @return MIN_RED_QTY 单笔赎回最低份额
     */
    public BigDecimal getMinRedQty() {
        return minRedQty;
    }

    /**
     * 单笔赎回最低份额
     * @param minRedQty 单笔赎回最低份额
     */
    public void setMinRedQty(BigDecimal minRedQty) {
        this.minRedQty = minRedQty;
    }

    /**
     * 最低保留份额
     * @return MIN_RETAIN_QTY 最低保留份额
     */
    public BigDecimal getMinRetainQty() {
        return minRetainQty;
    }

    /**
     * 最低保留份额
     * @param minRetainQty 最低保留份额
     */
    public void setMinRetainQty(BigDecimal minRetainQty) {
        this.minRetainQty = minRetainQty;
    }

    /**
     * 巨额赎回比例
     * @return HUGE_RED_RATIO 巨额赎回比例
     */
    public BigDecimal getHugeRedRatio() {
        return hugeRedRatio;
    }

    /**
     * 巨额赎回比例
     * @param hugeRedRatio 巨额赎回比例
     */
    public void setHugeRedRatio(BigDecimal hugeRedRatio) {
        this.hugeRedRatio = hugeRedRatio;
    }

    /**
     * 赎回原则
     * @return RED_PRINCIPLE 赎回原则
     */
    public String getRedPrinciple() {
        return redPrinciple;
    }

    /**
     * 赎回原则
     * @param redPrinciple 赎回原则
     */
    public void setRedPrinciple(String redPrinciple) {
        this.redPrinciple = redPrinciple == null ? null : redPrinciple.trim();
    }

    /**
     * 申购确认日
     * @return PUR_CDATE 申购确认日
     */
    public String getPurCdate() {
        return purCdate;
    }

    /**
     * 申购确认日
     * @param purCdate 申购确认日
     */
    public void setPurCdate(String purCdate) {
        this.purCdate = purCdate == null ? null : purCdate.trim();
    }

    /**
     * 赎回确认日
     * @return RED_CDATE 赎回确认日
     */
    public String getRedCdate() {
        return redCdate;
    }

    /**
     * 赎回确认日
     * @param redCdate 赎回确认日
     */
    public void setRedCdate(String redCdate) {
        this.redCdate = redCdate == null ? null : redCdate.trim();
    }

    /**
     * 投资本金到账日
     * @return INVEST_PRIN_DATE 投资本金到账日
     */
    public String getInvestPrinDate() {
        return investPrinDate;
    }

    /**
     * 投资本金到账日
     * @param investPrinDate 投资本金到账日
     */
    public void setInvestPrinDate(String investPrinDate) {
        this.investPrinDate = investPrinDate == null ? null : investPrinDate.trim();
    }

    /**
     * 投资收益到账日
     * @return INVEST_PROF_DATE 投资收益到账日
     */
    public String getInvestProfDate() {
        return investProfDate;
    }

    /**
     * 投资收益到账日
     * @param investProfDate 投资收益到账日
     */
    public void setInvestProfDate(String investProfDate) {
        this.investProfDate = investProfDate == null ? null : investProfDate.trim();
    }

    /**
     * 销售渠道
     * @return SALE_CHANNEL 销售渠道
     */
    public String getSaleChannel() {
        return saleChannel;
    }

    /**
     * 销售渠道
     * @param saleChannel 销售渠道
     */
    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel == null ? null : saleChannel.trim();
    }

    /**
     * 数据状态
     * @return DATA_STATUS 数据状态
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 数据状态
     * @param dataStatus 数据状态
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}