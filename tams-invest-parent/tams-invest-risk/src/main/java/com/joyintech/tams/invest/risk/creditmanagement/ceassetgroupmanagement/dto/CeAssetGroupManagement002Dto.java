/*
 * 文件名：CeAssetGroupManagement002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.CreditAssetpoolDetail;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.validator.CeAssetGroupManagement002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月22日
 * @see CeAssetGroupManagement002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = CeAssetGroupManagement002Validator.class)
public class CeAssetGroupManagement002Dto extends CreditAssetpoolDetail implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 2812633470416032411L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    
    /**
     * 选定参数
     */
    private List<CeAssetGroupManagement002Dto> selectedItem;
    
    /**
     * 资产名称
     */
    private String finprodName;
    /**
     * 系列名称
     */
    private String assetpoolName;

    
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


    
    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }


    
    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }


    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<CeAssetGroupManagement002Dto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<CeAssetGroupManagement002Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getSeqno() {
        return super.getSeqno();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(value = Field.PK_2)
    public String getFinprodId() {
        return super.getFinprodId();
    }


    
    /**
     * 获取 assetpoolName
     * @return assetpoolName.
     */
    @PrimaryKey(value = Field.PK_3)
    public String getAssetpoolName() {
        return assetpoolName;
    }

    
    /**
     * 设置 assetpoolName
     * @param assetpoolName assetpoolName
     */
    public void setAssetpoolName(String assetpoolName) {
        this.assetpoolName = assetpoolName;
    }

    /**
     * 机构
     */
    @PrimaryKey(value = Field.PK_5)
    private String institutionCode;

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

}
