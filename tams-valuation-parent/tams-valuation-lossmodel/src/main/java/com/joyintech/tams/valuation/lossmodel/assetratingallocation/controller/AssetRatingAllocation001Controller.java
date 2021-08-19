package com.joyintech.tams.valuation.lossmodel.assetratingallocation.controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * <p>Title: AssetRatingAllocation001Controller<／p>
 * <p>Description: 减值资产评级配置Controller<／p>
 * <p>Company: 安徽兆尹信息科技股份有限公司<／p> 
 * @author yuluan
 * @date 2021年6月23日
 */
@Controller
@PageId("AssetRatingAllocation001")
@RequestMapping("/model/impairment/assetratingallocation/AssetRatingAllocation001")
public class AssetRatingAllocation001Controller extends BaseController {
	
	private static Map<String, String> sendstatusMap = new LinkedHashMap<String, String>();
	static {
		sendstatusMap.put("01", "未报送");
		sendstatusMap.put("03", "手工处理");
		
	}
	public static List<SelectOptionDto> listSendStatus() {
		List<SelectOptionDto> penetrationReportList = new LinkedList<SelectOptionDto>();
		for (String key : sendstatusMap.keySet()) {
			SelectOptionDto selectOptionDto = new SelectOptionDto();
			selectOptionDto.setId(key);
			selectOptionDto.setText(sendstatusMap.get(key));
			selectOptionDto.setCol("");
			penetrationReportList.add(selectOptionDto);
		}
		return penetrationReportList;
	}
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
	    return "/lossmodel/assetratingallocation/assetcategories/AssetCategories001";
	}
	@RequestMapping("Specialassets")
    public String showSpecialAssetsPage(Model model) {
		return "/lossmodel/assetratingallocation/specialassets/Specialassets001";
	}
	
}
