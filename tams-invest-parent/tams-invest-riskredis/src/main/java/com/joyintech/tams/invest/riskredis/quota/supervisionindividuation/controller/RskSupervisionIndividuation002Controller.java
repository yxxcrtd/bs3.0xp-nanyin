package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.controller;

import java.math.BigDecimal;
import java.util.Map;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskSupervisionIndividuationDto;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.service.RskSupervisionIndividuation001Service;
/**
 * 个性化参数设置_条件设置新建
 * @author 王泽慧
 * @version 1.0 2019年8月16日
 * @see RskSupervisionIndividuation002Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionIndividuation002")
@RequestMapping("/invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation002")
public class RskSupervisionIndividuation002Controller extends BaseController{
	@Autowired
	private RskSupervisionIndividuation001Service rskSupervisionIndividuation001Service;
	private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] { BussinessDictCodeRds.控制类型, BussinessDictCodeRds.条件项
    };
	@Override
    public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		Map<String,Object> map = RequestUtil.getPageParam();
		String conditionItem = (String)map.get("conditionItem");
		String controlType = (String)map.get("controlType");
		String valueEnd = (String)map.get("valueEnd");
		String valueStr = (String)map.get("valueStr");
		String conditionId = (String)map.get("conditionId");
		model.addAttribute("CONTROL_TYPE_05", BussinessDictCodeListRds.控制类型_范围控制.getValue());
		model.addAttribute("CONDITION_ITEM_03", BussinessDictCodeListRds.条件项_建仓期时间.getValue());
		model.addAttribute("CONDITION_ITEM_02", BussinessDictCodeListRds.条件项_单位净值控制.getValue());
		model.addAttribute("VDATE", this.getParam("VDATE"));
		//编辑页面将数据赋给dto
		RskSupervisionIndividuationDto dto = new RskSupervisionIndividuationDto();
		if(StringUtil.isNotEmpty(this.getParam("conditionId"))){
			if(StringUtil.isNotEmpty(this.getParam("conditionId"))){
				dto.setConditionId(conditionId);
			}
			if(StringUtil.isNotEmpty(this.getParam("conditionItem"))){
				dto.setConditionItem(conditionItem);
			}
			if(StringUtil.equals(this.getParam("conditionItem"), BussinessDictCodeListRds.条件项_建仓期时间.getValue())){
				if(StringUtil.isNotEmpty(this.getParam("valueEnd"))){
					dto.setValueEnd(BigDecimal.valueOf(Long.parseLong(valueEnd)));
				}
				if(StringUtil.isNotEmpty(this.getParam("valueStr"))){
					dto.setValueStr(BigDecimal.valueOf(Long.parseLong(valueStr)));
					//dto.setValueStr(new BigDecimal(valueStr));
				}
			}else{
				if(StringUtil.isNotEmpty(this.getParam("valueEnd"))){
					dto.setValueEnd(new BigDecimal(valueEnd));
				}
				if(StringUtil.isNotEmpty(this.getParam("valueStr"))){
					dto.setValueStr(new BigDecimal(valueStr));
				}
			}
			if(StringUtil.isNotEmpty(this.getParam("controlType"))){
				dto.setControlType(controlType);
			}
		}
		else{
			dto.setConditionId(IDGenerator.generate());
		}
		dto.setIndId(this.getParam("indId"));
		dto.setPrtBagId(this.getParam("prtBagId"));
		model.addAttribute("entity", dto);
        return super.init(model);
    }
	
}
