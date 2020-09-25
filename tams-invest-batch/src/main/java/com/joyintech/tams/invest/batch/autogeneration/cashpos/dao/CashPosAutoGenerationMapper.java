/*
 * 文件名：CashPosAutoGenerationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.cashpos.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 组合现金余额自动生成批处理Mapper
 *
 * @author 刘二通
 * @version 1.0 2018年11月20日
 * @see CashPosAutoGenerationMapper
 * @since 1.0
 */
@Repository
public interface CashPosAutoGenerationMapper {

    /**
     * 删除当日持仓数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int deleteTodaySecPosData(Map<String, Object> paramMap);

    /**
     * 生成当日持仓数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int insertTodaySecPosData(Map<String, Object> paramMap);
}
