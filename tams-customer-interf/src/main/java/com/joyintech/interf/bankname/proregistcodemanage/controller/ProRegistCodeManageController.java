package com.joyintech.interf.bankname.proregistcodemanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;

/**
 * 产品登记编码管理
 * @author wangqidong
 *
 */
@RestController("ProRegistCodeManageCtr_dtx")
@RequestMapping("/ProRegistCodeManage")
public class ProRegistCodeManageController {

	@SuppressWarnings("unchecked")
	@PostMapping("checkConfirmMsgForImportNewData")
    public AjaxResult checkConfirmMsgForImportNewData(@RequestBody Map<String, Object> param)  {   
		AjaxResult webResult = new AjaxResult();
    	List<List<String>> dataList = (List<List<String>>) param.get("dataList");
    	String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
		String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
    	//TODO 后续个性化处理具体实现
    	
    	webResult.setMessage("检查通过");
        return webResult;
    }
	
	/**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
	@PostMapping("importNewFile")
    public List<ExcelValidDto> importNewFile(@RequestBody Map<String, Object> param) {
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
		String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
		//TODO 后续个性化处理具体实现
		
		return null;
    }
}
