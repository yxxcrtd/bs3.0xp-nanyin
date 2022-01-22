package com.joyintech.tams.report.submitmanagement.salerideninforegist.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmanagement.salerideninforegist.dto.SalerIdenInfoRegistDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SalerIdenInfoRegistService extends BaseService<SalerIdenInfoRegistDto> {
	 /**
    *
    * 查询功能
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public Page<SalerIdenInfoRegistDto> searchForPage(Map<String, Object> param){
	   return restTemplate.postForPage(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerIdenInfoRegist/findPage", param,
			   SalerIdenInfoRegistDto.class);
   }

   /**
    *
    * 查询功能（无分页）
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public List<SalerIdenInfoRegistDto> queryForInfoRegist(Map<String, Object> param){
	   return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerIdenInfoRegist/queryForInfoRegist",
			   param,
			   SalerIdenInfoRegistDto.class);
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
				SvrServiceApplication.CHB_SERVICE_NAME + "/SalerIdenInfoRegist/updateSumStaus",map,
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
				SvrServiceApplication.CHB_SERVICE_NAME + "/SalerIdenInfoRegist/cancelMannul",map,
				Integer.class);
	}

	/**
	 * 姓名下拉框
	 */
	public List<SuggestDto> getSalerName(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerIdenInfoRegist/getSalerName",
				map, SuggestDto.class);
	}
}