/*
 * 文件名：ProductReportProcessorMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.batch.psbc.productreport.dao;

import com.joyintech.tams.batch.psbc.productreport.dto.RepProdMessageDto;
import com.joyintech.tams.batch.psbc.productreport.dto.RepProdStatusDto;
import com.joyintech.tams.common.base.entity.ChbBotAstPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthAssetPosition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 理财报表生成批处理Mapper
 *
 * @author jiangpeng
 * @version 1.0 2020/12/05
 * @see ProductReportProcessorMapper
 * @since 1.0
 */
@Repository
public interface ProductReportProcessorMapper {

    /**
     *
     * @param map
     * @return
     */
    List<ChbMonthAssetPosition> selectAssetposition(Map<String, Object> map);

    /**
     * 查询通道交易调用共同方法
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    List<ChbMonthAssetPosition> selectChlDealByCommon(ChbMonthAssetPosition pos);

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
     * 查询 报表生成配置表
     *
     * @param list 报表CODE
     * @return 报表生成配置表
     */
    List<Map<String, Object>> selectRepBatchConfig(List<String> list);

    /**
     * 获取 理财产品状态
     *
     * @param param 参数
     * @return 理财产品状态
     */
    List<RepProdStatusDto> selectRepProdStatusDto(Map<String, Object> param);

    /**
     * 查询产品状态
     *
     * @param param 参数
     * @return 查询产品状态
     */
    List<Map<String, Object>> selectProdStatus(Map<String, Object> param);

    /**
     * 查询产品交易金额
     *
     * @param param 参数
     * @return 查询产品状态
     */
    Map<String, Object> selectTradeAmt(Map<String, Object> param);

    /**
     * 获取 理财产品信息
     *
     * @param param 参数
     * @return 理财产品信息
     */
    List<RepProdMessageDto> selectRepProdMessageDto(Map<String, Object> param);

    /**
     * 删除 理财产品信息
     *
     * @param finprodId 产品代码
     * @param cDate 统计日期
     */
    int deleteRepProdStatus(@Param("finprodId")String finprodId, @Param("cDate")Date cDate);

    /**
     * 删除 理财产品信息
     *
     * @param finprodId 产品代码
     * @param cDate 统计日期
     */
    int deleteRepProdMessage(@Param("finprodId")String finprodId, @Param("cDate")Date cDate);

    /**
     * 插入 理财产品信息
     *
     * @param repProdStatusDto 理财产品信息
     */
    int insetRepProdStatus(RepProdStatusDto repProdStatusDto);

    /**
     * 插入 理财产品信息
     *
     * @param repProdMessageDto 理财产品信息
     */
    int insetRepProdMessage(RepProdMessageDto repProdMessageDto);

    /**
     * 查询 非标准化债权类
     * @param assetCode 资产代码
     * @return
     */
    List<Map<String,Object>> findFinprodType(@Param("assetCode")String assetCode);
}
