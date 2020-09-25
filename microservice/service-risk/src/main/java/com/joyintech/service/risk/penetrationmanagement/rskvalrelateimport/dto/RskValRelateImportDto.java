/*
 * 文件名：RskValRelateImportDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskValtblPrtImpMap;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2018年12月20日
 * @see RskValRelateImportDto
 * @since 1.0
 */
public class RskValRelateImportDto extends RskValtblPrtImpMap{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -195958280213061474L;
    
    /**
     * 组合名称
     */
    private String name;
    
    /**
     * 清理字符

     */
    private String trimChars;
    
    /**
     * 选定参数
     */
    private List<RskValRelateImportDto> selectedItem;

    
    /**
     * 获取 name
     * @return name.
     */
    public String getName() {
        return name;
    }

    
    /**
     * 设置 name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }


    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<RskValRelateImportDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<RskValRelateImportDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    
    /**
     * 获取 trimChars
     * @return trimChars.
     */
    public String getTrimChars() {
        return trimChars;
    }


    
    /**
     * 设置 trimChars
     * @param trimChars trimChars
     */
    public void setTrimChars(String trimChars) {
        this.trimChars = trimChars;
    }
    
    

}
