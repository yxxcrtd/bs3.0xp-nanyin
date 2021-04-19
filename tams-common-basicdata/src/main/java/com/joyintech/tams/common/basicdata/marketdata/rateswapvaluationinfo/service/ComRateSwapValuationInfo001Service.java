/*
 * 文件名：ValBondMarketInfo001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo001Dto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * 利率互换估值信息
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ComRateSwapValuationInfo001Service
 * @since 1.0
 */
@Service("comRateSwapValuationInfo001Service")
public class ComRateSwapValuationInfo001Service extends CommonExportService<ComRateSwapValuationInfo001Dto> {

    /**
     * 分页查询
     * @param params
     * @return
     */
    public Page<ComRateSwapValuationInfo001Dto> queryPageList(Map<String, Object> params){
        return restTemplate.postForPage(ServiceApplication.CommonBasicDataService + "/ComRateSwapValuationInfo001/findPage",
    			params, ComRateSwapValuationInfo001Dto.class);
    }

    /**
     * 查询所有
     * @param params
     * @return
     */
    public List<ComRateSwapValuationInfo001Dto> queryAllList(Map<String, Object> params){
    	return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComRateSwapValuationInfo001/queryAllList",
                params, ComRateSwapValuationInfo001Dto.class);
    }

	/**
     * 交易编号获取
     *
     * @return 返回的数据
     */
	public List<SelectOptionDto> getFinprodId2(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComRateSwapValuationInfo001/getFinprodId2",
                params, SelectOptionDto.class);
	}


}
