package com.joyintech.tams.report.system.prorate.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@PageId("ProRateInfo001")
@RequestMapping("/svreport/system/prorate/ProRateInfo001")
public class ProRateInfo001Controller extends BaseController {

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }
}
