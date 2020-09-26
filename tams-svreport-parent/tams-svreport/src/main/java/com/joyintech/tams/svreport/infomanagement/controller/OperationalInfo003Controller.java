
/**  
* @Title: OperationalInfo003Controller.java
* @Package com.joyintech.tams.svreport.infomanagement.controller
* @Description: 业务数据维护待维护表新建
* @author gaojian
* @date 2019年7月2日
* @since V1.0  
* @Co.,Ltd. 安徽兆尹信息科技有限责任公司
*/
    
package com.joyintech.tams.svreport.infomanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfoMService;

/**
* @ClassName: OperationalInfo003Controller
* @Description: 业务数据维护待维护表新建
* @author gaojian
* @date 2019年7月2日 
*
*/

@Controller
@PageId("SvrOperationalInfo003")
@RequestMapping("/common/infomanagement/SvrOperationalInfo003")
public class OperationalInfo003Controller extends BaseController{
	
	@Autowired
	private OperationalInfoMService operationalInfoMService;
	/* 
	* 
	* 
	* @param model
	* @return 初始化页面
	* @see com.joyintech.framework.base.BaseController#init(org.springframework.ui.Model)
	*/
	    
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
		return super.init(model);
	}
	
	/**
	* @Title: checkData
	* @Description: 检查前台输入的待维护表代码是否合理
	* @param @return    前台参数
	* @return AjaxResult    检查结果
	* @throws
	*/
	@ResponseBody
	@PostMapping("checkData")
	public AjaxResult checkData() {
		return operationalInfoMService.checkData(RequestUtil.getPageParam());
	}
	
	/**
	* @Title: saveData
	* @Description: 存入待维护表信息
	* @param     参数
	* @return void    返回类型
	* @throws
	*/
	@ResponseBody
	@PostMapping("saveData")
	public void saveData() {
		operationalInfoMService.saveData(RequestUtil.getPageParam());
	}
}
