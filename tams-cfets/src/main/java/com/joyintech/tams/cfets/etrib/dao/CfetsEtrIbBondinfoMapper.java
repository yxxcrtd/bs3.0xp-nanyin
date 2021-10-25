/*
 * 文件名：EtrBondFirstBiddingMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.cfets.etrib.dao;

import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.cfets.etrib.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  场内交易委托
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年11月14日
 * @see CfetsEtrIbBondinfoMapper
 * @since 1.0
 */
@Repository
public interface CfetsEtrIbBondinfoMapper {

    /**
     * 产品持仓信息分页查询
     * <功能详细描述>
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<CfetsEtrIbBondinfo001Dto> findPositionsPage(Map<String, Object> pageParam);

    /**
     * 根据资产代码获取相关交易信息列表
     * @param map
     * @return
     */
    List<CfetsEtrTrdProductDealDto> getTradeListByFinprodId(Map<String, Object> map);
    /**
     * 查询产品基本信息
     * <功能详细描述>
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<CfetsEtrIbBondPosinfo001Dto> selectPortfolioInfoist(Map<String, Object> pageParam);
    /**
     * 查询资产基本信息
     * <功能详细描述>
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<CfetsEtrIbBondPosinfo001Dto> selectFinprodInfoList(Map<String, Object> pageParam);

    /**
     * 指令管理查询分页查询
     *
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<CfetsEtrIbBondinfo001Dto> findEtrinfoPage(Map<String, Object> pageParam);

    CfetsEtrFinProductDto getEtrFinProductInfo(@Param("secId") String secId);

    List<String> getEtrFinExerciseInfo(@Param("secId") String secId);


    List<CfetsEtrIbBondPosinfo001Dto> queryPositionsPage(CfetsEtrIbBondPosiPage001RequestDto pageParam);

    Map queryPositionInfo(CfetsEtrIbBondPosiInfoRequestDto RequestDto);

    /**
     * 查询前一日持仓指标
     * @param baseMap
     * @return
     */
    List<BaseMap> selectLastCostAmt(BaseMap baseMap);

    /**
     * 当日交易数据查询
     * @param baseMap
     * @return
     */
    List<BaseMap> selectCurrTrdData(BaseMap baseMap);
    
    //    交易日取最大值
    Date getMaxTradeDate(String secId);
    //    交易日取最小值
    Date getMinTradeDate(String secId);
}
