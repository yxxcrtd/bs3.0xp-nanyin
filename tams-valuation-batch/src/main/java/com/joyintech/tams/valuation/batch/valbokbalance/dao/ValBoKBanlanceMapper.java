/*
 * 文件名：ValBoKBanlanceMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valbokbalance.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.valbokbalance.dto.ValBatchTaskTodoDto;

/**
 * 科目余额汇总Mapper
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see ValBoKBanlanceMapper
 * @since 1.0
 */
@Repository
public interface ValBoKBanlanceMapper {
    /**
     * 查询所有待处理的账套代码
     * @param params 
     *          taskId 任务代码
     * @return 所有待处理的账套代码结果集
     * @see
     * @since 1.0
     */
    List<ValBatchTaskTodoDto> selectValBatchTaskTodo(Map<String, Object> params);
    
    void deleteBokBalTableData(@Param("balDate") Date balDate, @Param("booksetId") String booksetId);

}
