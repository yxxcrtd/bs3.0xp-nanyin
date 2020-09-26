package com.joyintech.tams.report.submitmanagement.investorsholdinforegist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.batch.batchstart.service.SysBatchStart001Service;
import com.joyintech.tams.common.base.entity.ChbBatchPara;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 投资者持有信息登记服务
 *
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see InvestorsHoldInfoRegistMService
 * @since 1.0
 */
@Service
public class InvestorsHoldInfoRegistMService extends BaseService<InvestorsHoldInfoRegist001Dto>{
	
    @Autowired
    private SysBatchStart001Service sysBatchStart001Service;
    
	/**
	 *
	 * 机构代码下拉框取得值
	 *
	 * @return 下拉框值
	 * @see
	 * @since 1.0
	 */
	public List<SuggestDto> getInstitutionCode(@RequestBody String param) {
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/getInstitutionCode", param,
	       		SuggestDto.class);
	}
	
	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<InvestorsHoldInfoRegist001Dto> selectForPage(Map<String, Object> param) {
    	return restTemplate.postForPage(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/findPage", param,
	       		InvestorsHoldInfoRegist001Dto.class);
	}

	/**
	 *
	 * 导入
	 *
	 * @param fileName
	 *            文件
	 * @return 导入数据
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> importData(String fileName) {
		Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
    	param.put("orgCode", CommonUtil.getOrgCode());
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/importData", 
	       		param,
	       		ExcelValidDto.class);
	}

	/**
	 *
	 * 业务画面导出查询
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<InvestorsHoldInfoRegist001Dto> selectAllList(Map<String, Object> pageParam) {
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/selectAllList", pageParam,
	       		InvestorsHoldInfoRegist001Dto.class);
	}

	/**
	 * 报送检查
	 *
	 * @return 检查结果
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> sendBeforeCheck(Map<String, Object> pageParam) {
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/sendBeforeCheck", pageParam,
	       		ExcelValidDto.class);
	}

	/**
	 * 中债直连报送
	 *
	 * @param map
	 *            传入需要报送的主键
	 * @return 反馈信息
	 * @see
	 * @since 1.0
	 */
	public void doSubmit(Map<String, Object> map) {
		restTemplate.postForObject(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/submitChb", map,
	       		Integer.class);
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
	@ResponseBody
	public AjaxResult doUpdate(Map<String, Object> map) {
		
		if(map.get("flag") != null && "01".equals(map.get("flag"))){
			//处理全部数据
			ChbBatchPara record = new ChbBatchPara();
		    record.setTaskId(BatchBeanName.投资者持有信息登记手工处理.getTaskId());
		    record.setParaId(IDGenerator.generate());
		    if (map.get("key") != null) {
		        record.setParas(map.get("key").toString());
				restTemplate.postForObject(
			       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/insert", record,
			       		Integer.class);
		    }
		    sysBatchStart001Service.executeBatch();
		    return new AjaxResult(this.getMessage("REPORT_MANUAL_0009"));
			
			
		}else{
			restTemplate.postForObject(
		       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/doUpdate", map,
		       		Integer.class);
		}
		
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
		
		
		
		

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
	public void cancelMannulAll(Map<String, Object> map) {
		restTemplate.postForObject(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/cancelMannulAll", map,
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
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/cancelMannul", map,
	       		Integer.class);
	}
	/**
	 * checkIsNotSubmit
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	public List<ValidMessage> checkIsNotSubmit(Map<String, Object> map) {
		return restTemplate.postForList(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/checkIsNotSubmit", map,
	       		ValidMessage.class);
	}
	/**
	 * 重新报送
	 *
	 * @param map
	 *            传入需要报送的主键
	 * @return 反馈信息
	 * @see
	 * @since 1.0
	 */
	public void doSubmitAgain(Map<String, Object> map) {
		restTemplate.postForObject(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/submitCbAgain", map,
	       		Integer.class);

	}
	
	   public boolean checkIfInBatchByTaskBeanName(String beanname) {
		   	return restTemplate.postForObject(
			       		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/checkIfInBatchByTaskBeanName", beanname,
			       	boolean.class);
		   }
}