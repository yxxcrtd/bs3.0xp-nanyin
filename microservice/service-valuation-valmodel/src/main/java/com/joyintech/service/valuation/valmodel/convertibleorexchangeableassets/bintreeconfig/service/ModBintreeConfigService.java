/*
 * 文件名：CheckReport001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年10月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dao.ModBintreeConfigDtoMapper;
import com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.ModBintreeConfigDto;
import com.joyintech.tams.constant.ModelDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;

/**
 * 二叉树模型参数配置
 * 
 * @author yuluan
 * @version 1.0 2021年7月1日
 * @see ModBintreeConfigService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ModBintreeConfigService extends BaseService<ModBintreeConfigDto> {
    @Autowired
    private ModBintreeConfigDtoMapper modBintreeConfigDtoMapper;
    
    public String check(Map<String, Object> param){
    	String finprodId=param.get("finprodId").toString();
    	int count=modBintreeConfigDtoMapper.checkIfHashData(finprodId);
    	if(count>0){
    		return "Y";
    	}
    	return "N";
    }
    public Page<ModBintreeConfigDto> selectForPage(int page, int length, Map<String, Object> param)
            throws Exception {
    	String finprodNames = (String)param.get("finprodNames");
        param.put("finprodIds", StringUtil.splitToList(GlobalConstant.COMMA, finprodNames));
        String stockBordsttus= (String)param.get("stockBordsttus");
        if("".equals(stockBordsttus)){
        	param.put("stockBordsttus", null);
        }
        return super.queryForPage(page, length, () -> modBintreeConfigDtoMapper.selectForPage(param));
    }
    public ModBintreeConfigDto queryDtoBySeqno(String seqno){
    	return modBintreeConfigDtoMapper.selectByPrimaryKey(seqno);
    }
    public void insert(ModBintreeConfigDto dto){
    	modBintreeConfigDtoMapper.insert(dto);
    }
    public void update(ModBintreeConfigDto dto){
    	modBintreeConfigDtoMapper.updateByPrimaryKeySelective(dto);
    }
    public int delete(ModBintreeConfigDto dto){
    	return modBintreeConfigDtoMapper.deleteByPrimaryKey(dto.getSeqno());
    }
    public int initBintreeConfig(Map<String,String> map){
    	ModBintreeConfigDto dto= new ModBintreeConfigDto();
    	dto.setFinprodId(map.get("FINPROD_ID"));
    	if("YTON".equals(map.get("action"))||"delete".equals(map.get("action"))){
    		return	modBintreeConfigDtoMapper.deleteByFinprodId(dto.getFinprodId());
    	}
    	dto.setFinprodName(map.get("FINPROD_NAME"));
    	dto.setStockBordsttus(map.get("STOCK_BORDSTTUS"));
    	if(ModelDictCodeList.上市状态_上市.getValue().equals(dto.getStockBordsttus())){
    		dto.setStockId(map.get("STOCK_ID"));
    	}else{
    		dto.setMarketMulti(ModelDictCodeList.市场乘数方法_市净率法.getValue());	
    	}
    	if("update".equals(map.get("action"))){
    		ModBintreeConfigDto eDto=modBintreeConfigDtoMapper.selectByFinprodId(dto.getFinprodId());
    		if(eDto!=null){
    			if(eDto.getStockBordsttus().equals(dto.getStockBordsttus())){
    				if(ModelDictCodeList.上市状态_上市.getValue().equals(eDto.getStockBordsttus())){//上市
    					if(eDto.getStockId().equals(dto.getStockId())){//一致 不用更新
    						return 0;
    					}else{//不一致 先删后插
    						modBintreeConfigDtoMapper.deleteByFinprodId(dto.getFinprodId());
    					}
    				}else{//未上市
    					return 0;
    				}
    			}else{//是否上市不一致 直接更新
    				modBintreeConfigDtoMapper.deleteByFinprodId(dto.getFinprodId());
    			}
    			
    		}
    	}
    	dto.setSmoothsection(BigDecimal.ZERO);
    	dto.setBintreeStep(new BigDecimal(100));
    	dto.setCurveCode("1232");//中债国债到期收益率
    	dto.setSeqno(IDGenerator.generate());
    	dto.setCreateUser("system");
    	dto.setCreateTime(new Date());
    	dto.setUpdateUser("system");
    	dto.setUpdateTime(new Date());
       return modBintreeConfigDtoMapper.insert(dto);
    }
    		
   
}