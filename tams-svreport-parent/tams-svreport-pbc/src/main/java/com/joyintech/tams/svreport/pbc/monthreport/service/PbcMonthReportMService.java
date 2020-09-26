package com.joyintech.tams.svreport.pbc.monthreport.service;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;


/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年4月26日
 * @see PbcMonthReportMService
 * @since 1.0
 */
@Service
public class PbcMonthReportMService extends BaseService<PbcMonthReport>{
 
	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<PbcMonthReport> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReport/findPage",param,
        		PbcMonthReport.class);
	}


    /**
     *
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    public PbcMonthReport searchPageBysubmitDate(Map<String, Object> param) {
        return restTemplate.postForObject(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReport/searchPageBysubmitDate",param,
        		PbcMonthReport.class);
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
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReport/updateSumStaus",map,
        		Integer.class);
	}
	
	public String recalReport(Map<String, Object> map){
		return restTemplate.postForObject(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReport/recalReport",map,
        		String.class);
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
	public boolean cancelMannul(Map<String, Object> map) {
		return restTemplate.postForObject(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReport/cancelMannul",map,
        		boolean.class);
	}
	/**
	 * 
	* 数据检查.
	* @author yuluan
	* @time 2019年9月4日 下午4:24:24
	* @param map
	* @return
	 */
	public List<ExcelValidDto> reportCheck(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReport/reportCheck", map,
                ExcelValidDto.class);
    }
}
