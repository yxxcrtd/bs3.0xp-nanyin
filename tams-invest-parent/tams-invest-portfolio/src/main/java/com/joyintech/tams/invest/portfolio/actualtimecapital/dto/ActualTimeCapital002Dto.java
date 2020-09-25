/*
 * 文件名：ActualTimeAssetDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.actualtimecapital.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.portfolio.actualtimecapital.validate.ActualTimeCapital002Validator;

@ValidLogic(targetClass = ActualTimeCapital002Validator.class)
public class ActualTimeCapital002Dto extends BaseEntity implements ITableRow {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7144928695650735181L;
    
    /**
     * 流水号
     */
    private String seqno;
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 资金账号
     */
    private String acctId;
    
    /**
     * 调整日期
     */
    private Date adjDate;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 调整金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal adjAmt;

    /**
     * 操作类型
     */
    private String operateType;
    
    public String getSeqno() {
        return seqno;
    }

    
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    
    public String getPortfolioId() {
        return portfolioId;
    }

    
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    
    public String getAcctId() {
        return acctId;
    }

    
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    
    public Date getAdjDate() {
        return adjDate;
    }

    
    public void setAdjDate(Date adjDate) {
        this.adjDate = adjDate;
    }

    
    public String getCcy() {
        return ccy;
    }

    
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    
    public BigDecimal getAdjAmt() {
        return adjAmt;
    }

    
    public void setAdjAmt(BigDecimal adjAmt) {
        this.adjAmt = adjAmt;
    }


    
    public String getOperateType() {
        return operateType;
    }


    
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
  
    
    
}
