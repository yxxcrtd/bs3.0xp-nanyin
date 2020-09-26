/*
 * 文件名：ValAssetBookParameter001Service.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

@Service
@Transactional(rollbackFor = Exception.class)
public class ValAssetBookParameter001Service extends CommonExportService<ValAssetBookParameter002Dto>{
  
    /**
     * 获取查询结果
     * 
     * @param name 套帐名称
     * @return List<ValAssetBookParameter002Dto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<ValAssetBookParameter002Dto> getAllAssetbookPage(Map<String,Object> params) throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBookParameter001/getAllAssetbookPage", params, ValAssetBookParameter002Dto.class);
    }
    
    /**
     * 查询所有
     * @param params
     * @return
     */
    public List<ValAssetBookParameter002Dto> queryAllList(Map<String, Object> params){
    	return restTemplate.postForList(
    			ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBookParameter001/queryAllList",
                params, ValAssetBookParameter002Dto.class);
    }

    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getBooksetIdNew(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBookParameter001/getBooksetIdNew",
                pageParam, SelectOptionDto.class);
    }
 
	/**
     * 新建check
     * @return
     * @throws Exception
     */
	public List<ValAssetBookParameter002Dto> checkfornew(String booksetId) throws Exception {
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValAssetBookParameter001/checkfornew",
                booksetId, ValAssetBookParameter002Dto.class);
    }

}