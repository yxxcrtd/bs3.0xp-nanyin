package com.joyintech.tams.report.submitmaintenance.salesstaffmanage;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PageId("SalesStaffManage")
@RequestMapping("/report/submitmaintenance/salesstaffmanage/SalesStaffManage")
public class SalesStaffManageController extends BaseController {

	/**
	 * 页面初始化
	 *
	 *
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		String lossType = CacheUtil.getSysParam("LOSS_TYPE");
		model.addAttribute("lossType", lossType);
		return super.init(model);
	}
}
