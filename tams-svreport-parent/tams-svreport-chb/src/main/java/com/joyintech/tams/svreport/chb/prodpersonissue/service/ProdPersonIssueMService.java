package com.joyintech.tams.svreport.chb.prodpersonissue.service;

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
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdInfo;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdPersonIssue001Dto;


/**
 * 个人发行登记
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ProdPersonIssueMService
 * @since 1.0
 */
@Service
public class ProdPersonIssueMService extends BaseService<ProdPersonIssue001Dto>{

    /**
    *
    * 查询功能
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public Page<ProdPersonIssue001Dto> searchForPage(Map<String, Object> param){
		return restTemplate.postForPage(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdPersonIssue/findPage",param,
	       		ProdPersonIssue001Dto.class);
   }
   /**
   *
   * 导出查询功能
   * <功能详细描述>
   * @return 存储了查询结果的list
   * @see
   * @since 1.0
   */
  public List<ProdPersonIssue001Dto> searchForExport(Map<String, Object> param){
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdPersonIssue/selectForExport",param,
	       		ProdPersonIssue001Dto.class);
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
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdPersonIssue/updateSumStaus",map,
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
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdPersonIssue/cancelMannul",map,
	       		String.class);
	}

	public List<ExcelValidDto> reportCheck(Map<String, Object> map) {
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdPersonIssue/reportCheck", map,
	       		ExcelValidDto.class);
	}
	    
}
