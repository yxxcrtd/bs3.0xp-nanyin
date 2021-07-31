package com.joyintech.tams.invest.risk.quota.rskspecificassettype.contorller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.risk.quota.rskspecificassettype.dto.RskSpecificAssetTypeDto;
import com.joyintech.tams.invest.risk.quota.rskspecificassettype.service.RskSpecificAssetTypeService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 特定资产类型设置
 *
 * zhangnian  20200331
 */
@Controller
@PageId("RskSpecificAssetType001")
@RequestMapping("/invest/risk/quota/rskspecificassettype/RskSpecificAssetType001")
public class RskSpecificAssetType001Controller extends BaseController {

	@Autowired
	private RskSpecificAssetTypeService rskSpecificAssetTypeService;

	/**
	 * 初始化的查询条件
	 */
	private static final IDictCode[] DICT_CODES = new DictCode[] { DictCode.是否, DictCode.授权类型一级, DictCode.授权类型二级, DictCode.授权类型三级, DictCode.授权类型四级 };

	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		model.addAttribute("YES", DictCodeList.是否_是.getValue());
		model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
		return super.init(model);
	}

	/**
	 * 画面查询方法
	 * 
	 * @return 所有数据
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<RskSpecificAssetTypeDto> selectAll() throws Exception {
		Map<String, Object> map = RequestUtil.getParameters(getRequest());
		Page<RskSpecificAssetTypeDto> page = rskSpecificAssetTypeService.selectForPage(map);
		return page;
	}


	@ResponseBody
	@RequestMapping("getAssetIdOption")
	public Page<SelectOptionDto> getAssetIdOption(){
		Map<String,Object> param = RequestUtil.getPageParam();
		return rskSpecificAssetTypeService.getAssetIdOption(param);
	}

	/**
	 * 删除按钮点击时的check
	 * 
	 * @return check结果
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupDelete.class }) RskSpecificAssetTypeDto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@DTXTransaction
	@ResponseBody
	@RequestMapping(ACTION_DELETE)
	public AjaxResult delete(@RequestBody @Validated({ ValidateGroupDelete.class }) RskSpecificAssetTypeDto dto,
			BindingResult br) throws Exception {

		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		ProcessParameter<RskSpecificAssetTypeDto> processParameter = new ProcessParameter<RskSpecificAssetTypeDto>();
		WorkFlowParameter<RskSpecificAssetTypeDto> params = new WorkFlowParameter<RskSpecificAssetTypeDto>();
		params.setPageId("RskSpecificAssetType002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getSpecificId());
		params.setBusinessType(BussinessDictCodeListRds.共同业务类型_特定资产类型设置.getValue());
		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> "特定资产类型:" + "("+ entity.getSpecificId()+")"
		);
		processParameter.setWorkFlowParameter(params);
		String msg = rskSpecificAssetTypeService.start(processParameter,
				dto.getSelectedItem().toArray(new RskSpecificAssetTypeDto[dto.getSelectedItem().size()]));
		return new AjaxResult(msg);
	}

	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 */
//	@RequestMapping(ACTION_EXPORT)
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return rskSpecificAssetTypeService.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}

}
