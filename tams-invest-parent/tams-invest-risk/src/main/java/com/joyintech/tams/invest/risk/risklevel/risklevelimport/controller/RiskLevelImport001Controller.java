package com.joyintech.tams.invest.risk.risklevel.risklevelimport.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
 import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.risk.risklevel.risklevelimport.dto.RiskLevelImportDto;
import com.joyintech.tams.invest.risk.risklevel.risklevelimport.service.RiskLevelImport001Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@PageId("RiskLevelImport001")
@RequestMapping("/invest/risk/risklevel/RiskLevelImport001")
public class RiskLevelImport001Controller extends BaseController {

	@Autowired
	private RiskLevelImport001Service riskLevelImport001Service;

	private static final IDictCode[] DICT_CODES = new DictCode[] { DictCode.风险五级分类};

	/**
	 * 
	 * 查询页面初始化
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		List<SelectOptionDto> FinprodTypeTG = pickFinprodTypeTG();
		model.addAttribute("finprodTypeTG", JsonUtils.objectArray2Json(FinprodTypeTG.toArray()));
		setSelectOptionToModel(model, DICT_CODES);
		return super.init(model);
	}

	/**
	 * 生成资产类型的枚举值 <功能详细描述>
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	private List<SelectOptionDto> pickFinprodTypeTG() {
		List<SelectOptionDto> listFinprodType = new ArrayList<SelectOptionDto>();
		SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.金融产品类型_投管用, null);
		for (SelectOptionDto dto : selectDto1.getOptions()) {
			if (DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(dto.getId())
					|| DictCodeListExt1.金融产品类型_投管用_存款.getValue().equals(dto.getId())
					|| DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue().equals(dto.getId())
					|| DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(dto.getId())) {
				if(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(dto.getId()))
					dto.setText("计划类资产（利率型）");
				listFinprodType.add(dto);
			}
		}
		return listFinprodType;
	}

	/**
	 * 画面查询方法
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<RiskLevelImportDto> selectAll() throws Exception {
		Map<String, Object> selectParam = RequestUtil.getParameters(getRequest());
		Page<RiskLevelImportDto> page = riskLevelImport001Service.selectForPage(selectParam);
		for(RiskLevelImportDto riskLevelImportDto:page.getRoot()){
			riskLevelImportDto.setBondRiskType(SysDataUtil.getNameFromDataDict(DictCode.风险五级分类,riskLevelImportDto.getBondRiskType()));
			riskLevelImportDto.setAssetsType(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用,riskLevelImportDto.getAssetsType()));
			riskLevelImportDto.setUpdateTimeStr(DateUtil.format(riskLevelImportDto.getUpdateTime()));

		}
		return page;
	}




	@ResponseBody
	@RequestMapping(value = "check")
	public AjaxResult checkConfirmMsgForImportData(String fileName)  {
		return new AjaxResult();
	}


	/**
	 * 导入
	 *
	 * @param fileName
	 *            导入的文件名
	 * @return 处理结果
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("importFile")
	public List<ExcelValidDto> importData(String fileName) throws Exception {
		return this.riskLevelImport001Service.importData(fileName);
	}

	/**
	 * 文件导出 <功能详细描述>
	 * 
	 * @return ResponseEntity
	 * @throws Exception
	 *             Exception
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return riskLevelImport001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}

	/**
	 * 保存
	 *
	 * @param dto
	 *            dto
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("delete")
	public Boolean delete(@RequestBody RiskLevelImportDto dto) throws Exception {
 		 riskLevelImport001Service.delete(dto);
 		 return true;
	}
}
