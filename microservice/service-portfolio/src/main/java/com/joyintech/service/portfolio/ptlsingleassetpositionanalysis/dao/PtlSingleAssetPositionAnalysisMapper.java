/*
 * 文件名：PtlSingleAssetPositionAnalysisMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlsingleassetpositionanalysis.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlsingleassetpositionanalysis.dto.PtlSingleAssetPositionAnalysis001Dto;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysisDto;

/**
 * 单一资产持仓分析Mapper
 * @author wanghb
 * @version 1.0 2019年1月23日
 * @see PtlSingleAssetPositionAnalysisMapper
 * @since 1.0
 */
@Repository
public interface PtlSingleAssetPositionAnalysisMapper {
    /**
     * 查询界面查询事件
     * @param pageParam 页面参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<PtlSingleAssetPositionAnalysis001Dto> select(Map<String, Object> pageParam);
    /**
     * 资产代码的联想下拉框事件
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> map);
    /**
     * 查询界面导出事件
     *
     * @param pageParam 页面参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<PtlSingleAssetPositionAnalysis001Dto> selectForExport(Map<String, Object> pageParam);
    /**
     * 查询界面查询事件
     * @param pageParam 页面参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<PtlSingleAssetPositionAnalysis001Dto> selectAll(Map<String, Object> pageParam);
}
