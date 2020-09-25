/*
 * 文件名：RiskCreditListCommonDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2020年4月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.risk.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditLinesDeal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2020年4月8日
 * @see RiskCreditListCommonDto
 * @since 1.0
 */
public class RiskCreditListCommonDto {
    
    private TrdProductDeal dealModel;
    private List<CreditLinesDealCommon> creditLinesDealCommonlist;
    private List<CreditLines> creLineslist;
    private CreditLinesDeal creditLinesDeal;
    private BigDecimal todocreditAmt;
    private Boolean isEnoughCreditflag;
    
    private String status;
     
    /**
     * 获取 status
     * @return status.
     */
    public String getStatus() {
        return status;
    }

    
    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取 dealModel
     * @return dealModel.
     */
    public TrdProductDeal getDealModel() {
        return dealModel;
    }
    
    /**
     * 设置 dealModel
     * @param dealModel dealModel
     */
    public void setDealModel(TrdProductDeal dealModel) {
        this.dealModel = dealModel;
    }
    
    /**
     * 获取 creditLinesDealCommonlist
     * @return creditLinesDealCommonlist.
     */
    public List<CreditLinesDealCommon> getCreditLinesDealCommonlist() {
        return creditLinesDealCommonlist;
    }
    
    /**
     * 设置 creditLinesDealCommonlist
     * @param creditLinesDealCommonlist creditLinesDealCommonlist
     */
    public void setCreditLinesDealCommonlist(List<CreditLinesDealCommon> creditLinesDealCommonlist) {
        this.creditLinesDealCommonlist = creditLinesDealCommonlist;
    }
    
    /**
     * 获取 creLineslist
     * @return creLineslist.
     */
    public List<CreditLines> getCreLineslist() {
        return creLineslist;
    }
    
    /**
     * 设置 creLineslist
     * @param creLineslist creLineslist
     */
    public void setCreLineslist(List<CreditLines> creLineslist) {
        this.creLineslist = creLineslist;
    }
    
    /**
     * 获取 creditLinesDeal
     * @return creditLinesDeal.
     */
    public CreditLinesDeal getCreditLinesDeal() {
        return creditLinesDeal;
    }
    
    /**
     * 设置 creditLinesDeal
     * @param creditLinesDeal creditLinesDeal
     */
    public void setCreditLinesDeal(CreditLinesDeal creditLinesDeal) {
        this.creditLinesDeal = creditLinesDeal;
    }
    
    /**
     * 获取 todocreditAmt
     * @return todocreditAmt.
     */
    public BigDecimal getTodocreditAmt() {
        return todocreditAmt;
    }
    
    /**
     * 设置 todocreditAmt
     * @param todocreditAmt todocreditAmt
     */
    public void setTodocreditAmt(BigDecimal todocreditAmt) {
        this.todocreditAmt = todocreditAmt;
    }
    
    /**
     * 获取 isEnoughCreditflag
     * @return isEnoughCreditflag.
     */
    public Boolean getIsEnoughCreditflag() {
        return isEnoughCreditflag;
    }
    
    /**
     * 设置 isEnoughCreditflag
     * @param isEnoughCreditflag isEnoughCreditflag
     */
    public void setIsEnoughCreditflag(Boolean isEnoughCreditflag) {
        this.isEnoughCreditflag = isEnoughCreditflag;
    }
    
}
