package com.joyintech.tams.report.submitmanagement.salesstaffmanageregist;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PageId("SalesStaffManageRegist")
@RequestMapping("/report/submitmanagement/salesstaffmanageregist/SalesStaffManageRegist")
public class SalesStaffManageRegistController extends BaseController {

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
