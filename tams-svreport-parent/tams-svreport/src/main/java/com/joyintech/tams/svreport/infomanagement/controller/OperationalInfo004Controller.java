
/**  
* @Title: OperationalInfo004Controller.java
* @Package com.joyintech.tams.svreport.infomanagement.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author gaojian
* @date 2019年7月2日
* @since V1.0  
* @Co.,Ltd. 安徽兆尹信息科技有限责任公司
*/
    
package com.joyintech.tams.svreport.infomanagement.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfo004Service;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfoMService;

/**
* @ClassName: OperationalInfo004Controller
* @Description: 详细配置业务数据维护的查询条件和查询结果
* @author gaojian
* @date 2019年7月2日 
*
*/
@Controller
@PageId("SvrOperationalInfo004")
@RequestMapping("/common/infomanagement/SvrOperationalInfo004")
public class OperationalInfo004Controller extends BaseController{
	
	@Autowired
	private OperationalInfoMService operationalInfoMService;
	
	@Autowired
	private OperationalInfo004Service operationalInfo004Service;
	
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
		// 待维护表ID
		String mgrTableId = this.getParam("mgrTableId");
		// 待维护表名
		String mgrTableName = this.getParam("mgrTableName");
		// 待维护表中文名
        String mgrTableChiName = this.getParam("mgrTableChiName");
        // 待维护表接口名称
        String dataObject = this.getParam("dataObject");
        model.addAttribute("mgrTableId", mgrTableId);
		model.addAttribute("mgrTableName", mgrTableName);
		model.addAttribute("mgrTableChiName", mgrTableChiName);
		if (StringUtil.isNotEmpty(dataObject)) {
			model.addAttribute("dataObject", dataObject);
		} else {
			model.addAttribute("dataObject", "");
		}
		// 字段类型枚举
		List<SelectOptionDto> baseColumnTypeList = new ArrayList<SelectOptionDto>();
		Map<String, String> baseColumnTypeMap = operationalInfo004Service.querybaseColumnType();
		List<SelectOptionDto> basePkList = new ArrayList<SelectOptionDto>();
		Map<String, String> basePkMap = queryPkFlag();
		for (String key : baseColumnTypeMap.keySet()) {
			SelectOptionDto baseColumnType = new SelectOptionDto();
			baseColumnType.setId(key+"");
			baseColumnType.setText(baseColumnTypeMap.get(key)+"");
			baseColumnTypeList.add(baseColumnType);
		}
		for (String key : basePkMap.keySet()) {
			SelectOptionDto baseColumnType = new SelectOptionDto();
			baseColumnType.setId(key+"");
			baseColumnType.setText(basePkMap.get(key)+"");
			basePkList.add(baseColumnType);
		}
		model.addAttribute("BASE_COLUMN_TYPE_LIST", JsonUtils.objectArray2Json(baseColumnTypeList.toArray()));
		model.addAttribute("PK_FLAG", JsonUtils.objectArray2Json(basePkList.toArray()));
		return super.init(model);
	}
	
	
	
	
	
	/**
	* @Title: queryPkFlag
	* @Description: 是否业务主键枚举值
	* @param @return    参数
	* @return Map<String,String>    返回类型
	* @throws
	*/
	private Map<String, String> queryPkFlag() {
		Map<String, String> basePkMap = new HashMap<String, String>();
		basePkMap.put("是", "Y");
		basePkMap.put("否", "N");
		return basePkMap;
	}
	
	/**
	* @Title: check
	* @Description: (数据检查)
	* @param @return    参数
	* @return AjaxResult    返回类型
	* @throws
	*/
	@ResponseBody
    @RequestMapping("check")
    public List<ExcelValidDto> check() {
		return operationalInfoMService.check(RequestUtil.getPageParam());
    }
	
	/**
	* @Title: saveData
	* @Description: (保存查询条件和结果方案)
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	@ResponseBody
    @RequestMapping("savePlanData")
	public String savePlanData() {
		return operationalInfoMService.savePlanData(RequestUtil.getPageParam());
	}
}
