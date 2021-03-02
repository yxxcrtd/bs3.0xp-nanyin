package com.joyintech.service.risk.creditmanagement.ceorg.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditmanagement.ceorg.dao.CeOrgMapper;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditMstInfoDto;
import com.joyintech.tams.common.base.dao.CreditLinesDealMapper;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditLinesDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 机构授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeOrg002Service
 * @since 1.0
 */
@Service("backCeOrg002Service")
public class CeOrg002Service extends BaseService<CreditMstInfoDto> {
	
	@Autowired
	private CeOrgMapper ceOrgMapper;
	
	@Autowired
	private CreditLinesMapper creditLinesMapper;

	@Autowired(required = false)
	private BaseGetBussinessId baseGetBussinessId;
	
	@Autowired
	private CreditLinesDealMapper creditLinesDealMapper;

    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public List<SelectOptionDto> getCustomerId(Map<String, Object> map) {
        return ceOrgMapper.getCustomerId(map);
    }
    
    @SuppressWarnings("rawtypes")
	public List<TreeNode> getAllCeOrgQuote(String rank) {
        return ceOrgMapper.getAllCeOrgQuote(rank);
    }
    
    @SuppressWarnings("rawtypes")
   	public List<TreeNode> getParentCeOrgQuote(String rank) {
           return ceOrgMapper.getParentCeOrgQuote(rank);
       }
    
    public Page<CreditMstInfoDto> getTreeCeOrgQuote(int page, int length,Map<String, Object> param) 
            throws Exception {
            return super.queryForPage(page, length, () -> ceOrgMapper.getTreeCeOrgQuote(param));
        }
    
    /**
     * 查询额度信息
     * @param map
     */
    public CreditLinesOrgDto queryCeOrgBySeqno(Map<String, Object> map ) {
    	CreditLinesOrgDto retCre = ceOrgMapper.queryCeOrgBySeqno(map);
    	List<CreditLines> retMxList=ceOrgMapper.queryCeOrgByPflshAndCustomId(retCre.getPflsh(),retCre.getCustomerId());
    	retCre.setCreditQuoteItem(retMxList);
    	return retCre;
    }
    
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param name
     */
    public int searchRepeatByVmDate(Map<String, Object> param ) {
    	param.put("institutionCodes", LoginUserUtil.getTreeInstCode()) ;
    	return ceOrgMapper.searchRepeatByVmDate(param);
    }
    
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param name
     */
    public int searchRepeatByVmDateArea(Map<String, Object> param ) {
    	param.put("institutionCodes", LoginUserUtil.getTreeInstCode()) ;
    		return ceOrgMapper.searchRepeatByVmDateArea(param);
    }
    /**
     * 新建编辑场合时查询批复号是否重复
     * @param name
     */
    public int searchRepeatByPflsh(String pflsh,String seqno,String creditMstId ) {
    	return ceOrgMapper.searchRepeatByPflsh(pflsh,seqno,creditMstId,LoginUserUtil.getTreeInstCode());
    }
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(CreditLinesOrgDto entity) {
    	if(StringUtil.isNotEmpty(entity.getCreditAmt())){
    		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
    	}
    	entity.setHapCreditAmt(new BigDecimal(0));
    	entity.setCreditFlag(DictCodeList.是否_是.getValue());
		//多级法人改造 add by jingqiongqiong 20200616
		//当前用户所属机构代码
		String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
		entity.setInstitutionCode(createInst);
		//多级法人改造 add by jingqiongqiong 20200616
    	if(StringUtil.isNotEmpty(entity.getCreditQuoteItem())){
    		for(CreditLines dto:entity.getCreditQuoteItem()){
    			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
    				dto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
    	    	}
    			dto.setVdate(entity.getVdate());
    			dto.setMdate(entity.getMdate());
    			dto.setRemark(entity.getRemark());
    			dto.setPflsh(entity.getPflsh());
    			dto.setHapCreditAmt(new BigDecimal(0));
    			dto.setCustomerId(entity.getCustomerId());
    			dto.setCreditFlag(DictCodeList.是否_是.getValue());
    			dto.setParentCustomerId(entity.getCustomerId());
				//多级法人改造 add by jingqiongqiong 20200616
				dto.setInstitutionCode(createInst);
				//多级法人改造 add by jingqiongqiong 20200616
				dto.setHapCreditAmt(extendCreditLinesDeal(dto.getSeqno(), dto.getVdate(), dto.getCreditMstId(),dto.getCustomerId()));
    			creditLinesMapper.insert(dto);
    		}
    		
    	}
		entity.setHapCreditAmt(extendCreditLinesDeal(entity.getSeqno(), entity.getVdate(), entity.getCreditMstId(),entity.getCustomerId()));
    	creditLinesMapper.insert(entity);

	}
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(CreditLinesOrgDto entity) {
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
    		if(StringUtil.isNotEmpty(entity.getCreditQuoteItem())){
        		for(CreditLines dto:entity.getCreditQuoteItem()){
        			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
        				dto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
        	    	}
            		//多级法人改造 add by jingqiongqiong 20200622
            		//当前用户所属机构代码
        			dto.setInstitutionCode(createInst);
            		//多级法人改造 add by jingqiongqiong 20200622
        			dto.setVdate(entity.getVdate());
        			dto.setMdate(entity.getMdate());
        			dto.setRemark(entity.getRemark());
        			dto.setPflsh(entity.getPflsh());
        			dto.setCustomerId(entity.getCustomerId());
        			dto.setParentCustomerId(entity.getCustomerId());
        			if(StringUtil.isNotEmpty(dto.getSeqno())){
        				//如果授信额度为空或者为0则删除
						if (!StringUtil.isNotEmpty(dto.getCreditAmt()) || dto.getCreditAmt().compareTo(new BigDecimal("0"))== 0 ){
							creditLinesMapper.deleteByPrimaryKey(dto.getSeqno());
						}else {
							creditLinesMapper.updateByPrimaryKeySelective(dto);
						}
        			}
        			else{
        				dto.setSeqno(getQuotePrimaryKey());
        				dto.setHapCreditAmt(new BigDecimal(0));
        				dto.setCreditFlag(DictCodeList.是否_是.getValue());
						dto.setHapCreditAmt(extendCreditLinesDeal(dto.getSeqno(), dto.getVdate(), dto.getCreditMstId(),dto.getCustomerId()));
            			creditLinesMapper.insert(dto);
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
     * 删除机构授信
     * @param name
     */
    public int delete(CreditLinesOrgDto dto) {
    	Map<String, Object> param=new HashMap<String, Object>();
    	param.put("seqno", dto.getSeqno());
    	CreditLinesOrgDto retCre = ceOrgMapper.queryCeOrgBySeqno(param);
    	return ceOrgMapper.deleteOrgMx(retCre.getPflsh());
    }

	/**
	 * 如果当前数据额度已被占用则不允许操作
	 * by nixiukuan 20191024
	 * @param param
	 */
	public Boolean searchIsUse(Map<String, Object> param) {
		int count= ceOrgMapper.searchIsUse(param);
		if(count>0)
		{
			return  true;
		}
		return  false;
	}

	/**
	 * 授信继承
	 * @param name
	 */
	public BigDecimal extendCreditLinesDeal(String seqno, Date vdate, String creditMstId, String customerId) {
		if(CacheUtil.getSysParam("IS_EXTEND_CREDIT").equals("true") && vdate.compareTo(DateUtil.platDateFromCache())<=0){
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("creditMstId", creditMstId);
			param.put("customerId", customerId);
			param.put("vdate", vdate);
			List<CreditLinesDeal> creditLinesDealList = ceOrgMapper.selectCreditLinesDeal(param);
			for(CreditLinesDeal creditLinesDeal : creditLinesDealList){
				creditLinesDeal.setSeqno(seqno);
				creditLinesDealMapper.insert(creditLinesDeal);
			}
			BigDecimal res = ceOrgMapper.selectCreditLinesHap(param);
			return res == null? new BigDecimal("0") : res;
		}else{
			return new BigDecimal("0");
		}
	}

	public List<CreditLines> selectCreditLines(Map<String, Object> param){
		return ceOrgMapper.selectCreditLines(param);
	}

}
