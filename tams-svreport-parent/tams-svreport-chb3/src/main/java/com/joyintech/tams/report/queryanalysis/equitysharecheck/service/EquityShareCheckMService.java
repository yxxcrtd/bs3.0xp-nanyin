package com.joyintech.tams.report.queryanalysis.equitysharecheck.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.queryanalysis.equitysharecheck.dto.EquityShareCheck001Dto;

/**
 * 投资者份额检查service
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年11月12日
 * @see EquityShareCheckMService
 * @since 1.0
 */
@Service
public class EquityShareCheckMService extends CommonExportService<EquityShareCheck001Dto> {

	 /**
    *
    * 业务画面查询事件
    *
    * @return 结果列表
    * @see
    * @since 1.0
    */
   public Page<EquityShareCheck001Dto> searchPage(Map<String, Object> param) {
   	return restTemplate.postForPage(
       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityShareCheck/searchPage",param,
       		EquityShareCheck001Dto.class);
	}

	/**
	 * 查询产品名称
	 * 
	 * @param param
	 *            参数
	 * @return 结果list
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> queryPrdNameList(Map<String, Object> param) {
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityShareCheck/queryPrdNameList",param,
	       		SelectOptionDto.class);
	}

	/**
	 * 查询识别标识
	 * 
	 * @param param
	 *            参数
	 * @return 结果list
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> queryIndentifyMarkList(Map<String, Object> param) {
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityShareCheck/queryIndentifyMarkList",param,
	       		SelectOptionDto.class);
	}



}
