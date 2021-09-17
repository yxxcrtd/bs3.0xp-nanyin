/*
 * 文件名：SecPosAutoGenerationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.risk.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 强制赎回费征收条件落地批处理Mapper
 *
 * @author yflv
 * @version 1.0 2021年9月17日
 * @see RiskComRedFeeExpCondGenMapper
 * @since 1.0
 */
@Repository
public interface RiskComRedFeeExpCondGenMapper {

    /**
     * 删除当日数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int deleteTodayRiskComRedFeeExpCondGen(Map<String, Object> paramMap);
    
    /**
     * 插入当日数据
     * @param paramMap
     * @return
     */
    int insertTodayRiskComRedFeeExpCondGen(Map<String, Object> paramMap);

    
}
