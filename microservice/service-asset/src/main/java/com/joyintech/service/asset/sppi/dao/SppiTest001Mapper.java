/*
 * 文件名：SppiTest001Mapper
 * 版权：Copyright 2017-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020-10-12
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.asset.sppi.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.sppi.dto.SppiImportDto;
import com.joyintech.service.asset.sppi.dto.SppiTemplateDto;
import com.joyintech.service.asset.sppi.dto.SppiTest001Dto;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.SppiTestQa;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 资产标的SPPI测试结果查询
 *
 * @author Jiaoxujin
 * @version 1.0 2020-10-12
 */
@Repository
public interface SppiTest001Mapper {
    /**
     * 查询SPPI测试结果列表
     * @param pageParam Map&lt;String, Object&gt;
     * @return List&lt;SppiTest001Dto&gt;
     */
    List<SppiTest001Dto> queryForPage(Map<String, Object> pageParam);

    /**
     * 查询适用于SPPI测试的资产标的列表<br />
     * 仅能查询出债券类资产、项目类资产、计划类利率型资产
     * @param paramMap Map&lt;String, Object&gt;
     * @return List&lt;SelectOptionDto&gt;
     */
    List<SelectOptionDto> querySppiAssetList(Map<String, Object> paramMap);

    /**
     * 嵌套查询SPPI测试详细信息
     * @param sppiTestId String
     * @param finprodId String
     * @return SppiTemplateDto
     */
    SppiTemplateDto selectSppiTestDetail(@Param("sppiTestId") String sppiTestId,
                                         @Param("finprodId") String finprodId);

    /**
     * 批量插入SPPI测试问题记录
     * @param dataList List&lt;SppiTestQa&gt;
     * @return int
     */
    int batchInsertSppiTestQa(List<SppiTestQa> dataList);

    /**
     * 根据testId删除SPPI测试问题记录
     * @param sppiTestId
     * @return int
     */
    int deleteByTestId(String sppiTestId);

    /**
     * 查询金融产品有效交易数
     * @param finprodId
     * @return int
     */
    int queryValidTradeCount(String finprodId);
    
    /**
     * 查询重复债券ID
     * @param list
     * @return
     */
    List<SppiImportDto> getBondId(List<SppiImportDto> list);
    
    /**
     * 查询中债导入数据信息
     * @param finprodId
     * @return
     */
    SppiImportDto selectByBondId(String finprodId);

    List<FinProduct> getBondAssetInfo(Map<String, Object> param);
}
