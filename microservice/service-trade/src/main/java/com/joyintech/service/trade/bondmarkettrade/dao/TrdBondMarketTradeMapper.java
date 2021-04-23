package com.joyintech.service.trade.bondmarkettrade.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.bondmarkettrade.dto.TrdBondMarketTradeDto;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 债券上市流通交易Mapper
 *
 * @author 马数凯
 * @version 1.0 2021年4月6日
 * @see TrdBondMarketTradeMapper
 * @since 1.0
 */
@Repository
public interface TrdBondMarketTradeMapper {
    /**
     * 检索债券上市流通交易列表
     *
     * @param paraMap 参数
     * @return 债券交易列表
     * @see
     * @since 1.0
     */
    List<TrdBondMarketTradeDto> selectDeal(Map<String, Object> paraMap);

    /**
     * 查询发行代码下拉框
     *
     * @param param 参数
     * @return 发行代码下拉框
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> param);

    /**
     * 查询上市代码下拉框
     *
     * @param param 参数
     * @return 上市代码下拉框
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAppearMarketId(Map<String, Object> param);

    /**
     * 根据资产代码和日期获取持仓数据
     * 
     * */
    List<PtlSecPosition> getPtlPosition(Map<String, Object> map);

    /**
     * 根据资产代码和日期获取指令持仓数据
     *
     * */
    List<EtrSecPosition> getEtrPosition(Map<String, Object> map);

    /**
     * 查询是否有当日交易数据
     * @param dto
     * @return
     */
    int selectDealNum(TrdBondMarketTradeDto dto);

    /**
     * 查询是否有当日交易数据
     * @param dto
     * @return
     */
    int selectRevokeDeal(TrdBondMarketTradeDto dto);
    
    /**
     * 查询是否有当日交易数据
     * @param dto
     * @return
     */
    int selectIsInWorkflow(TrdBondMarketTradeDto dto);

    /**
     * 检索债券上市流通交易实体
     *
     * @param totalTradeId 参数
     * @return 债券上市流通交易实体
     * @see
     * @since 1.0
     */
    TrdBondMarketTradeDto selectByTotalTradeId(@Param("totalTradeId")String totalTradeId);

    /**
     * 根据主键更新金融产品附属表表中数据
     *
     * @param entity 参数
     * @see
     * @since 1.0
     */
    void updateByPrimaryKey(TrdBondMarketTradeDto entity);

    
    List<MstBondCreditRating> selectByPrimaryKey(@Param("oCode")String oCode);

    List<String> selectTrdProductDeals(@Param("bizCode")String bizCode);

    List<PtlSecPositionFlowDto> selectRevokePtlPositinFlows(List<String> tradeIds);

    List<EtrSecPositionFlowDto> selectRevokeEtrPositinFlows(@Param("bizCode")String bizCode);

    /**
     * 查询是否有当日以及后续交易数据
     * @param dto
     * @return
     */
    int selectDealNums(TrdBondMarketTradeDto dto);
}
