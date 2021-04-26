package com.joyintech.tams.valuation.valapi.dto;

import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName BokStlOrderBuild001Dto
 * @Description: 结算指令入账dto
 * @Author yanya
 * @Date 2021/2/26
 * @Version V1.0
 **/
public class BokStlOrderBuild001Dto implements Serializable {

    private static final long serialVersionUID = 1985317993328438921L;

    /**产品代码**/
    @ValInterfApiField
    private String prodId;
    @ValInterfApiField(fieldVal="F16,F22,F18")
    /**产品类型**/
    private String prodFinprodType;
    /**子产品起息日**/
    @ValInterfApiField(type="02",appointField="prodFinprodType",appointVal="F22")
    private Date prodVdate;
    /**指令编号**/
    @ValInterfApiField
    private String orderId;
    /**业务类型**/
    private String busiType;
    /**指令类型**/
    @ValInterfApiField
    private String orderType;
    /**指令序号**/
    private Long orderNum;
    /**结算日期**/
    @ValInterfApiField
    private Date settleDate;
    /**交易编号**/
    private String tradeId;
    /**本方资金账户**/
    @ValInterfApiField
    private String myAcctNo;
    /**收付方向**/
    @ValInterfApiField(dictCode="PS_PAYMENT")
    private String ps;
    /**对方资金账户**/
    private String counterAcctNo;
    /**结算金额**/
    private BigDecimal settleAmt;
    /**币种**/
    @ValInterfApiField(dictCode="CCY")
    private String ccy;
    /**结算状态**/
    private String settleStatus;
    /**指令状态**/
    @ValInterfApiField(dictCode="TRADE_STATUS")
    private String orderStatus;
    /**原指令编号**/
    private String pOrderId;
    /**关联系统指令编号**/
    private String fOrderId;
    /**通道代码**/
    private String channelId;

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

    public Date getProdVdate() {
        return prodVdate;
    }

    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getMyAcctNo() {
        return myAcctNo;
    }

    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getCounterAcctNo() {
        return counterAcctNo;
    }

    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }

    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getpOrderId() {
        return pOrderId;
    }

    public void setpOrderId(String pOrderId) {
        this.pOrderId = pOrderId;
    }

    public String getfOrderId() {
        return fOrderId;
    }

    public void setfOrderId(String fOrderId) {
        this.fOrderId = fOrderId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "BokStlOrderBuild001Dto{" +
                "prodId='" + prodId + '\'' +
                ", prodFinprodType='" + prodFinprodType + '\'' +
                ", prodVdate=" + prodVdate +
                ", orderId='" + orderId + '\'' +
                ", busiType='" + busiType + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderNum=" + orderNum +
                ", settleDate=" + settleDate +
                ", tradeId='" + tradeId + '\'' +
                ", myAcctNo='" + myAcctNo + '\'' +
                ", ps='" + ps + '\'' +
                ", counterAcctNo='" + counterAcctNo + '\'' +
                ", settleAmt=" + settleAmt +
                ", ccy='" + ccy + '\'' +
                ", settleStatus='" + settleStatus + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", pOrderId='" + pOrderId + '\'' +
                ", fOrderId='" + fOrderId + '\'' +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
