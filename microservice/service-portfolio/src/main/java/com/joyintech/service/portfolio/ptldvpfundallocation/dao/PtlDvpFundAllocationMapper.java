/*
 * 文件名：PtlDvpFundAllocationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年3月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptldvpfundallocation.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlCashPositionFlow;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptldvpfundallocation.dto.PtlDvpFundAllocationDto;

/**
 * DVP资金调拨Mapper
 *
 * @author etliu
 * @version 1.0 2019年3月29日
 * @see PtlDvpFundAllocationMapper
 * @since 1.0
 */
@Repository
public interface PtlDvpFundAllocationMapper {
    
    /**
     * 画面查询方法
     * @param map 请求参数
     * @return 查询结果集
     */
    List<PtlDvpFundAllocationDto> findPage(Map<String,Object> map);

    /**
     * 查询组合托管户账户
     * @param map 请求参数
     * @return 组合托管户账户集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPtlTghCounterAcctNo(Map<String,Object> map);
    
    /**
     * 查询组合证券账户
     * @param map 请求参数
     * @return 组合证券账户集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPtlSecTrdAcctNo(Map<String,Object> map);
    
    /**
     * 获取现金流水中的流水号
     * @param map 交易编号
     * @return 流水号
     * @see
     * @since 1.0
     */
    String selectSeq(Map<String,Object> map);
    
    /**
     * 获取划款指令
     * @param map 交易编号
     * @return 划款指令编号
     * @see
     * @since 1.0
     */
    String getPayOrderId(Map<String,Object> map);
    
    /**
     * 查询交易编号(根据指令编号)
     * @param map 参数
     * @return 交易编号
     * @see
     * @since 1.0
     */
    String getTradeId(Map<String,Object> map);
    
    /**
     * 查询dvp指令信息
     *
     * @param bizCode 交易编号
     * @param IE_FLAG_02 IE_FLAG_02
     * @return dvp指令信息
     * @see
     * @since 1.0
     */
    List<BussTrdTransferOrderBase> selectOrdersByBizCode(@Param("bizCode")String bizCode, @Param("IE_FLAG_02") String IE_FLAG_02);
    
    /**
     * 根据指令编号查询之前的组合现金流水记录
     *
     * @param paramMap 参数map
     * @return 组合现金流水记录
     * @see
     * @since 1.0
     */
    List<PtlCashPositionFlow> selectPtlCashPositionFlowList(Map<String, Object> paramMap);
    /**
     * 从3025接口表查询余额
     * @param paramMap 参数map
     * @return 余额
     */ 
    String getAmount(Map<String, Object> paramMap);
    
}
