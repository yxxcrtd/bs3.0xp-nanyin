/*
 * 文件名：RecePosAutoGenerationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.recepos.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 应收款持仓汇总处理批处理Mapper
 *
 * @author renping
 * @version 1.0 2019年12月20日
 * @see RecePosAutoGenerationMapper
 * @since 1.0
 */
@Repository
public interface RecePosAutoGenerationMapper {

    /**
     * 删除当日持仓数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int deleteTodayRecePosData(Map<String, Object> paramMap);

    /**
     * 生成当日持仓数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int insertTodayRecePosData(Map<String, Object> paramMap);
}
