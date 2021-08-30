package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.controller;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
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
	 *
	 *
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		model.addAttribute("lossType", CacheUtil.getSysParam(ValComponentConstant.LOSSTYPE));
		model.addAttribute("isShowMyHome", true);//展示前瞻因子配置（普华）
		model.addAttribute("isShowKpmgMyHome", true);//展示前瞻因子配置(毕马威)
		model.addAttribute("isShowRemind", true);//债券、其他债权评级PD维护
		model.addAttribute("isShowHgPd", true);  //回购评级PD维护
		model.addAttribute("isShowCkPd", true);  //存款评级PD维护
		return super.init(model);
	}
}
