package com.joyintech.tams.exchange.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@PageId("TrdExchange006")
@RequestMapping("/invest/trade/investtrade/exchangesystem/TrdExchange006")
public class TrdExchange006Controller extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TrdExchange006Controller.class);

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        if (SecurityUtil.hasAuthenticated()){
            User user = SecurityUtil.getLoginUser();
            Date sysDate = DateUtil.platDateFromCache();
            model.addAttribute("username", user.getUserName());
            model.addAttribute("sysdate", sysDate);
        }
        return super.init(model);
    }

}
