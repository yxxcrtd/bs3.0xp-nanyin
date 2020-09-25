/*
 * 文件名：RepAssetDeptRegistDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repassetdeptregist.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产要素登记
 * @author fanshuanghu
 * @version 1.0 2019年1月19日
 * @see RepAssetDeptRegistDto
 * @since 1.0
 */
public class RepAssetDeptRegistDto extends RepAssetDept{

    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 资金存入银行
     */
    private String repBankName;

    /**
     * 存款金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repPrinAmt;

    /**
     * 起息日
     */
    private Date repVdate;

    /**
     * 到期日
     */
    private Date repMdate;

    /**
     * 年利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRateYear;

    /**
     * 计息基础
     */
    private String repBasis;

    /**
     * 是否为结构性存款
     */
    private String repIsStruct;

    /**
     * 挂钩标的类别
     */
    private String repTargetType;

    /**
     * 结构性存款挂钩标的
     */
    private String repTarget;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 债券代码
     */
    private String secId;

    /**
     * 具体类别
     */
    private String repSectype;

    /**
     * 发行方式
     */
    private String repIssueMode;

    /**
     * 主体评级
     */
    private String repMainGrade;

    /**
     * 发行机构类型（按规模划分） 
     */
    private String repPartyType1;

    /**
     * 发行机构类型（按经济类型划分） 
     */
    private String repPartyType2;

    /**
     * 发行机构类型（按技术领域划分）
     */
    private String repPartyType3;

    /**
     * 发行机构所属行业
     */
    private String repIndustry;

    /**
     * 发行机构所属行业二级分类
     */
    private String repIndustryLevel2;

    /**
     * 登记托管机构
     */
    private String repRegistOrg;

    /**
     * 登记托管机构说明
     */
    private String repRegistorgComment;

    /**
     * 起息日
     */
    private Date repValueDate;

    /**
     * 到期日
     */
    private Date repExpiringDate;

    /**
     * 对手方
     */
    private String repCounterparty;

    /**
     * 回购标的金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTargetAmt;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAmt;

    /**
     * 收/受益权类型 参照数据字典
     */
    private String repProfitType;

    /**
     * 是否属于买入返售
     */
    private String repIsResale;

    /**
     * 项目期限
     */
    private Integer repTerm;

    /**
     * 是否有预期收益率
     */
    private String repRateFlag;

    /**
     * 项目收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repProjYield;

    /**
     * 付息频率(个月/次)
     */
    private Short repIntpayCycle;

    /**
     * 还本付息情况说明
     */
    private String repRepayCmt;

    /**
     * 基准利率种类
     */
    private String repBasisType;

    /**
     * 浮动因子
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repFloatFactor;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSpreadRate;

    /**
     * 融资人
     */
    private String repFinancier;

    /**
     * 融资人内部信用评级
     */
    private String repFcrGradeIn;

    /**
     * 外部评级机构及对融资人评级
     */
    private String repFcrGradeOut;

    /**
     * 融资人类型（按规模划分）
     */
    private String repFinancierType1;

    /**
     * 融资人类型（按经济类型划分）
     */
    private String repFinancierType2;

    /**
     * 融资人类型（按技术领域划分）
     */
    private String repFinancierType3;

    /**
     * 融资项目
     */
    private String repFinanceProj;

    /**
     * 融资人所属行业
     */
    private String repIndustry1;

    /**
     * 融资项目所属行业
     */
    private String repProIndustry;

    /**
     * 融资项目所属行业二级分类
     */
    private String repProIndustryLevel2;

    /**
     * 是否属于重点监控行业和领域
     */
    private String repIndustryFlag;

    /**
     * 重点监控行业和领域类别
     */
    private String repMonitType;

    /**
     * 重点监控行业和领域类别说明
     */
    private String repMonTypeComm;

    /**
     * 担保方式
     */
    private String repGuaType;

    /**
     * 担保方式说明
     */
    private String repGuaTypeRemark;

    /**
     * 融资人主体评级
     */
    private String repFinancierGrade;

    /**
     * 资产内部评级
     */
    private String repInGrade;

    /**
     * 资产外部评级
     */
    private String repOutGrade;

    /**
     * 选择权
     */
    private String repChose;

    /**
     * 行权条件说明：默认“无”
     */
    private String repConditionCmm;

    /**
     * 融资人所属地区
     */
    private String repFinArea;

    /**
     * 融资总费率(%),不需要除100
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTotalFeeRate;

    /**
     * 融资人组织机构代码
     */
    private String repFinOrgCode;

    /**
     * 是否为收/受益权
     */
    private String repIsRight;

    /**
     * 类型
     */
    private String repAstBillType;

    /**
     * 数量
     */
    private Integer repNum;

    /**
     * 合计金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAmtSum;

    /**
     * 加权剩余期限(天)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRemainTerm;

    /**
     * 最长剩余期限(天)
     */
    private Integer repMaxRemainTerm;

    /**
     * 最短剩余期限(天)
     */
    private Integer repMinRemainTerm;

    /**
     * 贴现利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repDiscRate;
    /**
     * 股票代码
     */
    private String repSharesId;

    /**
     * 股票类型
     */
    private String repSharesType;

    /**
     * 投资阶段
     */
    private String repInvestStage;

    /**
     * 是否为质押融资
     */
    private String repIsPledge;
    /**
     * 名义本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repNomCapi;

    /**
     * 标的类别
     */
    private String repDeriType;

    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea;
    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea8;
    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea9;
    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea10;
    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea11;
    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea13;
    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea14;
    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea15;
    /**
     * 所属国家或地区 参照数据字典
     */
    private String repCountryArea18;

    /**
     * 债券代码
     */
    private String repSecId;

    /**
     * 发行机构
     */
    private String repIssueParty;
    /**
     * 发行机构主体信用评级
     */
    private String repPartyGrade;

    /**
     * 债券信用评级
     */
    private String repSecGrade;

    /**
     * 票面利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repPaperRate;

    /**
     * 担保情况说明
     */
    private String repGuaranteeCmt;

    /**
     * 是否含权等特殊条款情况说明
     */
    private String repSpecialCmt;

    /**
     * 对手方
     */
    private String repRivalParty;
    /**
     * 股票/基金代码
     */
    private String repShrsFundId;

    /**
     * 固定收益部分所占比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repIncomeRatio;

    /**
     * 衍生金融工具所占比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repDerInstrRatio;

    /**
     * 衍生金融工具具体投资方式
     */
    private String repDerInstrInvType;

    /**
     * 衍生金融工具挂钩标的资产
     */
    private String repDerInstrTarget;

    /**
     * 利息结算方式
     */
    private String repAcctMode;

    /**
     * 结构性票据最高收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMaxYield;

    /**
     * 结构性票据最低收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMinYield;

    /**
     * 挂钩标的资产基准价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repBasicPrice;

    /**
     * 挂钩标的资产登记日价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRegDatePrice;

    /**
     * 交易费
     */
    private String repTradeFee;

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
     * 是否属于政府投资基金
     */
    private String repIsGovInvest;

    /**
     * 政府投资基金投向
     */
    private String repGovInvestFor;

    /**
     * 基金公司名称
     */
    private String repFundCompName;

    /**
     * 基金管理机构名称
     */
    private String repMngPartyName;

    /**
     * 基金托管机构名称
     */
    private String repTrustPartyName;

    /**
     * 基金投资资产
     */
    private String repFundInvestAsset;

    /**
     * 受托人
     */
    private String repTrustee;

    /**
     * 实际管理人
     */
    private String repActualManager;

    /**
     * 托管人
     */
    private String repCustodian;

    /**
     * 委托投资金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repEntrustInvestMoney;

    /**
     * 资金运用方式
     */
    private String repApplicationMode;

    /**
     * 投资运作起始日期
     */
    private Date repInvestStartdate;

    /**
     * 投资运作终止日期
     */
    private Date repInvestEnddate;

    /**
     * 委外投资属性_中债
     */
    private String repTrustInvestAttribute;

    /**
     * 是否有预期收益率
     */
    private String repContainExpectedYield;

    /**
     * 预期最高收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMaxExpectedYield;

    /**
     * 预期最低收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMinExpectedYield;

    /**
     * 管理费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repManagementRate;

    /**
     * 托管费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTrustRate;

    /**
     * 交易相关合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTradeSumRate;

    /**
     * 中介服务机构合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repIntermediarySumRate;

    /**
     * 其他合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherSumRate;

    /**
     * 期限(天)
     */
    private Integer repDuration;

    /**
     * 负债规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repLiabilitiesMoney;

    /**
     * 利率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    
    /**
     * 资产价值
     */
    private BigDecimal repInterestRate;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAssetValue;
    
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAssetYield;
    
    private BigDecimal repProjectDuration;
    
    private String repContainExpectedYield2;
    
    /**
     * 行内资产类别
     */
    private String repBankAssetType;

    /**
     * 行内资产类别说明
     */
    private String repBankAssetTyprExplain;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMoney;

    /**
     * 项目收益率(利率)%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repProjectYieldRate;

    /**
     * 融资人组织机构（社会信用）代码
     */
    private String repFinancierOrgCode;

    /**
     * 资产登记日
     */
    private Date repAssetRegDate;
    
    private String repTradeTarget;
    
    //
    /**
     * 市场代码，可以按规则生成，或者手输，不校验唯一
     */
    private String finprodMarketId2;
    
    /**
     * 市场代码，可以按规则生成，或者手输，不校验唯一
     */
    private String finprodMarketId8;
    
    /**
     * 市场代码，可以按规则生成，或者手输，不校验唯一
     */
    private String finprodMarketId16;
    
    /**
     * 起息日
     */
    private Date repVdate3;

    /**
     * 到期日
     */
    private Date repMdate3;
    
    /**
     * 起息日
     */
    private Date repVdate4;

    /**
     * 到期日
     */
    private Date repMdate4;
    
    /**
     * 起息日
     */
    private Date repVdate5;

    /**
     * 到期日
     */
    private Date repMdate5;
    
    /**
     * 起息日
     */
    private Date repVdate8;

    /**
     * 到期日
     */
    private Date repMdate8;
    
    /**
     * 起息日
     */
    private Date repVdate9;

    /**
     * 到期日
     */
    private Date repMdate9;
    
    /**
     * 起息日
     */
    private Date repVdate11;

    /**
     * 到期日
     */
    private Date repMdate11;
    
    /**
     * 起息日
     */
    private Date repVdate15;

    /**
     * 到期日
     */
    private Date repMdate15;
    
    /**
     * 计息基础
     */
    private String repBasis3;
    
    /**
     * 计息基础
     */
    private String repBasis4;
    
    /**
     * 计息基础
     */
    private String repBasis9;
    
    /**
     * 发行机构所属行业
     */
    private String repIndustry16;
    
private String repFinprodName4;
    
    private String repFinprodName6;
    
    private String repFinprodName7;
    
    private String repFinprodName8;
    
    private String repFinprodName10;
    
    private String repFinprodName11;
    
    private String repFinprodName12;
    
    private String repFinprodName13;
    
    private String repFinprodName14;
    
    private String repFinprodName15;
    
    private String repFinprodName16;
    
    private String repFinprodName17;
    
    private String repFinprodName18;
    
    /**
     * 融资人所属行业
     */
    private String repIndustry14;

    /**
     * 融资项目所属行业
     */
    private String repProIndustry14;
    
    /**
     * 发行机构类型（按规模划分） 
     */
    private String repPartyType1_6;

    /**
     * 发行机构类型（按经济类型划分） 
     */
    private String repPartyType2_6;

    /**
     * 发行机构类型（按技术领域划分）
     */
    private String repPartyType3_6;
    
    /**
     * 发行机构类型（按规模划分） 
     */
    private String repPartyType1_16;

    /**
     * 发行机构类型（按经济类型划分） 
     */
    private String repPartyType2_16;

    /**
     * 发行机构类型（按技术领域划分）
     */
    private String repPartyType3_16;
    
    /**
     * 获取 repFinprodId
     * @return repFinprodId.
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    
    /**
     * 设置 repFinprodId
     * @param repFinprodId repFinprodId
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId;
    }

    
    /**
     * 获取 repBankName
     * @return repBankName.
     */
    public String getRepBankName() {
        return repBankName;
    }

    
    /**
     * 设置 repBankName
     * @param repBankName repBankName
     */
    public void setRepBankName(String repBankName) {
        this.repBankName = repBankName;
    }

    
    /**
     * 获取 repPrinAmt
     * @return repPrinAmt.
     */
    public BigDecimal getRepPrinAmt() {
        return repPrinAmt;
    }

    
    /**
     * 设置 repPrinAmt
     * @param repPrinAmt repPrinAmt
     */
    public void setRepPrinAmt(BigDecimal repPrinAmt) {
        this.repPrinAmt = repPrinAmt;
    }

    
    /**
     * 获取 repVdate
     * @return repVdate.
     */
    public Date getRepVdate() {
        return repVdate;
    }

    
    /**
     * 设置 repVdate
     * @param repVdate repVdate
     */
    public void setRepVdate(Date repVdate) {
        this.repVdate = repVdate;
    }

    
    /**
     * 获取 repMdate
     * @return repMdate.
     */
    public Date getRepMdate() {
        return repMdate;
    }

    
    /**
     * 设置 repMdate
     * @param repMdate repMdate
     */
    public void setRepMdate(Date repMdate) {
        this.repMdate = repMdate;
    }

    
    /**
     * 获取 repRateYear
     * @return repRateYear.
     */
    public BigDecimal getRepRateYear() {
        return repRateYear;
    }

    
    /**
     * 设置 repRateYear
     * @param repRateYear repRateYear
     */
    public void setRepRateYear(BigDecimal repRateYear) {
        this.repRateYear = repRateYear;
    }

    
    /**
     * 获取 repBasis
     * @return repBasis.
     */
    public String getRepBasis() {
        return repBasis;
    }

    
    /**
     * 设置 repBasis
     * @param repBasis repBasis
     */
    public void setRepBasis(String repBasis) {
        this.repBasis = repBasis;
    }

    
    /**
     * 获取 repIsStruct
     * @return repIsStruct.
     */
    public String getRepIsStruct() {
        return repIsStruct;
    }

    
    /**
     * 设置 repIsStruct
     * @param repIsStruct repIsStruct
     */
    public void setRepIsStruct(String repIsStruct) {
        this.repIsStruct = repIsStruct;
    }

    
    /**
     * 获取 repTargetType
     * @return repTargetType.
     */
    public String getRepTargetType() {
        return repTargetType;
    }

    
    /**
     * 设置 repTargetType
     * @param repTargetType repTargetType
     */
    public void setRepTargetType(String repTargetType) {
        this.repTargetType = repTargetType;
    }

    
    /**
     * 获取 repTarget
     * @return repTarget.
     */
    public String getRepTarget() {
        return repTarget;
    }

    
    /**
     * 设置 repTarget
     * @param repTarget repTarget
     */
    public void setRepTarget(String repTarget) {
        this.repTarget = repTarget;
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

    
    /**
     * 获取 secId
     * @return secId.
     */
    public String getSecId() {
        return secId;
    }

    
    /**
     * 设置 secId
     * @param secId secId
     */
    public void setSecId(String secId) {
        this.secId = secId;
    }

    
    /**
     * 获取 repSectype
     * @return repSectype.
     */
    public String getRepSectype() {
        return repSectype;
    }

    
    /**
     * 设置 repSectype
     * @param repSectype repSectype
     */
    public void setRepSectype(String repSectype) {
        this.repSectype = repSectype;
    }

    
    /**
     * 获取 repIssueMode
     * @return repIssueMode.
     */
    public String getRepIssueMode() {
        return repIssueMode;
    }

    
    /**
     * 设置 repIssueMode
     * @param repIssueMode repIssueMode
     */
    public void setRepIssueMode(String repIssueMode) {
        this.repIssueMode = repIssueMode;
    }

    
    /**
     * 获取 repMainGrade
     * @return repMainGrade.
     */
    public String getRepMainGrade() {
        return repMainGrade;
    }

    
    /**
     * 设置 repMainGrade
     * @param repMainGrade repMainGrade
     */
    public void setRepMainGrade(String repMainGrade) {
        this.repMainGrade = repMainGrade;
    }

    
    /**
     * 获取 repPartyType1
     * @return repPartyType1.
     */
    public String getRepPartyType1() {
        return repPartyType1;
    }

    
    /**
     * 设置 repPartyType1
     * @param repPartyType1 repPartyType1
     */
    public void setRepPartyType1(String repPartyType1) {
        this.repPartyType1 = repPartyType1;
    }

    
    /**
     * 获取 repPartyType2
     * @return repPartyType2.
     */
    public String getRepPartyType2() {
        return repPartyType2;
    }

    
    /**
     * 设置 repPartyType2
     * @param repPartyType2 repPartyType2
     */
    public void setRepPartyType2(String repPartyType2) {
        this.repPartyType2 = repPartyType2;
    }

    
    /**
     * 获取 repPartyType3
     * @return repPartyType3.
     */
    public String getRepPartyType3() {
        return repPartyType3;
    }

    
    /**
     * 设置 repPartyType3
     * @param repPartyType3 repPartyType3
     */
    public void setRepPartyType3(String repPartyType3) {
        this.repPartyType3 = repPartyType3;
    }

    
    /**
     * 获取 repIndustry
     * @return repIndustry.
     */
    public String getRepIndustry() {
        return repIndustry;
    }

    
    /**
     * 设置 repIndustry
     * @param repIndustry repIndustry
     */
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry;
    }

    
    /**
     * 获取 repIndustryLevel2
     * @return repIndustryLevel2.
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    
    /**
     * 设置 repIndustryLevel2
     * @param repIndustryLevel2 repIndustryLevel2
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2;
    }

    
    /**
     * 获取 repRegistOrg
     * @return repRegistOrg.
     */
    public String getRepRegistOrg() {
        return repRegistOrg;
    }

    
    /**
     * 设置 repRegistOrg
     * @param repRegistOrg repRegistOrg
     */
    public void setRepRegistOrg(String repRegistOrg) {
        this.repRegistOrg = repRegistOrg;
    }

    
    /**
     * 获取 repRegistorgComment
     * @return repRegistorgComment.
     */
    public String getRepRegistorgComment() {
        return repRegistorgComment;
    }

    
    /**
     * 设置 repRegistorgComment
     * @param repRegistorgComment repRegistorgComment
     */
    public void setRepRegistorgComment(String repRegistorgComment) {
        this.repRegistorgComment = repRegistorgComment;
    }

    
    /**
     * 获取 repValueDate
     * @return repValueDate.
     */
    public Date getRepValueDate() {
        return repValueDate;
    }

    
    /**
     * 设置 repValueDate
     * @param repValueDate repValueDate
     */
    public void setRepValueDate(Date repValueDate) {
        this.repValueDate = repValueDate;
    }

    
    /**
     * 获取 repExpiringDate
     * @return repExpiringDate.
     */
    public Date getRepExpiringDate() {
        return repExpiringDate;
    }

    
    /**
     * 设置 repExpiringDate
     * @param repExpiringDate repExpiringDate
     */
    public void setRepExpiringDate(Date repExpiringDate) {
        this.repExpiringDate = repExpiringDate;
    }

    
    /**
     * 获取 repCounterparty
     * @return repCounterparty.
     */
    public String getRepCounterparty() {
        return repCounterparty;
    }

    
    /**
     * 设置 repCounterparty
     * @param repCounterparty repCounterparty
     */
    public void setRepCounterparty(String repCounterparty) {
        this.repCounterparty = repCounterparty;
    }

    
    /**
     * 获取 repTargetAmt
     * @return repTargetAmt.
     */
    public BigDecimal getRepTargetAmt() {
        return repTargetAmt;
    }

    
    /**
     * 设置 repTargetAmt
     * @param repTargetAmt repTargetAmt
     */
    public void setRepTargetAmt(BigDecimal repTargetAmt) {
        this.repTargetAmt = repTargetAmt;
    }

    
    /**
     * 获取 repAmt
     * @return repAmt.
     */
    public BigDecimal getRepAmt() {
        return repAmt;
    }

    
    /**
     * 设置 repAmt
     * @param repAmt repAmt
     */
    public void setRepAmt(BigDecimal repAmt) {
        this.repAmt = repAmt;
    }

    
    /**
     * 获取 repProfitType
     * @return repProfitType.
     */
    public String getRepProfitType() {
        return repProfitType;
    }

    
    /**
     * 设置 repProfitType
     * @param repProfitType repProfitType
     */
    public void setRepProfitType(String repProfitType) {
        this.repProfitType = repProfitType;
    }

    
    /**
     * 获取 repIsResale
     * @return repIsResale.
     */
    public String getRepIsResale() {
        return repIsResale;
    }

    
    /**
     * 设置 repIsResale
     * @param repIsResale repIsResale
     */
    public void setRepIsResale(String repIsResale) {
        this.repIsResale = repIsResale;
    }

    
    /**
     * 获取 repTerm
     * @return repTerm.
     */
    public Integer getRepTerm() {
        return repTerm;
    }

    
    /**
     * 设置 repTerm
     * @param repTerm repTerm
     */
    public void setRepTerm(Integer repTerm) {
        this.repTerm = repTerm;
    }

    
    /**
     * 获取 repRateFlag
     * @return repRateFlag.
     */
    public String getRepRateFlag() {
        return repRateFlag;
    }

    
    /**
     * 设置 repRateFlag
     * @param repRateFlag repRateFlag
     */
    public void setRepRateFlag(String repRateFlag) {
        this.repRateFlag = repRateFlag;
    }

    
    /**
     * 获取 repProjYield
     * @return repProjYield.
     */
    public BigDecimal getRepProjYield() {
        return repProjYield;
    }

    
    /**
     * 设置 repProjYield
     * @param repProjYield repProjYield
     */
    public void setRepProjYield(BigDecimal repProjYield) {
        this.repProjYield = repProjYield;
    }

    
    /**
     * 获取 repIntpayCycle
     * @return repIntpayCycle.
     */
    public Short getRepIntpayCycle() {
        return repIntpayCycle;
    }

    
    /**
     * 设置 repIntpayCycle
     * @param repIntpayCycle repIntpayCycle
     */
    public void setRepIntpayCycle(Short repIntpayCycle) {
        this.repIntpayCycle = repIntpayCycle;
    }

    
    /**
     * 获取 repRepayCmt
     * @return repRepayCmt.
     */
    public String getRepRepayCmt() {
        return repRepayCmt;
    }

    
    /**
     * 设置 repRepayCmt
     * @param repRepayCmt repRepayCmt
     */
    public void setRepRepayCmt(String repRepayCmt) {
        this.repRepayCmt = repRepayCmt;
    }

    
    /**
     * 获取 repBasisType
     * @return repBasisType.
     */
    public String getRepBasisType() {
        return repBasisType;
    }

    
    /**
     * 设置 repBasisType
     * @param repBasisType repBasisType
     */
    public void setRepBasisType(String repBasisType) {
        this.repBasisType = repBasisType;
    }

    
    /**
     * 获取 repFloatFactor
     * @return repFloatFactor.
     */
    public BigDecimal getRepFloatFactor() {
        return repFloatFactor;
    }

    
    /**
     * 设置 repFloatFactor
     * @param repFloatFactor repFloatFactor
     */
    public void setRepFloatFactor(BigDecimal repFloatFactor) {
        this.repFloatFactor = repFloatFactor;
    }

    
    /**
     * 获取 repSpreadRate
     * @return repSpreadRate.
     */
    public BigDecimal getRepSpreadRate() {
        return repSpreadRate;
    }

    
    /**
     * 设置 repSpreadRate
     * @param repSpreadRate repSpreadRate
     */
    public void setRepSpreadRate(BigDecimal repSpreadRate) {
        this.repSpreadRate = repSpreadRate;
    }

    
    /**
     * 获取 repFinancier
     * @return repFinancier.
     */
    public String getRepFinancier() {
        return repFinancier;
    }

    
    /**
     * 设置 repFinancier
     * @param repFinancier repFinancier
     */
    public void setRepFinancier(String repFinancier) {
        this.repFinancier = repFinancier;
    }

    
    /**
     * 获取 repFcrGradeIn
     * @return repFcrGradeIn.
     */
    public String getRepFcrGradeIn() {
        return repFcrGradeIn;
    }

    
    /**
     * 设置 repFcrGradeIn
     * @param repFcrGradeIn repFcrGradeIn
     */
    public void setRepFcrGradeIn(String repFcrGradeIn) {
        this.repFcrGradeIn = repFcrGradeIn;
    }

    
    /**
     * 获取 repFcrGradeOut
     * @return repFcrGradeOut.
     */
    public String getRepFcrGradeOut() {
        return repFcrGradeOut;
    }

    
    /**
     * 设置 repFcrGradeOut
     * @param repFcrGradeOut repFcrGradeOut
     */
    public void setRepFcrGradeOut(String repFcrGradeOut) {
        this.repFcrGradeOut = repFcrGradeOut;
    }

    
    /**
     * 获取 repFinancierType1
     * @return repFinancierType1.
     */
    public String getRepFinancierType1() {
        return repFinancierType1;
    }

    
    /**
     * 设置 repFinancierType1
     * @param repFinancierType1 repFinancierType1
     */
    public void setRepFinancierType1(String repFinancierType1) {
        this.repFinancierType1 = repFinancierType1;
    }

    
    /**
     * 获取 repFinancierType2
     * @return repFinancierType2.
     */
    public String getRepFinancierType2() {
        return repFinancierType2;
    }

    
    /**
     * 设置 repFinancierType2
     * @param repFinancierType2 repFinancierType2
     */
    public void setRepFinancierType2(String repFinancierType2) {
        this.repFinancierType2 = repFinancierType2;
    }

    
    /**
     * 获取 repFinancierType3
     * @return repFinancierType3.
     */
    public String getRepFinancierType3() {
        return repFinancierType3;
    }

    
    /**
     * 设置 repFinancierType3
     * @param repFinancierType3 repFinancierType3
     */
    public void setRepFinancierType3(String repFinancierType3) {
        this.repFinancierType3 = repFinancierType3;
    }

    
    /**
     * 获取 repFinanceProj
     * @return repFinanceProj.
     */
    public String getRepFinanceProj() {
        return repFinanceProj;
    }

    
    /**
     * 设置 repFinanceProj
     * @param repFinanceProj repFinanceProj
     */
    public void setRepFinanceProj(String repFinanceProj) {
        this.repFinanceProj = repFinanceProj;
    }

    
    /**
     * 获取 repIndustry1
     * @return repIndustry1.
     */
    public String getRepIndustry1() {
        return repIndustry1;
    }

    
    /**
     * 设置 repIndustry1
     * @param repIndustry1 repIndustry1
     */
    public void setRepIndustry1(String repIndustry1) {
        this.repIndustry1 = repIndustry1;
    }

    
    /**
     * 获取 repProIndustry
     * @return repProIndustry.
     */
    public String getRepProIndustry() {
        return repProIndustry;
    }

    
    /**
     * 设置 repProIndustry
     * @param repProIndustry repProIndustry
     */
    public void setRepProIndustry(String repProIndustry) {
        this.repProIndustry = repProIndustry;
    }

    
    /**
     * 获取 repProIndustryLevel2
     * @return repProIndustryLevel2.
     */
    public String getRepProIndustryLevel2() {
        return repProIndustryLevel2;
    }

    
    /**
     * 设置 repProIndustryLevel2
     * @param repProIndustryLevel2 repProIndustryLevel2
     */
    public void setRepProIndustryLevel2(String repProIndustryLevel2) {
        this.repProIndustryLevel2 = repProIndustryLevel2;
    }

    
    /**
     * 获取 repIndustryFlag
     * @return repIndustryFlag.
     */
    public String getRepIndustryFlag() {
        return repIndustryFlag;
    }

    
    /**
     * 设置 repIndustryFlag
     * @param repIndustryFlag repIndustryFlag
     */
    public void setRepIndustryFlag(String repIndustryFlag) {
        this.repIndustryFlag = repIndustryFlag;
    }

    
    /**
     * 获取 repMonitType
     * @return repMonitType.
     */
    public String getRepMonitType() {
        return repMonitType;
    }

    
    /**
     * 设置 repMonitType
     * @param repMonitType repMonitType
     */
    public void setRepMonitType(String repMonitType) {
        this.repMonitType = repMonitType;
    }

    
    /**
     * 获取 repMonTypeComm
     * @return repMonTypeComm.
     */
    public String getRepMonTypeComm() {
        return repMonTypeComm;
    }

    
    /**
     * 设置 repMonTypeComm
     * @param repMonTypeComm repMonTypeComm
     */
    public void setRepMonTypeComm(String repMonTypeComm) {
        this.repMonTypeComm = repMonTypeComm;
    }

    
    /**
     * 获取 repGuaType
     * @return repGuaType.
     */
    public String getRepGuaType() {
        return repGuaType;
    }

    
    /**
     * 设置 repGuaType
     * @param repGuaType repGuaType
     */
    public void setRepGuaType(String repGuaType) {
        this.repGuaType = repGuaType;
    }

    
    /**
     * 获取 repGuaTypeRemark
     * @return repGuaTypeRemark.
     */
    public String getRepGuaTypeRemark() {
        return repGuaTypeRemark;
    }

    
    /**
     * 设置 repGuaTypeRemark
     * @param repGuaTypeRemark repGuaTypeRemark
     */
    public void setRepGuaTypeRemark(String repGuaTypeRemark) {
        this.repGuaTypeRemark = repGuaTypeRemark;
    }

    
    /**
     * 获取 repFinancierGrade
     * @return repFinancierGrade.
     */
    public String getRepFinancierGrade() {
        return repFinancierGrade;
    }

    
    /**
     * 设置 repFinancierGrade
     * @param repFinancierGrade repFinancierGrade
     */
    public void setRepFinancierGrade(String repFinancierGrade) {
        this.repFinancierGrade = repFinancierGrade;
    }

    
    /**
     * 获取 repInGrade
     * @return repInGrade.
     */
    public String getRepInGrade() {
        return repInGrade;
    }

    
    /**
     * 设置 repInGrade
     * @param repInGrade repInGrade
     */
    public void setRepInGrade(String repInGrade) {
        this.repInGrade = repInGrade;
    }

    
    /**
     * 获取 repOutGrade
     * @return repOutGrade.
     */
    public String getRepOutGrade() {
        return repOutGrade;
    }

    
    /**
     * 设置 repOutGrade
     * @param repOutGrade repOutGrade
     */
    public void setRepOutGrade(String repOutGrade) {
        this.repOutGrade = repOutGrade;
    }

    
    /**
     * 获取 repChose
     * @return repChose.
     */
    public String getRepChose() {
        return repChose;
    }

    
    /**
     * 设置 repChose
     * @param repChose repChose
     */
    public void setRepChose(String repChose) {
        this.repChose = repChose;
    }

    
    /**
     * 获取 repConditionCmm
     * @return repConditionCmm.
     */
    public String getRepConditionCmm() {
        return repConditionCmm;
    }

    
    /**
     * 设置 repConditionCmm
     * @param repConditionCmm repConditionCmm
     */
    public void setRepConditionCmm(String repConditionCmm) {
        this.repConditionCmm = repConditionCmm;
    }

    
    /**
     * 获取 repFinArea
     * @return repFinArea.
     */
    public String getRepFinArea() {
        return repFinArea;
    }

    
    /**
     * 设置 repFinArea
     * @param repFinArea repFinArea
     */
    public void setRepFinArea(String repFinArea) {
        this.repFinArea = repFinArea;
    }

    
    /**
     * 获取 repTotalFeeRate
     * @return repTotalFeeRate.
     */
    public BigDecimal getRepTotalFeeRate() {
        return repTotalFeeRate;
    }

    
    /**
     * 设置 repTotalFeeRate
     * @param repTotalFeeRate repTotalFeeRate
     */
    public void setRepTotalFeeRate(BigDecimal repTotalFeeRate) {
        this.repTotalFeeRate = repTotalFeeRate;
    }

    
    /**
     * 获取 repFinOrgCode
     * @return repFinOrgCode.
     */
    public String getRepFinOrgCode() {
        return repFinOrgCode;
    }

    
    /**
     * 设置 repFinOrgCode
     * @param repFinOrgCode repFinOrgCode
     */
    public void setRepFinOrgCode(String repFinOrgCode) {
        this.repFinOrgCode = repFinOrgCode;
    }

    
    /**
     * 获取 repIsRight
     * @return repIsRight.
     */
    public String getRepIsRight() {
        return repIsRight;
    }

    
    /**
     * 设置 repIsRight
     * @param repIsRight repIsRight
     */
    public void setRepIsRight(String repIsRight) {
        this.repIsRight = repIsRight;
    }

    
    /**
     * 获取 repAstBillType
     * @return repAstBillType.
     */
    public String getRepAstBillType() {
        return repAstBillType;
    }

    
    /**
     * 设置 repAstBillType
     * @param repAstBillType repAstBillType
     */
    public void setRepAstBillType(String repAstBillType) {
        this.repAstBillType = repAstBillType;
    }

    
    /**
     * 获取 repNum
     * @return repNum.
     */
    public Integer getRepNum() {
        return repNum;
    }

    
    /**
     * 设置 repNum
     * @param repNum repNum
     */
    public void setRepNum(Integer repNum) {
        this.repNum = repNum;
    }

    
    /**
     * 获取 repAmtSum
     * @return repAmtSum.
     */
    public BigDecimal getRepAmtSum() {
        return repAmtSum;
    }

    
    /**
     * 设置 repAmtSum
     * @param repAmtSum repAmtSum
     */
    public void setRepAmtSum(BigDecimal repAmtSum) {
        this.repAmtSum = repAmtSum;
    }

    
    /**
     * 获取 repRemainTerm
     * @return repRemainTerm.
     */
    public BigDecimal getRepRemainTerm() {
        return repRemainTerm;
    }

    
    /**
     * 设置 repRemainTerm
     * @param repRemainTerm repRemainTerm
     */
    public void setRepRemainTerm(BigDecimal repRemainTerm) {
        this.repRemainTerm = repRemainTerm;
    }

    
    /**
     * 获取 repMaxRemainTerm
     * @return repMaxRemainTerm.
     */
    public Integer getRepMaxRemainTerm() {
        return repMaxRemainTerm;
    }

    
    /**
     * 设置 repMaxRemainTerm
     * @param repMaxRemainTerm repMaxRemainTerm
     */
    public void setRepMaxRemainTerm(Integer repMaxRemainTerm) {
        this.repMaxRemainTerm = repMaxRemainTerm;
    }

    
    /**
     * 获取 repMinRemainTerm
     * @return repMinRemainTerm.
     */
    public Integer getRepMinRemainTerm() {
        return repMinRemainTerm;
    }

    
    /**
     * 设置 repMinRemainTerm
     * @param repMinRemainTerm repMinRemainTerm
     */
    public void setRepMinRemainTerm(Integer repMinRemainTerm) {
        this.repMinRemainTerm = repMinRemainTerm;
    }

    
    /**
     * 获取 repDiscRate
     * @return repDiscRate.
     */
    public BigDecimal getRepDiscRate() {
        return repDiscRate;
    }

    
    /**
     * 设置 repDiscRate
     * @param repDiscRate repDiscRate
     */
    public void setRepDiscRate(BigDecimal repDiscRate) {
        this.repDiscRate = repDiscRate;
    }

    
    /**
     * 获取 repSharesId
     * @return repSharesId.
     */
    public String getRepSharesId() {
        return repSharesId;
    }

    
    /**
     * 设置 repSharesId
     * @param repSharesId repSharesId
     */
    public void setRepSharesId(String repSharesId) {
        this.repSharesId = repSharesId;
    }

    
    /**
     * 获取 repSharesType
     * @return repSharesType.
     */
    public String getRepSharesType() {
        return repSharesType;
    }

    
    /**
     * 设置 repSharesType
     * @param repSharesType repSharesType
     */
    public void setRepSharesType(String repSharesType) {
        this.repSharesType = repSharesType;
    }

    
    /**
     * 获取 repInvestStage
     * @return repInvestStage.
     */
    public String getRepInvestStage() {
        return repInvestStage;
    }

    
    /**
     * 设置 repInvestStage
     * @param repInvestStage repInvestStage
     */
    public void setRepInvestStage(String repInvestStage) {
        this.repInvestStage = repInvestStage;
    }

    
    /**
     * 获取 repIsPledge
     * @return repIsPledge.
     */
    public String getRepIsPledge() {
        return repIsPledge;
    }

    
    /**
     * 设置 repIsPledge
     * @param repIsPledge repIsPledge
     */
    public void setRepIsPledge(String repIsPledge) {
        this.repIsPledge = repIsPledge;
    }

    
    /**
     * 获取 repNomCapi
     * @return repNomCapi.
     */
    public BigDecimal getRepNomCapi() {
        return repNomCapi;
    }

    
    /**
     * 设置 repNomCapi
     * @param repNomCapi repNomCapi
     */
    public void setRepNomCapi(BigDecimal repNomCapi) {
        this.repNomCapi = repNomCapi;
    }

    
    /**
     * 获取 repDeriType
     * @return repDeriType.
     */
    public String getRepDeriType() {
        return repDeriType;
    }

    
    /**
     * 设置 repDeriType
     * @param repDeriType repDeriType
     */
    public void setRepDeriType(String repDeriType) {
        this.repDeriType = repDeriType;
    }

    
    /**
     * 获取 repCountryArea
     * @return repCountryArea.
     */
    public String getRepCountryArea() {
        return repCountryArea;
    }

    
    /**
     * 设置 repCountryArea
     * @param repCountryArea repCountryArea
     */
    public void setRepCountryArea(String repCountryArea) {
        this.repCountryArea = repCountryArea;
    }

    
    /**
     * 获取 repSecId
     * @return repSecId.
     */
    public String getRepSecId() {
        return repSecId;
    }

    
    /**
     * 设置 repSecId
     * @param repSecId repSecId
     */
    public void setRepSecId(String repSecId) {
        this.repSecId = repSecId;
    }

    
    /**
     * 获取 repIssueParty
     * @return repIssueParty.
     */
    public String getRepIssueParty() {
        return repIssueParty;
    }

    
    /**
     * 设置 repIssueParty
     * @param repIssueParty repIssueParty
     */
    public void setRepIssueParty(String repIssueParty) {
        this.repIssueParty = repIssueParty;
    }

    
    /**
     * 获取 repPartyGrade
     * @return repPartyGrade.
     */
    public String getRepPartyGrade() {
        return repPartyGrade;
    }

    
    /**
     * 设置 repPartyGrade
     * @param repPartyGrade repPartyGrade
     */
    public void setRepPartyGrade(String repPartyGrade) {
        this.repPartyGrade = repPartyGrade;
    }

    
    /**
     * 获取 repSecGrade
     * @return repSecGrade.
     */
    public String getRepSecGrade() {
        return repSecGrade;
    }

    
    /**
     * 设置 repSecGrade
     * @param repSecGrade repSecGrade
     */
    public void setRepSecGrade(String repSecGrade) {
        this.repSecGrade = repSecGrade;
    }

    
    /**
     * 获取 repPaperRate
     * @return repPaperRate.
     */
    public BigDecimal getRepPaperRate() {
        return repPaperRate;
    }

    
    /**
     * 设置 repPaperRate
     * @param repPaperRate repPaperRate
     */
    public void setRepPaperRate(BigDecimal repPaperRate) {
        this.repPaperRate = repPaperRate;
    }

    
    /**
     * 获取 repGuaranteeCmt
     * @return repGuaranteeCmt.
     */
    public String getRepGuaranteeCmt() {
        return repGuaranteeCmt;
    }

    
    /**
     * 设置 repGuaranteeCmt
     * @param repGuaranteeCmt repGuaranteeCmt
     */
    public void setRepGuaranteeCmt(String repGuaranteeCmt) {
        this.repGuaranteeCmt = repGuaranteeCmt;
    }

    
    /**
     * 获取 repSpecialCmt
     * @return repSpecialCmt.
     */
    public String getRepSpecialCmt() {
        return repSpecialCmt;
    }

    
    /**
     * 设置 repSpecialCmt
     * @param repSpecialCmt repSpecialCmt
     */
    public void setRepSpecialCmt(String repSpecialCmt) {
        this.repSpecialCmt = repSpecialCmt;
    }

    
    /**
     * 获取 repRivalParty
     * @return repRivalParty.
     */
    public String getRepRivalParty() {
        return repRivalParty;
    }

    
    /**
     * 设置 repRivalParty
     * @param repRivalParty repRivalParty
     */
    public void setRepRivalParty(String repRivalParty) {
        this.repRivalParty = repRivalParty;
    }

    
    /**
     * 获取 repShrsFundId
     * @return repShrsFundId.
     */
    public String getRepShrsFundId() {
        return repShrsFundId;
    }

    
    /**
     * 设置 repShrsFundId
     * @param repShrsFundId repShrsFundId
     */
    public void setRepShrsFundId(String repShrsFundId) {
        this.repShrsFundId = repShrsFundId;
    }

    
    /**
     * 获取 repIncomeRatio
     * @return repIncomeRatio.
     */
    public BigDecimal getRepIncomeRatio() {
        return repIncomeRatio;
    }

    
    /**
     * 设置 repIncomeRatio
     * @param repIncomeRatio repIncomeRatio
     */
    public void setRepIncomeRatio(BigDecimal repIncomeRatio) {
        this.repIncomeRatio = repIncomeRatio;
    }

    
    /**
     * 获取 repDerInstrRatio
     * @return repDerInstrRatio.
     */
    public BigDecimal getRepDerInstrRatio() {
        return repDerInstrRatio;
    }

    
    /**
     * 设置 repDerInstrRatio
     * @param repDerInstrRatio repDerInstrRatio
     */
    public void setRepDerInstrRatio(BigDecimal repDerInstrRatio) {
        this.repDerInstrRatio = repDerInstrRatio;
    }

    
    /**
     * 获取 repDerInstrInvType
     * @return repDerInstrInvType.
     */
    public String getRepDerInstrInvType() {
        return repDerInstrInvType;
    }

    
    /**
     * 设置 repDerInstrInvType
     * @param repDerInstrInvType repDerInstrInvType
     */
    public void setRepDerInstrInvType(String repDerInstrInvType) {
        this.repDerInstrInvType = repDerInstrInvType;
    }

    
    /**
     * 获取 repDerInstrTarget
     * @return repDerInstrTarget.
     */
    public String getRepDerInstrTarget() {
        return repDerInstrTarget;
    }

    
    /**
     * 设置 repDerInstrTarget
     * @param repDerInstrTarget repDerInstrTarget
     */
    public void setRepDerInstrTarget(String repDerInstrTarget) {
        this.repDerInstrTarget = repDerInstrTarget;
    }

    
    /**
     * 获取 repAcctMode
     * @return repAcctMode.
     */
    public String getRepAcctMode() {
        return repAcctMode;
    }

    
    /**
     * 设置 repAcctMode
     * @param repAcctMode repAcctMode
     */
    public void setRepAcctMode(String repAcctMode) {
        this.repAcctMode = repAcctMode;
    }

    
    /**
     * 获取 repMaxYield
     * @return repMaxYield.
     */
    public BigDecimal getRepMaxYield() {
        return repMaxYield;
    }

    
    /**
     * 设置 repMaxYield
     * @param repMaxYield repMaxYield
     */
    public void setRepMaxYield(BigDecimal repMaxYield) {
        this.repMaxYield = repMaxYield;
    }

    
    /**
     * 获取 repMinYield
     * @return repMinYield.
     */
    public BigDecimal getRepMinYield() {
        return repMinYield;
    }

    
    /**
     * 设置 repMinYield
     * @param repMinYield repMinYield
     */
    public void setRepMinYield(BigDecimal repMinYield) {
        this.repMinYield = repMinYield;
    }

    
    /**
     * 获取 repBasicPrice
     * @return repBasicPrice.
     */
    public BigDecimal getRepBasicPrice() {
        return repBasicPrice;
    }

    
    /**
     * 设置 repBasicPrice
     * @param repBasicPrice repBasicPrice
     */
    public void setRepBasicPrice(BigDecimal repBasicPrice) {
        this.repBasicPrice = repBasicPrice;
    }

    
    /**
     * 获取 repRegDatePrice
     * @return repRegDatePrice.
     */
    public BigDecimal getRepRegDatePrice() {
        return repRegDatePrice;
    }

    
    /**
     * 设置 repRegDatePrice
     * @param repRegDatePrice repRegDatePrice
     */
    public void setRepRegDatePrice(BigDecimal repRegDatePrice) {
        this.repRegDatePrice = repRegDatePrice;
    }

    
    /**
     * 获取 repTradeFee
     * @return repTradeFee.
     */
    public String getRepTradeFee() {
        return repTradeFee;
    }

    
    /**
     * 设置 repTradeFee
     * @param repTradeFee repTradeFee
     */
    public void setRepTradeFee(String repTradeFee) {
        this.repTradeFee = repTradeFee;
    }

    
    /**
     * 获取 repPartyCode
     * @return repPartyCode.
     */
    public String getRepPartyCode() {
        return repPartyCode;
    }

    
    /**
     * 设置 repPartyCode
     * @param repPartyCode repPartyCode
     */
    public void setRepPartyCode(String repPartyCode) {
        this.repPartyCode = repPartyCode;
    }

    
    /**
     * 获取 repRegistCode
     * @return repRegistCode.
     */
    public String getRepRegistCode() {
        return repRegistCode;
    }

    
    /**
     * 设置 repRegistCode
     * @param repRegistCode repRegistCode
     */
    public void setRepRegistCode(String repRegistCode) {
        this.repRegistCode = repRegistCode;
    }

    
    /**
     * 获取 repManagerId
     * @return repManagerId.
     */
    public String getRepManagerId() {
        return repManagerId;
    }

    
    /**
     * 设置 repManagerId
     * @param repManagerId repManagerId
     */
    public void setRepManagerId(String repManagerId) {
        this.repManagerId = repManagerId;
    }

    
    /**
     * 获取 repTrusteeId
     * @return repTrusteeId.
     */
    public String getRepTrusteeId() {
        return repTrusteeId;
    }

    
    /**
     * 设置 repTrusteeId
     * @param repTrusteeId repTrusteeId
     */
    public void setRepTrusteeId(String repTrusteeId) {
        this.repTrusteeId = repTrusteeId;
    }

    
    /**
     * 获取 repCapiInvest
     * @return repCapiInvest.
     */
    public String getRepCapiInvest() {
        return repCapiInvest;
    }

    
    /**
     * 设置 repCapiInvest
     * @param repCapiInvest repCapiInvest
     */
    public void setRepCapiInvest(String repCapiInvest) {
        this.repCapiInvest = repCapiInvest;
    }

    
    /**
     * 获取 repCapUseWay
     * @return repCapUseWay.
     */
    public String getRepCapUseWay() {
        return repCapUseWay;
    }

    
    /**
     * 设置 repCapUseWay
     * @param repCapUseWay repCapUseWay
     */
    public void setRepCapUseWay(String repCapUseWay) {
        this.repCapUseWay = repCapUseWay;
    }

    
    /**
     * 获取 repCapUseIndu
     * @return repCapUseIndu.
     */
    public String getRepCapUseIndu() {
        return repCapUseIndu;
    }

    
    /**
     * 设置 repCapUseIndu
     * @param repCapUseIndu repCapUseIndu
     */
    public void setRepCapUseIndu(String repCapUseIndu) {
        this.repCapUseIndu = repCapUseIndu;
    }

    
    /**
     * 获取 repCapUseInduLevel2
     * @return repCapUseInduLevel2.
     */
    public String getRepCapUseInduLevel2() {
        return repCapUseInduLevel2;
    }

    
    /**
     * 设置 repCapUseInduLevel2
     * @param repCapUseInduLevel2 repCapUseInduLevel2
     */
    public void setRepCapUseInduLevel2(String repCapUseInduLevel2) {
        this.repCapUseInduLevel2 = repCapUseInduLevel2;
    }

    
    /**
     * 获取 repSetDate
     * @return repSetDate.
     */
    public Date getRepSetDate() {
        return repSetDate;
    }

    
    /**
     * 设置 repSetDate
     * @param repSetDate repSetDate
     */
    public void setRepSetDate(Date repSetDate) {
        this.repSetDate = repSetDate;
    }

    
    /**
     * 获取 repEndDate
     * @return repEndDate.
     */
    public Date getRepEndDate() {
        return repEndDate;
    }

    
    /**
     * 设置 repEndDate
     * @param repEndDate repEndDate
     */
    public void setRepEndDate(Date repEndDate) {
        this.repEndDate = repEndDate;
    }

    
    /**
     * 获取 repPlanProp
     * @return repPlanProp.
     */
    public String getRepPlanProp() {
        return repPlanProp;
    }

    
    /**
     * 设置 repPlanProp
     * @param repPlanProp repPlanProp
     */
    public void setRepPlanProp(String repPlanProp) {
        this.repPlanProp = repPlanProp;
    }

    
    /**
     * 获取 repYieldFlag
     * @return repYieldFlag.
     */
    public String getRepYieldFlag() {
        return repYieldFlag;
    }

    
    /**
     * 设置 repYieldFlag
     * @param repYieldFlag repYieldFlag
     */
    public void setRepYieldFlag(String repYieldFlag) {
        this.repYieldFlag = repYieldFlag;
    }

    
    /**
     * 获取 repMaxRate
     * @return repMaxRate.
     */
    public BigDecimal getRepMaxRate() {
        return repMaxRate;
    }

    
    /**
     * 设置 repMaxRate
     * @param repMaxRate repMaxRate
     */
    public void setRepMaxRate(BigDecimal repMaxRate) {
        this.repMaxRate = repMaxRate;
    }

    
    /**
     * 获取 repMinRate
     * @return repMinRate.
     */
    public BigDecimal getRepMinRate() {
        return repMinRate;
    }

    
    /**
     * 设置 repMinRate
     * @param repMinRate repMinRate
     */
    public void setRepMinRate(BigDecimal repMinRate) {
        this.repMinRate = repMinRate;
    }

    
    /**
     * 获取 repBuyStruct
     * @return repBuyStruct.
     */
    public String getRepBuyStruct() {
        return repBuyStruct;
    }

    
    /**
     * 设置 repBuyStruct
     * @param repBuyStruct repBuyStruct
     */
    public void setRepBuyStruct(String repBuyStruct) {
        this.repBuyStruct = repBuyStruct;
    }

    
    /**
     * 获取 repManageType
     * @return repManageType.
     */
    public String getRepManageType() {
        return repManageType;
    }

    
    /**
     * 设置 repManageType
     * @param repManageType repManageType
     */
    public void setRepManageType(String repManageType) {
        this.repManageType = repManageType;
    }

    
    /**
     * 获取 repIsCtrlBottom
     * @return repIsCtrlBottom.
     */
    public String getRepIsCtrlBottom() {
        return repIsCtrlBottom;
    }

    
    /**
     * 设置 repIsCtrlBottom
     * @param repIsCtrlBottom repIsCtrlBottom
     */
    public void setRepIsCtrlBottom(String repIsCtrlBottom) {
        this.repIsCtrlBottom = repIsCtrlBottom;
    }

    
    /**
     * 获取 repMgrFeeRate
     * @return repMgrFeeRate.
     */
    public BigDecimal getRepMgrFeeRate() {
        return repMgrFeeRate;
    }

    
    /**
     * 设置 repMgrFeeRate
     * @param repMgrFeeRate repMgrFeeRate
     */
    public void setRepMgrFeeRate(BigDecimal repMgrFeeRate) {
        this.repMgrFeeRate = repMgrFeeRate;
    }

    
    /**
     * 获取 repTruFeeRate
     * @return repTruFeeRate.
     */
    public BigDecimal getRepTruFeeRate() {
        return repTruFeeRate;
    }

    
    /**
     * 设置 repTruFeeRate
     * @param repTruFeeRate repTruFeeRate
     */
    public void setRepTruFeeRate(BigDecimal repTruFeeRate) {
        this.repTruFeeRate = repTruFeeRate;
    }

    
    /**
     * 获取 repTradeFeeRate
     * @return repTradeFeeRate.
     */
    public BigDecimal getRepTradeFeeRate() {
        return repTradeFeeRate;
    }

    
    /**
     * 设置 repTradeFeeRate
     * @param repTradeFeeRate repTradeFeeRate
     */
    public void setRepTradeFeeRate(BigDecimal repTradeFeeRate) {
        this.repTradeFeeRate = repTradeFeeRate;
    }

    
    /**
     * 获取 repAgencyFeeRate
     * @return repAgencyFeeRate.
     */
    public BigDecimal getRepAgencyFeeRate() {
        return repAgencyFeeRate;
    }

    
    /**
     * 设置 repAgencyFeeRate
     * @param repAgencyFeeRate repAgencyFeeRate
     */
    public void setRepAgencyFeeRate(BigDecimal repAgencyFeeRate) {
        this.repAgencyFeeRate = repAgencyFeeRate;
    }

    
    /**
     * 获取 repOtherFeeRate
     * @return repOtherFeeRate.
     */
    public BigDecimal getRepOtherFeeRate() {
        return repOtherFeeRate;
    }

    
    /**
     * 设置 repOtherFeeRate
     * @param repOtherFeeRate repOtherFeeRate
     */
    public void setRepOtherFeeRate(BigDecimal repOtherFeeRate) {
        this.repOtherFeeRate = repOtherFeeRate;
    }

    
    /**
     * 获取 repIsGovInvest
     * @return repIsGovInvest.
     */
    public String getRepIsGovInvest() {
        return repIsGovInvest;
    }

    
    /**
     * 设置 repIsGovInvest
     * @param repIsGovInvest repIsGovInvest
     */
    public void setRepIsGovInvest(String repIsGovInvest) {
        this.repIsGovInvest = repIsGovInvest;
    }

    
    /**
     * 获取 repGovInvestFor
     * @return repGovInvestFor.
     */
    public String getRepGovInvestFor() {
        return repGovInvestFor;
    }

    
    /**
     * 设置 repGovInvestFor
     * @param repGovInvestFor repGovInvestFor
     */
    public void setRepGovInvestFor(String repGovInvestFor) {
        this.repGovInvestFor = repGovInvestFor;
    }

    
    /**
     * 获取 repFundCompName
     * @return repFundCompName.
     */
    public String getRepFundCompName() {
        return repFundCompName;
    }

    
    /**
     * 设置 repFundCompName
     * @param repFundCompName repFundCompName
     */
    public void setRepFundCompName(String repFundCompName) {
        this.repFundCompName = repFundCompName;
    }

    
    /**
     * 获取 repMngPartyName
     * @return repMngPartyName.
     */
    public String getRepMngPartyName() {
        return repMngPartyName;
    }

    
    /**
     * 设置 repMngPartyName
     * @param repMngPartyName repMngPartyName
     */
    public void setRepMngPartyName(String repMngPartyName) {
        this.repMngPartyName = repMngPartyName;
    }

    
    /**
     * 获取 repTrustPartyName
     * @return repTrustPartyName.
     */
    public String getRepTrustPartyName() {
        return repTrustPartyName;
    }

    
    /**
     * 设置 repTrustPartyName
     * @param repTrustPartyName repTrustPartyName
     */
    public void setRepTrustPartyName(String repTrustPartyName) {
        this.repTrustPartyName = repTrustPartyName;
    }

    
    /**
     * 获取 repFundInvestAsset
     * @return repFundInvestAsset.
     */
    public String getRepFundInvestAsset() {
        return repFundInvestAsset;
    }

    
    /**
     * 设置 repFundInvestAsset
     * @param repFundInvestAsset repFundInvestAsset
     */
    public void setRepFundInvestAsset(String repFundInvestAsset) {
        this.repFundInvestAsset = repFundInvestAsset;
    }

    
    /**
     * 获取 repTrustee
     * @return repTrustee.
     */
    public String getRepTrustee() {
        return repTrustee;
    }

    
    /**
     * 设置 repTrustee
     * @param repTrustee repTrustee
     */
    public void setRepTrustee(String repTrustee) {
        this.repTrustee = repTrustee;
    }

    
    /**
     * 获取 repActualManager
     * @return repActualManager.
     */
    public String getRepActualManager() {
        return repActualManager;
    }

    
    /**
     * 设置 repActualManager
     * @param repActualManager repActualManager
     */
    public void setRepActualManager(String repActualManager) {
        this.repActualManager = repActualManager;
    }

    
    /**
     * 获取 repCustodian
     * @return repCustodian.
     */
    public String getRepCustodian() {
        return repCustodian;
    }

    
    /**
     * 设置 repCustodian
     * @param repCustodian repCustodian
     */
    public void setRepCustodian(String repCustodian) {
        this.repCustodian = repCustodian;
    }

    
    /**
     * 获取 repEntrustInvestMoney
     * @return repEntrustInvestMoney.
     */
    public BigDecimal getRepEntrustInvestMoney() {
        return repEntrustInvestMoney;
    }

    
    /**
     * 设置 repEntrustInvestMoney
     * @param repEntrustInvestMoney repEntrustInvestMoney
     */
    public void setRepEntrustInvestMoney(BigDecimal repEntrustInvestMoney) {
        this.repEntrustInvestMoney = repEntrustInvestMoney;
    }

    
    /**
     * 获取 repApplicationMode
     * @return repApplicationMode.
     */
    public String getRepApplicationMode() {
        return repApplicationMode;
    }

    
    /**
     * 设置 repApplicationMode
     * @param repApplicationMode repApplicationMode
     */
    public void setRepApplicationMode(String repApplicationMode) {
        this.repApplicationMode = repApplicationMode;
    }

    
    /**
     * 获取 repInvestStartdate
     * @return repInvestStartdate.
     */
    public Date getRepInvestStartdate() {
        return repInvestStartdate;
    }

    
    /**
     * 设置 repInvestStartdate
     * @param repInvestStartdate repInvestStartdate
     */
    public void setRepInvestStartdate(Date repInvestStartdate) {
        this.repInvestStartdate = repInvestStartdate;
    }

    
    /**
     * 获取 repInvestEnddate
     * @return repInvestEnddate.
     */
    public Date getRepInvestEnddate() {
        return repInvestEnddate;
    }

    
    /**
     * 设置 repInvestEnddate
     * @param repInvestEnddate repInvestEnddate
     */
    public void setRepInvestEnddate(Date repInvestEnddate) {
        this.repInvestEnddate = repInvestEnddate;
    }

    
    /**
     * 获取 repTrustInvestAttribute
     * @return repTrustInvestAttribute.
     */
    public String getRepTrustInvestAttribute() {
        return repTrustInvestAttribute;
    }

    /**
     * 设置 repTrustInvestAttribute
     * @param repTrustInvestAttribute repTrustInvestAttribute
     */
    public void setRepTrustInvestAttribute(String repTrustInvestAttribute) {
        this.repTrustInvestAttribute = repTrustInvestAttribute;
    }

    /**
     * 获取 repContainExpectedYield
     * @return repContainExpectedYield.
     */
    public String getRepContainExpectedYield() {
        return repContainExpectedYield;
    }
    
    /**
     * 设置 repContainExpectedYield
     * @param repContainExpectedYield repContainExpectedYield
     */
    public void setRepContainExpectedYield(String repContainExpectedYield) {
        this.repContainExpectedYield = repContainExpectedYield;
    }
    
    /**
     * 获取 repMaxExpectedYield
     * @return repMaxExpectedYield.
     */
    public BigDecimal getRepMaxExpectedYield() {
        return repMaxExpectedYield;
    }
    
    /**
     * 设置 repMaxExpectedYield
     * @param repMaxExpectedYield repMaxExpectedYield
     */
    public void setRepMaxExpectedYield(BigDecimal repMaxExpectedYield) {
        this.repMaxExpectedYield = repMaxExpectedYield;
    }

    /**
     * 获取 repMinExpectedYield
     * @return repMinExpectedYield.
     */
    public BigDecimal getRepMinExpectedYield() {
        return repMinExpectedYield;
    }
    
    /**
     * 设置 repMinExpectedYield
     * @param repMinExpectedYield repMinExpectedYield
     */
    public void setRepMinExpectedYield(BigDecimal repMinExpectedYield) {
        this.repMinExpectedYield = repMinExpectedYield;
    }
    
    /**
     * 获取 repManagementRate
     * @return repManagementRate.
     */
    public BigDecimal getRepManagementRate() {
        return repManagementRate;
    }
    
    /**
     * 设置 repManagementRate
     * @param repManagementRate repManagementRate
     */
    public void setRepManagementRate(BigDecimal repManagementRate) {
        this.repManagementRate = repManagementRate;
    }

    /**
     * 获取 repTrustRate
     * @return repTrustRate.
     */
    public BigDecimal getRepTrustRate() {
        return repTrustRate;
    }
    
    /**
     * 设置 repTrustRate
     * @param repTrustRate repTrustRate
     */
    public void setRepTrustRate(BigDecimal repTrustRate) {
        this.repTrustRate = repTrustRate;
    }

    /**
     * 获取 repTradeSumRate
     * @return repTradeSumRate.
     */
    public BigDecimal getRepTradeSumRate() {
        return repTradeSumRate;
    }

    /**
     * 设置 repTradeSumRate
     * @param repTradeSumRate repTradeSumRate
     */
    public void setRepTradeSumRate(BigDecimal repTradeSumRate) {
        this.repTradeSumRate = repTradeSumRate;
    }
    
    /**
     * 获取 repIntermediarySumRate
     * @return repIntermediarySumRate.
     */
    public BigDecimal getRepIntermediarySumRate() {
        return repIntermediarySumRate;
    }
    
    /**
     * 设置 repIntermediarySumRate
     * @param repIntermediarySumRate repIntermediarySumRate
     */
    public void setRepIntermediarySumRate(BigDecimal repIntermediarySumRate) {
        this.repIntermediarySumRate = repIntermediarySumRate;
    }
    
    /**
     * 获取 repOtherSumRate
     * @return repOtherSumRate.
     */
    public BigDecimal getRepOtherSumRate() {
        return repOtherSumRate;
    }
    
    /**
     * 设置 repOtherSumRate
     * @param repOtherSumRate repOtherSumRate
     */
    public void setRepOtherSumRate(BigDecimal repOtherSumRate) {
        this.repOtherSumRate = repOtherSumRate;
    }

    /**
     * 获取 repDuration
     * @return repDuration.
     */
    public Integer getRepDuration() {
        return repDuration;
    }
    
    /**
     * 设置 repDuration
     * @param repDuration repDuration
     */
    public void setRepDuration(Integer repDuration) {
        this.repDuration = repDuration;
    }
    
    /**
     * 获取 repLiabilitiesMoney
     * @return repLiabilitiesMoney.
     */
    public BigDecimal getRepLiabilitiesMoney() {
        return repLiabilitiesMoney;
    }
    
    /**
     * 设置 repLiabilitiesMoney
     * @param repLiabilitiesMoney repLiabilitiesMoney
     */
    public void setRepLiabilitiesMoney(BigDecimal repLiabilitiesMoney) {
        this.repLiabilitiesMoney = repLiabilitiesMoney;
    }
    
    /**
     * 获取 repInterestRate
     * @return repInterestRate.
     */
    public BigDecimal getRepInterestRate() {
        return repInterestRate;
    }
    
    /**
     * 设置 repInterestRate
     * @param repInterestRate repInterestRate
     */
    public void setRepInterestRate(BigDecimal repInterestRate) {
        this.repInterestRate = repInterestRate;
    }

    /**
     * 获取 repAssetValue
     * @return repAssetValue.
     */
    public BigDecimal getRepAssetValue() {
        return repAssetValue;
    }

    /**
     * 设置 repAssetValue
     * @param repAssetValue repAssetValue
     */
    public void setRepAssetValue(BigDecimal repAssetValue) {
        this.repAssetValue = repAssetValue;
    }

    /**
     * 获取 repBankAssetType
     * @return repBankAssetType.
     */
    public String getRepBankAssetType() {
        return repBankAssetType;
    }
    
    /**
     * 设置 repBankAssetType
     * @param repBankAssetType repBankAssetType
     */
    public void setRepBankAssetType(String repBankAssetType) {
        this.repBankAssetType = repBankAssetType;
    }
    
    /**
     * 获取 repAssetYield
     * @return repAssetYield.
     */
    public BigDecimal getRepAssetYield() {
        return repAssetYield;
    }
    
    /**
     * 设置 repAssetYield
     * @param repAssetYield repAssetYield
     */
    public void setRepAssetYield(BigDecimal repAssetYield) {
        this.repAssetYield = repAssetYield;
    }
    
    /**
     * 获取 repBankAssetTyprExplain
     * @return repBankAssetTyprExplain.
     */
    public String getRepBankAssetTyprExplain() {
        return repBankAssetTyprExplain;
    }
    
    /**
     * 设置 repBankAssetTyprExplain
     * @param repBankAssetTyprExplain repBankAssetTyprExplain
     */
    public void setRepBankAssetTyprExplain(String repBankAssetTyprExplain) {
        this.repBankAssetTyprExplain = repBankAssetTyprExplain;
    }
    
    /**
     * 获取 repMoney
     * @return repMoney.
     */
    public BigDecimal getRepMoney() {
        return repMoney;
    }

    /**
     * 设置 repMoney
     * @param repMoney repMoney
     */
    public void setRepMoney(BigDecimal repMoney) {
        this.repMoney = repMoney;
    }

    /**
     * 获取 repProjectDuration
     * @return repProjectDuration.
     */
    public BigDecimal getRepProjectDuration() {
        return repProjectDuration;
    }

    /**
     * 获取 repContainExpectedYield2
     * @return repContainExpectedYield2.
     */
    public String getRepContainExpectedYield2() {
        return repContainExpectedYield2;
    }

    /**
     * 设置 repContainExpectedYield2
     * @param repContainExpectedYield2 repContainExpectedYield2
     */
    public void setRepContainExpectedYield2(String repContainExpectedYield2) {
        this.repContainExpectedYield2 = repContainExpectedYield2;
    }

    /**
     * 设置 repProjectDuration
     * @param repProjectDuration repProjectDuration
     */
    public void setRepProjectDuration(BigDecimal repProjectDuration) {
        this.repProjectDuration = repProjectDuration;
    }


    
    /**
     * 获取 repProjectYieldRate
     * @return repProjectYieldRate.
     */
    public BigDecimal getRepProjectYieldRate() {
        return repProjectYieldRate;
    }
    
    /**
     * 设置 repProjectYieldRate
     * @param repProjectYieldRate repProjectYieldRate
     */
    public void setRepProjectYieldRate(BigDecimal repProjectYieldRate) {
        this.repProjectYieldRate = repProjectYieldRate;
    }
    
    /**
     * 获取 repFinancierOrgCode
     * @return repFinancierOrgCode.
     */
    public String getRepFinancierOrgCode() {
        return repFinancierOrgCode;
    }
    
    /**
     * 设置 repFinancierOrgCode
     * @param repFinancierOrgCode repFinancierOrgCode
     */
    public void setRepFinancierOrgCode(String repFinancierOrgCode) {
        this.repFinancierOrgCode = repFinancierOrgCode;
    }
    
    /**
     * 获取 repAssetRegDate
     * @return repAssetRegDate.
     */
    public Date getRepAssetRegDate() {
        return repAssetRegDate;
    }
    
    /**
     * 设置 repAssetRegDate
     * @param repAssetRegDate repAssetRegDate
     */
    public void setRepAssetRegDate(Date repAssetRegDate) {
        this.repAssetRegDate = repAssetRegDate;
    }
    
    /**
     * 获取 repTradeTarget
     * @return repTradeTarget.
     */
    public String getRepTradeTarget() {
        return repTradeTarget;
    }
    
    /**
     * 设置 repTradeTarget
     * @param repTradeTarget repTradeTarget
     */
    public void setRepTradeTarget(String repTradeTarget) {
        this.repTradeTarget = repTradeTarget;
    }


	public String getFinprodMarketId2() {
		return finprodMarketId2;
	}


	public void setFinprodMarketId2(String finprodMarketId2) {
		this.finprodMarketId2 = finprodMarketId2;
	}


	public String getFinprodMarketId8() {
		return finprodMarketId8;
	}


	public void setFinprodMarketId8(String finprodMarketId8) {
		this.finprodMarketId8 = finprodMarketId8;
	}


	public String getFinprodMarketId16() {
		return finprodMarketId16;
	}


	public void setFinprodMarketId16(String finprodMarketId16) {
		this.finprodMarketId16 = finprodMarketId16;
	}


	public Date getRepVdate3() {
		return repVdate3;
	}


	public void setRepVdate3(Date repVdate3) {
		this.repVdate3 = repVdate3;
	}


	public Date getRepMdate3() {
		return repMdate3;
	}


	public void setRepMdate3(Date repMdate3) {
		this.repMdate3 = repMdate3;
	}


	public Date getRepVdate4() {
		return repVdate4;
	}


	public void setRepVdate4(Date repVdate4) {
		this.repVdate4 = repVdate4;
	}


	public Date getRepMdate4() {
		return repMdate4;
	}


	public void setRepMdate4(Date repMdate4) {
		this.repMdate4 = repMdate4;
	}


	public Date getRepVdate5() {
		return repVdate5;
	}


	public void setRepVdate5(Date repVdate5) {
		this.repVdate5 = repVdate5;
	}


	public Date getRepMdate5() {
		return repMdate5;
	}


	public void setRepMdate5(Date repMdate5) {
		this.repMdate5 = repMdate5;
	}


	public Date getRepVdate8() {
		return repVdate8;
	}


	public void setRepVdate8(Date repVdate8) {
		this.repVdate8 = repVdate8;
	}


	public Date getRepMdate8() {
		return repMdate8;
	}


	public void setRepMdate8(Date repMdate8) {
		this.repMdate8 = repMdate8;
	}


	public Date getRepVdate9() {
		return repVdate9;
	}


	public void setRepVdate9(Date repVdate9) {
		this.repVdate9 = repVdate9;
	}


	public Date getRepMdate9() {
		return repMdate9;
	}


	public void setRepMdate9(Date repMdate9) {
		this.repMdate9 = repMdate9;
	}


	public Date getRepVdate11() {
		return repVdate11;
	}


	public void setRepVdate11(Date repVdate11) {
		this.repVdate11 = repVdate11;
	}


	public Date getRepMdate11() {
		return repMdate11;
	}


	public void setRepMdate11(Date repMdate11) {
		this.repMdate11 = repMdate11;
	}


	public Date getRepVdate15() {
		return repVdate15;
	}


	public void setRepVdate15(Date repVdate15) {
		this.repVdate15 = repVdate15;
	}


	public Date getRepMdate15() {
		return repMdate15;
	}


	public void setRepMdate15(Date repMdate15) {
		this.repMdate15 = repMdate15;
	}


	public String getRepBasis3() {
		return repBasis3;
	}


	public void setRepBasis3(String repBasis3) {
		this.repBasis3 = repBasis3;
	}


	public String getRepBasis4() {
		return repBasis4;
	}


	public void setRepBasis4(String repBasis4) {
		this.repBasis4 = repBasis4;
	}


	public String getRepBasis9() {
		return repBasis9;
	}


	public void setRepBasis9(String repBasis9) {
		this.repBasis9 = repBasis9;
	}


	public String getRepIndustry16() {
		return repIndustry16;
	}


	public void setRepIndustry16(String repIndustry16) {
		this.repIndustry16 = repIndustry16;
	}


	public String getRepFinprodName4() {
		return repFinprodName4;
	}


	public void setRepFinprodName4(String repFinprodName4) {
		this.repFinprodName4 = repFinprodName4;
	}


	public String getRepFinprodName6() {
		return repFinprodName6;
	}


	public void setRepFinprodName6(String repFinprodName6) {
		this.repFinprodName6 = repFinprodName6;
	}


	public String getRepFinprodName7() {
		return repFinprodName7;
	}


	public void setRepFinprodName7(String repFinprodName7) {
		this.repFinprodName7 = repFinprodName7;
	}


	public String getRepFinprodName8() {
		return repFinprodName8;
	}


	public void setRepFinprodName8(String repFinprodName8) {
		this.repFinprodName8 = repFinprodName8;
	}


	public String getRepFinprodName10() {
		return repFinprodName10;
	}


	public void setRepFinprodName10(String repFinprodName10) {
		this.repFinprodName10 = repFinprodName10;
	}


	public String getRepFinprodName11() {
		return repFinprodName11;
	}


	public void setRepFinprodName11(String repFinprodName11) {
		this.repFinprodName11 = repFinprodName11;
	}


	public String getRepFinprodName12() {
		return repFinprodName12;
	}


	public void setRepFinprodName12(String repFinprodName12) {
		this.repFinprodName12 = repFinprodName12;
	}


	public String getRepFinprodName13() {
		return repFinprodName13;
	}


	public void setRepFinprodName13(String repFinprodName13) {
		this.repFinprodName13 = repFinprodName13;
	}


	public String getRepFinprodName14() {
		return repFinprodName14;
	}


	public void setRepFinprodName14(String repFinprodName14) {
		this.repFinprodName14 = repFinprodName14;
	}


	public String getRepFinprodName15() {
		return repFinprodName15;
	}


	public void setRepFinprodName15(String repFinprodName15) {
		this.repFinprodName15 = repFinprodName15;
	}


	public String getRepFinprodName16() {
		return repFinprodName16;
	}


	public void setRepFinprodName16(String repFinprodName16) {
		this.repFinprodName16 = repFinprodName16;
	}


	public String getRepFinprodName17() {
		return repFinprodName17;
	}


	public void setRepFinprodName17(String repFinprodName17) {
		this.repFinprodName17 = repFinprodName17;
	}


	public String getRepFinprodName18() {
		return repFinprodName18;
	}


	public void setRepFinprodName18(String repFinprodName18) {
		this.repFinprodName18 = repFinprodName18;
	}


	public String getRepIndustry14() {
		return repIndustry14;
	}


	public void setRepIndustry14(String repIndustry14) {
		this.repIndustry14 = repIndustry14;
	}


	public String getRepProIndustry14() {
		return repProIndustry14;
	}


	public void setRepProIndustry14(String repProIndustry14) {
		this.repProIndustry14 = repProIndustry14;
	}


	public String getRepPartyType1_6() {
		return repPartyType1_6;
	}


	public void setRepPartyType1_6(String repPartyType1_6) {
		this.repPartyType1_6 = repPartyType1_6;
	}


	public String getRepPartyType2_6() {
		return repPartyType2_6;
	}


	public void setRepPartyType2_6(String repPartyType2_6) {
		this.repPartyType2_6 = repPartyType2_6;
	}


	public String getRepPartyType3_6() {
		return repPartyType3_6;
	}


	public void setRepPartyType3_6(String repPartyType3_6) {
		this.repPartyType3_6 = repPartyType3_6;
	}


	public String getRepPartyType1_16() {
		return repPartyType1_16;
	}


	public void setRepPartyType1_16(String repPartyType1_16) {
		this.repPartyType1_16 = repPartyType1_16;
	}


	public String getRepPartyType2_16() {
		return repPartyType2_16;
	}


	public void setRepPartyType2_16(String repPartyType2_16) {
		this.repPartyType2_16 = repPartyType2_16;
	}


	public String getRepPartyType3_16() {
		return repPartyType3_16;
	}


	public void setRepPartyType3_16(String repPartyType3_16) {
		this.repPartyType3_16 = repPartyType3_16;
	}

    public String getRepCountryArea8() {
        return repCountryArea8;
    }

    public void setRepCountryArea8(String repCountryArea8) {
        this.repCountryArea8 = repCountryArea8;
    }

    public String getRepCountryArea9() {
        return repCountryArea9;
    }

    public void setRepCountryArea9(String repCountryArea9) {
        this.repCountryArea9 = repCountryArea9;
    }

    public String getRepCountryArea10() {
        return repCountryArea10;
    }

    public void setRepCountryArea10(String repCountryArea10) {
        this.repCountryArea10 = repCountryArea10;
    }

    public String getRepCountryArea11() {
        return repCountryArea11;
    }

    public void setRepCountryArea11(String repCountryArea11) {
        this.repCountryArea11 = repCountryArea11;
    }

    public String getRepCountryArea13() {
        return repCountryArea13;
    }

    public void setRepCountryArea13(String repCountryArea13) {
        this.repCountryArea13 = repCountryArea13;
    }

    public String getRepCountryArea14() {
        return repCountryArea14;
    }

    public void setRepCountryArea14(String repCountryArea14) {
        this.repCountryArea14 = repCountryArea14;
    }

    public String getRepCountryArea15() {
        return repCountryArea15;
    }

    public void setRepCountryArea15(String repCountryArea15) {
        this.repCountryArea15 = repCountryArea15;
    }

    public String getRepCountryArea18() {
        return repCountryArea18;
    }

    public void setRepCountryArea18(String repCountryArea18) {
        this.repCountryArea18 = repCountryArea18;
    }

    private String repFinancier14;

    private String repFcrGradeOut14;

    private String repFinancierType114;

    private String repFinancierType314;

    private String repFinancierType214;

    private String repFinanceProj14;

    private String repIndustryFlag14;

    private String repMonitType14;

    private String repMonTypeComm14;

    private String repOutGrade14;

    public String getRepFinancier14() {
        return repFinancier14;
    }

    public void setRepFinancier14(String repFinancier14) {
        this.repFinancier14 = repFinancier14;
    }

    public String getRepFcrGradeOut14() {
        return repFcrGradeOut14;
    }

    public void setRepFcrGradeOut14(String repFcrGradeOut14) {
        this.repFcrGradeOut14 = repFcrGradeOut14;
    }

    public String getRepFinancierType114() {
        return repFinancierType114;
    }
    public void setRepFinancierType114(String repFinancierType114) {
        this.repFinancierType114 = repFinancierType114;
    }

    public String getRepFinancierType314() {
        return repFinancierType314;
    }

    public void setRepFinancierType314(String repFinancierType314) {
        this.repFinancierType314 = repFinancierType314;
    }

    public String getRepFinancierType214() {
        return repFinancierType214;
    }

    public void setRepFinancierType214(String repFinancierType214) {
        this.repFinancierType214 = repFinancierType214;
    }

    public String getRepFinanceProj14() {
        return repFinanceProj14;
    }

    public void setRepFinanceProj14(String repFinanceProj14) {
        this.repFinanceProj14 = repFinanceProj14;
    }

    public String getRepIndustryFlag14() {
        return repIndustryFlag14;
    }

    public void setRepIndustryFlag14(String repIndustryFlag14) {
        this.repIndustryFlag14 = repIndustryFlag14;
    }

    public String getRepMonitType14() {
        return repMonitType14;
    }

    public void setRepMonitType14(String repMonitType14) {
        this.repMonitType14 = repMonitType14;
    }

    public String getRepMonTypeComm14() {
        return repMonTypeComm14;
    }

    public void setRepMonTypeComm14(String repMonTypeComm14) {
        this.repMonTypeComm14 = repMonTypeComm14;
    }

    public String getRepOutGrade14() {
        return repOutGrade14;
    }

    public void setRepOutGrade14(String repOutGrade14) {
        this.repOutGrade14 = repOutGrade14;
    }
}
