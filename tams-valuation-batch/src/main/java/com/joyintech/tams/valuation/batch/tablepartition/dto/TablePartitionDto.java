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

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 表分区DTO
 *
 * @author 王波
 * @version 1.0 2018年10月16日
 * @see TablePartitionDto
 * @since 1.0
 */
public class TablePartitionDto extends NeedOperateEntity {
    /**
     * 分区名 01-按照账套ID
     */
    private String partitionType;
    /**
     * 表名
     */
    private List<String> tableName;
    
    /**
     * 获取 partitionType
     * @return partitionType.
     */
    public String getPartitionType() {
        return partitionType;
    }
    
    /**
     * 设置 partitionType
     * @param partitionType partitionType
     */
    public void setPartitionType(String partitionType) {
        this.partitionType = partitionType;
    }
    
    /**
     * 获取 tableName
     * @return tableName.
     */
    public List<String> getTableName() {
        return tableName;
    }
    
    /**
     * 设置 tableName
     * @param tableName tableName
     */
    public void setTableName(List<String> tableName) {
        this.tableName = tableName;
    }
}
