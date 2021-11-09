package com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.batch.batchstart.service.SysBatchStart001Service;
import com.joyintech.tams.common.base.entity.ChbBatchPara;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dto.SalesTransactionMaintenanceRegist001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 投资者身份信息登记画面
 *
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 */
@Service
public class SalesTransactionMaintenanceRegistMService extends BaseService<SalesTransactionMaintenanceRegist001Dto> {
	
    @Autowired
    private SysBatchStart001Service sysBatchStart001Service;
	 /**
    *
    * 查询功能
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public Page<SalesTransactionMaintenanceRegist001Dto> searchForPage(Map<String, Object> param){
	   return restTemplate.postForPage(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenanceRegist/findPage", param,
			   SalesTransactionMaintenanceRegist001Dto.class);
   }

	/**
	 * 数据检查
	 * @param map 查询条件
	 * @return 检查结果
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> reportCheck(Map<String,Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenanceRegist/reportCheck", map,
				ExcelValidDto.class);
	}
   /**
    *
    * 查询功能（无分页）
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   public List<SalesTransactionMaintenanceRegist001Dto> queryForInfoRegist(Map<String, Object> param){
	   return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenanceRegist/queryForInfoRegist",
			   param,
			   SalesTransactionMaintenanceRegist001Dto.class);
   }



   /**
    *
    * 导入
    * <功能详细描述>
    * @param fileName 文件
    * @return 导入数据
    * @see
    * @since 1.0
    */
   public List<ExcelValidDto> importData(String fileName){
		Map<String,Object> param = new HashMap<String,Object>();
	   	param.put("dataList", ExcelUtil.getDataList(fileName));
	   	param.put("deptCode", CommonUtil.getDeptCode());
	   	param.put("orgCode", CommonUtil.getOrgCode());
	    return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfoRegist/importData", 
	       		param,
	       		ExcelValidDto.class);
  }
   /**
    *手工处理功能
    * @return 提示信息
    * @param map 条件map
 * @return 
    * @seea
    * @since 1.0
    */
   @ResponseBody
   public AjaxResult doUpdate(Map<String,Object> map){
	   map.put("deptCode", CommonUtil.getDeptCode());
	   if(map.get("flag") != null && "01".equals(map.get("flag"))){
			ChbBatchPara record = new ChbBatchPara();
		    record.setTaskId(BatchBeanName.投资者身份信息登记手工处理.getTaskId());
		    record.setParaId(IDGenerator.generate());
		    if (map.get("key") != null) {
		        record.setParas(map.get("key").toString());
		        restTemplate.postForObject(
         	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenanceRegist/insert", record,
         	       	ChbBatchPara.class);
		    }
		    
		    sysBatchStart001Service.executeBatch();
		    return new AjaxResult(this.getMessage("REPORT_MANUAL_0009"));
	   }else{
		   restTemplate.postForObject(
		       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenanceRegist/doUpdate", map,
		       		Integer.class);
		   return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	   }
	   

   }

   /**
    *取消手工处理功能
    * @return 提示信息
    * @param map 条件map
    * @see
    * @since 1.0
    */
   public void cancelMannulAll(Map<String,Object> map) {
	   map.put("deptCode", CommonUtil.getDeptCode());
	   restTemplate.postForObject(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfoRegist/cancelMannulAll", map,
	       		Integer.class);
   }

   /**
    *取消手工处理功能
    * @return 提示信息
    * @see
    * @since 1.0
    */
   public void cancelMannul(String upKey) {
	   restTemplate.postForObject(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfoRegist/cancelMannul", upKey,
	       		Integer.class);
   }
   /**
    * checkIsNotSubmit
    * @return 提示信息
    * @since 1.0
    */
public List<ValidMessage> checkIsNotSubmit(String upKey) {
	   return  restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfoRegist/checkIsNotSubmit", upKey,
	       		ValidMessage.class);
   	
   }
   
   /**
    * 将选择的数据导出成excel
    * 查询数据
    * @return 查询结果
    * @see
    * @since 1.0
    */
   public List<SalesTransactionMaintenanceRegist001Dto> queryExportSelectRegistList(String exportKeys){
	   return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenanceRegist/queryExportSelectRegistList", exportKeys,
	       		SalesTransactionMaintenanceRegist001Dto.class);
   }

   /**
    * 将选择的数据导出成excel
    * 查询数据
    * @return 查询结果
    * @see
    * @since 1.0
    */
   public List<SalesTransactionMaintenanceRegist001Dto> queryExportRegistDelList(Map<String,Object> paramMap){
	   return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfoRegist/queryExportSelectRegistList", paramMap,
	       		SalesTransactionMaintenanceRegist001Dto.class);
   }

   /**
    * 中债直连报送 二次报送
    * @param paramMap 传入需要报送的主键
    * @return 反馈信息
    * @see
    * @since 1.0
    */
   public void submitCbAgain(Map<String,Object> paramMap){
	   restTemplate.postForObject(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfoRegist/submitCbAgain", paramMap,
	       		Integer.class);
   }
   
   public boolean checkIfInBatchByTaskBeanName(String beanname) {
   	return restTemplate.postForObject(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfoRegist/checkIfInBatchByTaskBeanName", beanname,
	       	boolean.class);
   }
   
   
}