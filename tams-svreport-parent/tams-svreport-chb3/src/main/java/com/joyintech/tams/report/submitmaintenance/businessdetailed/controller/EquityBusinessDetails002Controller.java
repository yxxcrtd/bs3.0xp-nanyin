package com.joyintech.tams.report.submitmaintenance.businessdetailed.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquiteBusiDetails001Dto;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.service.EquityBusinessDetailsMService;

@Controller
@PageId("SvrEquityBusinessDetails002")
@RequestMapping("/svreport/submitmaintenance/businessdetailed/SvrEquityBusinessDetails002")
public class EquityBusinessDetails002Controller extends BaseController{
	
	@Autowired
    private EquityBusinessDetailsMService equityBusinessDetailsMService;
	
	@Override
	public String init(Model model){
		super.setSelectOptionToModel(model, DictCode.业务种类,DictCode.币种_中债,DictCode.销售区域_中债,
				DictCode.投资者明细渠道,DictCode.是否_中债,DictCode.监管机构,DictCode.监管机构2);
		Map<String,Object>map=RequestUtil.getPageParam();
		//pageMode不为空且其不为新建时
        if(map.containsKey(GlobalConstant.DETAIL_PAGE_MODEL_KEY)&&
            !StringUtil.equals(map.get(GlobalConstant.DETAIL_PAGE_MODEL_KEY), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	EquiteBusiDetails001Dto entity = equityBusinessDetailsMService.getEntityByKey(map);
            model.addAttribute("entity", entity);
        }
		model.addAttribute("REGIST_CODE", JsonUtils.objectArray2Json(equityBusinessDetailsMService.queryAllRegistCode().toArray()));
		model.addAttribute("account", this.getParam("account"));
//		//业务发生所属地监管 SVR_BANK_REGU_BURE
//		IDictCode dictCode = DictCode.监管机构;
//		SelectDto selectDto = this.getSelectDataFromDataDic(dictCode, null);
//        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
//            List<SelectOptionDto> options = selectDto.getOptions();
//            for (int index = options.size() - 1; index >= 0; index-- ) {
//                SelectOptionDto option = options.get(index);
//                if (StringUtil.equals(option.getId(), "01")) {
//                    options.remove(option);
//                }
//            }
//            model.addAttribute("SVR_BANK_REGU_BURE2", JsonUtils.objectArray2Json(options.toArray()));
//        }
		return super.init(model);
	}
	
	/**
	 * 保存按钮点击是的check
	 * @param dto
	 * @param result
	 * @return
	 */
	@ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) EquiteBusiDetails001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
	
	@ResponseBody
	@RequestMapping("doSave")
	public AjaxResult doSave(@RequestBody EquiteBusiDetails001Dto dto){
		equityBusinessDetailsMService.doSaveOrUpdate(dto);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0006"));
	}
	
}
