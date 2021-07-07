/*
 * 文件名：EtrBondFirstBiddingMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etribbondinfo.dao;

import com.joyintech.service.entrust.etribbondinfo.dto.EtrFinProductDto;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrIbBondinfo001Dto;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrTrdProductDealDto;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *  场内交易委托
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年11月14日
 * @see EtrIbBondinfo001Mapper
 * @since 1.0
 */
@Component
public interface EtrIbBondinfo001Mapper {
    /**
     * 产品持仓信息分页查询
     * <功能详细描述>
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<EtrIbBondinfo001Dto> findPositionsPage(Map<String, Object> pageParam);
    /**
     * 指令管理查询分页查询
     *
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<EtrIbBondinfo001Dto> findZqzyPositionsPage(Map<String, Object> pageParam);

    /**
     * 根据资产代码获取相关交易信息列表
     * @param map
     * @return
     */
    List<EtrTrdProductDealDto> getTradeListByFinprodId(Map<String, Object> map);

    EtrFinProductDto getEtrFinProductInfo(Map<String, Object> map);

    List<String> getEtrFinExerciseInfo(Map<String, Object> map);
}
