package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.dao.RskIndividConditionLineMapper;
import com.joyintech.tams.common.base.entity.RskIndividConditionCustom;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividConditionCustomDto;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.service.RskSupervisionIndividuation001Service;

/**
 * 个性化参数设置-自定义条件新建
 * @author 代志伟
 * @version 1.0 2020年9月1日
 * @see RskSupervisionIndividuation007Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionIndividuation007")
@RequestMapping("/invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation007")
public class RskSupervisionIndividuation007Controller extends BaseController{
	
	@Autowired
	private RskSupervisionIndividuation001Service rskSupervisionIndividuation001Service;
	@Autowired
	private RskIndividConditionLineMapper rskIndividConditionLineMapper;


	private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] { BussinessDictCodeRds.单位,BussinessDictCodeRds.指标参数实现类型};
	@Override
    public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		String conditionItem = this.getParam("conditionItem");
		String conditionName = this.getParam("conditionName");
		String operateType = this.getParam("operateType");


		RskIndividConditionCustomDto dto = new RskIndividConditionCustomDto();
		if(StringUtil.isNotEmpty(conditionItem) && StringUtil.isNotEmpty(conditionName)){
			RskIndividConditionCustom rskIndividConditionCustom = rskSupervisionIndividuation001Service.findRskIndividConditionCustom(conditionItem);
			BeanUtil.copyPropertiesIgnoreNull(dto,rskIndividConditionCustom);
		}else{
			dto.setConditionType(BussinessDictCodeListRds.指标参数实现类型_SQL.getValue());
		}
		model.addAttribute("pageModel",this.getParam("pageModel"));
		model.addAttribute("entity", dto);
		model.addAttribute("operateType", operateType);
		return super.init(model);
	}

	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody RskIndividConditionCustomDto dto, BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 保存
	 * @param dto dto
	 * @return 结果
	 * @throws Exception 异常
	 */
	@DTXTransaction
	@ResponseBody
	@RequestMapping("save")
	public AjaxResult save(@RequestBody RskIndividConditionCustomDto dto) throws Exception {
		rskSupervisionIndividuation001Service.save(dto);
		String msg = "";
		if ("delete".equals(dto.getOperateType())){
			msg = "删除成功";
		}else {
			msg = "保存成功";
		}
		return new AjaxResult(msg);
	}
}
