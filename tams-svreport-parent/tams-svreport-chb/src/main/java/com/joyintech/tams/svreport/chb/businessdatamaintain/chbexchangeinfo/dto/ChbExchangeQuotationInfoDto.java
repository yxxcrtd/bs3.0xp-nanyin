/*
 * 文件名：MstYieldCurveInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 汇率行情dto
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ChbExchangeQuotationInfoDto
 * @since 1.0
 */
@PageId("ChbExchangeQuotationInfo002")
public class ChbExchangeQuotationInfoDto extends BaseEntity implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5143537191208289654L;

    /**
     * 行情代码，一般为货币对
     */
    private String exchargeCode;


    /**
     * 交易日
     */
    private Date tradeDate;

    /**
     * 数据来源
     */
    private String datasourceType;

    private String updateTimeStr;

    /**
     * 开盘价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal openPrice;

    /**
     * 收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice;

    /**
     * 最高价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highPrice;

    /**
     * 最低价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowPrice;

    /**
     * 中间价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal midPrice;

    /**
     * 基础币种，比如USD/CNY中的USD
     */
    private String basisCyy;

    /**
     * 报价币种，比如USD/CNY中的CNY
     */
    private String quotCyy;

    /**
     * 报价单位
     */
    private BigDecimal quotUnit;

    /**
     * 录入方式，接口、手工等
     */
    private String inputType;


    /**
     * 选中的数据
     */
    private transient List<ChbExchangeQuotationInfoDto> selectedItem;

    /**
     * 操作类型
     */
    private String operateType;

    private String summitType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 错误信息
     */
    private String errMsg;

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getDatasourceType() {
        return datasourceType;
    }

    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public void setMidPrice(BigDecimal midPrice) {
        this.midPrice = midPrice;
    }

    public String getBasisCyy() {
        return basisCyy;
    }

    public void setBasisCyy(String basisCyy) {
        this.basisCyy = basisCyy;
    }

    public String getQuotCyy() {
        return quotCyy;
    }

    public void setQuotCyy(String quotCyy) {
        this.quotCyy = quotCyy;
    }

    public void setQuotUnit(BigDecimal quotUnit) {
        this.quotUnit = quotUnit;
    }

    public String getInputType() {
        return inputType;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getSummitType() {
        return summitType;
    }

    public void setSummitType(String summitType) {
        this.summitType = summitType;
    }

    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<ChbExchangeQuotationInfoDto> getSelectedItem() {
        return selectedItem;
    }
 
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<ChbExchangeQuotationInfoDto> selectedItem) {
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

    public void setExchargeCode(String exchargeCode) {
        this.exchargeCode = exchargeCode;
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


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    
   
    
    

    @PrimaryKey(value=Field.PK_1)
    @Length(fieldName = "MstExchangeQuotationInfo001.exchargeCode", max = 50, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstExchangeQuotationInfo001.exchargeCode", groups = {ValidateGroupEdit.class})
    public String getExchargeCode() {
        return exchargeCode;
    }
    /**
     * 设置交易日为主键
     * <一句话功能简述>
     * <功能详细描述>
     * @return 返回格式化后的值
     * @see
     * @since 1.0
     */
    @PrimaryKey(value = Field.PK_2)
    public String getTradeDateStr() {
        return DateUtil.format(tradeDate);
    }
    

    @NotEmpty(fieldName = "MstExchangeQuotationInfo001.tradeDate", groups = {ValidateGroupEdit.class})
    public Date getTradeDate() {
        return tradeDate;
    }

  

    @Length(fieldName = "MstExchangeQuotationInfo002.openPrice", max = 20, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstExchangeQuotationInfo002.openPrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getOpenPrice() {
        return openPrice;
    }
   

    @Length(fieldName = "MstExchangeQuotationInfo002.closePrice", max = 20, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstExchangeQuotationInfo002.closePrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getClosePrice() {
        return closePrice;
    }
    

    @Length(fieldName = "MstExchangeQuotationInfo002.midPrice", max = 20, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstExchangeQuotationInfo002.midPrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getMidPrice() {
        return midPrice;
    }
   

    @Length(fieldName = "MstExchangeQuotationInfo002.highPrice", max = 20, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstExchangeQuotationInfo002.highPrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getHighPrice() {
        return highPrice;
    }
    

    @Length(fieldName = "MstExchangeQuotationInfo002.lowPrice", max = 20, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstExchangeQuotationInfo002.lowPrice", groups = {ValidateGroupEdit.class})
    public BigDecimal getLowPrice() {
        return lowPrice;
    }
    

    @Length(fieldName = "MstExchangeQuotationInfo002.quotUnit", max = 20, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstExchangeQuotationInfo002.quotUnit", groups = {ValidateGroupEdit.class})
    public BigDecimal getQuotUnit() {
        return quotUnit;
    }
}
