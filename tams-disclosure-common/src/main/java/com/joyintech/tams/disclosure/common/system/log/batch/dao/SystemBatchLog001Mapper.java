/*
 * 文件名：SystemBatchLog001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月2日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.disclosure.common.system.log.batch.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbSysTask;
import com.joyintech.tams.disclosure.common.system.log.batch.dto.SystemBatchLog001Dto;


/**
 * @author 何阳阳
 * @version 1.0 2017年12月2日
 * @see SystemBatchLog001Mapper
 * @since 1.0
 */
@Repository(value="disSystemBatchLog001Mapper")
public interface SystemBatchLog001Mapper {
    /**
     * 查询所有有效的系统任务
     * @return 系统任务列表
     */
    List<ChbSysTask> queryTask();
    
    /**
     * 查询批处理列表
     * @param param 参数
     * @return 查询结果
     */
    List<SystemBatchLog001Dto> searchForPage(Map<String, Object> param);
}
