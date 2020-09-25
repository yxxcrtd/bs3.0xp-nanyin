/*
 * 文件名：RiskFactor003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenyunlong
 * 修改时间：2019年8月23日
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.stresstestmanage.riskfactor.dao;

import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003selectDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 因子配置
 * 〈功能详细描述〉
 *
 * @author chenyunlong
 * @version 1.0 2019年8月23日
 * @see RiskFactorConfigMapper
 * @since 1.0
 */
@Repository
public interface RiskFactorConfigMapper {
    /**
     * 查询因子历史数据
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor001Mapper
     * @since 1.0
     */
    List<RiskFactor001Dto> queryForPage(Map<String, Object> pageParam);

}
