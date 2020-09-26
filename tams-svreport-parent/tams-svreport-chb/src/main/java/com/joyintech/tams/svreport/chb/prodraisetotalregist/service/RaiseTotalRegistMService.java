package com.joyintech.tams.svreport.chb.prodraisetotalregist.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.dto.RaiseTotalRegist001Dto;

/**
 * 募集总量登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see RaiseTotalRegistMService
 * @since 3.0
 */
@Service
public class RaiseTotalRegistMService  extends BaseService<RaiseTotalRegist001Dto>{

    
    /**
     * @Description 产品募集总量登记分页查询
     * @return Page<InvestorsHoldInfo001Dto>
     * @version 3.0 2019年1月22日
     */
    public Page<RaiseTotalRegist001Dto> selectForPage(Map<String, Object> param) {
        return restTemplate.postForPage(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/RaiseTotalRegist/findPage",param,
	       		RaiseTotalRegist001Dto.class);
    }
    
    /**
    * 业务画面导出事件
    * @return
    * @since 1.0
    */
   public List<RaiseTotalRegist001Dto> selectForExport(Map<String, Object> param) {
         return restTemplate.postForList(
 	       		SvrServiceApplication.CHB_SERVICE_NAME + "/RaiseTotalRegist/selectForExport",param,
 	       	RaiseTotalRegist001Dto.class);
   }
   
   /**
    *手工处理功能
    * @return 提示信息
    * @param map 条件map
    */
   public void updateSumStaus(Map<String,Object> map) {
	   restTemplate.postForObject(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/RaiseTotalRegist/updateSumStaus",map,
	       		Integer.class);
   }
   
   /**
    * @Description 取消手工处理
    * @param map
    * @return
    * @return AjaxResult
    * @version 3.0 2019年1月24日
    */
	public String cancelMannul(Map<String, Object> map) {
		return restTemplate.postForObject(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/RaiseTotalRegist/cancelMannul",map,
	       		String.class);
	}
	
	public List<ExcelValidDto> reportCheck(Map<String, Object> map) {
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/RaiseTotalRegist/reportCheck", map,
	       		ExcelValidDto.class);
	}

}
