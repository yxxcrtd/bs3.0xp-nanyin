/*
 * 文件名：FinPayPlanAdjustmentDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.dto;

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
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.validator.FinPayPlanAdjustmentValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 信息调整管理_付费计划调整  〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月18日
 * @see FinPayPlanAdjustmentDto
 * @since 1.0
 */
@PageId("FinPayPlanAdjustment001")
@ValidLogic(targetClass = FinPayPlanAdjustmentValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FinPayPlanAdjustmentDto extends FinCashPlan implements ProcessEntity{
    
    /**
     * 费用名称
     */
    private String feeName; 
    
    /**
     * 产品款型
     */
    private String prodType;
    /**
     * 状态
     */
    private String payStatus;
    
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
    private List<FinPayPlanAdjustmentDto> selectedItem1;
    /**
     * 
     */
    private List<FinPayPlanAdjustmentDto> selectedItem;
    
    /**
     * 
     */
    private String finprodMarketId;
    
    /**
     * 
     */
    private Date vdateCheck;
    
    /**
     * 
     */
    //private String updateFlg;
    
    /**
     * @return String
     */
    @PrimaryKey(value=Field.PK_1)
    public String getCashId(){
        return super.getCashId();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(value=Field.PK_2)
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
            return this.getPayStatus().equals("已支付")?"02":"01";
        }
        else{
            return null;
        }
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param updateFlg String
     * @see
     * @since 1.0
     */
    /*public void setUpdateFlg(String updateFlg){
        this.updateFlg = updateFlg;
    }*/
    
    /**
     * 获取 feeName
     * @return feeName.
     */
    @NotEmpty(fieldName = "FinPayPlanAdjustment001.feeName", groups = {ValidateGroupEdit.class})
    public String getFeeName() {
        return feeName;
    }


    
    /**
     * 设置 feeName
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FinPayPlanAdjustmentDto> getSelectedItem1() {
        return selectedItem1;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem1(List<FinPayPlanAdjustmentDto> selectedItem1) {
        this.selectedItem1 = selectedItem1;
    }

    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    @NotEmpty(fieldName = "FinPayPlanAdjustment001.finprodId", groups = {ValidateGroupEdit.class})
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
    public List<FinPayPlanAdjustmentDto> getSelectedItem() {
        return selectedItem;
    }

    
    
    /**
     * 获取 prodType
     * @return prodType.
     */
    public String getProdType() {
        return prodType;
    }

    
    /**
     * 设置 prodType
     * @param prodType prodType
     */
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinPayPlanAdjustmentDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

	public Date getVdateCheck() {
		return vdateCheck;
	}

	public void setVdateCheck(Date vdateCheck) {
		this.vdateCheck = vdateCheck;
	}

    
    
    
    
    
    
    
}
