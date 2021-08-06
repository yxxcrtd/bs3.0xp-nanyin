package com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: AssetRatingAllocation001Controller<／p>
 * <p>Description: 减值资产评级配置Controller<／p>
 * <p>Company: 安徽兆尹信息科技股份有限公司<／p> 
 * @author yuluan
 * @date 2021年6月23日
 */
@Controller
@PageId("LossGivenDefaultManage001")
@RequestMapping("/model/impairment/lossgivendefaultmanage/LossGivenDefaultManage001")
public class LossGivenDefaultManage001Controller extends BaseController {
	
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
	@RequestMapping("assetcategories")
    public String showAssetCateGoriesPage(Model model) {
		return "assetcategories";
	}
	@RequestMapping("maintain")
    public String showSpecialAssetsPage(Model model) {
		return "/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain001";
	}
	
}
