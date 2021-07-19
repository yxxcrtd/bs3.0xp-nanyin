package com.joyintech.service.risk.creditmanagement.ceassetgroup.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditmanagement.ceassetgroup.dao.CeAssetGroupMapper;
import com.joyintech.service.risk.creditmanagement.ceassetgroup.dto.CreditLinesAstGrpDto;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditMstInfoDto;
import com.joyintech.tams.common.base.dao.CreditAssetpoolMapper;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.entity.CreditAssetpool;


/**
 * 资产系列授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAssetGroup002Service
 * @since 1.0
 */
@Service("backCeAssetGroup002Service")
public class CeAssetGroup002Service extends BaseService<CreditMstInfoDto> {
	
	@Autowired
	private CeAssetGroupMapper ceAssetGroupMapper;
	
	@Autowired
	private CreditLinesMapper creditLinesMapper;
	
	@Autowired
	private CreditAssetpoolMapper creditAssetpoolMapper;
	
	
    /**
     * 查询额度信息
     * @param map
     */
    public CreditLinesAstGrpDto queryCeAssetGroupBySeqno(Map<String, Object> map ) {
    		return ceAssetGroupMapper.queryCeAssetGroupBySeqno(map);
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(CreditLinesAstGrpDto entity) {
    	if(StringUtil.isNotEmpty(entity.getCreditAmt())){
    		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
    	}
    	entity.setHapCreditAmt(new BigDecimal(0));
    	entity.setParentCustomerId(entity.getCustomerId());
    	entity.setCreditType("03");
    	creditLinesMapper.insert(entity);
    	//登录资产系列池子数据
    	CreditAssetpool pool = new CreditAssetpool();
    	pool.setSeqno(entity.getCustomerId());
    	pool.setAssetpoolName(entity.getCustomerName());
    	creditAssetpoolMapper.insert(pool);
    	
    }
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(CreditLinesAstGrpDto entity) {
    	if(null!=entity){
    		if(StringUtil.isNotEmpty(entity.getCreditAmt())){
        		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
        	}
    		creditLinesMapper.updateByPrimaryKeySelective(entity);
    	}
    }
    
    /**
     * 删除监督指标
     * @param name
     */
    public int delete(CreditLinesAstGrpDto dto) {
    	ceAssetGroupMapper.deleteByPrimaryKey(dto.getSeqno());
    	return creditLinesMapper.deleteByPrimaryKey(dto.getSeqno());
    }
    
    /**
     * 查询是否存在对应系列
      *
      * @param map 检索参数
      * @return SelectOptionDto
      */
    public List<CreditAssetpool> querycreditAssetpool(Map<String, Object> map) {
        return ceAssetGroupMapper.querycreditAssetpool(map);
    }
    
}
