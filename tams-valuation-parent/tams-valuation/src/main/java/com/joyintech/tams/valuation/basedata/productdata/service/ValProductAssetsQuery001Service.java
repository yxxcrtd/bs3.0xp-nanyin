/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.productdata.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.basedata.productdata.dto.ValProductAssetsQuery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.service.ValFinprodTradeQuery001Service;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service
public class ValProductAssetsQuery001Service extends CommonExportService<ValProductAssetsQuery001Dto> {
	
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValProductAssetsQuery001Dto> findPage(Map<String, Object> params) throws Exception {
//        return super.queryForPage(()-> this.queryPageList(params));
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValProductAssetsQuery001/findPage",
                params, ValProductAssetsQuery001Dto.class);
    }
    
    public List<ValProductAssetsQuery001Dto> queryPageList(Map<String, Object> params) {
//      //获取交易方向
//        String finprodType = (String)params.get("finprodType");
//        //交易方向多选，转为数组
//        String[] finprodTypeArr = null;
//        if(StringUtil.isNotEmpty(finprodType)){
//            finprodTypeArr = finprodType.split(",");
//        }
//        params.put("finprodTypeArr", finprodTypeArr);
//        return mapper.queryPageList(params);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValProductAssetsQuery001/queryPageList",
                params, ValProductAssetsQuery001Dto.class);
    }
    
}
