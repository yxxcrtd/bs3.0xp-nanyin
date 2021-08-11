/*
 * 文件名：CheckReport001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年10月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.lossmodel.lossgivendefaultmaintain.service;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.lossgivendefaultmaintain.dao.ModLgdConfigDtoMapper;
import com.joyintech.service.valuation.lossmodel.lossgivendefaultmaintain.dto.ModLgdConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;

/**
 * 报送前检查service
 * 
 * @author dingzhipeng
 * @version 1.0 2017年10月11日
 * @see LossGivenDefaultMaintainService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LossGivenDefaultMaintainService extends BaseService<ModLgdConfigDto> {
    @Autowired
    private ModLgdConfigDtoMapper modLgdConfigDtoMapper;
    
//    public String check(Map<String, Object> param){
//    	String finprodId=param.get("finprodId").toString();
//    	int count=modLgdConfigDtoMapper.checkIfHashData(finprodId);
//    	if(count>0){
//    		return "Y";
//    	}
//    	return "N";
//    }
    public Page<ModLgdConfigDto> selectForPage(int page, int length, Map<String, Object> param)
            throws Exception {
    	Page<ModLgdConfigDto> pageR=super.queryForPage(page, length, () -> modLgdConfigDtoMapper.selectForPage(param));
    	pageR.getRoot().forEach(item -> {
    		if(item.getAssetDetailType()==null||"".equals(item.getAssetDetailType())){
    			item.setAssetDetailType("-");
    		}
    		if(item.getIsContainPledge()==null||"".equals(item.getIsContainPledge())){
    			item.setIsContainPledge("-");
    		}
    	});
        return pageR;
    }
    public List<ModLgdConfigDto> selectForExport(Map<String, Object> param)
            throws Exception {
        return modLgdConfigDtoMapper.selectForPage(param);
    }
    public ModLgdConfigDto queryDtoBySeqno(String seqno){
    	return modLgdConfigDtoMapper.selectByPrimaryKey(seqno);
    }
    public String checkIfUsed(String seqno){
    	int count=modLgdConfigDtoMapper.checkIfUsed(seqno);
    	if(count>0){
    		return "Y";
    	}
    	return "N";
    }
    public void insert(ModLgdConfigDto dto){
    	modLgdConfigDtoMapper.insert(dto);
    }
    public void update(ModLgdConfigDto dto){
    	modLgdConfigDtoMapper.updateByPrimaryKey(dto);
    }
    public int delete(ModLgdConfigDto dto){
    	return modLgdConfigDtoMapper.deleteByPrimaryKey(dto.getSeqno());
    }
    		
   
}