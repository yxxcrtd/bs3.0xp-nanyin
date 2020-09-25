/*
 * 文件名：ValNetAssetQuotationInfoDto001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.valnetassetquotationinfo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

/**
 * 计划类（净值型）资产估值行情信息dto
 * 
 * @author liuwei
 * @version 1.0 2018年8月13日
 * @see ValNetAssetQuotationInfo001Dto
 * @since 1.0
 */
@PageId("ValNetAssetQuotationInfo002")
//@ValidLogic(targetClass = ValNetAssetQuotationInfo001Validator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ValNetAssetQuotationInfo001Dto extends FinProductValuation{

    private static final long serialVersionUID = 5143537191208289654L;    
    /**
     * 资产名称
     */
    private String finProdName;
    /**
     * 导入日
     */
    private String importDate;
    /**
     * 管理人
     */
    private String fundManager;
    /**
     * 操作类型
     */
    private String operateType;    
    /**
     * 界面类型
     */
    private String pageModel;   
    /**
     * 选中的数据列表
     */
    private transient List<ValNetAssetQuotationInfo001Dto> selectedItem;    
    /**
     * 画面元素
     */
    private String elementId;
    
    /**
     * 金融市场代码
     */
    private String finProdMarketId;

    /**
     * 收益类型
     */
    private String profitType;

    
    /**
     * 取得 每日估值，净值、万份收益等
     * @return 每日估值，净值、万份收益等
     */
    @Amount(fieldName = "ValNetAssetQuotationInfo001.netValue", maxIntBit = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getValuation() {
        return super.getValuation();
    }
    
    /**
     * 获取 finProdMarketId
     * @return finProdMarketId.
     */
    public String getFinProdMarketId() {
        return finProdMarketId;
    }

    
    /**
     * 设置 finProdMarketId
     * @param finProdMarketId finProdMarketId
     */
    public void setFinProdMarketId(String finProdMarketId) {
        this.finProdMarketId = finProdMarketId;
    }

    /**
     * 获取 金融产品名称
     * @return 金融产品名称.
     */
    @NotEmpty(fieldName = "ValNetAssetQuotationInfo001.assetName",groups={ValidateGroupEdit.class})
    @Length(fieldName = "ValNetAssetQuotationInfo001.assetName", max = 50)
    public String getFinProdName() {
        return finProdName;
    }
    
    /**
     * 设置 金融产品名称
     * @param finProdName 金融产品名称
     */
    public void setFinProdName(String finProdName) {
        this.finProdName = finProdName;
    }
    
    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    @NotEmpty(fieldName = "ValNetAssetQuotationInfo001.assetCode",groups={ValidateGroupEdit.class})
    @Length(fieldName = "ValNetAssetQuotationInfo001.assetCode", max = 32)
    public String getFinprodId() {
        return super.getFinprodId();
    } 
    
    /**
     * 取得 金融产品类型，债券、理财产品、基金(投管用)
     * @return 金融产品类型，债券、理财产品、基金(投管用)
     */
    public String getFinprodType2() {
        return super.getFinprodType2();
    }
    
    /**
     * 取得 估值日期字符串格式
     * @return 估值日期
     */
    public String getValDateStr() {
        return DateUtil.format(super.getValDate());
    }
    /**
     * 取得 估值日期
     * @return 估值日期
     */
    @NotEmpty(fieldName = "ValNetAssetQuotationInfo001.valDate",groups={ValidateGroupEdit.class})
    public Date getValDate() {
        return super.getValDate();
    }

    /**
     * 获取 导入日期
     * @return 导入日期.
     */
    public String getImportDate() {
        return importDate;
    }
   
    /**
     * 设置 导入日期
     * @param importDate 导入日期
     */
    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }
    
    /**
     * 获取 操作类型
     * @return 操作类型.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 操作类型
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
   
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }
    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    
    /**
     * 获取  选中列表
     * @return 选中列表.
     */
    public List<ValNetAssetQuotationInfo001Dto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 选中列表
     * @param selectedItem 选中列表
     */
    public void setSelectedItem(List<ValNetAssetQuotationInfo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }
    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    
    /**
     * 获取 管理人名称
     * @return 管理人名称.
     */
    public String getFundManager() {
        return fundManager;
    }
   
    /**
     * 设置 管理人名称
     * @param fundManager 管理人
     */
    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }
}
    