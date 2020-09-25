/*
 * 文件名：RskValtblPrtBotassetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dto.MarketRiskStressTestView001Dto;

/**
 * 底层资产映射
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年8月23日
 * @see MarketRiskStressTestView001Mapper
 * @since 1.0
 */
@Repository
public interface MarketRiskStressTestView001Mapper {

    List<MarketRiskStressTestView001Dto> queryForPage(Map<String, Object> pageParam);
    
    List<SelectOptionDto> queryTestList(Map<String, Object> pageParam);
    
    List<MarketRiskStressTestView001Dto> initPage(Map<String, Object> pageParam);
}
