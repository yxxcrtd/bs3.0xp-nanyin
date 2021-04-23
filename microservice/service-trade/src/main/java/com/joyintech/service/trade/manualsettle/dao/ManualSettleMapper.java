package com.joyintech.service.trade.manualsettle.dao;

import com.joyintech.service.trade.manualsettle.dto.ManualSettleDto;
import com.joyintech.service.trade.manualsettle.dto.ManualSettleFlowDto;
import com.joyintech.tams.common.base.entity.StlPayTempOrders;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhangnian
 * @ClassName ManualSettleMapper
 * @description TODO
 * @date 2020/10/22 0022 14:09
 **/
@Repository
public interface ManualSettleMapper {

    List<ManualSettleDto> queryForPage(Map<String, Object> param);

    List<ManualSettleFlowDto> queryForFlowPage(Map<String, Object> param);

    /**
     *功能描述 ：交收延期
     * @author zhangnian
     * @date 2020/10/22 0022
     * @param  * @param null
     * @return
    */
    void updateSettleInfo(Map<String, Object> param);

    StlPayTempOrders selectStlPayOrders(@Param("tradeId") String tradeId);

    void deleteEtrCashFlow(@Param("orderId") String orderId);

    void deletePtlCashFlow(@Param("orderId") String orderId);

    void deletePtlSecFlow(@Param("tradeId") String tradeId);

    void deleteEtrSecFlow(@Param("tradeId") String tradeId);

    List<EtrCashPositionFlowDto> selectEtrCashPositionFlow(@Param("orderId") String orderId);

    List<EtrSecPositionFlowDto> selectEtrSecPositionFlow(@Param("tradeId") String tradeId);

} 

