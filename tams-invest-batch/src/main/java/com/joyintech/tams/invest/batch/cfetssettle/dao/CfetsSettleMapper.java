/*
 * 文件名：ResetBaseRuleInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：sjj
 * 修改时间：2021年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.cfetssettle.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.TrdBankManualSettleInfo;
import com.joyintech.tams.invest.batch.cfetssettle.dto.CfetsSettleDealInfo;
import com.joyintech.tams.invest.batch.cfetssettle.dto.CfetsSettleAllInfoDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * reader相关业务处理
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2021年11月19日
 * @see CfetsSettleMapper
 * @since 1.0
 */
@Repository
public interface CfetsSettleMapper {

    /**
     * 查询待交收数据List
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    List<CfetsSettleAllInfoDto> selectAllSettleToDoList(Map<String, Object> paramMap);

    /**
     * 删除交收信息表
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    void deleteTodayCfetsSettleInfo(Map<String, Object> paramMap);

    /**
     * 删除交收明细表
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    void deleteTodayCfetsSettleFlow(Map<String, Object> paramMap);

    /**
     * 删除交收流水表
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    void deleteTodayCfetsSettleDetail(Map<String, Object> paramMap);

    /**
     * 插入/更新交收信息表
     * @param trdBankManualSettleInfo
     * @return
     * @see
     * @since 1.0
     */
    void mergeInfo(TrdBankManualSettleInfo trdBankManualSettleInfo);

    /**
     * 成交确认明细查询
     *
     * @param tradeId 参数
     * @return List<CfetsManualSettleDealInfo>
     */
    List<CfetsSettleDealInfo> getDealDetailsByTradeId(@Param("tradeId") String tradeId);

    /**
     * 查询证券账户
     * @param param
     * @return
     */
    String qryPortfolioSecAcctId(Map<String, Object> param);

    /**
     * 查询托管户
     * @param param
     * @return
     */
    String qryPortfolioCashAcctId(Map<String, Object> param);
    /**
     * 回购质押券信息
     * @param tradeId
     * @return
     */
    List<PledgeBondInfoDto> getPledgeListByTradeId(@Param("tradeId") String tradeId);

}
