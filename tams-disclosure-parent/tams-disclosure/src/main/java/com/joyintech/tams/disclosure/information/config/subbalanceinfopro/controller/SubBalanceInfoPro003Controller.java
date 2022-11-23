/*
 * 文件名：ReportContentEnum003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.subbalanceinfopro.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage003Service;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.service.SubBalanceInfoPro002Service;
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
 * @see SubBalanceInfoPro003Controller
 * @since 1.0
 */
@Controller
@PageId("SubBalanceInfoPro003")
@RequestMapping("/disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro003")
public class SubBalanceInfoPro003Controller extends BaseController {

	@Autowired
	private SubBalanceInfoPro002Service subBalanceInfoPro002Service;
	/**
	 * 内容初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		//自定义指标id
		String repIndexId = this.getParam("repIndexId");
		model.addAttribute("repIndexId",repIndexId);

		String prodId = this.getParam("prodId");
		model.addAttribute("prodId",prodId);
		return super.init(model);
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/getAllProdIds")
	public List<TreeNode> getAllProdIds() {
		String repIndexId=super.getRequest().getParameter("repIndexId");
		String prodId=super.getRequest().getParameter("prodId");
		List<String>prodIdList=StringUtil.splitToList(",",prodId);
		if(StringUtil.isNotEmpty(repIndexId)){
			//return reportContentEnum002Service.getProductTreeByAgencyId(agencyId);
			return null;
		} else{//新建
			return subBalanceInfoPro002Service.getProductTree(prodIdList);
		}
	}

}
