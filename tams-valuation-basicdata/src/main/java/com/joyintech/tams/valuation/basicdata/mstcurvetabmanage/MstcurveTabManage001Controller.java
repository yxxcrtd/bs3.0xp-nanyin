package com.joyintech.tams.valuation.basicdata.mstcurvetabmanage;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:收益法曲线管理tab页
 * @auther: yuzj
 * @date: 2021/9/27
 * @param:
 * @return:
 */
@Controller
@PageId("MstcurveTabManage001")
@RequestMapping("/valuation/basicdata/mstcurvedefmanage/MstcurveTabManage001")
public class MstcurveTabManage001Controller extends BaseController {

	/**
	 * 页面初始化
	 *
	 *
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		String lossType = CacheUtil.getSysParam(ValComponentConstant.LOSSTYPE);
		model.addAttribute("lossType", lossType);
		return super.init(model);
	}
}
