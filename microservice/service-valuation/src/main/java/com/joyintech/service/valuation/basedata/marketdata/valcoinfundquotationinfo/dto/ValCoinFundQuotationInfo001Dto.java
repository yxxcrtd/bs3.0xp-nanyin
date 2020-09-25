/*
 * 文件名：ValCoinFundQuotationInfo001Dto.java

 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：范双虎
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.valcoinfundquotationinfo.dto;
import java.util.Date;

import com.joyintech.tams.common.base.entity.FinProductValuation;

/**
 * 货币型基金行情实体类
 * @author 范双虎
 * @version 1.0 2018年8月3日
 * @see ValCoinFundQuotationInfo001Dto
 * @since 1.0
 */
public class ValCoinFundQuotationInfo001Dto extends FinProductValuation {
    /**
     * 序列化值
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3103157969451025142L;
    /**
     * 市场机构代码
     */
    private String finprodMarketId;
    /**
     * 估值日
     */
    private String valuationDay;
    
    /**
     * 基金管理人
     */
    private String fundManager;
    /**
     * 导入日
     */
    private Date importDay;
    
    /**
     * 
     */
    private String type;
    
    
    /**
     * 获取 type
     * @return type.
     */
    public String getType() {
        return type;
    }

    
    /**
     * 设置 type
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }
    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }
 
    /**
     * 获取 valuationDay
     * @return valuationDay.
     */
    public String getValuationDay() {
        return valuationDay;
    }
    
    /**
     * 设置 valuationDay
     * @param valuationDay valuationDay
     */
    public void setValuationDay(String valuationDay) {
        this.valuationDay = valuationDay;
    }
    
    
    /**
     * 获取 fundManager
     * @return fundManager.
     */
    public String getFundManager() {
        return fundManager;
    }
    
    /**
     * 设置 fundManager
     * @param fundManager fundManager
     */
    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }
    
    /**
     * 获取 importDay
     * @return importDay.
     */
    public Date getImportDay() {
        return importDay;
    }
    
    /**
     * 设置 importDay
     * @param importDay importDay
     */
    public void setImportDay(Date importDay) {
        this.importDay = importDay;
    }
}
