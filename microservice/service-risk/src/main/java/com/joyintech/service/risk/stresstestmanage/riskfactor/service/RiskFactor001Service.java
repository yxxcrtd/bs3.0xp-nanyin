/*
 * 文件名：RskValtblPrtBotassetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenyunlong
 * 修改时间：2019年8月24日
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.stresstestmanage.riskfactor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.common.base.dao.CreditAssetpoolMapper;
import com.joyintech.tams.common.base.entity.CreditAssetpool;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dao.RiskFactor001Mapper;

/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @since 1.0
 */
@Service
public class RiskFactor001Service extends BaseService<RiskFactor001Dto> {

    @Autowired
    private RiskFactor001Mapper RiskFactor001Mapper;
    @Autowired
    private CreditAssetpoolMapper creditAssetpoolMapper;


    public Page<RiskFactor001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        Page<RiskFactor001Dto> PAGE = super.queryForPage(page, length, () -> RiskFactor001Mapper.queryForPage(pageParam));
        DynamicDataSourceHolder.clearDataSource();
        return PAGE;
    }


    public List<SelectOptionDto> selectRiskFactorMap(Map<String, Object> pageParam) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SelectOptionDto> LIST = RiskFactor001Mapper.queryftrname(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        return LIST;
    }
    public List<SelectOptionDto> selectRiskFactorMap2(Map<String, Object> pageParam) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SelectOptionDto> LIST = RiskFactor001Mapper.queryftrnamehost(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        return LIST;
    }
    public List<SelectOptionDto> selectRiskFactorMapType1(Map<String, Object> pageParam) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SelectOptionDto> LIST = RiskFactor001Mapper.queryftrnameType1(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        return LIST;
    }

    public List<SelectOptionDto> selectRiskFactorMapType2(Map<String, Object> pageParam) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SelectOptionDto> LIST = RiskFactor001Mapper.queryftrnameType2(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        return LIST;
    }

    public void update(RiskFactor001Dto dto) {
    	
        RiskFactor001Dto record = new RiskFactor001Dto();
        record.setEffstatus1(dto.getEffstatus1());
        record.setFtrid(dto.getFtrid());
//        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        RiskFactor001Mapper.update(record);
//        DynamicDataSourceHolder.clearDataSource();
    }
}
