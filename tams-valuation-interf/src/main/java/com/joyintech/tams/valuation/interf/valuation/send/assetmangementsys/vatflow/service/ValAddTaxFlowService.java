/*
 * 文件名：ValAddTaxFlowService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年4月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dao.ValAddTaxFlowMapper;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowDto;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowprocessorResult;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 增值税流水回传接口
 * @author wangqidong
 * @version 1.0 2019年4月23日
 * @see ValAddTaxFlowService
 * @since 1.0
 */
@Service
public class ValAddTaxFlowService {

    @Autowired
    private ValAddTaxFlowMapper valAddTaxFlowMapper;
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param item
     * @return
     * @see
     * @since 1.0
     */
    public ValAddTaxFlowprocessorResult execute(SendReaderEntity item) {
        //获取查询结果
        List<ValAddTaxFlowDto> listDto = valAddTaxFlowMapper.querryData(item);
        ValAddTaxFlowprocessorResult valAddTaxFlowprocessorResult = new ValAddTaxFlowprocessorResult();
        Map<String,List<ValAddTaxFlowDto>> dataMap = null;
        if(!StringUtil.isEmptyList(listDto)){
        	valAddTaxFlowprocessorResult.setDatas(listDto);
        	dataMap = new HashMap<>();
        	for(ValAddTaxFlowDto dto : listDto){
        		if(dataMap.get(dto.getFinprodId()) == null){
        			List<ValAddTaxFlowDto> dtoList = new ArrayList<>();
        			dtoList.add(dto);
        			dataMap.put(dto.getFinprodId(), dtoList);
        		} else {
        			List<ValAddTaxFlowDto> dtoList = dataMap.get(dto.getFinprodId());
        			dtoList.add(dto);
        			dataMap.put(dto.getFinprodId(), dtoList);
        		}
        	}       	
        }
        
        if(dataMap != null){
        	valAddTaxFlowprocessorResult.setTmpEName("ValAddTaxFlowBack");
        	valAddTaxFlowprocessorResult.setDatasMap(dataMap);
        }
        
        return valAddTaxFlowprocessorResult;
    }

}


