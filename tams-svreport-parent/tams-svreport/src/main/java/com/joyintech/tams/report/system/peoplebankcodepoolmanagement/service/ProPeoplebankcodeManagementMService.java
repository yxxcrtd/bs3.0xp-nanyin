package com.joyintech.tams.report.system.peoplebankcodepoolmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dto.ProPeoplebankcodeManagement001Dto;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 人行产品资产池代码管理Service
 * 
 * @author kx
 *
 */
@Service
public class ProPeoplebankcodeManagementMService extends BaseService<ProPeoplebankcodeManagement001Dto> {

	/**
	 * 内部产品代码下拉框
	 * 
	 * @return 产品名称列表
	 */
	public List<SelectOptionDto> queryInsideProdIdList(Map<String, Object> paramMap) {
		return restTemplate.postForList(SvrServiceApplication.COMMON_SERVICE_NAME + "/ProPeoplebankcodeManagement/getInsideProdId",
				paramMap, SelectOptionDto.class);
	}
	
	  /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<ProPeoplebankcodeManagement001Dto> searchForPage(Map<String,Object> paramMap) {
    	return restTemplate.postForPage(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProPeoplebankcodeManagement/findPage", paramMap,
	       		ProPeoplebankcodeManagement001Dto.class);
    }
    
    /**
     * 导出excel查询
     *
     * @return 结果数据
     */
    public List<ProPeoplebankcodeManagement001Dto> queryReportList(Map<String,Object> paramMap) {
        return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProPeoplebankcodeManagement/queryReportList", paramMap,
	       		ProPeoplebankcodeManagement001Dto.class);
    }
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
     	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
    	param.put("tempList", ExcelUtil.getTempList(fileName));
    	param.put("orgCode", CommonUtil.getOrgCode());
    	param.put("deptCode", CommonUtil.getDeptCode());
    	return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProPeoplebankcodeManagement/importData", 
	       		param,
	       		ExcelValidDto.class);
    }
    
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
    	param.put("orgCode", CommonUtil.getOrgCode());
    	param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProPeoplebankcodeManagement/checkConfirmMsgForImportData", 
	       		param,
	       		SvrAjaxResult.class);
    }
    
    

}
