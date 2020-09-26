/*
 * 文件名：CustomerReportConfig001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.system.customerreport.config.service.CustomerReportConfig001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.dao.ValBooksetDateSwitch001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;


/**
 * 报表定义一览Service
 * @author 陈卓
 * @version 1.0 2018年1月11日
 * @see CustomerReportConfig001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValBooksetDateSwitch001Service extends CommonExportService<ValBooksetDateSwitch001Dto> {

    /**
     * CustomerReportConfig001Mapper
     */
    @Autowired
    private ValBooksetDateSwitch001Mapper mapper;
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValBooksetDateSwitch001Dto> findPage(Map<String, Object> params) throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBooksetDateSwitch001/findPage", params, ValBooksetDateSwitch001Dto.class);
//        params.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
//        Page<ValBooksetDateSwitch001Dto> page = super.queryForPage(()-> mapper.queryPageList(params));
//    	return page;
    }

    /**
     * 获取产品组下的账套
     * @param object
     * @return
     */
    @DataSource(DataSourceType.OTHER)
    public List<SysBatchGrpPrdlink> findAllProduct(Map<String, Object> params) {
        List<SysBatchGrpPrdlink> list = mapper.findAllProduct(params);
        return list;
    }


    /**
     * 产品组
     * @param pageParam
     * @return
     */
    @DataSource(DataSourceType.OTHER)
    public Page<SelectOptionDto> findBooksetGroup(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBooksetDateSwitch001/findBooksetGroup", pageParam, SelectOptionDto.class);
//        return super.queryForPageForSelectReturn(()->mapper.selectBatchGroup(pageParam));
    }

    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getBooksetId(Map<String, Object> pageParam) {
        pageParam.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBooksetDateSwitch001/getBooksetId", pageParam, SelectOptionDto.class);
//        return super.queryForPageForSelectReturn(()->mapper.getBooksetId(pageParam));
    }
}
