/*
 * 文件名：RiskFactor003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenyunlong
 * 修改时间：2019年8月24日
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.stresstestmanage.riskfactor.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dao.RiskFactorConfigMapper;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import com.joyintech.tams.common.base.dao.CreditAssetpoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @since 1.0
 */
@Service("backRiskFactorConfigService")
public class RiskFactorConfigService extends BaseService<RiskFactor003Dto> {

    @Autowired
    private RiskFactorConfigMapper RiskFactorConfigMapper;
    @Autowired
    private CreditAssetpoolMapper creditAssetpoolMapper;


    public List<RiskFactor001Dto> selectForPage( Map<String, Object> pageParam)
            throws Exception {

        pageParam.put("sql", pageParam.get("sql"));

        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<RiskFactor001Dto> PAGE = RiskFactorConfigMapper.queryForPage(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        return PAGE;
    }



}
