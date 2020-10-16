/*
 * 文件名：RskValtblPrtBotassetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dao.MarketRiskStressTestView001Mapper;
import com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dto.MarketRiskStressTestView001Dto;
import com.joyintech.service.risk.conf.DataSourceType;

/**
 * 
 * @author lifangchen
 * @version 1.0 2019年8月24日
 * @since 1.0
 */
@Service("backMarketRiskStressTestView001Service")
public class MarketRiskStressTestView001Service extends BaseService<MarketRiskStressTestView001Dto>{

    @Autowired
    private MarketRiskStressTestView001Mapper marketRiskStressTestView001Mapper;
  
    
    public Page<MarketRiskStressTestView001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        Page<MarketRiskStressTestView001Dto> PAGE= super.queryForPage(page, length, () -> marketRiskStressTestView001Mapper.queryForPage(pageParam));
        DynamicDataSourceHolder.clearDataSource();
        return PAGE;
    }
    
    public List<SelectOptionDto> selectTestListMap(Map<String, Object> pageParam ){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SelectOptionDto> LIST=marketRiskStressTestView001Mapper.queryTestList(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        return LIST;
    }
    public List<MarketRiskStressTestView001Dto> initPage(Map<String, Object> pageParam ){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<MarketRiskStressTestView001Dto> LIST=marketRiskStressTestView001Mapper.initPage(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        return LIST;
    }
}
