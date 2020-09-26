/*
 * 文件名：TablePartitionWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.tablepartition.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.tablepartition.dao.TablePartitionMapper;
import com.joyintech.tams.valuation.batch.tablepartition.dto.TablePartitionNameDto;
import com.joyintech.tams.valuation.batch.tablepartition.dto.TablePartitionProcessorDto;

@Service
public class TablePartitionWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private TablePartitionMapper mapper;
    
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        List<TablePartitionProcessorDto> res = (List<TablePartitionProcessorDto> )items;
        for (TablePartitionProcessorDto dto : res) {
            List<TablePartitionNameDto> parts = dto.getListDto();
            for (TablePartitionNameDto part : parts) {
                Map<String, Object> params = new HashMap<>();
                params.put("tableName", part.getTableName());
                params.put("partitionName", part.getPartitionName());
                params.put("partValue", part.getPartValue());
                params.put("defPartName", part.getDefPartName());
                mapper.alterTablePartition(params);
            }
            if (dto.getProdId() == null) {
                dto.setProdId("TablePartition");
            }
        }
        
    }

}
