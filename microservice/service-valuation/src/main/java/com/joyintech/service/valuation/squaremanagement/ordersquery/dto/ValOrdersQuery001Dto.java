/*
 * 文件名：ValOrdersQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.squaremanagement.ordersquery.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 
 * 结算指令查询Dto
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValOrdersQuery001Dto
 * @since 1.0
 */
public class ValOrdersQuery001Dto extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 指令编号
     */
    private String orderId;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 指令类型
     */
    private String orderType;

    /**
     * 指令序号
     */
    private String orderNum;
    
    /**
     * 结算日期
     */
    private String settleDate;
    
    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 本方资金账户
     */
    private String myAcctNo;
    
    /**
     * 收付方向
     */
    private String ps;
    
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
     * 结算状态
     */
    private String settleStatus;

    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 录入日
     */
    private String booksetDate;
    
    /**
     * 入账日
     */
    private String bookDate;
    

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
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return this.busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }


    /**
     * 取得 结算日期
     * @return 结算日期
     */
    public String getSettleDate() {
        return DateUtil.formatDateAddSplit(settleDate);
    }

    /**
     * 设置 结算日期
     * @param settleDate 结算日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }


    /**
     * 取得 收付方向
     * @return 收付方向
     */
    public String getPs() {
        return this.ps;
    }

    /**
     * 设置 收付方向
     * @param ps 收付方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }


    /**
     * 获取 账套代码
     * @return booksetId 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 录入日
     * @return booksetDate 录入日
     */
    public String getBooksetDate() {
        return DateUtil.formatDateAddSplit(booksetDate);
    }

    /**
     * 设置 录入日
     * @param booksetDate 录入日
     */
    public void setBooksetDate(String booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 获取 入账日
     * @return bookDate 入账日
     */
    public String getBookDate() {
        return DateUtil.formatDateAddSplit(bookDate);
    }

    /**
     * 设置 入账日
     * @param bookDate 入账日
     */
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 获取 指令编号
     * @return orderId 指令编号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 指令编号
     * @param orderId 指令编号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取 指令类型
     * @return orderType 指令类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置 指令类型
     * @param orderType 指令类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取 指令序号
     * @return orderNum 指令序号
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * 设置 指令序号
     * @param orderNum 指令序号
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取 本方资金账户
     * @return myAcctNo 本方资金账户
     */
    public String getMyAcctNo() {
        return myAcctNo;
    }

    /**
     * 设置 本方资金账户
     * @param myAcctNo 本方资金账户
     */
    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo;
    }

    /**
     * 获取 对方资金账户
     * @return counterAcctNo 对方资金账户
     */
    public String getCounterAcctNo() {
        return counterAcctNo;
    }

    /**
     * 设置 对方资金账户
     * @param counterAcctNo 对方资金账户
     */
    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }

    /**
     * 获取 结算金额
     * @return settleAmt 结算金额
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
     * 获取 结算状态
     * @return settleStatus 结算状态
     */
    public String getSettleStatus() {
        return settleStatus;
    }

    /**
     * 设置 结算状态
     * @param settleStatus 结算状态
     */
    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }
}     				
      			
      