/*
 * 文件名：InterfBaseMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;

@Repository
public interface InterfBaseMapper {

    /**
     * 获取任务信息 
     * 
     * @param params 参数
     * @return
     */
    List<InterfExecuteInfo> getInterfExecuteInfo(Map<String, Object> params);
    
    /**
     * 通过模板名称获取批次号
     * 
     * @param tempENames
     * @return
     * @see
     * @since 1.0
     */
    List<String> getOrderNosByTempENames(Map<String, Object> params);
    
    
}
