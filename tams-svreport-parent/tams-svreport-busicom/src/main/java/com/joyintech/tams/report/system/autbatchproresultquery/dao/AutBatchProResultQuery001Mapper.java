/*
 * 文件名：AutBatchProResultQuery001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zuoyinyi
 * 修改时间：2017年11月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.autbatchproresultquery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.system.autbatchproresultquery.dto.AutBatchProResultQuery001Dto;


/**
 * 自动报送批处理结果查询Mapper
 * @author zuoyinyi
 * @version 1.0 2017年11月14日
 * @see AutBatchProResultQuery001Mapper
 * @since 1.0
 */
@Repository
public interface AutBatchProResultQuery001Mapper {

    /**
     * 
     * 查询自动批处理日志结果列表
     * @param map 查询条件
     * @return 结果列表
     * @see 
     * @since 1.0
     */
    List<AutBatchProResultQuery001Dto> queryBatchProResult(Map<String,Object> map);
}
