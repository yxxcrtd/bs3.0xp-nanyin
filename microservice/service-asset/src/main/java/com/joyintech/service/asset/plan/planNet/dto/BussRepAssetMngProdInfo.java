package com.joyintech.service.asset.plan.planNet.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * 资产管理计划报送信息组件Dto
 *
 * @author liuwei
 * @version 1.0 2018年9月18日
 * @see BussRepAssetMngProdInfo
 * @since 1.0
 */
//@ValidLogic(targetClass = BussRepDepositInfoValidator.class , groups = {ValidateGroupEdit.class})
public class BussRepAssetMngProdInfo{
    /**
     * 交易流通场所
     */
    private String repTradeMarket       ;
    /**
     * 二级资产类别
     */
    private String repAssetType         ;
    /**
     * 资管计划发起人机构编码
     */
    private String repPartyCode         ;
    /**
     * 资管计划登记编码
     */
    private String repRegistCode        ;
    /**
     * 资金实际投向
     */
    private String repCapiInvest        ;
    /**
     * 资金运用方式
     */
    private String repCapUseWay         ;
    /**
     * 资金运用行业
     */
    private String repCapUseIndu        ;
    /**
     * 资金运用行业二级分类
     */
    private String repCapUseInduLevel2  ;
    /**
     * 资管计划属性
     */
    private String repPlanProp          ;
    /**
     * 是否有预期收益率
     */
    private String repYieldFlag 		;
    /**
     * 预期最高收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMaxRate		;
    /**
     * 预期最低收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMinRate 		;
    /**
     * 购买结构
     */

    private String repBuyStruct 		;
    /**
     * 管理方式
     */
    private String repManageType 		;
    /**
     * 是否掌控计划底层资产详情
     */

    private String repIsCtrlBottom  	;
    /**
     * 管理费率%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal repMgrFeeRate 	;
    /**
     * 托管费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTruFeeRate  	;
    /**
     * 交易相关合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTradeFeeRate  ;
    /**
     * 中介服务机构合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAgencyFeeRate ;
    /**
     * 其他合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherFeeRate  ;
    // 增加是否投向金融资产投资公司发行的私募资产管理产品 by 赵东东 2019.10.10 start
    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String repPrivateprdFlag;
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
     * 是否由金融资产投资公司发行
     */
    private String repFinancialComissue;
    
    public String getRepFinancialComissue() {
		return repFinancialComissue;
	}
	public void setRepFinancialComissue(String repFinancialComissue) {
		this.repFinancialComissue = repFinancialComissue;
	}
	/**
     * 获取 repPrivateprdFlag
     *
     * @return repPrivateprdFlag
     */
    public String getRepPrivateprdFlag() {
		return repPrivateprdFlag;
	}
    /**
     * 设置repPrivateprdFlag
     *
     * @param repPrivateprdFlag repPrivateprdFlag
     */
	public void setRepPrivateprdFlag(String repPrivateprdFlag) {
		this.repPrivateprdFlag = repPrivateprdFlag;
	}
	// 增加是否投向金融资产投资公司发行的私募资产管理产品 by 赵东东 2019.10.10 end

	/**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAmt 			;
    /**
     * 备注
     */
    private String repRemark;

    /**
     * 获取 repTradeMarket
     *
     * @return repTradeMarket
     */
    public String getRepTradeMarket() {
        return repTradeMarket;
    }

    /**
     * 设置 repTradeMarket
     *
     * @param repTradeMarket repTradeMarket
     */
    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket;
    }

    /**
     * 获取 repAssetType
     *
     * @return repAssetType
     */
    public String getRepAssetType() {
        return repAssetType;
    }

    /**
     * 设置 repAssetType
     *
     * @param repAssetType repAssetType
     */
    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }

    /**
     * 获取 repPartyCode
     *
     * @return repPartyCode
     */
    public String getRepPartyCode() {
        return repPartyCode;
    }

    /**
     * 设置 repPartyCode
     *
     * @param repPartyCode repPartyCode
     */
    public void setRepPartyCode(String repPartyCode) {
        this.repPartyCode = repPartyCode;
    }

    /**
     * 获取 repRegistCode
     *
     * @return repRegistCode
     */
    public String getRepRegistCode() {
        return repRegistCode;
    }

    /**
     * 设置 repRegistCode
     *
     * @param repRegistCode repRegistCode
     */
    public void setRepRegistCode(String repRegistCode) {
        this.repRegistCode = repRegistCode;
    }

    /**
     * 获取 repCapiInvest
     *
     * @return repCapiInvest
     */
    public String getRepCapiInvest() {
        return repCapiInvest;
    }

    /**
     * 设置 repCapiInvest
     *
     * @param repCapiInvest repCapiInvest
     */
    public void setRepCapiInvest(String repCapiInvest) {
        this.repCapiInvest = repCapiInvest;
    }

    /**
     * 获取 repCapUseWay
     *
     * @return repCapUseWay
     */
    public String getRepCapUseWay() {
        return repCapUseWay;
    }

    /**
     * 设置 repCapUseWay
     *
     * @param repCapUseWay repCapUseWay
     */
    public void setRepCapUseWay(String repCapUseWay) {
        this.repCapUseWay = repCapUseWay;
    }

    /**
     * 获取 repCapUseIndu
     *
     * @return repCapUseIndu
     */
    public String getRepCapUseIndu() {
        return repCapUseIndu;
    }

    /**
     * 设置 repCapUseIndu
     *
     * @param repCapUseIndu repCapUseIndu
     */
    public void setRepCapUseIndu(String repCapUseIndu) {
        this.repCapUseIndu = repCapUseIndu;
    }

    /**
     * 获取 repCapUseInduLevel2
     *
     * @return repCapUseInduLevel2
     */
    public String getRepCapUseInduLevel2() {
        return repCapUseInduLevel2;
    }

    /**
     * 设置 repCapUseInduLevel2
     *
     * @param repCapUseInduLevel2 repCapUseInduLevel2
     */
    public void setRepCapUseInduLevel2(String repCapUseInduLevel2) {
        this.repCapUseInduLevel2 = repCapUseInduLevel2;
    }

    /**
     * 获取 repPlanProp
     *
     * @return repPlanProp
     */
    public String getRepPlanProp() {
        return repPlanProp;
    }

    /**
     * 设置 repPlanProp
     *
     * @param repPlanProp repPlanProp
     */
    public void setRepPlanProp(String repPlanProp) {
        this.repPlanProp = repPlanProp;
    }

    /**
     * 获取 repYieldFlag
     *
     * @return repYieldFlag
     */
    public String getRepYieldFlag() {
        return repYieldFlag;
    }

    /**
     * 设置 repYieldFlag
     *
     * @param repYieldFlag repYieldFlag
     */
    public void setRepYieldFlag(String repYieldFlag) {
        this.repYieldFlag = repYieldFlag;
    }

    /**
     * 获取 repMaxRate
     *
     * @return repMaxRate
     */
    public BigDecimal getRepMaxRate() {
        return repMaxRate;
    }

    /**
     * 设置 repMaxRate
     *
     * @param repMaxRate repMaxRate
     */
    public void setRepMaxRate(BigDecimal repMaxRate) {
        this.repMaxRate = repMaxRate;
    }

    /**
     * 获取 repMinRate
     *
     * @return repMinRate
     */
    public BigDecimal getRepMinRate() {
        return repMinRate;
    }

    /**
     * 设置 repMinRate
     *
     * @param repMinRate repMinRate
     */
    public void setRepMinRate(BigDecimal repMinRate) {
        this.repMinRate = repMinRate;
    }

    /**
     * 获取 repBuyStruct
     *
     * @return repBuyStruct
     */
    public String getRepBuyStruct() {
        return repBuyStruct;
    }

    /**
     * 设置 repBuyStruct
     *
     * @param repBuyStruct repBuyStruct
     */
    public void setRepBuyStruct(String repBuyStruct) {
        this.repBuyStruct = repBuyStruct;
    }

    /**
     * 获取 repManageType
     *
     * @return repManageType
     */
    public String getRepManageType() {
        return repManageType;
    }

    /**
     * 设置 repManageType
     *
     * @param repManageType repManageType
     */
    public void setRepManageType(String repManageType) {
        this.repManageType = repManageType;
    }

    /**
     * 获取 repIsCtrlBottom
     *
     * @return repIsCtrlBottom
     */
    public String getRepIsCtrlBottom() {
        return repIsCtrlBottom;
    }

    /**
     * 设置 repIsCtrlBottom
     *
     * @param repIsCtrlBottom repIsCtrlBottom
     */
    public void setRepIsCtrlBottom(String repIsCtrlBottom) {
        this.repIsCtrlBottom = repIsCtrlBottom;
    }

    /**
     * 获取 repMgrFeeRate
     *
     * @return repMgrFeeRate
     */
    public BigDecimal getRepMgrFeeRate() {
        return repMgrFeeRate;
    }

    /**
     * 设置 repMgrFeeRate
     *
     * @param repMgrFeeRate repMgrFeeRate
     */
    public void setRepMgrFeeRate(BigDecimal repMgrFeeRate) {
        this.repMgrFeeRate = repMgrFeeRate;
    }

    /**
     * 获取 repTruFeeRate
     *
     * @return repTruFeeRate
     */
    public BigDecimal getRepTruFeeRate() {
        return repTruFeeRate;
    }

    /**
     * 设置 repTruFeeRate
     *
     * @param repTruFeeRate repTruFeeRate
     */
    public void setRepTruFeeRate(BigDecimal repTruFeeRate) {
        this.repTruFeeRate = repTruFeeRate;
    }

    /**
     * 获取 repTradeFeeRate
     *
     * @return repTradeFeeRate
     */
    public BigDecimal getRepTradeFeeRate() {
        return repTradeFeeRate;
    }

    /**
     * 设置 repTradeFeeRate
     *
     * @param repTradeFeeRate repTradeFeeRate
     */
    public void setRepTradeFeeRate(BigDecimal repTradeFeeRate) {
        this.repTradeFeeRate = repTradeFeeRate;
    }

    /**
     * 获取 repAgencyFeeRate
     *
     * @return repAgencyFeeRate
     */
    public BigDecimal getRepAgencyFeeRate() {
        return repAgencyFeeRate;
    }

    /**
     * 设置 repAgencyFeeRate
     *
     * @param repAgencyFeeRate repAgencyFeeRate
     */
    public void setRepAgencyFeeRate(BigDecimal repAgencyFeeRate) {
        this.repAgencyFeeRate = repAgencyFeeRate;
    }

    /**
     * 获取 repOtherFeeRate
     *
     * @return repOtherFeeRate
     */
    public BigDecimal getRepOtherFeeRate() {
        return repOtherFeeRate;
    }

    /**
     * 设置 repOtherFeeRate
     *
     * @param repOtherFeeRate repOtherFeeRate
     */
    public void setRepOtherFeeRate(BigDecimal repOtherFeeRate) {
        this.repOtherFeeRate = repOtherFeeRate;
    }

    /**
     * 获取 repAmt
     *
     * @return repAmt
     */
    public BigDecimal getRepAmt() {
        return repAmt;
    }

    /**
     * 设置 repAmt
     *
     * @param repAmt repAmt
     */
    public void setRepAmt(BigDecimal repAmt) {
        this.repAmt = repAmt;
    }

    
    /**
     * 获取 repRemark
     * @return repRemark.
     */
    public String getRepRemark() {
        return repRemark;
    }

    
    /**
     * 设置 repRemark
     * @param repRemark repRemark
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark;
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
}

