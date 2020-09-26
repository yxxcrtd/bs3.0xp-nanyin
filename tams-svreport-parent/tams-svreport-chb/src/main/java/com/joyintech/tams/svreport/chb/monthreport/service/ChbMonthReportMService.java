package com.joyintech.tams.svreport.chb.monthreport.service;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;


/**
 * 中债银行月度报表报送登服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ChbMonthReportMService
 * @since 1.0
 */
@Service
public class ChbMonthReportMService extends BaseService<ChbMonthReport>{

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<ChbMonthReport> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/findPage",param,
        		ChbMonthReport.class);
	}
	 /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   public ChbMonthReport searchPageBysubmitDate(Map<String, Object> param) {
       return restTemplate.postForObject(
       		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/searchPageBysubmitDate",param,
       		ChbMonthReport.class);
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
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/updateSumStaus",map,
        		Integer.class);
	}
	public String recalReport(Map<String, Object> map){
		return restTemplate.postForObject(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/recalReport",map,
        		String.class);
	}
	public void doAseetBatch(Map<String, Object> map) {
		restTemplate.postForObject(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/doAseetBatch",map,
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
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/cancelMannul",map,
        		Integer.class);
	}
	/**
	 * 
	* 数据检查.
	* @author liuyu
	* @time 2019年6月25日 下午4:24:24
	* @param map
	* @return
	 */
	public List<ExcelValidDto> reportCheck(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/reportCheck", map,
                ExcelValidDto.class);
    }
	
}
