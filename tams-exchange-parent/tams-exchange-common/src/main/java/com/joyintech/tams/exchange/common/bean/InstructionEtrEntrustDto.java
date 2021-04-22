package com.joyintech.tams.exchange.common.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.exchange.common.utils.DoubleSerializerUtils;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InstructionEtrEntrustDto implements Serializable {

    private String entrustId = "";//委托代码
    private String portfolioId = "";//组合代码
    private String portfolioName = "";//组合名称
    private String finprodId = "";//金融产品代码
    private String finprodAbbr = "";//金融产品简称
    private String finprodName = "";//金融产品名称
    private String finprodType2 = "";//金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
    private String acctNo = "";//网点账号
    private String ccy = "";//币种
    private String busiAcctNo = "";//资金账号
    private String acctName = "";// 	 户名
    private String secAcctId = "";//	 证券账户代码
    private String secAcctName = "";//	 证券账户名称
    private String stockHolderNo = "";//	 股东代码
    private String tradeMarket = "";// 交易场所
    private String tradePlat = "";// 交易平台
    private String finprodMarketId = "";// 市场代码
    //@JsonSerialize(using = PsSerializerUtils.class)
    private String ps = "";//	 交易方向
    private String dealMode = "";//	 交易方式
    private String counterId = "";//	 交易对手
    private String counterSeat = "";// 	 对手席位
    private String counterTrader = "";//	 对手方交易员
    private String counterInfo = "";//	 对手方联系方式
    private String counterDealerId = "";//	 对手方交易商代码
    private String appointmentNumber = ""; // 	 约定号
    private String dealNo = "";//	 成交编号
    private String trdMatchId = "";//	 初始交易编号
    private String quoterefId = "";//	 交易所请求号
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date tradeDate = new Date();//委托日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date vdate = new Date();//委托开始日
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date mdate = new Date();//委托结束日
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal entrustAmt = new BigDecimal(0);//委托额度
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal entrustNum = new BigDecimal(0);
    private String entrustStatus = "";//	 委托状态
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal entrustPrice = new BigDecimal(0);//委托单价
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal fullPrice = new BigDecimal(0);//全价
    private String priceMode = "";//	 限价模式
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal entrustLeftAmt = new BigDecimal(0);//已完成数量
    //冻结数量
    private double frozenQuantity;
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal settleSpeed = new BigDecimal(0);//清算速度
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date valueDate = new Date();//	 起息日
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date valueDate2 = new Date();//	 起息日2
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date maturityDate = new Date();//	 到期日
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date maturityDate2 = new Date();//	 到期日2
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal termDays = new BigDecimal(0);//期限天数
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal termDays2 = new BigDecimal(0);//期限天数2
    private String purchType = "";//	 申购类型
    private String saleCode = "";// 配售代码
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal minAmt = new BigDecimal(0);//最低成交金额
    private String tarFinprodId = "";// 目标金融产品代码
    private String tarinprodAbbr = "";//	 目标金融产品简称
    private String tarinprodMarketId = "";//	 目标金融产品市场代码
    private String tradeId = "";//	 交易员编号
    private String traderName = "";//	 交易员姓名
    private String invManagerId = "";//	 投资经理
    private String assetType = "";//	 资产类型
    private String assetType1 = "";//	 资产二级分类
    private String assetType10 = "";// 资产三级分类
    private String assetType11 = "";// 资产四级分类
    @Id
    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getSecAcctName() {
        return secAcctName;
    }

    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    public String getStockHolderNo() {
        return stockHolderNo;
    }

    public void setStockHolderNo(String stockHolderNo) {
        this.stockHolderNo = stockHolderNo;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getCounterSeat() {
        return counterSeat;
    }

    public void setCounterSeat(String counterSeat) {
        this.counterSeat = counterSeat;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    public String getCounterInfo() {
        return counterInfo;
    }

    public void setCounterInfo(String counterInfo) {
        this.counterInfo = counterInfo;
    }

    public String getCounterDealerId() {
        return counterDealerId;
    }

    public void setCounterDealerId(String counterDealerId) {
        this.counterDealerId = counterDealerId;
    }

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public String getDealNo() {
        return dealNo;
    }

    public void setDealNo(String dealNo) {
        this.dealNo = dealNo;
    }

    public String getTrdMatchId() {
        return trdMatchId;
    }

    public void setTrdMatchId(String trdMatchId) {
        this.trdMatchId = trdMatchId;
    }

    public String getQuoterefId() {
        return quoterefId;
    }

    public void setQuoterefId(String quoterefId) {
        this.quoterefId = quoterefId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public BigDecimal getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(BigDecimal entrustNum) {
        this.entrustNum = entrustNum;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public String getPriceMode() {
        return priceMode;
    }

    public void setPriceMode(String priceMode) {
        this.priceMode = priceMode;
    }

    public BigDecimal getEntrustLeftAmt() {
        return entrustLeftAmt;
    }

    public void setEntrustLeftAmt(BigDecimal entrustLeftAmt) {
        this.entrustLeftAmt = entrustLeftAmt;
    }

    public double getFrozenQuantity() {
        return frozenQuantity;
    }

    public void setFrozenQuantity(double frozenQuantity) {
        this.frozenQuantity = frozenQuantity;
    }

    public BigDecimal getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(BigDecimal settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Date getValueDate2() {
        return valueDate2;
    }

    public void setValueDate2(Date valueDate2) {
        this.valueDate2 = valueDate2;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Date getMaturityDate2() {
        return maturityDate2;
    }

    public void setMaturityDate2(Date maturityDate2) {
        this.maturityDate2 = maturityDate2;
    }

    public BigDecimal getTermDays() {
        return termDays;
    }

    public void setTermDays(BigDecimal termDays) {
        this.termDays = termDays;
    }

    public BigDecimal getTermDays2() {
        return termDays2;
    }

    public void setTermDays2(BigDecimal termDays2) {
        this.termDays2 = termDays2;
    }

    public String getPurchType() {
        return purchType;
    }

    public void setPurchType(String purchType) {
        this.purchType = purchType;
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }

    public BigDecimal getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    public String getTarFinprodId() {
        return tarFinprodId;
    }

    public void setTarFinprodId(String tarFinprodId) {
        this.tarFinprodId = tarFinprodId;
    }

    public String getTarinprodAbbr() {
        return tarinprodAbbr;
    }

    public void setTarinprodAbbr(String tarinprodAbbr) {
        this.tarinprodAbbr = tarinprodAbbr;
    }

    public String getTarinprodMarketId() {
        return tarinprodMarketId;
    }

    public void setTarinprodMarketId(String tarinprodMarketId) {
        this.tarinprodMarketId = tarinprodMarketId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getInvManagerId() {
        return invManagerId;
    }

    public void setInvManagerId(String invManagerId) {
        this.invManagerId = invManagerId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getAssetType10() {
        return assetType10;
    }

    public void setAssetType10(String assetType10) {
        this.assetType10 = assetType10;
    }

    public String getAssetType11() {
        return assetType11;
    }

    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }
}
