/*
 * 文件名：ReportContentEnum003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage004Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 指标库详细信息,产品树
 * 
 * @author lishicheng
 * @version 1.0 2018年11月7日
 * @see DisProdPlanManage004Controller
 * @since 1.0
 */
@Controller
@PageId("DisProdPlanManage004")
@RequestMapping("/disclosure/information/config/disprodplanmanage/DisProdPlanManage004")
public class DisProdPlanManage004Controller extends BaseController {

	@Autowired
	private DisProdPlanManage004Service disProdPlanManage004Service;
	/**
	 * 内容初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		//自定义指标id
		String repIndexId = this.getParam("repIndexId");
		model.addAttribute("repIndexId",repIndexId);
		return super.init(model);
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/getAllProdIds")
	public List<TreeNode> getAllProdIds() {
		String repIndexId=super.getRequest().getParameter("repIndexId");

		if(StringUtil.isNotEmpty(repIndexId)){
			//return reportContentEnum002Service.getProductTreeByAgencyId(agencyId);
			return null;
		} else{//新建
			return disProdPlanManage004Service.getProductTree();
		}
	}

}
