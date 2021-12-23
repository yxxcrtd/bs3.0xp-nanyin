package com.joyintech.tams.exchange.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.DateUtil;

/**
 * @ClassName ExchangeSystemMonitorController
 * @Description TODO
 * @Author swwan
 * @Date 2020/8/26 10:00
 * @Version 1.0
 **/
@Controller
@PageId("SysExchangeMonitor001")
@RequestMapping("/invest/trade/investtrade/exchangesystem/SysExchangeMonitor001")
public class SysExchangeMonitor001Controller extends BaseController {

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
