package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class WflTrdProductDealAddPre extends WflTrdProductDealAddPreKey {
    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 理财产品代码
     */
    private String prodId;

    /**
     * 自定义现金流类型
     */
    private String customCashType;

    /**
     * 生成方式
     */
    private String genType;

    /**
     * 预计申购确认日
     */
    private Date purCfmDate;

    /**
     * 预计赎回到账日
     */
    private Date redCfmDate;

    /**
     * 权益登记日份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal regDateAmt;

    /**
     * 权益登记日
     */
    private Date regDate;

    /**
     * 预计分红日
     */
    private Date bonusCfmDate;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 赎回收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redProfit;

    /**
     * 赎回成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redCost;

    /**
     * 主协议金融产品代码
     */
    private String mpFinprodId;

    /**
     * 主协议分支序号
     */
    private Long mpBranch;

    /**
     * 交易方式
     */
    private String dealMode;

    /**
     * 除权除息日
     */
    private Date exDevidentDate;

    /**
     * 是否有锁定期
     */
    private String withLockPeriod;

    /**
     * 锁定期截止日
     */
    private Date lockMdate;

    /**
     * 外汇交易类型
     */
    private String excDealType;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 折美元金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal usdAmt;

    /**
     * 我方角色
     */
    private String ourRole;

    /**
     * 报价精度
     */
    private Long quotPre;

    /**
     * 定价日
     */
    private Date pricedDate;

    /**
     * 定价日即期汇率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pricedDateRate;

    /**
     * 差额收付金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal difPayAmt;

    /**
     * 差额收付币种
     */
    private String difPayCcy;

    /**
     * 差额收付方向
     */
    private String difPs;

    /**
     * 资产推荐方
     */
    private String assetRecommandOrg;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 偏离度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal deviation;

    /**
     * 是否现金交割
     */
    private String isCashset;

    /**
     * 取得 投资组合代码
     * @return 投资组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 投资组合代码
     * @param portfolioId 投资组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 理财产品代码
     * @return 理财产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 理财产品代码
     * @param prodId 理财产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 自定义现金流类型
     * @return 自定义现金流类型
     */
    public String getCustomCashType() {
        return customCashType;
    }

    /**
     * 设置 自定义现金流类型
     * @param customCashType 自定义现金流类型
     */
    public void setCustomCashType(String customCashType) {
        this.customCashType = customCashType == null ? null : customCashType.trim();
    }

    /**
     * 取得 生成方式
     * @return 生成方式
     */
    public String getGenType() {
        return genType;
    }

    /**
     * 设置 生成方式
     * @param genType 生成方式
     */
    public void setGenType(String genType) {
        this.genType = genType == null ? null : genType.trim();
    }

    /**
     * 取得 预计申购确认日
     * @return 预计申购确认日
     */
    public Date getPurCfmDate() {
        return purCfmDate;
    }

    /**
     * 设置 预计申购确认日
     * @param purCfmDate 预计申购确认日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPurCfmDate(Date purCfmDate) {
        this.purCfmDate = purCfmDate;
    }

    /**
     * 取得 预计赎回到账日
     * @return 预计赎回到账日
     */
    public Date getRedCfmDate() {
        return redCfmDate;
    }

    /**
     * 设置 预计赎回到账日
     * @param redCfmDate 预计赎回到账日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRedCfmDate(Date redCfmDate) {
        this.redCfmDate = redCfmDate;
    }

    /**
     * 取得 权益登记日份额
     * @return 权益登记日份额
     */
    public BigDecimal getRegDateAmt() {
        return regDateAmt;
    }

    /**
     * 设置 权益登记日份额
     * @param regDateAmt 权益登记日份额
     */
    public void setRegDateAmt(BigDecimal regDateAmt) {
        this.regDateAmt = regDateAmt;
    }

    /**
     * 取得 权益登记日
     * @return 权益登记日
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * 设置 权益登记日
     * @param regDate 权益登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * 取得 预计分红日
     * @return 预计分红日
     */
    public Date getBonusCfmDate() {
        return bonusCfmDate;
    }

    /**
     * 设置 预计分红日
     * @param bonusCfmDate 预计分红日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBonusCfmDate(Date bonusCfmDate) {
        this.bonusCfmDate = bonusCfmDate;
    }

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 支付类型
     * @return 支付类型
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 支付类型
     * @param payType 支付类型
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 取得 赎回收益
     * @return 赎回收益
     */
    public BigDecimal getRedProfit() {
        return redProfit;
    }

    /**
     * 设置 赎回收益
     * @param redProfit 赎回收益
     */
    public void setRedProfit(BigDecimal redProfit) {
        this.redProfit = redProfit;
    }

    /**
     * 取得 赎回成本
     * @return 赎回成本
     */
    public BigDecimal getRedCost() {
        return redCost;
    }

    /**
     * 设置 赎回成本
     * @param redCost 赎回成本
     */
    public void setRedCost(BigDecimal redCost) {
        this.redCost = redCost;
    }

    /**
     * 取得 主协议金融产品代码
     * @return 主协议金融产品代码
     */
    public String getMpFinprodId() {
        return mpFinprodId;
    }

    /**
     * 设置 主协议金融产品代码
     * @param mpFinprodId 主协议金融产品代码
     */
    public void setMpFinprodId(String mpFinprodId) {
        this.mpFinprodId = mpFinprodId == null ? null : mpFinprodId.trim();
    }

    /**
     * 取得 主协议分支序号
     * @return 主协议分支序号
     */
    public Long getMpBranch() {
        return mpBranch;
    }

    /**
     * 设置 主协议分支序号
     * @param mpBranch 主协议分支序号
     */
    public void setMpBranch(Long mpBranch) {
        this.mpBranch = mpBranch;
    }

    /**
     * 取得 交易方式
     * @return 交易方式
     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 设置 交易方式
     * @param dealMode 交易方式
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode == null ? null : dealMode.trim();
    }

    /**
     * 取得 除权除息日
     * @return 除权除息日
     */
    public Date getExDevidentDate() {
        return exDevidentDate;
    }

    /**
     * 设置 除权除息日
     * @param exDevidentDate 除权除息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setExDevidentDate(Date exDevidentDate) {
        this.exDevidentDate = exDevidentDate;
    }

    /**
     * 取得 是否有锁定期
     * @return 是否有锁定期
     */
    public String getWithLockPeriod() {
        return withLockPeriod;
    }

    /**
     * 设置 是否有锁定期
     * @param withLockPeriod 是否有锁定期
     */
    public void setWithLockPeriod(String withLockPeriod) {
        this.withLockPeriod = withLockPeriod == null ? null : withLockPeriod.trim();
    }

    /**
     * 取得 锁定期截止日
     * @return 锁定期截止日
     */
    public Date getLockMdate() {
        return lockMdate;
    }

    /**
     * 设置 锁定期截止日
     * @param lockMdate 锁定期截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLockMdate(Date lockMdate) {
        this.lockMdate = lockMdate;
    }

    /**
     * 取得 外汇交易类型
     * @return 外汇交易类型
     */
    public String getExcDealType() {
        return excDealType;
    }

    /**
     * 设置 外汇交易类型
     * @param excDealType 外汇交易类型
     */
    public void setExcDealType(String excDealType) {
        this.excDealType = excDealType == null ? null : excDealType.trim();
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
     * 取得 折美元金额
     * @return 折美元金额
     */
    public BigDecimal getUsdAmt() {
        return usdAmt;
    }

    /**
     * 设置 折美元金额
     * @param usdAmt 折美元金额
     */
    public void setUsdAmt(BigDecimal usdAmt) {
        this.usdAmt = usdAmt;
    }

    /**
     * 取得 我方角色
     * @return 我方角色
     */
    public String getOurRole() {
        return ourRole;
    }

    /**
     * 设置 我方角色
     * @param ourRole 我方角色
     */
    public void setOurRole(String ourRole) {
        this.ourRole = ourRole == null ? null : ourRole.trim();
    }

    /**
     * 取得 报价精度
     * @return 报价精度
     */
    public Long getQuotPre() {
        return quotPre;
    }

    /**
     * 设置 报价精度
     * @param quotPre 报价精度
     */
    public void setQuotPre(Long quotPre) {
        this.quotPre = quotPre;
    }

    /**
     * 取得 定价日
     * @return 定价日
     */
    public Date getPricedDate() {
        return pricedDate;
    }

    /**
     * 设置 定价日
     * @param pricedDate 定价日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPricedDate(Date pricedDate) {
        this.pricedDate = pricedDate;
    }

    /**
     * 取得 定价日即期汇率
     * @return 定价日即期汇率
     */
    public BigDecimal getPricedDateRate() {
        return pricedDateRate;
    }

    /**
     * 设置 定价日即期汇率
     * @param pricedDateRate 定价日即期汇率
     */
    public void setPricedDateRate(BigDecimal pricedDateRate) {
        this.pricedDateRate = pricedDateRate;
    }

    /**
     * 取得 差额收付金额
     * @return 差额收付金额
     */
    public BigDecimal getDifPayAmt() {
        return difPayAmt;
    }

    /**
     * 设置 差额收付金额
     * @param difPayAmt 差额收付金额
     */
    public void setDifPayAmt(BigDecimal difPayAmt) {
        this.difPayAmt = difPayAmt;
    }

    /**
     * 取得 差额收付币种
     * @return 差额收付币种
     */
    public String getDifPayCcy() {
        return difPayCcy;
    }

    /**
     * 设置 差额收付币种
     * @param difPayCcy 差额收付币种
     */
    public void setDifPayCcy(String difPayCcy) {
        this.difPayCcy = difPayCcy == null ? null : difPayCcy.trim();
    }

    /**
     * 取得 差额收付方向
     * @return 差额收付方向
     */
    public String getDifPs() {
        return difPs;
    }

    /**
     * 设置 差额收付方向
     * @param difPs 差额收付方向
     */
    public void setDifPs(String difPs) {
        this.difPs = difPs == null ? null : difPs.trim();
    }

    /**
     * 取得 资产推荐方
     * @return 资产推荐方
     */
    public String getAssetRecommandOrg() {
        return assetRecommandOrg;
    }

    /**
     * 设置 资产推荐方
     * @param assetRecommandOrg 资产推荐方
     */
    public void setAssetRecommandOrg(String assetRecommandOrg) {
        this.assetRecommandOrg = assetRecommandOrg == null ? null : assetRecommandOrg.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 偏离度
     * @return 偏离度
     */
    public BigDecimal getDeviation() {
        return deviation;
    }

    /**
     * 设置 偏离度
     * @param deviation 偏离度
     */
    public void setDeviation(BigDecimal deviation) {
        this.deviation = deviation;
    }

    /**
     * 取得 是否现金交割
     * @return 是否现金交割
     */
    public String getIsCashset() {
        return isCashset;
    }

    /**
     * 设置 是否现金交割
     * @param isCashset 是否现金交割
     */
    public void setIsCashset(String isCashset) {
        this.isCashset = isCashset == null ? null : isCashset.trim();
    }
}