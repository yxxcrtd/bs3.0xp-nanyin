/*
 * 文件名：CeAssetGroupManagement001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.CreditAssetpool;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.validator.CeAssetGroupManagement001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月11日
 * @see CeAssetGroupManagement001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = CeAssetGroupManagement001Validator.class)
public class CeAssetGroupManagement001Dto extends CreditAssetpool implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 979450231371811110L;
    
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
     * 资产代码
     */
    private String finprodId;
    /**
     * 资产名称
     */
    private String finprodName;
    /**
     * selectedItem
     */
    private List<CeAssetGroupManagement001Dto> selectedItem;
    /**
     * 
     */
    private Boolean flag;
    
    
    @Override
    @PrimaryKey(value=Field.PK_1)
    public String getSeqno() {
        return super.getSeqno();
    }
    
    @Override
    @PrimaryKey(value=Field.PK_2)
    public String getAssetpoolName() {
        return super.getAssetpoolName();
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
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }


    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
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
    public List<CeAssetGroupManagement001Dto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<CeAssetGroupManagement001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    /**
     * 获取 flag
     * @return flag.
     */
    public Boolean getFlag() {
        return flag;
    }

    
    /**
     * 设置 flag
     * @param flag flag
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
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
