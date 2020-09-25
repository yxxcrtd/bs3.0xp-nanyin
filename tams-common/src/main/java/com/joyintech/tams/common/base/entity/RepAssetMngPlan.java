package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepAssetMngPlan extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 发起人机构编码
     */
    private String repPartyCode;

    /**
     * 登记编码
     */
    private String repRegistCode;

    /**
     * 管理人
     */
    private String repManagerId;

    /**
     * 托管人
     */
    private String repTrusteeId;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAmt;

    /**
     * 资金实际投向
     */
    private String repCapiInvest;

    /**
     * 资金运用方式
     */
    private String repCapUseWay;

    /**
     * 资金运用行业
     */
    private String repCapUseIndu;

    /**
     * 资金运用行业二级分类
     */
    private String repCapUseInduLevel2;

    /**
     * 成立日
     */
    private Date repSetDate;

    /**
     * 终止日
     */
    private Date repEndDate;

    /**
     * 资管计划属性
     */
    private String repPlanProp;

    /**
     * 是否有预期收益率
     */
    private String repYieldFlag;

    /**
     * 最高预期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMaxRate;

    /**
     * 最低预期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMinRate;

    /**
     * 购买结构
     */
    private String repBuyStruct;

    /**
     * 管理方式
     */
    private String repManageType;

    /**
     * 是否掌控计划底层资产详情
     */
    private String repIsCtrlBottom;

    /**
     * 管理费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMgrFeeRate;

    /**
     * 托管费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTruFeeRate;

    /**
     * 交易相关合计费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTradeFeeRate;

    /**
     * 中介服务机构合计费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAgencyFeeRate;

    /**
     * 其他合计费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherFeeRate;

    /**
     * 备注
     */
    private String repRemark;

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
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 发起人机构编码
     * @return 发起人机构编码
     */
    public String getRepPartyCode() {
        return repPartyCode;
    }

    /**
     * 设置 发起人机构编码
     * @param repPartyCode 发起人机构编码
     */
    public void setRepPartyCode(String repPartyCode) {
        this.repPartyCode = repPartyCode == null ? null : repPartyCode.trim();
    }

    /**
     * 取得 登记编码
     * @return 登记编码
     */
    public String getRepRegistCode() {
        return repRegistCode;
    }

    /**
     * 设置 登记编码
     * @param repRegistCode 登记编码
     */
    public void setRepRegistCode(String repRegistCode) {
        this.repRegistCode = repRegistCode == null ? null : repRegistCode.trim();
    }

    /**
     * 取得 管理人
     * @return 管理人
     */
    public String getRepManagerId() {
        return repManagerId;
    }

    /**
     * 设置 管理人
     * @param repManagerId 管理人
     */
    public void setRepManagerId(String repManagerId) {
        this.repManagerId = repManagerId == null ? null : repManagerId.trim();
    }

    /**
     * 取得 托管人
     * @return 托管人
     */
    public String getRepTrusteeId() {
        return repTrusteeId;
    }

    /**
     * 设置 托管人
     * @param repTrusteeId 托管人
     */
    public void setRepTrusteeId(String repTrusteeId) {
        this.repTrusteeId = repTrusteeId == null ? null : repTrusteeId.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getRepAmt() {
        return repAmt;
    }

    /**
     * 设置 金额
     * @param repAmt 金额
     */
    public void setRepAmt(BigDecimal repAmt) {
        this.repAmt = repAmt;
    }

    /**
     * 取得 资金实际投向
     * @return 资金实际投向
     */
    public String getRepCapiInvest() {
        return repCapiInvest;
    }

    /**
     * 设置 资金实际投向
     * @param repCapiInvest 资金实际投向
     */
    public void setRepCapiInvest(String repCapiInvest) {
        this.repCapiInvest = repCapiInvest == null ? null : repCapiInvest.trim();
    }

    /**
     * 取得 资金运用方式
     * @return 资金运用方式
     */
    public String getRepCapUseWay() {
        return repCapUseWay;
    }

    /**
     * 设置 资金运用方式
     * @param repCapUseWay 资金运用方式
     */
    public void setRepCapUseWay(String repCapUseWay) {
        this.repCapUseWay = repCapUseWay == null ? null : repCapUseWay.trim();
    }

    /**
     * 取得 资金运用行业
     * @return 资金运用行业
     */
    public String getRepCapUseIndu() {
        return repCapUseIndu;
    }

    /**
     * 设置 资金运用行业
     * @param repCapUseIndu 资金运用行业
     */
    public void setRepCapUseIndu(String repCapUseIndu) {
        this.repCapUseIndu = repCapUseIndu == null ? null : repCapUseIndu.trim();
    }

    /**
     * 取得 资金运用行业二级分类
     * @return 资金运用行业二级分类
     */
    public String getRepCapUseInduLevel2() {
        return repCapUseInduLevel2;
    }

    /**
     * 设置 资金运用行业二级分类
     * @param repCapUseInduLevel2 资金运用行业二级分类
     */
    public void setRepCapUseInduLevel2(String repCapUseInduLevel2) {
        this.repCapUseInduLevel2 = repCapUseInduLevel2 == null ? null : repCapUseInduLevel2.trim();
    }

    /**
     * 取得 成立日
     * @return 成立日
     */
    public Date getRepSetDate() {
        return repSetDate;
    }

    /**
     * 设置 成立日
     * @param repSetDate 成立日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepSetDate(Date repSetDate) {
        this.repSetDate = repSetDate;
    }

    /**
     * 取得 终止日
     * @return 终止日
     */
    public Date getRepEndDate() {
        return repEndDate;
    }

    /**
     * 设置 终止日
     * @param repEndDate 终止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepEndDate(Date repEndDate) {
        this.repEndDate = repEndDate;
    }

    /**
     * 取得 资管计划属性
     * @return 资管计划属性
     */
    public String getRepPlanProp() {
        return repPlanProp;
    }

    /**
     * 设置 资管计划属性
     * @param repPlanProp 资管计划属性
     */
    public void setRepPlanProp(String repPlanProp) {
        this.repPlanProp = repPlanProp == null ? null : repPlanProp.trim();
    }

    /**
     * 取得 是否有预期收益率
     * @return 是否有预期收益率
     */
    public String getRepYieldFlag() {
        return repYieldFlag;
    }

    /**
     * 设置 是否有预期收益率
     * @param repYieldFlag 是否有预期收益率
     */
    public void setRepYieldFlag(String repYieldFlag) {
        this.repYieldFlag = repYieldFlag == null ? null : repYieldFlag.trim();
    }

    /**
     * 取得 最高预期收益率
     * @return 最高预期收益率
     */
    public BigDecimal getRepMaxRate() {
        return repMaxRate;
    }

    /**
     * 设置 最高预期收益率
     * @param repMaxRate 最高预期收益率
     */
    public void setRepMaxRate(BigDecimal repMaxRate) {
        this.repMaxRate = repMaxRate;
    }

    /**
     * 取得 最低预期收益率
     * @return 最低预期收益率
     */
    public BigDecimal getRepMinRate() {
        return repMinRate;
    }

    /**
     * 设置 最低预期收益率
     * @param repMinRate 最低预期收益率
     */
    public void setRepMinRate(BigDecimal repMinRate) {
        this.repMinRate = repMinRate;
    }

    /**
     * 取得 购买结构
     * @return 购买结构
     */
    public String getRepBuyStruct() {
        return repBuyStruct;
    }

    /**
     * 设置 购买结构
     * @param repBuyStruct 购买结构
     */
    public void setRepBuyStruct(String repBuyStruct) {
        this.repBuyStruct = repBuyStruct == null ? null : repBuyStruct.trim();
    }

    /**
     * 取得 管理方式
     * @return 管理方式
     */
    public String getRepManageType() {
        return repManageType;
    }

    /**
     * 设置 管理方式
     * @param repManageType 管理方式
     */
    public void setRepManageType(String repManageType) {
        this.repManageType = repManageType == null ? null : repManageType.trim();
    }

    /**
     * 取得 是否掌控计划底层资产详情
     * @return 是否掌控计划底层资产详情
     */
    public String getRepIsCtrlBottom() {
        return repIsCtrlBottom;
    }

    /**
     * 设置 是否掌控计划底层资产详情
     * @param repIsCtrlBottom 是否掌控计划底层资产详情
     */
    public void setRepIsCtrlBottom(String repIsCtrlBottom) {
        this.repIsCtrlBottom = repIsCtrlBottom == null ? null : repIsCtrlBottom.trim();
    }

    /**
     * 取得 管理费率
     * @return 管理费率
     */
    public BigDecimal getRepMgrFeeRate() {
        return repMgrFeeRate;
    }

    /**
     * 设置 管理费率
     * @param repMgrFeeRate 管理费率
     */
    public void setRepMgrFeeRate(BigDecimal repMgrFeeRate) {
        this.repMgrFeeRate = repMgrFeeRate;
    }

    /**
     * 取得 托管费率
     * @return 托管费率
     */
    public BigDecimal getRepTruFeeRate() {
        return repTruFeeRate;
    }

    /**
     * 设置 托管费率
     * @param repTruFeeRate 托管费率
     */
    public void setRepTruFeeRate(BigDecimal repTruFeeRate) {
        this.repTruFeeRate = repTruFeeRate;
    }

    /**
     * 取得 交易相关合计费率
     * @return 交易相关合计费率
     */
    public BigDecimal getRepTradeFeeRate() {
        return repTradeFeeRate;
    }

    /**
     * 设置 交易相关合计费率
     * @param repTradeFeeRate 交易相关合计费率
     */
    public void setRepTradeFeeRate(BigDecimal repTradeFeeRate) {
        this.repTradeFeeRate = repTradeFeeRate;
    }

    /**
     * 取得 中介服务机构合计费率
     * @return 中介服务机构合计费率
     */
    public BigDecimal getRepAgencyFeeRate() {
        return repAgencyFeeRate;
    }

    /**
     * 设置 中介服务机构合计费率
     * @param repAgencyFeeRate 中介服务机构合计费率
     */
    public void setRepAgencyFeeRate(BigDecimal repAgencyFeeRate) {
        this.repAgencyFeeRate = repAgencyFeeRate;
    }

    /**
     * 取得 其他合计费率
     * @return 其他合计费率
     */
    public BigDecimal getRepOtherFeeRate() {
        return repOtherFeeRate;
    }

    /**
     * 设置 其他合计费率
     * @param repOtherFeeRate 其他合计费率
     */
    public void setRepOtherFeeRate(BigDecimal repOtherFeeRate) {
        this.repOtherFeeRate = repOtherFeeRate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }
}