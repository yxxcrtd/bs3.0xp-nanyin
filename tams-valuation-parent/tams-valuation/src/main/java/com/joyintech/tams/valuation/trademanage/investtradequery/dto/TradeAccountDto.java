/*
 * 文件名：TradeAccountDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 交易账户收划款dto
 * 
 * @author Administrator
 * @version 1.0 2018年8月13日
 * @see TradeAccountDto
 * @since 1.0
 */
public class TradeAccountDto extends InvestTradeDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 收划款类型
     */
    private String type;

    /**
     * 收划款类型Code
     */
    private String typeCode;

    /**
     * 收划款日期
     */
    private String settleDat;

    /**
     * 收划款金额
     */
    private BigDecimal settleAmt;

    /**
     * 收款账户户名
     */
    private String myAcctName;

    /**
     * 收款账户账号
     */
    private String myAcctNo;

    /**
     * 付款账户户名
     */
    private String counterAcctName;

    /**
     * 付款账户账号
     */
    private String counterAcctNo;

    /**
     * 收划款状态
     */
    private String status;

    /**
     * 获取 type
     * 
     * @return type.
     */
    public String getTypeFt() {
        if (type.equals("1001") || type.equals("1002")) {
            return SysDataUtil.getNameFromDataDict(DictCode.指令类型, this.type);
        }
        return type;
    }

    
    
    /**
     * 获取 type
     * @return type.
     */
    public String getType() {
    	return SysDataUtil.getNameFromDataDict(DictCode.指令类型, this.type);
    }


    /**
     * 设置 type
     * 
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 typeCode
     * 
     * @return typeCode.
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置 typeCode
     * 
     * @param typeCode typeCode
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取 settleDat
     * 
     * @return settleDat.
     */
    public String getSettleDat() {
        return settleDat;
    }

    /**
     * 设置 settleDat
     * 
     * @param settleDat settleDat
     */
    public void setSettleDat(String settleDat) {
        this.settleDat = settleDat;
    }

    /**
     * 获取 settleAmt
     * 
     * @return settleAmt.
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    /**
     * 设置 settleAmt
     * 
     * @param settleAmt settleAmt
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    /**
     * 获取 myAcctName
     * 
     * @return myAcctName.
     */
    public String getMyAcctName() {
        return myAcctName;
    }

    /**
     * 设置 myAcctName
     * 
     * @param myAcctName myAcctName
     */
    public void setMyAcctName(String myAcctName) {
        this.myAcctName = myAcctName;
    }

    /**
     * 获取 myAcctNo
     * 
     * @return myAcctNo.
     */
    public String getMyAcctNo() {
        return myAcctNo;
    }

    /**
     * 设置 myAcctNo
     * 
     * @param myAcctNo myAcctNo
     */
    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo;
    }

    /**
     * 获取 counterAcctName
     * 
     * @return counterAcctName.
     */
    public String getCounterAcctName() {
        return counterAcctName;
    }

    /**
     * 设置 counterAcctName
     * 
     * @param counterAcctName counterAcctName
     */
    public void setCounterAcctName(String counterAcctName) {
        this.counterAcctName = counterAcctName;
    }

    /**
     * 获取 counterAcctNo
     * 
     * @return counterAcctNo.
     */
    public String getCounterAcctNo() {
        return counterAcctNo;
    }

    /**
     * 设置 counterAcctNo
     * 
     * @param counterAcctNo counterAcctNo
     */
    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }

    /**
     * 获取 status
     * 
     * @return status.
     */
    public String getStatusFt() {
         return SysDataUtil.getNameFromDataDict(DictCode.交易状态, this.status);
    }

    
    
    
    /**
     * 获取 status
     * @return status.
     */
    public String getStatus() {
    	return SysDataUtil.getNameFromDataDict(DictCode.交易状态, this.status);
    }



    /**
     * 设置 status
     * 
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
