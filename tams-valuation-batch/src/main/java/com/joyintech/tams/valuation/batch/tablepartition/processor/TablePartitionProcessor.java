/*
 * 文件名：TablePartitionProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.tablepartition.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.tablepartition.dao.TablePartitionMapper;
import com.joyintech.tams.valuation.batch.tablepartition.dto.TablePartitionDto;
import com.joyintech.tams.valuation.batch.tablepartition.dto.TablePartitionNameDto;
import com.joyintech.tams.valuation.batch.tablepartition.dto.TablePartitionProcessorDto;

/**
 * 表分区获取
 * @author 王波
 * @version 1.0 2018年10月16日
 * @see TablePartitionProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class TablePartitionProcessor extends BaseProcessor<NeedOperateEntity, TablePartitionProcessorDto>{

    @Autowired
    private TablePartitionMapper mapper;
    
    @Override
    public TablePartitionProcessorDto process(NeedOperateEntity item)
        throws Exception {
        TablePartitionDto dto = (TablePartitionDto)item;
        Map<String, Object> params = new HashMap<>();
        // 需要分区的表
        params.put("tableNames", dto.getTableName());
        List<TablePartitionNameDto> list = mapper.findAllUnPartionsByBooksetId(params);
        TablePartitionProcessorDto res = new TablePartitionProcessorDto();
        res.setListDto(list);
        return res;
    }

}
