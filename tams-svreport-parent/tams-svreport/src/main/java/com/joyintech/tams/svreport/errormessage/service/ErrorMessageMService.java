package com.joyintech.tams.svreport.errormessage.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.errormessage.dto.ErrorMessage001;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;

/**
 * 错误表信息查询
 * 
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see ErrorMessageMService
 * @since 1.0
 */
@Service
public class ErrorMessageMService extends CommonExportService<ChbEquHoldCheckDto> {

	/**
	 * 错误信息查询
	 * 
	 * @author liuyu
	 * @time 2019年5月13日 上午11:00:54
	 * @param selectParam
	 *            查询参数
	 * @return infoList
	 */
	public Page<ErrorMessage001> queryPage(Map<String, Object> selectParam) {
		return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/CheckErrorMessage/findPage",
				selectParam, ErrorMessage001.class);
	}

	/**
	 * 获取接口下拉列表.
	 * 
	 * @author liuyu
	 * @time 2019年5月14日 上午11:26:48
	 * @return options 选择集合
	 */
	public List<SelectOptionDto> getInterTypeSelects( Map<String, Object> param) {
		return restTemplate.postForList(SvrServiceApplication.COMMON_SERVICE_NAME + "/CheckErrorMessage/getInterTypeSelects",
				param, SelectOptionDto.class);
	}
}
