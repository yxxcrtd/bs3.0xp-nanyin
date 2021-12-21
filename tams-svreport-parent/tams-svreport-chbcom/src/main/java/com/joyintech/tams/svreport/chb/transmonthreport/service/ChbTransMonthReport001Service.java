package com.joyintech.tams.svreport.chb.transmonthreport.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportSubDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 理财产品转型情况月度报表报送登服务
 *
 * @author dubaojun
 * @version 1.6.0 2020年6月4日
 * @see ChbTransMonthReport001Service
 * @since 1.0
 */
@Service
public class ChbTransMonthReport001Service extends BaseService<ChbTransMonthReportSubDto> {

	/**
	 * 业务画面查询事件
	 *
	 * @param param 检索参数
	 * @return 检索结果
	 */
	public Page<ChbTransMonthReportSubDto> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
				SvrServiceApplication.CHB_SERVICE_NAME + "/ChbTransMonthReport/findPage", param,
				ChbTransMonthReportSubDto.class);
	}

	/**
	 * 业务画面查询事件
	 *
	 * @param param 检索参数
	 * @return 检索结果
	 */
	public ChbTransMonthReportSubDto searchPageBysubmitDate(Map<String, Object> param) {
		return restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/ChbTransMonthReport/searchPageBysubmitDate", param,
				ChbTransMonthReportSubDto.class);
	}

	/**
	 * 手工处理功能
	 *
	 * @param map 条件map
	 */
	public void updateSumStaus(Map<String, Object> map) {
		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/ChbTransMonthReport/updateSumStaus", map,
				Integer.class);
	}

	/**
	 * 重新计算按钮按下的处理
	 *
	 * @param map 处理参数
	 * @return 路径
	 */
	public String recalReport(Map<String, Object> map) {
		return restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/ChbTransMonthReport/recalReport", map,
				String.class);
	}

	/**
	 * 资产批处理
	 *
	 * @param map 处理参数
	 */
	public void doAseetBatch(Map<String, Object> map) {
		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/ChbTransMonthReport/doAseetBatch", map,
				Integer.class);
	}

	/**
	 * 取消手工处理功能
	 *
	 * @param map 条件map
	 * @return 提示信息
	 * @seea
	 * @since 1.0
	 */
	public void cancelMannul(Map<String, Object> map) {
		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/ChbTransMonthReport/cancelMannul", map,
				Integer.class);
	}

	/**
	 * 数据检查.
	 *
	 * @param map 检查参数
	 * @return
	 */
	public List<ExcelValidDto> reportCheck(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB_SERVICE_NAME + "/ChbTransMonthReport/reportCheck", map,
				ExcelValidDto.class);
	}

}
