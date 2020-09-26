/*
 * 文件名：TablePartitionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.tablepartition.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 表分区DTO
 *
 * @author 王波
 * @version 1.0 2018年10月16日
 * @see TablePartitionNameDto
 * @since 1.0
 */
public class TablePartitionNameDto {
    /**
     * 分区名
     */
    private String partitionName;
    /**
     * 表名
     */
    private String tableName;
    
    /**
     * 分区的值
     */
    private String partValue;
    
    /**
     * 默认分区名称
     */
    private String defPartName;
    
    /**
     * 获取 defPartName
     * @return defPartName.
     */
    public String getDefPartName() {
        return defPartName;
    }

    /**
     * 设置 defPartName
     * @param defPartName defPartName
     */
    public void setDefPartName(String defPartName) {
        this.defPartName = defPartName;
    }


    /**
     * 获取 partValue
     * @return partValue.
     */
    public String getPartValue() {
        return partValue;
    }

    
    /**
     * 设置 partValue
     * @param partValue partValue
     */
    public void setPartValue(String partValue) {
        this.partValue = partValue;
    }

    /**
     * 获取 partitionName
     * @return partitionName.
     */
    public String getPartitionName() {
        return partitionName;
    }
    
    /**
     * 设置 partitionName
     * @param partitionName partitionName
     */
    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }
    
    /**
     * 获取 tableName
     * @return tableName.
     */
    public String getTableName() {
        return tableName;
    }
    
    /**
     * 设置 tableName
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
}
