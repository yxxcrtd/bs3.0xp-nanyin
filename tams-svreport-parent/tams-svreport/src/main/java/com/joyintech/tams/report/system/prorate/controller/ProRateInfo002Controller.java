package com.joyintech.tams.report.system.prorate.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.report.system.prorate.service.ProrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@PageId("ProRateInfo002")
@RequestMapping("/svreport/system/prorate/ProRateInfo002")
public class ProRateInfo002Controller extends BaseController {

    @Autowired
    private ProrateService prorateService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("checkAndSave")
    public AjaxResult checkAndSave(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProRateInfo001Dto dto, BindingResult result) {
        AjaxResult webResult = new AjaxResult();
        List<ProRateInfo001Dto> dtoList=new ArrayList<>();
        dtoList.add(dto);
        List<Integer> intList =prorateService.selectPrdFeeData(dtoList);
        return webResult;
    }



}
