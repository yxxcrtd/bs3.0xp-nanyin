/*
 * 文件名：DataExtraction001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.system.dataextraction.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.system.dataextraction.dao.DataExtraction001Mapper;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme001Dto;

/**
 * 数据提取service
 * @author wangqidong
 * @version 1.0 2019年2月18日
 * @see DataExtraction001Service
 * @since 1.0
 */

@SuppressWarnings("rawtypes")
@Service
public class DataExtraction001Service extends CommonExportService{

    @Autowired
    private DataExtraction001Mapper mapper;
    
    private static final String OTHER = "base";
    
    /**
     * 查询
     * @param replace
     * @return
     * @see
     * @since 1.0
     */
    public List<LinkedHashMap<String, Object>> selectAllData(String dataSource, String sqlText) {
        String dataSourceType = OTHER.equals(dataSource) ?  DataSourceType.OTHER : DataSourceType.MASERT;
        DynamicDataSourceHolder.putDataSource(dataSourceType);
        List<LinkedHashMap<String, Object>> list = mapper.selectAllData(sqlText);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    /**
     * 分页查询
     * @param replace
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
	@SuppressWarnings("unchecked")
	public Page<LinkedHashMap<String, Object>> querySelectAllDataPage(String dataSource, String sqlText) throws Exception {
		return super.queryForPageForSelectReturn(()-> this.selectAllData(dataSource,sqlText));
    }
	
//	/**
//     * 查询
//     * @param replace
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public List<LinkedHashMap> selectAllData(String dataSource, String sqlText) {
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("dataSource", dataSource);
//        map.put("sqlText", sqlText);
//        return  restTemplate.postForList(
//            ServiceApplication.VALUATION_SERVICE_NAME + "/DataExtraction001/selectAllData",
//            map, LinkedHashMap.class);
//    }
//    /**
//     * 分页查询
//     * @param replace
//     * @return
//     * @throws Exception 
//     * @see
//     * @since 1.0
//     */
//    @SuppressWarnings("unchecked")
//    public Page<LinkedHashMap> querySelectAllDataPage(String dataSource, String sqlText) throws Exception {
//        //return super.queryForPageForSelectReturn(()-> this.selectAllData(dataSource,sqlText));
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("dataSource", dataSource);
//        map.put("sqlText", sqlText);
//        return restTemplate.postForPage(
//            ServiceApplication.VALUATION_SERVICE_NAME + "/DataExtraction001/querySelectAllDataPage",
//            map, LinkedHashMap.class);
//    }
}
