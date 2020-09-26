/*
 * 文件名：TablePartitionMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.tablepartition.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.tablepartition.dto.TablePartitionNameDto;

/**
 * 表分区Mapper
 * 
 * @author 王波
 * @version 1.0 2018年10月16日
 * @see TablePartitionMapper
 * @since 1.0
 */
@Repository
public interface TablePartitionMapper {
    /**
     * 查询所有未分区的数据--以账套ID
     * @return
     * @see
     * @since 1.0
     */
    List<TablePartitionNameDto> findAllUnPartionsByBooksetId(Map<String, Object> param);

    /**
     * 对表进行分区
     * 
     * @param params
     * @see
     * @since 1.0
     */
    void alterTablePartition(Map<String, Object> params);
}
