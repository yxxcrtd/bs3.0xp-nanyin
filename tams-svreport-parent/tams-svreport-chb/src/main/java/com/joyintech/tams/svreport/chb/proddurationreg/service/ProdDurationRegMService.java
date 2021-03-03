package com.joyintech.tams.svreport.chb.proddurationreg.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;


/**
 * 存续期交易登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ProdDurationRegMService
 * @since 1.0
 */
@Service
public class ProdDurationRegMService extends BaseService<ProdDurationReg001Dto> {
    
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   public Page<ProdDurationReg001Dto> selectForPage(Map<String, Object> param) {
       return restTemplate.postForPage(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdDurationReg/findPage",param,
	       		ProdDurationReg001Dto.class);
   }
   
   public List<ProdDurationReg001Dto> searchForExport(Map<String, Object> param) {
       return restTemplate.postForList(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdDurationReg/selectForExport",param,
	       		ProdDurationReg001Dto.class);
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
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdDurationReg/updateSumStaus",map,
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
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdDurationReg/cancelMannul",map,
	       		String.class);
	}
	
	 /**
	    * 数据检查
	    * @param map 查询条件
	    * @return 检查结果
	    * @see
	    * @since 1.0
	    */
	   public Map<String,Object> reportCheck(Map<String,Object> map) {
		   return restTemplate.postForObject(
		       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdDurationReg/reportCheck", map,
		       		Map.class);
	   }
}
