package com.joyintech.tams.report.submitmanagement.salerinstinforegist.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmanagement.salerinstinforegist.dto.SalerInstInfoRegistDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SalerInstInfoRegistService extends BaseService<SalerInstInfoRegistDto> {
	 /**
    *
    * 查询功能
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public Page<SalerInstInfoRegistDto> searchForPage(Map<String, Object> param){
	   return restTemplate.postForPage(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfoRegist/findPage", param,
			   SalerInstInfoRegistDto.class);
   }

   /**
    *
    * 查询功能（无分页）
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public List<SalerInstInfoRegistDto> queryForInfoRegist(Map<String, Object> param){
	   return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfoRegist/queryForInfoRegist",
			   param,
			   SalerInstInfoRegistDto.class);
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
				SvrServiceApplication.CHB_SERVICE_NAME + "/SalerInstInfoRegist/updateSumStaus",map,
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
				SvrServiceApplication.CHB_SERVICE_NAME + "/SalerInstInfoRegist/cancelMannul",map,
				Integer.class);
	}

	/**
	 * 销售机构代码下拉框
	 */
	public List<SuggestDto> getSalerInstCode(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfoRegist/getSalerInstCode",
				map, SuggestDto.class);
	}

	/**
	 * 总部或分支机构名称下拉框
	 */
	public List<SuggestDto> getSalerOrgName(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfoRegist/getSalerOrgName",
				map, SuggestDto.class);
	}
}