package com.joyintech.tams.report.submitmanagement.agentprdinforegist.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmanagement.agentprdinforegist.dto.AgentPrdInfoRegistDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AgentPrdInfoRegistService extends BaseService<AgentPrdInfoRegistDto> {
	 /**
    *
    * 查询功能
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public Page<AgentPrdInfoRegistDto> searchForPage(Map<String, Object> param){
	   return restTemplate.postForPage(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfoRegist/findPage", param,
			   AgentPrdInfoRegistDto.class);
   }

   /**
    *
    * 查询功能（无分页）
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public List<AgentPrdInfoRegistDto> queryForInfoRegist(Map<String, Object> param){
	   return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfoRegist/queryForInfoRegist",
			   param,
			   AgentPrdInfoRegistDto.class);
   }
	/**
	 * 手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	public void updateSumStaus(Map<String, Object> map) {
		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/AgentPrdInfoRegist/updateSumStaus",map,
				Integer.class);
	}

	/**
	 * 取消手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	public void cancelMannul(Map<String, Object> map) {
		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/AgentPrdInfoRegist/cancelMannul",map,
				Integer.class);
	}

	/**
	 * 代理销售机构代码下拉框
	 */
	public List<SuggestDto> getAgentInstitutionCode(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfoRegist/getAgentInstitutionCode",
				map, SuggestDto.class);
	}

	/**
	 * 产品代码下拉框
	 */
	public List<SuggestDto> getProdId(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfoRegist/getProdId",
				map, SuggestDto.class);
	}

	/**
	 * 产品登记编码下拉框
	 */
	public List<SuggestDto> getProdRegistCode(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfoRegist/getProdRegistCode",
				map, SuggestDto.class);
	}
}