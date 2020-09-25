/*
 * 文件名：RiskFactor003Service.java
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
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003selectDto;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dao.RiskFactor003Mapper;

/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @since 1.0
 */
@Service
public class RiskFactor003Service extends BaseService<RiskFactor003Dto> {

    @Autowired
    private RiskFactor003Mapper RiskFactor003Mapper;
    @Autowired
    private CreditAssetpoolMapper creditAssetpoolMapper;


    public Page<RiskFactor003Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
    	DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        RiskFactor003selectDto dto = RiskFactor003Mapper.selectdto(pageParam);
        //+ dto.getSelectcriteria() + " and " + dto.getInputcolumn() + "=" + dto.getInputvalue()
        String sql = "select " + dto.getSelectcolumn() + ",'" + dto.getFtrid() + "' as ftrid from " + dto.getTablename() + " where 1=1 " ;

        pageParam.put("sql", sql);
        pageParam.put("Selectcriteria", dto.getSelectcriteria());
        pageParam.put("Inputcolumn",  dto.getInputcolumn());
        pageParam.put("Inputvalue",  dto.getInputvalue());
        pageParam.put("dateperiod", dto.getDateperiod());
        pageParam.put("seccode", dto.getAsccolumn());
        pageParam.put("desccode", dto.getDesccolumn());
        pageParam.put("conditionext", dto.getConditionext());
        // return super.queryForPage(page, length, () -> RiskFactor003Mapper.queryForPage(pageParam));
        
        Page<RiskFactor003Dto> PAGE = super.queryForPage(page, length, () -> RiskFactor003Mapper.queryForPage(pageParam));
        DynamicDataSourceHolder.clearDataSource();
        return PAGE;
    }

    public RiskFactor003Dto selectdto(Map<String, Object> pageParam)
            throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        RiskFactor003Dto DAT = RiskFactor003Mapper.selectdtodate(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        // return RiskFactor003Mapper.selectdtodate(pageParam);
        return DAT;
    }

}
