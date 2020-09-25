/*
 * 文件名：AssetClassManagement001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2019年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.assetclassmanagement.dto;

import java.util.List;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.AssetClass;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotation001Dto;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.validator.AssetClassManagement001validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉  
 * 
 * @author wangqidong
 * @version 1.0 2019年12月10日
 * @see AssetClassManagement001Dto
 * @since 1.0
 */

@ValidLogic(targetClass = AssetClassManagement001validator.class,groups = {ValidateGroupEdit.class})
public class AssetClassManagement001Dto extends AssetClass implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 元素Id
     */
    private String elementId;

    /**
     *  
     */
    private String pageModel;
   


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
     * 获取 pageModel
     * 
     * @return pageModel.
     */
     public String getPageModel() {
			return pageModel;
		}

     /**
      * 设置 pageModel
      * 
      * @param pageModel  
      */
	public void setPageModel(String pageModel) {
			this.pageModel = pageModel;
		}
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
    
    /**
     * 取得 大类资产配置系统类型
     * @return 大类资产配置系统类型
     */
    public String getPinanAssetType() {
        return pinanAssetType;
    }
    /**
     * 取得 资产大类
     * @return 资产大类
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产大类
     * @param assetType 资产大类
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }
    /**
     * 设置 大类资产配置系统类型
     * @param pinanAssetType 大类资产配置系统类型
     */
    public void setPinanAssetType(String pinanAssetType) {
        this.pinanAssetType = pinanAssetType == null ? null : pinanAssetType.trim();
    }

    public String getCustomAssetTypeTwoName() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, this.getCustomAssetTypeTwo());
    }       

    public String getCustomAssetTypeThreeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, this.getCustomAssetTypeThree());
    }        
   
    public String getCustomAssetTypeFourName() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, this.getCustomAssetTypeFour());
    }
             
    public String getCustomAssetTypeFiveName() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产五级分类, this.getCustomAssetTypeFive());
    }        
  
    public String getAssetTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产大类, this.getAssetType());
    }
   
    public String getPinanAssetTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产大类, this.getPinanAssetType());
    }
    
    public String getIsNonstandardName() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsNonstandard());
    }


    private List<AssetClassManagement001Dto> selectedItem;


	public List<AssetClassManagement001Dto> getselectedItem() {
		return selectedItem;
	}


	public void setselectedItem(List<AssetClassManagement001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

   
    

}
