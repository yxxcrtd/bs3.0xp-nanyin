/*
 * 文件名：FinIntPayDateAdjustmentDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finintpaydateadjustment.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * 信息调整管理_付息日调整 
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月23日
 * @see FinIntPayDateAdjustmentDto
 * @since 1.0
 */
public class FinIntPayDateAdjustmentDto extends FinCashPlan{
    
    /**
     * 
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
    private List<FinIntPayDateAdjustmentDto> selectedItem1;
    
    /**
     * 
     */
    private String finprodMarketId;

    
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
    public List<FinIntPayDateAdjustmentDto> getSelectedItem1() {
        return selectedItem1;
    }

    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem1(List<FinIntPayDateAdjustmentDto> selectedItem1) {
        this.selectedItem1 = selectedItem1;
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
    
    

}
