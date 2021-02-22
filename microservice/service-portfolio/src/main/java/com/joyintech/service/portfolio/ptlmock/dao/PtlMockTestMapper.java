/*
 * 文件名：PtlSingleAssetPositionAnalysisMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlmock.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlmock.dto.PtlMockTestDto;
import com.joyintech.service.portfolio.ptlmock.dto.PtlMockTestTradeInfoDto;
import com.joyintech.tams.common.base.entity.MockTestNetTrend;
import com.joyintech.tams.common.base.entity.MockTestRsk;
import com.joyintech.tams.common.base.entity.MockTestTaa;

/**
 * 模拟试算
 *
 * @author yjm
 * @version 1.0 2020/04/14
 * @see PtlMockTestMapper
 * @since 1.0
 */
@Repository
public interface PtlMockTestMapper {

    /**
     * 根据模拟编号查询模拟试算具体信息
     * @param map 参数map
     * @return 模拟试算具体信息
     */
    PtlMockTestDto getInitInfo(Map<String, Object> map);

    /**
     * 组合代码下拉框选项获取
     * 
     * @param map 参数map
     * @return 组合代码下拉框选项
     */
    List<SelectOptionDto> getPtlInfo(Map<String, Object> map);

    /**
     * 画面查询方法
     * 
     * @param pageParam 请求参数
     * @return 查询结果集
     */
    List<PtlMockTestDto> selectForPage(Map<String, Object> pageParam);

    /**
     * 根据模拟编号删除模拟金融产品交易表数据
     * @param mockId 模拟编号
     */
    void deleteMockProductDeal(@Param("mockId") String mockId);
    
    /**
     * 根据模拟编号删除模拟测算净值表数据
     * @param mockId 模拟编号
     */
    void deleteMockTestNetTrend(@Param("mockId") String mockId);

    /**
     * 根据模拟编号删除模拟交易类金融产品表数据
     * @param mockId 模拟编号
     */
    void deleteMockTradeProduct(@Param("mockId") String mockId);

    /**
     * 根据模拟编号删除模拟测算资产配置表数据
     * @param mockId 模拟编号
     */
    void deleteMockTestTaa(@Param("mockId") String mockId);

    /**
     * 根据模拟编号删除模拟测算风险测算表数据
     * @param mockId 模拟编号
     */
    void deleteMockTestRsk(@Param("mockId") String mockId);

    /**
     * 获取资产代码下拉框选项
     * @param map 参数map
     * @return 资产代码下拉框选项
     */
    List<SelectOptionDto> getFinProdId(Map<String, Object> map);

    /**
     * 根据模拟编号查询模拟金融产品交易表数据
     * @param map 参数map
     * @return 模拟金融产品交易表数据
     */
    List<PtlMockTestTradeInfoDto> getMockProductDeal(Map<String, Object> map);

    /**
     * 根据模拟编号查询模拟测算风险测算表数据
     * @param map 参数map
     * @return 模拟测算风险测算表数据
     */
    List<MockTestRsk> selectMockTestRskData(Map<String, Object> map);

    /**
     * 根据模拟编号查询模拟测算资产配置表数据
     * @param map 参数map
     * @return 模拟测算资产配置表数据
     */
    List<MockTestTaa> selectMockTestTaaData(Map<String, Object> map);

    /**
     * 根据模拟编号查询模拟模拟测算净值数据表数据
     * @param map 参数map
     * @return 模拟测算净值数据表数据
     */
    List<MockTestNetTrend> selectMockTestNetTrendData(Map<String, Object> map);

    /**
     * 查询试算占用数量
     * @param map
     * @return
     */
    BigDecimal getUsedMockPosition(Map<String, Object> map);
}
