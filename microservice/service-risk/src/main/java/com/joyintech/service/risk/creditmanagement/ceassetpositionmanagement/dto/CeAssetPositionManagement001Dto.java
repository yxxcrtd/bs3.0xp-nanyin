/*
 * 文件名：CeAssetPositionManagement001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetpositionmanagement.dto;

import com.joyintech.tams.common.base.entity.CreditLinesSecPosition;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月16日
 * @see CeAssetPositionManagement001Dto
 * @since 1.0
 */
public class CeAssetPositionManagement001Dto extends CreditLinesSecPosition{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7308308865258406384L;
    
    /**
     * 资产类型
     */
    private String assetType;
    
    /**
     * 资产名称
     */
    private String finprodName;

    
    /**
     * 获取 assetType
     * @return assetType.
     */
    public String getAssetType() {
        return assetType;
    }

    
    /**
     * 设置 assetType
     * @param assetType assetType
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
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
    
    

}
