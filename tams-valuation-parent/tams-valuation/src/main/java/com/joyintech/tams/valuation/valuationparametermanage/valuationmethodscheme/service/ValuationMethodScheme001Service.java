/*
 * 文件名：ValuationMethodScheme001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme001Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 
 * 估值方法方案
 * @author zhangwei
 * @version 1.0 2018年8月27日
 * @see ValuationMethodScheme001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValuationMethodScheme001Service extends CommonExportService<ValuationMethodScheme001Dto>{
	
	
	/**
     * 获取估值方案 - 下拉框
     * @return
     * @throws Exception
     */
	public List<SelectOptionDto> findScheme(Map<String, Object> parameters) throws Exception {
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme001/findScheme",
                parameters, SelectOptionDto.class);
		
    }
	
	/**
     * 获取估值方案 - 分页
     * @return
     * @throws Exception
     */
	public Page<ValuationMethodScheme001Dto> findSchemeByPage(Map<String, Object> parameters) throws Exception {
    	return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme001/findSchemeByPage",
                parameters, ValuationMethodScheme001Dto.class);
    }

	 /**
     * 获取估值方案详情 - 分页
     * @return
     * @throws Exception
     */
	public Page<ValuationMethodScheme001Dto> findSchemeDetailByPage(Map<String, Object> parameters) throws Exception {
		return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme001/findSchemeDetailByPage",
                parameters, ValuationMethodScheme001Dto.class);
    }
	
	/**
     * 获取估值方案详情 - 分页 : 特殊资产估值
     * @return
     * @throws Exception
     */
	public Page<ValuationMethodScheme001Dto> findSpecialAssetsByPage(Map<String, Object> parameters) throws Exception {
		return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme001/findSpecialAssetsByPage",
                parameters, ValuationMethodScheme001Dto.class);
    }
	
	
	/**
     * 获取估值方案详情 - 不分页
     * @return
     * @throws Exception
     */
	public List<ValuationMethodScheme001Dto> findSchemeDetail(Map<String, Object> parameters) throws Exception {
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme001/findSchemeDetail",
                parameters, ValuationMethodScheme001Dto.class);
    }
	
	/**
     * 获取估值方案详情 - 不分页 : 特殊资产估值
     * @return
     * @throws Exception
     */
	public List<ValuationMethodScheme001Dto> findSpecialAssets(Map<String, Object> parameters) throws Exception {
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme001/findSpecialAssets",
                parameters, ValuationMethodScheme001Dto.class);
    }
	
	/**
     * 获取产品代码 - 特殊资产查询下拉框
     * @return
     * @throws Exception
     */
	public List<SelectOptionDto> findFinprod(Map<String, Object> parameters) throws Exception {
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme001/findFinprod",
                parameters, SelectOptionDto.class);
    }
	

//    /**
//     * 组装方案简述
//     */
//    private void assembleSchDesc(List<ValuationMethodScheme001Dto> list) {
//    	if(CollectionUtils.isNotEmpty(list)) {
//    		for (ValuationMethodScheme001Dto dto : list) {
//    			String schemeDesc = dto.getSchemeName() ;
//				dto.setScheme(schemeDesc);
//			}
//    	}
//    }
    
    /**
     * 通用联动查询
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
	public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme001/getLinkSelectOption",
                param, SelectOptionDto.class);
	}
	
	
	/**
     * 是否存在默认估值方案
     * @return
     * @throws Exception
     */
	public boolean getIsdefault() throws Exception {
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme001/getIsdefault", null, boolean.class);
    }
}
