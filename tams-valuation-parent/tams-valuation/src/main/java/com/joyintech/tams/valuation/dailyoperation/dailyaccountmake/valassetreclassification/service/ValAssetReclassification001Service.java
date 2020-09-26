package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valassetreclassification.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto.ValAssetReclassification001Dto;

@Service
@Transactional(rollbackFor = Exception.class)
public class ValAssetReclassification001Service extends CommonExportService<ValAssetReclassification001Dto> {
	
	public Page<SelectOptionDto> getBookSetId(Map<String, Object> paMap){
//	    return super.queryForPageForSelectReturn(()->valAssetReclassification001Mapper.getBookSetId(paMap));
		return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetReclassification001/getBookSetId",
                paMap, SelectOptionDto.class);
	}
	
	public Page<SelectOptionDto> getFinProduct(Map<String, Object> paMap){
//        return super.queryForPageForSelectReturn(()->valAssetReclassification001Mapper.getFinProduct(paMap));
		return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetReclassification001/getFinProduct",
                paMap, SelectOptionDto.class);
	}
	
	
	public List<ValAssetReclassification001Dto> selectReclassList(Map<String, Object> params){
	    
//	    if(StringUtil.isNotEmpty(params.get("booksetId"))){
//	        params.put("booksets", params.get("booksetId").toString().split(","));
//	    }
//	    
//        if(StringUtil.isNotEmpty(params.get("finprodId"))){
//            params.put("finprodIds", params.get("finprodId").toString().split(","));
//        }
//	    
//        List<ValAssetReclassification001Dto> list = valAssetReclassification001Mapper.selectReclassList(params);
//        
//	    return list;
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetReclassification001/selectReclassList",
                params, ValAssetReclassification001Dto.class);
	}

}
