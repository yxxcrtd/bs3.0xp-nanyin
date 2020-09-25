/*
 * 文件名：FinIntPayDateAdjustmentDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.validator.FinIntPayDateAdjustmentValidator;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.validator.FinPayPlanAdjustmentValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 信息调整管理_付息日调整 
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月23日
 * @see FinIntPayDateAdjustmentDto
 * @since 1.0
 */
@PageId("FinIntPayDateAdjustment001")
@ValidLogic(targetClass = FinIntPayDateAdjustmentValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FinIntPayDateAdjustmentDto extends FinCashPlan implements ProcessEntity{
    
    /**
     * 
     */
    private String payStatus;
    
    /**
     * 
     */
    private List<FinIntPayDateAdjustmentDto> selectedItem1;
    /**
     * 
     */
    private List<FinIntPayDateAdjustmentDto> selectedItem;
    
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 
     */
    private String elementId;
    
    /**
     * 
     */
    private String pageModel;
    
    /**
     * 
     */
    private String finprodAbbr;
    
    /**
     * 
     */
    private String finprodMarketId;
    
    /**
     * 
     */
    private Date vdateCheck;
    
    @PrimaryKey(value=Field.PK_1)
    public String getCashId(){
        return super.getCashId();
    }
    
    @PrimaryKey(value=Field.PK_2)
    public String getPK_2(){
        return DictCodeList.共通业务类型_付息日调整.getValue();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(value=Field.PK_3)
    public String getFinprodId() {
        return super.getFinprodId();
    }
    
    /**
     * 
     * 控制表格置灰
     * <功能详细描述>
     * @return String
     * @see
     * @since 1.0
     */
    public String getUpdateFlg(){
        if(StringUtil.isNotEmpty(this.getPayStatus())){
            return this.getPayStatus().equals("已付息")?"02":"01";
        }
        else{
            return null;
        }
    }
    
    /**
     * 获取 selectedItem1
     * @return selectedItem1.
     */
    public List<FinIntPayDateAdjustmentDto> getSelectedItem1() {
        return selectedItem1;
    }

    
    /**
     * 设置 selectedItem1
     * @param selectedItem1 selectedItem1
     */
    public void setSelectedItem1(List<FinIntPayDateAdjustmentDto> selectedItem1) {
        this.selectedItem1 = selectedItem1;
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
     * 获取 payStatus
     * @return payStatus.
     */
    public String getPayStatus() {
        return payStatus;
    }


    
    /**
     * 设置 payStatus
     * @param payStatus payStatus
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    @NotEmpty(fieldName = "FinIntPayDateAdjustment001.cashId", groups = {ValidateGroupEdit.class})
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FinIntPayDateAdjustmentDto> getSelectedItem() {
        return selectedItem;
    }

    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinIntPayDateAdjustmentDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

	public Date getVdateCheck() {
		return vdateCheck;
	}

	public void setVdateCheck(Date vdateCheck) {
		this.vdateCheck = vdateCheck;
	}
    
    
    
}
