package com.joyintech.service.risk.creditmanagement.ceasset.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.service.risk.creditmanagement.ceorg.service.CeOrg002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditmanagement.ceasset.dao.CeAssetMapper;
import com.joyintech.service.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditMstInfoDto;


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

	@Autowired
	private CeOrg002Service ceOrg002Service;
	
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
    	entity.setHapCreditAmt(new BigDecimal(0));
    	entity.setParentCustomerId(entity.getCustomerId());
		//多级法人改造 add by jingqiongqiong 20200622
		//当前用户所属机构代码
		String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
		entity.setInstitutionCode(createInst);
		//多级法人改造 add by jingqiongqiong 20200622
		entity.setHapCreditAmt(ceOrg002Service.extendCreditLinesDeal(entity.getSeqno(), entity.getVdate(), entity.getCreditMstId(),entity.getCustomerId()));
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
    		//多级法人改造 add by jingqiongqiong 20200622
    		//当前用户所属机构代码
    		String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
    		entity.setInstitutionCode(createInst);
    		//多级法人改造 add by jingqiongqiong 20200622
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
