package com.joyintech.tams.svreport.position.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.position.dto.ChbAssetPosition;

public interface ChbAssetPositionMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(ChbAssetPosition record);

    int insertSelective(ChbAssetPosition record);

    ChbAssetPosition selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(ChbAssetPosition record);

    int updateByPrimaryKey(ChbAssetPosition record);
    
//    /**
//     * 
//     * 查询今日投资交易合计
//     * 根据同一个资产+投资类型+产品+通道分组
//     * @return
//     * @see
//     * @since 1.0
//     */
//    List<ChbAssetPosition> selectInvestTrade(Date cdate);
    /**
     * 查询当日买入交易汇总
     * @param cdate
     * @return
     */
    void selectInvestTradeBuy(Map<String, Object> param);
    /**
     * 查询当日卖出交易汇总
     * @param cdate
     * @return
     */
    void selectInvestTradeSale(Map<String, Object> param);
    
    
    /**
     * 
     * 将昨日的头寸，复制到今日，作为日出头寸
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    int insertYsdPosition(Map<String, Object> param);
    
    /**
     * 
     * 查询今日头寸
     * 支持的查询条件：资产+投资类型+产品+通道分组
     * @return
     * @see
     * @since 1.0
     */
    List<ChbAssetPosition> select(ChbAssetPosition pos);
    
    /**
     * 
     * 更新今日头寸
     * 更新条件：资产+投资类型+产品+通道分组
     * @return
     * @see
     * @since 1.0
     */    
    int update(ChbAssetPosition pos);
    /**
     * 跑批时先删后插
     * @param cdate
     * @return
     */
    int deleteByDate(Map<String, Object> param);
    /**
     * 查询资产持仓份额
     * @param pos
     * @return
     */
    BigDecimal selectAccuqtyByModel(ChbAssetPosition pos);
    /**
     * 查询剩余现金 产品端 当日
     * @param cdate
     * @return
     */
    void selectPrdCashByModel(Map<String, Object> param);
    /**
     * 查询剩余现金 资产端穿透前 当日
     * @param cdate
     * @return
     */
    void selectAssetCashByModel(Map<String, Object> param);
    /**
     * 查询头寸剩余现金 
     * @param cdate
     * @return
     */
    void selectAssetPositionCashByModel(Map<String, Object> param);
    
    /**
     * 查询最小跑批日
     * @param bookDate
     * @param prodIdList
     * @return
     */
    Date queryMinSettdate(@Param("cdate")Date batchDate,@Param("prodids")List<String> prodids,
            @Param("assetcodes")List<String>assetcodes);

    /**
     * 功能:根据产品代码、资产代码查询资产基础指标的最小跑批日
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年7月2日
     * @param batchDate
     * @param prodids
     * @param assetcodes
     * @return Date
     */
    Date selectMinAssetsBasisDataByProdidAndAssetCode(@Param("cdate")Date batchDate,
            @Param("prodids")List<String> prodids, @Param("assetcodes")List<String> assetcodes,@Param("isBtm")String isBtm);

    /**
     * 功能:查询资产估值信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年7月2日
     * @param batchDate 跑批日
     * @param prodids 产品代码
     * @param assetcodes 资产代码
     * @return 
     * List<ChbAssetPosition>
     */
    List<ChbAssetPosition> selectAssetsBasisData(@Param("cdate")Date batchDate,
            @Param("prodids")List<String> prodids, @Param("assetcodes")List<String> assetcodes);

    /**
     * 功能:根据产品代码、资产代码查询上游科目余额的最小跑批日
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年7月2日
     * @param batchDate
     * @param prodids
     * @param assetcodes
     * @return Date
     */
    Date selectMintBalanceDate(@Param("cdate")Date batchDate,
            @Param("prodids")List<String> prodids, @Param("assetcodes")List<String> assetcodes);

    /**
     * 功能:根据产品代码、资产代码查询上游科目余额的最小跑批日-中债子系统
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年7月2日
     * @param batchDate
     * @param prodids
     * @param assetcodes
     * @return Date
     */
    Date selectMintBalanceDateSubSystem(@Param("cdate")Date batchDate,
            @Param("prodids")List<String> prodids, @Param("assetcodes")List<String> assetcodes);

    /**
     * 功能:查询剩余现金信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年7月2日
     * @param batchDate 跑批日
     * @param prodids 产品代码
     * @param assetcodes 资产代码
     * @return 
     * List<ChbAssetPosition>
     */
    List<ChbAssetPosition> selectBalanceData(@Param("cdate")Date batchDate,
            @Param("prodids")List<String> prodids, @Param("assetcodes")List<String> assetcodes);

    /**
     * 功能:查询剩余现金信息-中债子系统
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年7月2日
     * @param batchDate 跑批日
     * @param prodids 产品代码
     * @param assetcodes 资产代码
     * @return 
     * List<ChbAssetPosition>
     */
    List<ChbAssetPosition> selectBalanceDataSubSystem(@Param("cdate")Date batchDate,
            @Param("prodids")List<String> prodids, @Param("assetcodes")List<String> assetcodes);

}