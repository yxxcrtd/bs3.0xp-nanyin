package com.joyintech.tams.report.submitmanagement.salerrewpenainforegist.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmanagement.salerrewpenainforegist.dto.SalerRewPenaInfoRegistDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SalerRewPenaInfoRegistService extends BaseService<SalerRewPenaInfoRegistDto> {
	 /**
    *
    * 查询功能
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public Page<SalerRewPenaInfoRegistDto> searchForPage(Map<String, Object> param){
	   return restTemplate.postForPage(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfoRegist/findPage", param,
			   SalerRewPenaInfoRegistDto.class);
   }

   /**
    *
    * 查询功能（无分页）
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public List<SalerRewPenaInfoRegistDto> queryForInfoRegist(Map<String, Object> param){
	   return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfoRegist/queryForInfoRegist",
			   param,
			   SalerRewPenaInfoRegistDto.class);
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
				SvrServiceApplication.CHB_SERVICE_NAME + "/SalerRewPenaInfoRegist/updateSumStaus",map,
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
				SvrServiceApplication.CHB_SERVICE_NAME + "/SalerRewPenaInfoRegist/cancelMannul",map,
				Integer.class);
	}
}