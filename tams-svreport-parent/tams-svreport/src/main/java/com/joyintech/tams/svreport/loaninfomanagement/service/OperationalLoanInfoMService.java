package com.joyintech.tams.svreport.loaninfomanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;

/**
 * 贷款明细信息维护 Service
 * @author hpp
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationalLoanInfoMService extends BaseService<OperationalLoanInfo001Dto>{

	/**
	 * 根据前台传入的条件动态查询结果
	 * 
	 * @param map 前台条件组成的map
	 * @return 动态结果
	 */
	@SuppressWarnings("unchecked")
	public String queryData(Map<String, Object> param) {
		return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/queryData",param,
        		String.class);
	}
	
	/**
	 * 根据主键删除数据
	 * @param dto
	 */
	public void doDelete(Map<String, Object> param){
		restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/doDelete", 
				param, Integer.class);
	}
	
	/**
	 * 根据主键查询资产收益权贷款明细基本信息表数据
	 * @param map
	 * @return
	 */
	public OperationalLoanInfo001Dto selectByPrimaryKey(Map<String, Object> map){
		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/selectByPrimaryKey", map, 
				OperationalLoanInfo001Dto.class);
	}
	
	/**
	 * 查询产品资产代码
	 * @param map
	 * @return
	 */
	public List<SelectOptionDto> queryPbcAssetCode(Map<String, Object> map){
		return restTemplate.postForList(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/queryPbcAssetCode", map, 
				SelectOptionDto.class);
	}
	public String queryPbcAssetType(Map<String, Object> map){
		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/queryPbcAssetType", map, 
				String.class);
	}
	
	/**
	 * 保存提交
	 * @param dto
	 */
	public void doSave(OperationalLoanInfo001Dto dto){
		restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/doSave", dto, Integer.class);
	}
	
	public int checkAssetCodeExist(String assetCode){
		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/checkAssetCodeExist", assetCode, Integer.class);
	}
	
	/**
	 * 根据计划名称查询计划id以及待维护表ID
	 * @param pid
	 * @return
	 */
	public OperationalLoanInfo001Dto queryPlanInfo(String pid){
		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/queryPlanInfo", pid, OperationalLoanInfo001Dto.class);
	}
	
	/**
     * 
     * 省市县联动
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     */
    public List<SelectOptionDto> getAddressSelectOption(Map<String,Object> param) {
        return restTemplate.postForList(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/getAddressSelectOption",
            param, SelectOptionDto.class);
    }
    
}
