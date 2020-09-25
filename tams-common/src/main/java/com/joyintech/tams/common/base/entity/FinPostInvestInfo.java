package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinPostInvestInfo extends BaseEntity {
    /**
     * 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     */
    private String finprodId;

    /**
     * 主办客户经理，非标债权专属
     */
    private String hostCustomerManager;

    /**
     * 协办客户经理，非标债权专属
     */
    private String assistCustomerManager;

    /**
     * 是否分层，非标债权专属
     */
    private String isLay;

    /**
     * 分层组合，非标债权专属
     */
    private String layPortfolio;

    /**
     * 我行对应层级，非标债权专属
     */
    private String myBankGrade;

    /**
     * 底层合同形式，非标债权专属
     */
    private String bottomContractForm;

    /**
     * 受让资产性质，券商债权收益权、券商收益凭证专属
     */
    private String natureAssignAssets;

    /**
     * 是否有浮动收益，券商债权收益权、券商收益凭证专属
     */
    private String isFloat;

    /**
     * 浮动收益说明，券商债权收益权、券商收益凭证专属
     */
    private String floatIssueCmt;

    /**
     * 业务品种，结构化证券专属
     */
    private String prdTypes;

    /**
     * 限售期截止日，股票定向增发投资、结构化证券专属
     */
    private Date saleDeadline;

    /**
     * 优先劣后比例，结构化证券、股票定向增发投资专属
     */
    private String propPrioPer;

    /**
     * 劣后出资金额，结构化证券、股票定向增发投资专属
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amountInvesBad;

    /**
     * 预警线，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    private String warnLine;

    /**
     * 预警机制，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    private String warnRule;

    /**
     * 平仓线，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    private String flatLine;

    /**
     * 平仓机制，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    private String flatRule;

    /**
     * 差补方名称，结构化证券、股票定向增发投资专属
     */
    private String diffrentPartyName;

    /**
     * 单票集中度，结构化证券专属
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal singleVoteRatio;

    /**
     * 批复方，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    private String compoundBatch;

    /**
     * 差补情况说明，结构化证券专属
     */
    private String compenSpecCmt;

    /**
     * 质押股票，股票质押式回购专属
     */
    private String pawnShare;

    /**
     * 初始质押率，股票质押式回购专属
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pawnSharePer;

    /**
     * 初始质押价格，股票质押式回购专属
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pawnShareMoney;

    /**
     * 原质押股本数，股票质押式回购专属
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pawnShareQuanlity;

    /**
     * 质押股票是否限售股，股票质押式回购专属
     */
    private String isRestrictShare;

    /**
     * 占总股本比例，股票质押式回购专属
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal propTotalCapitalShare;

    /**
     * 股票质押，股票质押式回购专属
     */
    private String pledgeShare;

    /**
     * 标的股票持有主体，股票质押式回购、股票定向增发投资专属
     */
    private String shareHolder;

    /**
     * 交易场所，股票质押式回购、股票定向增发投资专属
     */
    private String tradeMarket;

    /**
     * 回购方名称，股票质押式回购、股票定向增发投资专属
     */
    private String repurchasePartyName;

    /**
     * 罚息比例，股票质押式回购专属
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal penaltyRate;

    /**
     * 股票代码，股票定向增发投资专属
     */
    private String shareCode;

    /**
     * 股票名称，股票定向增发投资专属
     */
    private String shareName;

    /**
     * 是否代持，股票定向增发投资专属
     */
    private String isBuyBack;

    /**
     * 代持利率，股票定向增发投资专属
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyBackRate;

    /**
     * 代持购回条件，股票定向增发投资专属
     */
    private String buyBackPolicy;

    /**
     * 初始劣后占比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal initInferiorRate;

    /**
     * 是否含回购条款
     */
    private String isRepClause;

    /**
     * 部门内部资产分类
     */
    private String assetInsideType;

    /**
     * 底层资产类型
     */
    private String assetBotType;

    /**
     * 投后管理机构
     */
    private String postOrg;

    /**
     * 取得 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     * @return 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     * @param finprodId 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 主办客户经理，非标债权专属
     * @return 主办客户经理，非标债权专属
     */
    public String getHostCustomerManager() {
        return hostCustomerManager;
    }

    /**
     * 设置 主办客户经理，非标债权专属
     * @param hostCustomerManager 主办客户经理，非标债权专属
     */
    public void setHostCustomerManager(String hostCustomerManager) {
        this.hostCustomerManager = hostCustomerManager == null ? null : hostCustomerManager.trim();
    }

    /**
     * 取得 协办客户经理，非标债权专属
     * @return 协办客户经理，非标债权专属
     */
    public String getAssistCustomerManager() {
        return assistCustomerManager;
    }

    /**
     * 设置 协办客户经理，非标债权专属
     * @param assistCustomerManager 协办客户经理，非标债权专属
     */
    public void setAssistCustomerManager(String assistCustomerManager) {
        this.assistCustomerManager = assistCustomerManager == null ? null : assistCustomerManager.trim();
    }

    /**
     * 取得 是否分层，非标债权专属
     * @return 是否分层，非标债权专属
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 是否分层，非标债权专属
     * @param isLay 是否分层，非标债权专属
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay == null ? null : isLay.trim();
    }

    /**
     * 取得 分层组合，非标债权专属
     * @return 分层组合，非标债权专属
     */
    public String getLayPortfolio() {
        return layPortfolio;
    }

    /**
     * 设置 分层组合，非标债权专属
     * @param layPortfolio 分层组合，非标债权专属
     */
    public void setLayPortfolio(String layPortfolio) {
        this.layPortfolio = layPortfolio == null ? null : layPortfolio.trim();
    }

    /**
     * 取得 我行对应层级，非标债权专属
     * @return 我行对应层级，非标债权专属
     */
    public String getMyBankGrade() {
        return myBankGrade;
    }

    /**
     * 设置 我行对应层级，非标债权专属
     * @param myBankGrade 我行对应层级，非标债权专属
     */
    public void setMyBankGrade(String myBankGrade) {
        this.myBankGrade = myBankGrade == null ? null : myBankGrade.trim();
    }

    /**
     * 取得 底层合同形式，非标债权专属
     * @return 底层合同形式，非标债权专属
     */
    public String getBottomContractForm() {
        return bottomContractForm;
    }

    /**
     * 设置 底层合同形式，非标债权专属
     * @param bottomContractForm 底层合同形式，非标债权专属
     */
    public void setBottomContractForm(String bottomContractForm) {
        this.bottomContractForm = bottomContractForm == null ? null : bottomContractForm.trim();
    }

    /**
     * 取得 受让资产性质，券商债权收益权、券商收益凭证专属
     * @return 受让资产性质，券商债权收益权、券商收益凭证专属
     */
    public String getNatureAssignAssets() {
        return natureAssignAssets;
    }

    /**
     * 设置 受让资产性质，券商债权收益权、券商收益凭证专属
     * @param natureAssignAssets 受让资产性质，券商债权收益权、券商收益凭证专属
     */
    public void setNatureAssignAssets(String natureAssignAssets) {
        this.natureAssignAssets = natureAssignAssets == null ? null : natureAssignAssets.trim();
    }

    /**
     * 取得 是否有浮动收益，券商债权收益权、券商收益凭证专属
     * @return 是否有浮动收益，券商债权收益权、券商收益凭证专属
     */
    public String getIsFloat() {
        return isFloat;
    }

    /**
     * 设置 是否有浮动收益，券商债权收益权、券商收益凭证专属
     * @param isFloat 是否有浮动收益，券商债权收益权、券商收益凭证专属
     */
    public void setIsFloat(String isFloat) {
        this.isFloat = isFloat == null ? null : isFloat.trim();
    }

    /**
     * 取得 浮动收益说明，券商债权收益权、券商收益凭证专属
     * @return 浮动收益说明，券商债权收益权、券商收益凭证专属
     */
    public String getFloatIssueCmt() {
        return floatIssueCmt;
    }

    /**
     * 设置 浮动收益说明，券商债权收益权、券商收益凭证专属
     * @param floatIssueCmt 浮动收益说明，券商债权收益权、券商收益凭证专属
     */
    public void setFloatIssueCmt(String floatIssueCmt) {
        this.floatIssueCmt = floatIssueCmt == null ? null : floatIssueCmt.trim();
    }

    /**
     * 取得 业务品种，结构化证券专属
     * @return 业务品种，结构化证券专属
     */
    public String getPrdTypes() {
        return prdTypes;
    }

    /**
     * 设置 业务品种，结构化证券专属
     * @param prdTypes 业务品种，结构化证券专属
     */
    public void setPrdTypes(String prdTypes) {
        this.prdTypes = prdTypes == null ? null : prdTypes.trim();
    }

    /**
     * 取得 限售期截止日，股票定向增发投资、结构化证券专属
     * @return 限售期截止日，股票定向增发投资、结构化证券专属
     */
    public Date getSaleDeadline() {
        return saleDeadline;
    }

    /**
     * 设置 限售期截止日，股票定向增发投资、结构化证券专属
     * @param saleDeadline 限售期截止日，股票定向增发投资、结构化证券专属
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSaleDeadline(Date saleDeadline) {
        this.saleDeadline = saleDeadline;
    }

    /**
     * 取得 优先劣后比例，结构化证券、股票定向增发投资专属
     * @return 优先劣后比例，结构化证券、股票定向增发投资专属
     */
    public String getPropPrioPer() {
        return propPrioPer;
    }

    /**
     * 设置 优先劣后比例，结构化证券、股票定向增发投资专属
     * @param propPrioPer 优先劣后比例，结构化证券、股票定向增发投资专属
     */
    public void setPropPrioPer(String propPrioPer) {
        this.propPrioPer = propPrioPer == null ? null : propPrioPer.trim();
    }

    /**
     * 取得 劣后出资金额，结构化证券、股票定向增发投资专属
     * @return 劣后出资金额，结构化证券、股票定向增发投资专属
     */
    public BigDecimal getAmountInvesBad() {
        return amountInvesBad;
    }

    /**
     * 设置 劣后出资金额，结构化证券、股票定向增发投资专属
     * @param amountInvesBad 劣后出资金额，结构化证券、股票定向增发投资专属
     */
    public void setAmountInvesBad(BigDecimal amountInvesBad) {
        this.amountInvesBad = amountInvesBad;
    }

    /**
     * 取得 预警线，股票质押式回购、结构化证券、股票定向增发投资专属
     * @return 预警线，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public String getWarnLine() {
        return warnLine;
    }

    /**
     * 设置 预警线，股票质押式回购、结构化证券、股票定向增发投资专属
     * @param warnLine 预警线，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public void setWarnLine(String warnLine) {
        this.warnLine = warnLine == null ? null : warnLine.trim();
    }

    /**
     * 取得 预警机制，股票质押式回购、结构化证券、股票定向增发投资专属
     * @return 预警机制，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public String getWarnRule() {
        return warnRule;
    }

    /**
     * 设置 预警机制，股票质押式回购、结构化证券、股票定向增发投资专属
     * @param warnRule 预警机制，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public void setWarnRule(String warnRule) {
        this.warnRule = warnRule == null ? null : warnRule.trim();
    }

    /**
     * 取得 平仓线，股票质押式回购、结构化证券、股票定向增发投资专属
     * @return 平仓线，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public String getFlatLine() {
        return flatLine;
    }

    /**
     * 设置 平仓线，股票质押式回购、结构化证券、股票定向增发投资专属
     * @param flatLine 平仓线，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public void setFlatLine(String flatLine) {
        this.flatLine = flatLine == null ? null : flatLine.trim();
    }

    /**
     * 取得 平仓机制，股票质押式回购、结构化证券、股票定向增发投资专属
     * @return 平仓机制，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public String getFlatRule() {
        return flatRule;
    }

    /**
     * 设置 平仓机制，股票质押式回购、结构化证券、股票定向增发投资专属
     * @param flatRule 平仓机制，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public void setFlatRule(String flatRule) {
        this.flatRule = flatRule == null ? null : flatRule.trim();
    }

    /**
     * 取得 差补方名称，结构化证券、股票定向增发投资专属
     * @return 差补方名称，结构化证券、股票定向增发投资专属
     */
    public String getDiffrentPartyName() {
        return diffrentPartyName;
    }

    /**
     * 设置 差补方名称，结构化证券、股票定向增发投资专属
     * @param diffrentPartyName 差补方名称，结构化证券、股票定向增发投资专属
     */
    public void setDiffrentPartyName(String diffrentPartyName) {
        this.diffrentPartyName = diffrentPartyName == null ? null : diffrentPartyName.trim();
    }

    /**
     * 取得 单票集中度，结构化证券专属
     * @return 单票集中度，结构化证券专属
     */
    public BigDecimal getSingleVoteRatio() {
        return singleVoteRatio;
    }

    /**
     * 设置 单票集中度，结构化证券专属
     * @param singleVoteRatio 单票集中度，结构化证券专属
     */
    public void setSingleVoteRatio(BigDecimal singleVoteRatio) {
        this.singleVoteRatio = singleVoteRatio;
    }

    /**
     * 取得 批复方，股票质押式回购、结构化证券、股票定向增发投资专属
     * @return 批复方，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public String getCompoundBatch() {
        return compoundBatch;
    }

    /**
     * 设置 批复方，股票质押式回购、结构化证券、股票定向增发投资专属
     * @param compoundBatch 批复方，股票质押式回购、结构化证券、股票定向增发投资专属
     */
    public void setCompoundBatch(String compoundBatch) {
        this.compoundBatch = compoundBatch == null ? null : compoundBatch.trim();
    }

    /**
     * 取得 差补情况说明，结构化证券专属
     * @return 差补情况说明，结构化证券专属
     */
    public String getCompenSpecCmt() {
        return compenSpecCmt;
    }

    /**
     * 设置 差补情况说明，结构化证券专属
     * @param compenSpecCmt 差补情况说明，结构化证券专属
     */
    public void setCompenSpecCmt(String compenSpecCmt) {
        this.compenSpecCmt = compenSpecCmt == null ? null : compenSpecCmt.trim();
    }

    /**
     * 取得 质押股票，股票质押式回购专属
     * @return 质押股票，股票质押式回购专属
     */
    public String getPawnShare() {
        return pawnShare;
    }

    /**
     * 设置 质押股票，股票质押式回购专属
     * @param pawnShare 质押股票，股票质押式回购专属
     */
    public void setPawnShare(String pawnShare) {
        this.pawnShare = pawnShare == null ? null : pawnShare.trim();
    }

    /**
     * 取得 初始质押率，股票质押式回购专属
     * @return 初始质押率，股票质押式回购专属
     */
    public BigDecimal getPawnSharePer() {
        return pawnSharePer;
    }

    /**
     * 设置 初始质押率，股票质押式回购专属
     * @param pawnSharePer 初始质押率，股票质押式回购专属
     */
    public void setPawnSharePer(BigDecimal pawnSharePer) {
        this.pawnSharePer = pawnSharePer;
    }

    /**
     * 取得 初始质押价格，股票质押式回购专属
     * @return 初始质押价格，股票质押式回购专属
     */
    public BigDecimal getPawnShareMoney() {
        return pawnShareMoney;
    }

    /**
     * 设置 初始质押价格，股票质押式回购专属
     * @param pawnShareMoney 初始质押价格，股票质押式回购专属
     */
    public void setPawnShareMoney(BigDecimal pawnShareMoney) {
        this.pawnShareMoney = pawnShareMoney;
    }

    /**
     * 取得 原质押股本数，股票质押式回购专属
     * @return 原质押股本数，股票质押式回购专属
     */
    public BigDecimal getPawnShareQuanlity() {
        return pawnShareQuanlity;
    }

    /**
     * 设置 原质押股本数，股票质押式回购专属
     * @param pawnShareQuanlity 原质押股本数，股票质押式回购专属
     */
    public void setPawnShareQuanlity(BigDecimal pawnShareQuanlity) {
        this.pawnShareQuanlity = pawnShareQuanlity;
    }

    /**
     * 取得 质押股票是否限售股，股票质押式回购专属
     * @return 质押股票是否限售股，股票质押式回购专属
     */
    public String getIsRestrictShare() {
        return isRestrictShare;
    }

    /**
     * 设置 质押股票是否限售股，股票质押式回购专属
     * @param isRestrictShare 质押股票是否限售股，股票质押式回购专属
     */
    public void setIsRestrictShare(String isRestrictShare) {
        this.isRestrictShare = isRestrictShare == null ? null : isRestrictShare.trim();
    }

    /**
     * 取得 占总股本比例，股票质押式回购专属
     * @return 占总股本比例，股票质押式回购专属
     */
    public BigDecimal getPropTotalCapitalShare() {
        return propTotalCapitalShare;
    }

    /**
     * 设置 占总股本比例，股票质押式回购专属
     * @param propTotalCapitalShare 占总股本比例，股票质押式回购专属
     */
    public void setPropTotalCapitalShare(BigDecimal propTotalCapitalShare) {
        this.propTotalCapitalShare = propTotalCapitalShare;
    }

    /**
     * 取得 股票质押，股票质押式回购专属
     * @return 股票质押，股票质押式回购专属
     */
    public String getPledgeShare() {
        return pledgeShare;
    }

    /**
     * 设置 股票质押，股票质押式回购专属
     * @param pledgeShare 股票质押，股票质押式回购专属
     */
    public void setPledgeShare(String pledgeShare) {
        this.pledgeShare = pledgeShare == null ? null : pledgeShare.trim();
    }

    /**
     * 取得 标的股票持有主体，股票质押式回购、股票定向增发投资专属
     * @return 标的股票持有主体，股票质押式回购、股票定向增发投资专属
     */
    public String getShareHolder() {
        return shareHolder;
    }

    /**
     * 设置 标的股票持有主体，股票质押式回购、股票定向增发投资专属
     * @param shareHolder 标的股票持有主体，股票质押式回购、股票定向增发投资专属
     */
    public void setShareHolder(String shareHolder) {
        this.shareHolder = shareHolder == null ? null : shareHolder.trim();
    }

    /**
     * 取得 交易场所，股票质押式回购、股票定向增发投资专属
     * @return 交易场所，股票质押式回购、股票定向增发投资专属
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所，股票质押式回购、股票定向增发投资专属
     * @param tradeMarket 交易场所，股票质押式回购、股票定向增发投资专属
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 回购方名称，股票质押式回购、股票定向增发投资专属
     * @return 回购方名称，股票质押式回购、股票定向增发投资专属
     */
    public String getRepurchasePartyName() {
        return repurchasePartyName;
    }

    /**
     * 设置 回购方名称，股票质押式回购、股票定向增发投资专属
     * @param repurchasePartyName 回购方名称，股票质押式回购、股票定向增发投资专属
     */
    public void setRepurchasePartyName(String repurchasePartyName) {
        this.repurchasePartyName = repurchasePartyName == null ? null : repurchasePartyName.trim();
    }

    /**
     * 取得 罚息比例，股票质押式回购专属
     * @return 罚息比例，股票质押式回购专属
     */
    public BigDecimal getPenaltyRate() {
        return penaltyRate;
    }

    /**
     * 设置 罚息比例，股票质押式回购专属
     * @param penaltyRate 罚息比例，股票质押式回购专属
     */
    public void setPenaltyRate(BigDecimal penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    /**
     * 取得 股票代码，股票定向增发投资专属
     * @return 股票代码，股票定向增发投资专属
     */
    public String getShareCode() {
        return shareCode;
    }

    /**
     * 设置 股票代码，股票定向增发投资专属
     * @param shareCode 股票代码，股票定向增发投资专属
     */
    public void setShareCode(String shareCode) {
        this.shareCode = shareCode == null ? null : shareCode.trim();
    }

    /**
     * 取得 股票名称，股票定向增发投资专属
     * @return 股票名称，股票定向增发投资专属
     */
    public String getShareName() {
        return shareName;
    }

    /**
     * 设置 股票名称，股票定向增发投资专属
     * @param shareName 股票名称，股票定向增发投资专属
     */
    public void setShareName(String shareName) {
        this.shareName = shareName == null ? null : shareName.trim();
    }

    /**
     * 取得 是否代持，股票定向增发投资专属
     * @return 是否代持，股票定向增发投资专属
     */
    public String getIsBuyBack() {
        return isBuyBack;
    }

    /**
     * 设置 是否代持，股票定向增发投资专属
     * @param isBuyBack 是否代持，股票定向增发投资专属
     */
    public void setIsBuyBack(String isBuyBack) {
        this.isBuyBack = isBuyBack == null ? null : isBuyBack.trim();
    }

    /**
     * 取得 代持利率，股票定向增发投资专属
     * @return 代持利率，股票定向增发投资专属
     */
    public BigDecimal getBuyBackRate() {
        return buyBackRate;
    }

    /**
     * 设置 代持利率，股票定向增发投资专属
     * @param buyBackRate 代持利率，股票定向增发投资专属
     */
    public void setBuyBackRate(BigDecimal buyBackRate) {
        this.buyBackRate = buyBackRate;
    }

    /**
     * 取得 代持购回条件，股票定向增发投资专属
     * @return 代持购回条件，股票定向增发投资专属
     */
    public String getBuyBackPolicy() {
        return buyBackPolicy;
    }

    /**
     * 设置 代持购回条件，股票定向增发投资专属
     * @param buyBackPolicy 代持购回条件，股票定向增发投资专属
     */
    public void setBuyBackPolicy(String buyBackPolicy) {
        this.buyBackPolicy = buyBackPolicy == null ? null : buyBackPolicy.trim();
    }

    /**
     * 取得 初始劣后占比
     * @return 初始劣后占比
     */
    public BigDecimal getInitInferiorRate() {
        return initInferiorRate;
    }

    /**
     * 设置 初始劣后占比
     * @param initInferiorRate 初始劣后占比
     */
    public void setInitInferiorRate(BigDecimal initInferiorRate) {
        this.initInferiorRate = initInferiorRate;
    }

    /**
     * 取得 是否含回购条款
     * @return 是否含回购条款
     */
    public String getIsRepClause() {
        return isRepClause;
    }

    /**
     * 设置 是否含回购条款
     * @param isRepClause 是否含回购条款
     */
    public void setIsRepClause(String isRepClause) {
        this.isRepClause = isRepClause == null ? null : isRepClause.trim();
    }

    /**
     * 取得 部门内部资产分类
     * @return 部门内部资产分类
     */
    public String getAssetInsideType() {
        return assetInsideType;
    }

    /**
     * 设置 部门内部资产分类
     * @param assetInsideType 部门内部资产分类
     */
    public void setAssetInsideType(String assetInsideType) {
        this.assetInsideType = assetInsideType == null ? null : assetInsideType.trim();
    }

    /**
     * 取得 底层资产类型
     * @return 底层资产类型
     */
    public String getAssetBotType() {
        return assetBotType;
    }

    /**
     * 设置 底层资产类型
     * @param assetBotType 底层资产类型
     */
    public void setAssetBotType(String assetBotType) {
        this.assetBotType = assetBotType == null ? null : assetBotType.trim();
    }

    /**
     * 取得 投后管理机构
     * @return 投后管理机构
     */
    public String getPostOrg() {
        return postOrg;
    }

    /**
     * 设置 投后管理机构
     * @param postOrg 投后管理机构
     */
    public void setPostOrg(String postOrg) {
        this.postOrg = postOrg == null ? null : postOrg.trim();
    }
}