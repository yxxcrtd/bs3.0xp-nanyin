/*
 * 文件名：BussRepAssetCreditNonstdInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 信贷及非标资产报送信息DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see SvrBussRepAssetCreditNonstdInfoDto
 * @since 1.0
 */
public class SvrBussRepAssetCreditNonstdInfoDto extends RepAssetCreditNonstdDto {
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    private String type6;
    
    private String type7;
    /**
     * 资产/负债类别
     */
    private String repAssetType;
    
    /**
     * 交易流通场所
     */
    private String repTradeMarket;

    /**
     * 是否投向金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    private String repPrivatefundFlag;

    /**
     * 公共PPP
     */
    private String repPppflag;

    /**
     * 是否小微企业信贷资产流转和收益权转让
     */
    private String repSmallcredit;

    /**
     * 是否投向投向市场化债转股相关产品的部分
     */
    private String repBondtostockFlag;

    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String repPrivateprdFlag;

    /**
     * 人行机构规模
     */
    private String repInstitSize;

    /**
     * 受让形式
     */
    private String repAssignee;

    
    /**
    * 法定到期日 中债新增 20200403 start
    */
    private Date repFmdate;

    /**
    * 份额面值
    */
    private BigDecimal repPrinBaseamt;

    /**
    * 计息类型
    */
    private String repCouponSpecies;

    /**
    * 利息分布方式
    */
    private String repIntpayRule;

    /**
    * 结构档次
    */
    private String repStructGrade;

    /**
    * 还本方式
    */
    private String repRepaymentType;

    /**
    * 分期还本条款标识
    */
    private String repPeriodRepaymentFlag;

    /**
    * 超额收益分配比例%
    */
    private BigDecimal repExcessProfitRate;

    /**
    * 含权类型
    */
    private String repContainType;

    /**
    * 行权方式
    */
    private String repExerciseType;

    /**
    * 固定行权日
    */
    private Date repExerciseDate;

    /**
    * 首次行权日期
    */
    private Date repFirstExerciseDate;

    /**
    * 行权周期
    */
    private BigDecimal repExercisePeriod;

    /**
    * 行权价格
    */
    private BigDecimal repExercisePrice;

    /**
    * 永续条款类型
    */
    private String repPerpetualClauseType;

    /**
    * 利息递延条款类型
    */
    private String repDeferredInterestType;

    /**
    * 递延利息是否计息
    */
    private String repDeferredInterestInt;

    /**
    * 首次重定价日期
    */
    private Date repFirstReprceDate;

    /**
    * 重定价周期
    */
    private BigDecimal repReprceCycle;

    /**
    * 部分赎回标识
    */
    private String repPartRedemFlag;

    /**
    * 部分赎回比例%
    */
    private BigDecimal repPartRedemRate;

    /**
    * 抵质押物类型
    */
    private String repPledgeType;

    /**
    * 抵质押物价值（元）
    */
    private BigDecimal repPledgeValue;

    /**
    * 基础资产类型
    */
    private String repBasicAssetType;
    
    /**
     * 担保性质
     */
    private String repNatureGuarantee;

    /**
    * 担保人与融资人关系  中债新增 20200403 end
    */
    private String repRelationFinan;

    /**
     * 规则付息标识
     */
    private String   interestRuleFlag;

    /**
     * 计息基础
     */
    private  String basis;
    /**
     *金额
     */
    private  BigDecimal amt;


    /**
     *基准利率种类
     */
    private   String  basisType;

    /**
     *是否有浮动因子
     */
    private   String  floatFactorFlag;

    /**
     *浮动因子%
     */
    private   BigDecimal  floatFactor;

    private BigDecimal assetYield;

    /**
     * 利差
     */
    private BigDecimal spreadRate;

    /**
     * 增信机构代码
     */
    private  String  creditOrgCode;

    /**
     * 增信机构名称
     */
    private  String  creditOrgName;

    /**
     * 融资总费率%
     */
    private BigDecimal  totalFeeRate;


    private String  industryLevel2Type;

    public String getIndustryLevel2Type() {
        return industryLevel2Type;
    }

    public void setIndustryLevel2Type(String industryLevel2Type) {
        this.industryLevel2Type = industryLevel2Type;
    }

    public String getCreditOrgCode() {
        return creditOrgCode;
    }

    public void setCreditOrgCode(String creditOrgCode) {
        this.creditOrgCode = creditOrgCode;
    }

    public String getCreditOrgName() {
        return creditOrgName;
    }

    public void setCreditOrgName(String creditOrgName) {
        this.creditOrgName = creditOrgName;
    }

    public BigDecimal getTotalFeeRate() {
        return totalFeeRate;
    }

    public void setTotalFeeRate(BigDecimal totalFeeRate) {
        this.totalFeeRate = totalFeeRate;
    }

    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    public String getInterestRuleFlag() {
        return interestRuleFlag;
    }

    public void setInterestRuleFlag(String interestRuleFlag) {
        this.interestRuleFlag = interestRuleFlag;
    }


    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getBasisType() {
        return basisType;
    }

    public void setBasisType(String basisType) {
        this.basisType = basisType;
    }

    public String getFloatFactorFlag() {
        return floatFactorFlag;
    }

    public void setFloatFactorFlag(String floatFactorFlag) {
        this.floatFactorFlag = floatFactorFlag;
    }

    public BigDecimal getFloatFactor() {
        return floatFactor;
    }

    public void setFloatFactor(BigDecimal floatFactor) {
        this.floatFactor = floatFactor;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }

    /**
     * 获取 repAssetType
     * @return repAssetType.
     */
    public String getRepAssetType() {
        return repAssetType;
    }

    
    /**
     * 设置 repAssetType
     * @param repAssetType repAssetType
     */
    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }

    
    /**
     * 获取 repTradeMarket
     * @return repTradeMarket.
     */
    public String getRepTradeMarket() {
        return repTradeMarket;
    }

    
    /**
     * 设置 repTradeMarket
     * @param repTradeMarket repTradeMarket
     */
    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket;
    }


    
    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }


    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }


    
    /**
     * 获取 type7
     * @return type7.
     */
    public String getType7() {
        return type7;
    }


    
    /**
     * 设置 type7
     * @param type7 type7
     */
    public void setType7(String type7) {
        this.type7 = type7;
    }
    
    //还本付息情况说明
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repRepayCmt", max = 200, groups = {ValidateGroupEdit.class})
    public String getRepRepayCmt(){
        return super.getRepRepayCmt();
    }
    
    //融资人
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repFinancier", max = 200, groups = {ValidateGroupEdit.class})
    public String getRepFinancier(){
        return super.getRepFinancier();
    }
    
    //融资人内部信用评级
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repFcrGradeIn", max = 10, groups = {ValidateGroupEdit.class})
    public String getRepFcrGradeIn(){
        return super.getRepFcrGradeIn();
    }
    
    //外部评级机构名称及对融资人评级结果
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repFcrGradeOut", max = 200, groups = {ValidateGroupEdit.class})
    public String getRepFcrGradeOut(){
        return super.getRepFcrGradeOut();
    }
    //融资项目
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repFinanceProj", max = 200, groups = {ValidateGroupEdit.class})
    public String getRepFinanceProj(){
        return super.getRepFinanceProj();
    }
    //重点监控行业和领域类别说明
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repMonTypeComm", max = 256, groups = {ValidateGroupEdit.class})
    public String getRepMonTypeComm(){
        return super.getRepMonTypeComm();
    }
    
    //担保情况说明
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repGuaTypeRemark", max = 200, groups = {ValidateGroupEdit.class})
    public String getRepGuaTypeRemark(){
        return super.getRepGuaTypeRemark();
    }
    //资产内部评级
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repInGrade", max = 20, groups = {ValidateGroupEdit.class})
    public String getRepInGrade(){
        return super.getRepInGrade();
    }
    //选择权
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repChose", max = 60, groups = {ValidateGroupEdit.class})
    public String getRepChose(){
        return super.getRepChose();
    }
    //行权条件说明
    @Length(fieldName = "BussRepAssetCreditNonstdInfo.repConditionCmm", max = 200, groups = {ValidateGroupEdit.class})
    public String getRepConditionCmm(){
        return super.getRepConditionCmm();
    }
    

    public String getRepPrivatefundFlag() {
        return repPrivatefundFlag;
    }

    public void setRepPrivatefundFlag(String repPrivatefundFlag) {
        this.repPrivatefundFlag = repPrivatefundFlag;
    }

    public String getRepPppflag() {
        return repPppflag;
    }

    public void setRepPppflag(String repPppflag) {
        this.repPppflag = repPppflag;
    }

    public String getRepSmallcredit() {
        return repSmallcredit;
    }

    public void setRepSmallcredit(String repSmallcredit) {
        this.repSmallcredit = repSmallcredit;
    }

    public String getRepBondtostockFlag() {
        return repBondtostockFlag;
    }

    public void setRepBondtostockFlag(String repBondtostockFlag) {
        this.repBondtostockFlag = repBondtostockFlag;
    }

    public String getRepPrivateprdFlag() {
        return repPrivateprdFlag;
    }

    public void setRepPrivateprdFlag(String repPrivateprdFlag) {
        this.repPrivateprdFlag = repPrivateprdFlag;
    }

    public String getRepInstitSize() {
        return repInstitSize;
    }

    public void setRepInstitSize(String repInstitSize) {
        this.repInstitSize = repInstitSize;
    }

    public String getRepAssignee() {
        return repAssignee;
    }

    public void setRepAssignee(String repAssignee) {
        this.repAssignee = repAssignee;
    }


	/**
	 * @return the repFmdate
	 */
	public Date getRepFmdate() {
		return repFmdate;
	}


	/**
	 * @param repFmdate the repFmdate to set
	 */
	public void setRepFmdate(Date repFmdate) {
		this.repFmdate = repFmdate;
	}


	/**
	 * @return the repPrinBaseamt
	 */
	public BigDecimal getRepPrinBaseamt() {
		return repPrinBaseamt;
	}


	/**
	 * @param repPrinBaseamt the repPrinBaseamt to set
	 */
	public void setRepPrinBaseamt(BigDecimal repPrinBaseamt) {
		this.repPrinBaseamt = repPrinBaseamt;
	}


	/**
	 * @return the repCouponSpecies
	 */
	public String getRepCouponSpecies() {
		return repCouponSpecies;
	}


	/**
	 * @param repCouponSpecies the repCouponSpecies to set
	 */
	public void setRepCouponSpecies(String repCouponSpecies) {
		this.repCouponSpecies = repCouponSpecies;
	}


	/**
	 * @return the repIntpayRule
	 */
	public String getRepIntpayRule() {
		return repIntpayRule;
	}


	/**
	 * @param repIntpayRule the repIntpayRule to set
	 */
	public void setRepIntpayRule(String repIntpayRule) {
		this.repIntpayRule = repIntpayRule;
	}


	/**
	 * @return the repStructGrade
	 */
	public String getRepStructGrade() {
		return repStructGrade;
	}


	/**
	 * @param repStructGrade the repStructGrade to set
	 */
	public void setRepStructGrade(String repStructGrade) {
		this.repStructGrade = repStructGrade;
	}


	/**
	 * @return the repRepaymentType
	 */
	public String getRepRepaymentType() {
		return repRepaymentType;
	}


	/**
	 * @param repRepaymentType the repRepaymentType to set
	 */
	public void setRepRepaymentType(String repRepaymentType) {
		this.repRepaymentType = repRepaymentType;
	}


	/**
	 * @return the repPeriodRepaymentFlag
	 */
	public String getRepPeriodRepaymentFlag() {
		return repPeriodRepaymentFlag;
	}


	/**
	 * @param repPeriodRepaymentFlag the repPeriodRepaymentFlag to set
	 */
	public void setRepPeriodRepaymentFlag(String repPeriodRepaymentFlag) {
		this.repPeriodRepaymentFlag = repPeriodRepaymentFlag;
	}


	/**
	 * @return the repExcessProfitRate
	 */
	public BigDecimal getRepExcessProfitRate() {
		return repExcessProfitRate;
	}


	/**
	 * @param repExcessProfitRate the repExcessProfitRate to set
	 */
	public void setRepExcessProfitRate(BigDecimal repExcessProfitRate) {
		this.repExcessProfitRate = repExcessProfitRate;
	}


	/**
	 * @return the repContainType
	 */
	public String getRepContainType() {
		return repContainType;
	}


	/**
	 * @param repContainType the repContainType to set
	 */
	public void setRepContainType(String repContainType) {
		this.repContainType = repContainType;
	}


	/**
	 * @return the repExerciseType
	 */
	public String getRepExerciseType() {
		return repExerciseType;
	}


	/**
	 * @param repExerciseType the repExerciseType to set
	 */
	public void setRepExerciseType(String repExerciseType) {
		this.repExerciseType = repExerciseType;
	}


	/**
	 * @return the repExerciseDate
	 */
	public Date getRepExerciseDate() {
		return repExerciseDate;
	}


	/**
	 * @param repExerciseDate the repExerciseDate to set
	 */
	public void setRepExerciseDate(Date repExerciseDate) {
		this.repExerciseDate = repExerciseDate;
	}


	/**
	 * @return the repFirstExerciseDate
	 */
	public Date getRepFirstExerciseDate() {
		return repFirstExerciseDate;
	}


	/**
	 * @param repFirstExerciseDate the repFirstExerciseDate to set
	 */
	public void setRepFirstExerciseDate(Date repFirstExerciseDate) {
		this.repFirstExerciseDate = repFirstExerciseDate;
	}


	/**
	 * @return the repExercisePeriod
	 */
	public BigDecimal getRepExercisePeriod() {
		return repExercisePeriod;
	}


	/**
	 * @param repExercisePeriod the repExercisePeriod to set
	 */
	public void setRepExercisePeriod(BigDecimal repExercisePeriod) {
		this.repExercisePeriod = repExercisePeriod;
	}


	/**
	 * @return the repExercisePrice
	 */
	public BigDecimal getRepExercisePrice() {
		return repExercisePrice;
	}


	/**
	 * @param repExercisePrice the repExercisePrice to set
	 */
	public void setRepExercisePrice(BigDecimal repExercisePrice) {
		this.repExercisePrice = repExercisePrice;
	}


	/**
	 * @return the repPerpetualClauseType
	 */
	public String getRepPerpetualClauseType() {
		return repPerpetualClauseType;
	}


	/**
	 * @param repPerpetualClauseType the repPerpetualClauseType to set
	 */
	public void setRepPerpetualClauseType(String repPerpetualClauseType) {
		this.repPerpetualClauseType = repPerpetualClauseType;
	}


	/**
	 * @return the repDeferredInterestType
	 */
	public String getRepDeferredInterestType() {
		return repDeferredInterestType;
	}


	/**
	 * @param repDeferredInterestType the repDeferredInterestType to set
	 */
	public void setRepDeferredInterestType(String repDeferredInterestType) {
		this.repDeferredInterestType = repDeferredInterestType;
	}


	/**
	 * @return the repDeferredInterestInt
	 */
	public String getRepDeferredInterestInt() {
		return repDeferredInterestInt;
	}


	/**
	 * @param repDeferredInterestInt the repDeferredInterestInt to set
	 */
	public void setRepDeferredInterestInt(String repDeferredInterestInt) {
		this.repDeferredInterestInt = repDeferredInterestInt;
	}


	/**
	 * @return the repFirstReprceDate
	 */
	public Date getRepFirstReprceDate() {
		return repFirstReprceDate;
	}


	/**
	 * @param repFirstReprceDate the repFirstReprceDate to set
	 */
	public void setRepFirstReprceDate(Date repFirstReprceDate) {
		this.repFirstReprceDate = repFirstReprceDate;
	}


	/**
	 * @return the repReprceCycle
	 */
	public BigDecimal getRepReprceCycle() {
		return repReprceCycle;
	}


	/**
	 * @param repReprceCycle the repReprceCycle to set
	 */
	public void setRepReprceCycle(BigDecimal repReprceCycle) {
		this.repReprceCycle = repReprceCycle;
	}


	/**
	 * @return the repPartRedemFlag
	 */
	public String getRepPartRedemFlag() {
		return repPartRedemFlag;
	}


	/**
	 * @param repPartRedemFlag the repPartRedemFlag to set
	 */
	public void setRepPartRedemFlag(String repPartRedemFlag) {
		this.repPartRedemFlag = repPartRedemFlag;
	}


	/**
	 * @return the repPartRedemRate
	 */
	public BigDecimal getRepPartRedemRate() {
		return repPartRedemRate;
	}


	/**
	 * @param repPartRedemRate the repPartRedemRate to set
	 */
	public void setRepPartRedemRate(BigDecimal repPartRedemRate) {
		this.repPartRedemRate = repPartRedemRate;
	}


	/**
	 * @return the repPledgeType
	 */
	public String getRepPledgeType() {
		return repPledgeType;
	}


	/**
	 * @param repPledgeType the repPledgeType to set
	 */
	public void setRepPledgeType(String repPledgeType) {
		this.repPledgeType = repPledgeType;
	}


	/**
	 * @return the repPledgeValue
	 */
	public BigDecimal getRepPledgeValue() {
		return repPledgeValue;
	}


	/**
	 * @param repPledgeValue the repPledgeValue to set
	 */
	public void setRepPledgeValue(BigDecimal repPledgeValue) {
		this.repPledgeValue = repPledgeValue;
	}


	/**
	 * @return the repNatureGuarantee
	 */
	public String getRepNatureGuarantee() {
		return repNatureGuarantee;
	}


	/**
	 * @param repNatureGuarantee the repNatureGuarantee to set
	 */
	public void setRepNatureGuarantee(String repNatureGuarantee) {
		this.repNatureGuarantee = repNatureGuarantee;
	}


	/**
	 * @return the repRelationFinan
	 */
	public String getRepRelationFinan() {
		return repRelationFinan;
	}


	/**
	 * @param repRelationFinan the repRelationFinan to set
	 */
	public void setRepRelationFinan(String repRelationFinan) {
		this.repRelationFinan = repRelationFinan;
	}


	/**
	 * @return the repBasicAssetType
	 */
	public String getRepBasicAssetType() {
		return repBasicAssetType;
	}


	/**
	 * @param repBasicAssetType the repBasicAssetType to set
	 */
	public void setRepBasicAssetType(String repBasicAssetType) {
		this.repBasicAssetType = repBasicAssetType;
	}
}
