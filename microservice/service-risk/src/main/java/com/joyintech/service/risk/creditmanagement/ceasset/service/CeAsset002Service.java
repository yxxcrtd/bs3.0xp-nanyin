package com.joyintech.service.risk.creditmanagement.ceasset.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditmanagement.ceasset.dao.CeAssetMapper;
import com.joyintech.service.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditMstInfoDto;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;


/**
 * 资产授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAsset002Service
 * @since 1.0
 */
@Service("backCeAsset002Service")
public class CeAsset002Service extends BaseService<CreditMstInfoDto> {
	
	@Autowired
	private CeAssetMapper ceAssetMapper;
	
	@Autowired
	private CreditLinesMapper creditLinesMapper;
	
    /**
     * 查询额度信息
     * @param map
     */
    public CreditLinesAstDto queryCeAssetBySeqno(Map<String, Object> map ) {
    		return ceAssetMapper.queryCeAssetBySeqno(map);
    }
    
    /**
     *  查询资产在资产系列中是否已存在
     * @param customerId
     */
    public List<CreditLinesAstDto> searchSameAssetGroup(String customerId) {
    		return ceAssetMapper.searchSameAssetGroup(customerId);
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(CreditLinesAstDto entity) {
    	if(StringUtil.isNotEmpty(entity.getCreditAmt())){
    		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
    	}
        if(StringUtil.isNotEmpty(entity.getCreditShareAmt())){
            entity.setCreditAmt(entity.getCreditShareAmt().multiply(new BigDecimal(100000000)));
        }
    	entity.setHapCreditAmt(new BigDecimal(0));
    	entity.setParentCustomerId(entity.getCustomerId());
    	entity.setCreditType("02");
    	creditLinesMapper.insert(entity);
    }
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(CreditLinesAstDto entity) {
    	if(null!=entity){
    		if(StringUtil.isNotEmpty(entity.getCreditAmt())){
        		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
        	}
            if(StringUtil.isNotEmpty(entity.getCreditShareAmt())){
                entity.setCreditAmt(entity.getCreditShareAmt().multiply(new BigDecimal(100000000)));
            }
    		creditLinesMapper.updateByPrimaryKeySelective(entity);
    	}
    }
    
    /**
     * 删除监督指标
     * @param name
     */
    public int delete(CreditLinesAstDto dto) {
    	return creditLinesMapper.deleteByPrimaryKey(dto.getSeqno());
    }
}
