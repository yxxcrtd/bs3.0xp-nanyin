package com.joyintech.tams.disclosure.information.config.bonuscalist.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@PageId("BonusCalist003")
@Controller
@RequestMapping("/disclosure/information/config/bonuscalist/BonusCalist003")
public class BonusCalist003Controller extends BaseController {

    @Override
    public String init(Model model){
        return super.init(model);
    }
}
