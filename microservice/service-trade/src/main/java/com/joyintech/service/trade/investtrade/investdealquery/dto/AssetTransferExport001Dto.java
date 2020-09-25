/*
 * 文件名：ListImportDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年4月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.investtrade.investdealquery.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉 
 * @author wangyang1
 * @version 1.0 2019年9月19日
 * @see AssetTransferExport001Dto
 * @since 1.0
 */ 
public class AssetTransferExport001Dto extends BaseEntity {
 
  
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**转出资产理财产品名称*/ 
    private String inProdName;         
   /**转入资产理财产品名称*/ 
    private String outprodName;
    /**资产一级类型*/  
    private String trdType1;
    /**资产二级类型*/ 
    private String trdType2;
   /**转移资产名称*/ 
    private String assetName;
   /**转移资产代码*/ 
    private String assetId;
   /**转移日期*/ 
    private String tradeDate;
    /**转移日期*/ 
    private String settleDate;
    /**转移净价*/ 
    private String unitCprice;
    /**转移全价*/ 
    private String unitFprice;
    /**转移面额（万元）*/ 
    private String tradeAmt;
    /**转移金额（元）*/ 
    private String fpriceAmt;
    /**利率/到期收益率 */ 
    private String yield;
 
    /**
     * 获取 trdType1
     * @return trdType1.
     */
    public String getTrdType1() {
        return trdType1;
    }
    
    
    /**
     * 设置 trdType1
     * @param trdType1 trdType1
     */
    public void setTrdType1(String trdType1) {
        this.trdType1 = trdType1;
    }
    
    
    /**
     * 获取 trdType2
     * @return trdType2.
     */
    public String getTrdType2() {
        return trdType2;
    }
    
    
    /**
     * 设置 trdType2
     * @param trdType2 trdType2
     */
    public void setTrdType2(String trdType2) {
        this.trdType2 = trdType2;
    }

    /**
     * 获取 inProdName
     * @return inProdName.
     */
    public String getInProdName() {
        return inProdName;
    }
    
    /**
     * 设置 inProdName
     * @param inProdName inProdName
     */
    public void setInProdName(String inProdName) {
        this.inProdName = inProdName;
    }
    
    /**
     * 获取 outprodName
     * @return outprodName.
     */
    public String getOutprodName() {
        return outprodName;
    }
    
    /**
     * 设置 outprodName
     * @param outprodName outprodName
     */
    public void setOutprodName(String outprodName) {
        this.outprodName = outprodName;
    }
    
    /**
     * 获取 assetName
     * @return assetName.
     */
    public String getAssetName() {
        return assetName;
    }
    
    /**
     * 设置 assetName
     * @param assetName assetName
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    
    /**
     * 获取 assetId
     * @return assetId.
     */
    public String getAssetId() {
        return assetId;
    }
    
    /**
     * 设置 assetId
     * @param assetId assetId
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }
    
    /**
     * 获取 tradeDate
     * @return tradeDate.
     */
    public String getTradeDate() {
        return tradeDate;
    }
    
    /**
     * 设置 tradeDate
     * @param tradeDate tradeDate
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    
    /**
     * 获取 unitCprice
     * @return unitCprice.
     */
    public String getUnitCprice() {
        return unitCprice;
    }
    
    /**
     * 设置 unitCprice
     * @param unitCprice unitCprice
     */
    public void setUnitCprice(String unitCprice) {
        this.unitCprice = unitCprice;
    }
    
    /**
     * 获取 unitFprice
     * @return unitFprice.
     */
    public String getUnitFprice() {
        return unitFprice;
    }
    
    /**
     * 设置 unitFprice
     * @param unitFprice unitFprice
     */
    public void setUnitFprice(String unitFprice) {
        this.unitFprice = unitFprice;
    }
    
    /**
     * 获取 tradeAmt
     * @return tradeAmt.
     */
    public String getTradeAmt() {
        return tradeAmt;
    }
    
    /**
     * 设置 tradeAmt
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(String tradeAmt) {
        this.tradeAmt = tradeAmt;
    }
    
    /**
     * 获取 fpriceAmt
     * @return fpriceAmt.
     */
    public String getFpriceAmt() {
        return fpriceAmt;
    }
    
    /**
     * 设置 fpriceAmt
     * @param fpriceAmt fpriceAmt
     */
    public void setFpriceAmt(String fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }
    
    /**
     * 获取 yield
     * @return yield.
     */
    public String getYield() {
        return yield;
    }
    
    /**
     * 设置 yield
     * @param yield yield
     */
    public void setYield(String yield) {
        this.yield = yield;
    }
 
    /**
     * 获取 settleDate
     * @return settleDate.
     */
    public String getSettleDate() {
        return settleDate;
    }
  
    /**
     * 设置 settleDate
     * @param settleDate settleDate
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    } 
    
    
}
