/*
 * 文件名：MstYieldCurveInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.exchangerateinfo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

/**
 * 汇率行情dto
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ComExchangeQuotationInfoDto
 * @since 1.0
 */
public class ComExchangeQuotationInfoDto extends MstExchargeQuote implements ITableRow{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5143537191208289654L;
    
    /**
     * 选中的数据
     */
    private transient List<ComExchangeQuotationInfoDto> selectedItem;

    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 行情类型
     */
    private String marketType;

    /**
     * 期限
     */
    private String deadLine;

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<ComExchangeQuotationInfoDto> getSelectedItem() {
        return selectedItem;
    }
 
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<ComExchangeQuotationInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    
    /**
     * 设置 operateType
     * @param operateType operateType
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
    
    
    @Override
    public String getExchargeCode() {
        return super.getExchargeCode();
    }
    /**
     * 设置交易日为主键
     * <一句话功能简述>
     * <功能详细描述>
     * @return 返回格式化后的值
     * @see
     * @since 1.0
     */
    public String getTradeDateStr() {
        return DateUtil.format(super.getTradeDate());
    }
    
    @Override
    @NotEmpty(fieldName = "ComExchangeQuotationInfo001.tradeDate", groups = {ValidateGroupEdit.class})
    public Date getTradeDate() {
        return super.getTradeDate();
    }

  
    @Override
    @Length(fieldName = "ComExchangeQuotationInfo002.openPrice", max = 30, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ComExchangeQuotationInfo002.openPrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getOpenPrice() {
        return super.getOpenPrice();
    }
   
    @Override
    @Length(fieldName = "ComExchangeQuotationInfo002.closePrice", max = 30, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ComExchangeQuotationInfo002.closePrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getClosePrice() {
        return super.getClosePrice();
    }
   
    @Override
    @Length(fieldName = "ComExchangeQuotationInfo002.highPrice", max = 30, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ComExchangeQuotationInfo002.highPrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getHighPrice() {
        return super.getHighPrice();
    }
    
    @Override
    @Length(fieldName = "ComExchangeQuotationInfo002.midPrice", max = 30, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ComExchangeQuotationInfo002.midPrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getMidPrice() {
        return super.getMidPrice();
    }
    
    @Override
    @Length(fieldName = "ComExchangeQuotationInfo002.lowPrice", max = 30, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ComExchangeQuotationInfo002.lowPrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getLowPrice() {
        return super.getLowPrice();
    }
}
