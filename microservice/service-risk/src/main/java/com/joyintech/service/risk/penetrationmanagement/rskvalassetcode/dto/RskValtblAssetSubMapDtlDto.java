/*
 * 文件名：RskValtblAssetSubMapDtlDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskValtblAssetSubMapDtl;

/**
 * 资产科目映射明细DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月25日
 * @see RskValtblAssetSubMapDtlDto
 * @since 1.0
 */
public class RskValtblAssetSubMapDtlDto extends RskValtblAssetSubMapDtl{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 目标科目代码名称

     */
    private String tgtName;
    
    /**
     * 源科目代码(旧)

     */
    private String srcCodeOld;
    
    /**
     * 选中的记录
     */
    private transient List<RskValtblAssetSubMapDtlDto> selectedItem;

    
    /**
     * 获取 srcCodeOld
     * @return srcCodeOld.
     */
    public String getSrcCodeOld() {
        return srcCodeOld;
    }

    
    /**
     * 设置 srcCodeOld
     * @param srcCodeOld srcCodeOld
     */
    public void setSrcCodeOld(String srcCodeOld) {
        this.srcCodeOld = srcCodeOld;
    }


    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<RskValtblAssetSubMapDtlDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<RskValtblAssetSubMapDtlDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    
    /**
     * 获取 tgtName
     * @return tgtName.
     */
    public String getTgtName() {
        return tgtName;
    }


    
    /**
     * 设置 tgtName
     * @param tgtName tgtName
     */
    public void setTgtName(String tgtName) {
        this.tgtName = tgtName;
    }
    
    
    

}
