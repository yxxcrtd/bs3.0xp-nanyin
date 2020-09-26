/*
 * 文件名：ValAssetBookParameter002Service.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ValAssetBookParameter002Service extends CommonExportService<ValAssetBookParameter002Dto> implements BaseProcessService<ValAssetBookParameter002Dto> {

	/**
     * 获取参数 - 下拉框
     * @return
     * @throws Exception
     */
	public Page<SelectOptionDto> findParam(Map<String, Object> parameters) throws Exception {
		return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValAssetBookParameter002/findParam",
                parameters, SelectOptionDto.class);
    }
	
	/**
	 * 通过参数名，获取参数值
	 * @param pageParam
	 * @return
	 */
	public ValAssetBookParameter002Dto getParamValue(Map<String, Object> pageParam) {
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValAssetBookParameter002/getParamValue",
                pageParam, ValAssetBookParameter002Dto.class);
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValAssetBookParameter002Dto entity, OperationType type) {
		String action = StringUtil.EMPTY;
	       if (OperationType.NEW.equals(type)) {
	            action = "insert";
	        }
	        else if(OperationType.MODIFY.equals(type)){
	            action = "update";
	        }
    	restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBookParameter002/" + action,
    			entity, Integer.class);
    }
    
    /**
     * 获取选中记录
     * 
     * @param params
     * @return ValAssetBookParameter002Dto
     * @see
     * @since 1.0
     */
    public ValAssetBookParameter002Dto getByKey(Map<String,Object> params) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBookParameter002/getByKey", params, ValAssetBookParameter002Dto.class);
    }

}
