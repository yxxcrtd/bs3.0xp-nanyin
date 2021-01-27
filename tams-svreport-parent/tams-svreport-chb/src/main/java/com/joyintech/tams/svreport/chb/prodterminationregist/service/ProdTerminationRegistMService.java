package com.joyintech.tams.svreport.chb.prodterminationregist.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.prodterminationregist.dto.ProdTerminationRegist001Dto;


/**
 * @Description 产品终止登记服务
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @since 3.0
 */
@Service		
public class ProdTerminationRegistMService extends BaseService<ProdTerminationRegist001Dto>{


    /**
     * @Description 产品终止登记页面查询
     * @return Page<ProdTerminationRegist001Dto>
     * @version 3.0 2019年1月23日
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ProdTerminationRegist001Dto> selectForPage(Map<String, Object> param) {
    	return restTemplate.postForPage(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdTerminationRegist/findPage",param,
	       		ProdTerminationRegist001Dto.class);
    }
    
    /**
     * 业务画面导出事件
     * @return
     * @since 3.0
     */
    public List<ProdTerminationRegist001Dto> selectForExport(Map<String, Object> param) {
          return restTemplate.postForList(
  	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdTerminationRegist/selectForExport",param,
  	       	ProdTerminationRegist001Dto.class);
    }
    /**
     *手工处理功能
     * @return 提示信息
     * @param map 条件map
     */
    @PostMapping("updateSumStaus")
    public void updateSumStaus(Map<String,Object> map) {
    	restTemplate.postForObject(
  	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdTerminationRegist/updateSumStaus",map,
  	       		Integer.class);
    }
    
    /**
     * @Description 取消手工处理
     * @param map
     * @return
     * @return AjaxResult
     * @version 3.0 2019年1月24日
     */
	@PostMapping("cancelMannul")
	public String cancelMannul(Map<String, Object> map) {
    	return restTemplate.postForObject(
  	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdTerminationRegist/cancelMannul",map,
  	       			String.class);
	}
	
	/**   
	* @Function: ProdTerminationRegistMService.java
	* @Description: 产品终止登记数据检查
	*
	* @param: 待检查数据
	* @return：产品终止登记数据检查结果
	*
	* @author: 王冠/wangguan
	* @date: 2019年6月25日 上午10:27:36 
	*/
	@PostMapping("reportCheck")
	public Map<String,Object> reportCheck(Map<String, Object> map) {
		return restTemplate.postForObject(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/ProdTerminationRegist/reportCheck", map,
				Map.class);
	}
}
