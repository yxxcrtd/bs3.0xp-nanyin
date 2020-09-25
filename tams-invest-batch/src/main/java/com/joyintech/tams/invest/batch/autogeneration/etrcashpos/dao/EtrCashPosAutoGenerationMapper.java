/*
 * 文件名：CashPosAutoGenerationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.etrcashpos.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.EtrCashPositionFlow;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 组合现金余额自动生成批处理Mapper
 *
 * @author 刘二通
 * @version 1.0 2018年11月20日
 * @see EtrCashPosAutoGenerationMapper
 * @since 1.0
 */
@Repository
public interface EtrCashPosAutoGenerationMapper {

    /**
     * 删除当日现金余额数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int deleteTodayCashPosData(Map<String, Object> paramMap);

    /**
     * 生成当日可用现金余额
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int insertTodayCashPosData(Map<String, Object> paramMap);

    /**
     * 查询有效的委托
     * @param paramMap
     * @return 有效的委托
     */
    List<EntrustCommonDto> findEnableEntrust(Map<String, Object> paramMap);

    /**
     * 查询一条流水
     * @param orderId
     * @param type
     * @return
     */
    EtrCashPositionFlow selectCashFlow(@Param("orderId")String orderId, @Param("type")String type);

    /**
     * 查询冲销现金
     * @param orderId
     * @return
     */
    BigDecimal findReCash(@Param("orderId")String orderId);
}
