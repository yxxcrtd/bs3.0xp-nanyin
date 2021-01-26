package com.joyintech.tams.svreport.chb.astvalueregist.service;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.astvalueregist.dto.ChbAstValueRegist001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;


/**
 * 资产估值信息登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ChbAstValueRegistMService
 * @since 1.0
 */
@Service
public class ChbAstValueRegistMService extends BaseService<ChbAstValueRegist001Dto>{
    

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<ChbAstValueRegist001Dto> searchPage(Map<String, Object> param) {
		//update by leipeng 20200711
		param.put("orgCode", CommonUtil.getTreeInstCode());
		return restTemplate.postForPage(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbAstValueRegist/findPage",param,
        		ChbAstValueRegist001Dto.class);
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
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbAstValueRegist/updateSumStaus",map,
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
	@PostMapping("cancelMannul")
	public void cancelMannul(Map<String, Object> map) {
		restTemplate.postForObject(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbAstValueRegist/cancelMannul",map,
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
	@PostMapping("/getInstitutionCode")
	public List<SuggestDto> getInstitutionCode(@RequestBody String param) {
		return restTemplate.postForList(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/Institution/getInstitutionCode",param,
        		SuggestDto.class);
	}

	/**start  中债一二期直连代码  add  by  wangzehui  20201012*/
	public Map<String,Object> submitBeforeCheck(Map<String, Object> param) {
		return restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/ChbAstValueRegist/submitBeforeCheck", param,
				Map.class);
	}
	/**end  中债一二期直连代码  add  by  wangzehui  20201012*/
}
