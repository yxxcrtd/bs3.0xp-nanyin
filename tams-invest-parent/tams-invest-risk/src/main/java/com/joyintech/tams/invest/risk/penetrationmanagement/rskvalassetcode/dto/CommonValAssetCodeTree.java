/*
 * 文件名：CommonValAssetCodeTree.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.dto;


/**
 * 资产科目树DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月17日
 * @see CommonValAssetCodeTree
 * @since 1.0
 */
public class CommonValAssetCodeTree {

    private String assetCode;
    
    private String assetName;

    
    /**
     * 获取 assetCode
     * @return assetCode.
     */
    public String getAssetCode() {
        return assetCode;
    }

    
    /**
     * 设置 assetCode
     * @param assetCode assetCode
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    
    /**
     * 获取 assetName
     * @return assetName.
     */
    public String getAssetName() {
        return assetName;
    }

    
    /**
     * 设置 assetName
     * @param assetName assetName
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    
    
}
