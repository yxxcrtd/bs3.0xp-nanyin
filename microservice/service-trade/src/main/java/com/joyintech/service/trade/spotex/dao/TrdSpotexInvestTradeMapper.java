/*
 * 文件名：TrdSpotexInvestTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.spotex.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.trade.spotex.dto.TrdSpotexInvestTradeDto;

/**
 * 外汇即期交易Mapper
 * @author wangguoxian
 * @version 1.0 2019年7月4日
 * @see TrdSpotexInvestTradeMapper
 * @since 1.0
 */
@Repository
public interface TrdSpotexInvestTradeMapper {

    /**
     * 
     * 分页查询
     * @param param 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<TrdSpotexInvestTradeDto> findPage(Map<String, Object> param);

    /**
     * 根据交易编号查询流程中数据条数
     *
     * @param tradeId 交易编号
     * @return 流程中数据条数
     * @see
     * @since 1.0
     */
    int selectInFlowDataCount(@Param("tradeId") String tradeId);

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
     * 根据委托ID查询剩余可用委托额度
     * @param entrustId 委托ID
     * @return 剩余可用委托额度
     */
    BigDecimal selAvailableEtrAmt(@Param("entrustId") String entrustId);
}
