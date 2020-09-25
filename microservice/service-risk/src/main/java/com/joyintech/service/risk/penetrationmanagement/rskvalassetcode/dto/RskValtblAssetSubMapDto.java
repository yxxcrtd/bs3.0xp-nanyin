/*
 * 文件名：RskValtblAssetSubMapDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskValtblAssetSubMap;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月26日
 * @see RskValtblAssetSubMapDto
 * @since 1.0
 */
public class RskValtblAssetSubMapDto extends RskValtblAssetSubMap{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private String pageModel;

    /**
     * 选中的记录
     */
    private transient List<RskValtblAssetSubMapDto> selectedItem;
    
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
    public List<RskValtblAssetSubMapDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<RskValtblAssetSubMapDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    

}
