package com.joyintech.interf.bankname.mstnetassetcustominfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
/**
 * 计划类资产行情信息
 * @author wangqidong
 *
 */
@RestController("MstNetAssetCustomInfoCtr_dtx")
@RequestMapping("/MstNetAssetCustomInfo")
public class MstNetAssetCustomInfoController {

	/**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
    	AjaxResult webResult = new AjaxResult();
    	List<List<String>> dataList = (List<List<String>>) param.get("dataList");
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
	@PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		//TODO 后续个性化处理具体实现
		
		return null;
    }
}
