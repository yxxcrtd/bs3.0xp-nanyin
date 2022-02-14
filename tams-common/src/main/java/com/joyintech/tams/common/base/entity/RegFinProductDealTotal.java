package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RegFinProductDealTotal extends BaseEntity {
    /**
     * 汇总登记编号
     */
    private String totleTradeId;

    /**
     * 报价方式
     */
    private String quoteType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 市场代码
     */
    private String marketId;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易日期，行权回售为登记日
     */
    private Date tradeDate;

    /**
     * 本方银行间机构，21位编号
     */
    private String ouPartyId;

    /**
     * 本方银行间交易员
     */
    private String ouTraderId;

    /**
     * 对手方机构，交易对手代码
     */
    private String counterId;

    /**
     * 对手方交易员
     */
    private String counterTrader;

    /**
     * 交易币种
     */
    private String ccy;

    /**
     * 清算速度
     */
    private Long settleSpeed;

    /**
     * 结算日期，行权回售时为理论行权日期
     */
    private Date settleDate;

    /**
     * 单位净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 到期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 行权收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exerYield;

    /**
     * 单位利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 单位全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitFprice;

    /**
     * 单位面值，债券100或者当前面值，其他资产1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal parValue;

    /**
     * 净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;

    /**
     * 利息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 交易份额，券面总额/100
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 结算方式，券款对付、见券付款、见款付券
     */
    private String deliveryType;

    /**
     * 到期结算方式，券款对付、见券付款、见款付券
     */
    private String mDeliveryType;

    /**
     * 结算币种
     */
    private String settCcy;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 清算类型，净额清算/全额清算
     */
    private String clearType;

    /**
     * 证券账户代码
     */
    private String secManageAcctId;

    /**
     * 本方资金账户
     */
    private String acctId;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 交易场所，银行间、上交所、深交所、柜面等
     */
    private String tradeMarket;

    /**
     * 交易市场，一级市场、二级市场
     */
    private String tradeMarketMode;

    /**
     * 利率，回购拆借的利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 回购期限(天)
     */
    private Long repoTerm;

    /**
     * 期限品种，适用回购、拆借、互换、外汇掉期远端
     */
    private String termType;

    /**
     * 券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal faceValue;

    /**
     * 质押券折算总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal disFaceValue;

    /**
     * 回购方式，双边回购
     */
    private String repoType;

    /**
     * 首期结算日
     */
    private Date vSettleDate;

    /**
     * 到期结算日
     */
    private Date mSettleDate;

    /**
     * 到期利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mIntAmt;

    /**
     * 到期结算金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mTradeAmt;

    /**
     * 实际占款天数
     */
    private Long actRepoTerm;

    /**
     * 投管业务类型
     */
    private String tgBusiType;

    /**
     * 交易状态，初始、修改、撤销
     */
    private String tradeStatus;

    /**
     * 原交易编号
     */
    private String pTradeId;

    /**
     * 是否已撤销，默认“否”，在交易发生撤销时更新成“是”
     */
    private String isCancel;

    /**
     * 是否外部交易，是、否
     */
    private String isOutTrade;

    /**
     * 关联系统交易编号
     */
    private String fTradeId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分配方式，平均分配，比例分配
     */
    private String dipType;

    /**
     * 行权净价
     */
    private BigDecimal exercisePrice;
    /**
     * 赎回比例
     */
    private BigDecimal redeemRate;


    /**
     * 取得 汇总登记编号
     * @return 汇总登记编号
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 汇总登记编号
     * @param totleTradeId 汇总登记编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId == null ? null : totleTradeId.trim();
    }

    /**
     * 取得 报价方式
     * @return 报价方式
     */
    public String getQuoteType() {
        return quoteType;
    }

    /**
     * 设置 报价方式
     * @param quoteType 报价方式
     */
    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType == null ? null : quoteType.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType2 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 市场代码
     * @return 市场代码
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * 设置 市场代码
     * @param marketId 市场代码
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 交易日期，行权回售为登记日
     * @return 交易日期，行权回售为登记日
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日期，行权回售为登记日
     * @param tradeDate 交易日期，行权回售为登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
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
     * 取得 对手方机构，交易对手代码
     * @return 对手方机构，交易对手代码
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 对手方机构，交易对手代码
     * @param counterId 对手方机构，交易对手代码
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId == null ? null : counterId.trim();
    }

    /**
     * 取得 对手方交易员
     * @return 对手方交易员
     */
    public String getCounterTrader() {
        return counterTrader;
    }

    /**
     * 设置 对手方交易员
     * @param counterTrader 对手方交易员
     */
    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader == null ? null : counterTrader.trim();
    }

    /**
     * 取得 交易币种
     * @return 交易币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 交易币种
     * @param ccy 交易币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
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
     * 取得 结算日期，行权回售时为理论行权日期
     * @return 结算日期，行权回售时为理论行权日期
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 结算日期，行权回售时为理论行权日期
     * @param settleDate 结算日期，行权回售时为理论行权日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 取得 单位净价
     * @return 单位净价
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 单位净价
     * @param unitCprice 单位净价
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 取得 到期收益率
     * @return 到期收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 到期收益率
     * @param yield 到期收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 行权收益率
     * @return 行权收益率
     */
    public BigDecimal getExerYield() {
        return exerYield;
    }

    /**
     * 设置 行权收益率
     * @param exerYield 行权收益率
     */
    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    /**
     * 取得 单位利息
     * @return 单位利息
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }

    /**
     * 设置 单位利息
     * @param unitInt 单位利息
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    /**
     * 取得 单位全价
     * @return 单位全价
     */
    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    /**
     * 设置 单位全价
     * @param unitFprice 单位全价
     */
    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    /**
     * 取得 单位面值，债券100或者当前面值，其他资产1
     * @return 单位面值，债券100或者当前面值，其他资产1
     */
    public BigDecimal getParValue() {
        return parValue;
    }

    /**
     * 设置 单位面值，债券100或者当前面值，其他资产1
     * @param parValue 单位面值，债券100或者当前面值，其他资产1
     */
    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    /**
     * 取得 净价总额
     * @return 净价总额
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    /**
     * 设置 净价总额
     * @param cpriceAmt 净价总额
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    /**
     * 取得 利息总额
     * @return 利息总额
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 利息总额
     * @param intAmt 利息总额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
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
     * 取得 交易份额，券面总额/100
     * @return 交易份额，券面总额/100
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 交易份额，券面总额/100
     * @param shareAmt 交易份额，券面总额/100
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 结算方式，券款对付、见券付款、见款付券
     * @return 结算方式，券款对付、见券付款、见款付券
     */
    public String getDeliveryType() {
        return deliveryType;
    }

    /**
     * 设置 结算方式，券款对付、见券付款、见款付券
     * @param deliveryType 结算方式，券款对付、见券付款、见款付券
     */
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType == null ? null : deliveryType.trim();
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
     * 取得 交易金额
     * @return 交易金额
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 交易金额
     * @param tradeAmt 交易金额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 取得 清算类型，净额清算/全额清算
     * @return 清算类型，净额清算/全额清算
     */
    public String getClearType() {
        return clearType;
    }

    /**
     * 设置 清算类型，净额清算/全额清算
     * @param clearType 清算类型，净额清算/全额清算
     */
    public void setClearType(String clearType) {
        this.clearType = clearType == null ? null : clearType.trim();
    }

    /**
     * 取得 证券账户代码
     * @return 证券账户代码
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 证券账户代码
     * @param secManageAcctId 证券账户代码
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId == null ? null : secManageAcctId.trim();
    }

    /**
     * 取得 本方资金账户
     * @return 本方资金账户
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * 设置 本方资金账户
     * @param acctId 本方资金账户
     */
    public void setAcctId(String acctId) {
        this.acctId = acctId == null ? null : acctId.trim();
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }

    /**
     * 取得 交易场所，银行间、上交所、深交所、柜面等
     * @return 交易场所，银行间、上交所、深交所、柜面等
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所，银行间、上交所、深交所、柜面等
     * @param tradeMarket 交易场所，银行间、上交所、深交所、柜面等
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 交易市场，一级市场、二级市场
     * @return 交易市场，一级市场、二级市场
     */
    public String getTradeMarketMode() {
        return tradeMarketMode;
    }

    /**
     * 设置 交易市场，一级市场、二级市场
     * @param tradeMarketMode 交易市场，一级市场、二级市场
     */
    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode == null ? null : tradeMarketMode.trim();
    }

    /**
     * 取得 利率，回购拆借的利率
     * @return 利率，回购拆借的利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 利率，回购拆借的利率
     * @param rate 利率，回购拆借的利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 回购期限(天)
     * @return 回购期限(天)
     */
    public Long getRepoTerm() {
        return repoTerm;
    }

    /**
     * 设置 回购期限(天)
     * @param repoTerm 回购期限(天)
     */
    public void setRepoTerm(Long repoTerm) {
        this.repoTerm = repoTerm;
    }

    /**
     * 取得 期限品种，适用回购、拆借、互换、外汇掉期远端
     * @return 期限品种，适用回购、拆借、互换、外汇掉期远端
     */
    public String getTermType() {
        return termType;
    }

    /**
     * 设置 期限品种，适用回购、拆借、互换、外汇掉期远端
     * @param termType 期限品种，适用回购、拆借、互换、外汇掉期远端
     */
    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    /**
     * 取得 券面总额
     * @return 券面总额
     */
    public BigDecimal getFaceValue() {
        return faceValue;
    }

    /**
     * 设置 券面总额
     * @param faceValue 券面总额
     */
    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    /**
     * 取得 质押券折算总额
     * @return 质押券折算总额
     */
    public BigDecimal getDisFaceValue() {
        return disFaceValue;
    }

    /**
     * 设置 质押券折算总额
     * @param disFaceValue 质押券折算总额
     */
    public void setDisFaceValue(BigDecimal disFaceValue) {
        this.disFaceValue = disFaceValue;
    }

    /**
     * 取得 回购方式，双边回购
     * @return 回购方式，双边回购
     */
    public String getRepoType() {
        return repoType;
    }

    /**
     * 设置 回购方式，双边回购
     * @param repoType 回购方式，双边回购
     */
    public void setRepoType(String repoType) {
        this.repoType = repoType == null ? null : repoType.trim();
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
     * 取得 到期结算金额
     * @return 到期结算金额
     */
    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    /**
     * 设置 到期结算金额
     * @param mTradeAmt 到期结算金额
     */
    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    /**
     * 取得 实际占款天数
     * @return 实际占款天数
     */
    public Long getActRepoTerm() {
        return actRepoTerm;
    }

    /**
     * 设置 实际占款天数
     * @param actRepoTerm 实际占款天数
     */
    public void setActRepoTerm(Long actRepoTerm) {
        this.actRepoTerm = actRepoTerm;
    }

    /**
     * 取得 投管业务类型
     * @return 投管业务类型
     */
    public String getTgBusiType() {
        return tgBusiType;
    }

    /**
     * 设置 投管业务类型
     * @param tgBusiType 投管业务类型
     */
    public void setTgBusiType(String tgBusiType) {
        this.tgBusiType = tgBusiType == null ? null : tgBusiType.trim();
    }

    /**
     * 取得 交易状态，初始、修改、撤销
     * @return 交易状态，初始、修改、撤销
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置 交易状态，初始、修改、撤销
     * @param tradeStatus 交易状态，初始、修改、撤销
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    /**
     * 取得 原交易编号
     * @return 原交易编号
     */
    public String getpTradeId() {
        return pTradeId;
    }

    /**
     * 设置 原交易编号
     * @param pTradeId 原交易编号
     */
    public void setpTradeId(String pTradeId) {
        this.pTradeId = pTradeId == null ? null : pTradeId.trim();
    }

    /**
     * 取得 是否已撤销，默认“否”，在交易发生撤销时更新成“是”
     * @return 是否已撤销，默认“否”，在交易发生撤销时更新成“是”
     */
    public String getIsCancel() {
        return isCancel;
    }

    /**
     * 设置 是否已撤销，默认“否”，在交易发生撤销时更新成“是”
     * @param isCancel 是否已撤销，默认“否”，在交易发生撤销时更新成“是”
     */
    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel == null ? null : isCancel.trim();
    }

    /**
     * 取得 是否外部交易，是、否
     * @return 是否外部交易，是、否
     */
    public String getIsOutTrade() {
        return isOutTrade;
    }

    /**
     * 设置 是否外部交易，是、否
     * @param isOutTrade 是否外部交易，是、否
     */
    public void setIsOutTrade(String isOutTrade) {
        this.isOutTrade = isOutTrade == null ? null : isOutTrade.trim();
    }

    /**
     * 取得 关联系统交易编号
     * @return 关联系统交易编号
     */
    public String getfTradeId() {
        return fTradeId;
    }

    /**
     * 设置 关联系统交易编号
     * @param fTradeId 关联系统交易编号
     */
    public void setfTradeId(String fTradeId) {
        this.fTradeId = fTradeId == null ? null : fTradeId.trim();
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
     * 取得 分配方式，平均分配，比例分配
     * @return 分配方式，平均分配，比例分配
     */
    public String getDipType() {
        return dipType;
    }

    /**
     * 设置 分配方式，平均分配，比例分配
     * @param dipType 分配方式，平均分配，比例分配
     */
    public void setDipType(String dipType) {
        this.dipType = dipType == null ? null : dipType.trim();
    }

    public BigDecimal getExercisePrice() {
        return exercisePrice;
    }

    public void setExercisePrice(BigDecimal exercisePrice) {
        this.exercisePrice = exercisePrice;
    }

    public BigDecimal getRedeemRate() {
        return redeemRate;
    }

    public void setRedeemRate(BigDecimal redeemRate) {
        this.redeemRate = redeemRate;
    }
}