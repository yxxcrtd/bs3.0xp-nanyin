/*
 * 文件名：RiskIndConditionLineMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.redisinit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.redisinit.dto.RiskIndConditionLine;

/**
 * 监督指标条件阈值Redis初始化-Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2020年8月31日
 * @see RiskIndConditionLineMapper
 * @since 1.0
 */
@Repository
public interface RiskIndConditionLineMapper {

    /**
     * 查询监督指标条件阈值信息
     * 
     * @param indIdList 指标列表
     * @return 监督指标条件阈值信息
     */
    List<RiskIndConditionLine> selectIndConditionLine(List<String> indIdList);

    /**
     * 自定条件启用的指标
     * 
     * @param ptlList 组合列表
     * @return 组合指标对
     */
    List<Map<String, Object>> selectDisabledInd(List<String> ptlList);
}
