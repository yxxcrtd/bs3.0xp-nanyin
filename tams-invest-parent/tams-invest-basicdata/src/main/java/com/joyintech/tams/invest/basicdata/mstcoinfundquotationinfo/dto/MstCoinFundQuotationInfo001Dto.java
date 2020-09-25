/*
 * 文件名：MstCoinFundQuotationInfo001Dto.java

 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：范双虎
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstcoinfundquotationinfo.dto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstcoinfundquotationinfo.validate.MstCoinFundQuotationInfoValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 货币型基金行情实体类
 * @author 范双虎
 * @version 1.0 2018年8月3日
 * @see MstCoinFundQuotationInfo001Dto
 * @since 1.0
 */
@PageId("MstCoinFundQuotationInfo002")//页面id
@ValidLogic(targetClass = MstCoinFundQuotationInfoValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class MstCoinFundQuotationInfo001Dto extends FinProductValuation implements ITableRow,ProcessEntity{
    /**
     * 序列化值
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3103157969451025142L;
    /**
     * 市场机构代码
     */
    @NotEmpty(fieldName = "MstCoinFundQuotationInfo001.finprodId", groups = {ValidateGroupEdit.class})
    private String finprodMarketId;
    /**
     * 估值日
     */
    private String valuationDay;
    
    /**
     * 基金管理人
     */
    @Length(fieldName = "MstCoinFundQuotationInfo001.fundManager", max = 66, groups = {ValidateGroupEdit.class})
    private String fundManager;
    /**
     * 导入日
     */
    private Date importDay;
    /**
     * 选中的数据
     */
    private transient List<MstCoinFundQuotationInfo001Dto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * pageModel操作类型
     */
    private String pageModel;
    /**
     * elementId请求的节点名
     */
    private String elementId;
    /**
     * 操作类型
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
     * 获取 finprodChiType
     * @return finprodChiType.
     */
    public String getFinprodChiType(){
        return SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型, super.getFinprodType2());
    }
    /**
     * 基金类型
     * @return String 基金类型
     */
    @Override
    @PrimaryKey(value=Field.PK_1)
//    @NotEmpty(fieldName = "MstCoinFundQuotationInfo001.finprodId", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstCoinFundQuotationInfo001.finprodId", max = 32, groups = {ValidateGroupEdit.class})
    public String getFinprodId(){
        return super.getFinprodId();
    }
    
    /**
     * 基金总份额为了增加限制条件
     * 杨文君添加
     */
    @Override
    @NotEmpty(fieldName = "MstCoinFundQuotationInfo002.cShares", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "MstCoinFundQuotationInfo002.cShares", maxIntBit = 16,maxDecBit=4, groups = {ValidateGroupEdit.class})
	public BigDecimal getcShares() {
		// TODO Auto-generated method stub
		return super.getcShares();
	}

	/**
     * 估值日为了增加限制条件
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstCoinFundQuotationInfo001.valDate", groups = {ValidateGroupEdit.class})
    public Date getValDate(){
        return super.getValDate();
    }
    
    /**
     * 估值日，标准格式。为了设置主键
     * @return 格式化日期
     * @see
     * @since 1.0
     */
    @PrimaryKey(value=Field.PK_2)
    private String getDay(){
        String date =DateUtil.format(super.getValDate());
        return date;
    }
    /**
     * 数据字典中基金类型对应的字符串
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(value=Field.PK_3)
    @Length(fieldName = "MstCoinFundQuotationInfo002.finprodType", max = 50, groups = {ValidateGroupEdit.class})
    public String getFinprodType(){
        return super.getFinprodType2();
    }
    /**
     * 估值，设置check条件
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstCoinFundQuotationInfo002.valuation", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "MstCoinFundQuotationInfo002.valuation", maxIntBit = 16,maxDecBit=14, groups = {ValidateGroupEdit.class})
    public BigDecimal getValuation(){
        return super.getValuation();
    }


    @Override
    @Amount(fieldName = "MstCoinFundQuotationInfo002.day7YearYield", maxIntBit = 16,maxDecBit=4, groups = {ValidateGroupEdit.class})
    public BigDecimal getDay7YearYield() {
        return super.getDay7YearYield();
    }

    @Override
    @Amount(fieldName = "MstCoinFundQuotationInfo002.totalValue", maxIntBit = 16,maxDecBit=4, groups = {ValidateGroupEdit.class})
    public BigDecimal getTotalValue() {
        return super.getTotalValue();
    }

    @Override
    @Amount(fieldName = "MstCoinFundQuotationInfo002.restNetValue", maxIntBit = 16,maxDecBit=4, groups = {ValidateGroupEdit.class})
    public BigDecimal getRestNetValue() {
        return super.getRestNetValue();
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
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<MstCoinFundQuotationInfo001Dto> getSelectedItem() {
        return selectedItem;
    }

    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstCoinFundQuotationInfo001Dto> selectedItem) {
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
    
    @Override
    public String getInputType(){
        return super.getInputType();
    }

    private String finprodAbbr;

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
}
