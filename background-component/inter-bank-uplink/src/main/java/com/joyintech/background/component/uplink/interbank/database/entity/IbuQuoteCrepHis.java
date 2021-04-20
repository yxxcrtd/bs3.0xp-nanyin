package com.joyintech.background.component.uplink.interbank.database.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.json.BigDecimalSerializer;
import com.joyintech.background.component.common.pack.common.json.CustomDateDeserialize;

public class IbuQuoteCrepHis extends IbuQuoteCrepHisKey {
    /**
     * 报价类型，对话报价/请求报价
     */
    private String quoteType;

    /**
     * 外部报价编号
     */
    private String pQuoteId;

    /**
     * 操作类型
     */
    private String exeType;

    /**
     * CFETS报价编号
     */
    private String cfetsQuoteId;

    /**
     * 本方角色
     */
    private String quoteRoleType;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 报价日期
     */
    private Date quoteDate;

    /**
     * 首期结算日
     */
    private Date vSettleDate;

    /**
     * 到期结算日
     */
    private Date mSettleDate;

    /**
     * 清算速度
     */
    private Long settleSpeed;

    /**
     * 首期结算方式，券款对付、见券付款、见款付券
     */
    private String vDeliveryType;

    /**
     * 到期结算方式，券款对付、见券付款、见款付券
     */
    private String mDeliveryType;

    /**
     * 清算类型
     */
    private String settType;

    /**
     * 结算币种
     */
    private String settCcy;

    /**
     * 债券币种
     */
    private String ccy;

    /**
     * 回购方向
     */
    private String repoPs;

    /**
     * 回购利率 单位：%，精度：4位
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 期限(天)
     */
    private Long term;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 折算券面金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareCtAmt;

    /**
     * 中债折算券面金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cbShareCtAmt;

    /**
     * 上清折算券面金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scShareCtAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 中债全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cbFpriceAmt;

    /**
     * 上清全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scFpriceAmt;

    /**
     * 到期利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mIntAmt;

    /**
     * 到期金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mTradeAmt;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     */
    private String tradeStatus;

    /**
     * 发送状态，待发送/成功/失败/发送中
     */
    private String sendStatus;

    /**
     * 有效时间
     */
    private Date effTime;

    /**
     * 本方银行间机构，21位编号
     */
    private String ouPartyId;

    /**
     * 本方银行间交易员
     */
    private String ouTraderId;

    /**
     * 对手银行间机构，21位编号
     */
    private String cpPartyId;

    /**
     * 对手方银行间交易员
     */
    private String cpTraderId;

    /**
     * 本方资金账户
     */
    private String myAcctNo;

    /**
     * 证券管理户代码
     */
    private String secManageAcctId;

    /**
     * 成交编号
     */
    private String execid;

    /**
     * 前置版本号
     */
    private Long bVersion;

    /**
     * 备注
     */
    private String remark;

    /**
     * 序号ID，平安个性化字段
     */
    private String branchId;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 业务发生时间
     */
    private String transactime;

    /**
     * 取得 报价类型，对话报价/请求报价
     * @return 报价类型，对话报价/请求报价
     */
    public String getQuoteType() {
        return quoteType;
    }

    /**
     * 设置 报价类型，对话报价/请求报价
     * @param quoteType 报价类型，对话报价/请求报价
     */
    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType == null ? null : quoteType.trim();
    }

    /**
     * 取得 外部报价编号
     * @return 外部报价编号
     */
    public String getpQuoteId() {
        return pQuoteId;
    }

    /**
     * 设置 外部报价编号
     * @param pQuoteId 外部报价编号
     */
    public void setpQuoteId(String pQuoteId) {
        this.pQuoteId = pQuoteId == null ? null : pQuoteId.trim();
    }

    /**
     * 取得 操作类型
     * @return 操作类型
     */
    public String getExeType() {
        return exeType;
    }

    /**
     * 设置 操作类型
     * @param exeType 操作类型
     */
    public void setExeType(String exeType) {
        this.exeType = exeType == null ? null : exeType.trim();
    }

    /**
     * 取得 CFETS报价编号
     * @return CFETS报价编号
     */
    public String getCfetsQuoteId() {
        return cfetsQuoteId;
    }

    /**
     * 设置 CFETS报价编号
     * @param cfetsQuoteId CFETS报价编号
     */
    public void setCfetsQuoteId(String cfetsQuoteId) {
        this.cfetsQuoteId = cfetsQuoteId == null ? null : cfetsQuoteId.trim();
    }

    /**
     * 取得 本方角色
     * @return 本方角色
     */
    public String getQuoteRoleType() {
        return quoteRoleType;
    }

    /**
     * 设置 本方角色
     * @param quoteRoleType 本方角色
     */
    public void setQuoteRoleType(String quoteRoleType) {
        this.quoteRoleType = quoteRoleType == null ? null : quoteRoleType.trim();
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
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
     * 取得 报价日期
     * @return 报价日期
     */
    public Date getQuoteDate() {
        return quoteDate;
    }

    /**
     * 设置 报价日期
     * @param quoteDate 报价日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    /**
     * 取得 首期结算日
     * @return 首期结算日
     */
    public Date getvSettleDate() {
        return vSettleDate;
    }

    /**
     * 设置 首期结算日
     * @param vSettleDate 首期结算日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setvSettleDate(Date vSettleDate) {
        this.vSettleDate = vSettleDate;
    }

    /**
     * 取得 到期结算日
     * @return 到期结算日
     */
    public Date getmSettleDate() {
        return mSettleDate;
    }

    /**
     * 设置 到期结算日
     * @param mSettleDate 到期结算日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setmSettleDate(Date mSettleDate) {
        this.mSettleDate = mSettleDate;
    }

    /**
     * 取得 清算速度
     * @return 清算速度
     */
    public Long getSettleSpeed() {
        return settleSpeed;
    }

    /**
     * 设置 清算速度
     * @param settleSpeed 清算速度
     */
    public void setSettleSpeed(Long settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 取得 首期结算方式，券款对付、见券付款、见款付券
     * @return 首期结算方式，券款对付、见券付款、见款付券
     */
    public String getvDeliveryType() {
        return vDeliveryType;
    }

    /**
     * 设置 首期结算方式，券款对付、见券付款、见款付券
     * @param vDeliveryType 首期结算方式，券款对付、见券付款、见款付券
     */
    public void setvDeliveryType(String vDeliveryType) {
        this.vDeliveryType = vDeliveryType == null ? null : vDeliveryType.trim();
    }

    /**
     * 取得 到期结算方式，券款对付、见券付款、见款付券
     * @return 到期结算方式，券款对付、见券付款、见款付券
     */
    public String getmDeliveryType() {
        return mDeliveryType;
    }

    /**
     * 设置 到期结算方式，券款对付、见券付款、见款付券
     * @param mDeliveryType 到期结算方式，券款对付、见券付款、见款付券
     */
    public void setmDeliveryType(String mDeliveryType) {
        this.mDeliveryType = mDeliveryType == null ? null : mDeliveryType.trim();
    }

    /**
     * 取得 清算类型
     * @return 清算类型
     */
    public String getSettType() {
        return settType;
    }

    /**
     * 设置 清算类型
     * @param settType 清算类型
     */
    public void setSettType(String settType) {
        this.settType = settType == null ? null : settType.trim();
    }

    /**
     * 取得 结算币种
     * @return 结算币种
     */
    public String getSettCcy() {
        return settCcy;
    }

    /**
     * 设置 结算币种
     * @param settCcy 结算币种
     */
    public void setSettCcy(String settCcy) {
        this.settCcy = settCcy == null ? null : settCcy.trim();
    }

    /**
     * 取得 债券币种
     * @return 债券币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 债券币种
     * @param ccy 债券币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 回购方向
     * @return 回购方向
     */
    public String getRepoPs() {
        return repoPs;
    }

    /**
     * 设置 回购方向
     * @param repoPs 回购方向
     */
    public void setRepoPs(String repoPs) {
        this.repoPs = repoPs == null ? null : repoPs.trim();
    }

    /**
     * 取得 回购利率 单位：%，精度：4位
     * @return 回购利率 单位：%，精度：4位
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 回购利率 单位：%，精度：4位
     * @param rate 回购利率 单位：%，精度：4位
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 期限(天)
     * @return 期限(天)
     */
    public Long getTerm() {
        return term;
    }

    /**
     * 设置 期限(天)
     * @param term 期限(天)
     */
    public void setTerm(Long term) {
        this.term = term;
    }

    /**
     * 取得 交易份额，债券数量等
     * @return 交易份额，债券数量等
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 交易份额，债券数量等
     * @param shareAmt 交易份额，债券数量等
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 折算券面金额
     * @return 折算券面金额
     */
    public BigDecimal getShareCtAmt() {
        return shareCtAmt;
    }

    /**
     * 设置 折算券面金额
     * @param shareCtAmt 折算券面金额
     */
    public void setShareCtAmt(BigDecimal shareCtAmt) {
        this.shareCtAmt = shareCtAmt;
    }

    /**
     * 取得 中债折算券面金额
     * @return 中债折算券面金额
     */
    public BigDecimal getCbShareCtAmt() {
        return cbShareCtAmt;
    }

    /**
     * 设置 中债折算券面金额
     * @param cbShareCtAmt 中债折算券面金额
     */
    public void setCbShareCtAmt(BigDecimal cbShareCtAmt) {
        this.cbShareCtAmt = cbShareCtAmt;
    }

    /**
     * 取得 上清折算券面金额
     * @return 上清折算券面金额
     */
    public BigDecimal getScShareCtAmt() {
        return scShareCtAmt;
    }

    /**
     * 设置 上清折算券面金额
     * @param scShareCtAmt 上清折算券面金额
     */
    public void setScShareCtAmt(BigDecimal scShareCtAmt) {
        this.scShareCtAmt = scShareCtAmt;
    }

    /**
     * 取得 全价总额
     * @return 全价总额
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 全价总额
     * @param fpriceAmt 全价总额
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 取得 中债全价总额
     * @return 中债全价总额
     */
    public BigDecimal getCbFpriceAmt() {
        return cbFpriceAmt;
    }

    /**
     * 设置 中债全价总额
     * @param cbFpriceAmt 中债全价总额
     */
    public void setCbFpriceAmt(BigDecimal cbFpriceAmt) {
        this.cbFpriceAmt = cbFpriceAmt;
    }

    /**
     * 取得 上清全价总额
     * @return 上清全价总额
     */
    public BigDecimal getScFpriceAmt() {
        return scFpriceAmt;
    }

    /**
     * 设置 上清全价总额
     * @param scFpriceAmt 上清全价总额
     */
    public void setScFpriceAmt(BigDecimal scFpriceAmt) {
        this.scFpriceAmt = scFpriceAmt;
    }

    /**
     * 取得 到期利息
     * @return 到期利息
     */
    public BigDecimal getmIntAmt() {
        return mIntAmt;
    }

    /**
     * 设置 到期利息
     * @param mIntAmt 到期利息
     */
    public void setmIntAmt(BigDecimal mIntAmt) {
        this.mIntAmt = mIntAmt;
    }

    /**
     * 取得 到期金额
     * @return 到期金额
     */
    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    /**
     * 设置 到期金额
     * @param mTradeAmt 到期金额
     */
    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     * @return 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     * @param tradeStatus 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    /**
     * 取得 发送状态，待发送/成功/失败/发送中
     * @return 发送状态，待发送/成功/失败/发送中
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 发送状态，待发送/成功/失败/发送中
     * @param sendStatus 发送状态，待发送/成功/失败/发送中
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 有效时间
     * @return 有效时间
     */
    public Date getEffTime() {
        return effTime;
    }

    /**
     * 设置 有效时间
     * @param effTime 有效时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffTime(Date effTime) {
        this.effTime = effTime;
    }

    /**
     * 取得 本方银行间机构，21位编号
     * @return 本方银行间机构，21位编号
     */
    public String getOuPartyId() {
        return ouPartyId;
    }

    /**
     * 设置 本方银行间机构，21位编号
     * @param ouPartyId 本方银行间机构，21位编号
     */
    public void setOuPartyId(String ouPartyId) {
        this.ouPartyId = ouPartyId == null ? null : ouPartyId.trim();
    }

    /**
     * 取得 本方银行间交易员
     * @return 本方银行间交易员
     */
    public String getOuTraderId() {
        return ouTraderId;
    }

    /**
     * 设置 本方银行间交易员
     * @param ouTraderId 本方银行间交易员
     */
    public void setOuTraderId(String ouTraderId) {
        this.ouTraderId = ouTraderId == null ? null : ouTraderId.trim();
    }

    /**
     * 取得 对手银行间机构，21位编号
     * @return 对手银行间机构，21位编号
     */
    public String getCpPartyId() {
        return cpPartyId;
    }

    /**
     * 设置 对手银行间机构，21位编号
     * @param cpPartyId 对手银行间机构，21位编号
     */
    public void setCpPartyId(String cpPartyId) {
        this.cpPartyId = cpPartyId == null ? null : cpPartyId.trim();
    }

    /**
     * 取得 对手方银行间交易员
     * @return 对手方银行间交易员
     */
    public String getCpTraderId() {
        return cpTraderId;
    }

    /**
     * 设置 对手方银行间交易员
     * @param cpTraderId 对手方银行间交易员
     */
    public void setCpTraderId(String cpTraderId) {
        this.cpTraderId = cpTraderId == null ? null : cpTraderId.trim();
    }

    /**
     * 取得 本方资金账户
     * @return 本方资金账户
     */
    public String getMyAcctNo() {
        return myAcctNo;
    }

    /**
     * 设置 本方资金账户
     * @param myAcctNo 本方资金账户
     */
    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo == null ? null : myAcctNo.trim();
    }

    /**
     * 取得 证券管理户代码
     * @return 证券管理户代码
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 证券管理户代码
     * @param secManageAcctId 证券管理户代码
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId == null ? null : secManageAcctId.trim();
    }

    /**
     * 取得 成交编号
     * @return 成交编号
     */
    public String getExecid() {
        return execid;
    }

    /**
     * 设置 成交编号
     * @param execid 成交编号
     */
    public void setExecid(String execid) {
        this.execid = execid == null ? null : execid.trim();
    }

    /**
     * 取得 前置版本号
     * @return 前置版本号
     */
    public Long getbVersion() {
        return bVersion;
    }

    /**
     * 设置 前置版本号
     * @param bVersion 前置版本号
     */
    public void setbVersion(Long bVersion) {
        this.bVersion = bVersion;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 序号ID，平安个性化字段
     * @return 序号ID，平安个性化字段
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * 设置 序号ID，平安个性化字段
     * @param branchId 序号ID，平安个性化字段
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
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
     * 取得 业务发生时间
     * @return 业务发生时间
     */
    public String getTransactime() {
        return transactime;
    }

    /**
     * 设置 业务发生时间
     * @param transactime 业务发生时间
     */
    public void setTransactime(String transactime) {
        this.transactime = transactime == null ? null : transactime.trim();
    }
}