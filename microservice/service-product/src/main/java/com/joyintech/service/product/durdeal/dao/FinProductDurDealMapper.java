/*
 * 文件名：FinProductDurDealMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.durdeal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokPositionPrd;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealCashCalcRuleDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealDetailDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealProdDto;
import com.joyintech.service.product.durdeal.dto.InterfProdInfoDto;

/**
 * 产品存续期交易查询微服务Mapper
 *
 * @author etliu
 * @version 1.0 2018年12月18日
 * @see FinProductDurDealMapper
 * @since 1.0
 */
@Repository
public interface FinProductDurDealMapper {

    /**
     * 产品代码下拉框
     * @param paramMap 参数
     * @return 下拉框列表
     * @throws Exception
     */
    List<SelectOptionDto> queryProdIdInfo(Map<String, Object> paramMap);

    /**
     * 分页查询画面交易数据
     * @param pageParam 参数
     * @return 交易数据
     * @see
     * @since 1.0
     */
    List<FinProductDurDealDto> selectMainData(Map<String, Object> pageParam);
    
    /**
     * 分页查询画明细数据
     * @param pageParam 参数
     * @return 交易数据
     * @see
     * @since 1.0
     */
    List<FinProductDurDealDto> selectDetailData(Map<String, Object> pageParam);

    /**
     * 查询产品基本信息
     * @param pageParam 参数
     * @return 产品基本信息
     * @see
     * @since 1.0
     */
    List<FinProductDurDealDto> selectProdBaseInfoData(Map<String, Object> pageParam);

    /**
     * 1.2检索项目（当日总份额）
     * @param pageParam 参数
     * @return 当日总份额数据
     * @see
     * @since 1.0
     */
    List<FinProductDurDealDto> selectTotalData(Map<String, Object> pageParam);

    /**
     * 查询现金流计算规则表数据
     *
     * @param pageParam 参数
     * @return 现金流计算规则表数据
     * @see
     * @since 1.0
     */
    List<FinProductDurDealCashCalcRuleDto> selectFinCashCalcRuleData(Map<String, Object> pageParam);

    /**
     * 查询产品头寸数据
     *
     * @param pageParam 参数
     * @return 产品头寸数据
     * @see
     * @since 1.0
     */
    List<BokPositionPrd> selectBokPositionPrdData(Map<String, Object> pageParam);

    /**
     * 查询场外金融产品估值数据
     *
     * @param pageParam 参数
     * @return 场外金融产品估值数据
     * @see
     * @since 1.0
     */
    List<FinProductValuation> selectFinProductValuationData(Map<String, Object> pageParam);

    /**
     * 收益型产品 非分层产品 明细数据查询
     *
     * @param pageParam 参数
     * @return 收益型产品 非分层产品 明细数据
     * @see
     * @since 1.0
     */
    List<FinProductDurDealDetailDto> selectYqNotFcProdDetailData(Map<String, Object> pageParam);

    /**
     * 收益型产品 分层产品 明细数据查询
     *
     * @param pageParam 参数
     * @return 收益型产品 分层产品 明细数据
     * @see
     * @since 1.0
     */
    List<FinProductDurDealDetailDto> selectYqFcProdDetailData(Map<String, Object> pageParam);

    /**
     * 净值型/货币型产品 明细数据查询
     *
     * @param pageParam 参数
     * @return 净值型/货币型产品 明细数据
     * @see
     * @since 1.0
     */
    List<FinProductDurDealDetailDto> selectNotYqProdDetailData(Map<String, Object> pageParam);

    /**
     * 查询出开放类型为‘开放式’且处理模式为'日添利','智能日添利','货币型','净值型'
     * 且到期日大于平台日的产品代码、金融产品代码、销售部门、处理模式
     *
     * @return 产品信息
     * @see
     * @since 1.0
     */
    List<FinProductDurDealProdDto> selectTargetProdData(Map<String, Object> pageParam);

    /**
     * 查询产品申赎信息接口数据
     *
     * @param pageParam 参数
     * @return 产品申赎信息接口数据
     * @see
     * @since 1.0
     */
    List<InterfProdInfoDto> selectInterfProdInfoData(Map<String, Object> pageParam);

    /**
     * 查询已同步交易数据
     *
     * @param pageParam 参数
     * @return 已同步交易数据
     * @see
     * @since 1.0
     */
    List<TrdFinProductDealTotal> selectSynchroedTradeData(Map<String, Object> pageParam);
    /**
     * 通过totalTradeId查询交易明细表中数据
     * add by wm 
     * @param pageParam 参数
     * @return 交易明细
     * @see
     * @since 1.0
     */
    List<TrdProductDeal> selectTrdProductDealByTotalTradId(Map<String, Object> pageParam);

    /**
     * 查询交易净值
     * @param pageParam
     * @return
     */
    BigDecimal selectYield(Map<String, Object> pageParam);
}
