package com.joyintech.service.risk.creditmanagement.preoccupancymanagement.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.CreditLinesPreMapper;
import com.joyintech.tams.common.base.entity.CreditLinesPre;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditmanagement.preoccupancymanagement.dao.CePreOccupancyManagementMapper;
import com.joyintech.service.risk.creditmanagement.preoccupancymanagement.dto.CePreOccupancyManagementDto;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;


/**
 * 预占用授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CePreOccupancyManagementService
 * @since 1.0
 */
@Service
public class CePreOccupancyManagementService extends BaseService<CePreOccupancyManagementDto> {
	
	@Autowired
	private CePreOccupancyManagementMapper cePreOccupancyManagementMapper;
	
	@Autowired
	private CreditLinesPreMapper creditLinesPreMapper;
	
	@Autowired(required = false)
	private BaseGetBussinessId baseGetBussinessId;
	 /**
     * 画面查询方法
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<CePreOccupancyManagementDto> selectForPage(int page, int length,Map<String, Object> param) 
        throws Exception {
        return super.queryForPage(page, length, () -> cePreOccupancyManagementMapper.selectForPage(param));
    }

	
    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public List<SelectOptionDto> getCustomerId(Map<String, Object> map) {
        return cePreOccupancyManagementMapper.getCustomerId(map);
    }
    
    @SuppressWarnings("rawtypes")
	public List<TreeNode> getAllCeOrgQuote(String rank) {
        return cePreOccupancyManagementMapper.getAllCeOrgQuote(rank);
    }
    
    @SuppressWarnings("rawtypes")
   	public List<TreeNode> getParentCeOrgQuote(String rank) {
           return cePreOccupancyManagementMapper.getParentCeOrgQuote(rank);
       }
    
    /**
     * 查询额度信息
     * @param map
     */
    public CePreOccupancyManagementDto queryCeOrgBySeqno(Map<String, Object> map ) {
    	CePreOccupancyManagementDto retCre = cePreOccupancyManagementMapper.queryCeOrgBySeqno(map);
    	List<CreditLinesPre> retMxList=cePreOccupancyManagementMapper.queryCeOrgByCustomId(retCre.getSeqno(),retCre.getCustomerId());
    	retCre.setCreditQuoteItem(retMxList);
    	return retCre;
    }
    
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param name
     */
    public int searchRepeatByVmDate(Map<String, Object> param ) {
    		return cePreOccupancyManagementMapper.searchRepeatByVmDate(param);
    }
    
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param name
     */
    public int searchRepeatByVmDateArea(Map<String, Object> param ) {
    		return cePreOccupancyManagementMapper.searchRepeatByVmDateArea(param);
    }
    
    /**
     * 新建编辑场合时查询批复号是否重复
     * @param name
     */
    public int searchRepeatByPflsh(String pflsh,String seqno ) {
    		return cePreOccupancyManagementMapper.searchRepeatByPflsh(pflsh,seqno);
    }
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(CePreOccupancyManagementDto entity) {
    	if(StringUtil.isNotEmpty(entity.getCreditAmt())){
    		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
    	}
    	if(StringUtil.isNotEmpty(entity.getCreditQuoteItem())){
    		for(CreditLinesPre dto:entity.getCreditQuoteItem()){
    			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
    				dto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
    	    	}
    			dto.setVdate(entity.getVdate());
    			dto.setMdate(entity.getMdate());
    			dto.setRemark(entity.getRemark());
    			dto.setCustomerId(entity.getCustomerId());
    			dto.setParentCustomerId(entity.getSeqno());
    			creditLinesPreMapper.insert(dto);
    		}
    		
    	}
    	creditLinesPreMapper.insert(entity);
    }
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(CePreOccupancyManagementDto entity) {
    	if(null!=entity){
    		if(StringUtil.isNotEmpty(entity.getCreditAmt())){
        		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
        	}
    		creditLinesPreMapper.updateByPrimaryKeySelective(entity);
    		if(StringUtil.isNotEmpty(entity.getCreditQuoteItem())){
        		for(CreditLinesPre dto:entity.getCreditQuoteItem()){
        			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
        				dto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
        	    	}
        			dto.setVdate(entity.getVdate());
        			dto.setMdate(entity.getMdate());
        			dto.setRemark(entity.getRemark());
        			dto.setCustomerId(entity.getCustomerId());
        			dto.setParentCustomerId(entity.getSeqno());
        			if(StringUtil.isNotEmpty(dto.getSeqno())){
        				creditLinesPreMapper.updateByPrimaryKeySelective(dto);
        			}
        			else{
        				dto.setSeqno(getQuotePrimaryKey());
        				creditLinesPreMapper.insert(dto);
        			}
        		}
        		
        	}
    	}
    }
    
    private String getQuotePrimaryKey(){
		if(null != baseGetBussinessId) {
			 BussinessCodeParameter codeParameter = new BussinessCodeParameter();
	            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
			return baseGetBussinessId.buildBussinessCode(codeParameter);
		}
		return IDGenerator.generate();
	}
    
    /**
     * 删除预占用授信
     * @param name
     */
    public int delete(CePreOccupancyManagementDto dto) {
    	int retMx=cePreOccupancyManagementMapper.deletePreOccupaMx(dto.getSeqno());
    	int ret =creditLinesPreMapper.deleteByPrimaryKey(dto.getSeqno());
    	return retMx+ret;
    }
}
