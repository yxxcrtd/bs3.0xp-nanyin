package com.joyintech.tams.invest.basicdata.assetdeclarationcodemaintenance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.assetdeclarationcodemaintenance.dto.AssetDeclarationCodeMaintenanceDto;
import com.joyintech.tams.invest.basicdata.assetdeclarationcodemaintenance.service.AssetDeclarationCodeMaintenanceService;

@Controller
@PageId("AssetDeclarationCodeMaintenance002")
@RequestMapping("/invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance002")
public class AssetDeclarationCodeMaintenance002Controller extends BaseController {
	/**
	 * service
	 */
	@Autowired
	private AssetDeclarationCodeMaintenanceService assetDeclarationCodeMaintenanceService;

	/**
	 * 
	 * 查询页面初始化
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.金融产品其他代码类型);
	    // delete by dubaojun 2020-07-29 将资产代码下拉框修改为分页查询 start
//		try {
//			List<SelectOptionDto> finprodIds = getFinprodIds();
//			model.addAttribute("finprodIds", JsonUtils.objectArray2Json(finprodIds.toArray()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	    // delete by dubaojun 2020-07-29 将资产代码下拉框修改为分页查询 end
		if ("edit".equals(getParam("operateType")) || "detail".equals(getParam("operateType"))) {
			AssetDeclarationCodeMaintenanceDto dto = new AssetDeclarationCodeMaintenanceDto();
			dto.setOthCode(getParam("othCode"));
			AssetDeclarationCodeMaintenanceDto entity = assetDeclarationCodeMaintenanceService.selectByUniqueKey(dto);
			if (entity != null) {
				model.addAttribute("entity", entity);
			}
		}
		return super.init(model);
	}

	// modify by dubaojun 2020-07-29 将资产代码下拉框修改为分页查询 start
    @ResponseBody
    @RequestMapping("getFinprodIds")
	public Page<SelectOptionDto> getFinprodIds() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String param = super.getRequest().getParameter("search");
		map.put("finprodId", param);
		// add by dubaojun 2020-07-30 画面资产代码展示为市场代码 start
		map.put("initValue", super.getRequest().getParameter("initValue"));
		// add by dubaojun 2020-07-30 画面资产代码展示为市场代码 end
		Page<SelectOptionDto> temp = assetDeclarationCodeMaintenanceService.getFinprodIdPage(map);
		return temp;
	}
    // modify by dubaojun 2020-07-29 将资产代码下拉框修改为分页查询 end

	/**
	 * 画面查询方法
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("selectOthCodeType")
	public List<SelectOptionDto> selectOthCodeType() throws Exception {
		List<SelectOptionDto> othCodeType = new ArrayList<SelectOptionDto>();
		SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.金融产品其他代码类型, null);
		Map<String, Object> map = RequestUtil.getParameters(getRequest());
		if (StringUtils.isNotEmpty((String) map.get("finprodId"))) {// 选择资产
			List<SelectOptionDto> finprodType = assetDeclarationCodeMaintenanceService.getfinprodType(map);// 获取资产类型
			if (finprodType != null && finprodType.size() > 0) {
				String finprodTypeTg = finprodType.get(0).getText();// 根据资产类型债券
				if (finprodTypeTg.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
					for (SelectOptionDto dto : selectDto1.getOptions()) {
						if (DictCodeListExt3.金融产品其他代码类型_回售代码.getValue().equals(dto.getId())
								|| DictCodeListExt3.金融产品其他代码类型_质押代码.getValue().equals(dto.getId())
								|| DictCodeListExt3.金融产品其他代码类型_可转债信用申购代码.getValue().equals(dto.getId())
								|| DictCodeListExt3.金融产品其他代码类型_可转债转股代码.getValue().equals(dto.getId())
								|| DictCodeListExt3.金融产品其他代码类型_配债代码.getValue().equals(dto.getId())) {
							othCodeType.add(dto);
						}
					} // 根据资产类型股票
				} else if (finprodTypeTg.equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
					for (SelectOptionDto dto : selectDto1.getOptions()) {
						if (DictCodeListExt3.金融产品其他代码类型_配股代码.getValue().equals(dto.getId())
								|| DictCodeListExt3.金融产品其他代码类型_新股申购代码.getValue().equals(dto.getId())) {
							othCodeType.add(dto);
						}
					}
				}
			}
		} else {// 全部
			for (SelectOptionDto dto : selectDto1.getOptions()) {
				othCodeType.add(dto);
			}
		}
		return othCodeType;
	}
}
