/*
 * 文件名：RiskFactor003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenyunlong
 * 修改时间：2019年8月23日
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.stresstestmanage.riskfactor.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003selectDto;

/**
 * 因子历史数据
 * 〈功能详细描述〉
 *
 * @author chenyunlong
 * @version 1.0 2019年8月23日
 * @see RiskFactor003Mapper
 * @since 1.0
 */
@Repository
public interface RiskFactor003Mapper {
    /**
     * 查询因子历史数据
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor001Mapper
     * @since 1.0
     */
    List<RiskFactor003Dto> queryForPage(Map<String, Object> pageParam);
    /**
     * 查询因子配置信息
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor001Mapper
     * @since 1.0
     */
    RiskFactor003selectDto selectdto(Map<String, Object> pageParam);
    /**
     * 查询因子历史数据时间范围
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor001Mapper
     * @since 1.0
     */
    RiskFactor003Dto selectdtodate(Map<String, Object> pageParam);
}
