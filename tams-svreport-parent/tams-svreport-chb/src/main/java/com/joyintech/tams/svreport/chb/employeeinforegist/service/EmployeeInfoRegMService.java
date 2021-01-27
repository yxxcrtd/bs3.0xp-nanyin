package com.joyintech.tams.svreport.chb.employeeinforegist.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.employeeinforegist.dto.EmployeeInfoReg001Dto;


/**
 * 从业人员登记服务
 * 〈功能详细描述〉
 * @author lingxq
 * @version 1.6.0 2020年11月3日
 * @see EmployeeInfoRegMService
 * @since 1.0
 */
@Service
public class EmployeeInfoRegMService extends BaseService<EmployeeInfoReg001Dto> {
    
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   public Page<EmployeeInfoReg001Dto> selectForPage(Map<String, Object> param) {
       return restTemplate.postForPage(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/EmployeeInfoReg/findPage",param,
	       		EmployeeInfoReg001Dto.class);
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
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/EmployeeInfoReg/updateSumStaus",map,
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
	@ResponseBody
	@PostMapping("cancelMannul")
	public String cancelMannul(Map<String, Object> map) {
		return	restTemplate.postForObject(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/EmployeeInfoReg/cancelMannul",map,
	       		String.class);
	}
	
	/**
	 * 导出中债excel
	 * @param param
	 * @return
	 */
	public List<EmployeeInfoReg001Dto> selectForExport(Map<String, Object> param) {
	    return restTemplate.postForList(
		       		SvrServiceApplication.CHB_SERVICE_NAME + "/EmployeeInfoReg/selectForExport",param,
		       		EmployeeInfoReg001Dto.class);
	}
	
	/**
	 * 数据检查
	 * @param param
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public Map<String,Object> checkData(Map<String, Object> param){
    	return restTemplate.postForObject(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/EmployeeInfoReg/checkData",param,
	       		Map.class);
    }
}
