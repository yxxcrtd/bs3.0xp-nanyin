package com.joyintech.tams.about.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

@Controller
@RequestMapping("/tamsAbout")
@PageId("TamsAbout")
public class TamsAboutController extends BaseController{

    @Value("${application.versionInfo.systemVersion}")
    private String systemVersion;

    @Value("${application.versionInfo.lastChromeVersion:73}")
    private String lastChromeVersion;
    
    @Value("${application.versionInfo.customerName}")
    private String customerName;
    
    @Value("${application.logoAbout}")
    private String logoAbout;

    @RequestMapping("init")
    public String init(Model model) {
        model.addAttribute("logoAbout", logoAbout);
        model.addAttribute("systemVersion",systemVersion);
        model.addAttribute("lastChromeVersion", this.getMessage("aplication.browser", lastChromeVersion));
        model.addAttribute("customerName",customerName);
        return "tamsAbout";
    }
}
