package com.joyintech.service.valuation.feetrade.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.feetrade.dao.FeeTradeManagementMapper;
import com.joyintech.service.valuation.feetrade.dto.FeeTradeManagementDto;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 费用交易管理
 * @version 1.0 20200121
 * @see FeeTradeManagementService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FeeTradeManagementService extends CommonExportService<FeeTradeManagementDto> {
	
	@Autowired
    private FeeTradeManagementMapper feeTradeManagementMapper;
	
	public Page<FeeTradeManagementDto> findPage(Map<String,Object> params, int page, int length){
		params.put("PS_T10", DictCodeList.交易方向_付费.getValue());	//付费
		return super.queryForPage(page,length,() -> feeTradeManagementMapper.findPage(params));
    }
    /**
     * 帐套信息查询
     */
    public List<SelectOptionDto> getBookSetList(Map<String, Object> param){
    	return feeTradeManagementMapper.getBookSetList(param);
    }
    
    public BigDecimal getShouldPayAmt(Map<String, Object> param){
    	List<BigDecimal> list = feeTradeManagementMapper.getShouldPayAmt(param);
    	if(list!=null && list.size()>0) {
    		return list.get(0);
    	}
    	else {
    		return BigDecimal.ZERO;
    	}
    }
    
    public String getBaseSubjectNature(Map<String, Object> param){
    	List<String> list = feeTradeManagementMapper.getBaseSubjectNature(param);
    	if(list!=null && list.size()>0) {
    		return list.get(0);
    	}
    	else {
    		return null;
    	}
    }
    
    public String getBokDetailId(Map<String, Object> param){
    	List<String> list = feeTradeManagementMapper.getBokDetailId(param);
    	if(list!=null && list.size()>0) {
    		return list.get(0);
    	}
    	else {
    		return null;
    	}
    }
    
    public Map<String, Object> getFinProductByBookSetId(String bookSetId){
    	List<Map<String, Object>> list = feeTradeManagementMapper.getFinProductByBookSetId(bookSetId);
    	if(list!=null && list.size()>0) {
    		return list.get(0);
    	}
    	else {
    		return null;
    	}
    }
    
    public String getCcyByBokSetId(String bookSetId){
    	return feeTradeManagementMapper.getCcyByBokSetId(bookSetId);
    }
    
    public int updateBokDealAccounting(Map<String, Object> params){
    	return feeTradeManagementMapper.updateBokDealAccounting(params);
    }
    
    public int updateStlOrder(Map<String, Object> params){
    	return feeTradeManagementMapper.updateStlOrder(params);
    }
    
    public int deleteBokDealAccounting(Map<String, Object> params){
    	return feeTradeManagementMapper.deleteBokDealAccounting(params);
    }
    
    public int deleteStlOrder(Map<String, Object> params){
    	return feeTradeManagementMapper.deleteStlOrder(params);
    }
    
    /**
     * 子帐套信息查询
     */
    public List<SelectOptionDto> getSubBookSetList(Map<String, Object> params){
    	return feeTradeManagementMapper.getSubBookSetList(params);
    }
}
