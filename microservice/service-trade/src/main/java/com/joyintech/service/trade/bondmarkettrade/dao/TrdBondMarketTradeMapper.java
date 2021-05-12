package com.joyintech.service.trade.bondmarkettrade.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.bondmarkettrade.dto.TrdBondMarketTradeDto;
import com.joyintech.tams.common.base.entity.EtrSecPosition;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.common.base.entity.PtlSecPosition;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
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

    /**
     * 根据资产代码查询债券信用评级
     * @param  oCode
     * @return 债券信用评级list
     */
    List<MstBondCreditRating> selectByPrimaryKey(@Param("oCode")String oCode);

    /**
     * 根据汇总交易编号查询交易编号
     * @param  bizCode
     * @return 交易编号list
     */
    List<String> selectTrdProductDeals(@Param("bizCode")String bizCode);

    /**
     * 查询撤销时组合可用持仓流水
     * @param  tradeIds
     * @return 组合可用持仓流水list
     */
    List<PtlSecPositionFlowDto> selectRevokePtlPositinFlows(List<String> tradeIds);

    /**
     * 查询撤销时指令可用持仓流水
     * @param  bizCode
     * @return 指令可用持仓流水list
     */
    List<EtrSecPositionFlowDto> selectRevokeEtrPositinFlows(@Param("bizCode")String bizCode);

    /**
     * 查询是否有当日以及后续交易数据
     * @param dto
     * @return
     */
    int selectDealNums(TrdBondMarketTradeDto dto);

    /**
     * 根据汇总交易编号查询数据
     *
     * @param param 汇总交易编号
     * @see
     * @since 1.0
     */
    List<BaseTradeInfoDto> selectbaseTradeInfoDtos(Map<String,Object> param);

    /**
     * 根据交易日期和资产代码查询交易数据
     *
     * @param param 交易数据
     * @see
     * @since 1.0
     */
    List<Map<String,Object>> selectTrdProductDealsInfo(Map<String,Object> param);
    
    /**
     * 从风险实占信息里获取amount和number等信息
     * @param EventId
     * @return
     */
    Map<String,Object> getTradeData(String EventId);

    /**
     * 根据资产代码查询是否是存在上市流通交易的发行标的
     *
     * @param finprodId
     * @return 大于0则为是
     * @see
     * @since 1.0
     */
    int selectIssueAsset(@Param("finprodId") String finprodId);
}
