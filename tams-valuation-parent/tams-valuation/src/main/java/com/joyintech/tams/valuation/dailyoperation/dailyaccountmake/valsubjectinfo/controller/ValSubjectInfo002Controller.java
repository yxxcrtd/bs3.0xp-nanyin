package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.dto.ValSubjectInfo001Dto;

/**
 * 日志查看Controller 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValSubjectInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ValSubjectInfo002")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valsubjectinfo/ValSubjectInfo002")
public class ValSubjectInfo002Controller extends BaseController {

    @Override
    public String init(Model model) {
        String returnTxt = getParam("returnTxt");
        ValSubjectInfo001Dto dto = new ValSubjectInfo001Dto();
        dto.setReturnTxt(returnTxt);
        model.addAttribute("entity", dto);
        return super.init(model);
    }

}
