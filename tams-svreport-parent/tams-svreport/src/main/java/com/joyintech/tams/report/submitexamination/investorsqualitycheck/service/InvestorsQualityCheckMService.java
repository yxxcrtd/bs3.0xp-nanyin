package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

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
    public Page<InvestorsQualityCheck001Dto> searchInvestorsPassRate(Map<String, Object> pageParam){
    	return restTemplate.postForPage(
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
    
}
