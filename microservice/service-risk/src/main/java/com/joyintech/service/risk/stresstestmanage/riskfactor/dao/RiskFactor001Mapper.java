/*
 * 文件名：RiskFactor001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenyunlong
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.stresstestmanage.riskfactor.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;

/**
 * 因子查询
 * 〈功能详细描述〉
 *
 * @author chenyunlong
 * @version 1.0 2019年8月23日
 * @see RiskFactor001Mapper
 * @since 1.0
 */
@Repository
public interface RiskFactor001Mapper {
    /**
     * 查询因子数据
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor001Mapper
     * @since 1.0
     */
    List<RiskFactor001Dto> queryForPage(Map<String, Object> pageParam);

    /**
     * 因子查询中因子名称查询下拉框
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor001Mapper
     * @since 1.0
     */
    List<SelectOptionDto> queryftrname(Map<String, Object> pageParam);
    List<SelectOptionDto> queryftrnamehost(Map<String, Object> pageParam);

    /**
     * 因子查询页面因子分类下拉框
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor001Mapper
     * @since 1.0
     */
    List<SelectOptionDto> queryftrnameType1(Map<String, Object> pageParam);

    /**
     * 因子查询页面因子风险分类下拉框-
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor001Mapper
     * @since 1.0
     */
    List<SelectOptionDto> queryftrnameType2(Map<String, Object> pageParam);

    /**
     * 修改因子启停状态
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor001Mapper
     * @since 1.0
     */
    void update(RiskFactor001Dto dto);
}
