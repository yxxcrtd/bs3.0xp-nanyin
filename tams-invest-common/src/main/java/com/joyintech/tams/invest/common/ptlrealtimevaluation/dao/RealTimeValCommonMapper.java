package com.joyintech.tams.invest.common.ptlrealtimevaluation.dao;

import com.joyintech.tams.common.base.entity.BokTradeOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 估值共通mapper
 *
 * @author yjm
 * @version 1.0 2020/04/23
 * @see RealTimeValCommonMapper
 * @since 1.0
 */
@Repository
public interface RealTimeValCommonMapper {
    /**
     * 获取交易方向优先级
     *
     * @param acctStandard 参数
     * @return 跑批范围信息
     * @see
     * @since 1.0
     */
    List<BokTradeOrder> selectValTradeOrder(@Param("acctStandard") String acctStandard);
    /**
     * 获取交易方向优先级
     *
     * @param acctStandard 参数
     * @return 跑批范围信息
     * @see
     * @since 1.0
     */
    List<String> getValBokBooksetExt(Map<String,Object> paraMap);
}
