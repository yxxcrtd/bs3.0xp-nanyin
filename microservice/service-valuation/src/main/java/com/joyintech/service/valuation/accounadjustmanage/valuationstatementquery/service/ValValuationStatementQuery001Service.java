/*
 * 文件名：ValVoucherQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.dao.ValValuationStatementQuery001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.dto.ValValuationStatementQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dao.ValVoucherQuery001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValTablelist001Dto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001Dto;

/**
 * 记账凭证查询service
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery001Service
 * @since 2.0
 */
@Service(value="serviceValValuationStatementQuery001Service")
public class ValValuationStatementQuery001Service extends CommonExportService<ValValuationStatementQuery001Dto> {
	
    /**
     * ValVoucherQuery001Mapper
     */
    @Autowired
	private ValValuationStatementQuery001Mapper mapper;
    
    public List<ValValuationStatementQuery001Dto> queryFilepath(Map<String, Object> params) {
        return mapper.queryFilepath(params);
    }
	
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValValuationStatementQuery001Dto> findPage(int page ,int length,Map<String, Object> params) throws Exception {
    	return super.queryForPage(page,length,()-> mapper.queryPageList(params));
    }
    
}
