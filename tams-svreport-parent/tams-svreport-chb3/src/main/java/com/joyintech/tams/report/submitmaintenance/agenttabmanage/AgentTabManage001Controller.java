package com.joyintech.tams.report.submitmaintenance.agenttabmanage;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PageId("AgentTabManage001")
@RequestMapping("/report/submitmaintenance/agenttabmanage/AgentTabManage001")
public class AgentTabManage001Controller extends BaseController {

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
