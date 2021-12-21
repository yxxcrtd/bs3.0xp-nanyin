package com.joyintech.tams.svreport.chb.monthreport.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReport;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class SellMonthReportMService extends BaseService<SellMonthReport>{

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<SellMonthReport> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/SellMonthReport/findPage",param,
                SellMonthReport.class);
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
                SvrServiceApplication.CHB_SERVICE_NAME + "/SellMonthReport/updateSumStaus",map,
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
                SvrServiceApplication.CHB_SERVICE_NAME + "/SellMonthReport/cancelMannul",map,
                Integer.class);
    }
//	 /**
//    *
//    * 业务画面查询事件
//    *
//    * @return
//    * @see
//    * @since 1.0
//    */
//   public ChbMonthReport searchPageBysubmitDate(Map<String, Object> param) {
//       return restTemplate.postForObject(
//       		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/searchPageBysubmitDate",param,
//       		ChbMonthReport.class);
//   }
//	/**
//	 * 手工处理功能
//	 *
//	 * @return 提示信息
//	 * @param map
//	 *            条件map
//	 * @seea
//	 * @since 1.0
//	 */
//	public void updateSumStaus(Map<String, Object> map) {
//		restTemplate.postForObject(
//        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/updateSumStaus",map,
//        		Integer.class);
//	}
//	public String recalReport(Map<String, Object> map){
//		return restTemplate.postForObject(
//        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/recalReport",map,
//        		String.class);
//	}
//	public void doAseetBatch(Map<String, Object> map) {
//		restTemplate.postForObject(
//        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/doAseetBatch",map,
//        		Integer.class);
//	}
//	/**
//	 * 取消手工处理功能
//	 *
//	 * @return 提示信息
//	 * @param map
//	 *            条件map
//	 * @seea
//	 * @since 1.0
//	 */
//	public void cancelMannul(Map<String, Object> map) {
//		restTemplate.postForObject(
//        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/cancelMannul",map,
//        		Integer.class);
//	}
	
}
