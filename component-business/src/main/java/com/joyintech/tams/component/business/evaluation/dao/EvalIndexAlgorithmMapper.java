/*
 * 文件名：EvalIndexAlgorithmMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.evaluation.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.component.business.evaluation.dto.PtlTargetReturnDto;

/**
 * 评价指标计算Mapper
 * 
 * @author zhangJin
 * @version 1.0 2019年10月8日
 * @see EvalIndexAlgorithmMapper
 * @since 1.0
 */
@Repository
public interface EvalIndexAlgorithmMapper {

    /**
     * 收益目标查询
     * 
     * @param date 日期
     * @param portfolioId 组合代码
     * @return
     * @see
     * @since 1.0
     */
    List<PtlTargetReturnDto> queryTargetReturnList(Map<String, Object> paramMap);

    /**
     * 浮动收益目标查询
     * 
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    List<PtlTargetReturnDto> queryFloatTargetReturnList(Map<String, Object> paramMap);

    /**
     * 查询最新业绩基准
     * 
     * @param paramMap 参数
     * @return 业绩基准
     * @see
     * @since 1.0
     */
    FinBaseRuleInfo queryLatestBenchmark(Map<String, Object> paramMap);

    /**
     * 浮动业绩基准
     * @param paramMap
     * @return
     */
    List<BigDecimal>queryFloatBenchmark(Map<String, Object> paramMap);

}
