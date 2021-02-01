
/**  
* @Title: OperationalInfo002Controller.java
* @Package com.joyintech.tams.svreport.infomanagement.controller
* @Description: 业务数据维护待维护表管理Controller
* @author gaojian
* @date 2019年7月2日
* @since V1.0  
* @Co.,Ltd. 安徽兆尹信息科技有限责任公司
*/

package com.joyintech.tams.svreport.infomanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.BaseInformationDto;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.common.base.entity.ChbMgrTableInfo;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfoMService;

/**
 * @ClassName: OperationalInfo002Controller
 * @Description: 业务数据维护待维护表管理Controller
 * @author gaojian
 * @date 2019年7月2日
 *
 */

@Controller
@PageId("SvrOperationalInfo002")
@RequestMapping("/common/infomanagement/SvrOperationalInfo002")
public class OperationalInfo002Controller extends BaseController {
	@Autowired
	private OperationalInfoMService operationalInfoMService;
	/*
	 * 
	 * 
	 * @param model
	 * 
	 * @return 初始化页面
	 * 
	 * @see com.joyintech.framework.base.BaseController#init(org.springframework.ui.
	 * Model)
	 */

	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		List<SelectOptionDto> statusList = new ArrayList<SelectOptionDto>();
		SelectOptionDto selectOptionDto1 = new SelectOptionDto();
		selectOptionDto1.setId("E");
		selectOptionDto1.setText("有效");
		selectOptionDto1.setCol("");
		SelectOptionDto selectOptionDto2 = new SelectOptionDto();
		selectOptionDto2.setId("D");
		selectOptionDto2.setText("删除");
		selectOptionDto2.setCol("");
		statusList.add(selectOptionDto1);
		statusList.add(selectOptionDto2);
		model.addAttribute("statusList", JsonUtils.objectArray2Json(statusList.toArray()));
		return super.init(model);
	}

	/**
	 * @Title: searchPage @Description: 查询待维护表基本信息 @param @return 无 @return
	 * Page<ChbMgrTableInfo> 待维护表基本信息列表 @throws
	 */

	@ResponseBody
	@PostMapping("searchPage")
	public Page<ChbMgrTableInfo> searchPage() {
		return operationalInfoMService.searchPage(RequestUtil.getPageParam());
	}
	
	/**
	* @Title: updateTableStatus
	* @Description: 修改待维护表状态未有效
	* @param @return    参数
	* @return AjaxResult    返回类型
	* @throws
	*/
	@ResponseBody
	@PostMapping("updateTableStatus")
	public Integer updateTableStatus() {
		return operationalInfoMService.updateTableStatus(RequestUtil.getPageParam());
	}
	
	/**
	* @Title: queryConditionPage
	* @Description: 根据表名查询已配置查询条件列表
	* @param @return    参数
	* @return Page<BaseInformationDto> 查询条件列表
	* @throws
	*/
	@ResponseBody
	@PostMapping("queryConditionPage")
	public Page<BaseInformationDto> queryConditionPage() {
		return operationalInfoMService.queryConditionPage(RequestUtil.getPageParam());
	}
	
	/**
	* @Title: queryDataResultPage
	* @Description: 根据表名查询已配置查询结果列表
	* @param @return    参数
	* @return Page<ChbInfoMgrResultConfig> 查询结果列表
	* @throws
	*/
	    
	@ResponseBody
	@PostMapping("queryDataResultPage")
	public Page<ChbInfoMgrResultConfig> queryDataResultPage() {
		return operationalInfoMService.queryDataResultPage(RequestUtil.getPageParam());
	}


	/**
	 * @Title: queryDataResultPage
	 * @Description: 根据表名查询已配置查询结果列表
	 * @param @return    参数
	 * @return Page<ChbInfoMgrResultConfig> 查询结果列表
	 * @throws
	 */

	@ResponseBody
	@PostMapping("queryDataResultList")
	public List<ChbInfoMgrResultConfig> queryDataResultList() {
		return operationalInfoMService.queryDataResultList(RequestUtil.getPageParam());
	}
	
}
