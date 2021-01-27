package com.joyintech.tams.svreport.chb.botastpositionregist.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPositionRegist001Dto;


/**
 * 底层资产持仓信息登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ChbBotAstPositionRegistMService
 * @since 1.0
 */
@Service
public class ChbBotAstPositionRegistMService extends BaseService<ChbBotAstPositionRegist001Dto>{

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<ChbBotAstPositionRegist001Dto> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbBotAstPositionRegist/findPage",param,
        		ChbBotAstPositionRegist001Dto.class);
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
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbBotAstPositionRegist/updateSumStaus",map,
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
	public String cancelMannul(Map<String, Object> map) {
		return restTemplate.postForObject(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbBotAstPositionRegist/cancelMannul",map,
        		String.class);
	}
	
	public Map<String,Object> reportCheck(Map<String, Object> map) {
		return restTemplate.postForObject(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbBotAstPositionRegist/reportCheck", map,
	       		Map.class);
	}
	
}
