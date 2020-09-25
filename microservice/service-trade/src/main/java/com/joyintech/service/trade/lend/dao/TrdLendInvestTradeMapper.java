/*
 * 文件名：TrdLendInvestTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.lend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.trade.lend.dto.TrdLendInvestTradeDto;

@Repository
public interface TrdLendInvestTradeMapper {
    List<TrdLendInvestTradeDto> selectDeal(Map<String, Object> pageParam);

    TrdLendInvestTradeDto getTrdLendInvestTradeDto(@Param("tradeId") String tradeId, @Param("BRANCH_0") String BRANCH_0);

    /**
     * 根据工作流代码查询交易编号
     *
     * @param workflowId 工作流代码
     * @return 交易编号
     * @see
     * @since 1.0
     */
    String selectTradeIdByWorkflowId(@Param("workflowId") String workflowId);

    /**
     * 根据交易编号查询流程中数据条数
     *
     * @param tradeId 交易编号
     * @return 流程中数据条数
     * @see
     * @since 1.0
     */
    int selectInFlowDataCount(@Param("tradeId") String tradeId);

    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 start 
    /**
     * 根据交易编号查询存续期数据条数
     *
     * @param tradeId 交易编号
     * @return 存续期数据条数
     * @see
     * @since 1.0
     */
    int selectDurationDataCount(Map<String,Object> param);
    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 end 

    /**
     * 根据交易编号查询已撤销数据条数
     *
     * @param paramMap 参数
     * @return 已撤销数据条数
     * @see
     * @since 1.0
     */
    int selectRevokedDataCount(Map<String, Object> paramMap);

    /**
     * 根据交易编号查询已流出资金核对数据条数
     *
     * @param paramMap 参数
     * @return 已做流出资金核对数据条数
     * @see
     * @since 1.0
     */
    int selectLqdPipedTrade(Map<String, Object> paramMap);
}
