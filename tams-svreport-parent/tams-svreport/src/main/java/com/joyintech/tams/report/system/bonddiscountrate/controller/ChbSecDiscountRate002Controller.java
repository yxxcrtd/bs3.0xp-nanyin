package com.joyintech.tams.report.system.bonddiscountrate.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.system.bonddiscountrate.dto.ChbSecDiscountRateDto;
import com.joyintech.tams.report.system.bonddiscountrate.service.ChbSecDiscountRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@PageId("ChbSecDiscountRate002")
@RequestMapping("/svreport/system/bonddiscountrate/ChbSecDiscountRate002")
public class ChbSecDiscountRate002Controller extends BaseController {

    @Autowired
    private ChbSecDiscountRateService chbSecDiscountRateService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.中债债券类型, DictCode.债券类资产发行方式,DictCode.中债评级类型);
        ChbSecDiscountRateDto dto=new ChbSecDiscountRateDto();
        Map<String,Object> map= RequestUtil.getPageParam();
        if(!"add".equals(map.get("operateType"))){
            dto = chbSecDiscountRateService.selectOneDate(map);
            model.addAttribute("entity", dto);
        }else{
            model.addAttribute("entity",dto);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbSecDiscountRateDto dto, BindingResult result) {
        return commonCheck(result, "save");
    }

    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbSecDiscountRateDto dto, BindingResult result) {
        AjaxResult webResult = chbSecDiscountRateService.save(dto);
        return webResult;
    }

}
