package com.joyintech.tams.disclosure.information.manage.prodfilemanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dto.ReportProdFileManage001Dto;

/**
 * 产品文件新建Service层
 * @author hpp
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportProdFileManage002Service extends BaseService<ReportProdFileManage001Dto>{
	
	public ReportProdFileManage001Dto selectByPrimaryKey(Map<String, Object> map){
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME 
				+ "/ReportProdFileManage001/selectByPrimaryKey", map, ReportProdFileManage001Dto.class);
	}
	
	/**
	 * 保存提交
	 * @param dto
	 */
	public void doSave(ReportProdFileManage001Dto dto){
		restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME 
				+ "/ReportProdFileManage001/doSave", dto, Integer.class);
	}
	/**
     * 查询费用品种信息
     *
     * @return 费用品种信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map){
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME 
				+ "/ReportProdFileManage001/queryFeeInfoList", map, SelectOptionDto.class);
    }
    
	/**
	 * 查询费用信息列表
	 * @param prodId
	 * @return
	 */
	public List<ReportProdFileManage001Dto> queryFeeInfoPF(String prodId){
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME 
				+ "/ReportProdFileManage001/queryFeeInfoPF", prodId, ReportProdFileManage001Dto.class);
	}
	/**
	 * 查询投资比例区间信息列表
	 * @param prodId
	 * @return
	 */
	public List<ReportProdFileManage001Dto> queryInvestProporPF(String prodId){
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME 
				+ "/ReportProdFileManage001/queryInvestProporPF", prodId, ReportProdFileManage001Dto.class);
	}
	
}
