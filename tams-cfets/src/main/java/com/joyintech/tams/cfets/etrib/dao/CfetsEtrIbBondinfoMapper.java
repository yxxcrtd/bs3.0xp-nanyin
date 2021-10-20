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

import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosinfo001Dto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondinfo001Dto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrTrdProductDealDto;
import org.springframework.stereotype.Repository;

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


    Map findPositionInfo(Map<String, Object> pageParam);
}
