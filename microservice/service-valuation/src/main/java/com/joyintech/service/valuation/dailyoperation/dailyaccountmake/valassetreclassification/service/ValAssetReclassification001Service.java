package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dao.ValAssetReclassification001Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto.ValAssetReclassification001Dto;

@Service(value="serviceValAssetReclassification001Service")
@Transactional(rollbackFor = Exception.class)
public class ValAssetReclassification001Service extends CommonExportService<ValAssetReclassification001Dto> {
	
	@Autowired
	private ValAssetReclassification001Mapper valAssetReclassification001Mapper;
	
	
	public Page<SelectOptionDto> getBookSetId(int page,int length,Map<String, Object> paMap){
	    return super.queryForPageForSelectReturn(page,length,()->valAssetReclassification001Mapper.getBookSetId(paMap));
	}
	
	public Page<SelectOptionDto> getFinProduct(int page,int length,Map<String, Object> paMap){
        return super.queryForPageForSelectReturn(page,length,()->valAssetReclassification001Mapper.getFinProduct(paMap));
    }
	
	
	public List<ValAssetReclassification001Dto> selectReclassList(Map<String, Object> params){
	    
	    if(StringUtil.isNotEmpty(params.get("booksetId"))){
	        params.put("booksets", params.get("booksetId").toString().split(","));
	    }
	    
        if(StringUtil.isNotEmpty(params.get("finprodId"))){
            params.put("finprodIds", params.get("finprodId").toString().split(","));
        }
	    
        List<ValAssetReclassification001Dto> list = valAssetReclassification001Mapper.selectReclassList(params);
        
	    return list;
	}

}
