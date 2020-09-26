package com.joyintech.tams.valuation.assets.posit.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.assets.posit.dto.ValDespositAssetQuery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 存款类资产查询
 * 
 * @author wangtingting
 * @version 1.0 2018年7月19日
 * @see ValDespositAssetQuery001Service
 * @since 1.0
 */
@Service

public class ValDespositAssetQuery001Service extends CommonExportService<ValDespositAssetQuery001Dto> {

   
    /**
     * 根据条件获取表格信息
     * 
     * @param params Map<String, Object>
     * @return Page<ValDespositAssetQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ValDespositAssetQuery001Dto> findPage(Map<String, Object> params)
        throws Exception {
//
//        String finprodId = (String)params.get("finprodId");
//
//        // String profitType = (String)params.get("profitType");
//        String[] finprodIdArr = null;
//        if (StringUtil.isNotEmpty(finprodId)) {
//            finprodIdArr = finprodId.split(",");
//        }
//        // String[] profitTypeArr = null;
//        // if (StringUtil.isNotEmpty(profitType)) {
//        // profitTypeArr = profitType.split(",");
//        // }
//        params.put("finprodIdArr", finprodIdArr);
//        // params.put("profitTypeArr", profitTypeArr);
//        params.put("finprodType", DictCodeListExt1.金融产品类型_存款.getValue());
//        return super.queryForPage(() -> mapper.queryPageList(params));
    	 return restTemplate.postForPage(
                 ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery001/findPage",
                 params, ValDespositAssetQuery001Dto.class);
    }

    /**
     * 下拉框获取：资产
     * 
     * @param params Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryValFinproId(Map<String, Object> params) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery001/getFinprodId",
                params, SelectOptionDto.class);
    }


    /**
     * 导出功能
     * 
     * @param params Map<String, Object>
     * @return List<ValDespositAssetQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public List<ValDespositAssetQuery001Dto> findPageout(Map<String, Object> params){
//        params.put("finprodType", DictCodeListExt1.金融产品类型_存款.getValue());
//        return mapper.queryPageList(params);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery001/findAll",
                params, ValDespositAssetQuery001Dto.class);

    }

    /**
     * checkFinprodId
     * 
     * @param finprodId finprodId
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean checkFinprodId(String finprodId) {
    	
    	boolean flag= restTemplate.getForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery001/checkFinprodId?finprodId="+finprodId,
                 boolean.class);
    	 
        return flag;
//        return mapper.checkFinprodId(finprodId);

    }

  

}
