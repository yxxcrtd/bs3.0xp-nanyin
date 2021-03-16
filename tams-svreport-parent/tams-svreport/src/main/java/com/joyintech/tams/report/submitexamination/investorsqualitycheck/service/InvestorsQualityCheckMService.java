package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;

/**
 * 投资者质量核查Controller
 * @author hpp
 *
 */
@Service
public class InvestorsQualityCheckMService extends BaseService<InvestorsQualityCheck001Dto>{
    
	
	/**
     * 查询列表-颜色
     * 
     * @return Page
     */
	@SuppressWarnings("unchecked")
    public Map<String,String>  readConfig(Map<String, Object> pageParam) {
        return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/readConfig",pageParam,
        		Map.class);
    }
    
    /**
     * 查询左侧-核查项合格率信息
     * @return
     */
    public List<InvestorsQualityCheck001Dto> searchInvestorsPassRate(Map<String, Object> pageParam){
    	return restTemplate.postForList(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchInvestorsPassRate",pageParam,
        		InvestorsQualityCheck001Dto.class);
    }
    
    /**
     * 查询列表 
     * --证件类型合格率
     * @return Page
     */
    public Page<InvestorsQualityCheck001Dto> searchTableZJLX(Map<String, Object> pageParam){
    	return restTemplate.postForPage(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableZJLX",pageParam,
        		InvestorsQualityCheck001Dto.class);
    }
	
    /**
     * 查询列表 
     * --证件匹配合格率
     * @return Page
     */
    public Page<InvestorsQualityCheck001Dto> searchTableZJPP(Map<String, Object> pageParam){
    	return restTemplate.postForPage(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableZJPP",pageParam,
        		InvestorsQualityCheck001Dto.class);
    }
    
    /**
     * 查询列表 
     * --及时性合格率
     * @return Page
     */
    public Page<InvestorsQualityCheck001Dto> searchTableJSX(Map<String, Object> pageParam){
    	return restTemplate.postForPage(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableJSX",pageParam,
        		InvestorsQualityCheck001Dto.class);
    }

	/**
	 * 查询列表
	 * --产品状态合格率
	 * @return Page
	 */
	public Page<InvestorsQualityCheck001Dto> searchTableCPZT(Map<String, Object> pageParam){
		return restTemplate.postForPage(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableCPZT",pageParam,
				InvestorsQualityCheck001Dto.class);
	}

	/**
	 * 查询列表
	 * --投资者数量匹配合格率
	 * @return Page
	 */
	public Page<InvestorsQualityCheck001Dto> searchTableSLPP(Map<String, Object> pageParam){
		return restTemplate.postForPage(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableSLPP",pageParam,
				InvestorsQualityCheck001Dto.class);
	}
	
	/**
	 * 查询列表
	 * --募集总份额合格率
	 * @return Page
	 */
	public List<InvestorsQualityCheck001Dto> searchTableMJQFE(Map<String, Object> pageParam){
		return restTemplate.postForList(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableMJQFE",pageParam,
				InvestorsQualityCheck001Dto.class);
	}
	
	/**
	 * 查询列表
	 * --实际募集金额（元）合格率
	 * @return Page
	 */
	public List<InvestorsQualityCheck001Dto> searchTableMJQYE(Map<String, Object> pageParam){
		return restTemplate.postForList(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableMJQYE",pageParam,
				InvestorsQualityCheck001Dto.class);
	}
	
	/**
	 * 查询列表
	 * --登记完整性合格率
	 * @return List
	 */
	public Page<InvestorsQualityCheck001Dto> searchTableDJWZX(Map<String, Object> pageParam){
		return restTemplate.postForPage(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableDJWZX",pageParam,
				InvestorsQualityCheck001Dto.class);
	}
	
	/**
	 * 查询列表
	 * --产品余额（元）合格率（封闭式产品）
	 * @return List
	 */
	public List<InvestorsQualityCheck001Dto> searchTableCPYEFBX(Map<String, Object> pageParam){
		return restTemplate.postForList(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableCPYEFBX",pageParam,
				InvestorsQualityCheck001Dto.class);
	}
	
	/**
	 * 查询列表
	 * --产品余额（元）合格率（开放式式产品）
	 * @return List
	 */
	public List<InvestorsQualityCheck001Dto> searchTableCPYEKFX(Map<String, Object> pageParam){
		return restTemplate.postForList(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableCPYEKFX",pageParam,
				InvestorsQualityCheck001Dto.class);
	}
	
	/**
	 * 查询列表
	 * --产品份额（元）合格率（封闭式产品）
	 * @return List
	 */
	public List<InvestorsQualityCheck001Dto> searchTableCPFEFBX(Map<String, Object> pageParam){
		return restTemplate.postForList(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableCPFEFBX",pageParam,
				InvestorsQualityCheck001Dto.class);
	}
	
	/**
	 * 查询列表
	 * --产品份额（元）合格率（开放式产品）
	 * @return List
	 */
	public List<InvestorsQualityCheck001Dto> searchTableCPFEKFX(Map<String, Object> pageParam){
		return restTemplate.postForList(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsQualityCheck/searchTableCPFEKFX",pageParam,
				InvestorsQualityCheck001Dto.class);
	}
    
}
