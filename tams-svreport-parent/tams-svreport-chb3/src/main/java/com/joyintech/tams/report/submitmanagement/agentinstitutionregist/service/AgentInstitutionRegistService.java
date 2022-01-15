package com.joyintech.tams.report.submitmanagement.agentinstitutionregist.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmanagement.agentinstitutionregist.dto.AgentInstitutionRegistDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AgentInstitutionRegistService extends BaseService<AgentInstitutionRegistDto> {

	 /**
    *
    * 查询功能
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public Page<AgentInstitutionRegistDto> searchForPage(Map<String, Object> param){
	   return restTemplate.postForPage(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentInstitutionRegist/findPage", param,
			   AgentInstitutionRegistDto.class);
   }

	/**
	 * 数据检查
	 * @param map 查询条件
	 * @return 检查结果
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> reportCheck(Map<String,Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentInstitutionRegist/reportCheck", map,
				ExcelValidDto.class);
	}
   /**
    *
    * 查询功能（无分页）
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public List<AgentInstitutionRegistDto> queryForInfoRegist(Map<String, Object> param){
	   return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentInstitutionRegist/queryForInfoRegist",
			   param,
			   AgentInstitutionRegistDto.class);
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
				SvrServiceApplication.CHB_SERVICE_NAME + "/AgentInstitutionRegist/updateSumStaus",map,
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
				SvrServiceApplication.CHB_SERVICE_NAME + "/AgentInstitutionRegist/cancelMannul",map,
				Integer.class);
	}

	/**
	 * 代理销售机构代码下拉框
	 */
	public List<SuggestDto> getAgentInstitutionCode(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentInstitutionRegist/getAgentInstitutionCode",
				map, SuggestDto.class);
	}
}