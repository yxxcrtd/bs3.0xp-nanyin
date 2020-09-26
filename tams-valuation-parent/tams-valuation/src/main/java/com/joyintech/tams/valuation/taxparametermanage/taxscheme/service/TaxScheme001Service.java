/*
 * 文件名：TaxScheme001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.taxparametermanage.taxscheme.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme001Dto;

/**
 * 税务方案 - 主界面公用 
 * @author zhangwei
 * @version 1.0 2018年10月11日
 * @see TaxScheme001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxScheme001Service extends CommonExportService<TaxScheme001Dto>{
	
	/**
     * 获取税务方案 - 分页
     * @return
     * @throws Exception
     */
	public Page<TaxScheme001Dto> findSchemeByPage(Map<String, Object> parameters) throws Exception {
	    return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findScheme",
            parameters, TaxScheme001Dto.class);
    }

	/**
     * 获取增值税则表 - 分页
     * @return
     * @throws Exception
     */
	public Page<TaxScheme001Dto> findVatTaxByPage(Map<String, Object> parameters) throws Exception {
	    return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findVatTax",
            parameters, TaxScheme001Dto.class);
	}
	
	/**
     * 获取增值税则表 - 不分页
     * @return
     * @throws Exception
     */
	public List<TaxScheme001Dto> findVatTax(Map<String, Object> parameters) throws Exception {
	    return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findVatTax",
            parameters, TaxScheme001Dto.class);
	}
	
	/**
     * 获取附加税则表 - 分页
     * @return
     * @throws Exception
     */
	public Page<TaxScheme001Dto> findAdtTaxByPage(Map<String, Object> parameters) throws Exception {
	    return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findAdtTax",
            parameters, TaxScheme001Dto.class);
	}
	
	/**
     * 获取附加税则表 
     * @return
     * @throws Exception
     */
	public List<TaxScheme001Dto> findAdtTax(Map<String, Object> parameters) throws Exception {
	    return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findAdtTax",
            parameters, TaxScheme001Dto.class);
	}

    /**
     * 获取资产应税表 - 分页
     * @param pageParam
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<TaxScheme001Dto> findTaxableByPage(Map<String, Object> pageParam) throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findTaxable",
            pageParam, TaxScheme001Dto.class); 
    }
    
    /**
     * 获取资产应税表 - 不分页
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<TaxScheme001Dto> findTaxable(Map<String, Object> pageParam) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findTaxable",
            pageParam, TaxScheme001Dto.class); 
    }

    
    /**
     * 获取资产代码 - 下拉框
     * 
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> findAsset(Map<String, Object> pageParam) throws Exception {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findAsset",
            pageParam, SelectOptionDto.class); 
    }
    
    /**
     * 查询参数设置 - 分页
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<TaxScheme001Dto> findConfigByPage(Map<String, Object> pageParam) throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findConfig",
            pageParam, TaxScheme001Dto.class); 
    }
    
    /**
     * 查询参数设置 - 不分页
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<TaxScheme001Dto> findConfig(Map<String, Object> pageParam) throws Exception {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findConfig",
            pageParam, TaxScheme001Dto.class); 
    }

    /**
     * 获取会计场景计税配置 - 分页
     * @param pageParam
     * @return
     */
    public Page<TaxScheme001Dto> findTaxSchaBokConfigByPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findTaxSchaBokConfig",
            pageParam, TaxScheme001Dto.class);
    }

    /**
     * 查询参数设置 - 不分页
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<TaxScheme001Dto> findTaxSchaBokConfig(Map<String, Object> pageParam) throws Exception {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme001/findTaxSchaBokConfig",
            pageParam, TaxScheme001Dto.class);
    }
}
