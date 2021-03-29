/**
 * 姚振国
 * 2019年10月9日
 * 产品申报信息
 */
package com.joyintech.service.product.productelement.reportregister.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class ProductReportRegisterDto extends BaseEntity {
	/**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 金融产品名称
     */
    private String repFinprodName;

    /**
     * 产品登记编码
     */
    private String repFinprodRegId;

    /**
     * 行内标识码
     */
    private String repFinprodInsId;

    /**
     * 产品品牌
     */
    private String repFinprodBrand;

    /**
     * 产品期次
     */
    private Integer repFinprodStage;

    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 产品审批人身份证号
     */
    private String repApproverId;

    /**
     * 产品审批人姓名
     */
    private String repApproverName;

    /**
     * 产品设计人身份证号
     */
    private String repDesignerId;

    /**
     * 产品设计人姓名
     */
    private String repDesignerName;

    /**
     * 投资经理身份证号
     */
    private String repManagerId;

    /**
     * 投资经理姓名
     */
    private String repManagerName;

    /**
     * 业务联络人姓名
     */
    private String repLiaisonName;

    /**
     * 业务联络人座机
     */
    private String repLiaisonTel;

    /**
     * 业务联络人手机
     */
    private String repLiaisonMobile;

    /**
     * 业务联络人邮箱
     */
    private String repLiaisonEmail;

    /**
     * 产品收益类型
     */
    private String repProfitType;

    /**
     * 产品期限
     */
    private String repTerm;

    /**
     * 投资者类型
     */
    private String repInvestorType;

    /**
     * 资金投向地区
     */
    private String repInvestArea;

    /**
     * 产品投资国家或地区
     */
    private String repInvestCountry;

    /**
     * 理财业务服务模式
     */
    private String repFinanSerModel;

    /**
     * 产品运作模式
     */
    private String repOperateMode;

    /**
     * 会计核算方式
     */
    private String repBookMode;

    /**
     * 产品资产配置方式
     */
    private String repCmmode;

    /**
     * 产品管理模式
     */
    private String repPmmode;

    /**
     * 实际管理人员名称
     */
    private String repMname;

    /**
     * 产品定价方式
     */
    private String repPricedMode;

    /**
     * 投资资产类型
     */
    private String repInvAssetType;

    /**
     * 结构性产品挂钩标的
     */
    private String repConPrdTraget;

    /**
     * 合作模式
     */
    private String repContMode;

    /**
     * 合作机构名称
     */
    private String repCoopName;

    /**
     * 投资资产种类及比例
     */
    private String repInvTypeRat;

    /**
     * 是否有预期收益率
     */
    private String repIsYield;

    /**
     * 预计客户最高年收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repHighYield;

    /**
     * 预计客户最低年收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repLowYield;

    /**
     * 是否有收益率测算依据
     */
    private String repIsYieldBasis;

    /**
     * 投资者风险偏好
     */
    private String repRiskType;

    /**
     * 产品销售区域
     */
    private String repSaleArea;

    /**
     * 募集币种
     */
    private String repRaiseCcy;

    /**
     * 兑付本金币种
     */
    private String repCashPrinCcy;

    /**
     * 兑付收益币种
     */
    private String repCashProfCcy;

    /**
     * 起点销售金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSaleAmtStr;

    /**
     * 计划募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRaiseAmtPlan;

    /**
     * 募集起始日期（从）
     */
    private Date repRaisePstrdate;

    /**
     * 募集起始日期（到）
     */
    private Date repRaisePenddate;

    /**
     * 投资本金到账日
     */
    private String repInvestPrinDate;

    /**
     * 投资收益到账日
     */
    private String repInvestProfDate;

    /**
     * 销售手续费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSaleFeeRate;

    /**
     * 境内托管机构名称
     */
    private String repInDepName;

    /**
     * 境内托管机构代码
     */
    private String repInDepCode;

    /**
     * 境外托管机构国别
     */
    private String repOutDepCtry;

    /**
     * 境外托管机构名称
     */
    private String repOutDepName;

    /**
     * 托管费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTruFeeRate;

    /**
     * 产品风险等级
     */
    private String repRiskLevel;

    /**
     * 发型机构提前终止权标识
     */
    private String repTermRightFlag;

    /**
     * 客户赎回权标识
     */
    private String repCusRedeemFlag;

    /**
     * 产品增信标识
     */
    private String repCreditFlag;

    /**
     * 产品增信机构类型
     */
    private String repCreditType;

    /**
     * 产品增信形式
     */
    private String repCreditMode;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 报告登记日
     */
    private Date repDeclareDate;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

    /**
     * 是否金融同业专属
     */
    private String excFinSector;

    /**
     * 是否设置最短持有期限
     */
    private String isShortTerm;

    /**
     * 最短持有期限(天)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shortTerm;

    /**
     * 最短持有期后是否自由赎回
     */
    private String isFreeRedem;

    /**
     * 客户类型-人行报送
     */
    private String customerType;

    /**
     * 产品募集方式
     */
    private String repIssueType;

    /**
     * 业绩比较基准%
     */
    private String repYield;

    /**
     * 产品投资性质
     */
    private String repInvestNature;

    /**
     * 是否现金管理类
     */
    private String repIsCashManage;

    /**
     * 投资管理费率%
     */
    private String repInvestFeeRate;
    
    /**
     * 新老产品标记
     */
    private String isFhxgcp;
    /**
     * 是否养老理财
     */
    private String isYllccp;

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
     * 取得 金融产品名称
     * @return 金融产品名称
     */
    public String getRepFinprodName() {
        return repFinprodName;
    }

    /**
     * 设置 金融产品名称
     * @param repFinprodName 金融产品名称
     */
    public void setRepFinprodName(String repFinprodName) {
        this.repFinprodName = repFinprodName == null ? null : repFinprodName.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getRepFinprodRegId() {
        return repFinprodRegId;
    }

    /**
     * 设置 产品登记编码
     * @param repFinprodRegId 产品登记编码
     */
    public void setRepFinprodRegId(String repFinprodRegId) {
        this.repFinprodRegId = repFinprodRegId == null ? null : repFinprodRegId.trim();
    }

    /**
     * 取得 行内标识码
     * @return 行内标识码
     */
    public String getRepFinprodInsId() {
        return repFinprodInsId;
    }

    /**
     * 设置 行内标识码
     * @param repFinprodInsId 行内标识码
     */
    public void setRepFinprodInsId(String repFinprodInsId) {
        this.repFinprodInsId = repFinprodInsId == null ? null : repFinprodInsId.trim();
    }

    /**
     * 取得 产品品牌
     * @return 产品品牌
     */
    public String getRepFinprodBrand() {
        return repFinprodBrand;
    }

    /**
     * 设置 产品品牌
     * @param repFinprodBrand 产品品牌
     */
    public void setRepFinprodBrand(String repFinprodBrand) {
        this.repFinprodBrand = repFinprodBrand == null ? null : repFinprodBrand.trim();
    }

    /**
     * 取得 产品期次
     * @return 产品期次
     */
    public Integer getRepFinprodStage() {
        return repFinprodStage;
    }

    /**
     * 设置 产品期次
     * @param repFinprodStage 产品期次
     */
    public void setRepFinprodStage(Integer repFinprodStage) {
        this.repFinprodStage = repFinprodStage;
    }

    /**
     * 取得 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @return 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    /**
     * 设置 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @param repIssuePartyId 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId == null ? null : repIssuePartyId.trim();
    }

    /**
     * 取得 产品审批人身份证号
     * @return 产品审批人身份证号
     */
    public String getRepApproverId() {
        return repApproverId;
    }

    /**
     * 设置 产品审批人身份证号
     * @param repApproverId 产品审批人身份证号
     */
    public void setRepApproverId(String repApproverId) {
        this.repApproverId = repApproverId == null ? null : repApproverId.trim();
    }

    /**
     * 取得 产品审批人姓名
     * @return 产品审批人姓名
     */
    public String getRepApproverName() {
        return repApproverName;
    }

    /**
     * 设置 产品审批人姓名
     * @param repApproverName 产品审批人姓名
     */
    public void setRepApproverName(String repApproverName) {
        this.repApproverName = repApproverName == null ? null : repApproverName.trim();
    }

    /**
     * 取得 产品设计人身份证号
     * @return 产品设计人身份证号
     */
    public String getRepDesignerId() {
        return repDesignerId;
    }

    /**
     * 设置 产品设计人身份证号
     * @param repDesignerId 产品设计人身份证号
     */
    public void setRepDesignerId(String repDesignerId) {
        this.repDesignerId = repDesignerId == null ? null : repDesignerId.trim();
    }

    /**
     * 取得 产品设计人姓名
     * @return 产品设计人姓名
     */
    public String getRepDesignerName() {
        return repDesignerName;
    }

    /**
     * 设置 产品设计人姓名
     * @param repDesignerName 产品设计人姓名
     */
    public void setRepDesignerName(String repDesignerName) {
        this.repDesignerName = repDesignerName == null ? null : repDesignerName.trim();
    }

    /**
     * 取得 投资经理身份证号
     * @return 投资经理身份证号
     */
    public String getRepManagerId() {
        return repManagerId;
    }

    /**
     * 设置 投资经理身份证号
     * @param repManagerId 投资经理身份证号
     */
    public void setRepManagerId(String repManagerId) {
        this.repManagerId = repManagerId == null ? null : repManagerId.trim();
    }

    /**
     * 取得 投资经理姓名
     * @return 投资经理姓名
     */
    public String getRepManagerName() {
        return repManagerName;
    }

    /**
     * 设置 投资经理姓名
     * @param repManagerName 投资经理姓名
     */
    public void setRepManagerName(String repManagerName) {
        this.repManagerName = repManagerName == null ? null : repManagerName.trim();
    }

    /**
     * 取得 业务联络人姓名
     * @return 业务联络人姓名
     */
    public String getRepLiaisonName() {
        return repLiaisonName;
    }

    /**
     * 设置 业务联络人姓名
     * @param repLiaisonName 业务联络人姓名
     */
    public void setRepLiaisonName(String repLiaisonName) {
        this.repLiaisonName = repLiaisonName == null ? null : repLiaisonName.trim();
    }

    /**
     * 取得 业务联络人座机
     * @return 业务联络人座机
     */
    public String getRepLiaisonTel() {
        return repLiaisonTel;
    }

    /**
     * 设置 业务联络人座机
     * @param repLiaisonTel 业务联络人座机
     */
    public void setRepLiaisonTel(String repLiaisonTel) {
        this.repLiaisonTel = repLiaisonTel == null ? null : repLiaisonTel.trim();
    }

    /**
     * 取得 业务联络人手机
     * @return 业务联络人手机
     */
    public String getRepLiaisonMobile() {
        return repLiaisonMobile;
    }

    /**
     * 设置 业务联络人手机
     * @param repLiaisonMobile 业务联络人手机
     */
    public void setRepLiaisonMobile(String repLiaisonMobile) {
        this.repLiaisonMobile = repLiaisonMobile == null ? null : repLiaisonMobile.trim();
    }

    /**
     * 取得 业务联络人邮箱
     * @return 业务联络人邮箱
     */
    public String getRepLiaisonEmail() {
        return repLiaisonEmail;
    }

    /**
     * 设置 业务联络人邮箱
     * @param repLiaisonEmail 业务联络人邮箱
     */
    public void setRepLiaisonEmail(String repLiaisonEmail) {
        this.repLiaisonEmail = repLiaisonEmail == null ? null : repLiaisonEmail.trim();
    }

    /**
     * 取得 产品收益类型
     * @return 产品收益类型
     */
    public String getRepProfitType() {
        return repProfitType;
    }

    /**
     * 设置 产品收益类型
     * @param repProfitType 产品收益类型
     */
    public void setRepProfitType(String repProfitType) {
        this.repProfitType = repProfitType == null ? null : repProfitType.trim();
    }

    /**
     * 取得 产品期限
     * @return 产品期限
     */
    public String getRepTerm() {
        return repTerm;
    }

    /**
     * 设置 产品期限
     * @param repTerm 产品期限
     */
    public void setRepTerm(String repTerm) {
        this.repTerm = repTerm == null ? null : repTerm.trim();
    }

    /**
     * 取得 投资者类型
     * @return 投资者类型
     */
    public String getRepInvestorType() {
        return repInvestorType;
    }

    /**
     * 设置 投资者类型
     * @param repInvestorType 投资者类型
     */
    public void setRepInvestorType(String repInvestorType) {
        this.repInvestorType = repInvestorType == null ? null : repInvestorType.trim();
    }

    /**
     * 取得 资金投向地区
     * @return 资金投向地区
     */
    public String getRepInvestArea() {
        return repInvestArea;
    }

    /**
     * 设置 资金投向地区
     * @param repInvestArea 资金投向地区
     */
    public void setRepInvestArea(String repInvestArea) {
        this.repInvestArea = repInvestArea == null ? null : repInvestArea.trim();
    }

    /**
     * 取得 产品投资国家或地区
     * @return 产品投资国家或地区
     */
    public String getRepInvestCountry() {
        return repInvestCountry;
    }

    /**
     * 设置 产品投资国家或地区
     * @param repInvestCountry 产品投资国家或地区
     */
    public void setRepInvestCountry(String repInvestCountry) {
        this.repInvestCountry = repInvestCountry == null ? null : repInvestCountry.trim();
    }

    /**
     * 取得 理财业务服务模式
     * @return 理财业务服务模式
     */
    public String getRepFinanSerModel() {
        return repFinanSerModel;
    }

    /**
     * 设置 理财业务服务模式
     * @param repFinanSerModel 理财业务服务模式
     */
    public void setRepFinanSerModel(String repFinanSerModel) {
        this.repFinanSerModel = repFinanSerModel == null ? null : repFinanSerModel.trim();
    }

    /**
     * 取得 产品运作模式
     * @return 产品运作模式
     */
    public String getRepOperateMode() {
        return repOperateMode;
    }

    /**
     * 设置 产品运作模式
     * @param repOperateMode 产品运作模式
     */
    public void setRepOperateMode(String repOperateMode) {
        this.repOperateMode = repOperateMode == null ? null : repOperateMode.trim();
    }

    /**
     * 取得 会计核算方式
     * @return 会计核算方式
     */
    public String getRepBookMode() {
        return repBookMode;
    }

    /**
     * 设置 会计核算方式
     * @param repBookMode 会计核算方式
     */
    public void setRepBookMode(String repBookMode) {
        this.repBookMode = repBookMode == null ? null : repBookMode.trim();
    }

    /**
     * 取得 产品资产配置方式
     * @return 产品资产配置方式
     */
    public String getRepCmmode() {
        return repCmmode;
    }

    /**
     * 设置 产品资产配置方式
     * @param repCmmode 产品资产配置方式
     */
    public void setRepCmmode(String repCmmode) {
        this.repCmmode = repCmmode == null ? null : repCmmode.trim();
    }

    /**
     * 取得 产品管理模式
     * @return 产品管理模式
     */
    public String getRepPmmode() {
        return repPmmode;
    }

    /**
     * 设置 产品管理模式
     * @param repPmmode 产品管理模式
     */
    public void setRepPmmode(String repPmmode) {
        this.repPmmode = repPmmode == null ? null : repPmmode.trim();
    }

    /**
     * 取得 实际管理人员名称
     * @return 实际管理人员名称
     */
    public String getRepMname() {
        return repMname;
    }

    /**
     * 设置 实际管理人员名称
     * @param repMname 实际管理人员名称
     */
    public void setRepMname(String repMname) {
        this.repMname = repMname == null ? null : repMname.trim();
    }

    /**
     * 取得 产品定价方式
     * @return 产品定价方式
     */
    public String getRepPricedMode() {
        return repPricedMode;
    }

    /**
     * 设置 产品定价方式
     * @param repPricedMode 产品定价方式
     */
    public void setRepPricedMode(String repPricedMode) {
        this.repPricedMode = repPricedMode == null ? null : repPricedMode.trim();
    }

    /**
     * 取得 投资资产类型
     * @return 投资资产类型
     */
    public String getRepInvAssetType() {
        return repInvAssetType;
    }

    /**
     * 设置 投资资产类型
     * @param repInvAssetType 投资资产类型
     */
    public void setRepInvAssetType(String repInvAssetType) {
        this.repInvAssetType = repInvAssetType == null ? null : repInvAssetType.trim();
    }

    /**
     * 取得 结构性产品挂钩标的
     * @return 结构性产品挂钩标的
     */
    public String getRepConPrdTraget() {
        return repConPrdTraget;
    }

    /**
     * 设置 结构性产品挂钩标的
     * @param repConPrdTraget 结构性产品挂钩标的
     */
    public void setRepConPrdTraget(String repConPrdTraget) {
        this.repConPrdTraget = repConPrdTraget == null ? null : repConPrdTraget.trim();
    }

    /**
     * 取得 合作模式
     * @return 合作模式
     */
    public String getRepContMode() {
        return repContMode;
    }

    /**
     * 设置 合作模式
     * @param repContMode 合作模式
     */
    public void setRepContMode(String repContMode) {
        this.repContMode = repContMode == null ? null : repContMode.trim();
    }

    /**
     * 取得 合作机构名称
     * @return 合作机构名称
     */
    public String getRepCoopName() {
        return repCoopName;
    }

    /**
     * 设置 合作机构名称
     * @param repCoopName 合作机构名称
     */
    public void setRepCoopName(String repCoopName) {
        this.repCoopName = repCoopName == null ? null : repCoopName.trim();
    }

    /**
     * 取得 投资资产种类及比例
     * @return 投资资产种类及比例
     */
    public String getRepInvTypeRat() {
        return repInvTypeRat;
    }

    /**
     * 设置 投资资产种类及比例
     * @param repInvTypeRat 投资资产种类及比例
     */
    public void setRepInvTypeRat(String repInvTypeRat) {
        this.repInvTypeRat = repInvTypeRat == null ? null : repInvTypeRat.trim();
    }

    /**
     * 取得 是否有预期收益率
     * @return 是否有预期收益率
     */
    public String getRepIsYield() {
        return repIsYield;
    }

    /**
     * 设置 是否有预期收益率
     * @param repIsYield 是否有预期收益率
     */
    public void setRepIsYield(String repIsYield) {
        this.repIsYield = repIsYield == null ? null : repIsYield.trim();
    }

    /**
     * 取得 预计客户最高年收益率
     * @return 预计客户最高年收益率
     */
    public BigDecimal getRepHighYield() {
        return repHighYield;
    }

    /**
     * 设置 预计客户最高年收益率
     * @param repHighYield 预计客户最高年收益率
     */
    public void setRepHighYield(BigDecimal repHighYield) {
        this.repHighYield = repHighYield;
    }

    /**
     * 取得 预计客户最低年收益率
     * @return 预计客户最低年收益率
     */
    public BigDecimal getRepLowYield() {
        return repLowYield;
    }

    /**
     * 设置 预计客户最低年收益率
     * @param repLowYield 预计客户最低年收益率
     */
    public void setRepLowYield(BigDecimal repLowYield) {
        this.repLowYield = repLowYield;
    }

    /**
     * 取得 是否有收益率测算依据
     * @return 是否有收益率测算依据
     */
    public String getRepIsYieldBasis() {
        return repIsYieldBasis;
    }

    /**
     * 设置 是否有收益率测算依据
     * @param repIsYieldBasis 是否有收益率测算依据
     */
    public void setRepIsYieldBasis(String repIsYieldBasis) {
        this.repIsYieldBasis = repIsYieldBasis == null ? null : repIsYieldBasis.trim();
    }

    /**
     * 取得 投资者风险偏好
     * @return 投资者风险偏好
     */
    public String getRepRiskType() {
        return repRiskType;
    }

    /**
     * 设置 投资者风险偏好
     * @param repRiskType 投资者风险偏好
     */
    public void setRepRiskType(String repRiskType) {
        this.repRiskType = repRiskType == null ? null : repRiskType.trim();
    }

    /**
     * 取得 产品销售区域
     * @return 产品销售区域
     */
    public String getRepSaleArea() {
        return repSaleArea;
    }

    /**
     * 设置 产品销售区域
     * @param repSaleArea 产品销售区域
     */
    public void setRepSaleArea(String repSaleArea) {
        this.repSaleArea = repSaleArea == null ? null : repSaleArea.trim();
    }

    /**
     * 取得 募集币种
     * @return 募集币种
     */
    public String getRepRaiseCcy() {
        return repRaiseCcy;
    }

    /**
     * 设置 募集币种
     * @param repRaiseCcy 募集币种
     */
    public void setRepRaiseCcy(String repRaiseCcy) {
        this.repRaiseCcy = repRaiseCcy == null ? null : repRaiseCcy.trim();
    }

    /**
     * 取得 兑付本金币种
     * @return 兑付本金币种
     */
    public String getRepCashPrinCcy() {
        return repCashPrinCcy;
    }

    /**
     * 设置 兑付本金币种
     * @param repCashPrinCcy 兑付本金币种
     */
    public void setRepCashPrinCcy(String repCashPrinCcy) {
        this.repCashPrinCcy = repCashPrinCcy == null ? null : repCashPrinCcy.trim();
    }

    /**
     * 取得 兑付收益币种
     * @return 兑付收益币种
     */
    public String getRepCashProfCcy() {
        return repCashProfCcy;
    }

    /**
     * 设置 兑付收益币种
     * @param repCashProfCcy 兑付收益币种
     */
    public void setRepCashProfCcy(String repCashProfCcy) {
        this.repCashProfCcy = repCashProfCcy == null ? null : repCashProfCcy.trim();
    }

    /**
     * 取得 起点销售金额
     * @return 起点销售金额
     */
    public BigDecimal getRepSaleAmtStr() {
        return repSaleAmtStr;
    }

    /**
     * 设置 起点销售金额
     * @param repSaleAmtStr 起点销售金额
     */
    public void setRepSaleAmtStr(BigDecimal repSaleAmtStr) {
        this.repSaleAmtStr = repSaleAmtStr;
    }

    /**
     * 取得 计划募集金额
     * @return 计划募集金额
     */
    public BigDecimal getRepRaiseAmtPlan() {
        return repRaiseAmtPlan;
    }

    /**
     * 设置 计划募集金额
     * @param repRaiseAmtPlan 计划募集金额
     */
    public void setRepRaiseAmtPlan(BigDecimal repRaiseAmtPlan) {
        this.repRaiseAmtPlan = repRaiseAmtPlan;
    }

    /**
     * 取得 募集起始日期（从）
     * @return 募集起始日期（从）
     */
    public Date getRepRaisePstrdate() {
        return repRaisePstrdate;
    }

    /**
     * 设置 募集起始日期（从）
     * @param repRaisePstrdate 募集起始日期（从）
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepRaisePstrdate(Date repRaisePstrdate) {
        this.repRaisePstrdate = repRaisePstrdate;
    }

    /**
     * 取得 募集起始日期（到）
     * @return 募集起始日期（到）
     */
    public Date getRepRaisePenddate() {
        return repRaisePenddate;
    }

    /**
     * 设置 募集起始日期（到）
     * @param repRaisePenddate 募集起始日期（到）
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepRaisePenddate(Date repRaisePenddate) {
        this.repRaisePenddate = repRaisePenddate;
    }

    /**
     * 取得 投资本金到账日
     * @return 投资本金到账日
     */
    public String getRepInvestPrinDate() {
        return repInvestPrinDate;
    }

    /**
     * 设置 投资本金到账日
     * @param repInvestPrinDate 投资本金到账日
     */
    public void setRepInvestPrinDate(String repInvestPrinDate) {
        this.repInvestPrinDate = repInvestPrinDate == null ? null : repInvestPrinDate.trim();
    }

    /**
     * 取得 投资收益到账日
     * @return 投资收益到账日
     */
    public String getRepInvestProfDate() {
        return repInvestProfDate;
    }

    /**
     * 设置 投资收益到账日
     * @param repInvestProfDate 投资收益到账日
     */
    public void setRepInvestProfDate(String repInvestProfDate) {
        this.repInvestProfDate = repInvestProfDate == null ? null : repInvestProfDate.trim();
    }

    /**
     * 取得 销售手续费
     * @return 销售手续费
     */
    public BigDecimal getRepSaleFeeRate() {
        return repSaleFeeRate;
    }

    /**
     * 设置 销售手续费
     * @param repSaleFeeRate 销售手续费
     */
    public void setRepSaleFeeRate(BigDecimal repSaleFeeRate) {
        this.repSaleFeeRate = repSaleFeeRate;
    }

    /**
     * 取得 境内托管机构名称
     * @return 境内托管机构名称
     */
    public String getRepInDepName() {
        return repInDepName;
    }

    /**
     * 设置 境内托管机构名称
     * @param repInDepName 境内托管机构名称
     */
    public void setRepInDepName(String repInDepName) {
        this.repInDepName = repInDepName == null ? null : repInDepName.trim();
    }

    /**
     * 取得 境内托管机构代码
     * @return 境内托管机构代码
     */
    public String getRepInDepCode() {
        return repInDepCode;
    }

    /**
     * 设置 境内托管机构代码
     * @param repInDepCode 境内托管机构代码
     */
    public void setRepInDepCode(String repInDepCode) {
        this.repInDepCode = repInDepCode == null ? null : repInDepCode.trim();
    }

    /**
     * 取得 境外托管机构国别
     * @return 境外托管机构国别
     */
    public String getRepOutDepCtry() {
        return repOutDepCtry;
    }

    /**
     * 设置 境外托管机构国别
     * @param repOutDepCtry 境外托管机构国别
     */
    public void setRepOutDepCtry(String repOutDepCtry) {
        this.repOutDepCtry = repOutDepCtry == null ? null : repOutDepCtry.trim();
    }

    /**
     * 取得 境外托管机构名称
     * @return 境外托管机构名称
     */
    public String getRepOutDepName() {
        return repOutDepName;
    }

    /**
     * 设置 境外托管机构名称
     * @param repOutDepName 境外托管机构名称
     */
    public void setRepOutDepName(String repOutDepName) {
        this.repOutDepName = repOutDepName == null ? null : repOutDepName.trim();
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
     * 取得 产品风险等级
     * @return 产品风险等级
     */
    public String getRepRiskLevel() {
        return repRiskLevel;
    }

    /**
     * 设置 产品风险等级
     * @param repRiskLevel 产品风险等级
     */
    public void setRepRiskLevel(String repRiskLevel) {
        this.repRiskLevel = repRiskLevel == null ? null : repRiskLevel.trim();
    }

    /**
     * 取得 发型机构提前终止权标识
     * @return 发型机构提前终止权标识
     */
    public String getRepTermRightFlag() {
        return repTermRightFlag;
    }

    /**
     * 设置 发型机构提前终止权标识
     * @param repTermRightFlag 发型机构提前终止权标识
     */
    public void setRepTermRightFlag(String repTermRightFlag) {
        this.repTermRightFlag = repTermRightFlag == null ? null : repTermRightFlag.trim();
    }

    /**
     * 取得 客户赎回权标识
     * @return 客户赎回权标识
     */
    public String getRepCusRedeemFlag() {
        return repCusRedeemFlag;
    }

    /**
     * 设置 客户赎回权标识
     * @param repCusRedeemFlag 客户赎回权标识
     */
    public void setRepCusRedeemFlag(String repCusRedeemFlag) {
        this.repCusRedeemFlag = repCusRedeemFlag == null ? null : repCusRedeemFlag.trim();
    }

    /**
     * 取得 产品增信标识
     * @return 产品增信标识
     */
    public String getRepCreditFlag() {
        return repCreditFlag;
    }

    /**
     * 设置 产品增信标识
     * @param repCreditFlag 产品增信标识
     */
    public void setRepCreditFlag(String repCreditFlag) {
        this.repCreditFlag = repCreditFlag == null ? null : repCreditFlag.trim();
    }

    /**
     * 取得 产品增信机构类型
     * @return 产品增信机构类型
     */
    public String getRepCreditType() {
        return repCreditType;
    }

    /**
     * 设置 产品增信机构类型
     * @param repCreditType 产品增信机构类型
     */
    public void setRepCreditType(String repCreditType) {
        this.repCreditType = repCreditType == null ? null : repCreditType.trim();
    }

    /**
     * 取得 产品增信形式
     * @return 产品增信形式
     */
    public String getRepCreditMode() {
        return repCreditMode;
    }

    /**
     * 设置 产品增信形式
     * @param repCreditMode 产品增信形式
     */
    public void setRepCreditMode(String repCreditMode) {
        this.repCreditMode = repCreditMode == null ? null : repCreditMode.trim();
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

    /**
     * 取得 报告登记日
     * @return 报告登记日
     */
    public Date getRepDeclareDate() {
        return repDeclareDate;
    }

    /**
     * 设置 报告登记日
     * @param repDeclareDate 报告登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepDeclareDate(Date repDeclareDate) {
        this.repDeclareDate = repDeclareDate;
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getRepSendStatus() {
        return repSendStatus;
    }

    /**
     * 设置 报送状态
     * @param repSendStatus 报送状态
     */
    public void setRepSendStatus(String repSendStatus) {
        this.repSendStatus = repSendStatus == null ? null : repSendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRepStatusDate() {
        return repStatusDate;
    }

    /**
     * 设置 数据日期
     * @param repStatusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepStatusDate(Date repStatusDate) {
        this.repStatusDate = repStatusDate;
    }

    /**
     * 取得 是否金融同业专属
     * @return 是否金融同业专属
     */
    public String getExcFinSector() {
        return excFinSector;
    }

    /**
     * 设置 是否金融同业专属
     * @param excFinSector 是否金融同业专属
     */
    public void setExcFinSector(String excFinSector) {
        this.excFinSector = excFinSector == null ? null : excFinSector.trim();
    }

    /**
     * 取得 是否设置最短持有期限
     * @return 是否设置最短持有期限
     */
    public String getIsShortTerm() {
        return isShortTerm;
    }

    /**
     * 设置 是否设置最短持有期限
     * @param isShortTerm 是否设置最短持有期限
     */
    public void setIsShortTerm(String isShortTerm) {
        this.isShortTerm = isShortTerm == null ? null : isShortTerm.trim();
    }

    /**
     * 取得 最短持有期限(天)
     * @return 最短持有期限(天)
     */
    public BigDecimal getShortTerm() {
        return shortTerm;
    }

    /**
     * 设置 最短持有期限(天)
     * @param shortTerm 最短持有期限(天)
     */
    public void setShortTerm(BigDecimal shortTerm) {
        this.shortTerm = shortTerm;
    }

    /**
     * 取得 最短持有期后是否自由赎回
     * @return 最短持有期后是否自由赎回
     */
    public String getIsFreeRedem() {
        return isFreeRedem;
    }

    /**
     * 设置 最短持有期后是否自由赎回
     * @param isFreeRedem 最短持有期后是否自由赎回
     */
    public void setIsFreeRedem(String isFreeRedem) {
        this.isFreeRedem = isFreeRedem == null ? null : isFreeRedem.trim();
    }

    /**
     * 取得 客户类型-人行报送
     * @return 客户类型-人行报送
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * 设置 客户类型-人行报送
     * @param customerType 客户类型-人行报送
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    /**
     * 取得 产品募集方式
     * @return 产品募集方式
     */
    public String getRepIssueType() {
        return repIssueType;
    }

    /**
     * 设置 产品募集方式
     * @param repIssueType 产品募集方式
     */
    public void setRepIssueType(String repIssueType) {
        this.repIssueType = repIssueType == null ? null : repIssueType.trim();
    }

    /**
     * 取得 业绩比较基准%
     * @return 业绩比较基准%
     */
    public String getRepYield() {
        return repYield;
    }

    /**
     * 设置 业绩比较基准%
     * @param repYield 业绩比较基准%
     */
    public void setRepYield(String repYield) {
        this.repYield = repYield == null ? null : repYield.trim();
    }

    /**
     * 取得 产品投资性质
     * @return 产品投资性质
     */
    public String getRepInvestNature() {
        return repInvestNature;
    }

    /**
     * 设置 产品投资性质
     * @param repInvestNature 产品投资性质
     */
    public void setRepInvestNature(String repInvestNature) {
        this.repInvestNature = repInvestNature == null ? null : repInvestNature.trim();
    }

    /**
     * 取得 是否现金管理类
     * @return 是否现金管理类
     */
    public String getRepIsCashManage() {
        return repIsCashManage;
    }

    /**
     * 设置 是否现金管理类
     * @param repIsCashManage 是否现金管理类
     */
    public void setRepIsCashManage(String repIsCashManage) {
        this.repIsCashManage = repIsCashManage == null ? null : repIsCashManage.trim();
    }

    /**
     * 取得 投资管理费率%
     * @return 投资管理费率%
     */
    public String getRepInvestFeeRate() {
        return repInvestFeeRate;
    }

    /**
     * 设置 投资管理费率%
     * @param repInvestFeeRate 投资管理费率%
     */
    public void setRepInvestFeeRate(String repInvestFeeRate) {
        this.repInvestFeeRate = repInvestFeeRate == null ? null : repInvestFeeRate.trim();
    }

	public String getIsFhxgcp() {
		return isFhxgcp;
	}

	public void setIsFhxgcp(String isFhxgcp) {
		this.isFhxgcp = isFhxgcp;
	}

	public String getIsYllccp() {
		return isYllccp;
	}

	public void setIsYllccp(String isYllccp) {
		this.isYllccp = isYllccp;
	}
}
