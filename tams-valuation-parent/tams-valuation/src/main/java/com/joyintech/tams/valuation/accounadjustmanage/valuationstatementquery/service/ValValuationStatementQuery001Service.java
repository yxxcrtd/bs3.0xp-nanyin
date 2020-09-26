/*
 * 文件名：ValVoucherQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valuationstatementquery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationstatementquery.dao.ValValuationStatementQuery001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.valuationstatementquery.dto.ValValuationStatementQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dao.ValVoucherQuery001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValTablelist001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 记账凭证查询service
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery001Service
 * @since 2.0
 */
@Service
public class ValValuationStatementQuery001Service extends CommonExportService<ValValuationStatementQuery001Dto> {
	
    /**
     * ValVoucherQuery001Mapper
     */
//    @Autowired
//	private ValValuationStatementQuery001Mapper mapper;
    
    public List<ValValuationStatementQuery001Dto> queryFilepath(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValValuationStatementQuery001/queryFilepath", params, ValValuationStatementQuery001Dto.class);
//        return mapper.queryFilepath(params);
    }
	
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValValuationStatementQuery001Dto> findPage(Map<String, Object> params) throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValValuationStatementQuery001/findPage", params, ValValuationStatementQuery001Dto.class);
//    	return super.queryForPage(()-> mapper.queryPageList(params));
    }
    
}
