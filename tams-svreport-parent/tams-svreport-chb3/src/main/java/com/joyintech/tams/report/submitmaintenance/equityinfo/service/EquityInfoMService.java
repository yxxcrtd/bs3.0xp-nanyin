package com.joyintech.tams.report.submitmaintenance.equityinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 投资者身份信息维护
 *
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @since 1.0
 */
@Service
public class EquityInfoMService extends CommonExportService<EquityInfo001Dto> {

    /**
     * 查询列表
     *
     * @return Page
     */
    public Page<EquityInfo001Dto> searchForPage(Map<String, Object> param) {
        return restTemplate.postForPage(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/findPage",param,
        		EquityInfo001Dto.class);
    }


    /**
     * 导出选择数据
     *
     * @param expKeys 权益人信息主键
     * @return 权益人信息List
     * @see
     * @since 1.0
     */
	public List<EquityInfo001Dto> queryExportSelectList(String expKeys) { 
        return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/queryExportSelectList", expKeys,
        		EquityInfo001Dto.class);
    }
    
    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param fileName 文件名
     * @return 校验结果
     * @see
     * @since 1.0
     */

	public List<ExcelValidDto> importData(String fileName) {
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
    	param.put("tempList", ExcelUtil.getTempList(fileName));
    	return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/importData", 
        		param,
        		ExcelValidDto.class);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName) {
    	return restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/checkConfirmMsgForImportData", 
        		ExcelUtil.getDataList(fileName),
        		SvrAjaxResult.class);
    }
    
    /**
     * 删除 <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
	public List<ValidMessage> checkDeleteData(List<EquityInfo001Dto> list){
    	return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/checkDeleteData", list,
        		ValidMessage.class);
    }

    /**
     * 删除 <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public void doDelete(List<EquityInfo001Dto> list) {
    	restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/doDelete", list,
        		Integer.class);
    }


    /**
     * 导入代销数据
     *
     * @param fileName 文件名
     * @return 校验结果
     * @see
     * @since 1.0
     */
	public List<ExcelValidDto> importChbData(String fileName){
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
    	param.put("tempList", ExcelUtil.getTempList(fileName));
		param.put("dataTypeList", ExcelUtil.getDataList(fileName));
    	return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/importChbData", param,
        		ExcelValidDto.class);
    }


    /**
     * 导入代销数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForChbImportData(String fileName) {
     	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
    	param.put("tempList", ExcelUtil.getTempList(fileName));
    	return restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/checkConfirmMsgForChbImportData", 
        		param,
        		SvrAjaxResult.class);
    }

    /**
     * 修改的check <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public AjaxResult checkEdit(EquityInfo001Dto dto) {
    	return restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/checkEdit", dto,
        		SvrAjaxResult.class);
    }
    
    /**
     * 获取entity实例
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public EquityInfo001Dto getEntity(Map<String, Object> map) {
    	return restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/getEntity", map,
        		EquityInfo001Dto.class);
    }
    
    /**
     * 判断该数据是否已经报送完成
     * <功能详细描述>
     * @param dto 待校验数据
     * @return 标记
     * @see
     * @since 1.0
     */
    public String confirmSubmit(EquityInfo001Dto dto) {
    	return restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/confirmSubmit", dto,
        		String.class);
    }
    
    /**
     * 
     * check
     * <功能详细描述>
     * @param dto 待校验数据
     * @return jiaoyanjieguo
     * @see
     * @since 1.0
     */
   public AjaxResult checkOrSave(EquityInfo001Dto dto,boolean flag){
	   if(StringUtil.isEmpty(dto.getBankCode())){
           dto.setBankCode(CommonUtil.getUserBankCode());
       }
	   if(flag){
		   return restTemplate.postForObject(
		       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/doSave", dto,
		       		SvrAjaxResult.class);
	   }else{
		   return restTemplate.postForObject(
		       		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfo/doCheck", dto,
		       		SvrAjaxResult.class);
	   }
   }
}
