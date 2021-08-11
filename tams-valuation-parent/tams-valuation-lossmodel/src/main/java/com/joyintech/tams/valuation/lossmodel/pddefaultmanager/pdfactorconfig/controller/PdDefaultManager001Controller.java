package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * <p>
 * Title: PdDefaultManager001Controller<／p>
 * <p>
 * Description:PD违约概率管理Controller<／p>
 * <p>
 * Company: 安徽兆尹信息科技股份有限公司<／p>
 * 
 * @author zhangfei
 * @date 2021年6月26日
 */
@Controller
@PageId("PdDefaultManager001")
@RequestMapping("/model/impairment/pddefaultmanager/pdfactorconfig/PdDefaultManager001")
public class PdDefaultManager001Controller extends BaseController {

	/**
	 * 页面初始化
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		model.addAttribute("isShowMyHome", true);
		model.addAttribute("isShowRemind", true);

		return super.init(model);
	}
	/*
	 * @RequestMapping("assetcategories") public String
	 * showAssetCateGoriesPage(Model model) { return "assetcategories"; }
	 *//**
		 * 跳转前瞻因子维护页面
		 * 
		 * @param model
		 * @return
		 *//*
			 * @RequestMapping("PdFactorConfig001") public String
			 * showSpecialAssetsPage(Model model) { return
			 * "/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001"; }
			 */

}
