package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValCleanOrder extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 结算日期
     */
    private Date settleDate;

    /**
     * 指令编号
     */
    private String orderId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 收付方向
     */
    private String ps;

    /**
     * 本方资金账户
     */
    private String myAcctNo;

    /**
     * 对方资金账户
     */
    private String counterAcctNo;

    /**
     * 结算金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settleAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 业务类型，债券交易、债券存续交易、基金交易、收划款等
     */
    private String busiType;

    /**
     * 指令类型
     */
    private String orderType;

    /**
     * 新增、撤销
     */
    private String dataActionSign;

    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 结算日期
     * @return 结算日期
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 结算日期
     * @param settleDate 结算日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 取得 指令编号
     * @return 指令编号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 指令编号
     * @param orderId 指令编号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 收付方向
     * @return 收付方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 收付方向
     * @param ps 收付方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
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
     * 取得 对方资金账户
     * @return 对方资金账户
     */
    public String getCounterAcctNo() {
        return counterAcctNo;
    }

    /**
     * 设置 对方资金账户
     * @param counterAcctNo 对方资金账户
     */
    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo == null ? null : counterAcctNo.trim();
    }

    /**
     * 取得 结算金额
     * @return 结算金额
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    /**
     * 设置 结算金额
     * @param settleAmt 结算金额
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
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
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 业务类型，债券交易、债券存续交易、基金交易、收划款等
     * @return 业务类型，债券交易、债券存续交易、基金交易、收划款等
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型，债券交易、债券存续交易、基金交易、收划款等
     * @param busiType 业务类型，债券交易、债券存续交易、基金交易、收划款等
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 指令类型
     * @return 指令类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置 指令类型
     * @param orderType 指令类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * 取得 新增、撤销
     * @return 新增、撤销
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、撤销
     * @param dataActionSign 新增、撤销
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置 通道代码
     * @param channelId 通道代码
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }
}