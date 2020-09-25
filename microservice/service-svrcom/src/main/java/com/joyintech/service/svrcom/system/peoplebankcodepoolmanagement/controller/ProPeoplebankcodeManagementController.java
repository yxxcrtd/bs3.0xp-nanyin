package com.joyintech.service.svrcom.system.peoplebankcodepoolmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dto.ProPeoplebankcodeManagement001Dto;
import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.service.ProPeoplebankcodeManagement001Service;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;

/**
 * 人行产品资产池代码管理Controller
 * 
 * @author kx
 *
 */
@RestController
@RequestMapping("/ProPeoplebankcodeManagement")
public class ProPeoplebankcodeManagementController {

	@Autowired
	private ProPeoplebankcodeManagement001Service peoplebankcodeManagement001Service;

	/**
	 * 内部产品代码下拉框
	 * 
	 * @return 内部产品代码
	 */
	@PostMapping("getInsideProdId")
	public List<SelectOptionDto> queryInsideProdIdList(@RequestBody Map<String,Object> param){
		return peoplebankcodeManagement001Service.queryInsideProdIdList(param);
	}
	
	/**
     * 查询列表
     *
     * @return DtPage
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ProPeoplebankcodeManagement001Dto> search(@RequestBody Map<String,Object> paramMap) {
        return peoplebankcodeManagement001Service.searchForPage(paramMap);
    }
    
    /**
     * 导出excel查询
     *
     * @return 结果数据
     */
    @PostMapping("queryReportList")
    public List<ProPeoplebankcodeManagement001Dto> queryReportList(@RequestBody Map<String,Object> paramMap) {
        return peoplebankcodeManagement001Service.queryReportList(paramMap);
        
    }
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
	@PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
		String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return this.peoplebankcodeManagement001Service.importData(dataList,tempList,orgCode,deptCode);
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
    	List<List<String>> dataList = (List<List<String>>) param.get("dataList");
    	String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
		String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return peoplebankcodeManagement001Service.checkConfirmMsgForImportData(dataList,orgCode,deptCode);
    }
	
	

}
