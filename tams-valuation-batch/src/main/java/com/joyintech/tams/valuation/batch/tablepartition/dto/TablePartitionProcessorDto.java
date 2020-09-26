/*
 * 文件名：TablePartitionProcessorDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.tablepartition.dto;

import java.util.List;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

public class TablePartitionProcessorDto extends NeedToWriteEntity {

    /**
     * 待添加的分区
     */
    private List<TablePartitionNameDto> listDto;

    
    /**
     * 获取 listDto
     * @return listDto.
     */
    public List<TablePartitionNameDto> getListDto() {
        return listDto;
    }

    
    /**
     * 设置 listDto
     * @param listDto listDto
     */
    public void setListDto(List<TablePartitionNameDto> listDto) {
        this.listDto = listDto;
    }
}
