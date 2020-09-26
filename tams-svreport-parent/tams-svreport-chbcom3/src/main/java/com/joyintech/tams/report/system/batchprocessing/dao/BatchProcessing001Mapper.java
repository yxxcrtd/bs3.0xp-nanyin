/*
 * 文件名：BatchProcessing001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年12月26日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */
 
package com.joyintech.tams.report.system.batchprocessing.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.system.batchprocessing.dto.BatchProcessing001Dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年12月26日
 * @see BatchProcessing001Mapper
 * @since 1.0
 */
@Repository
public interface BatchProcessing001Mapper {

    /**
     * 批量插入task任务
     * <功能详细描述>
     * @param taskList 任务列表
     * @see
     * @since 1.0
     */
    void insertBatchData(@Param("taskList") List<BatchProcessing001Dto> taskList);

    /**
     * 获取当前批次的所有任务的情况
     * <功能详细描述>
     * @param param 参数
     * @return 任务list
     * @see
     * @since 1.0
     */
    List<BatchProcessing001Dto> getShowDataList(Map<String, Object> param);

    /**
     * 获取批处理日志的路径
     * <功能详细描述>
     * @return 路径
     * @see
     * @since 1.0
     */
    String getBatchLogPath();

}
