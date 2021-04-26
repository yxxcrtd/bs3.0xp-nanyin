package com.joyintech.tams.valuation.valapi.dto;


import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName BokTradeBuild001Dto
 * @Description: 交易入账信息dto
 * @Author yanya
 * @Date 2021/2/26
 * @Version V1.0
 **/
public class BokTradeBuild001Dto implements Serializable {

    private static final long serialVersionUID = -7092813005997935894L;
    /**产品代码**/
    @ValInterfApiField
    private String prodId;
    /**产品类型**/
    @ValInterfApiField(fieldVal="F16,F22,F18")
    private String prodFinprodType;
    /**分支序号**/
    private Long branch;
    /**交易编号**/
    @ValInterfApiField
    private String tradeId;
    /**金融产品代码**/
    @ValInterfApiField
    private String finprodId;
    /**金融产品类型**/
    @ValInterfApiField
    private String finprodType;
    /**业务类型**/
    @ValInterfApiField
    private String busiType;
    /**全价总额**/
    private BigDecimal fpriceAmt;
    /**通道代码**/
    private String chlId;
    /**投资目的**/
    @ValInterfApiField(type="02",appointField="busiType",appointVal="02",dictCode="INV_AIM")
    private String invAim;
    /**交易方向**/
    @ValInterfApiField
    private String ps;
    /**交易日**/
    @ValInterfApiField
    private Date tradeDate;
    /**交割日**/
    @ValInterfApiField
    private Date settleDate;
    /**币种**/
    @ValInterfApiField
    private String ccy;
    /**交易金额**/
    @ValInterfApiField
    private BigDecimal tradeAmt;
    /**交易场所**/
    private String tradeMarket;
    /**交易市场**/
    private String tradeMarketMode;
    /**交易状态**/
    @ValInterfApiField
    private String tradeStatus;
    /**收付方向**/
    private String psPayment;
    /**原交易编号**/
    private String pTradeId;
    /**指令结算金额**/
    private BigDecimal stlSettleAmt;
    /**指令结算日期**/
    private Date stlSettleDate;
    /**指令本方资金账户**/
    private String stlMyAcctNo;
    /**指令对方资金账户**/
    private String stlCounterAcctNo;
    /**实物金融产品代码**/
    private String barterFinprodId;
    /**实物金融产品类型**/
    private String barterFinprodType;
    /**实物分支序号**/
    private Long barterBranch;
    /**转换资产代码**/
    private String rFinprodId;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdFinprodType() {
        return prodFinprodType;
    }

    public void setProdFinprodType(String prodFinprodType) {
        this.prodFinprodType = prodFinprodType;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public String getChlId() {
        return chlId;
    }

    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradeMarketMode() {
        return tradeMarketMode;
    }

    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getPsPayment() {
        return psPayment;
    }

    public void setPsPayment(String psPayment) {
        this.psPayment = psPayment;
    }

    public String getpTradeId() {
        return pTradeId;
    }

    public void setpTradeId(String pTradeId) {
        this.pTradeId = pTradeId;
    }

    public BigDecimal getStlSettleAmt() {
        return stlSettleAmt;
    }

    public void setStlSettleAmt(BigDecimal stlSettleAmt) {
        this.stlSettleAmt = stlSettleAmt;
    }

    public Date getStlSettleDate() {
        return stlSettleDate;
    }

    public void setStlSettleDate(Date stlSettleDate) {
        this.stlSettleDate = stlSettleDate;
    }

    public String getStlMyAcctNo() {
        return stlMyAcctNo;
    }

    public void setStlMyAcctNo(String stlMyAcctNo) {
        this.stlMyAcctNo = stlMyAcctNo;
    }

    public String getStlCounterAcctNo() {
        return stlCounterAcctNo;
    }

    public void setStlCounterAcctNo(String stlCounterAcctNo) {
        this.stlCounterAcctNo = stlCounterAcctNo;
    }

    public String getBarterFinprodId() {
        return barterFinprodId;
    }

    public void setBarterFinprodId(String barterFinprodId) {
        this.barterFinprodId = barterFinprodId;
    }

    public String getBarterFinprodType() {
        return barterFinprodType;
    }

    public void setBarterFinprodType(String barterFinprodType) {
        this.barterFinprodType = barterFinprodType;
    }

    public Long getBarterBranch() {
        return barterBranch;
    }

    public void setBarterBranch(Long barterBranch) {
        this.barterBranch = barterBranch;
    }

    public String getrFinprodId() {
        return rFinprodId;
    }

    public void setrFinprodId(String rFinprodId) {
        this.rFinprodId = rFinprodId;
    }

    @Override
    public String toString() {
        return "BokTradeBuild001Dto{" +
                "prodId='" + prodId + '\'' +
                ", prodFinprodType='" + prodFinprodType + '\'' +
                ", branch=" + branch +
                ", tradeId='" + tradeId + '\'' +
                ", finprodId='" + finprodId + '\'' +
                ", finprodType='" + finprodType + '\'' +
                ", busiType='" + busiType + '\'' +
                ", fpriceAmt=" + fpriceAmt +
                ", chlId='" + chlId + '\'' +
                ", invAim='" + invAim + '\'' +
                ", ps='" + ps + '\'' +
                ", tradeDate=" + tradeDate +
                ", settleDate=" + settleDate +
                ", ccy='" + ccy + '\'' +
                ", tradeAmt=" + tradeAmt +
                ", tradeMarket='" + tradeMarket + '\'' +
                ", tradeMarketMode='" + tradeMarketMode + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", psPayment='" + psPayment + '\'' +
                ", pTradeId='" + pTradeId + '\'' +
                ", stlSettleAmt=" + stlSettleAmt +
                ", stlSettleDate=" + stlSettleDate +
                ", stlMyAcctNo='" + stlMyAcctNo + '\'' +
                ", stlCounterAcctNo='" + stlCounterAcctNo + '\'' +
                ", barterFinprodId='" + barterFinprodId + '\'' +
                ", barterFinprodType='" + barterFinprodType + '\'' +
                ", barterBranch=" + barterBranch +
                ", rFinprodId='" + rFinprodId + '\'' +
                '}';
    }
}
