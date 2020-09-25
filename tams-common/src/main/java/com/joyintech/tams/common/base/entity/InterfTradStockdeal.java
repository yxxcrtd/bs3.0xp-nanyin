package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfTradStockdeal extends BaseEntity {
    /**
     * 成交编号

     */
    private String tradeid;

    /**
     * 资产代码
,配售代码

     */
    private String finprodMarketId;

    /**
     * null
     */
    private String saleCode;

    /**
     * "04 大宗交易专场
02 大宗交易
01 竞价交易"

     */
    private String dealMode;

    /**
     * "T02 卖出
T01 买入
T48 新股中签
T47 增发（配售）
T46 配股"

     */
    private String ps;

    /**
     * 证券托管户

     */
    private String secManageAcctId;

    /**
     * 币种

     */
    private String ccy;

    /**
     * 交易日期

     */
    private String tradeDate;

    /**
     * 交割日期

     */
    private String settleDate;

    /**
     * 交易价格

     */
    private String unitCprice;

    /**
     * 交易数量

     */
    private String shareAmt;

    /**
     * 交易金额

     */
    private String tradeAmt;

    /**
     * 成交金额

     */
    private String fpriceAmt;

    /**
     * 佣金

     */
    private String commissionAmt;

    /**
     * 印花税

     */
    private String stampDutyAmt;

    /**
     * 过户费

     */
    private String transferFeesAmt;

    /**
     * 证管费

     */
    private String certificateManagementFeeAmt;

    /**
     * 经手费

     */
    private String exchangeFeesAmt;

    /**
     * 风险金

     */
    private String riskAmt;

    /**
     * 结算费

     */
    private String settleFreeAmt;

    /**
     * 费用1

     */
    private String feeAmt1;

    /**
     * 费用2

     */
    private String feeAmt2;

    /**
     * 费用3

     */
    private String feeAmt3;

    /**
     * 席位号

     */
    private String seatNo;

    /**
     * 券商代码

     */
    private String brokerageNo;

    /**
     * 取得 成交编号

     * @return 成交编号

     */
    public String getTradeid() {
        return tradeid;
    }

    /**
     * 设置 成交编号

     * @param tradeid 成交编号

     */
    public void setTradeid(String tradeid) {
        this.tradeid = tradeid == null ? null : tradeid.trim();
    }

    /**
     * 取得 资产代码
,配售代码

     * @return 资产代码
,配售代码

     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 资产代码
,配售代码

     * @param finprodMarketId 资产代码
,配售代码

     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId == null ? null : finprodMarketId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSaleCode() {
        return saleCode;
    }

    /**
     * 设置 null
     * @param saleCode null
     */
    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode == null ? null : saleCode.trim();
    }

    /**
     * 取得 "04 大宗交易专场
02 大宗交易
01 竞价交易"

     * @return "04 大宗交易专场
02 大宗交易
01 竞价交易"

     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 设置 "04 大宗交易专场
02 大宗交易
01 竞价交易"

     * @param dealMode "04 大宗交易专场
02 大宗交易
01 竞价交易"

     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode == null ? null : dealMode.trim();
    }

    /**
     * 取得 "T02 卖出
T01 买入
T48 新股中签
T47 增发（配售）
T46 配股"

     * @return "T02 卖出
T01 买入
T48 新股中签
T47 增发（配售）
T46 配股"

     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 "T02 卖出
T01 买入
T48 新股中签
T47 增发（配售）
T46 配股"

     * @param ps "T02 卖出
T01 买入
T48 新股中签
T47 增发（配售）
T46 配股"

     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 证券托管户

     * @return 证券托管户

     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 证券托管户

     * @param secManageAcctId 证券托管户

     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId == null ? null : secManageAcctId.trim();
    }

    /**
     * 取得 币种

     * @return 币种

     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种

     * @param ccy 币种

     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 交易日期

     * @return 交易日期

     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日期

     * @param tradeDate 交易日期

     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    /**
     * 取得 交割日期

     * @return 交割日期

     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 交割日期

     * @param settleDate 交割日期

     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate == null ? null : settleDate.trim();
    }

    /**
     * 取得 交易价格

     * @return 交易价格

     */
    public String getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 交易价格

     * @param unitCprice 交易价格

     */
    public void setUnitCprice(String unitCprice) {
        this.unitCprice = unitCprice == null ? null : unitCprice.trim();
    }

    /**
     * 取得 交易数量

     * @return 交易数量

     */
    public String getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 交易数量

     * @param shareAmt 交易数量

     */
    public void setShareAmt(String shareAmt) {
        this.shareAmt = shareAmt == null ? null : shareAmt.trim();
    }

    /**
     * 取得 交易金额

     * @return 交易金额

     */
    public String getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 交易金额

     * @param tradeAmt 交易金额

     */
    public void setTradeAmt(String tradeAmt) {
        this.tradeAmt = tradeAmt == null ? null : tradeAmt.trim();
    }

    /**
     * 取得 成交金额

     * @return 成交金额

     */
    public String getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 成交金额

     * @param fpriceAmt 成交金额

     */
    public void setFpriceAmt(String fpriceAmt) {
        this.fpriceAmt = fpriceAmt == null ? null : fpriceAmt.trim();
    }

    /**
     * 取得 佣金

     * @return 佣金

     */
    public String getCommissionAmt() {
        return commissionAmt;
    }

    /**
     * 设置 佣金

     * @param commissionAmt 佣金

     */
    public void setCommissionAmt(String commissionAmt) {
        this.commissionAmt = commissionAmt == null ? null : commissionAmt.trim();
    }

    /**
     * 取得 印花税

     * @return 印花税

     */
    public String getStampDutyAmt() {
        return stampDutyAmt;
    }

    /**
     * 设置 印花税

     * @param stampDutyAmt 印花税

     */
    public void setStampDutyAmt(String stampDutyAmt) {
        this.stampDutyAmt = stampDutyAmt == null ? null : stampDutyAmt.trim();
    }

    /**
     * 取得 过户费

     * @return 过户费

     */
    public String getTransferFeesAmt() {
        return transferFeesAmt;
    }

    /**
     * 设置 过户费

     * @param transferFeesAmt 过户费

     */
    public void setTransferFeesAmt(String transferFeesAmt) {
        this.transferFeesAmt = transferFeesAmt == null ? null : transferFeesAmt.trim();
    }

    /**
     * 取得 证管费

     * @return 证管费

     */
    public String getCertificateManagementFeeAmt() {
        return certificateManagementFeeAmt;
    }

    /**
     * 设置 证管费

     * @param certificateManagementFeeAmt 证管费

     */
    public void setCertificateManagementFeeAmt(String certificateManagementFeeAmt) {
        this.certificateManagementFeeAmt = certificateManagementFeeAmt == null ? null : certificateManagementFeeAmt.trim();
    }

    /**
     * 取得 经手费

     * @return 经手费

     */
    public String getExchangeFeesAmt() {
        return exchangeFeesAmt;
    }

    /**
     * 设置 经手费

     * @param exchangeFeesAmt 经手费

     */
    public void setExchangeFeesAmt(String exchangeFeesAmt) {
        this.exchangeFeesAmt = exchangeFeesAmt == null ? null : exchangeFeesAmt.trim();
    }

    /**
     * 取得 风险金

     * @return 风险金

     */
    public String getRiskAmt() {
        return riskAmt;
    }

    /**
     * 设置 风险金

     * @param riskAmt 风险金

     */
    public void setRiskAmt(String riskAmt) {
        this.riskAmt = riskAmt == null ? null : riskAmt.trim();
    }

    /**
     * 取得 结算费

     * @return 结算费

     */
    public String getSettleFreeAmt() {
        return settleFreeAmt;
    }

    /**
     * 设置 结算费

     * @param settleFreeAmt 结算费

     */
    public void setSettleFreeAmt(String settleFreeAmt) {
        this.settleFreeAmt = settleFreeAmt == null ? null : settleFreeAmt.trim();
    }

    /**
     * 取得 费用1

     * @return 费用1

     */
    public String getFeeAmt1() {
        return feeAmt1;
    }

    /**
     * 设置 费用1

     * @param feeAmt1 费用1

     */
    public void setFeeAmt1(String feeAmt1) {
        this.feeAmt1 = feeAmt1 == null ? null : feeAmt1.trim();
    }

    /**
     * 取得 费用2

     * @return 费用2

     */
    public String getFeeAmt2() {
        return feeAmt2;
    }

    /**
     * 设置 费用2

     * @param feeAmt2 费用2

     */
    public void setFeeAmt2(String feeAmt2) {
        this.feeAmt2 = feeAmt2 == null ? null : feeAmt2.trim();
    }

    /**
     * 取得 费用3

     * @return 费用3

     */
    public String getFeeAmt3() {
        return feeAmt3;
    }

    /**
     * 设置 费用3

     * @param feeAmt3 费用3

     */
    public void setFeeAmt3(String feeAmt3) {
        this.feeAmt3 = feeAmt3 == null ? null : feeAmt3.trim();
    }

    /**
     * 取得 席位号

     * @return 席位号

     */
    public String getSeatNo() {
        return seatNo;
    }

    /**
     * 设置 席位号

     * @param seatNo 席位号

     */
    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo == null ? null : seatNo.trim();
    }

    /**
     * 取得 券商代码

     * @return 券商代码

     */
    public String getBrokerageNo() {
        return brokerageNo;
    }

    /**
     * 设置 券商代码

     * @param brokerageNo 券商代码

     */
    public void setBrokerageNo(String brokerageNo) {
        this.brokerageNo = brokerageNo == null ? null : brokerageNo.trim();
    }
}