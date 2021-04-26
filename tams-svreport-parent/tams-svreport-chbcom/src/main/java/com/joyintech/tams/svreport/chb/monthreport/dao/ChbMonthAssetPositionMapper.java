package com.joyintech.tams.svreport.chb.monthreport.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthPrdPosition;
import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbBotAstPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthAssetPosition;

public interface ChbMonthAssetPositionMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(ChbMonthAssetPosition record);

    int insertSelective(ChbMonthAssetPosition record);

    ChbMonthAssetPosition selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(ChbMonthAssetPosition record);

    int updateByPrimaryKey(ChbMonthAssetPosition record);
    
    /**
     * 
     * 查询直投交易
     * 含产品直投底层资产，产品买通道，产品投资管计划、委外、私募资产
     * 查询条件:投资交易表，不是底层交易 并且 通道ID为空
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    void selectAssetposition(ChbMonthAssetPosition pos);
    
    /**
     * 查询通道交易
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    List<ChbMonthAssetPosition> selectChlDeal(ChbMonthAssetPosition pos);

    /**
     * 查询资管计划交易
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    List<ChbBotAstPosition> selectPlanDeal(ChbBotAstPosition pos);
    /**
     * 查询穿透资产信息
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
   ChbMonthAssetPosition selectPlanInfo(ChbMonthAssetPosition pos);
    
    /**
     * 功能:根据月度报表日期获取资产头寸数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年1月31日
     * @param startDate 月初日期
     * @param endDate 月末日期
     * @return List<ChbBotAstPosition>
     */
    List<ChbMonthAssetPosition> selectByMonthDate(@Param("startDate")String startDate,@Param("endDate")String endDate,@Param("prodids")List<String> prodids);
    /**
     * 跑批时先删后插
     * @param cdate
     * @return
     */
    int deleteByDate(ChbMonthPrdPosition para);
    /**
     * 查询剩余现金 产品端
     * @param cdate
     * @return
     */
    List<ChbMonthAssetPosition> selectPrdCashByDate(Date cdate);
    /**
     * 查询剩余现金 资产端穿透前
     * @param cdate
     * @return
     */
    List<ChbMonthAssetPosition> selectAssetCashByDateA(Date cdate);
    /**
     * 查询剩余现金 资产端穿透后增加
     * @param cdate
     * @return
     */
    List<ChbMonthAssetPosition> selectAssetCashByDateB(Date cdate);
    /**
     * 查询剩余现金 底层剩余
     * @param cdate
     * @return
     */
    BigDecimal selectBotCash(Date cdate);
    
    List<ChbMonthAssetPosition> selectBotCashList(Date cdate);
    
    /**
     * 
     * @param cdate
     * @return
     */
    List<ChbMonthAssetPosition> selectBotCashListEx(@Param("cdate")Date cdate,@Param("prodids")List<String> prodids);

    /**
     *
     * 查询直投交易评级调用共同方法
     * 含产品直投底层资产，产品买通道，产品投资管计划、委外、私募资产
     * 查询条件:投资交易表，不是底层交易 并且 通道ID为空
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    void selectAssetpositionByCommon(ChbMonthAssetPosition pos);


    /**
     * 查询穿透资产信息调用共同方法
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    ChbMonthAssetPosition selectPlanInfoByCommon(ChbMonthAssetPosition pos);

    /**
     * 查询通道交易调用共同方法
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    List<ChbMonthAssetPosition> selectChlDealByCommon(ChbMonthAssetPosition pos);
    
}