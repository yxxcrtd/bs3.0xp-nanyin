/*
 * 文件名：AssetClassManagement001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2019年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productplanning.assetclassmanagement.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.AssetClass;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangqidong
 * @version 1.0 2019年12月10日
 * @see AssetClassManagementDto
 * @since 1.0
 */
public class AssetClassManagementDto  extends  AssetClass {

	  /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 元素Id
     */
    private String elementId;

    /**
     * 资产二级分类
     */
    @PrimaryKey(Field.PK_1)
    @NotEmpty(fieldName="AssetClassManagement.customAssetTypeTwo",groups = {ValidateGroupEdit.class})
    private String customAssetTypeTwo;

    /**
     * 资产三级分类
     */
    @PrimaryKey(Field.PK_2)
    @NotEmpty(fieldName="AssetClassManagement.customAssetTypeThree",groups = {ValidateGroupEdit.class})
    private String customAssetTypeThree;

    /**
     * 资产四级分类
     */
    @PrimaryKey(Field.PK_3)
    @NotEmpty(fieldName="AssetClassManagement.customAssetTypeFour",groups = {ValidateGroupEdit.class})
    private String customAssetTypeFour;

    /**
     * 资产五级分类
     */
    @PrimaryKey(Field.PK_4)
    @NotEmpty(fieldName="AssetClassManagement.customAssetTypeFive",groups = {ValidateGroupEdit.class})
    private String customAssetTypeFive;

    /**
     * 资产大类
     */
    @NotEmpty(fieldName="AssetClassManagement.assetType",groups = {ValidateGroupEdit.class})
    private String assetType;

    /**
     * 大类资产配置系统类型
     */
    @NotEmpty(fieldName="AssetClassManagement.pinanAssetType",groups = {ValidateGroupEdit.class})
    private String pinanAssetType;

    /**
     * 是否非标
     */
    @NotEmpty(fieldName="AssetClassManagement.isNonstandard",groups = {ValidateGroupEdit.class})
    private String isNonstandard;

    /**
     * 操作类型
     */
	private String operateType;
    
    
    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }
    
    
    /**
     * 设置 elementId
     * 
     * @param elementId  
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    } 

    /**
     * 获取 isNonstandard
     * 
     * @return isNonstandard.
     */
    public String getIsNonstandard() {
        return isNonstandard;
    }

   
    
    /**
     * 设置 isNonstandard
     * 
     * @param isNonstandard isNonstandard
     */
    public void setIsNonstandard(String isNonstandard) {
        this.isNonstandard = isNonstandard;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
	public String getOperateType() {
		return operateType;
	}

	 /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
     * 取得 资产二级分类
     * @return 资产二级分类
     */
    public String getCustomAssetTypeTwo() {
        return customAssetTypeTwo;
    }

    /**
     * 设置 资产二级分类
     * @param customAssetTypeTwo 资产二级分类
     */
    public void setCustomAssetTypeTwo(String customAssetTypeTwo) {
        this.customAssetTypeTwo = customAssetTypeTwo == null ? null : customAssetTypeTwo.trim();
    }

    /**
     * 取得 资产三级分类
     * @return 资产三级分类
     */
    public String getCustomAssetTypeThree() {
        return customAssetTypeThree;
    }

    /**
     * 设置 资产三级分类
     * @param customAssetTypeThree 资产三级分类
     */
    public void setCustomAssetTypeThree(String customAssetTypeThree) {
        this.customAssetTypeThree = customAssetTypeThree == null ? null : customAssetTypeThree.trim();
    }

    /**
     * 取得 资产四级分类
     * @return 资产四级分类
     */
    public String getCustomAssetTypeFour() {
        return customAssetTypeFour;
    }

    /**
     * 设置 资产四级分类
     * @param customAssetTypeFour 资产四级分类
     */
    public void setCustomAssetTypeFour(String customAssetTypeFour) {
        this.customAssetTypeFour = customAssetTypeFour == null ? null : customAssetTypeFour.trim();
    }

    /**
     * 取得 资产五级分类
     * @return 资产五级分类
     */
    public String getCustomAssetTypeFive() {
        return customAssetTypeFive;
    }

    /**
     * 设置 资产五级分类
     * @param customAssetTypeFive 资产五级分类
     */
    public void setCustomAssetTypeFive(String customAssetTypeFive) {
        this.customAssetTypeFive = customAssetTypeFive == null ? null : customAssetTypeFive.trim();
    }

   
   
    


    private List<AssetClassManagementDto> selectedItem1;


	public List<AssetClassManagementDto> getSelectedItem1() {
		return selectedItem1;
	}


	public void setSelectedItem1(List<AssetClassManagementDto> selectedItem1) {
		this.selectedItem1 = selectedItem1;
	}

   
    


}
