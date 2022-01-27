package com.joyintech.tams.report.submitmanagement.salesstaffmanageregist;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

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
		Map<String, Object> map = RequestUtil.getPageParam();
		model.addAttribute("pageType",map.get("pageType"));
		if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
			model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
			if (StringUtil.isNotEmpty(map.get("status"))) {
				model.addAttribute("status", map.get("status").toString().trim());
			}
			if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
				model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
			}
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
		model.addAttribute("lossType", lossType);
		return super.init(model);
	}
}
