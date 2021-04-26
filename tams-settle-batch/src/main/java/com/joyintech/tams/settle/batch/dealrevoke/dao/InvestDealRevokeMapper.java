package com.joyintech.tams.settle.batch.dealrevoke.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 何阳阳
 * @date 2020/8/3
 * @see InvestDealRevokeMapper
 * @since 1.0
 */
@Repository
public interface InvestDealRevokeMapper {
    /**
     * 查询汇总交易编号
     * @param param 查询参数
     * @return 汇总交易编号列表
     */
    List<String> selectTotalTradeId(Map<String, Object> param);
    
    /**
     * 关联结算下行映射表查询汇总交易编号
     * @param param 查询参数
     * @return 汇总交易编号列表
     */
    List<String> selectTotalTradeIdTgM(Map<String, Object> param);

    /**
     * 查询交易编号
     * @param param 查询参数
     * @return 交易编号列表
     */
    List<String> selectTradeId(Map<String, Object> param);

    /**
     * 关联结算下行映射表查询交易编号
     * @param param 查询参数
     * @return 交易编号列表
     */
    List<String> selectTradeIdTgM(Map<String, Object> param);

    /**
     * 删除组合持仓流水
     * @param param 参数
     * @return
     */
    int deletePtlSecPositionFlow(Map<String, Object> param);

    /**
     * 删除组合现金流水
     * @param param 参数
     * @return
     */
    int deletePtlCashPositionFlow(Map<String, Object> param);

    /**
     * 删除指令持仓流水
     * @param param 参数
     * @return
     */
    int deleteEtrSecPositionFlow(Map<String, Object> param);

    /**
     * 删除指令现金流水
     * @param param 参数
     * @return
     */
    int deleteEtrCashPositionFlow(Map<String, Object> param);
    
    /**
     * 删除日间委托完成度
     * @param param 参数
     * @return
     */
    int deleteEtrFinishStatus(Map<String, Object> param);
    
    /**
     * 删除日终交易占用的委托完成度信息
     * @param param 参数
     * @return
     */
    int deleteEtrFinishStatusByTrdId(Map<String, Object> param);
    
    /**
     * 根据交易编号删除日间指令现金流水信息
     * @param param 参数
     * @return
     */
    int deleteEtrCashPositionFlowByTrdId(Map<String, Object> param);
}
