/*
 * 文件名：ProdPassAssetBonusMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2019年3月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.channelprodautocal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.ValBooksetTodoDto;


/**
 * 通道产品自动结转
 * @author weipeng
 * @version 1.0 2019年3月14日
 * @see ChannelProdAutoCalMapper
 * @since 1.0
 */
@Repository
public interface ChannelProdAutoCalMapper {

    /**
     * 查询所有待处理的账套代码
     * 
     * @param params taskId 任务代码
     * @return 所有待处理的账套代码结果集
     * @see
     * @since 1.0
     */
    List<ValBooksetTodoDto> selectBooksetTodo(Map<String, Object> params);

    
}

